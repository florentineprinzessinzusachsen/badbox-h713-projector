package r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f1887g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1888h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public u2.h f1889i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ h f1890j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(h hVar, y1.c cVar) {
        super(cVar);
        this.f1890j = hVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1887g = obj;
        this.f1888h |= Integer.MIN_VALUE;
        return this.f1890j.c(null, this);
    }
}
