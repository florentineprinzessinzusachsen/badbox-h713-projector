package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.InputDeviceCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface DividerMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public @interface OrientationMode {
    }

    int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    int getLocationOffset(View view) {
        return 0;
    }

    int getNextLocationOffset(View view) {
        return 0;
    }

    int measureNullChild(int i) {
        return 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray tintTypedArrayObtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = tintTypedArrayObtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = tintTypedArrayObtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = tintTypedArrayObtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(tintTypedArrayObtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = tintTypedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = tintTypedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        tintTypedArrayObtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable == this.mDivider) {
            return;
        }
        this.mDivider = drawable;
        if (drawable != null) {
            this.mDividerWidth = drawable.getIntrinsicWidth();
            this.mDividerHeight = drawable.getIntrinsicHeight();
        } else {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        setWillNotDraw(drawable == null);
        requestLayout();
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mDivider == null) {
            return;
        }
        if (this.mOrientation == 1) {
            drawDividersVertical(canvas);
        } else {
            drawDividersHorizontal(canvas);
        }
    }

    void drawDividersVertical(Canvas canvas) {
        int bottom;
        int virtualChildCount = getVirtualChildCount();
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                bottom = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                bottom = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, bottom);
        }
    }

    void drawDividersHorizontal(Canvas canvas) {
        int right;
        int left;
        int virtualChildCount = getVirtualChildCount();
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i = 0; i < virtualChildCount; i++) {
            View virtualChildAt = getVirtualChildAt(i);
            if (virtualChildAt != null && virtualChildAt.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (zIsLayoutRtl) {
                    left = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    left = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, left);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (zIsLayoutRtl) {
                    right = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    right = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (zIsLayoutRtl) {
                right = getPaddingLeft();
            } else {
                right = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, right);
        }
    }

    void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    @Override // android.view.View
    public int getBaseline() {
        int i;
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        if (getChildCount() <= this.mBaselineAlignedChildIndex) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View childAt = getChildAt(this.mBaselineAlignedChildIndex);
        int baseline = childAt.getBaseline();
        if (baseline == -1) {
            if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            }
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
        }
        int bottom = this.mBaselineChildTop;
        if (this.mOrientation == 1 && (i = this.mGravity & 112) != 48) {
            if (i == 16) {
                bottom += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
            } else if (i == 80) {
                bottom = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
            }
        }
        return bottom + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = i;
    }

    View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    protected boolean hasDividerBeforeChildAt(int i) {
        if (i == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if (i == getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if ((this.mShowDividers & 2) == 0) {
            return false;
        }
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (getChildAt(i2).getVisibility() != 8) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:149:0x0337  */
    /* JADX WARN: Code duplicated, block: B:155:0x0346  */
    /* JADX WARN: Code duplicated, block: B:59:0x0168  */
    /* JADX WARN: Code duplicated, block: B:62:0x016f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:64:0x0174  */
    /* JADX WARN: Code duplicated, block: B:66:0x017b  */
    /* JADX WARN: Code duplicated, block: B:68:0x017f  */
    void measureVertical(int i, int i2) {
        int i3;
        int i4;
        int iMax;
        int i5;
        float f;
        int i6;
        int i7;
        boolean z;
        boolean z2;
        int i8;
        int i9;
        int i10;
        int i11;
        View view;
        int i12;
        int i13;
        int i14;
        LayoutParams layoutParams;
        int i15;
        int iMax2;
        int i16;
        boolean z3;
        int i17;
        int measuredWidth;
        boolean z4;
        int i18 = 0;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i19 = this.mBaselineAlignedChildIndex;
        boolean z5 = this.mUseLargestChild;
        int i20 = 0;
        int iMax3 = 0;
        int childrenSkipCount = 0;
        boolean z6 = false;
        boolean z7 = false;
        int i21 = 0;
        float f2 = 0.0f;
        boolean z8 = true;
        int i22 = Integer.MIN_VALUE;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount);
            } else {
                int i23 = i20;
                if (virtualChildAt.getVisibility() == 8) {
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                    i20 = i23;
                } else {
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        this.mTotalLength += this.mDividerHeight;
                    }
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt.getLayoutParams();
                    float f3 = f2 + layoutParams2.weight;
                    if (mode2 == 1073741824 && layoutParams2.height == 0 && layoutParams2.weight > 0.0f) {
                        int i24 = this.mTotalLength;
                        iMax2 = i22;
                        this.mTotalLength = Math.max(i24, layoutParams2.topMargin + i24 + layoutParams2.bottomMargin);
                        i12 = iMax3;
                        view = virtualChildAt;
                        layoutParams = layoutParams2;
                        i15 = i18;
                        virtualChildCount = virtualChildCount;
                        mode2 = mode2;
                        z6 = true;
                        i13 = i21;
                        i11 = i23;
                        i14 = childrenSkipCount;
                    } else {
                        i9 = i22;
                        if (layoutParams2.height != 0 || layoutParams2.weight <= 0.0f) {
                            i10 = Integer.MIN_VALUE;
                        } else {
                            layoutParams2.height = -2;
                            i10 = 0;
                        }
                        mode2 = mode2;
                        i11 = i23;
                        int i25 = i10;
                        virtualChildCount = virtualChildCount;
                        view = virtualChildAt;
                        i12 = iMax3;
                        i13 = i21;
                        i14 = childrenSkipCount;
                        layoutParams = layoutParams2;
                        i15 = i18;
                        measureChildBeforeLayout(virtualChildAt, childrenSkipCount, i, 0, i2, f3 == 0.0f ? this.mTotalLength : 0);
                        if (i25 != Integer.MIN_VALUE) {
                            layoutParams.height = i25;
                        }
                        int measuredHeight = view.getMeasuredHeight();
                        int i26 = this.mTotalLength;
                        this.mTotalLength = Math.max(i26, i26 + measuredHeight + layoutParams.topMargin + layoutParams.bottomMargin + getNextLocationOffset(view));
                        if (z5) {
                            iMax2 = Math.max(measuredHeight, i9);
                        }
                    }
                    if (i19 >= 0) {
                        iMax2 = i9;
                        if (i19 == i14 + 1) {
                            this.mBaselineChildTop = this.mTotalLength;
                        }
                    }
                    if (i14 < i19 && layoutParams.weight > 0.0f) {
                        throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.");
                    }
                    if (mode != 1073741824) {
                        i16 = -1;
                        if (layoutParams.width == -1) {
                            z3 = true;
                            z7 = true;
                        }
                        i17 = layoutParams.leftMargin + layoutParams.rightMargin;
                        measuredWidth = view.getMeasuredWidth() + i17;
                        int iMax4 = Math.max(i11, measuredWidth);
                        int iCombineMeasuredStates = ViewUtils.combineMeasuredStates(i15, ViewCompat.getMeasuredState(view));
                        if (z8 || layoutParams.width != i16) {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        if (layoutParams.weight > 0.0f) {
                            if (!z3) {
                                i17 = measuredWidth;
                            }
                            iMax3 = Math.max(i12, i17);
                        } else {
                            int i27 = i12;
                            if (z3) {
                                measuredWidth = i17;
                            }
                            int iMax5 = Math.max(i13, measuredWidth);
                            iMax3 = i27;
                            i13 = iMax5;
                        }
                        z8 = z4;
                        i20 = iMax4;
                        i18 = iCombineMeasuredStates;
                        i22 = iMax2;
                        i21 = i13;
                        childrenSkipCount = getChildrenSkipCount(view, i14) + i14;
                        f2 = f3;
                    } else {
                        i16 = -1;
                    }
                    z3 = false;
                    i17 = layoutParams.leftMargin + layoutParams.rightMargin;
                    measuredWidth = view.getMeasuredWidth() + i17;
                    int iMax6 = Math.max(i11, measuredWidth);
                    int iCombineMeasuredStates2 = ViewUtils.combineMeasuredStates(i15, ViewCompat.getMeasuredState(view));
                    if (z8) {
                        z4 = false;
                    } else {
                        z4 = false;
                    }
                    if (layoutParams.weight > 0.0f) {
                        if (!z3) {
                            i17 = measuredWidth;
                        }
                        iMax3 = Math.max(i12, i17);
                    } else {
                        int i28 = i12;
                        if (z3) {
                            measuredWidth = i17;
                        }
                        int iMax7 = Math.max(i13, measuredWidth);
                        iMax3 = i28;
                        i13 = iMax7;
                    }
                    z8 = z4;
                    i20 = iMax6;
                    i18 = iCombineMeasuredStates2;
                    i22 = iMax2;
                    i21 = i13;
                    childrenSkipCount = getChildrenSkipCount(view, i14) + i14;
                    f2 = f3;
                }
                childrenSkipCount++;
                i18 = i18;
                mode2 = mode2;
                virtualChildCount = virtualChildCount;
            }
            childrenSkipCount++;
            i18 = i18;
            mode2 = mode2;
            virtualChildCount = virtualChildCount;
        }
        int i29 = i22;
        int i30 = iMax3;
        int i31 = i18;
        int i32 = virtualChildCount;
        int i33 = mode2;
        int iMax8 = i21;
        int iMax9 = i20;
        if (this.mTotalLength > 0) {
            i3 = i32;
            if (hasDividerBeforeChildAt(i3)) {
                this.mTotalLength += this.mDividerHeight;
            }
        } else {
            i3 = i32;
        }
        if (z5) {
            i4 = i33;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                this.mTotalLength = 0;
                int childrenSkipCount2 = 0;
                while (childrenSkipCount2 < i3) {
                    View virtualChildAt2 = getVirtualChildAt(childrenSkipCount2);
                    if (virtualChildAt2 == null) {
                        this.mTotalLength += measureNullChild(childrenSkipCount2);
                    } else if (virtualChildAt2.getVisibility() == 8) {
                        childrenSkipCount2 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount2);
                    } else {
                        LayoutParams layoutParams3 = (LayoutParams) virtualChildAt2.getLayoutParams();
                        int i34 = this.mTotalLength;
                        this.mTotalLength = Math.max(i34, i34 + i29 + layoutParams3.topMargin + layoutParams3.bottomMargin + getNextLocationOffset(virtualChildAt2));
                    }
                    childrenSkipCount2++;
                }
            }
        } else {
            i4 = i33;
        }
        this.mTotalLength += getPaddingTop() + getPaddingBottom();
        int iResolveSizeAndState = ViewCompat.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumHeight()), i2, 0);
        int i35 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
        if (z6 || (i35 != 0 && f2 > 0.0f)) {
            if (this.mWeightSum > 0.0f) {
                f2 = this.mWeightSum;
            }
            this.mTotalLength = 0;
            float f4 = f2;
            int i36 = 0;
            int iCombineMeasuredStates3 = i31;
            int i37 = i35;
            while (i36 < i3) {
                View virtualChildAt3 = getVirtualChildAt(i36);
                if (virtualChildAt3.getVisibility() == 8) {
                    f = f4;
                } else {
                    LayoutParams layoutParams4 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f5 = layoutParams4.weight;
                    if (f5 > 0.0f) {
                        int i38 = (int) ((i37 * f5) / f4);
                        i5 = i37 - i38;
                        f = f4 - f5;
                        int childMeasureSpec = getChildMeasureSpec(i, getPaddingLeft() + getPaddingRight() + layoutParams4.leftMargin + layoutParams4.rightMargin, layoutParams4.width);
                        if (layoutParams4.height == 0) {
                            i8 = 1073741824;
                            if (i4 == 1073741824) {
                                if (i38 <= 0) {
                                    i38 = 0;
                                }
                                virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(i38, 1073741824));
                            }
                            iCombineMeasuredStates3 = ViewUtils.combineMeasuredStates(iCombineMeasuredStates3, ViewCompat.getMeasuredState(virtualChildAt3) & InputDeviceCompat.SOURCE_ANY);
                        } else {
                            i8 = 1073741824;
                        }
                        int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i38;
                        if (measuredHeight2 < 0) {
                            measuredHeight2 = 0;
                        }
                        virtualChildAt3.measure(childMeasureSpec, View.MeasureSpec.makeMeasureSpec(measuredHeight2, i8));
                        iCombineMeasuredStates3 = ViewUtils.combineMeasuredStates(iCombineMeasuredStates3, ViewCompat.getMeasuredState(virtualChildAt3) & InputDeviceCompat.SOURCE_ANY);
                    } else {
                        i5 = i37;
                        f = f4;
                    }
                    int i39 = layoutParams4.leftMargin + layoutParams4.rightMargin;
                    int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i39;
                    iMax9 = Math.max(iMax9, measuredWidth2);
                    if (mode != 1073741824) {
                        i6 = i39;
                        i7 = -1;
                        z = layoutParams4.width == -1;
                        if (z) {
                            measuredWidth2 = i6;
                        }
                        iMax8 = Math.max(iMax8, measuredWidth2);
                        if (z8 || layoutParams4.width != i7) {
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        int i40 = this.mTotalLength;
                        this.mTotalLength = Math.max(i40, i40 + virtualChildAt3.getMeasuredHeight() + layoutParams4.topMargin + layoutParams4.bottomMargin + getNextLocationOffset(virtualChildAt3));
                        z8 = z2;
                        i37 = i5;
                    } else {
                        i6 = i39;
                        i7 = -1;
                    }
                    if (z) {
                        measuredWidth2 = i6;
                    }
                    iMax8 = Math.max(iMax8, measuredWidth2);
                    if (z8) {
                        z2 = false;
                    } else {
                        z2 = false;
                    }
                    int i41 = this.mTotalLength;
                    this.mTotalLength = Math.max(i41, i41 + virtualChildAt3.getMeasuredHeight() + layoutParams4.topMargin + layoutParams4.bottomMargin + getNextLocationOffset(virtualChildAt3));
                    z8 = z2;
                    i37 = i5;
                }
                i36++;
                f4 = f;
            }
            this.mTotalLength += getPaddingTop() + getPaddingBottom();
            iMax = iMax8;
            i31 = iCombineMeasuredStates3;
        } else {
            iMax = Math.max(iMax8, i30);
            if (z5 && i4 != 1073741824) {
                for (int i42 = 0; i42 < i3; i42++) {
                    View virtualChildAt4 = getVirtualChildAt(i42);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(i29, 1073741824));
                    }
                }
            }
        }
        if (!z8 && mode != 1073741824) {
            iMax9 = iMax;
        }
        setMeasuredDimension(ViewCompat.resolveSizeAndState(Math.max(iMax9 + getPaddingLeft() + getPaddingRight(), getSuggestedMinimumWidth()), i, i31), iResolveSizeAndState);
        if (z7) {
            forceUniformWidth(i3, i2);
        }
    }

    private void forceUniformWidth(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, iMakeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:202:0x046a  */
    /* JADX WARN: Code duplicated, block: B:60:0x017a  */
    /* JADX WARN: Code duplicated, block: B:67:0x019c  */
    /* JADX WARN: Code duplicated, block: B:68:0x019f  */
    /* JADX WARN: Code duplicated, block: B:75:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:78:0x01d2 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:83:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:85:0x01e6  */
    void measureHorizontal(int i, int i2) {
        int[] iArr;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int baseline;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z;
        boolean z2;
        LayoutParams layoutParams;
        int i13;
        View view;
        int i14;
        boolean z3;
        int i15;
        int measuredHeight;
        boolean z4;
        int iMax;
        int childrenSkipCount;
        int baseline2;
        int i16;
        this.mTotalLength = 0;
        int virtualChildCount = getVirtualChildCount();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] iArr2 = this.mMaxAscent;
        int[] iArr3 = this.mMaxDescent;
        iArr2[3] = -1;
        iArr2[2] = -1;
        iArr2[1] = -1;
        iArr2[0] = -1;
        iArr3[3] = -1;
        iArr3[2] = -1;
        iArr3[1] = -1;
        iArr3[0] = -1;
        boolean z5 = this.mBaselineAligned;
        boolean z6 = this.mUseLargestChild;
        int i17 = 1073741824;
        boolean z7 = mode == 1073741824;
        int childrenSkipCount2 = 0;
        int i18 = 0;
        boolean z8 = false;
        int iMax2 = 0;
        int i19 = 0;
        int i20 = 0;
        boolean z9 = false;
        boolean z10 = true;
        float f = 0.0f;
        int iMax3 = Integer.MIN_VALUE;
        while (true) {
            iArr = iArr3;
            i3 = 8;
            if (childrenSkipCount2 >= virtualChildCount) {
                break;
            }
            View virtualChildAt = getVirtualChildAt(childrenSkipCount2);
            if (virtualChildAt == null) {
                this.mTotalLength += measureNullChild(childrenSkipCount2);
            } else {
                if (virtualChildAt.getVisibility() == 8) {
                    childrenSkipCount2 += getChildrenSkipCount(virtualChildAt, childrenSkipCount2);
                } else {
                    if (hasDividerBeforeChildAt(childrenSkipCount2)) {
                        this.mTotalLength += this.mDividerWidth;
                    }
                    LayoutParams layoutParams2 = (LayoutParams) virtualChildAt.getLayoutParams();
                    f += layoutParams2.weight;
                    if (mode != i17 || layoutParams2.width != 0 || layoutParams2.weight <= 0.0f) {
                        if (layoutParams2.width != 0 || layoutParams2.weight <= 0.0f) {
                            i11 = Integer.MIN_VALUE;
                        } else {
                            layoutParams2.width = -2;
                            i11 = 0;
                        }
                        i12 = childrenSkipCount2;
                        int i21 = i11;
                        z = z6;
                        z2 = z5;
                        layoutParams = layoutParams2;
                        i13 = mode;
                        view = virtualChildAt;
                        measureChildBeforeLayout(virtualChildAt, i12, i, f == 0.0f ? this.mTotalLength : 0, i2, 0);
                        if (i21 != Integer.MIN_VALUE) {
                            layoutParams.width = i21;
                        }
                        int measuredWidth = view.getMeasuredWidth();
                        if (z7) {
                            this.mTotalLength += layoutParams.leftMargin + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(view);
                        } else {
                            int i22 = this.mTotalLength;
                            this.mTotalLength = Math.max(i22, i22 + measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin + getNextLocationOffset(view));
                        }
                        if (z) {
                            iMax3 = Math.max(measuredWidth, iMax3);
                        }
                    } else {
                        if (z7) {
                            this.mTotalLength += layoutParams2.leftMargin + layoutParams2.rightMargin;
                        } else {
                            int i23 = this.mTotalLength;
                            this.mTotalLength = Math.max(i23, layoutParams2.leftMargin + i23 + layoutParams2.rightMargin);
                        }
                        if (z5) {
                            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                            virtualChildAt.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                            i12 = childrenSkipCount2;
                            z = z6;
                            z2 = z5;
                            layoutParams = layoutParams2;
                            i13 = mode;
                            view = virtualChildAt;
                        } else {
                            i12 = childrenSkipCount2;
                            z = z6;
                            z2 = z5;
                            layoutParams = layoutParams2;
                            i13 = mode;
                            z8 = true;
                            i14 = 1073741824;
                            view = virtualChildAt;
                        }
                        if (mode2 == i14 && layoutParams.height == -1) {
                            z3 = true;
                            z9 = true;
                        } else {
                            z3 = false;
                        }
                        i15 = layoutParams.topMargin + layoutParams.bottomMargin;
                        measuredHeight = view.getMeasuredHeight() + i15;
                        int iCombineMeasuredStates = ViewUtils.combineMeasuredStates(i20, ViewCompat.getMeasuredState(view));
                        if (z2 && (baseline2 = view.getBaseline()) != -1) {
                            if (layoutParams.gravity < 0) {
                                i16 = this.mGravity;
                            } else {
                                i16 = layoutParams.gravity;
                            }
                            int i24 = (((i16 & 112) >> 4) & (-2)) >> 1;
                            iArr2[i24] = Math.max(iArr2[i24], baseline2);
                            iArr[i24] = Math.max(iArr[i24], measuredHeight - baseline2);
                        }
                        int iMax4 = Math.max(i18, measuredHeight);
                        if (z10 || layoutParams.height != -1) {
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        if (layoutParams.weight > 0.0f) {
                            if (!z3) {
                                i15 = measuredHeight;
                            }
                            iMax = Math.max(i19, i15);
                        } else {
                            iMax = i19;
                            if (z3) {
                                measuredHeight = i15;
                            }
                            iMax2 = Math.max(iMax2, measuredHeight);
                        }
                        int i25 = i12;
                        childrenSkipCount = getChildrenSkipCount(view, i25) + i25;
                        i20 = iCombineMeasuredStates;
                        i18 = iMax4;
                        z10 = z4;
                        i19 = iMax;
                    }
                    i14 = 1073741824;
                    if (mode2 == i14) {
                        z3 = false;
                    } else {
                        z3 = false;
                    }
                    i15 = layoutParams.topMargin + layoutParams.bottomMargin;
                    measuredHeight = view.getMeasuredHeight() + i15;
                    int iCombineMeasuredStates2 = ViewUtils.combineMeasuredStates(i20, ViewCompat.getMeasuredState(view));
                    if (z2) {
                        if (layoutParams.gravity < 0) {
                            i16 = this.mGravity;
                        } else {
                            i16 = layoutParams.gravity;
                        }
                        int i26 = (((i16 & 112) >> 4) & (-2)) >> 1;
                        iArr2[i26] = Math.max(iArr2[i26], baseline2);
                        iArr[i26] = Math.max(iArr[i26], measuredHeight - baseline2);
                    }
                    int iMax5 = Math.max(i18, measuredHeight);
                    if (z10) {
                        z4 = false;
                    } else {
                        z4 = false;
                    }
                    if (layoutParams.weight > 0.0f) {
                        if (!z3) {
                            i15 = measuredHeight;
                        }
                        iMax = Math.max(i19, i15);
                    } else {
                        iMax = i19;
                        if (z3) {
                            measuredHeight = i15;
                        }
                        iMax2 = Math.max(iMax2, measuredHeight);
                    }
                    int i27 = i12;
                    childrenSkipCount = getChildrenSkipCount(view, i27) + i27;
                    i20 = iCombineMeasuredStates2;
                    i18 = iMax5;
                    z10 = z4;
                    i19 = iMax;
                }
                i17 = i14;
                childrenSkipCount2 = childrenSkipCount + 1;
                iArr3 = iArr;
                z6 = z;
                z5 = z2;
                mode = i13;
            }
            childrenSkipCount = childrenSkipCount2;
            i14 = i17;
            z = z6;
            z2 = z5;
            i13 = mode;
            i17 = i14;
            childrenSkipCount2 = childrenSkipCount + 1;
            iArr3 = iArr;
            z6 = z;
            z5 = z2;
            mode = i13;
        }
        boolean z11 = z6;
        boolean z12 = z5;
        int i28 = mode;
        int iMax6 = i18;
        int i29 = iMax2;
        int i30 = i19;
        int iCombineMeasuredStates3 = i20;
        if (this.mTotalLength > 0 && hasDividerBeforeChildAt(virtualChildCount)) {
            this.mTotalLength += this.mDividerWidth;
        }
        if (iArr2[1] != -1 || iArr2[0] != -1 || iArr2[2] != -1 || iArr2[3] != -1) {
            iMax6 = Math.max(iMax6, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        }
        if (z11) {
            i4 = i28;
            if (i4 == Integer.MIN_VALUE || i4 == 0) {
                this.mTotalLength = 0;
                int childrenSkipCount3 = 0;
                while (childrenSkipCount3 < virtualChildCount) {
                    View virtualChildAt2 = getVirtualChildAt(childrenSkipCount3);
                    if (virtualChildAt2 == null) {
                        this.mTotalLength += measureNullChild(childrenSkipCount3);
                    } else if (virtualChildAt2.getVisibility() == i3) {
                        childrenSkipCount3 += getChildrenSkipCount(virtualChildAt2, childrenSkipCount3);
                    } else {
                        LayoutParams layoutParams3 = (LayoutParams) virtualChildAt2.getLayoutParams();
                        if (z7) {
                            this.mTotalLength += layoutParams3.leftMargin + iMax3 + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt2);
                        } else {
                            int i31 = this.mTotalLength;
                            i10 = childrenSkipCount3;
                            this.mTotalLength = Math.max(i31, i31 + iMax3 + layoutParams3.leftMargin + layoutParams3.rightMargin + getNextLocationOffset(virtualChildAt2));
                        }
                        childrenSkipCount3 = i10 + 1;
                        i3 = 8;
                    }
                    i10 = childrenSkipCount3;
                    childrenSkipCount3 = i10 + 1;
                    i3 = 8;
                }
            }
        } else {
            i4 = i28;
        }
        this.mTotalLength += getPaddingLeft() + getPaddingRight();
        int iResolveSizeAndState = ViewCompat.resolveSizeAndState(Math.max(this.mTotalLength, getSuggestedMinimumWidth()), i, 0);
        int i32 = (16777215 & iResolveSizeAndState) - this.mTotalLength;
        if (z8 || (i32 != 0 && f > 0.0f)) {
            float f2 = this.mWeightSum > 0.0f ? this.mWeightSum : f;
            iArr2[3] = -1;
            iArr2[2] = -1;
            iArr2[1] = -1;
            iArr2[0] = -1;
            iArr[3] = -1;
            iArr[2] = -1;
            iArr[1] = -1;
            iArr[0] = -1;
            this.mTotalLength = 0;
            i5 = i29;
            int i33 = 0;
            int iMax7 = -1;
            while (i33 < virtualChildCount) {
                View virtualChildAt3 = getVirtualChildAt(i33);
                if (virtualChildAt3 == null || virtualChildAt3.getVisibility() == 8) {
                    virtualChildCount = virtualChildCount;
                } else {
                    LayoutParams layoutParams4 = (LayoutParams) virtualChildAt3.getLayoutParams();
                    float f3 = layoutParams4.weight;
                    if (f3 > 0.0f) {
                        int i34 = (int) ((i32 * f3) / f2);
                        f2 -= f3;
                        i7 = i32 - i34;
                        int childMeasureSpec = getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom() + layoutParams4.topMargin + layoutParams4.bottomMargin, layoutParams4.height);
                        if (layoutParams4.width == 0) {
                            i9 = 1073741824;
                            if (i4 == 1073741824) {
                                if (i34 <= 0) {
                                    i34 = 0;
                                }
                                virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(i34, 1073741824), childMeasureSpec);
                            }
                            iCombineMeasuredStates3 = ViewUtils.combineMeasuredStates(iCombineMeasuredStates3, ViewCompat.getMeasuredState(virtualChildAt3) & (-16777216));
                        } else {
                            i9 = 1073741824;
                        }
                        int measuredWidth2 = virtualChildAt3.getMeasuredWidth() + i34;
                        if (measuredWidth2 < 0) {
                            measuredWidth2 = 0;
                        }
                        virtualChildAt3.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth2, i9), childMeasureSpec);
                        iCombineMeasuredStates3 = ViewUtils.combineMeasuredStates(iCombineMeasuredStates3, ViewCompat.getMeasuredState(virtualChildAt3) & (-16777216));
                    } else {
                        i7 = i32;
                    }
                    if (z7) {
                        this.mTotalLength += virtualChildAt3.getMeasuredWidth() + layoutParams4.leftMargin + layoutParams4.rightMargin + getNextLocationOffset(virtualChildAt3);
                    } else {
                        int i35 = this.mTotalLength;
                        this.mTotalLength = Math.max(i35, virtualChildAt3.getMeasuredWidth() + i35 + layoutParams4.leftMargin + layoutParams4.rightMargin + getNextLocationOffset(virtualChildAt3));
                    }
                    boolean z13 = mode2 != 1073741824 && layoutParams4.height == -1;
                    int i36 = layoutParams4.topMargin + layoutParams4.bottomMargin;
                    int measuredHeight2 = virtualChildAt3.getMeasuredHeight() + i36;
                    iMax7 = Math.max(iMax7, measuredHeight2);
                    if (!z13) {
                        i36 = measuredHeight2;
                    }
                    int iMax8 = Math.max(i5, i36);
                    if (z10) {
                        i8 = -1;
                        boolean z14 = layoutParams4.height == -1;
                        if (!z12 && (baseline = virtualChildAt3.getBaseline()) != i8) {
                            int i37 = ((((layoutParams4.gravity < 0 ? this.mGravity : layoutParams4.gravity) & 112) >> 4) & (-2)) >> 1;
                            iArr2[i37] = Math.max(iArr2[i37], baseline);
                            iArr[i37] = Math.max(iArr[i37], measuredHeight2 - baseline);
                        }
                        i5 = iMax8;
                        z10 = z14;
                        i32 = i7;
                    } else {
                        i8 = -1;
                    }
                    if (!z12) {
                    }
                    i5 = iMax8;
                    z10 = z14;
                    i32 = i7;
                }
                i33++;
                virtualChildCount = virtualChildCount;
            }
            i6 = virtualChildCount;
            this.mTotalLength += getPaddingLeft() + getPaddingRight();
            iMax6 = (iArr2[1] == -1 && iArr2[0] == -1 && iArr2[2] == -1 && iArr2[3] == -1) ? iMax7 : Math.max(iMax7, Math.max(iArr2[3], Math.max(iArr2[0], Math.max(iArr2[1], iArr2[2]))) + Math.max(iArr[3], Math.max(iArr[0], Math.max(iArr[1], iArr[2]))));
        } else {
            int iMax9 = Math.max(i29, i30);
            if (z11 && i4 != 1073741824) {
                for (int i38 = 0; i38 < virtualChildCount; i38++) {
                    View virtualChildAt4 = getVirtualChildAt(i38);
                    if (virtualChildAt4 != null && virtualChildAt4.getVisibility() != 8 && ((LayoutParams) virtualChildAt4.getLayoutParams()).weight > 0.0f) {
                        virtualChildAt4.measure(View.MeasureSpec.makeMeasureSpec(iMax3, 1073741824), View.MeasureSpec.makeMeasureSpec(virtualChildAt4.getMeasuredHeight(), 1073741824));
                    }
                }
            }
            i5 = iMax9;
            i6 = virtualChildCount;
        }
        if (z10 || mode2 == 1073741824) {
            i5 = iMax6;
        }
        setMeasuredDimension(iResolveSizeAndState | ((-16777216) & iCombineMeasuredStates3), ViewCompat.resolveSizeAndState(Math.max(i5 + getPaddingTop() + getPaddingBottom(), getSuggestedMinimumHeight()), i2, iCombineMeasuredStates3 << 16));
        if (z9) {
            forceUniformHeight(i6, i);
        }
    }

    private void forceUniformHeight(int i, int i2) {
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, iMakeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    void layoutVertical(int i, int i2, int i3, int i4) {
        int paddingTop;
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i8 = this.mGravity & 112;
        int i9 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if (i8 == 16) {
            paddingTop = (((i4 - i2) - this.mTotalLength) / 2) + getPaddingTop();
        } else if (i8 == 80) {
            paddingTop = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        } else {
            paddingTop = getPaddingTop();
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(childrenSkipCount);
            if (virtualChildAt == null) {
                paddingTop += measureNullChild(childrenSkipCount);
            } else {
                if (virtualChildAt.getVisibility() != 8) {
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    int measuredHeight = virtualChildAt.getMeasuredHeight();
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    int i10 = layoutParams.gravity;
                    if (i10 < 0) {
                        i10 = i9;
                    }
                    int absoluteGravity = GravityCompat.getAbsoluteGravity(i10, ViewCompat.getLayoutDirection(this)) & 7;
                    if (absoluteGravity == 1) {
                        i5 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                    } else if (absoluteGravity == 5) {
                        i5 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                    } else {
                        i5 = layoutParams.leftMargin + paddingLeft;
                    }
                    int i11 = i5;
                    if (hasDividerBeforeChildAt(childrenSkipCount)) {
                        paddingTop += this.mDividerHeight;
                    }
                    int i12 = paddingTop + layoutParams.topMargin;
                    setChildFrame(virtualChildAt, i11, i12 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                    int nextLocationOffset = i12 + measuredHeight + layoutParams.bottomMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount += getChildrenSkipCount(virtualChildAt, childrenSkipCount);
                    paddingTop = nextLocationOffset;
                    i6 = 1;
                }
                childrenSkipCount += i6;
            }
            i6 = 1;
            childrenSkipCount += i6;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:33:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:35:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:37:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:39:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:41:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:43:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:45:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:46:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:48:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:51:0x010a  */
    void layoutHorizontal(int i, int i2, int i3, int i4) {
        int paddingLeft;
        int i5;
        int i6;
        boolean z;
        int i7;
        int baseline;
        int i8;
        int i9;
        int i10;
        int i11;
        int measuredHeight;
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
        int paddingTop = getPaddingTop();
        int i12 = i4 - i2;
        int paddingBottom = i12 - getPaddingBottom();
        int paddingBottom2 = (i12 - paddingTop) - getPaddingBottom();
        int virtualChildCount = getVirtualChildCount();
        int i13 = this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        int i14 = this.mGravity & 112;
        boolean z2 = this.mBaselineAligned;
        int[] iArr = this.mMaxAscent;
        int[] iArr2 = this.mMaxDescent;
        int absoluteGravity = GravityCompat.getAbsoluteGravity(i13, ViewCompat.getLayoutDirection(this));
        boolean z3 = true;
        if (absoluteGravity == 1) {
            paddingLeft = (((i3 - i) - this.mTotalLength) / 2) + getPaddingLeft();
        } else if (absoluteGravity == 5) {
            paddingLeft = ((getPaddingLeft() + i3) - i) - this.mTotalLength;
        } else {
            paddingLeft = getPaddingLeft();
        }
        if (zIsLayoutRtl) {
            i5 = virtualChildCount - 1;
            i6 = -1;
        } else {
            i5 = 0;
            i6 = 1;
        }
        int childrenSkipCount = 0;
        while (childrenSkipCount < virtualChildCount) {
            int i15 = i5 + (i6 * childrenSkipCount);
            View virtualChildAt = getVirtualChildAt(i15);
            if (virtualChildAt == null) {
                paddingLeft += measureNullChild(i15);
                z = z3;
            } else {
                if (virtualChildAt.getVisibility() != 8) {
                    int measuredWidth = virtualChildAt.getMeasuredWidth();
                    int measuredHeight2 = virtualChildAt.getMeasuredHeight();
                    LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                    if (z2) {
                        i7 = childrenSkipCount;
                        virtualChildCount = virtualChildCount;
                        baseline = layoutParams.height != -1 ? virtualChildAt.getBaseline() : -1;
                        i8 = layoutParams.gravity;
                        if (i8 < 0) {
                            i8 = i14;
                        }
                        i9 = i8 & 112;
                        i14 = i14;
                        if (i9 != 16) {
                            z = true;
                            i10 = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                        } else if (i9 != 48) {
                            if (i9 != 80) {
                                i10 = paddingTop;
                            } else {
                                measuredHeight = (paddingBottom - measuredHeight2) - layoutParams.bottomMargin;
                                if (baseline != -1) {
                                    measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                                }
                                i10 = measuredHeight;
                            }
                            z = true;
                        } else {
                            i11 = layoutParams.topMargin + paddingTop;
                            if (baseline != -1) {
                                z = true;
                                i11 += iArr[1] - baseline;
                            } else {
                                z = true;
                            }
                            i10 = i11;
                        }
                        if (hasDividerBeforeChildAt(i15)) {
                            paddingLeft += this.mDividerWidth;
                        }
                        int i16 = layoutParams.leftMargin + paddingLeft;
                        paddingTop = paddingTop;
                        setChildFrame(virtualChildAt, i16 + getLocationOffset(virtualChildAt), i10, measuredWidth, measuredHeight2);
                        int nextLocationOffset = i16 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                        childrenSkipCount = i7 + getChildrenSkipCount(virtualChildAt, i15);
                        paddingLeft = nextLocationOffset;
                    } else {
                        i7 = childrenSkipCount;
                        virtualChildCount = virtualChildCount;
                    }
                    i8 = layoutParams.gravity;
                    if (i8 < 0) {
                        i8 = i14;
                    }
                    i9 = i8 & 112;
                    i14 = i14;
                    if (i9 != 16) {
                        z = true;
                        i10 = ((((paddingBottom2 - measuredHeight2) / 2) + paddingTop) + layoutParams.topMargin) - layoutParams.bottomMargin;
                    } else if (i9 != 48) {
                        if (i9 != 80) {
                            i10 = paddingTop;
                        } else {
                            measuredHeight = (paddingBottom - measuredHeight2) - layoutParams.bottomMargin;
                            if (baseline != -1) {
                                measuredHeight -= iArr2[2] - (virtualChildAt.getMeasuredHeight() - baseline);
                            }
                            i10 = measuredHeight;
                        }
                        z = true;
                    } else {
                        i11 = layoutParams.topMargin + paddingTop;
                        if (baseline != -1) {
                            z = true;
                            i11 += iArr[1] - baseline;
                        } else {
                            z = true;
                        }
                        i10 = i11;
                    }
                    if (hasDividerBeforeChildAt(i15)) {
                        paddingLeft += this.mDividerWidth;
                    }
                    int i17 = layoutParams.leftMargin + paddingLeft;
                    paddingTop = paddingTop;
                    setChildFrame(virtualChildAt, i17 + getLocationOffset(virtualChildAt), i10, measuredWidth, measuredHeight2);
                    int nextLocationOffset2 = i17 + measuredWidth + layoutParams.rightMargin + getNextLocationOffset(virtualChildAt);
                    childrenSkipCount = i7 + getChildrenSkipCount(virtualChildAt, i15);
                    paddingLeft = nextLocationOffset2;
                } else {
                    z = true;
                }
                childrenSkipCount++;
                z3 = z;
                virtualChildCount = virtualChildCount;
                i14 = i14;
                paddingTop = paddingTop;
            }
            childrenSkipCount++;
            z3 = z;
            virtualChildCount = virtualChildCount;
            i14 = i14;
            paddingTop = paddingTop;
        }
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= GravityCompat.START;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK;
        if ((8388615 & this.mGravity) != i2) {
            this.mGravity = i2 | (this.mGravity & (-8388616));
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        if ((this.mGravity & 112) != i2) {
            this.mGravity = i2 | (this.mGravity & (-113));
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -2);
        }
        if (this.mOrientation == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
        }
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = typedArrayObtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0f);
            this.gravity = typedArrayObtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = 0.0f;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((ViewGroup.MarginLayoutParams) layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }
}
