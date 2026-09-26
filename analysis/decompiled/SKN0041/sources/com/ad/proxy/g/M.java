package com.ad.proxy.g;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
public abstract class M {
    public static boolean a(char c) {
        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
            return (c >= '0' && c <= '9') || c == '-' || c == '.' || c == '_' || c == '~';
        }
        return true;
    }

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("%7E", "~");
        } catch (Throwable unused) {
            StringBuilder sb = new StringBuilder();
            try {
                for (byte b : str.getBytes("UTF-8")) {
                    int i = b & 255;
                    char c = (char) i;
                    if (a(c)) {
                        sb.append(c);
                    } else {
                        sb.append('%');
                        char upperCase = Character.toUpperCase(Character.forDigit((i >> 4) & 15, 16));
                        char upperCase2 = Character.toUpperCase(Character.forDigit(b & 15, 16));
                        sb.append(upperCase);
                        sb.append(upperCase2);
                    }
                }
            } catch (UnsupportedEncodingException unused2) {
                for (char c2 : str.toCharArray()) {
                    if (a(c2)) {
                        sb.append(c2);
                    } else {
                        sb.append('%');
                        char upperCase3 = Character.toUpperCase(Character.forDigit(((c2 & 255) >> 4) & 15, 16));
                        char upperCase4 = Character.toUpperCase(Character.forDigit(c2 & 15, 16));
                        sb.append(upperCase3);
                        sb.append(upperCase4);
                    }
                }
            }
            return sb.toString();
        }
    }
}
