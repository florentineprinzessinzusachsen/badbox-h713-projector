package d.h0.k;

import d.x;
import d.y;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: Platform.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final f f4606a = c();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Logger f4607b = Logger.getLogger(x.class.getName());

    static byte[] b(List<y> list) {
        e.c cVar = new e.c();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            y yVar = list.get(i);
            if (yVar != y.HTTP_1_0) {
                cVar.writeByte(yVar.toString().length());
                cVar.b(yVar.toString());
            }
        }
        return cVar.h();
    }

    public static f d() {
        return f4606a;
    }

    public static boolean e() {
        if ("conscrypt".equals(System.getProperty("okhttp.platform"))) {
            return true;
        }
        return "Conscrypt".equals(Security.getProviders()[0].getName());
    }

    public String a() {
        return "OkHttp";
    }

    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    public void a(SSLSocket sSLSocket) {
    }

    public void a(SSLSocket sSLSocket, String str, List<y> list) {
    }

    public String b(SSLSocket sSLSocket) {
        return null;
    }

    public void b(SSLSocketFactory sSLSocketFactory) {
    }

    public boolean b(String str) {
        return true;
    }

    protected X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        try {
            Object objA = a(sSLSocketFactory, Class.forName("sun.security.ssl.SSLContextImpl"), com.umeng.analytics.pro.b.Q);
            if (objA == null) {
                return null;
            }
            return (X509TrustManager) a(objA, X509TrustManager.class, "trustManager");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public void a(int i, String str, Throwable th) {
        f4607b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    private static f c() {
        f fVarC;
        f fVarC2 = a.c();
        if (fVarC2 != null) {
            return fVarC2;
        }
        if (e() && (fVarC = b.c()) != null) {
            return fVarC;
        }
        c cVarC = c.c();
        if (cVarC != null) {
            return cVarC;
        }
        f fVarC3 = d.c();
        return fVarC3 != null ? fVarC3 : new f();
    }

    public Object a(String str) {
        if (f4607b.isLoggable(Level.FINE)) {
            return new Throwable(str);
        }
        return null;
    }

    public void a(String str, Object obj) {
        if (obj == null) {
            str = str + " To see where this was allocated, set the OkHttpClient logger level to FINE: Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINE);";
        }
        a(5, str, (Throwable) obj);
    }

    public static List<String> a(List<y> list) {
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            y yVar = list.get(i);
            if (yVar != y.HTTP_1_0) {
                arrayList.add(yVar.toString());
            }
        }
        return arrayList;
    }

    public SSLContext b() {
        if ("1.7".equals(System.getProperty("java.specification.version"))) {
            try {
                return SSLContext.getInstance("TLSv1.2");
            } catch (NoSuchAlgorithmException unused) {
            }
        }
        try {
            return SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No TLS provider", e2);
        }
    }

    public d.h0.l.c a(X509TrustManager x509TrustManager) {
        return new d.h0.l.a(b(x509TrustManager));
    }

    public d.h0.l.e b(X509TrustManager x509TrustManager) {
        return new d.h0.l.b(x509TrustManager.getAcceptedIssuers());
    }

    public d.h0.l.c a(SSLSocketFactory sSLSocketFactory) {
        X509TrustManager x509TrustManagerC = c(sSLSocketFactory);
        if (x509TrustManagerC != null) {
            return a(x509TrustManagerC);
        }
        throw new IllegalStateException("Unable to extract the trust manager on " + d() + ", sslSocketFactory is " + sSLSocketFactory.getClass());
    }

    static <T> T a(Object obj, Class<T> cls, String str) {
        Object objA;
        for (Class<?> superclass = obj.getClass(); superclass != Object.class; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                declaredField.setAccessible(true);
                Object obj2 = declaredField.get(obj);
                if (obj2 != null && cls.isInstance(obj2)) {
                    return cls.cast(obj2);
                }
                return null;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            } catch (NoSuchFieldException unused2) {
            }
        }
        if (str.equals("delegate") || (objA = a(obj, (Class<Object>) Object.class, "delegate")) == null) {
            return null;
        }
        return (T) a(objA, cls, str);
    }
}
