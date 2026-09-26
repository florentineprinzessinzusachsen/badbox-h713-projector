package androidx.core.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: compiled from: ActivityRecreator.java */
/* JADX INFO: loaded from: classes.dex */
final class c {
    private static final Handler g = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected static final Class<?> f907a = a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected static final Field f908b = b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected static final Field f909c = c();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected static final Method f910d = b(f907a);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected static final Method f911e = a(f907a);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected static final Method f912f = c(f907a);

    /* JADX INFO: compiled from: ActivityRecreator.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ d f913a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f914b;

        a(d dVar, Object obj) {
            this.f913a = dVar;
            this.f914b = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f913a.f919a = this.f914b;
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Application f915a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ d f916b;

        b(Application application, d dVar) {
            this.f915a = application;
            this.f916b = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f915a.unregisterActivityLifecycleCallbacks(this.f916b);
        }
    }

    /* JADX INFO: renamed from: androidx.core.app.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityRecreator.java */
    static class RunnableC0015c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Object f917a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f918b;

        RunnableC0015c(Object obj, Object obj2) {
            this.f917a = obj;
            this.f918b = obj2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (c.f910d != null) {
                    c.f910d.invoke(this.f917a, this.f918b, false, "AppCompat recreation");
                } else {
                    c.f911e.invoke(this.f917a, this.f918b, false);
                }
            } catch (RuntimeException e2) {
                if (e2.getClass() == RuntimeException.class && e2.getMessage() != null && e2.getMessage().startsWith("Unable to stop")) {
                    throw e2;
                }
            } catch (Throwable th) {
                Log.e("ActivityRecreator", "Exception while invoking performStopActivity", th);
            }
        }
    }

    /* JADX INFO: compiled from: ActivityRecreator.java */
    private static final class d implements Application.ActivityLifecycleCallbacks {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        Object f919a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Activity f920b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f921c = false;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f922d = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f923e = false;

        d(Activity activity) {
            this.f920b = activity;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            if (this.f920b == activity) {
                this.f920b = null;
                this.f922d = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (!this.f922d || this.f923e || this.f921c || !c.a(this.f919a, activity)) {
                return;
            }
            this.f923e = true;
            this.f919a = null;
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (this.f920b == activity) {
                this.f921c = true;
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
        }
    }

    static boolean a(Activity activity) {
        Object obj;
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
            return true;
        }
        if (d() && f912f == null) {
            return false;
        }
        if (f911e == null && f910d == null) {
            return false;
        }
        try {
            Object obj2 = f909c.get(activity);
            if (obj2 == null || (obj = f908b.get(activity)) == null) {
                return false;
            }
            Application application = activity.getApplication();
            d dVar = new d(activity);
            application.registerActivityLifecycleCallbacks(dVar);
            g.post(new a(dVar, obj2));
            try {
                if (d()) {
                    f912f.invoke(obj, obj2, null, null, 0, false, null, null, false, false);
                } else {
                    activity.recreate();
                }
                return true;
            } finally {
                g.post(new b(application, dVar));
            }
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Method b(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE, String.class);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method c(Class<?> cls) {
        if (d() && cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod("requestRelaunchActivity", IBinder.class, List.class, List.class, Integer.TYPE, Boolean.TYPE, Configuration.class, Configuration.class, Boolean.TYPE, Boolean.TYPE);
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    private static boolean d() {
        int i = Build.VERSION.SDK_INT;
        return i == 26 || i == 27;
    }

    private static Field b() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mMainThread");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Field c() {
        try {
            Field declaredField = Activity.class.getDeclaredField("mToken");
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable unused) {
            return null;
        }
    }

    protected static boolean a(Object obj, Activity activity) {
        try {
            Object obj2 = f909c.get(activity);
            if (obj2 != obj) {
                return false;
            }
            g.postAtFrontOfQueue(new RunnableC0015c(f908b.get(activity), obj2));
            return true;
        } catch (Throwable th) {
            Log.e("ActivityRecreator", "Exception while fetching field values", th);
            return false;
        }
    }

    private static Method a(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        try {
            Method declaredMethod = cls.getDeclaredMethod("performStopActivity", IBinder.class, Boolean.TYPE);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Class<?> a() {
        try {
            return Class.forName("android.app.ActivityThread");
        } catch (Throwable unused) {
            return null;
        }
    }
}
