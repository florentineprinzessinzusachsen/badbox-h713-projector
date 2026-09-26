package com.android.umanalytics.http.utils;

import com.blankj.utilcode.util.EncodeUtils;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class AES {
    public static String aes_cipher = "AES/CBC/PKCS5Padding";
    public static String aes_iv = "e3d5d9c2347f3ea2";
    public static String cKey = "a5cce364452140d7";

    public static String Decrypt(String str, String str2, String str3) {
        if (str2 == null || str3 == null) {
            return null;
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
            Cipher cipher = Cipher.getInstance(aes_cipher);
            cipher.init(2, secretKeySpec, new IvParameterSpec(str3.getBytes()));
            try {
                return new String(cipher.doFinal(EncodeUtils.base64Decode(str)), "utf-8");
            } catch (Exception e2) {
                System.out.println(e2.toString());
                return null;
            }
        } catch (Exception e3) {
            System.out.println(e3.toString());
            return null;
        }
    }

    public static String Encrypt(String str, String str2, String str3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (str2 == null || str3 == null) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
        Cipher cipher = Cipher.getInstance(aes_cipher);
        cipher.init(1, secretKeySpec, new IvParameterSpec(str3.getBytes()));
        return EncodeUtils.base64Encode2String(cipher.doFinal(str.getBytes("utf-8")));
    }

    public void test() {
        String strEncrypt;
        System.out.println("gogogo");
        String strDecrypt = null;
        try {
            strEncrypt = Encrypt("gogogo", cKey, aes_iv);
        } catch (Exception e2) {
            e2.printStackTrace();
            strEncrypt = null;
        }
        System.out.println("加密后的字串是：" + strEncrypt);
        try {
            strDecrypt = Decrypt(strEncrypt, cKey, aes_iv);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        System.out.println("解密后的字串是：" + strDecrypt);
    }
}
