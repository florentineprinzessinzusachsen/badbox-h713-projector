package android.support.v17.leanback.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v17.leanback.R;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class PagingIndicator extends View {
    private static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final Property<Dot, Float> DOT_ALPHA = new Property<Dot, Float>(Float.class, "alpha") { // from class: android.support.v17.leanback.widget.PagingIndicator.1
        @Override // android.util.Property
        public Float get(Dot dot) {
            return Float.valueOf(dot.getAlpha());
        }

        @Override // android.util.Property
        public void set(Dot dot, Float f) {
            dot.setAlpha(f.floatValue());
        }
    };
    private static final Property<Dot, Float> DOT_DIAMETER = new Property<Dot, Float>(Float.class, "diameter") { // from class: android.support.v17.leanback.widget.PagingIndicator.2
        @Override // android.util.Property
        public Float get(Dot dot) {
            return Float.valueOf(dot.getDiameter());
        }

        @Override // android.util.Property
        public void set(Dot dot, Float f) {
            dot.setDiameter(f.floatValue());
        }
    };
    private static final Property<Dot, Float> DOT_TRANSLATION_X = new Property<Dot, Float>(Float.class, "translation_x") { // from class: android.support.v17.leanback.widget.PagingIndicator.3
        @Override // android.util.Property
        public Float get(Dot dot) {
            return Float.valueOf(dot.getTranslationX());
        }

        @Override // android.util.Property
        public void set(Dot dot, Float f) {
            dot.setTranslationX(f.floatValue());
        }
    };
    private static final long DURATION_ALPHA = 167;
    private static final long DURATION_DIAMETER = 417;
    private static final long DURATION_TRANSLATION_X = 417;
    private final AnimatorSet mAnimator;
    Bitmap mArrow;
    final int mArrowDiameter;
    private final int mArrowGap;
    final int mArrowRadius;
    final Rect mArrowRect;
    final float mArrowToBgRatio;
    final Paint mBgPaint;
    private int mCurrentPage;
    int mDotCenterY;
    final int mDotDiameter;

    @ColorInt
    final int mDotFgSelectColor;
    private final int mDotGap;
    final int mDotRadius;
    private int[] mDotSelectedNextX;
    private int[] mDotSelectedPrevX;
    private int[] mDotSelectedX;
    private Dot[] mDots;
    final Paint mFgPaint;
    private final AnimatorSet mHideAnimator;
    boolean mIsLtr;
    private int mPageCount;
    private int mPreviousPage;
    private final int mShadowRadius;
    private final AnimatorSet mShowAnimator;

    public PagingIndicator(Context context) {
        this(context, null, 0);
    }

    public PagingIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PagingIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimator = new AnimatorSet();
        Resources resources = getResources();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PagingIndicator, i, 0);
        this.mDotRadius = getDimensionFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.PagingIndicator_lbDotRadius, R.dimen.lb_page_indicator_dot_radius);
        this.mDotDiameter = this.mDotRadius * 2;
        this.mArrowRadius = getDimensionFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.PagingIndicator_arrowRadius, R.dimen.lb_page_indicator_arrow_radius);
        this.mArrowDiameter = this.mArrowRadius * 2;
        this.mDotGap = getDimensionFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.PagingIndicator_dotToDotGap, R.dimen.lb_page_indicator_dot_gap);
        this.mArrowGap = getDimensionFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.PagingIndicator_dotToArrowGap, R.dimen.lb_page_indicator_arrow_gap);
        int colorFromTypedArray = getColorFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.PagingIndicator_dotBgColor, R.color.lb_page_indicator_dot);
        this.mBgPaint = new Paint(1);
        this.mBgPaint.setColor(colorFromTypedArray);
        this.mDotFgSelectColor = getColorFromTypedArray(typedArrayObtainStyledAttributes, R.styleable.PagingIndicator_arrowBgColor, R.color.lb_page_indicator_arrow_background);
        typedArrayObtainStyledAttributes.recycle();
        this.mIsLtr = resources.getConfiguration().getLayoutDirection() == 0;
        int color = resources.getColor(R.color.lb_page_indicator_arrow_shadow);
        this.mShadowRadius = resources.getDimensionPixelSize(R.dimen.lb_page_indicator_arrow_shadow_radius);
        this.mFgPaint = new Paint(1);
        float dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.lb_page_indicator_arrow_shadow_offset);
        this.mFgPaint.setShadowLayer(this.mShadowRadius, dimensionPixelSize, dimensionPixelSize, color);
        this.mArrow = loadArrow();
        this.mArrowRect = new Rect(0, 0, this.mArrow.getWidth(), this.mArrow.getHeight());
        this.mArrowToBgRatio = this.mArrow.getWidth() / this.mArrowDiameter;
        this.mShowAnimator = new AnimatorSet();
        this.mShowAnimator.playTogether(createDotAlphaAnimator(0.0f, 1.0f), createDotDiameterAnimator(this.mDotRadius * 2, this.mArrowRadius * 2), createDotTranslationXAnimator());
        this.mHideAnimator = new AnimatorSet();
        this.mHideAnimator.playTogether(createDotAlphaAnimator(1.0f, 0.0f), createDotDiameterAnimator(this.mArrowRadius * 2, this.mDotRadius * 2), createDotTranslationXAnimator());
        this.mAnimator.playTogether(this.mShowAnimator, this.mHideAnimator);
        setLayerType(1, null);
    }

    private int getDimensionFromTypedArray(TypedArray typedArray, int i, int i2) {
        return typedArray.getDimensionPixelOffset(i, getResources().getDimensionPixelOffset(i2));
    }

    private int getColorFromTypedArray(TypedArray typedArray, int i, int i2) {
        return typedArray.getColor(i, getResources().getColor(i2));
    }

    private Bitmap loadArrow() {
        Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), R.drawable.lb_ic_nav_arrow);
        if (this.mIsLtr) {
            return bitmapDecodeResource;
        }
        Matrix matrix = new Matrix();
        matrix.preScale(-1.0f, 1.0f);
        return Bitmap.createBitmap(bitmapDecodeResource, 0, 0, bitmapDecodeResource.getWidth(), bitmapDecodeResource.getHeight(), matrix, false);
    }

    private Animator createDotAlphaAnimator(float f, float f2) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, DOT_ALPHA, f, f2);
        objectAnimatorOfFloat.setDuration(DURATION_ALPHA);
        objectAnimatorOfFloat.setInterpolator(DECELERATE_INTERPOLATOR);
        return objectAnimatorOfFloat;
    }

    private Animator createDotDiameterAnimator(float f, float f2) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, DOT_DIAMETER, f, f2);
        objectAnimatorOfFloat.setDuration(417L);
        objectAnimatorOfFloat.setInterpolator(DECELERATE_INTERPOLATOR);
        return objectAnimatorOfFloat;
    }

    private Animator createDotTranslationXAnimator() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, DOT_TRANSLATION_X, (-this.mArrowGap) + this.mDotGap, 0.0f);
        objectAnimatorOfFloat.setDuration(417L);
        objectAnimatorOfFloat.setInterpolator(DECELERATE_INTERPOLATOR);
        return objectAnimatorOfFloat;
    }

    public void setPageCount(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("The page count should be a positive integer");
        }
        this.mPageCount = i;
        this.mDots = new Dot[this.mPageCount];
        for (int i2 = 0; i2 < this.mPageCount; i2++) {
            this.mDots[i2] = new Dot();
        }
        calculateDotPositions();
        setSelectedPage(0);
    }

    public void onPageSelected(int i, boolean z) {
        if (this.mCurrentPage == i) {
            return;
        }
        if (this.mAnimator.isStarted()) {
            this.mAnimator.end();
        }
        this.mPreviousPage = this.mCurrentPage;
        if (z) {
            this.mHideAnimator.setTarget(this.mDots[this.mPreviousPage]);
            this.mShowAnimator.setTarget(this.mDots[i]);
            this.mAnimator.start();
        }
        setSelectedPage(i);
    }

    private void calculateDotPositions() {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int requiredWidth = getRequiredWidth();
        int i = (paddingLeft + width) / 2;
        this.mDotSelectedX = new int[this.mPageCount];
        this.mDotSelectedPrevX = new int[this.mPageCount];
        this.mDotSelectedNextX = new int[this.mPageCount];
        int i2 = 1;
        if (this.mIsLtr) {
            int i3 = i - (requiredWidth / 2);
            this.mDotSelectedX[0] = ((this.mDotRadius + i3) - this.mDotGap) + this.mArrowGap;
            this.mDotSelectedPrevX[0] = this.mDotRadius + i3;
            this.mDotSelectedNextX[0] = ((i3 + this.mDotRadius) - (this.mDotGap * 2)) + (this.mArrowGap * 2);
            while (i2 < this.mPageCount) {
                int i4 = i2 - 1;
                this.mDotSelectedX[i2] = this.mDotSelectedPrevX[i4] + this.mArrowGap;
                this.mDotSelectedPrevX[i2] = this.mDotSelectedPrevX[i4] + this.mDotGap;
                this.mDotSelectedNextX[i2] = this.mDotSelectedX[i4] + this.mArrowGap;
                i2++;
            }
        } else {
            int i5 = i + (requiredWidth / 2);
            this.mDotSelectedX[0] = ((i5 - this.mDotRadius) + this.mDotGap) - this.mArrowGap;
            this.mDotSelectedPrevX[0] = i5 - this.mDotRadius;
            this.mDotSelectedNextX[0] = ((i5 - this.mDotRadius) + (this.mDotGap * 2)) - (this.mArrowGap * 2);
            while (i2 < this.mPageCount) {
                int i6 = i2 - 1;
                this.mDotSelectedX[i2] = this.mDotSelectedPrevX[i6] - this.mArrowGap;
                this.mDotSelectedPrevX[i2] = this.mDotSelectedPrevX[i6] - this.mDotGap;
                this.mDotSelectedNextX[i2] = this.mDotSelectedX[i6] - this.mArrowGap;
                i2++;
            }
        }
        this.mDotCenterY = paddingTop + this.mArrowRadius;
        adjustDotPosition();
    }

    @VisibleForTesting
    int getPageCount() {
        return this.mPageCount;
    }

    @VisibleForTesting
    int[] getDotSelectedX() {
        return this.mDotSelectedX;
    }

    @VisibleForTesting
    int[] getDotSelectedLeftX() {
        return this.mDotSelectedPrevX;
    }

    @VisibleForTesting
    int[] getDotSelectedRightX() {
        return this.mDotSelectedNextX;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int desiredHeight = getDesiredHeight();
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            desiredHeight = Math.min(desiredHeight, View.MeasureSpec.getSize(i2));
        } else if (mode == 1073741824) {
            desiredHeight = View.MeasureSpec.getSize(i2);
        }
        int desiredWidth = getDesiredWidth();
        int mode2 = View.MeasureSpec.getMode(i);
        if (mode2 == Integer.MIN_VALUE) {
            desiredWidth = Math.min(desiredWidth, View.MeasureSpec.getSize(i));
        } else if (mode2 == 1073741824) {
            desiredWidth = View.MeasureSpec.getSize(i);
        }
        setMeasuredDimension(desiredWidth, desiredHeight);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        setMeasuredDimension(i, i2);
        calculateDotPositions();
    }

    private int getDesiredHeight() {
        return getPaddingTop() + this.mArrowDiameter + getPaddingBottom() + this.mShadowRadius;
    }

    private int getRequiredWidth() {
        return (this.mDotRadius * 2) + (this.mArrowGap * 2) + ((this.mPageCount - 3) * this.mDotGap);
    }

    private int getDesiredWidth() {
        return getPaddingLeft() + getRequiredWidth() + getPaddingRight();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < this.mPageCount; i++) {
            this.mDots[i].draw(canvas);
        }
    }

    private void setSelectedPage(int i) {
        if (i == this.mCurrentPage) {
            return;
        }
        this.mCurrentPage = i;
        adjustDotPosition();
    }

    private void adjustDotPosition() {
        int i = 0;
        while (true) {
            float f = -1.0f;
            if (i >= this.mCurrentPage) {
                break;
            }
            this.mDots[i].deselect();
            Dot dot = this.mDots[i];
            if (i != this.mPreviousPage) {
                f = 1.0f;
            }
            dot.mDirection = f;
            this.mDots[i].mCenterX = this.mDotSelectedPrevX[i];
            i++;
        }
        this.mDots[this.mCurrentPage].select();
        this.mDots[this.mCurrentPage].mDirection = this.mPreviousPage >= this.mCurrentPage ? 1.0f : -1.0f;
        this.mDots[this.mCurrentPage].mCenterX = this.mDotSelectedX[this.mCurrentPage];
        int i2 = this.mCurrentPage;
        while (true) {
            i2++;
            if (i2 >= this.mPageCount) {
                return;
            }
            this.mDots[i2].deselect();
            this.mDots[i2].mDirection = 1.0f;
            this.mDots[i2].mCenterX = this.mDotSelectedNextX[i2];
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        boolean z = i == 0;
        if (this.mIsLtr != z) {
            this.mIsLtr = z;
            this.mArrow = loadArrow();
            if (this.mDots != null) {
                for (Dot dot : this.mDots) {
                    dot.onRtlPropertiesChanged();
                }
            }
            calculateDotPositions();
            invalidate();
        }
    }

    public class Dot {
        static final float LEFT = -1.0f;
        static final float LTR = 1.0f;
        static final float RIGHT = 1.0f;
        static final float RTL = -1.0f;
        float mAlpha;
        float mArrowImageRadius;
        float mCenterX;
        float mDiameter;
        float mDirection = 1.0f;

        @ColorInt
        int mFgColor;
        float mLayoutDirection;
        float mRadius;
        float mTranslationX;

        public Dot() {
            this.mLayoutDirection = PagingIndicator.this.mIsLtr ? 1.0f : -1.0f;
        }

        void select() {
            this.mTranslationX = 0.0f;
            this.mCenterX = 0.0f;
            this.mDiameter = PagingIndicator.this.mArrowDiameter;
            this.mRadius = PagingIndicator.this.mArrowRadius;
            this.mArrowImageRadius = this.mRadius * PagingIndicator.this.mArrowToBgRatio;
            this.mAlpha = 1.0f;
            adjustAlpha();
        }

        void deselect() {
            this.mTranslationX = 0.0f;
            this.mCenterX = 0.0f;
            this.mDiameter = PagingIndicator.this.mDotDiameter;
            this.mRadius = PagingIndicator.this.mDotRadius;
            this.mArrowImageRadius = this.mRadius * PagingIndicator.this.mArrowToBgRatio;
            this.mAlpha = 0.0f;
            adjustAlpha();
        }

        public void adjustAlpha() {
            this.mFgColor = Color.argb(Math.round(this.mAlpha * 255.0f), Color.red(PagingIndicator.this.mDotFgSelectColor), Color.green(PagingIndicator.this.mDotFgSelectColor), Color.blue(PagingIndicator.this.mDotFgSelectColor));
        }

        public float getAlpha() {
            return this.mAlpha;
        }

        public void setAlpha(float f) {
            this.mAlpha = f;
            adjustAlpha();
            PagingIndicator.this.invalidate();
        }

        public float getTranslationX() {
            return this.mTranslationX;
        }

        public void setTranslationX(float f) {
            this.mTranslationX = f * this.mDirection * this.mLayoutDirection;
            PagingIndicator.this.invalidate();
        }

        public float getDiameter() {
            return this.mDiameter;
        }

        public void setDiameter(float f) {
            this.mDiameter = f;
            float f2 = f / 2.0f;
            this.mRadius = f2;
            this.mArrowImageRadius = f2 * PagingIndicator.this.mArrowToBgRatio;
            PagingIndicator.this.invalidate();
        }

        void draw(Canvas canvas) {
            float f = this.mCenterX + this.mTranslationX;
            canvas.drawCircle(f, PagingIndicator.this.mDotCenterY, this.mRadius, PagingIndicator.this.mBgPaint);
            if (this.mAlpha > 0.0f) {
                PagingIndicator.this.mFgPaint.setColor(this.mFgColor);
                canvas.drawCircle(f, PagingIndicator.this.mDotCenterY, this.mRadius, PagingIndicator.this.mFgPaint);
                canvas.drawBitmap(PagingIndicator.this.mArrow, PagingIndicator.this.mArrowRect, new Rect((int) (f - this.mArrowImageRadius), (int) (PagingIndicator.this.mDotCenterY - this.mArrowImageRadius), (int) (f + this.mArrowImageRadius), (int) (PagingIndicator.this.mDotCenterY + this.mArrowImageRadius)), (Paint) null);
            }
        }

        void onRtlPropertiesChanged() {
            this.mLayoutDirection = PagingIndicator.this.mIsLtr ? 1.0f : -1.0f;
        }
    }
}
