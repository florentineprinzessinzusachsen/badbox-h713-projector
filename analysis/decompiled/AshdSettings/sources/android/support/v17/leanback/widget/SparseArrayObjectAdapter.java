package android.support.v17.leanback.widget;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes.dex */
public class SparseArrayObjectAdapter extends ObjectAdapter {
    private SparseArray<Object> mItems;

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public boolean isImmediateNotifySupported() {
        return true;
    }

    public SparseArrayObjectAdapter(PresenterSelector presenterSelector) {
        super(presenterSelector);
        this.mItems = new SparseArray<>();
    }

    public SparseArrayObjectAdapter(Presenter presenter) {
        super(presenter);
        this.mItems = new SparseArray<>();
    }

    public SparseArrayObjectAdapter() {
        this.mItems = new SparseArray<>();
    }

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public int size() {
        return this.mItems.size();
    }

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public Object get(int i) {
        return this.mItems.valueAt(i);
    }

    public int indexOf(Object obj) {
        return this.mItems.indexOfValue(obj);
    }

    public int indexOf(int i) {
        return this.mItems.indexOfKey(i);
    }

    public void notifyArrayItemRangeChanged(int i, int i2) {
        notifyItemRangeChanged(i, i2);
    }

    public void set(int i, Object obj) {
        int iIndexOfKey = this.mItems.indexOfKey(i);
        if (iIndexOfKey >= 0) {
            if (this.mItems.valueAt(iIndexOfKey) != obj) {
                this.mItems.setValueAt(iIndexOfKey, obj);
                notifyItemRangeChanged(iIndexOfKey, 1);
                return;
            }
            return;
        }
        this.mItems.append(i, obj);
        notifyItemRangeInserted(this.mItems.indexOfKey(i), 1);
    }

    public void clear(int i) {
        int iIndexOfKey = this.mItems.indexOfKey(i);
        if (iIndexOfKey >= 0) {
            this.mItems.removeAt(iIndexOfKey);
            notifyItemRangeRemoved(iIndexOfKey, 1);
        }
    }

    public void clear() {
        int size = this.mItems.size();
        if (size == 0) {
            return;
        }
        this.mItems.clear();
        notifyItemRangeRemoved(0, size);
    }

    public Object lookup(int i) {
        return this.mItems.get(i);
    }
}
