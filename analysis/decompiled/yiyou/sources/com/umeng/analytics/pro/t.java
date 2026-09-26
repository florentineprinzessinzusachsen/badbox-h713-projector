package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: compiled from: SessionIdGenerateServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
class t implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private long f3726a = AnalyticsConfig.kContinueSessionMillis;

    t() {
    }

    @Override // com.umeng.analytics.pro.s
    public void a(long j) {
        this.f3726a = j;
    }

    @Override // com.umeng.analytics.pro.s
    public long a() {
        return this.f3726a;
    }

    @Override // com.umeng.analytics.pro.s
    public String a(Context context) {
        String deviceId = DeviceConfig.getDeviceId(context);
        String appkey = UMUtils.getAppkey(context);
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (appkey != null) {
            return UMUtils.MD5(jCurrentTimeMillis + appkey + deviceId);
        }
        throw new RuntimeException("Appkey is null or empty, Please check!");
    }

    @Override // com.umeng.analytics.pro.s
    public boolean a(long j, long j2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        return (j == 0 || jCurrentTimeMillis - j >= this.f3726a) && j2 > 0 && jCurrentTimeMillis - j2 > this.f3726a;
    }

    @Override // com.umeng.analytics.pro.s
    public void a(Context context, String str) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        try {
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(context).edit();
            editorEdit.putString(q.f3716c, str);
            editorEdit.putLong(q.f3715b, 0L);
            editorEdit.putLong(q.f3718e, jCurrentTimeMillis);
            editorEdit.putLong(q.f3719f, 0L);
            editorEdit.commit();
        } catch (Exception unused) {
        }
    }
}
