package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final z f3544a = new z();

    private void c(Context context) {
        al.c().a("collectAPWithStretegy 1");
        y yVarA = y.a(context);
        long jA = yVarA.a(g.AP_LIST);
        long jCurrentTimeMillis = System.currentTimeMillis();
        long jE = yVarA.e();
        al.c().a("now time: " + jCurrentTimeMillis + ": last time: " + jA + "; time interval: " + jE);
        if (jA == 0 || jCurrentTimeMillis - jA > jE) {
            al.c().a("collectAPWithStretegy 2");
            c.a(context);
        }
    }

    private void d(Context context) {
        al.c().a("collectAPPListWithStretegy 1");
        long jCurrentTimeMillis = System.currentTimeMillis();
        y yVarA = y.a(context);
        long jA = yVarA.a(g.APP_USER_LIST);
        long jF = yVarA.f();
        al.c().a("now time: " + jCurrentTimeMillis + ": last time: " + jA + "; userInterval : " + jF);
        if (jA == 0 || jCurrentTimeMillis - jA > jF || !yVarA.a(jA)) {
            al.c().a("collectUserAPPListWithStretegy 2");
            c.a(context, false);
        }
        long jA2 = yVarA.a(g.APP_SYS_LIST);
        long jG = yVarA.g();
        al.c().a("now time: " + jCurrentTimeMillis + ": last time: " + jA2 + "; sysInterval : " + jG);
        if (jA2 == 0 || jCurrentTimeMillis - jA2 > jG) {
            al.c().a("collectSysAPPListWithStretegy 2");
            c.a(context, true);
        }
    }

    private void e(Context context) {
        al.c().a("collectAPPTraceWithStretegy 1");
        long jCurrentTimeMillis = System.currentTimeMillis();
        y yVarA = y.a(context);
        long jA = yVarA.a(g.APP_TRACE_HIS);
        long jI = yVarA.i();
        al.c().a("now time: " + jCurrentTimeMillis + ": last time: " + jA + "; time interval: " + jI);
        if (jA == 0 || jCurrentTimeMillis - jA > jI) {
            al.c().a("collectAPPTraceWithStretegy 2");
            c.b(context, false);
        }
    }

    private void f(Context context) {
        al.c().a("collectAPKWithStretegy 1");
        long jCurrentTimeMillis = System.currentTimeMillis();
        y yVarA = y.a(context);
        long jA = yVarA.a(g.APP_APK);
        long jH = yVarA.h();
        al.c().a("now time: " + jCurrentTimeMillis + ": last time: " + jA + "; interval : " + jH);
        if (jA == 0 || jCurrentTimeMillis - jA > jH) {
            al.c().a("collectAPKWithStretegy 2");
            c.b(context);
        }
    }

    private void g(Context context) throws Throwable {
        y.a(context).a(g.LAST_SEND, System.currentTimeMillis());
        JSONObject jSONObjectA = h.a(context);
        al.c().a("header: " + jSONObjectA);
        int i = 0;
        while (a()) {
            int i2 = i + 1;
            if (i > 0) {
                h.c(jSONObjectA);
            }
            b(context, jSONObjectA);
            i = i2;
        }
    }

    public void a(Context context, JSONObject jSONObject) throws Throwable {
        al.c().a("startDataAnynalyzed start");
        a(jSONObject);
        y yVarA = y.a(context);
        boolean zA = yVarA.a();
        al.c().a("is data collect closed:" + zA);
        if (!zA) {
            if (!k.f3508a.b(10000)) {
                c(context);
            }
            String str = android.os.Build.MANUFACTURER;
            int i = android.os.Build.VERSION.SDK_INT;
            boolean z = false;
            if (!TextUtils.isEmpty(str) && "huawei".equals(str.trim().toLowerCase()) && i >= 28) {
                z = true;
            }
            if (!k.f3509b.b(10000) && !z) {
                d(context);
            }
            if (!k.f3510c.b(10000) && !z) {
                e(context);
            }
            if (ab.f3429e && !k.f3512e.b(10000) && !z) {
                f(context);
            }
            boolean zP = bb.p(context);
            if (zP && yVarA.l()) {
                al.c().a("sendLog");
                g(context);
            } else if (zP) {
                al.c().a("can not sendLog due to time stratergy");
            } else {
                al.c().a("isWifiAvailable = false, will not sendLog");
            }
        }
        al.c().a("startDataAnynalyzed finished");
    }

    public void b(Context context, String str) {
        y.a(context).b(str);
    }

    private void b(Context context, JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        int length = 0;
        try {
            jSONObject2.put(Config.HEADER_PART, jSONObject);
            length = 0 + jSONObject.toString().length();
        } catch (JSONException e2) {
            al.c().a(e2);
        }
        al.c().a("APP_MEM");
        if (!y.a(context).b()) {
            String strV = bb.v(context);
            JSONArray jSONArray = new JSONArray();
            al.c().a(strV);
            jSONArray.put(strV);
            if (jSONArray.length() > 0) {
                try {
                    jSONObject2.put("app_mem3", jSONArray);
                    length += jSONArray.toString().length();
                } catch (JSONException e3) {
                    al.c().a(e3);
                }
            }
        }
        al.c().a("APP_APK");
        List<String> listA = k.f3512e.a(20480);
        JSONArray jSONArray2 = new JSONArray();
        for (String str : listA) {
            al.c().a(str);
            jSONArray2.put(str);
        }
        if (jSONArray2.length() > 0) {
            try {
                jSONObject2.put("app_apk3", jSONArray2);
                length += jSONArray2.toString().length();
            } catch (JSONException e4) {
                al.c().a(e4);
            }
        }
        al.c().a("APP_CHANGE");
        List<String> listA2 = k.f3511d.a(10240);
        JSONArray jSONArray3 = new JSONArray();
        for (String str2 : listA2) {
            al.c().a(str2);
            jSONArray3.put(str2);
        }
        if (jSONArray3.length() > 0) {
            try {
                jSONObject2.put("app_change3", jSONArray3);
                length += jSONArray3.toString().length();
            } catch (JSONException e5) {
                al.c().a(e5);
            }
        }
        al.c().a("APP_TRACE");
        List<String> listA3 = k.f3510c.a(15360);
        JSONArray jSONArray4 = new JSONArray();
        for (String str3 : listA3) {
            al.c().a(str3);
            jSONArray4.put(str3);
        }
        if (jSONArray4.length() > 0) {
            try {
                jSONObject2.put("app_trace3", jSONArray4);
                length += jSONArray4.toString().length();
            } catch (JSONException e6) {
                al.c().a(e6);
            }
        }
        al.c().a("APP_LIST");
        List<String> listA4 = k.f3509b.a(46080);
        JSONArray jSONArray5 = new JSONArray();
        for (String str4 : listA4) {
            al.c().a(str4);
            jSONArray5.put(str4);
        }
        if (jSONArray5.length() > 0) {
            try {
                jSONObject2.put("app_list3", jSONArray5);
                length += jSONArray5.toString().length();
            } catch (JSONException e7) {
                al.c().a(e7);
            }
        }
        al.c().a("AP_LIST");
        List<String> listA5 = k.f3508a.a(Config.MAX_CACHE_JSON_CAPACITY - length);
        JSONArray jSONArray6 = new JSONArray();
        for (String str5 : listA5) {
            al.c().a(str5);
            jSONArray6.put(str5);
        }
        if (jSONArray6.length() > 0) {
            try {
                jSONObject2.put("ap_list3", jSONArray6);
                length += jSONArray6.toString().length();
            } catch (JSONException e8) {
                al.c().a(e8);
            }
        }
        al.c().a("log in bytes is almost :" + length);
        JSONArray jSONArray7 = new JSONArray();
        jSONArray7.put(jSONObject2);
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put("payload", jSONArray7);
            s.a().a(context, jSONObject3.toString());
        } catch (Exception e9) {
            al.c().a(e9);
        }
    }

    private void a(JSONObject jSONObject) {
        ac acVar = new ac(jSONObject);
        ab.f3426b = acVar.f3430a;
        ab.f3427c = acVar.f3431b;
        ab.f3428d = acVar.f3432c;
    }

    public void a(Context context, String str) {
        y.a(context).a(str);
    }

    public void a(Context context, long j) {
        y.a(context).a(g.LAST_UPDATE, j);
    }

    private boolean a() {
        return (k.f3508a.b() && k.f3509b.b() && k.f3510c.b() && k.f3511d.b() && k.f3512e.b()) ? false : true;
    }

    public boolean a(Context context) {
        if (!bb.c().booleanValue()) {
            return false;
        }
        y yVarA = y.a(context);
        long jA = yVarA.a(g.LAST_UPDATE);
        long jC = yVarA.c();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - jA > jC) {
            al.c().a("need to update, checkWithLastUpdateTime lastUpdateTime =" + jA + "nowTime=" + jCurrentTimeMillis + ";timeInteveral=" + jC);
            return true;
        }
        al.c().a("no need to update, checkWithLastUpdateTime lastUpdateTime =" + jA + "nowTime=" + jCurrentTimeMillis + ";timeInteveral=" + jC);
        return false;
    }

    public boolean b(Context context) {
        return !y.a(context).a() || a(context);
    }
}
