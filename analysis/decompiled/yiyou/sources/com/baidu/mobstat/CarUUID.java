package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Environment;
import android.os.Process;
import android.system.Os;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class CarUUID {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Pattern f3335a = Pattern.compile("(\\w{32})");

    private static String a(Context context) {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static String b(Context context) {
        return a(context.getFileStreamPath("libdueros_uuid.so"));
    }

    private static String c(Context context) {
        if (c(context, "android.permission.READ_EXTERNAL_STORAGE")) {
            return a(new File(new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig"), ".dueros_uuid"));
        }
        return null;
    }

    private static String d(Context context) {
        String strA;
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        for (ApplicationInfo applicationInfo2 : installedApplications) {
            if (!applicationInfo.packageName.equals(applicationInfo2.packageName) && (strA = a(new File(new File(applicationInfo2.dataDir, "files"), "libdueros_uuid.so"))) != null) {
                return strA;
            }
        }
        return null;
    }

    public static String optUUID(Context context) throws Throwable {
        String strB = b(context);
        if (strB != null) {
            return strB;
        }
        String strC = c(context);
        if (strC != null) {
            a(context, strC);
            return strC;
        }
        String strD = d(context);
        if (strD != null) {
            a(context, strD);
            b(context, strD);
            return strD;
        }
        String strA = a(context);
        if (strA == null) {
            return "";
        }
        a(context, strA);
        b(context, strA);
        return strA;
    }

    private static boolean a(Context context, String str) {
        boolean z = false;
        FileOutputStream fileOutputStreamOpenFileOutput = null;
        try {
            fileOutputStreamOpenFileOutput = context.openFileOutput("libdueros_uuid.so", android.os.Build.VERSION.SDK_INT >= 21 ? 0 : 1);
            if (a(fileOutputStreamOpenFileOutput, str)) {
                if (android.os.Build.VERSION.SDK_INT < 21) {
                    return true;
                }
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                File fileStreamPath = context.getFileStreamPath("libdueros_uuid.so");
                if (a(new File(applicationInfo.dataDir), 457) && a(fileStreamPath, 484)) {
                    z = true;
                }
                return z;
            }
        } catch (Exception unused) {
        } finally {
            az.a(fileOutputStreamOpenFileOutput);
        }
        return false;
    }

    private static boolean b(Context context, String str) throws Throwable {
        if (!c(context, "android.permission.WRITE_EXTERNAL_STORAGE")) {
            return false;
        }
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(new File(Environment.getExternalStorageDirectory(), "backups/.SystemConfig"), ".dueros_uuid"));
            try {
                boolean zA = a(fileOutputStream2, str);
                az.a(fileOutputStream2);
                return zA;
            } catch (Exception unused) {
                fileOutputStream = fileOutputStream2;
                az.a(fileOutputStream);
                return false;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                az.a(fileOutputStream);
                throw th;
            }
        } catch (Exception unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }

    private static boolean c(Context context, String str) {
        return context.checkPermission(str, Process.myPid(), Process.myUid()) == 0;
    }

    private static String a(File file) throws Throwable {
        FileInputStream fileInputStream;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    String str = new String(bArr, 0, fileInputStream.read(bArr));
                    String str2 = f3335a.matcher(str).matches() ? str : null;
                    az.a(fileInputStream);
                    return str2;
                } catch (Exception unused) {
                    az.a(fileInputStream);
                    return null;
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
        }
        return null;
    }

    private static boolean a(FileOutputStream fileOutputStream, String str) {
        try {
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    private static boolean a(File file, int i) {
        if (android.os.Build.VERSION.SDK_INT < 21) {
            return true;
        }
        try {
            Os.chmod(file.getAbsolutePath(), i);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
