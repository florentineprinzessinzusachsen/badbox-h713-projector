package com.umeng.commonsdk.statistics.common;

import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class HelperUtils {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private static final String TAG = "helper";

    public static String MD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(String.format("%02X", Byte.valueOf(b2)));
            }
            return stringBuffer.toString();
        } catch (Exception unused) {
            return str.replaceAll("[^[a-z][A-Z][0-9][.][_]]", "");
        }
    }

    public static boolean checkStrLen(String str, int i) {
        try {
            return !TextUtils.isEmpty(str) && str.length() <= i;
        } catch (Exception unused) {
            return false;
        }
    }

    public static String getFileMD5(File file) {
        byte[] bArr = new byte[1024];
        try {
            if (!file.isFile()) {
                return "";
            }
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i = fileInputStream.read(bArr, 0, 1024);
                if (i == -1) {
                    fileInputStream.close();
                    return String.format("%1$032x", new BigInteger(1, messageDigest.digest()));
                }
                messageDigest.update(bArr, 0, i);
            }
        } catch (Exception unused) {
            return null;
        }
    }

    public static String getMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(Integer.toHexString((b2 & 255) | (-256)).substring(6));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e2) {
            MLog.i(TAG, "getMD5 error", e2);
            return "";
        }
    }

    public static String getUmengMD5(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(Integer.toHexString(b2 & 255));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e2) {
            MLog.i(TAG, "getMD5 error", e2);
            return "";
        }
    }

    public static String readFile(File file) {
        FileInputStream fileInputStream;
        try {
            if (!file.exists()) {
                safeClose((InputStream) null);
                return null;
            }
            fileInputStream = new FileInputStream(file);
            try {
                byte[] bArr = new byte[fileInputStream.available()];
                fileInputStream.read(bArr);
                String str = new String(bArr);
                safeClose(fileInputStream);
                return str;
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            fileInputStream = null;
        }
        safeClose(fileInputStream);
        return null;
    }

    public static byte[] readStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    public static String readStreamToString(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[1024];
        StringWriter stringWriter = new StringWriter();
        while (true) {
            int i = inputStreamReader.read(cArr);
            if (-1 == i) {
                return stringWriter.toString();
            }
            stringWriter.write(cArr, 0, i);
        }
    }

    public static void safeClose(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static String subStr(String str, int i) {
        String strSubstring = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                strSubstring = str.substring(0, str.length() < i ? str.length() : i);
                int length = strSubstring.getBytes("UTF-8").length;
                int i2 = i;
                while (length > i) {
                    i2--;
                    strSubstring = str.substring(0, i2 > str.length() ? str.length() : i2);
                    length = strSubstring.getBytes("UTF-8").length;
                }
                return strSubstring;
            }
        } catch (Exception e2) {
            MLog.e(e2);
        }
        return strSubstring;
    }

    public static void writeFile(File file, byte[] bArr) {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            fileOutputStream.write(bArr);
            fileOutputStream.flush();
        } finally {
            safeClose(fileOutputStream);
        }
    }

    public static void safeClose(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void writeFile(File file, String str) {
        writeFile(file, str.getBytes());
    }
}
