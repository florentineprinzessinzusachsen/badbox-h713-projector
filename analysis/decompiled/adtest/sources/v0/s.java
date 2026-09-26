package v0;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class s extends s0.b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f2489a;

    public s(u uVar) {
        this.f2489a = uVar;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        Object objD = d();
        Map map = this.f2489a.f2495a;
        try {
            bVar.c();
            while (bVar.S()) {
                r rVar = (r) map.get(bVar.Z());
                if (rVar == null) {
                    bVar.m0();
                } else {
                    f(objD, bVar, rVar);
                }
            }
            bVar.C();
            return e(objD);
        } catch (IllegalAccessException e4) {
            d0.l0 l0Var = x0.c.f2671a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.13.2). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e4);
        } catch (IllegalStateException e5) {
            throw new s0.r(e5);
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        if (obj == null) {
            dVar.S();
            return;
        }
        dVar.k();
        try {
            Iterator it = this.f2489a.f2496b.iterator();
            while (it.hasNext()) {
                ((r) it.next()).a(dVar, obj);
            }
            dVar.C();
        } catch (IllegalAccessException e4) {
            d0.l0 l0Var = x0.c.f2671a;
            throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.13.2). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e4);
        }
    }

    public abstract Object d();

    public abstract Object e(Object obj);

    public abstract void f(Object obj, a1.b bVar, r rVar);
}
