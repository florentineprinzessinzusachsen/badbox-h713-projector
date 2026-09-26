package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final e f3492a = new e();

    e() {
    }

    private void b(Context context, boolean z) {
        String strA;
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return;
        }
        List<PackageInfo> arrayList = new ArrayList<>(1);
        try {
            arrayList = packageManager.getInstalledPackages(0);
        } catch (Exception e2) {
            al.c().b(e2);
        }
        JSONArray jSONArray = new JSONArray();
        for (PackageInfo packageInfo : arrayList) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null) {
                boolean z2 = (applicationInfo.flags & 1) != 0;
                String string = applicationInfo.loadLabel(packageManager).toString();
                String str = applicationInfo.sourceDir;
                if (z == z2) {
                    a(z, string, str, packageInfo, jSONArray);
                }
            }
        }
        if (jSONArray.length() == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() + "|");
        sb.append(z ? 1 : 0);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_list", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            strA = ar.a.a(jSONObject.toString().getBytes());
        } catch (Exception unused) {
            strA = "";
        }
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        k.f3509b.a(System.currentTimeMillis(), strA);
    }

    public synchronized void a(Context context, boolean z) {
        b(context, z);
    }

    private void a(boolean z, String str, String str2, PackageInfo packageInfo, JSONArray jSONArray) {
        long j;
        if (z && packageInfo.packageName.startsWith("com.android.")) {
            return;
        }
        long j2 = 0;
        try {
            j = packageInfo.firstInstallTime;
        } catch (Throwable th) {
            al.c().b(th);
            j = 0;
        }
        try {
            j2 = packageInfo.lastUpdateTime;
        } catch (Throwable th2) {
            al.c().b(th2);
        }
        long jA = a(str2);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("n", packageInfo.packageName);
            jSONObject.put("a", str);
            jSONObject.put("v", String.valueOf(packageInfo.versionName));
            jSONObject.put("f", j);
            jSONObject.put("l", j2);
            jSONObject.put(Config.MODEL, jA);
            jSONArray.put(jSONObject);
        } catch (JSONException e2) {
            al.c().b(e2);
        }
    }

    private long a(String str) {
        if (str != null) {
            File file = new File(str);
            if (file.exists()) {
                return file.lastModified();
            }
        }
        return 0L;
    }
}
