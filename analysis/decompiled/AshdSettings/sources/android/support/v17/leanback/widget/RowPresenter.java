package android.support.v17.leanback.widget;

import android.support.v17.leanback.graphics.ColorOverlayDimmer;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class RowPresenter extends Presenter {
    public static final int SYNC_ACTIVATED_CUSTOM = 0;
    public static final int SYNC_ACTIVATED_TO_EXPANDED = 1;
    public static final int SYNC_ACTIVATED_TO_EXPANDED_AND_SELECTED = 3;
    public static final int SYNC_ACTIVATED_TO_SELECTED = 2;
    private RowHeaderPresenter mHeaderPresenter = new RowHeaderPresenter();
    boolean mSelectEffectEnabled = true;
    int mSyncActivatePolicy = 1;

    protected abstract ViewHolder createRowViewHolder(ViewGroup viewGroup);

    public void freeze(ViewHolder viewHolder, boolean z) {
    }

    protected boolean isClippingChildren() {
        return false;
    }

    public boolean isUsingDefaultSelectEffect() {
        return true;
    }

    static class ContainerViewHolder extends Presenter.ViewHolder {
        final ViewHolder mRowViewHolder;

        public ContainerViewHolder(RowContainerView rowContainerView, ViewHolder viewHolder) {
            super(rowContainerView);
            rowContainerView.addRowView(viewHolder.view);
            if (viewHolder.mHeaderViewHolder != null) {
                rowContainerView.addHeaderView(viewHolder.mHeaderViewHolder.view);
            }
            this.mRowViewHolder = viewHolder;
            this.mRowViewHolder.mContainerViewHolder = this;
        }
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        private static final int ACTIVATED = 1;
        private static final int ACTIVATED_NOT_ASSIGNED = 0;
        private static final int NOT_ACTIVATED = 2;
        int mActivated;
        protected final ColorOverlayDimmer mColorDimmer;
        ContainerViewHolder mContainerViewHolder;
        boolean mExpanded;
        RowHeaderPresenter.ViewHolder mHeaderViewHolder;
        boolean mInitialzed;
        private BaseOnItemViewClickedListener mOnItemViewClickedListener;
        BaseOnItemViewSelectedListener mOnItemViewSelectedListener;
        private View.OnKeyListener mOnKeyListener;
        Row mRow;
        Object mRowObject;
        float mSelectLevel;
        boolean mSelected;

        public Object getSelectedItem() {
            return null;
        }

        public Presenter.ViewHolder getSelectedItemViewHolder() {
            return null;
        }

        public ViewHolder(View view) {
            super(view);
            this.mActivated = 0;
            this.mSelectLevel = 0.0f;
            this.mColorDimmer = ColorOverlayDimmer.createDefault(view.getContext());
        }

        public final Row getRow() {
            return this.mRow;
        }

        public final Object getRowObject() {
            return this.mRowObject;
        }

        public final boolean isExpanded() {
            return this.mExpanded;
        }

        public final boolean isSelected() {
            return this.mSelected;
        }

        public final float getSelectLevel() {
            return this.mSelectLevel;
        }

        public final RowHeaderPresenter.ViewHolder getHeaderViewHolder() {
            return this.mHeaderViewHolder;
        }

        public final void setActivated(boolean z) {
            this.mActivated = z ? 1 : 2;
        }

        public final void syncActivatedStatus(View view) {
            if (this.mActivated == 1) {
                view.setActivated(true);
            } else if (this.mActivated == 2) {
                view.setActivated(false);
            }
        }

        public void setOnKeyListener(View.OnKeyListener onKeyListener) {
            this.mOnKeyListener = onKeyListener;
        }

        public View.OnKeyListener getOnKeyListener() {
            return this.mOnKeyListener;
        }

        public final void setOnItemViewSelectedListener(BaseOnItemViewSelectedListener baseOnItemViewSelectedListener) {
            this.mOnItemViewSelectedListener = baseOnItemViewSelectedListener;
        }

        public final BaseOnItemViewSelectedListener getOnItemViewSelectedListener() {
            return this.mOnItemViewSelectedListener;
        }

        public final void setOnItemViewClickedListener(BaseOnItemViewClickedListener baseOnItemViewClickedListener) {
            this.mOnItemViewClickedListener = baseOnItemViewClickedListener;
        }

        public final BaseOnItemViewClickedListener getOnItemViewClickedListener() {
            return this.mOnItemViewClickedListener;
        }
    }

    public RowPresenter() {
        this.mHeaderPresenter.setNullItemVisibilityGone(true);
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public final Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Presenter.ViewHolder containerViewHolder;
        ViewHolder viewHolderCreateRowViewHolder = createRowViewHolder(viewGroup);
        viewHolderCreateRowViewHolder.mInitialzed = false;
        if (needsRowContainerView()) {
            RowContainerView rowContainerView = new RowContainerView(viewGroup.getContext());
            if (this.mHeaderPresenter != null) {
                viewHolderCreateRowViewHolder.mHeaderViewHolder = (RowHeaderPresenter.ViewHolder) this.mHeaderPresenter.onCreateViewHolder((ViewGroup) viewHolderCreateRowViewHolder.view);
            }
            containerViewHolder = new ContainerViewHolder(rowContainerView, viewHolderCreateRowViewHolder);
        } else {
            containerViewHolder = viewHolderCreateRowViewHolder;
        }
        initializeRowViewHolder(viewHolderCreateRowViewHolder);
        if (viewHolderCreateRowViewHolder.mInitialzed) {
            return containerViewHolder;
        }
        throw new RuntimeException("super.initializeRowViewHolder() must be called");
    }

    protected void initializeRowViewHolder(ViewHolder viewHolder) {
        viewHolder.mInitialzed = true;
        if (isClippingChildren()) {
            return;
        }
        if (viewHolder.view instanceof ViewGroup) {
            ((ViewGroup) viewHolder.view).setClipChildren(false);
        }
        if (viewHolder.mContainerViewHolder != null) {
            ((ViewGroup) viewHolder.mContainerViewHolder.view).setClipChildren(false);
        }
    }

    public final void setHeaderPresenter(RowHeaderPresenter rowHeaderPresenter) {
        this.mHeaderPresenter = rowHeaderPresenter;
    }

    public final RowHeaderPresenter getHeaderPresenter() {
        return this.mHeaderPresenter;
    }

    public final ViewHolder getRowViewHolder(Presenter.ViewHolder viewHolder) {
        if (viewHolder instanceof ContainerViewHolder) {
            return ((ContainerViewHolder) viewHolder).mRowViewHolder;
        }
        return (ViewHolder) viewHolder;
    }

    public final void setRowViewExpanded(Presenter.ViewHolder viewHolder, boolean z) {
        ViewHolder rowViewHolder = getRowViewHolder(viewHolder);
        rowViewHolder.mExpanded = z;
        onRowViewExpanded(rowViewHolder, z);
    }

    public final void setRowViewSelected(Presenter.ViewHolder viewHolder, boolean z) {
        ViewHolder rowViewHolder = getRowViewHolder(viewHolder);
        rowViewHolder.mSelected = z;
        onRowViewSelected(rowViewHolder, z);
    }

    protected void onRowViewExpanded(ViewHolder viewHolder, boolean z) {
        updateHeaderViewVisibility(viewHolder);
        updateActivateStatus(viewHolder, viewHolder.view);
    }

    private void updateActivateStatus(ViewHolder viewHolder, View view) {
        switch (this.mSyncActivatePolicy) {
            case 1:
                viewHolder.setActivated(viewHolder.isExpanded());
                break;
            case 2:
                viewHolder.setActivated(viewHolder.isSelected());
                break;
            case 3:
                viewHolder.setActivated(viewHolder.isExpanded() && viewHolder.isSelected());
                break;
        }
        viewHolder.syncActivatedStatus(view);
    }

    public final void setSyncActivatePolicy(int i) {
        this.mSyncActivatePolicy = i;
    }

    public final int getSyncActivatePolicy() {
        return this.mSyncActivatePolicy;
    }

    protected void dispatchItemSelectedListener(ViewHolder viewHolder, boolean z) {
        if (!z || viewHolder.mOnItemViewSelectedListener == null) {
            return;
        }
        viewHolder.mOnItemViewSelectedListener.onItemSelected(null, null, viewHolder, viewHolder.getRowObject());
    }

    protected void onRowViewSelected(ViewHolder viewHolder, boolean z) {
        dispatchItemSelectedListener(viewHolder, z);
        updateHeaderViewVisibility(viewHolder);
        updateActivateStatus(viewHolder, viewHolder.view);
    }

    private void updateHeaderViewVisibility(ViewHolder viewHolder) {
        if (this.mHeaderPresenter == null || viewHolder.mHeaderViewHolder == null) {
            return;
        }
        ((RowContainerView) viewHolder.mContainerViewHolder.view).showHeader(viewHolder.isExpanded());
    }

    public final void setSelectLevel(Presenter.ViewHolder viewHolder, float f) {
        ViewHolder rowViewHolder = getRowViewHolder(viewHolder);
        rowViewHolder.mSelectLevel = f;
        onSelectLevelChanged(rowViewHolder);
    }

    public final float getSelectLevel(Presenter.ViewHolder viewHolder) {
        return getRowViewHolder(viewHolder).mSelectLevel;
    }

    protected void onSelectLevelChanged(ViewHolder viewHolder) {
        if (getSelectEffectEnabled()) {
            viewHolder.mColorDimmer.setActiveLevel(viewHolder.mSelectLevel);
            if (viewHolder.mHeaderViewHolder != null) {
                this.mHeaderPresenter.setSelectLevel(viewHolder.mHeaderViewHolder, viewHolder.mSelectLevel);
            }
            if (isUsingDefaultSelectEffect()) {
                ((RowContainerView) viewHolder.mContainerViewHolder.view).setForegroundColor(viewHolder.mColorDimmer.getPaint().getColor());
            }
        }
    }

    public final void setSelectEffectEnabled(boolean z) {
        this.mSelectEffectEnabled = z;
    }

    public final boolean getSelectEffectEnabled() {
        return this.mSelectEffectEnabled;
    }

    final boolean needsDefaultSelectEffect() {
        return isUsingDefaultSelectEffect() && getSelectEffectEnabled();
    }

    final boolean needsRowContainerView() {
        return this.mHeaderPresenter != null || needsDefaultSelectEffect();
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public final void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        onBindRowViewHolder(getRowViewHolder(viewHolder), obj);
    }

    protected void onBindRowViewHolder(ViewHolder viewHolder, Object obj) {
        viewHolder.mRowObject = obj;
        viewHolder.mRow = obj instanceof Row ? (Row) obj : null;
        if (viewHolder.mHeaderViewHolder == null || viewHolder.getRow() == null) {
            return;
        }
        this.mHeaderPresenter.onBindViewHolder(viewHolder.mHeaderViewHolder, obj);
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public final void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        onUnbindRowViewHolder(getRowViewHolder(viewHolder));
    }

    protected void onUnbindRowViewHolder(ViewHolder viewHolder) {
        if (viewHolder.mHeaderViewHolder != null) {
            this.mHeaderPresenter.onUnbindViewHolder(viewHolder.mHeaderViewHolder);
        }
        viewHolder.mRow = null;
        viewHolder.mRowObject = null;
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public final void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        onRowViewAttachedToWindow(getRowViewHolder(viewHolder));
    }

    protected void onRowViewAttachedToWindow(ViewHolder viewHolder) {
        if (viewHolder.mHeaderViewHolder != null) {
            this.mHeaderPresenter.onViewAttachedToWindow(viewHolder.mHeaderViewHolder);
        }
    }

    @Override // android.support.v17.leanback.widget.Presenter
    public final void onViewDetachedFromWindow(Presenter.ViewHolder viewHolder) {
        onRowViewDetachedFromWindow(getRowViewHolder(viewHolder));
    }

    protected void onRowViewDetachedFromWindow(ViewHolder viewHolder) {
        if (viewHolder.mHeaderViewHolder != null) {
            this.mHeaderPresenter.onViewDetachedFromWindow(viewHolder.mHeaderViewHolder);
        }
        cancelAnimationsRecursive(viewHolder.view);
    }

    public void setEntranceTransitionState(ViewHolder viewHolder, boolean z) {
        if (viewHolder.mHeaderViewHolder == null || viewHolder.mHeaderViewHolder.view.getVisibility() == 8) {
            return;
        }
        viewHolder.mHeaderViewHolder.view.setVisibility(z ? 0 : 4);
    }
}
