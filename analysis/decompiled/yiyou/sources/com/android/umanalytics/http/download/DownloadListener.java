package com.android.umanalytics.http.download;

/* JADX INFO: loaded from: classes.dex */
public interface DownloadListener {
    void onFail(String str);

    void onFinish();

    void onProgress(int i);

    void onStart();
}
