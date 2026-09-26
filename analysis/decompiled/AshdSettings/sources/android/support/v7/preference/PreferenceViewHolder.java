package android.support.v7.preference;

import android.support.annotation.IdRes;
import android.support.annotation.RestrictTo;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> mCachedViews;
    private boolean mDividerAllowedAbove;
    private boolean mDividerAllowedBelow;

    @RestrictTo({RestrictTo.Scope.TESTS})
    public PreferenceViewHolder(View view) {
        super(view);
        this.mCachedViews = new SparseArray<>(4);
        this.mCachedViews.put(android.R.id.title, view.findViewById(android.R.id.title));
        this.mCachedViews.put(android.R.id.summary, view.findViewById(android.R.id.summary));
        this.mCachedViews.put(android.R.id.icon, view.findViewById(android.R.id.icon));
        this.mCachedViews.put(R.id.icon_frame, view.findViewById(R.id.icon_frame));
        this.mCachedViews.put(16908350, view.findViewById(16908350));
    }

    public View findViewById(@IdRes int i) {
        View view = this.mCachedViews.get(i);
        if (view != null) {
            return view;
        }
        View viewFindViewById = this.itemView.findViewById(i);
        if (viewFindViewById != null) {
            this.mCachedViews.put(i, viewFindViewById);
        }
        return viewFindViewById;
    }

    public boolean isDividerAllowedAbove() {
        return this.mDividerAllowedAbove;
    }

    public void setDividerAllowedAbove(boolean z) {
        this.mDividerAllowedAbove = z;
    }

    public boolean isDividerAllowedBelow() {
        return this.mDividerAllowedBelow;
    }

    public void setDividerAllowedBelow(boolean z) {
        this.mDividerAllowedBelow = z;
    }
}
