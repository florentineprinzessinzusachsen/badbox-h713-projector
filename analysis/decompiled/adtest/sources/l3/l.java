package l3;

import java.util.List;
import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final j f1391a = new j();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f1392b;

    static {
        boolean z3 = false;
        try {
            Class.forName("org.conscrypt.Conscrypt$Version", false, k.class.getClassLoader());
            if (Conscrypt.isAvailable() && k.a()) {
                z3 = true;
            }
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
        }
        f1392b = z3;
    }

    @Override // l3.o
    public final boolean a(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // l3.o
    public final String b(SSLSocket sSLSocket) {
        if (a(sSLSocket)) {
            return Conscrypt.getApplicationProtocol(sSLSocket);
        }
        return null;
    }

    @Override // l3.o
    public final boolean c() {
        return f1392b;
    }

    @Override // l3.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        j2.i.e(list, "protocols");
        if (a(sSLSocket)) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            k3.e eVar = k3.e.f1300a;
            Conscrypt.setApplicationProtocols(sSLSocket, (String[]) a1.a.i(list).toArray(new String[0]));
        }
    }
}
