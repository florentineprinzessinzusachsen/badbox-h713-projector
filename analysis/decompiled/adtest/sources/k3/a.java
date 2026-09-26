package k3;

import android.content.Context;
import android.net.http.X509TrustManagerExtensions;
import android.os.Build;
import android.os.StrictMode;
import android.security.NetworkSecurityPolicy;
import android.util.CloseGuard;
import android.util.Log;
import d0.l0;
import h0.j;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.X509TrustManager;
import l3.f;
import l3.i;
import l3.l;
import l3.n;
import l3.o;
import v1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends e implements d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f1292e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Context f1293c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayList f1294d;

    static {
        f1292e = Build.VERSION.SDK_INT >= 29;
    }

    public a() {
        int i4 = 0;
        ArrayList arrayListS0 = k.s0(Build.VERSION.SDK_INT >= 29 ? new l3.b() : null, new n(f.f1376f), new n(l.f1391a), new n(i.f1389a));
        ArrayList arrayList = new ArrayList();
        int size = arrayListS0.size();
        while (i4 < size) {
            Object obj = arrayListS0.get(i4);
            i4++;
            if (((o) obj).c()) {
                arrayList.add(obj);
            }
        }
        this.f1294d = arrayList;
    }

    @Override // k3.d
    public final void a(Context context) {
        this.f1293c = context;
    }

    @Override // k3.d
    public final Context b() {
        return this.f1293c;
    }

    @Override // k3.e
    public final l0 c(X509TrustManager x509TrustManager) {
        X509TrustManagerExtensions x509TrustManagerExtensions;
        try {
            x509TrustManagerExtensions = new X509TrustManagerExtensions(x509TrustManager);
        } catch (IllegalArgumentException unused) {
            x509TrustManagerExtensions = null;
        }
        l3.c cVar = x509TrustManagerExtensions != null ? new l3.c(x509TrustManager, x509TrustManagerExtensions) : null;
        return cVar != null ? cVar : new o3.a(d(x509TrustManager));
    }

    @Override // k3.e
    public final o3.d d(X509TrustManager x509TrustManager) {
        StrictMode.noteSlowCall("buildTrustRootIndex");
        return super.d(x509TrustManager);
    }

    @Override // k3.e
    public final void e(SSLSocket sSLSocket, String str, List list) {
        Object obj;
        j2.i.e(list, "protocols");
        ArrayList arrayList = this.f1294d;
        int size = arrayList.size();
        int i4 = 0;
        do {
            if (i4 >= size) {
                obj = null;
                break;
            } else {
                obj = arrayList.get(i4);
                i4++;
            }
        } while (!((o) obj).a(sSLSocket));
        o oVar = (o) obj;
        if (oVar != null) {
            oVar.d(sSLSocket, str, list);
        }
    }

    @Override // k3.e
    public final String g(SSLSocket sSLSocket) {
        Object obj;
        ArrayList arrayList = this.f1294d;
        int size = arrayList.size();
        int i4 = 0;
        do {
            if (i4 >= size) {
                obj = null;
                break;
            }
            obj = arrayList.get(i4);
            i4++;
        } while (!((o) obj).a(sSLSocket));
        o oVar = (o) obj;
        if (oVar != null) {
            return oVar.b(sSLSocket);
        }
        return null;
    }

    @Override // k3.e
    public final Object h() {
        if (Build.VERSION.SDK_INT < 30) {
            return super.h();
        }
        CloseGuard closeGuardD = j.d();
        closeGuardD.open("response.body().close()");
        return closeGuardD;
    }

    @Override // k3.e
    public final boolean i(String str) {
        j2.i.e(str, "hostname");
        return NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted(str);
    }

    @Override // k3.e
    public final void j(String str, int i4, Throwable th) {
        j2.i.e(str, "message");
        if (i4 == 5) {
            boolean z3 = c.f1297e;
            Log.w("OkHttp", str, th);
        } else {
            boolean z4 = c.f1297e;
            Log.i("OkHttp", str, th);
        }
    }

    @Override // k3.e
    public final void k(Object obj, String str) {
        j2.i.e(str, "message");
        if (Build.VERSION.SDK_INT < 30) {
            super.k(obj, str);
        } else {
            j2.i.c(obj, "null cannot be cast to non-null type android.util.CloseGuard");
            j.e(obj).warnIfOpen();
        }
    }

    @Override // k3.e
    public final SSLContext l() {
        StrictMode.noteSlowCall("newSSLContext");
        return super.l();
    }
}
