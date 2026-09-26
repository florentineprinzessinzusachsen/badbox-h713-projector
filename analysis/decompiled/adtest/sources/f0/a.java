package f0;

import a3.d0;
import com.speed.ad.bean.PluginInfoEntity;
import d0.a0;
import d0.l0;
import h0.q;
import h1.c0;
import i2.l;
import j2.i;
import java.io.IOException;
import java.util.Map;
import l0.p;
import m0.j;
import r2.q0;
import r2.s;
import r2.x;
import t1.o;
import w2.g;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f836d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f837e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f838f;

    public /* synthetic */ a(int i4, Object obj, Object obj2) {
        this.f836d = i4;
        this.f838f = obj;
        this.f837e = obj2;
    }

    private final void a() {
        try {
            ((Runnable) this.f838f).run();
            synchronized (((j) this.f837e).f1414h) {
                ((j) this.f837e).b();
            }
        } catch (Throwable th) {
            synchronized (((j) this.f837e).f1414h) {
                ((j) this.f837e).b();
                throw th;
            }
        }
    }

    private final void b() {
        synchronized (o.f2158a) {
            c0.f1036a.getClass();
            Map mapE = c0.e();
            mapE.clear();
            c0.o(mapE);
        }
        ((l) this.f838f).h(Boolean.TRUE);
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = 0;
        switch (this.f836d) {
            case 0:
                a0 a0VarE = a0.e();
                String str = b.f839e;
                StringBuilder sb = new StringBuilder("Scheduling work ");
                p pVar = (p) this.f837e;
                sb.append(pVar.f1331a);
                a0VarE.a(str, sb.toString());
                ((b) this.f838f).f840a.c(pVar);
                return;
            case 1:
                p pVarC = ((k0.b) this.f838f).f1281a.f697f.c((String) this.f837e);
                if (pVarC == null || i.a(d0.e.f432j, pVarC.f1340j)) {
                    return;
                }
                synchronized (((k0.b) this.f838f).f1283c) {
                    ((k0.b) this.f838f).f1286f.put(l0.s(pVarC), pVarC);
                    k0.b bVar = (k0.b) this.f838f;
                    ((k0.b) this.f838f).f1287g.put(l0.s(pVarC), q.a(bVar.f1288h, pVarC, (s) bVar.f1282b.f185f, bVar));
                    break;
                }
                return;
            case 2:
                a();
                return;
            case 3:
                ((r2.i) this.f838f).E((q0) this.f837e);
                return;
            case 4:
                ((r2.i) this.f837e).E((s2.d) this.f838f);
                return;
            case 5:
                a1.c.f("Failed to upload logs: ", ((IOException) this.f837e).getMessage());
                ((l) this.f838f).h(Boolean.FALSE);
                return;
            case 6:
                b();
                return;
            case 7:
                a1.c.f("Failed to upload logs: ", new IOException("Unexpected code " + ((d0) this.f837e)).getMessage());
                ((l) this.f838f).h(Boolean.FALSE);
                return;
            case 8:
                ((i2.p) this.f838f).f(null, (IOException) this.f837e);
                return;
            case 9:
                ((i2.p) this.f838f).f((PluginInfoEntity) this.f837e, null);
                return;
            case 10:
                ((i2.p) this.f838f).f(null, new IOException("Unexpected code " + ((d0) this.f837e)));
                return;
            default:
                g gVar = (g) this.f838f;
                s sVar = gVar.f2628g;
                while (true) {
                    try {
                        ((Runnable) this.f837e).run();
                    } catch (Throwable th) {
                        x.m(th, y1.i.f2726d);
                    }
                    Runnable runnableW = gVar.W();
                    if (runnableW == null) {
                        return;
                    }
                    this.f837e = runnableW;
                    i4++;
                    if (i4 >= 16 && sVar.U(gVar)) {
                        sVar.S(gVar, this);
                        return;
                    }
                    break;
                }
                break;
        }
    }

    public /* synthetic */ a(Object obj, Object obj2, int i4, boolean z3) {
        this.f836d = i4;
        this.f837e = obj;
        this.f838f = obj2;
    }
}
