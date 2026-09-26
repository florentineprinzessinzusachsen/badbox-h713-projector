package a3;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f145a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Proxy f146b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final InetSocketAddress f147c;

    public g0(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        j2.i.e(inetSocketAddress, "socketAddress");
        this.f145a = aVar;
        this.f146b = proxy;
        this.f147c = inetSocketAddress;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof g0)) {
            return false;
        }
        g0 g0Var = (g0) obj;
        return j2.i.a(g0Var.f145a, this.f145a) && j2.i.a(g0Var.f146b, this.f146b) && j2.i.a(g0Var.f147c, this.f147c);
    }

    public final int hashCode() {
        return this.f147c.hashCode() + ((this.f146b.hashCode() + ((this.f145a.hashCode() + 527) * 31)) * 31);
    }

    public final String toString() {
        String hostAddress;
        StringBuilder sb = new StringBuilder();
        t tVar = this.f145a.f58h;
        String str = tVar.f211d;
        InetSocketAddress inetSocketAddress = this.f147c;
        InetAddress address = inetSocketAddress.getAddress();
        String strB = (address == null || (hostAddress = address.getHostAddress()) == null) ? null : b3.c.b(hostAddress);
        if (p2.i.A0(str, ':')) {
            sb.append("[");
            sb.append(str);
            sb.append("]");
        } else {
            sb.append(str);
        }
        if (tVar.f212e != inetSocketAddress.getPort() || str.equals(strB)) {
            sb.append(":");
            sb.append(tVar.f212e);
        }
        if (!str.equals(strB)) {
            if (this.f146b.equals(Proxy.NO_PROXY)) {
                sb.append(" at ");
            } else {
                sb.append(" via proxy ");
            }
            if (strB == null) {
                sb.append("<unresolved>");
            } else if (p2.i.A0(strB, ':')) {
                sb.append("[");
                sb.append(strB);
                sb.append("]");
            } else {
                sb.append(strB);
            }
            sb.append(":");
            sb.append(inetSocketAddress.getPort());
        }
        return sb.toString();
    }
}
