package android.support.v17.leanback.widget;

/* JADX INFO: loaded from: classes.dex */
class WindowAlignment {
    private int mOrientation = 0;
    public final Axis vertical = new Axis("vertical");
    public final Axis horizontal = new Axis("horizontal");
    private Axis mMainAxis = this.horizontal;
    private Axis mSecondAxis = this.vertical;

    WindowAlignment() {
    }

    public static class Axis {
        private int mMaxEdge;
        private int mMaxScroll;
        private int mMinEdge;
        private int mMinScroll;
        private String mName;
        private int mPaddingHigh;
        private int mPaddingLow;
        private boolean mReversedFlow;
        private float mScrollCenter;
        private int mSize;
        private int mWindowAlignment = 3;
        private int mWindowAlignmentOffset = 0;
        private float mWindowAlignmentOffsetPercent = 50.0f;

        public Axis(String str) {
            reset();
            this.mName = str;
        }

        public final int getWindowAlignment() {
            return this.mWindowAlignment;
        }

        public final void setWindowAlignment(int i) {
            this.mWindowAlignment = i;
        }

        public final int getWindowAlignmentOffset() {
            return this.mWindowAlignmentOffset;
        }

        public final void setWindowAlignmentOffset(int i) {
            this.mWindowAlignmentOffset = i;
        }

        public final void setWindowAlignmentOffsetPercent(float f) {
            if ((f < 0.0f || f > 100.0f) && f != -1.0f) {
                throw new IllegalArgumentException();
            }
            this.mWindowAlignmentOffsetPercent = f;
        }

        public final float getWindowAlignmentOffsetPercent() {
            return this.mWindowAlignmentOffsetPercent;
        }

        public final int getScrollCenter() {
            return (int) this.mScrollCenter;
        }

        public final void setMinEdge(int i) {
            this.mMinEdge = i;
        }

        public final int getMinEdge() {
            return this.mMinEdge;
        }

        public final void setMinScroll(int i) {
            this.mMinScroll = i;
        }

        public final int getMinScroll() {
            return this.mMinScroll;
        }

        public final void invalidateScrollMin() {
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMinScroll = Integer.MIN_VALUE;
        }

        public final void setMaxEdge(int i) {
            this.mMaxEdge = i;
        }

        public final int getMaxEdge() {
            return this.mMaxEdge;
        }

        public final void setMaxScroll(int i) {
            this.mMaxScroll = i;
        }

        public final int getMaxScroll() {
            return this.mMaxScroll;
        }

        public final void invalidateScrollMax() {
            this.mMaxEdge = Integer.MAX_VALUE;
            this.mMaxScroll = Integer.MAX_VALUE;
        }

        public final float updateScrollCenter(float f) {
            this.mScrollCenter = f;
            return f;
        }

        void reset() {
            this.mScrollCenter = -2.1474836E9f;
            this.mMinEdge = Integer.MIN_VALUE;
            this.mMaxEdge = Integer.MAX_VALUE;
        }

        public final boolean isMinUnknown() {
            return this.mMinEdge == Integer.MIN_VALUE;
        }

        public final boolean isMaxUnknown() {
            return this.mMaxEdge == Integer.MAX_VALUE;
        }

        public final void setSize(int i) {
            this.mSize = i;
        }

        public final int getSize() {
            return this.mSize;
        }

        public final void setPadding(int i, int i2) {
            this.mPaddingLow = i;
            this.mPaddingHigh = i2;
        }

        public final int getPaddingLow() {
            return this.mPaddingLow;
        }

        public final int getPaddingHigh() {
            return this.mPaddingHigh;
        }

        public final int getClientSize() {
            return (this.mSize - this.mPaddingLow) - this.mPaddingHigh;
        }

        public final int getSystemScrollPos(boolean z, boolean z2) {
            return getSystemScrollPos((int) this.mScrollCenter, z, z2);
        }

