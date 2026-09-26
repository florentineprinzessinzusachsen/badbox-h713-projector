package com.hs.common.utils;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.widget.Toast;
import com.hs.p.common.http.HTTPHelper;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Logger {
    public static void append(Context context, String str, String str2) {
    }

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

    private static String getStackTrace(Throwable th) {
        if (th == null) {
            return "";
        }
        try {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            return stringWriter.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    private static File newLogFile() {
        return new File(Environment.getExternalStorageDirectory(), "debug.hs.log.enabled");
    }

    private static String notNull(String str) {
        return str == null ? "null" : str;
    }

    private static void showToast(final Context context, final String str) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.hs.common.utils.Logger.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Toast.makeText(context, str, 0).show();
                } catch (Throwable unused) {
                }
            }
        });
    }

    private static String strNow() {
        try {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS", Locale.getDefault()).format(new Date());
        } catch (Exception unused) {
            return "";
        }
    }

    private static int writeToFile(File file, byte[] bArr) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file, true);
            try {
                fileOutputStream2.write(bArr);
                fileOutputStream2.flush();
                int length = bArr.length;
                close(fileOutputStream2);
                return length;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                close(fileOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void append(Context context, String str, String str2, Throwable th) {
        append(context, str, str2 + "\n" + getStackTrace(th));
    }

    private static void writeToFile(File file, String str, String str2) throws Exception {
        writeToFile(file, (strNow() + " " + String.format("%5d", Integer.valueOf(Process.myPid())) + " " + String.format("%5d", Integer.valueOf(Process.myTid())) + " " + String.format("%4s", LOG.stag) + "  [" + str + "] " + str2 + "\n").getBytes(HTTPHelper.CHARSET_UTF8));
    }
}
