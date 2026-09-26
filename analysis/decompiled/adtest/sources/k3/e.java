package k3;

import a3.x;
import android.os.Build;
import d0.l0;
import j2.i;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile e f1300a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Logger f1301b;

    static {
        try {
            for (Map.Entry entry : l3.d.f1374b.entrySet()) {
                l3.d.b((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (RuntimeException e4) {
            System.err.println("Possibly running android unit test without robolectric");
            e4.printStackTrace();
        } catch (UnsatisfiedLinkError e5) {
            System.err.println("Possibly running android unit test without robolectric");
            e5.printStackTrace();
        }
        e aVar = a.f1292e ? new a() : null;
        if (aVar == null) {
            aVar = c.f1297e ? new c() : null;
        }
        if (aVar == null) {
            throw new IllegalStateException(a1.c.c(Build.VERSION.SDK_INT, "Expected Android API level 21+ but was "));
        }
        f1300a = aVar;
        f1301b = Logger.getLogger(x.class.getName());
    }

    public abstract l0 c(X509TrustManager x509TrustManager);

    public o3.d d(X509TrustManager x509TrustManager) {
        X509Certificate[] acceptedIssuers = x509TrustManager.getAcceptedIssuers();
        return new o3.b((X509Certificate[]) Arrays.copyOf(acceptedIssuers, acceptedIssuers.length));
    }

    public abstract void e(SSLSocket sSLSocket, String str, List list);

    public void f(Socket socket, InetSocketAddress inetSocketAddress, int i4) throws IOException {
        i.e(inetSocketAddress, "address");
        socket.connect(inetSocketAddress, i4);
    }

    public abstract String g(SSLSocket sSLSocket);

    public Object h() {
        if (f1301b.isLoggable(Level.FINE)) {
            return new Throwable("response.body().close()");
        }
        return null;
    }

    public abstract boolean i(String str);

    public abstract void j(String str, int i4, Throwable th);

    public void k(Object obj, String str) {
        i.e(str, "message");
        if (obj == null) {
            str = str.concat(" To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);");
        }
        j(str, 5, (Throwable) obj);
    }

    public SSLContext l() throws NoSuchAlgorithmException {
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        i.d(sSLContext, "getInstance(...)");
        return sSLContext;
    }

    public final String toString() {
        return getClass().getSimpleName();
    }
}
