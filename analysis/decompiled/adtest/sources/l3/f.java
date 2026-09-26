package l3;

import android.os.Build;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class f implements o {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final a1.a f1376f = new a1.a(7);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Class f1377a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f1378b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Method f1379c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Method f1380d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f1381e;

    public f(Class cls) throws NoSuchMethodException {
        this.f1377a = cls;
        Method declaredMethod = cls.getDeclaredMethod("setUseSessionTickets", Boolean.TYPE);
        j2.i.d(declaredMethod, "getDeclaredMethod(...)");
        this.f1378b = declaredMethod;
        this.f1379c = cls.getMethod("setHostname", String.class);
        this.f1380d = cls.getMethod("getAlpnSelectedProtocol", null);
        this.f1381e = cls.getMethod("setAlpnProtocols", byte[].class);
    }

    @Override // l3.o
    public final boolean a(SSLSocket sSLSocket) {
        return this.f1377a.isInstance(sSLSocket);
    }

    @Override // l3.o
    public final String b(SSLSocket sSLSocket) {
        if (this.f1377a.isInstance(sSLSocket)) {
            try {
                byte[] bArr = (byte[]) this.f1380d.invoke(sSLSocket, null);
                if (bArr != null) {
                    return new String(bArr, p2.a.f1738a);
                }
            } catch (IllegalAccessException e4) {
                throw new AssertionError(e4);
            } catch (InvocationTargetException e5) {
                Throwable cause = e5.getCause();
                if (!(cause instanceof NullPointerException) || !j2.i.a(((NullPointerException) cause).getMessage(), "ssl == null")) {
                    throw new AssertionError(e5);
                }
            }
        }
        return null;
    }

    @Override // l3.o
    public final boolean c() {
        boolean z3 = k3.c.f1297e;
        return k3.c.f1297e;
    }

    @Override // l3.o
    public final void d(SSLSocket sSLSocket, String str, List list) {
        j2.i.e(list, "protocols");
        if (this.f1377a.isInstance(sSLSocket)) {
            try {
                this.f1378b.invoke(sSLSocket, Boolean.TRUE);
                if (str != null && Build.VERSION.SDK_INT <= 23) {
                    this.f1379c.invoke(sSLSocket, str);
                }
                Method method = this.f1381e;
                k3.e eVar = k3.e.f1300a;
                method.invoke(sSLSocket, a1.a.k(list));
            } catch (IllegalAccessException e4) {
                throw new AssertionError(e4);
            } catch (InvocationTargetException e5) {
                throw new AssertionError(e5);
            }
        }
    }
}
