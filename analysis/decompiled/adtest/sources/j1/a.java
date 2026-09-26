package j1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f1233g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ e f1234h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1235i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(e eVar, a2.c cVar) {
        super(cVar);
        this.f1234h = eVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1233g = obj;
        this.f1235i |= Integer.MIN_VALUE;
        return this.f1234h.a(null, this);
    }
}
