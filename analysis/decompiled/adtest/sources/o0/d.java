package o0;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f1549g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1550h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ e f1551i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(e eVar, y1.c cVar) {
        super(cVar);
        this.f1551i = eVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1549g = obj;
        this.f1550h |= Integer.MIN_VALUE;
        return this.f1551i.c(null, this);
    }
}
