package a3;

import java.net.ProxySelector;
import java.util.List;
import java.util.Objects;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f51a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SocketFactory f52b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SSLSocketFactory f53c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HostnameVerifier f54d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f55e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f56f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ProxySelector f57g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final t f58h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final List f59i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final List f60j;

    public a(String str, int i4, m mVar, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, e eVar, b bVar, List list, List list2, ProxySelector proxySelector) {
        j2.i.e(str, "uriHost");
        j2.i.e(mVar, "dns");
        j2.i.e(socketFactory, "socketFactory");
        j2.i.e(bVar, "proxyAuthenticator");
        j2.i.e(list, "protocols");
        j2.i.e(list2, "connectionSpecs");
        j2.i.e(proxySelector, "proxySelector");
        this.f51a = mVar;
        this.f52b = socketFactory;
        this.f53c = sSLSocketFactory;
        this.f54d = hostnameVerifier;
        this.f55e = eVar;
        this.f56f = bVar;
        this.f57g = proxySelector;
        s sVar = new s();
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (str2.equalsIgnoreCase("http")) {
            sVar.f200a = "http";
        } else {
            if (!str2.equalsIgnoreCase("https")) {
                throw new IllegalArgumentException("unexpected scheme: ".concat(str2));
            }
            sVar.f200a = "https";
        }
        String strB = b3.c.b(p3.a.c(str, 0, 0, 7));
        if (strB == null) {
            throw new IllegalArgumentException("unexpected host: ".concat(str));
        }
        sVar.f203d = strB;
        if (1 > i4 || i4 >= 65536) {
            throw new IllegalArgumentException(a1.c.c(i4, "unexpected port: ").toString());
        }
        sVar.f204e = i4;
        this.f58h = sVar.a();
        this.f59i = b3.g.j(list);
        this.f60j = b3.g.j(list2);
    }

    public final boolean a(a aVar) {
        j2.i.e(aVar, "that");
        return j2.i.a(this.f51a, aVar.f51a) && j2.i.a(this.f56f, aVar.f56f) && j2.i.a(this.f59i, aVar.f59i) && j2.i.a(this.f60j, aVar.f60j) && j2.i.a(this.f57g, aVar.f57g) && j2.i.a(this.f53c, aVar.f53c) && j2.i.a(this.f54d, aVar.f54d) && j2.i.a(this.f55e, aVar.f55e) && this.f58h.f212e == aVar.f58h.f212e;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return j2.i.a(this.f58h, aVar.f58h) && a(aVar);
    }

    public final int hashCode() {
        return Objects.hashCode(this.f55e) + ((Objects.hashCode(this.f54d) + ((Objects.hashCode(this.f53c) + ((this.f57g.hashCode() + ((this.f60j.hashCode() + ((this.f59i.hashCode() + ((this.f56f.hashCode() + ((this.f51a.hashCode() + ((this.f58h.f215h.hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 961)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        t tVar = this.f58h;
        sb.append(tVar.f211d);
        sb.append(':');
        sb.append(tVar.f212e);
        sb.append(", ");
        sb.append("proxySelector=" + this.f57g);
        sb.append('}');
        return sb.toString();
    }
}
