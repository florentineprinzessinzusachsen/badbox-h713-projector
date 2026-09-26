package n0;

import a3.l;
import android.os.Handler;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Executor {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ l f1484d;

    public a(l lVar) {
        this.f1484d = lVar;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        ((Handler) this.f1484d.f186g).post(runnable);
    }
}
