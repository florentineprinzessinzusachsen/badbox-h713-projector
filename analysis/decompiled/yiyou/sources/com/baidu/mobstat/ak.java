package com.baidu.mobstat;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes.dex */
public abstract class ak {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3464a = 2;

    private String d(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable cause = th; cause != null; cause = cause.getCause()) {
            if (cause instanceof UnknownHostException) {
                return "";
            }
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public abstract String a();

    public void a(String str) {
        a(3, str);
    }

    public void b(String str) {
        a(5, str);
    }

    public abstract boolean b();

    public void c(String str) {
        a(6, str);
    }

    public void a(Throwable th) {
        a(3, d(th));
    }

    public void b(Throwable th) {
        a(5, d(th));
    }

    public void c(Throwable th) {
        a(6, d(th));
    }

    private void a(int i, String str) {
        if (!b() || i < f3464a) {
            return;
        }
        Log.println(i, a(), str);
    }
}
