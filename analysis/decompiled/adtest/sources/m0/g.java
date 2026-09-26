package m0;

import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;
import androidx.work.impl.WorkDatabase;
import d0.a0;
import d0.m0;
import e0.k0;
import e0.y;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import l0.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f1407a = {13, 15, 14};

    public static final void a(y yVar, String str) {
        k0 k0VarB;
        WorkDatabase workDatabase = yVar.f694c;
        j2.i.d(workDatabase, "getWorkDatabase(...)");
        t tVarW = workDatabase.w();
        l0.d dVarR = workDatabase.r();
        ArrayList arrayListT0 = v1.k.t0(str);
        while (!arrayListT0.isEmpty()) {
            String str2 = (String) v1.j.B0(arrayListT0);
            d0.k0 k0VarB2 = tVarW.b(str2);
            if (k0VarB2 != d0.k0.f469f && k0VarB2 != d0.k0.f470g) {
                ((Number) l3.h.W(tVarW.f1361a, false, true, new l0.b(10, str2))).intValue();
            }
            arrayListT0.addAll(dVarR.a(str2));
        }
        e0.f fVar = yVar.f697f;
        j2.i.d(fVar, "getProcessor(...)");
        synchronized (fVar.f622k) {
            a0.e().a(e0.f.f611l, "Processor cancelling " + str);
            fVar.f620i.add(str);
            k0VarB = fVar.b(str);
        }
        e0.f.e(str, k0VarB, 1);
        Iterator it = yVar.f696e.iterator();
        while (it.hasNext()) {
            ((e0.h) it.next()).a(str);
        }
    }

    public static final void b(WorkDatabase workDatabase, d0.b bVar, e0.q qVar) {
        int i4;
        j2.i.e(workDatabase, "workDatabase");
        j2.i.e(bVar, "configuration");
        if (Build.VERSION.SDK_INT < 24) {
            return;
        }
        ArrayList arrayListT0 = v1.k.t0(qVar);
        int i5 = 0;
        while (!arrayListT0.isEmpty()) {
            List list = ((e0.q) v1.j.B0(arrayListT0)).f674k;
            j2.i.d(list, "getWork(...)");
            if (list.isEmpty()) {
                i4 = 0;
            } else {
                Iterator it = list.iterator();
                i4 = 0;
                while (it.hasNext()) {
                    if (((m0) it.next()).f481b.f1340j.b() && (i4 = i4 + 1) < 0) {
                        throw new ArithmeticException("Count overflow has happened.");
                    }
                }
            }
            i5 += i4;
        }
        if (i5 == 0) {
            return;
        }
        int iIntValue = ((Number) l3.h.W(workDatabase.w().f1361a, true, false, new d0.h(11))).intValue();
        int i6 = bVar.f413j;
        if (iIntValue + i5 <= i6) {
            return;
        }
        throw new IllegalArgumentException("Too many workers with contentUriTriggers are enqueued:\ncontentUriTrigger workers limit: " + i6 + ";\nalready enqueued count: " + iIntValue + ";\ncurrent enqueue operation count: " + i5 + ".\nTo address this issue you can: \n1. enqueue less workers or batch some of workers with content uri triggers together;\n2. increase limit via Configuration.Builder.setContentUriTriggerWorkersLimit;\nPlease beware that workers with content uri triggers immediately occupy slots in JobScheduler so no updates to content uris are missed.");
    }

    public static f c(int[] iArr, int[] iArr2) {
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        for (int i4 : iArr) {
            try {
                builder.addCapability(i4);
            } catch (IllegalArgumentException e4) {
                a0 a0VarE = a0.e();
                String str = f.f1405b;
                String str2 = f.f1405b;
                String str3 = "Ignoring adding capability '" + i4 + '\'';
                if (a0VarE.f403a <= 5) {
                    Log.w(str2, str3, e4);
                }
            }
        }
        for (int i5 = 0; i5 < 3; i5++) {
            int i6 = f1407a[i5];
            int length = iArr.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    i7 = -1;
                    break;
                }
                if (i6 == iArr[i7]) {
                    break;
                }
                i7++;
            }
            if (!(i7 >= 0)) {
                try {
                    builder.removeCapability(i6);
                } catch (IllegalArgumentException e5) {
                    a0 a0VarE2 = a0.e();
                    String str4 = f.f1405b;
                    String str5 = f.f1405b;
                    String str6 = "Ignoring removing default capability '" + i6 + '\'';
                    if (a0VarE2.f403a <= 5) {
                        Log.w(str5, str6, e5);
                    }
                }
            }
        }
        for (int i8 : iArr2) {
            builder.addTransportType(i8);
        }
        NetworkRequest networkRequestBuild = builder.build();
        j2.i.d(networkRequestBuild, "build(...)");
        return new f(networkRequestBuild);
    }
}
