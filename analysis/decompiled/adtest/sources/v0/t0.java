package v0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t0 implements s0.c0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ Class f2491d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Class f2492e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ s0.b0 f2493f;

    public t0(Class cls, Class cls2, s0.b0 b0Var) {
        this.f2491d = cls;
        this.f2492e = cls2;
        this.f2493f = b0Var;
    }

    @Override // s0.c0
    public final s0.b0 a(s0.n nVar, z0.a aVar) {
        Class cls = aVar.f2778a;
        if (cls == this.f2491d || cls == this.f2492e) {
            return this.f2493f;
        }
        return null;
    }

    public final String toString() {
        return "Factory[type=" + this.f2492e.getName() + "+" + this.f2491d.getName() + ",adapter=" + this.f2493f + "]";
    }
}
