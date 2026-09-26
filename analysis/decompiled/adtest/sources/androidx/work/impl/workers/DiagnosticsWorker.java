package androidx.work.impl.workers;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import d0.a0;
import d0.b0;
import d0.i0;
import d0.k0;
import d0.x;
import e0.y;
import i2.l;
import j2.i;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import l0.j;
import l0.m;
import l0.t;
import l0.v;
import l3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class DiagnosticsWorker extends Worker {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DiagnosticsWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        i.e(context, "context");
        i.e(workerParameters, "parameters");
    }

    @Override // androidx.work.Worker
    public final x c() {
        y yVarS = y.S(this.f514a);
        WorkDatabase workDatabase = yVarS.f694c;
        i.d(workDatabase, "getWorkDatabase(...)");
        t tVarW = workDatabase.w();
        m mVarU = workDatabase.u();
        v vVarX = workDatabase.x();
        j jVarT = workDatabase.t();
        yVarS.f693b.f407d.getClass();
        final long jCurrentTimeMillis = System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1L);
        List list = (List) h.W(tVarW.f1361a, true, false, new l() { // from class: l0.s
            @Override // i2.l
            public final Object h(Object obj) throws Exception {
                long j4 = jCurrentTimeMillis;
                w.a aVar = (w.a) obj;
                j2.i.e(aVar, "_connection");
                w.c cVarP = aVar.P("SELECT * FROM workspec WHERE last_enqueue_time >= ? AND state IN (2, 3, 5) ORDER BY last_enqueue_time DESC");
                try {
                    cVarP.a(1, j4);
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
                        ArrayList arrayList2 = arrayList;
                        int i4 = iD;
                        k0 k0VarO = l3.h.O((int) cVarP.getLong(iD2));
                        String strN2 = cVarP.n(iD3);
                        String strN3 = cVarP.n(iD4);
                        byte[] blob = cVarP.getBlob(iD5);
                        d0.j jVar = d0.j.f464b;
                        d0.j jVarA = l3.h.A(blob);
                        d0.j jVarA2 = l3.h.A(cVarP.getBlob(iD6));
                        long j5 = cVarP.getLong(iD7);
                        long j6 = cVarP.getLong(iD8);
                        long j7 = cVarP.getLong(iD9);
                        int i5 = (int) cVarP.getLong(iD10);
                        d0.a aVarL = l3.h.L((int) cVarP.getLong(iD11));
                        long j8 = cVarP.getLong(iD12);
                        long j9 = cVarP.getLong(iD13);
                        long j10 = cVarP.getLong(iD14);
                        int i6 = iD15;
                        long j11 = cVarP.getLong(i6);
                        int i7 = iD14;
                        int i8 = iD16;
                        boolean z3 = ((int) cVarP.getLong(i8)) != 0;
                        int i9 = iD17;
                        i0 i0VarN = l3.h.N((int) cVarP.getLong(i9));
                        int i10 = iD18;
                        int i11 = iD2;
                        int i12 = (int) cVarP.getLong(i10);
                        int i13 = iD19;
                        int i14 = (int) cVarP.getLong(i13);
                        long j12 = cVarP.getLong(iD20);
                        int i15 = iD21;
                        int i16 = (int) cVarP.getLong(i15);
                        iD21 = i15;
                        iD22 = iD22;
                        int i17 = (int) cVarP.getLong(iD22);
                        int i18 = iD23;
                        Boolean boolValueOf = null;
                        String strN4 = cVarP.isNull(i18) ? null : cVarP.n(i18);
                        int i19 = iD24;
                        Integer numValueOf = cVarP.isNull(i19) ? null : Integer.valueOf((int) cVarP.getLong(i19));
                        if (numValueOf != null) {
                            boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                        }
                        Boolean bool = boolValueOf;
                        int i20 = iD25;
                        b0 b0VarM = l3.h.M((int) cVarP.getLong(i20));
                        int i21 = iD26;
                        m0.f fVarP0 = l3.h.p0(cVarP.getBlob(i21));
                        int i22 = iD27;
                        boolean z4 = ((int) cVarP.getLong(i22)) != 0;
                        int i23 = iD28;
                        boolean z5 = ((int) cVarP.getLong(i23)) != 0;
                        int i24 = iD29;
                        boolean z6 = ((int) cVarP.getLong(i24)) != 0;
                        iD29 = i24;
                        int i25 = iD30;
                        int i26 = iD31;
                        int i27 = iD32;
                        iD31 = i26;
                        int i28 = iD33;
                        p pVar = new p(strN, k0VarO, strN2, strN3, jVarA, jVarA2, j5, j6, j7, new d0.e(fVarP0, b0VarM, z4, z5, z6, ((int) cVarP.getLong(i25)) != 0, cVarP.getLong(i26), cVarP.getLong(i27), l3.h.f(cVarP.getBlob(i28))), i5, aVarL, j8, j9, j10, j11, z3, i0VarN, i12, i14, j12, i16, i17, strN4, bool);
                        iD33 = i28;
                        iD32 = i27;
                        arrayList = arrayList2;
                        arrayList.add(pVar);
                        iD30 = i25;
                        iD14 = i7;
                        iD15 = i6;
                        iD16 = i8;
                        iD17 = i9;
                        iD19 = i13;
                        iD23 = i18;
                        iD24 = i19;
                        iD25 = i20;
                        iD26 = i21;
                        iD27 = i22;
                        iD = i4;
                        iD28 = i23;
                        iD2 = i11;
                        iD18 = i10;
                    }
                    return arrayList;
                } finally {
                    cVarP.close();
                }
            }
        });
        List list2 = (List) h.W(tVarW.f1361a, true, false, new d0.h(9));
        List list3 = (List) h.W(tVarW.f1361a, true, false, new d0.h(13));
        if (!list.isEmpty()) {
            a0 a0VarE = a0.e();
            String str = o0.h.f1557a;
            a0VarE.f(str, "Recently completed work:\n\n");
            a0.e().f(str, o0.h.a(mVarU, vVarX, jVarT, list));
        }
        if (!list2.isEmpty()) {
            a0 a0VarE2 = a0.e();
            String str2 = o0.h.f1557a;
            a0VarE2.f(str2, "Running work:\n\n");
            a0.e().f(str2, o0.h.a(mVarU, vVarX, jVarT, list2));
        }
        if (!list3.isEmpty()) {
            a0 a0VarE3 = a0.e();
            String str3 = o0.h.f1557a;
            a0VarE3.f(str3, "Enqueued work:\n\n");
            a0.e().f(str3, o0.h.a(mVarU, vVarX, jVarT, list3));
        }
        return new x();
    }
}
