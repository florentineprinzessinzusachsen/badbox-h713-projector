package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.statistics.common.ULog;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/* JADX INFO: compiled from: AliUMIDManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static Object f3881a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f3882b;

    public static void a(Context context) {
        Constructor<?> constructor;
        Object objNewInstance;
        try {
            Class<?>[] clsArr = {Context.class};
            try {
                Class<?> cls = Class.forName("com.wireless.security.securityenv.sdk.SecurityEnvSDK");
                if (cls == null || (constructor = cls.getConstructor(clsArr)) == null || (objNewInstance = constructor.newInstance(context)) == null) {
                    return;
                }
                Method declaredMethod = cls.getDeclaredMethod("initSync", new Class[0]);
                if (declaredMethod != null) {
                    declaredMethod.setAccessible(true);
                    declaredMethod.invoke(objNewInstance, new Object[0]);
                }
                Method declaredMethod2 = cls.getDeclaredMethod("getToken", new Class[0]);
                if (declaredMethod2 != null) {
                    declaredMethod2.setAccessible(true);
                    String str = (String) declaredMethod2.invoke(objNewInstance, new Object[0]);
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    synchronized (f3881a) {
                        try {
                            f3882b = str;
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        } catch (Exception e2) {
            ULog.e(UMModuleRegister.INNER, "e is " + e2);
        }
    }

    public static String b(Context context) {
        String str;
        synchronized (f3881a) {
            str = f3882b;
        }
        return str;
    }
}
