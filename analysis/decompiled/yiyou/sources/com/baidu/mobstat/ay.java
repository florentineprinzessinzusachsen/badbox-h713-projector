package com.baidu.mobstat;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public final class ay {

    public static class a {
        public static String a(byte[] bArr) {
            try {
                return ay.b(MessageDigest.getInstance("md5"), bArr);
            } catch (Exception unused) {
                return "";
            }
        }
    }

    public static class b {
        public static String a(byte[] bArr) {
            try {
                return ay.b(MessageDigest.getInstance("SHA-256"), bArr);
            } catch (Exception unused) {
                return "";
            }
        }

        public static String a(File file) {
            try {
                return ay.b(MessageDigest.getInstance("SHA-256"), file);
            } catch (NoSuchAlgorithmException unused) {
                return "";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(MessageDigest messageDigest, byte[] bArr) {
        messageDigest.update(bArr);
        return a(messageDigest.digest());
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            int i2 = (bArr[i] >> 4) & 15;
            int i3 = bArr[i] & com.umeng.commonsdk.proguard.ap.m;
            sb.append((char) (i2 >= 10 ? (i2 + 97) - 10 : i2 + 48));
            sb.append((char) (i3 >= 10 ? (i3 + 97) - 10 : i3 + 48));
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(MessageDigest messageDigest, File file) throws Throwable {
        FileInputStream fileInputStream;
        if (!file.isFile()) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[4048];
                while (true) {
                    int i = fileInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    messageDigest.update(bArr, 0, i);
                }
            } catch (Exception unused) {
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception unused2) {
                    }
                }
                return a(messageDigest.digest());
            } catch (Throwable th) {
                th = th;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception unused3) {
                    }
                }
                throw th;
            }
        } catch (Exception unused4) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        fileInputStream.close();
        return a(messageDigest.digest());
    }
}
