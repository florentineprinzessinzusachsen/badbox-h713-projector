package android.support.v17.leanback.app;

import android.content.Context;
import android.support.v17.leanback.widget.Util;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: loaded from: classes.dex */
class GuidedStepRootLayout extends LinearLayout {
    private boolean mFocusOutEnd;
    private boolean mFocusOutStart;

    public GuidedStepRootLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFocusOutStart = false;
        this.mFocusOutEnd = false;
    }

    public GuidedStepRootLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mFocusOutStart = false;
        this.mFocusOutEnd = false;
    }

    public void setFocusOutStart(boolean z) {
        this.mFocusOutStart = z;
    }

    public void setFocusOutEnd(boolean z) {
        this.mFocusOutEnd = z;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public View focusSearch(View view, int i) {
        View viewFocusSearch = super.focusSearch(view, i);
        if ((i != 17 && i != 66) || Util.isDescendant(this, viewFocusSearch)) {
            return viewFocusSearch;
        }
        if (getLayoutDirection() != 0 ? i == 66 : i == 17) {
            if (!this.mFocusOutStart) {
                return view;
            }
        } else if (!this.mFocusOutEnd) {
            return view;
        }
        return viewFocusSearch;
    }
}
