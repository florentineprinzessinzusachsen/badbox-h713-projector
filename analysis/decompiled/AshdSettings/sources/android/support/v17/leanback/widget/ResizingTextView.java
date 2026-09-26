package android.support.v17.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v17.leanback.R;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
class ResizingTextView extends TextView {
    public static final int TRIGGER_MAX_LINES = 1;
    private float mDefaultLineSpacingExtra;
    private int mDefaultPaddingBottom;
    private int mDefaultPaddingTop;
    private int mDefaultTextSize;
    private boolean mDefaultsInitialized;
    private boolean mIsResized;
    private boolean mMaintainLineSpacing;
    private int mResizedPaddingAdjustmentBottom;
    private int mResizedPaddingAdjustmentTop;
    private int mResizedTextSize;
    private int mTriggerConditions;

    public ResizingTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i);
        this.mIsResized = false;
        this.mDefaultsInitialized = false;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbResizingTextView, i, i2);
        try {
            this.mTriggerConditions = typedArrayObtainStyledAttributes.getInt(R.styleable.lbResizingTextView_resizeTrigger, 1);
            this.mResizedTextSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.lbResizingTextView_resizedTextSize, -1);
            this.mMaintainLineSpacing = typedArrayObtainStyledAttributes.getBoolean(R.styleable.lbResizingTextView_maintainLineSpacing, false);
            this.mResizedPaddingAdjustmentTop = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.lbResizingTextView_resizedPaddingAdjustmentTop, 0);
            this.mResizedPaddingAdjustmentBottom = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.lbResizingTextView_resizedPaddingAdjustmentBottom, 0);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public ResizingTextView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public ResizingTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, android.R.attr.textViewStyle);
    }

    public ResizingTextView(Context context) {
        this(context, null);
    }

    public int getTriggerConditions() {
        return this.mTriggerConditions;
    }

    public void setTriggerConditions(int i) {
        if (this.mTriggerConditions != i) {
            this.mTriggerConditions = i;
            requestLayout();
        }
    }

    public int getResizedTextSize() {
        return this.mResizedTextSize;
    }

    public void setResizedTextSize(int i) {
        if (this.mResizedTextSize != i) {
            this.mResizedTextSize = i;
            resizeParamsChanged();
        }
    }

    public boolean getMaintainLineSpacing() {
        return this.mMaintainLineSpacing;
    }

    public void setMaintainLineSpacing(boolean z) {
        if (this.mMaintainLineSpacing != z) {
            this.mMaintainLineSpacing = z;
            resizeParamsChanged();
        }
    }

    public int getResizedPaddingAdjustmentTop() {
        return this.mResizedPaddingAdjustmentTop;
    }

    public void setResizedPaddingAdjustmentTop(int i) {
        if (this.mResizedPaddingAdjustmentTop != i) {
            this.mResizedPaddingAdjustmentTop = i;
            resizeParamsChanged();
        }
    }

    public int getResizedPaddingAdjustmentBottom() {
        return this.mResizedPaddingAdjustmentBottom;
    }

    public void setResizedPaddingAdjustmentBottom(int i) {
        if (this.mResizedPaddingAdjustmentBottom != i) {
            this.mResizedPaddingAdjustmentBottom = i;
            resizeParamsChanged();
        }
    }

    private void resizeParamsChanged() {
        if (this.mIsResized) {
            requestLayout();
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0053  */
    /* JADX WARN: Code duplicated, block: B:46:0x00db A[PHI: r2
      0x00db: PHI (r2v7 boolean) = (r2v2 boolean), (r2v9 boolean) binds: [B:44:0x00d8, B:29:0x009d] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        boolean z;
        boolean z2 = true;
        if (!this.mDefaultsInitialized) {
            this.mDefaultTextSize = (int) getTextSize();
            this.mDefaultLineSpacingExtra = getLineSpacingExtra();
            this.mDefaultPaddingTop = getPaddingTop();
            this.mDefaultPaddingBottom = getPaddingBottom();
            this.mDefaultsInitialized = true;
        }
        boolean z3 = false;
        setTextSize(0, this.mDefaultTextSize);
        setLineSpacing(this.mDefaultLineSpacingExtra, getLineSpacingMultiplier());
        setPaddingTopAndBottom(this.mDefaultPaddingTop, this.mDefaultPaddingBottom);
        super.onMeasure(i, i2);
        Layout layout = getLayout();
        if (layout == null || (this.mTriggerConditions & 1) <= 0) {
            z = false;
        } else {
            int lineCount = layout.getLineCount();
            int maxLines = getMaxLines();
            if (maxLines <= 1 || lineCount != maxLines) {
                z = false;
            } else {
                z = true;
            }
        }
        int textSize = (int) getTextSize();
        if (z) {
            if (this.mResizedTextSize != -1 && textSize != this.mResizedTextSize) {
                setTextSize(0, this.mResizedTextSize);
                z3 = true;
            }
            float f = (this.mDefaultLineSpacingExtra + this.mDefaultTextSize) - this.mResizedTextSize;
            if (this.mMaintainLineSpacing && getLineSpacingExtra() != f) {
                setLineSpacing(f, getLineSpacingMultiplier());
                z3 = true;
            }
            int i3 = this.mDefaultPaddingTop + this.mResizedPaddingAdjustmentTop;
            int i4 = this.mDefaultPaddingBottom + this.mResizedPaddingAdjustmentBottom;
            if (getPaddingTop() == i3 && getPaddingBottom() == i4) {
                z2 = z3;
            } else {
                setPaddingTopAndBottom(i3, i4);
            }
        } else {
            if (this.mResizedTextSize != -1 && textSize != this.mDefaultTextSize) {
                setTextSize(0, this.mDefaultTextSize);
                z3 = true;
            }
            if (this.mMaintainLineSpacing && getLineSpacingExtra() != this.mDefaultLineSpacingExtra) {
                setLineSpacing(this.mDefaultLineSpacingExtra, getLineSpacingMultiplier());
                z3 = true;
            }
            if (getPaddingTop() == this.mDefaultPaddingTop && getPaddingBottom() == this.mDefaultPaddingBottom) {
                z2 = z3;
            } else {
                setPaddingTopAndBottom(this.mDefaultPaddingTop, this.mDefaultPaddingBottom);
            }
        }
        this.mIsResized = z;
        if (z2) {
            super.onMeasure(i, i2);
        }
    }

    private void setPaddingTopAndBottom(int i, int i2) {
        if (isPaddingRelative()) {
            setPaddingRelative(getPaddingStart(), i, getPaddingEnd(), i2);
        } else {
            setPadding(getPaddingLeft(), i, getPaddingRight(), i2);
        }
    }
}
