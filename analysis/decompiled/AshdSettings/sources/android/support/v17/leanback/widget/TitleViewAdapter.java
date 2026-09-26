package android.support.v17.leanback.widget;

import android.graphics.drawable.Drawable;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class TitleViewAdapter {
    public static final int BRANDING_VIEW_VISIBLE = 2;
    public static final int FULL_VIEW_VISIBLE = 6;
    public static final int SEARCH_VIEW_VISIBLE = 4;

    public interface Provider {
        TitleViewAdapter getTitleViewAdapter();
    }

    public Drawable getBadgeDrawable() {
        return null;
    }

    public SearchOrbView.Colors getSearchAffordanceColors() {
        return null;
    }

    public abstract View getSearchAffordanceView();

    public CharSequence getTitle() {
        return null;
    }

    public void setAnimationEnabled(boolean z) {
    }

    public void setBadgeDrawable(Drawable drawable) {
    }

    public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
    }

    public void setTitle(CharSequence charSequence) {
    }

    public void updateComponentsVisibility(int i) {
    }

    public void setOnSearchClickedListener(View.OnClickListener onClickListener) {
        View searchAffordanceView = getSearchAffordanceView();
        if (searchAffordanceView != null) {
            searchAffordanceView.setOnClickListener(onClickListener);
        }
    }
}
