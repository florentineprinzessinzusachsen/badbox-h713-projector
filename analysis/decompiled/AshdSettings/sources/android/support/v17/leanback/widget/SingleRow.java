package android.support.v17.leanback.widget;

import android.support.annotation.NonNull;
import android.support.v4.util.CircularIntArray;
import android.support.v7.widget.RecyclerView;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
class SingleRow extends Grid {
    private final Grid.Location mTmpLocation = new Grid.Location(0);
    private Object[] mTmpItem = new Object[1];

    SingleRow() {
        setNumRows(1);
    }

    @Override // android.support.v17.leanback.widget.Grid
    public final Grid.Location getLocation(int i) {
        return this.mTmpLocation;
    }

    @Override // android.support.v17.leanback.widget.Grid
    public final void debugPrint(PrintWriter printWriter) {
        printWriter.print("SingleRow<");
        printWriter.print(this.mFirstVisibleIndex);
        printWriter.print(",");
        printWriter.print(this.mLastVisibleIndex);
        printWriter.print(">");
        printWriter.println();
    }

    int getStartIndexForAppend() {
        if (this.mLastVisibleIndex >= 0) {
            return this.mLastVisibleIndex + 1;
        }
        if (this.mStartIndex != -1) {
            return Math.min(this.mStartIndex, this.mProvider.getCount() - 1);
        }
        return 0;
    }

    int getStartIndexForPrepend() {
        if (this.mFirstVisibleIndex >= 0) {
            return this.mFirstVisibleIndex - 1;
        }
        if (this.mStartIndex != -1) {
            return Math.min(this.mStartIndex, this.mProvider.getCount() - 1);
        }
        return this.mProvider.getCount() - 1;
    }

    @Override // android.support.v17.leanback.widget.Grid
    protected final boolean prependVisibleItems(int i, boolean z) {
        int edge;
        if (this.mProvider.getCount() == 0) {
            return false;
        }
        if (!z && checkPrependOverLimit(i)) {
            return false;
        }
        int startIndexForPrepend = getStartIndexForPrepend();
        boolean z2 = false;
        while (startIndexForPrepend >= 0) {
            int iCreateItem = this.mProvider.createItem(startIndexForPrepend, false, this.mTmpItem);
            if (this.mFirstVisibleIndex < 0 || this.mLastVisibleIndex < 0) {
                edge = this.mReversedFlow ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                this.mFirstVisibleIndex = startIndexForPrepend;
                this.mLastVisibleIndex = startIndexForPrepend;
            } else {
                if (this.mReversedFlow) {
                    edge = this.mProvider.getEdge(startIndexForPrepend + 1) + this.mSpacing + iCreateItem;
                } else {
                    edge = (this.mProvider.getEdge(startIndexForPrepend + 1) - this.mSpacing) - iCreateItem;
                }
                this.mFirstVisibleIndex = startIndexForPrepend;
            }
            this.mProvider.addItem(this.mTmpItem[0], startIndexForPrepend, iCreateItem, 0, edge);
            if (z || checkPrependOverLimit(i)) {
                return true;
            }
            startIndexForPrepend--;
            z2 = true;
        }
        return z2;
    }

    @Override // android.support.v17.leanback.widget.Grid
    protected final boolean appendVisibleItems(int i, boolean z) {
        int edge;
        if (this.mProvider.getCount() == 0) {
            return false;
        }
        if (!z && checkAppendOverLimit(i)) {
            return false;
        }
        int startIndexForAppend = getStartIndexForAppend();
        boolean z2 = false;
        while (startIndexForAppend < this.mProvider.getCount()) {
            int iCreateItem = this.mProvider.createItem(startIndexForAppend, true, this.mTmpItem);
            if (this.mFirstVisibleIndex < 0 || this.mLastVisibleIndex < 0) {
                edge = this.mReversedFlow ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                this.mFirstVisibleIndex = startIndexForAppend;
                this.mLastVisibleIndex = startIndexForAppend;
            } else {
                if (this.mReversedFlow) {
                    int i2 = startIndexForAppend - 1;
                    edge = (this.mProvider.getEdge(i2) - this.mProvider.getSize(i2)) - this.mSpacing;
                } else {
                    int i3 = startIndexForAppend - 1;
                    edge = this.mProvider.getEdge(i3) + this.mProvider.getSize(i3) + this.mSpacing;
                }
                this.mLastVisibleIndex = startIndexForAppend;
            }
            this.mProvider.addItem(this.mTmpItem[0], startIndexForAppend, iCreateItem, 0, edge);
            if (z || checkAppendOverLimit(i)) {
                return true;
            }
            startIndexForAppend++;
            z2 = true;
        }
        return z2;
    }

    @Override // android.support.v17.leanback.widget.Grid
    public void collectAdjacentPrefetchPositions(int i, int i2, @NonNull RecyclerView.LayoutManager.LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int startIndexForPrepend;
        int edge;
        if (!this.mReversedFlow ? i2 < 0 : i2 > 0) {
            if (getFirstVisibleIndex() == 0) {
                return;
            }
            startIndexForPrepend = getStartIndexForPrepend();
            edge = this.mProvider.getEdge(this.mFirstVisibleIndex) + (this.mReversedFlow ? this.mSpacing : -this.mSpacing);
        } else {
            if (getLastVisibleIndex() == this.mProvider.getCount() - 1) {
                return;
            }
            startIndexForPrepend = getStartIndexForAppend();
            int size = this.mProvider.getSize(this.mLastVisibleIndex) + this.mSpacing;
            int edge2 = this.mProvider.getEdge(this.mLastVisibleIndex);
            if (this.mReversedFlow) {
                size = -size;
            }
            edge = size + edge2;
        }
        layoutPrefetchRegistry.addPosition(startIndexForPrepend, Math.abs(edge - i));
    }

    @Override // android.support.v17.leanback.widget.Grid
    public final CircularIntArray[] getItemPositionsInRows(int i, int i2) {
        this.mTmpItemPositionsInRows[0].clear();
        this.mTmpItemPositionsInRows[0].addLast(i);
        this.mTmpItemPositionsInRows[0].addLast(i2);
        return this.mTmpItemPositionsInRows;
    }

    @Override // android.support.v17.leanback.widget.Grid
    protected final int findRowMin(boolean z, int i, int[] iArr) {
        if (iArr != null) {
            iArr[0] = 0;
            iArr[1] = i;
        }
        return this.mReversedFlow ? this.mProvider.getEdge(i) - this.mProvider.getSize(i) : this.mProvider.getEdge(i);
    }

    @Override // android.support.v17.leanback.widget.Grid
    protected final int findRowMax(boolean z, int i, int[] iArr) {
        if (iArr != null) {
            iArr[0] = 0;
            iArr[1] = i;
        }
        if (this.mReversedFlow) {
            return this.mProvider.getEdge(i);
        }
        return this.mProvider.getSize(i) + this.mProvider.getEdge(i);
    }
}
