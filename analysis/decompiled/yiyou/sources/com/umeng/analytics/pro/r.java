package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: ViewPageTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3721b = 5;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static JSONArray f3722c = new JSONArray();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Object f3723d = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Map<String, Long> f3725e = new HashMap();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Stack<String> f3724a = new Stack<>();

    public static void a(Context context) {
        String string;
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                synchronized (f3723d) {
                    string = f3722c.toString();
                    f3722c = new JSONArray();
                }
                if (string.length() > 0) {
                    jSONObject.put("__a", new JSONArray(string));
                    if (jSONObject.length() > 0) {
                        g.a(context).a(q.a().c(), jSONObject, g.a.PAGE);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    protected int a() {
        return 2;
    }

    public void b(String str) {
        Long l;
        Context appContext;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (!this.f3725e.containsKey(str)) {
            if (UMConfigure.isDebugLog() && this.f3724a.size() == 0) {
                UMLog.aq(h.G, 0, "\\|", new String[]{"@"}, new String[]{str}, null, null);
                return;
            }
            return;
        }
        synchronized (this.f3725e) {
            l = this.f3725e.get(str);
        }
        if (l == null) {
            return;
        }
        if (UMConfigure.isDebugLog() && this.f3724a.size() > 0 && str.equals(this.f3724a.peek())) {
            this.f3724a.pop();
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - l.longValue();
        synchronized (f3723d) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(b.u, str);
                jSONObject.put("duration", jCurrentTimeMillis);
                jSONObject.put(b.w, l);
                jSONObject.put("type", a());
                f3722c.put(jSONObject);
                if (f3722c.length() >= 5 && (appContext = UMGlobalContext.getAppContext(null)) != null) {
                    UMWorkDispatch.sendEvent(appContext, k.a.f3684c, CoreProtocol.getInstance(appContext), null);
                }
            } catch (Throwable unused) {
            }
        }
        if (!UMConfigure.isDebugLog() || this.f3724a.size() == 0) {
            return;
        }
        UMLog.aq(h.E, 0, "\\|", new String[]{"@"}, new String[]{str}, null, null);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (UMConfigure.isDebugLog() && this.f3724a.size() != 0) {
            UMLog.aq(h.F, 0, "\\|", new String[]{"@"}, new String[]{this.f3724a.peek()}, null, null);
        }
        synchronized (this.f3725e) {
            this.f3725e.put(str, Long.valueOf(System.currentTimeMillis()));
            if (UMConfigure.isDebugLog()) {
                this.f3724a.push(str);
            }
        }
    }

    public void b() {
        String key;
        synchronized (this.f3725e) {
            key = null;
            long j = 0;
            for (Map.Entry<String, Long> entry : this.f3725e.entrySet()) {
                if (entry.getValue().longValue() > j) {
                    long jLongValue = entry.getValue().longValue();
                    key = entry.getKey();
                    j = jLongValue;
                }
            }
        }
        if (key != null) {
            b(key);
        }
    }
}
