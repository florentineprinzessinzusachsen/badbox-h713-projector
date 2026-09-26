package s2;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.CancellationException;
import r2.a0;
import r2.e0;
import r2.g0;
import r2.g1;
import r2.i;
import r2.n1;
import r2.s;
import r2.x;
import w2.n;
import y1.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends s implements a0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Handler f2146f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f2147g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final d f2148h;

    public d(Handler handler, boolean z3) {
        this.f2146f = handler;
        this.f2147g = z3;
        this.f2148h = z3 ? this : new d(handler, true);
    }

    @Override // r2.a0
    public final g0 A(long j4, final n1 n1Var, h hVar) {
        if (j4 > 4611686018427387903L) {
            j4 = 4611686018427387903L;
        }
        if (this.f2146f.postDelayed(n1Var, j4)) {
            return new g0() { // from class: s2.c
                @Override // r2.g0
                public final void a() {
                    this.f2144d.f2146f.removeCallbacks(n1Var);
                }
            };
        }
        W(hVar, n1Var);
        return g1.f1979d;
    }

    @Override // r2.a0
    public final void J(long j4, i iVar) {
        f0.a aVar = new f0.a(iVar, this, 4, false);
        if (j4 > 4611686018427387903L) {
            j4 = 4611686018427387903L;
        }
        if (this.f2146f.postDelayed(aVar, j4)) {
            iVar.x(new h0.e(9, this, aVar));
        } else {
            W(iVar.f1988h, aVar);
        }
    }

    @Override // r2.s
    public final void S(h hVar, Runnable runnable) {
        if (this.f2146f.post(runnable)) {
            return;
        }
        W(hVar, runnable);
    }

    @Override // r2.s
    public final boolean U(h hVar) {
        return (this.f2147g && j2.i.a(Looper.myLooper(), this.f2146f.getLooper())) ? false : true;
    }

    public final void W(h hVar, Runnable runnable) {
        x.d(hVar, new CancellationException("The task was rejected, the handler underlying the dispatcher '" + this + "' was closed"));
        y2.e eVar = e0.f1974a;
        y2.d.f2753f.S(hVar, runnable);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return dVar.f2146f == this.f2146f && dVar.f2147g == this.f2147g;
    }

    public final int hashCode() {
        return System.identityHashCode(this.f2146f) ^ (this.f2147g ? 1231 : 1237);
    }

    @Override // r2.s
    public final String toString() {
        d dVar;
        String str;
        y2.e eVar = e0.f1974a;
        d dVar2 = n.f2645a;
        if (this == dVar2) {
            str = "Dispatchers.Main";
        } else {
            try {
                dVar = dVar2.f2148h;
            } catch (UnsupportedOperationException unused) {
                dVar = null;
            }
            str = this == dVar ? "Dispatchers.Main.immediate" : null;
        }
        if (str != null) {
            return str;
        }
        String string = this.f2146f.toString();
        if (!this.f2147g) {
            return string;
        }
        return string + ".immediate";
    }
}
