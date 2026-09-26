package com.baidu.mobstat;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Session {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile long f3400a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private volatile long f3401b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile long f3402c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile long f3403d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile long f3404e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private volatile int f3405f = 0;
    private List<a> g = new ArrayList();
    private volatile JSONObject h = null;

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f3406a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3407b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3408c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private long f3409d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f3410e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f3411f;
        private JSONObject g;
        private boolean h;

        public a(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2) {
            this.f3407b = str;
            this.f3408c = str2;
            this.f3406a = str3;
            this.f3409d = j;
            this.f3410e = j2;
            this.f3411f = z;
            this.g = extraInfo != null ? extraInfo.dumpToJson() : new JSONObject();
            this.h = z2;
        }

        public String a() {
            return this.f3407b;
        }

        public JSONObject e() {
            return this.g;
        }

        public boolean f() {
            return this.f3411f;
        }

        public void a(a aVar) {
            this.f3406a = aVar.f3406a;
            this.f3407b = aVar.f3407b;
            this.f3408c = aVar.f3408c;
            this.f3409d = aVar.f3409d;
            this.f3410e = aVar.f3410e;
            this.f3411f = aVar.f3411f;
            this.g = aVar.g;
            this.h = aVar.h;
        }

        public String b() {
            return this.f3408c;
        }

        public long c() {
            return this.f3409d;
        }

        public long d() {
            return this.f3410e;
        }
    }

    private void a(List<a> list, a aVar) {
        if (list == null || aVar == null) {
            return;
        }
        int size = list.size();
        if (size == 0) {
            list.add(aVar);
            return;
        }
        a aVar2 = list.get(size - 1);
        if (TextUtils.isEmpty(aVar2.f3406a) || TextUtils.isEmpty(aVar.f3406a)) {
            list.add(aVar);
            return;
        }
        if (!aVar2.f3406a.equals(aVar.f3406a) || aVar2.f3411f == aVar.f3411f) {
            list.add(aVar);
        } else if (aVar2.f3411f) {
            aVar2.a(aVar);
        }
    }

    public static JSONObject getPVJson(a aVar, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", aVar.a());
            jSONObject.put("d", aVar.c());
            long jD = aVar.d() - j;
            if (jD < 0) {
                jD = 0;
            }
            jSONObject.put("ps", jD);
            jSONObject.put("t", aVar.b());
            int i = 1;
            jSONObject.put("at", aVar.f() ? 1 : 0);
            JSONObject jSONObjectE = aVar.e();
            if (jSONObjectE != null && jSONObjectE.length() != 0) {
                jSONObject.put("ext", jSONObjectE);
            }
            if (!aVar.h) {
                i = 0;
            }
            jSONObject.put("h5", i);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public void addPageView(String str, String str2, String str3, long j, long j2, boolean z, ExtraInfo extraInfo, boolean z2) {
        a(this.g, new a(str, str2, str3, j, j2, z, extraInfo, z2));
    }

    public JSONObject constructJSONObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.f3400a);
            jSONObject.put("e", this.f3401b);
            jSONObject.put("i", this.f3404e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.f3402c == 0 ? this.f3400a : this.f3402c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.f3403d == 0 ? this.f3401b : this.f3403d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f3405f);
            if (this.h != null && this.h.length() != 0) {
                jSONObject.put(Config.LAUNCH, this.h);
            }
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.g.size(); i++) {
                jSONArray.put(getPVJson(this.g.get(i), this.f3400a));
            }
            jSONObject.put("p", jSONArray);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public JSONObject getPageSessionHead() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("s", this.f3400a);
            jSONObject.put("e", this.f3401b);
            jSONObject.put("i", this.f3404e);
            jSONObject.put("c", 1);
            jSONObject.put(Config.SESSTION_TRACK_START_TIME, this.f3402c == 0 ? this.f3400a : this.f3402c);
            jSONObject.put(Config.SESSTION_TRACK_END_TIME, this.f3403d == 0 ? this.f3401b : this.f3403d);
            jSONObject.put(Config.SESSTION_TRIGGER_CATEGORY, this.f3405f);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public long getStartTime() {
        return this.f3400a;
    }

    public boolean hasEnd() {
        return this.f3401b > 0;
    }

    public boolean hasStart() {
        return this.f3400a > 0;
    }

    public void reset() {
        this.f3400a = 0L;
        this.f3401b = 0L;
        this.f3402c = 0L;
        this.f3403d = 0L;
        this.f3405f = 0;
        this.g.clear();
    }

    public void setEndTime(long j) {
        this.f3401b = j;
    }

    public void setInvokeType(int i) {
        this.f3405f = i;
    }

    public void setLaunchInfo(JSONObject jSONObject) {
        this.h = jSONObject;
    }

    public void setStartTime(long j) {
        if (this.f3400a > 0) {
            return;
        }
        this.f3400a = j;
        this.f3404e = j;
    }

    public void setTrackEndTime(long j) {
        this.f3403d = j;
    }

    public void setTrackStartTime(long j) {
        if (this.f3402c > 0) {
            return;
        }
        this.f3402c = j;
    }

    public String toString() {
        return constructJSONObject().toString();
    }

    public void addPageView(a aVar) {
        a(this.g, aVar);
    }
}
