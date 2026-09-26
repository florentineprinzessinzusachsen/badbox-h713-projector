package com.android.umanalytics.http.download;

import android.util.Log;
import d.d0;
import d.v;
import e.c;
import e.e;
import e.h;
import e.l;
import e.s;

/* JADX INFO: loaded from: classes.dex */
public class DownloadResponseBody extends d0 {
    private static final String TAG = "DownloadResponseBody";
    private e mBufferedSource;
    private DownloadListener mDownloadListener;
    private d0 mResponseBody;

    public DownloadResponseBody(d0 d0Var, DownloadListener downloadListener) {
        this.mResponseBody = d0Var;
        this.mDownloadListener = downloadListener;
    }

    @Override // d.d0
    public long contentLength() {
        return this.mResponseBody.contentLength();
    }

    @Override // d.d0
    public v contentType() {
        return this.mResponseBody.contentType();
    }

    @Override // d.d0
    public e source() {
        if (this.mBufferedSource == null) {
            this.mBufferedSource = l.a(source(this.mResponseBody.source()));
        }
        return this.mBufferedSource;
    }

    private s source(s sVar) {
        return new h(sVar) { // from class: com.android.umanalytics.http.download.DownloadResponseBody.1
            long totalBytesRead = 0;

            @Override // e.h, e.s
            public long read(c cVar, long j) {
                long j2 = super.read(cVar, j);
                this.totalBytesRead += j2 != -1 ? j2 : 0L;
                int iContentLength = (int) ((this.totalBytesRead * 100) / DownloadResponseBody.this.mResponseBody.contentLength());
                Log.i(DownloadResponseBody.TAG, "DownloadRead: " + iContentLength);
                if (DownloadResponseBody.this.mDownloadListener != null && j2 != -1) {
                    DownloadResponseBody.this.mDownloadListener.onProgress(iContentLength);
                }
                return j2;
            }
        };
    }
}
