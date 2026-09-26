package com.hs.cld.ds;

import android.content.Context;
import android.content.Intent;
import com.hs.p.common.utils.DigestUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DLS {
    private static final String TAG = "DLS";
    private List<InnerDownloadTask> mDownloadTasks;

    private static class Holder {
        private static final DLS INSTANCE = new DLS();

        private Holder() {
        }
    }

    private class InnerDownloadTask implements DownloadManager.OnTransferListener {
        private String mApkRealUrl;
        private final Context mContext;
        private final String mLocalApkUrl;
        private int mProgressMilestone = 0;
        private final TrackAppInfo mTrackAppInfo;

        public InnerDownloadTask(Context context, TrackAppInfo trackAppInfo, String str) {
            this.mContext = context;
            this.mTrackAppInfo = trackAppInfo;
            this.mLocalApkUrl = str;
            this.mApkRealUrl = trackAppInfo.mApkInfo.mApkUrl;
        }

        @Override // com.hs.cld.ds.DownloadManager.OnTransferListener
        public void onBegin(String str) {
            LOG.i(DLS.TAG, "[ID:" + str + "] on begin ...");
        }

        @Override // com.hs.cld.ds.DownloadManager.OnTransferListener
        public void onCancel(String str) {
            LOG.i(DLS.TAG, "[ID:" + str + "] on cancel ...");
            DLS.onDownload(this.mTrackAppInfo, false, new Exception("cancel"));
            DLS.this.mDownloadTasks.remove(this);
        }

        @Override // com.hs.cld.ds.DownloadManager.OnTransferListener
        public void onException(String str, Exception exc) {
            LOG.w(DLS.TAG, "[ID:" + str + "] on exception: " + exc);
            DLS.onDownload(this.mTrackAppInfo, false, exc);
            DLS.this.mDownloadTasks.remove(this);
        }

        @Override // com.hs.cld.ds.DownloadManager.OnTransferListener
        public void onFinish(String str) {
            LOG.i(DLS.TAG, "[ID:" + str + "] on finish ...");
            DLS.onDownload(this.mTrackAppInfo, true, null);
            DLS.this.mDownloadTasks.remove(this);
            Context context = this.mContext;
            TrackAppInfo trackAppInfo = this.mTrackAppInfo;
            DLS.submitTrackers(context, trackAppInfo, trackAppInfo.mApkInfo.mDownTrackers, "down");
            if (TextUtils.empty(this.mTrackAppInfo.mApkInfo.mApkMd5) || DLS.verifyApkFile(new File(this.mLocalApkUrl), this.mTrackAppInfo.mApkInfo.mApkMd5)) {
                DLS.this.installAndActivate(this.mContext, this.mTrackAppInfo, this.mLocalApkUrl);
                return;
            }
            LOG.i(DLS.TAG, "[" + this.mTrackAppInfo.mApkInfo.mApkUrl + "] apk(" + this.mLocalApkUrl + ") MD5 mismatch(" + this.mTrackAppInfo.mApkInfo.mApkMd5 + "), delete ...");
            DLS.safeDelete(this.mLocalApkUrl);
        }

        @Override // com.hs.cld.ds.DownloadManager.OnTransferListener
        public void onNetworkError(String str) {
            LOG.w(DLS.TAG, "[ID:" + str + "] on network error ...");
            DLS.onDownload(this.mTrackAppInfo, false, new Exception("network error"));
            DLS.this.mDownloadTasks.remove(this);
        }

        @Override // com.hs.cld.ds.DownloadManager.OnTransferListener
        public void onRedirectUrl(String str, String str2) {
            LOG.i(DLS.TAG, "[ID:" + str + "] on redirect url: " + str2);
            if (TextUtils.equals(this.mApkRealUrl, str2)) {
                return;
            }
            this.mApkRealUrl = str2;
        }

        @Override // com.hs.cld.ds.DownloadManager.OnTransferListener
        public void onTransfer(String str, int i, long j, long j2) {
            if (i - this.mProgressMilestone >= 10) {
                LOG.i(DLS.TAG, "[ID:" + str + "] on transfer: " + i + "% " + j + "/" + j2);
                this.mProgressMilestone = i;
            }
        }

        public void start() {
            LOG.i(DLS.TAG, "start: pkg=" + this.mTrackAppInfo.mApkInfo.mPackageName + ", url=" + this.mTrackAppInfo.mApkInfo.mApkUrl + ", local=" + this.mLocalApkUrl);
            DownloadManager.get().start(this.mContext, this.mTrackAppInfo.mApkInfo.mApkUrl, this.mLocalApkUrl, this);
            Context context = this.mContext;
            TrackAppInfo trackAppInfo = this.mTrackAppInfo;
            DLS.submitTrackers(context, trackAppInfo, trackAppInfo.mApkInfo.mStartDownTrackers, "startdown");
        }
    }

    private static class TrackAppInfo {
        private final ApkInfo mApkInfo;
        private final OnDownloadListener mOnDownloadListener;
        private final List<String[]> mTrackers = new ArrayList();

        public TrackAppInfo(ApkInfo apkInfo, OnDownloadListener onDownloadListener) {
            this.mApkInfo = apkInfo;
            this.mOnDownloadListener = onDownloadListener;
            safeAddTrackers(apkInfo.mStartDownTrackers);
            safeAddTrackers(apkInfo.mDownTrackers);
            safeAddTrackers(apkInfo.mStartInstallTrackers);
            safeAddTrackers(apkInfo.mInstallTrackers);
            safeAddTrackers(apkInfo.mActiveTrackers);
        }

        private void safeAddTrackers(String[] strArr) {
            if (strArr != null) {
                this.mTrackers.add(strArr);
            }
        }
    }

    private DLS() {
        this.mDownloadTasks = Collections.synchronizedList(new ArrayList());
    }

    private void downloadApk(Context context, TrackAppInfo trackAppInfo) {
        StringBuilder sb;
        String str;
        validateParameter(trackAppInfo);
        if (isApkDownloading(trackAppInfo)) {
            LOG.i(TAG, "[" + trackAppInfo.mApkInfo.mApkUrl + "] apk downloading, ignore ...");
            return;
        }
        String apkLocalUrl = getApkLocalUrl(context, trackAppInfo);
        LOG.i(TAG, "downloadApk localApkUrl := " + apkLocalUrl);
        if (new File(apkLocalUrl).exists()) {
            if (TextUtils.empty(trackAppInfo.mApkInfo.mApkMd5)) {
                sb = new StringBuilder();
                sb.append("[");
                sb.append(trackAppInfo.mApkInfo.mApkUrl);
                sb.append("] apk(");
                sb.append(apkLocalUrl);
                str = ") exist, delete ...";
            } else {
                if (verifyApkFile(new File(apkLocalUrl), trackAppInfo.mApkInfo.mApkMd5)) {
                    LOG.i(TAG, "[" + trackAppInfo.mApkInfo.mApkUrl + "] apk(" + apkLocalUrl + ") exist, install ...");
                    installAndActivate(context, trackAppInfo, apkLocalUrl);
                    return;
                }
                sb = new StringBuilder();
                sb.append("[");
                sb.append(trackAppInfo.mApkInfo.mApkUrl);
                sb.append("] apk(");
                sb.append(apkLocalUrl);
                str = ") exist, but mismatch, delete ...";
            }
            sb.append(str);
            LOG.i(TAG, sb.toString());
            safeDelete(apkLocalUrl);
        }
        onStartDownload(trackAppInfo);
        InnerDownloadTask innerDownloadTask = new InnerDownloadTask(context, trackAppInfo, apkLocalUrl);
        innerDownloadTask.start();
        this.mDownloadTasks.add(innerDownloadTask);
    }

    public static DLS get() {
        return Holder.INSTANCE;
    }

    private static String getApkDisplayName(String str) {
        if (TextUtils.empty(str)) {
            return str;
        }
        int iLastIndexOf = str.lastIndexOf("/");
        int iIndexOf = str.indexOf("?");
        if (iIndexOf > 0) {
            return iLastIndexOf < iIndexOf ? str.substring(iLastIndexOf + 1, iIndexOf) : str;
        }
        return str.substring(iLastIndexOf + 1);
    }

    private static String getApkFilename(String str, String str2, String str3) {
        if (TextUtils.empty(str)) {
            str = "0";
        }
        return str + "_" + DigestUtils.md5AsString(str3) + ".apk";
    }

    private static String getApkLocalUrl(Context context, TrackAppInfo trackAppInfo) {
        return getLocalApkUrl(context, trackAppInfo, getApkFilename(trackAppInfo.mApkInfo.mPackageName, trackAppInfo.mApkInfo.mApkMd5, trackAppInfo.mApkInfo.mApkUrl));
    }

    private String getIntentString(Intent intent, String str, String str2) {
        return (intent == null || !intent.hasExtra(str)) ? str2 : intent.getStringExtra(str);
    }

    private static String getLocalApkUrl(Context context, TrackAppInfo trackAppInfo, String str) {
        String absolutePath;
        if (TextUtils.empty(trackAppInfo.mApkInfo.mLocalPath)) {
            File externalFilesDir = context.getExternalFilesDir("apk");
            if (externalFilesDir == null || !externalFilesDir.exists()) {
                externalFilesDir = new File(context.getFilesDir(), "apk");
            }
            absolutePath = externalFilesDir.getAbsolutePath();
        } else {
            absolutePath = trackAppInfo.mApkInfo.mLocalPath;
        }
        return absolutePath + File.separator + str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void installAndActivate(Context context, TrackAppInfo trackAppInfo, String str) {
        try {
            submitTrackers(context, trackAppInfo, trackAppInfo.mApkInfo.mStartInstallTrackers, "startinstall");
            onStartInstall(trackAppInfo);
            ApkUtils.install(context, str, true);
            submitTrackers(context, trackAppInfo, trackAppInfo.mApkInfo.mInstallTrackers, "install");
            safeDelete(str);
            onInstall(trackAppInfo, true, null);
        } catch (Exception e) {
            onInstall(trackAppInfo, false, e);
            LOG.e(TAG, "[" + str + "] install failed: " + e, e);
        }
    }

    private boolean isApkDownloading(TrackAppInfo trackAppInfo) {
        Iterator<InnerDownloadTask> it = this.mDownloadTasks.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().mTrackAppInfo.mApkInfo.mApkUrl, trackAppInfo.mApkInfo.mApkUrl)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onDownload(TrackAppInfo trackAppInfo, boolean z, Exception exc) {
        if (trackAppInfo != null) {
            try {
                if (trackAppInfo.mOnDownloadListener != null) {
                    trackAppInfo.mOnDownloadListener.onDownloaded(trackAppInfo.mApkInfo, z, exc);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static void onInstall(TrackAppInfo trackAppInfo, boolean z, Exception exc) {
        if (trackAppInfo != null) {
            try {
                if (trackAppInfo.mOnDownloadListener != null) {
                    trackAppInfo.mOnDownloadListener.onInstalled(trackAppInfo.mApkInfo, z, exc);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static void onStartDownload(TrackAppInfo trackAppInfo) {
        if (trackAppInfo != null) {
            try {
                if (trackAppInfo.mOnDownloadListener != null) {
                    trackAppInfo.mOnDownloadListener.onStartDownload(trackAppInfo.mApkInfo);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static void onStartInstall(TrackAppInfo trackAppInfo) {
        if (trackAppInfo != null) {
            try {
                if (trackAppInfo.mOnDownloadListener != null) {
                    trackAppInfo.mOnDownloadListener.onStartInstall(trackAppInfo.mApkInfo);
                }
            } catch (Throwable unused) {
            }
        }
    }

    private static void safeDelete(File file) {
        try {
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void submitTrackers(Context context, TrackAppInfo trackAppInfo, String[] strArr, String str) {
        synchronized (DLS.class) {
            if (trackAppInfo.mTrackers.contains(strArr)) {
                Tracker.post(context, strArr, str);
                trackAppInfo.mTrackers.remove(strArr);
            }
        }
    }

    private static void validateParameter(TrackAppInfo trackAppInfo) {
        if (TextUtils.empty(trackAppInfo.mApkInfo.mApkUrl)) {
            throw new IllegalArgumentException("empty apk URL");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean verifyApkFile(File file, String str) {
        return TextUtils.equalsIgnoreCase(DigestUtils.md5AsString(file), str);
    }

    public synchronized void download(Context context, ApkInfo apkInfo, OnDownloadListener onDownloadListener) {
        downloadApk(context, new TrackAppInfo(apkInfo, onDownloadListener));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void safeDelete(String str) {
        safeDelete(new File(str));
    }
}
