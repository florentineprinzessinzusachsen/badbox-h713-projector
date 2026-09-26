package com.android.umanalytics.http.download;

import d.c0;
import d.u;

/* JADX INFO: loaded from: classes.dex */
public class DownloadInterceptor implements u {
    private DownloadListener mDownloadListener;

    public DownloadInterceptor(DownloadListener downloadListener) {
        this.mDownloadListener = downloadListener;
    }

    @Override // d.u
    public c0 intercept(u.a aVar) {
        c0 c0VarA = aVar.a(aVar.request());
        c0.a aVarS = c0VarA.s();
        aVarS.a(new DownloadResponseBody(c0VarA.a(), this.mDownloadListener));
        return aVarS.a();
    }
}
