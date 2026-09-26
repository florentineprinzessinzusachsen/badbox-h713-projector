package w2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2633d = AtomicReferenceFieldUpdater.newUpdater(j.class, Object.class, "_next$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2634e = AtomicReferenceFieldUpdater.newUpdater(j.class, Object.class, "_prev$volatile");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2635f = AtomicReferenceFieldUpdater.newUpdater(j.class, Object.class, "_removedRef$volatile");
    private volatile /* synthetic */ Object _next$volatile = this;
    private volatile /* synthetic */ Object _prev$volatile = this;
    private volatile /* synthetic */ Object _removedRef$volatile;

    public final boolean e(j jVar, int i4) {
        while (true) {
            j jVarF = f();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2634e;
            if (jVarF == null) {
                Object obj = atomicReferenceFieldUpdater.get(this);
                while (true) {
                    jVarF = (j) obj;
                    if (!jVarF.i()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(jVarF);
                }
            }
            if (jVarF instanceof h) {
                return (((h) jVarF).f2632g & i4) == 0 && jVarF.e(jVar, i4);
            }
            atomicReferenceFieldUpdater.set(jVar, jVarF);
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f2633d;
            atomicReferenceFieldUpdater2.set(jVar, this);
            do {
                if (atomicReferenceFieldUpdater2.compareAndSet(jVarF, this, jVar)) {
                    jVar.g(this);
                    return true;
                }
            } while (atomicReferenceFieldUpdater2.get(jVarF) == this);
        }
    }

    public final j f() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        Object obj;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = f2634e;
            j jVar = (j) atomicReferenceFieldUpdater2.get(this);
            j jVar2 = jVar;
            while (true) {
                j jVar3 = null;
                while (true) {
                    atomicReferenceFieldUpdater = f2633d;
                    obj = atomicReferenceFieldUpdater.get(jVar2);
                    if (obj == this) {
                        if (jVar == jVar2) {
                            return jVar2;
                        }
                        while (!atomicReferenceFieldUpdater2.compareAndSet(this, jVar, jVar2)) {
                            if (atomicReferenceFieldUpdater2.get(this) != jVar) {
                                break;
                            }
                        }
                        return jVar2;
                    }
                    if (i()) {
                        return null;
                    }
                    if (!(obj instanceof o)) {
                        j2.i.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
                        jVar3 = jVar2;
                        jVar2 = (j) obj;
                    } else {
                        if (jVar3 != null) {
                            break;
                        }
                        jVar2 = (j) atomicReferenceFieldUpdater2.get(jVar2);
                    }
                }
                j jVar4 = ((o) obj).f2646a;
                while (!atomicReferenceFieldUpdater.compareAndSet(jVar3, jVar2, jVar4)) {
                    if (atomicReferenceFieldUpdater.get(jVar3) != jVar2) {
                        break;
                    }
                }
                jVar2 = jVar3;
            }
        }
    }

    public final void g(j jVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2634e;
            j jVar2 = (j) atomicReferenceFieldUpdater.get(jVar);
            if (f2633d.get(this) != jVar) {
                return;
            }
            do {
                if (atomicReferenceFieldUpdater.compareAndSet(jVar, jVar2, this)) {
                    if (i()) {
                        jVar.f();
                        return;
                    }
                    return;
                }
            } while (atomicReferenceFieldUpdater.get(jVar) == jVar2);
        }
    }

    public final j h() {
        j jVar;
        Object obj = f2633d.get(this);
        o oVar = obj instanceof o ? (o) obj : null;
        if (oVar != null && (jVar = oVar.f2646a) != null) {
            return jVar;
        }
        j2.i.c(obj, "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode");
        return (j) obj;
    }

    public boolean i() {
        return f2633d.get(this) instanceof o;
    }

    public String toString() {
        return new i(this, r2.x.class, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;", 1) + '@' + r2.x.k(this);
    }
}
