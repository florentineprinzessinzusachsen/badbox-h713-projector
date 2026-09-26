package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: InfoPreference.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3846a = "info";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3847b = "a_na";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3848c = "a_st";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3849d = "a_ad";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3850e = "blueinfo";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3851f = "a_dc";
    private static final String g = "bssid";
    private static final String h = "ssid";
    private static final String i = "a_fcy";
    private static final String j = "a_hssid";
    private static final String k = "a_ip";
    private static final String l = "a_ls";
    private static final String m = "a_mac";
    private static final String n = "a_nid";
    private static final String o = "rssi";
    private static final String p = "sta";
    private static final String q = "ts";
    private static final String r = "wifiinfo";
    private static final String s = "ua";

    public static JSONObject a(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences == null) {
            return null;
        }
        try {
            String string = sharedPreferences.getString(f3850e, null);
            if (string != null) {
                return new JSONObject(string);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static JSONArray b(Context context) {
        String string;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            if (sharedPreferences == null || (string = sharedPreferences.getString(r, null)) == null) {
                return null;
            }
            return new JSONArray(string);
        } catch (Exception e2) {
            ULog.e(e2.getMessage());
            return null;
        }
    }

    public static void c(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(r).commit();
        }
    }

    public static String d(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString(s, null);
        }
        return null;
    }

    public static void a(Context context, Object obj) {
        if (obj != null) {
            try {
                a.b bVar = (a.b) obj;
                SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
                String string = null;
                if (sharedPreferences != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(f3847b, bVar.f3817c);
                    jSONObject.put(f3848c, bVar.f3816b);
                    jSONObject.put(f3849d, bVar.f3815a);
                    jSONObject.put("ts", System.currentTimeMillis());
                    string = jSONObject.toString();
                }
                if (string != null) {
                    sharedPreferences.edit().putString(f3850e, string).commit();
                }
            } catch (Exception e2) {
                ULog.e("saveBluetoothInfo:" + e2.getMessage());
            }
        }
    }

    public static void a(Context context, a.c cVar) {
        JSONArray jSONArray;
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
            String string = null;
            if (sharedPreferences != null) {
                String string2 = sharedPreferences.getString(r, null);
                if (string2 == null) {
                    jSONArray = new JSONArray();
                } else {
                    jSONArray = new JSONArray(string2);
                }
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f3851f, cVar.f3818a);
                jSONObject.put(g, cVar.f3819b);
                jSONObject.put(h, cVar.f3820c);
                jSONObject.put(i, cVar.f3821d);
                jSONObject.put(j, cVar.f3822e);
                jSONObject.put(k, cVar.f3823f);
                jSONObject.put(l, cVar.g);
                jSONObject.put(m, cVar.h);
                jSONObject.put(n, cVar.i);
                jSONObject.put(o, cVar.j);
                jSONObject.put(p, cVar.k);
                jSONObject.put("ts", cVar.l);
                jSONArray.put(jSONObject);
                string = jSONArray.toString();
            }
            if (string != null) {
                sharedPreferences.edit().putString(r, string).commit();
            }
        } catch (Exception e2) {
            ULog.e(e2.getMessage());
        }
    }

    public static void a(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("info", 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(s, str).commit();
        }
    }
}
