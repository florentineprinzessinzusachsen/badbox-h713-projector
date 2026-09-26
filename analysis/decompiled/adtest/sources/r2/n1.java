package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n1 extends w2.q implements Runnable {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final long f2005h;

    public n1(long j4, a2.c cVar) {
        super(cVar, cVar.g());
        this.f2005h = j4;
    }

    @Override // r2.d1
    public final String O() {
        return super.O() + "(timeMillis=" + this.f2005h + ')';
    }

    @Override // java.lang.Runnable
    public final void run() {
        x.j(this.f1948f);
        r(new m1("Timed out waiting for " + this.f2005h + " ms", this));
    }
}
