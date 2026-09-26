package d;

import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final h[] f4626e = {h.k, h.m, h.l, h.n, h.p, h.o};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final h[] f4627f = {h.k, h.m, h.l, h.n, h.p, h.o, h.i, h.j, h.g, h.h, h.f4332e, h.f4333f, h.f4331d};
    public static final k g;
    public static final k h;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final boolean f4628a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final boolean f4629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final String[] f4630c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final String[] f4631d;

    static {
        a aVar = new a(true);
        aVar.a(f4626e);
        aVar.a(f0.TLS_1_2);
        aVar.a(true);
        aVar.a();
        a aVar2 = new a(true);
        aVar2.a(f4627f);
        aVar2.a(f0.TLS_1_2, f0.TLS_1_1, f0.TLS_1_0);
        aVar2.a(true);
        g = aVar2.a();
        a aVar3 = new a(g);
        aVar3.a(f0.TLS_1_0);
        aVar3.a(true);
        aVar3.a();
        h = new a(false).a();
    }

    k(a aVar) {
        this.f4628a = aVar.f4632a;
        this.f4630c = aVar.f4633b;
        this.f4631d = aVar.f4634c;
        this.f4629b = aVar.f4635d;
    }

    public List<h> a() {
        String[] strArr = this.f4630c;
        if (strArr != null) {
            return h.a(strArr);
        }
        return null;
    }

    public boolean b() {
        return this.f4628a;
    }

    public boolean c() {
        return this.f4629b;
    }

    public List<f0> d() {
        String[] strArr = this.f4631d;
        if (strArr != null) {
            return f0.a(strArr);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        k kVar = (k) obj;
        boolean z = this.f4628a;
        if (z != kVar.f4628a) {
            return false;
        }
        return !z || (Arrays.equals(this.f4630c, kVar.f4630c) && Arrays.equals(this.f4631d, kVar.f4631d) && this.f4629b == kVar.f4629b);
    }

    public int hashCode() {
        if (this.f4628a) {
            return ((((527 + Arrays.hashCode(this.f4630c)) * 31) + Arrays.hashCode(this.f4631d)) * 31) + (!this.f4629b ? 1 : 0);
        }
        return 17;
    }

    public String toString() {
        if (!this.f4628a) {
            return "ConnectionSpec()";
        }
        return "ConnectionSpec(cipherSuites=" + (this.f4630c != null ? a().toString() : "[all enabled]") + ", tlsVersions=" + (this.f4631d != null ? d().toString() : "[all enabled]") + ", supportsTlsExtensions=" + this.f4629b + ")";
    }

    /* JADX INFO: compiled from: ConnectionSpec.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f4632a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String[] f4633b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String[] f4634c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f4635d;

        a(boolean z) {
            this.f4632a = z;
        }

        public a a(h... hVarArr) {
            if (!this.f4632a) {
                throw new IllegalStateException("no cipher suites for cleartext connections");
            }
            String[] strArr = new String[hVarArr.length];
            for (int i = 0; i < hVarArr.length; i++) {
                strArr[i] = hVarArr[i].f4334a;
            }
            a(strArr);
            return this;
        }

        public a b(String... strArr) {
            if (!this.f4632a) {
                throw new IllegalStateException("no TLS versions for cleartext connections");
            }
            if (strArr.length == 0) {
                throw new IllegalArgumentException("At least one TLS version is required");
            }
            this.f4634c = (String[]) strArr.clone();
            return this;
        }

        public a(k kVar) {
            this.f4632a = kVar.f4628a;
            this.f4633b = kVar.f4630c;
            this.f4634c = kVar.f4631d;
            this.f4635d = kVar.f4629b;
        }

        public a a(String... strArr) {
            if (this.f4632a) {
                if (strArr.length != 0) {
                    this.f4633b = (String[]) strArr.clone();
                    return this;
                }
                throw new IllegalArgumentException("At least one cipher suite is required");
            }
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }

        public a a(f0... f0VarArr) {
            if (this.f4632a) {
                String[] strArr = new String[f0VarArr.length];
                for (int i = 0; i < f0VarArr.length; i++) {
                    strArr[i] = f0VarArr[i].f4320a;
                }
                b(strArr);
                return this;
            }
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }

        public a a(boolean z) {
            if (this.f4632a) {
                this.f4635d = z;
                return this;
            }
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }

        public k a() {
            return new k(this);
        }
    }

    private k b(SSLSocket sSLSocket, boolean z) {
        String[] strArrA = this.f4630c != null ? d.h0.c.a(h.f4329b, sSLSocket.getEnabledCipherSuites(), this.f4630c) : sSLSocket.getEnabledCipherSuites();
        String[] strArrA2 = this.f4631d != null ? d.h0.c.a(d.h0.c.o, sSLSocket.getEnabledProtocols(), this.f4631d) : sSLSocket.getEnabledProtocols();
        String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
        int iA = d.h0.c.a(h.f4329b, supportedCipherSuites, "TLS_FALLBACK_SCSV");
        if (z && iA != -1) {
            strArrA = d.h0.c.a(strArrA, supportedCipherSuites[iA]);
        }
        a aVar = new a(this);
        aVar.a(strArrA);
        aVar.b(strArrA2);
        return aVar.a();
    }

    void a(SSLSocket sSLSocket, boolean z) {
        k kVarB = b(sSLSocket, z);
        String[] strArr = kVarB.f4631d;
        if (strArr != null) {
            sSLSocket.setEnabledProtocols(strArr);
        }
        String[] strArr2 = kVarB.f4630c;
        if (strArr2 != null) {
            sSLSocket.setEnabledCipherSuites(strArr2);
        }
    }

    public boolean a(SSLSocket sSLSocket) {
        if (!this.f4628a) {
            return false;
        }
        String[] strArr = this.f4631d;
        if (strArr != null && !d.h0.c.b(d.h0.c.o, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f4630c;
        return strArr2 == null || d.h0.c.b(h.f4329b, strArr2, sSLSocket.getEnabledCipherSuites());
    }
}
