package r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public s f1922g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public f f1923h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1924i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f1925j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ s f1926k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1927l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p(s sVar, a2.c cVar) {
        super(cVar);
        this.f1926k = sVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1925j = obj;
        this.f1927l |= Integer.MIN_VALUE;
        return this.f1926k.f(false, this);
    }
}
