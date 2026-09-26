package m0;

import android.os.Build;
import android.text.TextUtils;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.workers.ConstraintTrackingWorker;
import d0.a0;
import d0.k0;
import d0.m0;
import e0.y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import l0.t;
import l0.u;
import l0.v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f1395a = a0.g("EnqueueRunnable");

    /* JADX WARN: Code duplicated, block: B:113:0x0261  */
    /* JADX WARN: Code duplicated, block: B:82:0x019a  */
    public static boolean a(e0.q qVar) {
        boolean z3;
        boolean z4;
        boolean z5;
        y yVar;
        boolean z6;
        WorkDatabase workDatabase;
        boolean z7;
        boolean z8;
        boolean z9;
        l0.p pVarB;
        l0.p pVarB2;
        e0.q qVar2 = qVar;
        HashSet hashSetK = e0.q.K(qVar2);
        y yVar2 = qVar2.f671h;
        List list = qVar2.f674k;
        int i4 = 0;
        String[] strArr = (String[]) hashSetK.toArray(new String[0]);
        String str = qVar2.f672i;
        d0.n nVar = qVar2.f673j;
        yVar2.f693b.f407d.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        WorkDatabase workDatabase2 = yVar2.f694c;
        boolean z10 = strArr != null && strArr.length > 0;
        k0 k0Var = k0.f469f;
        k0 k0Var2 = k0.f472i;
        k0 k0Var3 = k0.f470g;
        if (z10) {
            int length = strArr.length;
            z3 = false;
            z4 = false;
            z5 = true;
            while (true) {
                if (i4 < length) {
                    String str2 = strArr[i4];
                    List list2 = list;
                    l0.p pVarC = workDatabase2.w().c(str2);
                    if (pVarC == null) {
                        a0.e().c(f1395a, "Prerequisite " + str2 + " doesn't exist; not enqueuing");
                    } else {
                        k0 k0Var4 = pVarC.f1332b;
                        z5 &= k0Var4 == k0Var;
                        if (k0Var4 == k0Var3) {
                            z4 = true;
                        } else if (k0Var4 == k0Var2) {
                            z3 = true;
                        }
                        i4++;
                        list = list2;
                    }
                }
                z9 = false;
                z8 = true;
                qVar2.f677n = z8;
                return z9;
            }
        }
        z3 = false;
        z4 = false;
        z5 = true;
        List list3 = list;
        boolean zIsEmpty = TextUtils.isEmpty(str);
        k0 k0Var5 = k0.f467d;
        if (zIsEmpty || z10) {
            yVar = yVar2;
            z6 = zIsEmpty;
            workDatabase = workDatabase2;
            z7 = false;
        } else {
            List listD = workDatabase2.w().d(str);
            if (listD.isEmpty()) {
                yVar = yVar2;
                z6 = zIsEmpty;
                workDatabase = workDatabase2;
            } else {
                d0.n nVar2 = d0.n.f485f;
                z6 = zIsEmpty;
                d0.n nVar3 = d0.n.f486g;
                if (nVar == nVar2 || nVar == nVar3) {
                    l0.d dVarR = workDatabase2.r();
                    ArrayList arrayList = new ArrayList();
                    Iterator it = listD.iterator();
                    while (it.hasNext()) {
                        WorkDatabase workDatabase3 = workDatabase2;
                        l0.o oVar = (l0.o) it.next();
                        Iterator it2 = it;
                        String str3 = oVar.f1328a;
                        dVarR.getClass();
                        j2.i.e(str3, "id");
                        l0.d dVar = dVarR;
                        y yVar3 = yVar2;
                        if (!((Boolean) l3.h.W(dVarR.f1307a, true, false, new l0.b(0, str3))).booleanValue()) {
                            k0 k0Var6 = oVar.f1329b;
                            boolean z11 = z5 & (k0Var6 == k0Var);
                            if (k0Var6 == k0Var3) {
                                z4 = true;
                            } else if (k0Var6 == k0Var2) {
                                z3 = true;
                            }
                            arrayList.add(oVar.f1328a);
                            z5 = z11;
                        }
                        it = it2;
                        workDatabase2 = workDatabase3;
                        dVarR = dVar;
                        yVar2 = yVar3;
                    }
                    yVar = yVar2;
                    workDatabase = workDatabase2;
                    List list4 = arrayList;
                    list4 = arrayList;
                    if (nVar == nVar3 && (z3 || z4)) {
                        t tVarW = workDatabase.w();
                        Iterator it3 = tVarW.d(str).iterator();
                        while (it3.hasNext()) {
                            tVarW.a(((l0.o) it3.next()).f1328a);
                        }
                        z3 = false;
                        z4 = false;
                        list4 = Collections.EMPTY_LIST;
                    }
                    strArr = (String[]) list4.toArray(strArr);
                    z10 = strArr.length > 0;
                } else {
                    if (nVar == d0.n.f484e) {
                        Iterator it4 = listD.iterator();
                        while (true) {
                            if (it4.hasNext()) {
                                k0 k0Var7 = ((l0.o) it4.next()).f1329b;
                                if (k0Var7 == k0Var5 || k0Var7 == k0.f468e) {
                                    z9 = false;
                                    z8 = true;
                                    qVar2.f677n = z8;
                                    return z9;
                                }
                            }
                        }
                    }
                    workDatabase2.o(new d0.t(workDatabase2, str, yVar2, 4));
                    t tVarW2 = workDatabase2.w();
                    Iterator it5 = listD.iterator();
                    while (it5.hasNext()) {
                        tVarW2.a(((l0.o) it5.next()).f1328a);
                    }
                    yVar = yVar2;
                    workDatabase = workDatabase2;
                    z7 = true;
                }
            }
            z7 = false;
        }
        Iterator it6 = list3.iterator();
        boolean z12 = z7;
        while (it6.hasNext()) {
            m0 m0Var = (m0) it6.next();
            l0.p pVar = m0Var.f481b;
            UUID uuid = m0Var.f480a;
            if (!z10 || z5) {
                pVar.f1344n = jCurrentTimeMillis;
            } else if (z4) {
                pVar.f1332b = k0Var3;
            } else if (z3) {
                pVar.f1332b = k0Var2;
            } else {
                pVar.f1332b = k0.f471h;
            }
            if (pVar.f1332b == k0Var5) {
                z12 = true;
            }
            t tVarW3 = workDatabase.w();
            y yVar4 = yVar;
            Iterator it7 = it6;
            j2.i.e(yVar4.f696e, "schedulers");
            j2.i.e(pVar, "workSpec");
            d0.j jVar = pVar.f1335e;
            boolean zA = jVar.a("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME");
            k0 k0Var8 = k0Var5;
            boolean zA2 = jVar.a("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_PACKAGE_NAME");
            boolean zA3 = jVar.a("androidx.work.impl.workers.RemoteListenableWorker.ARGUMENT_CLASS_NAME");
            if (!zA && zA2 && zA3) {
                String str4 = pVar.f1333c;
                d0.i iVar = new d0.i(0);
                iVar.b(jVar.f465a);
                LinkedHashMap linkedHashMap = iVar.f460a;
                linkedHashMap.put("androidx.work.multiprocess.RemoteListenableDelegatingWorker.ARGUMENT_REMOTE_LISTENABLE_WORKER_NAME", str4);
                d0.j jVar2 = new d0.j(linkedHashMap);
                l3.h.n0(jVar2);
                pVarB = l0.p.b(pVar, null, null, "androidx.work.multiprocess.RemoteListenableDelegatingWorker", jVar2, 0, 0L, 0, 0, 0L, 0, 33554411);
            } else {
                pVarB = pVar;
            }
            if (Build.VERSION.SDK_INT <= 25) {
                d0.e eVar = pVarB.f1340j;
                String str5 = pVarB.f1333c;
                if (j2.i.a(str5, ConstraintTrackingWorker.class.getName()) || !(eVar.f437e || eVar.f438f)) {
                    pVarB2 = pVarB;
                } else {
                    d0.i iVar2 = new d0.i(0);
                    d0.j jVar3 = pVarB.f1335e;
                    l0.p pVar2 = pVarB;
                    j2.i.e(jVar3, "data");
                    iVar2.b(jVar3.f465a);
                    LinkedHashMap linkedHashMap2 = iVar2.f460a;
                    linkedHashMap2.put("androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME", str5);
                    d0.j jVar4 = new d0.j(linkedHashMap2);
                    l3.h.n0(jVar4);
                    pVarB2 = l0.p.b(pVar2, null, null, ConstraintTrackingWorker.class.getName(), jVar4, 0, 0L, 0, 0, 0L, 0, 33554411);
                }
            } else {
                pVarB2 = pVarB;
            }
            tVarW3.getClass();
            l3.h.W(tVarW3.f1361a, false, true, new h0.e(7, tVarW3, pVarB2));
            if (z10) {
                int i5 = 0;
                for (int length2 = strArr.length; i5 < length2; length2 = length2) {
                    String str6 = strArr[i5];
                    String string = uuid.toString();
                    j2.i.d(string, "toString(...)");
                    l0.a aVar = new l0.a(string, str6);
                    l0.d dVarR2 = workDatabase.r();
                    dVarR2.getClass();
                    l3.h.W(dVarR2.f1307a, false, true, new h0.e(1, dVarR2, aVar));
                    i5++;
                    strArr = strArr;
                }
            }
            String[] strArr2 = strArr;
            v vVarX = workDatabase.x();
            String string2 = uuid.toString();
            j2.i.d(string2, "toString(...)");
            Set set = m0Var.f482c;
            vVarX.getClass();
            j2.i.e(string2, "id");
            j2.i.e(set, "tags");
            Iterator it8 = set.iterator();
            while (it8.hasNext()) {
                l3.h.W(vVarX.f1365a, false, true, new h0.e(8, vVarX, new u((String) it8.next(), string2)));
            }
            if (!z6) {
                l0.m mVarU = workDatabase.u();
                String string3 = uuid.toString();
                j2.i.d(string3, "toString(...)");
                l0.l lVar = new l0.l(str, string3);
                mVarU.getClass();
                l3.h.W(mVarU.f1325a, false, true, new h0.e(4, mVarU, lVar));
            }
            it6 = it7;
            k0Var5 = k0Var8;
            strArr = strArr2;
            yVar = yVar4;
        }
        z8 = true;
        qVar2 = qVar;
        z9 = z12;
        qVar2.f677n = z8;
        return z9;
    }
}
