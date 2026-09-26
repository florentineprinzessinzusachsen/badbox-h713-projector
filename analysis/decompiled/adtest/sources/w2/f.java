package w2;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import r2.c0;
import r2.l1;
import r2.o0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f extends c0 implements a2.d, y1.c {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2621k = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "_reusableCancellableContinuation$volatile");
    private volatile /* synthetic */ Object _reusableCancellableContinuation$volatile;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final r2.s f2622g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a2.c f2623h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f2624i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f2625j;

    public f(r2.s sVar, a2.c cVar) {
        super(-1);
        this.f2622g = sVar;
        this.f2623h = cVar;
        this.f2624i = a.f2610b;
        this.f2625j = a.k(cVar.g());
    }

    @Override // a2.d
    public final a2.d e() {
        return this.f2623h;
    }

    @Override // y1.c
    public final y1.h g() {
        return this.f2623h.g();
    }

    @Override // r2.c0
    public final Object i() {
        Object obj = this.f2624i;
        this.f2624i = a.f2610b;
        return obj;
    }

    @Override // y1.c
    public final void j(Object obj) {
        Throwable thA = u1.h.a(obj);
        Object qVar = thA == null ? obj : new r2.q(thA, false);
        a2.c cVar = this.f2623h;
        y1.h hVarG = cVar.g();
        r2.s sVar = this.f2622g;
        if (sVar.U(hVarG)) {
            this.f2624i = qVar;
            this.f1962f = 0;
            sVar.S(cVar.g(), this);
            return;
        }
        o0 o0VarA = l1.a();
        if (o0VarA.f2007f >= 4294967296L) {
            this.f2624i = qVar;
            this.f1962f = 0;
            o0VarA.X(this);
            return;
        }
        o0VarA.Z(true);
        try {
            y1.h hVarG2 = cVar.g();
            Object objL = a.l(hVarG2, this.f2625j);
            try {
                cVar.j(obj);
                a.g(hVarG2, objL);
                while (o0VarA.b0()) {
                }
            } catch (Throwable th) {
                a.g(hVarG2, objL);
                throw th;
            }
        } catch (Throwable th2) {
            try {
                h(th2);
            } finally {
                o0VarA.W(true);
            }
        }
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.f2622g + ", " + r2.x.t(this.f2623h) + ']';
    }

    @Override // r2.c0
    public final y1.c c() {
        return this;
    }
}
