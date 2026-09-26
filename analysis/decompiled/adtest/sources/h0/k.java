package h0;

import android.net.ConnectivityManager;
import androidx.work.impl.WorkDatabase;
import d0.a0;
import h3.d0;
import h3.y;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class k implements i2.a {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1014d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f1015e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f1016f;

    public /* synthetic */ k(int i4, Object obj, Object obj2) {
        this.f1014d = i4;
        this.f1015e = obj;
        this.f1016f = obj2;
    }

    private final Object c() {
        long jA;
        int i4;
        y[] yVarArr;
        h3.p pVar = (h3.p) this.f1015e;
        d0 d0Var = (d0) this.f1016f;
        j2.n nVar = new j2.n();
        h3.q qVar = pVar.f1130e;
        synchronized (qVar.f1153z) {
            synchronized (qVar) {
                try {
                    d0 d0Var2 = qVar.f1148u;
                    d0 d0Var3 = new d0();
                    d0Var3.b(d0Var2);
                    d0Var3.b(d0Var);
                    nVar.f1276d = d0Var3;
                    jA = ((long) d0Var3.a()) - ((long) d0Var2.a());
                    yVarArr = (jA == 0 || qVar.f1132e.isEmpty()) ? null : (y[]) qVar.f1132e.values().toArray(new y[0]);
                    d0 d0Var4 = (d0) nVar.f1276d;
                    j2.i.e(d0Var4, "<set-?>");
                    qVar.f1148u = d0Var4;
                    d3.c.c(qVar.f1140m, qVar.f1133f + " onSettings", new k(3, qVar, nVar));
                } catch (Throwable th) {
                    throw th;
                }
            }
            try {
                qVar.f1153z.b((d0) nVar.f1276d);
            } catch (IOException e4) {
                h3.b bVar = h3.b.PROTOCOL_ERROR;
                qVar.b(bVar, bVar, e4);
            }
        }
        if (yVarArr != null) {
            for (y yVar : yVarArr) {
                synchronized (yVar) {
                    yVar.f1187h += jA;
                    if (jA > 0) {
                        yVar.notifyAll();
                    }
                }
            }
        }
        return u1.k.f2301a;
    }

    private final Object e() {
        i0.b bVar = (i0.b) this.f1015e;
        i0.a aVar = (i0.a) this.f1016f;
        j0.g gVar = bVar.f1204a;
        gVar.getClass();
        synchronized (gVar.f1222c) {
            if (gVar.f1223d.remove(aVar) && gVar.f1223d.isEmpty()) {
                gVar.d();
            }
        }
        return u1.k.f2301a;
    }

    @Override // i2.a
    public final Object a() {
        switch (this.f1014d) {
            case 0:
                e eVar = (e) this.f1015e;
                ConnectivityManager connectivityManager = (ConnectivityManager) this.f1016f;
                synchronized (l.f1018b) {
                    LinkedHashMap linkedHashMap = l.f1019c;
                    linkedHashMap.remove(eVar);
                    if (linkedHashMap.isEmpty()) {
                        a0.e().a(q.f1032a, "NetworkRequestConstraintController unregister shared callback");
                        connectivityManager.unregisterNetworkCallback(l.f1017a);
                        l.f1022f = false;
                        l.f1020d = null;
                        l.f1021e = false;
                    }
                    break;
                }
                break;
            case 1:
                h3.q qVar = (h3.q) this.f1015e;
                y yVar = (y) this.f1016f;
                try {
                    qVar.f1131d.b(yVar);
                    break;
                } catch (IOException e4) {
                    k3.e eVar2 = k3.e.f1300a;
                    k3.e.f1300a.j("Http2Connection.Listener failure for " + qVar.f1133f, 4, e4);
                    try {
                        yVar.e(h3.b.PROTOCOL_ERROR, e4);
                        break;
                    } catch (IOException unused) {
                    }
                }
                return u1.k.f2301a;
            case 2:
                return c();
            case 3:
                h3.q qVar2 = (h3.q) this.f1015e;
                qVar2.f1131d.a(qVar2, (d0) ((j2.n) this.f1016f).f1276d);
                break;
            case 4:
                return e();
            default:
                e0.y yVar2 = (e0.y) this.f1015e;
                UUID uuid = (UUID) this.f1016f;
                WorkDatabase workDatabase = yVar2.f694c;
                j2.i.d(workDatabase, "getWorkDatabase(...)");
                workDatabase.o(new e0.e(3, yVar2, uuid));
                e0.k.b(yVar2.f693b, yVar2.f694c, yVar2.f696e);
                break;
        }
        return u1.k.f2301a;
    }
}
