package android.support.v17.leanback.widget;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ArrayObjectAdapter extends ObjectAdapter {
    private ArrayList<Object> mItems;

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public boolean isImmediateNotifySupported() {
        return true;
    }

    public ArrayObjectAdapter(PresenterSelector presenterSelector) {
        super(presenterSelector);
        this.mItems = new ArrayList<>();
    }

    public ArrayObjectAdapter(Presenter presenter) {
        super(presenter);
        this.mItems = new ArrayList<>();
    }

    public ArrayObjectAdapter() {
        this.mItems = new ArrayList<>();
    }

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public int size() {
        return this.mItems.size();
    }

    @Override // android.support.v17.leanback.widget.ObjectAdapter
    public Object get(int i) {
        return this.mItems.get(i);
    }

    public int indexOf(Object obj) {
        return this.mItems.indexOf(obj);
    }

    public void notifyArrayItemRangeChanged(int i, int i2) {
        notifyItemRangeChanged(i, i2);
    }

    public void add(Object obj) {
        add(this.mItems.size(), obj);
    }

    public void add(int i, Object obj) {
        this.mItems.add(i, obj);
        notifyItemRangeInserted(i, 1);
    }

    public void addAll(int i, Collection collection) {
        int size = collection.size();
        if (size == 0) {
            return;
        }
        this.mItems.addAll(i, collection);
        notifyItemRangeInserted(i, size);
    }

    public boolean remove(Object obj) {
        int iIndexOf = this.mItems.indexOf(obj);
        if (iIndexOf >= 0) {
            this.mItems.remove(iIndexOf);
            notifyItemRangeRemoved(iIndexOf, 1);
        }
        return iIndexOf >= 0;
    }

    public void replace(int i, Object obj) {
        this.mItems.set(i, obj);
        notifyItemRangeChanged(i, 1);
    }

    public int removeItems(int i, int i2) {
        int iMin = Math.min(i2, this.mItems.size() - i);
        if (iMin <= 0) {
            return 0;
        }
        for (int i3 = 0; i3 < iMin; i3++) {
            this.mItems.remove(i);
        }
        notifyItemRangeRemoved(i, iMin);
        return iMin;
    }

    public void clear() {
        int size = this.mItems.size();
        if (size == 0) {
            return;
        }
        this.mItems.clear();
        notifyItemRangeRemoved(0, size);
    }

    public <E> List<E> unmodifiableList() {
        return Collections.unmodifiableList(this.mItems);
    }
}
