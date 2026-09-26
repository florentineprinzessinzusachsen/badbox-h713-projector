package android.support.v17.leanback.widget;

import android.database.Observable;

/* JADX INFO: loaded from: classes.dex */
public abstract class ObjectAdapter {
    public static final int NO_ID = -1;
    private boolean mHasStableIds;
    private final DataObservable mObservable = new DataObservable();
    private PresenterSelector mPresenterSelector;

    public abstract Object get(int i);

    public long getId(int i) {
        return -1L;
    }

    public boolean isImmediateNotifySupported() {
        return false;
    }

    protected void onHasStableIdsChanged() {
    }

    protected void onPresenterSelectorChanged() {
    }

    public abstract int size();

    public static abstract class DataObserver {
        public void onChanged() {
        }

        public void onItemRangeChanged(int i, int i2) {
            onChanged();
        }

        public void onItemRangeInserted(int i, int i2) {
            onChanged();
        }

        public void onItemRangeRemoved(int i, int i2) {
            onChanged();
        }
    }

    private static final class DataObservable extends Observable<DataObserver> {
        DataObservable() {
        }

        public void notifyChanged() {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((DataObserver) this.mObservers.get(size)).onChanged();
            }
        }

        public void notifyItemRangeChanged(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((DataObserver) this.mObservers.get(size)).onItemRangeChanged(i, i2);
            }
        }

        public void notifyItemRangeInserted(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((DataObserver) this.mObservers.get(size)).onItemRangeInserted(i, i2);
            }
        }

        public void notifyItemRangeRemoved(int i, int i2) {
            for (int size = this.mObservers.size() - 1; size >= 0; size--) {
                ((DataObserver) this.mObservers.get(size)).onItemRangeRemoved(i, i2);
            }
        }
    }

    public ObjectAdapter(PresenterSelector presenterSelector) {
        setPresenterSelector(presenterSelector);
    }

    public ObjectAdapter(Presenter presenter) {
        setPresenterSelector(new SinglePresenterSelector(presenter));
    }

    public ObjectAdapter() {
    }

    public final void setPresenterSelector(PresenterSelector presenterSelector) {
        if (presenterSelector == null) {
            throw new IllegalArgumentException("Presenter selector must not be null");
        }
        boolean z = false;
        boolean z2 = this.mPresenterSelector != null;
        if (z2 && this.mPresenterSelector != presenterSelector) {
            z = true;
        }
        this.mPresenterSelector = presenterSelector;
        if (z) {
            onPresenterSelectorChanged();
        }
        if (z2) {
            notifyChanged();
        }
    }

    public final PresenterSelector getPresenterSelector() {
        return this.mPresenterSelector;
    }

    public final void registerObserver(DataObserver dataObserver) {
        this.mObservable.registerObserver(dataObserver);
    }

    public final void unregisterObserver(DataObserver dataObserver) {
        this.mObservable.unregisterObserver(dataObserver);
    }

    public final void unregisterAllObservers() {
        this.mObservable.unregisterAll();
    }

    public final void notifyItemRangeChanged(int i, int i2) {
        this.mObservable.notifyItemRangeChanged(i, i2);
    }

    protected final void notifyItemRangeInserted(int i, int i2) {
        this.mObservable.notifyItemRangeInserted(i, i2);
    }

    protected final void notifyItemRangeRemoved(int i, int i2) {
        this.mObservable.notifyItemRangeRemoved(i, i2);
    }

    protected final void notifyChanged() {
        this.mObservable.notifyChanged();
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final void setHasStableIds(boolean z) {
        boolean z2 = this.mHasStableIds != z;
        this.mHasStableIds = z;
        if (z2) {
            onHasStableIdsChanged();
        }
    }

    public final Presenter getPresenter(Object obj) {
        if (this.mPresenterSelector == null) {
            throw new IllegalStateException("Presenter selector must not be null");
        }
        return this.mPresenterSelector.getPresenter(obj);
    }
}
