package com.rk_itvui.settings.dialog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
public class LinearColorBar extends LinearLayout {
    static final int LEFT_COLOR = -6250336;
    static final int MIDDLE_COLOR = -6250336;
    static final int RIGHT_COLOR = -6242144;
    final Paint mColorGradientPaint;
    final Path mColorPath;
    final Paint mEdgeGradientPaint;
    final Path mEdgePath;
    private float mGreenRatio;
    int mLastInterestingLeft;
    int mLastInterestingRight;
    int mLineWidth;
    final Paint mPaint;
    final Rect mRect;
    private float mRedRatio;
    private boolean mShowingGreen;
    private float mYellowRatio;

    public LinearColorBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mRect = new Rect();
        this.mPaint = new Paint();
        this.mColorPath = new Path();
        this.mEdgePath = new Path();
        this.mColorGradientPaint = new Paint();
        this.mEdgeGradientPaint = new Paint();
        setWillNotDraw(false);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mColorGradientPaint.setStyle(Paint.Style.FILL);
        this.mColorGradientPaint.setAntiAlias(true);
        this.mEdgeGradientPaint.setStyle(Paint.Style.STROKE);
        this.mLineWidth = getResources().getDisplayMetrics().densityDpi >= 240 ? 2 : 1;
        this.mEdgeGradientPaint.setStrokeWidth(this.mLineWidth);
        this.mEdgeGradientPaint.setAntiAlias(true);
    }

    public void setRatios(float f, float f2, float f3) {
        this.mRedRatio = f;
        this.mYellowRatio = f2;
        this.mGreenRatio = f3;
        invalidate();
    }

    public void setShowingGreen(boolean z) {
        if (this.mShowingGreen != z) {
            this.mShowingGreen = z;
            updateIndicator();
            invalidate();
        }
    }

    private void updateIndicator() {
        int paddingTop = getPaddingTop() - getPaddingBottom();
        if (paddingTop < 0) {
            paddingTop = 0;
        }
        this.mRect.top = paddingTop;
        this.mRect.bottom = getHeight();
        if (this.mShowingGreen) {
            this.mColorGradientPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, paddingTop - 2, 10535072, RIGHT_COLOR, Shader.TileMode.CLAMP));
        } else {
            this.mColorGradientPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, paddingTop - 2, 10526880, -6250336, Shader.TileMode.CLAMP));
        }
        this.mEdgeGradientPaint.setShader(new LinearGradient(0.0f, 0.0f, 0.0f, paddingTop / 2, 10526880, -6250336, Shader.TileMode.CLAMP));
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateIndicator();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        super.onDraw(canvas);
        int width = getWidth();
        float f = width;
        int i3 = ((int) (this.mRedRatio * f)) + 0;
        int i4 = ((int) (this.mYellowRatio * f)) + i3;
        int i5 = ((int) (f * this.mGreenRatio)) + i4;
        if (this.mShowingGreen) {
            i2 = i5;
            i = i4;
        } else {
            i = i3;
            i2 = i4;
        }
        if (this.mLastInterestingLeft != i || this.mLastInterestingRight != i2) {
            this.mColorPath.reset();
            this.mEdgePath.reset();
            if (i < i2) {
                int i6 = this.mRect.top;
                float f2 = i;
                this.mColorPath.moveTo(f2, this.mRect.top);
                float f3 = i6;
                this.mColorPath.cubicTo(f2, 0.0f, -2.0f, f3, -2.0f, 0.0f);
                float f4 = (width + 2) - 1;
                this.mColorPath.lineTo(f4, 0.0f);
                float f5 = i2;
                this.mColorPath.cubicTo(f4, f3, f5, 0.0f, f5, this.mRect.top);
                this.mColorPath.close();
                float f6 = this.mLineWidth + 0.5f;
                float f7 = (-2.0f) + f6;
                this.mEdgePath.moveTo(f7, 0.0f);
                float f8 = f2 + f6;
                this.mEdgePath.cubicTo(f7, f3, f8, 0.0f, f8, this.mRect.top);
                float f9 = f4 - f6;
                this.mEdgePath.moveTo(f9, 0.0f);
                float f10 = f5 - f6;
                this.mEdgePath.cubicTo(f9, f3, f10, 0.0f, f10, this.mRect.top);
            }
            this.mLastInterestingLeft = i;
            this.mLastInterestingRight = i2;
        }
        if (!this.mEdgePath.isEmpty()) {
            canvas.drawPath(this.mEdgePath, this.mEdgeGradientPaint);
            canvas.drawPath(this.mColorPath, this.mColorGradientPaint);
        }
        if (i3 > 0) {
            this.mRect.left = 0;
            this.mRect.right = i3;
            this.mPaint.setColor(-6250336);
            canvas.drawRect(this.mRect, this.mPaint);
            width -= i3 + 0;
        } else {
            i3 = 0;
        }
        if (i3 < i4) {
            this.mRect.left = i3;
            this.mRect.right = i4;
            this.mPaint.setColor(-6250336);
            canvas.drawRect(this.mRect, this.mPaint);
            width -= i4 - i3;
            i3 = i4;
        }
        int i7 = width + i3;
        if (i3 < i7) {
            this.mRect.left = i3;
            this.mRect.right = i7;
            this.mPaint.setColor(RIGHT_COLOR);
            canvas.drawRect(this.mRect, this.mPaint);
        }
    }
}
