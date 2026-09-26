package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class PhoneUtils {
    private PhoneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean call(String str) {
        Intent intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    public static boolean dial(String str) {
        Intent intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }

    @SuppressLint({"HardwareIds"})
    public static String getDeviceId() {
        TelephonyManager telephonyManager = getTelephonyManager();
        String deviceId = telephonyManager.getDeviceId();
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        if (Build.VERSION.SDK_INT < 26) {
            return "";
        }
        String imei = telephonyManager.getImei();
        if (!TextUtils.isEmpty(imei)) {
            return imei;
        }
        String meid = telephonyManager.getMeid();
        return TextUtils.isEmpty(meid) ? "" : meid;
    }

    public static String getIMEI() {
        return getImeiOrMeid(true);
    }

    @SuppressLint({"HardwareIds"})
    public static String getIMSI() {
        return getTelephonyManager().getSubscriberId();
    }

    /* JADX WARN: Code duplicated, block: B:52:0x00b4 A[PHI: r1
      0x00b4: PHI (r1v8 java.lang.String) = (r1v5 java.lang.String), (r1v9 java.lang.String) binds: [B:51:0x00b2, B:43:0x00a0] A[DONT_GENERATE, DONT_INLINE]] */
    @SuppressLint({"HardwareIds"})
    public static String getImeiOrMeid(boolean z) {
        String str;
        TelephonyManager telephonyManager = getTelephonyManager();
        int i = Build.VERSION.SDK_INT;
        int i2 = 1;
        if (i >= 26) {
            return z ? getMinOne(telephonyManager.getImei(0), telephonyManager.getImei(1)) : getMinOne(telephonyManager.getMeid(0), telephonyManager.getMeid(1));
        }
        if (i < 21) {
            String deviceId = telephonyManager.getDeviceId();
            if (z) {
                if (deviceId != null && deviceId.length() >= 15) {
                    return deviceId;
                }
            } else if (deviceId != null && deviceId.length() == 14) {
                return deviceId;
            }
            return "";
        }
        String systemPropertyByReflect = getSystemPropertyByReflect(z ? "ril.gsm.imei" : "ril.cdma.meid");
        if (!TextUtils.isEmpty(systemPropertyByReflect)) {
            String[] strArrSplit = systemPropertyByReflect.split(",");
            return strArrSplit.length == 2 ? getMinOne(strArrSplit[0], strArrSplit[1]) : strArrSplit[0];
        }
        String deviceId2 = telephonyManager.getDeviceId();
        try {
            Method method = telephonyManager.getClass().getMethod("getDeviceId", Integer.TYPE);
            Object[] objArr = new Object[1];
            if (!z) {
                i2 = 2;
            }
            objArr[0] = Integer.valueOf(i2);
            str = (String) method.invoke(telephonyManager, objArr);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            str = "";
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            str = "";
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            str = "";
        }
        if (z) {
            if (deviceId2 != null && deviceId2.length() < 15) {
                deviceId2 = "";
            }
            if (str != null && str.length() < 15) {
                str = "";
            }
        } else {
            if (deviceId2 != null && deviceId2.length() == 14) {
                deviceId2 = "";
            }
            if (str != null && str.length() == 14) {
                str = "";
            }
        }
        return getMinOne(deviceId2, str);
    }

    public static String getMEID() {
        return getImeiOrMeid(false);
    }

    private static String getMinOne(String str, String str2) {
        boolean zIsEmpty = TextUtils.isEmpty(str);
        boolean zIsEmpty2 = TextUtils.isEmpty(str2);
        if (zIsEmpty && zIsEmpty2) {
            return "";
        }
        if (zIsEmpty || zIsEmpty2) {
            return !zIsEmpty ? str : str2;
        }
        return str.compareTo(str2) <= 0 ? str : str2;
    }

    public static int getPhoneType() {
        return getTelephonyManager().getPhoneType();
    }

    @SuppressLint({"HardwareIds"})
    public static String getSerial() {
        return Build.VERSION.SDK_INT >= 26 ? Build.getSerial() : Build.SERIAL;
    }

    /* JADX WARN: Failed to clean up code after switch over string restore
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r2v0 int, still in use, count: 4, list:
      (r2v0 int) from 0x0015: IF  (r2v0 int) != (49679479 int)  -> B:8:0x0017 A[HIDDEN]
      (r2v0 int) from 0x001a: IF  (r2v0 int) != (49679502 int)  -> B:10:0x001c A[HIDDEN]
      (r2v0 int) from 0x001f: IF  (r2v0 int) != (49679532 int)  -> B:12:0x0021 A[HIDDEN]
      (r2v0 int) from 0x0024: SWITCH (r2v0 int)
     case 49679475: goto B:21:0x003d
     case 49679476: goto B:18:0x0033
     case 49679477: goto B:15:0x0029
     default: goto B:45:0x008e A[RegionRef:SW:13]
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.unbindInsn(InsnRemover.java:93)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:226)
    	at jadx.core.utils.InsnRemover.remove(InsnRemover.java:215)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.replaceWithMergedSwitch(SwitchOverStringVisitor.java:355)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:111)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:72)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:140)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:47)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:66)
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static String getSimOperatorByMnc() {
        String simOperator = getTelephonyManager().getSimOperator();
        if (simOperator == null) {
            return "";
        }
        byte b2 = -1;
        if (iHashCode != 49679479) {
            if (iHashCode != 49679502) {
                if (iHashCode != 49679532) {
                    switch (simOperator) {
                        case "46000":
                            b2 = 0;
                            break;
                        case "46001":
                            b2 = 4;
                            break;
                        case "46002":
                            b2 = 1;
                            break;
                        case "46003":
                            b2 = 7;
                            break;
                        default:
                            switch (iHashCode) {
                                case 49679475:
                                    if (simOperator.equals("46005")) {
                                        b2 = 8;
                                    }
                                    break;
                                case 49679476:
                                    if (simOperator.equals("46006")) {
                                        b2 = 5;
                                    }
                                    break;
                                case 49679477:
                                    if (simOperator.equals("46007")) {
                                        b2 = 2;
                                    }
                                    break;
                            }
                    }
                } else if (simOperator.equals("46020")) {
                    b2 = 3;
                }
            } else if (simOperator.equals("46011")) {
                b2 = 9;
            }
        } else if (simOperator.equals("46009")) {
            b2 = 6;
        }
        switch (b2) {
            case 0:
            case 1:
            case 2:
            case 3:
                return "中国移动";
            case 4:
            case 5:
            case 6:
                return "中国联通";
            case 7:
            case 8:
            case 9:
                return "中国电信";
            default:
                return simOperator;
        }
    }

    public static String getSimOperatorName() {
        return getTelephonyManager().getSimOperatorName();
    }

    private static String getSystemPropertyByReflect(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    private static TelephonyManager getTelephonyManager() {
        return (TelephonyManager) Utils.getApp().getSystemService("phone");
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static boolean isPhone() {
        return getTelephonyManager().getPhoneType() != 0;
    }

    public static boolean isSimCardReady() {
        return getTelephonyManager().getSimState() == 5;
    }

    public static boolean sendSms(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
        if (!isIntentAvailable(intent)) {
            return false;
        }
        intent.putExtra("sms_body", str2);
        Utils.getApp().startActivity(intent.addFlags(268435456));
        return true;
    }
}
