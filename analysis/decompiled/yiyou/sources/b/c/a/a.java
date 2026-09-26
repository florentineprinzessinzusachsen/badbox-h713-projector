package b.c.a;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: I.java */
/* JADX INFO: loaded from: classes.dex */
class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String[] f1709a = {". ", " ."};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f1710b = 0;

    static void a(int i, String str, String str2, boolean z) {
        String strA = a(str, z);
        if (z) {
            str = strA;
        }
        Logger logger = Logger.getLogger(str);
        if (i != 4) {
            logger.log(Level.WARNING, str2);
        } else {
            logger.log(Level.INFO, str2);
        }
    }

    private static String a(String str, boolean z) {
        if (!z) {
            return str;
        }
        f1710b ^= 1;
        return f1709a[f1710b] + str;
    }
}
