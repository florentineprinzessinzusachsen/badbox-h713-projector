package android.support.v17.leanback.widget;

import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public abstract class PresenterSwitcher {
    private Presenter mCurrentPresenter;
    private Presenter.ViewHolder mCurrentViewHolder;
    private ViewGroup mParent;
    private PresenterSelector mPresenterSelector;

    protected abstract void insertView(View view);

    protected void onViewSelected(View view) {
    }

    public void init(ViewGroup viewGroup, PresenterSelector presenterSelector) {
        clear();
        this.mParent = viewGroup;
        this.mPresenterSelector = presenterSelector;
    }

    public void select(Object obj) {
        switchView(obj);
        showView(true);
    }

    public void unselect() {
        showView(false);
    }

    public final ViewGroup getParentViewGroup() {
        return this.mParent;
    }

    private void showView(boolean z) {
        if (this.mCurrentViewHolder != null) {
            showView(this.mCurrentViewHolder.view, z);
        }
    }

    private void switchView(Object obj) {
        Presenter presenter = this.mPresenterSelector.getPresenter(obj);
        if (presenter != this.mCurrentPresenter) {
            showView(false);
            clear();
            this.mCurrentPresenter = presenter;
            if (this.mCurrentPresenter == null) {
                return;
            }
            this.mCurrentViewHolder = this.mCurrentPresenter.onCreateViewHolder(this.mParent);
            insertView(this.mCurrentViewHolder.view);
        } else if (this.mCurrentPresenter == null) {
            return;
        } else {
            this.mCurrentPresenter.onUnbindViewHolder(this.mCurrentViewHolder);
        }
        this.mCurrentPresenter.onBindViewHolder(this.mCurrentViewHolder, obj);
        onViewSelected(this.mCurrentViewHolder.view);
    }

    protected void showView(View view, boolean z) {
        view.setVisibility(z ? 0 : 8);
    }

    public void clear() {
        if (this.mCurrentPresenter != null) {
            this.mCurrentPresenter.onUnbindViewHolder(this.mCurrentViewHolder);
            this.mParent.removeView(this.mCurrentViewHolder.view);
            this.mCurrentViewHolder = null;
            this.mCurrentPresenter = null;
        }
    }
}
