package com.hs.p.common.utils;

import com.hs.p.common.http.HTTPHelper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Locale;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DigestUtils {
    public static final String MD5 = "MD5";
    public static final String SHA256 = "SHA-256";

    public static String getAsString(String str, File file) throws Exception {
        return getAsString(str, loadFromFile(file));
    }

    public static String getSafeString(String str, File file) {
        try {
            return getAsString(str, file);
        } catch (Exception unused) {
            return "";
        }
    }

    private static byte[] loadFromFile(File file) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[65536];
                while (true) {
                    int i = fileInputStream2.read(bArr);
                    if (-1 == i) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        IoUtils.close(fileInputStream2);
                        return byteArray;
                    }
                    if (i > 0) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                IoUtils.close(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String md5AsString(File file) {
        return getSafeString(MD5, file);
    }

    public static String sha256AsString(String str) {
        return getSafeString(SHA256, str);
    }

    private static String toHexByteArray(byte[] bArr, int i, int i2) {
        int length = i2 + i;
        if (length > bArr.length) {
            length = bArr.length;
        }
        String str = "";
        while (i < length) {
            str = str + Integer.toHexString((bArr[i] & 255) | (-256)).substring(6);
            i++;
        }
        return str.toLowerCase(Locale.getDefault());
    }

    public static String getAsString(String str, String str2) throws Exception {
        return getAsString(str, str2.getBytes(HTTPHelper.CHARSET_UTF8));
    }

    public static String getSafeString(String str, String str2) {
        try {
            return getAsString(str, str2.getBytes(StandardCharsets.UTF_8));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String md5AsString(String str) {
        return getSafeString(MD5, str);
    }

    public static String getAsString(String str, byte[] bArr) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        messageDigest.update(bArr);
        byte[] bArrDigest = messageDigest.digest();
        return toHexByteArray(bArrDigest, 0, bArrDigest.length);
    }

    public static String getSafeString(String str, byte[] bArr) {
        try {
            return getAsString(str, bArr);
        } catch (Exception unused) {
            return "";
        }
    }

    public static String md5AsString(byte[] bArr) {
        return getSafeString(MD5, bArr);
    }
}
