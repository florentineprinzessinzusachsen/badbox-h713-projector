package android.support.v17.leanback.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.CircularIntArray;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.FocusFinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
final class GridLayoutManager extends RecyclerView.LayoutManager {
    static final boolean DEBUG = false;
    static final int MAX_PENDING_MOVES = 10;
    static final int MIN_MS_SMOOTH_SCROLL_MAIN_SCREEN = 30;
    private static final int NEXT_ITEM = 1;
    private static final int NEXT_ROW = 3;
    private static final int PREV_ITEM = 0;
    private static final int PREV_ROW = 2;
    private static final String TAG = "GridLayoutManager";
    static final boolean TRACE = false;
    private static final Rect sTempRect = new Rect();
    static int[] sTwoInts = new int[2];
    final BaseGridView mBaseGridView;
    private int mExtraLayoutSpace;
    private FacetProviderAdapter mFacetProviderAdapter;
    private int mFixedRowSizeSecondary;
    private boolean mFocusOutEnd;
    private boolean mFocusOutFront;
    private boolean mFocusSearchDisabled;
    private boolean mForceFullLayout;
    Grid mGrid;
    private int mHorizontalSpacing;
    boolean mInFastRelayout;
    boolean mInLayout;
    boolean mInLayoutSearchFocus;
    private boolean mInScroll;
    boolean mIsSlidingChildViews;

    @VisibleForTesting
    OnLayoutCompleteListener mLayoutCompleteListener;
    boolean mLayoutEatenInSliding;
    private int mMaxSizeSecondary;
    int mNumRows;
    PendingMoveSmoothScroller mPendingMoveSmoothScroller;
    private int mPrimaryScrollExtra;
    RecyclerView.Recycler mRecycler;
    private boolean mRowSecondarySizeRefresh;
    private int[] mRowSizeSecondary;
    private int mRowSizeSecondaryRequested;
    private int mScrollOffsetPrimary;
    int mScrollOffsetSecondary;
    private int mSizePrimary;
    private int mSpacingPrimary;
    private int mSpacingSecondary;
    RecyclerView.State mState;
    private int mVerticalSpacing;
    int mOrientation = 0;
    private OrientationHelper mOrientationHelper = OrientationHelper.createHorizontalHelper(this);
    boolean mInSelection = false;
    private OnChildSelectedListener mChildSelectedListener = null;
    private ArrayList<OnChildViewHolderSelectedListener> mChildViewHolderSelectedListeners = null;
    OnChildLaidOutListener mChildLaidOutListener = null;
    int mFocusPosition = -1;
    int mSubFocusPosition = 0;
    private int mFocusPositionOffset = 0;
    private boolean mLayoutEnabled = true;
    int mChildVisibility = -1;
    private int mGravity = 8388659;
    private int mNumRowsRequested = 1;
    private int mFocusScrollStrategy = 0;
    final WindowAlignment mWindowAlignment = new WindowAlignment();
    private final ItemAlignment mItemAlignment = new ItemAlignment();
    private boolean mFocusOutSideStart = true;
    private boolean mFocusOutSideEnd = true;
    private boolean mPruneChild = true;
    private boolean mScrollEnabled = true;
    boolean mReverseFlowPrimary = false;
    private boolean mReverseFlowSecondary = false;
    private int[] mMeasuredDimension = new int[2];
    final ViewsStateBundle mChildrenStates = new ViewsStateBundle();
    private final Runnable mRequestLayoutRunnable = new Runnable() { // from class: android.support.v17.leanback.widget.GridLayoutManager.1
        @Override // java.lang.Runnable
        public void run() {
            GridLayoutManager.this.requestLayout();
        }
    };
    private Grid.Provider mGridProvider = new Grid.Provider() { // from class: android.support.v17.leanback.widget.GridLayoutManager.2
        @Override // android.support.v17.leanback.widget.Grid.Provider
        public int getCount() {
            return GridLayoutManager.this.mState.getItemCount();
        }

        @Override // android.support.v17.leanback.widget.Grid.Provider
        public int createItem(int i, boolean z, Object[] objArr) {
            View viewForPosition = GridLayoutManager.this.getViewForPosition(i);
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            layoutParams.setItemAlignmentFacet((ItemAlignmentFacet) GridLayoutManager.this.getFacet(GridLayoutManager.this.mBaseGridView.getChildViewHolder(viewForPosition), ItemAlignmentFacet.class));
            if (!layoutParams.isItemRemoved()) {
                if (z) {
                    GridLayoutManager.this.addView(viewForPosition);
                } else {
                    GridLayoutManager.this.addView(viewForPosition, 0);
                }
                if (GridLayoutManager.this.mChildVisibility != -1) {
                    viewForPosition.setVisibility(GridLayoutManager.this.mChildVisibility);
                }
                if (GridLayoutManager.this.mPendingMoveSmoothScroller != null) {
                    GridLayoutManager.this.mPendingMoveSmoothScroller.consumePendingMovesBeforeLayout();
                }
                int subPositionByView = GridLayoutManager.this.getSubPositionByView(viewForPosition, viewForPosition.findFocus());
                if (!GridLayoutManager.this.mInLayout) {
                    if (i == GridLayoutManager.this.mFocusPosition && subPositionByView == GridLayoutManager.this.mSubFocusPosition && GridLayoutManager.this.mPendingMoveSmoothScroller == null) {
                        GridLayoutManager.this.dispatchChildSelected();
                    }
                } else if (!GridLayoutManager.this.mInFastRelayout) {
                    if (!GridLayoutManager.this.mInLayoutSearchFocus && i == GridLayoutManager.this.mFocusPosition && subPositionByView == GridLayoutManager.this.mSubFocusPosition) {
                        GridLayoutManager.this.dispatchChildSelected();
                    } else if (GridLayoutManager.this.mInLayoutSearchFocus && i >= GridLayoutManager.this.mFocusPosition && viewForPosition.hasFocusable()) {
                        GridLayoutManager.this.mFocusPosition = i;
                        GridLayoutManager.this.mSubFocusPosition = subPositionByView;
                        GridLayoutManager.this.mInLayoutSearchFocus = false;
                        GridLayoutManager.this.dispatchChildSelected();
                    }
                }
                GridLayoutManager.this.measureChild(viewForPosition);
            }
            objArr[0] = viewForPosition;
            return GridLayoutManager.this.mOrientation == 0 ? GridLayoutManager.this.getDecoratedMeasuredWidthWithMargin(viewForPosition) : GridLayoutManager.this.getDecoratedMeasuredHeightWithMargin(viewForPosition);
        }

        @Override // android.support.v17.leanback.widget.Grid.Provider
        public void addItem(Object obj, int i, int i2, int i3, int i4) {
            int i5;
            int i6;
            View view = (View) obj;
            if (i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE) {
                i4 = !GridLayoutManager.this.mGrid.isReversedFlow() ? GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingLow() : GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() - GridLayoutManager.this.mWindowAlignment.mainAxis().getPaddingHigh();
            }
            if (!GridLayoutManager.this.mGrid.isReversedFlow()) {
                i6 = i2 + i4;
                i5 = i4;
            } else {
                i5 = i4 - i2;
                i6 = i4;
            }
            int rowStartSecondary = GridLayoutManager.this.getRowStartSecondary(i3) - GridLayoutManager.this.mScrollOffsetSecondary;
            GridLayoutManager.this.mChildrenStates.loadView(view, i);
            GridLayoutManager.this.layoutChild(i3, view, i5, i6, rowStartSecondary);
            if (i == GridLayoutManager.this.mGrid.getFirstVisibleIndex()) {
                if (!GridLayoutManager.this.mGrid.isReversedFlow()) {
                    GridLayoutManager.this.updateScrollMin();
                } else {
                    GridLayoutManager.this.updateScrollMax();
                }
            }
            if (i == GridLayoutManager.this.mGrid.getLastVisibleIndex()) {
                if (!GridLayoutManager.this.mGrid.isReversedFlow()) {
                    GridLayoutManager.this.updateScrollMax();
                } else {
                    GridLayoutManager.this.updateScrollMin();
                }
            }
            if (!GridLayoutManager.this.mInLayout && GridLayoutManager.this.mPendingMoveSmoothScroller != null) {
                GridLayoutManager.this.mPendingMoveSmoothScroller.consumePendingMovesAfterLayout();
            }
            if (GridLayoutManager.this.mChildLaidOutListener != null) {
                RecyclerView.ViewHolder childViewHolder = GridLayoutManager.this.mBaseGridView.getChildViewHolder(view);
                GridLayoutManager.this.mChildLaidOutListener.onChildLaidOut(GridLayoutManager.this.mBaseGridView, view, i, childViewHolder == null ? -1L : childViewHolder.getItemId());
            }
        }

        @Override // android.support.v17.leanback.widget.Grid.Provider
        public void removeItem(int i) {
            View viewFindViewByPosition = GridLayoutManager.this.findViewByPosition(i);
            if (GridLayoutManager.this.mInLayout) {
                GridLayoutManager.this.detachAndScrapView(viewFindViewByPosition, GridLayoutManager.this.mRecycler);
            } else {
                GridLayoutManager.this.removeAndRecycleView(viewFindViewByPosition, GridLayoutManager.this.mRecycler);
            }
        }

        @Override // android.support.v17.leanback.widget.Grid.Provider
        public int getEdge(int i) {
            if (GridLayoutManager.this.mReverseFlowPrimary) {
                return GridLayoutManager.this.getViewMax(GridLayoutManager.this.findViewByPosition(i));
            }
            return GridLayoutManager.this.getViewMin(GridLayoutManager.this.findViewByPosition(i));
        }

        @Override // android.support.v17.leanback.widget.Grid.Provider
        public int getSize(int i) {
            return GridLayoutManager.this.getViewPrimarySize(GridLayoutManager.this.findViewByPosition(i));
        }
    };

    @VisibleForTesting
    public static class OnLayoutCompleteListener {
        public void onLayoutCompleted(RecyclerView.State state) {
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z) {
        return false;
    }

    static final class LayoutParams extends RecyclerView.LayoutParams {
        private int[] mAlignMultiple;
        private int mAlignX;
        private int mAlignY;
        private ItemAlignmentFacet mAlignmentFacet;
        int mBottomInset;
        int mLeftInset;
        int mRightInset;
        int mTopInset;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((RecyclerView.LayoutParams) layoutParams);
        }

        int getAlignX() {
            return this.mAlignX;
        }

        int getAlignY() {
            return this.mAlignY;
        }

        int getOpticalLeft(View view) {
            return view.getLeft() + this.mLeftInset;
        }

        int getOpticalTop(View view) {
            return view.getTop() + this.mTopInset;
        }

        int getOpticalRight(View view) {
            return view.getRight() - this.mRightInset;
        }

        int getOpticalBottom(View view) {
            return view.getBottom() - this.mBottomInset;
        }

        int getOpticalWidth(View view) {
            return (view.getWidth() - this.mLeftInset) - this.mRightInset;
        }

        int getOpticalHeight(View view) {
            return (view.getHeight() - this.mTopInset) - this.mBottomInset;
        }

        int getOpticalLeftInset() {
            return this.mLeftInset;
        }

        int getOpticalRightInset() {
            return this.mRightInset;
        }

        int getOpticalTopInset() {
            return this.mTopInset;
        }

        int getOpticalBottomInset() {
            return this.mBottomInset;
        }

        void setAlignX(int i) {
            this.mAlignX = i;
        }

        void setAlignY(int i) {
            this.mAlignY = i;
        }

        void setItemAlignmentFacet(ItemAlignmentFacet itemAlignmentFacet) {
            this.mAlignmentFacet = itemAlignmentFacet;
        }

        ItemAlignmentFacet getItemAlignmentFacet() {
            return this.mAlignmentFacet;
        }

        void calculateItemAlignments(int i, View view) {
            ItemAlignmentFacet.ItemAlignmentDef[] alignmentDefs = this.mAlignmentFacet.getAlignmentDefs();
            if (this.mAlignMultiple == null || this.mAlignMultiple.length != alignmentDefs.length) {
                this.mAlignMultiple = new int[alignmentDefs.length];
            }
            for (int i2 = 0; i2 < alignmentDefs.length; i2++) {
                this.mAlignMultiple[i2] = ItemAlignmentFacetHelper.getAlignmentPosition(view, alignmentDefs[i2], i);
            }
            if (i == 0) {
                this.mAlignX = this.mAlignMultiple[0];
            } else {
                this.mAlignY = this.mAlignMultiple[0];
            }
        }

        int[] getAlignMultiple() {
            return this.mAlignMultiple;
        }

        void setOpticalInsets(int i, int i2, int i3, int i4) {
            this.mLeftInset = i;
            this.mTopInset = i2;
            this.mRightInset = i3;
            this.mBottomInset = i4;
        }
    }

