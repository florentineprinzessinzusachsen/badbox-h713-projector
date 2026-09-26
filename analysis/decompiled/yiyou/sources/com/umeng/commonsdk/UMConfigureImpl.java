package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.d;
import com.umeng.commonsdk.internal.utils.b;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.c;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UMConfigureImpl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f3778a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f3779b = false;

    private static synchronized void a(final Context context) {
        if (context != null) {
            try {
                if (!f3779b) {
                    String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                    String packageName = context.getPackageName();
                    if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                        new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.1
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    JSONArray jSONArrayB = c.b(context);
                                    if (jSONArrayB == null || jSONArrayB.length() <= 0) {
                                        return;
                                    }
                                    f.a(context, context.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.f4025e + "/" + Base64.encodeToString(com.umeng.commonsdk.internal.a.n.getBytes(), 0), 10);
                                    JSONObject jSONObject = new JSONObject();
                                    jSONObject.put("lbs", jSONArrayB);
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("tp", jSONObject);
                                    UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                                    uMSLEnvelopeBuild.buildSLEnvelope(context, uMSLEnvelopeBuild.buildSLBaseHeader(context), jSONObject2, com.umeng.commonsdk.internal.a.n);
                                } catch (Exception e2) {
                                    UMCrashManager.reportCrash(context, e2);
                                }
                            }
                        }).start();
                    }
                    f3779b = true;
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    private static synchronized void b(final Context context) {
        if (context != null) {
            try {
                if (!f3778a) {
                    new Thread(new Runnable() { // from class: com.umeng.commonsdk.UMConfigureImpl.2
                        @Override // java.lang.Runnable
                        public void run() {
                            try {
                                String currentProcessName = UMFrUtils.getCurrentProcessName(context);
                                String packageName = context.getPackageName();
                                if (TextUtils.isEmpty(currentProcessName) || TextUtils.isEmpty(packageName) || !currentProcessName.equals(packageName)) {
                                    return;
                                }
                                try {
                                    com.umeng.commonsdk.proguard.a.a(context);
                                } catch (Throwable th) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th);
                                }
                                try {
                                    c.a(context);
                                } catch (Throwable th2) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th2);
                                }
                                try {
                                    if (!com.umeng.commonsdk.internal.utils.c.a(context).a()) {
                                        com.umeng.commonsdk.internal.utils.c.a(context).b();
                                    }
                                } catch (Throwable th3) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th3);
                                }
                                try {
                                    l.b(context);
                                } catch (Throwable th4) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th4);
                                }
                                try {
                                    com.umeng.commonsdk.internal.utils.a.n(context);
                                } catch (Throwable th5) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th5);
                                }
                                try {
                                    com.umeng.commonsdk.internal.utils.a.d(context);
                                } catch (Throwable th6) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th6);
                                }
                                try {
                                    j.b(context);
                                } catch (Throwable th7) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th7);
                                }
                                try {
                                    d.b(context);
                                } catch (Throwable th8) {
                                    ULog.e(UMModuleRegister.INNER, "e is " + th8);
                                }
                                try {
                                    d.c(context);
                                } catch (Throwable unused) {
                                }
                            } catch (Throwable th9) {
                                UMCrashManager.reportCrash(context, th9);
                            }
                        }
                    }).start();
                    try {
                        if (!b.a(context).a()) {
                            b.a(context).b();
                        }
                    } catch (Throwable unused) {
                        ULog.e(UMModuleRegister.INNER, "get station is null ");
                    }
                    f3778a = true;
                }
            } catch (Throwable th) {
                ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static void init(Context context) {
        if (context == null) {
            return;
        }
        Context applicationContext = context.getApplicationContext();
        b(applicationContext);
        a(applicationContext);
    }
}
