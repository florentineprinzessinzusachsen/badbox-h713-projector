package d.h0.i;

import e.r;
import e.s;
import e.t;
import java.io.EOFException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: Http2Stream.java */
/* JADX INFO: loaded from: classes.dex */
public final class i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    long f4550b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f4551c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final g f4552d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<d.h0.i.c> f4553e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f4554f;
    private final b g;
    final a h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f4549a = 0;
    final c i = new c();
    final c j = new c();
    d.h0.i.b k = null;

    /* JADX INFO: compiled from: Http2Stream.java */
    private final class b implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e.c f4559a = new e.c();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e.c f4560b = new e.c();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final long f4561c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f4562d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f4563e;

        b(long j) {
            this.f4561c = j;
        }

        private void a(long j) {
            i.this.f4552d.a(j);
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            long jQ;
            synchronized (i.this) {
                this.f4562d = true;
                jQ = this.f4560b.q();
                this.f4560b.a();
                i.this.notifyAll();
            }
            if (jQ > 0) {
                a(jQ);
            }
            i.this.a();
        }

        @Override // e.s
        public long read(e.c cVar, long j) throws n {
            d.h0.i.b bVar;
            long j2;
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            synchronized (i.this) {
                a();
                if (this.f4562d) {
                    throw new IOException("stream closed");
                }
                bVar = i.this.k;
                if (this.f4560b.q() > 0) {
                    j2 = this.f4560b.read(cVar, Math.min(j, this.f4560b.q()));
                    i.this.f4549a += j2;
                } else {
                    j2 = -1;
                }
                if (bVar == null && i.this.f4549a >= i.this.f4552d.n.c() / 2) {
                    i.this.f4552d.a(i.this.f4551c, i.this.f4549a);
                    i.this.f4549a = 0L;
                }
            }
            if (j2 != -1) {
                a(j2);
                return j2;
            }
            if (bVar == null) {
                return -1L;
            }
            throw new n(bVar);
        }

        @Override // e.s
        public t timeout() {
            return i.this.i;
        }

        private void a() throws IOException {
            i.this.i.g();
            while (this.f4560b.q() == 0 && !this.f4563e && !this.f4562d && i.this.k == null) {
                try {
                    i.this.k();
                } catch (Throwable th) {
                    i.this.i.k();
                    throw th;
                }
            }
            i.this.i.k();
        }

        void a(e.e eVar, long j) throws EOFException {
            boolean z;
            boolean z2;
            boolean z3;
            while (j > 0) {
                synchronized (i.this) {
                    z = this.f4563e;
                    z2 = true;
                    z3 = this.f4560b.q() + j > this.f4561c;
                }
                if (z3) {
                    eVar.skip(j);
                    i.this.b(d.h0.i.b.FLOW_CONTROL_ERROR);
                    return;
                }
                if (z) {
                    eVar.skip(j);
                    return;
                }
                long j2 = eVar.read(this.f4559a, j);
                if (j2 != -1) {
                    j -= j2;
                    synchronized (i.this) {
                        if (this.f4560b.q() != 0) {
                            z2 = false;
                        }
                        this.f4560b.a((s) this.f4559a);
                        if (z2) {
                            i.this.notifyAll();
                        }
                    }
                } else {
                    throw new EOFException();
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    class c extends e.a {
        c() {
        }

        @Override // e.a
        protected IOException b(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // e.a
        protected void i() {
            i.this.b(d.h0.i.b.CANCEL);
        }

        public void k() throws IOException {
            if (h()) {
                throw b((IOException) null);
            }
        }
    }

    i(int i, g gVar, boolean z, boolean z2, List<d.h0.i.c> list) {
        if (gVar == null) {
            throw new NullPointerException("connection == null");
        }
        if (list == null) {
            throw new NullPointerException("requestHeaders == null");
        }
        this.f4551c = i;
        this.f4552d = gVar;
        this.f4550b = gVar.o.c();
        this.g = new b(gVar.n.c());
        this.h = new a();
        this.g.f4563e = z2;
        this.h.f4557c = z;
    }

    public void a(d.h0.i.b bVar) {
        if (d(bVar)) {
            this.f4552d.b(this.f4551c, bVar);
        }
    }

    public void b(d.h0.i.b bVar) {
        if (d(bVar)) {
            this.f4552d.c(this.f4551c, bVar);
        }
    }

    public int c() {
        return this.f4551c;
    }

    public r d() {
        synchronized (this) {
            if (!this.f4554f && !f()) {
                throw new IllegalStateException("reply before requesting the sink");
            }
        }
        return this.h;
    }

    public s e() {
        return this.g;
    }

    public boolean f() {
        return this.f4552d.f4493a == ((this.f4551c & 1) == 1);
    }

    public synchronized boolean g() {
        if (this.k != null) {
            return false;
        }
        return ((this.g.f4563e || this.g.f4562d) && (this.h.f4557c || this.h.f4556b) && this.f4554f) ? false : true;
    }

    public t h() {
        return this.i;
    }

    void i() {
        boolean zG;
        synchronized (this) {
            this.g.f4563e = true;
            zG = g();
            notifyAll();
        }
        if (zG) {
            return;
        }
        this.f4552d.c(this.f4551c);
    }

    public synchronized List<d.h0.i.c> j() {
        List<d.h0.i.c> list;
        if (!f()) {
            throw new IllegalStateException("servers cannot read response headers");
        }
        this.i.g();
        while (this.f4553e == null && this.k == null) {
            try {
                k();
            } catch (Throwable th) {
                this.i.k();
                throw th;
            }
        }
        this.i.k();
        list = this.f4553e;
        if (list == null) {
            throw new n(this.k);
        }
        this.f4553e = null;
        return list;
    }

    void k() throws InterruptedIOException {
        try {
            wait();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException();
        }
    }

    public t l() {
        return this.j;
    }

    synchronized void c(d.h0.i.b bVar) {
        if (this.k == null) {
            this.k = bVar;
            notifyAll();
        }
    }

    /* JADX INFO: compiled from: Http2Stream.java */
    final class a implements r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e.c f4555a = new e.c();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f4556b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f4557c;

        a() {
        }

        @Override // e.r
        public void a(e.c cVar, long j) throws IOException {
            this.f4555a.a(cVar, j);
            while (this.f4555a.q() >= 16384) {
                a(false);
            }
        }

        @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (i.this) {
                if (this.f4556b) {
                    return;
                }
                if (!i.this.h.f4557c) {
                    if (this.f4555a.q() > 0) {
                        while (this.f4555a.q() > 0) {
                            a(true);
                        }
                    } else {
                        i iVar = i.this;
                        iVar.f4552d.a(iVar.f4551c, true, (e.c) null, 0L);
                    }
                }
                synchronized (i.this) {
                    this.f4556b = true;
                }
                i.this.f4552d.flush();
                i.this.a();
            }
        }

        @Override // e.r, java.io.Flushable
        public void flush() throws IOException {
            synchronized (i.this) {
                i.this.b();
            }
            while (this.f4555a.q() > 0) {
                a(false);
                i.this.f4552d.flush();
            }
        }

        @Override // e.r
        public t timeout() {
            return i.this.j;
        }

        private void a(boolean z) throws IOException {
            long jMin;
            synchronized (i.this) {
                i.this.j.g();
                while (i.this.f4550b <= 0 && !this.f4557c && !this.f4556b && i.this.k == null) {
                    try {
                        i.this.k();
                    } catch (Throwable th) {
                        i.this.j.k();
                        throw th;
                    }
                }
                i.this.j.k();
                i.this.b();
                jMin = Math.min(i.this.f4550b, this.f4555a.q());
                i.this.f4550b -= jMin;
            }
            i.this.j.g();
            try {
                i.this.f4552d.a(i.this.f4551c, z && jMin == this.f4555a.q(), this.f4555a, jMin);
            } finally {
                i.this.j.k();
            }
        }
    }

    void a(List<d.h0.i.c> list) {
        boolean zG;
        synchronized (this) {
            zG = true;
            this.f4554f = true;
            if (this.f4553e == null) {
                this.f4553e = list;
                zG = g();
                notifyAll();
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(this.f4553e);
                arrayList.add(null);
                arrayList.addAll(list);
                this.f4553e = arrayList;
            }
        }
        if (zG) {
            return;
        }
        this.f4552d.c(this.f4551c);
    }

    void b() throws IOException {
        a aVar = this.h;
        if (!aVar.f4556b) {
            if (!aVar.f4557c) {
                d.h0.i.b bVar = this.k;
                if (bVar != null) {
                    throw new n(bVar);
                }
                return;
            }
            throw new IOException("stream finished");
        }
        throw new IOException("stream closed");
    }

    private boolean d(d.h0.i.b bVar) {
        synchronized (this) {
            if (this.k != null) {
                return false;
            }
            if (this.g.f4563e && this.h.f4557c) {
                return false;
            }
            this.k = bVar;
            notifyAll();
            this.f4552d.c(this.f4551c);
            return true;
        }
    }

    void a(e.e eVar, int i) throws EOFException {
        this.g.a(eVar, i);
    }

    void a() {
        boolean z;
        boolean zG;
        synchronized (this) {
            z = !this.g.f4563e && this.g.f4562d && (this.h.f4557c || this.h.f4556b);
            zG = g();
        }
        if (z) {
            a(d.h0.i.b.CANCEL);
        } else {
            if (zG) {
                return;
            }
            this.f4552d.c(this.f4551c);
        }
    }

    void a(long j) {
        this.f4550b += j;
        if (j > 0) {
            notifyAll();
        }
    }
}