    abstract class GridLinearSmoothScroller extends LinearSmoothScroller {
        GridLinearSmoothScroller() {
            super(GridLayoutManager.this.mBaseGridView.getContext());
        }

        @Override // android.support.v7.widget.LinearSmoothScroller, android.support.v7.widget.RecyclerView.SmoothScroller
        protected void onStop() {
            View viewFindViewByPosition = findViewByPosition(getTargetPosition());
            if (viewFindViewByPosition == null) {
                if (getTargetPosition() >= 0) {
                    GridLayoutManager.this.scrollToSelection(getTargetPosition(), 0, false, 0);
                }
                super.onStop();
                return;
            }
            if (GridLayoutManager.this.mFocusPosition != getTargetPosition()) {
                GridLayoutManager.this.mFocusPosition = getTargetPosition();
            }
            if (GridLayoutManager.this.hasFocus()) {
                GridLayoutManager.this.mInSelection = true;
                viewFindViewByPosition.requestFocus();
                GridLayoutManager.this.mInSelection = false;
            }
            GridLayoutManager.this.dispatchChildSelected();
            GridLayoutManager.this.dispatchChildSelectedAndPositioned();
            super.onStop();
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        protected int calculateTimeForScrolling(int i) {
            int iCalculateTimeForScrolling = super.calculateTimeForScrolling(i);
            if (GridLayoutManager.this.mWindowAlignment.mainAxis().getSize() <= 0) {
                return iCalculateTimeForScrolling;
            }
            float size = (30.0f / GridLayoutManager.this.mWindowAlignment.mainAxis().getSize()) * i;
            return ((float) iCalculateTimeForScrolling) < size ? (int) size : iCalculateTimeForScrolling;
        }

        @Override // android.support.v7.widget.LinearSmoothScroller, android.support.v7.widget.RecyclerView.SmoothScroller
        protected void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
            int i;
            int i2;
            if (GridLayoutManager.this.getScrollPosition(view, null, GridLayoutManager.sTwoInts)) {
                if (GridLayoutManager.this.mOrientation == 0) {
                    i = GridLayoutManager.sTwoInts[0];
                    i2 = GridLayoutManager.sTwoInts[1];
                } else {
                    i = GridLayoutManager.sTwoInts[1];
                    i2 = GridLayoutManager.sTwoInts[0];
                }
                action.update(i, i2, calculateTimeForDeceleration((int) Math.sqrt((i * i) + (i2 * i2))), this.mDecelerateInterpolator);
            }
        }
    }

    final class PendingMoveSmoothScroller extends GridLinearSmoothScroller {
        static final int TARGET_UNDEFINED = -2;
        private int mPendingMoves;
        private final boolean mStaggeredGrid;

        PendingMoveSmoothScroller(int i, boolean z) {
            super();
            this.mPendingMoves = i;
            this.mStaggeredGrid = z;
            setTargetPosition(-2);
        }

        void increasePendingMoves() {
            if (this.mPendingMoves < 10) {
                this.mPendingMoves++;
            }
        }

        void decreasePendingMoves() {
            if (this.mPendingMoves > -10) {
                this.mPendingMoves--;
            }
        }

        void consumePendingMovesBeforeLayout() {
            View viewFindViewByPosition;
            if (this.mStaggeredGrid || this.mPendingMoves == 0) {
                return;
            }
            View view = null;
            int i = this.mPendingMoves > 0 ? GridLayoutManager.this.mFocusPosition + GridLayoutManager.this.mNumRows : GridLayoutManager.this.mFocusPosition - GridLayoutManager.this.mNumRows;
            while (this.mPendingMoves != 0 && (viewFindViewByPosition = findViewByPosition(i)) != null) {
                if (GridLayoutManager.this.canScrollTo(viewFindViewByPosition)) {
                    GridLayoutManager.this.mFocusPosition = i;
                    GridLayoutManager.this.mSubFocusPosition = 0;
                    if (this.mPendingMoves > 0) {
                        this.mPendingMoves--;
                    } else {
                        this.mPendingMoves++;
                    }
                    view = viewFindViewByPosition;
                }
                i = this.mPendingMoves > 0 ? i + GridLayoutManager.this.mNumRows : i - GridLayoutManager.this.mNumRows;
            }
            if (view == null || !GridLayoutManager.this.hasFocus()) {
                return;
            }
            GridLayoutManager.this.mInSelection = true;
            view.requestFocus();
            GridLayoutManager.this.mInSelection = false;
        }

        void consumePendingMovesAfterLayout() {
            if (this.mStaggeredGrid && this.mPendingMoves != 0) {
                this.mPendingMoves = GridLayoutManager.this.processSelectionMoves(true, this.mPendingMoves);
            }
            if (this.mPendingMoves == 0 || ((this.mPendingMoves > 0 && GridLayoutManager.this.hasCreatedLastItem()) || (this.mPendingMoves < 0 && GridLayoutManager.this.hasCreatedFirstItem()))) {
                setTargetPosition(GridLayoutManager.this.mFocusPosition);
                stop();
            }
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        protected void updateActionForInterimTarget(RecyclerView.SmoothScroller.Action action) {
            if (this.mPendingMoves == 0) {
                return;
            }
            super.updateActionForInterimTarget(action);
        }

        @Override // android.support.v7.widget.LinearSmoothScroller
        public PointF computeScrollVectorForPosition(int i) {
            if (this.mPendingMoves == 0) {
                return null;
            }
            int i2 = (!GridLayoutManager.this.mReverseFlowPrimary ? this.mPendingMoves < 0 : this.mPendingMoves > 0) ? 1 : -1;
            if (GridLayoutManager.this.mOrientation == 0) {
                return new PointF(i2, 0.0f);
            }
            return new PointF(0.0f, i2);
        }

        @Override // android.support.v17.leanback.widget.GridLayoutManager.GridLinearSmoothScroller, android.support.v7.widget.LinearSmoothScroller, android.support.v7.widget.RecyclerView.SmoothScroller
        protected void onStop() {
            super.onStop();
            this.mPendingMoves = 0;
            GridLayoutManager.this.mPendingMoveSmoothScroller = null;
            View viewFindViewByPosition = findViewByPosition(getTargetPosition());
            if (viewFindViewByPosition != null) {
                GridLayoutManager.this.scrollToView(viewFindViewByPosition, true);
            }
        }
    }

    String getTag() {
        return "GridLayoutManager:" + this.mBaseGridView.getId();
    }

    public GridLayoutManager(BaseGridView baseGridView) {
        this.mBaseGridView = baseGridView;
    }

    public void setOrientation(int i) {
        if (i == 0 || i == 1) {
            this.mOrientation = i;
            this.mOrientationHelper = OrientationHelper.createOrientationHelper(this, this.mOrientation);
            this.mWindowAlignment.setOrientation(i);
            this.mItemAlignment.setOrientation(i);
            this.mForceFullLayout = true;
        }
    }

    public void onRtlPropertiesChanged(int i) {
        if (this.mOrientation == 0) {
            this.mReverseFlowPrimary = i == 1;
            this.mReverseFlowSecondary = false;
        } else {
            this.mReverseFlowSecondary = i == 1;
            this.mReverseFlowPrimary = false;
        }
        this.mWindowAlignment.horizontal.setReversedFlow(i == 1);
    }

    public int getFocusScrollStrategy() {
        return this.mFocusScrollStrategy;
    }

    public void setFocusScrollStrategy(int i) {
        this.mFocusScrollStrategy = i;
    }

    public void setWindowAlignment(int i) {
        this.mWindowAlignment.mainAxis().setWindowAlignment(i);
    }

    public int getWindowAlignment() {
        return this.mWindowAlignment.mainAxis().getWindowAlignment();
    }

    public void setWindowAlignmentOffset(int i) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffset(i);
    }

