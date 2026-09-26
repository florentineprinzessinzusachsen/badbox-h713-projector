package v0;

import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements s0.c0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final k f2463f;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final u0.c f2464d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ConcurrentHashMap f2465e = new ConcurrentHashMap();

    static {
        int i4 = 0;
        f2463f = new k(i4);
        new k(i4);
    }

    public l(u0.c cVar) {
        this.f2464d = cVar;
    }

    @Override // s0.c0
    public final s0.b0 a(s0.n nVar, z0.a aVar) {
        t0.a aVar2 = (t0.a) aVar.f2778a.getAnnotation(t0.a.class);
        if (aVar2 == null) {
            return null;
        }
        return b(this.f2464d, nVar, aVar, aVar2, true);
    }

    public final s0.b0 b(u0.c cVar, s0.n nVar, z0.a aVar, t0.a aVar2, boolean z3) {
        s0.b0 b0VarA;
        Object objA = cVar.b(new z0.a(aVar2.value()), true).a();
        boolean zNullSafe = aVar2.nullSafe();
        if (objA instanceof s0.b0) {
            b0VarA = (s0.b0) objA;
        } else {
            if (!(objA instanceof s0.c0)) {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + objA.getClass().getName() + " as a @JsonAdapter for " + u0.i.l(aVar.f2779b) + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
            s0.c0 c0Var = (s0.c0) objA;
            if (z3) {
                s0.c0 c0Var2 = (s0.c0) this.f2465e.putIfAbsent(aVar.f2778a, c0Var);
                if (c0Var2 != null) {
                    c0Var = c0Var2;
                }
            }
            b0VarA = c0Var.a(nVar, aVar);
        }
        return (b0VarA == null || !zNullSafe) ? b0VarA : b0VarA.a();
    }
}
