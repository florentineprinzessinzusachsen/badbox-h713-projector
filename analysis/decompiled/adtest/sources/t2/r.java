package t2;

import h1.a0;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.w0;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r extends r2.a implements s, i {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final e f2229g;

    public r(y1.h hVar, e eVar) {
        super(hVar, true);
        this.f2229g = eVar;
    }

    @Override // r2.a
    public final void Z(Throwable th, boolean z3) {
        if (this.f2229g.k(th, false) || z3) {
            return;
        }
        x.m(th, this.f1948f);
    }

    @Override // t2.u
    public final Object a(v2.i iVar) {
        e eVar = this.f2229g;
        eVar.getClass();
        return e.z(eVar, iVar);
    }

    @Override // r2.a
    public final void a0(Object obj) {
        this.f2229g.j(null);
    }

    @Override // r2.d1, r2.v0
    public final void b(CancellationException cancellationException) {
        if (J()) {
            return;
        }
        if (cancellationException == null) {
            cancellationException = new w0(u(), null, this);
        }
        s(cancellationException);
    }

    public final void c0(a0 a0Var) {
        e eVar = this.f2229g;
        eVar.getClass();
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e.f2195m;
        while (!atomicReferenceFieldUpdater.compareAndSet(eVar, null, a0Var)) {
            if (atomicReferenceFieldUpdater.get(eVar) != null) {
                while (true) {
                    Object obj = atomicReferenceFieldUpdater.get(eVar);
                    a3.h hVar = g.f2214q;
                    if (obj != hVar) {
                        if (obj == g.f2215r) {
                            throw new IllegalStateException("Another handler was already registered and successfully invoked");
                        }
                        throw new IllegalStateException(("Another handler is already registered: " + obj).toString());
                    }
                    a3.h hVar2 = g.f2215r;
                    do {
                        if (atomicReferenceFieldUpdater.compareAndSet(eVar, hVar, hVar2)) {
                            a0Var.h(eVar.p());
                            return;
                        }
                    } while (atomicReferenceFieldUpdater.get(eVar) == hVar);
                }
            }
        }
    }

    @Override // t2.v
    public final Object d(Object obj, y1.c cVar) {
        return this.f2229g.d(obj, cVar);
    }

    @Override // t2.u
    public final Object f() {
        return this.f2229g.f();
    }

    @Override // t2.v
    public final Object h(Object obj) {
        return this.f2229g.h(obj);
    }

    @Override // t2.u
    public final b iterator() {
        e eVar = this.f2229g;
        eVar.getClass();
        return new b(eVar);
    }

    @Override // r2.d1
    public final void s(CancellationException cancellationException) {
        this.f2229g.k(cancellationException, true);
        r(cancellationException);
    }
}
