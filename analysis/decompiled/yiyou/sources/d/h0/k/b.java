package d.h0.k;

import d.y;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import org.conscrypt.Conscrypt;
import org.conscrypt.OpenSSLProvider;

/* JADX INFO: compiled from: ConscryptPlatform.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends f {
    private b() {
    }

    private Provider f() {
        return new OpenSSLProvider();
    }

    @Override // d.h0.k.f
    public void a(SSLSocket sSLSocket, String str, List<y> list) {
        if (!Conscrypt.isConscrypt(sSLSocket)) {
            super.a(sSLSocket, str, list);
            return;
        }
        if (str != null) {
            Conscrypt.setUseSessionTickets(sSLSocket, true);
            Conscrypt.setHostname(sSLSocket, str);
        }
        Conscrypt.setApplicationProtocols(sSLSocket, (String[]) f.a(list).toArray(new String[0]));
    }

    @Override // d.h0.k.f
    public String b(SSLSocket sSLSocket) {
        return Conscrypt.isConscrypt(sSLSocket) ? Conscrypt.getApplicationProtocol(sSLSocket) : super.b(sSLSocket);
    }

    @Override // d.h0.k.f
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        if (!Conscrypt.isConscrypt(sSLSocketFactory)) {
            return super.c(sSLSocketFactory);
        }
        try {
            Object objA = f.a(sSLSocketFactory, (Class<Object>) Object.class, "sslParameters");
            if (objA != null) {
                return (X509TrustManager) f.a(objA, X509TrustManager.class, "x509TrustManager");
            }
            return null;
        } catch (Exception e2) {
            throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on Conscrypt", e2);
        }
    }

    @Override // d.h0.k.f
    public SSLContext b() {
        try {
            return SSLContext.getInstance("TLS", f());
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }

    @Override // d.h0.k.f
    public void b(SSLSocketFactory sSLSocketFactory) {
        if (Conscrypt.isConscrypt(sSLSocketFactory)) {
            Conscrypt.setUseEngineSocket(sSLSocketFactory, true);
        }
    }

    public static f c() {
        try {
            Class.forName("org.conscrypt.ConscryptEngineSocket");
            if (Conscrypt.isAvailable()) {
                return new b();
            }
            return null;
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
