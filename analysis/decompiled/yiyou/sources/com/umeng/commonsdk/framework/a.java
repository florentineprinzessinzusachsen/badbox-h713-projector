package com.umeng.commonsdk.framework;

import android.content.Context;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UMEnvelopeBuildImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    public static long a(Context context) {
        if (context == null) {
            return 0L;
        }
        return UMFrUtils.getLastSuccessfulBuildTime(context.getApplicationContext());
    }

    public static long b(Context context) {
        if (context == null) {
            return 0L;
        }
        return UMFrUtils.getLastInstantBuildTime(context.getApplicationContext());
    }

    public static long c(Context context) {
        if (context == null) {
            return 0L;
        }
        return com.umeng.commonsdk.statistics.b.a(context.getApplicationContext());
    }

    public static boolean a(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        boolean z = false;
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            boolean zIsOnline = UMFrUtils.isOnline(applicationContext);
            int iEnvelopeFileNumber = UMFrUtils.envelopeFileNumber(applicationContext);
            if (zIsOnline) {
                if (b.a()) {
                    c.a(b.b());
                } else if (!UMFrUtils.hasEnvelopeFile(applicationContext, uMBusinessType)) {
                    z = true;
                }
            }
            if (zIsOnline && iEnvelopeFileNumber > 0) {
                b.d();
            }
        }
        return z;
    }

    public static JSONObject a(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject jSONObject3;
        JSONException e2;
        ULog.d("--->>> buildEnvelopeFile Enter.");
        if (!UMGlobalContext.getInstance().isMainProcess(context)) {
            try {
                jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("exception", 101);
                } catch (JSONException e3) {
                    e2 = e3;
                    e2.printStackTrace();
                }
            } catch (JSONException e4) {
                jSONObject3 = null;
                e2 = e4;
            }
            return jSONObject3;
        }
        return new com.umeng.commonsdk.statistics.b().a(context.getApplicationContext(), jSONObject, jSONObject2);
    }

    public static String a(Context context, String str, String str2) {
        return context == null ? str2 : ImprintHandler.getImprintService(context.getApplicationContext()).c().a(str, str2);
    }
}
