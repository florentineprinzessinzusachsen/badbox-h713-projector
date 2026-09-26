package v2;

import u2.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public t[] f2523d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2524e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2525f;

    public abstract t d();

    public abstract t[] e();

    public final void f(t tVar) {
        int i4;
        y1.c[] cVarArr;
        synchronized (this) {
            try {
                int i5 = this.f2524e - 1;
                this.f2524e = i5;
                if (i5 == 0) {
                    this.f2525f = 0;
                }
                j2.i.c(tVar, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
                tVar.getClass();
                tVar.f2365a.set(null);
                cVarArr = c.f2526a;
            } catch (Throwable th) {
                throw th;
            }
        }
        for (y1.c cVar : cVarArr) {
            if (cVar != null) {
                cVar.j(u1.k.f2301a);
            }
        }
    }
}
