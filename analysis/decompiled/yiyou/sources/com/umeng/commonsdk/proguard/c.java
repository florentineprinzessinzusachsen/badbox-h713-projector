package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UMSysLocationCache.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3953a = "lng";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3954b = "lat";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3955c = "ts";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final long f3956d = 30000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3957e = 200;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final String f3958f = "UMSysLocationCache";
    private static boolean g = true;

    public static JSONArray b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.p, 0);
        JSONArray jSONArray = null;
        if (sharedPreferences == null) {
            return null;
        }
        try {
            String string = sharedPreferences.getString(com.umeng.commonsdk.internal.a.q, "");
            if (!TextUtils.isEmpty(string)) {
                jSONArray = new JSONArray(string);
            }
        } catch (JSONException e2) {
            ULog.i(f3958f, "e is " + e2);
        } catch (Throwable th) {
            ULog.i(f3958f, "e is " + th);
        }
        if (jSONArray != null) {
            ULog.i(f3958f, "get json str is " + jSONArray.toString());
        }
        return jSONArray;
    }

    public static void c(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.p, 0);
            if (sharedPreferences != null) {
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.putString(com.umeng.commonsdk.internal.a.q, "");
                editorEdit.commit();
                ULog.i(f3958f, "delete is ok~~");
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(final Context context) {
        ULog.i(f3958f, "begin location");
        if (context == null) {
            return;
        }
        try {
            new Thread(new Runnable() { // from class: com.umeng.commonsdk.proguard.c.1
                @Override // java.lang.Runnable
                public void run() {
                    while (c.g) {
                        try {
                            try {
                                JSONArray jSONArrayB = c.b(context);
                                if (jSONArrayB != null && jSONArrayB.length() >= 200) {
                                    boolean unused = c.g = false;
                                    return;
                                }
                                ULog.i(c.f3958f, "location status is ok, time is " + System.currentTimeMillis());
                                final b bVar = new b(context);
                                bVar.a(new d() { // from class: com.umeng.commonsdk.proguard.c.1.1
                                    /* JADX WARN: Multi-variable type inference failed */
                                    @Override // com.umeng.commonsdk.proguard.d
                                    public void a(Location location) {
                                        int i;
                                        String str = com.umeng.commonsdk.internal.a.s;
                                        if (location != null) {
                                            try {
                                                double longitude = location.getLongitude();
                                                double latitude = location.getLatitude();
                                                float accuracy = location.getAccuracy();
                                                double altitude = location.getAltitude();
                                                ULog.i(c.f3958f, "lon is " + longitude + ", lat is " + latitude + ", acc is " + accuracy + ", alt is " + altitude);
                                                if (longitude != 0.0d && latitude != 0.0d) {
                                                    long time = location.getTime();
                                                    JSONObject jSONObject = new JSONObject();
                                                    try {
                                                        jSONObject.put("lng", longitude);
                                                        jSONObject.put("lat", latitude);
                                                        jSONObject.put("ts", time);
                                                        jSONObject.put("acc", accuracy);
                                                        jSONObject.put("alt", altitude);
                                                    } catch (JSONException e2) {
                                                        ULog.i(c.f3958f, "e is " + e2);
                                                    }
                                                    ULog.i(c.f3958f, "locationJSONObject is " + jSONObject.toString());
                                                    SharedPreferences sharedPreferences = context.getSharedPreferences(com.umeng.commonsdk.internal.a.p, 0);
                                                    if (sharedPreferences != null) {
                                                        String string = sharedPreferences.getString(com.umeng.commonsdk.internal.a.r, "");
                                                        String string2 = sharedPreferences.getString(com.umeng.commonsdk.internal.a.s, "");
                                                        ULog.i(c.f3958f, "--->>> get lon is " + string + ", lat is " + string2);
                                                        try {
                                                            if (TextUtils.isEmpty(string) || Double.parseDouble(string) != longitude || TextUtils.isEmpty(string2) || Double.parseDouble(string2) != latitude) {
                                                                JSONArray jSONArrayB2 = c.b(context);
                                                                if (jSONArrayB2 == null) {
                                                                    jSONArrayB2 = new JSONArray();
                                                                }
                                                                jSONArrayB2.put(jSONObject);
                                                                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                                                                editorEdit.putString(com.umeng.commonsdk.internal.a.r, String.valueOf(longitude));
                                                                editorEdit.putString(com.umeng.commonsdk.internal.a.s, String.valueOf(latitude));
                                                                editorEdit.putString(com.umeng.commonsdk.internal.a.q, jSONArrayB2.toString());
                                                                editorEdit.commit();
                                                                Object[] objArr = new Object[1];
                                                                objArr[0] = "location put is ok~~";
                                                                ULog.i(c.f3958f, objArr);
                                                                str = "location put is ok~~";
                                                            } else {
                                                                Object[] objArr2 = new Object[1];
                                                                objArr2[0] = "location same";
                                                                ULog.i(c.f3958f, objArr2);
                                                                str = "location same";
                                                            }
                                                        } catch (Throwable th) {
                                                            th = th;
                                                            i = str;
                                                            Object[] objArr3 = new Object[i];
                                                            objArr3[0] = "" + th.getMessage();
                                                            ULog.i(c.f3958f, objArr3);
                                                        }
                                                    }
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                i = 1;
                                            }
                                        }
                                        bVar.a();
                                    }
                                });
                                try {
                                    Thread.sleep(c.f3956d);
                                } catch (Exception unused2) {
                                }
                            } catch (Throwable unused3) {
                                boolean unused4 = c.g = false;
                                return;
                            }
                        } catch (Throwable unused5) {
                            return;
                        }
                    }
                }
            }).start();
        } catch (Exception unused) {
        }
    }
}
