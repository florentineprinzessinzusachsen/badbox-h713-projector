package com.baidu.mobstat;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static f f3493a = new f();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3494b = "";

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f3495a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3496b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3497c;

        public a(String str, String str2, String str3) {
            this.f3495a = str == null ? "" : str;
            this.f3496b = str2 == null ? "" : str2;
            this.f3497c = str3 == null ? "" : str3;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", this.f3495a);
                jSONObject.put("v", this.f3496b);
                jSONObject.put(Config.DEVICE_WIDTH, this.f3497c);
                return jSONObject;
            } catch (JSONException e2) {
                al.c().b(e2);
                return null;
            }
        }

        public String b() {
            return this.f3495a;
        }
    }

    f() {
    }

    private boolean a(int i) {
        return i == 100 || i == 200 || i == 130;
    }

    private ArrayList<a> b(Context context, int i) {
        List<ActivityManager.RunningTaskInfo> runningTasks;
        try {
            runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(50);
        } catch (Exception e2) {
            al.c().b(e2);
            runningTasks = null;
        }
        if (runningTasks == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {
            if (linkedHashMap.size() > i) {
                break;
            }
            ComponentName componentName = runningTaskInfo.topActivity;
            if (componentName != null) {
                String packageName = componentName.getPackageName();
                if (!TextUtils.isEmpty(packageName) && !b(context, packageName) && !linkedHashMap.containsKey(packageName)) {
                    linkedHashMap.put(packageName, new a(packageName, a(context, packageName), ""));
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    private ArrayList<a> c(Context context, int i) {
        String[] strArr;
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return new ArrayList<>();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i2 = 0; i2 < runningAppProcesses.size() && linkedHashMap.size() <= i; i2++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i2);
            if (a(runningAppProcessInfo.importance) && (strArr = runningAppProcessInfo.pkgList) != null && strArr.length != 0) {
                String str = strArr[0];
                if (!TextUtils.isEmpty(str) && !b(context, str) && !linkedHashMap.containsKey(str)) {
                    linkedHashMap.put(str, new a(str, a(context, str), String.valueOf(runningAppProcessInfo.importance)));
                }
            }
        }
        return new ArrayList<>(linkedHashMap.values());
    }

    public synchronized void a(Context context, boolean z) {
        a(context, z, z ? 1 : 20);
    }

    private void a(Context context, boolean z, int i) {
        ArrayList<a> arrayListA = a(context, i);
        if (arrayListA == null || arrayListA.size() == 0) {
            return;
        }
        if (z) {
            String strB = arrayListA.get(0).b();
            if (a(strB, this.f3494b)) {
                this.f3494b = strB;
            }
        }
        a(context, arrayListA, z);
    }

    private ArrayList<a> a(Context context, int i) {
        if (android.os.Build.VERSION.SDK_INT >= 21) {
            return c(context, i);
        }
        return b(context, i);
    }

    private boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) || str.equals(this.f3494b)) ? false : true;
    }

    private String a(Context context, String str) {
        String str2;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        try {
            str2 = packageManager.getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e2) {
            al.c().b(e2);
            str2 = "";
        }
        return str2 == null ? "" : str2;
    }

    private void a(Context context, ArrayList<a> arrayList, boolean z) {
        String strA;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() + "|");
        sb.append(z ? 1 : 0);
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<a> it = arrayList.iterator();
            while (it.hasNext()) {
                JSONObject jSONObjectA = it.next().a();
                if (jSONObjectA != null) {
                    jSONArray.put(jSONObjectA);
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_trace", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            strA = ar.a.a(jSONObject.toString().getBytes());
        } catch (Exception e2) {
            al.c().b(e2);
            strA = "";
        }
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        k.f3510c.a(System.currentTimeMillis(), strA);
    }

    private boolean b(Context context, String str) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getPackageInfo(str, 0).applicationInfo;
            return (applicationInfo == null || (applicationInfo.flags & 1) == 0) ? false : true;
        } catch (PackageManager.NameNotFoundException e2) {
            al.c().b(e2);
            return false;
        }
    }
}
