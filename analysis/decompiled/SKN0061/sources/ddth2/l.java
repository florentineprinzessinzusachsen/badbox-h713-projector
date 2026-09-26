package ddth2;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import java.net.NetworkInterface;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public final class l {
    public static String a(Context context) {
        byte[] hardwareAddress;
        String macAddress;
        if (Build.VERSION.SDK_INT < 23) {
            try {
                WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService("wifi");
                if (wifiManager != null && (macAddress = wifiManager.getConnectionInfo().getMacAddress()) != null && !macAddress.equals("02:00:00:00:00:00")) {
                    return macAddress.replace(":", "");
                }
            } catch (Exception unused) {
            }
        }
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0") && (hardwareAddress = networkInterface.getHardwareAddress()) != null) {
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        int length = hardwareAddress.length;
                        sb.append(String.format("%02X%s", Byte.valueOf(b), ""));
                    }
                    String string = sb.toString();
                    if (!string.equals("020000000000")) {
                        return string;
                    }
                }
            }
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }
}
