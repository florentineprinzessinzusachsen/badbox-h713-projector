package com.cloudmedia.tv.server;

import android.util.Log;
import com.tools.e;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Logger f38a = Logger.getLogger(b.class.getName());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f39b = 0;

    public static void a(a aVar) {
        do {
            try {
                aVar.M(a.m, false);
                e.c("sys.ashd.mediaport", "" + a.s);
                Log.i("executeInstance while", "server port" + a.s);
            } catch (IOException e) {
                Log.e("executeInstance while", "server " + e + a.s);
                f39b = f39b + 1;
                a.s = a.s + 1;
            }
            if (aVar.t()) {
                return;
            }
        } while (f39b < 10);
    }

    public static <T extends a> void b(Class<T> cls) {
        try {
            a(cls.newInstance());
        } catch (Exception e) {
            f38a.log(Level.SEVERE, "Cound nor create server", (Throwable) e);
        }
    }
}
