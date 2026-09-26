package com.hs.p.dx;

import com.hs.p.common.http.HTTPHelper;
import com.hs.p.common.utils.DigestUtils;
import java.security.MessageDigest;
import java.util.UUID;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class AES {
    private static final byte[] IV = {48, 49, 48, 50, 48, 51, 48, 52, 48, 53, 48, 54, 48, 55, 48, 56};
    private static final String MODE = "AES/CFB/NoPadding";

    public static byte[] decrypt(byte[] bArr, int i, int i2, String str) throws Exception {
        byte[] bArrDigest = MessageDigest.getInstance(DigestUtils.MD5).digest(str.getBytes(HTTPHelper.CHARSET_UTF8));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArrDigest, "AES");
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr, i, i2);
    }

    public static String decryptAsString(byte[] bArr, int i, int i2, String str) throws Exception {
        return new String(decrypt(bArr, i, i2, str), HTTPHelper.CHARSET_UTF8);
    }

    public static byte[] encrypt(String str, String str2) throws Exception {
        byte[] bytes = str.getBytes(HTTPHelper.CHARSET_UTF8);
        return encrypt(bytes, 0, bytes.length, str2);
    }

    public static String genSalt() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static byte[] decrypt(byte[] bArr, int i, int i2, String str, String str2) throws Exception {
        return decrypt(bArr, i, i2, str + str2);
    }

    public static String decryptAsString(byte[] bArr, int i, int i2, String str, String str2) throws Exception {
        return new String(decrypt(bArr, i, i2, str + str2), HTTPHelper.CHARSET_UTF8);
    }

    public static byte[] encrypt(String str, String str2, String str3) throws Exception {
        byte[] bytes = str.getBytes(HTTPHelper.CHARSET_UTF8);
        return encrypt(bytes, 0, bytes.length, str2, str3);
    }

    public static byte[] encrypt(byte[] bArr, int i, int i2, String str) throws Exception {
        byte[] bArrDigest = MessageDigest.getInstance(DigestUtils.MD5).digest(str.getBytes(HTTPHelper.CHARSET_UTF8));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArrDigest, "AES");
        Cipher cipher = Cipher.getInstance(MODE);
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(bArr, i, i2);
    }

    public static byte[] encrypt(byte[] bArr, int i, int i2, String str, String str2) throws Exception {
        return encrypt(bArr, i, i2, str + str2);
    }
}
