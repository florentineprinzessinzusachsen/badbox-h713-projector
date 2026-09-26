package u2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public t2.s f2308g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f2309h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c f2310i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2311j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(c cVar, a2.c cVar2) {
        super(cVar2);
        this.f2310i = cVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f2309h = obj;
        this.f2311j |= Integer.MIN_VALUE;
        return this.f2310i.c(null, this);
    }
}
