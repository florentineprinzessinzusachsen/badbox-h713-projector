package com.umeng.analytics;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.umeng.analytics.pro.g;
import com.umeng.analytics.pro.h;
import com.umeng.analytics.pro.i;
import com.umeng.analytics.pro.j;
import com.umeng.analytics.pro.k;
import com.umeng.analytics.pro.l;
import com.umeng.analytics.pro.o;
import com.umeng.analytics.pro.p;
import com.umeng.analytics.pro.q;
import com.umeng.analytics.pro.r;
import com.umeng.analytics.pro.u;
import com.umeng.analytics.pro.x;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: InternalAgent.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements p {
    private static final String A = "umsp_3";
    private static final String B = "umsp_4";
    private static final String C = "umsp_5";
    private static final String h = "sp_uapp";
    private static final String i = "prepp_uapp";
    private static final int o = 128;
    private static final int p = 256;
    private static String q = "";
    private static String r = "";
    private static final String s = "ekv_bl";
    private static final String t = "ekv_bl_ver";
    private static final String v = "ekv_wl";
    private static final String w = "ekv_wl_ver";
    private static final String y = "umsp_1";
    private static final String z = "umsp_2";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f3563a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private x f3564b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private l f3565c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private r f3566d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private i f3567e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private q f3568f;
    private j g;
    private boolean j;
    private volatile JSONObject k;
    private volatile JSONObject l;
    private volatile JSONObject m;
    private boolean n;
    private com.umeng.analytics.filter.a u;
    private com.umeng.analytics.filter.b x;

    /* JADX INFO: compiled from: InternalAgent.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final b f3569a = new b();

        private a() {
        }
    }

    public static b a() {
        return a.f3569a;
    }

    private void i(Context context) {
        try {
            if (context == null) {
                MLog.e("unexpected null context in getNativeSuperProperties");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (this.k == null) {
                this.k = new JSONObject();
            }
            if (this.l == null) {
                this.l = new JSONObject();
            }
            String string = sharedPreferences.getString(i, null);
            if (!TextUtils.isEmpty(string)) {
                try {
                    this.m = new JSONObject(string);
                } catch (JSONException unused) {
                }
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
        } catch (Throwable unused2) {
        }
    }

    private void j(Context context) {
        try {
            Class.forName("com.umeng.visual.UMVisualAgent");
        } catch (ClassNotFoundException unused) {
            if (Build.VERSION.SDK_INT > 13) {
                UMWorkDispatch.sendEvent(context, k.a.s, CoreProtocol.getInstance(context), Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public JSONObject b() {
        return this.k;
    }

    public JSONObject c() {
        return this.m;
    }

    public JSONObject d() {
        return this.l;
    }

    public void e() {
        this.l = null;
    }

    public String f() {
        if (UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            return q;
        }
        MLog.e("getOnResumedActivityName can not be called in child process");
        return null;
    }

    public String g() {
        if (UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            return r;
        }
        MLog.e("getOnPausedActivityName can not be called in child process");
        return null;
    }

    public void h() {
        try {
            if (this.f3563a != null) {
                if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                    MLog.e("onStartSessionInternal can not be called in child process");
                    return;
                } else {
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    UMWorkDispatch.sendEvent(this.f3563a, k.a.k, CoreProtocol.getInstance(this.f3563a), Long.valueOf(jCurrentTimeMillis));
                    UMWorkDispatch.sendEvent(this.f3563a, k.a.g, CoreProtocol.getInstance(this.f3563a), Long.valueOf(jCurrentTimeMillis));
                }
            }
            if (this.f3564b != null) {
                this.f3564b.a();
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void k() {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("unregisterSuperPropertyByCoreProtocol can not be called in child process");
                return;
            }
            if (this.k == null || this.f3563a == null) {
                this.k = new JSONObject();
            } else {
                SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(this.f3563a).edit();
                editorEdit.putString(h, this.k.toString());
                editorEdit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized JSONObject l() {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("getSuperPropertiesJSONObject can not be called in child process");
            return null;
        }
        if (this.k == null) {
            this.k = new JSONObject();
        }
        return this.k;
    }

    public synchronized void m() {
        try {
            if (this.f3563a != null) {
                if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                    MLog.e("clearSuperPropertiesByCoreProtocol can not be called in child process");
                } else {
                    SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(this.f3563a).edit();
                    editorEdit.remove(h);
                    editorEdit.commit();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private b() {
        this.f3563a = null;
        this.f3565c = new l();
        this.f3566d = new r();
        this.f3567e = new i();
        this.f3568f = q.a();
        this.g = null;
        this.j = false;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = false;
        this.u = null;
        this.x = null;
        this.f3565c.a(this);
    }

    public void a(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (this.u == null) {
                this.u = new com.umeng.analytics.filter.a("ekv_bl", "ekv_bl_ver");
                this.u.register(this.f3563a);
            }
            if (this.x == null) {
                this.x = new com.umeng.analytics.filter.b("ekv_wl", "ekv_wl_ver");
                this.x.register(this.f3563a);
            }
            if (UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                if (!this.j) {
                    this.j = true;
                    i(this.f3563a);
                }
                if (Build.VERSION.SDK_INT > 13) {
                    synchronized (this) {
                        if (!this.n) {
                            this.g = new j(context);
                            if (this.g.a()) {
                                this.n = true;
                            }
                        }
                    }
                } else {
                    this.n = true;
                }
                if (UMConfigure.isDebugLog()) {
                    UMLog.mutlInfo(h.B, 3, "", null, null);
                }
                if (Build.VERSION.SDK_INT > 13) {
                    UMWorkDispatch.sendEvent(this.f3563a, k.a.w, CoreProtocol.getInstance(this.f3563a), Long.valueOf(System.currentTimeMillis()));
                }
                UMWorkDispatch.registerConnStateObserver(CoreProtocol.getInstance(this.f3563a));
            }
        } catch (Throwable unused) {
        }
    }

    void b(String str) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("onPageEnd can not be called in child process");
            return;
        }
        try {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.f3566d.b(str);
            }
        } catch (Throwable unused) {
        }
    }

    void c(Context context) {
        if (context == null) {
            UMLog.aq(h.p, 0, "\\|");
            return;
        }
        if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
            return;
        }
        if (this.f3563a == null) {
            this.f3563a = context.getApplicationContext();
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("onPause can not be called in child process");
            return;
        }
        if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
            UMLog.aq(h.q, 2, "\\|");
        }
        try {
            if (!this.j || !this.n) {
                a(context);
            }
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_MANUAL) {
                this.f3567e.b(context.getClass().getName());
            }
            i();
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e("Exception occurred in Mobclick.onRause(). ", th);
            }
        }
        if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
            r = context.getClass().getName();
        }
    }

    void d(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("onKillProcess can not be called in child process");
                return;
            }
            if (this.g != null) {
                this.g.c();
            }
            if (this.f3567e != null) {
                this.f3567e.b();
            }
            if (this.f3566d != null) {
                this.f3566d.b();
            }
            if (this.f3563a != null) {
                if (this.f3568f != null) {
                    this.f3568f.c(this.f3563a, Long.valueOf(System.currentTimeMillis()));
                }
                k.a(this.f3563a).d();
                r.a(this.f3563a);
                j.a(this.f3563a);
                PreferenceWrapper.getDefault(this.f3563a).edit().commit();
            }
            UMWorkDispatch.Quit();
        } catch (Throwable unused) {
        }
    }

    public synchronized Object e(Context context, String str) {
        try {
            if (context == null) {
                UMLog.aq(h.ah, 0, "\\|");
                return null;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("getSuperProperty can not be called in child process");
                return null;
            }
            if (TextUtils.isEmpty(str)) {
                UMLog.aq(h.af, 0, "\\|");
                return null;
            }
            if (!str.equals(y) && !str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C)) {
                MLog.e("please check key or value, must be correct!");
                return null;
            }
            if (this.k == null) {
                this.k = new JSONObject();
            } else if (this.k.has(str)) {
                return this.k.opt(str);
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public synchronized void f(Context context) {
        try {
            if (context == null) {
                UMLog.aq(h.ag, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("clearSuperProperties can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            this.k = new JSONObject();
            UMWorkDispatch.sendEvent(this.f3563a, k.a.q, CoreProtocol.getInstance(this.f3563a), null);
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void g(Context context) {
        try {
            if (context == null) {
                UMLog.aq(h.ao, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("clearPreProperties can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (this.m.length() > 0) {
                UMWorkDispatch.sendEvent(this.f3563a, k.a.v, CoreProtocol.getInstance(this.f3563a), null);
            }
            this.m = new JSONObject();
        } catch (Throwable th) {
            throw th;
        }
    }

    void j() {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("onProfileSignOff can not be called in child process");
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ts", jCurrentTimeMillis);
            k.a(this.f3563a).a((Object) null, true);
            UMWorkDispatch.sendEvent(this.f3563a, k.a.f3687f, CoreProtocol.getInstance(this.f3563a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignOff", th);
            }
        }
    }

    void b(Context context) {
        if (context == null) {
            MLog.e("unexpected null context in onResume");
            return;
        }
        if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO) {
            return;
        }
        if (this.f3563a == null) {
            this.f3563a = context.getApplicationContext();
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("onResume can not be called in child process");
            return;
        }
        if (UMConfigure.isDebugLog() && !(context instanceof Activity)) {
            UMLog.aq(h.o, 2, "\\|");
        }
        try {
            if (!this.j || !this.n) {
                a(context);
            }
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_MANUAL) {
                this.f3567e.a(context.getClass().getName());
            }
            h();
            j(this.f3563a);
            if (UMConfigure.isDebugLog() && (context instanceof Activity)) {
                q = context.getClass().getName();
            }
        } catch (Throwable th) {
            MLog.e("Exception occurred in Mobclick.onResume(). ", th);
        }
    }

    public synchronized JSONObject h(Context context) {
        try {
            if (context == null) {
                UMLog.aq(h.ap, 0, "\\|");
                return null;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("getPreProperties can not be called in child process");
                return null;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
            JSONObject jSONObject = new JSONObject();
            if (this.m.length() > 0) {
                try {
                    jSONObject = new JSONObject(this.m.toString());
                } catch (JSONException unused) {
                }
            }
            return jSONObject;
        } catch (Throwable th) {
            throw th;
        }
    }

    public void i() {
        try {
            if (this.f3563a != null) {
                if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                    MLog.e("onEndSessionInternal can not be called in child process");
                    return;
                }
                UMWorkDispatch.sendEvent(this.f3563a, k.a.h, CoreProtocol.getInstance(this.f3563a), Long.valueOf(System.currentTimeMillis()));
                UMWorkDispatch.sendEvent(this.f3563a, k.a.f3685d, CoreProtocol.getInstance(this.f3563a), null);
                UMWorkDispatch.sendEvent(this.f3563a, k.a.f3684c, CoreProtocol.getInstance(this.f3563a), null);
                UMWorkDispatch.sendEvent(this.f3563a, k.a.i, CoreProtocol.getInstance(this.f3563a), null);
            }
        } catch (Throwable unused) {
        }
        x xVar = this.f3564b;
        if (xVar != null) {
            xVar.b();
        }
    }

    public synchronized void f(Context context, String str) {
        try {
            if (context == null) {
                UMLog.aq(h.am, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("unregisterPreProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
            if (str != null && str.length() > 0) {
                if (this.m.has(str)) {
                    this.m.remove(str);
                    UMWorkDispatch.sendEvent(this.f3563a, k.a.u, CoreProtocol.getInstance(this.f3563a), this.m.toString());
                } else if (UMConfigure.isDebugLog()) {
                    UMLog.aq(h.an, 0, "\\|");
                }
                return;
            }
            MLog.e("please check propertics, property is null!");
        } catch (Throwable th) {
            throw th;
        }
    }

    public synchronized void d(Context context, String str) {
        try {
            if (context == null) {
                UMLog.aq(h.ag, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("unregisterSuperProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (TextUtils.isEmpty(str)) {
                UMLog.aq(h.af, 0, "\\|");
                return;
            }
            if (!str.equals(y) && !str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C)) {
                MLog.e("please check key or value, must be correct!");
                return;
            }
            if (this.k == null) {
                this.k = new JSONObject();
            }
            if (this.k.has(str)) {
                this.k.remove(str);
                UMWorkDispatch.sendEvent(this.f3563a, k.a.r, CoreProtocol.getInstance(this.f3563a), str);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private boolean c(String str) {
        if (this.u.enabled() && this.u.matchHit(str)) {
            return true;
        }
        if (!this.x.enabled()) {
            return false;
        }
        if (!this.x.matchHit(str)) {
            return true;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> white list match! id = " + str);
        return false;
    }

    public void b(Context context, String str) {
        try {
            if (context == null) {
                UMLog.aq(h.M, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("onDeepLinkReceived can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (!TextUtils.isEmpty(str)) {
                HashMap map = new HashMap();
                map.put(com.umeng.analytics.pro.b.aB, str);
                b(this.f3563a, com.umeng.analytics.pro.b.aA, map, -1L);
                return;
            }
            UMLog.aq(h.N, 0, "\\|");
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public synchronized String e(Context context) {
        try {
            if (context == null) {
                UMLog.aq(h.ah, 0, "\\|");
                return null;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("getSuperProperties can not be called in child process");
                return null;
            }
            if (this.k != null) {
                return this.k.toString();
            }
            this.k = new JSONObject();
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    void c(Context context, String str) {
        if (context == null) {
            UMLog.aq(h.z, 0, "\\|");
            return;
        }
        if (this.f3563a == null) {
            this.f3563a = context.getApplicationContext();
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setSecret can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(this.f3563a);
        }
        AnalyticsConfig.a(this.f3563a, str);
    }

    void a(String str) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("onPageStart can not be called in child process");
            return;
        }
        try {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.LEGACY_AUTO) {
                this.f3566d.a(str);
            }
        } catch (Throwable unused) {
        }
    }

    public void a(x xVar) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setSysListener can not be called in child process");
        } else {
            this.f3564b = xVar;
        }
    }

    public void a(Context context, int i2) {
        if (context == null) {
            MLog.e("unexpected null context in setVerticalType");
            return;
        }
        if (this.f3563a == null) {
            this.f3563a = context.getApplicationContext();
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setVerticalType can not be called in child process");
            return;
        }
        if (!this.j || !this.n) {
            a(this.f3563a);
        }
        AnalyticsConfig.a(this.f3563a, i2);
    }

    private void b(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (context == null) {
                MLog.e("context is null in onEventNoCheck, please check!");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (c(str)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                return;
            }
            String string = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                string = this.k.toString();
            }
            o.a(this.f3563a).a(str, map, j, string);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public void a(Context context, String str, HashMap<String, Object> map) {
        if (context == null) {
            return;
        }
        try {
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("onGKVEvent can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            String string = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                string = this.k.toString();
            }
            o.a(this.f3563a).a(str, map, string);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    public synchronized void b(Object obj) {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("updateNativePrePropertiesByCoreProtocol can not be called in child process");
                return;
            }
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(this.f3563a).edit();
            if (obj != null) {
                String str = (String) obj;
                if (editorEdit != null && !TextUtils.isEmpty(str)) {
                    editorEdit.putString(i, str).commit();
                }
            } else if (editorEdit != null) {
                editorEdit.remove(i).commit();
            }
        } catch (Throwable unused) {
        }
    }

    void a(Context context, String str) {
        if (context == null) {
            UMLog.aq(h.w, 0, "\\|");
            return;
        }
        if (this.f3563a == null) {
            this.f3563a = context.getApplicationContext();
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("reportError can not be called in child process");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            if (UMConfigure.isDebugLog()) {
                UMLog.aq(h.x, 0, "\\|");
                return;
            }
            return;
        }
        try {
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ts", System.currentTimeMillis());
            jSONObject.put(com.umeng.analytics.pro.b.P, 2);
            jSONObject.put(com.umeng.analytics.pro.b.Q, str);
            jSONObject.put("__ii", this.f3568f.c());
            UMWorkDispatch.sendEvent(this.f3563a, k.a.j, CoreProtocol.getInstance(this.f3563a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    private boolean b(String str, Object obj) {
        int length;
        try {
            if (TextUtils.isEmpty(str)) {
                MLog.e("key is " + str + ", please check key, illegal");
                return false;
            }
            try {
                length = str.getBytes("UTF-8").length;
            } catch (UnsupportedEncodingException unused) {
                length = 0;
            }
            if (length > o) {
                MLog.e("key length is " + length + ", please check key, illegal");
                return false;
            }
            if (obj instanceof String) {
                if (((String) obj).getBytes("UTF-8").length <= 256) {
                    return true;
                }
                MLog.e("value length is " + ((String) obj).getBytes("UTF-8").length + ", please check value, illegal");
                return false;
            }
            if ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof Float)) {
                return true;
            }
            MLog.e("value is " + obj + ", please check value, type illegal");
            return false;
        } catch (Throwable unused2) {
        }
    }

    void a(Context context, Throwable th) {
        if (context != null && th != null) {
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("reportError can not be called in child process");
                return;
            }
            try {
                if (!this.j || !this.n) {
                    a(this.f3563a);
                }
                a(this.f3563a, DataHelper.convertExceptionToString(th));
                return;
            } catch (Exception e2) {
                if (MLog.DEBUG) {
                    MLog.e(e2);
                    return;
                }
                return;
            }
        }
        UMLog.aq(h.y, 0, "\\|");
    }

    public void a(Context context, String str, String str2, long j, int i2) {
        if (context == null) {
            return;
        }
        try {
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (c(str)) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> filter ekv [" + str + "].");
                return;
            }
            String string = "";
            if (this.k == null) {
                this.k = new JSONObject();
            } else {
                string = this.k.toString();
            }
            o.a(this.f3563a).a(str, str2, j, i2, string);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    void a(Context context, String str, Map<String, Object> map, long j) {
        try {
            if (!TextUtils.isEmpty(str)) {
                if (Arrays.asList(com.umeng.analytics.pro.b.aC).contains(str)) {
                    UMLog.aq(h.f3664b, 0, "\\|");
                    return;
                }
                if (map.isEmpty()) {
                    UMLog.aq(h.f3666d, 0, "\\|");
                    return;
                }
                Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    if (Arrays.asList(com.umeng.analytics.pro.b.aC).contains(it.next().getKey())) {
                        UMLog.aq(h.f3667e, 0, "\\|");
                        return;
                    }
                }
                b(context, str, map, j);
                return;
            }
            UMLog.aq(h.f3665c, 0, "\\|");
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(th);
            }
        }
    }

    @Override // com.umeng.analytics.pro.p
    public void a(Throwable th) {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("onAppCrash can not be called in child process");
                UMWorkDispatch.Quit();
                return;
            }
            if (this.f3566d != null) {
                this.f3566d.b();
            }
            if (this.f3567e != null) {
                this.f3567e.b();
            }
            if (this.g != null) {
                this.g.c();
            }
            if (this.f3563a != null) {
                if (this.f3568f != null) {
                    this.f3568f.c(this.f3563a, Long.valueOf(System.currentTimeMillis()));
                }
                if (th != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONObject.put(com.umeng.analytics.pro.b.P, 1);
                    jSONObject.put(com.umeng.analytics.pro.b.Q, DataHelper.convertExceptionToString(th));
                    g.a(this.f3563a).a(this.f3568f.c(), jSONObject.toString(), 1);
                }
                k.a(this.f3563a).d();
                r.a(this.f3563a);
                j.a(this.f3563a);
                PreferenceWrapper.getDefault(this.f3563a).edit().commit();
            }
            UMWorkDispatch.Quit();
        } catch (Exception e2) {
            if (MLog.DEBUG) {
                MLog.e("Exception in onAppCrash", e2);
            }
        }
    }

    void a(String str, String str2) {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("onProfileSignIn can not be called in child process");
                return;
            }
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(com.umeng.analytics.pro.b.L, str);
            jSONObject.put(Config.CUSTOM_USER_ID, str2);
            jSONObject.put("ts", jCurrentTimeMillis);
            k.a(this.f3563a).a((Object) null, true);
            UMWorkDispatch.sendEvent(this.f3563a, k.a.f3686e, CoreProtocol.getInstance(this.f3563a), jSONObject);
        } catch (Throwable th) {
            if (MLog.DEBUG) {
                MLog.e(" Excepthon  in  onProfileSignIn", th);
            }
        }
    }

    void a(boolean z2) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setCatchUncaughtExceptions can not be called in child process");
        } else {
            if (AnalyticsConfig.CHANGE_CATCH_EXCEPTION_NOTALLOW) {
                return;
            }
            AnalyticsConfig.CATCH_EXCEPTION = z2;
        }
    }

    void a(GL10 gl10) {
        String[] gpu = UMUtils.getGPU(gl10);
        if (gpu.length == 2) {
            AnalyticsConfig.GPU_VENDER = gpu[0];
            AnalyticsConfig.GPU_RENDERER = gpu[1];
        }
    }

    void a(MobclickAgent.PageMode pageMode) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setPageCollectionMode can not be called in child process");
        } else {
            AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION = pageMode;
        }
    }

    void a(double d2, double d3) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setLocation can not be called in child process");
            return;
        }
        if (AnalyticsConfig.f3545a == null) {
            AnalyticsConfig.f3545a = new double[2];
        }
        double[] dArr = AnalyticsConfig.f3545a;
        dArr[0] = d2;
        dArr[1] = d3;
    }

    void a(Context context, MobclickAgent.EScenarioType eScenarioType) {
        if (context == null) {
            MLog.e("unexpected null context in setScenarioType");
            return;
        }
        if (this.f3563a == null) {
            this.f3563a = context.getApplicationContext();
        }
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setScenarioType can not be called in child process");
            return;
        }
        if (eScenarioType != null) {
            a(this.f3563a, eScenarioType.toValue());
        }
        if (this.j && this.n) {
            return;
        }
        a(this.f3563a);
    }

    void a(long j) {
        if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
            MLog.e("setSessionContinueMillis can not be called in child process");
        } else {
            AnalyticsConfig.kContinueSessionMillis = j;
            u.a().a(AnalyticsConfig.kContinueSessionMillis);
        }
    }

    public synchronized void a(Context context, String str, Object obj) {
        int i2 = 0;
        try {
            if (context == null) {
                UMLog.aq(h.ae, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("registerSuperProperty can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (!TextUtils.isEmpty(str) && obj != null) {
                if (!str.equals(y) && !str.equals(z) && !str.equals(A) && !str.equals(B) && !str.equals(C)) {
                    MLog.e("property name is " + str + ", please check key, must be correct!");
                    return;
                }
                if ((obj instanceof String) && !HelperUtils.checkStrLen(obj.toString(), 256)) {
                    MLog.e("property value is " + obj + ", please check value, lawless!");
                    return;
                }
                try {
                    if (this.k == null) {
                        this.k = new JSONObject();
                    }
                    new JSONObject();
                    if (obj.getClass().isArray()) {
                        if (obj instanceof String[]) {
                            String[] strArr = (String[]) obj;
                            if (strArr.length > 10) {
                                MLog.e("please check value, size is " + strArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray = new JSONArray();
                            while (i2 < strArr.length) {
                                if (strArr[i2] != null && HelperUtils.checkStrLen(strArr[i2], 256)) {
                                    jSONArray.put(strArr[i2]);
                                    i2++;
                                }
                                MLog.e("please check value, length is " + strArr[i2].length() + ", overlength 256!");
                                return;
                            }
                            this.k.put(str, jSONArray);
                        } else if (obj instanceof long[]) {
                            long[] jArr = (long[]) obj;
                            if (jArr.length > 10) {
                                MLog.e("please check value, size is " + jArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray2 = new JSONArray();
                            while (i2 < jArr.length) {
                                jSONArray2.put(jArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray2);
                        } else if (obj instanceof int[]) {
                            int[] iArr = (int[]) obj;
                            if (iArr.length > 10) {
                                MLog.e("please check value, size is " + iArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray3 = new JSONArray();
                            while (i2 < iArr.length) {
                                jSONArray3.put(iArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray3);
                        } else if (obj instanceof float[]) {
                            float[] fArr = (float[]) obj;
                            if (fArr.length > 10) {
                                MLog.e("please check value, size is " + fArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray4 = new JSONArray();
                            while (i2 < fArr.length) {
                                jSONArray4.put(fArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray4);
                        } else if (obj instanceof double[]) {
                            double[] dArr = (double[]) obj;
                            if (dArr.length > 10) {
                                MLog.e("please check value, size is " + dArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray5 = new JSONArray();
                            while (i2 < dArr.length) {
                                jSONArray5.put(dArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray5);
                        } else if (obj instanceof short[]) {
                            short[] sArr = (short[]) obj;
                            if (sArr.length > 10) {
                                MLog.e("please check value, size is " + sArr.length + ", overstep 10!");
                                return;
                            }
                            JSONArray jSONArray6 = new JSONArray();
                            while (i2 < sArr.length) {
                                jSONArray6.put((int) sArr[i2]);
                                i2++;
                            }
                            this.k.put(str, jSONArray6);
                        } else {
                            MLog.e("please check value, illegal type!");
                            return;
                        }
                    } else {
                        if (!(obj instanceof String) && !(obj instanceof Long) && !(obj instanceof Integer) && !(obj instanceof Float) && !(obj instanceof Double) && !(obj instanceof Short)) {
                            MLog.e("please check value, illegal type!");
                            return;
                        }
                        this.k.put(str, obj);
                    }
                } catch (Throwable unused) {
                }
                UMWorkDispatch.sendEvent(this.f3563a, k.a.p, CoreProtocol.getInstance(this.f3563a), this.k.toString());
                return;
            }
            UMLog.aq(h.af, 0, "\\|");
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a(String str, Object obj) {
        try {
            if (this.k == null) {
                this.k = new JSONObject();
            }
            new JSONObject();
            int i2 = 0;
            if (obj.getClass().isArray()) {
                if (obj instanceof String[]) {
                    String[] strArr = (String[]) obj;
                    if (strArr.length > 10) {
                        return;
                    }
                    JSONArray jSONArray = new JSONArray();
                    while (i2 < strArr.length) {
                        if (strArr[i2] != null && !HelperUtils.checkStrLen(strArr[i2], 256)) {
                            jSONArray.put(strArr[i2]);
                        }
                        i2++;
                    }
                    this.k.put(str, jSONArray);
                    return;
                }
                if (obj instanceof long[]) {
                    long[] jArr = (long[]) obj;
                    JSONArray jSONArray2 = new JSONArray();
                    while (i2 < jArr.length) {
                        jSONArray2.put(jArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray2);
                    return;
                }
                if (obj instanceof int[]) {
                    int[] iArr = (int[]) obj;
                    JSONArray jSONArray3 = new JSONArray();
                    while (i2 < iArr.length) {
                        jSONArray3.put(iArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray3);
                    return;
                }
                if (obj instanceof float[]) {
                    float[] fArr = (float[]) obj;
                    JSONArray jSONArray4 = new JSONArray();
                    while (i2 < fArr.length) {
                        jSONArray4.put(fArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray4);
                    return;
                }
                if (obj instanceof double[]) {
                    double[] dArr = (double[]) obj;
                    JSONArray jSONArray5 = new JSONArray();
                    while (i2 < dArr.length) {
                        jSONArray5.put(dArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray5);
                    return;
                }
                if (obj instanceof short[]) {
                    short[] sArr = (short[]) obj;
                    JSONArray jSONArray6 = new JSONArray();
                    while (i2 < sArr.length) {
                        jSONArray6.put((int) sArr[i2]);
                        i2++;
                    }
                    this.k.put(str, jSONArray6);
                    return;
                }
                return;
            }
            if (obj instanceof List) {
                List list = (List) obj;
                JSONArray jSONArray7 = new JSONArray();
                while (i2 < list.size()) {
                    Object obj2 = list.get(i2);
                    if ((obj2 instanceof String) || (obj2 instanceof Long) || (obj2 instanceof Integer) || (obj2 instanceof Float) || (obj2 instanceof Double) || (obj2 instanceof Short)) {
                        jSONArray7.put(list.get(i2));
                    }
                    i2++;
                }
                this.k.put(str, jSONArray7);
                return;
            }
            if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                this.k.put(str, obj);
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void a(Object obj) {
        try {
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("registerSuperPropertyByCoreProtocol can not be called in child process");
                return;
            }
            if (obj != null && this.f3563a != null) {
                String str = (String) obj;
                SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(this.f3563a).edit();
                if (editorEdit != null && !TextUtils.isEmpty(str)) {
                    editorEdit.putString(h, this.k.toString()).commit();
                }
            }
        } catch (Throwable unused) {
        }
    }

    public synchronized void a(Context context, List<String> list) {
        try {
            if (context == null) {
                UMLog.aq(h.ai, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("setFirstLaunchEvent can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            o.a(this.f3563a).a(list);
        } catch (Throwable th) {
            MLog.e(th);
        }
    }

    public synchronized void a(Context context, JSONObject jSONObject) {
        try {
            if (context == null) {
                UMLog.aq(h.ak, 0, "\\|");
                return;
            }
            if (this.f3563a == null) {
                this.f3563a = context.getApplicationContext();
            }
            if (!UMGlobalContext.getInstance().isMainProcess(this.f3563a)) {
                MLog.e("registerPreProperties can not be called in child process");
                return;
            }
            if (!this.j || !this.n) {
                a(this.f3563a);
            }
            if (this.m == null) {
                this.m = new JSONObject();
            }
            if (jSONObject != null && jSONObject.length() > 0) {
                JSONObject jSONObject2 = null;
                try {
                    jSONObject2 = new JSONObject(this.m.toString());
                } catch (Exception unused) {
                }
                if (jSONObject2 == null) {
                    jSONObject2 = new JSONObject();
                }
                Iterator<String> itKeys = jSONObject.keys();
                if (itKeys != null) {
                    while (itKeys.hasNext()) {
                        try {
                            String string = itKeys.next().toString();
                            Object obj = jSONObject.get(string);
                            if (b(string, obj)) {
                                jSONObject2.put(string, obj);
                                if (jSONObject2.length() > 10) {
                                    MLog.e("please check propertics, size overlength!");
                                    return;
                                }
                                continue;
                            } else {
                                return;
                            }
                        } catch (Exception unused2) {
                        }
                    }
                }
                this.m = jSONObject2;
                if (this.m.length() > 0) {
                    UMWorkDispatch.sendEvent(this.f3563a, k.a.t, CoreProtocol.getInstance(this.f3563a), this.m.toString());
                }
                return;
            }
            UMLog.aq(h.al, 0, "\\|");
        } catch (Throwable th) {
            throw th;
        }
    }
}
