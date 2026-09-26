package com.umeng.commonsdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.statistics.common.ULog;
import java.lang.reflect.InvocationTargetException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UMInternalDataProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public class c implements UMLogDataProtocol {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3808b = "info";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3809c = "stat";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f3810a;

    public c(Context context) {
        if (context != null) {
            this.f3810a = context.getApplicationContext();
        }
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return null;
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) throws IllegalAccessException, ClassNotFoundException, InvocationTargetException {
        ULog.i("walle", "[internal] workEvent");
        switch (i) {
            case a.f3803e /* 32769 */:
                ULog.i("walle", "[internal] workEvent send envelope");
                Class<?> cls = Class.forName("com.umeng.commonsdk.internal.UMInternalManagerAgent");
                if (cls != null) {
                    cls.getMethod("sendInternalEnvelopeByStateful2", Context.class).invoke(cls, this.f3810a);
                }
                break;
            case a.g /* 32771 */:
                ULog.i("walle", "[internal] workEvent cache battery, event is " + obj.toString());
                Class<?> cls2 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                if (cls2 != null) {
                    cls2.getMethod("saveBattery", Context.class, String.class).invoke(cls2, this.f3810a, (String) obj);
                }
                break;
            case a.h /* 32772 */:
                ULog.i("walle", "[internal] workEvent cache station, event is " + obj.toString());
                Class<?> cls3 = Class.forName("com.umeng.commonsdk.internal.utils.UMInternalUtilsAgent");
                if (cls3 != null) {
                    cls3.getMethod("saveBaseStationStrength", Context.class, String.class).invoke(cls3, this.f3810a, (String) obj);
                }
                break;
            case a.i /* 32773 */:
                Class<?> cls4 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                if (cls4 != null) {
                    cls4.getMethod("saveBluetoothInfo", Context.class, Object.class).invoke(cls4, this.f3810a, obj);
                }
                break;
            case a.j /* 32774 */:
                Class<?> cls5 = Class.forName("com.umeng.commonsdk.internal.utils.ApplicationLayerUtilAgent");
                if (cls5 != null) {
                    cls5.getMethod("wifiChange", Context.class).invoke(cls5, this.f3810a);
                }
                break;
            case a.k /* 32775 */:
                Class<?> cls6 = Class.forName("com.umeng.commonsdk.internal.utils.InfoPreferenceAgent");
                if (cls6 != null) {
                    cls6.getMethod("saveUA", Context.class, String.class).invoke(cls6, this.f3810a, (String) obj);
                }
                break;
            case a.l /* 32776 */:
                SharedPreferences sharedPreferences = this.f3810a.getApplicationContext().getSharedPreferences("info", 0);
                if (sharedPreferences != null) {
                    sharedPreferences.edit().putString(f3809c, (String) obj).commit();
                }
                break;
            case a.m /* 32777 */:
                try {
                    ULog.i("walle", "[internal] workEvent send envelope");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(e.aw, a.f3802d);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put(e.ak, new JSONObject());
                    JSONObject jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.f3810a, jSONObject, jSONObject2);
                    if (jSONObjectBuildEnvelopeWithExtHeader != null && !jSONObjectBuildEnvelopeWithExtHeader.has("exception")) {
                        ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
                        break;
                    }
                } catch (Throwable unused) {
                    return;
                }
                break;
        }
    }
}
