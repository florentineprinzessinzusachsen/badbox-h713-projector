package com.rk_itvui.settings;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/* JADX INFO: loaded from: classes.dex */
public class ScaleAnimEffect {
    private long duration;
    private float fromAlpha;
    private float fromXScale;
    private float fromYScale;
    private float toAlpha;
    private float toXScale;
    private float toYScale;

    public void setAttributs(float f, float f2, float f3, float f4, long j) {
        this.fromXScale = f;
        this.fromYScale = f3;
        this.toXScale = f2;
        this.toYScale = f4;
        this.duration = j;
    }

    public Animation createAnimation() {
        ScaleAnimation scaleAnimation = new ScaleAnimation(this.fromXScale, this.toXScale, this.fromYScale, this.toYScale, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setInterpolator(new AccelerateInterpolator());
        scaleAnimation.setDuration(this.duration);
        return scaleAnimation;
    }

    public Animation alphaAnimation(float f, float f2, long j, long j2) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f, f2);
        alphaAnimation.setDuration(j);
        alphaAnimation.setStartOffset(j2);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        return alphaAnimation;
    }
}
