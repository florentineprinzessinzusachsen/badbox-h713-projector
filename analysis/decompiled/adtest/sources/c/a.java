package c;

import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class a implements Executor {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f350d;

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f350d) {
            case 0:
                b.J().f353h.f356i.execute(runnable);
                break;
            default:
                runnable.run();
                break;
        }
    }
}
