package com.umeng.commonsdk.utils;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public interface FileLockCallback {
    boolean onFileLock(File file, int i);

    boolean onFileLock(String str);

    boolean onFileLock(String str, Object obj);
}
