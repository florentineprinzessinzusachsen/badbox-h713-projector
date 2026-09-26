package com.szns.sdk;

import android.util.Base64;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public final class s {
    public static String a(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec("5360e2884c119aa32a767ceb1c0889b0".getBytes(), "AES");
            byte[] bArr = new byte[16];
            new SecureRandom().nextBytes(bArr);
            cipher.init(1, secretKeySpec, new IvParameterSpec(bArr));
            byte[] bArrDoFinal = cipher.doFinal(str.getBytes());
            return Base64.encodeToString(bArr, 2) + ":" + Base64.encodeToString(bArrDoFinal, 2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String b(String str) {
        try {
            String[] strArrSplit = str.split(":");
            if (strArrSplit.length != 2) {
                throw new IllegalArgumentException("Invalid encrypted format");
            }
            byte[] bArrDecode = Base64.decode(strArrSplit[0], 2);
            byte[] bArrDecode2 = Base64.decode(strArrSplit[1], 2);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec("5360e2884c119aa32a767ceb1c0889b0".getBytes(), "AES"), new IvParameterSpec(bArrDecode));
            return new String(cipher.doFinal(bArrDecode2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
