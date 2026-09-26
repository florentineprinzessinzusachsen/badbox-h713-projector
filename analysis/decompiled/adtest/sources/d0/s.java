package d0;

import java.util.concurrent.atomic.AtomicBoolean;
import r2.v0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f501d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f502e;

    public /* synthetic */ s(int i4, Object obj) {
        this.f501d = i4;
        this.f502e = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f501d) {
            case 0:
                ((AtomicBoolean) this.f502e).set(true);
                break;
            case 1:
                ((AtomicBoolean) this.f502e).set(true);
                break;
            default:
                v0 v0Var = (v0) this.f502e;
                if (v0Var != null) {
                    v0Var.b(null);
                }
                break;
        }
    }
}
