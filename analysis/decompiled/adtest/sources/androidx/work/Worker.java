package androidx.work;

import a.a;
import android.content.Context;
import d0.n0;
import d0.r;
import d0.x;
import d0.z;
import g.l;
import j2.i;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Worker extends z {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Worker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        i.e(context, "context");
        i.e(workerParameters, "workerParams");
    }

    @Override // d0.z
    public final l a() {
        ExecutorService executorService = this.f515b.f312c;
        i.d(executorService, "getBackgroundExecutor(...)");
        return a.l(new r(executorService, new n0(this, 1), 1));
    }

    @Override // d0.z
    public final l b() {
        ExecutorService executorService = this.f515b.f312c;
        i.d(executorService, "getBackgroundExecutor(...)");
        return a.l(new r(executorService, new n0(this, 0), 1));
    }

    public abstract x c();
}
