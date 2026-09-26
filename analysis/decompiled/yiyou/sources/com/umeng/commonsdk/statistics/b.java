package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: EnvelopeManager.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f4062a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f4063b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f4064c = "EnvelopeManager";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f4065d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static boolean f4066f;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f4067e = 0;

    public static long a(Context context) {
        long j = DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX - DataHelper.ENVELOPE_EXTRA_LENGTH;
        JSONObject jSONObjectB = b(context);
        if (jSONObjectB != null && jSONObjectB.toString() != null && jSONObjectB.toString().getBytes() != null) {
            long length = jSONObjectB.toString().getBytes().length;
            if (ULog.DEBUG) {
                Log.i(f4064c, "headerLen size is " + length);
            }
            j -= length;
        }
        if (ULog.DEBUG) {
            Log.i(f4064c, "free size is " + j);
        }
        return j;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0230 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:112:0x0242 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:0x0200  */
    /* JADX WARN: Code duplicated, block: B:57:0x0208 A[Catch: all -> 0x021f, TRY_LEAVE, TryCatch #9 {all -> 0x021f, blocks: (B:55:0x0202, B:57:0x0208), top: B:116:0x0202 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x021f  */
    /* JADX WARN: Code duplicated, block: B:62:0x0226 A[Catch: Exception -> 0x022c, all -> 0x0292, TRY_LEAVE, TryCatch #3 {Exception -> 0x022c, blocks: (B:52:0x01fc, B:60:0x0220, B:62:0x0226), top: B:104:0x01fc, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:68:0x023a A[Catch: Exception -> 0x023e, all -> 0x0292, TRY_LEAVE, TryCatch #1 {Exception -> 0x023e, blocks: (B:66:0x0230, B:68:0x023a), top: B:100:0x0230, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x0246 A[Catch: Exception -> 0x0255, all -> 0x0292, TryCatch #2 {all -> 0x0292, blocks: (B:3:0x0009, B:6:0x0017, B:44:0x01b9, B:45:0x01d6, B:46:0x01e8, B:48:0x01f2, B:52:0x01fc, B:60:0x0220, B:62:0x0226, B:66:0x0230, B:68:0x023a, B:72:0x0242, B:74:0x0246, B:76:0x024c, B:77:0x0255, B:78:0x0263, B:80:0x026d, B:82:0x0270, B:85:0x027b, B:87:0x0280, B:89:0x0286, B:71:0x023f, B:65:0x022d, B:51:0x01f9, B:9:0x0023, B:12:0x0079, B:14:0x0082, B:16:0x008c, B:17:0x0091, B:19:0x009b, B:20:0x00a0, B:22:0x00aa, B:23:0x00af, B:25:0x0109, B:26:0x0126, B:29:0x016c, B:34:0x0183, B:36:0x018b, B:37:0x0192, B:39:0x01a8, B:40:0x01af, B:30:0x0172, B:32:0x017a, B:33:0x017e, B:13:0x007f), top: B:102:0x0009, inners: #1, #3, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:80:0x026d A[Catch: all -> 0x0292, TRY_LEAVE, TryCatch #2 {all -> 0x0292, blocks: (B:3:0x0009, B:6:0x0017, B:44:0x01b9, B:45:0x01d6, B:46:0x01e8, B:48:0x01f2, B:52:0x01fc, B:60:0x0220, B:62:0x0226, B:66:0x0230, B:68:0x023a, B:72:0x0242, B:74:0x0246, B:76:0x024c, B:77:0x0255, B:78:0x0263, B:80:0x026d, B:82:0x0270, B:85:0x027b, B:87:0x0280, B:89:0x0286, B:71:0x023f, B:65:0x022d, B:51:0x01f9, B:9:0x0023, B:12:0x0079, B:14:0x0082, B:16:0x008c, B:17:0x0091, B:19:0x009b, B:20:0x00a0, B:22:0x00aa, B:23:0x00af, B:25:0x0109, B:26:0x0126, B:29:0x016c, B:34:0x0183, B:36:0x018b, B:37:0x0192, B:39:0x01a8, B:40:0x01af, B:30:0x0172, B:32:0x017a, B:33:0x017e, B:13:0x007f), top: B:102:0x0009, inners: #1, #3, #4, #5 }] */
    /* JADX WARN: Code duplicated, block: B:86:0x027e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:87:0x0280 A[Catch: all -> 0x0292, TryCatch #2 {all -> 0x0292, blocks: (B:3:0x0009, B:6:0x0017, B:44:0x01b9, B:45:0x01d6, B:46:0x01e8, B:48:0x01f2, B:52:0x01fc, B:60:0x0220, B:62:0x0226, B:66:0x0230, B:68:0x023a, B:72:0x0242, B:74:0x0246, B:76:0x024c, B:77:0x0255, B:78:0x0263, B:80:0x026d, B:82:0x0270, B:85:0x027b, B:87:0x0280, B:89:0x0286, B:71:0x023f, B:65:0x022d, B:51:0x01f9, B:9:0x0023, B:12:0x0079, B:14:0x0082, B:16:0x008c, B:17:0x0091, B:19:0x009b, B:20:0x00a0, B:22:0x00aa, B:23:0x00af, B:25:0x0109, B:26:0x0126, B:29:0x016c, B:34:0x0183, B:36:0x018b, B:37:0x0192, B:39:0x01a8, B:40:0x01af, B:30:0x0172, B:32:0x017a, B:33:0x017e, B:13:0x007f), top: B:102:0x0009, inners: #1, #3, #4, #5 }] */
    private static JSONObject b(Context context) {
        JSONObject jSONObject;
        byte[] bArrA;
        String strImprintProperty;
        String str;
        Class<?> cls;
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            if (TextUtils.isEmpty(f4065d)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(e.o, DeviceConfig.getAppMD5Signature(context));
                jSONObject2.put(e.p, DeviceConfig.getAppSHA1Key(context));
                jSONObject2.put(e.q, DeviceConfig.getAppHashKey(context));
                jSONObject2.put("app_version", DeviceConfig.getAppVersionName(context));
                jSONObject2.put("version_code", Integer.parseInt(DeviceConfig.getAppVersionCode(context)));
                jSONObject2.put(e.u, DeviceConfig.getDeviceIdUmengMD5(context));
                jSONObject2.put(e.v, DeviceConfig.getCPU());
                String mccmnc = DeviceConfig.getMCCMNC(context);
                if (TextUtils.isEmpty(mccmnc)) {
                    jSONObject2.put(e.A, "");
                } else {
                    jSONObject2.put(e.A, mccmnc);
                    f4063b = mccmnc;
                }
                String subOSName = DeviceConfig.getSubOSName(context);
                if (!TextUtils.isEmpty(subOSName)) {
                    jSONObject2.put(e.J, subOSName);
                }
                String subOSVersion = DeviceConfig.getSubOSVersion(context);
                if (!TextUtils.isEmpty(subOSVersion)) {
                    jSONObject2.put(e.K, subOSVersion);
                }
                String deviceType = DeviceConfig.getDeviceType(context);
                if (!TextUtils.isEmpty(deviceType)) {
                    jSONObject2.put(e.af, deviceType);
                }
                jSONObject2.put(e.n, DeviceConfig.getPackageName(context));
                jSONObject2.put(e.t, "Android");
                jSONObject2.put("device_id", DeviceConfig.getDeviceId(context));
                jSONObject2.put("device_model", Build.MODEL);
                jSONObject2.put(e.D, Build.BOARD);
                jSONObject2.put(e.E, Build.BRAND);
                jSONObject2.put(e.F, Build.TIME);
                jSONObject2.put(e.G, Build.MANUFACTURER);
                jSONObject2.put(e.H, Build.ID);
                jSONObject2.put(e.I, Build.DEVICE);
                jSONObject2.put(e.w, "Android");
                jSONObject2.put(e.x, Build.VERSION.RELEASE);
                int[] resolutionArray = DeviceConfig.getResolutionArray(context);
                if (resolutionArray != null) {
                    jSONObject2.put(e.y, resolutionArray[1] + "*" + resolutionArray[0]);
                }
                jSONObject2.put("mc", DeviceConfig.getMac(context));
                jSONObject2.put(e.L, DeviceConfig.getTimeZone(context));
                String[] localeInfo = DeviceConfig.getLocaleInfo(context);
                jSONObject2.put(e.N, localeInfo[0]);
                jSONObject2.put(e.M, localeInfo[1]);
                jSONObject2.put(e.O, DeviceConfig.getNetworkOperatorName(context));
                jSONObject2.put(e.r, DeviceConfig.getAppName(context));
                String[] networkAccessMode = DeviceConfig.getNetworkAccessMode(context);
                if ("Wi-Fi".equals(networkAccessMode[0])) {
                    jSONObject2.put(e.P, "wifi");
                } else if ("2G/3G".equals(networkAccessMode[0])) {
                    jSONObject2.put(e.P, "2G/3G");
                } else {
                    jSONObject2.put(e.P, "unknow");
                }
                if (!"".equals(networkAccessMode[1])) {
                    jSONObject2.put(e.Q, networkAccessMode[1]);
                }
                jSONObject2.put(e.f3963b, SdkVersion.SDK_VERSION);
                jSONObject2.put(e.f3964c, SdkVersion.SDK_TYPE);
                if (!TextUtils.isEmpty(f4062a)) {
                    jSONObject2.put(e.f3965d, f4062a);
                }
                f4065d = jSONObject2.toString();
                jSONObject = jSONObject2;
            } else {
                try {
                    jSONObject = new JSONObject(f4065d);
                } catch (Exception unused) {
                    jSONObject = null;
                }
            }
            if (jSONObject == null) {
                return null;
            }
            try {
                jSONObject.put(e.R, sharedPreferences.getInt("successful_request", 0));
                jSONObject.put(e.S, sharedPreferences.getInt(e.S, 0));
                jSONObject.put(e.T, sharedPreferences.getInt("last_request_spent_ms", 0));
            } catch (Exception unused2) {
            }
            jSONObject.put("channel", UMUtils.getChannel(context));
            jSONObject.put("appkey", UMUtils.getAppkey(context));
            try {
                String deviceToken = UMUtils.getDeviceToken(context);
                if (!TextUtils.isEmpty(deviceToken)) {
                    jSONObject.put(e.f3962a, deviceToken);
                    try {
                        if (SdkVersion.SDK_TYPE != 1) {
                            try {
                                cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                                if (cls != null) {
                                    str = (String) cls.getMethod("getUmtt", Context.class).invoke(cls, context);
                                } else {
                                    str = null;
                                }
                            } catch (Throwable unused3) {
                            }
                            if (TextUtils.isEmpty(str)) {
                                strImprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f3967f, null);
                                if (TextUtils.isEmpty(strImprintProperty)) {
                                    if (SdkVersion.SDK_TYPE != 1) {
                                        jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                                    }
                                    jSONObject.put("wrapper_type", a.f4058a);
                                    jSONObject.put("wrapper_version", a.f4059b);
                                    bArrA = ImprintHandler.getImprintService(context).a();
                                    if (bArrA == null) {
                                        if (jSONObject != null) {
                                            return new JSONObject().put("header", jSONObject);
                                        }
                                    } else if (jSONObject != null) {
                                        return new JSONObject().put("header", jSONObject);
                                    }
                                } else {
                                    jSONObject.put(e.f3967f, strImprintProperty);
                                    if (SdkVersion.SDK_TYPE != 1) {
                                        jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                                    }
                                    jSONObject.put("wrapper_type", a.f4058a);
                                    jSONObject.put("wrapper_version", a.f4059b);
                                    bArrA = ImprintHandler.getImprintService(context).a();
                                    if (bArrA == null) {
                                        if (jSONObject != null) {
                                            return new JSONObject().put("header", jSONObject);
                                        }
                                    } else if (jSONObject != null) {
                                        return new JSONObject().put("header", jSONObject);
                                    }
                                }
                            } else {
                                jSONObject.put(e.f3966e, str);
                                try {
                                    strImprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f3967f, null);
                                    if (TextUtils.isEmpty(strImprintProperty)) {
                                        jSONObject.put(e.f3967f, strImprintProperty);
                                        try {
                                            if (SdkVersion.SDK_TYPE != 1 && com.umeng.commonsdk.proguard.a.b(context) != null) {
                                                jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                                            }
                                        } catch (Exception unused4) {
                                        }
                                        try {
                                            jSONObject.put("wrapper_type", a.f4058a);
                                            jSONObject.put("wrapper_version", a.f4059b);
                                        } catch (Exception unused5) {
                                        }
                                        bArrA = ImprintHandler.getImprintService(context).a();
                                        if (bArrA == null && bArrA.length > 0) {
                                            try {
                                                jSONObject.put(e.U, Base64.encodeToString(bArrA, 0));
                                            } catch (JSONException e2) {
                                                UMCrashManager.reportCrash(context, e2);
                                            }
                                            if (jSONObject != null) {
                                                return new JSONObject().put("header", jSONObject);
                                            }
                                        } else if (jSONObject != null && jSONObject.length() > 0) {
                                            return new JSONObject().put("header", jSONObject);
                                        }
                                    } else {
                                        if (SdkVersion.SDK_TYPE != 1) {
                                            jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                                        }
                                        jSONObject.put("wrapper_type", a.f4058a);
                                        jSONObject.put("wrapper_version", a.f4059b);
                                        bArrA = ImprintHandler.getImprintService(context).a();
                                        if (bArrA == null) {
                                            if (jSONObject != null) {
                                                return new JSONObject().put("header", jSONObject);
                                            }
                                        } else if (jSONObject != null) {
                                            return new JSONObject().put("header", jSONObject);
                                        }
                                    }
                                } catch (Exception e3) {
                                    UMCrashManager.reportCrash(context, e3);
                                }
                            }
                        } else {
                            strImprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f3967f, null);
                            if (TextUtils.isEmpty(strImprintProperty)) {
                                jSONObject.put(e.f3967f, strImprintProperty);
                                if (SdkVersion.SDK_TYPE != 1) {
                                    jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                                }
                                jSONObject.put("wrapper_type", a.f4058a);
                                jSONObject.put("wrapper_version", a.f4059b);
                                bArrA = ImprintHandler.getImprintService(context).a();
                                if (bArrA == null) {
                                    if (jSONObject != null) {
                                        return new JSONObject().put("header", jSONObject);
                                    }
                                } else if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else {
                                if (SdkVersion.SDK_TYPE != 1) {
                                    jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                                }
                                jSONObject.put("wrapper_type", a.f4058a);
                                jSONObject.put("wrapper_version", a.f4059b);
                                bArrA = ImprintHandler.getImprintService(context).a();
                                if (bArrA == null) {
                                    if (jSONObject != null) {
                                        return new JSONObject().put("header", jSONObject);
                                    }
                                } else if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            }
                        }
                    } catch (Exception e4) {
                        UMCrashManager.reportCrash(context, e4);
                    }
                } else if (SdkVersion.SDK_TYPE != 1) {
                    cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent");
                    if (cls != null) {
                        str = (String) cls.getMethod("getUmtt", Context.class).invoke(cls, context);
                    } else {
                        str = null;
                    }
                    if (TextUtils.isEmpty(str)) {
                        jSONObject.put(e.f3966e, str);
                        strImprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f3967f, null);
                        if (TextUtils.isEmpty(strImprintProperty)) {
                            jSONObject.put(e.f3967f, strImprintProperty);
                            if (SdkVersion.SDK_TYPE != 1) {
                                jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                            }
                            jSONObject.put("wrapper_type", a.f4058a);
                            jSONObject.put("wrapper_version", a.f4059b);
                            bArrA = ImprintHandler.getImprintService(context).a();
                            if (bArrA == null) {
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        } else {
                            if (SdkVersion.SDK_TYPE != 1) {
                                jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                            }
                            jSONObject.put("wrapper_type", a.f4058a);
                            jSONObject.put("wrapper_version", a.f4059b);
                            bArrA = ImprintHandler.getImprintService(context).a();
                            if (bArrA == null) {
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        }
                    } else {
                        strImprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f3967f, null);
                        if (TextUtils.isEmpty(strImprintProperty)) {
                            jSONObject.put(e.f3967f, strImprintProperty);
                            if (SdkVersion.SDK_TYPE != 1) {
                                jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                            }
                            jSONObject.put("wrapper_type", a.f4058a);
                            jSONObject.put("wrapper_version", a.f4059b);
                            bArrA = ImprintHandler.getImprintService(context).a();
                            if (bArrA == null) {
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        } else {
                            if (SdkVersion.SDK_TYPE != 1) {
                                jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                            }
                            jSONObject.put("wrapper_type", a.f4058a);
                            jSONObject.put("wrapper_version", a.f4059b);
                            bArrA = ImprintHandler.getImprintService(context).a();
                            if (bArrA == null) {
                                if (jSONObject != null) {
                                    return new JSONObject().put("header", jSONObject);
                                }
                            } else if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        }
                    }
                } else {
                    strImprintProperty = UMEnvelopeBuild.imprintProperty(context, e.f3967f, null);
                    if (TextUtils.isEmpty(strImprintProperty)) {
                        jSONObject.put(e.f3967f, strImprintProperty);
                        if (SdkVersion.SDK_TYPE != 1) {
                            jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                        }
                        jSONObject.put("wrapper_type", a.f4058a);
                        jSONObject.put("wrapper_version", a.f4059b);
                        bArrA = ImprintHandler.getImprintService(context).a();
                        if (bArrA == null) {
                            if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        } else if (jSONObject != null) {
                            return new JSONObject().put("header", jSONObject);
                        }
                    } else {
                        if (SdkVersion.SDK_TYPE != 1) {
                            jSONObject.put(e.g, com.umeng.commonsdk.proguard.a.b(context));
                        }
                        jSONObject.put("wrapper_type", a.f4058a);
                        jSONObject.put("wrapper_version", a.f4059b);
                        bArrA = ImprintHandler.getImprintService(context).a();
                        if (bArrA == null) {
                            if (jSONObject != null) {
                                return new JSONObject().put("header", jSONObject);
                            }
                        } else if (jSONObject != null) {
                            return new JSONObject().put("header", jSONObject);
                        }
                    }
                }
            } catch (Exception e5) {
                UMCrashManager.reportCrash(context, e5);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
        return null;
    }

    private JSONObject a(int i, JSONObject jSONObject) {
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
    }

    public JSONObject a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3;
        String str;
        String string;
        Envelope envelopeA;
        String str2;
        String str3;
        if (ULog.DEBUG && jSONObject != null && jSONObject2 != null) {
            Log.i(f4064c, "headerJSONObject size is " + jSONObject.toString().getBytes().length);
            Log.i(f4064c, "bodyJSONObject size is " + jSONObject2.toString().getBytes().length);
        }
        if (context != null && jSONObject2 != null) {
            try {
                JSONObject jSONObjectB = b(context);
                if (jSONObjectB != null && jSONObject != null) {
                    jSONObjectB = a(jSONObjectB, jSONObject);
                }
                if (jSONObjectB != null && jSONObject2 != null) {
                    Iterator<String> itKeys = jSONObject2.keys();
                    while (itKeys.hasNext()) {
                        String next = itKeys.next();
                        if (next != null && (next instanceof String) && (str3 = next) != null && jSONObject2.opt(str3) != null) {
                            try {
                                jSONObjectB.put(str3, jSONObject2.opt(str3));
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
                if (jSONObjectB != null) {
                    StringBuilder sb = new StringBuilder();
                    if (jSONObjectB.length() > 0) {
                        if (jSONObjectB.has("push")) {
                            String strOptString = jSONObjectB.optJSONObject("header").optString(e.au);
                            if (!TextUtils.isEmpty("p") && !TextUtils.isEmpty(strOptString)) {
                                sb.append("p");
                                sb.append("==");
                                sb.append(strOptString);
                                sb.append("&=");
                            }
                        }
                        if (jSONObjectB.has("share")) {
                            String strOptString2 = jSONObjectB.optJSONObject("header").optString(e.av);
                            if (!TextUtils.isEmpty("s") && !TextUtils.isEmpty(strOptString2)) {
                                sb.append("s");
                                sb.append("==");
                                sb.append(strOptString2);
                                sb.append("&=");
                            }
                        }
                        if (jSONObjectB.has("analytics")) {
                            if (jSONObjectB.has("dplus")) {
                                str2 = e.an;
                            } else {
                                str2 = jSONObjectB.optJSONObject("header").has("st") ? "t" : "a";
                            }
                            String strOptString3 = jSONObjectB.optJSONObject("header").optString("sdk_version");
                            if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(strOptString3)) {
                                sb.append(str2);
                                sb.append("==");
                                sb.append(strOptString3);
                                sb.append("&=");
                            }
                        }
                        if (jSONObjectB.has("dplus")) {
                            String strOptString4 = jSONObjectB.optJSONObject("header").optString("sdk_version");
                            if (jSONObjectB.has("analytics")) {
                                if (!sb.toString().contains(e.an) && !TextUtils.isEmpty(e.an) && !TextUtils.isEmpty(strOptString4)) {
                                    sb.append(e.an);
                                    sb.append("==");
                                    sb.append(strOptString4);
                                    sb.append("&=");
                                }
                            } else if (!TextUtils.isEmpty("d") && !TextUtils.isEmpty(strOptString4)) {
                                sb.append("d");
                                sb.append("==");
                                sb.append(strOptString4);
                                sb.append("&=");
                            }
                        }
                        if (jSONObjectB.has(e.ak)) {
                            String strOptString5 = jSONObjectB.optJSONObject("header").optString(e.aw);
                            if (!TextUtils.isEmpty("i") && !TextUtils.isEmpty(strOptString5)) {
                                sb.append("i");
                                sb.append("==");
                                sb.append(strOptString5);
                                sb.append("&=");
                            }
                        }
                    }
                    string = sb.toString();
                    if (TextUtils.isEmpty(string)) {
                        return a(101, jSONObjectB);
                    }
                    if (string.endsWith("&=")) {
                        string = string.substring(0, string.length() - 2);
                    }
                } else {
                    string = null;
                }
                if (jSONObjectB != null) {
                    try {
                        com.umeng.commonsdk.statistics.idtracking.e eVarA = com.umeng.commonsdk.statistics.idtracking.e.a(context);
                        if (eVarA != null) {
                            eVarA.a();
                            String strEncodeToString = Base64.encodeToString(new s().a(eVarA.b()), 0);
                            if (!TextUtils.isEmpty(strEncodeToString)) {
                                JSONObject jSONObject4 = jSONObjectB.getJSONObject("header");
                                jSONObject4.put(e.V, strEncodeToString);
                                jSONObjectB.put("header", jSONObject4);
                            }
                        }
                    } catch (Exception unused2) {
                    }
                }
                if (jSONObjectB != null && DataHelper.largeThanMaxSize(jSONObjectB.toString().getBytes().length, DataHelper.ENVELOPE_ENTITY_RAW_LENGTH_MAX)) {
                    SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
                    if (sharedPreferences != null) {
                        sharedPreferences.edit().putInt("serial", sharedPreferences.getInt("serial", 1) + 1).commit();
                    }
                    return a(113, jSONObjectB);
                }
                if (jSONObjectB != null) {
                    envelopeA = a(context, jSONObjectB.toString().getBytes());
                    if (envelopeA == null) {
                        return a(111, jSONObjectB);
                    }
                } else {
                    envelopeA = null;
                }
                if (envelopeA != null && DataHelper.largeThanMaxSize(envelopeA.toBinary().length, DataHelper.ENVELOPE_LENGTH_MAX)) {
                    return a(114, jSONObjectB);
                }
                int iA = a(context, envelopeA, string, jSONObjectB != null ? jSONObjectB.optJSONObject("header").optString("app_version") : null);
                if (iA != 0) {
                    return a(iA, jSONObjectB);
                }
                if (ULog.DEBUG) {
                    Log.i(f4064c, "constructHeader size is " + jSONObjectB.toString().getBytes().length);
                }
                return jSONObjectB;
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
                if (jSONObject != null) {
                    try {
                        jSONObject3 = new JSONObject();
                        try {
                            try {
                                jSONObject3.put("header", jSONObject);
                            } catch (Exception e2) {
                                e = e2;
                                UMCrashManager.reportCrash(context, e);
                                return a(110, jSONObject3);
                            }
                        } catch (JSONException unused3) {
                        }
                    } catch (Exception e3) {
                        e = e3;
                        jSONObject3 = null;
                        UMCrashManager.reportCrash(context, e);
                        return a(110, jSONObject3);
                    }
                } else {
                    jSONObject3 = null;
                }
                if (jSONObject2 != 0) {
                    if (jSONObject3 == null) {
                        jSONObject3 = new JSONObject();
                    }
                    if (jSONObject2 != 0) {
                        Iterator<String> itKeys2 = jSONObject2.keys();
                        while (itKeys2.hasNext()) {
                            String next2 = itKeys2.next();
                            if (next2 != null && (next2 instanceof String) && (str = next2) != null && jSONObject2.opt(str) != null) {
                                try {
                                    jSONObject3.put(str, jSONObject2.opt(str));
                                } catch (Exception unused4) {
                                }
                            }
                        }
                    }
                }
                return a(110, jSONObject3);
            }
        }
        return a(110, (JSONObject) null);
    }

    private JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        String str;
        if (jSONObject != null && jSONObject2 != null && jSONObject.opt("header") != null && (jSONObject.opt("header") instanceof JSONObject)) {
            JSONObject jSONObject3 = (JSONObject) jSONObject.opt("header");
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next != null && (next instanceof String) && (str = next) != null && jSONObject2.opt(str) != null) {
                    try {
                        jSONObject3.put(str, jSONObject2.opt(str));
                        if (str.equals(com.umeng.analytics.pro.b.i) && (jSONObject2.opt(str) instanceof Integer)) {
                            this.f4067e = ((Integer) jSONObject2.opt(str)).intValue();
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return jSONObject;
    }

    private Envelope a(Context context, byte[] bArr) {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(context, "codex", null);
        int iIntValue = -1;
        try {
            if (!TextUtils.isEmpty(strImprintProperty)) {
                iIntValue = Integer.valueOf(strImprintProperty).intValue();
            }
        } catch (NumberFormatException e2) {
            UMCrashManager.reportCrash(context, e2);
        }
        if (iIntValue == 0) {
            return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (iIntValue == 1) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        if (f4066f) {
            return Envelope.genEncryptEnvelope(context, UMUtils.getAppkey(context), bArr);
        }
        return Envelope.genEnvelope(context, UMUtils.getAppkey(context), bArr);
    }

    private int a(Context context, Envelope envelope, String str, String str2) {
        if (context == null || envelope == null || TextUtils.isEmpty(str)) {
            return 101;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = DeviceConfig.getAppVersionName(context);
        }
        return UMFrUtils.saveEnvelopeFile(context, str + "&&" + str2 + "_" + System.currentTimeMillis() + "_envelope.log", envelope.toBinary());
    }

    public static void a(boolean z) {
        f4066f = z;
    }
}
