package a3;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Build;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f197a;

    public q(z zVar) {
        h0.g gVar;
        j2.i.e(zVar, "trackers");
        i0.c cVar = new i0.c((j0.g) zVar.f280a, 0);
        i0.c cVar2 = new i0.c((j0.a) zVar.f282c);
        i0.c cVar3 = new i0.c((j0.g) zVar.f284e, 4);
        j0.g gVar2 = (j0.g) zVar.f283d;
        i0.c cVar4 = new i0.c(gVar2, 2);
        i0.c cVar5 = new i0.c(gVar2, 3);
        i0.f fVar = new i0.f(gVar2);
        i0.e eVar = new i0.e(gVar2);
        if (Build.VERSION.SDK_INT >= 28) {
            Context context = (Context) zVar.f281b;
            String str = h0.q.f1032a;
            j2.i.e(context, "context");
            Object systemService = context.getSystemService("connectivity");
            j2.i.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
            gVar = new h0.g((ConnectivityManager) systemService);
        } else {
            gVar = null;
        }
        this.f197a = v1.k.s0(cVar, cVar2, cVar3, cVar4, cVar5, fVar, eVar, gVar);
    }

    public r a() {
        return new r((String[]) this.f197a.toArray(new String[0]));
    }

    public void b(String str) {
        int i4 = 0;
        while (true) {
            ArrayList arrayList = this.f197a;
            if (i4 >= arrayList.size()) {
                return;
            }
            if (str.equalsIgnoreCase((String) arrayList.get(i4))) {
                arrayList.remove(i4);
                arrayList.remove(i4);
                i4 -= 2;
            }
            i4 += 2;
        }
    }

    public u2.g c(l0.p pVar) {
        j2.i.e(pVar, "spec");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = this.f197a;
        int size = arrayList2.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList2.get(i4);
            i4++;
            if (((i0.d) obj).a(pVar)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(v1.l.u0(arrayList));
        int size2 = arrayList.size();
        int i5 = 0;
        while (i5 < size2) {
            Object obj2 = arrayList.get(i5);
            i5++;
            arrayList3.add(((i0.d) obj2).c(pVar.f1340j));
        }
        return u2.s.b(new h0.o(0, (u2.g[]) v1.j.G0(arrayList3).toArray(new u2.g[0])));
    }

    public q() {
        this.f197a = new ArrayList(20);
    }
}
