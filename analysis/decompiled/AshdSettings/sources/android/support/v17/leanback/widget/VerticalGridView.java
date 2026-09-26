package android.support.v17.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.RestrictTo;
import android.support.v17.leanback.R;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class VerticalGridView extends BaseGridView {
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

    public VerticalGridView(Context context) {
        this(context, null);
    }

    public VerticalGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VerticalGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mLayoutManager.setOrientation(1);
        initAttributes(context, attributeSet);
    }

    protected void initAttributes(Context context, AttributeSet attributeSet) {
        initBaseGridViewAttributes(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbVerticalGridView);
        setColumnWidth(typedArrayObtainStyledAttributes);
        setNumColumns(typedArrayObtainStyledAttributes.getInt(R.styleable.lbVerticalGridView_numberOfColumns, 1));
        typedArrayObtainStyledAttributes.recycle();
    }

    void setColumnWidth(TypedArray typedArray) {
        if (typedArray.peekValue(R.styleable.lbVerticalGridView_columnWidth) != null) {
            setColumnWidth(typedArray.getLayoutDimension(R.styleable.lbVerticalGridView_columnWidth, 0));
        }
    }

    public void setNumColumns(int i) {
        this.mLayoutManager.setNumRows(i);
        requestLayout();
    }

    public void setColumnWidth(int i) {
        this.mLayoutManager.setRowHeight(i);
        requestLayout();
    }
}
