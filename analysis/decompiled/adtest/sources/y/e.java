package y;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends RuntimeException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f2684d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Throwable f2685e;

    public e(f fVar, Throwable th) {
        super(th);
        this.f2684d = fVar;
        this.f2685e = th;
    }

    @Override // java.lang.Throwable
    public final Throwable getCause() {
        return this.f2685e;
    }
}
