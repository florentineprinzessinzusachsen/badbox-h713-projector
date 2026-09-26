package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class BDStatCore {
    public static final int INVOKE_ACT = 1;
    public static final int INVOKE_API = 0;
    public static final int INVOKE_CUSTOME = 3;
    public static final int INVOKE_FRAG = 2;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static BDStatCore f3221a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3222b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SessionAnalysis f3224d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private EventAnalysis f3225e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Runnable f3226f;
    private Handler i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile boolean f3223c = false;
    private long g = 0;
    private volatile boolean h = false;

    private BDStatCore() {
        HandlerThread handlerThread = new HandlerThread("BDStatCore", 10);
        handlerThread.start();
        this.f3222b = new Handler(handlerThread.getLooper());
        this.f3224d = new SessionAnalysis();
        this.f3225e = new EventAnalysis();
        HandlerThread handlerThread2 = new HandlerThread("dataAnalyzeThread");
        handlerThread2.start();
        handlerThread2.setPriority(10);
        this.i = new Handler(handlerThread2.getLooper());
    }

    private void a(Context context) {
    }

    public static BDStatCore instance() {
        if (f3221a == null) {
            synchronized (BDStatCore.class) {
                if (f3221a == null) {
                    f3221a = new BDStatCore();
                }
            }
        }
        return f3221a;
    }

    public void autoTrackLaunchInfo(Context context, final LaunchInfo launchInfo, final boolean z) {
        if (launchInfo == null) {
            return;
        }
        if (z) {
            this.f3224d.autoTrackLaunchInfo(launchInfo, z);
        } else {
            init(context);
            this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.17
                @Override // java.lang.Runnable
                public void run() {
                    BDStatCore.this.f3224d.autoTrackLaunchInfo(launchInfo, z);
                }
            });
        }
    }

    public void autoTrackSessionEndTime(final Context context) {
        if (context == null) {
            return;
        }
        init(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.19
            @Override // java.lang.Runnable
            public void run() {
                BDStatCore.this.f3224d.autoTrackSessionEndTime(context, jCurrentTimeMillis);
            }
        });
    }

    public void autoTrackSessionStartTime(final Context context) {
        if (context == null) {
            return;
        }
        init(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.18
            @Override // java.lang.Runnable
            public void run() {
                BDStatCore.this.f3224d.autoTrackSessionStartTime(context, jCurrentTimeMillis);
            }
        });
    }

    public void cancelSendLogCheck() {
        Runnable runnable = this.f3226f;
        if (runnable != null) {
            this.f3222b.removeCallbacks(runnable);
        }
        this.f3226f = null;
    }

    public void doSendLogCheck(final Context context) {
        if (context == null) {
            return;
        }
        int sessionTimeOut = this.f3224d.getSessionTimeOut();
        this.f3226f = new Runnable() { // from class: com.baidu.mobstat.BDStatCore.20
            @Override // java.lang.Runnable
            public void run() {
                BDStatCore.this.f3224d.doSendLogCheck(context, System.currentTimeMillis());
            }
        };
        this.f3222b.postDelayed(this.f3226f, sessionTimeOut);
    }

    public JSONObject getPageSessionHead() {
        return this.f3224d.getPageSessionHead();
    }

    public int getSessionSize() {
        return this.f3224d.getSessionSize();
    }

    public long getSessionStartTime() {
        return this.f3224d.getSessionStartTime();
    }

    public void init(final Context context) {
        a(context);
        if (this.f3223c) {
            return;
        }
        ActivityLifeTask.registerActivityLifeCallback(context);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.1
            @Override // java.lang.Runnable
            public void run() {
                if (BDStatCore.this.f3223c) {
                    return;
                }
                PrefOperate.loadMetaDataConfig(context);
                BDStatCore.this.f3223c = true;
            }
        });
    }

    public void onErised(final Context context, String str, final String str2, final String str3) {
        if (this.f3224d.isSessionStart()) {
            return;
        }
        init(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.15
            @Override // java.lang.Runnable
            public void run() {
                DataCore.instance().init(context);
                EventAnalysis eventAnalysis = BDStatCore.this.f3225e;
                Context context2 = context;
                long j = jCurrentTimeMillis;
                eventAnalysis.onEvent(context2, j, str2, str3, 1, j, null, null, false);
                DataCore.instance().saveLogData(context, true, false, jCurrentTimeMillis, false);
                if (jCurrentTimeMillis - BDStatCore.this.g <= com.umeng.commonsdk.proguard.c.f3956d || !bb.p(context)) {
                    return;
                }
                LogSender.instance().onSend(context);
                BDStatCore.this.g = jCurrentTimeMillis;
            }
        });
    }

    public void onEvent(Context context, String str, String str2, int i, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        onEvent(context, str, str2, i, extraInfo, map, z, false);
    }

    public void onEventDuration(Context context, String str, String str2, long j, ExtraInfo extraInfo, Map<String, String> map, boolean z) {
        onEventDuration(context, str, str2, j, extraInfo, map, z, false);
    }

    public void onEventEnd(Context context, String str, String str2, ExtraInfo extraInfo, Map<String, String> map) {
        onEventEnd(context, str, str2, extraInfo, map, false);
    }

    public void onEventStart(final Context context, final String str, final String str2, final boolean z) {
        if (context == null) {
            return;
        }
        init(context);
        b(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.10
            @Override // java.lang.Runnable
            public void run() {
                String str3 = str2;
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                BDStatCore.this.f3224d.onSessionStart(context, jCurrentTimeMillis, z);
                am.c().a("Start event" + BDStatCore.this.a(str, str3, 1, -1L, null, null));
                BDStatCore.this.f3225e.onEventStart(context, str, str3, jCurrentTimeMillis);
            }
        });
    }

    public void onPageEnd(Context context, String str, ExtraInfo extraInfo) {
        onPageEnd(context, str, extraInfo, false);
    }

    public void onPageStart(final Context context, final String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        init(context);
        b(context);
        final int iA = a();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.21
            @Override // java.lang.Runnable
            public void run() {
                am.c().a("Start page view " + str);
                BDStatCore.this.f3224d.onPageStart(context, str, iA, jCurrentTimeMillis);
            }
        });
    }

    public void onPause(Activity activity, final boolean z, final ExtraInfo extraInfo) {
        final Context applicationContext;
        if (activity == null || (applicationContext = activity.getApplicationContext()) == null) {
            return;
        }
        init(applicationContext);
        final WeakReference weakReference = new WeakReference(activity);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.4
            @Override // java.lang.Runnable
            public void run() {
                Class<?> cls;
                Activity activity2 = (Activity) weakReference.get();
                if (activity2 == null || (cls = activity2.getClass()) == null) {
                    return;
                }
                String name = activity2.getClass().getName();
                String simpleName = activity2.getClass().getSimpleName();
                CharSequence title = activity2.getTitle();
                String string = title == null ? "" : title.toString();
                if (!z) {
                    am.c().a("End page view " + cls.getSimpleName());
                }
                BDStatCore.this.f3224d.onPageEndAct(applicationContext, name, simpleName, string, System.currentTimeMillis(), z, extraInfo);
            }
        });
    }

    public void onResume(Activity activity, final boolean z) {
        final Context applicationContext;
        if (activity == null || (applicationContext = activity.getApplicationContext()) == null) {
            return;
        }
        init(applicationContext);
        b(applicationContext);
        final WeakReference weakReference = new WeakReference(activity);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.23
            @Override // java.lang.Runnable
            public void run() {
                Class<?> cls;
                Activity activity2 = (Activity) weakReference.get();
                if (activity2 == null || (cls = activity2.getClass()) == null) {
                    return;
                }
                String name = activity2.getClass().getName();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (!z) {
                    am.c().a("Start page view " + cls.getSimpleName());
                }
                BDStatCore.this.f3224d.onPageStartAct(applicationContext, name, jCurrentTimeMillis, z);
            }
        });
    }

    public void onSessionStart(final Context context, final boolean z) {
        if (context == null) {
            return;
        }
        init(context);
        b(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.12
            @Override // java.lang.Runnable
            public void run() {
                BDStatCore.this.f3224d.onSessionStart(context, jCurrentTimeMillis, z);
            }
        });
    }

    public void onStat(final Context context, final String str) {
        if (this.f3224d.isSessionStart()) {
            return;
        }
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.14
            @Override // java.lang.Runnable
            public void run() {
                LogSender.instance().sendEmptyLogData(context, str);
            }
        });
    }

    public void setAutoSendLog(Context context, boolean z) {
        if (context == null) {
            return;
        }
        init(context);
        b(context);
        this.f3224d.setAutoSend(z);
    }

    public void setSessionTimeOut(int i) {
        this.f3224d.setSessionTimeOut(i);
    }

    public void onEvent(final Context context, final String str, final String str2, final int i, final ExtraInfo extraInfo, final Map<String, String> map, final boolean z, final boolean z2) {
        if (context == null) {
            return;
        }
        init(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.7
            @Override // java.lang.Runnable
            public void run() {
                String str3 = str2;
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                BDStatCore.this.f3224d.onSessionStart(context, jCurrentTimeMillis, z);
                am.c().a("Put event" + BDStatCore.this.a(str, str3, i, 0L, map, extraInfo));
                BDStatCore.this.f3225e.onEvent(context, BDStatCore.this.f3224d.getSessionStartTime(), str, str3, i, jCurrentTimeMillis, extraInfo, map, z2);
            }
        });
    }

    public void onEventDuration(final Context context, final String str, final String str2, final long j, final ExtraInfo extraInfo, final Map<String, String> map, final boolean z, final boolean z2) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        init(context);
        b(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.13
            @Override // java.lang.Runnable
            public void run() {
                String str3 = str2;
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                BDStatCore.this.f3224d.onSessionStart(context, jCurrentTimeMillis, z);
                am.c().a("Put event" + BDStatCore.this.a(str, str3, 1, j, map, extraInfo));
                BDStatCore.this.f3225e.onEventDuration(context, BDStatCore.this.f3224d.getSessionStartTime(), str, str3, jCurrentTimeMillis, j, extraInfo, map, z2);
            }
        });
    }

    public void onEventEnd(final Context context, final String str, final String str2, final ExtraInfo extraInfo, final Map<String, String> map, final boolean z) {
        if (context == null) {
            return;
        }
        init(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.11
            @Override // java.lang.Runnable
            public void run() {
                String str3 = str2;
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                long sessionStartTime = BDStatCore.this.f3224d.getSessionStartTime();
                am.c().a("End event" + BDStatCore.this.a(str, str3, 1, -1L, map, extraInfo));
                BDStatCore.this.f3225e.onEventEnd(context, sessionStartTime, str, str3, jCurrentTimeMillis, extraInfo, map, z);
            }
        });
    }

    public void onPageEnd(final Context context, final String str, final ExtraInfo extraInfo, final boolean z) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        init(context);
        final String strB = b();
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.22
            @Override // java.lang.Runnable
            public void run() {
                am.c().a("End page view " + str);
                SessionAnalysis sessionAnalysis = BDStatCore.this.f3224d;
                Context context2 = context;
                String str2 = str;
                sessionAnalysis.onPageEnd(context2, str2, str2, strB, jCurrentTimeMillis, extraInfo, z);
            }
        });
    }

    private String b() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (!TextUtils.isEmpty(className)) {
                Class<?> cls = null;
                try {
                    cls = Class.forName(className);
                } catch (Throwable unused) {
                }
                if (cls != null && Activity.class.isAssignableFrom(cls)) {
                    return cls.getName();
                }
            }
        }
        return "";
    }

    private int a() {
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        try {
            cls = Class.forName("android.app.Fragment");
        } catch (ClassNotFoundException unused) {
            cls = null;
        }
        try {
            cls2 = Class.forName("androidx.fragment.app.Fragment");
        } catch (ClassNotFoundException unused2) {
            cls2 = null;
        }
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 0; stackTrace != null && i < stackTrace.length; i++) {
            String className = stackTrace[i].getClassName();
            if (!TextUtils.isEmpty(className) && "onResume".equals(stackTrace[i].getMethodName())) {
                try {
                    cls3 = Class.forName(className);
                } catch (Throwable unused3) {
                    cls3 = null;
                }
                if (cls3 == null) {
                    continue;
                } else {
                    if (Activity.class.isAssignableFrom(cls3)) {
                        return 1;
                    }
                    if (cls != null && cls.isAssignableFrom(cls3)) {
                        return 2;
                    }
                    if (cls2 != null && cls2.isAssignableFrom(cls3)) {
                        return 2;
                    }
                }
            }
        }
        return 3;
    }

    public void onEvent(final Context context, final String str, final String str2, final int i, final String str3, final String str4, final int i2, final boolean z) {
        if (context == null) {
            return;
        }
        init(context);
        b(context);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.8
            @Override // java.lang.Runnable
            public void run() {
                String str5 = str2;
                if (TextUtils.isEmpty(str5)) {
                    str5 = "";
                }
                BDStatCore.this.f3224d.onSessionStart(context, jCurrentTimeMillis, false);
                am.c().a("Put event" + BDStatCore.this.a(str, str5, i, 0L, null, null));
                BDStatCore.this.f3225e.onEvent(context, BDStatCore.this.f3224d.getSessionStartTime(), str, str5, i, jCurrentTimeMillis, str3, str4, i2, z);
            }
        });
    }

    public void onPause(final Fragment fragment) {
        final androidx.fragment.app.c cVarF;
        final Context applicationContext;
        if (fragment == null || (cVarF = fragment.f()) == null || (applicationContext = cVarF.getApplicationContext()) == null) {
            return;
        }
        init(applicationContext);
        final WeakReference weakReference = new WeakReference(fragment);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.5
            @Override // java.lang.Runnable
            public void run() {
                Fragment fragment2 = (Fragment) weakReference.get();
                if (fragment2 == null || fragment2.getClass() == null) {
                    return;
                }
                String name = fragment.getClass().getName();
                String simpleName = fragment.getClass().getSimpleName();
                CharSequence title = cVarF.getTitle();
                String string = title == null ? "" : title.toString();
                am.c().a("End page view " + simpleName);
                BDStatCore.this.f3224d.onPageEndFrag(applicationContext, name, simpleName, string, System.currentTimeMillis());
            }
        });
    }

    public void onResume(Fragment fragment) {
        androidx.fragment.app.c cVarF;
        final Context applicationContext;
        if (fragment == null || (cVarF = fragment.f()) == null || (applicationContext = cVarF.getApplicationContext()) == null) {
            return;
        }
        init(applicationContext);
        b(applicationContext);
        final WeakReference weakReference = new WeakReference(fragment);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.2
            @Override // java.lang.Runnable
            public void run() {
                Class<?> cls;
                Fragment fragment2 = (Fragment) weakReference.get();
                if (fragment2 == null || (cls = fragment2.getClass()) == null) {
                    return;
                }
                String name = fragment2.getClass().getName();
                long jCurrentTimeMillis = System.currentTimeMillis();
                am.c().a("Start page view " + cls.getSimpleName());
                BDStatCore.this.f3224d.onPageStartFrag(applicationContext, name, jCurrentTimeMillis);
            }
        });
    }

    public void onEvent(Context context, String str, String str2, int i, long j, JSONArray jSONArray, JSONArray jSONArray2, String str3, String str4, String str5, Map<String, String> map) {
        onEvent(context, str, str2, i, j, jSONArray, jSONArray2, str3, str4, str5, map, false);
    }

    private void b(final Context context) {
        Handler handler;
        if (this.h || context == null || (handler = this.i) == null) {
            return;
        }
        handler.postDelayed(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.16
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!t.b(context)) {
                        t.a(2).a(context);
                    }
                } catch (Throwable unused) {
                }
                BDStatCore.this.h = false;
            }
        }, Config.BPLUS_DELAY_TIME);
        this.h = true;
    }

    public void onEvent(final Context context, final String str, final String str2, final int i, final long j, final JSONArray jSONArray, final JSONArray jSONArray2, final String str3, final String str4, final String str5, final Map<String, String> map, final boolean z) {
        if (context == null) {
            return;
        }
        init(context);
        b(context);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.9
            @Override // java.lang.Runnable
            public void run() {
                String str6 = str2;
                if (TextUtils.isEmpty(str6)) {
                    str6 = "";
                }
                BDStatCore.this.f3224d.onSessionStart(context, j, false);
                am.c().a("Put event" + BDStatCore.this.a(str, str6, i, 0L, map, null));
                BDStatCore.this.f3225e.onEvent(context, BDStatCore.this.f3224d.getSessionStartTime(), str, str6, i, j, jSONArray, jSONArray2, str3, str4, str5, map, z);
            }
        });
    }

    @SuppressLint({"NewApi"})
    public void onPause(final android.app.Fragment fragment) {
        final Activity activity;
        final Context applicationContext;
        if (fragment == null || (activity = fragment.getActivity()) == null || (applicationContext = activity.getApplicationContext()) == null) {
            return;
        }
        init(applicationContext);
        final WeakReference weakReference = new WeakReference(fragment);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.6
            @Override // java.lang.Runnable
            public void run() {
                android.app.Fragment fragment2 = (android.app.Fragment) weakReference.get();
                if (fragment2 == null || fragment2.getClass() == null) {
                    return;
                }
                String name = fragment.getClass().getName();
                String simpleName = fragment.getClass().getSimpleName();
                CharSequence title = activity.getTitle();
                String string = title == null ? "" : title.toString();
                am.c().a("End page view " + simpleName);
                BDStatCore.this.f3224d.onPageEndFrag(applicationContext, name, simpleName, string, System.currentTimeMillis());
            }
        });
    }

    @SuppressLint({"NewApi"})
    public void onResume(android.app.Fragment fragment) {
        Activity activity;
        final Context applicationContext;
        if (fragment == null || (activity = fragment.getActivity()) == null || (applicationContext = activity.getApplicationContext()) == null) {
            return;
        }
        init(applicationContext);
        b(applicationContext);
        final WeakReference weakReference = new WeakReference(fragment);
        this.f3222b.post(new Runnable() { // from class: com.baidu.mobstat.BDStatCore.3
            @Override // java.lang.Runnable
            public void run() {
                Class<?> cls;
                android.app.Fragment fragment2 = (android.app.Fragment) weakReference.get();
                if (fragment2 == null || (cls = fragment2.getClass()) == null) {
                    return;
                }
                String name = fragment2.getClass().getName();
                long jCurrentTimeMillis = System.currentTimeMillis();
                am.c().a("Start page view " + cls.getSimpleName());
                BDStatCore.this.f3224d.onPageStartFrag(applicationContext, name, jCurrentTimeMillis);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2, int i, long j, Map<String, String> map, ExtraInfo extraInfo) {
        JSONObject jSONObject;
        StringBuilder sb = new StringBuilder();
        if (map == null || map.size() == 0) {
            jSONObject = null;
        } else {
            try {
                jSONObject = new JSONObject(map.toString());
            } catch (Exception unused) {
                jSONObject = null;
            }
        }
        JSONObject jSONObjectDumpToJson = extraInfo != null ? extraInfo.dumpToJson() : null;
        sb.append(" eventId " + str + ", with eventLabel " + str2 + ", with acc " + i);
        if (j > 0) {
            sb.append(", with duration " + j);
        }
        if (jSONObject != null && jSONObject.length() != 0) {
            sb.append(", with attributes " + jSONObject);
        }
        if (jSONObjectDumpToJson != null && jSONObjectDumpToJson.length() != 0) {
            sb.append(", with extraInfo " + jSONObjectDumpToJson);
        }
        return sb.toString();
    }
}
