package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public final class at {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Proxy f3476a = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.172", 80));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Proxy f3477b = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("10.0.0.200", 80));

    public static String a() {
        try {
            return Environment.getExternalStorageState();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String b(String str) throws Throwable {
        FileInputStream fileInputStream;
        File fileA = a(str);
        if (fileA == null || !fileA.exists()) {
            return "";
        }
        try {
            fileInputStream = new FileInputStream(fileA);
            try {
                byte[] bArrA = a(fileInputStream);
                if (bArrA != null) {
                    String str2 = new String(bArrA, "utf-8");
                    az.a(fileInputStream);
                    return str2;
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                th = th;
                az.a(fileInputStream);
                throw th;
            }
        } catch (Exception unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
        az.a(fileInputStream);
        return "";
    }

    public static boolean c(String str) {
        File fileA = a(str);
        if (fileA == null || !fileA.isFile()) {
            return false;
        }
        return fileA.delete();
    }

    public static HttpURLConnection d(Context context, String str) {
        return a(context, str, 50000, 50000);
    }

    public static boolean e(Context context, String str) {
        boolean z = false;
        try {
            if (context.checkCallingOrSelfPermission(str) == 0) {
                z = true;
            }
        } catch (Exception unused) {
        }
        if (!z) {
            am.c().b("[WARNING] not have permission " + str + ", please add it in AndroidManifest.xml according our developer doc");
        }
        return z;
    }

    public static File a(String str) {
        File externalStorageDirectory;
        if (!"mounted".equals(a())) {
            return null;
        }
        try {
            externalStorageDirectory = Environment.getExternalStorageDirectory();
        } catch (Exception unused) {
            externalStorageDirectory = null;
        }
        if (externalStorageDirectory == null) {
            return null;
        }
        return new File(externalStorageDirectory, str);
    }

    public static boolean c(Context context, String str) {
        return context.getFileStreamPath(str).exists();
    }

    public static void a(Context context, String str, String str2, boolean z) {
        if (context == null) {
            return;
        }
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            fileOutputStreamOpenFileOutput = context.openFileOutput(str, z ? 32768 : 0);
            az.a(new ByteArrayInputStream(str2.getBytes("utf-8")), fileOutputStreamOpenFileOutput);
        } catch (Exception unused) {
        } finally {
            az.a(fileOutputStreamOpenFileOutput);
        }
    }

    public static boolean b(Context context, String str) {
        return context.deleteFile(str);
    }

    public static void a(String str, String str2, boolean z) throws Throwable {
        File parentFile;
        FileOutputStream fileOutputStream = null;
        try {
            File fileA = a(str);
            if (fileA != null) {
                if (!fileA.exists() && (parentFile = fileA.getParentFile()) != null) {
                    parentFile.mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(fileA, z);
                try {
                    az.a(new ByteArrayInputStream(str2.getBytes("utf-8")), fileOutputStream2);
                } catch (Exception unused) {
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    az.a(fileOutputStream);
                    throw th;
                }
                fileOutputStream = fileOutputStream2;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
        az.a(fileOutputStream);
    }

    public static String a(Context context, String str) {
        FileInputStream fileInputStreamOpenFileInput = null;
        try {
            fileInputStreamOpenFileInput = context.openFileInput(str);
            byte[] bArrA = a(fileInputStreamOpenFileInput);
            if (bArrA != null) {
                String str2 = new String(bArrA, "utf-8");
                az.a(fileInputStreamOpenFileInput);
                return str2;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            az.a(fileInputStreamOpenFileInput);
            throw th;
        }
        az.a(fileInputStreamOpenFileInput);
        return "";
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (az.a(inputStream, byteArrayOutputStream)) {
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x006e  */
    @SuppressLint({"DefaultLocale"})
    public static HttpURLConnection a(Context context, String str, int i, int i2) {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if (networkInfo2 != null && networkInfo2.isAvailable()) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } else if (networkInfo == null || !networkInfo.isAvailable()) {
            httpURLConnection = null;
        } else {
            String extraInfo = networkInfo.getExtraInfo();
            String lowerCase = extraInfo != null ? extraInfo.toLowerCase() : "";
            if (!lowerCase.startsWith("cmwap") && !lowerCase.startsWith("uniwap") && !lowerCase.startsWith("3gwap")) {
                if (lowerCase.startsWith("ctwap")) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(f3477b);
                } else {
                    httpURLConnection = null;
                }
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection(f3476a);
            }
        }
        if (httpURLConnection == null) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        httpURLConnection.setConnectTimeout(i);
        httpURLConnection.setReadTimeout(i2);
        return httpURLConnection;
    }
}
