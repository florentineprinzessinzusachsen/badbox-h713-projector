package android.support.v17.leanback.app;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v17.leanback.R;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.DividerPresenter;
import android.support.v17.leanback.widget.DividerRow;
import android.support.v17.leanback.widget.FocusHighlightHelper;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowHeaderPresenter;
import android.support.v17.leanback.widget.SectionRow;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes.dex */
public class HeadersSupportFragment extends BaseRowSupportFragment {
    private static final PresenterSelector sHeaderPresenter = new ClassPresenterSelector().addClassPresenter(DividerRow.class, new DividerPresenter()).addClassPresenter(SectionRow.class, new RowHeaderPresenter(R.layout.lb_section_header, false)).addClassPresenter(Row.class, new RowHeaderPresenter(R.layout.lb_header));
    static View.OnLayoutChangeListener sLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: android.support.v17.leanback.app.HeadersSupportFragment.2
        @Override // android.view.View.OnLayoutChangeListener
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            view.setPivotX(view.getLayoutDirection() == 1 ? view.getWidth() : 0.0f);
            view.setPivotY(view.getMeasuredHeight() / 2);
        }
    };
    private int mBackgroundColor;
    private boolean mBackgroundColorSet;
    OnHeaderClickedListener mOnHeaderClickedListener;
    private OnHeaderViewSelectedListener mOnHeaderViewSelectedListener;
    private boolean mHeadersEnabled = true;
    private boolean mHeadersGone = false;
    private final ItemBridgeAdapter.AdapterListener mAdapterListener = new ItemBridgeAdapter.AdapterListener() { // from class: android.support.v17.leanback.app.HeadersSupportFragment.1
        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onCreate(final ItemBridgeAdapter.ViewHolder viewHolder) {
            View view = viewHolder.getViewHolder().view;
            view.setOnClickListener(new View.OnClickListener() { // from class: android.support.v17.leanback.app.HeadersSupportFragment.1.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    if (HeadersSupportFragment.this.mOnHeaderClickedListener != null) {
                        HeadersSupportFragment.this.mOnHeaderClickedListener.onHeaderClicked((RowHeaderPresenter.ViewHolder) viewHolder.getViewHolder(), (Row) viewHolder.getItem());
                    }
                }
            });
            if (HeadersSupportFragment.this.mWrapper != null) {
                viewHolder.itemView.addOnLayoutChangeListener(HeadersSupportFragment.sLayoutChangeListener);
            } else {
                view.addOnLayoutChangeListener(HeadersSupportFragment.sLayoutChangeListener);
            }
        }
    };
    final ItemBridgeAdapter.Wrapper mWrapper = new ItemBridgeAdapter.Wrapper() { // from class: android.support.v17.leanback.app.HeadersSupportFragment.3
        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.Wrapper
        public void wrap(View view, View view2) {
            ((FrameLayout) view).addView(view2);
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.Wrapper
        public View createWrapper(View view) {
            return new NoOverlappingFrameLayout(view.getContext());
        }
    };

    public interface OnHeaderClickedListener {
        void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row);
    }

    public interface OnHeaderViewSelectedListener {
        void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row);
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    public /* bridge */ /* synthetic */ int getSelectedPosition() {
        return super.getSelectedPosition();
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment, android.support.v4.app.Fragment
    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment, android.support.v4.app.Fragment
    public /* bridge */ /* synthetic */ void onDestroyView() {
        super.onDestroyView();
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment, android.support.v4.app.Fragment
    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    public /* bridge */ /* synthetic */ boolean onTransitionPrepare() {
        return super.onTransitionPrepare();
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    public /* bridge */ /* synthetic */ void setAlignment(int i) {
        super.setAlignment(i);
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    public /* bridge */ /* synthetic */ void setSelectedPosition(int i) {
        super.setSelectedPosition(i);
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    public /* bridge */ /* synthetic */ void setSelectedPosition(int i, boolean z) {
        super.setSelectedPosition(i, z);
    }

    public HeadersSupportFragment() {
        setPresenterSelector(sHeaderPresenter);
    }

    public void setOnHeaderClickedListener(OnHeaderClickedListener onHeaderClickedListener) {
        this.mOnHeaderClickedListener = onHeaderClickedListener;
    }

    public void setOnHeaderViewSelectedListener(OnHeaderViewSelectedListener onHeaderViewSelectedListener) {
        this.mOnHeaderViewSelectedListener = onHeaderViewSelectedListener;
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    VerticalGridView findGridViewFromRoot(View view) {
        return (VerticalGridView) view.findViewById(R.id.browse_headers);
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    void onRowSelected(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        if (this.mOnHeaderViewSelectedListener != null) {
            if (viewHolder != null && i >= 0) {
                ItemBridgeAdapter.ViewHolder viewHolder2 = (ItemBridgeAdapter.ViewHolder) viewHolder;
                this.mOnHeaderViewSelectedListener.onHeaderSelected((RowHeaderPresenter.ViewHolder) viewHolder2.getViewHolder(), (Row) viewHolder2.getItem());
            } else {
                this.mOnHeaderViewSelectedListener.onHeaderSelected(null, null);
            }
        }
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    int getLayoutResourceId() {
        return R.layout.lb_headers_fragment;
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment, android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        VerticalGridView verticalGridView = getVerticalGridView();
        if (verticalGridView == null) {
            return;
        }
        FocusHighlightHelper.setupHeaderItemFocusHighlight(verticalGridView);
        if (this.mBackgroundColorSet) {
            verticalGridView.setBackgroundColor(this.mBackgroundColor);
            updateFadingEdgeToBrandColor(this.mBackgroundColor);
        } else {
            Drawable background = verticalGridView.getBackground();
            if (background instanceof ColorDrawable) {
                updateFadingEdgeToBrandColor(((ColorDrawable) background).getColor());
            }
        }
        updateListViewVisibility();
    }

    private void updateListViewVisibility() {
        VerticalGridView verticalGridView = getVerticalGridView();
        if (verticalGridView != null) {
            getView().setVisibility(this.mHeadersGone ? 8 : 0);
            if (this.mHeadersGone) {
                return;
            }
            if (this.mHeadersEnabled) {
                verticalGridView.setChildrenVisibility(0);
            } else {
                verticalGridView.setChildrenVisibility(4);
            }
        }
    }

    void setHeadersEnabled(boolean z) {
        this.mHeadersEnabled = z;
        updateListViewVisibility();
    }

    void setHeadersGone(boolean z) {
        this.mHeadersGone = z;
        updateListViewVisibility();
    }

    static class NoOverlappingFrameLayout extends FrameLayout {
        @Override // android.view.View
        public boolean hasOverlappingRendering() {
            return false;
        }

        public NoOverlappingFrameLayout(Context context) {
            super(context);
        }
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    void updateAdapter() {
        super.updateAdapter();
        ItemBridgeAdapter bridgeAdapter = getBridgeAdapter();
        bridgeAdapter.setAdapterListener(this.mAdapterListener);
        bridgeAdapter.setWrapper(this.mWrapper);
    }

    void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        this.mBackgroundColorSet = true;
        if (getVerticalGridView() != null) {
            getVerticalGridView().setBackgroundColor(this.mBackgroundColor);
            updateFadingEdgeToBrandColor(this.mBackgroundColor);
        }
    }

    private void updateFadingEdgeToBrandColor(int i) {
        Drawable background = getView().findViewById(R.id.fade_out_edge).getBackground();
        if (background instanceof GradientDrawable) {
            background.mutate();
            ((GradientDrawable) background).setColors(new int[]{0, i});
        }
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    public void onTransitionStart() {
        VerticalGridView verticalGridView;
        super.onTransitionStart();
        if (this.mHeadersEnabled || (verticalGridView = getVerticalGridView()) == null) {
            return;
        }
        verticalGridView.setDescendantFocusability(131072);
        if (verticalGridView.hasFocus()) {
            verticalGridView.requestFocus();
        }
    }

    @Override // android.support.v17.leanback.app.BaseRowSupportFragment
    public void onTransitionEnd() {
        VerticalGridView verticalGridView;
        if (this.mHeadersEnabled && (verticalGridView = getVerticalGridView()) != null) {
            verticalGridView.setDescendantFocusability(262144);
            if (verticalGridView.hasFocus()) {
                verticalGridView.requestFocus();
            }
        }
        super.onTransitionEnd();
    }

    public boolean isScrolling() {
        return getVerticalGridView().getScrollState() != 0;
    }
}
