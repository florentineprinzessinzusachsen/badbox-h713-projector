package android.support.v17.leanback.app;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.OnChildViewHolderSelectedListener;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseRowFragment extends Fragment {
    private static final String CURRENT_SELECTED_POSITION = "currentSelectedPosition";
    private ObjectAdapter mAdapter;
    private boolean mPendingTransitionPrepare;
    private PresenterSelector mPresenterSelector;
    VerticalGridView mVerticalGridView;
    final ItemBridgeAdapter mBridgeAdapter = new ItemBridgeAdapter();
    int mSelectedPosition = -1;
    private LateSelectionObserver mLateSelectionObserver = new LateSelectionObserver();
    private final OnChildViewHolderSelectedListener mRowSelectedListener = new OnChildViewHolderSelectedListener() { // from class: android.support.v17.leanback.app.BaseRowFragment.1
        @Override // android.support.v17.leanback.widget.OnChildViewHolderSelectedListener
        public void onChildViewHolderSelected(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
            if (BaseRowFragment.this.mLateSelectionObserver.mIsLateSelection) {
                return;
            }
            BaseRowFragment.this.mSelectedPosition = i;
            BaseRowFragment.this.onRowSelected(recyclerView, viewHolder, i, i2);
        }
    };

    abstract int getLayoutResourceId();

    void onRowSelected(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
    }

    BaseRowFragment() {
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewInflate = layoutInflater.inflate(getLayoutResourceId(), viewGroup, false);
        this.mVerticalGridView = findGridViewFromRoot(viewInflate);
        if (this.mPendingTransitionPrepare) {
            this.mPendingTransitionPrepare = false;
            onTransitionPrepare();
        }
        return viewInflate;
    }

    VerticalGridView findGridViewFromRoot(View view) {
        return (VerticalGridView) view;
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        if (bundle != null) {
            this.mSelectedPosition = bundle.getInt(CURRENT_SELECTED_POSITION, -1);
        }
        setAdapterAndSelection();
        this.mVerticalGridView.setOnChildViewHolderSelectedListener(this.mRowSelectedListener);
    }

    private class LateSelectionObserver extends RecyclerView.AdapterDataObserver {
        boolean mIsLateSelection = false;

        LateSelectionObserver() {
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            performLateSelection();
        }

        @Override // android.support.v7.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int i, int i2) {
            performLateSelection();
        }

        void startLateSelection() {
            this.mIsLateSelection = true;
            BaseRowFragment.this.mBridgeAdapter.registerAdapterDataObserver(this);
        }

        void performLateSelection() {
            clear();
            if (BaseRowFragment.this.mVerticalGridView != null) {
                BaseRowFragment.this.mVerticalGridView.setSelectedPosition(BaseRowFragment.this.mSelectedPosition);
            }
        }

        void clear() {
            if (this.mIsLateSelection) {
                this.mIsLateSelection = false;
                BaseRowFragment.this.mBridgeAdapter.unregisterAdapterDataObserver(this);
            }
        }
    }

    void setAdapterAndSelection() {
        this.mVerticalGridView.setAdapter(this.mBridgeAdapter);
        if (this.mBridgeAdapter.getItemCount() == 0 && this.mSelectedPosition >= 0) {
            this.mLateSelectionObserver.startLateSelection();
        } else if (this.mSelectedPosition >= 0) {
            this.mVerticalGridView.setSelectedPosition(this.mSelectedPosition);
        }
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mLateSelectionObserver.clear();
        this.mVerticalGridView = null;
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(CURRENT_SELECTED_POSITION, this.mSelectedPosition);
    }

    public final void setPresenterSelector(PresenterSelector presenterSelector) {
        this.mPresenterSelector = presenterSelector;
        updateAdapter();
    }

    public final PresenterSelector getPresenterSelector() {
        return this.mPresenterSelector;
    }

    public final void setAdapter(ObjectAdapter objectAdapter) {
        this.mAdapter = objectAdapter;
        updateAdapter();
    }

    public final ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    final ItemBridgeAdapter getBridgeAdapter() {
        return this.mBridgeAdapter;
    }

    public void setSelectedPosition(int i) {
        setSelectedPosition(i, true);
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    public void setSelectedPosition(int i, boolean z) {
        if (this.mSelectedPosition == i) {
            return;
        }
        this.mSelectedPosition = i;
        if (this.mVerticalGridView == null || this.mLateSelectionObserver.mIsLateSelection) {
            return;
        }
        if (z) {
            this.mVerticalGridView.setSelectedPositionSmooth(i);
        } else {
            this.mVerticalGridView.setSelectedPosition(i);
        }
    }

    public final VerticalGridView getVerticalGridView() {
        return this.mVerticalGridView;
    }

    void updateAdapter() {
        this.mBridgeAdapter.setAdapter(this.mAdapter);
        this.mBridgeAdapter.setPresenter(this.mPresenterSelector);
        if (this.mVerticalGridView != null) {
            setAdapterAndSelection();
        }
    }

    Object getItem(Row row, int i) {
        if (row instanceof ListRow) {
            return ((ListRow) row).getAdapter().get(i);
        }
        return null;
    }

    public boolean onTransitionPrepare() {
        if (this.mVerticalGridView != null) {
            this.mVerticalGridView.setAnimateChildLayout(false);
            this.mVerticalGridView.setScrollEnabled(false);
            return true;
        }
        this.mPendingTransitionPrepare = true;
        return false;
    }

    public void onTransitionStart() {
        if (this.mVerticalGridView != null) {
            this.mVerticalGridView.setPruneChild(false);
            this.mVerticalGridView.setLayoutFrozen(true);
            this.mVerticalGridView.setFocusSearchDisabled(true);
        }
    }

    public void onTransitionEnd() {
        if (this.mVerticalGridView != null) {
            this.mVerticalGridView.setLayoutFrozen(false);
            this.mVerticalGridView.setAnimateChildLayout(true);
            this.mVerticalGridView.setPruneChild(true);
            this.mVerticalGridView.setFocusSearchDisabled(false);
            this.mVerticalGridView.setScrollEnabled(true);
        }
    }

    public void setAlignment(int i) {
        if (this.mVerticalGridView != null) {
            this.mVerticalGridView.setItemAlignmentOffset(0);
            this.mVerticalGridView.setItemAlignmentOffsetPercent(-1.0f);
            this.mVerticalGridView.setWindowAlignmentOffset(i);
            this.mVerticalGridView.setWindowAlignmentOffsetPercent(-1.0f);
            this.mVerticalGridView.setWindowAlignment(0);
        }
    }
}
