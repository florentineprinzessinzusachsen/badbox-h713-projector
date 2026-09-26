package android.support.v17.leanback.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BaseCardView extends FrameLayout {
    public static final int CARD_REGION_VISIBLE_ACTIVATED = 1;
    public static final int CARD_REGION_VISIBLE_ALWAYS = 0;
    public static final int CARD_REGION_VISIBLE_SELECTED = 2;
    public static final int CARD_TYPE_INFO_OVER = 1;
    public static final int CARD_TYPE_INFO_UNDER = 2;
    public static final int CARD_TYPE_INFO_UNDER_WITH_EXTRA = 3;
    private static final int CARD_TYPE_INVALID = 4;
    public static final int CARD_TYPE_MAIN_ONLY = 0;
    private static final boolean DEBUG = false;
    private static final int[] LB_PRESSED_STATE_SET = {R.attr.state_pressed};
    private static final String TAG = "BaseCardView";
    private final int mActivatedAnimDuration;
    private Animation mAnim;
    private final Runnable mAnimationTrigger;
    private int mCardType;
    private boolean mDelaySelectedAnim;
    ArrayList<View> mExtraViewList;
    private int mExtraVisibility;
    float mInfoAlpha;
    float mInfoOffset;
    ArrayList<View> mInfoViewList;
    float mInfoVisFraction;
    private int mInfoVisibility;
    private ArrayList<View> mMainViewList;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    private final int mSelectedAnimDuration;
    private int mSelectedAnimationDelay;

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public BaseCardView(Context context) {
        this(context, null);
    }

    public BaseCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, android.support.v17.leanback.R.attr.baseCardViewStyle);
    }

    public BaseCardView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimationTrigger = new Runnable() { // from class: android.support.v17.leanback.widget.BaseCardView.1
            @Override // java.lang.Runnable
            public void run() {
                BaseCardView.this.animateInfoOffset(true);
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, android.support.v17.leanback.R.styleable.lbBaseCardView, i, 0);
        try {
            this.mCardType = typedArrayObtainStyledAttributes.getInteger(android.support.v17.leanback.R.styleable.lbBaseCardView_cardType, 0);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(android.support.v17.leanback.R.styleable.lbBaseCardView_cardForeground);
            if (drawable != null) {
                setForeground(drawable);
            }
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(android.support.v17.leanback.R.styleable.lbBaseCardView_cardBackground);
            if (drawable2 != null) {
                setBackground(drawable2);
            }
            this.mInfoVisibility = typedArrayObtainStyledAttributes.getInteger(android.support.v17.leanback.R.styleable.lbBaseCardView_infoVisibility, 1);
            this.mExtraVisibility = typedArrayObtainStyledAttributes.getInteger(android.support.v17.leanback.R.styleable.lbBaseCardView_extraVisibility, 2);
            if (this.mExtraVisibility < this.mInfoVisibility) {
                this.mExtraVisibility = this.mInfoVisibility;
            }
            this.mSelectedAnimationDelay = typedArrayObtainStyledAttributes.getInteger(android.support.v17.leanback.R.styleable.lbBaseCardView_selectedAnimationDelay, getResources().getInteger(android.support.v17.leanback.R.integer.lb_card_selected_animation_delay));
            this.mSelectedAnimDuration = typedArrayObtainStyledAttributes.getInteger(android.support.v17.leanback.R.styleable.lbBaseCardView_selectedAnimationDuration, getResources().getInteger(android.support.v17.leanback.R.integer.lb_card_selected_animation_duration));
            this.mActivatedAnimDuration = typedArrayObtainStyledAttributes.getInteger(android.support.v17.leanback.R.styleable.lbBaseCardView_activatedAnimationDuration, getResources().getInteger(android.support.v17.leanback.R.integer.lb_card_activated_animation_duration));
            typedArrayObtainStyledAttributes.recycle();
            this.mDelaySelectedAnim = true;
            this.mMainViewList = new ArrayList<>();
            this.mInfoViewList = new ArrayList<>();
            this.mExtraViewList = new ArrayList<>();
            this.mInfoOffset = 0.0f;
            this.mInfoVisFraction = getFinalInfoVisFraction();
            this.mInfoAlpha = getFinalInfoAlpha();
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public void setSelectedAnimationDelayed(boolean z) {
        this.mDelaySelectedAnim = z;
    }

    public boolean isSelectedAnimationDelayed() {
        return this.mDelaySelectedAnim;
    }

    public void setCardType(int i) {
        if (this.mCardType != i) {
            if (i >= 0 && i < 4) {
                this.mCardType = i;
            } else {
                Log.e(TAG, "Invalid card type specified: " + i + ". Defaulting to type CARD_TYPE_MAIN_ONLY.");
                this.mCardType = 0;
            }
            requestLayout();
        }
    }

    public int getCardType() {
        return this.mCardType;
    }

    public void setInfoVisibility(int i) {
        if (this.mInfoVisibility != i) {
            cancelAnimations();
            this.mInfoVisibility = i;
            this.mInfoVisFraction = getFinalInfoVisFraction();
            requestLayout();
            float finalInfoAlpha = getFinalInfoAlpha();
            if (finalInfoAlpha != this.mInfoAlpha) {
                this.mInfoAlpha = finalInfoAlpha;
                for (int i2 = 0; i2 < this.mInfoViewList.size(); i2++) {
                    this.mInfoViewList.get(i2).setAlpha(this.mInfoAlpha);
                }
            }
        }
    }

    final float getFinalInfoVisFraction() {
        return (this.mCardType == 2 && this.mInfoVisibility == 2 && !isSelected()) ? 0.0f : 1.0f;
    }

    final float getFinalInfoAlpha() {
        return (this.mCardType == 1 && this.mInfoVisibility == 2 && !isSelected()) ? 0.0f : 1.0f;
    }

    public int getInfoVisibility() {
        return this.mInfoVisibility;
    }

    @Deprecated
    public void setExtraVisibility(int i) {
        if (this.mExtraVisibility != i) {
            this.mExtraVisibility = i;
        }
    }

    @Deprecated
    public int getExtraVisibility() {
        return this.mExtraVisibility;
    }

    @Override // android.view.View
    public void setActivated(boolean z) {
        if (z != isActivated()) {
            super.setActivated(z);
            applyActiveState(isActivated());
        }
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        if (z != isSelected()) {
            super.setSelected(z);
            applySelectedState(isSelected());
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        int measuredHeight2;
        boolean z = false;
        this.mMeasuredWidth = 0;
        this.mMeasuredHeight = 0;
        findChildrenViews();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int measuredHeight3 = 0;
        int iCombineMeasuredStates = 0;
        for (int i3 = 0; i3 < this.mMainViewList.size(); i3++) {
            View view = this.mMainViewList.get(i3);
            if (view.getVisibility() != 8) {
                measureChild(view, iMakeMeasureSpec, iMakeMeasureSpec);
                this.mMeasuredWidth = Math.max(this.mMeasuredWidth, view.getMeasuredWidth());
                measuredHeight3 += view.getMeasuredHeight();
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view.getMeasuredState());
            }
        }
        setPivotX(this.mMeasuredWidth / 2);
        setPivotY(measuredHeight3 / 2);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(this.mMeasuredWidth, 1073741824);
        if (hasInfoRegion()) {
            measuredHeight = 0;
            int iCombineMeasuredStates2 = iCombineMeasuredStates;
            for (int i4 = 0; i4 < this.mInfoViewList.size(); i4++) {
                View view2 = this.mInfoViewList.get(i4);
                if (view2.getVisibility() != 8) {
                    measureChild(view2, iMakeMeasureSpec2, iMakeMeasureSpec);
                    if (this.mCardType != 1) {
                        measuredHeight += view2.getMeasuredHeight();
                    }
                    iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, view2.getMeasuredState());
                }
            }
            if (hasExtraRegion()) {
                int iCombineMeasuredStates3 = iCombineMeasuredStates2;
                measuredHeight2 = 0;
                for (int i5 = 0; i5 < this.mExtraViewList.size(); i5++) {
                    View view3 = this.mExtraViewList.get(i5);
                    if (view3.getVisibility() != 8) {
                        measureChild(view3, iMakeMeasureSpec2, iMakeMeasureSpec);
                        measuredHeight2 += view3.getMeasuredHeight();
                        iCombineMeasuredStates3 = View.combineMeasuredStates(iCombineMeasuredStates3, view3.getMeasuredState());
                    }
                }
                iCombineMeasuredStates = iCombineMeasuredStates3;
            } else {
                iCombineMeasuredStates = iCombineMeasuredStates2;
                measuredHeight2 = 0;
            }
        } else {
            measuredHeight = 0;
            measuredHeight2 = 0;
        }
        if (hasInfoRegion() && this.mInfoVisibility == 2) {
            z = true;
        }
        this.mMeasuredHeight = (int) (((measuredHeight3 + (z ? measuredHeight * this.mInfoVisFraction : measuredHeight)) + measuredHeight2) - (z ? 0.0f : this.mInfoOffset));
        setMeasuredDimension(View.resolveSizeAndState(this.mMeasuredWidth + getPaddingLeft() + getPaddingRight(), i, iCombineMeasuredStates), View.resolveSizeAndState(this.mMeasuredHeight + getPaddingTop() + getPaddingBottom(), i2, iCombineMeasuredStates << 16));
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float paddingTop = getPaddingTop();
        for (int i5 = 0; i5 < this.mMainViewList.size(); i5++) {
            View view = this.mMainViewList.get(i5);
            if (view.getVisibility() != 8) {
                view.layout(getPaddingLeft(), (int) paddingTop, this.mMeasuredWidth + getPaddingLeft(), (int) (view.getMeasuredHeight() + paddingTop));
                paddingTop += view.getMeasuredHeight();
            }
        }
        if (hasInfoRegion()) {
            float measuredHeight = 0.0f;
            for (int i6 = 0; i6 < this.mInfoViewList.size(); i6++) {
                measuredHeight += this.mInfoViewList.get(i6).getMeasuredHeight();
            }
            if (this.mCardType == 1) {
                paddingTop -= measuredHeight;
                if (paddingTop < 0.0f) {
                    paddingTop = 0.0f;
                }
            } else if (this.mCardType == 2) {
                if (this.mInfoVisibility == 2) {
                    measuredHeight *= this.mInfoVisFraction;
                }
            } else {
                paddingTop -= this.mInfoOffset;
            }
            float measuredHeight2 = paddingTop;
            for (int i7 = 0; i7 < this.mInfoViewList.size(); i7++) {
                View view2 = this.mInfoViewList.get(i7);
                if (view2.getVisibility() != 8) {
                    int measuredHeight3 = view2.getMeasuredHeight();
                    if (measuredHeight3 > measuredHeight) {
                        measuredHeight3 = (int) measuredHeight;
                    }
                    float f = measuredHeight3;
                    measuredHeight2 += f;
                    view2.layout(getPaddingLeft(), (int) measuredHeight2, this.mMeasuredWidth + getPaddingLeft(), (int) measuredHeight2);
                    measuredHeight -= f;
                    if (measuredHeight <= 0.0f) {
                        break;
                    }
                }
            }
            if (hasExtraRegion()) {
                for (int i8 = 0; i8 < this.mExtraViewList.size(); i8++) {
                    View view3 = this.mExtraViewList.get(i8);
                    if (view3.getVisibility() != 8) {
                        view3.layout(getPaddingLeft(), (int) measuredHeight2, this.mMeasuredWidth + getPaddingLeft(), (int) (view3.getMeasuredHeight() + measuredHeight2));
                        measuredHeight2 += view3.getMeasuredHeight();
                    }
                }
            }
        }
        onSizeChanged(0, 0, i3 - i, i4 - i2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mAnimationTrigger);
        cancelAnimations();
    }

    private boolean hasInfoRegion() {
        return this.mCardType != 0;
    }

    private boolean hasExtraRegion() {
        return this.mCardType == 3;
    }

    private boolean isRegionVisible(int i) {
        switch (i) {
            case 0:
                return true;
            case 1:
                return isActivated();
            case 2:
                return isSelected();
            default:
                return false;
        }
    }

    private boolean isCurrentRegionVisible(int i) {
        switch (i) {
            case 0:
                return true;
            case 1:
                return isActivated();
            case 2:
                if (this.mCardType == 2) {
                    return this.mInfoVisFraction > 0.0f;
                }
                return isSelected();
            default:
                return false;
        }
    }

    private void findChildrenViews() {
        this.mMainViewList.clear();
        this.mInfoViewList.clear();
        this.mExtraViewList.clear();
        int childCount = getChildCount();
        boolean z = hasInfoRegion() && isCurrentRegionVisible(this.mInfoVisibility);
        boolean z2 = hasExtraRegion() && this.mInfoOffset > 0.0f;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt != null) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.viewType == 1) {
                    childAt.setAlpha(this.mInfoAlpha);
                    this.mInfoViewList.add(childAt);
                    childAt.setVisibility(z ? 0 : 8);
                } else if (layoutParams.viewType == 2) {
                    this.mExtraViewList.add(childAt);
                    childAt.setVisibility(z2 ? 0 : 8);
                } else {
                    this.mMainViewList.add(childAt);
                    childAt.setVisibility(0);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i);
        int length = iArrOnCreateDrawableState.length;
        boolean z = false;
        boolean z2 = false;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArrOnCreateDrawableState[i2] == 16842919) {
                z = true;
            }
            if (iArrOnCreateDrawableState[i2] == 16842910) {
                z2 = true;
            }
        }
        if (z && z2) {
            return View.PRESSED_ENABLED_STATE_SET;
        }
        if (z) {
            return LB_PRESSED_STATE_SET;
        }
        if (z2) {
            return View.ENABLED_STATE_SET;
        }
        return View.EMPTY_STATE_SET;
    }

    private void applyActiveState(boolean z) {
        if (hasInfoRegion() && this.mInfoVisibility == 1) {
            setInfoViewVisibility(isRegionVisible(this.mInfoVisibility));
        }
    }

    private void setInfoViewVisibility(boolean z) {
        if (this.mCardType != 3) {
            if (this.mCardType == 2) {
                if (this.mInfoVisibility == 2) {
                    animateInfoHeight(z);
                    return;
                }
                for (int i = 0; i < this.mInfoViewList.size(); i++) {
                    this.mInfoViewList.get(i).setVisibility(z ? 0 : 8);
                }
                return;
            }
            if (this.mCardType == 1) {
                animateInfoAlpha(z);
                return;
            }
            return;
        }
        if (z) {
            for (int i2 = 0; i2 < this.mInfoViewList.size(); i2++) {
                this.mInfoViewList.get(i2).setVisibility(0);
            }
            return;
        }
        for (int i3 = 0; i3 < this.mInfoViewList.size(); i3++) {
            this.mInfoViewList.get(i3).setVisibility(8);
        }
        for (int i4 = 0; i4 < this.mExtraViewList.size(); i4++) {
            this.mExtraViewList.get(i4).setVisibility(8);
        }
        this.mInfoOffset = 0.0f;
    }

    private void applySelectedState(boolean z) {
        removeCallbacks(this.mAnimationTrigger);
        if (this.mCardType != 3) {
            if (this.mInfoVisibility == 2) {
                setInfoViewVisibility(z);
            }
        } else {
            if (z) {
                if (!this.mDelaySelectedAnim) {
                    post(this.mAnimationTrigger);
                    this.mDelaySelectedAnim = true;
                    return;
                } else {
                    postDelayed(this.mAnimationTrigger, this.mSelectedAnimationDelay);
                    return;
                }
            }
            animateInfoOffset(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cancelAnimations() {
        if (this.mAnim != null) {
            this.mAnim.cancel();
            this.mAnim = null;
            clearAnimation();
        }
    }

    void animateInfoOffset(boolean z) {
        cancelAnimations();
        int i = 0;
        if (z) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mMeasuredWidth, 1073741824);
            int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            int iMax = 0;
            for (int i2 = 0; i2 < this.mExtraViewList.size(); i2++) {
                View view = this.mExtraViewList.get(i2);
                view.setVisibility(0);
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                iMax = Math.max(iMax, view.getMeasuredHeight());
            }
            i = iMax;
        }
        this.mAnim = new InfoOffsetAnimation(this.mInfoOffset, z ? i : 0.0f);
        this.mAnim.setDuration(this.mSelectedAnimDuration);
        this.mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mAnim.setAnimationListener(new Animation.AnimationListener() { // from class: android.support.v17.leanback.widget.BaseCardView.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (BaseCardView.this.mInfoOffset == 0.0f) {
                    for (int i3 = 0; i3 < BaseCardView.this.mExtraViewList.size(); i3++) {
                        BaseCardView.this.mExtraViewList.get(i3).setVisibility(8);
                    }
                }
            }
        });
        startAnimation(this.mAnim);
    }

    private void animateInfoHeight(boolean z) {
        cancelAnimations();
        if (z) {
            for (int i = 0; i < this.mInfoViewList.size(); i++) {
                this.mInfoViewList.get(i).setVisibility(0);
            }
        }
        float f = z ? 1.0f : 0.0f;
        if (this.mInfoVisFraction == f) {
            return;
        }
        this.mAnim = new InfoHeightAnimation(this.mInfoVisFraction, f);
        this.mAnim.setDuration(this.mSelectedAnimDuration);
        this.mAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        this.mAnim.setAnimationListener(new Animation.AnimationListener() { // from class: android.support.v17.leanback.widget.BaseCardView.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (BaseCardView.this.mInfoVisFraction == 0.0f) {
                    for (int i2 = 0; i2 < BaseCardView.this.mInfoViewList.size(); i2++) {
                        BaseCardView.this.mInfoViewList.get(i2).setVisibility(8);
                    }
                }
            }
        });
        startAnimation(this.mAnim);
    }

    private void animateInfoAlpha(boolean z) {
        cancelAnimations();
        if (z) {
            for (int i = 0; i < this.mInfoViewList.size(); i++) {
                this.mInfoViewList.get(i).setVisibility(0);
            }
        }
        if ((z ? 1.0f : 0.0f) == this.mInfoAlpha) {
            return;
        }
        this.mAnim = new InfoAlphaAnimation(this.mInfoAlpha, z ? 1.0f : 0.0f);
        this.mAnim.setDuration(this.mActivatedAnimDuration);
        this.mAnim.setInterpolator(new DecelerateInterpolator());
        this.mAnim.setAnimationListener(new Animation.AnimationListener() { // from class: android.support.v17.leanback.widget.BaseCardView.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (BaseCardView.this.mInfoAlpha == 0.0d) {
                    for (int i2 = 0; i2 < BaseCardView.this.mInfoViewList.size(); i2++) {
                        BaseCardView.this.mInfoViewList.get(i2).setVisibility(8);
                    }
                }
            }
        });
        startAnimation(this.mAnim);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int VIEW_TYPE_EXTRA = 2;
        public static final int VIEW_TYPE_INFO = 1;
        public static final int VIEW_TYPE_MAIN = 0;

        @ViewDebug.ExportedProperty(category = "layout", mapping = {@ViewDebug.IntToString(from = 0, to = "MAIN"), @ViewDebug.IntToString(from = 1, to = "INFO"), @ViewDebug.IntToString(from = 2, to = "EXTRA")})
        public int viewType;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.viewType = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, android.support.v17.leanback.R.styleable.lbBaseCardView_Layout);
            this.viewType = typedArrayObtainStyledAttributes.getInt(android.support.v17.leanback.R.styleable.lbBaseCardView_Layout_layout_viewType, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.viewType = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.viewType = 0;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((FrameLayout.LayoutParams) layoutParams);
            this.viewType = 0;
            this.viewType = layoutParams.viewType;
        }
    }

    class AnimationBase extends Animation {
        AnimationBase() {
        }

        @VisibleForTesting
        final void mockStart() {
            getTransformation(0L, null);
        }

        @VisibleForTesting
        final void mockEnd() {
            applyTransformation(1.0f, null);
            BaseCardView.this.cancelAnimations();
        }
    }

    final class InfoOffsetAnimation extends AnimationBase {
        private float mDelta;
        private float mStartValue;

        public InfoOffsetAnimation(float f, float f2) {
            super();
            this.mStartValue = f;
            this.mDelta = f2 - f;
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            BaseCardView.this.mInfoOffset = this.mStartValue + (f * this.mDelta);
            BaseCardView.this.requestLayout();
        }
    }

    final class InfoHeightAnimation extends AnimationBase {
        private float mDelta;
        private float mStartValue;

        public InfoHeightAnimation(float f, float f2) {
            super();
            this.mStartValue = f;
            this.mDelta = f2 - f;
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            BaseCardView.this.mInfoVisFraction = this.mStartValue + (f * this.mDelta);
            BaseCardView.this.requestLayout();
        }
    }

    final class InfoAlphaAnimation extends AnimationBase {
        private float mDelta;
        private float mStartValue;

        public InfoAlphaAnimation(float f, float f2) {
            super();
            this.mStartValue = f;
            this.mDelta = f2 - f;
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            BaseCardView.this.mInfoAlpha = this.mStartValue + (f * this.mDelta);
            for (int i = 0; i < BaseCardView.this.mInfoViewList.size(); i++) {
                BaseCardView.this.mInfoViewList.get(i).setAlpha(BaseCardView.this.mInfoAlpha);
            }
        }
    }

    @Override // android.view.View
    public String toString() {
        return super.toString();
    }
}
