package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.TimeConstants;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.CoreProtocol;
import com.umeng.analytics.process.UMProcessDBHelper;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.utils.JSONArraySortUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: CoreProtocolImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Context f3676a = null;
    private static final String l = "first_activate_time";
    private static final String m = "ana_is_f";
    private static final String n = "thtstart";
    private static final String o = "dstk_last_time";
    private static final String p = "dstk_cnt";
    private static final String q = "gkvc";
    private static final String r = "ekvc";
    private static final String t = "-1";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private c f3677b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private SharedPreferences f3678c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3679d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3680e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3681f;
    private JSONArray g;
    private final int h;
    private int i;
    private int j;
    private long k;
    private final long s;
    private boolean u;
    private boolean v;
    private Object w;

    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int f3682a = 4097;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final int f3683b = 4098;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final int f3684c = 4099;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final int f3685d = 4100;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final int f3686e = 4101;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final int f3687f = 4102;
        public static final int g = 4103;
        public static final int h = 4104;
        public static final int i = 4105;
        public static final int j = 4106;
        public static final int k = 4352;
        public static final int l = 4353;
        public static final int m = 4354;
        public static final int n = 8193;
        public static final int o = 8194;
        public static final int p = 8195;
        public static final int q = 8196;
        public static final int r = 8197;
        public static final int s = 8198;
        public static final int t = 8199;
        public static final int u = 8200;
        public static final int v = 8201;
        public static final int w = 8202;
    }

    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final k f3688a = new k();

        private b() {
        }
    }

    public static k a(Context context) {
        if (f3676a == null && context != null) {
            f3676a = context.getApplicationContext();
        }
        return b.f3688a;
    }

    private void e(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (2050 == jSONObject.getInt("__t")) {
                if (!a(this.k, this.i)) {
                    return;
                } else {
                    this.i++;
                }
            } else if (2049 == jSONObject.getInt("__t")) {
                if (!a(this.k, this.j)) {
                    return;
                } else {
                    this.j++;
                }
            }
            if (this.g.length() >= this.f3681f) {
                g.a(f3676a).a(this.g);
                this.g = new JSONArray();
            }
            if (this.k == 0) {
                this.k = System.currentTimeMillis();
            }
            this.g.put(jSONObject);
        } catch (Throwable th) {
            MLog.e(th);
        }
    }

    private void h() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("loadNativeData", Context.class).invoke(null, f3676a);
        } catch (Exception unused) {
        }
    }

    private void i() {
        try {
            Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("processCommond", Context.class, String.class).invoke(null, f3676a, AnalyticsConfig.getAppkey(f3676a));
        } catch (Exception unused) {
        }
    }

    private void j() {
        JSONObject jSONObjectB = b(UMEnvelopeBuild.maxDataSpace(f3676a));
        if (jSONObjectB == null || jSONObjectB.length() < 1) {
            return;
        }
        JSONObject jSONObject = (JSONObject) jSONObjectB.opt("header");
        JSONObject jSONObject2 = (JSONObject) jSONObjectB.opt("content");
        if (f3676a == null || jSONObject == null || jSONObject2 == null) {
            return;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> constructInstantMessage: request build envelope.");
        JSONObject jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(f3676a, jSONObject, jSONObject2);
        if (jSONObjectBuildEnvelopeWithExtHeader != null) {
            try {
                if (jSONObjectBuildEnvelopeWithExtHeader.has("exception")) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + jSONObjectBuildEnvelopeWithExtHeader.getInt("exception"));
                }
            } catch (Throwable unused) {
            }
            b((Object) jSONObjectBuildEnvelopeWithExtHeader);
        }
    }

    private void k() {
        JSONObject jSONObjectBuildEnvelopeWithExtHeader;
        JSONObject jSONObjectA = a(UMEnvelopeBuild.maxDataSpace(f3676a));
        if (jSONObjectA == null || jSONObjectA.length() < 1) {
            return;
        }
        JSONObject jSONObject = (JSONObject) jSONObjectA.opt("header");
        JSONObject jSONObject2 = (JSONObject) jSONObjectA.opt("content");
        Context context = f3676a;
        if (context == null || jSONObject == null || jSONObject2 == null || (jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, jSONObject2)) == null) {
            return;
        }
        try {
            if (jSONObjectBuildEnvelopeWithExtHeader.has("exception")) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "Build envelope error code: " + jSONObjectBuildEnvelopeWithExtHeader.getInt("exception"));
            }
        } catch (Throwable unused) {
        }
        c(jSONObjectBuildEnvelopeWithExtHeader);
        a((Object) jSONObjectBuildEnvelopeWithExtHeader);
    }

    private JSONObject l() {
        JSONObject jSONObjectM = m();
        if (jSONObjectM != null) {
            try {
                jSONObjectM.put("st", "1");
            } catch (Throwable unused) {
            }
        }
        return jSONObjectM;
    }

    private JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (AnalyticsConfig.mWrapperType != null && AnalyticsConfig.mWrapperVersion != null) {
                jSONObject.put("wrapper_version", AnalyticsConfig.mWrapperVersion);
                jSONObject.put("wrapper_type", AnalyticsConfig.mWrapperType);
            }
            jSONObject.put(com.umeng.analytics.pro.b.i, AnalyticsConfig.getVerticalType(f3676a));
            jSONObject.put("sdk_version", v.f3732a);
            String strMD5 = HelperUtils.MD5(AnalyticsConfig.getSecretKey(f3676a));
            if (!TextUtils.isEmpty(strMD5)) {
                jSONObject.put("secret", strMD5);
            }
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(f3676a, "pr_ve", null);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3676a);
            String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(f3676a, com.umeng.analytics.pro.b.ak, "");
            if (!TextUtils.isEmpty(strImprintProperty2)) {
                if (AnalyticsConfig.CLEAR_EKV_BL) {
                    jSONObject.put(com.umeng.analytics.pro.b.am, "");
                } else {
                    jSONObject.put(com.umeng.analytics.pro.b.am, strImprintProperty2);
                }
            }
            String strImprintProperty3 = UMEnvelopeBuild.imprintProperty(f3676a, com.umeng.analytics.pro.b.al, "");
            if (!TextUtils.isEmpty(strImprintProperty3)) {
                if (AnalyticsConfig.CLEAR_EKV_WL) {
                    jSONObject.put(com.umeng.analytics.pro.b.an, "");
                } else {
                    jSONObject.put(com.umeng.analytics.pro.b.an, strImprintProperty3);
                }
            }
            jSONObject.put(com.umeng.analytics.pro.b.ae, "1.0.0");
            if (t()) {
                jSONObject.put(com.umeng.analytics.pro.b.ag, "1");
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putLong(m, 0L).commit();
                }
            }
            jSONObject.put(com.umeng.analytics.pro.b.l, n());
            jSONObject.put(com.umeng.analytics.pro.b.m, o());
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("vers_name", "");
                if (!TextUtils.isEmpty(string)) {
                    String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    if (TextUtils.isEmpty(strImprintProperty)) {
                        jSONObject.put(com.umeng.analytics.pro.b.l, sharedPreferences.getString("vers_pre_version", "0"));
                        jSONObject.put(com.umeng.analytics.pro.b.m, sharedPreferences.getString("vers_date", str));
                    }
                    sharedPreferences.edit().putString("pre_version", string).putString("cur_version", DeviceConfig.getAppVersionName(f3676a)).putString("pre_date", str).remove("vers_name").remove("vers_code").remove("vers_date").remove("vers_pre_version").commit();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    private String n() {
        String string;
        String strImprintProperty = null;
        try {
            strImprintProperty = UMEnvelopeBuild.imprintProperty(f3676a, "pr_ve", null);
            if (!TextUtils.isEmpty(strImprintProperty)) {
                string = strImprintProperty;
            } else {
                if (!TextUtils.isEmpty(this.f3679d)) {
                    return this.f3679d;
                }
                if (this.f3678c == null) {
                    this.f3678c = PreferenceWrapper.getDefault(f3676a);
                }
                String string2 = this.f3678c.getString("pre_version", "");
                String appVersionName = DeviceConfig.getAppVersionName(f3676a);
                if (TextUtils.isEmpty(string2)) {
                    this.f3678c.edit().putString("pre_version", "0").putString("cur_version", appVersionName).commit();
                    string = "0";
                } else {
                    string = this.f3678c.getString("cur_version", "");
                    if (appVersionName.equals(string)) {
                        string = string2;
                    } else {
                        this.f3678c.edit().putString("pre_version", string).putString("cur_version", appVersionName).commit();
                    }
                }
            }
        } catch (Throwable unused) {
        }
        this.f3679d = string;
        return string;
    }

    private String o() {
        String string;
        String strImprintProperty = null;
        try {
            strImprintProperty = UMEnvelopeBuild.imprintProperty(f3676a, "ud_da", null);
            if (!TextUtils.isEmpty(strImprintProperty)) {
                string = strImprintProperty;
            } else {
                if (!TextUtils.isEmpty(this.f3680e)) {
                    return this.f3680e;
                }
                if (this.f3678c == null) {
                    this.f3678c = PreferenceWrapper.getDefault(f3676a);
                }
                string = this.f3678c.getString("pre_date", "");
                if (TextUtils.isEmpty(string)) {
                    string = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    this.f3678c.edit().putString("pre_date", string).commit();
                } else {
                    String str = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
                    if (!string.equals(str)) {
                        this.f3678c.edit().putString("pre_date", str).commit();
                        string = str;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        this.f3680e = string;
        return string;
    }

    private void p() {
        try {
            this.i = 0;
            this.j = 0;
            this.k = System.currentTimeMillis();
            PreferenceWrapper.getDefault(f3676a).edit().putLong(o, System.currentTimeMillis()).putInt(p, 0).commit();
        } catch (Throwable unused) {
        }
    }

    private boolean q() {
        try {
            if (!TextUtils.isEmpty(q.a().b())) {
                b(f3676a);
            }
            if (this.g.length() <= 0) {
                return false;
            }
            for (int i = 0; i < this.g.length(); i++) {
                JSONObject jSONObjectOptJSONObject = this.g.optJSONObject(i);
                if (jSONObjectOptJSONObject != null && jSONObjectOptJSONObject.length() > 0) {
                    String strOptString = jSONObjectOptJSONObject.optString("__i");
                    if (TextUtils.isEmpty(strOptString) || t.equals(strOptString)) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Throwable unused) {
            return true;
        }
    }

    private void r() {
        if (this.g.length() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.g.length(); i++) {
                try {
                    JSONObject jSONObject = this.g.getJSONObject(i);
                    if (jSONObject == null || jSONObject.length() <= 0) {
                        jSONArray.put(jSONObject);
                    } else {
                        String strOptString = jSONObject.optString("__i");
                        if (TextUtils.isEmpty(strOptString) || t.equals(strOptString)) {
                            String strB = q.a().b();
                            if (TextUtils.isEmpty(strB)) {
                                strB = t;
                            }
                            jSONObject.put("__i", strB);
                        }
                        jSONArray.put(jSONObject);
                    }
                } catch (Throwable unused) {
                }
            }
            this.g = jSONArray;
        }
    }

    private void s() {
        SharedPreferences sharedPreferences;
        try {
            if (!t() || f3676a == null || (sharedPreferences = PreferenceWrapper.getDefault(f3676a)) == null || sharedPreferences.getLong(l, 0L) != 0) {
                return;
            }
            sharedPreferences.edit().putLong(l, System.currentTimeMillis()).commit();
        } catch (Throwable unused) {
        }
    }

    private boolean t() {
        SharedPreferences sharedPreferences;
        try {
            return (f3676a == null || (sharedPreferences = PreferenceWrapper.getDefault(f3676a)) == null || sharedPreferences.getLong(m, -1L) == 0) ? false : true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public JSONObject b(long j) {
        if (TextUtils.isEmpty(u.a().d(UMGlobalContext.getAppContext(f3676a)))) {
            return null;
        }
        JSONObject jSONObjectB = g.a(UMGlobalContext.getAppContext(f3676a)).b(false);
        String[] strArrA = com.umeng.analytics.c.a(f3676a);
        if (strArrA != null && !TextUtils.isEmpty(strArrA[0]) && !TextUtils.isEmpty(strArrA[1])) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(com.umeng.analytics.pro.b.L, strArrA[0]);
                jSONObject.put(com.umeng.analytics.pro.b.M, strArrA[1]);
                if (jSONObject.length() > 0) {
                    jSONObjectB.put(com.umeng.analytics.pro.b.K, jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
        int iA = n.a().a(f3676a);
        if (jSONObjectB.length() == 1 && jSONObjectB.optJSONObject(com.umeng.analytics.pro.b.K) != null && iA != 3) {
            return null;
        }
        n.a().b(jSONObjectB, f3676a);
        if (jSONObjectB.length() <= 0 && iA != 3) {
            return null;
        }
        JSONObject jSONObjectL = l();
        if (jSONObjectL != null) {
            a(jSONObjectL);
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            if (iA == 3) {
                jSONObject3.put("analytics", new JSONObject());
            } else if (jSONObjectB != null && jSONObjectB.length() > 0) {
                jSONObject3.put("analytics", jSONObjectB);
            }
            if (jSONObjectL != null && jSONObjectL.length() > 0) {
                jSONObject2.put("header", jSONObjectL);
            }
            if (jSONObject3.length() > 0) {
                jSONObject2.put("content", jSONObject3);
            }
            return b(jSONObject2, j);
        } catch (Throwable unused2) {
            return jSONObject2;
        }
    }

    public void b() {
    }

    public void c() {
        b(f3676a);
        d();
        a(true);
    }

    public void d() {
        try {
            if (this.g.length() > 0) {
                g.a(f3676a).a(this.g);
                this.g = new JSONArray();
            }
            PreferenceWrapper.getDefault(f3676a).edit().putLong(n, this.k).putInt(q, this.i).putInt(r, this.j).commit();
        } catch (Throwable unused) {
        }
    }

    public long f() {
        SharedPreferences sharedPreferences;
        long jCurrentTimeMillis = 0;
        try {
            if (f3676a == null || (sharedPreferences = PreferenceWrapper.getDefault(f3676a)) == null) {
                return 0L;
            }
            long j = sharedPreferences.getLong(l, 0L);
            if (j == 0) {
                try {
                    jCurrentTimeMillis = System.currentTimeMillis();
                    sharedPreferences.edit().putLong(l, jCurrentTimeMillis).commit();
                    return jCurrentTimeMillis;
                } catch (Throwable unused) {
                }
            }
            return j;
        } catch (Throwable unused2) {
            return jCurrentTimeMillis;
        }
    }

    private k() {
        this.f3677b = null;
        this.f3678c = null;
        this.f3679d = null;
        this.f3680e = null;
        this.f3681f = 10;
        this.g = new JSONArray();
        this.h = 5000;
        this.i = 0;
        this.j = 0;
        this.k = 0L;
        this.s = 28800000L;
        this.u = false;
        this.v = false;
        this.w = new Object();
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3676a);
            this.k = sharedPreferences.getLong(n, 0L);
            this.i = sharedPreferences.getInt(q, 0);
            this.j = sharedPreferences.getInt(r, 0);
            this.f3677b = new c();
        } catch (Throwable unused) {
        }
    }

    private void g(Object obj) {
        try {
            b(f3676a);
            d();
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
                String string = jSONObject.getString(com.umeng.analytics.pro.b.L);
                String string2 = jSONObject.getString(Config.CUSTOM_USER_ID);
                long j = jSONObject.getLong("ts");
                String[] strArrA = com.umeng.analytics.c.a(f3676a);
                if (strArrA != null && string.equals(strArrA[0]) && string2.equals(strArrA[1])) {
                    return;
                }
                q.a().a(f3676a, j);
                String strC = u.a().c(f3676a);
                boolean zB = q.a().b(f3676a, j);
                com.umeng.analytics.c.a(f3676a, string, string2);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + strC);
                q.a().a(f3676a, j, true);
                if (zB) {
                    q.a().c(f3676a, j);
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private ReportPolicy.ReportStrategy f3689a = null;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f3690b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f3691c = -1;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3692d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f3693e = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private ABTest f3694f;

        public c() {
            this.f3694f = null;
            this.f3694f = ABTest.getService(k.f3676a);
        }

        public void a() {
            try {
                int[] iArrA = a(-1, -1);
                this.f3690b = iArrA[0];
                this.f3691c = iArrA[1];
            } catch (Throwable unused) {
            }
        }

        protected void b() {
            int iA;
            Defcon service = Defcon.getService(k.f3676a);
            if (service.isOpen()) {
                ReportPolicy.ReportStrategy reportStrategy = this.f3689a;
                this.f3689a = (reportStrategy instanceof ReportPolicy.DefconPolicy) && reportStrategy.isValid() ? this.f3689a : new ReportPolicy.DefconPolicy(StatTracer.getInstance(k.f3676a), service);
            } else {
                boolean z = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.f3676a, "integrated_test", k.t)).intValue() == 1;
                if (UMConfigure.isDebugLog() && z && !MLog.DEBUG) {
                    UMLog.mutlInfo(h.J, 3, "\\|", null, null);
                }
                if (MLog.DEBUG && z) {
                    this.f3689a = new ReportPolicy.DebugPolicy(StatTracer.getInstance(k.f3676a));
                } else if (this.f3694f.isInTest() && "RPT".equals(this.f3694f.getTestName())) {
                    if (this.f3694f.getTestPolicy() == 6) {
                        if (Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.f3676a, "test_report_interval", k.t)).intValue() != -1) {
                            iA = a(90000);
                        } else {
                            iA = this.f3691c;
                            if (iA <= 0) {
                                iA = this.f3693e;
                            }
                        }
                    } else {
                        iA = 0;
                    }
                    this.f3689a = b(this.f3694f.getTestPolicy(), iA);
                } else {
                    int i = this.f3692d;
                    int i2 = this.f3693e;
                    int i3 = this.f3690b;
                    if (i3 != -1) {
                        i2 = this.f3691c;
                        i = i3;
                    }
                    this.f3689a = b(i, i2);
                }
            }
            if (UMConfigure.isDebugLog()) {
                try {
                    if (this.f3689a instanceof ReportPolicy.ReportAtLaunch) {
                        UMLog.mutlInfo(h.H, 3, "", null, null);
                    } else if (this.f3689a instanceof ReportPolicy.ReportByInterval) {
                        UMLog.mutlInfo(h.I, 3, "", new String[]{"@"}, new String[]{String.valueOf(((ReportPolicy.ReportByInterval) this.f3689a).getReportInterval() / 1000)});
                    } else if (this.f3689a instanceof ReportPolicy.DebugPolicy) {
                        UMLog.mutlInfo(h.K, 3, "", null, null);
                    } else if (this.f3689a instanceof ReportPolicy.ReportQuasiRealtime) {
                        String[] strArr = {String.valueOf(((ReportPolicy.ReportQuasiRealtime) this.f3689a).getReportInterval() / 1000)};
                        UMLog uMLog = UMConfigure.umDebugLog;
                        UMLog.mutlInfo(h.L, 3, "", new String[]{"@"}, strArr);
                    } else {
                        boolean z2 = this.f3689a instanceof ReportPolicy.DefconPolicy;
                    }
                } catch (Throwable unused) {
                }
            }
        }

        public ReportPolicy.ReportStrategy c() {
            b();
            return this.f3689a;
        }

        public int[] a(int i, int i2) {
            int iIntValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.f3676a, "report_policy", k.t)).intValue();
            int iIntValue2 = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.f3676a, "report_interval", k.t)).intValue();
            if (iIntValue == -1 || !ReportPolicy.isValid(iIntValue)) {
                return new int[]{i, i2};
            }
            if (6 == iIntValue) {
                int i3 = 90;
                if (iIntValue2 != -1 && iIntValue2 >= 90 && iIntValue2 <= 86400) {
                    i3 = iIntValue2;
                }
                return new int[]{iIntValue, i3 * TimeConstants.SEC};
            }
            if (11 != iIntValue) {
                return new int[]{i, i2};
            }
            int i4 = 15;
            if (iIntValue2 != -1 && iIntValue2 >= 15 && iIntValue2 <= 3600) {
                i4 = iIntValue2;
            }
            return new int[]{iIntValue, i4 * TimeConstants.SEC};
        }

        public int a(int i) {
            int iIntValue = Integer.valueOf(UMEnvelopeBuild.imprintProperty(k.f3676a, "test_report_interval", k.t)).intValue();
            return (iIntValue == -1 || iIntValue < 90 || iIntValue > 86400) ? i : iIntValue * TimeConstants.SEC;
        }

        private ReportPolicy.ReportStrategy b(int i, int i2) {
            if (i == 0) {
                ReportPolicy.ReportStrategy reportStrategy = this.f3689a;
                return reportStrategy instanceof ReportPolicy.ReportRealtime ? reportStrategy : new ReportPolicy.ReportRealtime();
            }
            if (i == 1) {
                ReportPolicy.ReportStrategy reportStrategy2 = this.f3689a;
                return reportStrategy2 instanceof ReportPolicy.ReportAtLaunch ? reportStrategy2 : new ReportPolicy.ReportAtLaunch();
            }
            if (i == 4) {
                ReportPolicy.ReportStrategy reportStrategy3 = this.f3689a;
                return reportStrategy3 instanceof ReportPolicy.ReportDaily ? reportStrategy3 : new ReportPolicy.ReportDaily(StatTracer.getInstance(k.f3676a));
            }
            if (i == 5) {
                ReportPolicy.ReportStrategy reportStrategy4 = this.f3689a;
                return reportStrategy4 instanceof ReportPolicy.ReportWifiOnly ? reportStrategy4 : new ReportPolicy.ReportWifiOnly(k.f3676a);
            }
            if (i == 6) {
                ReportPolicy.ReportStrategy reportStrategy5 = this.f3689a;
                if (reportStrategy5 instanceof ReportPolicy.ReportByInterval) {
                    ((ReportPolicy.ReportByInterval) reportStrategy5).setReportInterval(i2);
                    return reportStrategy5;
                }
                return new ReportPolicy.ReportByInterval(StatTracer.getInstance(k.f3676a), i2);
            }
            if (i == 8) {
                ReportPolicy.ReportStrategy reportStrategy6 = this.f3689a;
                return reportStrategy6 instanceof ReportPolicy.SmartPolicy ? reportStrategy6 : new ReportPolicy.SmartPolicy(StatTracer.getInstance(k.f3676a));
            }
            if (i != 11) {
                ReportPolicy.ReportStrategy reportStrategy7 = this.f3689a;
                return reportStrategy7 instanceof ReportPolicy.ReportAtLaunch ? reportStrategy7 : new ReportPolicy.ReportAtLaunch();
            }
            ReportPolicy.ReportStrategy reportStrategy8 = this.f3689a;
            if (reportStrategy8 instanceof ReportPolicy.ReportQuasiRealtime) {
                ((ReportPolicy.ReportQuasiRealtime) reportStrategy8).setReportInterval(i2);
                return reportStrategy8;
            }
            ReportPolicy.ReportQuasiRealtime reportQuasiRealtime = new ReportPolicy.ReportQuasiRealtime();
            reportQuasiRealtime.setReportInterval(i2);
            return reportQuasiRealtime;
        }
    }

    private boolean c(boolean z) {
        if (t()) {
            return true;
        }
        if (this.f3677b == null) {
            this.f3677b = new c();
        }
        this.f3677b.a();
        ReportPolicy.ReportStrategy reportStrategyC = this.f3677b.c();
        MLog.d("Report policy : " + reportStrategyC.getClass().getSimpleName());
        boolean zShouldSendMessage = reportStrategyC.shouldSendMessage(z);
        if (zShouldSendMessage) {
            if (((reportStrategyC instanceof ReportPolicy.ReportByInterval) || (reportStrategyC instanceof ReportPolicy.DebugPolicy) || (reportStrategyC instanceof ReportPolicy.ReportQuasiRealtime)) && q()) {
                d();
            }
            if ((reportStrategyC instanceof ReportPolicy.DefconPolicy) && q()) {
                d();
            }
        }
        return zShouldSendMessage;
    }

    private void h(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject == null || jSONObject.length() <= 0 || !jSONObject.has("__ii")) {
                return;
            }
            String strOptString = jSONObject.optString("__ii");
            jSONObject.remove("__ii");
            if (TextUtils.isEmpty(strOptString)) {
                return;
            }
            g.a(f3676a).a(strOptString, obj.toString(), 2);
        } catch (Throwable unused) {
        }
    }

    public void a() {
        if (f3676a != null) {
            synchronized (this.w) {
                if (this.u) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> network is now available, rebuild instant session data packet.");
                    UMWorkDispatch.sendEvent(f3676a, a.l, CoreProtocol.getInstance(f3676a), null);
                }
            }
            synchronized (this.w) {
                if (this.v) {
                    UMWorkDispatch.sendEvent(f3676a, a.m, CoreProtocol.getInstance(f3676a), null);
                }
            }
        }
    }

    /* JADX INFO: compiled from: CoreProtocolImpl.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Map<String, Object> f3695a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3696b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3697c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f3698d;

        private d() {
            this.f3695a = null;
            this.f3696b = null;
            this.f3697c = null;
            this.f3698d = 0L;
        }

        public Map<String, Object> a() {
            return this.f3695a;
        }

        public String b() {
            return this.f3697c;
        }

        public String c() {
            return this.f3696b;
        }

        public long d() {
            return this.f3698d;
        }

        public d(String str, Map<String, Object> map, String str2, long j) {
            this.f3695a = null;
            this.f3696b = null;
            this.f3697c = null;
            this.f3698d = 0L;
            this.f3695a = map;
            this.f3696b = str;
            this.f3698d = j;
            this.f3697c = str2;
        }
    }

    private void f(Object obj) {
        try {
            JSONObject jSONObject = (JSONObject) obj;
            if (jSONObject != null && jSONObject.length() > 0) {
                long j = jSONObject.getLong("ts");
                b(f3676a);
                d();
                String[] strArrA = com.umeng.analytics.c.a(f3676a);
                if (strArrA == null || TextUtils.isEmpty(strArrA[0]) || TextUtils.isEmpty(strArrA[1])) {
                    return;
                }
                q.a().a(f3676a, j);
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> onProfileSignIn: force generate new session: session id = " + u.a().c(f3676a));
                boolean zB = q.a().b(f3676a, j);
                com.umeng.analytics.c.b(f3676a);
                q.a().a(f3676a, j, true);
                if (zB) {
                    q.a().c(f3676a, j);
                }
            }
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    private void d(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        JSONObject jSONObjectOptJSONObject2;
        try {
            if (jSONObject.getJSONObject("header").has(com.umeng.analytics.pro.b.ay)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (jSONObject.has("analytics")) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject("analytics");
                    if (jSONObject2.has(com.umeng.analytics.pro.b.n) && (jSONObjectOptJSONObject2 = jSONObject2.getJSONArray(com.umeng.analytics.pro.b.n).optJSONObject(0)) != null) {
                        String strOptString = jSONObjectOptJSONObject2.optString("id");
                        if (!TextUtils.isEmpty(strOptString)) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: really delete instant session data");
                            g.a(f3676a).b(strOptString);
                        }
                    }
                }
                g.a(f3676a).b();
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> removeAllInstantData: send INSTANT_SESSION_START_CONTINUE event because OVERSIZE.");
                UMWorkDispatch.sendEvent(f3676a, a.l, CoreProtocol.getInstance(f3676a), null);
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (jSONObjectOptJSONObject = jSONObject.optJSONObject("analytics")) != null && jSONObjectOptJSONObject.length() > 0 && jSONObjectOptJSONObject.has(com.umeng.analytics.pro.b.n)) {
                g.a(f3676a).a(true, false);
            }
            g.a(f3676a).b();
        } catch (Exception unused) {
        }
    }

    private void e(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        try {
            if (jSONObject.getJSONObject("header").has(com.umeng.analytics.pro.b.ay)) {
                if (jSONObject.has("content")) {
                    jSONObject = jSONObject.getJSONObject("content");
                }
                if (jSONObject.has("analytics")) {
                    if (jSONObject.getJSONObject("analytics").has(com.umeng.analytics.pro.b.n)) {
                        g.a(f3676a).i();
                        g.a(f3676a).h();
                        g.a(f3676a).b(true, false);
                        g.a(f3676a).a();
                        return;
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> Error, Should not go to this branch.");
                    return;
                }
                return;
            }
            if (jSONObject.has("content")) {
                jSONObject = jSONObject.getJSONObject("content");
            }
            if (jSONObject.has("analytics") && (jSONObjectOptJSONObject = jSONObject.optJSONObject("analytics")) != null && jSONObjectOptJSONObject.length() > 0) {
                if (jSONObjectOptJSONObject.has(com.umeng.analytics.pro.b.n)) {
                    g.a(f3676a).b(true, false);
                }
                if (jSONObjectOptJSONObject.has(com.umeng.analytics.pro.b.R) || jSONObjectOptJSONObject.has(com.umeng.analytics.pro.b.S)) {
                    g.a(f3676a).h();
                }
                if (jSONObjectOptJSONObject.has(com.umeng.analytics.pro.b.N)) {
                    g.a(f3676a).i();
                }
            }
            g.a(f3676a).a();
        } catch (Exception unused) {
        }
    }

    private void c(JSONObject jSONObject) {
        JSONObject jSONObject2;
        String str = "appkey";
        if (jSONObject == null) {
            return;
        }
        try {
            if (jSONObject.length() <= 0) {
                return;
            }
            JSONObject jSONObject3 = new JSONObject();
            if (jSONObject.has("analytics")) {
                JSONObject jSONObject4 = jSONObject.getJSONObject("analytics");
                if (jSONObject4.has(com.umeng.analytics.pro.b.R)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.R, jSONObject4.getJSONArray(com.umeng.analytics.pro.b.R));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.S)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.S, jSONObject4.getJSONArray(com.umeng.analytics.pro.b.S));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.N)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.N, jSONObject4.getJSONArray(com.umeng.analytics.pro.b.N));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.n)) {
                    JSONArray jSONArray = new JSONArray();
                    int i = 0;
                    for (JSONArray jSONArray2 = jSONObject4.getJSONArray(com.umeng.analytics.pro.b.n); i < jSONArray2.length(); jSONArray2 = jSONArray2) {
                        JSONObject jSONObject5 = jSONArray2.getJSONObject(i);
                        if (jSONObject5 != null && jSONObject5.length() > 0) {
                            if (jSONObject5.has(com.umeng.analytics.pro.b.t)) {
                                jSONObject5.remove(com.umeng.analytics.pro.b.t);
                            }
                            jSONArray.put(jSONObject5);
                        }
                        i++;
                    }
                    jSONObject3.put(com.umeng.analytics.pro.b.n, jSONArray);
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.H)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.H, jSONObject4.getJSONObject(com.umeng.analytics.pro.b.H));
                }
                if (jSONObject4.has(com.umeng.analytics.pro.b.K)) {
                    jSONObject3.put(com.umeng.analytics.pro.b.K, jSONObject4.getJSONObject(com.umeng.analytics.pro.b.K));
                }
            } else {
                str = "appkey";
            }
            if (jSONObject.has("dplus")) {
                jSONObject3.put("dplus", jSONObject.getJSONObject("dplus"));
            }
            if (jSONObject.has("header") && jSONObject.has("header") && (jSONObject2 = jSONObject.getJSONObject("header")) != null && jSONObject2.length() > 0) {
                if (jSONObject2.has("sdk_version")) {
                    jSONObject3.put("sdk_version", jSONObject2.getString("sdk_version"));
                }
                if (jSONObject2.has("device_id")) {
                    jSONObject3.put("device_id", jSONObject2.getString("device_id"));
                }
                if (jSONObject2.has("device_model")) {
                    jSONObject3.put("device_model", jSONObject2.getString("device_model"));
                }
                if (jSONObject2.has("version_code")) {
                    jSONObject3.put(Config.INPUT_DEF_VERSION, jSONObject2.getInt("version_code"));
                }
                String str2 = str;
                if (jSONObject2.has(str2)) {
                    jSONObject3.put(str2, jSONObject2.getString(str2));
                }
                if (jSONObject2.has("channel")) {
                    jSONObject3.put("channel", jSONObject2.getString("channel"));
                }
            }
            if (jSONObject3.length() > 0) {
                MLog.d("constructMessage:" + jSONObject3.toString());
                UMRTLog.i(UMRTLog.RTLOG_TAG, "constructMessage: " + jSONObject3.toString());
            }
        } catch (Throwable th) {
            MLog.e(th);
        }
    }

    public void a(Object obj, int i) {
        try {
            switch (i) {
                case a.f3682a /* 4097 */:
                    if (UMGlobalContext.getInstance().isMainProcess(f3676a)) {
                        if (obj != null) {
                            e(obj);
                        }
                        if (t.equals(((JSONObject) obj).optString("__i"))) {
                            return;
                        }
                        a(false);
                        return;
                    }
                    UMProcessDBHelper.getInstance(f3676a).insertEventsInSubProcess(UMFrUtils.getSubProcessName(f3676a), new JSONArray().put(obj));
                    return;
                case a.f3683b /* 4098 */:
                    if (obj != null) {
                        e(obj);
                    }
                    if (t.equals(((JSONObject) obj).optString("__i"))) {
                        return;
                    }
                    a(false);
                    return;
                case a.f3684c /* 4099 */:
                    r.a(f3676a);
                    return;
                case a.f3685d /* 4100 */:
                    j.a(f3676a);
                    return;
                case a.f3686e /* 4101 */:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNIN");
                    g(obj);
                    return;
                case a.f3687f /* 4102 */:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> PROFILE_SIGNOFF");
                    f(obj);
                    return;
                case a.g /* 4103 */:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> START_SESSION");
                    q.a().a(f3676a, obj);
                    synchronized (this.w) {
                        this.v = true;
                        break;
                    }
                    return;
                case a.h /* 4104 */:
                    q.a().c(f3676a, obj);
                    return;
                case a.i /* 4105 */:
                    d();
                    return;
                case a.j /* 4106 */:
                    h(obj);
                    return;
                default:
                    switch (i) {
                        case a.k /* 4352 */:
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> INSTANT_SESSION_START");
                            q.a().b(f3676a, obj);
                            synchronized (this.w) {
                                this.u = true;
                                break;
                            }
                            return;
                        case a.l /* 4353 */:
                            a(obj, true);
                            return;
                        case a.m /* 4354 */:
                            c();
                            return;
                        default:
                            switch (i) {
                                case a.p /* 8195 */:
                                    com.umeng.analytics.b.a().a(obj);
                                    return;
                                case a.q /* 8196 */:
                                    com.umeng.analytics.b.a().m();
                                    return;
                                case a.r /* 8197 */:
                                    com.umeng.analytics.b.a().k();
                                    return;
                                case a.s /* 8198 */:
                                    if (TextUtils.isEmpty(q.a().b())) {
                                        return;
                                    }
                                    i();
                                    return;
                                case a.t /* 8199 */:
                                case a.u /* 8200 */:
                                    com.umeng.analytics.b.a().b(obj);
                                    return;
                                case a.v /* 8201 */:
                                    com.umeng.analytics.b.a().b((Object) null);
                                    return;
                                case a.w /* 8202 */:
                                    h();
                                    return;
                                default:
                                    return;
                            }
                    }
            }
        } catch (Throwable unused) {
        }
    }

    private JSONObject b(JSONObject jSONObject, long j) {
        try {
            if (m.a(jSONObject) <= j) {
                return jSONObject;
            }
            jSONObject = null;
            g.a(f3676a).a(true, false);
            g.a(f3676a).b();
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> Instant session packet overload !!! ");
            return null;
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private void b(JSONObject jSONObject) {
        try {
            if (!g.a(f3676a).e()) {
                JSONObject jSONObjectG = g.a(f3676a).g();
                if (jSONObjectG != null) {
                    String strOptString = jSONObjectG.optString("__av");
                    String strOptString2 = jSONObjectG.optString("__vc");
                    if (TextUtils.isEmpty(strOptString)) {
                        jSONObject.put("app_version", UMUtils.getAppVersionName(f3676a));
                    } else {
                        jSONObject.put("app_version", strOptString);
                    }
                    if (TextUtils.isEmpty(strOptString2)) {
                        jSONObject.put("version_code", UMUtils.getAppVersionCode(f3676a));
                        return;
                    } else {
                        jSONObject.put("version_code", strOptString2);
                        return;
                    }
                }
                return;
            }
            jSONObject.put("app_version", UMUtils.getAppVersionName(f3676a));
            jSONObject.put("version_code", UMUtils.getAppVersionCode(f3676a));
        } catch (Throwable unused) {
        }
    }

    public void d(Object obj) {
        s();
        n();
        o();
        a(true);
    }

    private boolean d(boolean z) {
        if (this.f3677b == null) {
            this.f3677b = new c();
        }
        ReportPolicy.ReportStrategy reportStrategyC = this.f3677b.c();
        if (!(reportStrategyC instanceof ReportPolicy.DefconPolicy)) {
            return true;
        }
        if (z) {
            return ((ReportPolicy.DefconPolicy) reportStrategyC).shouldSendMessageByInstant();
        }
        return reportStrategyC.shouldSendMessage(false);
    }

    public void e() {
        if (d(false)) {
            k();
        }
    }

    public JSONObject b(boolean z) {
        JSONArray jSONArray;
        JSONObject jSONObjectA = null;
        try {
            jSONObjectA = g.a(f3676a).a(z);
            if (jSONObjectA == null) {
                jSONObjectA = new JSONObject();
            } else {
                try {
                    boolean zHas = jSONObjectA.has(com.umeng.analytics.pro.b.n);
                    jSONObjectA = jSONObjectA;
                    if (zHas) {
                        JSONArray jSONArray2 = jSONObjectA.getJSONArray(com.umeng.analytics.pro.b.n);
                        JSONArray jSONArray3 = new JSONArray();
                        int i = 0;
                        while (i < jSONArray2.length()) {
                            JSONObject jSONObject = (JSONObject) jSONArray2.get(i);
                            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(com.umeng.analytics.pro.b.s);
                            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(com.umeng.analytics.pro.b.t);
                            if (jSONArrayOptJSONArray == null && jSONArrayOptJSONArray2 != null) {
                                jSONObject.put(com.umeng.analytics.pro.b.s, jSONArrayOptJSONArray2);
                                jSONObject.remove(com.umeng.analytics.pro.b.t);
                            }
                            if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray2 != null) {
                                ArrayList arrayList = new ArrayList();
                                for (int i2 = 0; i2 < jSONArrayOptJSONArray.length(); i2++) {
                                    arrayList.add((JSONObject) jSONArrayOptJSONArray.get(i2));
                                }
                                for (int i3 = 0; i3 < jSONArrayOptJSONArray2.length(); i3++) {
                                    arrayList.add((JSONObject) jSONArrayOptJSONArray2.get(i3));
                                }
                                JSONArraySortUtil jSONArraySortUtil = new JSONArraySortUtil();
                                jSONArraySortUtil.setCompareKey(com.umeng.analytics.pro.b.w);
                                Collections.sort(arrayList, jSONArraySortUtil);
                                JSONArray jSONArray4 = new JSONArray();
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    jSONArray4.put((JSONObject) it.next());
                                }
                                jSONObject.put(com.umeng.analytics.pro.b.s, jSONArray4);
                                jSONObject.remove(com.umeng.analytics.pro.b.t);
                            }
                            if (jSONObject.has(com.umeng.analytics.pro.b.s)) {
                                JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray(com.umeng.analytics.pro.b.s);
                                int i4 = 0;
                                while (i4 < jSONArrayOptJSONArray3.length()) {
                                    JSONObject jSONObject2 = jSONArrayOptJSONArray3.getJSONObject(i4);
                                    if (jSONObject2.has(com.umeng.analytics.pro.b.w)) {
                                        jSONObject2.put("ts", jSONObject2.getLong(com.umeng.analytics.pro.b.w));
                                        jSONObject2.remove(com.umeng.analytics.pro.b.w);
                                    }
                                    i4++;
                                    jSONArray2 = jSONArray2;
                                }
                                jSONArray = jSONArray2;
                                jSONObject.put(com.umeng.analytics.pro.b.s, jSONArrayOptJSONArray3);
                                jSONObject.put(com.umeng.analytics.pro.b.y, jSONArrayOptJSONArray3.length());
                            } else {
                                jSONArray = jSONArray2;
                                jSONObject.put(com.umeng.analytics.pro.b.y, 0);
                            }
                            jSONArray3.put(jSONObject);
                            i++;
                            jSONArray2 = jSONArray;
                        }
                        jSONObjectA.put(com.umeng.analytics.pro.b.n, jSONArray3);
                        jSONObjectA = jSONObjectA;
                    }
                } catch (Exception e2) {
                    MLog.e("merge pages error");
                    e2.printStackTrace();
                    jSONObjectA = jSONObjectA;
                }
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(f3676a);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    jSONObjectA.put("userlevel", string);
                }
            }
            String[] strArrA = com.umeng.analytics.c.a(f3676a);
            if (strArrA != null && !TextUtils.isEmpty(strArrA[0]) && !TextUtils.isEmpty(strArrA[1])) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put(com.umeng.analytics.pro.b.L, strArrA[0]);
                jSONObject3.put(com.umeng.analytics.pro.b.M, strArrA[1]);
                if (jSONObject3.length() > 0) {
                    jSONObjectA.put(com.umeng.analytics.pro.b.K, jSONObject3);
                }
            }
            if (ABTest.getService(f3676a).isInTest()) {
                JSONObject jSONObject4 = new JSONObject();
                jSONObject4.put(ABTest.getService(f3676a).getTestName(), ABTest.getService(f3676a).getGroupInfo());
                jSONObjectA.put(com.umeng.analytics.pro.b.J, jSONObject4);
            }
            n.a().a(jSONObjectA, f3676a);
        } catch (Throwable unused) {
        }
        return jSONObjectA;
    }

    public void a(boolean z) {
        if (c(z)) {
            if (!(this.f3677b.c() instanceof ReportPolicy.ReportQuasiRealtime)) {
                if (UMEnvelopeBuild.isReadyBuild(f3676a, UMLogDataProtocol.UMBusinessType.U_APP)) {
                    k();
                }
            } else {
                if (z) {
                    if (UMEnvelopeBuild.isOnline(f3676a)) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send session start in policy ReportQuasiRealtime.");
                        k();
                        return;
                    }
                    return;
                }
                if (UMEnvelopeBuild.isReadyBuild(f3676a, UMLogDataProtocol.UMBusinessType.U_APP)) {
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> send normal data in policy ReportQuasiRealtime.");
                    k();
                }
            }
        }
    }

    public void c(Object obj) {
        b(f3676a);
        d();
        if (d(false)) {
            k();
        }
    }

    public JSONObject a(long j) {
        if (TextUtils.isEmpty(u.a().d(f3676a))) {
            return null;
        }
        JSONObject jSONObjectB = b(false);
        int iA = n.a().a(f3676a);
        if (jSONObjectB.length() <= 0) {
            if (iA != 3) {
                return null;
            }
        } else if (jSONObjectB.length() == 1) {
            if (jSONObjectB.optJSONObject(com.umeng.analytics.pro.b.K) != null && iA != 3) {
                return null;
            }
            if (!TextUtils.isEmpty(jSONObjectB.optString("userlevel")) && iA != 3) {
                return null;
            }
        } else if (jSONObjectB.length() == 2 && jSONObjectB.optJSONObject(com.umeng.analytics.pro.b.K) != null && !TextUtils.isEmpty(jSONObjectB.optString("userlevel")) && iA != 3) {
            return null;
        }
        JSONObject jSONObjectM = m();
        if (jSONObjectM != null) {
            b(jSONObjectM);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (iA == 3) {
                jSONObject2.put("analytics", new JSONObject());
            } else if (jSONObjectB != null && jSONObjectB.length() > 0) {
                jSONObject2.put("analytics", jSONObjectB);
            }
            if (jSONObjectM != null && jSONObjectM.length() > 0) {
                jSONObject.put("header", jSONObjectM);
            }
            if (jSONObject2.length() > 0) {
                jSONObject.put("content", jSONObject2);
            }
            return a(jSONObject, j);
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private void a(JSONObject jSONObject) {
        JSONObject jSONObjectF;
        if (g.a(UMGlobalContext.getAppContext(f3676a)).c() || (jSONObjectF = g.a(UMGlobalContext.getAppContext(f3676a)).f()) == null) {
            return;
        }
        String strOptString = jSONObjectF.optString("__av");
        String strOptString2 = jSONObjectF.optString("__vc");
        try {
            if (TextUtils.isEmpty(strOptString)) {
                jSONObject.put("app_version", UMUtils.getAppVersionName(f3676a));
            } else {
                jSONObject.put("app_version", strOptString);
            }
            if (TextUtils.isEmpty(strOptString2)) {
                jSONObject.put("version_code", UMUtils.getAppVersionCode(f3676a));
            } else {
                jSONObject.put("version_code", strOptString2);
            }
        } catch (Throwable unused) {
        }
    }

    public void b(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() > 0 && (!jSONObject.has("exception") || 101 != jSONObject.getInt("exception"))) {
                    d(jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void b(Context context) {
        try {
            g.a(context).d();
            r();
        } catch (Throwable unused) {
        }
    }

    private JSONObject a(JSONObject jSONObject, long j) {
        try {
            if (m.a(jSONObject) <= j) {
                return jSONObject;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("header");
            jSONObject2.put(com.umeng.analytics.pro.b.ay, m.a(jSONObject));
            jSONObject.put("header", jSONObject2);
            return m.a(f3676a, j, jSONObject);
        } catch (Throwable unused) {
            return jSONObject;
        }
    }

    private boolean a(long j, int i) {
        if (j == 0) {
            return true;
        }
        if (System.currentTimeMillis() - j <= 28800000) {
            return i < 5000;
        }
        p();
        return true;
    }

    public void a(Object obj) {
        if (obj != null) {
            try {
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject.length() > 0 && (!jSONObject.has("exception") || 101 != jSONObject.getInt("exception"))) {
                    e(jSONObject);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void a(Object obj, boolean z) {
        if (z) {
            if (d(true)) {
                j();
            }
        } else if (UMEnvelopeBuild.isOnline(f3676a) && d(true)) {
            j();
        }
    }
}
