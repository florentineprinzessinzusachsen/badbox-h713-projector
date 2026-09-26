package d;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.Socket;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: OkHttpClient.java */
/* JADX INFO: loaded from: classes.dex */
public class x implements Cloneable, e.a, g0 {
    static final List<y> B = d.h0.c.a(y.HTTP_2, y.HTTP_1_1);
    static final List<k> C = d.h0.c.a(k.g, k.h);
    final int A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final n f4693a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final Proxy f4694b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final List<y> f4695c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final List<k> f4696d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final List<u> f4697e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final List<u> f4698f;
    final p.c g;
    final ProxySelector h;
    final m i;
    final c j;
    final d.h0.e.f k;
    final SocketFactory l;
    final SSLSocketFactory m;
    final d.h0.l.c n;
    final HostnameVerifier o;
    final g p;
    final d.b q;
    final d.b r;
    final j s;
    final o t;
    final boolean u;
    final boolean v;
    final boolean w;
    final int x;
    final int y;
    final int z;

    /* JADX INFO: compiled from: OkHttpClient.java */
    class a extends d.h0.a {
        a() {
        }

        @Override // d.h0.a
        public void a(s.a aVar, String str) {
            aVar.a(str);
        }

        @Override // d.h0.a
        public void b(j jVar, d.h0.f.c cVar) {
            jVar.b(cVar);
        }

        @Override // d.h0.a
        public void a(s.a aVar, String str, String str2) {
            aVar.b(str, str2);
        }

        @Override // d.h0.a
        public boolean a(j jVar, d.h0.f.c cVar) {
            return jVar.a(cVar);
        }

        @Override // d.h0.a
        public d.h0.f.c a(j jVar, d.a aVar, d.h0.f.g gVar, e0 e0Var) {
            return jVar.a(aVar, gVar, e0Var);
        }

        @Override // d.h0.a
        public boolean a(d.a aVar, d.a aVar2) {
            return aVar.a(aVar2);
        }

        @Override // d.h0.a
        public Socket a(j jVar, d.a aVar, d.h0.f.g gVar) {
            return jVar.a(aVar, gVar);
        }

        @Override // d.h0.a
        public d.h0.f.d a(j jVar) {
            return jVar.f4623e;
        }

        @Override // d.h0.a
        public int a(c0.a aVar) {
            return aVar.f4289c;
        }

        @Override // d.h0.a
        public void a(k kVar, SSLSocket sSLSocket, boolean z) {
            kVar.a(sSLSocket, z);
        }
    }

    /* JADX INFO: compiled from: OkHttpClient.java */
    public static final class b {
        int A;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Proxy f4700b;
        c j;
        d.h0.e.f k;
        SSLSocketFactory m;
        d.h0.l.c n;
        d.b q;
        d.b r;
        j s;
        o t;
        boolean u;
        boolean v;
        boolean w;
        int x;
        int y;
        int z;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final List<u> f4703e = new ArrayList();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final List<u> f4704f = new ArrayList();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        n f4699a = new n();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        List<y> f4701c = x.B;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        List<k> f4702d = x.C;
        p.c g = p.a(p.f4650a);
        ProxySelector h = ProxySelector.getDefault();
        m i = m.f4642a;
        SocketFactory l = SocketFactory.getDefault();
        HostnameVerifier o = d.h0.l.d.f4610a;
        g p = g.f4321c;

        public b() {
            d.b bVar = d.b.f4247a;
            this.q = bVar;
            this.r = bVar;
            this.s = new j();
            this.t = o.f4649a;
            this.u = true;
            this.v = true;
            this.w = true;
            this.x = 10000;
            this.y = 10000;
            this.z = 10000;
            this.A = 0;
        }

        public b a(long j, TimeUnit timeUnit) {
            this.x = d.h0.c.a("timeout", j, timeUnit);
            return this;
        }

        public b b(u uVar) {
            if (uVar == null) {
                throw new IllegalArgumentException("interceptor == null");
            }
            this.f4704f.add(uVar);
            return this;
        }

        public b a(Proxy proxy) {
            this.f4700b = proxy;
            return this;
        }

        public b a(c cVar) {
            this.j = cVar;
            this.k = null;
            return this;
        }

