package com.umeng.analytics.pro;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AutoViewPageTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3669a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static JSONArray f3670e = new JSONArray();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Object f3671f = new Object();
    private Application g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Map<String, Long> f3674d = new HashMap();
    private boolean h = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    boolean f3672b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Application.ActivityLifecycleCallbacks f3673c = new Application.ActivityLifecycleCallbacks() { // from class: com.umeng.analytics.pro.j.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.AUTO) {
                return;
            }
            j.this.b(activity);
            com.umeng.analytics.b.a().i();
            j.this.f3672b = false;
            try {
                if (UMConfigure.isDebugLog()) {
                    String name = activity.getClass().getName();
                    if (name.equals(com.umeng.analytics.b.a().f())) {
                        return;
                    }
                    UMLog.aq(h.r, 0, "\\|", new String[]{"@"}, new String[]{name}, null, null);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION == MobclickAgent.PageMode.AUTO && activity != null) {
                j jVar = j.this;
                if (!jVar.f3672b) {
                    jVar.a(activity);
                    com.umeng.analytics.b.a().h();
                    return;
                }
                jVar.f3672b = false;
                if (TextUtils.isEmpty(j.f3669a)) {
                    j.f3669a = activity.getPackageName() + "." + activity.getLocalClassName();
                    return;
                }
                if (j.f3669a.equals(activity.getPackageName() + "." + activity.getLocalClassName())) {
                    return;
                }
                j.this.a(activity);
                com.umeng.analytics.b.a().h();
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (AnalyticsConfig.AUTO_ACTIVITY_PAGE_COLLECTION != MobclickAgent.PageMode.AUTO) {
                return;
            }
            try {
                if (UMConfigure.isDebugLog()) {
                    String name = activity.getClass().getName();
                    if (name.equals(com.umeng.analytics.b.a().g())) {
                        return;
                    }
                    UMLog.aq(h.s, 0, "\\|", new String[]{"@"}, new String[]{name.substring(0, name.length() - 1)}, null, null);
                }
            } catch (Throwable unused) {
            }
        }
    };

    public j(Context context) {
        this.g = null;
        synchronized (this) {
            if (this.g == null && context != null) {
                if (context instanceof Activity) {
                    this.g = ((Activity) context).getApplication();
                } else if (context instanceof Application) {
                    this.g = (Application) context;
                }
                if (this.g != null) {
                    b(context);
                }
            }
        }
    }

    public void c() {
        b((Activity) null);
        b();
    }

    private void b(Context context) {
        if (this.h) {
            return;
        }
        this.h = true;
        Application application = this.g;
        if (application == null || Build.VERSION.SDK_INT < 14) {
            return;
        }
        application.registerActivityLifecycleCallbacks(this.f3673c);
    }

    public boolean a() {
        return this.h;
    }

    public static void a(Context context) {
        String string;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (f3671f) {
                    string = f3670e.toString();
                    f3670e = new JSONArray();
                }
                if (string.length() > 0) {
                    jSONObject.put(c.d.a.f3632c, new JSONArray(string));
                    g.a(context).a(q.a().c(), jSONObject, g.a.AUTOPAGE);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void b() {
        this.h = false;
        Application application = this.g;
        if (application != null) {
            if (Build.VERSION.SDK_INT >= 14) {
                application.unregisterActivityLifecycleCallbacks(this.f3673c);
            }
            this.g = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Activity activity) {
        long j;
        long j2;
        try {
            synchronized (this.f3674d) {
                if (f3669a == null && activity != null) {
                    f3669a = activity.getPackageName() + "." + activity.getLocalClassName();
                }
                j = 0;
                if (TextUtils.isEmpty(f3669a) || !this.f3674d.containsKey(f3669a)) {
                    j2 = 0;
                } else {
                    long jLongValue = this.f3674d.get(f3669a).longValue();
                    long jCurrentTimeMillis = System.currentTimeMillis() - jLongValue;
                    this.f3674d.remove(f3669a);
                    j = jCurrentTimeMillis;
                    j2 = jLongValue;
                }
            }
            synchronized (f3671f) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(b.u, f3669a);
                    jSONObject.put("duration", j);
                    jSONObject.put(b.w, j2);
                    jSONObject.put("type", 0);
                    f3670e.put(jSONObject);
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Activity activity) {
        f3669a = activity.getPackageName() + "." + activity.getLocalClassName();
        synchronized (this.f3674d) {
            this.f3674d.put(f3669a, Long.valueOf(System.currentTimeMillis()));
        }
    }
}
