package u2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public e f2330g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f2331h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e f2332i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2333j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(e eVar, y1.c cVar) {
        super(cVar);
        this.f2332i = eVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f2331h = obj;
        this.f2333j |= Integer.MIN_VALUE;
        return this.f2332i.c(null, this);
    }
}
