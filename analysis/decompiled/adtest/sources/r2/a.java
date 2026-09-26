package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends d1 implements y1.c, v {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y1.h f1948f;

    public a(y1.h hVar, boolean z3) {
        super(z3);
        H((v0) hVar.k(t.f2027e));
        this.f1948f = hVar.l(this);
    }

    @Override // r2.d1
    public final void G(a0.c cVar) {
        x.m(cVar, this.f1948f);
    }

    @Override // r2.d1
    public final void R(Object obj) {
        if (!(obj instanceof q)) {
            a0(obj);
        } else {
            q qVar = (q) obj;
            Z(qVar.f2018a, q.f2017b.get(qVar) != 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void b0(w wVar, a aVar, i2.p pVar) {
        int iOrdinal = wVar.ordinal();
        u1.k kVar = u1.k.f2301a;
        if (iOrdinal == 0) {
            try {
                w2.a.h(kVar, z1.d.a(((a2.a) pVar).i(aVar, this)));
                return;
            } catch (Throwable th) {
                j(d0.l0.l(th));
                throw th;
            }
        }
        if (iOrdinal != 1) {
            if (iOrdinal == 2) {
                z1.d.a(((a2.a) pVar).i(aVar, this)).j(kVar);
                return;
            }
            if (iOrdinal != 3) {
                throw new a0.c();
            }
            try {
                y1.h hVar = this.f1948f;
                Object objL = w2.a.l(hVar, null);
                try {
                    j2.q.a(2, pVar);
                    Object objF = pVar.f(aVar, this);
                    w2.a.g(hVar, objL);
                    if (objF != z1.a.f2781d) {
                        j(objF);
                    }
                } catch (Throwable th2) {
                    w2.a.g(hVar, objL);
                    throw th2;
                }
            } catch (Throwable th3) {
                j(d0.l0.l(th3));
            }
        }
    }

    @Override // y1.c
    public final y1.h g() {
        return this.f1948f;
    }

    @Override // r2.v
    public final y1.h i() {
        return this.f1948f;
    }

    @Override // y1.c
    public final void j(Object obj) {
        Throwable thA = u1.h.a(obj);
        if (thA != null) {
            obj = new q(thA, false);
        }
        Object objN = N(obj);
        if (objN == x.f2043e) {
            return;
        }
        q(objN);
    }

    @Override // r2.d1
    public final String u() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    public void a0(Object obj) {
    }

    public void Z(Throwable th, boolean z3) {
    }
}
