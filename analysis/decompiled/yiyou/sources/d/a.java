package d;

import com.baidu.mobstat.Config;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: Address.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final t f4230a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final o f4231b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final SocketFactory f4232c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final b f4233d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final List<y> f4234e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final List<k> f4235f;
    final ProxySelector g;
    final Proxy h;
    final SSLSocketFactory i;
    final HostnameVerifier j;
    final g k;

    public a(String str, int i, o oVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, g gVar, b bVar, Proxy proxy, List<y> list, List<k> list2, ProxySelector proxySelector) {
        t.a aVar = new t.a();
        aVar.e(sSLSocketFactory != null ? "https" : "http");
        aVar.b(str);
        aVar.a(i);
        this.f4230a = aVar.a();
        if (oVar == null) {
            throw new NullPointerException("dns == null");
        }
        this.f4231b = oVar;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.f4232c = socketFactory;
        if (bVar == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.f4233d = bVar;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.f4234e = d.h0.c.a(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f4235f = d.h0.c.a(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.g = proxySelector;
        this.h = proxy;
        this.i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = gVar;
    }

    public g a() {
        return this.k;
    }

    public List<k> b() {
        return this.f4235f;
    }

    public o c() {
        return this.f4231b;
    }

    public HostnameVerifier d() {
        return this.j;
    }

    public List<y> e() {
        return this.f4234e;
    }

    public boolean equals(Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f4230a.equals(aVar.f4230a) && a(aVar)) {
                return true;
            }
        }
        return false;
    }

    public Proxy f() {
        return this.h;
    }

    public b g() {
        return this.f4233d;
    }

    public ProxySelector h() {
        return this.g;
    }

    public int hashCode() {
        int iHashCode = (((((((((((527 + this.f4230a.hashCode()) * 31) + this.f4231b.hashCode()) * 31) + this.f4233d.hashCode()) * 31) + this.f4234e.hashCode()) * 31) + this.f4235f.hashCode()) * 31) + this.g.hashCode()) * 31;
        Proxy proxy = this.h;
        int iHashCode2 = (iHashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.i;
        int iHashCode3 = (iHashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.j;
        int iHashCode4 = (iHashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        g gVar = this.k;
        return iHashCode4 + (gVar != null ? gVar.hashCode() : 0);
    }

    public SocketFactory i() {
        return this.f4232c;
    }

    public SSLSocketFactory j() {
        return this.i;
    }

    public t k() {
        return this.f4230a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f4230a.g());
        sb.append(Config.TRACE_TODAY_VISIT_SPLIT);
        sb.append(this.f4230a.k());
        if (this.h != null) {
            sb.append(", proxy=");
            sb.append(this.h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.g);
        }
        sb.append("}");
        return sb.toString();
    }

    boolean a(a aVar) {
        return this.f4231b.equals(aVar.f4231b) && this.f4233d.equals(aVar.f4233d) && this.f4234e.equals(aVar.f4234e) && this.f4235f.equals(aVar.f4235f) && this.g.equals(aVar.g) && d.h0.c.a(this.h, aVar.h) && d.h0.c.a(this.i, aVar.i) && d.h0.c.a(this.j, aVar.j) && d.h0.c.a(this.k, aVar.k) && k().k() == aVar.k().k();
    }
}
