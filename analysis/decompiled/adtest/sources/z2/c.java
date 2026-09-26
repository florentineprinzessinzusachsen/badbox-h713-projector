package z2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.x;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends g implements a {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2785k = AtomicReferenceFieldUpdater.newUpdater(c.class, Object.class, "owner$volatile");
    private volatile /* synthetic */ Object owner$volatile;

    public c() {
        super(1);
        this.owner$volatile = d.f2786a;
    }

    @Override // z2.a
    public final void b(Object obj) {
        while (Math.max(g.f2793j.get(this), 0) == 0) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2785k;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            a3.h hVar = d.f2786a;
            if (obj2 != hVar) {
                if (obj2 != obj && obj != null) {
                    throw new IllegalStateException(("This mutex is locked by " + obj2 + ", but " + obj + " is expected").toString());
                }
                do {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, hVar)) {
                        d();
                        return;
                    }
                } while (atomicReferenceFieldUpdater.get(this) == obj2);
            }
        }
        throw new IllegalStateException("This mutex is not locked");
    }

    @Override // z2.a
    public final Object c(a2.c cVar) {
        int i4;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = g.f2793j;
            int i5 = atomicIntegerFieldUpdater.get(this);
            int i6 = this.f2794d;
            if (i5 > i6) {
                do {
                    i4 = atomicIntegerFieldUpdater.get(this);
                    if (i4 <= i6) {
                        break;
                    }
                } while (!atomicIntegerFieldUpdater.compareAndSet(this, i4, i6));
            } else {
                k kVar = k.f2301a;
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2785k;
                if (i5 <= 0) {
                    r2.i iVarL = x.l(z1.d.a(cVar));
                    try {
                        b bVar = new b(this, iVarL);
                        while (true) {
                            int andDecrement = atomicIntegerFieldUpdater.getAndDecrement(this);
                            if (andDecrement <= i6) {
                                if (andDecrement > 0) {
                                    c cVar2 = bVar.f2784e;
                                    atomicReferenceFieldUpdater.set(cVar2, null);
                                    r2.i iVar = bVar.f2783d;
                                    iVar.D(kVar, iVar.f1962f, new r2.h(0, new f1.c(cVar2, bVar)));
                                    break;
                                }
                                if (a(bVar)) {
                                    break;
                                }
                            }
                        }
                        Object objU = iVarL.u();
                        z1.a aVar = z1.a.f2781d;
                        if (objU != aVar) {
                            objU = kVar;
                        }
                        return objU == aVar ? objU : kVar;
                    } catch (Throwable th) {
                        iVarL.C();
                        throw th;
                    }
                }
                if (atomicIntegerFieldUpdater.compareAndSet(this, i5, i5 - 1)) {
                    atomicReferenceFieldUpdater.set(this, null);
                    return kVar;
                }
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Mutex@");
        sb.append(x.k(this));
        sb.append("[isLocked=");
        sb.append(Math.max(g.f2793j.get(this), 0) == 0);
        sb.append(",owner=");
        sb.append(f2785k.get(this));
        sb.append(']');
        return sb.toString();
    }
}
