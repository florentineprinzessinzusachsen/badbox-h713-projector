package com.hs.s;

import android.app.ActivityManager;
import android.content.Context;
import com.hs.common.utils.TextUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AutoRunUtils {
    private static boolean mHasAutoRunMethod = true;

    private static boolean disableForbiddenAutorun(Context context) {
        try {
            String packageName = context.getPackageName();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            activityManager.getClass().getMethod("setForbiddenAutorunPackages", String.class, Boolean.TYPE).invoke(activityManager, packageName, Boolean.FALSE);
            return true;
        } catch (NoSuchMethodException unused) {
            mHasAutoRunMethod = false;
            return false;
        } catch (Throwable unused2) {
            return false;
        }
    }

    public static void enable(Context context) {
        if (mHasAutoRunMethod && isForbiddenAutorun(context) && disableForbiddenAutorun(context)) {
            isForbiddenAutorun(context);
        }
    }

    private static boolean isForbiddenAutorun(Context context) {
        try {
            String packageName = context.getPackageName();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            List list = (List) activityManager.getClass().getMethod("getForbiddenAutorunPackages", new Class[0]).invoke(activityManager, new Object[0]);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    if (TextUtils.equals(packageName, (String) it.next())) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }
}
