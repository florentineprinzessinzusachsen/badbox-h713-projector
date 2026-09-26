package y2;

import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Runnable f2760f;

    public j(Runnable runnable, long j4, boolean z3) {
        super(j4, z3);
        this.f2760f = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f2760f.run();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Task[");
        Runnable runnable = this.f2760f;
        sb.append(runnable.getClass().getSimpleName());
        sb.append('@');
        sb.append(x.k(runnable));
        sb.append(", ");
        sb.append(this.f2758d);
        sb.append(", ");
        sb.append(this.f2759e ? "Blocking" : "Non-blocking");
        sb.append(']');
        return sb.toString();
    }
}
