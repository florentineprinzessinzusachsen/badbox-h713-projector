package g;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends l3.h {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f921h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f922i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f923j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f924k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final AtomicReferenceFieldUpdater f925l;

    public e(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater3, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater4, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater5) {
        this.f921h = atomicReferenceFieldUpdater;
        this.f922i = atomicReferenceFieldUpdater2;
        this.f923j = atomicReferenceFieldUpdater3;
        this.f924k = atomicReferenceFieldUpdater4;
        this.f925l = atomicReferenceFieldUpdater5;
    }

    @Override // l3.h
    public final void f0(g gVar, g gVar2) {
        this.f922i.lazySet(gVar, gVar2);
    }

    @Override // l3.h
    public final boolean g(h hVar, d dVar, d dVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f924k;
            if (atomicReferenceFieldUpdater.compareAndSet(hVar, dVar, dVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(hVar) == dVar);
        return false;
    }

    @Override // l3.h
    public final void g0(g gVar, Thread thread) {
        this.f921h.lazySet(gVar, thread);
    }

    @Override // l3.h
    public final boolean h(h hVar, Object obj, Object obj2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f925l;
            if (atomicReferenceFieldUpdater.compareAndSet(hVar, obj, obj2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(hVar) == obj);
        return false;
    }

    @Override // l3.h
    public final boolean i(h hVar, g gVar, g gVar2) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        do {
            atomicReferenceFieldUpdater = this.f923j;
            if (atomicReferenceFieldUpdater.compareAndSet(hVar, gVar, gVar2)) {
                return true;
            }
        } while (atomicReferenceFieldUpdater.get(hVar) == gVar);
        return false;
    }
}
