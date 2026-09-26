package android.support.v17.leanback.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ScaleFrameLayout extends FrameLayout {
    private static final int DEFAULT_CHILD_GRAVITY = 8388659;
    private float mChildScale;
    private float mLayoutScaleX;
    private float mLayoutScaleY;

    public ScaleFrameLayout(Context context) {
        this(context, null);
    }

    public ScaleFrameLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScaleFrameLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLayoutScaleX = 1.0f;
        this.mLayoutScaleY = 1.0f;
        this.mChildScale = 1.0f;
    }

    public void setLayoutScaleX(float f) {
        if (f != this.mLayoutScaleX) {
            this.mLayoutScaleX = f;
            requestLayout();
        }
    }

    public void setLayoutScaleY(float f) {
        if (f != this.mLayoutScaleY) {
            this.mLayoutScaleY = f;
            requestLayout();
        }
    }

    public void setChildScale(float f) {
        if (this.mChildScale != f) {
            this.mChildScale = f;
            for (int i = 0; i < getChildCount(); i++) {
                getChildAt(i).setScaleX(f);
                getChildAt(i).setScaleY(f);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        view.setScaleX(this.mChildScale);
        view.setScaleY(this.mChildScale);
    }

    @Override // android.view.ViewGroup
    protected boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        boolean zAddViewInLayout = super.addViewInLayout(view, i, layoutParams, z);
        if (zAddViewInLayout) {
            view.setScaleX(this.mChildScale);
            view.setScaleY(this.mChildScale);
        }
        return zAddViewInLayout;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float pivotX;
        int paddingLeft;
        int paddingRight;
        int paddingTop;
        int paddingBottom;
        int i5;
        int i6;
        ScaleFrameLayout scaleFrameLayout = this;
        int childCount = getChildCount();
        int layoutDirection = getLayoutDirection();
        if (layoutDirection == 1) {
            pivotX = getWidth() - getPivotX();
        } else {
            pivotX = getPivotX();
        }
        if (scaleFrameLayout.mLayoutScaleX != 1.0f) {
            paddingLeft = getPaddingLeft() + ((int) ((pivotX - (pivotX / scaleFrameLayout.mLayoutScaleX)) + 0.5f));
            paddingRight = ((int) (((((i3 - i) - pivotX) / scaleFrameLayout.mLayoutScaleX) + pivotX) + 0.5f)) - getPaddingRight();
        } else {
            paddingLeft = getPaddingLeft();
            paddingRight = (i3 - i) - getPaddingRight();
        }
        float pivotY = getPivotY();
        if (scaleFrameLayout.mLayoutScaleY != 1.0f) {
            paddingTop = getPaddingTop() + ((int) ((pivotY - (pivotY / scaleFrameLayout.mLayoutScaleY)) + 0.5f));
            paddingBottom = ((int) (((((i4 - i2) - pivotY) / scaleFrameLayout.mLayoutScaleY) + pivotY) + 0.5f)) - getPaddingBottom();
        } else {
            paddingTop = getPaddingTop();
            paddingBottom = (i4 - i2) - getPaddingBottom();
        }
        int i7 = 0;
        while (i7 < childCount) {
            View childAt = scaleFrameLayout.getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i8 = layoutParams.gravity;
                if (i8 == -1) {
                    i8 = DEFAULT_CHILD_GRAVITY;
                }
                int absoluteGravity = Gravity.getAbsoluteGravity(i8, layoutDirection);
                int i9 = i8 & 112;
                int i10 = absoluteGravity & 7;
                if (i10 == 1) {
                    i5 = (((((paddingRight - paddingLeft) - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (i10 == 5) {
                    i5 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                } else {
                    i5 = layoutParams.leftMargin + paddingLeft;
                }
                if (i9 == 16) {
                    i6 = (((((paddingBottom - paddingTop) - measuredHeight) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                } else if (i9 != 48 && i9 == 80) {
                    i6 = (paddingBottom - measuredHeight) - layoutParams.bottomMargin;
                } else {
                    i6 = layoutParams.topMargin + paddingTop;
                }
                childAt.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
                childAt.setPivotX(pivotX - i5);
                childAt.setPivotY(pivotY - i6);
            }
            i7++;
            scaleFrameLayout = this;
        }
    }

    private static int getScaledMeasureSpec(int i, float f) {
        return f == 1.0f ? i : View.MeasureSpec.makeMeasureSpec((int) ((View.MeasureSpec.getSize(i) / f) + 0.5f), View.MeasureSpec.getMode(i));
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mLayoutScaleX != 1.0f || this.mLayoutScaleY != 1.0f) {
            super.onMeasure(getScaledMeasureSpec(i, this.mLayoutScaleX), getScaledMeasureSpec(i2, this.mLayoutScaleY));
            setMeasuredDimension((int) ((getMeasuredWidth() * this.mLayoutScaleX) + 0.5f), (int) ((getMeasuredHeight() * this.mLayoutScaleY) + 0.5f));
        } else {
            super.onMeasure(i, i2);
        }
    }

    @Override // android.view.View
    public void setForeground(Drawable drawable) {
        throw new UnsupportedOperationException();
    }
}
