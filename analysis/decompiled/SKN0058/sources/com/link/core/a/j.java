package com.link.core.a;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class j {
    public static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr = a;
            int length = bArrDigest.length;
            char[] cArr2 = new char[length << 1];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                int i3 = i + 1;
                cArr2[i] = cArr[(bArrDigest[i2] & 240) >>> 4];
                i = i3 + 1;
                cArr2[i3] = cArr[bArrDigest[i2] & 15];
            }
            return new String(cArr2);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
