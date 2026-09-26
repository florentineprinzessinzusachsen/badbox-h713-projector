package l0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p.t f1311a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final c f1312b = new c(1);

    public f(p.t tVar) {
        this.f1311a = tVar;
    }

    public final Long a(String str) {
        return (Long) l3.h.W(this.f1311a, true, false, new b(3, str));
    }

    public final void b(e eVar) {
        l3.h.W(this.f1311a, false, true, new h0.e(2, this, eVar));
    }
}
