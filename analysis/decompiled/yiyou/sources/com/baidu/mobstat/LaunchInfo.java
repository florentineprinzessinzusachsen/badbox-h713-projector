package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LaunchInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3368a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3369b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3370c;

    public static JSONObject getConvertedJson(int i, String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", String.valueOf(i));
                if (str == null) {
                    str = "";
                }
                jSONObject.put(Config.LAUNCH_REFERER, str);
                if (str2 == null) {
                    str2 = "";
                }
                jSONObject.put(Config.LAUNCH_INFO, str2);
                jSONObject.put("content", "");
                return jSONObject;
            } catch (Exception unused) {
                return jSONObject;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String getLauncherHomePkgName(Context context) {
        String strA = ap.a(context);
        return !TextUtils.isEmpty(strA) ? strA : "";
    }

    public int getLaunchType(Context context) {
        if (!TextUtils.isEmpty(this.f3368a)) {
            return 2;
        }
        String packageName = context != null ? context.getPackageName() : "";
        if (TextUtils.isEmpty(this.f3370c) || this.f3370c.equals(packageName)) {
            return 0;
        }
        String strA = ap.a(context);
        if (TextUtils.isEmpty(strA)) {
            return !ap.a(context, this.f3370c) ? 1 : 0;
        }
        return !this.f3370c.equals(strA) ? 1 : 0;
    }

    public String getPushContent() {
        return !TextUtils.isEmpty(this.f3369b) ? this.f3369b : "";
    }

    public String getPushLandingPage() {
        return !TextUtils.isEmpty(this.f3368a) ? this.f3368a : "";
    }

    public String getRefererPkgName() {
        return !TextUtils.isEmpty(this.f3370c) ? this.f3370c : "";
    }

    public void setPushInfo(String str, String str2) {
        this.f3368a = str;
        this.f3369b = bc.a(str2, 1024);
    }

    public void setRefererPkgName(String str) {
        this.f3370c = str;
    }
}
