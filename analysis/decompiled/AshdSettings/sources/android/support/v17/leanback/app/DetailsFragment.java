package android.support.v17.leanback.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v17.leanback.R;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.transition.TransitionListener;
import android.support.v17.leanback.widget.BaseOnItemViewClickedListener;
import android.support.v17.leanback.widget.BaseOnItemViewSelectedListener;
import android.support.v17.leanback.widget.BrowseFrameLayout;
import android.support.v17.leanback.widget.DetailsParallax;
import android.support.v17.leanback.widget.FullWidthDetailsOverviewRowPresenter;
import android.support.v17.leanback.widget.ItemAlignmentFacet;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class DetailsFragment extends BaseFragment {
    static boolean DEBUG = false;
    static final int PF_ENTER_TRANSITION_PENDING = 1;
    static final int PF_ENTRANCE_TRANSITION_PENDING = 2;
    static final int PF_PENDING_START = 4;
    static final String TAG = "DetailsFragment";
    ObjectAdapter mAdapter;
    Drawable mBackgroundDrawable;
    View mBackgroundView;
    int mContainerListAlignTop;
    DetailsFragmentBackgroundController mDetailsBackgroundController;
    DetailsParallax mDetailsParallax;
    BaseOnItemViewSelectedListener mExternalOnItemViewSelectedListener;
    BaseOnItemViewClickedListener mOnItemViewClickedListener;
    BrowseFrameLayout mRootView;
    RowsFragment mRowsFragment;
    Object mSceneAfterEntranceTransition;
    Fragment mVideoFragment;
    WaitEnterTransitionTimeout mWaitEnterTransitionTimeout;
    TransitionListener mEnterTransitionListener = new TransitionListener() { // from class: android.support.v17.leanback.app.DetailsFragment.1
        @Override // android.support.v17.leanback.transition.TransitionListener
        public void onTransitionStart(Object obj) {
            if (DetailsFragment.this.mWaitEnterTransitionTimeout != null) {
                DetailsFragment.this.mWaitEnterTransitionTimeout.mRef.clear();
            }
        }

        @Override // android.support.v17.leanback.transition.TransitionListener
        public void onTransitionCancel(Object obj) {
            DetailsFragment.this.clearPendingEnterTransition();
        }

        @Override // android.support.v17.leanback.transition.TransitionListener
        public void onTransitionEnd(Object obj) {
            DetailsFragment.this.clearPendingEnterTransition();
        }
    };
    TransitionListener mReturnTransitionListener = new TransitionListener() { // from class: android.support.v17.leanback.app.DetailsFragment.2
        @Override // android.support.v17.leanback.transition.TransitionListener
        public void onTransitionStart(Object obj) {
            DetailsFragment.this.onReturnTransitionStart();
        }
    };
    int mStartAndTransitionFlag = 0;
    final SetSelectionRunnable mSetSelectionRunnable = new SetSelectionRunnable();
    final BaseOnItemViewSelectedListener<Object> mOnItemViewSelectedListener = new BaseOnItemViewSelectedListener<Object>() { // from class: android.support.v17.leanback.app.DetailsFragment.3
        @Override // android.support.v17.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
            int selectedPosition = DetailsFragment.this.mRowsFragment.getVerticalGridView().getSelectedPosition();
            int selectedSubPosition = DetailsFragment.this.mRowsFragment.getVerticalGridView().getSelectedSubPosition();
            if (DetailsFragment.DEBUG) {
                Log.v(DetailsFragment.TAG, "row selected position " + selectedPosition + " subposition " + selectedSubPosition);
            }
            DetailsFragment.this.onRowSelected(selectedPosition, selectedSubPosition);
            if (DetailsFragment.this.mExternalOnItemViewSelectedListener != null) {
                DetailsFragment.this.mExternalOnItemViewSelectedListener.onItemSelected(viewHolder, obj, viewHolder2, obj2);
            }
        }
    };

    @Override // android.support.v17.leanback.app.BaseFragment, android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public /* bridge */ /* synthetic */ void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    public /* bridge */ /* synthetic */ void prepareEntranceTransition() {
        super.prepareEntranceTransition();
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    public /* bridge */ /* synthetic */ void startEntranceTransition() {
        super.startEntranceTransition();
    }

    private class SetSelectionRunnable implements Runnable {
        int mPosition;
        boolean mSmooth = true;

        SetSelectionRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (DetailsFragment.this.mRowsFragment == null) {
                return;
            }
            DetailsFragment.this.mRowsFragment.setSelectedPosition(this.mPosition, this.mSmooth);
        }
    }

    static class WaitEnterTransitionTimeout implements Runnable {
        static final long WAIT_ENTERTRANSITION_START = 200;
        final WeakReference<DetailsFragment> mRef;

        WaitEnterTransitionTimeout(DetailsFragment detailsFragment) {
            this.mRef = new WeakReference<>(detailsFragment);
            detailsFragment.getView().postDelayed(this, WAIT_ENTERTRANSITION_START);
        }

        @Override // java.lang.Runnable
        public void run() {
            DetailsFragment detailsFragment = this.mRef.get();
            if (detailsFragment != null) {
                detailsFragment.clearPendingEnterTransition();
            }
        }
    }

    public void setAdapter(ObjectAdapter objectAdapter) {
        this.mAdapter = objectAdapter;
        Presenter[] presenters = objectAdapter.getPresenterSelector().getPresenters();
        if (presenters != null) {
            for (Presenter presenter : presenters) {
                setupPresenter(presenter);
            }
        } else {
            Log.e(TAG, "PresenterSelector.getPresenters() not implemented");
        }
        if (this.mRowsFragment != null) {
            this.mRowsFragment.setAdapter(objectAdapter);
        }
    }

    public ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setOnItemViewSelectedListener(BaseOnItemViewSelectedListener baseOnItemViewSelectedListener) {
        this.mExternalOnItemViewSelectedListener = baseOnItemViewSelectedListener;
    }

    public void setOnItemViewClickedListener(BaseOnItemViewClickedListener baseOnItemViewClickedListener) {
        if (this.mOnItemViewClickedListener != baseOnItemViewClickedListener) {
            this.mOnItemViewClickedListener = baseOnItemViewClickedListener;
            if (this.mRowsFragment != null) {
                this.mRowsFragment.setOnItemViewClickedListener(baseOnItemViewClickedListener);
            }
        }
    }

    public BaseOnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContainerListAlignTop = getResources().getDimensionPixelSize(R.dimen.lb_details_rows_align_top);
        Activity activity = getActivity();
        if (activity != null) {
            Object enterTransition = TransitionHelper.getEnterTransition(activity.getWindow());
            if (enterTransition != null) {
                this.mStartAndTransitionFlag |= 1;
                TransitionHelper.addTransitionListener(enterTransition, this.mEnterTransitionListener);
            }
            Object returnTransition = TransitionHelper.getReturnTransition(activity.getWindow());
            if (returnTransition != null) {
                TransitionHelper.addTransitionListener(returnTransition, this.mReturnTransitionListener);
            }
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = (BrowseFrameLayout) layoutInflater.inflate(R.layout.lb_details_fragment, viewGroup, false);
        this.mBackgroundView = this.mRootView.findViewById(R.id.details_background_view);
        if (this.mBackgroundView != null) {
            this.mBackgroundView.setBackground(this.mBackgroundDrawable);
        }
        this.mRowsFragment = (RowsFragment) getChildFragmentManager().findFragmentById(R.id.details_rows_dock);
        if (this.mRowsFragment == null) {
            this.mRowsFragment = new RowsFragment();
            getChildFragmentManager().beginTransaction().replace(R.id.details_rows_dock, this.mRowsFragment).commit();
        }
        installTitleView(layoutInflater, this.mRootView, bundle);
        this.mRowsFragment.setAdapter(this.mAdapter);
        this.mRowsFragment.setOnItemViewSelectedListener(this.mOnItemViewSelectedListener);
        this.mRowsFragment.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        this.mSceneAfterEntranceTransition = TransitionHelper.createScene(this.mRootView, new Runnable() { // from class: android.support.v17.leanback.app.DetailsFragment.4
            @Override // java.lang.Runnable
            public void run() {
                DetailsFragment.this.mRowsFragment.setEntranceTransitionState(true);
            }
        });
        setupDpadNavigation();
        if (Build.VERSION.SDK_INT >= 21) {
            this.mRowsFragment.setExternalAdapterListener(new ItemBridgeAdapter.AdapterListener() { // from class: android.support.v17.leanback.app.DetailsFragment.5
                @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
                public void onCreate(ItemBridgeAdapter.ViewHolder viewHolder) {
                    if (DetailsFragment.this.mDetailsParallax == null || !(viewHolder.getViewHolder() instanceof FullWidthDetailsOverviewRowPresenter.ViewHolder)) {
                        return;
                    }
                    ((FullWidthDetailsOverviewRowPresenter.ViewHolder) viewHolder.getViewHolder()).getOverviewView().setTag(R.id.lb_parallax_source, DetailsFragment.this.mDetailsParallax);
                }
            });
        }
        return this.mRootView;
    }

    @Deprecated
    protected View inflateTitle(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onInflateTitleView(layoutInflater, viewGroup, bundle);
    }

    @Override // android.support.v17.leanback.app.BrandedFragment
    public View onInflateTitleView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return inflateTitle(layoutInflater, viewGroup, bundle);
    }

    void setVerticalGridViewLayout(VerticalGridView verticalGridView) {
        verticalGridView.setItemAlignmentOffset(-this.mContainerListAlignTop);
        verticalGridView.setItemAlignmentOffsetPercent(-1.0f);
        verticalGridView.setWindowAlignmentOffset(0);
        verticalGridView.setWindowAlignmentOffsetPercent(-1.0f);
        verticalGridView.setWindowAlignment(0);
    }

    protected void setupPresenter(Presenter presenter) {
        if (presenter instanceof FullWidthDetailsOverviewRowPresenter) {
            setupDetailsOverviewRowPresenter((FullWidthDetailsOverviewRowPresenter) presenter);
        }
    }

    protected void setupDetailsOverviewRowPresenter(FullWidthDetailsOverviewRowPresenter fullWidthDetailsOverviewRowPresenter) {
        ItemAlignmentFacet itemAlignmentFacet = new ItemAlignmentFacet();
        ItemAlignmentFacet.ItemAlignmentDef itemAlignmentDef = new ItemAlignmentFacet.ItemAlignmentDef();
        itemAlignmentDef.setItemAlignmentViewId(R.id.details_frame);
        itemAlignmentDef.setItemAlignmentOffset(-getResources().getDimensionPixelSize(R.dimen.lb_details_v2_align_pos_for_actions));
        itemAlignmentDef.setItemAlignmentOffsetPercent(0.0f);
        ItemAlignmentFacet.ItemAlignmentDef itemAlignmentDef2 = new ItemAlignmentFacet.ItemAlignmentDef();
        itemAlignmentDef2.setItemAlignmentViewId(R.id.details_frame);
        itemAlignmentDef2.setItemAlignmentFocusViewId(R.id.details_overview_description);
        itemAlignmentDef2.setItemAlignmentOffset(-getResources().getDimensionPixelSize(R.dimen.lb_details_v2_align_pos_for_description));
        itemAlignmentDef2.setItemAlignmentOffsetPercent(0.0f);
        itemAlignmentFacet.setAlignmentDefs(new ItemAlignmentFacet.ItemAlignmentDef[]{itemAlignmentDef, itemAlignmentDef2});
        fullWidthDetailsOverviewRowPresenter.setFacet(ItemAlignmentFacet.class, itemAlignmentFacet);
    }

    VerticalGridView getVerticalGridView() {
        if (this.mRowsFragment == null) {
            return null;
        }
        return this.mRowsFragment.getVerticalGridView();
    }

    public RowsFragment getRowsFragment() {
        return this.mRowsFragment;
    }

    private void setupChildFragmentLayout() {
        setVerticalGridViewLayout(this.mRowsFragment.getVerticalGridView());
    }

    public void setSelectedPosition(int i) {
        setSelectedPosition(i, true);
    }

    public void setSelectedPosition(int i, boolean z) {
        this.mSetSelectionRunnable.mPosition = i;
        this.mSetSelectionRunnable.mSmooth = z;
        if (getView() == null || getView().getHandler() == null) {
            return;
        }
        getView().getHandler().post(this.mSetSelectionRunnable);
    }

    final Fragment findOrCreateVideoFragment() {
        Fragment fragmentFindFragmentById = getChildFragmentManager().findFragmentById(R.id.video_surface_container);
        if (fragmentFindFragmentById == null && this.mDetailsBackgroundController != null) {
            FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
            int i = R.id.video_surface_container;
            Fragment fragmentOnCreateVideoFragment = this.mDetailsBackgroundController.onCreateVideoFragment();
            fragmentTransactionBeginTransaction.add(i, fragmentOnCreateVideoFragment);
            fragmentTransactionBeginTransaction.commit();
            fragmentFindFragmentById = fragmentOnCreateVideoFragment;
        }
        this.mVideoFragment = fragmentFindFragmentById;
        return this.mVideoFragment;
    }

    void onRowSelected(int i, int i2) {
        ObjectAdapter adapter = getAdapter();
        if (this.mRowsFragment != null && this.mRowsFragment.getView() != null && this.mRowsFragment.getView().hasFocus() && (adapter == null || adapter.size() == 0 || (getVerticalGridView().getSelectedPosition() == 0 && getVerticalGridView().getSelectedSubPosition() == 0))) {
            showTitle(true);
        } else {
            showTitle(false);
        }
        if (adapter == null || adapter.size() <= i) {
            return;
        }
        VerticalGridView verticalGridView = getVerticalGridView();
        int childCount = verticalGridView.getChildCount();
        if (childCount > 0 && (1 & this.mStartAndTransitionFlag) != 0 && this.mWaitEnterTransitionTimeout == null) {
            this.mWaitEnterTransitionTimeout = new WaitEnterTransitionTimeout(this);
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            ItemBridgeAdapter.ViewHolder viewHolder = (ItemBridgeAdapter.ViewHolder) verticalGridView.getChildViewHolder(verticalGridView.getChildAt(i3));
            RowPresenter rowPresenter = (RowPresenter) viewHolder.getPresenter();
            onSetRowStatus(rowPresenter, rowPresenter.getRowViewHolder(viewHolder.getViewHolder()), viewHolder.getAdapterPosition(), i, i2);
        }
    }

    void clearPendingEnterTransition() {
        if ((this.mStartAndTransitionFlag & 1) != 0) {
            this.mStartAndTransitionFlag &= -2;
            dispatchOnStartAndTransitionFinished();
        }
    }

    void dispatchOnStartAndTransitionFinished() {
        if ((this.mStartAndTransitionFlag & 4) == 0 || (this.mStartAndTransitionFlag & 3) != 0) {
            return;
        }
        this.mStartAndTransitionFlag &= -5;
        onSafeStart();
    }

    @CallSuper
    void onSafeStart() {
        if (this.mDetailsBackgroundController != null) {
            this.mDetailsBackgroundController.onStart();
        }
    }

    @CallSuper
    void onReturnTransitionStart() {
        if (this.mDetailsBackgroundController == null || this.mDetailsBackgroundController.disableVideoParallax() || this.mVideoFragment == null) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.remove(this.mVideoFragment);
        fragmentTransactionBeginTransaction.commit();
        this.mVideoFragment = null;
    }

    @Override // android.app.Fragment
    public void onStop() {
        if (this.mDetailsBackgroundController != null) {
            this.mDetailsBackgroundController.onStop();
        }
        super.onStop();
    }

    protected void onSetRowStatus(RowPresenter rowPresenter, RowPresenter.ViewHolder viewHolder, int i, int i2, int i3) {
        if (rowPresenter instanceof FullWidthDetailsOverviewRowPresenter) {
            onSetDetailsOverviewRowStatus((FullWidthDetailsOverviewRowPresenter) rowPresenter, (FullWidthDetailsOverviewRowPresenter.ViewHolder) viewHolder, i, i2, i3);
        }
    }

    protected void onSetDetailsOverviewRowStatus(FullWidthDetailsOverviewRowPresenter fullWidthDetailsOverviewRowPresenter, FullWidthDetailsOverviewRowPresenter.ViewHolder viewHolder, int i, int i2, int i3) {
        if (i2 > i) {
            fullWidthDetailsOverviewRowPresenter.setState(viewHolder, 0);
            return;
        }
        if (i2 == i && i3 == 1) {
            fullWidthDetailsOverviewRowPresenter.setState(viewHolder, 0);
        } else if (i2 == i && i3 == 0) {
            fullWidthDetailsOverviewRowPresenter.setState(viewHolder, 1);
        } else {
            fullWidthDetailsOverviewRowPresenter.setState(viewHolder, 2);
        }
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        this.mStartAndTransitionFlag |= 4;
        dispatchOnStartAndTransitionFinished();
        setupChildFragmentLayout();
        if (isEntranceTransitionEnabled()) {
            this.mRowsFragment.setEntranceTransitionState(false);
        }
        if (this.mDetailsParallax != null) {
            this.mDetailsParallax.setRecyclerView(this.mRowsFragment.getVerticalGridView());
        }
        this.mRowsFragment.getVerticalGridView().requestFocus();
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected Object createEntranceTransition() {
        return TransitionHelper.loadTransition(FragmentUtil.getContext(this), R.transition.lb_details_enter_transition);
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void runEntranceTransition(Object obj) {
        TransitionHelper.runTransition(this.mSceneAfterEntranceTransition, obj);
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void onEntranceTransitionEnd() {
        this.mStartAndTransitionFlag &= -3;
        dispatchOnStartAndTransitionFinished();
        this.mRowsFragment.onTransitionEnd();
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void onEntranceTransitionPrepare() {
        this.mStartAndTransitionFlag |= 2;
        this.mRowsFragment.onTransitionPrepare();
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void onEntranceTransitionStart() {
        this.mRowsFragment.onTransitionStart();
    }

    public DetailsParallax getParallax() {
        if (this.mDetailsParallax == null) {
            this.mDetailsParallax = new DetailsParallax();
            if (this.mRowsFragment != null && this.mRowsFragment.getView() != null) {
                this.mDetailsParallax.setRecyclerView(this.mRowsFragment.getVerticalGridView());
            }
        }
        return this.mDetailsParallax;
    }

    void setBackgroundDrawable(Drawable drawable) {
        if (this.mBackgroundView != null) {
            this.mBackgroundView.setBackground(drawable);
        }
        this.mBackgroundDrawable = drawable;
    }

    void setupDpadNavigation() {
        this.mRootView.setOnChildFocusListener(new BrowseFrameLayout.OnChildFocusListener() { // from class: android.support.v17.leanback.app.DetailsFragment.6
            @Override // android.support.v17.leanback.widget.BrowseFrameLayout.OnChildFocusListener
            public boolean onRequestFocusInDescendants(int i, Rect rect) {
                return false;
            }

            @Override // android.support.v17.leanback.widget.BrowseFrameLayout.OnChildFocusListener
            public void onRequestChildFocus(View view, View view2) {
                if (view != DetailsFragment.this.mRootView.getFocusedChild()) {
                    if (view.getId() == R.id.details_fragment_root) {
                        DetailsFragment.this.slideInGridView();
                        DetailsFragment.this.showTitle(true);
                    } else if (view.getId() == R.id.video_surface_container) {
                        DetailsFragment.this.slideOutGridView();
                        DetailsFragment.this.showTitle(false);
                    } else {
                        DetailsFragment.this.showTitle(true);
                    }
                }
            }
        });
        this.mRootView.setOnFocusSearchListener(new BrowseFrameLayout.OnFocusSearchListener() { // from class: android.support.v17.leanback.app.DetailsFragment.7
            @Override // android.support.v17.leanback.widget.BrowseFrameLayout.OnFocusSearchListener
            public View onFocusSearch(View view, int i) {
                if (DetailsFragment.this.mRowsFragment.getVerticalGridView() == null || !DetailsFragment.this.mRowsFragment.getVerticalGridView().hasFocus()) {
                    if (DetailsFragment.this.mVideoFragment != null && DetailsFragment.this.mVideoFragment.getView() != null && DetailsFragment.this.mVideoFragment.getView().hasFocus()) {
                        if (i == 130 && DetailsFragment.this.mRowsFragment.getVerticalGridView() != null) {
                            return DetailsFragment.this.mRowsFragment.getVerticalGridView();
                        }
                    } else if (DetailsFragment.this.getTitleView() != null && DetailsFragment.this.getTitleView().hasFocus() && i == 130 && DetailsFragment.this.mRowsFragment.getVerticalGridView() != null) {
                        return DetailsFragment.this.mRowsFragment.getVerticalGridView();
                    }
                } else if (i == 33) {
                    if (DetailsFragment.this.mVideoFragment != null && DetailsFragment.this.mVideoFragment.getView() != null) {
                        return DetailsFragment.this.mVideoFragment.getView();
                    }
                    if (DetailsFragment.this.getTitleView() != null && DetailsFragment.this.getTitleView().hasFocusable()) {
                        return DetailsFragment.this.getTitleView();
                    }
                }
                return view;
            }
        });
        this.mRootView.setOnDispatchKeyListener(new View.OnKeyListener() { // from class: android.support.v17.leanback.app.DetailsFragment.8
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (DetailsFragment.this.mVideoFragment == null || DetailsFragment.this.mVideoFragment.getView() == null || !DetailsFragment.this.mVideoFragment.getView().hasFocus()) {
                    return false;
                }
                if (i != 4 && i != 111) {
                    return false;
                }
                DetailsFragment.this.getVerticalGridView().requestFocus();
                return true;
            }
        });
    }

    void slideOutGridView() {
        if (getVerticalGridView() != null) {
            getVerticalGridView().animateOut();
        }
    }

    void slideInGridView() {
        if (getVerticalGridView() != null) {
            getVerticalGridView().animateIn();
        }
    }
}
