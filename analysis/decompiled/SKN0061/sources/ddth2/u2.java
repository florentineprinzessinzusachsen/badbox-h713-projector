package ddth2;

import android.content.Context;
import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public class u2 {
    public static u2 a = null;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public static String f95a = "";
    public static String b = "";
    public static String c = "";
    public static String d = "";
    public static String e = "";

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final Context f96a;

    public u2(Context context) {
        this.f96a = context.getApplicationContext();
        m73a();
    }

    public static synchronized u2 a(Context context) {
        if (a == null) {
            a = new u2(context);
        }
        return a;
    }

    public static String a() {
        return f95a;
    }

    public static String b() {
        return e;
    }

    public static String d() {
        return b;
    }

    public static String e() {
        return c;
    }

    public static String f() {
        return d;
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final void m73a() {
        try {
            f95a = Build.BRAND;
            b = Build.MODEL;
            c = "Android " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ")";
            d = this.f96a.getPackageName();
            e = c();
        } catch (Exception unused) {
        }
    }

    public final String c() {
        return b0.a(this.f96a);
    }
}
