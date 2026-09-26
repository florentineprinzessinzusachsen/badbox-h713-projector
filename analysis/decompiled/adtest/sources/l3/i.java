package l3;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.bouncycastle.jsse.BCSSLParameters;
import org.bouncycastle.jsse.BCSSLSocket;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final g f1389a = new g();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f1390b;

    static {
        boolean z3 = false;
        try {
            Class.forName("org.bouncycastle.jsse.provider.BouncyCastleJsseProvider", false, h.class.getClassLoader());
            z3 = true;
        } catch (ClassNotFoundException unused) {
        }
        f1390b = z3;
    }

    @Override // l3.o
    public final boolean a(SSLSocket sSLSocket) {
        return false;
    }

    @Override // l3.o
    public final String b(SSLSocket sSLSocket) {
        String applicationProtocol = ((BCSSLSocket) sSLSocket).getApplicationProtocol();
        if (applicationProtocol == null || applicationProtocol.equals("")) {
            return null;
        }
        return applicationProtocol;
    }

    @Override // l3.o
    public final boolean c() {
        return f1390b;
    }

    @Override // l3.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        j2.i.e(list, "protocols");
        if (a(sSLSocket)) {
            BCSSLSocket bCSSLSocket = (BCSSLSocket) sSLSocket;
            BCSSLParameters parameters = bCSSLSocket.getParameters();
            k3.e eVar = k3.e.f1300a;
            parameters.setApplicationProtocols((String[]) a1.a.i(list).toArray(new String[0]));
            bCSSLSocket.setParameters(parameters);
        }
    }
}
