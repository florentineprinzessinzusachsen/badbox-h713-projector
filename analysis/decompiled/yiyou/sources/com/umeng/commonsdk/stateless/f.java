package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ap;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.zip.Deflater;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: compiled from: UMSLUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f4055a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final byte[] f4056b = {10, 1, 11, 5, 4, ap.m, 7, 9, 23, 3, 1, 6, 8, 12, ap.k, 91};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Object f4057c = new Object();

    public static boolean a(long j, long j2) {
        return j > j2;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x00d5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a(android.content.Context r8, java.lang.String r9, java.lang.String r10, byte[] r11) {
        /*
            Method dump skipped, instruction units count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.stateless.f.a(android.content.Context, java.lang.String, java.lang.String, byte[]):boolean");
    }

    public static byte[] b(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String c(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b2 : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static byte[] a(String str) {
        Throwable th;
        FileChannel channel;
        IOException e2;
        byte[] bArr;
        synchronized (f4057c) {
            ULog.i("walle", "[stateless] begin read envelope, thread is " + Thread.currentThread());
            try {
                channel = new RandomAccessFile(str, "r").getChannel();
                try {
                    try {
                        MappedByteBuffer mappedByteBufferLoad = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).load();
                        System.out.println(mappedByteBufferLoad.isLoaded());
                        bArr = new byte[(int) channel.size()];
                        if (mappedByteBufferLoad.remaining() > 0) {
                            mappedByteBufferLoad.get(bArr, 0, mappedByteBufferLoad.remaining());
                        }
                        ULog.i("walle", "[stateless] end read envelope, thread id " + Thread.currentThread());
                        if (channel != null) {
                            try {
                                channel.close();
                            } catch (IOException unused) {
                            }
                        }
                    } catch (IOException e3) {
                        e2 = e3;
                        ULog.i("walle", "[stateless] write envelope, e is " + e2.getMessage());
                        throw e2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e2 = e4;
                channel = null;
            } catch (Throwable th3) {
                th = th3;
                channel = null;
                if (channel != null) {
                    channel.close();
                }
                throw th;
            }
        }
        return bArr;
    }

    public static File a(Context context) {
        File[] fileArrListFiles;
        File[] fileArrListFiles2;
        File file = null;
        try {
            synchronized (f4057c) {
                try {
                    ULog.i("walle", "get last envelope begin, thread is " + Thread.currentThread());
                    if (context != null && context.getApplicationContext() != null) {
                        String str = context.getApplicationContext().getFilesDir() + "/" + a.f4025e;
                        if (!TextUtils.isEmpty(str)) {
                            File file2 = new File(str);
                            if (file2.isDirectory() && (fileArrListFiles = file2.listFiles()) != null && fileArrListFiles.length > 0) {
                                File file3 = null;
                                for (File file4 : fileArrListFiles) {
                                    try {
                                        if (file4 != null && file4.isDirectory() && (fileArrListFiles2 = file4.listFiles()) != null && fileArrListFiles2.length > 0) {
                                            Arrays.sort(fileArrListFiles2, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.1
                                                @Override // java.util.Comparator
                                                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                                                public int compare(File file5, File file6) {
                                                    long jLastModified = file5.lastModified() - file6.lastModified();
                                                    if (jLastModified > 0) {
                                                        return 1;
                                                    }
                                                    return jLastModified == 0 ? 0 : -1;
                                                }
                                            });
                                            File file5 = fileArrListFiles2[0];
                                            if (file5 != null && (file3 == null || file3.lastModified() > file5.lastModified())) {
                                                file3 = file5;
                                            }
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        file = file3;
                                        throw th;
                                    }
                                }
                                file = file3;
                            }
                        }
                    }
                    ULog.i("walle", "get last envelope end, thread is " + Thread.currentThread());
                    return file;
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            UMCrashManager.reportCrash(context, th3);
        }
    }

    public static void a(Context context, String str, int i) {
        try {
            if (str == null) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            File file = new File(str);
            if (!file.isDirectory()) {
                ULog.i("AmapLBS", "[lbs-build] fileDir not exist, thread is " + Thread.currentThread());
                return;
            }
            synchronized (f4057c) {
                File[] fileArrListFiles = file.listFiles();
                ULog.i("AmapLBS", "[lbs-build] delete file begin " + fileArrListFiles.length + ", thread is " + Thread.currentThread());
                if (fileArrListFiles != null && fileArrListFiles.length >= i) {
                    ULog.i("AmapLBS", "[lbs-build] file size >= max");
                    ArrayList arrayList = new ArrayList();
                    for (File file2 : fileArrListFiles) {
                        if (file2 != null) {
                            arrayList.add(file2);
                        }
                    }
                    if (arrayList.size() >= i) {
                        Collections.sort(arrayList, new Comparator<File>() { // from class: com.umeng.commonsdk.stateless.f.2
                            @Override // java.util.Comparator
                            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                            public int compare(File file3, File file4) {
                                if (file3 == null || file4 == null || file3.lastModified() >= file4.lastModified()) {
                                    return (file3 == null || file4 == null || file3.lastModified() != file4.lastModified()) ? 1 : 0;
                                }
                                return -1;
                            }
                        });
                        if (ULog.DEBUG) {
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                ULog.i("AmapLBS", "[lbs-build] overrun native file is " + ((File) arrayList.get(i2)).getPath());
                            }
                        }
                        for (int i3 = 0; i3 <= arrayList.size() - i; i3++) {
                            if (arrayList.get(i3) != null) {
                                ULog.i("AmapLBS", "[lbs-build] overrun remove file is " + ((File) arrayList.get(i3)).getPath());
                                try {
                                    ((File) arrayList.get(i3)).delete();
                                    arrayList.remove(i3);
                                } catch (Exception unused) {
                                }
                            }
                        }
                    }
                } else {
                    ULog.i("AmapLBS", "[lbs-build] file size < max");
                }
                ULog.i("AmapLBS", "[lbs-build] delete file end " + fileArrListFiles.length + ", thread is " + Thread.currentThread());
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(context, th);
        }
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
        f4055a = 0;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            while (!deflater.finished()) {
                try {
                    int iDeflate = deflater.deflate(bArr2);
                    f4055a += iDeflate;
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

    public static byte[] a(byte[] bArr, byte[] bArr2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f4056b));
        return cipher.doFinal(bArr);
    }
}
