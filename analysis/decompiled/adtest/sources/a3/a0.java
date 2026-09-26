package a3;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import d0.l0;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f61a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f62b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f63c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f64d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f65e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f66f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f67g;

    public a0(Context context, String str, Serializable serializable, i2.l lVar) {
        this.f61a = 1;
        d0.h hVar = new d0.h(5);
        this.f63c = context;
        this.f62b = str;
        this.f64d = serializable;
        this.f65e = hVar;
        this.f66f = lVar;
        this.f67g = new u1.i(new o(5, this));
    }

    public Object a(n2.c cVar) {
        Object objH;
        j2.i.e(cVar, "property");
        String string = ((SharedPreferences) ((u1.i) this.f67g).getValue()).getString(this.f62b, null);
        return (string == null || (objH = ((i2.l) this.f66f).h(string)) == null) ? this.f64d : objH;
    }

    public q3.l b() throws IOException {
        k3.e eVar = k3.e.f1300a;
        Object obj = k3.e.f1300a;
        k3.d dVar = obj != null ? (k3.d) obj : null;
        Context contextB = dVar != null ? dVar.b() : null;
        AssetManager assets = contextB != null ? contextB.getAssets() : null;
        if (assets == null) {
            if (Build.FINGERPRINT == null) {
                throw new IOException("Platform applicationContext not initialized. Possibly running Android unit test without Robolectric. Android tests should run with Robolectric and call OkHttp.initialize before test");
            }
            throw new IOException("Platform applicationContext not initialized. Startup Initializer possibly disabled, call OkHttp.initialize before test.");
        }
        InputStream inputStreamOpen = assets.open(this.f62b);
        j2.i.d(inputStreamOpen, "open(...)");
        return new q3.l(inputStreamOpen, new q3.w());
    }

    public z c() {
        z zVar = new z();
        zVar.f281b = (t) this.f63c;
        zVar.f280a = this.f62b;
        zVar.f283d = (b0) this.f65e;
        zVar.f284e = (b3.a) this.f66f;
        zVar.f282c = ((r) this.f64d).c();
        return zVar;
    }

    public void d() {
        try {
            q3.o oVarF = l0.f(b());
            try {
                q3.h hVarQ = oVarF.q(oVarF.readInt());
                q3.h hVarQ2 = oVarF.q(oVarF.readInt());
                oVarF.close();
                synchronized (this) {
                    j2.i.b(hVarQ);
                    this.f65e = hVarQ;
                    j2.i.b(hVarQ2);
                    this.f66f = hVarQ2;
                }
                ((CountDownLatch) this.f64d).countDown();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    l3.h.j(oVarF, th);
                    throw th2;
                }
            }
        } catch (Throwable th3) {
            ((CountDownLatch) this.f64d).countDown();
            throw th3;
        }
    }

    public void e(n2.c cVar, Object obj) {
        j2.i.e(cVar, "property");
        SharedPreferences sharedPreferences = (SharedPreferences) ((u1.i) this.f67g).getValue();
        j2.i.d(sharedPreferences, "<get-sharedPreferences>(...)");
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        j2.i.b(editorEdit);
        editorEdit.putString(this.f62b, (String) ((i2.l) this.f65e).h(obj));
        editorEdit.apply();
    }

    public String toString() {
        switch (this.f61a) {
            case 0:
                b3.a aVar = (b3.a) this.f66f;
                StringBuilder sb = new StringBuilder(32);
                sb.append("Request{method=");
                sb.append(this.f62b);
                sb.append(", url=");
                sb.append((t) this.f63c);
                r rVar = (r) this.f64d;
                if (rVar.size() != 0) {
                    sb.append(", headers=[");
                    Iterator it = rVar.iterator();
                    int i4 = 0;
                    while (true) {
                        j2.a aVar2 = (j2.a) it;
                        if (aVar2.hasNext()) {
                            Object next = aVar2.next();
                            int i5 = i4 + 1;
                            if (i4 < 0) {
                                throw new ArithmeticException("Index overflow has happened.");
                            }
                            u1.f fVar = (u1.f) next;
                            String str = (String) fVar.f2294d;
                            String str2 = (String) fVar.f2295e;
                            if (i4 > 0) {
                                sb.append(", ");
                            }
                            sb.append(str);
                            sb.append(':');
                            if (b3.d.j(str)) {
                                str2 = "██";
                            }
                            sb.append(str2);
                            i4 = i5;
                        } else {
                            sb.append(']');
                        }
                    }
                }
                if (!j2.i.a(aVar, b3.a.f339a)) {
                    sb.append(", tags=");
                    sb.append(aVar);
                }
                sb.append('}');
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public a0() {
        this.f61a = 2;
        this.f61a = 2;
        this.f63c = new AtomicBoolean(false);
        this.f64d = new CountDownLatch(1);
        this.f62b = "PublicSuffixDatabase.list";
    }

    public a0(z zVar) {
        this.f61a = 0;
        t tVar = (t) zVar.f281b;
        if (tVar != null) {
            this.f63c = tVar;
            this.f62b = (String) zVar.f280a;
            this.f64d = ((q) zVar.f282c).a();
            this.f65e = (b0) zVar.f283d;
            this.f66f = (b3.a) zVar.f284e;
            return;
        }
        throw new IllegalStateException("url == null");
    }
}
