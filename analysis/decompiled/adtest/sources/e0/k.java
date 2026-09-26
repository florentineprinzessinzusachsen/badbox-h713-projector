package e0;

import android.os.Build;
import androidx.work.impl.WorkDatabase;
import d0.b0;
import d0.i0;
import d0.k0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f642a = d0.a0.g("Schedulers");

    public static void a(l0.t tVar, d0.l lVar, List list) {
        if (list.size() > 0) {
            lVar.getClass();
            long jCurrentTimeMillis = System.currentTimeMillis();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                tVar.e(((l0.p) it.next()).f1331a, jCurrentTimeMillis);
            }
        }
    }

    public static void b(d0.b bVar, WorkDatabase workDatabase, List list) {
        List list2;
        if (list == null || list.size() == 0) {
            return;
        }
        l0.t tVarW = workDatabase.w();
        workDatabase.b();
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                list2 = (List) l3.h.W(tVarW.f1361a, true, false, new d0.h(10));
                a(tVarW, bVar.f407d, list2);
            } else {
                list2 = null;
            }
            final int i4 = bVar.f414k;
            List list3 = (List) l3.h.W(tVarW.f1361a, true, false, new i2.l() { // from class: l0.q
                @Override // i2.l
                public final Object h(Object obj) throws Exception {
                    int i5 = i4;
                    w.a aVar = (w.a) obj;
                    j2.i.e(aVar, "_connection");
                    w.c cVarP = aVar.P("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 ORDER BY last_enqueue_time LIMIT (SELECT MAX(?-COUNT(*), 0) FROM workspec WHERE schedule_requested_at<>-1 AND LENGTH(content_uri_triggers)=0 AND state NOT IN (2, 3, 5))");
                    try {
                        cVarP.a(1, i5);
                        int iD = l3.h.D(cVarP, "id");
                        int iD2 = l3.h.D(cVarP, "state");
                        int iD3 = l3.h.D(cVarP, "worker_class_name");
                        int iD4 = l3.h.D(cVarP, "input_merger_class_name");
                        int iD5 = l3.h.D(cVarP, "input");
                        int iD6 = l3.h.D(cVarP, "output");
                        int iD7 = l3.h.D(cVarP, "initial_delay");
                        int iD8 = l3.h.D(cVarP, "interval_duration");
                        int iD9 = l3.h.D(cVarP, "flex_duration");
                        int iD10 = l3.h.D(cVarP, "run_attempt_count");
                        int iD11 = l3.h.D(cVarP, "backoff_policy");
                        int iD12 = l3.h.D(cVarP, "backoff_delay_duration");
                        int iD13 = l3.h.D(cVarP, "last_enqueue_time");
                        int iD14 = l3.h.D(cVarP, "minimum_retention_duration");
                        int iD15 = l3.h.D(cVarP, "schedule_requested_at");
                        int iD16 = l3.h.D(cVarP, "run_in_foreground");
                        int iD17 = l3.h.D(cVarP, "out_of_quota_policy");
                        int iD18 = l3.h.D(cVarP, "period_count");
                        int iD19 = l3.h.D(cVarP, "generation");
                        int iD20 = l3.h.D(cVarP, "next_schedule_time_override");
                        int iD21 = l3.h.D(cVarP, "next_schedule_time_override_generation");
                        int iD22 = l3.h.D(cVarP, "stop_reason");
                        int iD23 = l3.h.D(cVarP, "trace_tag");
                        int iD24 = l3.h.D(cVarP, "backoff_on_system_interruptions");
                        int iD25 = l3.h.D(cVarP, "required_network_type");
                        int iD26 = l3.h.D(cVarP, "required_network_request");
                        int iD27 = l3.h.D(cVarP, "requires_charging");
                        int iD28 = l3.h.D(cVarP, "requires_device_idle");
                        int iD29 = l3.h.D(cVarP, "requires_battery_not_low");
                        int iD30 = l3.h.D(cVarP, "requires_storage_not_low");
                        int iD31 = l3.h.D(cVarP, "trigger_content_update_delay");
                        int iD32 = l3.h.D(cVarP, "trigger_max_content_delay");
                        int iD33 = l3.h.D(cVarP, "content_uri_triggers");
                        ArrayList arrayList = new ArrayList();
                        while (cVarP.F()) {
                            String strN = cVarP.n(iD);
                            int i6 = iD14;
                            ArrayList arrayList2 = arrayList;
                            k0 k0VarO = l3.h.O((int) cVarP.getLong(iD2));
                            String strN2 = cVarP.n(iD3);
                            String strN3 = cVarP.n(iD4);
                            byte[] blob = cVarP.getBlob(iD5);
                            d0.j jVar = d0.j.f464b;
                            d0.j jVarA = l3.h.A(blob);
                            d0.j jVarA2 = l3.h.A(cVarP.getBlob(iD6));
                            long j4 = cVarP.getLong(iD7);
                            long j5 = cVarP.getLong(iD8);
                            long j6 = cVarP.getLong(iD9);
                            int i7 = (int) cVarP.getLong(iD10);
                            d0.a aVarL = l3.h.L((int) cVarP.getLong(iD11));
                            long j7 = cVarP.getLong(iD12);
                            long j8 = cVarP.getLong(iD13);
                            long j9 = cVarP.getLong(i6);
                            int i8 = iD15;
                            long j10 = cVarP.getLong(i8);
                            int i9 = iD;
                            int i10 = iD16;
                            int i11 = iD2;
                            boolean z3 = ((int) cVarP.getLong(i10)) != 0;
                            int i12 = iD17;
                            int i13 = iD3;
                            i0 i0VarN = l3.h.N((int) cVarP.getLong(i12));
                            int i14 = iD18;
                            int i15 = (int) cVarP.getLong(i14);
                            int i16 = iD19;
                            int i17 = (int) cVarP.getLong(i16);
                            long j11 = cVarP.getLong(iD20);
                            int i18 = iD21;
                            int i19 = (int) cVarP.getLong(i18);
                            int i20 = iD22;
                            int i21 = (int) cVarP.getLong(i20);
                            int i22 = iD23;
                            Boolean boolValueOf = null;
                            String strN4 = cVarP.isNull(i22) ? null : cVarP.n(i22);
                            int i23 = iD24;
                            Integer numValueOf = cVarP.isNull(i23) ? null : Integer.valueOf((int) cVarP.getLong(i23));
                            if (numValueOf != null) {
                                boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                            }
                            iD24 = i23;
                            int i24 = iD25;
                            Boolean bool = boolValueOf;
                            b0 b0VarM = l3.h.M((int) cVarP.getLong(i24));
                            int i25 = iD26;
                            m0.f fVarP0 = l3.h.p0(cVarP.getBlob(i25));
                            iD25 = i24;
                            int i26 = iD27;
                            boolean z4 = ((int) cVarP.getLong(i26)) != 0;
                            iD27 = i26;
                            int i27 = iD28;
                            boolean z5 = ((int) cVarP.getLong(i27)) != 0;
                            iD28 = i27;
                            int i28 = iD29;
                            boolean z6 = ((int) cVarP.getLong(i28)) != 0;
                            iD29 = i28;
                            int i29 = iD30;
                            int i30 = iD31;
                            int i31 = iD32;
                            iD31 = i30;
                            int i32 = iD33;
                            p pVar = new p(strN, k0VarO, strN2, strN3, jVarA, jVarA2, j4, j5, j6, new d0.e(fVarP0, b0VarM, z4, z5, z6, ((int) cVarP.getLong(i29)) != 0, cVarP.getLong(i30), cVarP.getLong(i31), l3.h.f(cVarP.getBlob(i32))), i7, aVarL, j7, j8, j9, j10, z3, i0VarN, i15, i17, j11, i19, i21, strN4, bool);
                            iD33 = i32;
                            iD32 = i31;
                            arrayList = arrayList2;
                            arrayList.add(pVar);
                            iD30 = i29;
                            iD = i9;
                            iD14 = i6;
                            iD15 = i8;
                            iD3 = i13;
                            iD17 = i12;
                            iD19 = i16;
                            iD21 = i18;
                            iD22 = i20;
                            iD23 = i22;
                            iD26 = i25;
                            iD2 = i11;
                            iD16 = i10;
                            iD18 = i14;
                        }
                        return arrayList;
                    } finally {
                        cVarP.close();
                    }
                }
            });
            a(tVarW, bVar.f407d, list3);
            if (list2 != null) {
                list3.addAll(list2);
            }
            List list4 = (List) l3.h.W(tVarW.f1361a, true, false, new d0.h(13));
            workDatabase.p();
            workDatabase.l();
            if (list3.size() > 0) {
                l0.p[] pVarArr = (l0.p[]) list3.toArray(new l0.p[list3.size()]);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    h hVar = (h) it.next();
                    if (hVar.e()) {
                        hVar.c(pVarArr);
                    }
                }
            }
            if (list4.size() > 0) {
                l0.p[] pVarArr2 = (l0.p[]) list4.toArray(new l0.p[list4.size()]);
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    h hVar2 = (h) it2.next();
                    if (!hVar2.e()) {
                        hVar2.c(pVarArr2);
                    }
                }
            }
        } catch (Throwable th) {
            workDatabase.l();
            throw th;
        }
    }
}
