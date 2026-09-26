package e0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n extends a2.i implements i2.r {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f663h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Throwable f664i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ long f665j;

    @Override // a2.a
    public final Object l(Object obj) {
        int i4 = this.f663h;
        if (i4 == 0) {
            d0.l0.M(obj);
            Throwable th = this.f664i;
            long j4 = this.f665j;
            d0.a0.e().d(p.f668a, "Cannot check for unfinished work", th);
            long jMin = Math.min(j4 * ((long) 30000), p.f669b);
            this.f663h = 1;
            Object objF = r2.x.f(jMin, this);
            z1.a aVar = z1.a.f2781d;
            if (objF == aVar) {
                return aVar;
            }
        } else {
            if (i4 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            d0.l0.M(obj);
        }
        return Boolean.TRUE;
    }
}
