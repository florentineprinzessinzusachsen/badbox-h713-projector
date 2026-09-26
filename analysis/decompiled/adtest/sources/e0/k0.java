package e0;

import android.content.Context;
import android.os.Build;
import android.os.Trace;
import android.util.Log;
import androidx.work.OverwritingInputMerger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import r2.d1;
import r2.v0;
import r2.x0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l0.p f643a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f644b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f645c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final a3.l f646d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d0.b f647e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final d0.l f648f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final k0.a f649g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final WorkDatabase f650h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final l0.t f651i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final l0.d f652j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final ArrayList f653k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final String f654l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final x0 f655m;

    public k0(c0 c0Var) {
        l0.p pVar = c0Var.f600e;
        this.f643a = pVar;
        this.f644b = c0Var.f602g;
        String str = pVar.f1331a;
        this.f645c = str;
        this.f646d = c0Var.f597b;
        d0.b bVar = c0Var.f596a;
        this.f647e = bVar;
        this.f648f = bVar.f407d;
        this.f649g = c0Var.f598c;
        WorkDatabase workDatabase = c0Var.f599d;
        this.f650h = workDatabase;
        this.f651i = workDatabase.w();
        this.f652j = workDatabase.r();
        ArrayList arrayList = c0Var.f601f;
        this.f653k = arrayList;
        this.f654l = "Work [ id=" + str + ", tags={ " + v1.j.y0(arrayList, ",", null, null, null, 62) + " } ]";
        this.f655m = r2.x.b();
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:44:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:46:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:47:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:58:0x012e  */
    /* JADX WARN: Code duplicated, block: B:59:0x0144  */
    /* JADX WARN: Code duplicated, block: B:61:0x0178 A[LOOP:0: B:60:0x0176->B:61:0x0178, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:67:0x0211  */
    /* JADX WARN: Code duplicated, block: B:68:0x0218  */
    /* JADX WARN: Code duplicated, block: B:70:0x021e  */
    /* JADX WARN: Code duplicated, block: B:71:0x0225  */
    /* JADX WARN: Code duplicated, block: B:7:0x0025  */
    /* JADX WARN: Code duplicated, block: B:85:0x0296  */
    public static final Object a(final k0 k0Var, a2.c cVar) {
        j0 j0Var;
        l0.p pVar;
        d0.j jVar;
        OverwritingInputMerger overwritingInputMerger;
        int i4;
        ArrayList arrayListA0;
        LinkedHashMap linkedHashMap;
        int size;
        String str;
        d0.j jVar2;
        m0.q qVar;
        String str2;
        final d0.z zVarA;
        d1 d1Var;
        Object objN;
        Object e0Var;
        Throwable th;
        String str3;
        CancellationException e4;
        String str4;
        String str5;
        String str6 = k0Var.f654l;
        String str7 = k0Var.f645c;
        a3.l lVar = k0Var.f646d;
        WorkDatabase workDatabase = k0Var.f650h;
        d0.b bVar = k0Var.f647e;
        d0.l lVar2 = bVar.f416m;
        l0.p pVar2 = k0Var.f643a;
        if (cVar instanceof j0) {
            j0Var = (j0) cVar;
            int i5 = j0Var.f641i;
            if ((i5 & Integer.MIN_VALUE) != 0) {
                j0Var.f641i = i5 - Integer.MIN_VALUE;
            } else {
                j0Var = new j0(k0Var, cVar);
            }
        } else {
            j0Var = new j0(k0Var, cVar);
        }
        Object objW = j0Var.f639g;
        int i6 = j0Var.f641i;
        if (i6 == 0) {
            d0.l0.M(objW);
            d0.l lVar3 = bVar.f408e;
            lVar2.getClass();
            final boolean zS = a.a.s();
            final String str8 = pVar2.f1354x;
            String str9 = pVar2.f1333c;
            String str10 = pVar2.f1334d;
            d0.j jVar3 = pVar2.f1335e;
            final int i7 = 0;
            if (zS && str8 != null) {
                int iHashCode = pVar2.hashCode();
                pVar = pVar2;
                if (Build.VERSION.SDK_INT >= 29) {
                    b0.a.a(iHashCode, a.a.I(str8));
                } else {
                    String strI = a.a.I(str8);
                    try {
                        if (a.a.f2f == null) {
                            jVar = jVar3;
                            try {
                                a.a.f2f = Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                            } catch (Exception e5) {
                                e = e5;
                                a.a.r("asyncTraceBegin", e);
                            }
                        } else {
                            jVar = jVar3;
                        }
                        a.a.f2f.invoke(null, Long.valueOf(a.a.f0d), strI, Integer.valueOf(iHashCode));
                    } catch (Exception e6) {
                        e = e6;
                        jVar = jVar3;
                    }
                }
                if (((Boolean) workDatabase.n(new Callable(k0Var) { // from class: e0.a0

                    /* JADX INFO: renamed from: b, reason: collision with root package name */
                    public final /* synthetic */ k0 f580b;

                    {
                        this.f580b = k0Var;
                    }

                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        switch (i7) {
                            case 0:
                                k0 k0Var2 = this.f580b;
                                l0.p pVar3 = k0Var2.f643a;
                                d0.k0 k0Var3 = pVar3.f1332b;
                                String str11 = pVar3.f1333c;
                                d0.k0 k0Var4 = d0.k0.f467d;
                                if (k0Var3 != k0Var4) {
                                    String str12 = m0.f662a;
                                    d0.a0.e().a(str12, str11 + " is not in ENQUEUED state. Nothing more to do");
                                    return Boolean.TRUE;
                                }
                                if (pVar3.c() || (pVar3.f1332b == k0Var4 && pVar3.f1341k > 0)) {
                                    k0Var2.f648f.getClass();
                                    if (System.currentTimeMillis() < pVar3.a()) {
                                        d0.a0.e().a(m0.f662a, "Delaying execution for " + str11 + " because it is being executed before schedule.");
                                        return Boolean.TRUE;
                                    }
                                }
                                return Boolean.FALSE;
                            default:
                                k0 k0Var5 = this.f580b;
                                l0.t tVar = k0Var5.f651i;
                                String str13 = k0Var5.f645c;
                                boolean z3 = false;
                                if (tVar.b(str13) == d0.k0.f467d) {
                                    tVar.h(d0.k0.f468e, str13);
                                    ((Number) l3.h.W(tVar.f1361a, false, true, new l0.b(14, str13))).intValue();
                                    tVar.i(-256, str13);
                                    z3 = true;
                                }
                                return Boolean.valueOf(z3);
                        }
                    }
                })).booleanValue()) {
                    return new f0();
                }
                if (pVar.c()) {
                    str = str7;
                    jVar2 = jVar;
                } else {
                    bVar.f409f.getClass();
                    j2.i.e(str10, "className");
                    String str11 = d0.p.f494a;
                    try {
                        try {
                            Object objNewInstance = Class.forName(str10).getDeclaredConstructor(null).newInstance(null);
                            j2.i.c(objNewInstance, "null cannot be cast to non-null type androidx.work.InputMerger");
                            overwritingInputMerger = (OverwritingInputMerger) objNewInstance;
                        } catch (Exception e7) {
                            e = e7;
                            d0.a0.e().d(d0.p.f494a, "Trouble instantiating ".concat(str10), e);
                            overwritingInputMerger = null;
                        }
                    } catch (Exception e8) {
                        e = e8;
                    }
                    if (overwritingInputMerger == null) {
                        d0.a0.e().c(m0.f662a, "Could not create Input Merger ".concat(str10));
                        return new d0();
                    }
                    List listS = l3.h.S(jVar);
                    l0.t tVar = k0Var.f651i;
                    tVar.getClass();
                    j2.i.e(str7, "id");
                    i4 = 0;
                    arrayListA0 = v1.j.A0(listS, (List) l3.h.W(tVar.f1361a, true, false, new l0.b(13, str7)));
                    d0.i iVar = new d0.i(0);
                    linkedHashMap = new LinkedHashMap();
                    size = arrayListA0.size();
                    while (i4 < size) {
                        Object obj = arrayListA0.get(i4);
                        i4++;
                        ArrayList arrayList = arrayListA0;
                        Map mapUnmodifiableMap = Collections.unmodifiableMap(((d0.j) obj).f465a);
                        j2.i.d(mapUnmodifiableMap, "unmodifiableMap(...)");
                        linkedHashMap.putAll(mapUnmodifiableMap);
                        str7 = str7;
                        arrayListA0 = arrayList;
                    }
                    str = str7;
                    iVar.b(linkedHashMap);
                    jVar2 = new d0.j(iVar.f460a);
                    l3.h.n0(jVar2);
                }
                UUID uuidFromString = UUID.fromString(str);
                ArrayList arrayList2 = k0Var.f653k;
                ExecutorService executorService = bVar.f404a;
                y2.e eVar = bVar.f405b;
                str2 = str6;
                qVar = new m0.q(workDatabase, k0Var.f649g, lVar);
                WorkerParameters workerParameters = new WorkerParameters();
                workerParameters.f310a = uuidFromString;
                workerParameters.f311b = jVar2;
                new HashSet(arrayList2);
                workerParameters.f312c = executorService;
                workerParameters.f313d = eVar;
                workerParameters.f314e = lVar;
                workerParameters.f315f = lVar3;
                try {
                    zVarA = lVar3.a(k0Var.f644b, str9, workerParameters);
                    final int i8 = 1;
                    zVarA.f517d = true;
                    y1.h hVar = j0Var.f42e;
                    j2.i.b(hVar);
                    y1.f fVarK = hVar.k(r2.t.f2027e);
                    j2.i.b(fVarK);
                    d1Var = (d1) ((v0) fVarK);
                    d1Var.I(true, new r2.h0(i8, new i2.l() { // from class: e0.b0
                        @Override // i2.l
                        public final Object h(Object obj2) {
                            String str12;
                            Throwable th2 = (Throwable) obj2;
                            if (th2 instanceof z) {
                                zVarA.f516c.compareAndSet(-256, ((z) th2).f702d);
                            }
                            if (zS && (str12 = str8) != null) {
                                k0 k0Var2 = k0Var;
                                d0.l lVar4 = k0Var2.f647e.f416m;
                                int iHashCode2 = k0Var2.f643a.hashCode();
                                lVar4.getClass();
                                if (Build.VERSION.SDK_INT >= 29) {
                                    b0.a.b(iHashCode2, a.a.I(str12));
                                } else {
                                    String strI2 = a.a.I(str12);
                                    try {
                                        if (a.a.f3g == null) {
                                            a.a.f3g = Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                                        }
                                        a.a.f3g.invoke(null, Long.valueOf(a.a.f0d), strI2, Integer.valueOf(iHashCode2));
                                    } catch (Exception e9) {
                                        a.a.r("asyncTraceEnd", e9);
                                    }
                                }
                            }
                            return u1.k.f2301a;
                        }
                    }));
                    objN = workDatabase.n(new Callable(k0Var) { // from class: e0.a0

                        /* JADX INFO: renamed from: b, reason: collision with root package name */
                        public final /* synthetic */ k0 f580b;

                        {
                            this.f580b = k0Var;
                        }

                        @Override // java.util.concurrent.Callable
                        public final Object call() {
                            switch (i8) {
                                case 0:
                                    k0 k0Var2 = this.f580b;
                                    l0.p pVar3 = k0Var2.f643a;
                                    d0.k0 k0Var3 = pVar3.f1332b;
                                    String str12 = pVar3.f1333c;
                                    d0.k0 k0Var4 = d0.k0.f467d;
                                    if (k0Var3 != k0Var4) {
                                        String str13 = m0.f662a;
                                        d0.a0.e().a(str13, str12 + " is not in ENQUEUED state. Nothing more to do");
                                        return Boolean.TRUE;
                                    }
                                    if (pVar3.c() || (pVar3.f1332b == k0Var4 && pVar3.f1341k > 0)) {
                                        k0Var2.f648f.getClass();
                                        if (System.currentTimeMillis() < pVar3.a()) {
                                            d0.a0.e().a(m0.f662a, "Delaying execution for " + str12 + " because it is being executed before schedule.");
                                            return Boolean.TRUE;
                                        }
                                    }
                                    return Boolean.FALSE;
                                default:
                                    k0 k0Var5 = this.f580b;
                                    l0.t tVar2 = k0Var5.f651i;
                                    String str14 = k0Var5.f645c;
                                    boolean z3 = false;
                                    if (tVar2.b(str14) == d0.k0.f467d) {
                                        tVar2.h(d0.k0.f468e, str14);
                                        ((Number) l3.h.W(tVar2.f1361a, false, true, new l0.b(14, str14))).intValue();
                                        tVar2.i(-256, str14);
                                        z3 = true;
                                    }
                                    return Boolean.valueOf(z3);
                            }
                        }
                    });
                    j2.i.d(objN, "runInTransaction(...)");
                    if (!((Boolean) objN).booleanValue()) {
                        return new f0();
                    }
                    if (d1Var.J()) {
                        return new f0();
                    }
                    n0.a aVar = (n0.a) lVar.f187h;
                    j2.i.d(aVar, "getMainThreadExecutor(...)");
                    r2.s sVarI = r2.x.i(aVar);
                    try {
                        d0.u uVar = new d0.u(k0Var, zVarA, qVar, null, 1);
                        j0Var.f641i = 1;
                        objW = r2.x.w(sVarI, uVar, j0Var);
                        e0Var = z1.a.f2781d;
                        if (objW != e0Var) {
                        }
                        return e0Var;
                    } catch (CancellationException e9) {
                        e4 = e9;
                        str3 = str2;
                        str4 = m0.f662a;
                        str5 = str3 + " was cancelled";
                        if (d0.a0.e().f403a <= 4) {
                            Log.i(str4, str5, e4);
                        }
                        throw e4;
                    } catch (Throwable th2) {
                        th = th2;
                        String str12 = m0.f662a;
                        d0.a0.e().d(str12, str2 + " failed because it threw an exception/error", th);
                        return new d0();
                    }
                } catch (Throwable unused) {
                    String str13 = m0.f662a;
                    d0.a0.e().c(str13, "Could not create Worker " + str9);
                    return new d0();
                }
            }
            pVar = pVar2;
            jVar = jVar3;
            if (((Boolean) workDatabase.n(new Callable(k0Var) { // from class: e0.a0

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ k0 f580b;

                {
                    this.f580b = k0Var;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    switch (i7) {
                        case 0:
                            k0 k0Var2 = this.f580b;
                            l0.p pVar3 = k0Var2.f643a;
                            d0.k0 k0Var3 = pVar3.f1332b;
                            String str14 = pVar3.f1333c;
                            d0.k0 k0Var4 = d0.k0.f467d;
                            if (k0Var3 != k0Var4) {
                                String str15 = m0.f662a;
                                d0.a0.e().a(str15, str14 + " is not in ENQUEUED state. Nothing more to do");
                                return Boolean.TRUE;
                            }
                            if (pVar3.c() || (pVar3.f1332b == k0Var4 && pVar3.f1341k > 0)) {
                                k0Var2.f648f.getClass();
                                if (System.currentTimeMillis() < pVar3.a()) {
                                    d0.a0.e().a(m0.f662a, "Delaying execution for " + str14 + " because it is being executed before schedule.");
                                    return Boolean.TRUE;
                                }
                            }
                            return Boolean.FALSE;
                        default:
                            k0 k0Var5 = this.f580b;
                            l0.t tVar2 = k0Var5.f651i;
                            String str16 = k0Var5.f645c;
                            boolean z3 = false;
                            if (tVar2.b(str16) == d0.k0.f467d) {
                                tVar2.h(d0.k0.f468e, str16);
                                ((Number) l3.h.W(tVar2.f1361a, false, true, new l0.b(14, str16))).intValue();
                                tVar2.i(-256, str16);
                                z3 = true;
                            }
                            return Boolean.valueOf(z3);
                    }
                }
            })).booleanValue()) {
                return new f0();
            }
            if (pVar.c()) {
                str = str7;
                jVar2 = jVar;
            } else {
                bVar.f409f.getClass();
                j2.i.e(str10, "className");
                String str14 = d0.p.f494a;
                Object objNewInstance2 = Class.forName(str10).getDeclaredConstructor(null).newInstance(null);
                j2.i.c(objNewInstance2, "null cannot be cast to non-null type androidx.work.InputMerger");
                overwritingInputMerger = (OverwritingInputMerger) objNewInstance2;
                if (overwritingInputMerger == null) {
                    d0.a0.e().c(m0.f662a, "Could not create Input Merger ".concat(str10));
                    return new d0();
                }
                List listS2 = l3.h.S(jVar);
                l0.t tVar2 = k0Var.f651i;
                tVar2.getClass();
                j2.i.e(str7, "id");
                i4 = 0;
                arrayListA0 = v1.j.A0(listS2, (List) l3.h.W(tVar2.f1361a, true, false, new l0.b(13, str7)));
                d0.i iVar2 = new d0.i(0);
                linkedHashMap = new LinkedHashMap();
                size = arrayListA0.size();
                while (i4 < size) {
                    Object obj2 = arrayListA0.get(i4);
                    i4++;
                    ArrayList arrayList3 = arrayListA0;
                    Map mapUnmodifiableMap2 = Collections.unmodifiableMap(((d0.j) obj2).f465a);
                    j2.i.d(mapUnmodifiableMap2, "unmodifiableMap(...)");
                    linkedHashMap.putAll(mapUnmodifiableMap2);
                    str7 = str7;
                    arrayListA0 = arrayList3;
                }
                str = str7;
                iVar2.b(linkedHashMap);
                jVar2 = new d0.j(iVar2.f460a);
                l3.h.n0(jVar2);
            }
            UUID uuidFromString2 = UUID.fromString(str);
            ArrayList arrayList4 = k0Var.f653k;
            ExecutorService executorService2 = bVar.f404a;
            y2.e eVar2 = bVar.f405b;
            str2 = str6;
            qVar = new m0.q(workDatabase, k0Var.f649g, lVar);
            WorkerParameters workerParameters2 = new WorkerParameters();
            workerParameters2.f310a = uuidFromString2;
            workerParameters2.f311b = jVar2;
            new HashSet(arrayList4);
            workerParameters2.f312c = executorService2;
            workerParameters2.f313d = eVar2;
            workerParameters2.f314e = lVar;
            workerParameters2.f315f = lVar3;
            zVarA = lVar3.a(k0Var.f644b, str9, workerParameters2);
            final int i9 = 1;
            zVarA.f517d = true;
            y1.h hVar2 = j0Var.f42e;
            j2.i.b(hVar2);
            y1.f fVarK2 = hVar2.k(r2.t.f2027e);
            j2.i.b(fVarK2);
            d1Var = (d1) ((v0) fVarK2);
            d1Var.I(true, new r2.h0(i9, new i2.l() { // from class: e0.b0
                @Override // i2.l
                public final Object h(Object obj3) {
                    String str15;
                    Throwable th3 = (Throwable) obj3;
                    if (th3 instanceof z) {
                        zVarA.f516c.compareAndSet(-256, ((z) th3).f702d);
                    }
                    if (zS && (str15 = str8) != null) {
                        k0 k0Var2 = k0Var;
                        d0.l lVar4 = k0Var2.f647e.f416m;
                        int iHashCode2 = k0Var2.f643a.hashCode();
                        lVar4.getClass();
                        if (Build.VERSION.SDK_INT >= 29) {
                            b0.a.b(iHashCode2, a.a.I(str15));
                        } else {
                            String strI2 = a.a.I(str15);
                            try {
                                if (a.a.f3g == null) {
                                    a.a.f3g = Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                                }
                                a.a.f3g.invoke(null, Long.valueOf(a.a.f0d), strI2, Integer.valueOf(iHashCode2));
                            } catch (Exception e10) {
                                a.a.r("asyncTraceEnd", e10);
                            }
                        }
                    }
                    return u1.k.f2301a;
                }
            }));
            objN = workDatabase.n(new Callable(k0Var) { // from class: e0.a0

                /* JADX INFO: renamed from: b, reason: collision with root package name */
                public final /* synthetic */ k0 f580b;

                {
                    this.f580b = k0Var;
                }

                @Override // java.util.concurrent.Callable
                public final Object call() {
                    switch (i9) {
                        case 0:
                            k0 k0Var2 = this.f580b;
                            l0.p pVar3 = k0Var2.f643a;
                            d0.k0 k0Var3 = pVar3.f1332b;
                            String str15 = pVar3.f1333c;
                            d0.k0 k0Var4 = d0.k0.f467d;
                            if (k0Var3 != k0Var4) {
                                String str16 = m0.f662a;
                                d0.a0.e().a(str16, str15 + " is not in ENQUEUED state. Nothing more to do");
                                return Boolean.TRUE;
                            }
                            if (pVar3.c() || (pVar3.f1332b == k0Var4 && pVar3.f1341k > 0)) {
                                k0Var2.f648f.getClass();
                                if (System.currentTimeMillis() < pVar3.a()) {
                                    d0.a0.e().a(m0.f662a, "Delaying execution for " + str15 + " because it is being executed before schedule.");
                                    return Boolean.TRUE;
                                }
                            }
                            return Boolean.FALSE;
                        default:
                            k0 k0Var5 = this.f580b;
                            l0.t tVar3 = k0Var5.f651i;
                            String str17 = k0Var5.f645c;
                            boolean z3 = false;
                            if (tVar3.b(str17) == d0.k0.f467d) {
                                tVar3.h(d0.k0.f468e, str17);
                                ((Number) l3.h.W(tVar3.f1361a, false, true, new l0.b(14, str17))).intValue();
                                tVar3.i(-256, str17);
                                z3 = true;
                            }
                            return Boolean.valueOf(z3);
                    }
                }
            });
            j2.i.d(objN, "runInTransaction(...)");
            if (!((Boolean) objN).booleanValue()) {
                return new f0();
            }
            if (d1Var.J()) {
                return new f0();
            }
            n0.a aVar2 = (n0.a) lVar.f187h;
            j2.i.d(aVar2, "getMainThreadExecutor(...)");
            r2.s sVarI2 = r2.x.i(aVar2);
            d0.u uVar2 = new d0.u(k0Var, zVarA, qVar, null, 1);
            j0Var.f641i = 1;
            objW = r2.x.w(sVarI2, uVar2, j0Var);
            e0Var = z1.a.f2781d;
            if (objW != e0Var) {
            }
            return e0Var;
        }
        if (i6 != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            d0.l0.M(objW);
            str2 = str6;
        } catch (CancellationException e10) {
            e4 = e10;
            str3 = str6;
            str4 = m0.f662a;
            str5 = str3 + " was cancelled";
            if (d0.a0.e().f403a <= 4) {
                Log.i(str4, str5, e4);
            }
            throw e4;
        } catch (Throwable th3) {
            th = th3;
            str2 = str6;
            String str15 = m0.f662a;
            d0.a0.e().d(str15, str2 + " failed because it threw an exception/error", th);
            return new d0();
        }
        d0.y yVar = (d0.y) objW;
        j2.i.b(yVar);
        e0Var = new e0(yVar);
        return e0Var;
    }

    public final void b(int i4) {
        l0.t tVar = this.f651i;
        d0.k0 k0Var = d0.k0.f467d;
        String str = this.f645c;
        tVar.h(k0Var, str);
        this.f648f.getClass();
        tVar.g(str, System.currentTimeMillis());
        tVar.f(this.f643a.f1352v, str);
        tVar.e(str, -1L);
        tVar.i(i4, str);
    }

    public final void c() {
        this.f648f.getClass();
        long jCurrentTimeMillis = System.currentTimeMillis();
        l0.t tVar = this.f651i;
        String str = this.f645c;
        tVar.g(str, jCurrentTimeMillis);
        tVar.h(d0.k0.f467d, str);
        tVar.getClass();
        ((Number) l3.h.W(tVar.f1361a, false, true, new l0.b(11, str))).intValue();
        tVar.f(this.f643a.f1352v, str);
        tVar.getClass();
        l3.h.W(tVar.f1361a, false, true, new l0.b(12, str));
        tVar.e(str, -1L);
    }

    public final void d(d0.y yVar) {
        j2.i.e(yVar, "result");
        String str = this.f645c;
        ArrayList arrayListT0 = v1.k.t0(str);
        while (true) {
            boolean zIsEmpty = arrayListT0.isEmpty();
            l0.t tVar = this.f651i;
            if (zIsEmpty) {
                d0.j jVar = ((d0.v) yVar).f512a;
                j2.i.d(jVar, "getOutputData(...)");
                tVar.f(this.f643a.f1352v, str);
                tVar.getClass();
                l3.h.W(tVar.f1361a, false, true, new h0.e(6, jVar, str));
                return;
            }
            String str2 = (String) v1.j.B0(arrayListT0);
            if (tVar.b(str2) != d0.k0.f472i) {
                tVar.h(d0.k0.f470g, str2);
            }
            arrayListT0.addAll(this.f652j.a(str2));
        }
    }
}
