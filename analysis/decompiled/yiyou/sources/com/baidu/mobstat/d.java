package com.baidu.mobstat;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static d f3487a = new d();

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f3488a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f3489b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3490c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f3491d;

        public a(String str, String str2, String str3, String str4) {
            str = str == null ? "" : str;
            str2 = str2 == null ? "" : str2;
            str3 = str3 == null ? "" : str3;
            str4 = str4 == null ? "" : str4;
            this.f3488a = str;
            this.f3489b = str2;
            this.f3490c = str3;
            this.f3491d = str4;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("n", this.f3488a);
                jSONObject.put("v", this.f3489b);
                jSONObject.put("c", this.f3490c);
                jSONObject.put("a", this.f3491d);
                return jSONObject;
            } catch (JSONException e2) {
                al.c().b(e2);
                return null;
            }
        }
    }

    private void b(Context context) {
        a(context, c(context));
    }

    private ArrayList<a> c(Context context) {
        ArrayList<a> arrayList = new ArrayList<>();
        for (PackageInfo packageInfo : d(context)) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null) {
                String str = packageInfo.packageName;
                String str2 = packageInfo.versionName;
                Signature[] signatureArr = packageInfo.signatures;
                String strA = ay.b.a(((signatureArr == null || signatureArr.length == 0) ? "" : signatureArr[0].toChars().toString()).getBytes());
                String str3 = applicationInfo.sourceDir;
                arrayList.add(new a(str, str2, strA, TextUtils.isEmpty(str3) ? "" : ay.b.a(new File(str3))));
            }
        }
        return arrayList;
    }

    private ArrayList<PackageInfo> d(Context context) {
        ArrayList<PackageInfo> arrayList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return arrayList;
        }
        List<PackageInfo> arrayList2 = new ArrayList<>(1);
        try {
            arrayList2 = packageManager.getInstalledPackages(64);
        } catch (Exception e2) {
            al.c().b(e2);
        }
        for (PackageInfo packageInfo : arrayList2) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo != null && (applicationInfo.flags & 1) == 0) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    public synchronized void a(Context context) {
        b(context);
    }

    private void a(Context context, ArrayList<a> arrayList) {
        String strA;
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
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
            jSONObject.put("app_apk", jSONArray);
            jSONObject.put("meta-data", sb.toString());
            strA = ar.a.a(jSONObject.toString().getBytes());
        } catch (Exception e2) {
            al.c().b(e2);
            strA = "";
        }
        if (TextUtils.isEmpty(strA)) {
            return;
        }
        k.f3512e.a(System.currentTimeMillis(), strA);
    }
}
