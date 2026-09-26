package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: DeflaterHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f4080a;

    public static byte[] a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return a(str.getBytes(str2));
    }

    public static byte[] b(byte[] bArr) throws DataFormatException {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        Inflater inflater = new Inflater();
        int i = 0;
        inflater.setInput(bArr, 0, bArr.length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr2 = new byte[1024];
        while (!inflater.needsInput()) {
            int iInflate = inflater.inflate(bArr2);
            byteArrayOutputStream.write(bArr2, i, iInflate);
            i += iInflate;
        }
        inflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] a(byte[] bArr) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        Deflater deflater = new Deflater();
        deflater.setInput(bArr);
        deflater.finish();
        byte[] bArr2 = new byte[8192];
        f4080a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int iDeflate = deflater.deflate(bArr2);
                    f4080a += iDeflate;
                    byteArrayOutputStream.write(bArr2, 0, iDeflate);
                } catch (Throwable th) {
                    th = th;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
            }
            deflater.end();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }

    public static String a(byte[] bArr, String str) throws DataFormatException {
        byte[] bArrB = b(bArr);
        if (bArrB != null) {
            return new String(bArrB, str);
        }
        return null;
    }
}
