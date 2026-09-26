package com.umeng.analytics.process;

import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class DBFileTraversalUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ExecutorService f3743a = Executors.newSingleThreadExecutor();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static FileLockUtil f3744b = new FileLockUtil();

    public interface a {
        void a();
    }

    public static void traverseDBFiles(String str, final FileLockCallback fileLockCallback, final a aVar) {
        final File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            f3743a.execute(new Runnable() { // from class: com.umeng.analytics.process.DBFileTraversalUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    for (File file2 : file.listFiles()) {
                        if (file2.getName().endsWith(com.umeng.analytics.process.a.f3763d)) {
                            DBFileTraversalUtil.f3744b.doFileOperateion(file2, fileLockCallback);
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> file: " + file2.getName());
                        }
                    }
                    a aVar2 = aVar;
                    if (aVar2 != null) {
                        aVar2.a();
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> end *** ");
                }
            });
        }
    }
}
