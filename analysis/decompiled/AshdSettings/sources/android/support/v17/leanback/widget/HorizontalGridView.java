package android.support.v17.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.RestrictTo;
import android.support.v17.leanback.R;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class HorizontalGridView extends BaseGridView {
    private boolean mFadingHighEdge;
    private boolean mFadingLowEdge;
    private LinearGradient mHighFadeShader;
    private int mHighFadeShaderLength;
    private int mHighFadeShaderOffset;
    private LinearGradient mLowFadeShader;
    private int mLowFadeShaderLength;
    private int mLowFadeShaderOffset;
    private Bitmap mTempBitmapHigh;
    private Bitmap mTempBitmapLow;
    private Paint mTempPaint;
    private Rect mTempRect;

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void addOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        super.addOnChildViewHolderSelectedListener(onChildViewHolderSelectedListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void animateIn() {
        super.animateIn();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void animateOut() {
        super.animateOut();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ boolean dispatchGenericFocusedEvent(MotionEvent motionEvent) {
        return super.dispatchGenericFocusedEvent(motionEvent);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.view.ViewGroup, android.view.View
    public /* bridge */ /* synthetic */ boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.view.View
    public /* bridge */ /* synthetic */ View focusSearch(int i) {
        return super.focusSearch(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.support.v7.widget.RecyclerView, android.view.ViewGroup
    public /* bridge */ /* synthetic */ int getChildDrawingOrder(int i, int i2) {
        return super.getChildDrawingOrder(i, i2);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public /* bridge */ /* synthetic */ int getExtraLayoutSpace() {
        return super.getExtraLayoutSpace();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getFocusScrollStrategy() {
        return super.getFocusScrollStrategy();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    @Deprecated
    public /* bridge */ /* synthetic */ int getHorizontalMargin() {
        return super.getHorizontalMargin();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getHorizontalSpacing() {
        return super.getHorizontalSpacing();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getInitialPrefetchItemCount() {
        return super.getInitialPrefetchItemCount();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getItemAlignmentOffset() {
        return super.getItemAlignmentOffset();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ float getItemAlignmentOffsetPercent() {
        return super.getItemAlignmentOffsetPercent();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getItemAlignmentViewId() {
        return super.getItemAlignmentViewId();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ BaseGridView.OnUnhandledKeyListener getOnUnhandledKeyListener() {
        return super.getOnUnhandledKeyListener();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getSelectedPosition() {
        return super.getSelectedPosition();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getSelectedSubPosition() {
        return super.getSelectedSubPosition();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    @Deprecated
    public /* bridge */ /* synthetic */ int getVerticalMargin() {
        return super.getVerticalMargin();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getVerticalSpacing() {
        return super.getVerticalSpacing();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void getViewSelectedOffsets(View view, int[] iArr) {
        super.getViewSelectedOffsets(view, iArr);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getWindowAlignment() {
        return super.getWindowAlignment();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ int getWindowAlignmentOffset() {
        return super.getWindowAlignmentOffset();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ float getWindowAlignmentOffsetPercent() {
        return super.getWindowAlignmentOffsetPercent();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.view.View
    public /* bridge */ /* synthetic */ boolean hasOverlappingRendering() {
        return super.hasOverlappingRendering();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ boolean hasPreviousViewInSameRow(int i) {
        return super.hasPreviousViewInSameRow(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ boolean isChildLayoutAnimated() {
        return super.isChildLayoutAnimated();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ boolean isFocusDrawingOrderEnabled() {
        return super.isFocusDrawingOrderEnabled();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ boolean isItemAlignmentOffsetWithPadding() {
        return super.isItemAlignmentOffsetWithPadding();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ boolean isScrollEnabled() {
        return super.isScrollEnabled();
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.support.v7.widget.RecyclerView, android.view.ViewGroup
    public /* bridge */ /* synthetic */ boolean onRequestFocusInDescendants(int i, Rect rect) {
        return super.onRequestFocusInDescendants(i, rect);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.view.View
    public /* bridge */ /* synthetic */ void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void removeOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        super.removeOnChildViewHolderSelectedListener(onChildViewHolderSelectedListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.support.v7.widget.RecyclerView
    public /* bridge */ /* synthetic */ void scrollToPosition(int i) {
        super.scrollToPosition(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setAnimateChildLayout(boolean z) {
        super.setAnimateChildLayout(z);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setChildrenVisibility(int i) {
        super.setChildrenVisibility(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public /* bridge */ /* synthetic */ void setExtraLayoutSpace(int i) {
        super.setExtraLayoutSpace(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setFocusDrawingOrderEnabled(boolean z) {
        super.setFocusDrawingOrderEnabled(z);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setFocusScrollStrategy(int i) {
        super.setFocusScrollStrategy(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setGravity(int i) {
        super.setGravity(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setHasOverlappingRendering(boolean z) {
        super.setHasOverlappingRendering(z);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    @Deprecated
    public /* bridge */ /* synthetic */ void setHorizontalMargin(int i) {
        super.setHorizontalMargin(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setHorizontalSpacing(int i) {
        super.setHorizontalSpacing(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setInitialPrefetchItemCount(int i) {
        super.setInitialPrefetchItemCount(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setItemAlignmentOffset(int i) {
        super.setItemAlignmentOffset(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setItemAlignmentOffsetPercent(float f) {
        super.setItemAlignmentOffsetPercent(f);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setItemAlignmentOffsetWithPadding(boolean z) {
        super.setItemAlignmentOffsetWithPadding(z);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setItemAlignmentViewId(int i) {
        super.setItemAlignmentViewId(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    @Deprecated
    public /* bridge */ /* synthetic */ void setItemMargin(int i) {
        super.setItemMargin(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setItemSpacing(int i) {
        super.setItemSpacing(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setLayoutEnabled(boolean z) {
        super.setLayoutEnabled(z);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setOnChildLaidOutListener(OnChildLaidOutListener onChildLaidOutListener) {
        super.setOnChildLaidOutListener(onChildLaidOutListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setOnChildSelectedListener(OnChildSelectedListener onChildSelectedListener) {
        super.setOnChildSelectedListener(onChildSelectedListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        super.setOnChildViewHolderSelectedListener(onChildViewHolderSelectedListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setOnKeyInterceptListener(BaseGridView.OnKeyInterceptListener onKeyInterceptListener) {
        super.setOnKeyInterceptListener(onKeyInterceptListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setOnMotionInterceptListener(BaseGridView.OnMotionInterceptListener onMotionInterceptListener) {
        super.setOnMotionInterceptListener(onMotionInterceptListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setOnTouchInterceptListener(BaseGridView.OnTouchInterceptListener onTouchInterceptListener) {
        super.setOnTouchInterceptListener(onTouchInterceptListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setOnUnhandledKeyListener(BaseGridView.OnUnhandledKeyListener onUnhandledKeyListener) {
        super.setOnUnhandledKeyListener(onUnhandledKeyListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setPruneChild(boolean z) {
        super.setPruneChild(z);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.support.v7.widget.RecyclerView
    public /* bridge */ /* synthetic */ void setRecyclerListener(RecyclerView.RecyclerListener recyclerListener) {
        super.setRecyclerListener(recyclerListener);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setScrollEnabled(boolean z) {
        super.setScrollEnabled(z);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPosition(int i) {
        super.setSelectedPosition(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPosition(int i, int i2) {
        super.setSelectedPosition(i, i2);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPosition(int i, ViewHolderTask viewHolderTask) {
        super.setSelectedPosition(i, viewHolderTask);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPositionSmooth(int i) {
        super.setSelectedPositionSmooth(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPositionSmooth(int i, ViewHolderTask viewHolderTask) {
        super.setSelectedPositionSmooth(i, viewHolderTask);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPositionSmoothWithSub(int i, int i2) {
        super.setSelectedPositionSmoothWithSub(i, i2);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPositionWithSub(int i, int i2) {
        super.setSelectedPositionWithSub(i, i2);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setSelectedPositionWithSub(int i, int i2, int i3) {
        super.setSelectedPositionWithSub(i, i2, i3);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    @Deprecated
    public /* bridge */ /* synthetic */ void setVerticalMargin(int i) {
        super.setVerticalMargin(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setVerticalSpacing(int i) {
        super.setVerticalSpacing(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setWindowAlignment(int i) {
        super.setWindowAlignment(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setWindowAlignmentOffset(int i) {
        super.setWindowAlignmentOffset(i);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView
    public /* bridge */ /* synthetic */ void setWindowAlignmentOffsetPercent(float f) {
        super.setWindowAlignmentOffsetPercent(f);
    }

    @Override // android.support.v17.leanback.widget.BaseGridView, android.support.v7.widget.RecyclerView
    public /* bridge */ /* synthetic */ void smoothScrollToPosition(int i) {
        super.smoothScrollToPosition(i);
    }

    public HorizontalGridView(Context context) {
        this(context, null);
    }

    public HorizontalGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTempPaint = new Paint();
        this.mTempRect = new Rect();
        this.mLayoutManager.setOrientation(0);
        initAttributes(context, attributeSet);
    }

    protected void initAttributes(Context context, AttributeSet attributeSet) {
        initBaseGridViewAttributes(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbHorizontalGridView);
        setRowHeight(typedArrayObtainStyledAttributes);
        setNumRows(typedArrayObtainStyledAttributes.getInt(R.styleable.lbHorizontalGridView_numberOfRows, 1));
        typedArrayObtainStyledAttributes.recycle();
        updateLayerType();
        this.mTempPaint = new Paint();
        this.mTempPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    void setRowHeight(TypedArray typedArray) {
        if (typedArray.peekValue(R.styleable.lbHorizontalGridView_rowHeight) != null) {
            setRowHeight(typedArray.getLayoutDimension(R.styleable.lbHorizontalGridView_rowHeight, 0));
        }
    }

    public void setNumRows(int i) {
        this.mLayoutManager.setNumRows(i);
        requestLayout();
    }

    public void setRowHeight(int i) {
        this.mLayoutManager.setRowHeight(i);
        requestLayout();
    }

    public final void setFadingLeftEdge(boolean z) {
        if (this.mFadingLowEdge != z) {
            this.mFadingLowEdge = z;
            if (!this.mFadingLowEdge) {
                this.mTempBitmapLow = null;
            }
            invalidate();
            updateLayerType();
        }
    }

    public final boolean getFadingLeftEdge() {
        return this.mFadingLowEdge;
    }

    public final void setFadingLeftEdgeLength(int i) {
        if (this.mLowFadeShaderLength != i) {
            this.mLowFadeShaderLength = i;
            if (this.mLowFadeShaderLength != 0) {
                this.mLowFadeShader = new LinearGradient(0.0f, 0.0f, this.mLowFadeShaderLength, 0.0f, 0, -16777216, Shader.TileMode.CLAMP);
            } else {
                this.mLowFadeShader = null;
            }
            invalidate();
        }
    }

    public final int getFadingLeftEdgeLength() {
        return this.mLowFadeShaderLength;
    }

    public final void setFadingLeftEdgeOffset(int i) {
        if (this.mLowFadeShaderOffset != i) {
            this.mLowFadeShaderOffset = i;
            invalidate();
        }
    }

    public final int getFadingLeftEdgeOffset() {
        return this.mLowFadeShaderOffset;
    }

    public final void setFadingRightEdge(boolean z) {
        if (this.mFadingHighEdge != z) {
            this.mFadingHighEdge = z;
            if (!this.mFadingHighEdge) {
                this.mTempBitmapHigh = null;
            }
            invalidate();
            updateLayerType();
        }
    }

    public final boolean getFadingRightEdge() {
        return this.mFadingHighEdge;
    }

    public final void setFadingRightEdgeLength(int i) {
        if (this.mHighFadeShaderLength != i) {
            this.mHighFadeShaderLength = i;
            if (this.mHighFadeShaderLength != 0) {
                this.mHighFadeShader = new LinearGradient(0.0f, 0.0f, this.mHighFadeShaderLength, 0.0f, -16777216, 0, Shader.TileMode.CLAMP);
            } else {
                this.mHighFadeShader = null;
            }
            invalidate();
        }
    }

    public final int getFadingRightEdgeLength() {
        return this.mHighFadeShaderLength;
    }

    public final void setFadingRightEdgeOffset(int i) {
        if (this.mHighFadeShaderOffset != i) {
            this.mHighFadeShaderOffset = i;
            invalidate();
        }
    }

    public final int getFadingRightEdgeOffset() {
        return this.mHighFadeShaderOffset;
    }

    private boolean needsFadingLowEdge() {
        if (!this.mFadingLowEdge) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (this.mLayoutManager.getOpticalLeft(getChildAt(i)) < getPaddingLeft() - this.mLowFadeShaderOffset) {
                return true;
            }
        }
        return false;
    }

    private boolean needsFadingHighEdge() {
        if (!this.mFadingHighEdge) {
            return false;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (this.mLayoutManager.getOpticalRight(getChildAt(childCount)) > (getWidth() - getPaddingRight()) + this.mHighFadeShaderOffset) {
                return true;
            }
        }
        return false;
    }

    private Bitmap getTempBitmapLow() {
        if (this.mTempBitmapLow == null || this.mTempBitmapLow.getWidth() != this.mLowFadeShaderLength || this.mTempBitmapLow.getHeight() != getHeight()) {
            this.mTempBitmapLow = Bitmap.createBitmap(this.mLowFadeShaderLength, getHeight(), Bitmap.Config.ARGB_8888);
        }
        return this.mTempBitmapLow;
    }

    private Bitmap getTempBitmapHigh() {
        if (this.mTempBitmapHigh == null || this.mTempBitmapHigh.getWidth() != this.mHighFadeShaderLength || this.mTempBitmapHigh.getHeight() != getHeight()) {
            this.mTempBitmapHigh = Bitmap.createBitmap(this.mHighFadeShaderLength, getHeight(), Bitmap.Config.ARGB_8888);
        }
        return this.mTempBitmapHigh;
    }

    @Override // android.support.v7.widget.RecyclerView, android.view.View
    public void draw(Canvas canvas) {
        boolean zNeedsFadingLowEdge = needsFadingLowEdge();
        boolean zNeedsFadingHighEdge = needsFadingHighEdge();
        if (!zNeedsFadingLowEdge) {
            this.mTempBitmapLow = null;
        }
        if (!zNeedsFadingHighEdge) {
            this.mTempBitmapHigh = null;
        }
        if (!zNeedsFadingLowEdge && !zNeedsFadingHighEdge) {
            super.draw(canvas);
            return;
        }
        int paddingLeft = this.mFadingLowEdge ? (getPaddingLeft() - this.mLowFadeShaderOffset) - this.mLowFadeShaderLength : 0;
        int width = this.mFadingHighEdge ? (getWidth() - getPaddingRight()) + this.mHighFadeShaderOffset + this.mHighFadeShaderLength : getWidth();
        int iSave = canvas.save();
        canvas.clipRect((this.mFadingLowEdge ? this.mLowFadeShaderLength : 0) + paddingLeft, 0, width - (this.mFadingHighEdge ? this.mHighFadeShaderLength : 0), getHeight());
        super.draw(canvas);
        canvas.restoreToCount(iSave);
        Canvas canvas2 = new Canvas();
        this.mTempRect.top = 0;
        this.mTempRect.bottom = getHeight();
        if (zNeedsFadingLowEdge && this.mLowFadeShaderLength > 0) {
            Bitmap tempBitmapLow = getTempBitmapLow();
            tempBitmapLow.eraseColor(0);
            canvas2.setBitmap(tempBitmapLow);
            int iSave2 = canvas2.save();
            canvas2.clipRect(0, 0, this.mLowFadeShaderLength, getHeight());
            float f = -paddingLeft;
            canvas2.translate(f, 0.0f);
            super.draw(canvas2);
            canvas2.restoreToCount(iSave2);
            this.mTempPaint.setShader(this.mLowFadeShader);
            canvas2.drawRect(0.0f, 0.0f, this.mLowFadeShaderLength, getHeight(), this.mTempPaint);
            this.mTempRect.left = 0;
            this.mTempRect.right = this.mLowFadeShaderLength;
            canvas.translate(paddingLeft, 0.0f);
            canvas.drawBitmap(tempBitmapLow, this.mTempRect, this.mTempRect, (Paint) null);
            canvas.translate(f, 0.0f);
        }
        if (!zNeedsFadingHighEdge || this.mHighFadeShaderLength <= 0) {
            return;
        }
        Bitmap tempBitmapHigh = getTempBitmapHigh();
        tempBitmapHigh.eraseColor(0);
        canvas2.setBitmap(tempBitmapHigh);
        int iSave3 = canvas2.save();
        canvas2.clipRect(0, 0, this.mHighFadeShaderLength, getHeight());
        canvas2.translate(-(width - this.mHighFadeShaderLength), 0.0f);
        super.draw(canvas2);
        canvas2.restoreToCount(iSave3);
        this.mTempPaint.setShader(this.mHighFadeShader);
        canvas2.drawRect(0.0f, 0.0f, this.mHighFadeShaderLength, getHeight(), this.mTempPaint);
        this.mTempRect.left = 0;
        this.mTempRect.right = this.mHighFadeShaderLength;
        canvas.translate(width - this.mHighFadeShaderLength, 0.0f);
        canvas.drawBitmap(tempBitmapHigh, this.mTempRect, this.mTempRect, (Paint) null);
        canvas.translate(-(width - this.mHighFadeShaderLength), 0.0f);
    }

    private void updateLayerType() {
        if (this.mFadingLowEdge || this.mFadingHighEdge) {
            setLayerType(2, null);
            setWillNotDraw(false);
        } else {
            setLayerType(0, null);
            setWillNotDraw(true);
        }
    }
}
