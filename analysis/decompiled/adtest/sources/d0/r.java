package d0;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class r implements g.j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Executor f499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ i2.a f500c;

    public /* synthetic */ r(Executor executor, i2.a aVar, int i4) {
        this.f498a = i4;
        this.f499b = executor;
        this.f500c = aVar;
    }

    @Override // g.j
    public final Object a(g.i iVar) {
        switch (this.f498a) {
            case 0:
                m0.p pVar = (m0.p) this.f500c;
                AtomicBoolean atomicBoolean = new AtomicBoolean(false);
                s sVar = new s(0, atomicBoolean);
                g.o oVar = iVar.f938c;
                if (oVar != null) {
                    oVar.a(sVar, m.f478d);
                }
                this.f499b.execute(new t(atomicBoolean, iVar, pVar, 0));
                return "setForegroundAsync";
            default:
                AtomicBoolean atomicBoolean2 = new AtomicBoolean(false);
                s sVar2 = new s(1, atomicBoolean2);
                g.o oVar2 = iVar.f938c;
                if (oVar2 != null) {
                    oVar2.a(sVar2, m.f478d);
                }
                this.f499b.execute(new t(atomicBoolean2, iVar, this.f500c, 1));
                return u1.k.f2301a;
        }
    }
}
