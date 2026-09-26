package com.hs.common.utils;

import android.util.Log;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class LOG {
    public static final int D = 3;
    public static final int E = 6;
    public static final int I = 4;
    public static final int W = 5;
    private static volatile boolean mEnabled = true;
    private static volatile int mLevel = 0;
    public static volatile String stag = "HS";

    public static void d(String str, String str2) {
        logLevel(3, str, str2);
    }

    public static void e(String str, String str2) {
        logLevel(6, str, str2);
    }

    private static boolean equalsIgnoreCase(String str, String str2) {
        if (str == null && str2 == null) {
            return true;
        }
        if (str == null || str2 == null) {
            return false;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static void i(String str, String str2) {
        logLevel(4, str, str2);
    }

    private static void logLevel(int i, String str, String str2) {
        if (mEnabled) {
            if (i < mLevel) {
                i = mLevel;
            }
            if (2 == i) {
                Log.v(stag, "[" + str + "] " + str2);
                return;
            }
            if (3 == i) {
                Log.d(stag, "[" + str + "] " + str2);
                return;
            }
            if (4 == i) {
                Log.i(stag, "[" + str + "] " + str2);
                return;
            }
            if (5 == i) {
                Log.w(stag, "[" + str + "] " + str2);
                return;
            }
            if (6 != i && 7 == i) {
                Log.wtf(stag, "[" + str + "] " + str2);
                return;
            }
            String str3 = stag;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(str);
            sb.append("] ");
            sb.append(str2);
            Log.e(str3, sb.toString());
        }
    }

    public static void setEnabled(boolean z) {
        mEnabled = z;
        if (z) {
            Log.i("LOG", "log enabled ...");
        }
    }

    public static void setLevel(int i) {
        mLevel = i;
    }

    public static void setTag(String str) {
        Log.i("LOG", "[" + stag + "] set log tag to " + str);
        stag = str;
    }

    public static void w(String str, String str2) {
        logLevel(5, str, str2);
    }

    public static void d(String str, String str2, Throwable th) {
        logLevel(3, str, str2, th);
    }

    public static void e(String str, String str2, Throwable th) {
        logLevel(6, str, str2, th);
    }

    public static void i(String str, String str2, Throwable th) {
        logLevel(4, str, str2, th);
    }

    private static void logLevel(int i, String str, String str2, Throwable th) {
        if (mEnabled) {
            if (i < mLevel) {
                i = mLevel;
            }
            if (2 == i) {
                Log.v(stag, "[" + str + "] " + str2, th);
                return;
            }
            if (3 == i) {
                Log.d(stag, "[" + str + "] " + str2, th);
                return;
            }
            if (4 == i) {
                Log.i(stag, "[" + str + "] " + str2, th);
                return;
            }
            if (5 == i) {
                Log.w(stag, "[" + str + "] " + str2, th);
                return;
            }
            if (6 != i && 7 == i) {
                Log.wtf(stag, "[" + str + "] " + str2, th);
                return;
            }
            String str3 = stag;
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(str);
            sb.append("] ");
            sb.append(str2);
            Log.e(str3, sb.toString(), th);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        logLevel(5, str, str2, th);
    }
}
