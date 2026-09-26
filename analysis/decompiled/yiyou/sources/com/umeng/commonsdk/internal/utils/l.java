package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* JADX INFO: compiled from: UMProbe.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f3874a = "UM_PROBE_DATA";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3875b = "_dsk_s";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f3876c = "_thm_z";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String f3877d = "_gdf_r";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Object f3878e = new Object();

    public static void b(final Context context) {
        if (c(context)) {
            return;
        }
        final String[] strArr = {"unknown", "unknown", "unknown"};
        new Thread() { // from class: com.umeng.commonsdk.internal.utils.l.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    strArr[0] = l.c();
                    strArr[1] = l.a();
                    strArr[2] = l.b();
                    ULog.i("diskType = " + strArr[0] + "; ThremalZone = " + strArr[1] + "; GoldFishRc = " + strArr[2]);
                    l.b(context, strArr);
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }.start();
    }

    public static boolean c(Context context) {
        SharedPreferences sharedPreferences;
        return (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(f3874a, 0)) == null || TextUtils.isEmpty(sharedPreferences.getString(f3875b, ""))) ? false : true;
    }

    public static String a(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f3874a, 0);
            if (sharedPreferences == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            synchronized (f3878e) {
                jSONObject.put(f3875b, sharedPreferences.getString(f3875b, ""));
                jSONObject.put(f3876c, sharedPreferences.getString(f3876c, ""));
                jSONObject.put(f3877d, sharedPreferences.getString(f3877d, ""));
            }
            return jSONObject.toString();
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Context context, String[] strArr) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getApplicationContext().getSharedPreferences(f3874a, 0)) == null) {
            return;
        }
        synchronized (f3878e) {
            sharedPreferences.edit().putString(f3875b, strArr[0]).putString(f3876c, strArr[1]).putString(f3877d, strArr[2]).commit();
        }
    }

    public static String c() {
        BufferedReader bufferedReader;
        String str;
        String line;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/diskstats"));
            do {
                try {
                    line = bufferedReader.readLine();
                    str = "mtd";
                    if (line == null) {
                        str = "unknown";
                        break;
                    }
                    if (line.contains("mmcblk")) {
                        str = "mmcblk";
                        break;
                    }
                    if (line.contains("sda")) {
                        str = "sda";
                        break;
                    }
                } catch (Throwable unused) {
                    str = "noper";
                }
            } while (!line.contains("mtd"));
        } catch (Throwable unused2) {
            bufferedReader = null;
        }
        if (bufferedReader != null) {
            try {
                bufferedReader.close();
            } catch (Throwable unused3) {
            }
        }
        return str;
    }

    public static String b() {
        int iA;
        try {
            iA = a("ls /", "goldfish");
        } catch (Throwable unused) {
            iA = -1;
        }
        if (iA > 0) {
            return "goldfish";
        }
        return iA < 0 ? "noper" : "unknown";
    }

    public static int a(String str, String str2) throws IOException {
        int i;
        Process processExec = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                i = -1;
                break;
            }
            if (line.contains(str2)) {
                i = 1;
                break;
            }
        }
        try {
            if (processExec.waitFor() != 0) {
                return -1;
            }
            return i;
        } catch (InterruptedException unused) {
            return -1;
        }
    }

    public static String a() {
        int iA;
        try {
            iA = a("ls /sys/class/thermal", "thermal_zone");
        } catch (Throwable unused) {
            iA = -1;
        }
        if (iA > 0) {
            return "thermal_zone";
        }
        return iA < 0 ? "noper" : "unknown";
    }
}
