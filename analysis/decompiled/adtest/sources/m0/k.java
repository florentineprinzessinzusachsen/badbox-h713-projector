package m0;

import d0.a0;
import e0.k0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e0.f f1415d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e0.l f1416e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1417f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f1418g;

    public k(e0.f fVar, e0.l lVar, boolean z3, int i4) {
        j2.i.e(fVar, "processor");
        j2.i.e(lVar, "token");
        this.f1415d = fVar;
        this.f1416e = lVar;
        this.f1417f = z3;
        this.f1418g = i4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zH;
        k0 k0VarB;
        if (this.f1417f) {
            e0.f fVar = this.f1415d;
            e0.l lVar = this.f1416e;
            int i4 = this.f1418g;
            fVar.getClass();
            String str = lVar.f656a.f1321a;
            synchronized (fVar.f622k) {
                k0VarB = fVar.b(str);
            }
            zH = e0.f.e(str, k0VarB, i4);
        } else {
            zH = this.f1415d.h(this.f1416e, this.f1418g);
        }
        a0.e().a(a0.g("StopWorkRunnable"), "StopWorkRunnable for " + this.f1416e.f656a.f1321a + "; Processor.stopWork = " + zH);
    }
}
