package r2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class x0 extends d1 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f2049f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x0(v0 v0Var) {
        super(true);
        boolean z3 = true;
        H(v0Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d1.f1972e;
        l lVar = (l) atomicReferenceFieldUpdater.get(this);
        m mVar = lVar instanceof m ? (m) lVar : null;
        if (mVar == null) {
            z3 = false;
            break;
        }
        d1 d1VarJ = mVar.j();
        while (!d1VarJ.B()) {
            l lVar2 = (l) atomicReferenceFieldUpdater.get(d1VarJ);
            m mVar2 = lVar2 instanceof m ? (m) lVar2 : null;
            if (mVar2 == null) {
                z3 = false;
                break;
            }
            d1VarJ = mVar2.j();
        }
        this.f2049f = z3;
    }

    @Override // r2.d1
    public final boolean B() {
        return this.f2049f;
    }

    @Override // r2.d1
    public final boolean D() {
        return true;
    }
}
