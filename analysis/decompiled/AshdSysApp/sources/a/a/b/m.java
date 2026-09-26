package a.a.b;

import android.os.Build;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f12a = null;
    public static String b = null;
    public static String c = null;
    public static String d = null;
    public static String e = null;
    public static String f = null;
    public static String g = "";
    public static String h = "";

    public static String a() {
        File[] fileArrListFiles;
        if (TextUtils.isEmpty(h) && (fileArrListFiles = new File("/sys/devices").listFiles()) != null) {
            for (File file : fileArrListFiles) {
                if (file.isDirectory() && file.getName().startsWith("rockchip_leds_gpio.")) {
                    h = file.getAbsolutePath() + "/led_ctl";
                }
            }
        }
        if (!TextUtils.isEmpty(h)) {
            File file2 = new File(h);
            if (file2.exists()) {
                String strA = a(file2);
                if (!strA.equals("unknown") && !strA.equals("")) {
                    SystemProperties.set("persist.sys.cm.hardversion", strA.trim());
                    return strA.trim();
                }
            }
        }
        String str = SystemProperties.get("persist.sys.cm.hardversion", "unknown");
        return (TextUtils.isEmpty(str) || str.equals("unknown")) ? "unknown" : str;
    }

    public static String a(File file) {
        FileInputStream fileInputStream;
        if (!file.exists()) {
            return "error";
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return new String(bArr);
            } catch (Exception unused) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                return "error";
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    public static String a(String str) {
        return SystemProperties.get(str, "unknown");
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0090  */
    /* JADX WARN: Code duplicated, block: B:28:0x009a  */
    /* JADX WARN: Code duplicated, block: B:31:0x00a1  */
    public static String b() {
        String strTrim;
        File[] fileArrListFiles;
        String strA = a("ro.product.model2");
        if (strA.equals("unknown")) {
            strA = a("ro.product.model");
        }
        if (TextUtils.isEmpty(g) && (fileArrListFiles = new File("/sys/devices").listFiles()) != null) {
            for (File file : fileArrListFiles) {
                if (file.isDirectory() && file.getName().startsWith("rockchip_leds_gpio.")) {
                    g = file.getAbsolutePath() + "/dts_model";
                }
            }
        }
        if (TextUtils.isEmpty(g)) {
            strTrim = SystemProperties.get("persist.sys.cm.dtsmodel", "unknown");
            if (TextUtils.isEmpty(strTrim)) {
                strTrim = "unknown";
            } else {
                strTrim = "unknown";
            }
        } else {
            File file2 = new File(g);
            if (file2.exists()) {
                String strA2 = a(file2);
                if (strA2.equals("unknown") || strA2.equals("")) {
                    strTrim = SystemProperties.get("persist.sys.cm.dtsmodel", "unknown");
                    if (TextUtils.isEmpty(strTrim) || strTrim.equals("unknown")) {
                        strTrim = "unknown";
                    }
                } else {
                    SystemProperties.set("persist.sys.cm.dtsmodel", strA2.trim());
                    strTrim = strA2.trim();
                }
            } else {
                strTrim = SystemProperties.get("persist.sys.cm.dtsmodel", "unknown");
                if (TextUtils.isEmpty(strTrim)) {
                    strTrim = "unknown";
                } else {
                    strTrim = "unknown";
                }
            }
        }
        if (!TextUtils.isEmpty(strTrim) && !strTrim.equals("unknown")) {
            return strTrim;
        }
        if (!TextUtils.isEmpty(strA) && strA.endsWith("RG_RK3128_D1E8_AS")) {
            String strA3 = a();
            if (!TextUtils.isEmpty(strA3)) {
                if (strA3.contains("812N")) {
                    return "RG_RK3128_D1E8_AS_812N";
                }
                if (strA3.contains("812N") && strA3.contains("C397CWV051")) {
                    return "RG_RK3128_D1E8_AS_812N_051";
                }
                if (strA3.contains("812")) {
                    return "RG_RK3128_D1E8_AS_812";
                }
                if (strA3.contains("810Z") && strA3.contains("C397CWV051")) {
                    return "RG_RK3128_D1E8_AS_810Z_051";
                }
                if (strA3.contains("810") && strA3.contains("C397CWV051")) {
                    return "RG_RK3128_D1E8_AS_810_051";
                }
                if (strA3.contains("810")) {
                    return "RG_RK3128_D1E8_AS_810";
                }
                if (strA3.contains("860Z") && strA3.contains("_2.6")) {
                    return "RG_RK3128_D1E8_AS_860Z_2.6";
                }
                if (strA3.contains("860Z") && strA3.contains("_2.4")) {
                    return "RG_RK3128_D1E8_AS_860Z_2.4";
                }
                if (strA3.contains("860Z")) {
                    return "RG_RK3128_D1E8_AS_860Z";
                }
                if (strA3.contains("860") && strA3.contains("_2.6")) {
                    return "RG_RK3128_D1E8_AS_860_2.6";
                }
                if (strA3.contains("860") && strA3.contains("_2.4")) {
                    return "RG_RK3128_D1E8_AS_860_2.4";
                }
                if (strA3.contains("860")) {
                    return "RG_RK3128_D1E8_AS_860";
                }
                if (strA3.contains("862") && strA3.contains("XQ")) {
                    return "RG_RK3128_D1E8_AS_862XQ";
                }
                if (strA3.contains("862")) {
                    return "RG_RK3128_D1E8_AS_862";
                }
            }
        }
        String strA4 = a();
        if (!TextUtils.isEmpty(strA4)) {
            if (strA4.contains("N2_EN")) {
                return "N2_EN";
            }
            if (strA4.contains("N2_CH")) {
                return "N2_CH";
            }
            if (strA4.contains("N1_OQ")) {
                return "N1_OQ";
            }
        }
        return strA;
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1104)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1058)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public static void c() {
        f12a = b();
        b = a("ro.android.chiptype");
        if (b.equals("unknown")) {
            b = a("ro.board.platform");
        }
        c = a();
        Log.i("CMUpdate2_UpdaterInfo", "hardware_version=" + c);
        d = a("ro.build.version.incremental");
        a("ro.android.buildtime");
        a("ro.build.version.release");
        e = SystemProperties.get("ro.android.productid", "74865231");
        a("persist.sys.cm.chipid");
        String str = Build.SERIAL;
        if (str.equals("unknown") || str.equals("UNKNOWN") || str.equals("") || str.length() < 9) {
            f = "unknown";
        } else {
            f = str.substring(0, 9);
        }
        a("ro.cloudmedia.customer");
        Log.i("CMUpdate2_UpdaterInfo", "vendorID=" + f);
    }
}
