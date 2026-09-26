package com.android.settingslib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/* JADX INFO: loaded from: classes.dex */
public class RestrictedLockImageSpan extends ImageSpan {
    private Context mContext;
    private final float mExtraPadding;
    private final Drawable mRestrictedPadlock;

    public RestrictedLockImageSpan(Context context) {
        super((Drawable) null);
        this.mContext = context;
        this.mExtraPadding = this.mContext.getResources().getDimensionPixelSize(R.dimen.restricted_icon_padding);
        this.mRestrictedPadlock = RestrictedLockUtils.getRestrictedPadlock(this.mContext);
    }

    @Override // android.text.style.ImageSpan, android.text.style.DynamicDrawableSpan
    public Drawable getDrawable() {
        return this.mRestrictedPadlock;
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Drawable drawable = getDrawable();
        canvas.save();
        canvas.translate(f + this.mExtraPadding, (i5 - drawable.getBounds().bottom) / 2.0f);
        drawable.draw(canvas);
        canvas.restore();
    }

    @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        return (int) (super.getSize(paint, charSequence, i, i2, fontMetricsInt) + (this.mExtraPadding * 2.0f));
    }
}
