package android.support.v17.leanback.widget;

import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class Presenter implements FacetProvider {
    private Map<Class, Object> mFacets;

    public static abstract class ViewHolderTask {
        public void run(ViewHolder viewHolder) {
        }
    }

    public abstract void onBindViewHolder(ViewHolder viewHolder, Object obj);

    public abstract ViewHolder onCreateViewHolder(ViewGroup viewGroup);

    public abstract void onUnbindViewHolder(ViewHolder viewHolder);

    public void onViewAttachedToWindow(ViewHolder viewHolder) {
    }

    public static class ViewHolder implements FacetProvider {
        private Map<Class, Object> mFacets;
        public final View view;

        public ViewHolder(View view) {
            this.view = view;
        }

        @Override // android.support.v17.leanback.widget.FacetProvider
        public final Object getFacet(Class<?> cls) {
            if (this.mFacets == null) {
                return null;
            }
            return this.mFacets.get(cls);
        }

        public final void setFacet(Class<?> cls, Object obj) {
            if (this.mFacets == null) {
                this.mFacets = new HashMap();
            }
            this.mFacets.put(cls, obj);
        }
    }

    public void onViewDetachedFromWindow(ViewHolder viewHolder) {
        cancelAnimationsRecursive(viewHolder.view);
    }

    protected static void cancelAnimationsRecursive(View view) {
        if (view == null || !view.hasTransientState()) {
            return;
        }
        view.animate().cancel();
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; view.hasTransientState() && i < childCount; i++) {
                cancelAnimationsRecursive(viewGroup.getChildAt(i));
            }
        }
    }

    public void setOnClickListener(ViewHolder viewHolder, View.OnClickListener onClickListener) {
        viewHolder.view.setOnClickListener(onClickListener);
    }

    @Override // android.support.v17.leanback.widget.FacetProvider
    public final Object getFacet(Class<?> cls) {
        if (this.mFacets == null) {
            return null;
        }
        return this.mFacets.get(cls);
    }

    public final void setFacet(Class<?> cls, Object obj) {
        if (this.mFacets == null) {
            this.mFacets = new HashMap();
        }
        this.mFacets.put(cls, obj);
    }
}
