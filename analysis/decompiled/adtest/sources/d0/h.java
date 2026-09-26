package d0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import r2.j1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class h implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f454d;

    public /* synthetic */ h(int i4) {
        this.f454d = i4;
    }

    private final Object c(Object obj) throws Exception {
        w.a aVar = (w.a) obj;
        j2.i.e(aVar, "_connection");
        w.c cVarP = aVar.P("SELECT * FROM workspec WHERE state=0 ORDER BY last_enqueue_time LIMIT ?");
        try {
            cVarP.a(1, 200);
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
                int i4 = iD13;
                int i5 = iD14;
                k0 k0VarO = l3.h.O((int) cVarP.getLong(iD2));
                String strN2 = cVarP.n(iD3);
                String strN3 = cVarP.n(iD4);
                byte[] blob = cVarP.getBlob(iD5);
                j jVar = j.f464b;
                j jVarA = l3.h.A(blob);
                j jVarA2 = l3.h.A(cVarP.getBlob(iD6));
                long j4 = cVarP.getLong(iD7);
                long j5 = cVarP.getLong(iD8);
                long j6 = cVarP.getLong(iD9);
                int i6 = (int) cVarP.getLong(iD10);
                int i7 = iD;
                int i8 = iD2;
                a aVarL = l3.h.L((int) cVarP.getLong(iD11));
                long j7 = cVarP.getLong(iD12);
                long j8 = cVarP.getLong(i4);
                long j9 = cVarP.getLong(i5);
                int i9 = iD15;
                long j10 = cVarP.getLong(i9);
                iD15 = i9;
                int i10 = iD16;
                int i11 = iD3;
                boolean z3 = ((int) cVarP.getLong(i10)) != 0;
                int i12 = iD17;
                int i13 = iD4;
                i0 i0VarN = l3.h.N((int) cVarP.getLong(i12));
                int i14 = iD18;
                int i15 = (int) cVarP.getLong(i14);
                int i16 = iD19;
                int i17 = (int) cVarP.getLong(i16);
                int i18 = iD20;
                long j11 = cVarP.getLong(i18);
                int i19 = iD21;
                int i20 = (int) cVarP.getLong(i19);
                iD21 = i19;
                iD22 = iD22;
                int i21 = (int) cVarP.getLong(iD22);
                int i22 = iD23;
                Boolean boolValueOf = null;
                String strN4 = cVarP.isNull(i22) ? null : cVarP.n(i22);
                int i23 = iD24;
                Integer numValueOf = cVarP.isNull(i23) ? null : Integer.valueOf((int) cVarP.getLong(i23));
                if (numValueOf != null) {
                    boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                }
                Boolean bool = boolValueOf;
                int i24 = iD25;
                b0 b0VarM = l3.h.M((int) cVarP.getLong(i24));
                int i25 = iD26;
                m0.f fVarP0 = l3.h.p0(cVarP.getBlob(i25));
                int i26 = iD27;
                boolean z4 = ((int) cVarP.getLong(i26)) != 0;
                int i27 = iD28;
                boolean z5 = ((int) cVarP.getLong(i27)) != 0;
                int i28 = iD29;
                boolean z6 = ((int) cVarP.getLong(i28)) != 0;
                iD29 = i28;
                int i29 = iD30;
                int i30 = iD31;
                int i31 = iD32;
                iD31 = i30;
                int i32 = iD33;
                arrayList.add(new l0.p(strN, k0VarO, strN2, strN3, jVarA, jVarA2, j4, j5, j6, new e(fVarP0, b0VarM, z4, z5, z6, ((int) cVarP.getLong(i29)) != 0, cVarP.getLong(i30), cVarP.getLong(i31), l3.h.f(cVarP.getBlob(i32))), i6, aVarL, j7, j8, j9, j10, z3, i0VarN, i15, i17, j11, i20, i21, strN4, bool));
                iD28 = i27;
                iD4 = i13;
                iD17 = i12;
                iD18 = i14;
                iD19 = i16;
                iD20 = i18;
                iD23 = i22;
                iD24 = i23;
                iD25 = i24;
                iD26 = i25;
                iD27 = i26;
                iD33 = i32;
                iD32 = i31;
                iD30 = i29;
                iD = i7;
                iD3 = i11;
                iD13 = i4;
                iD14 = i5;
                iD2 = i8;
                iD16 = i10;
            }
            return arrayList;
        } finally {
            cVarP.close();
        }
    }

    private final Object e(Object obj) {
        w.c cVar = (w.c) obj;
        j2.i.e(cVar, "statement");
        w1.i iVar = new w1.i();
        while (cVar.F()) {
            iVar.add(Integer.valueOf((int) cVar.getLong(0)));
        }
        return l0.g(iVar);
    }

    private final Object g(Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        int i4 = iIntValue / 20;
        if (i4 > q1.c.f1780c) {
            q1.c.f1780c = i4;
            l3.h.a0("[NoadUpdateManager] 下载进度: " + iIntValue + "%");
        }
        return u1.k.f2301a;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        Boolean boolValueOf;
        Boolean boolValueOf2;
        Boolean boolValueOf3;
        int i4 = this.f454d;
        u1.k kVar = u1.k.f2301a;
        switch (i4) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                j2.i.e(entry, "<destruct>");
                String str = (String) entry.getKey();
                Object value = entry.getValue();
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" : ");
                if (value instanceof Object[]) {
                    value = Arrays.toString((Object[]) value);
                    j2.i.d(value, "toString(...)");
                }
                sb.append(value);
                return sb.toString();
            case 1:
                i0.d dVar = (i0.d) obj;
                j2.i.e(dVar, "it");
                return dVar.getClass().getSimpleName();
            case 2:
                Byte b4 = (Byte) obj;
                b4.byteValue();
                return String.format("%02X", Arrays.copyOf(new Object[]{b4}, 1));
            case 3:
                Byte b5 = (Byte) obj;
                b5.byteValue();
                return String.format("%02X", Arrays.copyOf(new Object[]{b5}, 1));
            case 4:
                String str2 = (String) obj;
                j2.i.e(str2, "message");
                if (!p2.i.H0(str2)) {
                    l3.h.a0(str2);
                }
                return kVar;
            case 5:
                return new s0.n().e(obj);
            case 6:
                w.a aVar = (w.a) obj;
                j2.i.e(aVar, "_connection");
                w.c cVarP = aVar.P("SELECT DISTINCT work_spec_id FROM SystemIdInfo");
                try {
                    ArrayList arrayList = new ArrayList();
                    while (cVarP.F()) {
                        arrayList.add(cVarP.n(0));
                    }
                    cVarP.close();
                    return arrayList;
                } catch (Throwable th) {
                    cVarP.close();
                    throw th;
                }
            case 7:
                w.a aVar2 = (w.a) obj;
                j2.i.e(aVar2, "_connection");
                w.c cVarP2 = aVar2.P("DELETE FROM WorkProgress");
                try {
                    cVarP2.F();
                    return kVar;
                } finally {
                    cVarP2.close();
                }
            case 8:
                w.a aVar3 = (w.a) obj;
                j2.i.e(aVar3, "_connection");
                w.c cVarP3 = aVar3.P("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at<>-1");
                try {
                    int iD = l3.h.D(cVarP3, "id");
                    int iD2 = l3.h.D(cVarP3, "state");
                    int iD3 = l3.h.D(cVarP3, "worker_class_name");
                    int iD4 = l3.h.D(cVarP3, "input_merger_class_name");
                    int iD5 = l3.h.D(cVarP3, "input");
                    int iD6 = l3.h.D(cVarP3, "output");
                    int iD7 = l3.h.D(cVarP3, "initial_delay");
                    int iD8 = l3.h.D(cVarP3, "interval_duration");
                    int iD9 = l3.h.D(cVarP3, "flex_duration");
                    int iD10 = l3.h.D(cVarP3, "run_attempt_count");
                    int iD11 = l3.h.D(cVarP3, "backoff_policy");
                    int iD12 = l3.h.D(cVarP3, "backoff_delay_duration");
                    int iD13 = l3.h.D(cVarP3, "last_enqueue_time");
                    int iD14 = l3.h.D(cVarP3, "minimum_retention_duration");
                    int iD15 = l3.h.D(cVarP3, "schedule_requested_at");
                    int iD16 = l3.h.D(cVarP3, "run_in_foreground");
                    int iD17 = l3.h.D(cVarP3, "out_of_quota_policy");
                    int iD18 = l3.h.D(cVarP3, "period_count");
                    int iD19 = l3.h.D(cVarP3, "generation");
                    int iD20 = l3.h.D(cVarP3, "next_schedule_time_override");
                    int iD21 = l3.h.D(cVarP3, "next_schedule_time_override_generation");
                    int iD22 = l3.h.D(cVarP3, "stop_reason");
                    int iD23 = l3.h.D(cVarP3, "trace_tag");
                    int iD24 = l3.h.D(cVarP3, "backoff_on_system_interruptions");
                    int iD25 = l3.h.D(cVarP3, "required_network_type");
                    int iD26 = l3.h.D(cVarP3, "required_network_request");
                    int iD27 = l3.h.D(cVarP3, "requires_charging");
                    int iD28 = l3.h.D(cVarP3, "requires_device_idle");
                    int iD29 = l3.h.D(cVarP3, "requires_battery_not_low");
                    int iD30 = l3.h.D(cVarP3, "requires_storage_not_low");
                    int iD31 = l3.h.D(cVarP3, "trigger_content_update_delay");
                    int iD32 = l3.h.D(cVarP3, "trigger_max_content_delay");
                    int iD33 = l3.h.D(cVarP3, "content_uri_triggers");
                    ArrayList arrayList2 = new ArrayList();
                    while (cVarP3.F()) {
                        String strN = cVarP3.n(iD);
                        int i5 = iD14;
                        int i6 = iD13;
                        k0 k0VarO = l3.h.O((int) cVarP3.getLong(iD2));
                        String strN2 = cVarP3.n(iD3);
                        String strN3 = cVarP3.n(iD4);
                        byte[] blob = cVarP3.getBlob(iD5);
                        j jVar = j.f464b;
                        j jVarA = l3.h.A(blob);
                        j jVarA2 = l3.h.A(cVarP3.getBlob(iD6));
                        long j4 = cVarP3.getLong(iD7);
                        long j5 = cVarP3.getLong(iD8);
                        long j6 = cVarP3.getLong(iD9);
                        int i7 = (int) cVarP3.getLong(iD10);
                        int i8 = iD2;
                        int i9 = iD4;
                        a aVarL = l3.h.L((int) cVarP3.getLong(iD11));
                        long j7 = cVarP3.getLong(iD12);
                        long j8 = cVarP3.getLong(i6);
                        long j9 = cVarP3.getLong(i5);
                        int i10 = iD15;
                        long j10 = cVarP3.getLong(i10);
                        iD15 = i10;
                        int i11 = iD16;
                        int i12 = iD3;
                        boolean z3 = ((int) cVarP3.getLong(i11)) != 0;
                        int i13 = iD17;
                        int i14 = iD;
                        i0 i0VarN = l3.h.N((int) cVarP3.getLong(i13));
                        int i15 = iD18;
                        int i16 = (int) cVarP3.getLong(i15);
                        int i17 = iD19;
                        int i18 = (int) cVarP3.getLong(i17);
                        int i19 = iD20;
                        long j11 = cVarP3.getLong(i19);
                        int i20 = iD21;
                        int i21 = (int) cVarP3.getLong(i20);
                        iD21 = i20;
                        iD22 = iD22;
                        int i22 = (int) cVarP3.getLong(iD22);
                        iD23 = iD23;
                        String strN4 = cVarP3.isNull(iD23) ? null : cVarP3.n(iD23);
                        int i23 = iD24;
                        Integer numValueOf = cVarP3.isNull(i23) ? null : Integer.valueOf((int) cVarP3.getLong(i23));
                        if (numValueOf != null) {
                            boolValueOf = Boolean.valueOf(numValueOf.intValue() != 0);
                        } else {
                            boolValueOf = null;
                        }
                        int i24 = iD25;
                        b0 b0VarM = l3.h.M((int) cVarP3.getLong(i24));
                        int i25 = iD26;
                        m0.f fVarP0 = l3.h.p0(cVarP3.getBlob(i25));
                        int i26 = iD27;
                        boolean z4 = ((int) cVarP3.getLong(i26)) != 0;
                        int i27 = iD28;
                        boolean z5 = ((int) cVarP3.getLong(i27)) != 0;
                        int i28 = iD29;
                        boolean z6 = ((int) cVarP3.getLong(i28)) != 0;
                        iD29 = i28;
                        int i29 = iD30;
                        int i30 = iD31;
                        int i31 = iD32;
                        iD31 = i30;
                        int i32 = iD33;
                        arrayList2.add(new l0.p(strN, k0VarO, strN2, strN3, jVarA, jVarA2, j4, j5, j6, new e(fVarP0, b0VarM, z4, z5, z6, ((int) cVarP3.getLong(i29)) != 0, cVarP3.getLong(i30), cVarP3.getLong(i31), l3.h.f(cVarP3.getBlob(i32))), i7, aVarL, j7, j8, j9, j10, z3, i0VarN, i16, i18, j11, i21, i22, strN4, boolValueOf));
                        iD28 = i27;
                        iD = i14;
                        iD17 = i13;
                        iD18 = i15;
                        iD19 = i17;
                        iD20 = i19;
                        iD24 = i23;
                        iD25 = i24;
                        iD26 = i25;
                        iD27 = i26;
                        iD33 = i32;
                        iD32 = i31;
                        iD30 = i29;
                        iD2 = i8;
                        iD3 = i12;
                        iD13 = i6;
                        iD14 = i5;
                        iD4 = i9;
                        iD16 = i11;
                        break;
                    }
                    return arrayList2;
                } finally {
                    cVarP3.close();
                }
            case 9:
                w.a aVar4 = (w.a) obj;
                j2.i.e(aVar4, "_connection");
                w.c cVarP4 = aVar4.P("SELECT * FROM workspec WHERE state=1");
                try {
                    int iD34 = l3.h.D(cVarP4, "id");
                    int iD35 = l3.h.D(cVarP4, "state");
                    int iD36 = l3.h.D(cVarP4, "worker_class_name");
                    int iD37 = l3.h.D(cVarP4, "input_merger_class_name");
                    int iD38 = l3.h.D(cVarP4, "input");
                    int iD39 = l3.h.D(cVarP4, "output");
                    int iD40 = l3.h.D(cVarP4, "initial_delay");
                    int iD41 = l3.h.D(cVarP4, "interval_duration");
                    int iD42 = l3.h.D(cVarP4, "flex_duration");
                    int iD43 = l3.h.D(cVarP4, "run_attempt_count");
                    int iD44 = l3.h.D(cVarP4, "backoff_policy");
                    int iD45 = l3.h.D(cVarP4, "backoff_delay_duration");
                    int iD46 = l3.h.D(cVarP4, "last_enqueue_time");
                    int iD47 = l3.h.D(cVarP4, "minimum_retention_duration");
                    int iD48 = l3.h.D(cVarP4, "schedule_requested_at");
                    int iD49 = l3.h.D(cVarP4, "run_in_foreground");
                    int iD50 = l3.h.D(cVarP4, "out_of_quota_policy");
                    int iD51 = l3.h.D(cVarP4, "period_count");
                    int iD52 = l3.h.D(cVarP4, "generation");
                    int iD53 = l3.h.D(cVarP4, "next_schedule_time_override");
                    int iD54 = l3.h.D(cVarP4, "next_schedule_time_override_generation");
                    int iD55 = l3.h.D(cVarP4, "stop_reason");
                    int iD56 = l3.h.D(cVarP4, "trace_tag");
                    int iD57 = l3.h.D(cVarP4, "backoff_on_system_interruptions");
                    int iD58 = l3.h.D(cVarP4, "required_network_type");
                    int iD59 = l3.h.D(cVarP4, "required_network_request");
                    int iD60 = l3.h.D(cVarP4, "requires_charging");
                    int iD61 = l3.h.D(cVarP4, "requires_device_idle");
                    int iD62 = l3.h.D(cVarP4, "requires_battery_not_low");
                    int iD63 = l3.h.D(cVarP4, "requires_storage_not_low");
                    int iD64 = l3.h.D(cVarP4, "trigger_content_update_delay");
                    int iD65 = l3.h.D(cVarP4, "trigger_max_content_delay");
                    int iD66 = l3.h.D(cVarP4, "content_uri_triggers");
                    ArrayList arrayList3 = new ArrayList();
                    while (cVarP4.F()) {
                        String strN5 = cVarP4.n(iD34);
                        int i33 = iD47;
                        int i34 = iD46;
                        k0 k0VarO2 = l3.h.O((int) cVarP4.getLong(iD35));
                        String strN6 = cVarP4.n(iD36);
                        String strN7 = cVarP4.n(iD37);
                        byte[] blob2 = cVarP4.getBlob(iD38);
                        j jVar2 = j.f464b;
                        j jVarA3 = l3.h.A(blob2);
                        j jVarA4 = l3.h.A(cVarP4.getBlob(iD39));
                        long j12 = cVarP4.getLong(iD40);
                        long j13 = cVarP4.getLong(iD41);
                        long j14 = cVarP4.getLong(iD42);
                        int i35 = (int) cVarP4.getLong(iD43);
                        int i36 = iD35;
                        int i37 = iD37;
                        a aVarL2 = l3.h.L((int) cVarP4.getLong(iD44));
                        long j15 = cVarP4.getLong(iD45);
                        long j16 = cVarP4.getLong(i34);
                        long j17 = cVarP4.getLong(i33);
                        int i38 = iD48;
                        long j18 = cVarP4.getLong(i38);
                        int i39 = iD36;
                        int i40 = iD49;
                        boolean z7 = ((int) cVarP4.getLong(i40)) != 0;
                        int i41 = iD50;
                        int i42 = iD34;
                        i0 i0VarN2 = l3.h.N((int) cVarP4.getLong(i41));
                        iD49 = i40;
                        int i43 = iD51;
                        int i44 = (int) cVarP4.getLong(i43);
                        iD51 = i43;
                        int i45 = iD52;
                        int i46 = (int) cVarP4.getLong(i45);
                        int i47 = iD53;
                        long j19 = cVarP4.getLong(i47);
                        int i48 = iD54;
                        int i49 = (int) cVarP4.getLong(i48);
                        iD54 = i48;
                        iD55 = iD55;
                        int i50 = (int) cVarP4.getLong(iD55);
                        iD56 = iD56;
                        String strN8 = cVarP4.isNull(iD56) ? null : cVarP4.n(iD56);
                        int i51 = iD57;
                        Integer numValueOf2 = cVarP4.isNull(i51) ? null : Integer.valueOf((int) cVarP4.getLong(i51));
                        if (numValueOf2 != null) {
                            boolValueOf2 = Boolean.valueOf(numValueOf2.intValue() != 0);
                        } else {
                            boolValueOf2 = null;
                        }
                        int i52 = iD58;
                        b0 b0VarM2 = l3.h.M((int) cVarP4.getLong(i52));
                        int i53 = iD59;
                        m0.f fVarP1 = l3.h.p0(cVarP4.getBlob(i53));
                        int i54 = iD60;
                        boolean z8 = ((int) cVarP4.getLong(i54)) != 0;
                        int i55 = iD61;
                        boolean z9 = ((int) cVarP4.getLong(i55)) != 0;
                        int i56 = iD62;
                        boolean z10 = ((int) cVarP4.getLong(i56)) != 0;
                        iD62 = i56;
                        int i57 = iD63;
                        int i58 = iD64;
                        int i59 = iD65;
                        iD64 = i58;
                        int i60 = iD66;
                        arrayList3.add(new l0.p(strN5, k0VarO2, strN6, strN7, jVarA3, jVarA4, j12, j13, j14, new e(fVarP1, b0VarM2, z8, z9, z10, ((int) cVarP4.getLong(i57)) != 0, cVarP4.getLong(i58), cVarP4.getLong(i59), l3.h.f(cVarP4.getBlob(i60))), i35, aVarL2, j15, j16, j17, j18, z7, i0VarN2, i44, i46, j19, i49, i50, strN8, boolValueOf2));
                        iD61 = i55;
                        iD34 = i42;
                        iD50 = i41;
                        iD52 = i45;
                        iD53 = i47;
                        iD57 = i51;
                        iD58 = i52;
                        iD59 = i53;
                        iD60 = i54;
                        iD66 = i60;
                        iD65 = i59;
                        iD63 = i57;
                        iD47 = i33;
                        iD35 = i36;
                        iD37 = i37;
                        iD36 = i39;
                        iD48 = i38;
                        iD46 = i34;
                        break;
                    }
                    return arrayList3;
                } finally {
                    cVarP4.close();
                }
            case 10:
                w.a aVar5 = (w.a) obj;
                j2.i.e(aVar5, "_connection");
                w.c cVarP5 = aVar5.P("SELECT * FROM workspec WHERE state=0 AND schedule_requested_at=-1 AND LENGTH(content_uri_triggers)<>0 ORDER BY last_enqueue_time");
                try {
                    int iD67 = l3.h.D(cVarP5, "id");
                    int iD68 = l3.h.D(cVarP5, "state");
                    int iD69 = l3.h.D(cVarP5, "worker_class_name");
                    int iD70 = l3.h.D(cVarP5, "input_merger_class_name");
                    int iD71 = l3.h.D(cVarP5, "input");
                    int iD72 = l3.h.D(cVarP5, "output");
                    int iD73 = l3.h.D(cVarP5, "initial_delay");
                    int iD74 = l3.h.D(cVarP5, "interval_duration");
                    int iD75 = l3.h.D(cVarP5, "flex_duration");
                    int iD76 = l3.h.D(cVarP5, "run_attempt_count");
                    int iD77 = l3.h.D(cVarP5, "backoff_policy");
                    int iD78 = l3.h.D(cVarP5, "backoff_delay_duration");
                    int iD79 = l3.h.D(cVarP5, "last_enqueue_time");
                    int iD80 = l3.h.D(cVarP5, "minimum_retention_duration");
                    int iD81 = l3.h.D(cVarP5, "schedule_requested_at");
                    int iD82 = l3.h.D(cVarP5, "run_in_foreground");
                    int iD83 = l3.h.D(cVarP5, "out_of_quota_policy");
                    int iD84 = l3.h.D(cVarP5, "period_count");
                    int iD85 = l3.h.D(cVarP5, "generation");
                    int iD86 = l3.h.D(cVarP5, "next_schedule_time_override");
                    int iD87 = l3.h.D(cVarP5, "next_schedule_time_override_generation");
                    int iD88 = l3.h.D(cVarP5, "stop_reason");
                    int iD89 = l3.h.D(cVarP5, "trace_tag");
                    int iD90 = l3.h.D(cVarP5, "backoff_on_system_interruptions");
                    int iD91 = l3.h.D(cVarP5, "required_network_type");
                    int iD92 = l3.h.D(cVarP5, "required_network_request");
                    int iD93 = l3.h.D(cVarP5, "requires_charging");
                    int iD94 = l3.h.D(cVarP5, "requires_device_idle");
                    int iD95 = l3.h.D(cVarP5, "requires_battery_not_low");
                    int iD96 = l3.h.D(cVarP5, "requires_storage_not_low");
                    int iD97 = l3.h.D(cVarP5, "trigger_content_update_delay");
                    int iD98 = l3.h.D(cVarP5, "trigger_max_content_delay");
                    int iD99 = l3.h.D(cVarP5, "content_uri_triggers");
                    ArrayList arrayList4 = new ArrayList();
                    while (cVarP5.F()) {
                        String strN9 = cVarP5.n(iD67);
                        int i61 = iD80;
                        int i62 = iD79;
                        k0 k0VarO3 = l3.h.O((int) cVarP5.getLong(iD68));
                        String strN10 = cVarP5.n(iD69);
                        String strN11 = cVarP5.n(iD70);
                        byte[] blob3 = cVarP5.getBlob(iD71);
                        j jVar3 = j.f464b;
                        j jVarA5 = l3.h.A(blob3);
                        j jVarA6 = l3.h.A(cVarP5.getBlob(iD72));
                        long j20 = cVarP5.getLong(iD73);
                        long j21 = cVarP5.getLong(iD74);
                        long j22 = cVarP5.getLong(iD75);
                        int i63 = (int) cVarP5.getLong(iD76);
                        int i64 = iD68;
                        int i65 = iD70;
                        a aVarL3 = l3.h.L((int) cVarP5.getLong(iD77));
                        long j23 = cVarP5.getLong(iD78);
                        long j24 = cVarP5.getLong(i62);
                        long j25 = cVarP5.getLong(i61);
                        int i66 = iD81;
                        long j26 = cVarP5.getLong(i66);
                        int i67 = iD69;
                        int i68 = iD82;
                        boolean z11 = ((int) cVarP5.getLong(i68)) != 0;
                        int i69 = iD83;
                        int i70 = iD67;
                        i0 i0VarN3 = l3.h.N((int) cVarP5.getLong(i69));
                        iD82 = i68;
                        int i71 = iD84;
                        int i72 = (int) cVarP5.getLong(i71);
                        iD84 = i71;
                        int i73 = iD85;
                        int i74 = (int) cVarP5.getLong(i73);
                        int i75 = iD86;
                        long j27 = cVarP5.getLong(i75);
                        int i76 = iD87;
                        int i77 = (int) cVarP5.getLong(i76);
                        iD87 = i76;
                        iD88 = iD88;
                        int i78 = (int) cVarP5.getLong(iD88);
                        iD89 = iD89;
                        String strN12 = cVarP5.isNull(iD89) ? null : cVarP5.n(iD89);
                        int i79 = iD90;
                        Integer numValueOf3 = cVarP5.isNull(i79) ? null : Integer.valueOf((int) cVarP5.getLong(i79));
                        if (numValueOf3 != null) {
                            boolValueOf3 = Boolean.valueOf(numValueOf3.intValue() != 0);
                        } else {
                            boolValueOf3 = null;
                        }
                        int i80 = iD91;
                        b0 b0VarM3 = l3.h.M((int) cVarP5.getLong(i80));
                        int i81 = iD92;
                        m0.f fVarP2 = l3.h.p0(cVarP5.getBlob(i81));
                        int i82 = iD93;
                        boolean z12 = ((int) cVarP5.getLong(i82)) != 0;
                        int i83 = iD94;
                        boolean z13 = ((int) cVarP5.getLong(i83)) != 0;
                        int i84 = iD95;
                        boolean z14 = ((int) cVarP5.getLong(i84)) != 0;
                        iD95 = i84;
                        int i85 = iD96;
                        int i86 = iD97;
                        int i87 = iD98;
                        iD97 = i86;
                        int i88 = iD99;
                        arrayList4.add(new l0.p(strN9, k0VarO3, strN10, strN11, jVarA5, jVarA6, j20, j21, j22, new e(fVarP2, b0VarM3, z12, z13, z14, ((int) cVarP5.getLong(i85)) != 0, cVarP5.getLong(i86), cVarP5.getLong(i87), l3.h.f(cVarP5.getBlob(i88))), i63, aVarL3, j23, j24, j25, j26, z11, i0VarN3, i72, i74, j27, i77, i78, strN12, boolValueOf3));
                        iD94 = i83;
                        iD67 = i70;
                        iD83 = i69;
                        iD85 = i73;
                        iD86 = i75;
                        iD90 = i79;
                        iD91 = i80;
                        iD92 = i81;
                        iD93 = i82;
                        iD99 = i88;
                        iD98 = i87;
                        iD96 = i85;
                        iD80 = i61;
                        iD68 = i64;
                        iD70 = i65;
                        iD69 = i67;
                        iD81 = i66;
                        iD79 = i62;
                        break;
                    }
                    return arrayList4;
                } finally {
                    cVarP5.close();
                }
            case 11:
                w.a aVar6 = (w.a) obj;
                j2.i.e(aVar6, "_connection");
                w.c cVarP6 = aVar6.P("Select COUNT(*) FROM workspec WHERE LENGTH(content_uri_triggers)<>0 AND state NOT IN (2, 3, 5)");
                try {
                    int i89 = cVarP6.F() ? (int) cVarP6.getLong(0) : 0;
                    cVarP6.close();
                    return Integer.valueOf(i89);
                } catch (Throwable th2) {
                    cVarP6.close();
                    throw th2;
                }
            case 12:
                w.a aVar7 = (w.a) obj;
                j2.i.e(aVar7, "_connection");
                w.c cVarP7 = aVar7.P("SELECT COUNT(*) > 0 FROM workspec WHERE state NOT IN (2, 3, 5) LIMIT 1");
                try {
                    return Boolean.valueOf(cVarP7.F() && ((int) cVarP7.getLong(0)) != 0);
                } finally {
                    cVarP7.close();
                }
            case 13:
                return c(obj);
            case 14:
                w.a aVar8 = (w.a) obj;
                j2.i.e(aVar8, "_connection");
                w.c cVarP8 = aVar8.P("UPDATE workspec SET schedule_requested_at=-1 WHERE state NOT IN (2, 3, 5)");
                try {
                    cVarP8.F();
                    return Integer.valueOf(l0.y(aVar8));
                } finally {
                    cVarP8.close();
                }
            case 15:
                j2.i.e((p.a) obj, "config");
                throw new u1.e(0);
            case 16:
                w.c cVar = (w.c) obj;
                j2.i.e(cVar, "it");
                return Boolean.valueOf(cVar.F());
            case 17:
                return e(obj);
            case 18:
                return g(obj);
            case 19:
                y1.f fVar = (y1.f) obj;
                if (fVar instanceof r2.s) {
                    return (r2.s) fVar;
                }
                return null;
            default:
                String str3 = (String) obj;
                if (str3 == null || str3.length() == 0) {
                    h1.c0 c0Var = h1.c0.f1036a;
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    c0Var.getClass();
                    h1.c0.f1057v.e(h1.c0.f1037b[16], Long.valueOf(jCurrentTimeMillis));
                    l3.h.a0("未找到有效随机域名");
                    t1.o.f2158a.a("random_domain_failed", "未找到有效随机域名");
                } else {
                    l3.h.a0("随机域名获取成功：".concat(str3));
                    t1.o.f2158a.a("random_domain_success", "随机域名获取成功：".concat(str3));
                    h1.c0.f1036a.getClass();
                    h1.c0.f1044i.e(h1.c0.f1037b[2], str3);
                    List listD = h1.c0.d();
                    listD.set(listD.size() - 1, h1.c0.i());
                    h1.c0.n(listD);
                    j1 j1Var = s1.b.f2143b;
                    if (j1Var != null) {
                        j1Var.b(null);
                    }
                }
                return kVar;
        }
    }

    public /* synthetic */ h(p.t tVar) {
        this.f454d = 15;
    }
}
