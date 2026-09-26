package android.support.v17.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
final class StaggeredGridDefault extends StaggeredGrid {
    StaggeredGridDefault() {
    }

    int getRowMax(int i) {
        StaggeredGrid.Location location;
        if (this.mFirstVisibleIndex < 0) {
            return Integer.MIN_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(this.mFirstVisibleIndex);
            if (getLocation(this.mFirstVisibleIndex).row == i) {
                return edge;
            }
            int i2 = this.mFirstVisibleIndex;
            do {
                i2++;
                if (i2 <= getLastIndex()) {
                    location = getLocation(i2);
                    edge += location.offset;
                }
            } while (location.row != i);
            return edge;
        }
        int edge2 = this.mProvider.getEdge(this.mLastVisibleIndex);
        StaggeredGrid.Location location2 = getLocation(this.mLastVisibleIndex);
        if (location2.row == i) {
            return edge2 + location2.size;
        }
        int i3 = this.mLastVisibleIndex;
        do {
            i3--;
            if (i3 >= getFirstIndex()) {
                edge2 -= location2.offset;
                location2 = getLocation(i3);
            }
        } while (location2.row != i);
        return edge2 + location2.size;
        return Integer.MIN_VALUE;
    }

    int getRowMin(int i) {
        StaggeredGrid.Location location;
        if (this.mFirstVisibleIndex < 0) {
            return Integer.MAX_VALUE;
        }
        if (this.mReversedFlow) {
            int edge = this.mProvider.getEdge(this.mLastVisibleIndex);
            StaggeredGrid.Location location2 = getLocation(this.mLastVisibleIndex);
            if (location2.row == i) {
                return edge - location2.size;
            }
            int i2 = this.mLastVisibleIndex;
            do {
                i2--;
                if (i2 >= getFirstIndex()) {
                    edge -= location2.offset;
                    location2 = getLocation(i2);
                }
            } while (location2.row != i);
            return edge - location2.size;
        }
        int edge2 = this.mProvider.getEdge(this.mFirstVisibleIndex);
        if (getLocation(this.mFirstVisibleIndex).row == i) {
            return edge2;
        }
        int i3 = this.mFirstVisibleIndex;
        do {
            i3++;
            if (i3 <= getLastIndex()) {
                location = getLocation(i3);
                edge2 += location.offset;
            }
        } while (location.row != i);
        return edge2;
        return Integer.MAX_VALUE;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0035  */
    /* JADX WARN: Code duplicated, block: B:30:0x006d  */
    @Override // android.support.v17.leanback.widget.Grid
    public int findRowMax(boolean z, int i, int[] iArr) {
        int i2;
        int i3;
        int i4;
        int edge = this.mProvider.getEdge(i);
        StaggeredGrid.Location location = getLocation(i);
        int i5 = location.row;
        if (this.mReversedFlow) {
            i3 = i;
            i4 = edge;
            i2 = i5;
            int i6 = 1;
            for (int i7 = i + 1; i6 < this.mNumRows && i7 <= this.mLastVisibleIndex; i7++) {
                StaggeredGrid.Location location2 = getLocation(i7);
                edge += location2.offset;
                if (location2.row != i5) {
                    i5 = location2.row;
                    i6++;
                    if (z) {
                        if (edge > i4) {
                            i4 = edge;
                            i3 = i7;
                            i2 = i5;
                        }
                    } else if (edge < i4) {
                        i4 = edge;
                        i3 = i7;
                        i2 = i5;
                    }
                }
            }
        } else {
            int i8 = i;
            int size = this.mProvider.getSize(i) + edge;
            int i9 = 1;
            i2 = i5;
            for (int i10 = i - 1; i9 < this.mNumRows && i10 >= this.mFirstVisibleIndex; i10--) {
                edge -= location.offset;
                location = getLocation(i10);
                if (location.row != i5) {
                    i5 = location.row;
                    i9++;
                    int size2 = this.mProvider.getSize(i10) + edge;
                    if (z) {
                        if (size2 > size) {
                            i2 = i5;
                            i8 = i10;
                            size = size2;
                        }
                    } else if (size2 < size) {
                        i2 = i5;
                        i8 = i10;
                        size = size2;
                    }
                }
            }
            i3 = i8;
            i4 = size;
        }
        if (iArr != null) {
            iArr[0] = i2;
            iArr[1] = i3;
        }
        return i4;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0045  */
    /* JADX WARN: Code duplicated, block: B:30:0x006f  */
    @Override // android.support.v17.leanback.widget.Grid
    public int findRowMin(boolean z, int i, int[] iArr) {
        int i2;
        int size;
        int i3;
        int edge = this.mProvider.getEdge(i);
        StaggeredGrid.Location location = getLocation(i);
        int i4 = location.row;
        if (this.mReversedFlow) {
            i2 = i;
            size = edge - this.mProvider.getSize(i);
            int i5 = 1;
            i3 = i4;
            for (int i6 = i - 1; i5 < this.mNumRows && i6 >= this.mFirstVisibleIndex; i6--) {
                edge -= location.offset;
                location = getLocation(i6);
                if (location.row != i4) {
                    i4 = location.row;
                    i5++;
                    int size2 = edge - this.mProvider.getSize(i6);
                    if (z) {
                        if (size2 > size) {
                            i3 = i4;
                            i2 = i6;
                            size = size2;
                        }
                    } else if (size2 < size) {
                        i3 = i4;
                        i2 = i6;
                        size = size2;
                    }
                }
            }
        } else {
            i2 = i;
            size = edge;
            i3 = i4;
            int i7 = 1;
            for (int i8 = i + 1; i7 < this.mNumRows && i8 <= this.mLastVisibleIndex; i8++) {
                StaggeredGrid.Location location2 = getLocation(i8);
                edge += location2.offset;
                if (location2.row != i4) {
                    i4 = location2.row;
                    i7++;
                    if (z) {
                        if (edge > size) {
                            size = edge;
                            i2 = i8;
                            i3 = i4;
                        }
                    } else if (edge < size) {
                        size = edge;
                        i2 = i8;
                        i3 = i4;
                    }
                }
            }
        }
        if (iArr != null) {
            iArr[0] = i3;
            iArr[1] = i2;
        }
        return size;
    }

    private int findRowEdgeLimitSearchIndex(boolean z) {
        boolean z2 = false;
        if (z) {
            for (int i = this.mLastVisibleIndex; i >= this.mFirstVisibleIndex; i--) {
                int i2 = getLocation(i).row;
                if (i2 == 0) {
                    z2 = true;
                } else if (z2 && i2 == this.mNumRows - 1) {
                    return i;
                }
            }
            return -1;
        }
        for (int i3 = this.mFirstVisibleIndex; i3 <= this.mLastVisibleIndex; i3++) {
            int i4 = getLocation(i3).row;
            if (i4 == this.mNumRows - 1) {
                z2 = true;
            } else if (z2 && i4 == 0) {
                return i3;
            }
        }
        return -1;
    }

    @Override // android.support.v17.leanback.widget.StaggeredGrid
    protected boolean appendVisibleItemsWithoutCache(int i, boolean z) {
        int i2;
        int i3;
        boolean z2;
        int iFindRowMin;
        int rowMax;
        int count = this.mProvider.getCount();
        if (this.mLastVisibleIndex >= 0) {
            if (this.mLastVisibleIndex < getLastIndex()) {
                return false;
            }
            i2 = this.mLastVisibleIndex + 1;
            i3 = getLocation(this.mLastVisibleIndex).row;
            int iFindRowEdgeLimitSearchIndex = findRowEdgeLimitSearchIndex(true);
            if (iFindRowEdgeLimitSearchIndex < 0) {
                iFindRowMin = Integer.MIN_VALUE;
                for (int i4 = 0; i4 < this.mNumRows; i4++) {
                    iFindRowMin = this.mReversedFlow ? getRowMin(i4) : getRowMax(i4);
                    if (iFindRowMin != Integer.MIN_VALUE) {
                        break;
                    }
                }
            } else {
                iFindRowMin = this.mReversedFlow ? findRowMin(false, iFindRowEdgeLimitSearchIndex, null) : findRowMax(true, iFindRowEdgeLimitSearchIndex, null);
            }
            if (!this.mReversedFlow ? getRowMax(i3) >= iFindRowMin : getRowMin(i3) <= iFindRowMin) {
                i3++;
                if (i3 == this.mNumRows) {
                    iFindRowMin = this.mReversedFlow ? findRowMin(false, null) : findRowMax(true, null);
                    i3 = 0;
                }
            }
            z2 = true;
        } else {
            i2 = this.mStartIndex != -1 ? this.mStartIndex : 0;
            i3 = (this.mLocations.size() > 0 ? getLocation(getLastIndex()).row + 1 : i2) % this.mNumRows;
            z2 = false;
            iFindRowMin = 0;
        }
        int rowMin = iFindRowMin;
        boolean z3 = z2;
        boolean z4 = false;
        while (true) {
            if (i3 < this.mNumRows) {
                if (i2 == count || (!z && checkAppendOverLimit(i))) {
                    break;
                }
                int rowMin2 = this.mReversedFlow ? getRowMin(i3) : getRowMax(i3);
                if (rowMin2 != Integer.MAX_VALUE && rowMin2 != Integer.MIN_VALUE) {
                    rowMax = rowMin2 + (this.mReversedFlow ? -this.mSpacing : this.mSpacing);
                } else if (i3 == 0) {
                    rowMax = this.mReversedFlow ? getRowMin(this.mNumRows - 1) : getRowMax(this.mNumRows - 1);
                    if (rowMax != Integer.MAX_VALUE && rowMax != Integer.MIN_VALUE) {
                        rowMax += this.mReversedFlow ? -this.mSpacing : this.mSpacing;
                    }
                } else {
                    rowMax = this.mReversedFlow ? getRowMax(i3 - 1) : getRowMin(i3 - 1);
                }
                int i5 = i2 + 1;
                int iAppendVisibleItemToRow = appendVisibleItemToRow(i2, i3, rowMax);
                if (z3) {
                    while (true) {
                        if (!this.mReversedFlow) {
                            if (rowMax + iAppendVisibleItemToRow >= rowMin) {
                                break;
                            }
                            if (i5 != count) {
                            }
                            return true;
                        }
                        if (rowMax - iAppendVisibleItemToRow <= rowMin) {
                            break;
                        }
                        if (i5 != count || (!z && checkAppendOverLimit(i))) {
                            return true;
                        }
                        rowMax += this.mReversedFlow ? (-iAppendVisibleItemToRow) - this.mSpacing : iAppendVisibleItemToRow + this.mSpacing;
                        int i6 = i5 + 1;
                        int iAppendVisibleItemToRow2 = appendVisibleItemToRow(i5, i3, rowMax);
                        i5 = i6;
                        iAppendVisibleItemToRow = iAppendVisibleItemToRow2;
                    }
                } else {
                    rowMin = this.mReversedFlow ? getRowMin(i3) : getRowMax(i3);
                    z3 = true;
                }
                i2 = i5;
                i3++;
                z4 = true;
            } else {
                if (z) {
                    return z4;
                }
                rowMin = this.mReversedFlow ? findRowMin(false, null) : findRowMax(true, null);
                i3 = 0;
            }
        }
        return z4;
    }

    @Override // android.support.v17.leanback.widget.StaggeredGrid
    protected boolean prependVisibleItemsWithoutCache(int i, boolean z) {
        int i2;
        int i3;
        boolean z2;
        int iFindRowMax;
        int rowMin;
        if (this.mFirstVisibleIndex >= 0) {
            if (this.mFirstVisibleIndex > getFirstIndex()) {
                return false;
            }
            i2 = this.mFirstVisibleIndex - 1;
            i3 = getLocation(this.mFirstVisibleIndex).row;
            int iFindRowEdgeLimitSearchIndex = findRowEdgeLimitSearchIndex(false);
            if (iFindRowEdgeLimitSearchIndex < 0) {
                i3--;
                iFindRowMax = Integer.MAX_VALUE;
                for (int i4 = this.mNumRows - 1; i4 >= 0; i4--) {
                    iFindRowMax = this.mReversedFlow ? getRowMax(i4) : getRowMin(i4);
                    if (iFindRowMax != Integer.MAX_VALUE) {
                        break;
                    }
                }
            } else {
                iFindRowMax = this.mReversedFlow ? findRowMax(true, iFindRowEdgeLimitSearchIndex, null) : findRowMin(false, iFindRowEdgeLimitSearchIndex, null);
            }
            if (!this.mReversedFlow ? getRowMin(i3) <= iFindRowMax : getRowMax(i3) >= iFindRowMax) {
                i3--;
                if (i3 < 0) {
                    i3 = this.mNumRows - 1;
                    iFindRowMax = this.mReversedFlow ? findRowMax(true, null) : findRowMin(false, null);
                }
            }
            z2 = true;
        } else {
            i2 = this.mStartIndex != -1 ? this.mStartIndex : 0;
            i3 = (this.mLocations.size() >= 0 ? (getLocation(getFirstIndex()).row + this.mNumRows) - 1 : i2) % this.mNumRows;
            z2 = false;
            iFindRowMax = 0;
        }
        int rowMax = iFindRowMax;
        boolean z3 = z2;
        boolean z4 = false;
        while (true) {
            if (i3 >= 0) {
                if (i2 < 0 || (!z && checkPrependOverLimit(i))) {
                    break;
                }
                int rowMax2 = this.mReversedFlow ? getRowMax(i3) : getRowMin(i3);
                if (rowMax2 == Integer.MAX_VALUE || rowMax2 == Integer.MIN_VALUE) {
                    if (i3 == this.mNumRows - 1) {
                        rowMin = this.mReversedFlow ? getRowMax(0) : getRowMin(0);
                        if (rowMin != Integer.MAX_VALUE && rowMin != Integer.MIN_VALUE) {
                            rowMin += this.mReversedFlow ? this.mSpacing : -this.mSpacing;
                        }
                    } else {
                        rowMin = this.mReversedFlow ? getRowMin(i3 + 1) : getRowMax(i3 + 1);
                    }
                } else {
                    rowMin = rowMax2 + (this.mReversedFlow ? this.mSpacing : -this.mSpacing);
                }
                int i5 = i2 - 1;
                int iPrependVisibleItemToRow = prependVisibleItemToRow(i2, i3, rowMin);
                if (z3) {
                    while (true) {
                        if (!this.mReversedFlow) {
                            if (rowMin - iPrependVisibleItemToRow <= rowMax) {
                                break;
                            }
                            if (i5 >= 0) {
                            }
                            return true;
                        }
                        if (rowMin + iPrependVisibleItemToRow >= rowMax) {
                            break;
                        }
                        if (i5 >= 0 || (!z && checkPrependOverLimit(i))) {
                            return true;
                        }
                        rowMin += this.mReversedFlow ? iPrependVisibleItemToRow + this.mSpacing : (-iPrependVisibleItemToRow) - this.mSpacing;
                        int i6 = i5 - 1;
                        int iPrependVisibleItemToRow2 = prependVisibleItemToRow(i5, i3, rowMin);
                        i5 = i6;
                        iPrependVisibleItemToRow = iPrependVisibleItemToRow2;
                    }
                } else {
                    rowMax = this.mReversedFlow ? getRowMax(i3) : getRowMin(i3);
                    z3 = true;
                }
                i2 = i5;
                i3--;
                z4 = true;
            } else {
                if (z) {
                    return z4;
                }
                rowMax = this.mReversedFlow ? findRowMax(true, null) : findRowMin(false, null);
                i3 = this.mNumRows - 1;
            }
        }
        return z4;
    }
}
