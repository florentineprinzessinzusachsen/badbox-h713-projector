package d.h0.k;

import d.y;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: Jdk9Platform.java */
/* JADX INFO: loaded from: classes.dex */
final class c extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final Method f4594c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final Method f4595d;

    c(Method method, Method method2) {
        this.f4594c = method;
        this.f4595d = method2;
    }

    @Override // d.h0.k.f
    public void a(SSLSocket sSLSocket, String str, List<y> list) {
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            List<String> listA = f.a(list);
            this.f4594c.invoke(sSLParameters, listA.toArray(new String[listA.size()]));
            sSLSocket.setSSLParameters(sSLParameters);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw d.h0.c.a("unable to set ssl parameters", (Exception) e2);
        }
    }

    @Override // d.h0.k.f
    public String b(SSLSocket sSLSocket) {
        try {
            String str = (String) this.f4595d.invoke(sSLSocket, new Object[0]);
            if (str == null || str.equals("")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw d.h0.c.a("unable to get selected protocols", (Exception) e2);
        }
    }

    @Override // d.h0.k.f
    public X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    public static c c() {
        try {
            return new c(SSLParameters.class.getMethod("setApplicationProtocols", String[].class), SSLSocket.class.getMethod("getApplicationProtocol", new Class[0]));
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }
}
