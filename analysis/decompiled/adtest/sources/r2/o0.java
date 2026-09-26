package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class o0 extends s {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final /* synthetic */ int f2006i = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f2007f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f2008g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public v1.h f2009h;

    public final void W(boolean z3) {
        long j4 = this.f2007f - (z3 ? 4294967296L : 1L);
        this.f2007f = j4;
        if (j4 <= 0 && this.f2008g) {
            shutdown();
        }
    }

    public final void X(c0 c0Var) {
        v1.h hVar = this.f2009h;
        if (hVar == null) {
            hVar = new v1.h();
            this.f2009h = hVar;
        }
        hVar.addLast(c0Var);
    }

    public abstract Thread Y();

    public final void Z(boolean z3) {
        this.f2007f = (z3 ? 4294967296L : 1L) + this.f2007f;
        if (z3) {
            return;
        }
        this.f2008g = true;
    }

    public abstract long a0();

    public final boolean b0() {
        v1.h hVar = this.f2009h;
        if (hVar == null) {
            return false;
        }
        c0 c0Var = (c0) (hVar.isEmpty() ? null : hVar.removeFirst());
        if (c0Var == null) {
            return false;
        }
        c0Var.run();
        return true;
    }

    public void c0(long j4, l0 l0Var) {
        y.f2050m.h0(j4, l0Var);
    }

    public abstract void shutdown();
}
