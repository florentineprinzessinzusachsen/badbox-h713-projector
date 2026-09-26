package a2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y1.h f42e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public transient y1.c f43f;

    public c(y1.c cVar, y1.h hVar) {
        super(cVar);
        this.f42e = hVar;
    }

    @Override // y1.c
    public y1.h g() {
        y1.h hVar = this.f42e;
        j2.i.b(hVar);
        return hVar;
    }

    @Override // a2.a
    public void p() {
        y1.c cVar = this.f43f;
        if (cVar != null && cVar != this) {
            y1.f fVarK = g().k(y1.d.f2725d);
            j2.i.b(fVarK);
            w2.f fVar = (w2.f) cVar;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = w2.f.f2621k;
            while (atomicReferenceFieldUpdater.get(fVar) == w2.a.f2611c) {
            }
            Object obj = atomicReferenceFieldUpdater.get(fVar);
            r2.i iVar = obj instanceof r2.i ? (r2.i) obj : null;
            if (iVar != null) {
                iVar.r();
            }
        }
        this.f43f = b.f41d;
    }

    public c(y1.c cVar) {
        this(cVar, cVar != null ? cVar.g() : null);
    }
}
