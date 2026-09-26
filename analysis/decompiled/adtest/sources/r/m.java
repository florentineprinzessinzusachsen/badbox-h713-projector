package r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m implements t, p.n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1913a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f1914b;

    public /* synthetic */ m(int i4, Object obj) {
        this.f1913a = i4;
        this.f1914b = obj;
    }

    @Override // p.n
    public final Object b(String str, i2.l lVar, a2.c cVar) {
        switch (this.f1913a) {
            case 0:
                return ((s) this.f1914b).b(str, lVar, cVar);
            default:
                return ((s.d) this.f1914b).b(str, lVar, cVar);
        }
    }

    @Override // r.t
    public final w.a d() {
        switch (this.f1913a) {
            case 0:
                return ((s) this.f1914b).f1941a;
            default:
                return ((s.d) this.f1914b).f2073a;
        }
    }
}
