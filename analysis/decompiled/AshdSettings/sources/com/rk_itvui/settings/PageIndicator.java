package com.rk_itvui.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class PageIndicator extends View {
    private static final int MIN_DOT_COUNT = 1;
    public static final int NO_ACTIVE_DOT = -1;
    private static Rect sInRect = new Rect();
    private static Rect sOutRect = new Rect();
    private int mActiveDot;
    private int mDotCount;
    private Drawable mDotDrawable;
    private int mDotSpacing;
    private int mDotType;
    private int[] mExtraState;
    private int mGravity;
    private boolean mInitializing;

    public interface DotType {
        public static final int MULTIPLE = 1;
        public static final int SINGLE = 0;
    }

    public PageIndicator(Context context) {
        this(context, null);
    }

    public PageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.ashd.settings.R.attr.gdPageIndicatorStyle);
    }

    public PageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initPageIndicator();
        this.mInitializing = true;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PageIndicator, i, 0);
        setDotCount(typedArrayObtainStyledAttributes.getInt(1, this.mDotCount));
        setActiveDot(typedArrayObtainStyledAttributes.getInt(0, this.mActiveDot));
        Log.d("pageindicator", "drawable:" + typedArrayObtainStyledAttributes.getDrawable(2));
        setDotDrawable(typedArrayObtainStyledAttributes.getDrawable(2));
        setDotSpacing(typedArrayObtainStyledAttributes.getDimensionPixelSize(3, this.mDotSpacing));
        Log.d("pageindicator", "gravity:" + typedArrayObtainStyledAttributes.getInt(5, this.mGravity));
        setGravity(typedArrayObtainStyledAttributes.getInt(5, this.mGravity));
        Log.d("pageindicator", "dotType:" + typedArrayObtainStyledAttributes.getInt(4, this.mDotType));
        setDotType(typedArrayObtainStyledAttributes.getInt(4, this.mDotType));
        typedArrayObtainStyledAttributes.recycle();
        this.mInitializing = false;
    }

    private void initPageIndicator() {
        this.mDotCount = 1;
        this.mGravity = 17;
        this.mActiveDot = 0;
        this.mDotSpacing = 0;
        this.mDotType = 0;
        this.mExtraState = onCreateDrawableState(1);
        mergeDrawableStates(this.mExtraState, SELECTED_STATE_SET);
    }

    public int getDotCount() {
        return this.mDotCount;
    }

    public void setDotCount(int i) {
        if (i < 1) {
            i = 1;
        }
        if (this.mDotCount != i) {
            this.mDotCount = i;
            requestLayout();
            invalidate();
        }
    }

    public int getActiveDot() {
        return this.mActiveDot;
    }

    public void setActiveDot(int i) {
        int i2 = -1;
        if (i < 0) {
            i = -1;
        }
        switch (this.mDotType) {
            case 0:
                if (i <= this.mDotCount - 1) {
                    i2 = i;
                }
                break;
            case 1:
                if (i <= this.mDotCount) {
                    i2 = i;
                }
                break;
            default:
                i2 = i;
                break;
        }
        this.mActiveDot = i2;
        invalidate();
    }

    public Drawable getDotDrawable() {
        return this.mDotDrawable;
    }

    public void setDotDrawable(Drawable drawable) {
        if (drawable != this.mDotDrawable) {
            if (this.mDotDrawable != null) {
                this.mDotDrawable.setCallback(null);
            }
            this.mDotDrawable = drawable;
            if (drawable != null) {
                if (drawable.getIntrinsicHeight() == -1 || drawable.getIntrinsicWidth() == -1) {
                    return;
                }
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
            }
            requestLayout();
            invalidate();
        }
    }

    public int getDotSpacing() {
        return this.mDotSpacing;
    }

    public void setDotSpacing(int i) {
        if (i != this.mDotSpacing) {
            this.mDotSpacing = i;
            requestLayout();
            invalidate();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            this.mGravity = i;
            invalidate();
        }
    }

    public int getDotType() {
        return this.mDotType;
    }

    public void setDotType(int i) {
        if ((i == 0 || i == 1) && this.mDotType != i) {
            this.mDotType = i;
            invalidate();
        }
    }

    @Override // android.view.View
    public void requestLayout() {
        if (this.mInitializing) {
            return;
        }
        super.requestLayout();
    }

    @Override // android.view.View
    public void invalidate() {
        if (this.mInitializing) {
            return;
        }
        super.invalidate();
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mDotDrawable;
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        this.mExtraState = onCreateDrawableState(1);
        mergeDrawableStates(this.mExtraState, SELECTED_STATE_SET);
        invalidate();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int intrinsicHeight;
        Drawable drawable = this.mDotDrawable;
        int intrinsicWidth = 0;
        if (drawable != null) {
            intrinsicWidth = (this.mDotCount * (drawable.getIntrinsicWidth() + this.mDotSpacing)) - this.mDotSpacing;
            intrinsicHeight = drawable.getIntrinsicHeight();
        } else {
            intrinsicHeight = 0;
        }
        setMeasuredDimension(resolveSize(intrinsicWidth + getPaddingRight() + getPaddingLeft(), i), resolveSize(intrinsicHeight + getPaddingBottom() + getPaddingTop(), i2));
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        Drawable drawable = this.mDotDrawable;
        if (drawable != null) {
            int i = this.mDotType == 0 ? this.mDotCount : this.mActiveDot;
            if (i <= 0) {
                return;
            }
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int iMax = Math.max(0, ((drawable.getIntrinsicWidth() + this.mDotSpacing) * i) - this.mDotSpacing);
            sInRect.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
            Gravity.apply(this.mGravity, iMax, intrinsicHeight, sInRect, sOutRect);
            canvas.save();
            canvas.translate(sOutRect.left, sOutRect.top);
            for (int i2 = 0; i2 < i; i2++) {
                if (drawable.isStateful()) {
                    int[] drawableState = getDrawableState();
                    if (this.mDotType == 1 || i2 == this.mActiveDot) {
                        drawableState = this.mExtraState;
                    }
                    drawable.setCallback(null);
                    drawable.setState(drawableState);
                    drawable.setCallback(this);
                }
                drawable.draw(canvas);
                canvas.translate(this.mDotSpacing + drawable.getIntrinsicWidth(), 0.0f);
            }
            canvas.restore();
        }
    }

    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rk_itvui.settings.PageIndicator.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int activeDot;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.activeDot = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.activeDot);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.activeDot = this.mActiveDot;
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.mActiveDot = savedState.activeDot;
    }
}