    public int getWindowAlignmentOffset() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffset();
    }

    public void setWindowAlignmentOffsetPercent(float f) {
        this.mWindowAlignment.mainAxis().setWindowAlignmentOffsetPercent(f);
    }

    public float getWindowAlignmentOffsetPercent() {
        return this.mWindowAlignment.mainAxis().getWindowAlignmentOffsetPercent();
    }

    public void setItemAlignmentOffset(int i) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffset(i);
        updateChildAlignments();
    }

    public int getItemAlignmentOffset() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffset();
    }

    public void setItemAlignmentOffsetWithPadding(boolean z) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetWithPadding(z);
        updateChildAlignments();
    }

    public boolean isItemAlignmentOffsetWithPadding() {
        return this.mItemAlignment.mainAxis().isItemAlignmentOffsetWithPadding();
    }

    public void setItemAlignmentOffsetPercent(float f) {
        this.mItemAlignment.mainAxis().setItemAlignmentOffsetPercent(f);
        updateChildAlignments();
    }

    public float getItemAlignmentOffsetPercent() {
        return this.mItemAlignment.mainAxis().getItemAlignmentOffsetPercent();
    }

    public void setItemAlignmentViewId(int i) {
        this.mItemAlignment.mainAxis().setItemAlignmentViewId(i);
        updateChildAlignments();
    }

    public int getItemAlignmentViewId() {
        return this.mItemAlignment.mainAxis().getItemAlignmentViewId();
    }

    public void setFocusOutAllowed(boolean z, boolean z2) {
        this.mFocusOutFront = z;
        this.mFocusOutEnd = z2;
    }

    public void setFocusOutSideAllowed(boolean z, boolean z2) {
        this.mFocusOutSideStart = z;
        this.mFocusOutSideEnd = z2;
    }

    public void setNumRows(int i) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        this.mNumRowsRequested = i;
    }

    public void setRowHeight(int i) {
        if (i >= 0 || i == -2) {
            this.mRowSizeSecondaryRequested = i;
            return;
        }
        throw new IllegalArgumentException("Invalid row height: " + i);
    }

    public void setItemSpacing(int i) {
        this.mHorizontalSpacing = i;
        this.mVerticalSpacing = i;
        this.mSpacingSecondary = i;
        this.mSpacingPrimary = i;
    }

    public void setVerticalSpacing(int i) {
        if (this.mOrientation == 1) {
            this.mVerticalSpacing = i;
            this.mSpacingPrimary = i;
        } else {
            this.mVerticalSpacing = i;
            this.mSpacingSecondary = i;
        }
    }

    public void setHorizontalSpacing(int i) {
        if (this.mOrientation == 0) {
            this.mHorizontalSpacing = i;
            this.mSpacingPrimary = i;
        } else {
            this.mHorizontalSpacing = i;
            this.mSpacingSecondary = i;
        }
    }

    public int getVerticalSpacing() {
        return this.mVerticalSpacing;
    }

    public int getHorizontalSpacing() {
        return this.mHorizontalSpacing;
    }

    public void setGravity(int i) {
        this.mGravity = i;
    }

    protected boolean hasDoneFirstLayout() {
        return this.mGrid != null;
    }

    public void setOnChildSelectedListener(OnChildSelectedListener onChildSelectedListener) {
        this.mChildSelectedListener = onChildSelectedListener;
    }

    public void setOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        if (onChildViewHolderSelectedListener == null) {
            this.mChildViewHolderSelectedListeners = null;
            return;
        }
        if (this.mChildViewHolderSelectedListeners == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        } else {
            this.mChildViewHolderSelectedListeners.clear();
        }
        this.mChildViewHolderSelectedListeners.add(onChildViewHolderSelectedListener);
    }

    public void addOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        if (this.mChildViewHolderSelectedListeners == null) {
            this.mChildViewHolderSelectedListeners = new ArrayList<>();
        }
        this.mChildViewHolderSelectedListeners.add(onChildViewHolderSelectedListener);
    }

    public void removeOnChildViewHolderSelectedListener(OnChildViewHolderSelectedListener onChildViewHolderSelectedListener) {
        if (this.mChildViewHolderSelectedListeners != null) {
            this.mChildViewHolderSelectedListeners.remove(onChildViewHolderSelectedListener);
        }
    }

    boolean hasOnChildViewHolderSelectedListener() {
        return this.mChildViewHolderSelectedListeners != null && this.mChildViewHolderSelectedListeners.size() > 0;
    }

    void fireOnChildViewHolderSelected(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        if (this.mChildViewHolderSelectedListeners == null) {
            return;
        }
        for (int size = this.mChildViewHolderSelectedListeners.size() - 1; size >= 0; size--) {
            this.mChildViewHolderSelectedListeners.get(size).onChildViewHolderSelected(recyclerView, viewHolder, i, i2);
        }
    }

    void fireOnChildViewHolderSelectedAndPositioned(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        if (this.mChildViewHolderSelectedListeners == null) {
            return;
        }
        for (int size = this.mChildViewHolderSelectedListeners.size() - 1; size >= 0; size--) {
            this.mChildViewHolderSelectedListeners.get(size).onChildViewHolderSelectedAndPositioned(recyclerView, viewHolder, i, i2);
        }
    }

    void setOnChildLaidOutListener(OnChildLaidOutListener onChildLaidOutListener) {
        this.mChildLaidOutListener = onChildLaidOutListener;
    }

    private int getPositionByView(View view) {
        LayoutParams layoutParams;
        if (view == null || (layoutParams = (LayoutParams) view.getLayoutParams()) == null || layoutParams.isItemRemoved()) {
            return -1;
        }
        return layoutParams.getViewPosition();
    }

    int getSubPositionByView(View view, View view2) {
        ItemAlignmentFacet itemAlignmentFacet;
        if (view != null && view2 != null && (itemAlignmentFacet = ((LayoutParams) view.getLayoutParams()).getItemAlignmentFacet()) != null) {
            ItemAlignmentFacet.ItemAlignmentDef[] alignmentDefs = itemAlignmentFacet.getAlignmentDefs();
            if (alignmentDefs.length > 1) {
                while (view2 != view) {
                    int id = view2.getId();
                    if (id != -1) {
                        for (int i = 1; i < alignmentDefs.length; i++) {
                            if (alignmentDefs[i].getItemAlignmentFocusViewId() == id) {
                                return i;
                            }
                        }
                    }
                    view2 = (View) view2.getParent();
                }
            }
        }
        return 0;
    }

    private int getPositionByIndex(int i) {
        return getPositionByView(getChildAt(i));
    }

    void dispatchChildSelected() {
        if (this.mChildSelectedListener != null || hasOnChildViewHolderSelectedListener()) {
            View viewFindViewByPosition = this.mFocusPosition == -1 ? null : findViewByPosition(this.mFocusPosition);
            if (viewFindViewByPosition != null) {
                RecyclerView.ViewHolder childViewHolder = this.mBaseGridView.getChildViewHolder(viewFindViewByPosition);
                if (this.mChildSelectedListener != null) {
                    this.mChildSelectedListener.onChildSelected(this.mBaseGridView, viewFindViewByPosition, this.mFocusPosition, childViewHolder == null ? -1L : childViewHolder.getItemId());
                }
                fireOnChildViewHolderSelected(this.mBaseGridView, childViewHolder, this.mFocusPosition, this.mSubFocusPosition);
            } else {
                if (this.mChildSelectedListener != null) {
                    this.mChildSelectedListener.onChildSelected(this.mBaseGridView, null, -1, -1L);
                }
                fireOnChildViewHolderSelected(this.mBaseGridView, null, -1, 0);
            }
            if (this.mInLayout || this.mBaseGridView.isLayoutRequested()) {
                return;
            }
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                if (getChildAt(i).isLayoutRequested()) {
                    forceRequestLayout();
                    return;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchChildSelectedAndPositioned() {
        if (hasOnChildViewHolderSelectedListener()) {
            View viewFindViewByPosition = this.mFocusPosition == -1 ? null : findViewByPosition(this.mFocusPosition);
            if (viewFindViewByPosition != null) {
                fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, this.mBaseGridView.getChildViewHolder(viewFindViewByPosition), this.mFocusPosition, this.mSubFocusPosition);
                return;
            }
            if (this.mChildSelectedListener != null) {
                this.mChildSelectedListener.onChildSelected(this.mBaseGridView, null, -1, -1L);
            }
            fireOnChildViewHolderSelectedAndPositioned(this.mBaseGridView, null, -1, 0);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0 || this.mNumRows > 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean canScrollVertically() {
        return this.mOrientation == 1 || this.mNumRows > 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) layoutParams);
        }
        if (layoutParams instanceof RecyclerView.LayoutParams) {
            return new LayoutParams((RecyclerView.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    protected View getViewForPosition(int i) {
        return this.mRecycler.getViewForPosition(i);
    }

    final int getOpticalLeft(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalLeft(view);
    }

    final int getOpticalRight(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalRight(view);
    }

    final int getOpticalTop(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalTop(view);
    }

    final int getOpticalBottom(View view) {
        return ((LayoutParams) view.getLayoutParams()).getOpticalBottom(view);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getDecoratedLeft(View view) {
        return super.getDecoratedLeft(view) + ((LayoutParams) view.getLayoutParams()).mLeftInset;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getDecoratedTop(View view) {
        return super.getDecoratedTop(view) + ((LayoutParams) view.getLayoutParams()).mTopInset;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getDecoratedRight(View view) {
        return super.getDecoratedRight(view) - ((LayoutParams) view.getLayoutParams()).mRightInset;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getDecoratedBottom(View view) {
        return super.getDecoratedBottom(view) - ((LayoutParams) view.getLayoutParams()).mBottomInset;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        rect.left += layoutParams.mLeftInset;
        rect.top += layoutParams.mTopInset;
        rect.right -= layoutParams.mRightInset;
        rect.bottom -= layoutParams.mBottomInset;
    }

    int getViewMin(View view) {
        return this.mOrientationHelper.getDecoratedStart(view);
    }

    int getViewMax(View view) {
        return this.mOrientationHelper.getDecoratedEnd(view);
    }

    int getViewPrimarySize(View view) {
        getDecoratedBoundsWithMargins(view, sTempRect);
        return this.mOrientation == 0 ? sTempRect.width() : sTempRect.height();
    }

    private int getViewCenter(View view) {
        return this.mOrientation == 0 ? getViewCenterX(view) : getViewCenterY(view);
    }

    private int getViewCenterSecondary(View view) {
        return this.mOrientation == 0 ? getViewCenterY(view) : getViewCenterX(view);
    }

    private int getViewCenterX(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.getOpticalLeft(view) + layoutParams.getAlignX();
    }

    private int getViewCenterY(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return layoutParams.getOpticalTop(view) + layoutParams.getAlignY();
    }

    private void saveContext(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mRecycler != null || this.mState != null) {
            Log.e(TAG, "Recycler information was not released, bug!");
        }
        this.mRecycler = recycler;
        this.mState = state;
    }

    private void leaveContext() {
        this.mRecycler = null;
        this.mState = null;
    }

    private boolean layoutInit() {
        boolean z = this.mGrid != null && this.mFocusPosition >= 0 && this.mFocusPosition >= this.mGrid.getFirstVisibleIndex() && this.mFocusPosition <= this.mGrid.getLastVisibleIndex();
        int itemCount = this.mState.getItemCount();
        if (itemCount == 0) {
            this.mFocusPosition = -1;
            this.mSubFocusPosition = 0;
        } else if (this.mFocusPosition >= itemCount) {
            this.mFocusPosition = itemCount - 1;
            this.mSubFocusPosition = 0;
        } else if (this.mFocusPosition == -1 && itemCount > 0) {
            this.mFocusPosition = 0;
            this.mSubFocusPosition = 0;
        }
        if (!this.mState.didStructureChange() && this.mGrid != null && this.mGrid.getFirstVisibleIndex() >= 0 && !this.mForceFullLayout && this.mGrid.getNumRows() == this.mNumRows) {
            updateScrollController();
            updateScrollSecondAxis();
            this.mGrid.setSpacing(this.mSpacingPrimary);
            if (!z && this.mFocusPosition != -1) {
                this.mGrid.setStart(this.mFocusPosition);
            }
            return true;
        }
        this.mForceFullLayout = false;
        int firstVisibleIndex = z ? this.mGrid.getFirstVisibleIndex() : 0;
        if (this.mGrid == null || this.mNumRows != this.mGrid.getNumRows() || this.mReverseFlowPrimary != this.mGrid.isReversedFlow()) {
            this.mGrid = Grid.createGrid(this.mNumRows);
            this.mGrid.setProvider(this.mGridProvider);
            this.mGrid.setReversedFlow(this.mReverseFlowPrimary);
        }
        initScrollController();
        updateScrollSecondAxis();
        this.mGrid.setSpacing(this.mSpacingPrimary);
        detachAndScrapAttachedViews(this.mRecycler);
        this.mGrid.resetVisibleIndex();
        this.mWindowAlignment.mainAxis().invalidateScrollMin();
        this.mWindowAlignment.mainAxis().invalidateScrollMax();
        if (z && firstVisibleIndex <= this.mFocusPosition) {
            this.mGrid.setStart(firstVisibleIndex);
        } else {
            this.mGrid.setStart(this.mFocusPosition);
        }
        return false;
    }

    private int getRowSizeSecondary(int i) {
        if (this.mFixedRowSizeSecondary != 0) {
            return this.mFixedRowSizeSecondary;
        }
        if (this.mRowSizeSecondary == null) {
            return 0;
        }
        return this.mRowSizeSecondary[i];
    }

    int getRowStartSecondary(int i) {
        int rowSizeSecondary = 0;
        if (this.mReverseFlowSecondary) {
            for (int i2 = this.mNumRows - 1; i2 > i; i2--) {
                rowSizeSecondary += getRowSizeSecondary(i2) + this.mSpacingSecondary;
            }
            return rowSizeSecondary;
        }
        int rowSizeSecondary2 = 0;
        while (rowSizeSecondary < i) {
            rowSizeSecondary2 += getRowSizeSecondary(rowSizeSecondary) + this.mSpacingSecondary;
            rowSizeSecondary++;
        }
        return rowSizeSecondary2;
    }

    private int getSizeSecondary() {
        int i = this.mReverseFlowSecondary ? 0 : this.mNumRows - 1;
        return getRowStartSecondary(i) + getRowSizeSecondary(i);
    }

    int getDecoratedMeasuredWidthWithMargin(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredWidth(view) + layoutParams.leftMargin + layoutParams.rightMargin;
    }

    int getDecoratedMeasuredHeightWithMargin(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return getDecoratedMeasuredHeight(view) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    private void measureScrapChild(int i, int i2, int i3, int[] iArr) {
        View viewForPosition = this.mRecycler.getViewForPosition(i);
        if (viewForPosition != null) {
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            calculateItemDecorationsForChild(viewForPosition, sTempRect);
            viewForPosition.measure(ViewGroup.getChildMeasureSpec(i2, getPaddingLeft() + getPaddingRight() + layoutParams.leftMargin + layoutParams.rightMargin + sTempRect.left + sTempRect.right, layoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingTop() + getPaddingBottom() + layoutParams.topMargin + layoutParams.bottomMargin + sTempRect.top + sTempRect.bottom, layoutParams.height));
            iArr[0] = getDecoratedMeasuredWidthWithMargin(viewForPosition);
            iArr[1] = getDecoratedMeasuredHeightWithMargin(viewForPosition);
            this.mRecycler.recycleView(viewForPosition);
        }
    }

    private boolean processRowSizeSecondary(boolean z) {
        int i;
        int i2;
        int decoratedMeasuredWidthWithMargin;
        if (this.mFixedRowSizeSecondary != 0 || this.mRowSizeSecondary == null) {
            return false;
        }
        CircularIntArray[] itemPositionsInRows = this.mGrid == null ? null : this.mGrid.getItemPositionsInRows();
        int i3 = -1;
        int i4 = -1;
        boolean z2 = false;
        for (int i5 = 0; i5 < this.mNumRows; i5++) {
            CircularIntArray circularIntArray = itemPositionsInRows == null ? null : itemPositionsInRows[i5];
            int size = circularIntArray == null ? 0 : circularIntArray.size();
            int i6 = -1;
            for (int i7 = 0; i7 < size; i7 += 2) {
                int i8 = circularIntArray.get(i7 + 1);
                for (int i9 = circularIntArray.get(i7); i9 <= i8; i9++) {
                    View viewFindViewByPosition = findViewByPosition(i9);
                    if (viewFindViewByPosition != null) {
                        if (z) {
                            measureChild(viewFindViewByPosition);
                        }
                        if (this.mOrientation == 0) {
                            decoratedMeasuredWidthWithMargin = getDecoratedMeasuredHeightWithMargin(viewFindViewByPosition);
                        } else {
                            decoratedMeasuredWidthWithMargin = getDecoratedMeasuredWidthWithMargin(viewFindViewByPosition);
                        }
                        if (decoratedMeasuredWidthWithMargin > i6) {
                            i6 = decoratedMeasuredWidthWithMargin;
                        }
                    }
                }
            }
            int itemCount = this.mState.getItemCount();
            if (this.mBaseGridView.hasFixedSize() || !z || i6 >= 0 || itemCount <= 0) {
                i = i6;
            } else {
                if (i3 < 0 && i4 < 0) {
                    if (this.mFocusPosition == -1) {
                        i2 = 0;
                    } else {
                        i2 = this.mFocusPosition >= itemCount ? itemCount - 1 : this.mFocusPosition;
                    }
                    measureScrapChild(i2, View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0), this.mMeasuredDimension);
                    i3 = this.mMeasuredDimension[0];
                    i4 = this.mMeasuredDimension[1];
                }
                i = this.mOrientation == 0 ? i4 : i3;
            }
            if (i < 0) {
                i = 0;
            }
            if (this.mRowSizeSecondary[i5] != i) {
                this.mRowSizeSecondary[i5] = i;
                z2 = true;
            }
        }
        return z2;
    }

    private void updateRowSecondarySizeRefresh() {
        this.mRowSecondarySizeRefresh = processRowSizeSecondary(false);
        if (this.mRowSecondarySizeRefresh) {
            forceRequestLayout();
        }
    }

    private void forceRequestLayout() {
        ViewCompat.postOnAnimation(this.mBaseGridView, this.mRequestLayoutRunnable);
    }

    /* JADX WARN: Code duplicated, block: B:45:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:47:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:48:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:50:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:51:0x00ef  */
    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int i, int i2) {
        int size;
        int size2;
        int mode;
        int paddingLeft;
        int i3;
        saveContext(recycler, state);
        if (this.mOrientation == 0) {
            size2 = View.MeasureSpec.getSize(i);
            size = View.MeasureSpec.getSize(i2);
            mode = View.MeasureSpec.getMode(i2);
            paddingLeft = getPaddingTop() + getPaddingBottom();
        } else {
            size = View.MeasureSpec.getSize(i);
            size2 = View.MeasureSpec.getSize(i2);
            mode = View.MeasureSpec.getMode(i);
            paddingLeft = getPaddingLeft() + getPaddingRight();
        }
        this.mMaxSizeSecondary = size;
        if (this.mRowSizeSecondaryRequested == -2) {
            this.mNumRows = this.mNumRowsRequested == 0 ? 1 : this.mNumRowsRequested;
            this.mFixedRowSizeSecondary = 0;
            if (this.mRowSizeSecondary == null || this.mRowSizeSecondary.length != this.mNumRows) {
                this.mRowSizeSecondary = new int[this.mNumRows];
            }
            processRowSizeSecondary(true);
            if (mode == Integer.MIN_VALUE) {
                size = Math.min(getSizeSecondary() + paddingLeft, this.mMaxSizeSecondary);
            } else if (mode == 0) {
                size = getSizeSecondary() + paddingLeft;
            } else if (mode == 1073741824) {
                size = this.mMaxSizeSecondary;
            } else {
                throw new IllegalStateException("wrong spec");
            }
        } else if (mode == Integer.MIN_VALUE) {
            if (this.mNumRowsRequested != 0 && this.mRowSizeSecondaryRequested == 0) {
                this.mNumRows = 1;
                this.mFixedRowSizeSecondary = size - paddingLeft;
            } else if (this.mNumRowsRequested == 0) {
                this.mFixedRowSizeSecondary = this.mRowSizeSecondaryRequested;
                this.mNumRows = (this.mSpacingSecondary + size) / (this.mRowSizeSecondaryRequested + this.mSpacingSecondary);
            } else if (this.mRowSizeSecondaryRequested == 0) {
                this.mNumRows = this.mNumRowsRequested;
                this.mFixedRowSizeSecondary = ((size - paddingLeft) - (this.mSpacingSecondary * (this.mNumRows - 1))) / this.mNumRows;
            } else {
                this.mNumRows = this.mNumRowsRequested;
                this.mFixedRowSizeSecondary = this.mRowSizeSecondaryRequested;
            }
            if (mode == Integer.MIN_VALUE && (i3 = (this.mFixedRowSizeSecondary * this.mNumRows) + (this.mSpacingSecondary * (this.mNumRows - 1)) + paddingLeft) < size) {
                size = i3;
            }
        } else if (mode == 0) {
            this.mFixedRowSizeSecondary = this.mRowSizeSecondaryRequested == 0 ? size - paddingLeft : this.mRowSizeSecondaryRequested;
            this.mNumRows = this.mNumRowsRequested == 0 ? 1 : this.mNumRowsRequested;
            size = (this.mFixedRowSizeSecondary * this.mNumRows) + (this.mSpacingSecondary * (this.mNumRows - 1)) + paddingLeft;
        } else {
            if (mode != 1073741824) {
                throw new IllegalStateException("wrong spec");
            }
            if (this.mNumRowsRequested != 0) {
                if (this.mNumRowsRequested == 0) {
                    this.mFixedRowSizeSecondary = this.mRowSizeSecondaryRequested;
                    this.mNumRows = (this.mSpacingSecondary + size) / (this.mRowSizeSecondaryRequested + this.mSpacingSecondary);
                } else if (this.mRowSizeSecondaryRequested == 0) {
                    this.mNumRows = this.mNumRowsRequested;
                    this.mFixedRowSizeSecondary = ((size - paddingLeft) - (this.mSpacingSecondary * (this.mNumRows - 1))) / this.mNumRows;
                } else {
                    this.mNumRows = this.mNumRowsRequested;
                    this.mFixedRowSizeSecondary = this.mRowSizeSecondaryRequested;
                }
            } else if (this.mNumRowsRequested == 0) {
                this.mFixedRowSizeSecondary = this.mRowSizeSecondaryRequested;
                this.mNumRows = (this.mSpacingSecondary + size) / (this.mRowSizeSecondaryRequested + this.mSpacingSecondary);
            } else if (this.mRowSizeSecondaryRequested == 0) {
                this.mNumRows = this.mNumRowsRequested;
                this.mFixedRowSizeSecondary = ((size - paddingLeft) - (this.mSpacingSecondary * (this.mNumRows - 1))) / this.mNumRows;
            } else {
                this.mNumRows = this.mNumRowsRequested;
                this.mFixedRowSizeSecondary = this.mRowSizeSecondaryRequested;
            }
            if (mode == Integer.MIN_VALUE) {
                size = i3;
            }
        }
        if (this.mOrientation == 0) {
            setMeasuredDimension(size2, size);
        } else {
            setMeasuredDimension(size, size2);
        }
        leaveContext();
    }

    void measureChild(View view) {
        int iMakeMeasureSpec;
        int childMeasureSpec;
        int childMeasureSpec2;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        calculateItemDecorationsForChild(view, sTempRect);
        int i = layoutParams.leftMargin + layoutParams.rightMargin + sTempRect.left + sTempRect.right;
        int i2 = layoutParams.topMargin + layoutParams.bottomMargin + sTempRect.top + sTempRect.bottom;
        if (this.mRowSizeSecondaryRequested == -2) {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mFixedRowSizeSecondary, 1073741824);
        }
        if (this.mOrientation == 0) {
            childMeasureSpec2 = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i, layoutParams.width);
            childMeasureSpec = ViewGroup.getChildMeasureSpec(iMakeMeasureSpec, i2, layoutParams.height);
        } else {
            int childMeasureSpec3 = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), i2, layoutParams.height);
            int childMeasureSpec4 = ViewGroup.getChildMeasureSpec(iMakeMeasureSpec, i, layoutParams.width);
            childMeasureSpec = childMeasureSpec3;
            childMeasureSpec2 = childMeasureSpec4;
        }
        view.measure(childMeasureSpec2, childMeasureSpec);
    }

    /* JADX WARN: Multi-variable type inference failed */
    <E> E getFacet(RecyclerView.ViewHolder viewHolder, Class<? extends E> cls) {
        FacetProvider facetProvider;
        E e = viewHolder instanceof FacetProvider ? (E) ((FacetProvider) viewHolder).getFacet(cls) : null;
        return (e != null || this.mFacetProviderAdapter == null || (facetProvider = this.mFacetProviderAdapter.getFacetProvider(viewHolder.getItemViewType())) == null) ? e : (E) facetProvider.getFacet(cls);
    }

    void layoutChild(int i, View view, int i2, int i3, int i4) {
        int i5;
        int decoratedMeasuredHeightWithMargin = this.mOrientation == 0 ? getDecoratedMeasuredHeightWithMargin(view) : getDecoratedMeasuredWidthWithMargin(view);
        if (this.mFixedRowSizeSecondary > 0) {
            decoratedMeasuredHeightWithMargin = Math.min(decoratedMeasuredHeightWithMargin, this.mFixedRowSizeSecondary);
        }
        int i6 = this.mGravity & 112;
        int absoluteGravity = (this.mReverseFlowPrimary || this.mReverseFlowSecondary) ? Gravity.getAbsoluteGravity(this.mGravity & GravityCompat.RELATIVE_HORIZONTAL_GRAVITY_MASK, 1) : this.mGravity & 7;
        if ((this.mOrientation != 0 || i6 != 48) && (this.mOrientation != 1 || absoluteGravity != 3)) {
            if ((this.mOrientation == 0 && i6 == 80) || (this.mOrientation == 1 && absoluteGravity == 5)) {
                i4 += getRowSizeSecondary(i) - decoratedMeasuredHeightWithMargin;
            } else if ((this.mOrientation == 0 && i6 == 16) || (this.mOrientation == 1 && absoluteGravity == 1)) {
                i4 += (getRowSizeSecondary(i) - decoratedMeasuredHeightWithMargin) / 2;
            }
        }
        if (this.mOrientation == 0) {
            i3 = i4 + decoratedMeasuredHeightWithMargin;
            i5 = i3;
        } else {
            i5 = i4 + decoratedMeasuredHeightWithMargin;
            int i7 = i4;
            i4 = i2;
            i2 = i7;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutDecoratedWithMargins(view, i2, i4, i5, i3);
        super.getDecoratedBoundsWithMargins(view, sTempRect);
        layoutParams.setOpticalInsets(i2 - sTempRect.left, i4 - sTempRect.top, sTempRect.right - i5, sTempRect.bottom - i3);
        updateChildAlignments(view);
    }

    private void updateChildAlignments(View view) {
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (layoutParams.getItemAlignmentFacet() == null) {
            layoutParams.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(view));
            layoutParams.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(view));
            return;
        }
        layoutParams.calculateItemAlignments(this.mOrientation, view);
        if (this.mOrientation == 0) {
            layoutParams.setAlignY(this.mItemAlignment.vertical.getAlignmentPosition(view));
        } else {
            layoutParams.setAlignX(this.mItemAlignment.horizontal.getAlignmentPosition(view));
        }
    }

    private void updateChildAlignments() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            updateChildAlignments(getChildAt(i));
        }
    }

    void setExtraLayoutSpace(int i) {
        if (this.mExtraLayoutSpace == i) {
            return;
        }
        if (this.mExtraLayoutSpace < 0) {
            throw new IllegalArgumentException("ExtraLayoutSpace must >= 0");
        }
        this.mExtraLayoutSpace = i;
        requestLayout();
    }

    int getExtraLayoutSpace() {
        return this.mExtraLayoutSpace;
    }

    private void removeInvisibleViewsAtEnd() {
        int i;
        if (!this.mPruneChild || this.mIsSlidingChildViews) {
            return;
        }
        Grid grid = this.mGrid;
        int i2 = this.mFocusPosition;
        if (this.mReverseFlowPrimary) {
            i = -this.mExtraLayoutSpace;
        } else {
            i = this.mExtraLayoutSpace + this.mSizePrimary;
        }
        grid.removeInvisibleItemsAtEnd(i2, i);
    }

    private void removeInvisibleViewsAtFront() {
        if (!this.mPruneChild || this.mIsSlidingChildViews) {
            return;
        }
        this.mGrid.removeInvisibleItemsAtFront(this.mFocusPosition, this.mReverseFlowPrimary ? this.mSizePrimary + this.mExtraLayoutSpace : -this.mExtraLayoutSpace);
    }

    private boolean appendOneColumnVisibleItems() {
        return this.mGrid.appendOneColumnVisibleItems();
    }

    void slideIn() {
        if (this.mIsSlidingChildViews) {
            this.mIsSlidingChildViews = false;
            scrollToSelection(this.mFocusPosition, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
            if (this.mLayoutEatenInSliding) {
                this.mLayoutEatenInSliding = false;
                if (this.mBaseGridView.getScrollState() != 0 || isSmoothScrolling()) {
                    this.mBaseGridView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: android.support.v17.leanback.widget.GridLayoutManager.3
                        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
                        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                            if (i == 0) {
                                GridLayoutManager.this.mBaseGridView.removeOnScrollListener(this);
                                GridLayoutManager.this.requestLayout();
                            }
                        }
                    });
                } else {
                    requestLayout();
                }
            }
        }
    }

    void slideOut() {
        int width;
        if (this.mIsSlidingChildViews) {
            return;
        }
        this.mIsSlidingChildViews = true;
        if (this.mOrientation == 1) {
            int i = -getHeight();
            int top = getChildAt(0).getTop();
            if (top < 0) {
                i += top;
            }
            this.mBaseGridView.smoothScrollBy(0, i, new AccelerateDecelerateInterpolator());
            return;
        }
        if (this.mReverseFlowPrimary) {
            width = getWidth();
            int right = getChildAt(0).getRight();
            if (right > width) {
                width = right;
            }
        } else {
            width = -getWidth();
            int left = getChildAt(0).getLeft();
            if (left < 0) {
                width += left;
            }
        }
        this.mBaseGridView.smoothScrollBy(width, 0, new AccelerateDecelerateInterpolator());
    }

    private boolean prependOneColumnVisibleItems() {
        return this.mGrid.prependOneColumnVisibleItems();
    }

    private void appendVisibleItems() {
        int i;
        Grid grid = this.mGrid;
        if (this.mReverseFlowPrimary) {
            i = -this.mExtraLayoutSpace;
        } else {
            i = this.mExtraLayoutSpace + this.mSizePrimary;
        }
        grid.appendVisibleItems(i);
    }

    private void prependVisibleItems() {
        this.mGrid.prependVisibleItems(this.mReverseFlowPrimary ? this.mSizePrimary + this.mExtraLayoutSpace : -this.mExtraLayoutSpace);
    }

    private void fastRelayout() {
        boolean z;
        int positionByIndex;
        int decoratedMeasuredHeightWithMargin;
        int i;
        int childCount = getChildCount();
        int i2 = -1;
        int i3 = 0;
        while (true) {
            z = true;
            if (i3 >= childCount) {
                z = false;
                positionByIndex = i2;
                break;
            }
            View childAt = getChildAt(i3);
            positionByIndex = getPositionByIndex(i3);
            Grid.Location location = this.mGrid.getLocation(positionByIndex);
            if (location == null) {
                break;
            }
            int rowStartSecondary = getRowStartSecondary(location.row) - this.mScrollOffsetSecondary;
            int viewMin = getViewMin(childAt);
            int viewPrimarySize = getViewPrimarySize(childAt);
            if (((LayoutParams) childAt.getLayoutParams()).viewNeedsUpdate()) {
                int iIndexOfChild = this.mBaseGridView.indexOfChild(childAt);
                detachAndScrapView(childAt, this.mRecycler);
                childAt = getViewForPosition(positionByIndex);
                addView(childAt, iIndexOfChild);
            }
            View view = childAt;
            measureChild(view);
            if (this.mOrientation == 0) {
                decoratedMeasuredHeightWithMargin = getDecoratedMeasuredWidthWithMargin(view);
                i = viewMin + decoratedMeasuredHeightWithMargin;
            } else {
                decoratedMeasuredHeightWithMargin = getDecoratedMeasuredHeightWithMargin(view);
                i = viewMin + decoratedMeasuredHeightWithMargin;
            }
            layoutChild(location.row, view, viewMin, i, rowStartSecondary);
            if (viewPrimarySize != decoratedMeasuredHeightWithMargin) {
                break;
            }
            i3++;
            i2 = positionByIndex;
        }
        if (z) {
            int lastVisibleIndex = this.mGrid.getLastVisibleIndex();
            this.mGrid.invalidateItemsAfter(positionByIndex);
            if (this.mPruneChild) {
                appendVisibleItems();
                if (this.mFocusPosition >= 0 && this.mFocusPosition <= lastVisibleIndex) {
                    while (this.mGrid.getLastVisibleIndex() < this.mFocusPosition) {
                        this.mGrid.appendOneColumnVisibleItems();
                    }
                }
            } else {
                while (this.mGrid.appendOneColumnVisibleItems() && this.mGrid.getLastVisibleIndex() < lastVisibleIndex) {
                }
            }
        }
        updateScrollMin();
        updateScrollMax();
        updateScrollSecondAxis();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void removeAndRecycleAllViews(RecyclerView.Recycler recycler) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            removeAndRecycleViewAt(childCount, recycler);
        }
    }

    private void scrollToFocusViewInLayout(boolean z, boolean z2) {
        View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
        if (viewFindViewByPosition != null && z2) {
            scrollToView(viewFindViewByPosition, false);
        }
        if (viewFindViewByPosition != null && z && !viewFindViewByPosition.hasFocus()) {
            viewFindViewByPosition.requestFocus();
            return;
        }
        if (z || this.mBaseGridView.hasFocus()) {
            return;
        }
        if (viewFindViewByPosition != null && viewFindViewByPosition.hasFocusable()) {
            this.mBaseGridView.focusableViewAvailable(viewFindViewByPosition);
            return;
        }
        int childCount = getChildCount();
        View childAt = viewFindViewByPosition;
        for (int i = 0; i < childCount; i++) {
            childAt = getChildAt(i);
            if (childAt != null && childAt.hasFocusable()) {
                this.mBaseGridView.focusableViewAvailable(childAt);
                break;
            }
        }
        if (z2 && childAt != null && childAt.hasFocus()) {
            scrollToView(childAt, false);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        if (this.mLayoutCompleteListener != null) {
            this.mLayoutCompleteListener.onLayoutCompleted(state);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i;
        int i2;
        if (this.mNumRows != 0 && state.getItemCount() >= 0) {
            if (this.mIsSlidingChildViews) {
                this.mLayoutEatenInSliding = true;
                return;
            }
            if (!this.mLayoutEnabled) {
                discardLayoutInfo();
                removeAndRecycleAllViews(recycler);
                return;
            }
            this.mInLayout = true;
            if (state.didStructureChange()) {
                this.mBaseGridView.stopScroll();
            }
            boolean z = !isSmoothScrolling() && this.mFocusScrollStrategy == 0;
            if (this.mFocusPosition != -1 && this.mFocusPositionOffset != Integer.MIN_VALUE) {
                this.mFocusPosition += this.mFocusPositionOffset;
                this.mSubFocusPosition = 0;
            }
            this.mFocusPositionOffset = 0;
            saveContext(recycler, state);
            View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
            int i3 = this.mFocusPosition;
            int i4 = this.mSubFocusPosition;
            boolean zHasFocus = this.mBaseGridView.hasFocus();
            if (this.mFocusPosition == -1 || !z || this.mBaseGridView.getScrollState() == 0 || viewFindViewByPosition == null || !getScrollPosition(viewFindViewByPosition, viewFindViewByPosition.findFocus(), sTwoInts)) {
                i = 0;
                i2 = 0;
            } else {
                i = sTwoInts[0];
                i2 = sTwoInts[1];
            }
            boolean zLayoutInit = layoutInit();
            this.mInFastRelayout = zLayoutInit;
            if (zLayoutInit) {
                fastRelayout();
                if (this.mFocusPosition != -1) {
                    scrollToFocusViewInLayout(zHasFocus, z);
                }
            } else {
                this.mInLayoutSearchFocus = zHasFocus;
                if (this.mFocusPosition != -1) {
                    while (appendOneColumnVisibleItems() && findViewByPosition(this.mFocusPosition) == null) {
                    }
                }
                while (true) {
                    updateScrollMin();
                    updateScrollMax();
                    int firstVisibleIndex = this.mGrid.getFirstVisibleIndex();
                    int lastVisibleIndex = this.mGrid.getLastVisibleIndex();
                    scrollToFocusViewInLayout(zHasFocus, true);
                    appendVisibleItems();
                    prependVisibleItems();
                    removeInvisibleViewsAtFront();
                    removeInvisibleViewsAtEnd();
                    if (this.mGrid.getFirstVisibleIndex() == firstVisibleIndex && this.mGrid.getLastVisibleIndex() == lastVisibleIndex) {
                        break;
                    }
                }
            }
            if (z) {
                scrollDirectionPrimary(-i);
                scrollDirectionSecondary(-i2);
            }
            appendVisibleItems();
            prependVisibleItems();
            removeInvisibleViewsAtFront();
            removeInvisibleViewsAtEnd();
            if (this.mRowSecondarySizeRefresh) {
                this.mRowSecondarySizeRefresh = false;
            } else {
                updateRowSecondarySizeRefresh();
            }
            if (this.mInFastRelayout && (this.mFocusPosition != i3 || this.mSubFocusPosition != i4 || findViewByPosition(this.mFocusPosition) != viewFindViewByPosition)) {
                dispatchChildSelected();
            } else if (!this.mInFastRelayout && this.mInLayoutSearchFocus) {
                dispatchChildSelected();
            }
            dispatchChildSelectedAndPositioned();
            this.mInLayout = false;
            leaveContext();
        }
    }

    private void offsetChildrenSecondary(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        if (this.mOrientation == 0) {
            while (i2 < childCount) {
                getChildAt(i2).offsetTopAndBottom(i);
                i2++;
            }
        } else {
            while (i2 < childCount) {
                getChildAt(i2).offsetLeftAndRight(i);
                i2++;
            }
        }
    }

    private void offsetChildrenPrimary(int i) {
        int childCount = getChildCount();
        int i2 = 0;
        if (this.mOrientation == 1) {
            while (i2 < childCount) {
                getChildAt(i2).offsetTopAndBottom(i);
                i2++;
            }
        } else {
            while (i2 < childCount) {
                getChildAt(i2).offsetLeftAndRight(i);
                i2++;
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int iScrollDirectionSecondary;
        if (!this.mLayoutEnabled || !hasDoneFirstLayout()) {
            return 0;
        }
        saveContext(recycler, state);
        this.mInScroll = true;
        if (this.mOrientation == 0) {
            iScrollDirectionSecondary = scrollDirectionPrimary(i);
        } else {
            iScrollDirectionSecondary = scrollDirectionSecondary(i);
        }
        leaveContext();
        this.mInScroll = false;
        return iScrollDirectionSecondary;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int iScrollDirectionSecondary;
        if (!this.mLayoutEnabled || !hasDoneFirstLayout()) {
            return 0;
        }
        this.mInScroll = true;
        saveContext(recycler, state);
        if (this.mOrientation == 1) {
            iScrollDirectionSecondary = scrollDirectionPrimary(i);
        } else {
            iScrollDirectionSecondary = scrollDirectionSecondary(i);
        }
        leaveContext();
        this.mInScroll = false;
        return iScrollDirectionSecondary;
    }

    private int scrollDirectionPrimary(int i) {
        int minScroll;
        int maxScroll;
        if (!this.mIsSlidingChildViews) {
            if (i > 0) {
                if (!this.mWindowAlignment.mainAxis().isMaxUnknown() && this.mScrollOffsetPrimary + i > (maxScroll = this.mWindowAlignment.mainAxis().getMaxScroll())) {
                    i = maxScroll - this.mScrollOffsetPrimary;
                }
            } else if (i < 0 && !this.mWindowAlignment.mainAxis().isMinUnknown() && this.mScrollOffsetPrimary + i < (minScroll = this.mWindowAlignment.mainAxis().getMinScroll())) {
                i = minScroll - this.mScrollOffsetPrimary;
            }
        }
        if (i == 0) {
            return 0;
        }
        offsetChildrenPrimary(-i);
        this.mScrollOffsetPrimary += i;
        if (this.mInLayout) {
            return i;
        }
        int childCount = getChildCount();
        if (!this.mReverseFlowPrimary ? i < 0 : i > 0) {
            prependVisibleItems();
        } else {
            appendVisibleItems();
        }
        boolean z = getChildCount() > childCount;
        int childCount2 = getChildCount();
        if (!this.mReverseFlowPrimary ? i < 0 : i > 0) {
            removeInvisibleViewsAtEnd();
        } else {
            removeInvisibleViewsAtFront();
        }
        if ((getChildCount() < childCount2) | z) {
            updateRowSecondarySizeRefresh();
        }
        this.mBaseGridView.invalidate();
        return i;
    }

    private int scrollDirectionSecondary(int i) {
        if (i == 0) {
            return 0;
        }
        offsetChildrenSecondary(-i);
        this.mScrollOffsetSecondary += i;
        this.mBaseGridView.invalidate();
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void collectAdjacentPrefetchPositions(int i, int i2, RecyclerView.State state, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        try {
            saveContext(null, state);
            if (this.mOrientation != 0) {
                i = i2;
            }
            if (getChildCount() != 0 && i != 0) {
                this.mGrid.collectAdjacentPrefetchPositions(i < 0 ? -this.mExtraLayoutSpace : this.mSizePrimary + this.mExtraLayoutSpace, i, layoutPrefetchRegistry);
            }
        } finally {
            leaveContext();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void collectInitialPrefetchPositions(int i, RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int i2 = this.mBaseGridView.mInitialPrefetchItemCount;
        if (i == 0 || i2 == 0) {
            return;
        }
        int iMax = Math.max(0, Math.min(this.mFocusPosition - ((i2 - 1) / 2), i - i2));
        for (int i3 = iMax; i3 < i && i3 < iMax + i2; i3++) {
            layoutPrefetchRegistry.addPosition(i3, 0);
        }
    }

    void updateScrollMax() {
        int lastVisibleIndex = !this.mReverseFlowPrimary ? this.mGrid.getLastVisibleIndex() : this.mGrid.getFirstVisibleIndex();
        int itemCount = !this.mReverseFlowPrimary ? this.mState.getItemCount() - 1 : 0;
        if (lastVisibleIndex < 0) {
            return;
        }
        boolean z = lastVisibleIndex == itemCount;
        boolean zIsMaxUnknown = this.mWindowAlignment.mainAxis().isMaxUnknown();
        if (z || !zIsMaxUnknown) {
            int iFindRowMax = this.mGrid.findRowMax(true, sTwoInts) + this.mScrollOffsetPrimary;
            int i = sTwoInts[0];
            int i2 = sTwoInts[1];
            int maxEdge = this.mWindowAlignment.mainAxis().getMaxEdge();
            this.mWindowAlignment.mainAxis().setMaxEdge(iFindRowMax);
            int primarySystemScrollPositionOfChildMax = getPrimarySystemScrollPositionOfChildMax(findViewByPosition(i2));
            this.mWindowAlignment.mainAxis().setMaxEdge(maxEdge);
            if (z) {
                this.mWindowAlignment.mainAxis().setMaxEdge(iFindRowMax);
                this.mWindowAlignment.mainAxis().setMaxScroll(primarySystemScrollPositionOfChildMax);
            } else {
                this.mWindowAlignment.mainAxis().invalidateScrollMax();
            }
        }
    }

    void updateScrollMin() {
        int firstVisibleIndex = !this.mReverseFlowPrimary ? this.mGrid.getFirstVisibleIndex() : this.mGrid.getLastVisibleIndex();
        int itemCount = !this.mReverseFlowPrimary ? 0 : this.mState.getItemCount() - 1;
        if (firstVisibleIndex < 0) {
            return;
        }
        boolean z = firstVisibleIndex == itemCount;
        boolean zIsMinUnknown = this.mWindowAlignment.mainAxis().isMinUnknown();
        if (z || !zIsMinUnknown) {
            int iFindRowMin = this.mGrid.findRowMin(false, sTwoInts) + this.mScrollOffsetPrimary;
            int i = sTwoInts[0];
            int i2 = sTwoInts[1];
            int minEdge = this.mWindowAlignment.mainAxis().getMinEdge();
            this.mWindowAlignment.mainAxis().setMinEdge(iFindRowMin);
            int primarySystemScrollPosition = getPrimarySystemScrollPosition(findViewByPosition(i2));
            this.mWindowAlignment.mainAxis().setMinEdge(minEdge);
            if (z) {
                this.mWindowAlignment.mainAxis().setMinEdge(iFindRowMin);
                this.mWindowAlignment.mainAxis().setMinScroll(primarySystemScrollPosition);
            } else {
                this.mWindowAlignment.mainAxis().invalidateScrollMin();
            }
        }
    }

    private void updateScrollSecondAxis() {
        this.mWindowAlignment.secondAxis().setMinEdge(0);
        this.mWindowAlignment.secondAxis().setMaxEdge(getSizeSecondary());
    }

    private void initScrollController() {
        this.mWindowAlignment.reset();
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
        this.mScrollOffsetPrimary = -this.mWindowAlignment.mainAxis().getPaddingLow();
        this.mScrollOffsetSecondary = -this.mWindowAlignment.secondAxis().getPaddingLow();
    }

    private void updateScrollController() {
        int paddingTop;
        int paddingLeft;
        if (this.mOrientation == 0) {
            paddingTop = getPaddingLeft() - this.mWindowAlignment.horizontal.getPaddingLow();
            paddingLeft = getPaddingTop() - this.mWindowAlignment.vertical.getPaddingLow();
        } else {
            paddingTop = getPaddingTop() - this.mWindowAlignment.vertical.getPaddingLow();
            paddingLeft = getPaddingLeft() - this.mWindowAlignment.horizontal.getPaddingLow();
        }
        this.mScrollOffsetPrimary -= paddingTop;
        this.mScrollOffsetSecondary -= paddingLeft;
        this.mWindowAlignment.horizontal.setSize(getWidth());
        this.mWindowAlignment.vertical.setSize(getHeight());
        this.mWindowAlignment.horizontal.setPadding(getPaddingLeft(), getPaddingRight());
        this.mWindowAlignment.vertical.setPadding(getPaddingTop(), getPaddingBottom());
        this.mSizePrimary = this.mWindowAlignment.mainAxis().getSize();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void scrollToPosition(int i) {
        setSelection(i, 0, false, 0);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        setSelection(i, 0, true, 0);
    }

    public void setSelection(int i, int i2) {
        setSelection(i, 0, false, i2);
    }

    public void setSelectionSmooth(int i) {
        setSelection(i, 0, true, 0);
    }

    public void setSelectionWithSub(int i, int i2, int i3) {
        setSelection(i, i2, false, i3);
    }

    public void setSelectionSmoothWithSub(int i, int i2) {
        setSelection(i, i2, true, 0);
    }

    public int getSelection() {
        return this.mFocusPosition;
    }

    public int getSubSelection() {
        return this.mSubFocusPosition;
    }

    public void setSelection(int i, int i2, boolean z, int i3) {
        if ((this.mFocusPosition == i || i == -1) && i2 == this.mSubFocusPosition && i3 == this.mPrimaryScrollExtra) {
            return;
        }
        scrollToSelection(i, i2, z, i3);
    }

    void scrollToSelection(int i, int i2, boolean z, int i3) {
        this.mPrimaryScrollExtra = i3;
        View viewFindViewByPosition = findViewByPosition(i);
        if (viewFindViewByPosition != null) {
            this.mInSelection = true;
            scrollToView(viewFindViewByPosition, z);
            this.mInSelection = false;
            return;
        }
        this.mFocusPosition = i;
        this.mSubFocusPosition = i2;
        this.mFocusPositionOffset = Integer.MIN_VALUE;
        if (!this.mLayoutEnabled || this.mIsSlidingChildViews) {
            return;
        }
        if (z) {
            if (!hasDoneFirstLayout()) {
                Log.w(getTag(), "setSelectionSmooth should not be called before first layout pass");
                return;
            }
            int iStartPositionSmoothScroller = startPositionSmoothScroller(i);
            if (iStartPositionSmoothScroller != this.mFocusPosition) {
                this.mFocusPosition = iStartPositionSmoothScroller;
                this.mSubFocusPosition = 0;
                return;
            }
            return;
        }
        this.mForceFullLayout = true;
        requestLayout();
    }

    int startPositionSmoothScroller(int i) {
        GridLinearSmoothScroller gridLinearSmoothScroller = new GridLinearSmoothScroller() { // from class: android.support.v17.leanback.widget.GridLayoutManager.4
            @Override // android.support.v7.widget.LinearSmoothScroller
            public PointF computeScrollVectorForPosition(int i2) {
                if (getChildCount() == 0) {
                    return null;
                }
                boolean z = false;
                int position = GridLayoutManager.this.getPosition(GridLayoutManager.this.getChildAt(0));
                if (!GridLayoutManager.this.mReverseFlowPrimary ? i2 < position : i2 > position) {
                    z = true;
                }
                int i3 = z ? -1 : 1;
                if (GridLayoutManager.this.mOrientation == 0) {
                    return new PointF(i3, 0.0f);
                }
                return new PointF(0.0f, i3);
            }
        };
        gridLinearSmoothScroller.setTargetPosition(i);
        startSmoothScroll(gridLinearSmoothScroller);
        return gridLinearSmoothScroller.getTargetPosition();
    }

    private void processPendingMovement(boolean z) {
        if (z) {
            if (hasCreatedLastItem()) {
                return;
            }
        } else if (hasCreatedFirstItem()) {
            return;
        }
        if (this.mPendingMoveSmoothScroller != null) {
            if (z) {
                this.mPendingMoveSmoothScroller.increasePendingMoves();
                return;
            } else {
                this.mPendingMoveSmoothScroller.decreasePendingMoves();
                return;
            }
        }
        this.mBaseGridView.stopScroll();
        PendingMoveSmoothScroller pendingMoveSmoothScroller = new PendingMoveSmoothScroller(z ? 1 : -1, this.mNumRows > 1);
        this.mFocusPositionOffset = 0;
        startSmoothScroll(pendingMoveSmoothScroller);
        if (pendingMoveSmoothScroller.isRunning()) {
            this.mPendingMoveSmoothScroller = pendingMoveSmoothScroller;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsAdded(RecyclerView recyclerView, int i, int i2) {
        if (this.mFocusPosition != -1 && this.mGrid != null && this.mGrid.getFirstVisibleIndex() >= 0 && this.mFocusPositionOffset != Integer.MIN_VALUE && i <= this.mFocusPosition + this.mFocusPositionOffset) {
            this.mFocusPositionOffset += i2;
        }
        this.mChildrenStates.clear();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsChanged(RecyclerView recyclerView) {
        this.mFocusPositionOffset = 0;
        this.mChildrenStates.clear();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsRemoved(RecyclerView recyclerView, int i, int i2) {
        int i3;
        if (this.mFocusPosition != -1 && this.mGrid != null && this.mGrid.getFirstVisibleIndex() >= 0 && this.mFocusPositionOffset != Integer.MIN_VALUE && i <= (i3 = this.mFocusPosition + this.mFocusPositionOffset)) {
            if (i + i2 > i3) {
                this.mFocusPositionOffset = Integer.MIN_VALUE;
            } else {
                this.mFocusPositionOffset -= i2;
            }
        }
        this.mChildrenStates.clear();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsMoved(RecyclerView recyclerView, int i, int i2, int i3) {
        if (this.mFocusPosition != -1 && this.mFocusPositionOffset != Integer.MIN_VALUE) {
            int i4 = this.mFocusPosition + this.mFocusPositionOffset;
            if (i <= i4 && i4 < i + i3) {
                this.mFocusPositionOffset += i2 - i;
            } else if (i < i4 && i2 > i4 - i3) {
                this.mFocusPositionOffset -= i3;
            } else if (i > i4 && i2 < i4) {
                this.mFocusPositionOffset += i3;
            }
        }
        this.mChildrenStates.clear();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onItemsUpdated(RecyclerView recyclerView, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            this.mChildrenStates.remove(i);
            i++;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
        if (!this.mFocusSearchDisabled && getPositionByView(view) != -1 && !this.mInLayout && !this.mInSelection && !this.mInScroll) {
            scrollToView(view, view2, true);
        }
        return true;
    }

    int getScrollOffsetX() {
        return this.mOrientation == 0 ? this.mScrollOffsetPrimary : this.mScrollOffsetSecondary;
    }

    int getScrollOffsetY() {
        return this.mOrientation == 0 ? this.mScrollOffsetSecondary : this.mScrollOffsetPrimary;
    }

    public void getViewSelectedOffsets(View view, int[] iArr) {
        if (this.mOrientation == 0) {
            iArr[0] = getPrimarySystemScrollPosition(view) - this.mScrollOffsetPrimary;
            iArr[1] = getSecondarySystemScrollPosition(view) - this.mScrollOffsetSecondary;
        } else {
            iArr[1] = getPrimarySystemScrollPosition(view) - this.mScrollOffsetPrimary;
            iArr[0] = getSecondarySystemScrollPosition(view) - this.mScrollOffsetSecondary;
        }
    }

    private int getPrimarySystemScrollPosition(View view) {
        boolean z;
        boolean z2;
        int viewCenter = this.mScrollOffsetPrimary + getViewCenter(view);
        int viewMin = getViewMin(view);
        int viewMax = getViewMax(view);
        if (!this.mReverseFlowPrimary) {
            z2 = this.mGrid.getFirstVisibleIndex() == 0;
            z = this.mGrid.getLastVisibleIndex() == (this.mState == null ? getItemCount() : this.mState.getItemCount()) - 1;
        } else {
            z = this.mGrid.getFirstVisibleIndex() == 0;
            z2 = this.mGrid.getLastVisibleIndex() == (this.mState == null ? getItemCount() : this.mState.getItemCount()) - 1;
        }
        int childCount = getChildCount() - 1;
        while (true) {
            if ((!z2 && !z) || childCount < 0) {
                break;
            }
            View childAt = getChildAt(childCount);
            if (childAt != view && childAt != null) {
                if (z2 && getViewMin(childAt) < viewMin) {
                    z2 = false;
                }
                if (z && getViewMax(childAt) > viewMax) {
                    z = false;
                }
            }
            childCount--;
        }
        return this.mWindowAlignment.mainAxis().getSystemScrollPos(viewCenter, z2, z);
    }

    private int getPrimarySystemScrollPositionOfChildMax(View view) {
        int primarySystemScrollPosition = getPrimarySystemScrollPosition(view);
        int[] alignMultiple = ((LayoutParams) view.getLayoutParams()).getAlignMultiple();
        return (alignMultiple == null || alignMultiple.length <= 0) ? primarySystemScrollPosition : primarySystemScrollPosition + (alignMultiple[alignMultiple.length - 1] - alignMultiple[0]);
    }

    private int getAdjustedPrimaryScrollPosition(int i, View view, View view2) {
        int subPositionByView = getSubPositionByView(view, view2);
        if (subPositionByView == 0) {
            return i;
        }
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        return i + (layoutParams.getAlignMultiple()[subPositionByView] - layoutParams.getAlignMultiple()[0]);
    }

    private int getSecondarySystemScrollPosition(View view) {
        boolean z;
        int viewCenterSecondary = this.mScrollOffsetSecondary + getViewCenterSecondary(view);
        int i = this.mGrid.getLocation(getPositionByView(view)).row;
        boolean z2 = false;
        if (!this.mReverseFlowSecondary) {
            z = i == 0;
            if (i == this.mGrid.getNumRows() - 1) {
                z2 = true;
            }
        } else {
            z2 = i == 0;
            z = i == this.mGrid.getNumRows() - 1;
        }
        return this.mWindowAlignment.secondAxis().getSystemScrollPos(viewCenterSecondary, z, z2);
    }

    void scrollToView(View view, boolean z) {
        scrollToView(view, view == null ? null : view.findFocus(), z);
    }

    private void scrollToView(View view, View view2, boolean z) {
        if (this.mIsSlidingChildViews) {
            return;
        }
        int positionByView = getPositionByView(view);
        int subPositionByView = getSubPositionByView(view, view2);
        if (positionByView != this.mFocusPosition || subPositionByView != this.mSubFocusPosition) {
            this.mFocusPosition = positionByView;
            this.mSubFocusPosition = subPositionByView;
            this.mFocusPositionOffset = 0;
            if (!this.mInLayout) {
                dispatchChildSelected();
            }
            if (this.mBaseGridView.isChildrenDrawingOrderEnabledInternal()) {
                this.mBaseGridView.invalidate();
            }
        }
        if (view == null) {
            return;
        }
        if (!view.hasFocus() && this.mBaseGridView.hasFocus()) {
            view.requestFocus();
        }
        if ((this.mScrollEnabled || !z) && getScrollPosition(view, view2, sTwoInts)) {
            scrollGrid(sTwoInts[0], sTwoInts[1], z);
        }
    }

    boolean getScrollPosition(View view, View view2, int[] iArr) {
        switch (this.mFocusScrollStrategy) {
            case 1:
            case 2:
                return getNoneAlignedPosition(view, iArr);
            default:
                return getAlignedPosition(view, view2, iArr);
        }
    }

    private boolean getNoneAlignedPosition(View view, int[] iArr) {
        View viewFindViewByPosition;
        int viewMax;
        int positionByView = getPositionByView(view);
        int viewMin = getViewMin(view);
        int viewMax2 = getViewMax(view);
        int paddingLow = this.mWindowAlignment.mainAxis().getPaddingLow();
        int clientSize = this.mWindowAlignment.mainAxis().getClientSize();
        int rowIndex = this.mGrid.getRowIndex(positionByView);
        View viewFindViewByPosition2 = null;
        if (viewMin < paddingLow) {
            if (this.mFocusScrollStrategy == 2) {
                View view2 = view;
                while (true) {
                    if (!prependOneColumnVisibleItems()) {
                        viewFindViewByPosition = null;
                        viewFindViewByPosition2 = view2;
                        break;
                    }
                    CircularIntArray circularIntArray = this.mGrid.getItemPositionsInRows(this.mGrid.getFirstVisibleIndex(), positionByView)[rowIndex];
                    View viewFindViewByPosition3 = findViewByPosition(circularIntArray.get(0));
                    if (viewMax2 - getViewMin(viewFindViewByPosition3) > clientSize) {
                        if (circularIntArray.size() <= 2) {
                            viewFindViewByPosition = null;
                            viewFindViewByPosition2 = viewFindViewByPosition3;
                            break;
                        }
                        viewFindViewByPosition = null;
                        viewFindViewByPosition2 = findViewByPosition(circularIntArray.get(2));
                        break;
                    }
                    view2 = viewFindViewByPosition3;
                }
            } else {
                viewFindViewByPosition = null;
                viewFindViewByPosition2 = view;
            }
        } else if (viewMax2 <= clientSize + paddingLow) {
            viewFindViewByPosition = null;
        } else if (this.mFocusScrollStrategy == 2) {
            do {
                CircularIntArray circularIntArray2 = this.mGrid.getItemPositionsInRows(positionByView, this.mGrid.getLastVisibleIndex())[rowIndex];
                viewFindViewByPosition = findViewByPosition(circularIntArray2.get(circularIntArray2.size() - 1));
                if (getViewMax(viewFindViewByPosition) - viewMin > clientSize) {
                    viewFindViewByPosition = null;
                    break;
                }
            } while (appendOneColumnVisibleItems());
            if (viewFindViewByPosition == null) {
                viewFindViewByPosition2 = view;
            }
        } else {
            viewFindViewByPosition = view;
        }
        if (viewFindViewByPosition2 != null) {
            viewMax = getViewMin(viewFindViewByPosition2) - paddingLow;
        } else {
            viewMax = viewFindViewByPosition != null ? getViewMax(viewFindViewByPosition) - (paddingLow + clientSize) : 0;
        }
        if (viewFindViewByPosition2 != null) {
            view = viewFindViewByPosition2;
        } else if (viewFindViewByPosition != null) {
            view = viewFindViewByPosition;
        }
        int secondarySystemScrollPosition = getSecondarySystemScrollPosition(view) - this.mScrollOffsetSecondary;
        if (viewMax == 0 && secondarySystemScrollPosition == 0) {
            return false;
        }
        iArr[0] = viewMax;
        iArr[1] = secondarySystemScrollPosition;
        return true;
    }

    private boolean getAlignedPosition(View view, View view2, int[] iArr) {
        int primarySystemScrollPosition = getPrimarySystemScrollPosition(view);
        if (view2 != null) {
            primarySystemScrollPosition = getAdjustedPrimaryScrollPosition(primarySystemScrollPosition, view, view2);
        }
        int secondarySystemScrollPosition = getSecondarySystemScrollPosition(view);
        int i = primarySystemScrollPosition - this.mScrollOffsetPrimary;
        int i2 = secondarySystemScrollPosition - this.mScrollOffsetSecondary;
        int i3 = i + this.mPrimaryScrollExtra;
        if (i3 == 0 && i2 == 0) {
            return false;
        }
        iArr[0] = i3;
        iArr[1] = i2;
        return true;
    }

    private void scrollGrid(int i, int i2, boolean z) {
        if (this.mInLayout) {
            scrollDirectionPrimary(i);
            scrollDirectionSecondary(i2);
            return;
        }
        if (this.mOrientation != 0) {
            i2 = i;
            i = i2;
        }
        if (z) {
            this.mBaseGridView.smoothScrollBy(i, i2);
        } else {
            this.mBaseGridView.scrollBy(i, i2);
            dispatchChildSelectedAndPositioned();
        }
    }

    public void setPruneChild(boolean z) {
        if (this.mPruneChild != z) {
            this.mPruneChild = z;
            if (this.mPruneChild) {
                requestLayout();
            }
        }
    }

    public boolean getPruneChild() {
        return this.mPruneChild;
    }

    public void setScrollEnabled(boolean z) {
        if (this.mScrollEnabled != z) {
            this.mScrollEnabled = z;
            if (this.mScrollEnabled && this.mFocusScrollStrategy == 0 && this.mFocusPosition != -1) {
                scrollToSelection(this.mFocusPosition, this.mSubFocusPosition, true, this.mPrimaryScrollExtra);
            }
        }
    }

    public boolean isScrollEnabled() {
        return this.mScrollEnabled;
    }

    private int findImmediateChildIndex(View view) {
        View viewFindContainingItemView;
        if (this.mBaseGridView == null || view == this.mBaseGridView || (viewFindContainingItemView = findContainingItemView(view)) == null) {
            return -1;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i) == viewFindContainingItemView) {
                return i;
            }
        }
        return -1;
    }

    void onFocusChanged(boolean z, int i, Rect rect) {
        if (!z) {
            return;
        }
        int i2 = this.mFocusPosition;
        while (true) {
            View viewFindViewByPosition = findViewByPosition(i2);
            if (viewFindViewByPosition == null) {
                return;
            }
            if (viewFindViewByPosition.getVisibility() == 0 && viewFindViewByPosition.hasFocusable()) {
                viewFindViewByPosition.requestFocus();
                return;
            }
            i2++;
        }
    }

    void setFocusSearchDisabled(boolean z) {
        this.mFocusSearchDisabled = z;
    }

    boolean isFocusSearchDisabled() {
        return this.mFocusSearchDisabled;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public View onInterceptFocusSearch(View view, int i) {
        View viewFindNextFocus;
        if (this.mFocusSearchDisabled) {
            return view;
        }
        FocusFinder focusFinder = FocusFinder.getInstance();
        View viewFindNextFocus2 = null;
        if (i == 2 || i == 1) {
            if (canScrollVertically()) {
                viewFindNextFocus2 = focusFinder.findNextFocus(this.mBaseGridView, view, i == 2 ? 130 : 33);
            }
            if (canScrollHorizontally()) {
                viewFindNextFocus = focusFinder.findNextFocus(this.mBaseGridView, view, (getLayoutDirection() == 1) ^ (i == 2) ? 66 : 17);
            } else {
                viewFindNextFocus = viewFindNextFocus2;
            }
        } else {
            viewFindNextFocus = focusFinder.findNextFocus(this.mBaseGridView, view, i);
        }
        if (viewFindNextFocus != null) {
            return viewFindNextFocus;
        }
        if (this.mBaseGridView.getDescendantFocusability() == 393216) {
            return this.mBaseGridView.getParent().focusSearch(view, i);
        }
        int movement = getMovement(i);
        boolean z = this.mBaseGridView.getScrollState() != 0;
        if (movement == 1) {
            if (z || !this.mFocusOutEnd) {
                viewFindNextFocus = view;
            }
            if (this.mScrollEnabled && !hasCreatedLastItem()) {
                processPendingMovement(true);
                viewFindNextFocus = view;
            }
        } else if (movement == 0) {
            if (z || !this.mFocusOutFront) {
                viewFindNextFocus = view;
            }
            if (this.mScrollEnabled && !hasCreatedFirstItem()) {
                processPendingMovement(false);
                viewFindNextFocus = view;
            }
        } else if (movement == 3) {
        }
        if (viewFindNextFocus != null) {
            return viewFindNextFocus;
        }
        View viewFocusSearch = this.mBaseGridView.getParent().focusSearch(view, i);
        if (viewFocusSearch != null) {
            return viewFocusSearch;
        }
        return view != null ? view : this.mBaseGridView;
    }

    boolean hasPreviousViewInSameRow(int i) {
        if (this.mGrid == null || i == -1 || this.mGrid.getFirstVisibleIndex() < 0) {
            return false;
        }
        if (this.mGrid.getFirstVisibleIndex() > 0) {
            return true;
        }
        int i2 = this.mGrid.getLocation(i).row;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            int positionByIndex = getPositionByIndex(childCount);
            Grid.Location location = this.mGrid.getLocation(positionByIndex);
            if (location != null && location.row == i2 && positionByIndex < i) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x0088  */
    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i, int i2) {
        View childAt;
        if (this.mFocusSearchDisabled) {
            return true;
        }
        int childCount = 0;
        if (recyclerView.hasFocus()) {
            if (this.mPendingMoveSmoothScroller != null) {
                return true;
            }
            int movement = getMovement(i);
            int iFindImmediateChildIndex = findImmediateChildIndex(recyclerView.findFocus());
            int positionByIndex = getPositionByIndex(iFindImmediateChildIndex);
            int i3 = -1;
            if (positionByIndex != -1) {
                findViewByPosition(positionByIndex).addFocusables(arrayList, i, i2);
            }
            if (this.mGrid == null || getChildCount() == 0) {
                return true;
            }
            if ((movement == 3 || movement == 2) && this.mGrid.getNumRows() <= 1) {
                return true;
            }
            int i4 = (this.mGrid == null || positionByIndex == -1) ? -1 : this.mGrid.getLocation(positionByIndex).row;
            int size = arrayList.size();
            int i5 = (movement == 1 || movement == 3) ? 1 : -1;
            int childCount2 = i5 > 0 ? getChildCount() - 1 : 0;
            if (iFindImmediateChildIndex != -1) {
                childCount = iFindImmediateChildIndex + i5;
            } else if (i5 <= 0) {
                childCount = getChildCount() - 1;
            }
            while (true) {
                if (i5 <= 0) {
                    if (childCount < childCount2) {
                        break;
                    }
                    childAt = getChildAt(childCount);
                    if (childAt.getVisibility() != 0) {
                    }
                    childCount += i5;
                    i3 = -1;
                } else {
                    if (childCount > childCount2) {
                        break;
                    }
                    childAt = getChildAt(childCount);
                    if (childAt.getVisibility() != 0 && childAt.hasFocusable()) {
                        if (positionByIndex == i3) {
                            childAt.addFocusables(arrayList, i, i2);
                            if (arrayList.size() > size) {
                                break;
                            }
                        } else {
                            int positionByIndex2 = getPositionByIndex(childCount);
                            Grid.Location location = this.mGrid.getLocation(positionByIndex2);
                            if (location == null) {
                                continue;
                            } else if (movement == 1) {
                                if (location.row == i4 && positionByIndex2 > positionByIndex) {
                                    childAt.addFocusables(arrayList, i, i2);
                                    if (arrayList.size() > size) {
                                        break;
                                    }
                                }
                            } else if (movement == 0) {
                                if (location.row == i4 && positionByIndex2 < positionByIndex) {
                                    childAt.addFocusables(arrayList, i, i2);
                                    if (arrayList.size() > size) {
                                        break;
                                    }
                                }
                            } else if (movement == 3) {
                                if (location.row == i4) {
                                    continue;
                                } else {
                                    if (location.row < i4) {
                                        break;
                                    }
                                    childAt.addFocusables(arrayList, i, i2);
                                }
                            } else if (movement == 2 && location.row != i4) {
                                if (location.row > i4) {
                                    break;
                                }
                                childAt.addFocusables(arrayList, i, i2);
                            }
                        }
                    }
                    childCount += i5;
                    i3 = -1;
                }
            }
        } else {
            int size2 = arrayList.size();
            if (this.mFocusScrollStrategy != 0) {
                int paddingLow = this.mWindowAlignment.mainAxis().getPaddingLow();
                int clientSize = this.mWindowAlignment.mainAxis().getClientSize() + paddingLow;
                int childCount3 = getChildCount();
                for (int i6 = 0; i6 < childCount3; i6++) {
                    View childAt2 = getChildAt(i6);
                    if (childAt2.getVisibility() == 0 && getViewMin(childAt2) >= paddingLow && getViewMax(childAt2) <= clientSize) {
                        childAt2.addFocusables(arrayList, i, i2);
                    }
                }
                if (arrayList.size() == size2) {
                    int childCount4 = getChildCount();
                    while (childCount < childCount4) {
                        View childAt3 = getChildAt(childCount);
                        if (childAt3.getVisibility() == 0) {
                            childAt3.addFocusables(arrayList, i, i2);
                        }
                        childCount++;
                    }
                }
            } else {
                View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
                if (viewFindViewByPosition != null) {
                    viewFindViewByPosition.addFocusables(arrayList, i, i2);
                }
            }
            if (arrayList.size() == size2 && recyclerView.isFocusable()) {
                arrayList.add(recyclerView);
            }
        }
        return true;
    }

    boolean hasCreatedLastItem() {
        int itemCount = getItemCount();
        return itemCount == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(itemCount - 1) != null;
    }

    boolean hasCreatedFirstItem() {
        return getItemCount() == 0 || this.mBaseGridView.findViewHolderForAdapterPosition(0) != null;
    }

    boolean canScrollTo(View view) {
        return view.getVisibility() == 0 && (!hasFocus() || view.hasFocusable());
    }

    boolean gridOnRequestFocusInDescendants(RecyclerView recyclerView, int i, Rect rect) {
        switch (this.mFocusScrollStrategy) {
            case 1:
            case 2:
                return gridOnRequestFocusInDescendantsUnaligned(recyclerView, i, rect);
            default:
                return gridOnRequestFocusInDescendantsAligned(recyclerView, i, rect);
        }
    }

    private boolean gridOnRequestFocusInDescendantsAligned(RecyclerView recyclerView, int i, Rect rect) {
        View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
        if (viewFindViewByPosition != null) {
            return viewFindViewByPosition.requestFocus(i, rect);
        }
        return false;
    }

    private boolean gridOnRequestFocusInDescendantsUnaligned(RecyclerView recyclerView, int i, Rect rect) {
        int i2;
        int i3;
        int childCount = getChildCount();
        int i4 = -1;
        if ((i & 2) != 0) {
            i4 = childCount;
            i2 = 0;
            i3 = 1;
        } else {
            i2 = childCount - 1;
            i3 = -1;
        }
        int paddingLow = this.mWindowAlignment.mainAxis().getPaddingLow();
        int clientSize = this.mWindowAlignment.mainAxis().getClientSize() + paddingLow;
        while (i2 != i4) {
            View childAt = getChildAt(i2);
            if (childAt.getVisibility() == 0 && getViewMin(childAt) >= paddingLow && getViewMax(childAt) <= clientSize && childAt.requestFocus(i, rect)) {
                return true;
            }
            i2 += i3;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0032, code lost:
    
        if (r8.mReverseFlowSecondary == false) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0039, code lost:
    
        if (r8.mReverseFlowSecondary == false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
    
        return 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0015, code lost:
    
        if (r9 != 130) goto L27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int getMovement(int r9) {
        /*
            r8 = this;
            int r0 = r8.mOrientation
            r1 = 130(0x82, float:1.82E-43)
            r2 = 66
            r3 = 33
            r4 = 2
            r5 = 1
            r6 = 3
            r7 = 17
            if (r0 != 0) goto L23
            if (r9 == r7) goto L20
            if (r9 == r3) goto L1e
            if (r9 == r2) goto L1a
            if (r9 == r1) goto L18
            goto L3c
        L18:
            r5 = r6
            goto L3d
        L1a:
            boolean r8 = r8.mReverseFlowPrimary
            r4 = r8 ^ 1
        L1e:
            r5 = r4
            goto L3d
        L20:
            boolean r4 = r8.mReverseFlowPrimary
            goto L1e
        L23:
            int r0 = r8.mOrientation
            if (r0 != r5) goto L3c
            if (r9 == r7) goto L37
            if (r9 == r3) goto L35
            if (r9 == r2) goto L30
            if (r9 == r1) goto L3d
            goto L3c
        L30:
            boolean r8 = r8.mReverseFlowSecondary
            if (r8 != 0) goto L1e
            goto L18
        L35:
            r5 = 0
            goto L3d
        L37:
            boolean r8 = r8.mReverseFlowSecondary
            if (r8 != 0) goto L18
            goto L1e
        L3c:
            r5 = r7
        L3d:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v17.leanback.widget.GridLayoutManager.getMovement(int):int");
    }

    int getChildDrawingOrder(RecyclerView recyclerView, int i, int i2) {
        int iIndexOfChild;
        View viewFindViewByPosition = findViewByPosition(this.mFocusPosition);
        if (viewFindViewByPosition != null && i2 >= (iIndexOfChild = recyclerView.indexOfChild(viewFindViewByPosition))) {
            return i2 < i + (-1) ? ((iIndexOfChild + i) - 1) - i2 : iIndexOfChild;
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        if (adapter != null) {
            discardLayoutInfo();
            this.mFocusPosition = -1;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.clear();
        }
        if (adapter2 instanceof FacetProviderAdapter) {
            this.mFacetProviderAdapter = (FacetProviderAdapter) adapter2;
        } else {
            this.mFacetProviderAdapter = null;
        }
        super.onAdapterChanged(adapter, adapter2);
    }

    private void discardLayoutInfo() {
        this.mGrid = null;
        this.mRowSizeSecondary = null;
        this.mRowSecondarySizeRefresh = false;
    }

    public void setLayoutEnabled(boolean z) {
        if (this.mLayoutEnabled != z) {
            this.mLayoutEnabled = z;
            requestLayout();
        }
    }

    void setChildrenVisibility(int i) {
        this.mChildVisibility = i;
        if (this.mChildVisibility != -1) {
            int childCount = getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                getChildAt(i2).setVisibility(this.mChildVisibility);
            }
        }
    }

    static final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: android.support.v17.leanback.widget.GridLayoutManager.SavedState.1
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
        Bundle childStates;
        int index;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.index);
            parcel.writeBundle(this.childStates);
        }

        SavedState(Parcel parcel) {
            this.childStates = Bundle.EMPTY;
            this.index = parcel.readInt();
            this.childStates = parcel.readBundle(GridLayoutManager.class.getClassLoader());
        }

        SavedState() {
            this.childStates = Bundle.EMPTY;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState();
        savedState.index = getSelection();
        Bundle bundleSaveAsBundle = this.mChildrenStates.saveAsBundle();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int positionByView = getPositionByView(childAt);
            if (positionByView != -1) {
                bundleSaveAsBundle = this.mChildrenStates.saveOnScreenView(bundleSaveAsBundle, childAt, positionByView);
            }
        }
        savedState.childStates = bundleSaveAsBundle;
        return savedState;
    }

    void onChildRecycled(RecyclerView.ViewHolder viewHolder) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (adapterPosition != -1) {
            this.mChildrenStates.saveOffscreenView(viewHolder.itemView, adapterPosition);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mFocusPosition = savedState.index;
            this.mFocusPositionOffset = 0;
            this.mChildrenStates.loadFromBundle(savedState.childStates);
            this.mForceFullLayout = true;
            requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0 && this.mGrid != null) {
            return this.mGrid.getNumRows();
        }
        return super.getRowCountForAccessibility(recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1 && this.mGrid != null) {
            return this.mGrid.getNumRows();
        }
        return super.getColumnCountForAccessibility(recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (this.mGrid == null || !(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(recycler, state, view, accessibilityNodeInfoCompat);
            return;
        }
        int viewLayoutPosition = ((LayoutParams) layoutParams).getViewLayoutPosition();
        int rowIndex = this.mGrid.getRowIndex(viewLayoutPosition);
        int numRows = viewLayoutPosition / this.mGrid.getNumRows();
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(rowIndex, 1, numRows, 1, false, false));
        } else {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain(numRows, 1, rowIndex, 1, false, false));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean performAccessibilityAction(RecyclerView.Recycler recycler, RecyclerView.State state, int i, Bundle bundle) {
        saveContext(recycler, state);
        if (i == 4096) {
            processSelectionMoves(false, this.mState.getItemCount());
        } else if (i == 8192) {
            processSelectionMoves(false, -this.mState.getItemCount());
        }
        leaveContext();
        return true;
    }

    int processSelectionMoves(boolean z, int i) {
        if (this.mGrid == null) {
            return i;
        }
        int i2 = this.mFocusPosition;
        int rowIndex = i2 != -1 ? this.mGrid.getRowIndex(i2) : -1;
        View view = null;
        int childCount = getChildCount();
        int i3 = rowIndex;
        int i4 = i2;
        int i5 = i;
        for (int i6 = 0; i6 < childCount && i5 != 0; i6++) {
            int i7 = i5 > 0 ? i6 : (childCount - 1) - i6;
            View childAt = getChildAt(i7);
            if (canScrollTo(childAt)) {
                int positionByIndex = getPositionByIndex(i7);
                int rowIndex2 = this.mGrid.getRowIndex(positionByIndex);
                if (i3 == -1) {
                    i4 = positionByIndex;
                    view = childAt;
                    i3 = rowIndex2;
                } else if (rowIndex2 == i3 && ((i5 > 0 && positionByIndex > i4) || (i5 < 0 && positionByIndex < i4))) {
                    i5 = i5 > 0 ? i5 - 1 : i5 + 1;
                    i4 = positionByIndex;
                    view = childAt;
                }
            }
        }
        if (view != null) {
            if (z) {
                if (hasFocus()) {
                    this.mInSelection = true;
                    view.requestFocus();
                    this.mInSelection = false;
                }
                this.mFocusPosition = i4;
                this.mSubFocusPosition = 0;
            } else {
                scrollToView(view, true);
            }
        }
        return i5;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        saveContext(recycler, state);
        if (this.mScrollEnabled && !hasCreatedFirstItem()) {
            accessibilityNodeInfoCompat.addAction(8192);
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        if (this.mScrollEnabled && !hasCreatedLastItem()) {
            accessibilityNodeInfoCompat.addAction(4096);
            accessibilityNodeInfoCompat.setScrollable(true);
        }
        accessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(getRowCountForAccessibility(recycler, state), getColumnCountForAccessibility(recycler, state), isLayoutHierarchical(recycler, state), getSelectionModeForAccessibility(recycler, state)));
        leaveContext();
    }
}
