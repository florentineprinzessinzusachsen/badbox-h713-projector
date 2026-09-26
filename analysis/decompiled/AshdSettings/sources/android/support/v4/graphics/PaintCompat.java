package android.support.v4.graphics;

import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public final class PaintCompat {
    public static boolean hasGlyph(@NonNull Paint paint, @NonNull String str) {
        if (Build.VERSION.SDK_INT >= 23) {
            return PaintCompatApi23.hasGlyph(paint, str);
        }
        return PaintCompatGingerbread.hasGlyph(paint, str);
    }

    private PaintCompat() {
    }
}
