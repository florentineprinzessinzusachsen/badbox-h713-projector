package android.support.v17.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
public final class ItemAlignmentFacet {
    public static final float ITEM_ALIGN_OFFSET_PERCENT_DISABLED = -1.0f;
    private ItemAlignmentDef[] mAlignmentDefs = {new ItemAlignmentDef()};

    public static class ItemAlignmentDef {
        private boolean mAlignToBaseline;
        int mViewId = -1;
        int mFocusViewId = -1;
        int mOffset = 0;
        float mOffsetPercent = 50.0f;
        boolean mOffsetWithPadding = false;

        public final void setItemAlignmentOffset(int i) {
            this.mOffset = i;
        }

        public final int getItemAlignmentOffset() {
            return this.mOffset;
        }

        public final void setItemAlignmentOffsetWithPadding(boolean z) {
            this.mOffsetWithPadding = z;
        }

        public final boolean isItemAlignmentOffsetWithPadding() {
            return this.mOffsetWithPadding;
        }

        public final void setItemAlignmentOffsetPercent(float f) {
            if ((f < 0.0f || f > 100.0f) && f != -1.0f) {
                throw new IllegalArgumentException();
            }
            this.mOffsetPercent = f;
        }

        public final float getItemAlignmentOffsetPercent() {
            return this.mOffsetPercent;
        }

        public final void setItemAlignmentViewId(int i) {
            this.mViewId = i;
        }

        public final int getItemAlignmentViewId() {
            return this.mViewId;
        }

        public final void setItemAlignmentFocusViewId(int i) {
            this.mFocusViewId = i;
        }

        public final int getItemAlignmentFocusViewId() {
            return this.mFocusViewId != -1 ? this.mFocusViewId : this.mViewId;
        }

        public final void setAlignedToTextViewBaseline(boolean z) {
            this.mAlignToBaseline = z;
        }

        public boolean isAlignedToTextViewBaseLine() {
            return this.mAlignToBaseline;
        }
    }

    public boolean isMultiAlignment() {
        return this.mAlignmentDefs.length > 1;
    }

    public void setAlignmentDefs(ItemAlignmentDef[] itemAlignmentDefArr) {
        if (itemAlignmentDefArr == null || itemAlignmentDefArr.length < 1) {
            throw new IllegalArgumentException();
        }
        this.mAlignmentDefs = itemAlignmentDefArr;
    }

    public ItemAlignmentDef[] getAlignmentDefs() {
        return this.mAlignmentDefs;
    }
}
