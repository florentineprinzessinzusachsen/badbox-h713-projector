package r2;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class c0 extends y2.i {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1962f;

    public c0(int i4) {
        super(0L, false);
        this.f1962f = i4;
    }

    public abstract y1.c c();

    public Throwable d(Object obj) {
        q qVar = obj instanceof q ? (q) obj : null;
        if (qVar != null) {
            return qVar.f2018a;
        }
        return null;
    }

    public final void h(Throwable th) {
        x.m(new h2.a("Fatal exception in coroutines machinery for " + this + ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers", th), c().g());
    }

    public abstract Object i();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            y1.c cVarC = c();
            j2.i.c(cVarC, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTask>");
            w2.f fVar = (w2.f) cVarC;
            a2.c cVar = fVar.f2623h;
            Object obj = fVar.f2625j;
            y1.h hVarG = cVar.g();
            Object objL = w2.a.l(hVarG, obj);
            v0 v0Var = null;
            p1 p1VarV = objL != w2.a.f2612d ? x.v(cVar, hVarG, objL) : null;
            try {
                y1.h hVarG2 = cVar.g();
                Object objI = i();
                Throwable thD = d(objI);
                if (thD == null) {
                    int i4 = this.f1962f;
                    boolean z3 = true;
                    if (i4 != 1 && i4 != 2) {
                        z3 = false;
                    }
                    if (z3) {
                        v0Var = (v0) hVarG2.k(t.f2027e);
                    }
                }
                if (v0Var != null && !v0Var.c()) {
                    CancellationException cancellationExceptionZ = ((d1) v0Var).z();
                    b(cancellationExceptionZ);
                    cVar.j(d0.l0.l(cancellationExceptionZ));
                } else if (thD != null) {
                    cVar.j(d0.l0.l(thD));
                } else {
                    cVar.j(f(objI));
                }
            } finally {
                if (p1VarV == null || p1VarV.c0()) {
                    w2.a.g(hVarG, objL);
                }
            }
        } catch (Throwable th) {
            h(th);
        }
    }

    public void b(CancellationException cancellationException) {
    }

    public Object f(Object obj) {
        return obj;
    }
}
