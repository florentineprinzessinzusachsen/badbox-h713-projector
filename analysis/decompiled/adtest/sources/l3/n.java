package l3;

import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final m f1393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public o f1394b;

    public n(m mVar) {
        this.f1393a = mVar;
    }

    @Override // l3.o
    public final boolean a(SSLSocket sSLSocket) {
        return this.f1393a.a(sSLSocket);
    }

    @Override // l3.o
    public final String b(SSLSocket sSLSocket) {
        o oVarE = e(sSLSocket);
        if (oVarE != null) {
            return oVarE.b(sSLSocket);
        }
        return null;
    }

    @Override // l3.o
    public final boolean c() {
        return true;
    }

    @Override // l3.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        j2.i.e(list, "protocols");
        o oVarE = e(sSLSocket);
        if (oVarE != null) {
            oVarE.d(sSLSocket, str, list);
        }
    }

    public final synchronized o e(SSLSocket sSLSocket) {
        try {
            if (this.f1394b == null && this.f1393a.a(sSLSocket)) {
                this.f1394b = this.f1393a.c(sSLSocket);
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.f1394b;
    }
}
