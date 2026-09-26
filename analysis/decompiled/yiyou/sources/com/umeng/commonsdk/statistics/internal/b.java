package com.umeng.commonsdk.statistics.internal;

/* JADX INFO: compiled from: IRequestStat.java */
/* JADX INFO: loaded from: classes.dex */
public interface b {
    void onRequestEnd();

    void onRequestFailed();

    void onRequestStart();

    void onRequestSucceed(boolean z);
}
