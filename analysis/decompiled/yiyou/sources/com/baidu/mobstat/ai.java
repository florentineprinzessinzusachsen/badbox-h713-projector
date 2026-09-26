package com.baidu.mobstat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ai {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ai f3450b = new ai();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public a f3451a;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Handler f3453d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private volatile int f3454e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3455f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HandlerThread f3452c = new HandlerThread("fullTraceHandleThread");
    private JSONObject g = new JSONObject();
    private JSONArray h = new JSONArray();
    private JSONArray i = new JSONArray();
    private JSONArray j = new JSONArray();
    private JSONArray k = new JSONArray();
    private boolean l = false;

    public interface a {
        void a(JSONObject jSONObject);
    }

    private ai() {
        this.f3452c.start();
        this.f3452c.setPriority(10);
        this.f3453d = new Handler(this.f3452c.getLooper());
    }

    private void a(JSONObject jSONObject) {
    }

    private void c(Context context) {
        this.i = a(this.i, BDStatCore.instance().getPageSessionHead());
        a(context, false);
        b();
    }

    public void b(Context context, boolean z) {
        this.g = new JSONObject();
        a(context);
        this.i = new JSONArray();
        this.h = new JSONArray();
        this.j = new JSONArray();
        this.k = new JSONArray();
        if (!z) {
            ag.a().b();
        }
        b(context);
    }

    public static ai a() {
        return f3450b;
    }

    public void a(final Context context, final String str, final String str2, final int i, final long j, final String str3, final JSONArray jSONArray, final String str4, final JSONArray jSONArray2, final String str5, final Map<String, String> map, final boolean z, final JSONObject jSONObject, final String str6) {
        this.f3453d.post(new Runnable() { // from class: com.baidu.mobstat.ai.1
            @Override // java.lang.Runnable
            public void run() {
                long sessionStartTime = BDStatCore.instance().getSessionStartTime();
                if (sessionStartTime <= 0) {
                    return;
                }
                ai.this.a(context, sessionStartTime, str, str2, i, j, str3, jSONArray, str4, jSONArray2, str5, map, z, jSONObject, str6);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, long j, String str, String str2, int i, long j2, String str3, JSONArray jSONArray, String str4, JSONArray jSONArray2, String str5, Map<String, String> map, boolean z, JSONObject jSONObject, String str6) {
        a(context, EventAnalysis.getEvent(context, j, str, str2, i, j2, 0L, "", null, null, ap.a(str3), ap.a(str4), str5, Config.EventViewType.EDIT.getValue(), 3, null, map, ap.c(jSONArray), ap.d(jSONArray2), z, jSONObject, str6));
        b(context);
    }

    private void c() {
        this.f3455f = 0;
    }

    private void c(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception unused) {
        }
        try {
            jSONObject.put(Config.TRACE_PART, jSONObject2);
        } catch (Exception unused2) {
        }
    }

    public void b(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.HEADER_PART, new JSONObject(this.g.toString()));
            jSONObject.put(Config.PRINCIPAL_PART, new JSONArray(this.i.toString()));
            jSONObject.put(Config.EVENT_PART, new JSONArray(this.h.toString()));
            jSONObject.put(Config.FEED_LIST_PART, new JSONArray(this.j.toString()));
            jSONObject.put("sv", new JSONArray(this.k.toString()));
            jSONObject.put(Config.EVENT_PAGE_MAPPING, ag.a().a(ag.a.f3448b));
            jSONObject.put(Config.EVENT_PATH_MAPPING, ag.a().a(ag.a.f3447a));
            jSONObject.put(Config.FEED_LIST_MAPPING, ag.a().a(ag.a.f3449c));
        } catch (Exception unused) {
        }
        String string = jSONObject.toString();
        int length = string.getBytes().length;
        if (length >= 184320) {
            return;
        }
        this.f3454e = length;
        at.a(context, bb.s(context) + Config.STAT_FULL_CACHE_FILE_NAME, string, false);
    }

    private void a(Context context, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        if (ao.c().b()) {
            ao.c().a("putEvent: " + jSONObject.toString());
        }
        String string = jSONObject.toString();
        if (a(context, string)) {
            if (ao.c().b()) {
                ao.c().a("checkExceedLogLimit exceed:true; mCacheLogSize: " + this.f3454e + "; addedSize:" + string.length());
            }
            c(context);
        }
        EventAnalysis.doEventMerge(this.h, jSONObject);
    }

    private boolean a(Context context, String str) {
        return (str != null ? str.getBytes().length : 0) + this.f3454e > 184320;
    }

    public void a(Context context, boolean z) {
        if (z) {
            c();
        } else {
            b();
        }
        try {
            b(context, this.g);
        } catch (Exception unused) {
        }
        if (this.h.length() == 0 && this.i.length() == 0 && this.j.length() == 0 && this.k.length() == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.HEADER_PART, this.g);
        } catch (Exception unused2) {
        }
        try {
            jSONObject.put(Config.PRINCIPAL_PART, this.i);
        } catch (Exception unused3) {
        }
        try {
            jSONObject.put(Config.EVENT_PART, this.h);
        } catch (Exception unused4) {
        }
        try {
            jSONObject.put(Config.FEED_LIST_PART, this.j);
        } catch (Exception unused5) {
        }
        try {
            jSONObject.put("sv", this.k);
        } catch (Exception unused6) {
        }
        try {
            jSONObject.put(Config.EVENT_PAGE_MAPPING, ag.a().a(ag.a.f3448b));
        } catch (Exception unused7) {
        }
        try {
            jSONObject.put(Config.EVENT_PATH_MAPPING, ag.a().a(ag.a.f3447a));
        } catch (Exception unused8) {
        }
        try {
            jSONObject.put(Config.FEED_LIST_MAPPING, ag.a().a(ag.a.f3449c));
        } catch (Exception unused9) {
        }
        c(context, jSONObject);
        a(jSONObject);
        String string = jSONObject.toString();
        if (ao.c().b()) {
            ao.c().a("saveCurrentCacheToSend content: " + string);
        }
        b(context, string);
        b(context, !z);
        this.l = true;
    }

    private void b(Context context, JSONObject jSONObject) {
        CooperService.instance().getHeadObject().installHeader(context, jSONObject);
        try {
            jSONObject.put("t", System.currentTimeMillis());
            jSONObject.put(Config.SEQUENCE_INDEX, this.f3455f);
            jSONObject.put("ss", BDStatCore.instance().getSessionStartTime());
            jSONObject.put("at", "1");
            jSONObject.put("sign", CooperService.instance().getUUID());
        } catch (Exception unused) {
        }
    }

    private void b() {
        this.f3455f++;
    }

    private void b(Context context, String str) {
        LogSender.instance().saveLogData(context, str, true);
        if (this.f3451a != null) {
            try {
                this.f3451a.a(new JSONObject(str));
            } catch (Exception unused) {
            }
        }
    }

    public void a(Context context) {
        CooperService.instance().getHeadObject().installHeader(context, this.g);
    }

    private JSONArray a(JSONArray jSONArray, JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        JSONArray jSONArray2;
        JSONObject jSONObject4;
        if (jSONObject == null || jSONArray == null || jSONObject.optLong("s") <= 0) {
            return jSONArray;
        }
        JSONArray jSONArray3 = new JSONArray();
        if (jSONArray.length() == 0) {
            try {
                jSONObject2 = new JSONObject(jSONObject.toString());
                try {
                    jSONObject2.put("p", new JSONArray());
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                jSONObject2 = null;
            }
            if (jSONObject2 != null) {
                jSONArray3.put(jSONObject2);
            }
        } else {
            try {
                jSONObject3 = jSONArray.getJSONObject(0);
            } catch (Exception unused3) {
                jSONObject3 = null;
            }
            if (jSONObject3 != null) {
                try {
                    jSONArray2 = jSONObject3.getJSONArray("p");
                } catch (Exception unused4) {
                    jSONArray2 = null;
                }
            } else {
                jSONArray2 = null;
            }
            try {
                jSONObject4 = new JSONObject(jSONObject.toString());
                if (jSONArray2 != null) {
                    try {
                        jSONObject4.put("p", jSONArray2);
                    } catch (Exception unused5) {
                    }
                }
            } catch (Exception unused6) {
                jSONObject4 = null;
            }
            if (jSONObject4 != null) {
                jSONArray3.put(jSONObject4);
            }
        }
        return jSONArray3;
    }
}
