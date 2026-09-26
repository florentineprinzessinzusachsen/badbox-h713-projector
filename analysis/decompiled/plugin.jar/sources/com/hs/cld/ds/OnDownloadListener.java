package com.hs.cld.ds;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public interface OnDownloadListener {
    void onActivated(ApkInfo apkInfo, boolean z, Exception exc);

    void onDownloaded(ApkInfo apkInfo, boolean z, Exception exc);

    void onInstalled(ApkInfo apkInfo, boolean z, Exception exc);

    void onStartActivate(ApkInfo apkInfo);

    void onStartDownload(ApkInfo apkInfo);

    void onStartInstall(ApkInfo apkInfo);
}
