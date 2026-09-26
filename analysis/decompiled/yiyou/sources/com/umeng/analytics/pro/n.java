package com.umeng.analytics.pro;

import android.content.Context;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.service.UMGlobalContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DefconProcesser.java */
/* JADX INFO: loaded from: classes.dex */
public class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f3701a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f3702b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f3703c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f3704d = 3;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final long f3705e;

    /* JADX INFO: compiled from: DefconProcesser.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final n f3706a = new n();

        private a() {
        }
    }

    public static n a() {
        return a.f3706a;
    }

    private JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", u.a().d(UMGlobalContext.getAppContext(null)));
            jSONObject.put(b.p, jCurrentTimeMillis);
            jSONArray.put(jSONObject);
        } catch (JSONException unused) {
        }
        return jSONArray;
    }

    public void b(JSONObject jSONObject, Context context) {
        int iA = a(context);
        if (iA == 1) {
            if (jSONObject.has(b.K)) {
                jSONObject.remove(b.K);
            }
            if (jSONObject.has(b.n)) {
                try {
                    JSONArray jSONArray = jSONObject.getJSONArray(b.n);
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        if (jSONObject2.has(b.ar)) {
                            jSONObject2.remove(b.ar);
                        }
                        if (jSONObject2.has(b.as)) {
                            jSONObject2.remove(b.as);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
            g.a(context).a(false, true);
            return;
        }
        if (iA == 2) {
            if (jSONObject.has(b.K)) {
                jSONObject.remove(b.K);
            }
            if (jSONObject.has(b.n)) {
                jSONObject.remove(b.n);
            }
            try {
                jSONObject.put(b.n, c());
            } catch (Exception unused2) {
            }
            g.a(context).a(false, true);
            return;
        }
        if (iA == 3) {
            if (jSONObject.has(b.K)) {
                jSONObject.remove(b.K);
            }
            jSONObject.remove(b.n);
            g.a(context).a(false, true);
        }
    }

    private n() {
        this.f3705e = 60000L;
    }

    public int a(Context context) {
        return Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", String.valueOf(0))).intValue();
    }

    private void a(JSONObject jSONObject, boolean z) {
        if (!z && jSONObject.has(b.n)) {
            jSONObject.remove(b.n);
        }
        if (jSONObject.has(b.K)) {
            jSONObject.remove(b.K);
        }
        if (jSONObject.has(b.N)) {
            jSONObject.remove(b.N);
        }
        if (jSONObject.has(b.R)) {
            jSONObject.remove(b.R);
        }
        if (jSONObject.has(b.S)) {
            jSONObject.remove(b.S);
        }
        if (jSONObject.has(b.K)) {
            jSONObject.remove(b.K);
        }
        if (jSONObject.has("userlevel")) {
            jSONObject.remove("userlevel");
        }
    }

    public void a(JSONObject jSONObject, Context context) {
        int iA = a(context);
        if (iA == 1) {
            a(jSONObject, true);
            g.a(context).b(false, true);
        } else {
            if (iA == 2) {
                jSONObject.remove(b.n);
                try {
                    jSONObject.put(b.n, b());
                } catch (Exception unused) {
                }
                a(jSONObject, true);
                g.a(context).b(false, true);
                return;
            }
            if (iA == 3) {
                a(jSONObject, false);
                g.a(context).b(false, true);
            }
        }
    }

    private JSONArray b() {
        JSONArray jSONArray = new JSONArray();
        try {
            long jCurrentTimeMillis = System.currentTimeMillis();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", u.a().a(UMGlobalContext.getAppContext(null)));
            jSONObject.put(b.p, jCurrentTimeMillis);
            jSONObject.put(b.q, jCurrentTimeMillis + 60000);
            jSONObject.put("duration", 60000L);
            jSONArray.put(jSONObject);
        } catch (JSONException unused) {
        }
        return jSONArray;
    }
}
