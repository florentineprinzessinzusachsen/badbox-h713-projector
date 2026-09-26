package com.android.settingslib.animation;

import android.R;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public class DisappearAnimationUtils extends AppearAnimationUtils {
    private static final AppearAnimationUtils.RowTranslationScaler ROW_TRANSLATION_SCALER = new AppearAnimationUtils.RowTranslationScaler() { // from class: com.android.settingslib.animation.DisappearAnimationUtils.1
        @Override // com.android.settingslib.animation.AppearAnimationUtils.RowTranslationScaler
        public float getRowTranslationScale(int i, int i2) {
            return (float) (Math.pow(i2 - i, 2.0d) / ((double) i2));
        }
    };

    public DisappearAnimationUtils(Context context) {
        this(context, 220L, 1.0f, 1.0f, AnimationUtils.loadInterpolator(context, R.interpolator.fast_out_linear_in));
    }

    public DisappearAnimationUtils(Context context, long j, float f, float f2, Interpolator interpolator) {
        this(context, j, f, f2, interpolator, ROW_TRANSLATION_SCALER);
    }

    public DisappearAnimationUtils(Context context, long j, float f, float f2, Interpolator interpolator, AppearAnimationUtils.RowTranslationScaler rowTranslationScaler) {
        super(context, j, f, f2, interpolator);
        this.mRowTranslationScaler = rowTranslationScaler;
        this.mAppearing = false;
    }

    @Override // com.android.settingslib.animation.AppearAnimationUtils
    protected long calculateDelay(int i, int i2) {
        return (long) ((((double) (i * 60)) + (((double) i2) * (Math.pow(i, 0.4d) + 0.4d) * 10.0d)) * ((double) this.mDelayScale));
    }
}
