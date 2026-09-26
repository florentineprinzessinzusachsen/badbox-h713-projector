package e0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j0 extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f639g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ k0 f640h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f641i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j0(k0 k0Var, a2.c cVar) {
        super(cVar);
        this.f640h = k0Var;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f639g = obj;
        this.f641i |= Integer.MIN_VALUE;
        return k0.a(this.f640h, this);
    }
}
