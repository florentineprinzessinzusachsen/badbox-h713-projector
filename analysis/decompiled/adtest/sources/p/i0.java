package p;

import androidx.work.impl.WorkDatabase_Impl;
import d0.l0;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i0 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final String[] f1663l = {"INSERT", "UPDATE", "DELETE"};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WorkDatabase_Impl f1664a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LinkedHashMap f1665b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final LinkedHashMap f1666c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1667d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f1668e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String[] f1670g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e3.h f1671h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final a3.h f1672i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final AtomicBoolean f1673j = new AtomicBoolean(false);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public i2.a f1674k = new h1.a(2);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final LinkedHashMap f1669f = new LinkedHashMap();

    public i0(WorkDatabase_Impl workDatabase_Impl, LinkedHashMap linkedHashMap, LinkedHashMap linkedHashMap2, String[] strArr, boolean z3, g gVar) {
        String lowerCase;
        this.f1664a = workDatabase_Impl;
        this.f1665b = linkedHashMap;
        this.f1666c = linkedHashMap2;
        this.f1667d = z3;
        this.f1668e = gVar;
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i4 = 0; i4 < length; i4++) {
            String str = strArr[i4];
            Locale locale = Locale.ROOT;
            String lowerCase2 = str.toLowerCase(locale);
            j2.i.d(lowerCase2, "toLowerCase(...)");
            this.f1669f.put(lowerCase2, Integer.valueOf(i4));
            String str2 = (String) this.f1665b.get(strArr[i4]);
            if (str2 != null) {
                lowerCase = str2.toLowerCase(locale);
                j2.i.d(lowerCase, "toLowerCase(...)");
            } else {
                lowerCase = null;
            }
            if (lowerCase != null) {
                lowerCase2 = lowerCase;
            }
            strArr2[i4] = lowerCase2;
        }
        this.f1670g = strArr2;
        for (Map.Entry entry : this.f1665b.entrySet()) {
            String str3 = (String) entry.getValue();
            Locale locale2 = Locale.ROOT;
            String lowerCase3 = str3.toLowerCase(locale2);
            j2.i.d(lowerCase3, "toLowerCase(...)");
            if (this.f1669f.containsKey(lowerCase3)) {
                String lowerCase4 = ((String) entry.getKey()).toLowerCase(locale2);
                j2.i.d(lowerCase4, "toLowerCase(...)");
                LinkedHashMap linkedHashMap3 = this.f1669f;
                j2.i.e(linkedHashMap3, "<this>");
                Object obj = linkedHashMap3.get(lowerCase3);
                if (obj == null && !linkedHashMap3.containsKey(lowerCase3)) {
                    throw new NoSuchElementException("Key " + ((Object) lowerCase3) + " is missing in the map.");
                }
                linkedHashMap3.put(lowerCase4, obj);
            }
        }
        this.f1671h = new e3.h(this.f1670g.length);
        this.f1672i = new a3.h(this.f1670g.length);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public static final Object a(i0 i0Var, n nVar, a2.c cVar) {
        z zVar;
        if (cVar instanceof z) {
            zVar = (z) cVar;
            int i4 = zVar.f1735j;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                zVar.f1735j = i4 - Integer.MIN_VALUE;
            } else {
                zVar = new z(i0Var, cVar);
            }
        } else {
            zVar = new z(i0Var, cVar);
        }
        Object objB = zVar.f1733h;
        int i5 = zVar.f1735j;
        z1.a aVar = z1.a.f2781d;
        if (i5 == 0) {
            l0.M(objB);
            d0.h hVar = new d0.h(17);
            zVar.f1732g = nVar;
            zVar.f1735j = 1;
            objB = nVar.b("SELECT * FROM room_table_modification_log WHERE invalidated = 1", hVar, zVar);
            if (objB != aVar) {
            }
            return aVar;
        }
        if (i5 != 1) {
            if (i5 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Set set = (Set) zVar.f1732g;
            l0.M(objB);
            return set;
        }
        nVar = (n) zVar.f1732g;
        l0.M(objB);
        Set set2 = (Set) objB;
        if (!set2.isEmpty()) {
            zVar.f1732g = set2;
            zVar.f1735j = 2;
            if (l0.c.b(nVar, "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1", zVar) == aVar) {
                return aVar;
            }
        }
        return set2;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    public static final Object b(i0 i0Var, a2.c cVar) throws Throwable {
        c0 c0Var;
        c3.b bVar;
        Object objQ;
        Throwable th;
        c3.b bVar2;
        WorkDatabase_Impl workDatabase_Impl = i0Var.f1664a;
        if (cVar instanceof c0) {
            c0Var = (c0) cVar;
            int i4 = c0Var.f1613k;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                c0Var.f1613k = i4 - Integer.MIN_VALUE;
            } else {
                c0Var = new c0(i0Var, cVar);
            }
        } else {
            c0Var = new c0(i0Var, cVar);
        }
        Object obj = c0Var.f1611i;
        int i5 = c0Var.f1613k;
        if (i5 == 0) {
            l0.M(obj);
            bVar = workDatabase_Impl.f1718f;
            boolean zF = bVar.f();
            v1.r rVar = v1.r.f2519d;
            if (!zF) {
                return rVar;
            }
            try {
                if (!i0Var.f1673j.compareAndSet(true, false)) {
                    bVar.l();
                    return rVar;
                }
                if (!((Boolean) i0Var.f1674k.a()).booleanValue()) {
                    bVar.l();
                    return rVar;
                }
                d0 d0Var = new d0(i0Var, null, 1);
                c0Var.f1609g = i0Var;
                c0Var.f1610h = bVar;
                c0Var.f1613k = 1;
                objQ = workDatabase_Impl.q(false, d0Var, c0Var);
                z1.a aVar = z1.a.f2781d;
                if (objQ == aVar) {
                    return aVar;
                }
            } catch (Throwable th2) {
                c3.b bVar3 = bVar;
                th = th2;
                bVar2 = bVar3;
                bVar2.l();
                throw th;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            bVar2 = c0Var.f1610h;
            i0 i0Var2 = c0Var.f1609g;
            try {
                l0.M(obj);
                bVar = bVar2;
                i0Var = i0Var2;
                objQ = obj;
            } catch (Throwable th3) {
                th = th3;
                bVar2.l();
                throw th;
            }
        }
        Set set = (Set) objQ;
        if (!set.isEmpty()) {
            i0Var.f1672i.h(set);
            i0Var.f1668e.h(set);
        }
        bVar.l();
        return set;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0083  */
    /* JADX WARN: Code duplicated, block: B:23:0x0089  */
    /* JADX WARN: Code duplicated, block: B:24:0x008c  */
    /* JADX WARN: Code duplicated, block: B:29:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0070, code lost:
    
        if (l0.c.b(r13, r15, r0) == r4) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00eb, code lost:
    
        if (r15 == r4) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00ed, code lost:
    
        r13 = r13;
        return r4;
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x00eb -> B:28:0x00ee). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object c(p.i0 r12, p.y r13, int r14, a2.c r15) {
        /*
            Method dump skipped, instruction units count: 243
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: p.i0.c(p.i0, p.y, int, a2.c):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0050  */
    /* JADX WARN: Code duplicated, block: B:18:0x008f A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x008d -> B:19:0x0090). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object d(p.i0 r7, p.y r8, int r9, a2.c r10) {
        /*
            r7.getClass()
            boolean r0 = r10 instanceof p.f0
            if (r0 == 0) goto L16
            r0 = r10
            p.f0 r0 = (p.f0) r0
            int r1 = r0.f1639n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.f1639n = r1
            goto L1b
        L16:
            p.f0 r0 = new p.f0
            r0.<init>(r7, r10)
        L1b:
            java.lang.Object r10 = r0.f1637l
            int r1 = r0.f1639n
            r2 = 1
            if (r1 == 0) goto L3c
            if (r1 != r2) goto L34
            int r7 = r0.f1636k
            int r8 = r0.f1635j
            java.lang.String[] r9 = r0.f1634i
            java.lang.String r1 = r0.f1633h
            p.n r3 = r0.f1632g
            d0.l0.M(r10)
            r10 = r9
            r9 = r3
            goto L90
        L34:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3c:
            d0.l0.M(r10)
            java.lang.String[] r7 = r7.f1670g
            r7 = r7[r9]
            java.lang.String[] r9 = p.i0.f1663l
            r10 = 0
            r1 = 3
            r6 = r1
            r1 = r7
            r7 = r6
            r6 = r9
            r9 = r8
            r8 = r10
            r10 = r6
        L4e:
            if (r8 >= r7) goto L92
            r3 = r10[r8]
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "room_table_modification_trigger_"
            r4.<init>(r5)
            r4.append(r1)
            r5 = 95
            r4.append(r5)
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "DROP TRIGGER IF EXISTS `"
            r4.<init>(r5)
            r4.append(r3)
            r3 = 96
            r4.append(r3)
            java.lang.String r3 = r4.toString()
            r0.f1632g = r9
            r0.f1633h = r1
            r0.f1634i = r10
            r0.f1635j = r8
            r0.f1636k = r7
            r0.f1639n = r2
            java.lang.Object r3 = l0.c.b(r9, r3, r0)
            z1.a r4 = z1.a.f2781d
            if (r3 != r4) goto L90
            return r4
        L90:
            int r8 = r8 + r2
            goto L4e
        L92:
            u1.k r7 = u1.k.f2301a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: p.i0.d(p.i0, p.y, int, a2.c):java.lang.Object");
    }

    public final void e(i2.a aVar, i2.a aVar2) {
        j2.i.e(aVar, "onRefreshScheduled");
        j2.i.e(aVar2, "onRefreshCompleted");
        if (this.f1673j.compareAndSet(false, true)) {
            aVar.a();
            w2.c cVar = this.f1664a.f1713a;
            y1.c cVar2 = null;
            if (cVar != null) {
                r2.x.p(cVar, new r2.u(), null, new h0.f(this, aVar2, cVar2, 2), 2);
            } else {
                j2.i.h("coroutineScope");
                throw null;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object f(a2.c cVar) {
        g0 g0Var;
        c3.b bVar;
        if (cVar instanceof g0) {
            g0Var = (g0) cVar;
            int i4 = g0Var.f1644j;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                g0Var.f1644j = i4 - Integer.MIN_VALUE;
            } else {
                g0Var = new g0(this, cVar);
            }
        } else {
            g0Var = new g0(this, cVar);
        }
        Object obj = g0Var.f1642h;
        int i5 = g0Var.f1644j;
        if (i5 == 0) {
            l0.M(obj);
            WorkDatabase_Impl workDatabase_Impl = this.f1664a;
            c3.b bVar2 = workDatabase_Impl.f1718f;
            if (bVar2.f()) {
                try {
                    d0 d0Var = new d0(this, null, 2);
                    g0Var.f1641g = bVar2;
                    g0Var.f1644j = 1;
                    Object objQ = workDatabase_Impl.q(false, d0Var, g0Var);
                    z1.a aVar = z1.a.f2781d;
                    if (objQ == aVar) {
                        return aVar;
                    }
                    bVar = bVar2;
                    bVar.l();
                } catch (Throwable th) {
                    th = th;
                    bVar = bVar2;
                    bVar.l();
                    throw th;
                }
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            bVar = g0Var.f1641g;
            try {
                l0.M(obj);
                bVar.l();
            } catch (Throwable th2) {
                th = th2;
                bVar.l();
                throw th;
            }
        }
        return u1.k.f2301a;
    }
}
