package android.support.v17.leanback.graphics;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.support.v17.leanback.R;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class ColorFilterDimmer {
    private final float mActiveLevel;
    private final ColorFilterCache mColorDimmer;
    private final float mDimmedLevel;
    private ColorFilter mFilter;
    private final Paint mPaint;

    public static ColorFilterDimmer createDefault(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.styleable.LeanbackTheme);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.LeanbackTheme_overlayDimMaskColor, context.getResources().getColor(R.color.lb_view_dim_mask_color));
        float fraction = typedArrayObtainStyledAttributes.getFraction(R.styleable.LeanbackTheme_overlayDimActiveLevel, 1, 1, context.getResources().getFraction(R.fraction.lb_view_active_level, 1, 0));
        float fraction2 = typedArrayObtainStyledAttributes.getFraction(R.styleable.LeanbackTheme_overlayDimDimmedLevel, 1, 1, context.getResources().getFraction(R.fraction.lb_view_dimmed_level, 1, 1));
        typedArrayObtainStyledAttributes.recycle();
        return new ColorFilterDimmer(ColorFilterCache.getColorFilterCache(color), fraction, fraction2);
    }

    public static ColorFilterDimmer create(ColorFilterCache colorFilterCache, float f, float f2) {
        return new ColorFilterDimmer(colorFilterCache, f, f2);
    }

    private ColorFilterDimmer(ColorFilterCache colorFilterCache, float f, float f2) {
        this.mColorDimmer = colorFilterCache;
        f = f > 1.0f ? 1.0f : f;
        f = f < 0.0f ? 0.0f : f;
        float f3 = f2 <= 1.0f ? f2 : 1.0f;
        f3 = f3 < 0.0f ? 0.0f : f3;
        this.mActiveLevel = f;
        this.mDimmedLevel = f3;
        this.mPaint = new Paint();
    }

    public void applyFilterToView(View view) {
        if (this.mFilter != null) {
            view.setLayerType(2, this.mPaint);
        } else {
            view.setLayerType(0, null);
        }
        view.invalidate();
    }

    public void setActiveLevel(float f) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > 1.0f) {
            f = 1.0f;
        }
        this.mFilter = this.mColorDimmer.getFilterForLevel(this.mDimmedLevel + (f * (this.mActiveLevel - this.mDimmedLevel)));
        this.mPaint.setColorFilter(this.mFilter);
    }

    public ColorFilter getColorFilter() {
        return this.mFilter;
    }

    public Paint getPaint() {
        return this.mPaint;
    }
}
