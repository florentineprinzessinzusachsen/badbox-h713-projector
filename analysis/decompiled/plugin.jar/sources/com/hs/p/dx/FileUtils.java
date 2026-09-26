package com.hs.p.dx;

import com.hs.p.common.utils.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class FileUtils {
    private static void close(Closeable... closeableArr) {
        if (closeableArr != null) {
            for (Closeable closeable : closeableArr) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public static File create(File file, boolean z) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (z && file.exists()) {
            file.delete();
        }
        if (file.exists()) {
            return file;
        }
        try {
            file.createNewFile();
            return file;
        } catch (Exception unused) {
            return null;
        }
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
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return file.delete();
        }
        String[] list = file.list();
        if (list != null) {
            for (String str : list) {
                deleteDir(new File(file, str));
            }
        }
        return file.delete();
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

    public static byte[] read(File file) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i = fileInputStream2.read(bArr);
                    if (-1 == i) {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        close(byteArrayOutputStream, fileInputStream2);
                        return byteArray;
                    }
                    if (i > 0) {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                close(byteArrayOutputStream, fileInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static int write(File file, byte[] bArr) throws Exception {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        if (file.exists()) {
            if (TextUtils.empty(bArr)) {
                return 0;
            }
            return writeToFile(file, bArr);
        }
        throw new Exception("create file(" + file.toString() + ") failed");
    }

    private static int writeToFile(File file, byte[] bArr) throws Exception {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                int length = bArr.length;
                close(fileOutputStream, null);
                return length;
            } catch (Throwable th) {
                th = th;
                close(fileOutputStream, null);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
    }

    public static boolean deleteDir(String str) {
        try {
            return deleteDir(new File(str));
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean deleteFile(String str) {
        try {
            return deleteFile(new File(str));
        } catch (Exception unused) {
            return false;
        }
    }

    public static byte[] read(String str) throws Exception {
        return read(new File(str));
    }

    public static int write(String str, byte[] bArr) throws Exception {
        return write(new File(str), bArr);
    }
}
