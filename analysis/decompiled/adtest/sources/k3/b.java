package k3;

import j2.i;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements o3.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X509TrustManager f1295a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f1296b;

    public b(X509TrustManager x509TrustManager, Method method) {
        this.f1295a = x509TrustManager;
        this.f1296b = method;
    }

    @Override // o3.d
    public final X509Certificate a(X509Certificate x509Certificate) {
        try {
            Object objInvoke = this.f1296b.invoke(this.f1295a, x509Certificate);
            i.c(objInvoke, "null cannot be cast to non-null type java.security.cert.TrustAnchor");
            return ((TrustAnchor) objInvoke).getTrustedCert();
        } catch (IllegalAccessException e4) {
            throw new AssertionError("unable to get issues and signature", e4);
        } catch (InvocationTargetException unused) {
            return null;
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        return i.a(this.f1295a, bVar.f1295a) && i.a(this.f1296b, bVar.f1296b);
    }

    public final int hashCode() {
        return this.f1296b.hashCode() + (this.f1295a.hashCode() * 31);
    }

    public final String toString() {
        return "CustomTrustRootIndex(trustManager=" + this.f1295a + ", findByIssuerAndSignatureMethod=" + this.f1296b + ')';
    }
}
