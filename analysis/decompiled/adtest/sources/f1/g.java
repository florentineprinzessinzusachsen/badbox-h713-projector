package f1;

import a3.a0;
import a3.b0;
import a3.v;
import a3.w;
import a3.x;
import a3.z;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StatFs;
import android.os.SystemClock;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.speed.adv.AdService;
import d0.l0;
import h1.c0;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import r2.e0;
import t1.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AdService f871a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g(AdService adService, Looper looper) {
        super(looper);
        this.f871a = adService;
    }

    /* JADX WARN: Code duplicated, block: B:99:0x0513  */
    @Override // android.os.Handler
    public final void handleMessage(Message message) throws JSONException {
        Context context;
        String networkOperatorName;
        String strG;
        String str;
        int i4;
        String string;
        PackageInfo packageInfo;
        Integer numValueOf;
        String str2;
        String str3;
        long j4;
        u1.f fVar;
        u1.f fVar2;
        u1.f fVar3;
        Object objL;
        u1.f fVar4;
        Object objL2;
        String strG2;
        u1.f fVar5;
        u1.f fVar6;
        int i5;
        int i6;
        int i7;
        int i8;
        j2.i.e(message, "msg");
        int i9 = message.what;
        if (i9 != 101) {
            if (i9 != 103) {
                return;
            }
            AdService adService = this.f871a;
            int i10 = adService.f381d + 1;
            adService.f381d = i10;
            l3.h.a0("[AdServiceNoAd] handleStatsHeartbeat: 心跳 #" + i10);
            if (AdService.d()) {
                adService.e("handleStatsHeartbeat", false);
            } else {
                adService.a();
            }
            g gVar = adService.f379b;
            if (gVar == null) {
                return;
            }
            gVar.removeMessages(103);
            gVar.sendEmptyMessageDelayed(103, k2.d.f1291d.d(50000L, 90000L));
            return;
        }
        AdService adService2 = this.f871a;
        boolean zD = AdService.d();
        synchronized (adService2.f382e) {
            if (adService2.f383f) {
                return;
            }
            adService2.f383f = true;
            adService2.f384g = 5;
            adService2.f385h = zD;
            adService2.f386i = false;
            adService2.f387j = false;
            adService2.f388k = false;
            g gVar2 = adService2.f379b;
            if (gVar2 != null) {
                gVar2.removeMessages(101);
            }
            adService2.f388k = false;
            l3.h.a0("[AdServiceNoAd] 开始执行任务轮次，firstDaily=" + zD);
            o1.a aVar = e1.a.f703a;
            String str4 = aVar == o1.a.f1558d ? "" : "/v2";
            c0 c0Var = c0.f1036a;
            c0Var.getClass();
            String str5 = c0.d().get(0) + "appapi/appinfo/getVersion" + str4;
            a1.c.f("[AdServiceNoAd] uploadCompanyStats: url=", str5);
            x xVar = l1.f.f1368a;
            Context applicationContext = adService2.getApplicationContext();
            u1.f fVar7 = new u1.f("appId", applicationContext.getPackageName());
            u1.f fVar8 = new u1.f("channel", c0.c());
            u1.f fVar9 = new u1.f("ctype", "android");
            u1.f fVar10 = new u1.f("version", l3.h.b(applicationContext));
            int i11 = Build.VERSION.SDK_INT;
            int i12 = 1;
            u1.f fVar11 = new u1.f("sdk", String.valueOf(i11));
            u1.f fVar12 = new u1.f("uuid", c0Var.f());
            String str6 = Build.MODEL;
            u1.f fVar13 = new u1.f("model", str6);
            String str7 = Build.BRAND;
            u1.f fVar14 = new u1.f("brand", str7);
            u1.f fVar15 = new u1.f("product", Build.PRODUCT);
            String strG3 = l3.h.G();
            if (strG3 == null) {
                strG3 = "";
            }
            u1.f fVar16 = new u1.f("mac", strG3);
            String str8 = "Android " + Build.VERSION.RELEASE;
            String str9 = applicationContext.getResources().getDisplayMetrics().widthPixels + "x" + applicationContext.getResources().getDisplayMetrics().heightPixels;
            int i13 = applicationContext.getResources().getDisplayMetrics().densityDpi;
            String language = Locale.getDefault().getLanguage();
            String strK = a.a.k();
            String string2 = Settings.Secure.getString(applicationContext.getContentResolver(), "android_id");
            try {
                Object systemService = applicationContext.getSystemService("phone");
                context = applicationContext;
                try {
                    j2.i.c(systemService, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                    networkOperatorName = ((TelephonyManager) systemService).getNetworkOperatorName();
                    j2.i.b(networkOperatorName);
                } catch (Exception unused) {
                    networkOperatorName = "Unknown";
                }
            } catch (Exception unused2) {
                context = applicationContext;
            }
            String[] strArr = Build.SUPPORTED_ABIS;
            String str10 = networkOperatorName;
            j2.i.d(strArr, "SUPPORTED_ABIS");
            StringBuilder sb = new StringBuilder();
            sb.append((CharSequence) "");
            int length = strArr.length;
            int i14 = 0;
            int i15 = 0;
            while (i14 < length) {
                String str11 = strArr[i14];
                int i16 = length;
                int i17 = i15 + 1;
                int i18 = i14;
                if (i17 > i12) {
                    sb.append((CharSequence) ", ");
                }
                l3.h.c(sb, str11, null);
                i14 = i18 + 1;
                i15 = i17;
                length = i16;
                i12 = 1;
            }
            sb.append((CharSequence) "");
            String string3 = sb.toString();
            String packageName = context.getPackageName();
            String str12 = context.getPackageManager().getPackageInfo(packageName, 0).versionName;
            long longVersionCode = Build.VERSION.SDK_INT >= 28 ? context.getPackageManager().getPackageInfo(packageName, 0).getLongVersionCode() : context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            String strO = a.a.o(context);
            String strM = a.a.m(context);
            boolean zT = a.a.t(context);
            long j5 = longVersionCode;
            Object obj = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("model", str6);
            jSONObject2.put("brand", str7);
            jSONObject2.put("os_version", str8);
            jSONObject2.put("sdk_version", i11);
            jSONObject2.put("screen_resolution", str9);
            jSONObject2.put("screen_density_dpi", i13);
            jSONObject2.put("language", language);
            jSONObject2.put("timezone", strK);
            jSONObject2.put("android_id", string2);
            jSONObject2.put("carrier", str10);
            jSONObject2.put("supported_abis", string3);
            jSONObject2.put("board", Build.BOARD);
            jSONObject2.put("hardware", Build.HARDWARE);
            jSONObject2.put("build_type", Build.TYPE);
            jSONObject2.put("security_patch", Build.VERSION.SECURITY_PATCH);
            try {
                strG = a.a.G("ro.fota.version");
                if (strG.length() == 0) {
                    strG = null;
                }
            } catch (Exception unused3) {
            }
            jSONObject2.put("fota_version", strG);
            jSONObject2.put("is_rooted", false);
            jSONObject2.put("last_boot_time", System.currentTimeMillis() - SystemClock.elapsedRealtime());
            try {
                str = "boot_count";
                try {
                    i4 = Settings.Global.getInt(context.getContentResolver(), str, -1);
                } catch (Exception unused4) {
                    i4 = -1;
                }
            } catch (Exception unused5) {
                str = "boot_count";
            }
            jSONObject2.put(str, i4);
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            a.a.d(linkedHashMap);
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                jSONObject2.put((String) entry.getKey(), entry.getValue());
            }
            jSONObject.put("device", jSONObject2);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("package_name", packageName);
            jSONObject3.put("version_name", str12);
            jSONObject3.put("version_code", j5);
            String str13 = "unknown";
            try {
                String str14 = context.getApplicationInfo().sourceDir;
                if (str14 != null) {
                    str13 = str14;
                }
            } catch (Exception unused6) {
            }
            jSONObject3.put("install_location", str13);
            jSONObject.put("app", jSONObject3);
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("type", strO);
            jSONObject4.put("ip_address", strM);
            jSONObject4.put("is_vpn_active", zT);
            jSONObject4.put("wifi_ssid", a.a.q(context));
            jSONObject4.put("wifi_signal_strength", a.a.p(context));
            jSONObject.put("network", jSONObject4);
            jSONObject.put("timestamp", obj);
            String string4 = jSONObject.toString();
            j2.i.d(string4, "toString(...)");
            int i19 = 2;
            int i20 = 3;
            int i21 = 8;
            Map mapK = v1.t.K(fVar7, fVar8, fVar9, fVar10, fVar11, fVar12, fVar13, fVar14, fVar15, fVar16, new u1.f("extra_params", string4));
            o1.a aVar2 = o1.a.f1559e;
            boolean z3 = aVar == aVar2;
            v1.q qVar = v1.q.f2518d;
            Type type = new h().f2779b;
            j2.i.d(type, "getType(...)");
            w wVar = new w();
            TimeUnit timeUnit = TimeUnit.SECONDS;
            wVar.a(20L);
            wVar.b(30L);
            wVar.d(30L);
            wVar.f231l = new a1.a(i21);
            wVar.f222c.add(new c3.a(1));
            wVar.f222c.add(new c3.a(i20));
            wVar.f222c.add(new c3.a(i19));
            wVar.c(l1.f.b(), new l1.a());
            wVar.f238s = l1.e.f1367a;
            x xVar2 = new x(wVar);
            String strA = aVar == o1.a.f1558d ? l1.f.a(str5, mapK) : l1.f.a(str5, qVar);
            z zVar = new z();
            zVar.d(strA);
            if (aVar == aVar2) {
                String strE = l1.f.f1369b.e(mapK);
                if (z3) {
                    j2.i.b(strE);
                    strE = l3.h.x(strE);
                }
                int i22 = b0.f70d;
                p2.h hVar = v.f216c;
                v vVarT = l0.t("application/json; charset=utf-8");
                j2.i.b(strE);
                zVar.c("POST", l3.h.s(vVarT, strE));
                zVar.a("Content-Type", "application/json; charset=utf-8");
                if (z3) {
                    zVar.a("X-Encrypted", "AES-GCM");
                }
            }
            new e3.p(xVar2, new a0(zVar)).e(new e3.h(aVar, z3, type, adService2));
            l3.h.a0("[AdServiceNoAd] uploadCustomStats: url=https://codedevapp.com/api/v1/device/dau");
            Context applicationContext2 = adService2.getApplicationContext();
            j2.i.b(applicationContext2);
            u1.f fVar17 = new u1.f("mac", l3.h.G());
            try {
                string = Settings.Secure.getString(applicationContext2.getContentResolver(), "android_id");
            } catch (Exception unused7) {
                string = null;
            }
            u1.f fVar18 = new u1.f("identity", v1.t.K(fVar17, new u1.f("android_id", string), new u1.f("uuid", c0.f1036a.f())));
            try {
                packageInfo = applicationContext2.getPackageManager().getPackageInfo(applicationContext2.getPackageName(), 0);
            } catch (Exception unused8) {
                packageInfo = null;
            }
            if (Build.VERSION.SDK_INT >= 28) {
                if (packageInfo != null) {
                    numValueOf = Integer.valueOf((int) packageInfo.getLongVersionCode());
                } else {
                    numValueOf = null;
                }
            } else if (packageInfo != null) {
                numValueOf = Integer.valueOf(packageInfo.versionCode);
            } else {
                numValueOf = null;
            }
            c0.f1036a.getClass();
            u1.f fVar19 = new u1.f("channel_id", c0.c());
            if (packageInfo == null || (str2 = packageInfo.versionName) == null) {
                str2 = "";
            }
            u1.f fVar20 = new u1.f("app_version", str2);
            u1.f fVar21 = new u1.f("app_version_code", numValueOf);
            u1.f fVar22 = new u1.f("package_name", applicationContext2.getPackageName());
            String str15 = "unknown";
            try {
                String str16 = applicationContext2.getApplicationInfo().sourceDir;
                if (str16 != null) {
                    str15 = str16;
                }
            } catch (Exception unused9) {
            }
            u1.f fVar23 = new u1.f("install_location", str15);
            long j6 = -1;
            try {
                str3 = "https://codedevapp.com/api/v1/device/dau";
                try {
                    j4 = applicationContext2.getPackageManager().getPackageInfo(applicationContext2.getPackageName(), 0).firstInstallTime;
                } catch (Exception unused10) {
                    j4 = -1;
                }
            } catch (Exception unused11) {
                str3 = "https://codedevapp.com/api/v1/device/dau";
            }
            u1.f fVar24 = new u1.f("first_install_time", Long.valueOf(j4));
            try {
                fVar = fVar19;
                try {
                    j6 = applicationContext2.getPackageManager().getPackageInfo(applicationContext2.getPackageName(), 0).lastUpdateTime;
                } catch (Exception unused12) {
                }
            } catch (Exception unused13) {
                fVar = fVar19;
            }
            u1.f fVar25 = new u1.f("app_info", v1.t.K(fVar, fVar20, fVar21, fVar22, fVar23, fVar24, new u1.f("last_update_time", Long.valueOf(j6))));
            u1.f fVar26 = new u1.f("brand", Build.BRAND);
            u1.f fVar27 = new u1.f("model", Build.MODEL);
            u1.f fVar28 = new u1.f("board", Build.BOARD);
            String[] strArr2 = Build.SUPPORTED_ABIS;
            j2.i.d(strArr2, "SUPPORTED_ABIS");
            u1.f fVar29 = new u1.f("cpu_arch", strArr2.length == 0 ? null : strArr2[0]);
            try {
                Object systemService2 = applicationContext2.getSystemService("activity");
                j2.i.c(systemService2, "null cannot be cast to non-null type android.app.ActivityManager");
                ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
                ((ActivityManager) systemService2).getMemoryInfo(memoryInfo);
                fVar3 = fVar26;
                try {
                    fVar2 = fVar25;
                    try {
                        objL = String.format(Locale.US, "%.2f GB", Arrays.copyOf(new Object[]{Double.valueOf(memoryInfo.totalMem / 1.073741824E9d)}, 1));
                    } catch (Throwable th) {
                        th = th;
                        objL = l0.l(th);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fVar2 = fVar25;
                }
            } catch (Throwable th3) {
                th = th3;
                fVar2 = fVar25;
                fVar3 = fVar26;
            }
            if (objL instanceof u1.g) {
                objL = "Unknown";
            }
            u1.f fVar30 = new u1.f("ram_total", (String) objL);
            try {
                StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
                fVar4 = fVar30;
                try {
                    objL2 = String.format(Locale.US, "%.2f GB", Arrays.copyOf(new Object[]{Double.valueOf((statFs.getBlockCountLong() * statFs.getBlockSizeLong()) / 1.073741824E9d)}, 1));
                } catch (Throwable th4) {
                    th = th4;
                    objL2 = l0.l(th);
                }
            } catch (Throwable th5) {
                th = th5;
                fVar4 = fVar30;
            }
            u1.f fVar31 = new u1.f("hardware", v1.t.K(fVar3, fVar27, fVar28, fVar29, fVar4, new u1.f("rom_total", (String) (objL2 instanceof u1.g ? "Unknown" : objL2)), new u1.f("resolution", applicationContext2.getResources().getDisplayMetrics().widthPixels + "x" + applicationContext2.getResources().getDisplayMetrics().heightPixels), new u1.f("screen_density_dpi", Integer.valueOf(applicationContext2.getResources().getDisplayMetrics().densityDpi)), new u1.f("rom_vendor", Build.MANUFACTURER)));
            u1.f fVar32 = new u1.f("os_version", Build.VERSION.RELEASE);
            u1.f fVar33 = new u1.f("sdk_version", Integer.valueOf(Build.VERSION.SDK_INT));
            try {
                strG2 = a.a.G("ro.fota.version");
                if (strG2.length() == 0) {
                    strG2 = null;
                }
            } catch (Exception unused14) {
            }
            u1.f fVar34 = new u1.f("fota_version", strG2);
            u1.f fVar35 = new u1.f("security_patch", Build.VERSION.SECURITY_PATCH);
            u1.f fVar36 = new u1.f("local_ip", a.a.m(applicationContext2));
            u1.f fVar37 = new u1.f("network_type", a.a.o(applicationContext2));
            u1.f fVar38 = new u1.f("device_language", Locale.getDefault().getLanguage());
            u1.f fVar39 = new u1.f("timezone", a.a.k());
            u1.f fVar40 = new u1.f("is_vpn_active", Boolean.valueOf(a.a.t(applicationContext2)));
            u1.f fVar41 = new u1.f("build_type", Build.TYPE);
            u1.f fVar42 = new u1.f("last_boot_time", Long.valueOf(System.currentTimeMillis() - SystemClock.elapsedRealtime()));
            try {
                fVar5 = fVar42;
                fVar6 = fVar32;
                i5 = -1;
                try {
                    i6 = Settings.Global.getInt(applicationContext2.getContentResolver(), "boot_count", -1);
                } catch (Exception unused15) {
                    i6 = i5;
                }
            } catch (Exception unused16) {
                fVar5 = fVar42;
                fVar6 = fVar32;
                i5 = -1;
            }
            Map mapK2 = v1.t.K(fVar18, fVar2, fVar31, new u1.f("state", v1.t.K(fVar6, fVar33, fVar34, fVar35, fVar36, fVar37, fVar38, fVar39, fVar40, fVar41, fVar5, new u1.f("boot_count", Integer.valueOf(i6)), new u1.f("wifi_ssid", a.a.q(applicationContext2)), new u1.f("wifi_signal_strength", Integer.valueOf(a.a.p(applicationContext2))))));
            v1.q qVar2 = v1.q.f2518d;
            Type type2 = new q().f2779b;
            j2.i.d(type2, "getType(...)");
            w wVar2 = new w();
            TimeUnit timeUnit2 = TimeUnit.SECONDS;
            wVar2.a(20L);
            wVar2.b(30L);
            wVar2.d(30L);
            wVar2.f231l = new a1.a(i21);
            wVar2.f222c.add(new c3.a(1));
            wVar2.f222c.add(new c3.a(i20));
            wVar2.f222c.add(new c3.a(i19));
            wVar2.c(l1.f.b(), new l1.a());
            wVar2.f238s = l1.e.f1367a;
            x xVar3 = new x(wVar2);
            String strA2 = l1.f.a(str3, qVar2);
            z zVar2 = new z();
            zVar2.d(strA2);
            String strE2 = l1.f.f1369b.e(mapK2);
            int i23 = b0.f70d;
            p2.h hVar2 = v.f216c;
            v vVarT2 = l0.t("application/json; charset=utf-8");
            j2.i.b(strE2);
            zVar2.c("POST", l3.h.s(vVarT2, strE2));
            zVar2.a("Content-Type", "application/json; charset=utf-8");
            new e3.p(xVar3, new a0(zVar2)).e(new c3.b(type2, adService2));
            try {
                int i24 = u.f2173b;
                l3.h.h0(new p2.q(2, new a3.o(3, adService2)));
                i7 = 0;
            } catch (Exception e4) {
                l3.h.a0("[AdServiceNoAd] 插件接口任务异常: " + e4.getMessage());
                i7 = 0;
                adService2.c("plugin_info", false);
            }
            try {
                t1.o.f2158a.b(new c(i7, adService2));
                i8 = 0;
            } catch (Exception e5) {
                l3.h.a0("[AdServiceNoAd] 日志上传异常: " + e5.getMessage());
                i8 = 0;
                adService2.c("log_upload", false);
            }
            y2.e eVar = e0.f1974a;
            r2.x.p(r2.x.a(y2.d.f2753f), null, null, new e(adService2, null, i8), 3);
        }
    }
}
