package d.h0.k;

import d.y;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
/* JADX INFO: loaded from: classes.dex */
class d extends f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Method f4596c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Method f4597d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Method f4598e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final Class<?> f4599f;
    private final Class<?> g;

    /* JADX INFO: compiled from: JdkWithJettyBootPlatform.java */
    private static class a implements InvocationHandler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<String> f4600a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f4601b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        String f4602c;

        a(List<String> list) {
            this.f4600a = list;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            Class<?> returnType = method.getReturnType();
            if (objArr == null) {
                objArr = d.h0.c.f4338b;
            }
            if (name.equals("supports") && Boolean.TYPE == returnType) {
                return true;
            }
            if (name.equals("unsupported") && Void.TYPE == returnType) {
                this.f4601b = true;
                return null;
            }
            if (name.equals("protocols") && objArr.length == 0) {
                return this.f4600a;
            }
            if ((!name.equals("selectProtocol") && !name.equals("select")) || String.class != returnType || objArr.length != 1 || !(objArr[0] instanceof List)) {
                if ((!name.equals("protocolSelected") && !name.equals("selected")) || objArr.length != 1) {
                    return method.invoke(this, objArr);
                }
                this.f4602c = (String) objArr[0];
                return null;
            }
            List list = (List) objArr[0];
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (this.f4600a.contains(list.get(i))) {
                    String str = (String) list.get(i);
                    this.f4602c = str;
                    return str;
                }
            }
            String str2 = this.f4600a.get(0);
            this.f4602c = str2;
            return str2;
        }
    }

    d(Method method, Method method2, Method method3, Class<?> cls, Class<?> cls2) {
        this.f4596c = method;
        this.f4597d = method2;
        this.f4598e = method3;
        this.f4599f = cls;
        this.g = cls2;
    }

    public static f c() {
        try {
            Class<?> cls = Class.forName("org.eclipse.jetty.alpn.ALPN");
            Class<?> cls2 = Class.forName("org.eclipse.jetty.alpn.ALPN$Provider");
            return new d(cls.getMethod("put", SSLSocket.class, cls2), cls.getMethod("get", SSLSocket.class), cls.getMethod("remove", SSLSocket.class), Class.forName("org.eclipse.jetty.alpn.ALPN$ClientProvider"), Class.forName("org.eclipse.jetty.alpn.ALPN$ServerProvider"));
        } catch (ClassNotFoundException | NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // d.h0.k.f
    public void a(SSLSocket sSLSocket, String str, List<y> list) {
        try {
            this.f4596c.invoke(null, sSLSocket, Proxy.newProxyInstance(f.class.getClassLoader(), new Class[]{this.f4599f, this.g}, new a(f.a(list))));
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw d.h0.c.a("unable to set alpn", (Exception) e2);
        }
    }

    @Override // d.h0.k.f
    public String b(SSLSocket sSLSocket) {
        try {
            a aVar = (a) Proxy.getInvocationHandler(this.f4597d.invoke(null, sSLSocket));
            if (!aVar.f4601b && aVar.f4602c == null) {
                f.d().a(4, "ALPN callback dropped: HTTP/2 is disabled. Is alpn-boot on the boot class path?", (Throwable) null);
                return null;
            }
            if (aVar.f4601b) {
                return null;
            }
            return aVar.f4602c;
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw d.h0.c.a("unable to get selected protocol", (Exception) e2);
        }
    }

    @Override // d.h0.k.f
    public void a(SSLSocket sSLSocket) {
        try {
            this.f4598e.invoke(null, sSLSocket);
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw d.h0.c.a("unable to remove alpn", (Exception) e2);
        }
    }
}
