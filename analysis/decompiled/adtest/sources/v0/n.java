package v0;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n extends s0.b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2467a = 1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2468b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final s0.b0 f2469c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f2470d;

    public n(s0.n nVar, s0.b0 b0Var, Type type) {
        this.f2468b = nVar;
        this.f2469c = b0Var;
        this.f2470d = type;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        switch (this.f2467a) {
            case 0:
                int iF0 = bVar.f0();
                if (iF0 == 9) {
                    bVar.b0();
                    return null;
                }
                Map map = (Map) ((u0.q) this.f2470d).a();
                if (iF0 == 1) {
                    bVar.b();
                    while (bVar.S()) {
                        bVar.b();
                        Object objB = ((n) this.f2468b).f2469c.b(bVar);
                        if (map.put(objB, ((n) this.f2469c).f2469c.b(bVar)) != null) {
                            throw new s0.r("duplicate key: " + objB);
                        }
                        bVar.A();
                    }
                    bVar.A();
                } else {
                    bVar.c();
                    while (bVar.S()) {
                        a1.a.f9e.getClass();
                        int iL = bVar.f17j;
                        if (iL == 0) {
                            iL = bVar.l();
                        }
                        if (iL == 13) {
                            bVar.f17j = 9;
                        } else if (iL == 12) {
                            bVar.f17j = 8;
                        } else {
                            if (iL != 14) {
                                throw bVar.o0("a name");
                            }
                            bVar.f17j = 10;
                        }
                        Object objB2 = ((n) this.f2468b).f2469c.b(bVar);
                        if (map.put(objB2, ((n) this.f2469c).f2469c.b(bVar)) != null) {
                            throw new s0.r("duplicate key: " + objB2);
                        }
                    }
                    bVar.C();
                }
                return map;
            default:
                return this.f2469c.b(bVar);
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0045  */
    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        s0.b0 b0VarD;
        switch (this.f2467a) {
            case 0:
                Map map = (Map) obj;
                n nVar = (n) this.f2469c;
                if (map == null) {
                    dVar.S();
                } else {
                    dVar.k();
                    for (Map.Entry entry : map.entrySet()) {
                        dVar.J(String.valueOf(entry.getKey()));
                        nVar.c(dVar, entry.getValue());
                    }
                    dVar.C();
                }
                break;
            default:
                Type type = (Type) this.f2470d;
                Type type2 = (obj == null || !((type instanceof Class) || (type instanceof TypeVariable))) ? type : obj.getClass();
                s0.b0 b0Var = this.f2469c;
                if (type2 != type) {
                    s0.b0 b0VarC = ((s0.n) this.f2468b).c(new z0.a(type2));
                    if (b0VarC instanceof s) {
                        s0.b0 b0Var2 = b0Var;
                        while ((b0Var2 instanceof x) && (b0VarD = ((x) b0Var2).d()) != b0Var2) {
                            b0Var2 = b0VarD;
                        }
                        if (b0Var2 instanceof s) {
                            b0Var = b0VarC;
                        }
                    } else {
                        b0Var = b0VarC;
                    }
                }
                b0Var.c(dVar, obj);
                break;
        }
    }

    public n(d dVar, n nVar, n nVar2, u0.q qVar) {
        this.f2468b = nVar;
        this.f2469c = nVar2;
        this.f2470d = qVar;
    }
}
