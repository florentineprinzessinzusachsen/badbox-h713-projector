package e0;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.Build;
import android.os.Trace;
import androidx.work.impl.WorkDatabase;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class y extends d0.l0 {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static y f689k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static y f690l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Object f691m;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d0.b f693b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final WorkDatabase f694c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a3.l f695d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f696e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f f697f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final m0.e f698g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f699h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public BroadcastReceiver.PendingResult f700i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final a3.z f701j;

    static {
        d0.a0.g("WorkManagerImpl");
        f689k = null;
        f690l = null;
        f691m = new Object();
    }

    public y(Context context, final d0.b bVar, a3.l lVar, final WorkDatabase workDatabase, final List list, f fVar, a3.z zVar) {
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 24 && applicationContext.isDeviceProtectedStorage()) {
            throw new IllegalStateException("Cannot initialize WorkManager in direct boot mode");
        }
        d0.a0 a0Var = new d0.a0(bVar.f411h);
        synchronized (d0.a0.f401b) {
            try {
                if (d0.a0.f402c == null) {
                    d0.a0.f402c = a0Var;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f692a = applicationContext;
        this.f695d = lVar;
        this.f694c = workDatabase;
        this.f697f = fVar;
        this.f701j = zVar;
        this.f693b = bVar;
        this.f696e = list;
        r2.s sVar = (r2.s) lVar.f185f;
        j2.i.d(sVar, "getTaskCoroutineDispatcher(...)");
        w2.c cVarA = r2.x.a(sVar);
        this.f698g = new m0.e(workDatabase, 1);
        final m0.j jVar = (m0.j) lVar.f184e;
        String str = k.f642a;
        fVar.a(new b() { // from class: e0.i
            @Override // e0.b
            public final void d(final l0.k kVar, boolean z3) {
                final List list2 = list;
                final d0.b bVar2 = bVar;
                final WorkDatabase workDatabase2 = workDatabase;
                jVar.execute(new Runnable() { // from class: e0.j
                    @Override // java.lang.Runnable
                    public final void run() {
                        List list3 = list2;
                        Iterator it = list3.iterator();
                        while (it.hasNext()) {
                            ((h) it.next()).a(kVar.f1321a);
                        }
                        k.b(bVar2, workDatabase2, list3);
                    }
                });
            }
        });
        ((m0.j) lVar.f184e).execute(new m0.c(applicationContext, this));
        String str2 = p.f668a;
        if (m0.i.a(applicationContext, bVar)) {
            p.t tVar = workDatabase.w().f1361a;
            d0.h hVar = new d0.h(12);
            p.h hVarF = tVar.f();
            String[] strArr = (String[]) Arrays.copyOf(new String[]{"workspec"}, 1);
            j2.i.e(strArr, "tables");
            p.i0 i0Var = hVarF.f1646b;
            i0Var.getClass();
            w1.i iVar = new w1.i();
            for (String str3 : strArr) {
                LinkedHashMap linkedHashMap = i0Var.f1666c;
                String lowerCase = str3.toLowerCase(Locale.ROOT);
                j2.i.d(lowerCase, "toLowerCase(...)");
                Set set = (Set) linkedHashMap.get(lowerCase);
                if (set != null) {
                    iVar.addAll(set);
                } else {
                    iVar.add(str3);
                }
            }
            String[] strArr2 = (String[]) d0.l0.g(iVar).toArray(new String[0]);
            int length = strArr2.length;
            int[] iArr = new int[length];
            for (int i4 = 0; i4 < length; i4++) {
                String str4 = strArr2[i4];
                LinkedHashMap linkedHashMap2 = i0Var.f1669f;
                String lowerCase2 = str4.toLowerCase(Locale.ROOT);
                j2.i.d(lowerCase2, "toLowerCase(...)");
                Integer num = (Integer) linkedHashMap2.get(lowerCase2);
                if (num == null) {
                    throw new IllegalArgumentException("There is no table with name ".concat(str4));
                }
                iArr[i4] = num.intValue();
            }
            u1.f fVar2 = new u1.f(strArr2, iArr);
            String[] strArr3 = (String[]) fVar2.f2294d;
            int[] iArr2 = (int[]) fVar2.f2295e;
            j2.i.e(strArr3, "resolvedTableNames");
            j2.i.e(iArr2, "tableIds");
            y1.c cVar = null;
            u2.g oVar = new h0.o(2, new m0.n(i0Var, iArr2, strArr3, null));
            y1.i iVar2 = y1.i.f2726d;
            t2.a aVar = t2.a.f2175e;
            u2.g mVar = new u2.m(new r.i(oVar instanceof v2.l ? ((v2.l) oVar).a(iVar2, 0, aVar) : new v2.e(oVar, iVar2, 0, aVar), tVar, hVar), new n(4, null));
            r2.x.p(cVarA, null, null, new j1.d(new u2.m(u2.s.b(mVar instanceof v2.l ? ((v2.l) mVar).a(iVar2, 0, aVar) : new v2.e(mVar, iVar2, 0, aVar)), new o(applicationContext, null)), cVar, 5), 3);
        }
    }

    public static y R() {
        synchronized (f691m) {
            try {
                y yVar = f689k;
                if (yVar != null) {
                    return yVar;
                }
                return f690l;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static y S(Context context) {
        y yVarR;
        synchronized (f691m) {
            try {
                yVarR = R();
                if (yVarR == null) {
                    context.getApplicationContext();
                    throw new IllegalStateException("WorkManager is not initialized properly.  You have explicitly disabled WorkManagerInitializer in your manifest, have not manually called WorkManager#initialize at this point, and your Application does not implement Configuration.Provider.");
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return yVarR;
    }

    public final void T() {
        synchronized (f691m) {
            try {
                this.f699h = true;
                BroadcastReceiver.PendingResult pendingResult = this.f700i;
                if (pendingResult != null) {
                    pendingResult.finish();
                    this.f700i = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void U() {
        d0.l lVar = this.f693b.f416m;
        a3.o oVar = new a3.o(2, this);
        j2.i.e(lVar, "<this>");
        boolean zS = a.a.s();
        if (zS) {
            try {
                Trace.beginSection(a.a.I("ReschedulingWork"));
            } finally {
                if (zS) {
                    Trace.endSection();
                }
            }
        }
        oVar.a();
    }
}
