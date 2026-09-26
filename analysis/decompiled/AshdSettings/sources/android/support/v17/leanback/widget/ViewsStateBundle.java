package android.support.v17.leanback.widget;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.util.LruCache;
import android.util.SparseArray;
import android.view.View;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ViewsStateBundle {
    public static final int LIMIT_DEFAULT = 100;
    public static final int UNLIMITED = Integer.MAX_VALUE;
    private LruCache<String, SparseArray<Parcelable>> mChildStates;
    private int mSavePolicy = 0;
    private int mLimitNumber = 100;

    public void clear() {
        if (this.mChildStates != null) {
            this.mChildStates.evictAll();
        }
    }

    public void remove(int i) {
        if (this.mChildStates == null || this.mChildStates.size() == 0) {
            return;
        }
        this.mChildStates.remove(getSaveStatesKey(i));
    }

    public final Bundle saveAsBundle() {
        if (this.mChildStates == null || this.mChildStates.size() == 0) {
            return null;
        }
        Map<String, SparseArray<Parcelable>> mapSnapshot = this.mChildStates.snapshot();
        Bundle bundle = new Bundle();
        for (Map.Entry<String, SparseArray<Parcelable>> entry : mapSnapshot.entrySet()) {
            bundle.putSparseParcelableArray(entry.getKey(), entry.getValue());
        }
        return bundle;
    }

    public final void loadFromBundle(Bundle bundle) {
        if (this.mChildStates == null || bundle == null) {
            return;
        }
        this.mChildStates.evictAll();
        for (String str : bundle.keySet()) {
            this.mChildStates.put(str, bundle.getSparseParcelableArray(str));
        }
    }

    public final int getSavePolicy() {
        return this.mSavePolicy;
    }

    public final int getLimitNumber() {
        return this.mLimitNumber;
    }

    public final void setSavePolicy(int i) {
        this.mSavePolicy = i;
        applyPolicyChanges();
    }

    public final void setLimitNumber(int i) {
        this.mLimitNumber = i;
        applyPolicyChanges();
    }

    protected void applyPolicyChanges() {
        if (this.mSavePolicy == 2) {
            if (this.mLimitNumber <= 0) {
                throw new IllegalArgumentException();
            }
            if (this.mChildStates == null || this.mChildStates.maxSize() != this.mLimitNumber) {
                this.mChildStates = new LruCache<>(this.mLimitNumber);
                return;
            }
            return;
        }
        if (this.mSavePolicy == 3 || this.mSavePolicy == 1) {
            if (this.mChildStates == null || this.mChildStates.maxSize() != Integer.MAX_VALUE) {
                this.mChildStates = new LruCache<>(Integer.MAX_VALUE);
                return;
            }
            return;
        }
        this.mChildStates = null;
    }

    public final void loadView(View view, int i) {
        if (this.mChildStates != null) {
            SparseArray<Parcelable> sparseArrayRemove = this.mChildStates.remove(getSaveStatesKey(i));
            if (sparseArrayRemove != null) {
                view.restoreHierarchyState(sparseArrayRemove);
            }
        }
    }

    protected final void saveViewUnchecked(View view, int i) {
        if (this.mChildStates != null) {
            String saveStatesKey = getSaveStatesKey(i);
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            view.saveHierarchyState(sparseArray);
            this.mChildStates.put(saveStatesKey, sparseArray);
        }
    }

    public final Bundle saveOnScreenView(Bundle bundle, View view, int i) {
        if (this.mSavePolicy != 0) {
            String saveStatesKey = getSaveStatesKey(i);
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            view.saveHierarchyState(sparseArray);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray(saveStatesKey, sparseArray);
        }
        return bundle;
    }

    public final void saveOffscreenView(View view, int i) {
        switch (this.mSavePolicy) {
            case 1:
                remove(i);
                break;
            case 2:
            case 3:
                saveViewUnchecked(view, i);
                break;
        }
    }

    static String getSaveStatesKey(int i) {
        return Integer.toString(i);
    }
}
