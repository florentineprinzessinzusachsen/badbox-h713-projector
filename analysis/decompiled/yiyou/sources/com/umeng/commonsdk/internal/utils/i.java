package com.umeng.commonsdk.internal.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* JADX INFO: compiled from: SDStorage.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3855a = "/.um/.umm.dat";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String f3856b = "/.uxx/.cca.dat";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3857c = "/.cc/.adfwe.dat";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f3858d = "/.a.dat";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f3859e = "umdat";

    public static void a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strG = g(context);
        if (TextUtils.isEmpty(strG) || !str.equals(strG)) {
            b(context, str);
        }
    }

    public static String b(Context context) {
        return h(context);
    }

    public static String c(Context context) {
        return c(context, f3855a);
    }

    public static String d(Context context) {
        return c(context, f3856b);
    }

    public static String e(Context context) {
        return c(context, f3857c);
    }

    public static String f(Context context) {
        return c(context, f3858d);
    }

    public static String g(Context context) {
        return i(context);
    }

    private static String h(Context context) {
        return com.umeng.commonsdk.framework.a.a(context, com.umeng.commonsdk.proguard.e.f3966e, (String) null);
    }

    private static String i(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f3859e, 0);
        if (sharedPreferences != null) {
            return sharedPreferences.getString("u", null);
        }
        return null;
    }

    public static void b(Context context, String str) {
        a(context, str, f3855a);
        a(context, str, f3856b);
        a(context, str, f3857c);
        a(context, str, f3858d);
        d(context, str);
    }

    private static String c(Context context, String str) {
        String externalStorageState;
        try {
            if (!DeviceConfig.checkPermission(context, "android.permission.READ_EXTERNAL_STORAGE") || (externalStorageState = Environment.getExternalStorageState()) == null || !externalStorageState.equalsIgnoreCase("mounted")) {
                return null;
            }
            if (!new File(Environment.getExternalStorageDirectory() + str).exists()) {
                return null;
            }
            FileChannel channel = new RandomAccessFile(Environment.getExternalStorageDirectory() + str, "rw").getChannel();
            FileLock fileLockTryLock = channel.tryLock();
            StringBuilder sb = new StringBuilder();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1024);
            byteBufferAllocate.clear();
            while (channel.read(byteBufferAllocate) != -1) {
                byte[] bArr = new byte[byteBufferAllocate.position()];
                for (int i = 0; i < byteBufferAllocate.position(); i++) {
                    bArr[i] = byteBufferAllocate.get(i);
                }
                sb.append(new String(bArr));
                byteBufferAllocate.clear();
            }
            if (channel != null) {
                fileLockTryLock.release();
            }
            channel.close();
            return sb.toString();
        } catch (Exception e2) {
            ULog.e("getFileUmtt:" + e2.getMessage());
            return null;
        }
    }

    private static void d(Context context, String str) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(f3859e, 0);
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("u", null);
            if (string == null || !string.equals(str)) {
                sharedPreferences.edit().putString("u", str).commit();
            }
        }
    }

    public static String a(Context context) {
        String strB = b(context);
        if (strB == null || strB.equals("")) {
            strB = g(context);
        }
        if (strB == null || strB.equals("")) {
            strB = c(context);
        }
        if (strB == null || strB.equals("")) {
            strB = d(context);
        }
        if (strB == null || strB.equals("")) {
            strB = e(context);
        }
        return (strB == null || strB.equals("")) ? f(context) : strB;
    }

    private static void a(Context context, String str, String str2) {
        if (DeviceConfig.checkPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            try {
                String externalStorageState = Environment.getExternalStorageState();
                if (externalStorageState == null || !externalStorageState.equalsIgnoreCase("mounted")) {
                    return;
                }
                String strC = c(context, str2);
                if (strC == null || !strC.equals(str)) {
                    File file = new File(Environment.getExternalStorageDirectory() + str2);
                    if (file.getParentFile() != null && !file.getParentFile().exists()) {
                        file.getParentFile().mkdir();
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(Environment.getExternalStorageDirectory() + str2, "rw");
                    randomAccessFile.setLength((long) str.getBytes().length);
                    FileChannel channel = randomAccessFile.getChannel();
                    FileLock fileLockTryLock = channel.tryLock();
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1024);
                    byteBufferAllocate.clear();
                    byteBufferAllocate.put(str.getBytes());
                    byteBufferAllocate.flip();
                    while (byteBufferAllocate.hasRemaining()) {
                        channel.write(byteBufferAllocate);
                    }
                    channel.force(true);
                    if (fileLockTryLock != null) {
                        fileLockTryLock.release();
                    }
                    channel.close();
                }
            } catch (Exception e2) {
                ULog.e("saveFileUmtt:" + e2.getMessage());
            }
        }
    }
}
