package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p1 extends w2.q {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ThreadLocal f2016h;
    private volatile boolean threadLocalIsSet;

    /* JADX WARN: Illegal instructions before constructor call */
    public p1(y1.h hVar, a2.c cVar) {
        q1 q1Var = q1.f2020d;
        super(cVar, hVar.k(q1Var) == null ? hVar.l(q1Var) : hVar);
        this.f2016h = new ThreadLocal();
        if (cVar.g().k(y1.d.f2725d) instanceof s) {
            return;
        }
        Object objL = w2.a.l(hVar, null);
        w2.a.g(hVar, objL);
        d0(hVar, objL);
    }

    public final boolean c0() {
        boolean z3 = this.threadLocalIsSet && this.f2016h.get() == null;
        this.f2016h.remove();
        return !z3;
    }

    public final void d0(y1.h hVar, Object obj) {
        this.threadLocalIsSet = true;
        this.f2016h.set(new u1.f(hVar, obj));
    }

    @Override // w2.q, r2.d1
    public final void q(Object obj) {
        if (this.threadLocalIsSet) {
            u1.f fVar = (u1.f) this.f2016h.get();
            if (fVar != null) {
                w2.a.g((y1.h) fVar.f2294d, fVar.f2295e);
            }
            this.f2016h.remove();
        }
        Object objQ = x.q(obj);
        y1.c cVar = this.f2647g;
        y1.h hVarG = cVar.g();
        Object objL = w2.a.l(hVarG, null);
        p1 p1VarV = objL != w2.a.f2612d ? x.v(cVar, hVarG, objL) : null;
        try {
            this.f2647g.j(objQ);
        } finally {
            if (p1VarV == null || p1VarV.c0()) {
                w2.a.g(hVarG, objL);
            }
        }
    }
}
