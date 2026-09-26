package com.hs.p.basic;

import android.util.Base64;
import android.util.Log;
import com.hs.p.common.utils.AESUtils;
import com.hs.p.common.utils.GzipUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class EncryptUtils {
    private static final String TAG = "EncryptUtils";

    @Deprecated
    public static String decrypt(String str) {
        return (str == null || !str.startsWith("H4sI")) ? decryptBase32(str) : decryptGzipBase64(str);
    }

    public static String decryptBase32(String str) {
        if (str == null) {
            return null;
        }
        return new String(CustomBase32.decode(str), StandardCharsets.UTF_8);
    }

    public static byte[] decryptBody(byte[] bArr) {
        try {
            return AESUtils.decrypt(GzipUtils.decompress(Base64.decode(bArr, 2)), Constants.API_AESKEY);
        } catch (Exception e) {
            Log.e(TAG, "decryptBody failed", e);
            return null;
        }
    }

    public static String decryptGzipBase64(String str) {
        try {
            byte[] bArrDecompress = GzipUtils.decompress(Base64.decode(str.getBytes(StandardCharsets.UTF_8), 2));
            return bArrDecompress == null ? "" : new String(bArrDecompress, StandardCharsets.UTF_8);
        } catch (Exception unused) {
            return "";
        }
    }

    @Deprecated
    public static String encrypt(String str) {
        return encryptBase32(str);
    }

    public static String encryptBase32(String str) {
        if (str == null) {
            return null;
        }
        return CustomBase32.encode(str.getBytes(StandardCharsets.UTF_8));
    }

    public static String encryptGzipBase64(String str, String str2) {
        if (str != null && str2 != null) {
            try {
                byte[] bytes = (str2 + str).getBytes(StandardCharsets.UTF_8);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            } catch (IOException e) {
                Log.e(TAG, "GZIP encryption failed", e);
            }
        }
        return null;
    }

    public static String decrypt(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return new String(AESUtils.decrypt(GzipUtils.decompress(Base64.decode(str, 2)), str2), StandardCharsets.UTF_8);
        } catch (Exception unused) {
            return str;
        }
    }

    public static List<String> decryptGzipBase64(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(decryptGzipBase64(it.next()));
            }
        }
        return arrayList;
    }

    public static String encrypt(String str, String str2) {
        if (str == null) {
            return null;
        }
        try {
            return new String(Base64.encode(GzipUtils.compress(AESUtils.encrypt(str, str2)), 2), StandardCharsets.UTF_8);
        } catch (Exception e) {
            Log.e(TAG, "Encryption failed for input string.", e);
            return str;
        }
    }

    @Deprecated
    public static List<String> decrypt(List<String> list) {
        return decryptGzipBase64(list);
    }

    public static List<String> encrypt(List<String> list, String str) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(encrypt(it.next(), str));
            }
        }
        return arrayList;
    }

    public static List<String> decrypt(List<String> list, String str) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(decrypt(it.next(), str));
            }
        }
        return arrayList;
    }
}
