package com.android.sysapp;

import a.a.b.m;
import a.a.b.n;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UpdateService extends Service implements a.a.b.g.a {
    public static boolean p = false;
    public static int q = 0;
    public static a.a.b.j r = null;
    public static a.a.b.j s = null;
    public static boolean t = false;
    public static boolean u = false;
    public static boolean v = false;
    public static a.a.c.a.c w;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Context f57a;
    public BroadcastReceiver c;
    public BroadcastReceiver d;
    public a.a.b.g f;
    public a.a.b.h k;
    public a.a.b.i l;
    public k b = new k();
    public final Handler e = new Handler();
    public AlertDialog.Builder g = null;
    public AlertDialog h = null;
    public AlertDialog.Builder i = null;
    public AlertDialog j = null;
    public boolean m = false;
    public String n = "/advert.bmp";
    public String o = "/bootanimation.zip";

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            UpdateService.this.k.a(0);
            a.a.b.k.a();
            UpdateService.this.f.a();
            Log.e("CMUpdate2Service", "setAppMode 0 clearCache  startReboot");
        }
    }

    public class b implements Runnable {
        public b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            UpdateService.this.k.a(0);
            a.a.b.k.a();
            UpdateService.this.f.b();
            Log.e("CMUpdate2Service", "setAppMode 0 clearCache  startReboot");
        }
    }

    public class c implements Runnable {
        public c(UpdateService updateService) {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    public class d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f60a;

        public d(UpdateService updateService, int i) {
            this.f60a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f60a;
            if (i != 0 && i == 100) {
                Log.i("CMUpdate2Service", "onVerify progress == 100!");
            }
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i("CMUpdate2Service", "onVerifyFailed");
            a.a.b.k.a();
            UpdateService.this.k.a(0);
        }
    }

    public class f implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f62a;

        public f(int i) {
            this.f62a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f62a;
            if (i != 0 && i == 100) {
                Log.i("CMUpdate2Service", "onCopyProgress == 100!");
                if (!UpdateService.p) {
                    Log.i("CMUpdate2Service", "Normal Update Mode！installPackage() ");
                    return;
                }
                Log.i("CMUpdate2Service", "Force Update Mode！global window ");
                Intent intent = new Intent(UpdateService.this.f57a, (Class<?>) UpdateService.class);
                intent.putExtra("start_command", 107);
                UpdateService.this.f57a.startService(intent);
            }
        }
    }

    public class g implements Runnable {

        public class a implements DialogInterface.OnClickListener {

            /* JADX INFO: renamed from: com.android.sysapp.UpdateService$g$a$a, reason: collision with other inner class name */
            public class RunnableC0007a implements Runnable {
                public RunnableC0007a(a aVar) {
                }

                @Override // java.lang.Runnable
                public void run() {
                    a.a.c.a.c cVar = UpdateService.w;
                    if (cVar != null) {
                        ((a.a.c.a.g.b.a) cVar).a(6000);
                    }
                }
            }

            public class b implements Runnable {
                public b() {
                }

                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    UpdateService.this.f.a(a.a.b.g.e + "/update.zip", UpdateService.this);
                }
            }

            public a() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                UpdateService updateService = UpdateService.this;
                if (updateService.f == null) {
                    updateService.f = new a.a.b.g(updateService.f57a);
                }
                UpdateService.this.e.post(new RunnableC0007a(this));
                UpdateService.this.f();
                UpdateService updateService2 = UpdateService.this;
                updateService2.m = true;
                updateService2.k.a(0);
                new Thread(new b()).start();
            }
        }

        public class b implements DialogInterface.OnClickListener {
            public b() {
            }

            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Log.i("CMUpdate2Service", "The next reboot update OS!Set AppMode 6!");
                UpdateService.this.k.a(6);
                if (UpdateService.this.h.isShowing()) {
                    UpdateService.this.h.dismiss();
                }
            }
        }

        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (UpdateService.this.g == null) {
                    UpdateService.this.g = new AlertDialog.Builder(UpdateService.this.f57a, android.R.style.Theme.Holo.Light.Dialog);
                    UpdateService.this.g.setCancelable(false);
                    UpdateService.this.g.setMessage(R.string.global_Message).setTitle(R.string.global_Title);
                    UpdateService.this.g.setPositiveButton(R.string.global_Positive_Button, new a());
                    UpdateService.this.g.setNegativeButton(R.string.global_Negative_Button, new b());
                }
                if (UpdateService.this.h == null) {
                    UpdateService.this.h = UpdateService.this.g.create();
                    UpdateService.this.h.setCancelable(false);
                }
                if (UpdateService.this.h.isShowing()) {
                    return;
                }
                UpdateService.this.h.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                UpdateService.this.h.getWindow().setType(2003);
                UpdateService.this.h.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class h implements Runnable {
        public h() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Looper.prepare();
                if (UpdateService.this.h != null && UpdateService.this.h.isShowing()) {
                    UpdateService.this.h.dismiss();
                }
                if (UpdateService.this.i == null) {
                    UpdateService.this.i = new AlertDialog.Builder(UpdateService.this.f57a, android.R.style.Theme.Holo.Light.Dialog);
                    UpdateService.this.i.setCancelable(false);
                    UpdateService.this.i.setMessage(R.string.global_Message_2).setTitle(R.string.global_Title);
                }
                if (UpdateService.this.j == null) {
                    UpdateService.this.j = UpdateService.this.i.create();
                    UpdateService.this.j.setCancelable(false);
                }
                if (!UpdateService.this.j.isShowing()) {
                    UpdateService.this.j.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    UpdateService.this.j.getWindow().setType(2003);
                    UpdateService.this.j.show();
                }
                Looper.loop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class i extends BroadcastReceiver {
        public i() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            a.a.b.j jVar;
            String action = intent.getAction();
            if (n.f13a) {
                Log.d("CMUpdate2Service", "mNetcastReceiver()_action=" + action);
            }
            int iA = UpdateService.this.k.a();
            Log.i("CMUpdate2Service", "statusMode=" + iA);
            if (!UpdateService.this.a()) {
                Log.i("CMUpdate2Service", "no InterNet");
                if (iA != 2 || (jVar = UpdateService.s) == null || jVar.d == 1) {
                    return;
                }
                jVar.d = 2;
                return;
            }
            if (iA == 0 || iA == 1) {
                Log.i("CMUpdate2Service", "reStartCheck()");
                a.a.b.j jVar2 = UpdateService.r;
                if (jVar2 == null || jVar2.d == 1) {
                    return;
                }
                jVar2.e();
                UpdateService.this.k.a(1);
                return;
            }
            if (iA != 2) {
                Log.i("CMUpdate2Service", "6___");
                return;
            }
            Log.i("CMUpdate2Service", "case 2_mDownloadTask.start()");
            if (UpdateService.s == null || !UpdateService.p) {
                return;
            }
            a.a.b.j jVar3 = UpdateService.s;
            int i = jVar3.d;
            if (i != 2) {
                if (i != 1) {
                    jVar3.e();
                }
            } else {
                Thread thread = jVar3.f10a;
                if (thread == null || !thread.isAlive()) {
                    jVar3.e();
                }
                jVar3.d = 1;
            }
        }
    }

    public class j extends BroadcastReceiver {
        public j() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("CMUpdate2Service", "mUsbBroadcastReceiver action=" + action);
            if (action.equals("android.intent.action.MEDIA_MOUNTED")) {
                Log.i("CMUpdate2Service", "Build.VERSION.SDK_INT =" + Build.VERSION.SDK_INT + ",Build.VERSION_CODES.KITKAT=19");
                int i = Build.VERSION.SDK_INT;
                UpdateService.this.a(context);
            }
        }
    }

    public static class k extends Binder {
        public int a(int i) {
            a.a.b.j jVar;
            if (i == 101 ? (jVar = UpdateService.r) == null : i != 102 || (jVar = UpdateService.s) == null) {
                return -1;
            }
            return jVar.c;
        }

        public void a(a.a.c.a.c cVar) {
            UpdateService.w = cVar;
        }

        public int b(int i) {
            a.a.b.j jVar;
            if (i == 101 ? (jVar = UpdateService.r) == null : i != 102 || (jVar = UpdateService.s) == null) {
                return -1;
            }
            return jVar.b;
        }

        public int c(int i) {
            a.a.b.j jVar;
            if (i == 101 ? (jVar = UpdateService.r) == null : i != 102 || (jVar = UpdateService.s) == null) {
                return -1;
            }
            return jVar.d;
        }

        public boolean d(int i) {
            a.a.b.j jVar;
            if (i == 101 ? (jVar = UpdateService.r) == null : i != 102 || (jVar = UpdateService.s) == null) {
                return false;
            }
            return jVar.d();
        }
    }

    @Override // a.a.b.g.a
    public void a(int i2) {
        this.e.post(new f(i2));
    }

    @Override // a.a.b.g.a
    public void a(int i2, Object obj) {
        this.e.post(new e());
    }

    public void a(Context context) {
        try {
            List<String> listB = b();
            if (listB == null) {
                return;
            }
            boolean z = false;
            for (int i2 = 0; i2 < listB.size(); i2++) {
                Log.i("CMUpdate2Service", "list.get(" + i2 + ")=" + listB.get(i2));
                File[] fileArrListFiles = new File(listB.get(i2)).listFiles();
                if (fileArrListFiles != null) {
                    for (File file : fileArrListFiles) {
                        if (file.isFile() && file.canRead() && file.getName().endsWith("update.zip")) {
                            Log.i("CMUpdate2Service", "path =" + file.getAbsolutePath());
                            Intent intent = new Intent();
                            intent.setComponent(new ComponentName("com.android.sysapp", "com.android.sysapp.OtaActivity"));
                            intent.putExtra("path", file.getAbsolutePath());
                            intent.addFlags(268435456);
                            context.startActivity(intent);
                            return;
                        }
                        if (file.isDirectory() && file.canRead()) {
                            String str = file + "/update.zip";
                            if (new File(str).exists()) {
                                Log.i("CMUpdate2Service", "path =" + str);
                                Intent intent2 = new Intent();
                                intent2.setComponent(new ComponentName("com.android.sysapp", "com.android.sysapp.OtaActivity"));
                                intent2.putExtra("path", str);
                                intent2.addFlags(268435456);
                                context.startActivity(intent2);
                                return;
                            }
                            Log.i("CMUpdate2Service", "no path =" + str);
                        }
                    }
                }
                String str2 = listB.get(i2) + "/update.zip";
                if (str2.contains("/storage/emulated/0") || str2.contains("sdcard/") || str2.contains("internal_sd/")) {
                    Log.i("CMUpdate2Service", "2222222path =" + str2);
                } else {
                    Log.i("CMUpdate2Service", "list.get(i) =" + listB.get(i2));
                    File file2 = new File(listB.get(i2));
                    if (file2.exists() && file2.canRead()) {
                        Log.i("CMUpdate2Service", "list.get(i) true2=" + listB.get(i2));
                        z = true;
                    }
                }
            }
            if (listB.size() > 0 && z && c()) {
                Intent intent3 = new Intent(context, (Class<?>) DialogActivity.class);
                intent3.setFlags(268435456);
                context.startActivity(intent3);
            }
        } catch (Exception e2) {
            Log.i("CMUpdate2Service", "getUsbstatus()4_4 error" + e2.toString());
        }
    }

    @Override // a.a.b.g.a
    public void a(String str, int i2) {
    }

    public final boolean a() {
        Context context = this.f57a;
        if (context != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                return true;
            }
            Log.v("CMUpdate2Service", "It's can't connect the Internet!");
        }
        return false;
    }

    public final List<String> b() {
        try {
            StorageVolume[] volumeList = ((StorageManager) getSystemService("storage")).getVolumeList();
            ArrayList arrayList = new ArrayList();
            for (StorageVolume storageVolume : volumeList) {
                String path = storageVolume.getPath();
                Log.i("CMUpdate2Service", "path=" + path);
                if (path != null && !path.equals("emulated") && !path.equals("self")) {
                    arrayList.add(path);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // a.a.b.g.a
    public void b(String str, int i2) {
        Handler handler;
        Runnable cVar;
        if (i2 == -1) {
            handler = this.e;
            cVar = new a();
        } else if (i2 == 101) {
            handler = this.e;
            cVar = new b();
        } else {
            handler = this.e;
            cVar = new c(this);
        }
        handler.post(cVar);
    }

    public final boolean c() {
        String packageName;
        if (m.b().contains("RG_RK3128_D1E8")) {
            return false;
        }
        boolean z = SystemProperties.getBoolean("persist.sys.hide.jumpfm", false);
        Log.i("CMUpdate2Service", "isNeedJump jumpfm" + z);
        if (z) {
            return false;
        }
        try {
            packageName = ((ActivityManager) this.f57a.getApplicationContext().getSystemService("activity")).getRunningTasks(1).get(0).topActivity.getPackageName();
        } catch (Exception unused) {
            packageName = "error";
        }
        boolean z2 = !packageName.contains("com.cloudmedia.testapk");
        Log.i("CMUpdate2Service", "isNeedJump r1" + z2);
        return z2;
    }

    public void d() {
        this.c = new i();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("ConnectivityManager.CONNECTIVITY_ACTION");
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        registerReceiver(this.c, intentFilter);
    }

    public void e() {
        Log.i("CMUpdate2Service", "showDialog()");
        if (this.m) {
            return;
        }
        this.e.post(new g());
    }

    public void f() {
        Log.i("CMUpdate2Service", "showDialog()2");
        new Thread(new h()).start();
    }

    public void g() {
        this.d = new j();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addDataScheme("file");
        registerReceiver(this.d, intentFilter);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.b;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        if (n.f13a) {
            Log.i("CMUpdate2Service", "onCreate()");
        }
        if (t) {
            return;
        }
        this.f57a = getBaseContext();
        this.k = new a.a.b.h(getApplicationContext());
        int iA = this.k.a();
        p = this.k.f8a.getBoolean("force_mode", true);
        Log.i("CMUpdate2Service", "start AppMode=" + iA);
        String strD = this.k.d();
        if (strD != null && strD.endsWith("update.zip")) {
            n.h = this.k.d();
        }
        if (r == null) {
            Log.i("CMUpdate2Service", "new CheckingTask()!");
            r = new a.a.b.a(this, this.e);
        }
        if (s == null) {
            Log.i("CMUpdate2Service", "new DownloadTask()!");
            s = new a.a.b.c(this, this.e);
        }
        d();
        g();
        this.l = new a.a.b.i();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
        intentFilter.addDataScheme("file");
        registerReceiver(this.l, intentFilter);
        t = true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.c);
            unregisterReceiver(this.d);
            unregisterReceiver(this.l);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Log.i("CMUpdate2Service", "onDestroy()");
    }

    @Override // a.a.b.g.a, android.os.RecoverySystem.ProgressListener
    public void onProgress(int i2) {
        this.e.post(new d(this, i2));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x016f, code lost:
    
        if (a() != false) goto L55;
     */
    @Override // android.app.Service
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int onStartCommand(android.content.Intent r3, int r4, int r5) {
        /*
            Method dump skipped, instruction units count: 444
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.sysapp.UpdateService.onStartCommand(android.content.Intent, int, int):int");
    }
}
