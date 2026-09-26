package t1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2155d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Exception f2156e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ i2.l f2157f;

    public /* synthetic */ n(Exception exc, i2.l lVar, int i4) {
        this.f2155d = i4;
        this.f2156e = exc;
        this.f2157f = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f2155d) {
            case 0:
                a1.c.f("Failed to upload logs: ", this.f2156e.getMessage());
                this.f2157f.h(Boolean.FALSE);
                break;
            default:
                a1.c.f("Failed to upload logs: ", this.f2156e.getMessage());
                this.f2157f.h(Boolean.FALSE);
                break;
        }
    }
}