        public b a(SSLSocketFactory sSLSocketFactory) {
            if (sSLSocketFactory != null) {
                this.m = sSLSocketFactory;
                this.n = d.h0.k.f.d().a(sSLSocketFactory);
                return this;
            }
            throw new NullPointerException("sslSocketFactory == null");
        }

        public b a(HostnameVerifier hostnameVerifier) {
            if (hostnameVerifier != null) {
                this.o = hostnameVerifier;
                return this;
            }
            throw new NullPointerException("hostnameVerifier == null");
        }

        public b a(u uVar) {
            if (uVar != null) {
                this.f4703e.add(uVar);
                return this;
            }
            throw new IllegalArgumentException("interceptor == null");
        }

        public x a() {
            return new x(this);
        }
    }

    static {
        d.h0.a.f4335a = new a();
    }

    public x() {
        this(new b());
    }

    private static SSLSocketFactory a(X509TrustManager x509TrustManager) {
        try {
            SSLContext sSLContextB = d.h0.k.f.d().b();
            sSLContextB.init(null, new TrustManager[]{x509TrustManager}, null);
            return sSLContextB.getSocketFactory();
        } catch (GeneralSecurityException e2) {
            throw d.h0.c.a("No System TLS", (Exception) e2);
        }
    }

    public g b() {
        return this.p;
    }

    public int c() {
        return this.x;
    }

    public j d() {
        return this.s;
    }

    public List<k> e() {
        return this.f4696d;
    }

    public m f() {
        return this.i;
    }

    public n g() {
        return this.f4693a;
    }

    public o h() {
        return this.t;
    }

    public p.c i() {
        return this.g;
    }

    public boolean j() {
        return this.v;
    }

    public boolean k() {
        return this.u;
    }

    public HostnameVerifier l() {
        return this.o;
    }

    public List<u> m() {
        return this.f4697e;
    }

    d.h0.e.f n() {
        c cVar = this.j;
        return cVar != null ? cVar.f4256a : this.k;
    }

    public List<u> o() {
        return this.f4698f;
    }

    public int p() {
        return this.A;
    }

    public List<y> q() {
        return this.f4695c;
    }

    public Proxy r() {
        return this.f4694b;
    }

    public d.b s() {
        return this.q;
    }

    public ProxySelector t() {
        return this.h;
    }

    public int u() {
        return this.y;
    }

    public boolean v() {
        return this.w;
    }

    public SocketFactory w() {
        return this.l;
    }

    public SSLSocketFactory x() {
        return this.m;
    }

    public int y() {
        return this.z;
    }

    x(b bVar) {
        boolean z;
        this.f4693a = bVar.f4699a;
        this.f4694b = bVar.f4700b;
        this.f4695c = bVar.f4701c;
        this.f4696d = bVar.f4702d;
        this.f4697e = d.h0.c.a(bVar.f4703e);
        this.f4698f = d.h0.c.a(bVar.f4704f);
        this.g = bVar.g;
        this.h = bVar.h;
        this.i = bVar.i;
        this.j = bVar.j;
        this.k = bVar.k;
        this.l = bVar.l;
        Iterator<k> it = this.f4696d.iterator();
        loop0: while (true) {
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                } else {
                    z = z || it.next().b();
                }
            }
        }
        if (bVar.m == null && z) {
            X509TrustManager x509TrustManagerA = d.h0.c.a();
            this.m = a(x509TrustManagerA);
            this.n = d.h0.l.c.a(x509TrustManagerA);
        } else {
            this.m = bVar.m;
            this.n = bVar.n;
        }
        if (this.m != null) {
            d.h0.k.f.d().b(this.m);
        }
        this.o = bVar.o;
        this.p = bVar.p.a(this.n);
        this.q = bVar.q;
        this.r = bVar.r;
        this.s = bVar.s;
        this.t = bVar.t;
        this.u = bVar.u;
        this.v = bVar.v;
        this.w = bVar.w;
        this.x = bVar.x;
        this.y = bVar.y;
        this.z = bVar.z;
        this.A = bVar.A;
        if (this.f4697e.contains(null)) {
            throw new IllegalStateException("Null interceptor: " + this.f4697e);
        }
        if (this.f4698f.contains(null)) {
            throw new IllegalStateException("Null network interceptor: " + this.f4698f);
        }
    }

    public d.b a() {
        return this.r;
    }

    @Override // d.e.a
    public e a(a0 a0Var) {
        return z.a(this, a0Var, false);
    }
}
