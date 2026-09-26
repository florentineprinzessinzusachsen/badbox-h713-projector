package androidx.core.app;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.constant.TimeConstants;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: compiled from: NotificationManagerCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static String f947d;
    private static c g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f950a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final NotificationManager f951b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f946c = new Object();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static Set<String> f948e = new HashSet();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final Object f949f = new Object();

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ComponentName f956a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final IBinder f957b;

        b(ComponentName componentName, IBinder iBinder) {
            this.f956a = componentName;
            this.f957b = iBinder;
        }
    }

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    private static class c implements Handler.Callback, ServiceConnection {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Context f958a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Handler f960c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Map<ComponentName, a> f961d = new HashMap();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private Set<String> f962e = new HashSet();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final HandlerThread f959b = new HandlerThread("NotificationManagerCompat");

        /* JADX INFO: compiled from: NotificationManagerCompat.java */
        private static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final ComponentName f963a;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            INotificationSideChannel f965c;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            boolean f964b = false;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            ArrayDeque<d> f966d = new ArrayDeque<>();

            /* JADX INFO: renamed from: e, reason: collision with root package name */
            int f967e = 0;

            a(ComponentName componentName) {
                this.f963a = componentName;
            }
        }

        c(Context context) {
            this.f958a = context;
            this.f959b.start();
            this.f960c = new Handler(this.f959b.getLooper(), this);
        }

        private void b(d dVar) {
            a();
            for (a aVar : this.f961d.values()) {
                aVar.f966d.add(dVar);
                c(aVar);
            }
        }

        private void c(a aVar) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + aVar.f963a + ", " + aVar.f966d.size() + " queued tasks");
            }
            if (aVar.f966d.isEmpty()) {
                return;
            }
            if (!a(aVar) || aVar.f965c == null) {
                d(aVar);
                return;
            }
            while (true) {
                d dVarPeek = aVar.f966d.peek();
                if (dVarPeek == null) {
                    break;
                }
                try {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Sending task " + dVarPeek);
                    }
                    dVarPeek.a(aVar.f965c);
                    aVar.f966d.remove();
                } catch (DeadObjectException unused) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Remote service has died: " + aVar.f963a);
                    }
                } catch (RemoteException e2) {
                    Log.w("NotifManCompat", "RemoteException communicating with " + aVar.f963a, e2);
                }
            }
            if (aVar.f966d.isEmpty()) {
                return;
            }
            d(aVar);
        }

        private void d(a aVar) {
            if (this.f960c.hasMessages(3, aVar.f963a)) {
                return;
            }
            aVar.f967e++;
            int i = aVar.f967e;
            if (i <= 6) {
                int i2 = (1 << (i - 1)) * TimeConstants.SEC;
                if (Log.isLoggable("NotifManCompat", 3)) {
                    Log.d("NotifManCompat", "Scheduling retry for " + i2 + " ms");
                }
                this.f960c.sendMessageDelayed(this.f960c.obtainMessage(3, aVar.f963a), i2);
                return;
            }
            Log.w("NotifManCompat", "Giving up on delivering " + aVar.f966d.size() + " tasks to " + aVar.f963a + " after " + aVar.f967e + " retries");
            aVar.f966d.clear();
        }

        public void a(d dVar) {
            this.f960c.obtainMessage(0, dVar).sendToTarget();
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                b((d) message.obj);
                return true;
            }
            if (i == 1) {
                b bVar = (b) message.obj;
                a(bVar.f956a, bVar.f957b);
                return true;
            }
            if (i == 2) {
                b((ComponentName) message.obj);
                return true;
            }
            if (i != 3) {
                return false;
            }
            a((ComponentName) message.obj);
            return true;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName);
            }
            this.f960c.obtainMessage(1, new b(componentName, iBinder)).sendToTarget();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName);
            }
            this.f960c.obtainMessage(2, componentName).sendToTarget();
        }

        private void a(ComponentName componentName, IBinder iBinder) {
            a aVar = this.f961d.get(componentName);
            if (aVar != null) {
                aVar.f965c = INotificationSideChannel.Stub.asInterface(iBinder);
                aVar.f967e = 0;
                c(aVar);
            }
        }

        private void b(ComponentName componentName) {
            a aVar = this.f961d.get(componentName);
            if (aVar != null) {
                b(aVar);
            }
        }

        private void a(ComponentName componentName) {
            a aVar = this.f961d.get(componentName);
            if (aVar != null) {
                c(aVar);
            }
        }

        private void b(a aVar) {
            if (aVar.f964b) {
                this.f958a.unbindService(this);
                aVar.f964b = false;
            }
            aVar.f965c = null;
        }

        private void a() {
            Set<String> setB = k.b(this.f958a);
            if (setB.equals(this.f962e)) {
                return;
            }
            this.f962e = setB;
            List<ResolveInfo> listQueryIntentServices = this.f958a.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
            HashSet<ComponentName> hashSet = new HashSet();
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (setB.contains(resolveInfo.serviceInfo.packageName)) {
                    ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
                    if (resolveInfo.serviceInfo.permission != null) {
                        Log.w("NotifManCompat", "Permission present on component " + componentName + ", not adding listener record.");
                    } else {
                        hashSet.add(componentName);
                    }
                }
            }
            for (ComponentName componentName2 : hashSet) {
                if (!this.f961d.containsKey(componentName2)) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Adding listener record for " + componentName2);
                    }
                    this.f961d.put(componentName2, new a(componentName2));
                }
            }
            Iterator<Map.Entry<ComponentName, a>> it = this.f961d.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<ComponentName, a> next = it.next();
                if (!hashSet.contains(next.getKey())) {
                    if (Log.isLoggable("NotifManCompat", 3)) {
                        Log.d("NotifManCompat", "Removing listener record for " + next.getKey());
                    }
                    b(next.getValue());
                    it.remove();
                }
            }
        }

        private boolean a(a aVar) {
            if (aVar.f964b) {
                return true;
            }
            aVar.f964b = this.f958a.bindService(new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(aVar.f963a), this, 33);
            if (aVar.f964b) {
                aVar.f967e = 0;
            } else {
                Log.w("NotifManCompat", "Unable to bind to listener " + aVar.f963a);
                this.f958a.unbindService(this);
            }
            return aVar.f964b;
        }
    }

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    private interface d {
        void a(INotificationSideChannel iNotificationSideChannel);
    }

    private k(Context context) {
        this.f950a = context;
        this.f951b = (NotificationManager) this.f950a.getSystemService("notification");
    }

    public static k a(Context context) {
        return new k(context);
    }

    public void b() {
        this.f951b.cancelAll();
        if (Build.VERSION.SDK_INT <= 19) {
            a(new a(this.f950a.getPackageName()));
        }
    }

    public void a(int i) {
        a(null, i);
    }

    public void a(String str, int i) {
        this.f951b.cancel(str, i);
        if (Build.VERSION.SDK_INT <= 19) {
            a(new a(this.f950a.getPackageName(), i, str));
        }
    }

    public static Set<String> b(Context context) {
        Set<String> set;
        String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        synchronized (f946c) {
            if (string != null) {
                if (!string.equals(f947d)) {
                    String[] strArrSplit = string.split(Config.TRACE_TODAY_VISIT_SPLIT, -1);
                    HashSet hashSet = new HashSet(strArrSplit.length);
                    for (String str : strArrSplit) {
                        ComponentName componentNameUnflattenFromString = ComponentName.unflattenFromString(str);
                        if (componentNameUnflattenFromString != null) {
                            hashSet.add(componentNameUnflattenFromString.getPackageName());
                        }
                    }
                    f948e = hashSet;
                    f947d = string;
                }
                set = f948e;
            } else {
                set = f948e;
            }
            throw th;
        }
        return set;
    }

    /* JADX INFO: compiled from: NotificationManagerCompat.java */
    private static class a implements d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f952a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f953b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final String f954c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f955d;

        a(String str) {
            this.f952a = str;
            this.f953b = 0;
            this.f954c = null;
            this.f955d = true;
        }

        @Override // androidx.core.app.k.d
        public void a(INotificationSideChannel iNotificationSideChannel) {
            if (this.f955d) {
                iNotificationSideChannel.cancelAll(this.f952a);
            } else {
                iNotificationSideChannel.cancel(this.f952a, this.f953b, this.f954c);
            }
        }

        public String toString() {
            return "CancelTask[packageName:" + this.f952a + ", id:" + this.f953b + ", tag:" + this.f954c + ", all:" + this.f955d + "]";
        }

        a(String str, int i, String str2) {
            this.f952a = str;
            this.f953b = i;
            this.f954c = str2;
            this.f955d = false;
        }
    }

    public boolean a() {
        int i = Build.VERSION.SDK_INT;
        if (i >= 24) {
            return this.f951b.areNotificationsEnabled();
        }
        if (i < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) this.f950a.getSystemService("appops");
        ApplicationInfo applicationInfo = this.f950a.getApplicationInfo();
        String packageName = this.f950a.getApplicationContext().getPackageName();
        int i2 = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i2), packageName)).intValue() == 0;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }

    private void a(d dVar) {
        synchronized (f949f) {
            if (g == null) {
                g = new c(this.f950a.getApplicationContext());
            }
            g.a(dVar);
        }
    }
}
