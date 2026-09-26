package com.ad.proxy.g;

import android.util.Log;
import com.ad.proxy.Robin;

/* JADX INFO: loaded from: classes.dex */
public abstract class F {
    public static final boolean a = B.a();

    public static void a(String str, String str2) {
        if (Robin.isDebug()) {
            if (a) {
                Log.d(str, str2);
                return;
            }
            System.out.println("[DEBUG][" + str + "] " + str2);
        }
    }

    public static void b(String str, String str2) {
        if (Robin.isDebug()) {
            if (a) {
                Log.e(str, str2);
                return;
            }
            System.err.println("[ERROR][" + str + "] " + str2);
        }
    }

    public static void a(String str, Exception exc) {
        if (Robin.isDebug()) {
            if (a) {
                Log.e(str, "", exc);
            } else {
                exc.printStackTrace();
            }
        }
    }
}
