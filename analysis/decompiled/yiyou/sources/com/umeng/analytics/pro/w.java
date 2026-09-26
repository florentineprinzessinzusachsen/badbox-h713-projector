package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: compiled from: EncryptHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class w {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3733a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3734b = "umeng+";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3735c = "ek__id";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3736d = "ek_key";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static w f3737e;

    private w() {
    }

    public static w a() {
        if (f3737e == null) {
            synchronized (w.class) {
                if (f3737e == null) {
                    f3737e = new w();
                }
            }
        }
        return f3737e;
    }

    public String b(String str) {
        try {
            return TextUtils.isEmpty(f3733a) ? str : new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), f3733a.getBytes()));
        } catch (Exception unused) {
            return null;
        }
    }

    public void a(Context context) {
        try {
            if (TextUtils.isEmpty(f3733a)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(context, f3735c);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = DeviceConfig.getDBencryptID(context);
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(context, f3735c, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String strSubstring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < strSubstring.length(); i++) {
                        char cCharAt = strSubstring.charAt(i);
                        if (Character.isDigit(cCharAt)) {
                            if (Integer.parseInt(Character.toString(cCharAt)) == 0) {
                                sb.append(0);
                            } else {
                                sb.append(10 - Integer.parseInt(Character.toString(cCharAt)));
                            }
                        } else {
                            sb.append(cCharAt);
                        }
                    }
                    f3733a = sb.toString();
                }
                if (TextUtils.isEmpty(f3733a)) {
                    return;
                }
                f3733a += new StringBuilder(f3733a).reverse().toString();
                String multiProcessSP2 = UMUtils.getMultiProcessSP(context, f3736d);
                if (TextUtils.isEmpty(multiProcessSP2)) {
                    UMUtils.setMultiProcessSP(context, f3736d, a(f3734b));
                } else {
                    f3734b.equals(b(multiProcessSP2));
                }
            }
        } catch (Throwable unused) {
        }
    }

    public String a(String str) {
        try {
            return TextUtils.isEmpty(f3733a) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), f3733a.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }
}
