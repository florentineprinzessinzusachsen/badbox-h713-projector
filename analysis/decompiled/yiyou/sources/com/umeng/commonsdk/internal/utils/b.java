package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.ULog;

/* JADX INFO: compiled from: BaseStationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3824b = "BaseStationUtils";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f3825c = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static Context f3826d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    PhoneStateListener f3827a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TelephonyManager f3828e;

    /* JADX INFO: compiled from: BaseStationUtils.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final b f3830a = new b(b.f3826d);

        private a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) f3826d.getSystemService("phone");
            if (telephonyManager == null) {
                return null;
            }
            String simOperator = telephonyManager.getSimOperator();
            if (TextUtils.isEmpty(simOperator)) {
                return null;
            }
            if (!simOperator.equals("46000") && !simOperator.equals("46002")) {
                if (simOperator.equals("46001")) {
                    return "中国联通";
                }
                if (simOperator.equals("46003")) {
                    return "中国电信";
                }
                return null;
            }
            return "中国移动";
        } catch (Throwable unused) {
            return null;
        }
    }

    public synchronized void c() {
        ULog.e(f3824b, "base station unRegisterListener");
        try {
            if (this.f3828e != null) {
                this.f3828e.listen(this.f3827a, 0);
            }
            f3825c = false;
        } catch (Throwable unused) {
        }
    }

    private b(Context context) {
        this.f3827a = new PhoneStateListener() { // from class: com.umeng.commonsdk.internal.utils.b.1
            @Override // android.telephony.PhoneStateListener
            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                String str;
                super.onSignalStrengthsChanged(signalStrength);
                ULog.e(b.f3824b, "base station onSignalStrengthsChanged");
                try {
                    b.this.f3828e = (TelephonyManager) b.f3826d.getSystemService("phone");
                    String[] strArrSplit = signalStrength.toString().split(" ");
                    String str2 = null;
                    if (b.this.f3828e != null && b.this.f3828e.getNetworkType() == 13) {
                        str = "" + Integer.parseInt(strArrSplit[9]);
                    } else if (b.this.f3828e == null || !(b.this.f3828e.getNetworkType() == 8 || b.this.f3828e.getNetworkType() == 10 || b.this.f3828e.getNetworkType() == 9 || b.this.f3828e.getNetworkType() == 3)) {
                        str = ((signalStrength.getGsmSignalStrength() * 2) - 113) + "";
                    } else {
                        String strE = b.this.e();
                        if (!TextUtils.isEmpty(strE) && strE.equals("中国移动")) {
                            str2 = "0";
                        } else if (!TextUtils.isEmpty(strE) && strE.equals("中国联通")) {
                            str2 = signalStrength.getCdmaDbm() + "";
                        } else if (!TextUtils.isEmpty(strE) && strE.equals("中国电信")) {
                            str2 = signalStrength.getEvdoDbm() + "";
                        }
                        str = str2;
                    }
                    ULog.e(b.f3824b, "stationStrength is " + str);
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            UMWorkDispatch.sendEvent(b.f3826d, com.umeng.commonsdk.internal.a.h, com.umeng.commonsdk.internal.b.a(b.f3826d).a(), str);
                        } catch (Throwable unused) {
                        }
                    }
                    b.this.c();
                } catch (Exception unused2) {
                }
            }
        };
        if (context != null) {
            try {
                this.f3828e = (TelephonyManager) context.getSystemService("phone");
            } catch (Throwable unused) {
            }
        }
    }

    public synchronized void b() {
        ULog.e(f3824b, "base station registerListener");
        try {
            if (this.f3828e != null) {
                this.f3828e.listen(this.f3827a, 256);
            }
            f3825c = true;
        } catch (Throwable unused) {
        }
    }

    public static b a(Context context) {
        if (f3826d == null && context != null) {
            f3826d = context.getApplicationContext();
        }
        return a.f3830a;
    }

    public synchronized boolean a() {
        return f3825c;
    }
}
