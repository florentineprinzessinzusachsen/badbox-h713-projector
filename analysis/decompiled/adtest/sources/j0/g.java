package j0;

import android.content.Context;
import java.util.LinkedHashSet;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a3.l f1220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f1221b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f1222c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final LinkedHashSet f1223d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f1224e;

    public g(Context context, a3.l lVar) {
        this.f1220a = lVar;
        Context applicationContext = context.getApplicationContext();
        j2.i.d(applicationContext, "getApplicationContext(...)");
        this.f1221b = applicationContext;
        this.f1222c = new Object();
        this.f1223d = new LinkedHashSet();
    }

    public abstract Object a();

    public final void b(Object obj) {
        synchronized (this.f1222c) {
            Object obj2 = this.f1224e;
            if (obj2 == null || !obj2.equals(obj)) {
                this.f1224e = obj;
                ((n0.a) this.f1220a.f187h).execute(new e0.e(2, v1.j.G0(this.f1223d), this));
            }
        }
    }

    public abstract void c();

    public abstract void d();
}
