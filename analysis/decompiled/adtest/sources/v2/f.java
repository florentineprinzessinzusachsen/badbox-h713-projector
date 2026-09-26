package v2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f2533g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ g f2534h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2535i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(g gVar, y1.c cVar) {
        super(cVar);
        this.f2534h = gVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f2533g = obj;
        this.f2535i |= Integer.MIN_VALUE;
        return this.f2534h.c(null, this);
    }
}
