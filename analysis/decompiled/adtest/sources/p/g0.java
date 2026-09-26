package p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g0 extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public c3.b f1641g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f1642h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ i0 f1643i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1644j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g0(i0 i0Var, a2.c cVar) {
        super(cVar);
        this.f1643i = i0Var;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1642h = obj;
        this.f1644j |= Integer.MIN_VALUE;
        return this.f1643i.f(this);
    }
}
