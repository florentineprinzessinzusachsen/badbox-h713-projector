package com.hs.cld.da;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import com.hs.cld.da.model.ApkBean;
import com.hs.cld.da.model.EventTypeEnum;
import com.hs.cld.da.model.InstallTypeEnum;
import com.hs.cld.da.model.VerbTypeEnum;
import com.hs.cld.da.t.Tracker;
import com.hs.cld.ds.ApkInfo;
import com.hs.cld.ds.DLS;
import com.hs.cld.ds.OnDownloadListener;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AppExe implements OnDownloadListener {
    private static final String TAG = "AE";
    private final ApkBean mApkBean;
    private final Context mContext;

    public AppExe(Context context, ApkBean apkBean) {
        this.mContext = context;
        this.mApkBean = apkBean;
    }

    private void handle() {
        int versionCode;
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        String str;
        String string;
        if (VerbTypeEnum.INSTALL.valueEquals(this.mApkBean.verb_type)) {
            if (!InstallTypeEnum.ALWAYS.valueEquals(this.mApkBean.install_type)) {
                if (InstallTypeEnum.CREATE.valueEquals(this.mApkBean.install_type)) {
                    if (isPackageInstalled(this.mContext, this.mApkBean.app_pkgname)) {
                        sb3 = new StringBuilder();
                        sb3.append("[");
                        sb3.append(this.mApkBean.task_id);
                        sb3.append("][type=");
                        sb3.append(this.mApkBean.install_type);
                        sb3.append("] package exist, ignore: pkg=");
                        sb3.append(this.mApkBean.app_pkgname);
                        string = sb3.toString();
                        LOG.i(TAG, string);
                        return;
                    }
                    sb2 = new StringBuilder();
                    sb2.append("[");
                    sb2.append(this.mApkBean.task_id);
                    sb2.append("][type=");
                    sb2.append(this.mApkBean.install_type);
                    sb2.append("] package not found, create: pkg=");
                    sb2.append(this.mApkBean.app_pkgname);
                    LOG.d(TAG, sb2.toString());
                    installApplication(this.mApkBean);
                }
                if (InstallTypeEnum.UPDATE.valueEquals(this.mApkBean.install_type)) {
                    if (!isPackageInstalled(this.mContext, this.mApkBean.app_pkgname)) {
                        sb3 = new StringBuilder();
                        sb3.append("[");
                        sb3.append(this.mApkBean.task_id);
                        sb3.append("][type=");
                        sb3.append(this.mApkBean.install_type);
                        sb3.append("] package not found: pkg=");
                        sb3.append(this.mApkBean.app_pkgname);
                        string = sb3.toString();
                        LOG.i(TAG, string);
                        return;
                    }
                    versionCode = SystemUtils.getVersionCode(this.mContext, this.mApkBean.app_pkgname);
                    if (this.mApkBean.vercode > versionCode) {
                        sb2 = new StringBuilder();
                        sb2.append("[");
                        sb2.append(this.mApkBean.task_id);
                        sb2.append("][type=");
                        sb2.append(this.mApkBean.install_type);
                        sb2.append("] package version larger, update: pkg=");
                        sb2.append(this.mApkBean.app_pkgname);
                        LOG.d(TAG, sb2.toString());
                        installApplication(this.mApkBean);
                    }
                    sb = new StringBuilder();
                    sb.append("[");
                    sb.append(this.mApkBean.task_id);
                    sb.append("][type=");
                    sb.append(this.mApkBean.install_type);
                    sb.append("] package version smaller, ignore: cloud(");
                    sb.append(this.mApkBean.vercode);
                    sb.append(") <= installed(");
                    sb.append(versionCode);
                    sb.append(")");
                    string = sb.toString();
                    LOG.i(TAG, string);
                    return;
                }
                if (InstallTypeEnum.OVERLAP.valueEquals(this.mApkBean.install_type)) {
                    if (!isPackageInstalled(this.mContext, this.mApkBean.app_pkgname)) {
                        sb3 = new StringBuilder();
                        sb3.append("[");
                        sb3.append(this.mApkBean.task_id);
                        sb3.append("][type=");
                        sb3.append(this.mApkBean.install_type);
                        sb3.append("] package not found: pkg=");
                        sb3.append(this.mApkBean.app_pkgname);
                        string = sb3.toString();
                        LOG.i(TAG, string);
                        return;
                    }
                    sb2 = new StringBuilder();
                    sb2.append("[");
                    sb2.append(this.mApkBean.task_id);
                    sb2.append("][type=");
                    sb2.append(this.mApkBean.install_type);
                    str = "] package exist, overlap: pkg=";
                } else {
                    if (!InstallTypeEnum.CREATE_OR_UPDATE.valueEquals(this.mApkBean.install_type)) {
                        return;
                    }
                    if (isPackageInstalled(this.mContext, this.mApkBean.app_pkgname)) {
                        versionCode = SystemUtils.getVersionCode(this.mContext, this.mApkBean.app_pkgname);
                        if (this.mApkBean.vercode <= versionCode) {
                            sb = new StringBuilder();
                            sb.append("[");
                            sb.append(this.mApkBean.task_id);
                            sb.append("][type=");
                            sb.append(this.mApkBean.install_type);
                            sb.append("] package version smaller, ignore: cloud(");
                            sb.append(this.mApkBean.vercode);
                            sb.append(") <= installed(");
                            sb.append(versionCode);
                            sb.append(")");
                            string = sb.toString();
                            LOG.i(TAG, string);
                            return;
                        }
                        sb2 = new StringBuilder();
                        sb2.append("[");
                        sb2.append(this.mApkBean.task_id);
                        sb2.append("][type=");
                        sb2.append(this.mApkBean.install_type);
                        sb2.append("] package version larger, update: pkg=");
                        sb2.append(this.mApkBean.app_pkgname);
                        LOG.d(TAG, sb2.toString());
                    } else {
                        LOG.i(TAG, "[" + this.mApkBean.task_id + "][type=" + this.mApkBean.install_type + "] package not found, create: pkg=" + this.mApkBean.app_pkgname);
                    }
                }
                installApplication(this.mApkBean);
            }
            sb2 = new StringBuilder();
            sb2.append("[");
            sb2.append(this.mApkBean.task_id);
            sb2.append("][type=");
            sb2.append(this.mApkBean.install_type);
            str = "] create: pkg=";
            sb2.append(str);
            sb2.append(this.mApkBean.app_pkgname);
            LOG.d(TAG, sb2.toString());
            installApplication(this.mApkBean);
        }
    }

    private void installApplication(ApkBean apkBean) {
        ApkInfo apkInfo = new ApkInfo();
        apkInfo.mApkMd5 = apkBean.file_md5;
        apkInfo.mApkUrl = apkBean.file_url;
        apkInfo.mPackageName = apkBean.app_pkgname;
        apkInfo.mInstallOnSystemIdle = 1 == apkBean.install_scene;
        DLS.get().download(this.mContext, apkInfo, this);
    }

    private boolean isPackageInstalled(Context context, String str) {
        List<PackageInfo> installedPackages;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (installedPackages = packageManager.getInstalledPackages(0)) != null) {
                Iterator<PackageInfo> it = installedPackages.iterator();
                while (it.hasNext()) {
                    if (TextUtils.equals(it.next().packageName, str)) {
                        return true;
                    }
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    private void submitTracker(EventTypeEnum eventTypeEnum, int i, String str) {
        new Tracker(this.mContext).setTaskId(this.mApkBean.task_id).setTrackerId(this.mApkBean.tracker_id).setEventType(eventTypeEnum).setEventTime(System.currentTimeMillis()).setEventCode(i).setEventMessage(str).submitAsync();
    }

    public void fire() {
        try {
            handle();
        } catch (Throwable th) {
            LOG.e(TAG, "[" + this.mApkBean.task_id + "] app exe failed: " + th);
        }
    }

    @Override // com.hs.cld.ds.OnDownloadListener
    public void onActivated(ApkInfo apkInfo, boolean z, Exception exc) {
        EventTypeEnum eventTypeEnum = EventTypeEnum.ACTIVATED;
        if (z) {
            submitTracker(eventTypeEnum, 0, "OK");
            return;
        }
        String str = "";
        if (exc != null) {
            str = "" + exc;
        }
        submitTracker(eventTypeEnum, 300, str);
    }

    @Override // com.hs.cld.ds.OnDownloadListener
    public void onDownloaded(ApkInfo apkInfo, boolean z, Exception exc) {
        EventTypeEnum eventTypeEnum = EventTypeEnum.DOWNLOADED;
        if (z) {
            submitTracker(eventTypeEnum, 0, "OK");
            return;
        }
        String str = "";
        if (exc != null) {
            str = "" + exc;
        }
        submitTracker(eventTypeEnum, 100, str);
    }

    @Override // com.hs.cld.ds.OnDownloadListener
    public void onInstalled(ApkInfo apkInfo, boolean z, Exception exc) {
        EventTypeEnum eventTypeEnum = EventTypeEnum.INSTALLED;
        if (z) {
            submitTracker(eventTypeEnum, 0, "OK");
            return;
        }
        String str = "";
        if (exc != null) {
            str = "" + exc;
        }
        submitTracker(eventTypeEnum, 200, str);
    }

    @Override // com.hs.cld.ds.OnDownloadListener
    public void onStartActivate(ApkInfo apkInfo) {
    }

    @Override // com.hs.cld.ds.OnDownloadListener
    public void onStartDownload(ApkInfo apkInfo) {
    }

    @Override // com.hs.cld.ds.OnDownloadListener
    public void onStartInstall(ApkInfo apkInfo) {
    }
}
