package l3;

import android.net.http.X509TrustManagerExtensions;
import d0.l0;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X509TrustManager f1371a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final X509TrustManagerExtensions f1372b;

    public c(X509TrustManager x509TrustManager, X509TrustManagerExtensions x509TrustManagerExtensions) {
        this.f1371a = x509TrustManager;
        this.f1372b = x509TrustManagerExtensions;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof c) && ((c) obj).f1371a == this.f1371a;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f1371a);
    }

    @Override // d0.l0
    public final List i(List list, String str) throws SSLPeerUnverifiedException {
        j2.i.e(list, "chain");
        j2.i.e(str, "hostname");
        try {
            List<X509Certificate> listCheckServerTrusted = this.f1372b.checkServerTrusted((X509Certificate[]) list.toArray(new X509Certificate[0]), "RSA", str);
            j2.i.d(listCheckServerTrusted, "checkServerTrusted(...)");
            return listCheckServerTrusted;
        } catch (CertificateException e4) {
            SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e4.getMessage());
            sSLPeerUnverifiedException.initCause(e4);
            throw sSLPeerUnverifiedException;
        }
    }
}
