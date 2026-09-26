package android.support.v17.leanback.app;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v17.leanback.R;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.transition.TransitionListener;
import android.support.v17.leanback.widget.BrowseFrameLayout;
import android.support.v17.leanback.widget.InvisibleRowPresenter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.PageRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.ScaleFrameLayout;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BrowseFragment extends BaseFragment {
    private static final String CURRENT_SELECTED_POSITION = "currentSelectedPosition";
    static boolean DEBUG = false;
    public static final int HEADERS_DISABLED = 3;
    public static final int HEADERS_ENABLED = 1;
    public static final int HEADERS_HIDDEN = 2;
    static final String HEADER_SHOW = "headerShow";
    static final String HEADER_STACK_INDEX = "headerStackIndex";
    private static final String IS_PAGE_ROW = "isPageRow";
    private static final String LB_HEADERS_BACKSTACK = "lbHeadersBackStack_";
    static final String TAG = "BrowseFragment";
    private ObjectAdapter mAdapter;
    private PresenterSelector mAdapterPresenter;
    BackStackListener mBackStackChangedListener;
    private boolean mBrandColorSet;
    BrowseFrameLayout mBrowseFrame;
    BrowseTransitionListener mBrowseTransitionListener;
    private int mContainerListAlignTop;
    private int mContainerListMarginStart;
    OnItemViewSelectedListener mExternalOnItemViewSelectedListener;
    private PresenterSelector mHeaderPresenterSelector;
    HeadersFragment mHeadersFragment;
    Object mHeadersTransition;
    boolean mIsPageRow;
    Fragment mMainFragment;
    MainFragmentAdapter mMainFragmentAdapter;
    private MainFragmentRowsAdapter mMainFragmentRowsAdapter;
    private OnItemViewClickedListener mOnItemViewClickedListener;
    private float mScaleFactor;
    private ScaleFrameLayout mScaleFrameLayout;
    private Object mSceneAfterEntranceTransition;
    Object mSceneWithHeaders;
    Object mSceneWithoutHeaders;
    String mWithHeadersBackStackName;
    private PresenterSelector mWrappingPresenterSelector;
    private static final String ARG_TITLE = BrowseFragment.class.getCanonicalName() + ".title";
    private static final String ARG_HEADERS_STATE = BrowseFragment.class.getCanonicalName() + ".headersState";
    private MainFragmentAdapterRegistry mMainFragmentAdapterRegistry = new MainFragmentAdapterRegistry();
    private int mHeadersState = 1;
    private int mBrandColor = 0;
    boolean mHeadersBackStackEnabled = true;
    boolean mShowingHeaders = true;
    boolean mCanShowHeaders = true;
    private boolean mMainFragmentScaleEnabled = true;
    private int mSelectedPosition = -1;
    private final SetSelectionRunnable mSetSelectionRunnable = new SetSelectionRunnable();
    private final BrowseFrameLayout.OnFocusSearchListener mOnFocusSearchListener = new BrowseFrameLayout.OnFocusSearchListener() { // from class: android.support.v17.leanback.app.BrowseFragment.3
        @Override // android.support.v17.leanback.widget.BrowseFrameLayout.OnFocusSearchListener
        public View onFocusSearch(View view, int i) {
            if (BrowseFragment.this.mCanShowHeaders && BrowseFragment.this.isInHeadersTransition()) {
                return view;
            }
            if (BrowseFragment.DEBUG) {
                Log.v(BrowseFragment.TAG, "onFocusSearch focused " + view + " + direction " + i);
            }
            if (BrowseFragment.this.getTitleView() != null && view != BrowseFragment.this.getTitleView() && i == 33) {
                return BrowseFragment.this.getTitleView();
            }
            if (BrowseFragment.this.getTitleView() != null && BrowseFragment.this.getTitleView().hasFocus() && i == 130) {
                return (BrowseFragment.this.mCanShowHeaders && BrowseFragment.this.mShowingHeaders) ? BrowseFragment.this.mHeadersFragment.getVerticalGridView() : BrowseFragment.this.mMainFragment.getView();
            }
            boolean z = ViewCompat.getLayoutDirection(view) == 1;
            int i2 = z ? 66 : 17;
            int i3 = z ? 17 : 66;
            if (BrowseFragment.this.mCanShowHeaders && i == i2) {
                return (BrowseFragment.this.isVerticalScrolling() || BrowseFragment.this.mShowingHeaders || !BrowseFragment.this.isHeadersDataReady()) ? view : BrowseFragment.this.mHeadersFragment.getVerticalGridView();
            }
            if (i == i3) {
                return (BrowseFragment.this.isVerticalScrolling() || BrowseFragment.this.mMainFragment == null || BrowseFragment.this.mMainFragment.getView() == null) ? view : BrowseFragment.this.mMainFragment.getView();
            }
            if (i == 130 && BrowseFragment.this.mShowingHeaders) {
                return view;
            }
            return null;
        }
    };
    private final BrowseFrameLayout.OnChildFocusListener mOnChildFocusListener = new BrowseFrameLayout.OnChildFocusListener() { // from class: android.support.v17.leanback.app.BrowseFragment.4
        @Override // android.support.v17.leanback.widget.BrowseFrameLayout.OnChildFocusListener
        public boolean onRequestFocusInDescendants(int i, Rect rect) {
            if (BrowseFragment.this.getChildFragmentManager().isDestroyed()) {
                return true;
            }
            if (BrowseFragment.this.mCanShowHeaders && BrowseFragment.this.mShowingHeaders && BrowseFragment.this.mHeadersFragment != null && BrowseFragment.this.mHeadersFragment.getView() != null && BrowseFragment.this.mHeadersFragment.getView().requestFocus(i, rect)) {
                return true;
            }
            if (BrowseFragment.this.mMainFragment == null || BrowseFragment.this.mMainFragment.getView() == null || !BrowseFragment.this.mMainFragment.getView().requestFocus(i, rect)) {
                return BrowseFragment.this.getTitleView() != null && BrowseFragment.this.getTitleView().requestFocus(i, rect);
            }
            return true;
        }

        @Override // android.support.v17.leanback.widget.BrowseFrameLayout.OnChildFocusListener
        public void onRequestChildFocus(View view, View view2) {
            if (BrowseFragment.this.getChildFragmentManager().isDestroyed() || !BrowseFragment.this.mCanShowHeaders || BrowseFragment.this.isInHeadersTransition()) {
                return;
            }
            int id = view.getId();
            if (id == R.id.browse_container_dock && BrowseFragment.this.mShowingHeaders) {
                BrowseFragment.this.startHeadersTransitionInternal(false);
            } else {
                if (id != R.id.browse_headers_dock || BrowseFragment.this.mShowingHeaders) {
                    return;
                }
                BrowseFragment.this.startHeadersTransitionInternal(true);
            }
        }
    };
    private HeadersFragment.OnHeaderClickedListener mHeaderClickedListener = new HeadersFragment.OnHeaderClickedListener() { // from class: android.support.v17.leanback.app.BrowseFragment.9
        @Override // android.support.v17.leanback.app.HeadersFragment.OnHeaderClickedListener
        public void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
            if (BrowseFragment.this.mCanShowHeaders && BrowseFragment.this.mShowingHeaders && !BrowseFragment.this.isInHeadersTransition()) {
                BrowseFragment.this.startHeadersTransitionInternal(false);
                BrowseFragment.this.mMainFragment.getView().requestFocus();
            }
        }
    };
    private HeadersFragment.OnHeaderViewSelectedListener mHeaderViewSelectedListener = new HeadersFragment.OnHeaderViewSelectedListener() { // from class: android.support.v17.leanback.app.BrowseFragment.10
        @Override // android.support.v17.leanback.app.HeadersFragment.OnHeaderViewSelectedListener
        public void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row) {
            int selectedPosition = BrowseFragment.this.mHeadersFragment.getSelectedPosition();
            if (BrowseFragment.DEBUG) {
                Log.v(BrowseFragment.TAG, "header selected position " + selectedPosition);
            }
            BrowseFragment.this.onRowSelected(selectedPosition);
        }
    };

    public static class BrowseTransitionListener {
        public void onHeadersTransitionStart(boolean z) {
        }

        public void onHeadersTransitionStop(boolean z) {
        }
    }

    public static abstract class FragmentFactory<T extends Fragment> {
        public abstract T createFragment(Object obj);
    }

    public interface FragmentHost {
        void notifyDataReady(MainFragmentAdapter mainFragmentAdapter);

        void notifyViewCreated(MainFragmentAdapter mainFragmentAdapter);

        void showTitleView(boolean z);
    }

    public interface MainFragmentAdapterProvider {
        MainFragmentAdapter getMainFragmentAdapter();
    }

    public interface MainFragmentRowsAdapterProvider {
        MainFragmentRowsAdapter getMainFragmentRowsAdapter();
    }

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

    final class BackStackListener implements FragmentManager.OnBackStackChangedListener {
        int mIndexOfHeadersBackStack = -1;
        int mLastEntryCount;

        BackStackListener() {
            this.mLastEntryCount = BrowseFragment.this.getFragmentManager().getBackStackEntryCount();
        }

        void load(Bundle bundle) {
            if (bundle != null) {
                this.mIndexOfHeadersBackStack = bundle.getInt(BrowseFragment.HEADER_STACK_INDEX, -1);
                BrowseFragment.this.mShowingHeaders = this.mIndexOfHeadersBackStack == -1;
            } else {
                if (BrowseFragment.this.mShowingHeaders) {
                    return;
                }
                BrowseFragment.this.getFragmentManager().beginTransaction().addToBackStack(BrowseFragment.this.mWithHeadersBackStackName).commit();
            }
        }

        void save(Bundle bundle) {
            bundle.putInt(BrowseFragment.HEADER_STACK_INDEX, this.mIndexOfHeadersBackStack);
        }

        @Override // android.app.FragmentManager.OnBackStackChangedListener
        public void onBackStackChanged() {
            if (BrowseFragment.this.getFragmentManager() == null) {
                Log.w(BrowseFragment.TAG, "getFragmentManager() is null, stack:", new Exception());
                return;
            }
            int backStackEntryCount = BrowseFragment.this.getFragmentManager().getBackStackEntryCount();
            if (backStackEntryCount > this.mLastEntryCount) {
                int i = backStackEntryCount - 1;
                if (BrowseFragment.this.mWithHeadersBackStackName.equals(BrowseFragment.this.getFragmentManager().getBackStackEntryAt(i).getName())) {
                    this.mIndexOfHeadersBackStack = i;
                }
            } else if (backStackEntryCount < this.mLastEntryCount && this.mIndexOfHeadersBackStack >= backStackEntryCount) {
                if (!BrowseFragment.this.isHeadersDataReady()) {
                    BrowseFragment.this.getFragmentManager().beginTransaction().addToBackStack(BrowseFragment.this.mWithHeadersBackStackName).commit();
                    return;
                } else {
                    this.mIndexOfHeadersBackStack = -1;
                    if (!BrowseFragment.this.mShowingHeaders) {
                        BrowseFragment.this.startHeadersTransitionInternal(true);
                    }
                }
            }
            this.mLastEntryCount = backStackEntryCount;
        }
    }

    private class SetSelectionRunnable implements Runnable {
        static final int TYPE_INTERNAL_SYNC = 0;
        static final int TYPE_INVALID = -1;
        static final int TYPE_USER_REQUEST = 1;
        private int mPosition;
        private boolean mSmooth;
        private int mType;

        SetSelectionRunnable() {
            reset();
        }

        void post(int i, int i2, boolean z) {
            if (i2 >= this.mType) {
                this.mPosition = i;
                this.mType = i2;
                this.mSmooth = z;
                BrowseFragment.this.mBrowseFrame.removeCallbacks(this);
                BrowseFragment.this.mBrowseFrame.post(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            BrowseFragment.this.setSelection(this.mPosition, this.mSmooth);
            reset();
        }

        private void reset() {
            this.mPosition = -1;
            this.mType = -1;
            this.mSmooth = false;
        }
    }

    private final class FragmentHostImpl implements FragmentHost {
        boolean mShowTitleView = true;
        boolean mDataReady = false;

        FragmentHostImpl() {
        }

        @Override // android.support.v17.leanback.app.BrowseFragment.FragmentHost
        public void notifyViewCreated(MainFragmentAdapter mainFragmentAdapter) {
            BrowseFragment.this.performPendingStates();
        }

        @Override // android.support.v17.leanback.app.BrowseFragment.FragmentHost
        public void notifyDataReady(MainFragmentAdapter mainFragmentAdapter) {
            this.mDataReady = true;
            if (BrowseFragment.this.mMainFragmentAdapter != null && BrowseFragment.this.mMainFragmentAdapter.getFragmentHost() == this && BrowseFragment.this.mIsPageRow) {
                BrowseFragment.this.performPendingStates();
            }
        }

        @Override // android.support.v17.leanback.app.BrowseFragment.FragmentHost
        public void showTitleView(boolean z) {
            this.mShowTitleView = z;
            if (BrowseFragment.this.mMainFragmentAdapter != null && BrowseFragment.this.mMainFragmentAdapter.getFragmentHost() == this && BrowseFragment.this.mIsPageRow) {
                BrowseFragment.this.updateTitleViewVisibility();
            }
        }
    }

    public static class MainFragmentAdapter<T extends Fragment> {
        private final T mFragment;
        FragmentHostImpl mFragmentHost;
        private boolean mScalingEnabled;

        public boolean isScrolling() {
            return false;
        }

        public void onTransitionEnd() {
        }

        public boolean onTransitionPrepare() {
            return false;
        }

        public void onTransitionStart() {
        }

        public void setAlignment(int i) {
        }

        public void setEntranceTransitionState(boolean z) {
        }

        public void setExpand(boolean z) {
        }

        public MainFragmentAdapter(T t) {
            this.mFragment = t;
        }

        public final T getFragment() {
            return this.mFragment;
        }

        public boolean isScalingEnabled() {
            return this.mScalingEnabled;
        }

        public void setScalingEnabled(boolean z) {
            this.mScalingEnabled = z;
        }

        public final FragmentHost getFragmentHost() {
            return this.mFragmentHost;
        }

        void setFragmentHost(FragmentHostImpl fragmentHostImpl) {
            this.mFragmentHost = fragmentHostImpl;
        }
    }

    public static class MainFragmentRowsAdapter<T extends Fragment> {
        private final T mFragment;

        public RowPresenter.ViewHolder findRowViewHolderByPosition(int i) {
            return null;
        }

        public int getSelectedPosition() {
            return 0;
        }

        public void setAdapter(ObjectAdapter objectAdapter) {
        }

        public void setOnItemViewClickedListener(OnItemViewClickedListener onItemViewClickedListener) {
        }

        public void setOnItemViewSelectedListener(OnItemViewSelectedListener onItemViewSelectedListener) {
        }

        public void setSelectedPosition(int i, boolean z) {
        }

        public void setSelectedPosition(int i, boolean z, Presenter.ViewHolderTask viewHolderTask) {
        }

        public MainFragmentRowsAdapter(T t) {
            if (t == null) {
                throw new IllegalArgumentException("Fragment can't be null");
            }
            this.mFragment = t;
        }

        public final T getFragment() {
            return this.mFragment;
        }
    }

    private boolean createMainFragment(ObjectAdapter objectAdapter, int i) {
        if (objectAdapter == null || objectAdapter.size() == 0) {
            return false;
        }
        if (i < 0) {
            i = 0;
        } else if (i >= objectAdapter.size()) {
            throw new IllegalArgumentException(String.format("Invalid position %d requested", Integer.valueOf(i)));
        }
        Object obj = objectAdapter.get(i);
        boolean z = this.mIsPageRow;
        this.mIsPageRow = obj instanceof PageRow;
        boolean z2 = (this.mMainFragment == null || z) ? true : this.mIsPageRow;
        if (z2) {
            this.mMainFragment = this.mMainFragmentAdapterRegistry.createFragment(obj);
            if (!(this.mMainFragment instanceof MainFragmentAdapterProvider)) {
                throw new IllegalArgumentException("Fragment must implement MainFragmentAdapterProvider");
            }
            this.mMainFragmentAdapter = ((MainFragmentAdapterProvider) this.mMainFragment).getMainFragmentAdapter();
            this.mMainFragmentAdapter.setFragmentHost(new FragmentHostImpl());
            if (!this.mIsPageRow) {
                if (this.mMainFragment instanceof MainFragmentRowsAdapterProvider) {
                    this.mMainFragmentRowsAdapter = ((MainFragmentRowsAdapterProvider) this.mMainFragment).getMainFragmentRowsAdapter();
                } else {
                    this.mMainFragmentRowsAdapter = null;
                }
                this.mIsPageRow = this.mMainFragmentRowsAdapter == null;
            } else {
                this.mMainFragmentRowsAdapter = null;
            }
        }
        return z2;
    }

    public static class ListRowFragmentFactory extends FragmentFactory<RowsFragment> {
        @Override // android.support.v17.leanback.app.BrowseFragment.FragmentFactory
        public RowsFragment createFragment(Object obj) {
            return new RowsFragment();
        }
    }

    public static final class MainFragmentAdapterRegistry {
        private static final FragmentFactory sDefaultFragmentFactory = new ListRowFragmentFactory();
        private final Map<Class, FragmentFactory> mItemToFragmentFactoryMapping = new HashMap();

        public MainFragmentAdapterRegistry() {
            registerFragment(ListRow.class, sDefaultFragmentFactory);
        }

        public void registerFragment(Class cls, FragmentFactory fragmentFactory) {
            this.mItemToFragmentFactoryMapping.put(cls, fragmentFactory);
        }

        public Fragment createFragment(Object obj) {
            if (obj == null) {
                throw new IllegalArgumentException("Item can't be null");
            }
            FragmentFactory fragmentFactory = this.mItemToFragmentFactoryMapping.get(obj.getClass());
            if (fragmentFactory == null && !(obj instanceof PageRow)) {
                fragmentFactory = sDefaultFragmentFactory;
            }
            return fragmentFactory.createFragment(obj);
        }
    }

    public static Bundle createArgs(Bundle bundle, String str, int i) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString(ARG_TITLE, str);
        bundle.putInt(ARG_HEADERS_STATE, i);
        return bundle;
    }

    public void setBrandColor(@ColorInt int i) {
        this.mBrandColor = i;
        this.mBrandColorSet = true;
        if (this.mHeadersFragment != null) {
            this.mHeadersFragment.setBackgroundColor(this.mBrandColor);
        }
    }

    @ColorInt
    public int getBrandColor() {
        return this.mBrandColor;
    }

    private void createAndSetWrapperPresenter() {
        final PresenterSelector presenterSelector = this.mAdapter.getPresenterSelector();
        if (presenterSelector == null) {
            throw new IllegalArgumentException("Adapter.getPresenterSelector() is null");
        }
        if (presenterSelector == this.mAdapterPresenter) {
            return;
        }
        this.mAdapterPresenter = presenterSelector;
        Presenter[] presenters = presenterSelector.getPresenters();
        final InvisibleRowPresenter invisibleRowPresenter = new InvisibleRowPresenter();
        final Presenter[] presenterArr = new Presenter[presenters.length + 1];
        System.arraycopy(presenterArr, 0, presenters, 0, presenters.length);
        presenterArr[presenterArr.length - 1] = invisibleRowPresenter;
        this.mAdapter.setPresenterSelector(new PresenterSelector() { // from class: android.support.v17.leanback.app.BrowseFragment.1
            @Override // android.support.v17.leanback.widget.PresenterSelector
            public Presenter getPresenter(Object obj) {
                if (((Row) obj).isRenderedAsRowView()) {
                    return presenterSelector.getPresenter(obj);
                }
                return invisibleRowPresenter;
            }

            @Override // android.support.v17.leanback.widget.PresenterSelector
            public Presenter[] getPresenters() {
                return presenterArr;
            }
        });
    }

    public void setAdapter(ObjectAdapter objectAdapter) {
        this.mAdapter = objectAdapter;
        createAndSetWrapperPresenter();
        if (getView() == null) {
            return;
        }
        replaceMainFragment(this.mSelectedPosition);
        if (objectAdapter != null) {
            if (this.mMainFragmentRowsAdapter != null) {
                this.mMainFragmentRowsAdapter.setAdapter(new ListRowDataAdapter(objectAdapter));
            }
            this.mHeadersFragment.setAdapter(objectAdapter);
        }
    }

    public final MainFragmentAdapterRegistry getMainFragmentRegistry() {
        return this.mMainFragmentAdapterRegistry;
    }

    public ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setOnItemViewSelectedListener(OnItemViewSelectedListener onItemViewSelectedListener) {
        this.mExternalOnItemViewSelectedListener = onItemViewSelectedListener;
    }

    public OnItemViewSelectedListener getOnItemViewSelectedListener() {
        return this.mExternalOnItemViewSelectedListener;
    }

    public RowsFragment getRowsFragment() {
        if (this.mMainFragment instanceof RowsFragment) {
            return (RowsFragment) this.mMainFragment;
        }
        return null;
    }

    public Fragment getMainFragment() {
        return this.mMainFragment;
    }

    public HeadersFragment getHeadersFragment() {
        return this.mHeadersFragment;
    }

    public void setOnItemViewClickedListener(OnItemViewClickedListener onItemViewClickedListener) {
        this.mOnItemViewClickedListener = onItemViewClickedListener;
        if (this.mMainFragmentRowsAdapter != null) {
            this.mMainFragmentRowsAdapter.setOnItemViewClickedListener(onItemViewClickedListener);
        }
    }

    public OnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mOnItemViewClickedListener;
    }

    public void startHeadersTransition(boolean z) {
        if (!this.mCanShowHeaders) {
            throw new IllegalStateException("Cannot start headers transition");
        }
        if (isInHeadersTransition() || this.mShowingHeaders == z) {
            return;
        }
        startHeadersTransitionInternal(z);
    }

    public boolean isInHeadersTransition() {
        return this.mHeadersTransition != null;
    }

    public boolean isShowingHeaders() {
        return this.mShowingHeaders;
    }

    public void setBrowseTransitionListener(BrowseTransitionListener browseTransitionListener) {
        this.mBrowseTransitionListener = browseTransitionListener;
    }

    @Deprecated
    public void enableRowScaling(boolean z) {
        enableMainFragmentScaling(z);
    }

    public void enableMainFragmentScaling(boolean z) {
        this.mMainFragmentScaleEnabled = z;
    }

    void startHeadersTransitionInternal(final boolean z) {
        if (!getFragmentManager().isDestroyed() && isHeadersDataReady()) {
            this.mShowingHeaders = z;
            this.mMainFragmentAdapter.onTransitionPrepare();
            this.mMainFragmentAdapter.onTransitionStart();
            onExpandTransitionStart(!z, new Runnable() { // from class: android.support.v17.leanback.app.BrowseFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    BrowseFragment.this.mHeadersFragment.onTransitionPrepare();
                    BrowseFragment.this.mHeadersFragment.onTransitionStart();
                    BrowseFragment.this.createHeadersTransition();
                    if (BrowseFragment.this.mBrowseTransitionListener != null) {
                        BrowseFragment.this.mBrowseTransitionListener.onHeadersTransitionStart(z);
                    }
                    TransitionHelper.runTransition(z ? BrowseFragment.this.mSceneWithHeaders : BrowseFragment.this.mSceneWithoutHeaders, BrowseFragment.this.mHeadersTransition);
                    if (BrowseFragment.this.mHeadersBackStackEnabled) {
                        if (!z) {
                            BrowseFragment.this.getFragmentManager().beginTransaction().addToBackStack(BrowseFragment.this.mWithHeadersBackStackName).commit();
                            return;
                        }
                        int i = BrowseFragment.this.mBackStackChangedListener.mIndexOfHeadersBackStack;
                        if (i >= 0) {
                            BrowseFragment.this.getFragmentManager().popBackStackImmediate(BrowseFragment.this.getFragmentManager().getBackStackEntryAt(i).getId(), 1);
                        }
                    }
                }
            });
        }
    }

    boolean isVerticalScrolling() {
        return this.mHeadersFragment.isScrolling() || this.mMainFragmentAdapter.isScrolling();
    }

    final boolean isHeadersDataReady() {
        return (this.mAdapter == null || this.mAdapter.size() == 0) ? false : true;
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(CURRENT_SELECTED_POSITION, this.mSelectedPosition);
        bundle.putBoolean(IS_PAGE_ROW, this.mIsPageRow);
        if (this.mBackStackChangedListener != null) {
            this.mBackStackChangedListener.save(bundle);
        } else {
            bundle.putBoolean(HEADER_SHOW, this.mShowingHeaders);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Context context = FragmentUtil.getContext(this);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(R.styleable.LeanbackTheme);
        this.mContainerListMarginStart = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.LeanbackTheme_browseRowsMarginStart, context.getResources().getDimensionPixelSize(R.dimen.lb_browse_rows_margin_start));
        this.mContainerListAlignTop = (int) typedArrayObtainStyledAttributes.getDimension(R.styleable.LeanbackTheme_browseRowsMarginTop, context.getResources().getDimensionPixelSize(R.dimen.lb_browse_rows_margin_top));
        typedArrayObtainStyledAttributes.recycle();
        readArguments(getArguments());
        if (this.mCanShowHeaders) {
            if (this.mHeadersBackStackEnabled) {
                this.mWithHeadersBackStackName = LB_HEADERS_BACKSTACK + this;
                this.mBackStackChangedListener = new BackStackListener();
                getFragmentManager().addOnBackStackChangedListener(this.mBackStackChangedListener);
                this.mBackStackChangedListener.load(bundle);
            } else if (bundle != null) {
                this.mShowingHeaders = bundle.getBoolean(HEADER_SHOW);
            }
        }
        this.mScaleFactor = getResources().getFraction(R.fraction.lb_browse_rows_scale, 1, 1);
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onDestroyView() {
        this.mMainFragmentRowsAdapter = null;
        this.mMainFragmentAdapter = null;
        this.mMainFragment = null;
        this.mHeadersFragment = null;
        super.onDestroyView();
    }

    @Override // android.app.Fragment
    public void onDestroy() {
        if (this.mBackStackChangedListener != null) {
            getFragmentManager().removeOnBackStackChangedListener(this.mBackStackChangedListener);
        }
        super.onDestroy();
    }

    public HeadersFragment onCreateHeadersFragment() {
        return new HeadersFragment();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getChildFragmentManager().findFragmentById(R.id.scale_frame) == null) {
            this.mHeadersFragment = onCreateHeadersFragment();
            createMainFragment(this.mAdapter, this.mSelectedPosition);
            FragmentTransaction fragmentTransactionReplace = getChildFragmentManager().beginTransaction().replace(R.id.browse_headers_dock, this.mHeadersFragment);
            if (this.mMainFragment != null) {
                fragmentTransactionReplace.replace(R.id.scale_frame, this.mMainFragment);
            } else {
                this.mMainFragmentAdapter = new MainFragmentAdapter(null);
                this.mMainFragmentAdapter.setFragmentHost(new FragmentHostImpl());
            }
            fragmentTransactionReplace.commit();
        } else {
            this.mHeadersFragment = (HeadersFragment) getChildFragmentManager().findFragmentById(R.id.browse_headers_dock);
            this.mMainFragment = getChildFragmentManager().findFragmentById(R.id.scale_frame);
            this.mMainFragmentAdapter = ((MainFragmentAdapterProvider) this.mMainFragment).getMainFragmentAdapter();
            this.mMainFragmentAdapter.setFragmentHost(new FragmentHostImpl());
            this.mIsPageRow = bundle != null && bundle.getBoolean(IS_PAGE_ROW, false);
            this.mSelectedPosition = bundle != null ? bundle.getInt(CURRENT_SELECTED_POSITION, 0) : 0;
            if (!this.mIsPageRow && (this.mMainFragment instanceof MainFragmentRowsAdapterProvider)) {
                this.mMainFragmentRowsAdapter = ((MainFragmentRowsAdapterProvider) this.mMainFragment).getMainFragmentRowsAdapter();
            } else {
                this.mMainFragmentRowsAdapter = null;
            }
        }
        this.mHeadersFragment.setHeadersGone(true ^ this.mCanShowHeaders);
        if (this.mHeaderPresenterSelector != null) {
            this.mHeadersFragment.setPresenterSelector(this.mHeaderPresenterSelector);
        }
        this.mHeadersFragment.setAdapter(this.mAdapter);
        this.mHeadersFragment.setOnHeaderViewSelectedListener(this.mHeaderViewSelectedListener);
        this.mHeadersFragment.setOnHeaderClickedListener(this.mHeaderClickedListener);
        View viewInflate = layoutInflater.inflate(R.layout.lb_browse_fragment, viewGroup, false);
        getProgressBarManager().setRootView((ViewGroup) viewInflate);
        this.mBrowseFrame = (BrowseFrameLayout) viewInflate.findViewById(R.id.browse_frame);
        this.mBrowseFrame.setOnChildFocusListener(this.mOnChildFocusListener);
        this.mBrowseFrame.setOnFocusSearchListener(this.mOnFocusSearchListener);
        installTitleView(layoutInflater, this.mBrowseFrame, bundle);
        this.mScaleFrameLayout = (ScaleFrameLayout) viewInflate.findViewById(R.id.scale_frame);
        this.mScaleFrameLayout.setPivotX(0.0f);
        this.mScaleFrameLayout.setPivotY(this.mContainerListAlignTop);
        setupMainFragment();
        if (this.mBrandColorSet) {
            this.mHeadersFragment.setBackgroundColor(this.mBrandColor);
        }
        this.mSceneWithHeaders = TransitionHelper.createScene(this.mBrowseFrame, new Runnable() { // from class: android.support.v17.leanback.app.BrowseFragment.5
            @Override // java.lang.Runnable
            public void run() {
                BrowseFragment.this.showHeaders(true);
            }
        });
        this.mSceneWithoutHeaders = TransitionHelper.createScene(this.mBrowseFrame, new Runnable() { // from class: android.support.v17.leanback.app.BrowseFragment.6
            @Override // java.lang.Runnable
            public void run() {
                BrowseFragment.this.showHeaders(false);
            }
        });
        this.mSceneAfterEntranceTransition = TransitionHelper.createScene(this.mBrowseFrame, new Runnable() { // from class: android.support.v17.leanback.app.BrowseFragment.7
            @Override // java.lang.Runnable
            public void run() {
                BrowseFragment.this.setEntranceTransitionEndState();
            }
        });
        return viewInflate;
    }

    private void setupMainFragment() {
        if (this.mMainFragmentRowsAdapter != null) {
            if (this.mAdapter != null) {
                this.mMainFragmentRowsAdapter.setAdapter(new ListRowDataAdapter(this.mAdapter));
            }
            this.mMainFragmentRowsAdapter.setOnItemViewSelectedListener(new MainFragmentItemViewSelectedListener(this.mMainFragmentRowsAdapter));
            this.mMainFragmentRowsAdapter.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        }
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    boolean isReadyForPrepareEntranceTransition() {
        return (this.mMainFragment == null || this.mMainFragment.getView() == null) ? false : true;
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    boolean isReadyForStartEntranceTransition() {
        return (this.mMainFragment == null || this.mMainFragment.getView() == null || (this.mIsPageRow && !this.mMainFragmentAdapter.mFragmentHost.mDataReady)) ? false : true;
    }

    void createHeadersTransition() {
        this.mHeadersTransition = TransitionHelper.loadTransition(FragmentUtil.getContext(this), this.mShowingHeaders ? R.transition.lb_browse_headers_in : R.transition.lb_browse_headers_out);
        TransitionHelper.addTransitionListener(this.mHeadersTransition, new TransitionListener() { // from class: android.support.v17.leanback.app.BrowseFragment.8
            @Override // android.support.v17.leanback.transition.TransitionListener
            public void onTransitionStart(Object obj) {
            }

            @Override // android.support.v17.leanback.transition.TransitionListener
            public void onTransitionEnd(Object obj) {
                VerticalGridView verticalGridView;
                View view;
                BrowseFragment.this.mHeadersTransition = null;
                if (BrowseFragment.this.mMainFragmentAdapter != null) {
                    BrowseFragment.this.mMainFragmentAdapter.onTransitionEnd();
                    if (!BrowseFragment.this.mShowingHeaders && BrowseFragment.this.mMainFragment != null && (view = BrowseFragment.this.mMainFragment.getView()) != null && !view.hasFocus()) {
                        view.requestFocus();
                    }
                }
                if (BrowseFragment.this.mHeadersFragment != null) {
                    BrowseFragment.this.mHeadersFragment.onTransitionEnd();
                    if (BrowseFragment.this.mShowingHeaders && (verticalGridView = BrowseFragment.this.mHeadersFragment.getVerticalGridView()) != null && !verticalGridView.hasFocus()) {
                        verticalGridView.requestFocus();
                    }
                }
                BrowseFragment.this.updateTitleViewVisibility();
                if (BrowseFragment.this.mBrowseTransitionListener != null) {
                    BrowseFragment.this.mBrowseTransitionListener.onHeadersTransitionStop(BrowseFragment.this.mShowingHeaders);
                }
            }
        });
    }

    void updateTitleViewVisibility() {
        boolean zIsFirstRowWithContent;
        boolean zIsFirstRowWithContent2;
        if (!this.mShowingHeaders) {
            if (this.mIsPageRow && this.mMainFragmentAdapter != null) {
                zIsFirstRowWithContent2 = this.mMainFragmentAdapter.mFragmentHost.mShowTitleView;
            } else {
                zIsFirstRowWithContent2 = isFirstRowWithContent(this.mSelectedPosition);
            }
            if (zIsFirstRowWithContent2) {
                showTitle(6);
                return;
            } else {
                showTitle(false);
                return;
            }
        }
        if (this.mIsPageRow && this.mMainFragmentAdapter != null) {
            zIsFirstRowWithContent = this.mMainFragmentAdapter.mFragmentHost.mShowTitleView;
        } else {
            zIsFirstRowWithContent = isFirstRowWithContent(this.mSelectedPosition);
        }
        boolean zIsFirstRowWithContentOrPageRow = isFirstRowWithContentOrPageRow(this.mSelectedPosition);
        int i = zIsFirstRowWithContent ? 2 : 0;
        if (zIsFirstRowWithContentOrPageRow) {
            i |= 4;
        }
        if (i != 0) {
            showTitle(i);
        } else {
            showTitle(false);
        }
    }

    boolean isFirstRowWithContentOrPageRow(int i) {
        if (this.mAdapter == null || this.mAdapter.size() == 0) {
            return true;
        }
        int i2 = 0;
        while (i2 < this.mAdapter.size()) {
            Row row = (Row) this.mAdapter.get(i2);
            if (row.isRenderedAsRowView() || (row instanceof PageRow)) {
                return i == i2;
            }
            i2++;
        }
        return true;
    }

    boolean isFirstRowWithContent(int i) {
        if (this.mAdapter == null || this.mAdapter.size() == 0) {
            return true;
        }
        int i2 = 0;
        while (i2 < this.mAdapter.size()) {
            if (((Row) this.mAdapter.get(i2)).isRenderedAsRowView()) {
                return i == i2;
            }
            i2++;
        }
        return true;
    }

    public void setHeaderPresenterSelector(PresenterSelector presenterSelector) {
        this.mHeaderPresenterSelector = presenterSelector;
        if (this.mHeadersFragment != null) {
            this.mHeadersFragment.setPresenterSelector(this.mHeaderPresenterSelector);
        }
    }

    private void setHeadersOnScreen(boolean z) {
        View view = this.mHeadersFragment.getView();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        marginLayoutParams.setMarginStart(z ? 0 : -this.mContainerListMarginStart);
        view.setLayoutParams(marginLayoutParams);
    }

    void showHeaders(boolean z) {
        if (DEBUG) {
            Log.v(TAG, "showHeaders " + z);
        }
        this.mHeadersFragment.setHeadersEnabled(z);
        setHeadersOnScreen(z);
        expandMainFragment(!z);
    }

    private void expandMainFragment(boolean z) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mScaleFrameLayout.getLayoutParams();
        marginLayoutParams.setMarginStart(!z ? this.mContainerListMarginStart : 0);
        this.mScaleFrameLayout.setLayoutParams(marginLayoutParams);
        this.mMainFragmentAdapter.setExpand(z);
        setMainFragmentAlignment();
        float f = (!z && this.mMainFragmentScaleEnabled && this.mMainFragmentAdapter.isScalingEnabled()) ? this.mScaleFactor : 1.0f;
        this.mScaleFrameLayout.setLayoutScaleY(f);
        this.mScaleFrameLayout.setChildScale(f);
    }

    class MainFragmentItemViewSelectedListener implements OnItemViewSelectedListener {
        MainFragmentRowsAdapter mMainFragmentRowsAdapter;

        public MainFragmentItemViewSelectedListener(MainFragmentRowsAdapter mainFragmentRowsAdapter) {
            this.mMainFragmentRowsAdapter = mainFragmentRowsAdapter;
        }

        @Override // android.support.v17.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
            int selectedPosition = this.mMainFragmentRowsAdapter.getSelectedPosition();
            if (BrowseFragment.DEBUG) {
                Log.v(BrowseFragment.TAG, "row selected position " + selectedPosition);
            }
            BrowseFragment.this.onRowSelected(selectedPosition);
            if (BrowseFragment.this.mExternalOnItemViewSelectedListener != null) {
                BrowseFragment.this.mExternalOnItemViewSelectedListener.onItemSelected(viewHolder, obj, viewHolder2, row);
            }
        }
    }

    void onRowSelected(int i) {
        if (i != this.mSelectedPosition) {
            this.mSetSelectionRunnable.post(i, 0, true);
        }
    }

    void setSelection(int i, boolean z) {
        if (i == -1) {
            return;
        }
        this.mSelectedPosition = i;
        if (this.mHeadersFragment == null || this.mMainFragmentAdapter == null) {
            return;
        }
        this.mHeadersFragment.setSelectedPosition(i, z);
        replaceMainFragment(i);
        if (this.mMainFragmentRowsAdapter != null) {
            this.mMainFragmentRowsAdapter.setSelectedPosition(i, z);
        }
        updateTitleViewVisibility();
    }

    private void replaceMainFragment(int i) {
        if (createMainFragment(this.mAdapter, i)) {
            swapToMainFragment();
            expandMainFragment((this.mCanShowHeaders && this.mShowingHeaders) ? false : true);
            setupMainFragment();
            performPendingStates();
        }
    }

    private void swapToMainFragment() {
        final VerticalGridView verticalGridView = this.mHeadersFragment.getVerticalGridView();
        if (isShowingHeaders() && verticalGridView != null && verticalGridView.getScrollState() != 0) {
            getChildFragmentManager().beginTransaction().replace(R.id.scale_frame, new Fragment()).commit();
            verticalGridView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: android.support.v17.leanback.app.BrowseFragment.11
                @Override // android.support.v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                    if (i == 0) {
                        verticalGridView.removeOnScrollListener(this);
                        FragmentManager childFragmentManager = BrowseFragment.this.getChildFragmentManager();
                        if (childFragmentManager.findFragmentById(R.id.scale_frame) != BrowseFragment.this.mMainFragment) {
                            childFragmentManager.beginTransaction().replace(R.id.scale_frame, BrowseFragment.this.mMainFragment).commit();
                        }
                    }
                }
            });
        } else {
            getChildFragmentManager().beginTransaction().replace(R.id.scale_frame, this.mMainFragment).commit();
        }
    }

    public void setSelectedPosition(int i) {
        setSelectedPosition(i, true);
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    public RowPresenter.ViewHolder getSelectedRowViewHolder() {
        if (this.mMainFragmentRowsAdapter == null) {
            return null;
        }
        return this.mMainFragmentRowsAdapter.findRowViewHolderByPosition(this.mMainFragmentRowsAdapter.getSelectedPosition());
    }

    public void setSelectedPosition(int i, boolean z) {
        this.mSetSelectionRunnable.post(i, 1, z);
    }

    public void setSelectedPosition(int i, boolean z, Presenter.ViewHolderTask viewHolderTask) {
        if (this.mMainFragmentAdapterRegistry == null) {
            return;
        }
        if (viewHolderTask != null) {
            startHeadersTransition(false);
        }
        if (this.mMainFragmentRowsAdapter != null) {
            this.mMainFragmentRowsAdapter.setSelectedPosition(i, z, viewHolderTask);
        }
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        this.mHeadersFragment.setAlignment(this.mContainerListAlignTop);
        setMainFragmentAlignment();
        if (this.mCanShowHeaders && this.mShowingHeaders && this.mHeadersFragment != null && this.mHeadersFragment.getView() != null) {
            this.mHeadersFragment.getView().requestFocus();
        } else if ((!this.mCanShowHeaders || !this.mShowingHeaders) && this.mMainFragment != null && this.mMainFragment.getView() != null) {
            this.mMainFragment.getView().requestFocus();
        }
        if (this.mCanShowHeaders) {
            showHeaders(this.mShowingHeaders);
        }
        if (isEntranceTransitionEnabled()) {
            setEntranceTransitionStartState();
        }
    }

    private void onExpandTransitionStart(boolean z, Runnable runnable) {
        if (z) {
            runnable.run();
        } else {
            new ExpandPreLayout(runnable, this.mMainFragmentAdapter, getView()).execute();
        }
    }

    private void setMainFragmentAlignment() {
        int i = this.mContainerListAlignTop;
        if (this.mMainFragmentScaleEnabled && this.mMainFragmentAdapter.isScalingEnabled() && this.mShowingHeaders) {
            i = (int) ((i / this.mScaleFactor) + 0.5f);
        }
        this.mMainFragmentAdapter.setAlignment(i);
    }

    public final void setHeadersTransitionOnBackEnabled(boolean z) {
        this.mHeadersBackStackEnabled = z;
    }

    public final boolean isHeadersTransitionOnBackEnabled() {
        return this.mHeadersBackStackEnabled;
    }

    private void readArguments(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        if (bundle.containsKey(ARG_TITLE)) {
            setTitle(bundle.getString(ARG_TITLE));
        }
        if (bundle.containsKey(ARG_HEADERS_STATE)) {
            setHeadersState(bundle.getInt(ARG_HEADERS_STATE));
        }
    }

    public void setHeadersState(int i) {
        if (i < 1 || i > 3) {
            throw new IllegalArgumentException("Invalid headers state: " + i);
        }
        if (DEBUG) {
            Log.v(TAG, "setHeadersState " + i);
        }
        if (i != this.mHeadersState) {
            this.mHeadersState = i;
            switch (i) {
                case 1:
                    this.mCanShowHeaders = true;
                    this.mShowingHeaders = true;
                    break;
                case 2:
                    this.mCanShowHeaders = true;
                    this.mShowingHeaders = false;
                    break;
                case 3:
                    this.mCanShowHeaders = false;
                    this.mShowingHeaders = false;
                    break;
                default:
                    Log.w(TAG, "Unknown headers state: " + i);
                    break;
            }
            if (this.mHeadersFragment != null) {
                this.mHeadersFragment.setHeadersGone(!this.mCanShowHeaders);
            }
        }
    }

    public int getHeadersState() {
        return this.mHeadersState;
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected Object createEntranceTransition() {
        return TransitionHelper.loadTransition(FragmentUtil.getContext(this), R.transition.lb_browse_entrance_transition);
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void runEntranceTransition(Object obj) {
        TransitionHelper.runTransition(this.mSceneAfterEntranceTransition, obj);
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void onEntranceTransitionPrepare() {
        this.mHeadersFragment.onTransitionPrepare();
        this.mMainFragmentAdapter.setEntranceTransitionState(false);
        this.mMainFragmentAdapter.onTransitionPrepare();
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void onEntranceTransitionStart() {
        this.mHeadersFragment.onTransitionStart();
        this.mMainFragmentAdapter.onTransitionStart();
    }

    @Override // android.support.v17.leanback.app.BaseFragment
    protected void onEntranceTransitionEnd() {
        if (this.mMainFragmentAdapter != null) {
            this.mMainFragmentAdapter.onTransitionEnd();
        }
        if (this.mHeadersFragment != null) {
            this.mHeadersFragment.onTransitionEnd();
        }
    }

    void setSearchOrbViewOnScreen(boolean z) {
        View searchAffordanceView = getTitleViewAdapter().getSearchAffordanceView();
        if (searchAffordanceView != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) searchAffordanceView.getLayoutParams();
            marginLayoutParams.setMarginStart(z ? 0 : -this.mContainerListMarginStart);
            searchAffordanceView.setLayoutParams(marginLayoutParams);
        }
    }

    void setEntranceTransitionStartState() {
        setHeadersOnScreen(false);
        setSearchOrbViewOnScreen(false);
        this.mMainFragmentAdapter.setEntranceTransitionState(false);
    }

    void setEntranceTransitionEndState() {
        setHeadersOnScreen(this.mShowingHeaders);
        setSearchOrbViewOnScreen(true);
        this.mMainFragmentAdapter.setEntranceTransitionState(true);
    }

    private class ExpandPreLayout implements ViewTreeObserver.OnPreDrawListener {
        static final int STATE_FIRST_DRAW = 1;
        static final int STATE_INIT = 0;
        static final int STATE_SECOND_DRAW = 2;
        private final Runnable mCallback;
        private int mState;
        private final View mView;
        private MainFragmentAdapter mainFragmentAdapter;

        ExpandPreLayout(Runnable runnable, MainFragmentAdapter mainFragmentAdapter, View view) {
            this.mView = view;
            this.mCallback = runnable;
            this.mainFragmentAdapter = mainFragmentAdapter;
        }

        void execute() {
            this.mView.getViewTreeObserver().addOnPreDrawListener(this);
            this.mainFragmentAdapter.setExpand(false);
            this.mView.invalidate();
            this.mState = 0;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            if (BrowseFragment.this.getView() == null || FragmentUtil.getContext(BrowseFragment.this) == null) {
                this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
                return true;
            }
            if (this.mState == 0) {
                this.mainFragmentAdapter.setExpand(true);
                this.mView.invalidate();
                this.mState = 1;
                return false;
            }
            if (this.mState != 1) {
                return false;
            }
            this.mCallback.run();
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mState = 2;
            return false;
        }
    }
}
