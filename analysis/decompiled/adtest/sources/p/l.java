package p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f1680g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ a3.h f1681h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1682i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(a3.h hVar, a2.c cVar) {
        super(cVar);
        this.f1681h = hVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1680g = obj;
        this.f1682i |= Integer.MIN_VALUE;
        this.f1681h.e(null, this);
        return z1.a.f2781d;
    }
}
