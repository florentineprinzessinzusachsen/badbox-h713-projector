package z2;

import i2.q;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.r1;
import u1.k;
import w2.r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements r2.g, r1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final r2.i f2783d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ c f2784e;

    public b(c cVar, r2.i iVar) {
        this.f2784e = cVar;
        this.f2783d = iVar;
    }

    @Override // r2.r1
    public final void a(r rVar, int i4) {
        this.f2783d.a(rVar, i4);
    }

    @Override // y1.c
    public final y1.h g() {
        return this.f2783d.f1988h;
    }

    @Override // y1.c
    public final void j(Object obj) {
        this.f2783d.j(obj);
    }

    @Override // r2.g
    public final void m(Object obj, q qVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = c.f2785k;
        c cVar = this.f2784e;
        atomicReferenceFieldUpdater.set(cVar, null);
        f1.c cVar2 = new f1.c(cVar, this);
        r2.i iVar = this.f2783d;
        iVar.D(k.f2301a, iVar.f1962f, new r2.h(0, cVar2));
    }

    @Override // r2.g
    public final a3.h n(Object obj, q qVar) {
        c cVar = this.f2784e;
        r2.h hVar = new r2.h(cVar, this);
        a3.h hVarN = this.f2783d.n((k) obj, hVar);
        if (hVarN != null) {
            c.f2785k.set(cVar, null);
        }
        return hVarN;
    }

    @Override // r2.g
    public final void o(Object obj) {
        this.f2783d.o(obj);
    }
}
