package ddth2.hidden;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

/* JADX INFO: renamed from: ddth2.hidden.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0010j {
    public static String a = "";

    public static void a(Context context) {
        String string;
        String strConcat;
        String string2;
        try {
            string = context.getSharedPreferences("persistvendor", 0).getString("unidt1", "");
        } catch (Exception unused) {
            string = "";
        }
        a = string;
        try {
            if (string == "") {
                try {
                    String str = Build.SERIAL;
                    String str2 = Build.FINGERPRINT;
                    String string3 = Settings.System.getString(context.getContentResolver(), "android_id");
                    if (string3 == null) {
                        string2 = "";
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append(string3);
                        if (str == null) {
                            strConcat = "";
                        } else {
                            if (str2 == null) {
                                str2 = "";
                            }
                            strConcat = str.concat(str2);
                        }
                        sb.append(strConcat);
                        string2 = sb.toString();
                    }
                    if (string2 != "") {
                        String strA = a(string2);
                        a = strA;
                        context.getSharedPreferences("persistvendor", 0).edit().putString("unidt1", strA).apply();
                    } else {
                        String strA2 = a(("Tmp" + new Random().nextLong()) + new Date().getTime());
                        a = strA2;
                        context.getSharedPreferences("persistvendor", 0).edit().putString("unidt1", strA2).apply();
                    }
                } catch (Exception unused2) {
                }
            }
        } catch (Exception unused3) {
        }
    }

    public static boolean b(Context context) {
        int port;
        String property;
        try {
            if (Build.VERSION.SDK_INT >= 14) {
                property = System.getProperty("http.proxyHost");
                String property2 = System.getProperty("http.proxyPort");
                if (property2 == null) {
                    property2 = "-1";
                }
                port = Integer.parseInt(property2);
            } else {
                String host = Proxy.getHost(context);
                port = Proxy.getPort(context);
                property = host;
            }
            return !TextUtils.isEmpty(property) && port > 0;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.getType() == 1 || activeNetworkInfo.getType() == 9;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String a(int[] iArr) {
        int length = iArr.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) (iArr[i] ^ (-108));
        }
        return new String(bArr);
    }

    public static String a(String str) {
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "md5_fail";
        }
    }

    public static String a(String str, String str2) {
        try {
            String str3 = "biz=%s&timestamp=%d&version=7.17&os=2&id=%s&%s";
            String str4 = "biz=%s&timestamp=%d&version=7.17&os=2&id=%s&sign=%s";
            long jCurrentTimeMillis = System.currentTimeMillis();
            return String.format(str4, str, Long.valueOf(jCurrentTimeMillis), a, a(String.format(str3, str, Long.valueOf(jCurrentTimeMillis), a, str2)));
        } catch (Exception unused) {
            return "";
        }
    }
}
