package n1;

import android.util.Log;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements X509TrustManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1493a;

    @Override // javax.net.ssl.X509TrustManager
    public final void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        switch (this.f1493a) {
            case 0:
                break;
            default:
                Log.d("SSLUtils", "checkClientTrusted: Accepting all client certificates");
                break;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        switch (this.f1493a) {
            case 0:
                break;
            default:
                Log.d("SSLUtils", "checkServerTrusted: Accepting all server certificates");
                break;
        }
    }

    @Override // javax.net.ssl.X509TrustManager
    public final X509Certificate[] getAcceptedIssuers() {
        switch (this.f1493a) {
        }
        return null;
    }

    private final void a(X509Certificate[] x509CertificateArr, String str) {
    }

    private final void b(X509Certificate[] x509CertificateArr, String str) {
    }
}
