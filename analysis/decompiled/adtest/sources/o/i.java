package o;

import android.content.Context;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1529d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Context f1530e;

    public /* synthetic */ i(Context context, int i4) {
        this.f1529d = i4;
        this.f1530e = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1529d) {
            case 0:
                new ThreadPoolExecutor(0, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue()).execute(new i(this.f1530e, 1));
                break;
            default:
                g.t(this.f1530e, new c.a(1), g.f1518a, false);
                break;
        }
    }
}
