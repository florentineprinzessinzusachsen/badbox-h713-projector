package androidx.core.f;

import android.os.Build;
import android.view.Gravity;

/* JADX INFO: compiled from: GravityCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static int a(int i, int i2) {
        return Build.VERSION.SDK_INT >= 17 ? Gravity.getAbsoluteGravity(i, i2) : i & (-8388609);
    }
}
