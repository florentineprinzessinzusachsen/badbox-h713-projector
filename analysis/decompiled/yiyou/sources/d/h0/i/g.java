package d.h0.i;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Closeable {
    private static final ExecutorService u = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), d.h0.c.a("OkHttp Http2Connection", true));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final boolean f4493a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final h f4494b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final String f4496d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f4497e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f4498f;
    boolean g;
    private final ScheduledExecutorService h;
    private final ExecutorService i;
    final l j;
    private boolean k;
    long m;
    final Socket q;
    final d.h0.i.j r;
    final j s;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Map<Integer, d.h0.i.i> f4495c = new LinkedHashMap();
    long l = 0;
    m n = new m();
    final m o = new m();
    boolean p = false;
    final Set<Integer> t = new LinkedHashSet();

    /* JADX INFO: compiled from: Http2Connection.java */
    class a extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4499b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d.h0.i.b f4500c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        a(String str, Object[] objArr, int i, d.h0.i.b bVar) {
            super(str, objArr);
            this.f4499b = i;
            this.f4500c = bVar;
        }

        @Override // d.h0.b
        public void b() {
            try {
                g.this.b(this.f4499b, this.f4500c);
            } catch (IOException unused) {
                g.this.o();
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class b extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4502b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ long f4503c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        b(String str, Object[] objArr, int i, long j) {
            super(str, objArr);
            this.f4502b = i;
            this.f4503c = j;
        }

        @Override // d.h0.b
        public void b() {
            try {
                g.this.r.a(this.f4502b, this.f4503c);
            } catch (IOException unused) {
                g.this.o();
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class c extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4505b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ List f4506c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        c(String str, Object[] objArr, int i, List list) {
            super(str, objArr);
            this.f4505b = i;
            this.f4506c = list;
        }

        @Override // d.h0.b
        public void b() {
            if (g.this.j.a(this.f4505b, this.f4506c)) {
                try {
                    g.this.r.a(this.f4505b, d.h0.i.b.CANCEL);
                    synchronized (g.this) {
                        try {
                            g.this.t.remove(Integer.valueOf(this.f4505b));
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } catch (IOException unused) {
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class d extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4508b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ List f4509c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ boolean f4510d;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        d(String str, Object[] objArr, int i, List list, boolean z) {
            super(str, objArr);
            this.f4508b = i;
            this.f4509c = list;
            this.f4510d = z;
        }

        @Override // d.h0.b
        public void b() {
            boolean zA = g.this.j.a(this.f4508b, this.f4509c, this.f4510d);
            if (zA) {
                try {
                    g.this.r.a(this.f4508b, d.h0.i.b.CANCEL);
                } catch (IOException unused) {
                    return;
                }
            }
            if (zA || this.f4510d) {
                synchronized (g.this) {
                    try {
                        g.this.t.remove(Integer.valueOf(this.f4508b));
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class e extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4512b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ e.c f4513c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f4514d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ boolean f4515e;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        e(String str, Object[] objArr, int i, e.c cVar, int i2, boolean z) {
            super(str, objArr);
            this.f4512b = i;
            this.f4513c = cVar;
            this.f4514d = i2;
            this.f4515e = z;
        }

        @Override // d.h0.b
        public void b() {
            try {
                boolean zA = g.this.j.a(this.f4512b, this.f4513c, this.f4514d, this.f4515e);
                if (zA) {
                    g.this.r.a(this.f4512b, d.h0.i.b.CANCEL);
                }
                if (zA || this.f4515e) {
                    synchronized (g.this) {
                        g.this.t.remove(Integer.valueOf(this.f4512b));
                    }
                }
            } catch (IOException unused) {
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class f extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4517b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ d.h0.i.b f4518c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        f(String str, Object[] objArr, int i, d.h0.i.b bVar) {
            super(str, objArr);
            this.f4517b = i;
            this.f4518c = bVar;
        }

        @Override // d.h0.b
        public void b() {
            g.this.j.a(this.f4517b, this.f4518c);
            synchronized (g.this) {
                g.this.t.remove(Integer.valueOf(this.f4517b));
            }
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    public static abstract class h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final h f4526a = new a();

        /* JADX INFO: compiled from: Http2Connection.java */
        class a extends h {
            a() {
            }

            @Override // d.h0.i.g.h
            public void a(d.h0.i.i iVar) {
                iVar.a(d.h0.i.b.REFUSED_STREAM);
            }
        }

        public void a(g gVar) {
        }

        public abstract void a(d.h0.i.i iVar);
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    final class i extends d.h0.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f4527b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f4528c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final int f4529d;

        i(boolean z, int i, int i2) {
            super("OkHttp %s ping %08x%08x", g.this.f4496d, Integer.valueOf(i), Integer.valueOf(i2));
            this.f4527b = z;
            this.f4528c = i;
            this.f4529d = i2;
        }

        @Override // d.h0.b
        public void b() {
            g.this.a(this.f4527b, this.f4528c, this.f4529d);
        }
    }

    g(C0100g c0100g) {
        this.j = c0100g.f4525f;
        boolean z = c0100g.g;
        this.f4493a = z;
        this.f4494b = c0100g.f4524e;
        this.f4498f = z ? 1 : 2;
        if (c0100g.g) {
            this.f4498f += 2;
        }
        if (c0100g.g) {
            this.n.a(7, 16777216);
        }
        this.f4496d = c0100g.f4521b;
        this.h = new ScheduledThreadPoolExecutor(1, d.h0.c.a(d.h0.c.a("OkHttp %s Writer", this.f4496d), false));
        if (c0100g.h != 0) {
            ScheduledExecutorService scheduledExecutorService = this.h;
            i iVar = new i(false, 0, 0);
            int i2 = c0100g.h;
            scheduledExecutorService.scheduleAtFixedRate(iVar, i2, i2, TimeUnit.MILLISECONDS);
        }
        this.i = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), d.h0.c.a(d.h0.c.a("OkHttp %s Push Observer", this.f4496d), true));
        this.o.a(7, 65535);
        this.o.a(5, 16384);
        this.m = this.o.c();
        this.q = c0100g.f4520a;
        this.r = new d.h0.i.j(c0100g.f4523d, this.f4493a);
        this.s = new j(new d.h0.i.h(c0100g.f4522c, this.f4493a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        try {
            a(d.h0.i.b.PROTOCOL_ERROR, d.h0.i.b.PROTOCOL_ERROR);
        } catch (IOException unused) {
        }
    }

    boolean b(int i2) {
        return i2 != 0 && (i2 & 1) == 0;
    }

    synchronized d.h0.i.i c(int i2) {
        d.h0.i.i iVarRemove;
        iVarRemove = this.f4495c.remove(Integer.valueOf(i2));
        notifyAll();
        return iVarRemove;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        a(d.h0.i.b.NO_ERROR, d.h0.i.b.CANCEL);
    }

    public void flush() {
        this.r.flush();
    }

    public void m() {
        a(true);
    }

    public synchronized int b() {
        return this.o.b(Integer.MAX_VALUE);
    }

    private d.h0.i.i b(int i2, List<d.h0.i.c> list, boolean z) {
        int i3;
        d.h0.i.i iVar;
        boolean z2;
        boolean z3 = !z;
        synchronized (this.r) {
            synchronized (this) {
                if (this.f4498f > 1073741823) {
                    a(d.h0.i.b.REFUSED_STREAM);
                }
                if (!this.g) {
                    i3 = this.f4498f;
                    this.f4498f += 2;
                    iVar = new d.h0.i.i(i3, this, z3, false, list);
                    z2 = !z || this.m == 0 || iVar.f4550b == 0;
                    if (iVar.g()) {
                        this.f4495c.put(Integer.valueOf(i3), iVar);
                    }
                } else {
                    throw new d.h0.i.a();
                }
            }
            if (i2 == 0) {
                this.r.a(z3, i3, i2, list);
            } else if (!this.f4493a) {
                this.r.a(i2, i3, list);
            } else {
                throw new IllegalArgumentException("client streams shouldn't have associated stream IDs");
            }
        }
        if (z2) {
            this.r.flush();
        }
        return iVar;
    }

    synchronized d.h0.i.i a(int i2) {
        return this.f4495c.get(Integer.valueOf(i2));
    }

    /* JADX INFO: renamed from: d.h0.i.g$g, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Http2Connection.java */
    public static class C0100g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Socket f4520a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f4521b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        e.e f4522c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        e.d f4523d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        h f4524e = h.f4526a;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        l f4525f = l.f4578a;
        boolean g;
        int h;

        public C0100g(boolean z) {
            this.g = z;
        }

        public C0100g a(Socket socket, String str, e.e eVar, e.d dVar) {
            this.f4520a = socket;
            this.f4521b = str;
            this.f4522c = eVar;
            this.f4523d = dVar;
            return this;
        }

        public C0100g a(h hVar) {
            this.f4524e = hVar;
            return this;
        }

        public C0100g a(int i) {
            this.h = i;
            return this;
        }

        public g a() {
            return new g(this);
        }
    }

    synchronized void a(long j2) {
        this.l += j2;
        if (this.l >= this.n.c() / 2) {
            a(0, this.l);
            this.l = 0L;
        }
    }

    void c(int i2, d.h0.i.b bVar) {
        try {
            this.h.execute(new a("OkHttp %s stream %d", new Object[]{this.f4496d, Integer.valueOf(i2)}, i2, bVar));
        } catch (RejectedExecutionException unused) {
        }
    }

    /* JADX INFO: compiled from: Http2Connection.java */
    class j extends d.h0.b implements d.h0.i.h.b {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final d.h0.i.h f4531b;

        /* JADX INFO: compiled from: Http2Connection.java */
        class a extends d.h0.b {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ d.h0.i.i f4533b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            a(String str, Object[] objArr, d.h0.i.i iVar) {
                super(str, objArr);
                this.f4533b = iVar;
            }

            @Override // d.h0.b
            public void b() {
                try {
                    g.this.f4494b.a(this.f4533b);
                } catch (IOException e2) {
                    d.h0.k.f.d().a(4, "Http2Connection.Listener failure for " + g.this.f4496d, e2);
                    try {
                        this.f4533b.a(d.h0.i.b.PROTOCOL_ERROR);
                    } catch (IOException unused) {
                    }
                }
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        class b extends d.h0.b {
            b(String str, Object... objArr) {
                super(str, objArr);
            }

            @Override // d.h0.b
            public void b() {
                g gVar = g.this;
                gVar.f4494b.a(gVar);
            }
        }

        /* JADX INFO: compiled from: Http2Connection.java */
        class c extends d.h0.b {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ m f4536b;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            c(String str, Object[] objArr, m mVar) {
                super(str, objArr);
                this.f4536b = mVar;
            }

            @Override // d.h0.b
            public void b() {
                try {
                    g.this.r.a(this.f4536b);
                } catch (IOException unused) {
                    g.this.o();
                }
            }
        }

        j(d.h0.i.h hVar) {
            super("OkHttp %s", g.this.f4496d);
            this.f4531b = hVar;
        }

        @Override // d.h0.i.h.b
        public void a() {
        }

        @Override // d.h0.i.h.b
        public void a(int i, int i2, int i3, boolean z) {
        }

        @Override // d.h0.i.h.b
        public void a(boolean z, int i, e.e eVar, int i2) throws IOException {
            if (g.this.b(i)) {
                g.this.a(i, eVar, i2, z);
                return;
            }
            d.h0.i.i iVarA = g.this.a(i);
            if (iVarA == null) {
                g.this.c(i, d.h0.i.b.PROTOCOL_ERROR);
                long j = i2;
                g.this.a(j);
                eVar.skip(j);
                return;
            }
            iVarA.a(eVar, i2);
            if (z) {
                iVarA.i();
            }
        }

        @Override // d.h0.b
        protected void b() throws Throwable {
            d.h0.i.b bVar;
            g gVar;
            d.h0.i.b bVar2 = d.h0.i.b.INTERNAL_ERROR;
            try {
                try {
                    try {
                        this.f4531b.a(this);
                        while (this.f4531b.a(false, (d.h0.i.h.b) this)) {
                        }
                        bVar = d.h0.i.b.NO_ERROR;
                        try {
                            bVar2 = d.h0.i.b.CANCEL;
                            gVar = g.this;
                        } catch (IOException unused) {
                            bVar = d.h0.i.b.PROTOCOL_ERROR;
                            bVar2 = d.h0.i.b.PROTOCOL_ERROR;
                            gVar = g.this;
                        }
                    } catch (IOException unused2) {
                    }
                } catch (IOException unused3) {
                } catch (Throwable th) {
                    th = th;
                    bVar = bVar2;
                    try {
                        g.this.a(bVar, bVar2);
                    } catch (IOException unused4) {
                    }
                    d.h0.c.a(this.f4531b);
                    throw th;
                }
                gVar.a(bVar, bVar2);
                d.h0.c.a(this.f4531b);
            } catch (Throwable th2) {
                th = th2;
            }
        }

        @Override // d.h0.i.h.b
        public void a(boolean z, int i, int i2, List<d.h0.i.c> list) {
            if (g.this.b(i)) {
                g.this.a(i, list, z);
                return;
            }
            synchronized (g.this) {
                d.h0.i.i iVarA = g.this.a(i);
                if (iVarA == null) {
                    if (g.this.g) {
                        return;
                    }
                    if (i <= g.this.f4497e) {
                        return;
                    }
                    if (i % 2 == g.this.f4498f % 2) {
                        return;
                    }
                    d.h0.i.i iVar = new d.h0.i.i(i, g.this, false, z, list);
                    g.this.f4497e = i;
                    g.this.f4495c.put(Integer.valueOf(i), iVar);
                    g.u.execute(new a("OkHttp %s stream %d", new Object[]{g.this.f4496d, Integer.valueOf(i)}, iVar));
                    return;
                }
                iVarA.a(list);
                if (z) {
                    iVarA.i();
                }
            }
        }

        @Override // d.h0.i.h.b
        public void a(int i, d.h0.i.b bVar) {
            if (g.this.b(i)) {
                g.this.a(i, bVar);
                return;
            }
            d.h0.i.i iVarC = g.this.c(i);
            if (iVarC != null) {
                iVarC.c(bVar);
            }
        }

        @Override // d.h0.i.h.b
        public void a(boolean z, m mVar) {
            d.h0.i.i[] iVarArr;
            long j;
            int i;
            synchronized (g.this) {
                int iC = g.this.o.c();
                if (z) {
                    g.this.o.a();
                }
                g.this.o.a(mVar);
                a(mVar);
                int iC2 = g.this.o.c();
                iVarArr = null;
                if (iC2 == -1 || iC2 == iC) {
                    j = 0;
                } else {
                    j = iC2 - iC;
                    if (!g.this.p) {
                        g.this.p = true;
                    }
                    if (!g.this.f4495c.isEmpty()) {
                        iVarArr = (d.h0.i.i[]) g.this.f4495c.values().toArray(new d.h0.i.i[g.this.f4495c.size()]);
                    }
                }
                g.u.execute(new b("OkHttp %s settings", g.this.f4496d));
            }
            if (iVarArr == null || j == 0) {
                return;
            }
            for (d.h0.i.i iVar : iVarArr) {
                synchronized (iVar) {
                    iVar.a(j);
                }
            }
        }

        private void a(m mVar) {
            try {
                g.this.h.execute(new c("OkHttp %s ACK Settings", new Object[]{g.this.f4496d}, mVar));
            } catch (RejectedExecutionException unused) {
            }
        }

        @Override // d.h0.i.h.b
        public void a(boolean z, int i, int i2) {
            if (!z) {
                try {
                    g.this.h.execute(g.this.new i(true, i, i2));
                } catch (RejectedExecutionException unused) {
                }
            } else {
                synchronized (g.this) {
                    g.this.k = false;
                    g.this.notifyAll();
                }
            }
        }

        @Override // d.h0.i.h.b
        public void a(int i, d.h0.i.b bVar, e.f fVar) {
            d.h0.i.i[] iVarArr;
            fVar.f();
            synchronized (g.this) {
                iVarArr = (d.h0.i.i[]) g.this.f4495c.values().toArray(new d.h0.i.i[g.this.f4495c.size()]);
                g.this.g = true;
            }
            for (d.h0.i.i iVar : iVarArr) {
                if (iVar.c() > i && iVar.f()) {
                    iVar.c(d.h0.i.b.REFUSED_STREAM);
                    g.this.c(iVar.c());
                }
            }
        }

        @Override // d.h0.i.h.b
        public void a(int i, long j) {
            if (i == 0) {
                synchronized (g.this) {
                    g.this.m += j;
                    g.this.notifyAll();
                }
                return;
            }
            d.h0.i.i iVarA = g.this.a(i);
            if (iVarA != null) {
                synchronized (iVarA) {
                    iVarA.a(j);
                }
            }
        }

        @Override // d.h0.i.h.b
        public void a(int i, int i2, List<d.h0.i.c> list) {
            g.this.a(i2, list);
        }
    }

    public d.h0.i.i a(List<d.h0.i.c> list, boolean z) {
        return b(0, list, z);
    }

    public void a(int i2, boolean z, e.c cVar, long j2) {
        int iMin;
        long j3;
        if (j2 == 0) {
            this.r.a(z, i2, cVar, 0);
            return;
        }
        while (j2 > 0) {
            synchronized (this) {
                while (this.m <= 0) {
                    try {
                        if (this.f4495c.containsKey(Integer.valueOf(i2))) {
                            wait();
                        } else {
                            throw new IOException("stream closed");
                        }
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                iMin = Math.min((int) Math.min(j2, this.m), this.r.b());
                j3 = iMin;
                this.m -= j3;
            }
            j2 -= j3;
            this.r.a(z && j2 == 0, i2, cVar, iMin);
        }
    }

    void a(int i2, long j2) {
        try {
            this.h.execute(new b("OkHttp Window Update %s stream %d", new Object[]{this.f4496d, Integer.valueOf(i2)}, i2, j2));
        } catch (RejectedExecutionException unused) {
        }
    }

    void b(int i2, d.h0.i.b bVar) {
        this.r.a(i2, bVar);
    }

    void a(boolean z, int i2, int i3) {
        boolean z2;
        if (!z) {
            synchronized (this) {
                z2 = this.k;
                this.k = true;
            }
            if (z2) {
                o();
                return;
            }
        }
        try {
            this.r.a(z, i2, i3);
        } catch (IOException unused) {
            o();
        }
    }

    public void a(d.h0.i.b bVar) {
        synchronized (this.r) {
            synchronized (this) {
                if (this.g) {
                    return;
                }
                this.g = true;
                this.r.a(this.f4497e, bVar, d.h0.c.f4337a);
            }
        }
    }

    void a(d.h0.i.b bVar, d.h0.i.b bVar2) throws IOException {
        d.h0.i.i[] iVarArr = null;
        try {
            a(bVar);
            e = null;
        } catch (IOException e2) {
            e = e2;
        }
        synchronized (this) {
            if (!this.f4495c.isEmpty()) {
                iVarArr = (d.h0.i.i[]) this.f4495c.values().toArray(new d.h0.i.i[this.f4495c.size()]);
                this.f4495c.clear();
            }
        }
        if (iVarArr != null) {
            for (d.h0.i.i iVar : iVarArr) {
                try {
                    iVar.a(bVar2);
                } catch (IOException e3) {
                    if (e != null) {
                        e = e3;
                    }
                }
            }
        }
        try {
            this.r.close();
        } catch (IOException e4) {
            if (e == null) {
                e = e4;
            }
        }
        try {
            this.q.close();
        } catch (IOException e5) {
            e = e5;
        }
        this.h.shutdown();
        this.i.shutdown();
        if (e != null) {
            throw e;
        }
    }

    void a(boolean z) {
        if (z) {
            this.r.a();
            this.r.b(this.n);
            int iC = this.n.c();
            if (iC != 65535) {
                this.r.a(0, iC - 65535);
            }
        }
        new Thread(this.s).start();
    }

    public synchronized boolean a() {
        return this.g;
    }

    void a(int i2, List<d.h0.i.c> list) {
        synchronized (this) {
            if (this.t.contains(Integer.valueOf(i2))) {
                c(i2, d.h0.i.b.PROTOCOL_ERROR);
                return;
            }
            this.t.add(Integer.valueOf(i2));
            try {
                a(new c("OkHttp %s Push Request[%s]", new Object[]{this.f4496d, Integer.valueOf(i2)}, i2, list));
            } catch (RejectedExecutionException unused) {
            }
        }
    }

    void a(int i2, List<d.h0.i.c> list, boolean z) {
        try {
            a(new d("OkHttp %s Push Headers[%s]", new Object[]{this.f4496d, Integer.valueOf(i2)}, i2, list, z));
        } catch (RejectedExecutionException unused) {
        }
    }

    void a(int i2, e.e eVar, int i3, boolean z) throws IOException {
        e.c cVar = new e.c();
        long j2 = i3;
        eVar.g(j2);
        eVar.read(cVar, j2);
        if (cVar.q() == j2) {
            a(new e("OkHttp %s Push Data[%s]", new Object[]{this.f4496d, Integer.valueOf(i2)}, i2, cVar, i3, z));
            return;
        }
        throw new IOException(cVar.q() + " != " + i3);
    }

    void a(int i2, d.h0.i.b bVar) {
        a(new f("OkHttp %s Push Reset[%s]", new Object[]{this.f4496d, Integer.valueOf(i2)}, i2, bVar));
    }

    private synchronized void a(d.h0.b bVar) {
        if (!a()) {
            this.i.execute(bVar);
        }
    }
}
