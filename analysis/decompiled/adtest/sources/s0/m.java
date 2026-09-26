package s0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class m extends v0.x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b0 f2095a = null;

    @Override // s0.b0
    public final Object b(a1.b bVar) {
        b0 b0Var = this.f2095a;
        if (b0Var != null) {
            return b0Var.b(bVar);
        }
        throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) {
        b0 b0Var = this.f2095a;
        if (b0Var == null) {
            throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
        }
        b0Var.c(dVar, obj);
    }

    @Override // v0.x
    public final b0 d() {
        b0 b0Var = this.f2095a;
        if (b0Var != null) {
            return b0Var;
        }
        throw new IllegalStateException("Adapter for type with cyclic dependency has been used before dependency has been resolved");
    }
}
