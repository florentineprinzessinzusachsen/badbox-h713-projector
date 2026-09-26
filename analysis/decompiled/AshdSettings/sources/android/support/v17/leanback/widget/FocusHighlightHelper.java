package android.support.v17.leanback.widget;

import android.animation.TimeAnimator;
import android.content.res.Resources;
import android.support.v17.leanback.R;
import android.support.v17.leanback.graphics.ColorOverlayDimmer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public class FocusHighlightHelper {
    static boolean isValidZoomIndex(int i) {
        return i == 0 || getResId(i) > 0;
    }

    static int getResId(int i) {
        switch (i) {
            case 1:
                return R.fraction.lb_focus_zoom_factor_small;
            case 2:
                return R.fraction.lb_focus_zoom_factor_medium;
            case 3:
                return R.fraction.lb_focus_zoom_factor_large;
            case 4:
                return R.fraction.lb_focus_zoom_factor_xsmall;
            default:
                return 0;
        }
    }

    static class FocusAnimator implements TimeAnimator.TimeListener {
        private final ColorOverlayDimmer mDimmer;
        private final int mDuration;
        private float mFocusLevelDelta;
        private float mFocusLevelStart;
        private final float mScaleDiff;
        private final View mView;
        private final ShadowOverlayContainer mWrapper;
        private float mFocusLevel = 0.0f;
        private final TimeAnimator mAnimator = new TimeAnimator();
        private final Interpolator mInterpolator = new AccelerateDecelerateInterpolator();

        void animateFocus(boolean z, boolean z2) {
            endAnimation();
            float f = z ? 1.0f : 0.0f;
            if (z2) {
                setFocusLevel(f);
            } else if (this.mFocusLevel != f) {
                this.mFocusLevelStart = this.mFocusLevel;
                this.mFocusLevelDelta = f - this.mFocusLevelStart;
                this.mAnimator.start();
            }
        }

        FocusAnimator(View view, float f, boolean z, int i) {
            this.mView = view;
            this.mDuration = i;
            this.mScaleDiff = f - 1.0f;
            if (view instanceof ShadowOverlayContainer) {
                this.mWrapper = (ShadowOverlayContainer) view;
            } else {
                this.mWrapper = null;
            }
            this.mAnimator.setTimeListener(this);
            if (z) {
                this.mDimmer = ColorOverlayDimmer.createDefault(view.getContext());
            } else {
                this.mDimmer = null;
            }
        }

        void setFocusLevel(float f) {
            this.mFocusLevel = f;
            float f2 = (this.mScaleDiff * f) + 1.0f;
            this.mView.setScaleX(f2);
            this.mView.setScaleY(f2);
            if (this.mWrapper != null) {
                this.mWrapper.setShadowFocusLevel(f);
            } else {
                ShadowOverlayHelper.setNoneWrapperShadowFocusLevel(this.mView, f);
            }
            if (this.mDimmer != null) {
                this.mDimmer.setActiveLevel(f);
                int color = this.mDimmer.getPaint().getColor();
                if (this.mWrapper != null) {
                    this.mWrapper.setOverlayColor(color);
                } else {
                    ShadowOverlayHelper.setNoneWrapperOverlayColor(this.mView, color);
                }
            }
        }

        float getFocusLevel() {
            return this.mFocusLevel;
        }

        void endAnimation() {
            this.mAnimator.end();
        }

        @Override // android.animation.TimeAnimator.TimeListener
        public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
            float interpolation;
            if (j >= this.mDuration) {
                interpolation = 1.0f;
                this.mAnimator.end();
            } else {
                interpolation = (float) (j / ((double) this.mDuration));
            }
            if (this.mInterpolator != null) {
                interpolation = this.mInterpolator.getInterpolation(interpolation);
            }
            setFocusLevel(this.mFocusLevelStart + (interpolation * this.mFocusLevelDelta));
        }
    }

    static class BrowseItemFocusHighlight implements FocusHighlightHandler {
        private static final int DURATION_MS = 150;
        private int mScaleIndex;
        private final boolean mUseDimmer;

        BrowseItemFocusHighlight(int i, boolean z) {
            if (!FocusHighlightHelper.isValidZoomIndex(i)) {
                throw new IllegalArgumentException("Unhandled zoom index");
            }
            this.mScaleIndex = i;
            this.mUseDimmer = z;
        }

        private float getScale(Resources resources) {
            if (this.mScaleIndex == 0) {
                return 1.0f;
            }
            return resources.getFraction(FocusHighlightHelper.getResId(this.mScaleIndex), 1, 1);
        }

        @Override // android.support.v17.leanback.widget.FocusHighlightHandler
        public void onItemFocused(View view, boolean z) {
            view.setSelected(z);
            getOrCreateAnimator(view).animateFocus(z, false);
        }

        @Override // android.support.v17.leanback.widget.FocusHighlightHandler
        public void onInitializeView(View view) {
            getOrCreateAnimator(view).animateFocus(false, true);
        }

        private FocusAnimator getOrCreateAnimator(View view) {
            FocusAnimator focusAnimator = (FocusAnimator) view.getTag(R.id.lb_focus_animator);
            if (focusAnimator != null) {
                return focusAnimator;
            }
            FocusAnimator focusAnimator2 = new FocusAnimator(view, getScale(view.getResources()), this.mUseDimmer, DURATION_MS);
            view.setTag(R.id.lb_focus_animator, focusAnimator2);
            return focusAnimator2;
        }
    }

    public static void setupBrowseItemFocusHighlight(ItemBridgeAdapter itemBridgeAdapter, int i, boolean z) {
        itemBridgeAdapter.setFocusHighlight(new BrowseItemFocusHighlight(i, z));
    }

    public static void setupHeaderItemFocusHighlight(VerticalGridView verticalGridView) {
        setupHeaderItemFocusHighlight(verticalGridView, true);
    }

    public static void setupHeaderItemFocusHighlight(VerticalGridView verticalGridView, boolean z) {
        if (verticalGridView == null || !(verticalGridView.getAdapter() instanceof ItemBridgeAdapter)) {
            return;
        }
        ((ItemBridgeAdapter) verticalGridView.getAdapter()).setFocusHighlight(new HeaderItemFocusHighlight(z));
    }

    static class HeaderItemFocusHighlight implements FocusHighlightHandler {
        private int mDuration;
        private boolean mInitialized;
        boolean mScaleEnabled;
        private float mSelectScale;

        @Override // android.support.v17.leanback.widget.FocusHighlightHandler
        public void onInitializeView(View view) {
        }

        HeaderItemFocusHighlight(boolean z) {
            this.mScaleEnabled = z;
        }

        void lazyInit(View view) {
            if (this.mInitialized) {
                return;
            }
            Resources resources = view.getResources();
            this.mSelectScale = this.mScaleEnabled ? Float.parseFloat(resources.getString(R.dimen.lb_browse_header_select_scale)) : 1.0f;
            this.mDuration = Integer.parseInt(resources.getString(R.dimen.lb_browse_header_select_duration));
            this.mInitialized = true;
        }

        class HeaderFocusAnimator extends FocusAnimator {
            ItemBridgeAdapter.ViewHolder mViewHolder;

            HeaderFocusAnimator(View view, float f, int i) {
                super(view, f, false, i);
                ViewParent parent = view.getParent();
                while (parent != null && !(parent instanceof RecyclerView)) {
                    parent = parent.getParent();
                }
                if (parent != null) {
                    this.mViewHolder = (ItemBridgeAdapter.ViewHolder) ((RecyclerView) parent).getChildViewHolder(view);
                }
            }

            @Override // android.support.v17.leanback.widget.FocusHighlightHelper.FocusAnimator
            void setFocusLevel(float f) {
                Presenter presenter = this.mViewHolder.getPresenter();
                if (presenter instanceof RowHeaderPresenter) {
                    ((RowHeaderPresenter) presenter).setSelectLevel((RowHeaderPresenter.ViewHolder) this.mViewHolder.getViewHolder(), f);
                }
                super.setFocusLevel(f);
            }
        }

        private void viewFocused(View view, boolean z) {
            lazyInit(view);
            view.setSelected(z);
            FocusAnimator headerFocusAnimator = (FocusAnimator) view.getTag(R.id.lb_focus_animator);
            if (headerFocusAnimator == null) {
                headerFocusAnimator = new HeaderFocusAnimator(view, this.mSelectScale, this.mDuration);
                view.setTag(R.id.lb_focus_animator, headerFocusAnimator);
            }
            headerFocusAnimator.animateFocus(z, false);
        }

        @Override // android.support.v17.leanback.widget.FocusHighlightHandler
        public void onItemFocused(View view, boolean z) {
            viewFocused(view, z);
        }
    }
}
