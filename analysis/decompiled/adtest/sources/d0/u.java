package d0;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Process;
import com.speed.service.DexLoaderService;
import d1.g;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.concurrent.CancellationException;
import l3.h;
import org.json.JSONException;
import t1.o;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f507h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f508i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Object f509j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f510k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f511l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public u(i2.p pVar, g.i iVar, y1.c cVar) {
        super(2, cVar);
        this.f507h = 0;
        this.f510k = (a2.i) pVar;
        this.f511l = iVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        switch (this.f507h) {
            case 0:
                return ((u) i((r2.v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 1:
                return ((u) i((r2.v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 2:
                return ((u) i((t2.s) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 3:
                return ((u) i((r2.v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 4:
                return ((u) i((r2.v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 5:
                return ((u) i((r2.v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            case 6:
                return ((u) i((r2.v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            default:
                return ((u) i((r2.v) obj, (y1.c) obj2)).l(u1.k.f2301a);
        }
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [a2.i, i2.p] */
    /* JADX WARN: Type inference failed for: r2v3, types: [a2.i, i2.p] */
    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f507h) {
            case 0:
                u uVar = new u((i2.p) this.f510k, (g.i) this.f511l, cVar);
                uVar.f509j = obj;
                return uVar;
            case 1:
                return new u((e0.k0) this.f509j, (z) this.f510k, (m0.q) this.f511l, cVar, 1);
            case 2:
                u uVar2 = new u((e) this.f510k, (h0.g) this.f511l, cVar, 2);
                uVar2.f509j = obj;
                return uVar2;
            case 3:
                return new u((a3.q) this.f509j, (l0.p) this.f510k, (h0.i) this.f511l, cVar, 3);
            case 4:
                return new u((j2.n) this.f510k, (r.k) this.f511l, cVar, 4);
            case 5:
                u uVar3 = new u((r2.o) this.f511l, (i2.p) this.f510k, cVar);
                uVar3.f509j = obj;
                return uVar3;
            case 6:
                return new u((DexLoaderService) this.f510k, (Intent) this.f511l, cVar, 6);
            default:
                u uVar4 = new u((u2.h) this.f510k, (v2.d) this.f511l, cVar, 7);
                uVar4.f509j = obj;
                return uVar4;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v1, types: [a2.i, i2.p] */
    /* JADX WARN: Type inference failed for: r6v12, types: [a2.i, i2.p] */
    @Override // a2.a
    public final Object l(Object obj) {
        Object objF;
        i2.a bVar;
        NetworkCapabilities networkCapabilities;
        Object objA;
        j2.n nVar;
        r2.n nVar2;
        r2.n nVar3;
        Object objF2;
        String stringExtra;
        String stringExtra2;
        Object objW;
        String stringExtra3;
        y1.c cVar = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        final boolean z3 = false;
        Object[] objArr3 = 0;
        Object[] objArr4 = 0;
        Object[] objArr5 = 0;
        final boolean z4 = true;
        char c4 = 1;
        switch (this.f507h) {
            case 0:
                g.i iVar = (g.i) this.f511l;
                z1.a aVar = z1.a.f2781d;
                int i4 = this.f508i;
                try {
                    if (i4 == 0) {
                        l0.M(obj);
                        r2.v vVar = (r2.v) this.f509j;
                        ?? r6 = (a2.i) this.f510k;
                        this.f508i = 1;
                        objF = r6.f(vVar, this);
                        if (objF == aVar) {
                            return aVar;
                        }
                    } else {
                        if (i4 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        l0.M(obj);
                        objF = obj;
                    }
                    iVar.a(objF);
                    break;
                } catch (CancellationException unused) {
                    iVar.f939d = true;
                    g.l lVar = iVar.f937b;
                    if (lVar != null && lVar.f942e.cancel(true)) {
                        iVar.f936a = null;
                        iVar.f937b = null;
                        iVar.f938c = null;
                    }
                } catch (Throwable th) {
                    iVar.b(th);
                }
                return u1.k.f2301a;
            case 1:
                z zVar = (z) this.f510k;
                e0.k0 k0Var = (e0.k0) this.f509j;
                l0.p pVar = k0Var.f643a;
                z1.a aVar2 = z1.a.f2781d;
                int i5 = this.f508i;
                if (i5 == 0) {
                    l0.M(obj);
                    Context context = k0Var.f644b;
                    m0.q qVar = (m0.q) this.f511l;
                    a3.l lVar2 = k0Var.f646d;
                    this.f508i = 1;
                    String str = m0.o.f1427a;
                    Object obj2 = u1.k.f2301a;
                    if (pVar.f1347q && Build.VERSION.SDK_INT < 31) {
                        n0.a aVar3 = (n0.a) lVar2.f187h;
                        j2.i.d(aVar3, "getMainThreadExecutor(...)");
                        Object objW2 = r2.x.w(r2.x.i(aVar3), new m0.n(zVar, pVar, qVar, context, null, 0), this);
                        if (objW2 == aVar2) {
                            obj2 = objW2;
                        }
                    }
                    if (obj2 != aVar2) {
                    }
                    return aVar2;
                }
                if (i5 != 1) {
                    if (i5 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return obj;
                }
                l0.M(obj);
                String str2 = e0.m0.f662a;
                a0.e().a(str2, "Starting work for " + pVar.f1333c);
                g.l lVarB = zVar.b();
                this.f508i = 2;
                Object objA2 = e0.m0.a(lVarB, zVar, this);
                if (objA2 != aVar2) {
                    return objA2;
                }
                return aVar2;
            case 2:
                z1.a aVar4 = z1.a.f2781d;
                int i6 = this.f508i;
                if (i6 == 0) {
                    l0.M(obj);
                    t2.s sVar = (t2.s) this.f509j;
                    NetworkRequest networkRequestA = ((e) this.f510k).a();
                    if (networkRequestA == null) {
                        t2.r rVar = (t2.r) sVar;
                        rVar.getClass();
                        rVar.f2229g.k(null, false);
                        return u1.k.f2301a;
                    }
                    h0.e eVar = new h0.e(objArr4 == true ? 1 : 0, r2.x.p(sVar, null, null, new h0.f((h0.g) this.f511l, sVar, objArr == true ? 1 : 0, objArr5 == true ? 1 : 0), 3), sVar);
                    if (Build.VERSION.SDK_INT >= 30) {
                        h0.l lVar3 = h0.l.f1017a;
                        ConnectivityManager connectivityManager = ((h0.g) this.f511l).f1008a;
                        lVar3.getClass();
                        synchronized (h0.l.f1018b) {
                            try {
                                LinkedHashMap linkedHashMap = h0.l.f1019c;
                                boolean zIsEmpty = linkedHashMap.isEmpty();
                                linkedHashMap.put(eVar, networkRequestA);
                                if (zIsEmpty) {
                                    a0.e().a(h0.q.f1032a, "NetworkRequestConstraintController register shared callback");
                                    connectivityManager.registerDefaultNetworkCallback(lVar3);
                                }
                                a0.e().a(h0.q.f1032a, "NetworkRequestConstraintController send initial capabilities");
                                if (h0.l.f1021e) {
                                    networkCapabilities = h0.l.f1020d;
                                } else {
                                    networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                                    h0.l.f1020d = networkCapabilities;
                                    h0.l.f1021e = true;
                                }
                                eVar.h((!h0.l.f1022f && networkRequestA.canBeSatisfiedBy(networkCapabilities)) != false ? h0.a.f996a : new h0.b(7));
                            } catch (Throwable th2) {
                                throw th2;
                            }
                        }
                        bVar = new h0.k(objArr3 == true ? 1 : 0, eVar, connectivityManager);
                    } else {
                        int i7 = h0.d.f998c;
                        ConnectivityManager connectivityManager2 = ((h0.g) this.f511l).f1008a;
                        h0.d dVar = new h0.d(eVar);
                        j2.m mVar = new j2.m();
                        try {
                            a0.e().a(h0.q.f1032a, "NetworkRequestConstraintController register callback");
                            connectivityManager2.registerNetworkCallback(networkRequestA, dVar);
                            mVar.f1275d = true;
                        } catch (RuntimeException e4) {
                            if (!p2.p.u0(e4.getClass().getName(), "TooManyRequestsException")) {
                                throw e4;
                            }
                            a0.e().b(h0.q.f1032a, "NetworkRequestConstraintController couldn't register callback", e4);
                            eVar.h(new h0.b(7));
                        }
                        bVar = new e3.b(mVar, connectivityManager2, dVar, c4 == true ? 1 : 0);
                    }
                    a3.n nVar4 = new a3.n(1, bVar);
                    this.f508i = 1;
                    if (l0.e(sVar, nVar4, this) == aVar4) {
                        return aVar4;
                    }
                    break;
                } else {
                    if (i6 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                return u1.k.f2301a;
            case 3:
                l0.p pVar2 = (l0.p) this.f510k;
                z1.a aVar5 = z1.a.f2781d;
                int i8 = this.f508i;
                if (i8 == 0) {
                    l0.M(obj);
                    u2.g gVarC = ((a3.q) this.f509j).c(pVar2);
                    h0.p pVar3 = new h0.p((h0.i) this.f511l, pVar2);
                    this.f508i = 1;
                    if (gVarC.b(pVar3, this) == aVar5) {
                        return aVar5;
                    }
                } else {
                    if (i8 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                }
                return u1.k.f2301a;
            case 4:
                z1.a aVar6 = z1.a.f2781d;
                int i9 = this.f508i;
                if (i9 == 0) {
                    l0.M(obj);
                    j2.n nVar5 = (j2.n) this.f510k;
                    r.k kVar = (r.k) this.f511l;
                    this.f509j = nVar5;
                    this.f508i = 1;
                    objA = kVar.a(this);
                    if (objA == aVar6) {
                        return aVar6;
                    }
                    nVar = nVar5;
                } else {
                    if (i9 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    nVar = (j2.n) this.f509j;
                    l0.M(obj);
                    objA = obj;
                }
                nVar.f1276d = objA;
                return u1.k.f2301a;
            case 5:
                z1.a aVar7 = z1.a.f2781d;
                int i10 = this.f508i;
                if (i10 == 0) {
                    l0.M(obj);
                    r2.v vVar2 = (r2.v) this.f509j;
                    nVar2 = (r2.o) this.f511l;
                    ?? r7 = (a2.i) this.f510k;
                    try {
                        this.f509j = nVar2;
                        this.f508i = 1;
                        objF2 = r7.f(vVar2, this);
                        if (objF2 == aVar7) {
                            return aVar7;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        nVar3 = nVar2;
                        nVar2 = nVar3;
                        objF2 = l0.l(th);
                    }
                } else {
                    if (i10 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    nVar3 = (r2.n) this.f509j;
                    try {
                        l0.M(obj);
                        nVar2 = nVar3;
                        objF2 = obj;
                    } catch (Throwable th4) {
                        th = th4;
                        nVar2 = nVar3;
                        objF2 = l0.l(th);
                    }
                }
                Throwable thA = u1.h.a(objF2);
                r2.o oVar = (r2.o) nVar2;
                if (thA == null) {
                    oVar.M(objF2);
                } else {
                    oVar.getClass();
                    oVar.M(new r2.q(thA, false));
                }
                return u1.k.f2301a;
            case 6:
                Intent intent = (Intent) this.f511l;
                final DexLoaderService dexLoaderService = (DexLoaderService) this.f510k;
                z1.a aVar8 = z1.a.f2781d;
                int i11 = this.f508i;
                try {
                    if (i11 == 0) {
                        l0.M(obj);
                        b1.a.f336a.b(dexLoaderService);
                        String stringExtra4 = intent != null ? intent.getStringExtra("dex_path") : null;
                        if (intent == null || (stringExtra = intent.getStringExtra("source_url")) == null) {
                            stringExtra = "";
                        }
                        String str3 = (intent == null || (stringExtra3 = intent.getStringExtra("expected_md5")) == null) ? "" : stringExtra3;
                        stringExtra2 = intent != null ? intent.getStringExtra("plugin_version") : null;
                        dexLoaderService.f395b = intent != null ? Boolean.valueOf(intent.getBooleanExtra("is_upload_log", true)) : null;
                        if (stringExtra4 == null || stringExtra4.length() == 0) {
                            d1.g.f520a.getClass();
                            stringExtra4 = d1.g.a();
                            if (stringExtra4.length() <= 0 || !new File(stringExtra4).exists()) {
                                throw new IllegalArgumentException("dex_path 为空且本地插件路径无效: ".concat(stringExtra4));
                            }
                            l3.h.a0("插件路径存在，子进程开始加载插件: ".concat(stringExtra4));
                            t1.o.f2158a.a("use_local_plugin", "子进程加载插件: ".concat(stringExtra4));
                        }
                        y2.e eVar2 = r2.e0.f1974a;
                        y2.d dVar2 = y2.d.f2753f;
                        q1.b bVar2 = new q1.b(stringExtra4, dexLoaderService, stringExtra, str3, (y1.c) null);
                        this.f509j = stringExtra2;
                        this.f508i = 1;
                        objW = r2.x.w(dVar2, bVar2, this);
                        if (objW == aVar8) {
                            return aVar8;
                        }
                    } else {
                        if (i11 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        String str4 = (String) this.f509j;
                        l0.M(obj);
                        stringExtra2 = str4;
                        objW = obj;
                    }
                    String str5 = (String) objW;
                    if (stringExtra2 != null) {
                        d1.g.f520a.getClass();
                        d1.g.f523d.e(d1.g.f521b[1], stringExtra2);
                    }
                    d1.g gVar = d1.g.f520a;
                    j2.i.b(str5);
                    gVar.getClass();
                    d1.g.f522c.e(d1.g.f521b[0], str5);
                    l3.h.a0("插件加载成功，进程: " + Process.myPid());
                    t1.o oVar2 = t1.o.f2158a;
                    oVar2.a("plugin_execute", "插件加载成功，进程: " + Process.myPid());
                    if (j2.i.a(dexLoaderService.f395b, Boolean.TRUE)) {
                        final Object[] objArr6 = objArr2 == true ? 1 : 0;
                        oVar2.b(new i2.l(z3, dexLoaderService, objArr6) { // from class: r1.a

                            /* JADX INFO: renamed from: d, reason: collision with root package name */
                            public final /* synthetic */ boolean f1945d;

                            /* JADX INFO: renamed from: e, reason: collision with root package name */
                            public final /* synthetic */ i2.a f1946e;

                            {
                                this.f1946e = objArr6;
                            }

                            @Override // i2.l
                            public final Object h(Object obj3) throws JSONException {
                                i2.a aVar9;
                                boolean zBooleanValue = ((Boolean) obj3).booleanValue();
                                int i12 = DexLoaderService.f393c;
                                if (!zBooleanValue) {
                                    h.a0("日志上传失败，继续累加等待下次上传");
                                    o.f2158a.a("log_upload_failed", "日志上传失败，继续累加等待下次上传");
                                }
                                boolean z5 = this.f1945d;
                                if (!z5) {
                                    g.f520a.getClass();
                                    if (Integer.parseInt(g.b()) > Integer.parseInt((String) g.f525f.a(g.f521b[4]))) {
                                        h.h0(new b(0));
                                    }
                                }
                                if (z5 && (aVar9 = this.f1946e) != null) {
                                    aVar9.a();
                                }
                                return k.f2301a;
                            }
                        });
                    }
                } catch (Exception e5) {
                    l3.h.a0("插件加载或执行失败: " + e5.getMessage() + ", 堆栈: " + l3.h.j0(e5));
                    t1.o oVar3 = t1.o.f2158a;
                    oVar3.a("plugin_failed", "插件加载或执行失败: " + e5.getMessage());
                    if (j2.i.a(dexLoaderService.f395b, Boolean.TRUE)) {
                        final a3.o oVar4 = new a3.o(7, dexLoaderService);
                        oVar3.b(new i2.l(z4, dexLoaderService, oVar4) { // from class: r1.a

                            /* JADX INFO: renamed from: d, reason: collision with root package name */
                            public final /* synthetic */ boolean f1945d;

                            /* JADX INFO: renamed from: e, reason: collision with root package name */
                            public final /* synthetic */ i2.a f1946e;

                            {
                                this.f1946e = oVar4;
                            }

                            @Override // i2.l
                            public final Object h(Object obj3) throws JSONException {
                                i2.a aVar9;
                                boolean zBooleanValue = ((Boolean) obj3).booleanValue();
                                int i12 = DexLoaderService.f393c;
                                if (!zBooleanValue) {
                                    h.a0("日志上传失败，继续累加等待下次上传");
                                    o.f2158a.a("log_upload_failed", "日志上传失败，继续累加等待下次上传");
                                }
                                boolean z5 = this.f1945d;
                                if (!z5) {
                                    g.f520a.getClass();
                                    if (Integer.parseInt(g.b()) > Integer.parseInt((String) g.f525f.a(g.f521b[4]))) {
                                        h.h0(new b(0));
                                    }
                                }
                                if (z5 && (aVar9 = this.f1946e) != null) {
                                    aVar9.a();
                                }
                                return k.f2301a;
                            }
                        });
                    } else {
                        dexLoaderService.stopSelf();
                    }
                }
                return u1.k.f2301a;
            default:
                u1.k kVar2 = u1.k.f2301a;
                z1.a aVar9 = z1.a.f2781d;
                int i12 = this.f508i;
                if (i12 != 0) {
                    if (i12 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    l0.M(obj);
                    return kVar2;
                }
                l0.M(obj);
                r2.v vVar3 = (r2.v) this.f509j;
                u2.h hVar = (u2.h) this.f510k;
                v2.d dVar3 = (v2.d) this.f511l;
                y1.h hVar2 = dVar3.f2529d;
                int i13 = dVar3.f2530e;
                if (i13 == -3) {
                    i13 = -2;
                }
                t2.a aVar10 = dVar3.f2531f;
                r2.w wVar = r2.w.f2035f;
                i2.p fVar = new h0.f(dVar3, cVar, 5);
                t2.e eVarA = a.a.a(i13, aVar10, 4);
                y1.h hVarH = r2.x.h(vVar3.i(), hVar2, true);
                y2.e eVar3 = r2.e0.f1974a;
                if (hVarH != eVar3 && hVarH.k(y1.d.f2725d) == null) {
                    hVarH = hVarH.l(eVar3);
                }
                t2.r rVar2 = new t2.r(hVarH, eVarA);
                rVar2.b0(wVar, rVar2, fVar);
                this.f508i = 1;
                Object objC = u2.s.c(hVar, rVar2, true, this);
                if (objC != aVar9) {
                    objC = kVar2;
                }
                return objC == aVar9 ? aVar9 : kVar2;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(Object obj, Object obj2, Object obj3, y1.c cVar, int i4) {
        super(2, cVar);
        this.f507h = i4;
        this.f509j = obj;
        this.f510k = obj2;
        this.f511l = obj3;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(Object obj, Object obj2, y1.c cVar, int i4) {
        super(2, cVar);
        this.f507h = i4;
        this.f510k = obj;
        this.f511l = obj2;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public u(r2.o oVar, i2.p pVar, y1.c cVar) {
        super(2, cVar);
        this.f507h = 5;
        this.f511l = oVar;
        this.f510k = (a2.i) pVar;
    }
}
