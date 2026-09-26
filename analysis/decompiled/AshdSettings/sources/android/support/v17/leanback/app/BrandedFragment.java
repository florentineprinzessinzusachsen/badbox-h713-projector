package android.support.v17.leanback.app;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v17.leanback.R;
import android.support.v17.leanback.widget.SearchOrbView;
import android.support.v17.leanback.widget.TitleHelper;
import android.support.v17.leanback.widget.TitleViewAdapter;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class BrandedFragment extends Fragment {
    private static final String TITLE_SHOW = "titleShow";
    private Drawable mBadgeDrawable;
    private View.OnClickListener mExternalOnSearchClickedListener;
    private boolean mSearchAffordanceColorSet;
    private SearchOrbView.Colors mSearchAffordanceColors;
    private boolean mShowingTitle = true;
    private CharSequence mTitle;
    private TitleHelper mTitleHelper;
    private View mTitleView;
    private TitleViewAdapter mTitleViewAdapter;

    public View onInflateTitleView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        TypedValue typedValue = new TypedValue();
        return layoutInflater.inflate(viewGroup.getContext().getTheme().resolveAttribute(R.attr.browseTitleViewLayout, typedValue, true) ? typedValue.resourceId : R.layout.lb_browse_title, viewGroup, false);
    }

    public void installTitleView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View viewOnInflateTitleView = onInflateTitleView(layoutInflater, viewGroup, bundle);
        if (viewOnInflateTitleView != null) {
            viewGroup.addView(viewOnInflateTitleView);
            setTitleView(viewOnInflateTitleView.findViewById(R.id.browse_title_group));
        } else {
            setTitleView(null);
        }
    }

    public void setTitleView(View view) {
        this.mTitleView = view;
        if (this.mTitleView == null) {
            this.mTitleViewAdapter = null;
            this.mTitleHelper = null;
            return;
        }
        this.mTitleViewAdapter = ((TitleViewAdapter.Provider) this.mTitleView).getTitleViewAdapter();
        this.mTitleViewAdapter.setTitle(this.mTitle);
        this.mTitleViewAdapter.setBadgeDrawable(this.mBadgeDrawable);
        if (this.mSearchAffordanceColorSet) {
            this.mTitleViewAdapter.setSearchAffordanceColors(this.mSearchAffordanceColors);
        }
        if (this.mExternalOnSearchClickedListener != null) {
            setOnSearchClickedListener(this.mExternalOnSearchClickedListener);
        }
        if (getView() instanceof ViewGroup) {
            this.mTitleHelper = new TitleHelper((ViewGroup) getView(), this.mTitleView);
        }
    }

    public View getTitleView() {
        return this.mTitleView;
    }

    public TitleViewAdapter getTitleViewAdapter() {
        return this.mTitleViewAdapter;
    }

    TitleHelper getTitleHelper() {
        return this.mTitleHelper;
    }

    @Override // android.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(TITLE_SHOW, this.mShowingTitle);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle != null) {
            this.mShowingTitle = bundle.getBoolean(TITLE_SHOW);
        }
        if (this.mTitleView == null || !(view instanceof ViewGroup)) {
            return;
        }
        this.mTitleHelper = new TitleHelper((ViewGroup) view, this.mTitleView);
    }

    @Override // android.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mTitleHelper = null;
    }

    public void showTitle(boolean z) {
        if (z == this.mShowingTitle) {
            return;
        }
        this.mShowingTitle = z;
        if (this.mTitleHelper != null) {
            this.mTitleHelper.showTitle(z);
        }
    }

    public void showTitle(int i) {
        if (this.mTitleViewAdapter != null) {
            this.mTitleViewAdapter.updateComponentsVisibility(i);
        }
        showTitle(true);
    }

    public void setBadgeDrawable(Drawable drawable) {
        if (this.mBadgeDrawable != drawable) {
            this.mBadgeDrawable = drawable;
            if (this.mTitleViewAdapter != null) {
                this.mTitleViewAdapter.setBadgeDrawable(drawable);
            }
        }
    }

    public Drawable getBadgeDrawable() {
        return this.mBadgeDrawable;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleViewAdapter != null) {
            this.mTitleViewAdapter.setTitle(charSequence);
        }
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public void setOnSearchClickedListener(View.OnClickListener onClickListener) {
        this.mExternalOnSearchClickedListener = onClickListener;
        if (this.mTitleViewAdapter != null) {
            this.mTitleViewAdapter.setOnSearchClickedListener(onClickListener);
        }
    }

    public void setSearchAffordanceColors(SearchOrbView.Colors colors) {
        this.mSearchAffordanceColors = colors;
        this.mSearchAffordanceColorSet = true;
        if (this.mTitleViewAdapter != null) {
            this.mTitleViewAdapter.setSearchAffordanceColors(this.mSearchAffordanceColors);
        }
    }

    public SearchOrbView.Colors getSearchAffordanceColors() {
        if (this.mSearchAffordanceColorSet) {
            return this.mSearchAffordanceColors;
        }
        if (this.mTitleViewAdapter == null) {
            throw new IllegalStateException("Fragment views not yet created");
        }
        return this.mTitleViewAdapter.getSearchAffordanceColors();
    }

    public void setSearchAffordanceColor(int i) {
        setSearchAffordanceColors(new SearchOrbView.Colors(i));
    }

    public int getSearchAffordanceColor() {
        return getSearchAffordanceColors().color;
    }

    @Override // android.app.Fragment
    public void onStart() {
        super.onStart();
        if (this.mTitleViewAdapter != null) {
            showTitle(this.mShowingTitle);
            this.mTitleViewAdapter.setAnimationEnabled(true);
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        if (this.mTitleViewAdapter != null) {
            this.mTitleViewAdapter.setAnimationEnabled(false);
        }
        super.onPause();
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mTitleViewAdapter != null) {
            this.mTitleViewAdapter.setAnimationEnabled(true);
        }
    }

    public final boolean isShowingTitle() {
        return this.mShowingTitle;
    }
}
