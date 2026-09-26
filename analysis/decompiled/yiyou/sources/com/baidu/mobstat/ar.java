package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class ar {

    public static class b {
        public static byte[] a(int i, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            int i2 = i - 1;
            if (i2 >= 0) {
                String[] strArr = aw.f3481a;
                if (strArr.length > i2) {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(strArr[i2].getBytes(), "AES");
                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    cipher.init(1, secretKeySpec);
                    return cipher.doFinal(bArr);
                }
            }
            return new byte[0];
        }

        public static byte[] b(int i, byte[] bArr) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
            int i2 = i - 1;
            if (i2 >= 0) {
                String[] strArr = aw.f3481a;
                if (strArr.length > i2) {
                    SecretKeySpec secretKeySpec = new SecretKeySpec(strArr[i2].getBytes(), "AES");
                    Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
                    cipher.init(2, secretKeySpec);
                    return cipher.doFinal(bArr);
                }
            }
            return new byte[0];
        }

        public static String c(int i, byte[] bArr) {
            try {
                return au.b(a(i, bArr));
            } catch (Exception unused) {
                return "";
            }
        }

        public static String d(int i, byte[] bArr) {
            String strC = c(i, bArr);
            if (TextUtils.isEmpty(strC)) {
                return "";
            }
            return strC + "|" + i;
        }
    }

    public static class a {
        @SuppressLint({"TrulyRandom"})
        public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr2);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr3);
        }

        public static byte[] b() {
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            return bArr;
        }

        public static String b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            try {
                return au.b(a(bArr, bArr2, ax.a(bArr3))) + "|" + ba.a(bArr) + "|" + ba.a(bArr2);
            } catch (Exception unused) {
                return "";
            }
        }

        public static byte[] a() throws NoSuchAlgorithmException {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom());
            return keyGenerator.generateKey().getEncoded();
        }

        public static String a(byte[] bArr) {
            try {
                return b(a(), b(), bArr);
            } catch (Exception unused) {
                return "";
            }
        }
    }
}
