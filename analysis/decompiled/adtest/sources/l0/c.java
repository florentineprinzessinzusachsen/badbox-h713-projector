package l0;

import android.net.NetworkRequest;
import android.os.Build;
import d0.b0;
import d0.i0;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f1306a;

    public /* synthetic */ c(int i4) {
        this.f1306a = i4;
    }

    public static final Object b(p.n nVar, String str, a2.c cVar) {
        Object objB = nVar.b(str, new d0.h(16), cVar);
        return objB == z1.a.f2781d ? objB : u1.k.f2301a;
    }

    public final void a(w.c cVar, Object obj) throws IOException {
        int i4;
        int i5;
        int[] iArrF0;
        int[] iArrF1;
        byte[] byteArray;
        byte[] byteArray2;
        int i6 = 3;
        switch (this.f1306a) {
            case 0:
                a aVar = (a) obj;
                j2.i.e(cVar, "statement");
                j2.i.e(aVar, "entity");
                cVar.o(1, aVar.f1302a);
                cVar.o(2, aVar.f1303b);
                return;
            case 1:
                e eVar = (e) obj;
                j2.i.e(cVar, "statement");
                j2.i.e(eVar, "entity");
                cVar.o(1, eVar.f1309a);
                cVar.a(2, eVar.f1310b.longValue());
                return;
            case 2:
                h hVar = (h) obj;
                j2.i.e(cVar, "statement");
                j2.i.e(hVar, "entity");
                cVar.o(1, hVar.f1313a);
                cVar.a(2, hVar.f1314b);
                cVar.a(3, hVar.f1315c);
                return;
            case 3:
                l lVar = (l) obj;
                j2.i.e(cVar, "statement");
                j2.i.e(lVar, "entity");
                cVar.o(1, lVar.f1323a);
                cVar.o(2, lVar.f1324b);
                return;
            case 4:
                p pVar = (p) obj;
                j2.i.e(cVar, "statement");
                j2.i.e(pVar, "entity");
                cVar.o(1, pVar.f1331a);
                cVar.a(2, l3.h.k0(pVar.f1332b));
                cVar.o(3, pVar.f1333c);
                cVar.o(4, pVar.f1334d);
                d0.j jVar = d0.j.f464b;
                cVar.d(5, l3.h.n0(pVar.f1335e));
                cVar.d(6, l3.h.n0(pVar.f1336f));
                cVar.a(7, pVar.f1337g);
                cVar.a(8, pVar.f1338h);
                cVar.a(9, pVar.f1339i);
                cVar.a(10, pVar.f1341k);
                d0.a aVar2 = pVar.f1342l;
                j2.i.e(aVar2, "backoffPolicy");
                int iOrdinal = aVar2.ordinal();
                if (iOrdinal == 0) {
                    i4 = 0;
                } else {
                    if (iOrdinal != 1) {
                        throw new a0.c();
                    }
                    i4 = 1;
                }
                cVar.a(11, i4);
                cVar.a(12, pVar.f1343m);
                cVar.a(13, pVar.f1344n);
                cVar.a(14, pVar.f1345o);
                cVar.a(15, pVar.f1346p);
                cVar.a(16, pVar.f1347q ? 1L : 0L);
                i0 i0Var = pVar.f1348r;
                j2.i.e(i0Var, "policy");
                int iOrdinal2 = i0Var.ordinal();
                if (iOrdinal2 == 0) {
                    i5 = 0;
                } else {
                    if (iOrdinal2 != 1) {
                        throw new a0.c();
                    }
                    i5 = 1;
                }
                cVar.a(17, i5);
                cVar.a(18, pVar.f1349s);
                cVar.a(19, pVar.f1350t);
                cVar.a(20, pVar.f1351u);
                cVar.a(21, pVar.f1352v);
                cVar.a(22, pVar.f1353w);
                String str = pVar.f1354x;
                if (str == null) {
                    cVar.e(23);
                } else {
                    cVar.o(23, str);
                }
                Boolean bool = pVar.f1355y;
                Integer numValueOf = bool != null ? Integer.valueOf(bool.booleanValue() ? 1 : 0) : null;
                if (numValueOf == null) {
                    cVar.e(24);
                } else {
                    cVar.a(24, numValueOf.intValue());
                }
                d0.e eVar2 = pVar.f1340j;
                b0 b0Var = eVar2.f433a;
                j2.i.e(b0Var, "networkType");
                int iOrdinal3 = b0Var.ordinal();
                if (iOrdinal3 == 0) {
                    i6 = 0;
                } else if (iOrdinal3 == 1) {
                    i6 = 1;
                } else if (iOrdinal3 == 2) {
                    i6 = 2;
                } else if (iOrdinal3 != 3) {
                    if (iOrdinal3 == 4) {
                        i6 = 4;
                    } else {
                        if (Build.VERSION.SDK_INT < 30 || b0Var != b0.f422i) {
                            throw new IllegalArgumentException("Could not convert " + b0Var + " to int");
                        }
                        i6 = 5;
                    }
                }
                cVar.a(25, i6);
                m0.f fVar = eVar2.f434b;
                j2.i.e(fVar, "requestCompat");
                int i7 = Build.VERSION.SDK_INT;
                if (i7 < 28) {
                    byteArray = new byte[0];
                } else {
                    NetworkRequest networkRequest = (NetworkRequest) fVar.f1406a;
                    if (networkRequest == null) {
                        byteArray = new byte[0];
                    } else {
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        try {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                            try {
                                if (i7 >= 31) {
                                    iArrF0 = networkRequest.getTransportTypes();
                                    j2.i.d(iArrF0, "getTransportTypes(...)");
                                } else {
                                    int[] iArr = {2, 0, 3, 6, 10, 9, 8, 4, 1, 5};
                                    ArrayList arrayList = new ArrayList();
                                    for (int i8 = 0; i8 < 10; i8++) {
                                        int i9 = iArr[i8];
                                        if (networkRequest.hasTransport(i9)) {
                                            arrayList.add(Integer.valueOf(i9));
                                        }
                                    }
                                    iArrF0 = v1.j.F0(arrayList);
                                }
                                if (Build.VERSION.SDK_INT >= 31) {
                                    iArrF1 = networkRequest.getCapabilities();
                                    j2.i.d(iArrF1, "getCapabilities(...)");
                                } else {
                                    int[] iArr2 = {17, 5, 2, 10, 29, 19, 3, 32, 7, 4, 12, 36, 23, 0, 33, 20, 11, 13, 18, 21, 15, 35, 34, 8, 1, 25, 14, 16, 6, 9};
                                    ArrayList arrayList2 = new ArrayList();
                                    for (int i10 = 0; i10 < 30; i10++) {
                                        int i11 = iArr2[i10];
                                        if (networkRequest.hasCapability(i11)) {
                                            arrayList2.add(Integer.valueOf(i11));
                                        }
                                    }
                                    iArrF1 = v1.j.F0(arrayList2);
                                }
                                objectOutputStream.writeInt(iArrF0.length);
                                for (int i12 : iArrF0) {
                                    objectOutputStream.writeInt(i12);
                                }
                                objectOutputStream.writeInt(iArrF1.length);
                                for (int i13 : iArrF1) {
                                    objectOutputStream.writeInt(i13);
                                }
                                objectOutputStream.close();
                                byteArrayOutputStream.close();
                                byteArray = byteArrayOutputStream.toByteArray();
                                j2.i.d(byteArray, "toByteArray(...)");
                            } catch (Throwable th) {
                                try {
                                    throw th;
                                } catch (Throwable th2) {
                                    l3.h.j(objectOutputStream, th);
                                    throw th2;
                                }
                            }
                        } catch (Throwable th3) {
                            try {
                                throw th3;
                            } catch (Throwable th4) {
                                l3.h.j(byteArrayOutputStream, th3);
                                throw th4;
                            }
                        }
                    }
                }
                cVar.d(26, byteArray);
                cVar.a(27, eVar2.f435c ? 1L : 0L);
                cVar.a(28, eVar2.f436d ? 1L : 0L);
                cVar.a(29, eVar2.f437e ? 1L : 0L);
                cVar.a(30, eVar2.f438f ? 1L : 0L);
                cVar.a(31, eVar2.f439g);
                cVar.a(32, eVar2.f440h);
                Set<d0.d> set = eVar2.f441i;
                j2.i.e(set, "triggers");
                if (set.isEmpty()) {
                    byteArray2 = new byte[0];
                } else {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(byteArrayOutputStream2);
                        try {
                            objectOutputStream2.writeInt(set.size());
                            for (d0.d dVar : set) {
                                objectOutputStream2.writeUTF(dVar.f430a.toString());
                                objectOutputStream2.writeBoolean(dVar.f431b);
                            }
                            objectOutputStream2.close();
                            byteArrayOutputStream2.close();
                            byteArray2 = byteArrayOutputStream2.toByteArray();
                            j2.i.d(byteArray2, "toByteArray(...)");
                        } catch (Throwable th5) {
                            try {
                                throw th5;
                            } catch (Throwable th6) {
                                l3.h.j(objectOutputStream2, th5);
                                throw th6;
                            }
                        }
                    } catch (Throwable th7) {
                        try {
                            throw th7;
                        } catch (Throwable th8) {
                            l3.h.j(byteArrayOutputStream2, th7);
                            throw th8;
                        }
                    }
                }
                cVar.d(33, byteArray2);
                return;
            default:
                u uVar = (u) obj;
                j2.i.e(cVar, "statement");
                j2.i.e(uVar, "entity");
                cVar.o(1, uVar.f1363a);
                cVar.o(2, uVar.f1364b);
                return;
        }
    }

    public void c(w.a aVar, Object obj) {
        String str;
        j2.i.e(aVar, "connection");
        if (obj == null) {
            return;
        }
        switch (this.f1306a) {
            case 0:
                str = "INSERT OR IGNORE INTO `Dependency` (`work_spec_id`,`prerequisite_id`) VALUES (?,?)";
                break;
            case 1:
                str = "INSERT OR REPLACE INTO `Preference` (`key`,`long_value`) VALUES (?,?)";
                break;
            case 2:
                str = "INSERT OR REPLACE INTO `SystemIdInfo` (`work_spec_id`,`generation`,`system_id`) VALUES (?,?,?)";
                break;
            case 3:
                str = "INSERT OR IGNORE INTO `WorkName` (`name`,`work_spec_id`) VALUES (?,?)";
                break;
            case 4:
                str = "INSERT OR IGNORE INTO `WorkSpec` (`id`,`state`,`worker_class_name`,`input_merger_class_name`,`input`,`output`,`initial_delay`,`interval_duration`,`flex_duration`,`run_attempt_count`,`backoff_policy`,`backoff_delay_duration`,`last_enqueue_time`,`minimum_retention_duration`,`schedule_requested_at`,`run_in_foreground`,`out_of_quota_policy`,`period_count`,`generation`,`next_schedule_time_override`,`next_schedule_time_override_generation`,`stop_reason`,`trace_tag`,`backoff_on_system_interruptions`,`required_network_type`,`required_network_request`,`requires_charging`,`requires_device_idle`,`requires_battery_not_low`,`requires_storage_not_low`,`trigger_content_update_delay`,`trigger_max_content_delay`,`content_uri_triggers`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                break;
            default:
                str = "INSERT OR IGNORE INTO `WorkTag` (`tag`,`work_spec_id`) VALUES (?,?)";
                break;
        }
        w.c cVarP = aVar.P(str);
        try {
            a(cVarP, obj);
            cVarP.F();
            l3.h.k(cVarP, null);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                l3.h.k(cVarP, th);
                throw th2;
            }
        }
    }
}
