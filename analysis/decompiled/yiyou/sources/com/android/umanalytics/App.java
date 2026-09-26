package com.android.umanalytics;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import com.android.umanalytics.utils.ShellUtils;
import com.baidu.mobstat.StatService;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.PathUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.Utils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class App extends Application {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static App f3197d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3198a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f3199b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3200c = true;

    class a implements CrashUtils.OnCrashListener {
        a(App app) {
        }

        @Override // com.blankj.utilcode.util.CrashUtils.OnCrashListener
        public void onCrash(String str, Throwable th) {
            Log.e("wxx", str);
            AppUtils.exitApp();
        }
    }

    public static synchronized App b() {
        return f3197d;
    }

    private void c() {
        StatService.setAppKey("bfe72cbf66");
        StatService.setAppChannel(this, "ashd-analytics", true);
        StatService.setOn(this, 1);
        StatService.start(this);
    }

    private void d() {
        try {
            LogUtils.i("SDCard状态：" + SDCardUtils.isSDCardEnableByEnvironment());
            this.f3198a = PathUtils.getInternalAppFilesPath() + File.separator;
            String str = "/data/data/" + AppUtils.getAppPackageName() + File.separator;
            Runtime.getRuntime().exec("chmod 777 " + str + " \n");
            Runtime.getRuntime().exec("chmod 777 " + this.f3198a + " \n");
            LogUtils.i("app root path:" + this.f3198a + ShellUtils.COMMAND_LINE_END);
        } catch (IOException e2) {
            LogUtils.e(e2.toString());
        }
    }

    private void e() {
        LogUtils.getConfig().setLogSwitch(a());
        LogUtils.getConfig().setLog2FileSwitch(a());
        LogUtils.getConfig().setDir(this.f3198a);
        LogUtils.getConfig().setGlobalTag("wxx");
        CrashUtils.init(this.f3198a, new a(this));
    }

    private void f() {
        Log.d("qzr", "初始化友盟");
        String str = "5deddf0b0cafb232a3001431";
        if (!TextUtils.equals(getPackageName(), "com.android.umanalytics")) {
            if (TextUtils.equals(getPackageName(), "com.android.umanalytics.ashd")) {
                str = "5e7ef0b1570df3a5d6000202";
            } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.javoda")) {
                str = "5e7ef15b0cafb2c7b2000048";
            } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.wanjiang")) {
                str = "5e7ef114570df3e3a300005b";
            } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.tianxing")) {
                str = "5e86a26f895cca86bb00024a";
            } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.asos")) {
                str = "5f805ea480455950e4a2dafa";
            } else if (TextUtils.equals(getPackageName(), "com.android.umanalytics.yiyou")) {
                str = "61c17582e014255fcbc1a9af";
            }
        }
        UMConfigure.setLogEnabled(a());
        UMConfigure.init(this, str, "ashd-analytics", 2, null);
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        UMConfigure.setProcessEvent(true);
    }

    public boolean a() {
        return false;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f3197d = this;
        Utils.init((Application) this);
        d();
        e();
        f();
        c();
    }
}
