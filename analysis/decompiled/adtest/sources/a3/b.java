package a3;

import d0.l0;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedHashMap;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements m, i0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final b f68d = new b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final b f69e = new b();

    public static final g a(b bVar, String str) {
        g gVar = new g(str);
        g.f127d.put(str, gVar);
        return gVar;
    }

    public static final void b(List list, StringBuilder sb) {
        m2.a aVarK = l0.K(l0.Q(0, list.size()), 2);
        int i4 = aVarK.f1443d;
        int i5 = aVarK.f1444e;
        int i6 = aVarK.f1445f;
        if ((i6 <= 0 || i4 > i5) && (i6 >= 0 || i5 > i4)) {
            return;
        }
        while (true) {
            String str = (String) list.get(i4);
            String str2 = (String) list.get(i4 + 1);
            if (i4 > 0) {
                sb.append('&');
            }
            sb.append(str);
            if (str2 != null) {
                sb.append('=');
                sb.append(str2);
            }
            if (i4 == i5) {
                return;
            } else {
                i4 += i6;
            }
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static h0 d(String str) {
        j2.i.e(str, "javaName");
        int iHashCode = str.hashCode();
        if (iHashCode != 79201641) {
            if (iHashCode != 79923350) {
                switch (iHashCode) {
                    case -503070503:
                        if (str.equals("TLSv1.1")) {
                            return h0.f153h;
                        }
                        break;
                    case -503070502:
                        if (str.equals("TLSv1.2")) {
                            return h0.f152g;
                        }
                        break;
                    case -503070501:
                        if (str.equals("TLSv1.3")) {
                            return h0.f151f;
                        }
                        break;
                }
            } else if (str.equals("TLSv1")) {
                return h0.f154i;
            }
        } else if (str.equals("SSLv3")) {
            return h0.f155j;
        }
        throw new IllegalArgumentException("Unexpected TLS version: ".concat(str));
    }

    public static y f(String str) throws IOException {
        if (str.equals("http/1.0")) {
            return y.f271f;
        }
        if (str.equals("http/1.1")) {
            return y.f272g;
        }
        if (str.equals("h2_prior_knowledge")) {
            return y.f275j;
        }
        if (str.equals("h2")) {
            return y.f274i;
        }
        if (str.equals("spdy/3.1")) {
            return y.f273h;
        }
        if (str.equals("quic")) {
            return y.f276k;
        }
        if (p2.p.z0(str, "h3", false)) {
            return y.f277l;
        }
        throw new IOException("Unexpected protocol: ".concat(str));
    }

    public synchronized g c(String str) {
        g gVar;
        String strConcat;
        try {
            j2.i.e(str, "javaName");
            LinkedHashMap linkedHashMap = g.f127d;
            gVar = (g) linkedHashMap.get(str);
            if (gVar == null) {
                if (p2.p.z0(str, "TLS_", false)) {
                    String strSubstring = str.substring(4);
                    j2.i.d(strSubstring, "substring(...)");
                    strConcat = "SSL_".concat(strSubstring);
                } else if (p2.p.z0(str, "SSL_", false)) {
                    String strSubstring2 = str.substring(4);
                    j2.i.d(strSubstring2, "substring(...)");
                    strConcat = "TLS_".concat(strSubstring2);
                } else {
                    strConcat = str;
                }
                gVar = (g) linkedHashMap.get(strConcat);
                if (gVar == null) {
                    gVar = new g(str);
                }
                linkedHashMap.put(str, gVar);
            }
        } catch (Throwable th) {
            throw th;
        }
        return gVar;
    }

    @Override // a3.m
    public List e(String str) throws UnknownHostException {
        j2.i.e(str, "hostname");
        try {
            InetAddress[] allByName = InetAddress.getAllByName(str);
            j2.i.d(allByName, "getAllByName(...)");
            return v1.i.d0(allByName);
        } catch (NullPointerException e4) {
            UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of ".concat(str));
            unknownHostException.initCause(e4);
            throw unknownHostException;
        }
    }
}
