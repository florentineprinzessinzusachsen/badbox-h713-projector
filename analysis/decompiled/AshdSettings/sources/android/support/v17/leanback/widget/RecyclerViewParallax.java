package android.support.v17.leanback.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerViewParallax extends Parallax<ChildPositionProperty> {
    boolean mIsVertical;
    RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() { // from class: android.support.v17.leanback.widget.RecyclerViewParallax.1
        @Override // android.support.v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            RecyclerViewParallax.this.updateValues();
        }
    };
    RecyclerView mRecylerView;

    public static final class ChildPositionProperty extends Parallax.IntProperty {
        int mAdapterPosition;
        float mFraction;
        int mOffset;
        int mViewId;

        ChildPositionProperty(String str, int i) {
            super(str, i);
        }

        public ChildPositionProperty adapterPosition(int i) {
            this.mAdapterPosition = i;
            return this;
        }

        public ChildPositionProperty viewId(int i) {
            this.mViewId = i;
            return this;
        }

        public ChildPositionProperty offset(int i) {
            this.mOffset = i;
            return this;
        }

        public ChildPositionProperty fraction(float f) {
            this.mFraction = f;
            return this;
        }

        public int getAdapterPosition() {
            return this.mAdapterPosition;
        }

        public int getViewId() {
            return this.mViewId;
        }

        public int getOffset() {
            return this.mOffset;
        }

        public float getFraction() {
            return this.mFraction;
        }

        void updateValue(RecyclerViewParallax recyclerViewParallax) {
            RecyclerView recyclerView = recyclerViewParallax.mRecylerView;
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = recyclerView == null ? null : recyclerView.findViewHolderForAdapterPosition(this.mAdapterPosition);
            if (viewHolderFindViewHolderForAdapterPosition == null) {
                if (recyclerView == null || recyclerView.getLayoutManager().getChildCount() == 0) {
                    recyclerViewParallax.setIntPropertyValue(getIndex(), Integer.MAX_VALUE);
                    return;
                } else if (recyclerView.findContainingViewHolder(recyclerView.getLayoutManager().getChildAt(0)).getAdapterPosition() < this.mAdapterPosition) {
                    recyclerViewParallax.setIntPropertyValue(getIndex(), Integer.MAX_VALUE);
                    return;
                } else {
                    recyclerViewParallax.setIntPropertyValue(getIndex(), Integer.MIN_VALUE);
                    return;
                }
            }
            View viewFindViewById = viewHolderFindViewHolderForAdapterPosition.itemView.findViewById(this.mViewId);
            if (viewFindViewById == null) {
                return;
            }
            Rect rect = new Rect(0, 0, viewFindViewById.getWidth(), viewFindViewById.getHeight());
            recyclerView.offsetDescendantRectToMyCoords(viewFindViewById, rect);
            float translationX = 0.0f;
            float translationY = 0.0f;
            while (viewFindViewById != recyclerView && viewFindViewById != null) {
                translationX += viewFindViewById.getTranslationX();
                translationY += viewFindViewById.getTranslationY();
                viewFindViewById = (View) viewFindViewById.getParent();
            }
            rect.offset((int) translationX, (int) translationY);
            if (recyclerViewParallax.mIsVertical) {
                recyclerViewParallax.setIntPropertyValue(getIndex(), rect.top + this.mOffset + ((int) (this.mFraction * rect.height())));
            } else {
                recyclerViewParallax.setIntPropertyValue(getIndex(), rect.left + this.mOffset + ((int) (this.mFraction * rect.width())));
            }
        }
    }

    @Override // android.support.v17.leanback.widget.Parallax
    public ChildPositionProperty createProperty(String str, int i) {
        return new ChildPositionProperty(str, i);
    }

    @Override // android.support.v17.leanback.widget.Parallax
    public float getMaxValue() {
        if (this.mRecylerView == null) {
            return 0.0f;
        }
        return this.mIsVertical ? this.mRecylerView.getHeight() : this.mRecylerView.getWidth();
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        if (this.mRecylerView == recyclerView) {
            return;
        }
        if (this.mRecylerView != null) {
            this.mRecylerView.removeOnScrollListener(this.mOnScrollListener);
        }
        this.mRecylerView = recyclerView;
        if (this.mRecylerView != null) {
            this.mRecylerView.getLayoutManager();
            this.mIsVertical = RecyclerView.LayoutManager.getProperties(this.mRecylerView.getContext(), null, 0, 0).orientation == 1;
            this.mRecylerView.addOnScrollListener(this.mOnScrollListener);
        }
    }

    @Override // android.support.v17.leanback.widget.Parallax
    public void updateValues() {
        Iterator<ChildPositionProperty> it = getProperties().iterator();
        while (it.hasNext()) {
            it.next().updateValue(this);
        }
        super.updateValues();
    }

    public RecyclerView getRecyclerView() {
        return this.mRecylerView;
    }
}
