package v0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r0 implements s0.c0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2486d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Class f2487e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ s0.b0 f2488f;

    public /* synthetic */ r0(Class cls, s0.b0 b0Var, int i4) {
        this.f2486d = i4;
        this.f2487e = cls;
        this.f2488f = b0Var;
    }

    @Override // s0.c0
    public final s0.b0 a(s0.n nVar, z0.a aVar) {
        switch (this.f2486d) {
            case 0:
                if (aVar.f2778a == this.f2487e) {
                    return this.f2488f;
                }
                return null;
            default:
                Class<?> cls = aVar.f2778a;
                if (this.f2487e.isAssignableFrom(cls)) {
                    return new c(this, cls);
                }
                return null;
        }
    }

    public final String toString() {
        switch (this.f2486d) {
            case 0:
                return "Factory[type=" + this.f2487e.getName() + ",adapter=" + this.f2488f + "]";
            default:
                return "Factory[typeHierarchy=" + this.f2487e.getName() + ",adapter=" + this.f2488f + "]";
        }
    }
}
