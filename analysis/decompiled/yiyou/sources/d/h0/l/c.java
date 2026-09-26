package d.h0.l;

import d.h0.k.f;
import java.security.cert.Certificate;
import java.util.List;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: CertificateChainCleaner.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c {
    public static c a(X509TrustManager x509TrustManager) {
        return f.d().a(x509TrustManager);
    }

    public abstract List<Certificate> a(List<Certificate> list, String str);
}
