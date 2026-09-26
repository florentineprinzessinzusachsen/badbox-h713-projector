package l0;

import d0.i0;
import d0.k0;
import d0.l0;
import java.util.ArrayList;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1304d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ String f1305e;

    public /* synthetic */ b(int i4, String str) {
        this.f1304d = i4;
        this.f1305e = str;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x01d9  */
    @Override // i2.l
    public final Object h(Object obj) throws Exception {
        boolean z3;
        boolean z4;
        Long lValueOf;
        p pVar;
        k0 k0VarO;
        int i4 = this.f1304d;
        u1.k kVar = u1.k.f2301a;
        String str = this.f1305e;
        switch (i4) {
            case 0:
                w.a aVar = (w.a) obj;
                j2.i.e(aVar, "_connection");
                w.c cVarP = aVar.P("SELECT COUNT(*)>0 FROM dependency WHERE prerequisite_id=?");
                try {
                    cVarP.o(1, str);
                    if (cVarP.F()) {
                        z3 = false;
                        if (((int) cVarP.getLong(0)) != 0) {
                            z4 = true;
                        }
                        cVarP.close();
                        return Boolean.valueOf(z4);
                    }
                    z3 = false;
                    z4 = z3;
                    cVarP.close();
                    return Boolean.valueOf(z4);
                } catch (Throwable th) {
                    cVarP.close();
                    throw th;
                }
            case 1:
                w.a aVar2 = (w.a) obj;
                j2.i.e(aVar2, "_connection");
                w.c cVarP2 = aVar2.P("SELECT work_spec_id FROM dependency WHERE prerequisite_id=?");
                try {
                    cVarP2.o(1, str);
                    ArrayList arrayList = new ArrayList();
                    while (cVarP2.F()) {
                        arrayList.add(cVarP2.n(0));
                    }
                    cVarP2.close();
                    return arrayList;
                } catch (Throwable th2) {
                    cVarP2.close();
                    throw th2;
                }
            case 2:
                w.a aVar3 = (w.a) obj;
                j2.i.e(aVar3, "_connection");
                w.c cVarP3 = aVar3.P("SELECT COUNT(*)=0 FROM dependency WHERE work_spec_id=? AND prerequisite_id IN (SELECT id FROM workspec WHERE state!=2)");
                try {
                    cVarP3.o(1, str);
                    return Boolean.valueOf(cVarP3.F() && ((int) cVarP3.getLong(0)) != 0);
                } finally {
                    cVarP3.close();
                }
            case 3:
                w.a aVar4 = (w.a) obj;
                j2.i.e(aVar4, "_connection");
                w.c cVarP4 = aVar4.P("SELECT long_value FROM Preference where `key`=?");
                try {
                    cVarP4.o(1, str);
                    if (cVarP4.F() && !cVarP4.isNull(0)) {
                        lValueOf = Long.valueOf(cVarP4.getLong(0));
                        break;
                    } else {
                        lValueOf = null;
                    }
                    return lValueOf;
                } finally {
                    cVarP4.close();
                }
            case 4:
                w.a aVar5 = (w.a) obj;
                j2.i.e(aVar5, "_connection");
                w.c cVarP5 = aVar5.P("DELETE FROM SystemIdInfo where work_spec_id=?");
                try {
                    cVarP5.o(1, str);
                    cVarP5.F();
                    return kVar;
                } finally {
                    cVarP5.close();
                }
            case 5:
                w.a aVar6 = (w.a) obj;
                j2.i.e(aVar6, "_connection");
                w.c cVarP6 = aVar6.P("SELECT name FROM workname WHERE work_spec_id=?");
                try {
                    cVarP6.o(1, str);
                    ArrayList arrayList2 = new ArrayList();
                    while (cVarP6.F()) {
                        arrayList2.add(cVarP6.n(0));
                    }
                    cVarP6.close();
                    return arrayList2;
                } catch (Throwable th3) {
                    cVarP6.close();
                    throw th3;
                }
            case 6:
                w.a aVar7 = (w.a) obj;
                j2.i.e(aVar7, "_connection");
                w.c cVarP7 = aVar7.P("DELETE from WorkProgress where work_spec_id=?");
                try {
                    cVarP7.o(1, str);
                    cVarP7.F();
                    return kVar;
                } finally {
                    cVarP7.close();
                }
            case 7:
                w.a aVar8 = (w.a) obj;
                j2.i.e(aVar8, "_connection");
                w.c cVarP8 = aVar8.P("SELECT * FROM workspec WHERE id=?");
                try {
                    cVarP8.o(1, str);
                    int iD = l3.h.D(cVarP8, "id");
                    int iD2 = l3.h.D(cVarP8, "state");
                    int iD3 = l3.h.D(cVarP8, "worker_class_name");
                    int iD4 = l3.h.D(cVarP8, "input_merger_class_name");
                    int iD5 = l3.h.D(cVarP8, "input");
                    int iD6 = l3.h.D(cVarP8, "output");
                    int iD7 = l3.h.D(cVarP8, "initial_delay");
                    int iD8 = l3.h.D(cVarP8, "interval_duration");
                    int iD9 = l3.h.D(cVarP8, "flex_duration");
                    int iD10 = l3.h.D(cVarP8, "run_attempt_count");
                    int iD11 = l3.h.D(cVarP8, "backoff_policy");
                    int iD12 = l3.h.D(cVarP8, "backoff_delay_duration");
                    int iD13 = l3.h.D(cVarP8, "last_enqueue_time");
                    int iD14 = l3.h.D(cVarP8, "minimum_retention_duration");
                    int iD15 = l3.h.D(cVarP8, "schedule_requested_at");
                    int iD16 = l3.h.D(cVarP8, "run_in_foreground");
                    int iD17 = l3.h.D(cVarP8, "out_of_quota_policy");
                    int iD18 = l3.h.D(cVarP8, "period_count");
                    int iD19 = l3.h.D(cVarP8, "generation");
                    int iD20 = l3.h.D(cVarP8, "next_schedule_time_override");
                    int iD21 = l3.h.D(cVarP8, "next_schedule_time_override_generation");
                    int iD22 = l3.h.D(cVarP8, "stop_reason");
                    int iD23 = l3.h.D(cVarP8, "trace_tag");
                    int iD24 = l3.h.D(cVarP8, "backoff_on_system_interruptions");
                    int iD25 = l3.h.D(cVarP8, "required_network_type");
                    int iD26 = l3.h.D(cVarP8, "required_network_request");
                    int iD27 = l3.h.D(cVarP8, "requires_charging");
                    int iD28 = l3.h.D(cVarP8, "requires_device_idle");
                    int iD29 = l3.h.D(cVarP8, "requires_battery_not_low");
                    int iD30 = l3.h.D(cVarP8, "requires_storage_not_low");
                    int iD31 = l3.h.D(cVarP8, "trigger_content_update_delay");
                    int iD32 = l3.h.D(cVarP8, "trigger_max_content_delay");
                    int iD33 = l3.h.D(cVarP8, "content_uri_triggers");
                    if (cVarP8.F()) {
                        String strN = cVarP8.n(iD);
                        k0 k0VarO2 = l3.h.O((int) cVarP8.getLong(iD2));
                        String strN2 = cVarP8.n(iD3);
                        String strN3 = cVarP8.n(iD4);
                        byte[] blob = cVarP8.getBlob(iD5);
                        d0.j jVar = d0.j.f464b;
                        d0.j jVarA = l3.h.A(blob);
                        d0.j jVarA2 = l3.h.A(cVarP8.getBlob(iD6));
                        long j4 = cVarP8.getLong(iD7);
                        long j5 = cVarP8.getLong(iD8);
                        long j6 = cVarP8.getLong(iD9);
                        int i5 = (int) cVarP8.getLong(iD10);
                        d0.a aVarL = l3.h.L((int) cVarP8.getLong(iD11));
                        long j7 = cVarP8.getLong(iD12);
                        long j8 = cVarP8.getLong(iD13);
                        long j9 = cVarP8.getLong(iD14);
                        long j10 = cVarP8.getLong(iD15);
                        boolean z5 = ((int) cVarP8.getLong(iD16)) != 0;
                        i0 i0VarN = l3.h.N((int) cVarP8.getLong(iD17));
                        int i6 = (int) cVarP8.getLong(iD18);
                        int i7 = (int) cVarP8.getLong(iD19);
                        long j11 = cVarP8.getLong(iD20);
                        int i8 = (int) cVarP8.getLong(iD21);
                        int i9 = (int) cVarP8.getLong(iD22);
                        String strN4 = cVarP8.isNull(iD23) ? null : cVarP8.n(iD23);
                        Integer numValueOf = cVarP8.isNull(iD24) ? null : Integer.valueOf((int) cVarP8.getLong(iD24));
                        pVar = new p(strN, k0VarO2, strN2, strN3, jVarA, jVarA2, j4, j5, j6, new d0.e(l3.h.p0(cVarP8.getBlob(iD26)), l3.h.M((int) cVarP8.getLong(iD25)), ((int) cVarP8.getLong(iD27)) != 0, ((int) cVarP8.getLong(iD28)) != 0, ((int) cVarP8.getLong(iD29)) != 0, ((int) cVarP8.getLong(iD30)) != 0, cVarP8.getLong(iD31), cVarP8.getLong(iD32), l3.h.f(cVarP8.getBlob(iD33))), i5, aVarL, j7, j8, j9, j10, z5, i0VarN, i6, i7, j11, i8, i9, strN4, numValueOf != null ? Boolean.valueOf(numValueOf.intValue() != 0) : null);
                    } else {
                        pVar = null;
                    }
                    return pVar;
                } finally {
                    cVarP8.close();
                }
            case 8:
                w.a aVar9 = (w.a) obj;
                j2.i.e(aVar9, "_connection");
                w.c cVarP9 = aVar9.P("SELECT state FROM workspec WHERE id=?");
                try {
                    cVarP9.o(1, str);
                    if (cVarP9.F()) {
                        Integer numValueOf2 = cVarP9.isNull(0) ? null : Integer.valueOf((int) cVarP9.getLong(0));
                        if (numValueOf2 != null) {
                            k0VarO = l3.h.O(numValueOf2.intValue());
                        } else {
                            k0VarO = null;
                        }
                        break;
                    } else {
                        k0VarO = null;
                    }
                    return k0VarO;
                } finally {
                    cVarP9.close();
                }
            case 9:
                w.a aVar10 = (w.a) obj;
                j2.i.e(aVar10, "_connection");
                w.c cVarP10 = aVar10.P("SELECT id FROM workspec WHERE state NOT IN (2, 3, 5) AND id IN (SELECT work_spec_id FROM workname WHERE name=?)");
                try {
                    cVarP10.o(1, str);
                    ArrayList arrayList3 = new ArrayList();
                    while (cVarP10.F()) {
                        arrayList3.add(cVarP10.n(0));
                    }
                    cVarP10.close();
                    return arrayList3;
                } catch (Throwable th4) {
                    cVarP10.close();
                    throw th4;
                }
            case 10:
                w.a aVar11 = (w.a) obj;
                j2.i.e(aVar11, "_connection");
                w.c cVarP11 = aVar11.P("UPDATE workspec SET stop_reason = CASE WHEN state=1 THEN 1 ELSE -256 END, state=5 WHERE id=?");
                try {
                    cVarP11.o(1, str);
                    cVarP11.F();
                    return Integer.valueOf(l0.y(aVar11));
                } finally {
                    cVarP11.close();
                }
            case 11:
                w.a aVar12 = (w.a) obj;
                j2.i.e(aVar12, "_connection");
                w.c cVarP12 = aVar12.P("UPDATE workspec SET run_attempt_count=0 WHERE id=?");
                try {
                    cVarP12.o(1, str);
                    cVarP12.F();
                    return Integer.valueOf(l0.y(aVar12));
                } finally {
                    cVarP12.close();
                }
            case 12:
                w.a aVar13 = (w.a) obj;
                j2.i.e(aVar13, "_connection");
                w.c cVarP13 = aVar13.P("UPDATE workspec SET period_count=period_count+1 WHERE id=?");
                try {
                    cVarP13.o(1, str);
                    cVarP13.F();
                    return kVar;
                } finally {
                    cVarP13.close();
                }
            case 13:
                w.a aVar14 = (w.a) obj;
                j2.i.e(aVar14, "_connection");
                w.c cVarP14 = aVar14.P("SELECT output FROM workspec WHERE id IN\n             (SELECT prerequisite_id FROM dependency WHERE work_spec_id=?)");
                try {
                    cVarP14.o(1, str);
                    ArrayList arrayList4 = new ArrayList();
                    while (cVarP14.F()) {
                        byte[] blob2 = cVarP14.getBlob(0);
                        d0.j jVar2 = d0.j.f464b;
                        arrayList4.add(l3.h.A(blob2));
                    }
                    cVarP14.close();
                    return arrayList4;
                } catch (Throwable th5) {
                    cVarP14.close();
                    throw th5;
                }
            case 14:
                w.a aVar15 = (w.a) obj;
                j2.i.e(aVar15, "_connection");
                w.c cVarP15 = aVar15.P("UPDATE workspec SET run_attempt_count=run_attempt_count+1 WHERE id=?");
                try {
                    cVarP15.o(1, str);
                    cVarP15.F();
                    return Integer.valueOf(l0.y(aVar15));
                } finally {
                    cVarP15.close();
                }
            case 15:
                w.a aVar16 = (w.a) obj;
                j2.i.e(aVar16, "_connection");
                w.c cVarP16 = aVar16.P("DELETE FROM workspec WHERE id=?");
                try {
                    cVarP16.o(1, str);
                    cVarP16.F();
                    return kVar;
                } finally {
                    cVarP16.close();
                }
            case 16:
                w.a aVar17 = (w.a) obj;
                j2.i.e(aVar17, "_connection");
                w.c cVarP17 = aVar17.P("SELECT id, state FROM workspec WHERE id IN (SELECT work_spec_id FROM workname WHERE name=?)");
                try {
                    cVarP17.o(1, str);
                    ArrayList arrayList5 = new ArrayList();
                    while (cVarP17.F()) {
                        String strN5 = cVarP17.n(0);
                        k0 k0VarO3 = l3.h.O((int) cVarP17.getLong(1));
                        j2.i.e(strN5, "id");
                        o oVar = new o();
                        oVar.f1328a = strN5;
                        oVar.f1329b = k0VarO3;
                        arrayList5.add(oVar);
                    }
                    cVarP17.close();
                    return arrayList5;
                } catch (Throwable th6) {
                    cVarP17.close();
                    throw th6;
                }
            case 17:
                w.a aVar18 = (w.a) obj;
                j2.i.e(aVar18, "_connection");
                w.c cVarP18 = aVar18.P("SELECT DISTINCT tag FROM worktag WHERE work_spec_id=?");
                try {
                    cVarP18.o(1, str);
                    ArrayList arrayList6 = new ArrayList();
                    while (cVarP18.F()) {
                        arrayList6.add(cVarP18.n(0));
                    }
                    cVarP18.close();
                    return arrayList6;
                } catch (Throwable th7) {
                    cVarP18.close();
                    throw th7;
                }
            default:
                String str2 = (String) obj;
                j2.i.e(str2, "it");
                if (p2.i.H0(str2)) {
                    return str2.length() < str.length() ? str : str2;
                }
                return str + str2;
        }
    }
}
