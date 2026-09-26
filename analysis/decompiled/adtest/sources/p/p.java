package p;

import android.content.Context;
import android.content.Intent;
import d0.l0;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f1683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final a f1685c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v f1686d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f1687e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final r.b f1688f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public x.a f1689g;

    public p(a aVar, d0.h hVar) {
        this.f1685c = aVar;
        this.f1686d = new o(-1, "", "");
        List list = aVar.f1581e;
        v1.p pVar = v1.p.f2517d;
        this.f1687e = list == null ? pVar : list;
        f1.c cVar = new f1.c(1, this);
        list = list == null ? pVar : list;
        e0.a aVar2 = new e0.a(cVar);
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(aVar2);
        Context context = aVar.f1577a;
        String str = aVar.f1578b;
        x.c cVar2 = aVar.f1579c;
        d0.i iVar = aVar.f1580d;
        boolean z3 = aVar.f1582f;
        r rVar = aVar.f1583g;
        Executor executor = aVar.f1584h;
        Executor executor2 = aVar.f1585i;
        Intent intent = aVar.f1586j;
        boolean z4 = aVar.f1587k;
        boolean z5 = aVar.f1588l;
        Set set = aVar.f1589m;
        String str2 = aVar.f1590n;
        File file = aVar.f1591o;
        Callable callable = aVar.f1592p;
        List list2 = aVar.f1593q;
        List list3 = aVar.f1594r;
        boolean z6 = aVar.f1595s;
        w.b bVar = aVar.f1596t;
        y1.h hVar2 = aVar.f1597u;
        j2.i.e(context, "context");
        j2.i.e(iVar, "migrationContainer");
        j2.i.e(executor, "queryExecutor");
        j2.i.e(executor2, "transactionExecutor");
        j2.i.e(list2, "typeConverters");
        j2.i.e(list3, "autoMigrationSpecs");
        hVar.h(new a(context, str, cVar2, iVar, arrayList, z3, rVar, executor, executor2, intent, z4, z5, set, str2, file, callable, list2, list3, z6, bVar, hVar2));
        throw null;
    }

    public static final void a(p pVar, w.a aVar) throws Throwable {
        Object objL;
        v vVar = pVar.f1686d;
        a aVar2 = pVar.f1685c;
        r rVar = aVar2.f1583g;
        r rVar2 = r.f1711f;
        if (rVar == rVar2) {
            l3.h.y(aVar, "PRAGMA journal_mode = WAL");
        } else {
            l3.h.y(aVar, "PRAGMA journal_mode = TRUNCATE");
        }
        if (aVar2.f1583g == rVar2) {
            l3.h.y(aVar, "PRAGMA synchronous = NORMAL");
        } else {
            l3.h.y(aVar, "PRAGMA synchronous = FULL");
        }
        b(aVar);
        w.c cVarP = aVar.P("PRAGMA user_version");
        try {
            cVarP.F();
            int i4 = (int) cVarP.getLong(0);
            l3.h.k(cVarP, null);
            int i5 = vVar.f1725a;
            if (i4 != i5) {
                l3.h.y(aVar, "BEGIN EXCLUSIVE TRANSACTION");
                try {
                    if (i4 == 0) {
                        pVar.d(aVar);
                    } else {
                        pVar.e(aVar, i4, i5);
                    }
                    l3.h.y(aVar, "PRAGMA user_version = " + i5);
                    objL = u1.k.f2301a;
                } catch (Throwable th) {
                    objL = l0.l(th);
                }
                if (!(objL instanceof u1.g)) {
                    l3.h.y(aVar, "END TRANSACTION");
                }
                Throwable thA = u1.h.a(objL);
                if (thA != null) {
                    l3.h.y(aVar, "ROLLBACK TRANSACTION");
                    throw thA;
                }
            }
            pVar.f(aVar);
        } catch (Throwable th2) {
            try {
                throw th2;
            } catch (Throwable th3) {
                l3.h.k(cVarP, th2);
                throw th3;
            }
        }
    }

    public static void b(w.a aVar) {
        w.c cVarP = aVar.P("PRAGMA busy_timeout");
        try {
            cVarP.F();
            long j4 = cVarP.getLong(0);
            l3.h.k(cVarP, null);
            if (j4 < 3000) {
                l3.h.y(aVar, "PRAGMA busy_timeout = 3000");
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                l3.h.k(cVarP, th);
                throw th2;
            }
        }
    }

    public final x.d c() {
        a3.h hVar;
        r.b bVar = this.f1688f;
        s.b bVar2 = bVar instanceof s.b ? (s.b) bVar : null;
        if (bVar2 == null || (hVar = bVar2.f2067d) == null) {
            return null;
        }
        return (x.d) hVar.f149e;
    }

    public final void d(w.a aVar) {
        j2.i.e(aVar, "connection");
        w.c cVarP = aVar.P("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z3 = false;
            if (cVarP.F() && cVarP.getLong(0) == 0) {
                z3 = true;
            }
            l3.h.k(cVarP, null);
            v vVar = this.f1686d;
            vVar.a(aVar);
            if (!z3) {
                u uVarG = vVar.g(aVar);
                if (!uVarG.f1723a) {
                    throw new IllegalStateException(("Pre-packaged database has an invalid schema: " + uVarG.f1724b).toString());
                }
            }
            g(aVar);
            vVar.c(aVar);
            Iterator it = this.f1687e.iterator();
            while (it.hasNext()) {
                ((e0.a) it.next()).getClass();
                if (aVar instanceof s.a) {
                    j2.i.e(((s.a) aVar).f2066d, "db");
                }
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                l3.h.k(cVarP, th);
                throw th2;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:127:0x009b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:128:0x009b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:131:? A[LOOP:4: B:9:0x0024->B:131:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:132:0x0098 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:133:0x0083 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:137:0x007c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:13:0x002b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:14:0x002d  */
    /* JADX WARN: Code duplicated, block: B:16:0x0039  */
    /* JADX WARN: Code duplicated, block: B:17:0x003b  */
    /* JADX WARN: Code duplicated, block: B:18:0x0045  */
    /* JADX WARN: Code duplicated, block: B:21:0x0052  */
    /* JADX WARN: Code duplicated, block: B:24:0x005e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0070  */
    public final void e(w.a aVar, int i4, int i5) {
        Iterable iterable;
        TreeMap treeMap;
        u1.f fVar;
        Iterator it;
        boolean z3;
        int iIntValue;
        TreeMap treeMap2;
        j2.i.e(aVar, "connection");
        a aVar2 = this.f1685c;
        d0.i iVar = aVar2.f1580d;
        j2.i.e(iVar, "<this>");
        LinkedHashMap linkedHashMap = iVar.f460a;
        if (i4 == i5) {
            iterable = v1.p.f2517d;
        } else {
            boolean z4 = i5 > i4;
            ArrayList arrayList = new ArrayList();
            int i6 = i4;
            while (true) {
                if (z4) {
                    if (i6 < i5) {
                        if (z4) {
                            treeMap2 = (TreeMap) linkedHashMap.get(Integer.valueOf(i6));
                            if (treeMap2 == null) {
                                fVar = null;
                            } else {
                                fVar = new u1.f(treeMap2, treeMap2.descendingKeySet());
                            }
                        } else {
                            treeMap = (TreeMap) linkedHashMap.get(Integer.valueOf(i6));
                            if (treeMap == null) {
                                fVar = null;
                            } else {
                                fVar = new u1.f(treeMap, treeMap.keySet());
                            }
                        }
                        if (fVar == null) {
                            Map map = (Map) fVar.f2294d;
                            it = ((Iterable) fVar.f2295e).iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    z3 = false;
                                    break;
                                }
                                iIntValue = ((Number) it.next()).intValue();
                                if (!z4) {
                                    if (i5 <= iIntValue && iIntValue < i6) {
                                        Object obj = map.get(Integer.valueOf(iIntValue));
                                        j2.i.b(obj);
                                        arrayList.add(obj);
                                        z3 = true;
                                        i6 = iIntValue;
                                        break;
                                        break;
                                    }
                                } else if (i6 + 1 <= iIntValue && iIntValue <= i5) {
                                    Object obj2 = map.get(Integer.valueOf(iIntValue));
                                    j2.i.b(obj2);
                                    arrayList.add(obj2);
                                    z3 = true;
                                    i6 = iIntValue;
                                    break;
                                }
                            }
                            if (!z3) {
                            }
                        }
                        iterable = null;
                    } else {
                        iterable = arrayList;
                    }
                } else if (i6 > i5) {
                    if (z4) {
                        treeMap2 = (TreeMap) linkedHashMap.get(Integer.valueOf(i6));
                        if (treeMap2 == null) {
                            fVar = null;
                        } else {
                            fVar = new u1.f(treeMap2, treeMap2.descendingKeySet());
                        }
                    } else {
                        treeMap = (TreeMap) linkedHashMap.get(Integer.valueOf(i6));
                        if (treeMap == null) {
                            fVar = null;
                        } else {
                            fVar = new u1.f(treeMap, treeMap.keySet());
                        }
                    }
                    if (fVar == null) {
                        Map map2 = (Map) fVar.f2294d;
                        it = ((Iterable) fVar.f2295e).iterator();
                        while (true) {
                            if (it.hasNext()) {
                                z3 = false;
                                break;
                                break;
                            }
                            iIntValue = ((Number) it.next()).intValue();
                            if (!z4) {
                                if (i6 + 1 <= iIntValue) {
                                    continue;
                                }
                            } else if (i5 <= iIntValue) {
                                continue;
                            }
                        }
                        if (!z3) {
                        }
                    }
                    iterable = null;
                } else {
                    iterable = arrayList;
                }
            }
        }
        v vVar = this.f1686d;
        if (iterable != null) {
            vVar.f(aVar);
            Iterator it2 = iterable.iterator();
            while (it2.hasNext()) {
                ((t.a) it2.next()).a(aVar);
            }
            u uVarG = vVar.g(aVar);
            if (uVarG.f1723a) {
                vVar.e(aVar);
                g(aVar);
                return;
            } else {
                throw new IllegalStateException(("Migration didn't properly handle: " + uVarG.f1724b).toString());
            }
        }
        j2.i.e(aVar2, "<this>");
        boolean z5 = false;
        if (i4 <= i5 || !aVar2.f1588l) {
            Set set = aVar2.f1589m;
            if (aVar2.f1587k && (set == null || !set.contains(Integer.valueOf(i4)))) {
                z5 = true;
            }
        }
        if (z5) {
            throw new IllegalStateException(("A migration from " + i4 + " to " + i5 + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* functions.").toString());
        }
        if (aVar2.f1595s) {
            w.c cVarP = aVar.P("SELECT name, type FROM sqlite_master WHERE type = 'table' OR type = 'view'");
            try {
                w1.c cVar = new w1.c(10);
                while (cVarP.F()) {
                    String strN = cVarP.n(0);
                    if (!p2.p.z0(strN, "sqlite_", false) && !strN.equals("android_metadata")) {
                        cVar.add(new u1.f(strN, Boolean.valueOf(j2.i.a(cVarP.n(1), "view"))));
                    }
                }
                w1.c cVarD = l3.h.d(cVar);
                l3.h.k(cVarP, null);
                ListIterator listIterator = cVarD.listIterator(0);
                while (true) {
                    w1.a aVar3 = (w1.a) listIterator;
                    if (!aVar3.hasNext()) {
                        break;
                    }
                    u1.f fVar2 = (u1.f) aVar3.next();
                    String str = (String) fVar2.f2294d;
                    if (((Boolean) fVar2.f2295e).booleanValue()) {
                        l3.h.y(aVar, "DROP VIEW IF EXISTS " + str);
                    } else {
                        l3.h.y(aVar, "DROP TABLE IF EXISTS " + str);
                    }
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    l3.h.k(cVarP, th);
                    throw th2;
                }
            }
        } else {
            vVar.b(aVar);
        }
        Iterator it3 = this.f1687e.iterator();
        while (it3.hasNext()) {
            ((e0.a) it3.next()).getClass();
            if (aVar instanceof s.a) {
                j2.i.e(((s.a) aVar).f2066d, "db");
            }
        }
        vVar.a(aVar);
    }

    public final void f(w.a aVar) throws Throwable {
        Object objL;
        j2.i.e(aVar, "connection");
        w.c cVarP = aVar.P("SELECT 1 FROM sqlite_master WHERE type = 'table' AND name = 'room_master_table'");
        try {
            boolean z3 = cVarP.F() && cVarP.getLong(0) != 0;
            l3.h.k(cVarP, null);
            v vVar = this.f1686d;
            if (z3) {
                w.c cVarP2 = aVar.P("SELECT identity_hash FROM room_master_table WHERE id = 42 LIMIT 1");
                try {
                    String strN = cVarP2.F() ? cVarP2.n(0) : null;
                    l3.h.k(cVarP2, null);
                    if (!vVar.f1726b.equals(strN) && !vVar.f1727c.equals(strN)) {
                        throw new IllegalStateException(("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: " + vVar.f1726b + ", found: " + strN).toString());
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        l3.h.k(cVarP2, th);
                        throw th2;
                    }
                }
            } else {
                l3.h.y(aVar, "BEGIN EXCLUSIVE TRANSACTION");
                try {
                    u uVarG = vVar.g(aVar);
                    if (!uVarG.f1723a) {
                        throw new IllegalStateException(("Pre-packaged database has an invalid schema: " + uVarG.f1724b).toString());
                    }
                    vVar.e(aVar);
                    g(aVar);
                    objL = u1.k.f2301a;
                    if (!(objL instanceof u1.g)) {
                        l3.h.y(aVar, "END TRANSACTION");
                    }
                    Throwable thA = u1.h.a(objL);
                    if (thA != null) {
                        l3.h.y(aVar, "ROLLBACK TRANSACTION");
                        throw thA;
                    }
                } catch (Throwable th3) {
                    objL = l0.l(th3);
                }
            }
            vVar.d(aVar);
            for (e0.a aVar2 : this.f1687e) {
                aVar2.getClass();
                if (aVar instanceof s.a) {
                    x.a aVar3 = ((s.a) aVar).f2066d;
                    switch (aVar2.f577a) {
                        case 0:
                            j2.i.e(aVar3, "db");
                            aVar3.i();
                            try {
                                StringBuilder sb = new StringBuilder("DELETE FROM workspec WHERE state IN (2, 3, 5) AND (last_enqueue_time + minimum_retention_duration) < ");
                                ((d0.l) aVar2.f578b).getClass();
                                sb.append(System.currentTimeMillis() - e0.s.f681a);
                                sb.append(" AND (SELECT COUNT(*)=0 FROM dependency WHERE     prerequisite_id=id AND     work_spec_id NOT IN         (SELECT id FROM workspec WHERE state IN (2, 3, 5)))");
                                aVar3.s(sb.toString());
                                aVar3.y();
                                aVar3.h();
                            } catch (Throwable th4) {
                                aVar3.h();
                                throw th4;
                            }
                            break;
                        default:
                            j2.i.e(aVar3, "db");
                            ((f1.c) aVar2.f578b).h(aVar3);
                            break;
                    }
                }
            }
            this.f1683a = true;
        } catch (Throwable th5) {
            try {
                throw th5;
            } catch (Throwable th6) {
                l3.h.k(cVarP, th5);
                throw th6;
            }
        }
    }

    public final void g(w.a aVar) {
        l3.h.y(aVar, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        String str = this.f1686d.f1726b;
        j2.i.e(str, "hash");
        l3.h.y(aVar, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '" + str + "')");
    }

    public p(a aVar, v vVar) {
        int i4;
        r.e eVar;
        r rVar = aVar.f1583g;
        x.c cVar = aVar.f1579c;
        String str = aVar.f1578b;
        this.f1685c = aVar;
        this.f1686d = vVar;
        List list = aVar.f1581e;
        this.f1687e = list == null ? v1.p.f2517d : list;
        w.b bVar = aVar.f1596t;
        if (bVar != null) {
            if (str == null) {
                eVar = new r.e(new c3.b(this, bVar));
            } else {
                c3.b bVar2 = new c3.b(this, bVar);
                int iOrdinal = rVar.ordinal();
                if (iOrdinal == 1) {
                    i4 = 1;
                } else {
                    if (iOrdinal != 2) {
                        throw new IllegalStateException(("Can't get max number of reader for journal mode '" + rVar + '\'').toString());
                    }
                    i4 = 4;
                }
                int iOrdinal2 = rVar.ordinal();
                if (iOrdinal2 != 1 && iOrdinal2 != 2) {
                    throw new IllegalStateException(("Can't get max number of writers for journal mode '" + rVar + '\'').toString());
                }
                eVar = new r.e(bVar2, str, i4);
            }
            this.f1688f = eVar;
        } else if (cVar != null) {
            Context context = aVar.f1577a;
            j2.i.e(context, "context");
            this.f1688f = new s.b(new a3.h(cVar.d(new x.b(context, str, new e3.w(this, vVar.f1725a), false, false))));
        } else {
            throw new IllegalArgumentException("SQLiteManager was constructed with both null driver and open helper factory!");
        }
        boolean z3 = rVar == r.f1711f;
        x.d dVarC = c();
        if (dVarC != null) {
            dVarC.setWriteAheadLoggingEnabled(z3);
        }
    }
}
