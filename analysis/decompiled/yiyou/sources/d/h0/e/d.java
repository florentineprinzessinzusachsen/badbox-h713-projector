package d.h0.e;

import e.l;
import e.r;
import e.s;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: DiskLruCache.java */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Closeable, Flushable {
    static final Pattern u = Pattern.compile("[a-z0-9_-]{1,120}");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final d.h0.j.a f4358a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final File f4359b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final File f4360c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final File f4361d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final File f4362e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f4363f;
    private long g;
    final int h;
    e.d j;
    int l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    boolean q;
    private final Executor s;
    private long i = 0;
    final LinkedHashMap<String, C0098d> k = new LinkedHashMap<>(0, 0.75f, true);
    private long r = 0;
    private final Runnable t = new a();

    /* JADX INFO: compiled from: DiskLruCache.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (d.this) {
                if ((!d.this.n) || d.this.o) {
                    return;
                }
                try {
                    d.this.p();
                } catch (IOException unused) {
                    d.this.p = true;
                }
                try {
                    if (d.this.n()) {
                        d.this.o();
                        d.this.l = 0;
                    }
                } catch (IOException unused2) {
                    d.this.q = true;
                    d.this.j = l.a(l.a());
                }
            }
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    class b extends d.h0.e.e {
        b(r rVar) {
            super(rVar);
        }

        @Override // d.h0.e.e
        protected void a(IOException iOException) {
            d.this.m = true;
        }
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class e implements Closeable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final String f4377a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final long f4378b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final s[] f4379c;

        e(String str, long j, s[] sVarArr, long[] jArr) {
            this.f4377a = str;
            this.f4378b = j;
            this.f4379c = sVarArr;
        }

        public c a() {
            return d.this.a(this.f4377a, this.f4378b);
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            for (s sVar : this.f4379c) {
                d.h0.c.a(sVar);
            }
        }

        public s a(int i) {
            return this.f4379c[i];
        }
    }

    d(d.h0.j.a aVar, File file, int i, int i2, long j, Executor executor) {
        this.f4358a = aVar;
        this.f4359b = file;
        this.f4363f = i;
        this.f4360c = new File(file, "journal");
        this.f4361d = new File(file, "journal.tmp");
        this.f4362e = new File(file, "journal.bkp");
        this.h = i2;
        this.g = j;
        this.s = executor;
    }

    public static d a(d.h0.j.a aVar, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        if (i2 > 0) {
            return new d(aVar, file, i, i2, j, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), d.h0.c.a("OkHttp DiskLruCache", true)));
        }
        throw new IllegalArgumentException("valueCount <= 0");
    }

    private void e(String str) throws IOException {
        String strSubstring;
        int iIndexOf = str.indexOf(32);
        if (iIndexOf == -1) {
            throw new IOException("unexpected journal line: " + str);
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(32, i);
        if (iIndexOf2 == -1) {
            strSubstring = str.substring(i);
            if (iIndexOf == 6 && str.startsWith("REMOVE")) {
                this.k.remove(strSubstring);
                return;
            }
        } else {
            strSubstring = str.substring(i, iIndexOf2);
        }
        C0098d c0098d = this.k.get(strSubstring);
        if (c0098d == null) {
            c0098d = new C0098d(strSubstring);
            this.k.put(strSubstring, c0098d);
        }
        if (iIndexOf2 != -1 && iIndexOf == 5 && str.startsWith("CLEAN")) {
            String[] strArrSplit = str.substring(iIndexOf2 + 1).split(" ");
            c0098d.f4375e = true;
            c0098d.f4376f = null;
            c0098d.a(strArrSplit);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 5 && str.startsWith("DIRTY")) {
            c0098d.f4376f = new c(c0098d);
            return;
        }
        if (iIndexOf2 == -1 && iIndexOf == 4 && str.startsWith("READ")) {
            return;
        }
        throw new IOException("unexpected journal line: " + str);
    }

    private void f(String str) {
        if (u.matcher(str).matches()) {
            return;
        }
        throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,120}: \"" + str + "\"");
    }

    private synchronized void q() {
        if (m()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    private e.d r() {
        return l.a(new b(this.f4358a.e(this.f4360c)));
    }

    private void s() {
        this.f4358a.a(this.f4361d);
        Iterator<C0098d> it = this.k.values().iterator();
        while (it.hasNext()) {
            C0098d next = it.next();
            int i = 0;
            if (next.f4376f == null) {
                while (i < this.h) {
                    this.i += next.f4372b[i];
                    i++;
                }
            } else {
                next.f4376f = null;
                while (i < this.h) {
                    this.f4358a.a(next.f4373c[i]);
                    this.f4358a.a(next.f4374d[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    private void t() {
        e.e eVarA = l.a(this.f4358a.b(this.f4360c));
        try {
            String strG = eVarA.g();
            String strG2 = eVarA.g();
            String strG3 = eVarA.g();
            String strG4 = eVarA.g();
            String strG5 = eVarA.g();
            if (!"libcore.io.DiskLruCache".equals(strG) || !"1".equals(strG2) || !Integer.toString(this.f4363f).equals(strG3) || !Integer.toString(this.h).equals(strG4) || !"".equals(strG5)) {
                throw new IOException("unexpected journal header: [" + strG + ", " + strG2 + ", " + strG4 + ", " + strG5 + "]");
            }
            int i = 0;
            while (true) {
                try {
                    e(eVarA.g());
                    i++;
                } catch (EOFException unused) {
                    this.l = i - this.k.size();
                    if (eVarA.j()) {
                        this.j = r();
                    } else {
                        o();
                    }
                    d.h0.c.a(eVarA);
                    return;
                }
            }
        } catch (Throwable th) {
            d.h0.c.a(eVarA);
            throw th;
        }
    }

    public synchronized void b() {
        if (this.n) {
            return;
        }
        if (this.f4358a.f(this.f4362e)) {
            if (this.f4358a.f(this.f4360c)) {
                this.f4358a.a(this.f4362e);
            } else {
                this.f4358a.a(this.f4362e, this.f4360c);
            }
        }
        if (this.f4358a.f(this.f4360c)) {
            try {
                t();
                s();
                this.n = true;
                return;
            } catch (IOException e2) {
                d.h0.k.f.d().a(5, "DiskLruCache " + this.f4359b + " is corrupt: " + e2.getMessage() + ", removing", e2);
                try {
                    a();
                    this.o = false;
                    o();
                    this.n = true;
                } catch (Throwable th) {
                    this.o = false;
                    throw th;
                }
            }
        }
        o();
        this.n = true;
    }

    public synchronized e c(String str) {
        b();
        q();
        f(str);
        C0098d c0098d = this.k.get(str);
        if (c0098d != null && c0098d.f4375e) {
            e eVarA = c0098d.a();
            if (eVarA == null) {
                return null;
            }
            this.l++;
            this.j.b("READ").writeByte(32).b(str).writeByte(10);
            if (n()) {
                this.s.execute(this.t);
            }
            return eVarA;
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        if (this.n && !this.o) {
            for (C0098d c0098d : (C0098d[]) this.k.values().toArray(new C0098d[this.k.size()])) {
                if (c0098d.f4376f != null) {
                    c0098d.f4376f.a();
                }
            }
            p();
            this.j.close();
            this.j = null;
            this.o = true;
            return;
        }
        this.o = true;
    }

    public synchronized boolean d(String str) {
        b();
        q();
        f(str);
        C0098d c0098d = this.k.get(str);
        if (c0098d == null) {
            return false;
        }
        boolean zA = a(c0098d);
        if (zA && this.i <= this.g) {
            this.p = false;
        }
        return zA;
    }

    @Override // java.io.Flushable
    public synchronized void flush() {
        if (this.n) {
            q();
            p();
            this.j.flush();
        }
    }

    public synchronized boolean m() {
        return this.o;
    }

    boolean n() {
        int i = this.l;
        return i >= 2000 && i >= this.k.size();
    }

    synchronized void o() {
        if (this.j != null) {
            this.j.close();
        }
        e.d dVarA = l.a(this.f4358a.c(this.f4361d));
        try {
            dVarA.b("libcore.io.DiskLruCache").writeByte(10);
            dVarA.b("1").writeByte(10);
            dVarA.h(this.f4363f).writeByte(10);
            dVarA.h(this.h).writeByte(10);
            dVarA.writeByte(10);
            for (C0098d c0098d : this.k.values()) {
                if (c0098d.f4376f != null) {
                    dVarA.b("DIRTY").writeByte(32);
                    dVarA.b(c0098d.f4371a);
                    dVarA.writeByte(10);
                } else {
                    dVarA.b("CLEAN").writeByte(32);
                    dVarA.b(c0098d.f4371a);
                    c0098d.a(dVarA);
                    dVarA.writeByte(10);
                }
            }
            dVarA.close();
            if (this.f4358a.f(this.f4360c)) {
                this.f4358a.a(this.f4360c, this.f4362e);
            }
            this.f4358a.a(this.f4361d, this.f4360c);
            this.f4358a.a(this.f4362e);
            this.j = r();
            this.m = false;
            this.q = false;
        } catch (Throwable th) {
            dVarA.close();
            throw th;
        }
    }

    void p() {
        while (this.i > this.g) {
            a(this.k.values().iterator().next());
        }
        this.p = false;
    }

    /* JADX INFO: renamed from: d.h0.e.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DiskLruCache.java */
    private final class C0098d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f4371a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long[] f4372b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final File[] f4373c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final File[] f4374d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f4375e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        c f4376f;
        long g;

        C0098d(String str) {
            this.f4371a = str;
            int i = d.this.h;
            this.f4372b = new long[i];
            this.f4373c = new File[i];
            this.f4374d = new File[i];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i2 = 0; i2 < d.this.h; i2++) {
                sb.append(i2);
                this.f4373c[i2] = new File(d.this.f4359b, sb.toString());
                sb.append(".tmp");
                this.f4374d[i2] = new File(d.this.f4359b, sb.toString());
                sb.setLength(length);
            }
        }

        private IOException b(String[] strArr) throws IOException {
            throw new IOException("unexpected journal line: " + Arrays.toString(strArr));
        }

        void a(String[] strArr) throws IOException {
            if (strArr.length != d.this.h) {
                b(strArr);
                throw null;
            }
            for (int i = 0; i < strArr.length; i++) {
                try {
                    this.f4372b[i] = Long.parseLong(strArr[i]);
                } catch (NumberFormatException unused) {
                    b(strArr);
                    throw null;
                }
            }
        }

        void a(e.d dVar) {
            for (long j : this.f4372b) {
                dVar.writeByte(32).h(j);
            }
        }

        e a() {
            if (Thread.holdsLock(d.this)) {
                s[] sVarArr = new s[d.this.h];
                long[] jArr = (long[]) this.f4372b.clone();
                for (int i = 0; i < d.this.h; i++) {
                    try {
                        sVarArr[i] = d.this.f4358a.b(this.f4373c[i]);
                    } catch (FileNotFoundException unused) {
                        for (int i2 = 0; i2 < d.this.h && sVarArr[i2] != null; i2++) {
                            d.h0.c.a(sVarArr[i2]);
                        }
                        try {
                            d.this.a(this);
                            return null;
                        } catch (IOException unused2) {
                            return null;
                        }
                    }
                }
                return d.this.new e(this.f4371a, this.g, sVarArr, jArr);
            }
            throw new AssertionError();
        }
    }

    public c a(String str) {
        return a(str, -1L);
    }

    synchronized c a(String str, long j) {
        b();
        q();
        f(str);
        C0098d c0098d = this.k.get(str);
        if (j != -1 && (c0098d == null || c0098d.g != j)) {
            return null;
        }
        if (c0098d != null && c0098d.f4376f != null) {
            return null;
        }
        if (!this.p && !this.q) {
            this.j.b("DIRTY").writeByte(32).b(str).writeByte(10);
            this.j.flush();
            if (this.m) {
                return null;
            }
            if (c0098d == null) {
                c0098d = new C0098d(str);
                this.k.put(str, c0098d);
            }
            c cVar = new c(c0098d);
            c0098d.f4376f = cVar;
            return cVar;
        }
        this.s.execute(this.t);
        return null;
    }

    /* JADX INFO: compiled from: DiskLruCache.java */
    public final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final C0098d f4366a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean[] f4367b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f4368c;

        /* JADX INFO: compiled from: DiskLruCache.java */
        class a extends d.h0.e.e {
            a(r rVar) {
                super(rVar);
            }

            @Override // d.h0.e.e
            protected void a(IOException iOException) {
                synchronized (d.this) {
                    c.this.c();
                }
            }
        }

        c(C0098d c0098d) {
            this.f4366a = c0098d;
            this.f4367b = c0098d.f4375e ? null : new boolean[d.this.h];
        }

        public r a(int i) {
            synchronized (d.this) {
                if (this.f4368c) {
                    throw new IllegalStateException();
                }
                if (this.f4366a.f4376f != this) {
                    return l.a();
                }
                if (!this.f4366a.f4375e) {
                    this.f4367b[i] = true;
                }
                try {
                    return new a(d.this.f4358a.c(this.f4366a.f4374d[i]));
                } catch (FileNotFoundException unused) {
                    return l.a();
                }
            }
        }

        public void b() {
            synchronized (d.this) {
                if (this.f4368c) {
                    throw new IllegalStateException();
                }
                if (this.f4366a.f4376f == this) {
                    d.this.a(this, true);
                }
                this.f4368c = true;
            }
        }

        void c() {
            if (this.f4366a.f4376f != this) {
                return;
            }
            int i = 0;
            while (true) {
                d dVar = d.this;
                if (i >= dVar.h) {
                    this.f4366a.f4376f = null;
                    return;
                } else {
                    try {
                        dVar.f4358a.a(this.f4366a.f4374d[i]);
                    } catch (IOException unused) {
                    }
                    i++;
                }
            }
        }

        public void a() {
            synchronized (d.this) {
                if (!this.f4368c) {
                    if (this.f4366a.f4376f == this) {
                        d.this.a(this, false);
                    }
                    this.f4368c = true;
                } else {
                    throw new IllegalStateException();
                }
            }
        }
    }

    synchronized void a(c cVar, boolean z) {
        C0098d c0098d = cVar.f4366a;
        if (c0098d.f4376f == cVar) {
            if (z && !c0098d.f4375e) {
                for (int i = 0; i < this.h; i++) {
                    if (cVar.f4367b[i]) {
                        if (!this.f4358a.f(c0098d.f4374d[i])) {
                            cVar.a();
                            return;
                        }
                    } else {
                        cVar.a();
                        throw new IllegalStateException("Newly created entry didn't create value for index " + i);
                    }
                }
            }
            for (int i2 = 0; i2 < this.h; i2++) {
                File file = c0098d.f4374d[i2];
                if (z) {
                    if (this.f4358a.f(file)) {
                        File file2 = c0098d.f4373c[i2];
                        this.f4358a.a(file, file2);
                        long j = c0098d.f4372b[i2];
                        long jG = this.f4358a.g(file2);
                        c0098d.f4372b[i2] = jG;
                        this.i = (this.i - j) + jG;
                    }
                } else {
                    this.f4358a.a(file);
                }
            }
            this.l++;
            c0098d.f4376f = null;
            if (c0098d.f4375e | z) {
                c0098d.f4375e = true;
                this.j.b("CLEAN").writeByte(32);
                this.j.b(c0098d.f4371a);
                c0098d.a(this.j);
                this.j.writeByte(10);
                if (z) {
                    long j2 = this.r;
                    this.r = 1 + j2;
                    c0098d.g = j2;
                }
            } else {
                this.k.remove(c0098d.f4371a);
                this.j.b("REMOVE").writeByte(32);
                this.j.b(c0098d.f4371a);
                this.j.writeByte(10);
            }
            this.j.flush();
            if (this.i > this.g || n()) {
                this.s.execute(this.t);
            }
            return;
        }
        throw new IllegalStateException();
    }

    boolean a(C0098d c0098d) {
        c cVar = c0098d.f4376f;
        if (cVar != null) {
            cVar.c();
        }
        for (int i = 0; i < this.h; i++) {
            this.f4358a.a(c0098d.f4373c[i]);
            long j = this.i;
            long[] jArr = c0098d.f4372b;
            this.i = j - jArr[i];
            jArr[i] = 0;
        }
        this.l++;
        this.j.b("REMOVE").writeByte(32).b(c0098d.f4371a).writeByte(10);
        this.k.remove(c0098d.f4371a);
        if (n()) {
            this.s.execute(this.t);
        }
        return true;
    }

    public void a() {
        close();
        this.f4358a.d(this.f4359b);
    }
}
