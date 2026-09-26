package r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public k f1898g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public /* synthetic */ Object f1899h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ k f1900i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f1901j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(k kVar, a2.c cVar) {
        super(cVar);
        this.f1900i = kVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1899h = obj;
        this.f1901j |= Integer.MIN_VALUE;
        return this.f1900i.a(this);
    }
}
