package c;

import android.os.Looper;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static volatile b f351i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a f352j = new a(0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e f353h = new e();

    public static b J() {
        if (f351i != null) {
            return f351i;
        }
        synchronized (b.class) {
            try {
                if (f351i == null) {
                    f351i = new b();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return f351i;
    }

    public final void K(Runnable runnable) {
        e eVar = this.f353h;
        if (eVar.f357j == null) {
            synchronized (eVar.f355h) {
                try {
                    if (eVar.f357j == null) {
                        eVar.f357j = e.J(Looper.getMainLooper());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        eVar.f357j.post(runnable);
    }
}
