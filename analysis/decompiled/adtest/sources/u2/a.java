package u2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public v2.o f2304g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f2305h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ h0.o f2306i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2307j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(h0.o oVar, y1.c cVar) {
        super(cVar);
        this.f2306i = oVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f2305h = obj;
        this.f2307j |= Integer.MIN_VALUE;
        return this.f2306i.b(null, this);
    }
}
