package com.hs.p.common.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class LocalFileUtils {
    public static File create(File file, boolean z) throws Exception {
        if (!file.exists() && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (z && file.exists()) {
            file.delete();
        }
        if (!file.exists()) {
            file.createNewFile();
            if (!file.exists()) {
                throw new Exception("create " + file + " failed");
            }
        }
        return file;
    }

    public static File createDir(File file) {
        if (file.exists() && !file.isDirectory()) {
            deleteFile(file);
        }
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        return null;
    }

    public static boolean deleteDir(File file) {
        try {
            if (file.exists()) {
                return doDeleteDir(file);
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean deleteFile(File file) {
        try {
            if (file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean doDeleteDir(File file) {
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return file.delete();
        }
        String[] list = file.list();
        if (list != null) {
            for (String str : list) {
                doDeleteDir(new File(file, str));
            }
        }
        return file.delete();
    }

    private static void ensureExist(File file) throws Exception {
        if (file.exists()) {
            return;
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        if (file.exists()) {
            return;
        }
        throw new Exception("create " + file + " failed");
    }

    public static byte[] readAsByteArray(File file) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] asByteArray = IoUtils.getAsByteArray(fileInputStream2);
                IoUtils.close(fileInputStream2);
                return asByteArray;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                IoUtils.close(fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static long write(File file, byte[] bArr) throws Exception {
        if (TextUtils.empty(bArr)) {
            return 0L;
        }
        create(file, true);
        return writeToFile(file, bArr);
    }

    private static long writeToFile(File file, byte[] bArr) throws Exception {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                long jWriteToFile = writeToFile(byteArrayInputStream2, file);
                IoUtils.close(byteArrayInputStream2);
                return jWriteToFile;
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = byteArrayInputStream2;
                IoUtils.close(byteArrayInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static boolean deleteDir(String str) {
        return deleteDir(new File(str));
    }

    public static boolean deleteFile(String str) {
        return deleteFile(new File(str));
    }

    public static byte[] readAsByteArray(String str) throws Exception {
        return readAsByteArray(new File(str));
    }

    public static long write(String str, byte[] bArr) throws Exception {
        return write(new File(str), bArr);
    }

    private static long writeToFile(InputStream inputStream, File file) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                long j = 0;
                while (true) {
                    int i = inputStream.read(bArr);
                    if (-1 == i) {
                        fileOutputStream2.flush();
                        IoUtils.close(fileOutputStream2);
                        return j;
                    }
                    if (i > 0) {
                        fileOutputStream2.write(bArr, 0, i);
                        j += (long) i;
                    }
                }
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                IoUtils.close(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
