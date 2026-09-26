package v0;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends s0.b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2449a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f2450b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f2451c;

    public c(n nVar, u0.q qVar) {
        this.f2450b = nVar;
        this.f2451c = qVar;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        switch (this.f2449a) {
            case 0:
                if (bVar.f0() == 9) {
                    bVar.b0();
                    return null;
                }
                Collection collection = (Collection) ((u0.q) this.f2451c).a();
                bVar.b();
                while (bVar.S()) {
                    collection.add(((n) this.f2450b).f2469c.b(bVar));
                }
                bVar.A();
                return collection;
            default:
                Class cls = (Class) this.f2450b;
                Object objB = ((r0) this.f2451c).f2488f.b(bVar);
                if (objB == null || cls.isInstance(objB)) {
                    return objB;
                }
                throw new s0.r("Expected a " + cls.getName() + " but was " + objB.getClass().getName() + "; at path " + bVar.K(true));
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        switch (this.f2449a) {
            case 0:
                Collection collection = (Collection) obj;
                if (collection != null) {
                    dVar.c();
                    Iterator it = collection.iterator();
                    while (it.hasNext()) {
                        ((n) this.f2450b).c(dVar, it.next());
                    }
                    dVar.A();
                } else {
                    dVar.S();
                }
                break;
            default:
                ((r0) this.f2451c).f2488f.c(dVar, obj);
                break;
        }
    }

    public c(r0 r0Var, Class cls) {
        this.f2451c = r0Var;
        this.f2450b = cls;
    }
}
