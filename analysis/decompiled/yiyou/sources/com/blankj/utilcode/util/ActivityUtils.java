package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.core.app.b;
import androidx.core.e.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class ActivityUtils {
    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void finishActivity(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        finishActivity(activity, false);
    }

    public static void finishAllActivities() {
        finishAllActivities(false);
    }

    public static void finishAllActivitiesExceptNewest() {
        finishAllActivitiesExceptNewest(false);
    }

    public static void finishOtherActivities(Class<? extends Activity> cls) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        finishOtherActivities(cls, false);
    }

    public static boolean finishToActivity(Activity activity, boolean z) {
        if (activity != null) {
            return finishToActivity(activity, z, false);
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Activity getActivityByContext(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static Activity getActivityByView(View view) {
        if (view != null) {
            return getActivityByContext(view.getContext());
        }
        throw new NullPointerException("Argument 'view' of type View (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityIcon(Activity activity) {
        if (activity != null) {
            return getActivityIcon(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static List<Activity> getActivityList() {
        return Utils.getActivityList();
    }

    public static Drawable getActivityLogo(Activity activity) {
        if (activity != null) {
            return getActivityLogo(activity.getComponentName());
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static String getLauncherActivity() {
        return getLauncherActivity(Utils.getApp().getPackageName());
    }

    public static List<String> getMainActivities() {
        return getMainActivities(Utils.getApp().getPackageName());
    }

    private static Bundle getOptionsBundle(Context context, int i, int i2) {
        return b.a(context, i, i2).a();
    }

    public static Activity getTopActivity() {
        return Utils.getActivityLifecycle().getTopActivity();
    }

    public static boolean isActivityAlive(Context context) {
        return isActivityAlive(getActivityByContext(context));
    }

    public static boolean isActivityExists(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 == null) {
            throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        return (Utils.getApp().getPackageManager().resolveActivity(intent, 0) == null || intent.resolveActivity(Utils.getApp().getPackageManager()) == null || Utils.getApp().getPackageManager().queryIntentActivities(intent, 0).size() == 0) ? false : true;
    }

    public static boolean isActivityExistsInStack(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Iterator<Activity> it = Utils.getActivityList().iterator();
        while (it.hasNext()) {
            if (it.next().equals(activity)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static void startActivities(Intent[] intentArr) {
        if (intentArr == null) {
            throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        startActivities(intentArr, Utils.getTopActivityOrApp(), (Bundle) null);
    }

    public static void startActivity(Class<? extends Activity> cls) {
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Context topActivityOrApp = Utils.getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), (Bundle) null);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls == null) {
            throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, (Bundle) null);
    }

    public static void startHomeActivity() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        startActivity(intent);
    }

    public static void startLauncherActivity() {
        startLauncherActivity(Utils.getApp().getPackageName());
    }

    public static void finishAllActivities(boolean z) {
        LinkedList<Activity> activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = activityList.get(size);
            activity.finish();
            if (!z) {
                activity.overridePendingTransition(0, 0);
            }
        }
    }

    public static void finishAllActivitiesExceptNewest(boolean z) {
        LinkedList<Activity> activityList = Utils.getActivityList();
        for (int size = activityList.size() - 2; size >= 0; size--) {
            finishActivity(activityList.get(size), z);
        }
    }

    public static String getLauncherActivity(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(str);
        List<ResolveInfo> listQueryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
        int size = listQueryIntentActivities.size();
        if (size == 0) {
            return "";
        }
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            if (resolveInfo.activityInfo.processName.equals(str)) {
                return resolveInfo.activityInfo.name;
            }
        }
        return listQueryIntentActivities.get(0).activityInfo.name;
    }

    public static List<String> getMainActivities(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.setPackage(str);
        List<ResolveInfo> listQueryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
        int size = listQueryIntentActivities.size();
        if (size == 0) {
            return arrayList;
        }
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            if (resolveInfo.activityInfo.processName.equals(str)) {
                arrayList.add(resolveInfo.activityInfo.name);
            }
        }
        return arrayList;
    }

    private static Bundle getOptionsBundle(Activity activity, View[] viewArr) {
        int length;
        if (Build.VERSION.SDK_INT < 21 || viewArr == null || (length = viewArr.length) <= 0) {
            return null;
        }
        d[] dVarArr = new d[length];
        for (int i = 0; i < length; i++) {
            dVarArr[i] = d.a(viewArr[i], viewArr[i].getTransitionName());
        }
        return b.a(activity, dVarArr).a();
    }

    public static boolean isActivityAlive(Activity activity) {
        return (activity == null || activity.isFinishing() || (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed())) ? false : true;
    }

    public static void startLauncherActivity(String str) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        String launcherActivity = getLauncherActivity(str);
        if (TextUtils.isEmpty(launcherActivity)) {
            return;
        }
        startActivity(str, launcherActivity);
    }

    public static void finishActivity(Activity activity, boolean z) {
        if (activity != null) {
            activity.finish();
            if (z) {
                return;
            }
            activity.overridePendingTransition(0, 0);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishOtherActivities(Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            LinkedList<Activity> activityList = Utils.getActivityList();
            for (int size = activityList.size() - 1; size >= 0; size--) {
                Activity activity = activityList.get(size);
                if (!activity.getClass().equals(cls)) {
                    finishActivity(activity, z);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Activity activity, boolean z, boolean z2) {
        if (activity != null) {
            LinkedList<Activity> activityList = Utils.getActivityList();
            for (int size = activityList.size() - 1; size >= 0; size--) {
                Activity activity2 = activityList.get(size);
                if (activity2.equals(activity)) {
                    if (z) {
                        finishActivity(activity2, z2);
                    }
                    return true;
                }
                finishActivity(activity2, z2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityIcon(Class<? extends Activity> cls) {
        if (cls != null) {
            return getActivityIcon(new ComponentName(Utils.getApp(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityLogo(Class<? extends Activity> cls) {
        if (cls != null) {
            return getActivityLogo(new ComponentName(Utils.getApp(), cls));
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivities(Intent[] intentArr, Bundle bundle) {
        if (intentArr != null) {
            startActivities(intentArr, Utils.getTopActivityOrApp(), bundle);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Class<? extends Activity> cls, Bundle bundle) {
        if (cls != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), bundle);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, bundle);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishAllActivitiesExceptNewest(int i, int i2) {
        LinkedList<Activity> activityList = Utils.getActivityList();
        for (int size = activityList.size() - 2; size >= 0; size--) {
            finishActivity(activityList.get(size), i, i2);
        }
    }

    public static Drawable getActivityIcon(ComponentName componentName) {
        if (componentName != null) {
            try {
                return Utils.getApp().getPackageManager().getActivityIcon(componentName);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static Drawable getActivityLogo(ComponentName componentName) {
        if (componentName != null) {
            try {
                return Utils.getApp().getPackageManager().getActivityLogo(componentName);
            } catch (PackageManager.NameNotFoundException e2) {
                e2.printStackTrace();
                return null;
            }
        }
        throw new NullPointerException("Argument 'activityName' of type ComponentName (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean isActivityExistsInStack(Class<? extends Activity> cls) {
        if (cls != null) {
            Iterator<Activity> it = Utils.getActivityList().iterator();
            while (it.hasNext()) {
                if (it.next().getClass().equals(cls)) {
                    return true;
                }
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivities(Intent[] intentArr, int i, int i2) {
        if (intentArr != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivities(intentArr, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
                return;
            }
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Activity activity, int i, int i2) {
        if (activity != null) {
            activity.finish();
            activity.overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishAllActivities(int i, int i2) {
        LinkedList<Activity> activityList = Utils.getActivityList();
        for (int size = activityList.size() - 1; size >= 0; size--) {
            Activity activity = activityList.get(size);
            activity.finish();
            activity.overridePendingTransition(i, i2);
        }
    }

    public static void startActivity(Class<? extends Activity> cls, int i, int i2) {
        if (cls != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
                return;
            }
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Class<? extends Activity> cls) {
        if (cls != null) {
            finishActivity(cls, false);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishOtherActivities(Class<? extends Activity> cls, int i, int i2) {
        if (cls != null) {
            LinkedList<Activity> activityList = Utils.getActivityList();
            for (int size = activityList.size() - 1; size >= 0; size--) {
                Activity activity = activityList.get(size);
                if (!activity.getClass().equals(cls)) {
                    finishActivity(activity, i, i2);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Activity activity, boolean z, int i, int i2) {
        if (activity != null) {
            LinkedList<Activity> activityList = Utils.getActivityList();
            for (int size = activityList.size() - 1; size >= 0; size--) {
                Activity activity2 = activityList.get(size);
                if (activity2.equals(activity)) {
                    if (z) {
                        finishActivity(activity2, i, i2);
                    }
                    return true;
                }
                finishActivity(activity2, i, i2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivities(Activity activity, Intent[] intentArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intentArr != null) {
            startActivities(intentArr, activity, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            for (Activity activity : Utils.getActivityList()) {
                if (activity.getClass().equals(cls)) {
                    activity.finish();
                    if (!z) {
                        activity.overridePendingTransition(0, 0);
                    }
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> cls, int i, int i2, int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivityForResult(activity, (Bundle) null, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivities(Activity activity, Intent[] intentArr, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intentArr != null) {
            startActivities(intentArr, activity, bundle);
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivities(Activity activity, Intent[] intentArr, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intentArr != null) {
            startActivities(intentArr, activity, getOptionsBundle(activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'intents' of type Intent[] (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void finishActivity(Class<? extends Activity> cls, int i, int i2) {
        if (cls != null) {
            for (Activity activity : Utils.getActivityList()) {
                if (activity.getClass().equals(cls)) {
                    activity.finish();
                    activity.overridePendingTransition(i, i2);
                }
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Class<? extends Activity> cls, boolean z) {
        if (cls != null) {
            return finishToActivity(cls, z, false);
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), bundle);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Class<? extends Activity> cls, boolean z, boolean z2) {
        if (cls != null) {
            LinkedList<Activity> activityList = Utils.getActivityList();
            for (int size = activityList.size() - 1; size >= 0; size--) {
                Activity activity = activityList.get(size);
                if (activity.getClass().equals(cls)) {
                    if (z) {
                        finishActivity(activity, z2);
                    }
                    return true;
                }
                finishActivity(activity, z2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static void startActivities(Intent[] intentArr, Context context, Bundle bundle) {
        if (!(context instanceof Activity)) {
            for (Intent intent : intentArr) {
                intent.addFlags(268435456);
            }
        }
        if (bundle != null && Build.VERSION.SDK_INT >= 16) {
            context.startActivities(intentArr, bundle);
        } else {
            context.startActivities(intentArr);
        }
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, bundle2);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Class<? extends Activity> cls, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, (Bundle) null, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean finishToActivity(Class<? extends Activity> cls, boolean z, int i, int i2) {
        if (cls != null) {
            LinkedList<Activity> activityList = Utils.getActivityList();
            for (int size = activityList.size() - 1; size >= 0; size--) {
                Activity activity = activityList.get(size);
                if (activity.getClass().equals(cls)) {
                    if (z) {
                        finishActivity(activity, i, i2);
                    }
                    return true;
                }
                finishActivity(activity, i, i2);
            }
            return false;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, int i2, int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivityForResult(activity, bundle, activity.getPackageName(), cls.getName(), i, getOptionsBundle(activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Class<? extends Activity> cls, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), bundle2);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Class<? extends Activity> cls, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, topActivityOrApp.getPackageName(), cls.getName(), getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
                return;
            }
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, bundle2);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, bundle, activity.getPackageName(), cls.getName(), (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, bundle, activity.getPackageName(), cls.getName(), bundle2);
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Bundle bundle, Activity activity, String str, String str2, int i, int i2, int i3) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivityForResult(activity, bundle, str, str2, i, getOptionsBundle(activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 7, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, Class<? extends Activity> cls, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (cls != null) {
            startActivity(activity, bundle, activity.getPackageName(), cls.getName(), getOptionsBundle(activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'clz' of type Class<? extends Activity> (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivityForResult(intent, activity, i, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivityForResult(intent, activity, i, bundle);
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(Utils.getTopActivityOrApp(), (Bundle) null, str, str2, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivityForResult(intent, activity, i, getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(String str, String str2, Bundle bundle) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(Utils.getTopActivityOrApp(), (Bundle) null, str, str2, bundle);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivityForResult(Activity activity, Intent intent, int i, int i2, int i3) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivityForResult(intent, activity, i, getOptionsBundle(activity, i2, i3));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i2, i3);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(String str, String str2, int i, int i2) {
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, (Bundle) null, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
                return;
            }
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static boolean startActivityForResult(Activity activity, Bundle bundle, String str, String str2, int i, Bundle bundle2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return startActivityForResult(intent, activity, i, bundle2);
    }

    public static void startActivity(Activity activity, String str, String str2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, (Bundle) null, str, str2, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static boolean startActivityForResult(Intent intent, Activity activity, int i, Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (bundle != null && Build.VERSION.SDK_INT >= 16) {
            activity.startActivityForResult(intent, i, bundle);
            return true;
        }
        activity.startActivityForResult(intent, i);
        return true;
    }

    public static void startActivity(Activity activity, String str, String str2, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, (Bundle) null, str, str2, bundle);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, String str, String str2, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, (Bundle) null, str, str2, getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, String str, String str2, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, (Bundle) null, str, str2, getOptionsBundle(activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, String str, String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(Utils.getTopActivityOrApp(), bundle, str, str2, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#2 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, String str, String str2, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(Utils.getTopActivityOrApp(), bundle, str, str2, bundle2);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, String str, String str2, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            startActivity(topActivityOrApp, bundle, str, str2, getOptionsBundle(topActivityOrApp, i, i2));
            if (Build.VERSION.SDK_INT >= 16 || !(topActivityOrApp instanceof Activity)) {
                return;
            }
            ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, bundle, str, str2, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2, Bundle bundle2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, bundle, str, str2, bundle2);
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2, View... viewArr) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, bundle, str, str2, getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 5, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Bundle bundle, Activity activity, String str, String str2, int i, int i2) {
        if (bundle == null) {
            throw new NullPointerException("Argument 'extras' of type Bundle (#0 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#1 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str == null) {
            throw new NullPointerException("Argument 'pkg' of type String (#2 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (str2 != null) {
            startActivity(activity, bundle, str, str2, getOptionsBundle(activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'cls' of type String (#3 out of 6, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean startActivity(Intent intent) {
        if (intent != null) {
            return startActivity(intent, Utils.getTopActivityOrApp(), (Bundle) null);
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean startActivity(Intent intent, Bundle bundle) {
        if (intent != null) {
            return startActivity(intent, Utils.getTopActivityOrApp(), bundle);
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static boolean startActivity(Intent intent, int i, int i2) {
        if (intent != null) {
            Context topActivityOrApp = Utils.getTopActivityOrApp();
            boolean zStartActivity = startActivity(intent, topActivityOrApp, getOptionsBundle(topActivityOrApp, i, i2));
            if (zStartActivity && Build.VERSION.SDK_INT < 16 && (topActivityOrApp instanceof Activity)) {
                ((Activity) topActivityOrApp).overridePendingTransition(i, i2);
            }
            return zStartActivity;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Intent intent) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivity(intent, activity, (Bundle) null);
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 2, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Intent intent, Bundle bundle) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivity(intent, activity, bundle);
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Intent intent, View... viewArr) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivity(intent, activity, getOptionsBundle(activity, viewArr));
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 3, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    public static void startActivity(Activity activity, Intent intent, int i, int i2) {
        if (activity == null) {
            throw new NullPointerException("Argument 'activity' of type Activity (#0 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        if (intent != null) {
            startActivity(intent, activity, getOptionsBundle(activity, i, i2));
            if (Build.VERSION.SDK_INT < 16) {
                activity.overridePendingTransition(i, i2);
                return;
            }
            return;
        }
        throw new NullPointerException("Argument 'intent' of type Intent (#1 out of 4, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
    }

    private static void startActivity(Context context, Bundle bundle, String str, String str2, Bundle bundle2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        startActivity(intent, context, bundle2);
    }

    private static boolean startActivity(Intent intent, Context context, Bundle bundle) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (bundle != null && Build.VERSION.SDK_INT >= 16) {
            context.startActivity(intent, bundle);
            return true;
        }
        context.startActivity(intent);
        return true;
    }
}
