package android.support.v17.leanback.graphics;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public final class ColorFilterCache {
    private static final SparseArray<ColorFilterCache> sColorToFiltersMap = new SparseArray<>();
    private final PorterDuffColorFilter[] mFilters = new PorterDuffColorFilter[256];

    public static ColorFilterCache getColorFilterCache(int i) {
        int iRed = Color.red(i);
        int iGreen = Color.green(i);
        int iBlue = Color.blue(i);
        int iRgb = Color.rgb(iRed, iGreen, iBlue);
        ColorFilterCache colorFilterCache = sColorToFiltersMap.get(iRgb);
        if (colorFilterCache != null) {
            return colorFilterCache;
        }
        ColorFilterCache colorFilterCache2 = new ColorFilterCache(iRed, iGreen, iBlue);
        sColorToFiltersMap.put(iRgb, colorFilterCache2);
        return colorFilterCache2;
    }

    private ColorFilterCache(int i, int i2, int i3) {
        for (int i4 = 0; i4 <= 255; i4++) {
            this.mFilters[i4] = new PorterDuffColorFilter(Color.argb(i4, i, i2, i3), PorterDuff.Mode.SRC_ATOP);
        }
    }

    public ColorFilter getFilterForLevel(float f) {
        if (f < 0.0f || f > 1.0d) {
            return null;
        }
        return this.mFilters[(int) (f * 255.0f)];
    }
}
