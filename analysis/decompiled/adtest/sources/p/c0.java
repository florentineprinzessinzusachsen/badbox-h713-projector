package p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c0 extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public i0 f1609g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public c3.b f1610h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f1611i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ i0 f1612j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1613k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c0(i0 i0Var, a2.c cVar) {
        super(cVar);
        this.f1612j = i0Var;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1611i = obj;
        this.f1613k |= Integer.MIN_VALUE;
        return i0.b(this.f1612j, this);
    }
}
