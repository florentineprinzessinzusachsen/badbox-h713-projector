package u2;

/* JADX INFO: loaded from: classes.dex */
public final class o extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public /* synthetic */ Object f2348g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2349h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ h0.p f2350i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Object f2351j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public h f2352k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(h0.p pVar, y1.c cVar) {
        super(cVar);
        this.f2350i = pVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f2348g = obj;
        this.f2349h |= Integer.MIN_VALUE;
        return this.f2350i.c(null, this);
    }
}
