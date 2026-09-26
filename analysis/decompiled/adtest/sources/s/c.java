package s;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public d f2068g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public x.a f2069h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f2070i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ d f2071j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2072k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(d dVar, a2.c cVar) {
        super(cVar);
        this.f2071j = dVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f2070i = obj;
        this.f2072k |= Integer.MIN_VALUE;
        return this.f2071j.e(null, null, this);
    }
}
