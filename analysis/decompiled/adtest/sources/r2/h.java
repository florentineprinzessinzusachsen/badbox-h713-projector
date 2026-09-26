package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements i2.q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1980d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1981e;

    public /* synthetic */ h(int i4, Object obj) {
        this.f1980d = i4;
        this.f1981e = obj;
    }

    @Override // i2.q
    public final Object d(Object obj, Object obj2, Object obj3) {
        switch (this.f1980d) {
            case 0:
                ((f1.c) this.f1981e).h((Throwable) obj);
                break;
            case 1:
                z2.c cVar = (z2.c) this.f1981e;
                z2.c.f2785k.set(cVar, null);
                cVar.b(null);
                break;
            default:
                ((z2.g) this.f1981e).d();
                break;
        }
        return u1.k.f2301a;
    }

    public /* synthetic */ h(z2.c cVar, z2.b bVar) {
        this.f1980d = 1;
        this.f1981e = cVar;
    }
}
