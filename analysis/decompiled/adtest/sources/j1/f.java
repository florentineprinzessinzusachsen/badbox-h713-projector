package j1;

import j2.i;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import l3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f1258a = {48, 49, 48, 50, 48, 51, 48, 52, 48, 53, 48, 54, 48, 55, 48, 56};

    public static byte[] a(byte[] bArr) throws Exception {
        String message;
        String message2;
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] bArr2 = new byte[4096];
                    while (true) {
                        int i4 = gZIPInputStream.read(bArr2);
                        if (i4 == -1) {
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            i.d(byteArray, "toByteArray(...)");
                            gZIPInputStream.close();
                            byteArrayInputStream.close();
                            return byteArray;
                        }
                        byteArrayOutputStream.write(bArr2, 0, i4);
                        try {
                            throw th;
                        } catch (Throwable th) {
                            h.j(byteArrayInputStream, th);
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        h.j(gZIPInputStream, th2);
                        throw th3;
                    }
                }
            } catch (Throwable th4) {
                throw th4;
            }
        } catch (Exception e4) {
            if (((e4 instanceof ZipException) && (message2 = e4.getMessage()) != null && p2.i.B0(message2, "GZIP")) || ((e4 instanceof IOException) && (message = e4.getMessage()) != null && p2.i.B0(message, "unknown format"))) {
                return bArr;
            }
            throw e4;
        }
    }

    public static byte[] b(byte[] bArr) throws BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        Charset charset = StandardCharsets.UTF_8;
        i.d(charset, "UTF_8");
        byte[] bytes = "ota.host.a46780a24111f056d95f955462606901".getBytes(charset);
        i.d(bytes, "getBytes(...)");
        byte[] bArrDigest = messageDigest.digest(bytes);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(f1258a);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArrDigest, "AESUtils");
        Cipher cipher = Cipher.getInstance("AES/CFB/NoPadding");
        cipher.init(2, secretKeySpec, ivParameterSpec);
        byte[] bArrDoFinal = cipher.doFinal(bArr);
        i.d(bArrDoFinal, "doFinal(...)");
        return bArrDoFinal;
    }
}
