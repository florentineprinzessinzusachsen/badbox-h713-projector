package com.hs.p.dx;

import android.util.Base64;
import com.hs.p.common.http.HTTPHelper;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DSAUtils {
    public static final String ALGORITHM = "DSA";
    private static final int KS1024 = 1024;
    private static final byte[] SEED = {84, 104, 105, 115, 39, 115, 32, 115, 101, 101, 100, 32, 102, 111, 114, 32, 103, 101, 110, 101, 114, 97, 116, 105, 110, 103, 32, 51, 54, 48, 79, 83, 47, 68, 83, 65, 32, 107, 101, 121, 32, 112, 97, 105, 114, 32, 111, 110, 32, 50, 48, 49, 54, 47, 48, 56, 47, 49, 49};

    public static KeyPair generate() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(SEED);
        keyPairGenerator.initialize(1024, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] getKeyEncoded(String str) throws UnsupportedEncodingException {
        return Base64.decode(str.getBytes(HTTPHelper.CHARSET_UTF8), 2);
    }

    public static String getKeyString(byte[] bArr) throws UnsupportedEncodingException {
        return new String(Base64.encode(bArr, 2), HTTPHelper.CHARSET_UTF8);
    }

    public static PrivateKey getPrivateKey(byte[] bArr) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return KeyFactory.getInstance("DSA").generatePrivate(new PKCS8EncodedKeySpec(bArr));
    }

    public static PublicKey getPublicKey(byte[] bArr) throws InvalidKeySpecException, NoSuchAlgorithmException {
        return KeyFactory.getInstance("DSA").generatePublic(new X509EncodedKeySpec(bArr));
    }

    public static byte[] sign(byte[] bArr, String str) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        return sign(bArr, getPrivateKey(getKeyEncoded(str)));
    }

    public static boolean verify(byte[] bArr, int i, int i2, byte[] bArr2, String str) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, UnsupportedEncodingException {
        return verify(bArr, i, i2, bArr2, getPublicKey(getKeyEncoded(str)));
    }

    public static byte[] sign(byte[] bArr, PrivateKey privateKey) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature = Signature.getInstance("DSA");
        signature.initSign(privateKey);
        signature.update(bArr);
        return signature.sign();
    }

    public static boolean verify(byte[] bArr, int i, int i2, byte[] bArr2, PublicKey publicKey) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature = Signature.getInstance("DSA");
        signature.initVerify(publicKey);
        signature.update(bArr, i, i2);
        return signature.verify(bArr2);
    }

    public static byte[] sign(byte[] bArr, byte[] bArr2) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return sign(bArr, getPrivateKey(bArr2));
    }

    public static boolean verify(byte[] bArr, int i, int i2, byte[] bArr2, byte[] bArr3) throws InvalidKeySpecException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return verify(bArr, i, i2, bArr2, getPublicKey(bArr3));
    }
}
