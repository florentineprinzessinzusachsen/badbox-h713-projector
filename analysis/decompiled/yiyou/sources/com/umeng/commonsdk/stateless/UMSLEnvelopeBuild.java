package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UMSLEnvelopeBuild {
    private static final String TAG = "UMSLEnvelopeBuild";
    private static String cacheSystemheader;
    private static boolean isEncryptEnabled;
    public static Context mContext;
    public static String module;

    private synchronized c constructEnvelope(Context context, byte[] bArr) {
        c cVarA;
        int iIntValue = -1;
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "slcodex", null);
        ULog.i("walle", "[stateless] build envelope, codexStr is " + strImprintProperty);
        try {
            if (!TextUtils.isEmpty(strImprintProperty)) {
                iIntValue = Integer.valueOf(strImprintProperty).intValue();
            }
        } catch (NumberFormatException e2) {
            UMCrashManager.reportCrash(context, e2);
        }
        if (iIntValue == 0) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 0");
            cVarA = c.a(context, UMUtils.getAppkey(context), bArr);
        } else if (iIntValue == 1) {
            ULog.i("walle", "[stateless] build envelope, codexValue is 1");
            cVarA = c.b(context, UMUtils.getAppkey(context), bArr);
        } else if (isEncryptEnabled) {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is true");
            cVarA = c.b(context, UMUtils.getAppkey(context), bArr);
        } else {
            ULog.i("walle", "[stateless] build envelope, isEncryptEnabled is false");
            cVarA = c.a(context, UMUtils.getAppkey(context), bArr);
        }
        return cVarA;
    }

    private synchronized JSONObject makeErrorResult(int i, JSONObject jSONObject) {
        try {
            if (jSONObject != null) {
                try {
                    jSONObject.put("exception", i);
                } catch (Exception unused) {
                }
                return jSONObject;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("exception", i);
            } catch (Exception unused2) {
            }
            return jSONObject2;
        } catch (Throwable th) {
            throw th;
        }
    }

    public static void setEncryptEnabled(boolean z) {
        isEncryptEnabled = z;
    }

    public synchronized JSONObject buildSLBaseHeader(Context context) {
        JSONObject jSONObject;
        String str;
        ULog.i("walle", "[stateless] begin build hader, thread is " + Thread.currentThread());
        if (context == null) {
            return null;
        }
        Context applicationContext = context.getApplicationContext();
        try {
            if (TextUtils.isEmpty(cacheSystemheader)) {
                jSONObject = new JSONObject();
                jSONObject.put(com.umeng.commonsdk.proguard.e.o, DeviceConfig.getAppMD5Signature(applicationContext));
                jSONObject.put(com.umeng.commonsdk.proguard.e.p, DeviceConfig.getAppSHA1Key(applicationContext));
                jSONObject.put(com.umeng.commonsdk.proguard.e.q, DeviceConfig.getAppHashKey(applicationContext));
                jSONObject.put("app_version", DeviceConfig.getAppVersionName(applicationContext));
                jSONObject.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(applicationContext)));
                jSONObject.put(com.umeng.commonsdk.proguard.e.u, DeviceConfig.getDeviceIdUmengMD5(applicationContext));
                jSONObject.put(com.umeng.commonsdk.proguard.e.v, DeviceConfig.getCPU());
                String mccmnc = DeviceConfig.getMCCMNC(applicationContext);
                if (TextUtils.isEmpty(mccmnc)) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.A, "");
                } else {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.A, mccmnc);
                }
                String subOSName = DeviceConfig.getSubOSName(applicationContext);
                if (!TextUtils.isEmpty(subOSName)) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.J, subOSName);
                }
                String subOSVersion = DeviceConfig.getSubOSVersion(applicationContext);
                if (!TextUtils.isEmpty(subOSVersion)) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.K, subOSVersion);
                }
                String deviceType = DeviceConfig.getDeviceType(applicationContext);
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.af, deviceType);
                }
                jSONObject.put(com.umeng.commonsdk.proguard.e.n, DeviceConfig.getPackageName(applicationContext));
                jSONObject.put(com.umeng.commonsdk.proguard.e.t, "Android");
                jSONObject.put("device_id", DeviceConfig.getDeviceId(applicationContext));
                jSONObject.put("device_model", Build.MODEL);
                jSONObject.put(com.umeng.commonsdk.proguard.e.D, Build.BOARD);
                jSONObject.put(com.umeng.commonsdk.proguard.e.E, Build.BRAND);
                jSONObject.put(com.umeng.commonsdk.proguard.e.F, Build.TIME);
                jSONObject.put(com.umeng.commonsdk.proguard.e.G, Build.MANUFACTURER);
                jSONObject.put(com.umeng.commonsdk.proguard.e.H, Build.ID);
                jSONObject.put(com.umeng.commonsdk.proguard.e.I, Build.DEVICE);
                jSONObject.put(com.umeng.commonsdk.proguard.e.w, "Android");
                jSONObject.put(com.umeng.commonsdk.proguard.e.x, Build.VERSION.RELEASE);
                int[] resolutionArray = DeviceConfig.getResolutionArray(applicationContext);
                if (resolutionArray != null) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.y, resolutionArray[1] + "*" + resolutionArray[0]);
                }
                jSONObject.put("mc", DeviceConfig.getMac(applicationContext));
                jSONObject.put(com.umeng.commonsdk.proguard.e.L, DeviceConfig.getTimeZone(applicationContext));
                String[] localeInfo = DeviceConfig.getLocaleInfo(applicationContext);
                jSONObject.put(com.umeng.commonsdk.proguard.e.N, localeInfo[0]);
                jSONObject.put(com.umeng.commonsdk.proguard.e.M, localeInfo[1]);
                jSONObject.put(com.umeng.commonsdk.proguard.e.O, DeviceConfig.getNetworkOperatorName(applicationContext));
                jSONObject.put(com.umeng.commonsdk.proguard.e.r, DeviceConfig.getAppName(applicationContext));
                String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(applicationContext);
                if ("Wi-Fi".equals(networkAccessMode[0])) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.P, "wifi");
                } else if ("2G/3G".equals(networkAccessMode[0])) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.P, "2G/3G");
                } else {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.P, "unknow");
                }
                if (!"".equals(networkAccessMode[1])) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.Q, networkAccessMode[1]);
                }
                jSONObject.put(com.umeng.commonsdk.proguard.e.f3963b, SdkVersion.SDK_VERSION);
                jSONObject.put(com.umeng.commonsdk.proguard.e.f3964c, SdkVersion.SDK_TYPE);
                if (!TextUtils.isEmpty(module)) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.f3965d, module);
                }
                cacheSystemheader = jSONObject.toString();
            } else {
                try {
                    jSONObject = new JSONObject(cacheSystemheader);
                } catch (Exception unused) {
                    jSONObject = null;
                }
            }
            if (jSONObject == null) {
                return null;
            }
            jSONObject.put("channel", UMUtils.getChannel(applicationContext));
            jSONObject.put("appkey", UMUtils.getAppkey(applicationContext));
            try {
                if (SdkVersion.SDK_TYPE != 1) {
                    try {
                        Class<?> cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                        str = cls != null ? (String) cls.getMethod("getUmtt", Context.class).invoke(cls, applicationContext) : null;
                    } catch (Throwable unused2) {
                    }
                    if (!TextUtils.isEmpty(str)) {
                        jSONObject.put(com.umeng.commonsdk.proguard.e.f3966e, str);
                    }
                }
            } catch (Exception unused3) {
            }
            try {
                String strImprintProperty = UMEnvelopeBuild.imprintProperty(applicationContext, com.umeng.commonsdk.proguard.e.f3967f, null);
                if (!TextUtils.isEmpty(strImprintProperty)) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.f3967f, strImprintProperty);
                }
            } catch (Exception unused4) {
            }
            try {
                if (SdkVersion.SDK_TYPE != 1 && com.umeng.commonsdk.proguard.a.b(applicationContext) != null) {
                    jSONObject.put(com.umeng.commonsdk.proguard.e.g, com.umeng.commonsdk.proguard.a.b(applicationContext));
                }
            } catch (Exception unused5) {
            }
            try {
                jSONObject.put("wrapper_type", a.f4021a);
                jSONObject.put("wrapper_version", a.f4022b);
            } catch (Exception unused6) {
            }
            if (jSONObject != null && jSONObject.length() > 0) {
                JSONObject jSONObject2 = new JSONObject();
                ULog.i("walle", "[stateless] build header end , header is " + jSONObject.toString() + ", thread is " + Thread.currentThread());
                return jSONObject2.put("header", jSONObject);
            }
            throw th;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(applicationContext, th);
        }
        ULog.i("walle", "[stateless] build header end , header is null !!! thread is " + Thread.currentThread());
        return null;
    }

    public synchronized JSONObject buildSLEnvelope(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        c cVarConstructEnvelope;
        String str2;
        ULog.i("walle", "[stateless] build envelope, heade is " + jSONObject.toString());
        ULog.i("walle", "[stateless] build envelope, body is " + jSONObject2.toString());
        ULog.i("walle", "[stateless] build envelope, thread is " + Thread.currentThread());
        if (context == null || jSONObject == null || jSONObject2 == null || str == null) {
            ULog.i("walle", "[stateless] build envelope, context is null or header is null or body is null");
            return makeErrorResult(110, null);
        }
        try {
            Context applicationContext = context.getApplicationContext();
            if (jSONObject != null && jSONObject2 != null) {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (next != null && (next instanceof String) && (str2 = next) != null && jSONObject2.opt(str2) != null) {
                        try {
                            jSONObject.put(str2, jSONObject2.opt(str2));
                        } catch (Exception unused) {
                        }
                    }
                }
            }
            if (jSONObject != null) {
                try {
                    com.umeng.commonsdk.statistics.idtracking.e eVarA = com.umeng.commonsdk.statistics.idtracking.e.a(applicationContext);
                    if (eVarA != null) {
                        eVarA.a();
                        String strEncodeToString = Base64.encodeToString(new s().a(eVarA.b()), 0);
                        if (!TextUtils.isEmpty(strEncodeToString)) {
                            JSONObject jSONObject3 = jSONObject.getJSONObject("header");
                            jSONObject3.put(com.umeng.commonsdk.proguard.e.V, strEncodeToString);
                            jSONObject.put("header", jSONObject3);
                        }
                    }
                } catch (Exception unused2) {
                }
            }
            if (jSONObject != null && f.a(jSONObject.toString().getBytes().length, a.f4023c)) {
                ULog.i("walle", "[stateless] build envelope, json overstep!!!! size is " + jSONObject.toString().getBytes().length);
                return makeErrorResult(113, jSONObject);
            }
            ULog.i("walle", "[stateless] build envelope, json size is " + jSONObject.toString().getBytes().length);
            if (jSONObject != null) {
                cVarConstructEnvelope = constructEnvelope(applicationContext, jSONObject.toString().getBytes());
                if (cVarConstructEnvelope == null) {
                    ULog.i("walle", "[stateless] build envelope, envelope is null !!!!");
                    return makeErrorResult(111, jSONObject);
                }
            } else {
                cVarConstructEnvelope = null;
            }
            if (cVarConstructEnvelope != null && f.a(cVarConstructEnvelope.b().length, a.f4024d)) {
                ULog.i("walle", "[stateless] build envelope, envelope overstep!!!! size is " + cVarConstructEnvelope.b().length);
                return makeErrorResult(114, jSONObject);
            }
            if (!f.a(applicationContext, Base64.encodeToString(str.getBytes(), 0), Base64.encodeToString((str + "_" + System.currentTimeMillis()).getBytes(), 0), cVarConstructEnvelope.b())) {
                ULog.i("walle", "[stateless] build envelope, save fail ----->>>>>");
                return makeErrorResult(101, jSONObject);
            }
            ULog.i("walle", "[stateless] build envelope, save ok ----->>>>>");
            ULog.i("walle", "[stateless] envelope file size is " + jSONObject.toString().getBytes().length);
            new d(applicationContext);
            d.b(d.f4045a);
            ULog.i("walle", "[stateless] build envelope end, thread is " + Thread.currentThread());
            return jSONObject;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
            ULog.i("walle", "build envelope end, thread is " + Thread.currentThread());
            return makeErrorResult(110, null);
        }
    }
}
