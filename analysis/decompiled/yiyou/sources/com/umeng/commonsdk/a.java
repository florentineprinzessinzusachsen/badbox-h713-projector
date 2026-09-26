package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.statistics.common.ULog;

/* JADX INFO: compiled from: UMConfigureInternation.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3782a = false;

    public static synchronized void a(final Context context) {
        if (context != null) {
            try {
                if (!f3782a) {
                    new Thread(new Runnable() { // from class: com.umeng.commonsdk.a.1
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                String packageName = context.getPackageName();
                                if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName) || !UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                                    return;
                                }
                                UMWorkDispatch.sendEvent(context, com.umeng.commonsdk.internal.a.m, b.a(context).a(), null);
                            } catch (Throwable unused) {
                            }
                        }
                    }).start();
                    f3782a = true;
                }
            } catch (Throwable th) {
                ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
            }
        }
    }
}
