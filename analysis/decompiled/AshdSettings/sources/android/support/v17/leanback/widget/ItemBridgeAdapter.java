package android.support.v17.leanback.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class ItemBridgeAdapter extends RecyclerView.Adapter implements FacetProviderAdapter {
    static final boolean DEBUG = false;
    static final String TAG = "ItemBridgeAdapter";
    private ObjectAdapter mAdapter;
    private AdapterListener mAdapterListener;
    private ObjectAdapter.DataObserver mDataObserver;
    FocusHighlightHandler mFocusHighlight;
    private PresenterSelector mPresenterSelector;
    private ArrayList<Presenter> mPresenters;
    Wrapper mWrapper;

    public static class AdapterListener {
        public void onAddPresenter(Presenter presenter, int i) {
        }

        public void onAttachedToWindow(ViewHolder viewHolder) {
        }

        public void onBind(ViewHolder viewHolder) {
        }

        public void onCreate(ViewHolder viewHolder) {
        }

        public void onDetachedFromWindow(ViewHolder viewHolder) {
        }

        public void onUnbind(ViewHolder viewHolder) {
        }
    }

    public static abstract class Wrapper {
        public abstract View createWrapper(View view);

        public abstract void wrap(View view, View view2);
    }

    protected void onAddPresenter(Presenter presenter, int i) {
    }

    protected void onAttachedToWindow(ViewHolder viewHolder) {
    }

    protected void onBind(ViewHolder viewHolder) {
    }

    protected void onCreate(ViewHolder viewHolder) {
    }

    protected void onDetachedFromWindow(ViewHolder viewHolder) {
    }

    protected void onUnbind(ViewHolder viewHolder) {
    }

    final class OnFocusChangeListener implements View.OnFocusChangeListener {
        View.OnFocusChangeListener mChainedListener;

        OnFocusChangeListener() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (ItemBridgeAdapter.this.mWrapper != null) {
                view = (View) view.getParent();
            }
            if (ItemBridgeAdapter.this.mFocusHighlight != null) {
                ItemBridgeAdapter.this.mFocusHighlight.onItemFocused(view, z);
            }
            if (this.mChainedListener != null) {
                this.mChainedListener.onFocusChange(view, z);
            }
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements FacetProvider {
        Object mExtraObject;
        final OnFocusChangeListener mFocusChangeListener;
        final Presenter.ViewHolder mHolder;
        Object mItem;
        final Presenter mPresenter;

        public final Presenter getPresenter() {
            return this.mPresenter;
        }

        public final Presenter.ViewHolder getViewHolder() {
            return this.mHolder;
        }

        public final Object getItem() {
            return this.mItem;
        }

        public final Object getExtraObject() {
            return this.mExtraObject;
        }

        public void setExtraObject(Object obj) {
            this.mExtraObject = obj;
        }

        @Override // android.support.v17.leanback.widget.FacetProvider
        public Object getFacet(Class<?> cls) {
            return this.mHolder.getFacet(cls);
        }

        ViewHolder(Presenter presenter, View view, Presenter.ViewHolder viewHolder) {
            super(view);
            this.mFocusChangeListener = ItemBridgeAdapter.this.new OnFocusChangeListener();
            this.mPresenter = presenter;
            this.mHolder = viewHolder;
        }
    }

    public ItemBridgeAdapter(ObjectAdapter objectAdapter, PresenterSelector presenterSelector) {
        this.mPresenters = new ArrayList<>();
        this.mDataObserver = new ObjectAdapter.DataObserver() { // from class: android.support.v17.leanback.widget.ItemBridgeAdapter.1
            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onChanged() {
                ItemBridgeAdapter.this.notifyDataSetChanged();
            }

            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeChanged(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(i, i2);
            }

            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeInserted(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeInserted(i, i2);
            }

            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeRemoved(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeRemoved(i, i2);
            }
        };
        setAdapter(objectAdapter);
        this.mPresenterSelector = presenterSelector;
    }

    public ItemBridgeAdapter(ObjectAdapter objectAdapter) {
        this(objectAdapter, null);
    }

    public ItemBridgeAdapter() {
        this.mPresenters = new ArrayList<>();
        this.mDataObserver = new ObjectAdapter.DataObserver() { // from class: android.support.v17.leanback.widget.ItemBridgeAdapter.1
            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onChanged() {
                ItemBridgeAdapter.this.notifyDataSetChanged();
            }

            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeChanged(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeChanged(i, i2);
            }

            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeInserted(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeInserted(i, i2);
            }

            @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
            public void onItemRangeRemoved(int i, int i2) {
                ItemBridgeAdapter.this.notifyItemRangeRemoved(i, i2);
            }
        };
    }

    public void setAdapter(ObjectAdapter objectAdapter) {
        if (objectAdapter == this.mAdapter) {
            return;
        }
        if (this.mAdapter != null) {
            this.mAdapter.unregisterObserver(this.mDataObserver);
        }
        this.mAdapter = objectAdapter;
        if (this.mAdapter == null) {
            notifyDataSetChanged();
            return;
        }
        this.mAdapter.registerObserver(this.mDataObserver);
        if (hasStableIds() != this.mAdapter.hasStableIds()) {
            setHasStableIds(this.mAdapter.hasStableIds());
        }
        notifyDataSetChanged();
    }

    public void setPresenter(PresenterSelector presenterSelector) {
        this.mPresenterSelector = presenterSelector;
        notifyDataSetChanged();
    }

    public void setWrapper(Wrapper wrapper) {
        this.mWrapper = wrapper;
    }

    public Wrapper getWrapper() {
        return this.mWrapper;
    }

    void setFocusHighlight(FocusHighlightHandler focusHighlightHandler) {
        this.mFocusHighlight = focusHighlightHandler;
    }

    public void clear() {
        setAdapter(null);
    }

    public void setPresenterMapper(ArrayList<Presenter> arrayList) {
        this.mPresenters = arrayList;
    }

    public ArrayList<Presenter> getPresenterMapper() {
        return this.mPresenters;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mAdapter != null) {
            return this.mAdapter.size();
        }
        return 0;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        Presenter presenter = (this.mPresenterSelector != null ? this.mPresenterSelector : this.mAdapter.getPresenterSelector()).getPresenter(this.mAdapter.get(i));
        int iIndexOf = this.mPresenters.indexOf(presenter);
        if (iIndexOf < 0) {
            this.mPresenters.add(presenter);
            iIndexOf = this.mPresenters.indexOf(presenter);
            onAddPresenter(presenter, iIndexOf);
            if (this.mAdapterListener != null) {
                this.mAdapterListener.onAddPresenter(presenter, iIndexOf);
            }
        }
        return iIndexOf;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Presenter.ViewHolder viewHolderOnCreateViewHolder;
        View viewCreateWrapper;
        Presenter presenter = this.mPresenters.get(i);
        if (this.mWrapper != null) {
            viewCreateWrapper = this.mWrapper.createWrapper(viewGroup);
            viewHolderOnCreateViewHolder = presenter.onCreateViewHolder(viewGroup);
            this.mWrapper.wrap(viewCreateWrapper, viewHolderOnCreateViewHolder.view);
        } else {
            viewHolderOnCreateViewHolder = presenter.onCreateViewHolder(viewGroup);
            viewCreateWrapper = viewHolderOnCreateViewHolder.view;
        }
        ViewHolder viewHolder = new ViewHolder(presenter, viewCreateWrapper, viewHolderOnCreateViewHolder);
        onCreate(viewHolder);
        if (this.mAdapterListener != null) {
            this.mAdapterListener.onCreate(viewHolder);
        }
        View view = viewHolder.mHolder.view;
        if (view != null) {
            viewHolder.mFocusChangeListener.mChainedListener = view.getOnFocusChangeListener();
            view.setOnFocusChangeListener(viewHolder.mFocusChangeListener);
        }
        if (this.mFocusHighlight != null) {
            this.mFocusHighlight.onInitializeView(viewCreateWrapper);
        }
        return viewHolder;
    }

    public void setAdapterListener(AdapterListener adapterListener) {
        this.mAdapterListener = adapterListener;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mItem = this.mAdapter.get(i);
        viewHolder2.mPresenter.onBindViewHolder(viewHolder2.mHolder, viewHolder2.mItem);
        onBind(viewHolder2);
        if (this.mAdapterListener != null) {
            this.mAdapterListener.onBind(viewHolder2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mPresenter.onUnbindViewHolder(viewHolder2.mHolder);
        onUnbind(viewHolder2);
        if (this.mAdapterListener != null) {
            this.mAdapterListener.onUnbind(viewHolder2);
        }
        viewHolder2.mItem = null;
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        onAttachedToWindow(viewHolder2);
        if (this.mAdapterListener != null) {
            this.mAdapterListener.onAttachedToWindow(viewHolder2);
        }
        viewHolder2.mPresenter.onViewAttachedToWindow(viewHolder2.mHolder);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        viewHolder2.mPresenter.onViewDetachedFromWindow(viewHolder2.mHolder);
        onDetachedFromWindow(viewHolder2);
        if (this.mAdapterListener != null) {
            this.mAdapterListener.onDetachedFromWindow(viewHolder2);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.mAdapter.getId(i);
    }

    @Override // android.support.v17.leanback.widget.FacetProviderAdapter
    public FacetProvider getFacetProvider(int i) {
        return this.mPresenters.get(i);
    }
}
