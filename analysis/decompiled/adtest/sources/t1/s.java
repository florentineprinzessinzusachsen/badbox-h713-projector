package t1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class s implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2161d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Exception f2162e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ i2.p f2163f;

    public /* synthetic */ s(Exception exc, i2.p pVar, int i4) {
        this.f2161d = i4;
        this.f2162e = exc;
        this.f2163f = pVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2161d) {
            case 0:
                this.f2163f.f(null, this.f2162e);
                break;
            default:
                this.f2163f.f(null, this.f2162e);
                break;
        }
    }
}
