package f0;

import a3.h;
import android.os.Handler;
import e0.l;
import j2.i;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f861a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c3.b f862b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f863c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f864d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f865e;

    public e(h hVar, c3.b bVar) {
        i.e(hVar, "runnableScheduler");
        long millis = TimeUnit.MINUTES.toMillis(90L);
        this.f861a = hVar;
        this.f862b = bVar;
        this.f863c = millis;
        this.f864d = new Object();
        this.f865e = new LinkedHashMap();
    }

    public final void a(l lVar) {
        Runnable runnable;
        i.e(lVar, "token");
        synchronized (this.f864d) {
            runnable = (Runnable) this.f865e.remove(lVar);
        }
        if (runnable != null) {
            ((Handler) this.f861a.f149e).removeCallbacks(runnable);
        }
    }

    public final void b(l lVar) {
        i.e(lVar, "token");
        e0.e eVar = new e0.e(1, this, lVar);
        synchronized (this.f864d) {
        }
        h hVar = this.f861a;
        ((Handler) hVar.f149e).postDelayed(eVar, this.f863c);
    }
}