        /* JADX WARN: Code restructure failed: missing block: B:38:0x008f, code lost:
        
            if (r9 == false) goto L39;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0095, code lost:
        
            if ((r8 - r7.mMinEdge) > r0) goto L43;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
        
            return r7.mMinEdge - r7.mPaddingLow;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final int getSystemScrollPos(int r8, boolean r9, boolean r10) {
            /*
                r7 = this;
                boolean r0 = r7.mReversedFlow
                r1 = 1120403456(0x42c80000, float:100.0)
                r2 = -1082130432(0xffffffffbf800000, float:-1.0)
                if (r0 != 0) goto L2a
                int r0 = r7.mWindowAlignmentOffset
                if (r0 < 0) goto L12
                int r0 = r7.mWindowAlignmentOffset
                int r3 = r7.mPaddingLow
                int r0 = r0 - r3
                goto L1a
            L12:
                int r0 = r7.mSize
                int r3 = r7.mWindowAlignmentOffset
                int r0 = r0 + r3
                int r3 = r7.mPaddingLow
                int r0 = r0 - r3
            L1a:
                float r3 = r7.mWindowAlignmentOffsetPercent
                int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
                if (r2 == 0) goto L4c
                int r2 = r7.mSize
                float r2 = (float) r2
                float r3 = r7.mWindowAlignmentOffsetPercent
                float r2 = r2 * r3
                float r2 = r2 / r1
                int r1 = (int) r2
                int r0 = r0 + r1
                goto L4c
            L2a:
                int r0 = r7.mWindowAlignmentOffset
                if (r0 < 0) goto L37
                int r0 = r7.mSize
                int r3 = r7.mWindowAlignmentOffset
                int r0 = r0 - r3
                int r3 = r7.mPaddingLow
                int r0 = r0 - r3
                goto L3d
            L37:
                int r0 = r7.mWindowAlignmentOffset
                int r0 = -r0
                int r3 = r7.mPaddingLow
                int r0 = r0 - r3
            L3d:
                float r3 = r7.mWindowAlignmentOffsetPercent
                int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
                if (r2 == 0) goto L4c
                int r2 = r7.mSize
                float r2 = (float) r2
                float r3 = r7.mWindowAlignmentOffsetPercent
                float r2 = r2 * r3
                float r2 = r2 / r1
                int r1 = (int) r2
                int r0 = r0 - r1
            L4c:
                int r1 = r7.getClientSize()
                int r2 = r1 - r0
                boolean r3 = r7.isMinUnknown()
                boolean r4 = r7.isMaxUnknown()
                if (r3 != 0) goto L7c
                if (r4 != 0) goto L7c
                int r5 = r7.mWindowAlignment
                r6 = 3
                r5 = r5 & r6
                if (r5 != r6) goto L7c
                int r5 = r7.mMaxEdge
                int r6 = r7.mMinEdge
                int r5 = r5 - r6
                if (r5 > r1) goto L7c
                boolean r8 = r7.mReversedFlow
                if (r8 == 0) goto L76
                int r8 = r7.mMaxEdge
                int r7 = r7.mPaddingLow
                int r8 = r8 - r7
                int r8 = r8 - r1
                goto L7b
            L76:
                int r8 = r7.mMinEdge
                int r7 = r7.mPaddingLow
                int r8 = r8 - r7
            L7b:
                return r8
            L7c:
                if (r3 != 0) goto L9d
                boolean r3 = r7.mReversedFlow
                if (r3 != 0) goto L89
                int r3 = r7.mWindowAlignment
                r3 = r3 & 1
                if (r3 == 0) goto L9d
                goto L8f
            L89:
                int r3 = r7.mWindowAlignment
                r3 = r3 & 2
                if (r3 == 0) goto L9d
            L8f:
                if (r9 != 0) goto L97
                int r9 = r7.mMinEdge
                int r9 = r8 - r9
                if (r9 > r0) goto L9d
            L97:
                int r8 = r7.mMinEdge
                int r7 = r7.mPaddingLow
                int r8 = r8 - r7
                return r8
            L9d:
                if (r4 != 0) goto Lbe
                boolean r9 = r7.mReversedFlow
                if (r9 != 0) goto Laa
                int r9 = r7.mWindowAlignment
                r9 = r9 & 2
                if (r9 == 0) goto Lbe
                goto Lb0
            Laa:
                int r9 = r7.mWindowAlignment
                r9 = r9 & 1
                if (r9 == 0) goto Lbe
            Lb0:
                if (r10 != 0) goto Lb7
                int r9 = r7.mMaxEdge
                int r9 = r9 - r8
                if (r9 > r2) goto Lbe
            Lb7:
                int r8 = r7.mMaxEdge
                int r7 = r7.mPaddingLow
                int r8 = r8 - r7
                int r8 = r8 - r1
                return r8
            Lbe:
                int r8 = r8 - r0
                int r7 = r7.mPaddingLow
                int r8 = r8 - r7
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: android.support.v17.leanback.widget.WindowAlignment.Axis.getSystemScrollPos(int, boolean, boolean):int");
        }

        public final void setReversedFlow(boolean z) {
            this.mReversedFlow = z;
        }

        public String toString() {
            return "center: " + this.mScrollCenter + " min:" + this.mMinEdge + " max:" + this.mMaxEdge;
        }
    }

    public final Axis mainAxis() {
        return this.mMainAxis;
    }

    public final Axis secondAxis() {
        return this.mSecondAxis;
    }

    public final void setOrientation(int i) {
        this.mOrientation = i;
        if (this.mOrientation == 0) {
            this.mMainAxis = this.horizontal;
            this.mSecondAxis = this.vertical;
        } else {
            this.mMainAxis = this.vertical;
            this.mSecondAxis = this.horizontal;
        }
    }

    public final int getOrientation() {
        return this.mOrientation;
    }

    public final void reset() {
        mainAxis().reset();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("horizontal=");
        stringBuffer.append(this.horizontal.toString());
        stringBuffer.append("; vertical=");
        stringBuffer.append(this.vertical.toString());
        return stringBuffer.toString();
    }
}
