package android.support.v17.leanback.app;

import android.os.Bundle;
import android.support.v17.leanback.R;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.widget.BrowseFrameLayout;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnChildLaidOutListener;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class VerticalGridFragment extends BaseFragment {
    static boolean DEBUG = false;
    static final String TAG = "VerticalGridFragment";
    private ObjectAdapter mAdapter;
    private VerticalGridPresenter mGridPresenter;
    VerticalGridPresenter.ViewHolder mGridViewHolder;
    private OnItemViewClickedListener mOnItemViewClickedListener;
    OnItemViewSelectedListener mOnItemViewSelectedListener;
    private Object mSceneAfterEntranceTransition;
    private int mSelectedPosition = -1;
    private final OnItemViewSelectedListener mViewSelectedListener = new OnItemViewSelectedListener() { // from class: android.support.v17.leanback.app.VerticalGridFragment.1
        @Override // android.support.v17.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
            int selectedPosition = VerticalGridFragment.this.mGridViewHolder.getGridView().getSelectedPosition();
            if (VerticalGridFragment.DEBUG) {
                Log.v(VerticalGridFragment.TAG, "grid selected position " + selectedPosition);
            }
            VerticalGridFragment.this.gridOnItemSelected(selectedPosition);
            if (VerticalGridFragment.this.mOnItemViewSelectedListener != null) {
                VerticalGridFragment.this.mOnItemViewSelectedListener.onItemSelected(viewHolder, obj, viewHolder2, row);
            }
        }
    };
    private final OnChildLaidOutListener mChildLaidOutListener = new OnChildLaidOutListener() { // from class: android.support.v17.leanback.app.VerticalGridFragment.2
        @Override // android.support.v17.leanback.widget.OnChildLaidOutListener
        public void onChildLaidOut(ViewGroup viewGroup, View view, int i, long j) {
            if (i == 0) {
                VerticalGridFragment.this.showOrHideTitle();
            }
        }
    };

    @Override // android.support.v17.leanback.app.BaseFragment
    public /* bridge */ /* synthetic */ void prepareEntranceTransition() {
        super.prepareEntranceTransition();
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    public /* bridge */ /* synthetic */ void startEntranceTransition() {
        super.startEntranceTransition();
    }

    public void setGridPresenter(VerticalGridPresenter verticalGridPresenter) {
        if (verticalGridPresenter == null) {
            throw new IllegalArgumentException("Grid presenter may not be null");
        }
        this.mGridPresenter = verticalGridPresenter;
        this.mGridPresenter.setOnItemViewSelectedListener(this.mViewSelectedListener);
        if (this.mOnItemViewClickedListener != null) {
            this.mGridPresenter.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        }
    }

    public VerticalGridPresenter getGridPresenter() {
        return this.mGridPresenter;
    }

    public void setAdapter(ObjectAdapter objectAdapter) {
        this.mAdapter = objectAdapter;
        updateAdapter();
    }

    public ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener onItemViewSelectedListener) {
        this.mOnItemViewSelectedListener = onItemViewSelectedListener;
    }

    void gridOnItemSelected(int i) {
        if (i != this.mSelectedPosition) {
            this.mSelectedPosition = i;
            showOrHideTitle();
        }
    }

    void showOrHideTitle() {
        if (this.mGridViewHolder.getGridView().findViewHolderForAdapterPosition(this.mSelectedPosition) == null) {
            return;
        }
        if (!this.mGridViewHolder.getGridView().hasPreviousViewInSameRow(this.mSelectedPosition)) {
            showTitle(true);
        } else {
            showTitle(false);
        }
    }

    public void setOnItemViewClickedListener(OnItemViewClickedListener onItemViewClickedListener) {
        this.mOnItemViewClickedListener = onItemViewClickedListener;
        if (this.mGridPresenter != null) {
            this.mGridPresenter.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        }
    }

    public OnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.lb_vertical_grid_fragment, viewGroup, false);
        installTitleView(layoutInflater, (ViewGroup) viewGroup2.findViewById(R.id.grid_frame), bundle);
        getProgressBarManager().setRootView(viewGroup2);
        return viewGroup2;
    }

    @Override // android.support.v17.leanback.app.BaseFragment, android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.browse_grid_dock);
        this.mGridViewHolder = this.mGridPresenter.onCreateViewHolder(viewGroup);
        viewGroup.addView(this.mGridViewHolder.view);
        this.mGridViewHolder.getGridView().setOnChildLaidOutListener(this.mChildLaidOutListener);
        this.mSceneAfterEntranceTransition = TransitionHelper.createScene(viewGroup, new Runnable() { // from class: android.support.v17.leanback.app.VerticalGridFragment.3
            @Override // java.lang.Runnable
            public void run() {
                VerticalGridFragment.this.setEntranceTransitionState(true);
            }
        });
        updateAdapter();
    }

    private void setupFocusSearchListener() {
        ((BrowseFrameLayout) getView().findViewById(R.id.grid_frame)).setOnFocusSearchListener(getTitleHelper().getOnFocusSearchListener());
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        setupFocusSearchListener();
        if (isEntranceTransitionEnabled()) {
            setEntranceTransitionState(false);
        }
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mGridViewHolder = null;
    }

    public void setSelectedPosition(int i) {
        this.mSelectedPosition = i;
        if (this.mGridViewHolder == null || this.mGridViewHolder.getGridView().getAdapter() == null) {
            return;
        }
        this.mGridViewHolder.getGridView().setSelectedPositionSmooth(i);
    }

    private void updateAdapter() {
        if (this.mGridViewHolder != null) {
            this.mGridPresenter.onBindViewHolder(this.mGridViewHolder, this.mAdapter);
            if (this.mSelectedPosition != -1) {
                this.mGridViewHolder.getGridView().setSelectedPosition(this.mSelectedPosition);
            }
        }
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected Object createEntranceTransition() {
        return TransitionHelper.loadTransition(FragmentUtil.getContext(this), R.transition.lb_vertical_grid_entrance_transition);
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void runEntranceTransition(Object obj) {
        TransitionHelper.runTransition(this.mSceneAfterEntranceTransition, obj);
    }

    void setEntranceTransitionState(boolean z) {
        this.mGridPresenter.setEntranceTransitionState(this.mGridViewHolder, z);
    }
}
