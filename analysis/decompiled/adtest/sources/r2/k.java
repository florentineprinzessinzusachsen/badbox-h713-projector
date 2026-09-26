package r2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k extends y0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1993h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i f1994i;

    public /* synthetic */ k(i iVar, int i4) {
        this.f1993h = i4;
        this.f1994i = iVar;
    }

    @Override // r2.y0
    public final boolean k() {
        switch (this.f1993h) {
            case 0:
                return true;
            default:
                return false;
        }
    }

    @Override // r2.y0
    public final void l(Throwable th) {
        switch (this.f1993h) {
            case 0:
                d1 d1VarJ = j();
                i iVar = this.f1994i;
                Throwable thT = iVar.t(d1VarJ);
                if (iVar.z()) {
                    w2.f fVar = (w2.f) iVar.f1987g;
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = w2.f.f2621k;
                    while (true) {
                        Object obj = atomicReferenceFieldUpdater.get(fVar);
                        a3.h hVar = w2.a.f2611c;
                        if (j2.i.a(obj, hVar)) {
                            while (!atomicReferenceFieldUpdater.compareAndSet(fVar, hVar, thT)) {
                                if (atomicReferenceFieldUpdater.get(fVar) != hVar) {
                                }
                            }
                            break;
                        } else if (obj instanceof Throwable) {
                            break;
                        } else {
                            while (true) {
                                if (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, null)) {
                                    if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                                    }
                                }
                            }
                        }
                    }
                }
                iVar.q(thT);
                if (!iVar.z()) {
                    iVar.r();
                }
                break;
            default:
                this.f1994i.j(u1.k.f2301a);
                break;
        }
    }
}
