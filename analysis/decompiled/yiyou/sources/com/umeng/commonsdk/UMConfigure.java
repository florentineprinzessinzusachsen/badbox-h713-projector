package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.TimeConstants;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.debug.UMLogCommon;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class UMConfigure {
    public static final int DEVICE_TYPE_BOX = 2;
    public static final int DEVICE_TYPE_PHONE = 1;
    private static final String KEY_FILE_NAME_APPKEY = "APPKEY";
    private static final String KEY_FILE_NAME_LOG = "LOG";
    private static final String KEY_METHOD_NAME_PUSH_SETCHANNEL = "setMessageChannel";
    private static final String KEY_METHOD_NAME_PUSH_SET_SECRET = "setSecret";
    private static final String KEY_METHOD_NAME_SETAPPKEY = "setAppkey";
    private static final String KEY_METHOD_NAME_SETCHANNEL = "setChannel";
    private static final String KEY_METHOD_NAME_SETDEBUGMODE = "setDebugMode";
    private static final String TAG = "UMConfigure";
    private static final String WRAPER_TYPE_COCOS2DX_X = "Cocos2d-x";
    private static final String WRAPER_TYPE_COCOS2DX_XLUA = "Cocos2d-x_lua";
    private static final String WRAPER_TYPE_FLUTTER = "flutter";
    private static final String WRAPER_TYPE_HYBRID = "hybrid";
    private static final String WRAPER_TYPE_NATIVE = "native";
    private static final String WRAPER_TYPE_PHONEGAP = "phonegap";
    private static final String WRAPER_TYPE_REACTNATIVE = "react-native";
    private static final String WRAPER_TYPE_UNITY = "Unity";
    private static final String WRAPER_TYPE_WEEX = "weex";
    private static boolean debugLog = false;
    public static UMLog umDebugLog = new UMLog();
    private static boolean isFinish = false;
    private static Object lockObject = new Object();

    private static Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    private static Object getDecInstanceObject(Class<?> cls) {
        Constructor<?> declaredConstructor;
        if (cls == null) {
            return null;
        }
        try {
            declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
        } catch (NoSuchMethodException unused) {
            declaredConstructor = null;
        }
        if (declaredConstructor == null) {
            return null;
        }
        declaredConstructor.setAccessible(true);
        try {
            return declaredConstructor.newInstance(new Object[0]);
        } catch (IllegalAccessException | IllegalArgumentException | InstantiationException | InvocationTargetException unused2) {
            return null;
        }
    }

    private static Method getDecMethod(Class<?> cls, String str, Class<?>[] clsArr) {
        Method declaredMethod = null;
        if (cls != null) {
            try {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
            }
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
            }
        }
        return declaredMethod;
    }

    public static boolean getInitStatus() {
        boolean z;
        synchronized (lockObject) {
            z = isFinish;
        }
        return z;
    }

    private Object getInstanceObject(Class<?> cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException | InstantiationException unused) {
            }
        }
        return null;
    }

    public static String[] getTestDeviceInfo(Context context) {
        String[] strArr = new String[2];
        if (context != null) {
            try {
                strArr[0] = DeviceConfig.getDeviceIdForGeneral(context);
                strArr[1] = DeviceConfig.getMac(context);
            } catch (Exception unused) {
            }
        }
        return strArr;
    }

    public static String getUMIDString(Context context) {
        if (context != null) {
            return UMUtils.getUMId(context.getApplicationContext());
        }
        return null;
    }

    public static void init(Context context, int i, String str) {
        init(context, null, null, i, str);
    }

    private static void invoke(Method method, Object obj, Object[] objArr) {
        if (method == null || obj == null) {
            return;
        }
        try {
            method.invoke(obj, objArr);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
        }
    }

    public static boolean isDebugLog() {
        return debugLog;
    }

    private static void saveSDKComponent() {
        StringBuffer stringBuffer = new StringBuffer();
        if (getClass("com.umeng.analytics.vismode.V") != null) {
            stringBuffer.append("v");
        } else if (getClass("com.umeng.analytics.MobclickAgent") != null) {
            stringBuffer.append("a");
        }
        if (getClass("com.umeng.visual.UMVisualAgent") != null) {
            stringBuffer.append(Config.EVENT_HEAT_X);
        }
        if (getClass("com.umeng.message.PushAgent") != null) {
            stringBuffer.append("p");
        }
        if (getClass("com.umeng.socialize.UMShareAPI") != null) {
            stringBuffer.append("s");
        }
        if (getClass("com.umeng.error.UMError") != null) {
            stringBuffer.append("e");
        }
        stringBuffer.append("i");
        if (SdkVersion.SDK_TYPE != 1 && getClass("com.umeng.commonsdk.internal.UMOplus") != null) {
            stringBuffer.append(Config.OS);
        }
        if (TextUtils.isEmpty(stringBuffer)) {
            return;
        }
        b.f4062a = stringBuffer.toString();
        UMSLEnvelopeBuild.module = stringBuffer.toString();
    }

    private static void setCheckDevice(boolean z) {
        AnalyticsConstants.CHECK_DEVICE = z;
    }

    public static void setEncryptEnabled(boolean z) {
        b.a(z);
        UMSLEnvelopeBuild.setEncryptEnabled(z);
    }

    private static void setFile(Class<?> cls, String str, String str2) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, str2);
            } catch (Exception unused) {
            }
        }
    }

    private static void setLatencyWindow(long j) {
        com.umeng.commonsdk.statistics.a.f4060c = ((int) j) * TimeConstants.SEC;
    }

    public static void setLogEnabled(boolean z) {
        try {
            debugLog = z;
            MLog.DEBUG = z;
            Class<?> cls = getClass("com.umeng.message.PushAgent");
            invoke(getDecMethod(cls, KEY_METHOD_NAME_SETDEBUGMODE, new Class[]{Boolean.TYPE}), getDecInstanceObject(cls), new Object[]{Boolean.valueOf(z)});
            setFile(getClass("com.umeng.socialize.Config"), "DEBUG", z);
        } catch (Exception e2) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + e2);
            }
        } catch (Throwable th) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + th);
            }
        }
    }

    public static void setProcessEvent(boolean z) {
        AnalyticsConstants.SUB_PROCESS_EVENT = z;
    }

    private static void setWraperType(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(WRAPER_TYPE_NATIVE)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_NATIVE;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_NATIVE;
            } else if (str.equals(WRAPER_TYPE_COCOS2DX_X)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_COCOS2DX_X;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_COCOS2DX_X;
            } else if (str.equals(WRAPER_TYPE_COCOS2DX_XLUA)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_COCOS2DX_XLUA;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_COCOS2DX_XLUA;
            } else if (str.equals(WRAPER_TYPE_UNITY)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_UNITY;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_UNITY;
            } else if (str.equals(WRAPER_TYPE_REACTNATIVE)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_REACTNATIVE;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_REACTNATIVE;
            } else if (str.equals(WRAPER_TYPE_PHONEGAP)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_PHONEGAP;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_PHONEGAP;
            } else if (str.equals(WRAPER_TYPE_WEEX)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_WEEX;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_WEEX;
            } else if (str.equals(WRAPER_TYPE_HYBRID)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_HYBRID;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_HYBRID;
            } else if (str.equals(WRAPER_TYPE_FLUTTER)) {
                com.umeng.commonsdk.stateless.a.f4021a = WRAPER_TYPE_FLUTTER;
                com.umeng.commonsdk.statistics.a.f4058a = WRAPER_TYPE_FLUTTER;
            }
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        com.umeng.commonsdk.stateless.a.f4022b = str2;
        com.umeng.commonsdk.statistics.a.f4059b = str2;
    }

    public static void init(Context context, String str, String str2, int i, String str3) {
        Method declaredMethod;
        Method declaredMethod2;
        Method declaredMethod3;
        Method declaredMethod4;
        Object objInvoke;
        Class<?> cls;
        Method declaredMethod5;
        Method declaredMethod6;
        try {
            try {
                if (debugLog) {
                    Log.i(TAG, "common version is 2.0.0");
                    Log.i(TAG, "common type is " + SdkVersion.SDK_TYPE);
                }
                if (context == null) {
                    if (debugLog) {
                        Log.e(TAG, "context is null !!!");
                        return;
                    }
                    return;
                }
                Context applicationContext = context.getApplicationContext();
                if (debugLog) {
                    String appkeyByXML = UMUtils.getAppkeyByXML(applicationContext);
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(appkeyByXML) && !str.equals(appkeyByXML)) {
                        UMLog.mutlInfo(UMLogCommon.SC_10011, 3, "", new String[]{"@", "#"}, new String[]{str, appkeyByXML});
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    str = UMUtils.getAppkeyByXML(applicationContext);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = UMUtils.getChannelByXML(applicationContext);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = "Unknown";
                }
                UMUtils.setChannel(applicationContext, str2);
                if (debugLog) {
                    Log.i(TAG, "channel is " + str2);
                }
                if (UMUtils.isMainProgress(applicationContext)) {
                    saveSDKComponent();
                }
                try {
                    Class<?> cls2 = Class.forName("com.umeng.analytics.MobclickAgent");
                    if (cls2 != null) {
                        Method declaredMethod7 = cls2.getDeclaredMethod("init", Context.class);
                        if (declaredMethod7 != null) {
                            declaredMethod7.setAccessible(true);
                            declaredMethod7.invoke(cls2, applicationContext);
                            if (debugLog) {
                                UMLog.mutlInfo(UMLogCommon.SC_10003, 2, "");
                            }
                        }
                        if (Class.forName("com.umeng.analytics.game.UMGameAgent") != null && (declaredMethod6 = cls2.getDeclaredMethod("setGameScenarioType", Context.class)) != null) {
                            declaredMethod6.setAccessible(true);
                            declaredMethod6.invoke(cls2, applicationContext);
                        }
                    }
                    if (b.f4062a.indexOf("e") >= 0 && (cls = Class.forName("com.umeng.analytics.MobclickAgent")) != null && (declaredMethod5 = cls.getDeclaredMethod("disableExceptionCatch", new Class[0])) != null) {
                        declaredMethod5.setAccessible(true);
                        declaredMethod5.invoke(cls, new Object[0]);
                    }
                } catch (Throwable unused) {
                }
                try {
                    Class<?> cls3 = Class.forName("com.umeng.message.MessageSharedPrefs");
                    if (cls3 != null && (declaredMethod4 = cls3.getDeclaredMethod("getInstance", Context.class)) != null && (objInvoke = declaredMethod4.invoke(cls3, applicationContext)) != null) {
                        Method declaredMethod8 = cls3.getDeclaredMethod("setMessageAppKey", String.class);
                        if (declaredMethod8 != null) {
                            declaredMethod8.setAccessible(true);
                            declaredMethod8.invoke(objInvoke, str);
                            if (debugLog) {
                                UMLog.mutlInfo(UMLogCommon.SC_10004, 2, "");
                            }
                        }
                        Method declaredMethod9 = cls3.getDeclaredMethod(KEY_METHOD_NAME_PUSH_SETCHANNEL, String.class);
                        if (declaredMethod9 != null) {
                            declaredMethod9.setAccessible(true);
                            declaredMethod9.invoke(objInvoke, str2);
                            if (debugLog) {
                                UMLog.mutlInfo(UMLogCommon.SC_10005, 2, "");
                            }
                        }
                        if (TextUtils.isEmpty(str3)) {
                            boolean z = debugLog;
                        } else {
                            if (debugLog) {
                                Log.i(TAG, "push secret is " + str3);
                            }
                            Method declaredMethod10 = cls3.getDeclaredMethod("setMessageAppSecret", String.class);
                            if (declaredMethod10 != null) {
                                declaredMethod10.setAccessible(true);
                                declaredMethod10.invoke(objInvoke, str3);
                                if (debugLog) {
                                    UMLog.mutlInfo(UMLogCommon.SC_10009, 2, "");
                                }
                            }
                        }
                    }
                } catch (Exception unused2) {
                }
                try {
                    Class<?> cls4 = getClass("com.umeng.socialize.UMShareAPI");
                    setFile(cls4, KEY_FILE_NAME_APPKEY, str);
                    if (cls4 != null && (declaredMethod3 = cls4.getDeclaredMethod("init", Context.class, String.class)) != null) {
                        declaredMethod3.setAccessible(true);
                        declaredMethod3.invoke(cls4, applicationContext, str);
                        if (debugLog) {
                            UMLog.mutlInfo(UMLogCommon.SC_10006, 2, "");
                        }
                    }
                } catch (Throwable unused3) {
                }
                if (TextUtils.isEmpty(str)) {
                    if (debugLog) {
                        UMLog.aq(UMLogCommon.SC_10007, 0, "\\|");
                        return;
                    }
                    return;
                }
                UMUtils.setAppkey(applicationContext, str);
                String lastAppkey = UMUtils.getLastAppkey(applicationContext);
                if (!TextUtils.isEmpty(str) && !str.equals(lastAppkey)) {
                    if (!TextUtils.isEmpty(lastAppkey) && debugLog) {
                        UMLog.mutlInfo(UMLogCommon.SC_10008, 2, "");
                    }
                    UMUtils.setLastAppkey(applicationContext, str);
                }
                if (debugLog) {
                    Log.i(TAG, "current appkey is " + str + ", last appkey is " + lastAppkey);
                }
                AnalyticsConstants.setDeviceType(i);
                try {
                    Class<?> cls5 = Class.forName("com.umeng.error.UMError");
                    if (cls5 != null && (declaredMethod2 = cls5.getDeclaredMethod("init", Context.class)) != null) {
                        declaredMethod2.setAccessible(true);
                        declaredMethod2.invoke(cls5, applicationContext);
                        if (debugLog) {
                            UMLog.mutlInfo(UMLogCommon.SC_10010, 2, "");
                        }
                    }
                } catch (Throwable unused4) {
                }
                if (UMUtils.isMainProgress(applicationContext)) {
                    if (SdkVersion.SDK_TYPE != 1) {
                        try {
                            Class<?> cls6 = Class.forName("com.umeng.commonsdk.UMConfigureImpl");
                            if (cls6 != null && (declaredMethod = cls6.getDeclaredMethod("init", Context.class)) != null) {
                                declaredMethod.setAccessible(true);
                                declaredMethod.invoke(cls6, applicationContext);
                            }
                        } catch (Throwable unused5) {
                        }
                    } else {
                        a.a(applicationContext);
                    }
                }
                try {
                    Method method = Class.forName("com.umeng.visual.UMVisualAgent").getMethod("init", Context.class, String.class);
                    if (!TextUtils.isEmpty(str)) {
                        method.invoke(null, context, str);
                    } else if (AnalyticsConstants.UM_DEBUG) {
                        MLog.e("initDebugSDK appkey  is null");
                    }
                } catch (ClassNotFoundException unused6) {
                    Class.forName("com.umeng.analytics.vismode.event.VisualHelper").getMethod("init", Context.class).invoke(null, context);
                } catch (Exception unused7) {
                }
                UMGlobalContext.a aVar = new UMGlobalContext.a();
                aVar.f4014a = applicationContext;
                aVar.f4015b = i;
                aVar.f4016c = str3;
                aVar.f4017d = str;
                aVar.f4018e = str2;
                aVar.f4019f = b.f4062a;
                aVar.g = false;
                aVar.h = UMFrUtils.getCurrentProcessName(applicationContext);
                aVar.i = UMUtils.getAppVersionName(applicationContext);
                aVar.j = UMUtils.isMainProgress(applicationContext);
                UMGlobalContext.newUMGlobalContext(aVar);
                synchronized (lockObject) {
                    isFinish = true;
                }
            } catch (Throwable th) {
                if (debugLog) {
                    Log.e(TAG, "init e is " + th);
                }
            }
        } catch (Exception e2) {
            if (debugLog) {
                Log.e(TAG, "init e is " + e2);
            }
        }
    }

    private static void invoke(Method method, Object[] objArr) {
        if (method != null) {
            try {
                method.invoke(null, objArr);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
            }
        }
    }

    private static void setFile(Class<?> cls, String str, boolean z) {
        if (cls != null) {
            try {
                cls.getField(str).set(str, Boolean.valueOf(z));
            } catch (Exception unused) {
            }
        }
    }
}
