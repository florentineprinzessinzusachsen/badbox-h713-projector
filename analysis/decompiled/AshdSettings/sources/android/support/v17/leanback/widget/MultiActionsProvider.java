package android.support.v17.leanback.widget;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public interface MultiActionsProvider {
    MultiAction[] getActions();

    public static class MultiAction {
        private Drawable[] mDrawables;
        private long mId;
        private int mIndex = 0;

        public MultiAction(long j) {
            this.mId = j;
        }

        public void setDrawables(Drawable[] drawableArr) {
            this.mDrawables = drawableArr;
            if (this.mIndex > drawableArr.length - 1) {
                this.mIndex = drawableArr.length - 1;
            }
        }

        public Drawable[] getDrawables() {
            return this.mDrawables;
        }

        public void incrementIndex() {
            setIndex(this.mIndex < this.mDrawables.length + (-1) ? this.mIndex + 1 : 0);
        }

        public void setIndex(int i) {
            this.mIndex = i;
        }

        public int getIndex() {
            return this.mIndex;
        }

        public Drawable getCurrentDrawable() {
            return this.mDrawables[this.mIndex];
        }

        public long getId() {
            return this.mId;
        }
    }
}
