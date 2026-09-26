package com.baidu.mobstat;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class ActivityLifeObserver {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final ActivityLifeObserver f3211b = new ActivityLifeObserver();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3212a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Set<IActivityLifeCallback> f3213c = new LinkedHashSet();

    public interface IActivityLifeCallback {
        void onActivityCreated(Activity activity, Bundle bundle);

        void onActivityDestroyed(Activity activity);

        void onActivityPaused(Activity activity);

        void onActivityResumed(Activity activity);

        void onActivitySaveInstanceState(Activity activity, Bundle bundle);

        void onActivityStarted(Activity activity);

        void onActivityStopped(Activity activity);
    }

    public static ActivityLifeObserver instance() {
        return f3211b;
    }

    public void addObserver(IActivityLifeCallback iActivityLifeCallback) {
        synchronized (this.f3213c) {
            this.f3213c.add(iActivityLifeCallback);
        }
    }

    public void clearObservers() {
        synchronized (this.f3213c) {
            this.f3213c.clear();
        }
    }

    @TargetApi(14)
    public void doRegister(Context context) {
        try {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.baidu.mobstat.ActivityLifeObserver.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity, Bundle bundle) {
                    synchronized (ActivityLifeObserver.this.f3213c) {
                        Iterator it = ActivityLifeObserver.this.f3213c.iterator();
                        while (it.hasNext()) {
                            ((IActivityLifeCallback) it.next()).onActivityCreated(activity, bundle);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f3213c) {
                        Iterator it = ActivityLifeObserver.this.f3213c.iterator();
                        while (it.hasNext()) {
                            ((IActivityLifeCallback) it.next()).onActivityDestroyed(activity);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f3213c) {
                        Iterator it = ActivityLifeObserver.this.f3213c.iterator();
                        while (it.hasNext()) {
                            ((IActivityLifeCallback) it.next()).onActivityPaused(activity);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f3213c) {
                        Iterator it = ActivityLifeObserver.this.f3213c.iterator();
                        while (it.hasNext()) {
                            ((IActivityLifeCallback) it.next()).onActivityResumed(activity);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                    synchronized (ActivityLifeObserver.this.f3213c) {
                        Iterator it = ActivityLifeObserver.this.f3213c.iterator();
                        while (it.hasNext()) {
                            ((IActivityLifeCallback) it.next()).onActivitySaveInstanceState(activity, bundle);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f3213c) {
                        Iterator it = ActivityLifeObserver.this.f3213c.iterator();
                        while (it.hasNext()) {
                            ((IActivityLifeCallback) it.next()).onActivityStarted(activity);
                        }
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity) {
                    synchronized (ActivityLifeObserver.this.f3213c) {
                        Iterator it = ActivityLifeObserver.this.f3213c.iterator();
                        while (it.hasNext()) {
                            ((IActivityLifeCallback) it.next()).onActivityStopped(activity);
                        }
                    }
                }
            });
        } catch (Exception unused) {
            am.c().a("registerActivityLifecycleCallbacks encounter exception");
        }
    }

    public void registerActivityLifeCallback(Context context) {
        if (!this.f3212a && android.os.Build.VERSION.SDK_INT >= 14) {
            doRegister(context);
            this.f3212a = true;
        }
    }

    public void removeObserver(IActivityLifeCallback iActivityLifeCallback) {
        synchronized (this.f3213c) {
            this.f3213c.remove(iActivityLifeCallback);
        }
    }
}
