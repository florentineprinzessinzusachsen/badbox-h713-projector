package com.blankj.utilcode.util;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.FileProvider;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public final class Utils {
    private static final String PERMISSION_ACTIVITY_CLASS_NAME = "com.blankj.utilcode.util.PermissionUtils$PermissionActivity";

    @SuppressLint({"StaticFieldLeak"})
    private static Application sApplication;
    private static final ActivityLifecycleImpl ACTIVITY_LIFECYCLE = new ActivityLifecycleImpl();
    private static final ExecutorService UTIL_POOL = Executors.newFixedThreadPool(3);
    private static final Handler UTIL_HANDLER = new Handler(Looper.getMainLooper());

    static class ActivityLifecycleImpl implements Application.ActivityLifecycleCallbacks {
        final LinkedList<Activity> mActivityList = new LinkedList<>();
        final Map<Object, OnAppStatusChangedListener> mStatusListenerMap = new HashMap();
        final Map<Activity, Set<OnActivityDestroyedListener>> mDestroyedListenerMap = new HashMap();
        private int mForegroundCount = 0;
        private int mConfigCount = 0;
        private boolean mIsBackground = false;

        ActivityLifecycleImpl() {
        }

        private void consumeOnActivityDestroyedListener(Activity activity) {
            Iterator<Map.Entry<Activity, Set<OnActivityDestroyedListener>>> it = this.mDestroyedListenerMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Activity, Set<OnActivityDestroyedListener>> next = it.next();
                if (next.getKey() == activity) {
                    Iterator<OnActivityDestroyedListener> it2 = next.getValue().iterator();
                    while (it2.hasNext()) {
                        it2.next().onActivityDestroyed(activity);
                    }
                    it.remove();
                }
            }
        }

        private Activity getTopActivityByReflect() {
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Object objInvoke = cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]);
                Field declaredField = cls.getDeclaredField("mActivityList");
                declaredField.setAccessible(true);
                Map map = (Map) declaredField.get(objInvoke);
                if (map == null) {
                    return null;
                }
                for (Object obj : map.values()) {
                    Class<?> cls2 = obj.getClass();
                    Field declaredField2 = cls2.getDeclaredField("paused");
                    declaredField2.setAccessible(true);
                    if (!declaredField2.getBoolean(obj)) {
                        Field declaredField3 = cls2.getDeclaredField("activity");
                        declaredField3.setAccessible(true);
                        return (Activity) declaredField3.get(obj);
                    }
                }
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            } catch (IllegalAccessException e3) {
                e3.printStackTrace();
            } catch (NoSuchFieldException e4) {
                e4.printStackTrace();
            } catch (NoSuchMethodException e5) {
                e5.printStackTrace();
            } catch (InvocationTargetException e6) {
                e6.printStackTrace();
            }
            return null;
        }

        private void postStatus(boolean z) {
            OnAppStatusChangedListener next;
            if (this.mStatusListenerMap.isEmpty()) {
                return;
            }
            Iterator<OnAppStatusChangedListener> it = this.mStatusListenerMap.values().iterator();
            while (it.hasNext() && (next = it.next()) != null) {
                if (z) {
                    next.onForeground();
                } else {
                    next.onBackground();
                }
            }
        }

        private void processHideSoftInputOnActivityDestroy(final Activity activity, boolean z) {
            if (z) {
                activity.getWindow().getDecorView().setTag(-123, Integer.valueOf(activity.getWindow().getAttributes().softInputMode));
                activity.getWindow().setSoftInputMode(3);
            } else {
                final Object tag = activity.getWindow().getDecorView().getTag(-123);
                if (tag instanceof Integer) {
                    Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.Utils.ActivityLifecycleImpl.1
                        @Override // java.lang.Runnable
                        public void run() {
                            activity.getWindow().setSoftInputMode(((Integer) tag).intValue());
                        }
                    }, 100L);
                }
            }
        }

        private void setTopActivity(Activity activity) {
            if (Utils.PERMISSION_ACTIVITY_CLASS_NAME.equals(activity.getClass().getName())) {
                return;
            }
            if (!this.mActivityList.contains(activity)) {
                this.mActivityList.addLast(activity);
            } else {
                if (this.mActivityList.getLast().equals(activity)) {
                    return;
                }
                this.mActivityList.remove(activity);
                this.mActivityList.addLast(activity);
            }
        }

        void addOnActivityDestroyedListener(Activity activity, OnActivityDestroyedListener onActivityDestroyedListener) {
            Set<OnActivityDestroyedListener> hashSet;
            if (activity == null || onActivityDestroyedListener == null) {
                return;
            }
            if (this.mDestroyedListenerMap.containsKey(activity)) {
                hashSet = this.mDestroyedListenerMap.get(activity);
                if (hashSet.contains(onActivityDestroyedListener)) {
                    return;
                }
            } else {
                hashSet = new HashSet<>();
                this.mDestroyedListenerMap.put(activity, hashSet);
            }
            hashSet.add(onActivityDestroyedListener);
        }

        void addOnAppStatusChangedListener(Object obj, OnAppStatusChangedListener onAppStatusChangedListener) {
            this.mStatusListenerMap.put(obj, onAppStatusChangedListener);
        }

        Activity getTopActivity() {
            if (!this.mActivityList.isEmpty()) {
                for (int size = this.mActivityList.size() - 1; size >= 0; size--) {
                    Activity activity = this.mActivityList.get(size);
                    if (activity != null && !activity.isFinishing() && (Build.VERSION.SDK_INT < 17 || !activity.isDestroyed())) {
                        return activity;
                    }
                }
            }
            Activity topActivityByReflect = getTopActivityByReflect();
            if (topActivityByReflect != null) {
                setTopActivity(topActivityByReflect);
            }
            return topActivityByReflect;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            LanguageUtils.applyLanguage(activity);
            Utils.setAnimatorsEnabled();
            setTopActivity(activity);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            this.mActivityList.remove(activity);
            consumeOnActivityDestroyedListener(activity);
            Utils.fixSoftInputLeaks(activity.getWindow());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            setTopActivity(activity);
            if (this.mIsBackground) {
                this.mIsBackground = false;
                postStatus(true);
            }
            processHideSoftInputOnActivityDestroy(activity, false);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (!this.mIsBackground) {
                setTopActivity(activity);
            }
            int i = this.mConfigCount;
            if (i < 0) {
                this.mConfigCount = i + 1;
            } else {
                this.mForegroundCount++;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (activity.isChangingConfigurations()) {
                this.mConfigCount--;
            } else {
                this.mForegroundCount--;
                if (this.mForegroundCount <= 0) {
                    this.mIsBackground = true;
                    postStatus(false);
                }
            }
            processHideSoftInputOnActivityDestroy(activity, true);
        }

        void removeOnActivityDestroyedListener(Activity activity) {
            if (activity == null) {
                return;
            }
            this.mDestroyedListenerMap.remove(activity);
        }

        void removeOnAppStatusChangedListener(Object obj) {
            this.mStatusListenerMap.remove(obj);
        }
    }

    public interface Callback<T> {
        void onCall(T t);
    }

    public static final class FileProvider4UtilCode extends FileProvider {
        @Override // androidx.core.content.FileProvider, android.content.ContentProvider
        public boolean onCreate() {
            Utils.init(getContext());
            return true;
        }
    }

    public interface OnActivityDestroyedListener {
        void onActivityDestroyed(Activity activity);
    }

    public interface OnAppStatusChangedListener {
        void onBackground();

        void onForeground();
    }

    public static abstract class Task<Result> implements Runnable {
        private static final int CANCELLED = 2;
        private static final int COMPLETING = 1;
        private static final int EXCEPTIONAL = 3;
        private static final int NEW = 0;
        private Callback<Result> mCallback;
        private volatile int state = 0;

        public Task(Callback<Result> callback) {
            this.mCallback = callback;
        }

        public void cancel() {
            this.state = 2;
        }

        abstract Result doInBackground();

        public boolean isCanceled() {
            return this.state == 2;
        }

        public boolean isDone() {
            return this.state != 0;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                final Result resultDoInBackground = doInBackground();
                if (this.state != 0) {
                    return;
                }
                this.state = 1;
                Utils.UTIL_HANDLER.post(new Runnable() { // from class: com.blankj.utilcode.util.Utils.Task.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Task.this.mCallback.onCall(resultDoInBackground);
                    }
                });
            } catch (Throwable unused) {
                if (this.state != 0) {
                    return;
                }
                this.state = 3;
            }
        }
    }

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    static <T> Task<T> doAsync(Task<T> task) {
        UTIL_POOL.execute(task);
        return task;
    }

    static void fixSoftInputLeaks(Window window) {
        InputMethodManager inputMethodManager = (InputMethodManager) getApp().getSystemService("input_method");
        if (inputMethodManager == null) {
            return;
        }
        for (String str : new String[]{"mLastSrvView", "mCurRootView", "mServedView", "mNextServedView"}) {
            try {
                Field declaredField = InputMethodManager.class.getDeclaredField(str);
                if (declaredField != null) {
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    Object obj = declaredField.get(inputMethodManager);
                    if ((obj instanceof View) && ((View) obj).getRootView() == window.getDecorView().getRootView()) {
                        declaredField.set(inputMethodManager, null);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    static ActivityLifecycleImpl getActivityLifecycle() {
        return ACTIVITY_LIFECYCLE;
    }

    static LinkedList<Activity> getActivityList() {
        return ACTIVITY_LIFECYCLE.mActivityList;
    }

    public static Application getApp() {
        Application application = sApplication;
        if (application != null) {
            return application;
        }
        Application applicationByReflect = getApplicationByReflect();
        init(applicationByReflect);
        return applicationByReflect;
    }

    private static Application getApplicationByReflect() {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Object objInvoke = cls.getMethod("getApplication", new Class[0]).invoke(cls.getMethod("currentActivityThread", new Class[0]).invoke(null, new Object[0]), new Object[0]);
            if (objInvoke != null) {
                return (Application) objInvoke;
            }
            throw new NullPointerException("u should init first");
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            throw new NullPointerException("u should init first");
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            throw new NullPointerException("u should init first");
        }
    }

    static String getCurrentProcessName() {
        String currentProcessNameByFile = getCurrentProcessNameByFile();
        if (!TextUtils.isEmpty(currentProcessNameByFile)) {
            return currentProcessNameByFile;
        }
        String currentProcessNameByAms = getCurrentProcessNameByAms();
        return !TextUtils.isEmpty(currentProcessNameByAms) ? currentProcessNameByAms : getCurrentProcessNameByReflect();
    }

    private static String getCurrentProcessNameByAms() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        String str;
        ActivityManager activityManager = (ActivityManager) getApp().getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid && (str = runningAppProcessInfo.processName) != null) {
                    return str;
                }
            }
        }
        return "";
    }

    private static String getCurrentProcessNameByFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/proc/" + Process.myPid() + "/cmdline")));
            String strTrim = bufferedReader.readLine().trim();
            bufferedReader.close();
            return strTrim;
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private static String getCurrentProcessNameByReflect() {
        try {
            Application app = getApp();
            Field field = app.getClass().getField("mLoadedApk");
            field.setAccessible(true);
            Object obj = field.get(app);
            Field declaredField = obj.getClass().getDeclaredField("mActivityThread");
            declaredField.setAccessible(true);
            Object obj2 = declaredField.get(obj);
            return (String) obj2.getClass().getDeclaredMethod("getProcessName", new Class[0]).invoke(obj2, new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    static SPUtils getSpUtils4Utils() {
        return SPUtils.getInstance("Utils");
    }

    static Context getTopActivityOrApp() {
        if (!isAppForeground()) {
            return getApp();
        }
        Activity topActivity = ACTIVITY_LIFECYCLE.getTopActivity();
        return topActivity == null ? getApp() : topActivity;
    }

    public static void init(Context context) {
        if (context == null) {
            init(getApplicationByReflect());
        } else {
            init((Application) context.getApplicationContext());
        }
    }

    static boolean isAppForeground() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        ActivityManager activityManager = (ActivityManager) getApp().getSystemService("activity");
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(getApp().getPackageName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            UTIL_HANDLER.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        UTIL_HANDLER.postDelayed(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setAnimatorsEnabled() {
        if (Build.VERSION.SDK_INT < 26 || !ValueAnimator.areAnimatorsEnabled()) {
            try {
                Field declaredField = ValueAnimator.class.getDeclaredField("sDurationScale");
                declaredField.setAccessible(true);
                if (((Float) declaredField.get(null)).floatValue() == 0.0f) {
                    declaredField.set(null, Float.valueOf(1.0f));
                    Log.i("Utils", "setAnimatorsEnabled: Animators are enabled now!");
                }
            } catch (IllegalAccessException e2) {
                e2.printStackTrace();
            } catch (NoSuchFieldException e3) {
                e3.printStackTrace();
            }
        }
    }

    public static void init(Application application) {
        if (sApplication == null) {
            if (application == null) {
                sApplication = getApplicationByReflect();
            } else {
                sApplication = application;
            }
            sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
            return;
        }
        if (application == null || application.getClass() == sApplication.getClass()) {
            return;
        }
        sApplication.unregisterActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
        ACTIVITY_LIFECYCLE.mActivityList.clear();
        sApplication = application;
        sApplication.registerActivityLifecycleCallbacks(ACTIVITY_LIFECYCLE);
    }
}
