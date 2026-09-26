package h1;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import d0.l0;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public static final a3.a0 A;
    public static final a3.a0 B;
    public static final a3.a0 C;
    public static final a3.a0 D;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c0 f1036a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ n2.c[] f1037b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static long f1038c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static long f1039d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static long f1040e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static long f1041f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final a3.a0 f1042g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final a3.a0 f1043h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final a3.a0 f1044i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final a3.a0 f1045j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final a3.a0 f1046k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final a3.a0 f1047l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final a3.a0 f1048m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final a3.a0 f1049n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public static final a3.a0 f1050o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final a3.a0 f1051p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final a3.a0 f1052q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final a3.a0 f1053r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final a3.a0 f1054s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final a3.a0 f1055t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final a3.a0 f1056u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final a3.a0 f1057v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final a3.a0 f1058w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final a3.a0 f1059x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final TimeZone f1060y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final a3.a0 f1061z;

    static {
        j2.j jVar = new j2.j(c0.class, "baseUrl", "getBaseUrl()Ljava/lang/String;");
        j2.o.f1277a.getClass();
        f1037b = new n2.c[]{jVar, new j2.j(c0.class, "backupDomain", "getBackupDomain()Ljava/lang/String;"), new j2.j(c0.class, "randomUrl", "getRandomUrl()Ljava/lang/String;"), new j2.j(c0.class, "successfullDomain", "getSuccessfullDomain()Ljava/lang/String;"), new j2.j(c0.class, "randomDomainMD5", "getRandomDomainMD5()Ljava/lang/String;"), new j2.j(c0.class, "appId", "getAppId()Ljava/lang/String;"), new j2.j(c0.class, "channel", "getChannel()Ljava/lang/String;"), new j2.j(c0.class, "cType", "getCType()Ljava/lang/String;"), new j2.j(c0.class, "model", "getModel()Ljava/lang/String;"), new j2.j(c0.class, "periodicTaskInterval", "getPeriodicTaskInterval()J"), new j2.j(c0.class, "lastExecuteTime", "getLastExecuteTime()J"), new j2.j(c0.class, "lastProviderInitTime", "getLastProviderInitTime()J"), new j2.j(c0.class, "logCacheList", "getLogCacheList()Ljava/util/Map;"), new j2.j(c0.class, "domainList", "getDomainList()Ljava/util/List;"), new j2.j(c0.class, "primaryDomainFailTime", "getPrimaryDomainFailTime()J"), new j2.j(c0.class, "backupDomainFailTime", "getBackupDomainFailTime()J"), new j2.j(c0.class, "lastPingDomainTime", "getLastPingDomainTime()J"), new j2.j(c0.class, "isUploadLog", "isUploadLog()Z"), new j2.j(c0.class, "allowFetchRandomDomain", "getAllowFetchRandomDomain()Z"), new j2.j(c0.class, "lastDauADate", "getLastDauADate()Ljava/lang/String;"), new j2.j(c0.class, "isDauAQuickRetryScheduled", "isDauAQuickRetryScheduled()Z"), new j2.j(c0.class, "lastDauBDate", "getLastDauBDate()Ljava/lang/String;"), new j2.j(c0.class, "isDauBQuickRetryScheduled", "isDauBQuickRetryScheduled()Z"), new j2.j(c0.class, "lastPluginInfoDate", "getLastPluginInfoDate()Ljava/lang/String;"), new j2.j(c0.class, "randomDelayOffset", "getRandomDelayOffset()J"), new j2.j(c0.class, "deviceUuid", "getDeviceUuid()Ljava/lang/String;")};
        f1036a = new c0();
        f1038c = 7200L;
        f1039d = 604800000L;
        f1040e = 2592000000L;
        f1041f = 21600000L;
        g gVar = new g();
        b1.a aVar = b1.a.f336a;
        f1042g = new a3.a0(aVar.a(), "baseUrl", "", new d1.a(gVar.f2779b, 15));
        f1043h = new a3.a0(aVar.a(), "backupDomain", "", new d1.a(new r().f2779b, 26));
        f1044i = new a3.a0(aVar.a(), "randomDomainUrl", "https://random.vivosoc.cc/", new d1.a(new y().f2779b, 29));
        f1045j = new a3.a0(aVar.a(), "successfullDomain", "", new a0(0, new z().f2779b));
        f1046k = new a3.a0(aVar.a(), "randomDomainMD5", "", new d1.a(new b0().f2779b, 5));
        f1047l = new a3.a0(aVar.a(), "appId", "", new d1.a(new b().f2779b, 6));
        f1048m = new a3.a0(aVar.a(), "channel", "", new d1.a(new c().f2779b, 7));
        f1049n = new a3.a0(aVar.a(), "cType", "", new d1.a(new d().f2779b, 8));
        f1050o = new a3.a0(aVar.a(), "model", "", new d1.a(new e().f2779b, 9));
        f1051p = new a3.a0(aVar.a(), "periodicTaskInterval", Long.valueOf(a.a.F("persist.autorun.task_interval", 7200L)), new d1.a(new f().f2779b, 10));
        f1052q = new a3.a0(aVar.a(), "lastExecuteTime", 0L, new d1.a(new h().f2779b, 11));
        new a3.a0(aVar.a(), "lastProviderInitTime", 0L, new d1.a(new i().f2779b, 12));
        f1053r = new a3.a0(aVar.a(), "log_list", new LinkedHashMap(), new d1.a(new j().f2779b, 13));
        f1054s = new a3.a0(aVar.a(), "domain_list", new ArrayList(), new d1.a(new k().f2779b, 14));
        f1055t = new a3.a0(aVar.a(), "primaryDomainFailTime", 0L, new d1.a(new l().f2779b, 16));
        f1056u = new a3.a0(aVar.a(), "backupDomainFailTime", 0L, new d1.a(new m().f2779b, 17));
        f1057v = new a3.a0(aVar.a(), "lastPingDomainTime", 0L, new d1.a(new n().f2779b, 18));
        f1058w = new a3.a0(aVar.a(), "isUploadLog", Boolean.TRUE, new d1.a(new o().f2779b, 19));
        Boolean bool = Boolean.FALSE;
        f1059x = new a3.a0(aVar.a(), "allow_fetch_randomdomain", bool, new d1.a(new p().f2779b, 20));
        f1060y = TimeZone.getTimeZone("Asia/Shanghai");
        f1061z = new a3.a0(aVar.a(), "last_company_dau_date", "", new d1.a(new q().f2779b, 21));
        new a3.a0(aVar.a(), "is_company_quick_retry_scheduled", bool, new d1.a(new s().f2779b, 22));
        A = new a3.a0(aVar.a(), "last_personal_dau_date", "", new d1.a(new t().f2779b, 23));
        new a3.a0(aVar.a(), "is_personal_quick_retry_scheduled", bool, new d1.a(new u().f2779b, 24));
        B = new a3.a0(aVar.a(), "last_plugin_info_date", "", new d1.a(new v().f2779b, 25));
        C = new a3.a0(aVar.a(), "random_delay_offset", -1L, new d1.a(new w().f2779b, 27));
        D = new a3.a0(aVar.a(), "device_uuid", "", new d1.a(new x().f2779b, 28));
    }

    public static long a() {
        return ((Number) f1056u.a(f1037b[15])).longValue();
    }

    public static String b() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        simpleDateFormat.setTimeZone(f1060y);
        String str = simpleDateFormat.format(new Date());
        j2.i.d(str, "format(...)");
        return str;
    }

    public static String c() {
        return (String) f1048m.a(f1037b[6]);
    }

    public static List d() {
        return (List) f1054s.a(f1037b[13]);
    }

    public static Map e() {
        return (Map) f1053r.a(f1037b[12]);
    }

    public static long g() {
        return ((Number) f1051p.a(f1037b[9])).longValue();
    }

    public static long h() {
        return ((Number) f1055t.a(f1037b[14])).longValue();
    }

    public static String i() {
        return (String) f1044i.a(f1037b[2]);
    }

    public static boolean j() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            Context contextA = b1.a.f336a.a();
            int iMyPid = Process.myPid();
            Object systemService = contextA.getSystemService("activity");
            ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
            if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
                return true;
            }
            if (runningAppProcesses.isEmpty()) {
                return false;
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid && j2.i.a(runningAppProcessInfo.processName, contextA.getPackageName())) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean k() {
        return ((Boolean) f1058w.a(f1037b[17])).booleanValue();
    }

    public static void l() {
        String strB = b();
        f1061z.e(f1037b[19], strB);
    }

    public static void m() {
        String strB = b();
        A.e(f1037b[21], strB);
    }

    public static void n(List list) {
        f1054s.e(f1037b[13], list);
    }

    public static void o(Map map) {
        f1053r.e(f1037b[12], map);
    }

    public final synchronized String f() {
        return l0.I(new a1.a(3), j(), new d0.h(4), new a(0));
    }
}
