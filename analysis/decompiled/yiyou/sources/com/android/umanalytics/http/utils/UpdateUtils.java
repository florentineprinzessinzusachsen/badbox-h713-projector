package com.android.umanalytics.http.utils;

import android.text.TextUtils;
import b.b.a.f;
import c.a.f0.b;
import c.a.s;
import com.android.umanalytics.App;
import com.android.umanalytics.http.HttpUtils;
import com.android.umanalytics.http.api.MainApi;
import com.android.umanalytics.http.api.UpdateApi;
import com.android.umanalytics.http.bean.UpdateInfoBean;
import com.android.umanalytics.utils.FUtils;
import com.android.umanalytics.utils.PkgUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SDCardUtils;
import com.blankj.utilcode.util.SPUtils;
import d.d0;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class UpdateUtils {
    private static UpdateUtils instance;
    private f gson = new f();
    private UpdateApi api = (UpdateApi) HttpUtils.getInstance().getRetrofit4NoFilter().create(UpdateApi.class);
    private MainApi mainApi = (MainApi) HttpUtils.getInstance().getRetrofit4NoFilter().create(MainApi.class);
    private String apkDownloadPath = "";
    private String downloadFileMD5 = "";

    private UpdateUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadApk(final UpdateInfoBean updateInfoBean) {
        final String str = AppUtils.getAppName() + "_" + updateInfoBean.getVersionCode() + ".apk";
        this.apkDownloadPath = App.b().f3198a + str;
        this.downloadFileMD5 = FileUtils.getFileMD5ToString(this.apkDownloadPath).toLowerCase();
        if (!FileUtils.isFileExists(this.apkDownloadPath)) {
            this.mainApi.downloadFile(updateInfoBean.getDownLoadURL()).subscribeOn(b.b()).observeOn(b.b()).subscribe(new s<d0>() { // from class: com.android.umanalytics.http.utils.UpdateUtils.2
                @Override // c.a.s
                public void onComplete() {
                }

                @Override // c.a.s
                public void onError(Throwable th) {
                    LogUtils.e(th.toString());
                }

                @Override // c.a.s
                public void onSubscribe(c.a.y.b bVar) {
                }

                @Override // c.a.s
                public void onNext(d0 d0Var) throws Throwable {
                    try {
                        FUtils.saveFile(d0Var, App.b().f3198a, str);
                        LogUtils.i("下载成功：path=" + UpdateUtils.this.apkDownloadPath);
                        UpdateUtils.this.downloadFileMD5 = FileUtils.getFileMD5ToString(UpdateUtils.this.apkDownloadPath).toLowerCase();
                        LogUtils.i("网络MD5:" + updateInfoBean.getMD5().toLowerCase() + ", 已下载文件MD5:" + UpdateUtils.this.downloadFileMD5);
                        if (!TextUtils.equals(updateInfoBean.getMD5().toLowerCase(), UpdateUtils.this.downloadFileMD5)) {
                            FileUtils.deleteFile(UpdateUtils.this.apkDownloadPath);
                            LogUtils.e("下载文件出错，请重新下载");
                            return;
                        }
                        if (!SDCardUtils.isSDCardEnableByEnvironment()) {
                            try {
                                Runtime.getRuntime().exec("chmod 777 " + UpdateUtils.this.apkDownloadPath + " \n");
                                StringBuilder sb = new StringBuilder();
                                sb.append("exec chmod success: ");
                                sb.append(UpdateUtils.this.apkDownloadPath);
                                LogUtils.i(sb.toString());
                            } catch (IOException e2) {
                                e2.printStackTrace();
                                LogUtils.e("exec chmod fail");
                            }
                        }
                        PkgUtils.restartApp();
                        PkgUtils.install(UpdateUtils.this.apkDownloadPath, true);
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
            });
            return;
        }
        LogUtils.i("apk已下载：" + this.apkDownloadPath);
        if (!TextUtils.equals(updateInfoBean.getMD5().toLowerCase(), this.downloadFileMD5)) {
            FileUtils.deleteFile(this.apkDownloadPath);
            LogUtils.e("下载文件出错，请重新下载");
            return;
        }
        if (!SDCardUtils.isSDCardEnableByEnvironment()) {
            try {
                Runtime.getRuntime().exec("chmod 777 " + this.apkDownloadPath + " \n");
                StringBuilder sb = new StringBuilder();
                sb.append("exec chmod success: ");
                sb.append(this.apkDownloadPath);
                LogUtils.i(sb.toString());
            } catch (IOException e2) {
                e2.printStackTrace();
                LogUtils.e("exec chmod fail");
            }
        }
        PkgUtils.restartApp();
        PkgUtils.install(this.apkDownloadPath, true);
    }

    public static UpdateUtils getInstance() {
        if (instance == null) {
            synchronized (UpdateUtils.class) {
                if (instance == null) {
                    instance = new UpdateUtils();
                }
            }
        }
        return instance;
    }

    public void checkUpdate(final boolean z) {
        this.api.getUpdateInfo("http://ty.ishanghd.com/work/app/wj/factory/update.json").subscribeOn(b.b()).observeOn(b.b()).subscribe(new s<UpdateInfoBean>() { // from class: com.android.umanalytics.http.utils.UpdateUtils.1
            @Override // c.a.s
            public void onComplete() {
            }

            @Override // c.a.s
            public void onError(Throwable th) {
            }

            @Override // c.a.s
            public void onSubscribe(c.a.y.b bVar) {
            }

            @Override // c.a.s
            public void onNext(UpdateInfoBean updateInfoBean) {
                LogUtils.i("getUpdateInfo---获取升级信息成功：" + updateInfoBean.toString());
                SPUtils.getInstance().put("updateInfo", UpdateUtils.this.gson.a(updateInfoBean));
                if (AppUtils.getAppVersionCode() < updateInfoBean.getVersionCode()) {
                    UpdateUtils.this.downloadApk(updateInfoBean);
                    LogUtils.i("发现新版本");
                } else {
                    if (z) {
                        return;
                    }
                    LogUtils.i("已是最新版本");
                }
            }
        });
    }
}
