package r2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class y0 extends w2.j implements g0, s0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public d1 f2052g;

    @Override // r2.g0
    public final void a() {
        d1 d1VarJ = j();
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d1.f1971d;
            Object obj = atomicReferenceFieldUpdater.get(d1VarJ);
            if (obj instanceof y0) {
                if (obj != this) {
                    return;
                }
                i0 i0Var = x.f2048j;
                while (!atomicReferenceFieldUpdater.compareAndSet(d1VarJ, obj, i0Var)) {
                    if (atomicReferenceFieldUpdater.get(d1VarJ) != obj) {
                    }
                }
                return;
            }
            if (!(obj instanceof s0) || ((s0) obj).d() == null) {
                return;
            }
            while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = w2.j.f2633d;
                Object obj2 = atomicReferenceFieldUpdater2.get(this);
                if (obj2 instanceof w2.o) {
                    return;
                }
                if (obj2 == this) {
                    return;
                }
                j2.i.c(obj2, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
                w2.j jVar = (w2.j) obj2;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3 = w2.j.f2635f;
                w2.o oVar = (w2.o) atomicReferenceFieldUpdater3.get(jVar);
                if (oVar == null) {
                    oVar = new w2.o(jVar);
                    atomicReferenceFieldUpdater3.set(jVar, oVar);
                }
                do {
                    if (atomicReferenceFieldUpdater2.compareAndSet(this, obj2, oVar)) {
                        jVar.f();
                        return;
                    }
                } while (atomicReferenceFieldUpdater2.get(this) == obj2);
            }
        }
    }

    @Override // r2.s0
    public final boolean c() {
        return true;
    }

    @Override // r2.s0
    public final f1 d() {
        return null;
    }

    public v0 getParent() {
        return j();
    }

    public final d1 j() {
        d1 d1Var = this.f2052g;
        if (d1Var != null) {
            return d1Var;
        }
        j2.i.h("job");
        throw null;
    }

    public abstract boolean k();

    public abstract void l(Throwable th);

    @Override // w2.j
    public final String toString() {
        return getClass().getSimpleName() + '@' + x.k(this) + "[job@" + x.k(j()) + ']';
    }
}
