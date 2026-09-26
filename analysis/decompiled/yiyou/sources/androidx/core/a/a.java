package androidx.core.a;

import android.graphics.Color;

/* JADX INFO: compiled from: ColorUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    static {
        new ThreadLocal();
    }

    private static int a(int i, int i2) {
        return 255 - (((255 - i2) * (255 - i)) / 255);
    }

    public static int b(int i, int i2) {
        int iAlpha = Color.alpha(i2);
        int iAlpha2 = Color.alpha(i);
        int iA = a(iAlpha2, iAlpha);
        return Color.argb(iA, a(Color.red(i), iAlpha2, Color.red(i2), iAlpha, iA), a(Color.green(i), iAlpha2, Color.green(i2), iAlpha, iA), a(Color.blue(i), iAlpha2, Color.blue(i2), iAlpha, iA));
    }

    public static int c(int i, int i2) {
        if (i2 < 0 || i2 > 255) {
            throw new IllegalArgumentException("alpha must be between 0 and 255.");
        }
        return (i & 16777215) | (i2 << 24);
    }

    private static int a(int i, int i2, int i3, int i4, int i5) {
        if (i5 == 0) {
            return 0;
        }
        return (((i * 255) * i2) + ((i3 * i4) * (255 - i2))) / (i5 * 255);
    }
}
