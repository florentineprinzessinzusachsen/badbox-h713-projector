package androidx.core.f;

import android.os.Build;
import android.view.ViewGroup;

/* JADX INFO: compiled from: MarginLayoutParamsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class f {
    public static int a(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return Build.VERSION.SDK_INT >= 17 ? marginLayoutParams.getMarginEnd() : marginLayoutParams.rightMargin;
    }

    public static int b(ViewGroup.MarginLayoutParams marginLayoutParams) {
        return Build.VERSION.SDK_INT >= 17 ? marginLayoutParams.getMarginStart() : marginLayoutParams.leftMargin;
    }
}
