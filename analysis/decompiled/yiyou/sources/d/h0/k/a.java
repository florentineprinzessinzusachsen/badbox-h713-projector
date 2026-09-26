package d.h0.k;

import android.os.Build;
import android.util.Log;
import d.y;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

/* JADX INFO: compiled from: AndroidPlatform.java */
/* JADX INFO: loaded from: classes.dex */
class a extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Class<?> f4583c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final e<Socket> f4584d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final e<Socket> f4585e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final e<Socket> f4586f;
    private final e<Socket> g;
    private final c h = c.a();

    /* JADX INFO: renamed from: d.h0.k.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AndroidPlatform.java */
    static final class C0102a extends d.h0.l.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Object f4587a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Method f4588b;

        C0102a(Object obj, Method method) {
            this.f4587a = obj;
            this.f4588b = method;
        }

        @Override // d.h0.l.c
        public List<Certificate> a(List<Certificate> list, String str) throws SSLPeerUnverifiedException {
            try {
                return (List) this.f4588b.invoke(this.f4587a, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), "RSA", str);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            } catch (InvocationTargetException e3) {
                SSLPeerUnverifiedException sSLPeerUnverifiedException = new SSLPeerUnverifiedException(e3.getMessage());
                sSLPeerUnverifiedException.initCause(e3);
                throw sSLPeerUnverifiedException;
            }
        }

        public boolean equals(Object obj) {
            return obj instanceof C0102a;
        }

        public int hashCode() {
            return 0;
        }
    }

    /* JADX INFO: compiled from: AndroidPlatform.java */
    static final class b implements d.h0.l.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final X509TrustManager f4589a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Method f4590b;

        b(X509TrustManager x509TrustManager, Method method) {
            this.f4590b = method;
            this.f4589a = x509TrustManager;
        }

        @Override // d.h0.l.e
        public X509Certificate a(X509Certificate x509Certificate) {
            try {
                TrustAnchor trustAnchor = (TrustAnchor) this.f4590b.invoke(this.f4589a, x509Certificate);
                if (trustAnchor != null) {
                    return trustAnchor.getTrustedCert();
                }
                return null;
            } catch (IllegalAccessException e2) {
                throw d.h0.c.a("unable to get issues and signature", (Exception) e2);
            } catch (InvocationTargetException unused) {
                return null;
            }
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return this.f4589a.equals(bVar.f4589a) && this.f4590b.equals(bVar.f4590b);
        }

        public int hashCode() {
            return this.f4589a.hashCode() + (this.f4590b.hashCode() * 31);
        }
    }

    a(Class<?> cls, e<Socket> eVar, e<Socket> eVar2, e<Socket> eVar3, e<Socket> eVar4) {
        this.f4583c = cls;
        this.f4584d = eVar;
        this.f4585e = eVar2;
        this.f4586f = eVar3;
        this.g = eVar4;
    }

    private static boolean f() {
        if (Security.getProvider("GMSCore_OpenSSL") != null) {
            return true;
        }
        try {
            Class.forName("android.net.Network");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // d.h0.k.f
    public void a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e2) {
            if (!d.h0.c.a(e2)) {
                throw e2;
            }
            throw new IOException(e2);
        } catch (ClassCastException e3) {
            if (Build.VERSION.SDK_INT != 26) {
                throw e3;
            }
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e3);
            throw iOException;
        } catch (SecurityException e4) {
            IOException iOException2 = new IOException("Exception in connect");
            iOException2.initCause(e4);
            throw iOException2;
        }
    }

    @Override // d.h0.k.f
    public String b(SSLSocket sSLSocket) {
        byte[] bArr;
        e<Socket> eVar = this.f4586f;
        if (eVar == null || !eVar.a(sSLSocket) || (bArr = (byte[]) this.f4586f.d(sSLSocket, new Object[0])) == null) {
            return null;
        }
        return new String(bArr, d.h0.c.i);
    }

    @Override // d.h0.k.f
    protected X509TrustManager c(SSLSocketFactory sSLSocketFactory) {
        Object objA = f.a(sSLSocketFactory, this.f4583c, "sslParameters");
        if (objA == null) {
            try {
                objA = f.a(sSLSocketFactory, Class.forName("com.google.android.gms.org.conscrypt.SSLParametersImpl", false, sSLSocketFactory.getClass().getClassLoader()), "sslParameters");
            } catch (ClassNotFoundException unused) {
                return super.c(sSLSocketFactory);
            }
        }
        X509TrustManager x509TrustManager = (X509TrustManager) f.a(objA, X509TrustManager.class, "x509TrustManager");
        return x509TrustManager != null ? x509TrustManager : (X509TrustManager) f.a(objA, X509TrustManager.class, "trustManager");
    }

    /* JADX INFO: compiled from: AndroidPlatform.java */
    static final class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Method f4591a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Method f4592b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Method f4593c;

        c(Method method, Method method2, Method method3) {
            this.f4591a = method;
            this.f4592b = method2;
            this.f4593c = method3;
        }

        Object a(String str) {
            Method method = this.f4591a;
            if (method != null) {
                try {
                    Object objInvoke = method.invoke(null, new Object[0]);
                    this.f4592b.invoke(objInvoke, str);
                    return objInvoke;
                } catch (Exception unused) {
                }
            }
            return null;
        }

        boolean a(Object obj) {
            if (obj == null) {
                return false;
            }
            try {
                this.f4593c.invoke(obj, new Object[0]);
                return true;
            } catch (Exception unused) {
                return false;
            }
        }

        static c a() throws NoSuchMethodException {
            Method method;
            Method method2;
            Method method3 = null;
            try {
                Class<?> cls = Class.forName("dalvik.system.CloseGuard");
                Method method4 = cls.getMethod("get", new Class[0]);
                method2 = cls.getMethod("open", String.class);
                method = cls.getMethod("warnIfOpen", new Class[0]);
                method3 = method4;
            } catch (Exception unused) {
                method = null;
                method2 = null;
            }
            return new c(method3, method2, method);
        }
    }

    @Override // d.h0.k.f
    public boolean b(String str) {
        try {
            Class<?> cls = Class.forName("android.security.NetworkSecurityPolicy");
            return b(str, cls, cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return super.b(str);
        } catch (IllegalAccessException e2) {
            e = e2;
            throw d.h0.c.a("unable to determine cleartext support", e);
        } catch (IllegalArgumentException e3) {
            e = e3;
            throw d.h0.c.a("unable to determine cleartext support", e);
        } catch (InvocationTargetException e4) {
            e = e4;
            throw d.h0.c.a("unable to determine cleartext support", e);
        }
    }

    public static f c() {
        Class<?> cls;
        e eVar;
        e eVar2;
        try {
            try {
                cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
            } catch (ClassNotFoundException unused) {
                cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
            }
            Class<?> cls2 = cls;
            e eVar3 = new e(null, "setUseSessionTickets", Boolean.TYPE);
            e eVar4 = new e(null, "setHostname", String.class);
            if (f()) {
                e eVar5 = new e(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                eVar2 = new e(null, "setAlpnProtocols", byte[].class);
                eVar = eVar5;
            } else {
                eVar = null;
                eVar2 = null;
            }
            return new a(cls2, eVar3, eVar4, eVar, eVar2);
        } catch (ClassNotFoundException unused2) {
            return null;
        }
    }

    private boolean b(String str, Class<?> cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", String.class).invoke(obj, str)).booleanValue();
        } catch (NoSuchMethodException unused) {
            return a(str, cls, obj);
        }
    }

    @Override // d.h0.k.f
    public void a(SSLSocket sSLSocket, String str, List<y> list) {
        if (str != null) {
            this.f4584d.c(sSLSocket, true);
            this.f4585e.c(sSLSocket, str);
        }
        e<Socket> eVar = this.g;
        if (eVar == null || !eVar.a(sSLSocket)) {
            return;
        }
        this.g.d(sSLSocket, f.b(list));
    }

    @Override // d.h0.k.f
    public d.h0.l.e b(X509TrustManager x509TrustManager) {
        try {
            Method declaredMethod = x509TrustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            declaredMethod.setAccessible(true);
            return new b(x509TrustManager, declaredMethod);
        } catch (NoSuchMethodException unused) {
            return super.b(x509TrustManager);
        }
    }

    @Override // d.h0.k.f
    public void a(int i, String str, Throwable th) {
        int iMin;
        int i2 = i != 5 ? 3 : 5;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int iIndexOf = str.indexOf(10, i3);
            if (iIndexOf == -1) {
                iIndexOf = length;
            }
            while (true) {
                iMin = Math.min(iIndexOf, i3 + 4000);
                Log.println(i2, "OkHttp", str.substring(i3, iMin));
                if (iMin >= iIndexOf) {
                    break;
                } else {
                    i3 = iMin;
                }
            }
            i3 = iMin + 1;
        }
    }

    @Override // d.h0.k.f
    public SSLContext b() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 16 && i < 22) {
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

    @Override // d.h0.k.f
    public Object a(String str) {
        return this.h.a(str);
    }

    @Override // d.h0.k.f
    public void a(String str, Object obj) {
        if (this.h.a(obj)) {
            return;
        }
        a(5, str, (Throwable) null);
    }

    private boolean a(String str, Class<?> cls, Object obj) {
        try {
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(obj, new Object[0])).booleanValue();
        } catch (NoSuchMethodException unused) {
            return super.b(str);
        }
    }

    @Override // d.h0.k.f
    public d.h0.l.c a(X509TrustManager x509TrustManager) {
        try {
            Class<?> cls = Class.forName("android.net.http.X509TrustManagerExtensions");
            return new C0102a(cls.getConstructor(X509TrustManager.class).newInstance(x509TrustManager), cls.getMethod("checkServerTrusted", X509Certificate[].class, String.class, String.class));
        } catch (Exception unused) {
            return super.a(x509TrustManager);
        }
    }
}
