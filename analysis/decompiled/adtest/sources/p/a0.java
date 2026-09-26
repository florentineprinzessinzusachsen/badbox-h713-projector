package p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b0 f1599g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int[] f1600h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f1601i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ b0 f1602j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1603k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a0(b0 b0Var, y1.c cVar) {
        super(cVar);
        this.f1602j = b0Var;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1601i = obj;
        this.f1603k |= Integer.MIN_VALUE;
        return this.f1602j.c(null, this);
    }
}
