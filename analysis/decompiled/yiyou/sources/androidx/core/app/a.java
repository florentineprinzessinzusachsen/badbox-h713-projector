package androidx.core.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;

/* JADX INFO: compiled from: ActivityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends androidx.core.content.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static b f905c;

    /* JADX INFO: renamed from: androidx.core.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface InterfaceC0014a {
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface b {
        boolean a(Activity activity, int i, int i2, Intent intent);
    }

    /* JADX INFO: compiled from: ActivityCompat.java */
    public interface c {
    }

    public static b a() {
        return f905c;
    }

    public static void b(Activity activity) {
        if (Build.VERSION.SDK_INT >= 28) {
            activity.recreate();
        } else {
            if (androidx.core.app.c.a(activity)) {
                return;
            }
            activity.recreate();
        }
    }

    public static void a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 16) {
            activity.finishAffinity();
        } else {
            activity.finish();
        }
    }
}
