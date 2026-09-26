package android.support.v17.leanback.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v17.leanback.R;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class DetailsOverviewRowPresenter extends RowPresenter {
    static final boolean DEBUG = false;
    private static final long DEFAULT_TIMEOUT = 5000;
    private static final int MORE_ACTIONS_FADE_MS = 100;
    static final String TAG = "DetailsOverviewRowPresenter";
    OnActionClickedListener mActionClickedListener;
    private boolean mBackgroundColorSet;
    final Presenter mDetailsPresenter;
    private DetailsOverviewSharedElementHelper mSharedElementHelper;
    private int mBackgroundColor = 0;
    private boolean mIsStyleLarge = true;

    @Override // android.support.v17.leanback.widget.RowPresenter
    public final boolean isUsingDefaultSelectEffect() {
        return false;
    }

    class ActionsItemBridgeAdapter extends ItemBridgeAdapter {
        ViewHolder mViewHolder;

        ActionsItemBridgeAdapter(ViewHolder viewHolder) {
            this.mViewHolder = viewHolder;
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter
        public void onBind(final ItemBridgeAdapter.ViewHolder viewHolder) {
            if (this.mViewHolder.getOnItemViewClickedListener() == null && DetailsOverviewRowPresenter.this.mActionClickedListener == null) {
                return;
            }
            viewHolder.getPresenter().setOnClickListener(viewHolder.getViewHolder(), new View.OnClickListener() { // from class: android.support.v17.leanback.widget.DetailsOverviewRowPresenter.ActionsItemBridgeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (ActionsItemBridgeAdapter.this.mViewHolder.getOnItemViewClickedListener() != null) {
                        ActionsItemBridgeAdapter.this.mViewHolder.getOnItemViewClickedListener().onItemClicked(viewHolder.getViewHolder(), viewHolder.getItem(), ActionsItemBridgeAdapter.this.mViewHolder, ActionsItemBridgeAdapter.this.mViewHolder.getRow());
                    }
                    if (DetailsOverviewRowPresenter.this.mActionClickedListener != null) {
                        DetailsOverviewRowPresenter.this.mActionClickedListener.onActionClicked((Action) viewHolder.getItem());
                    }
                }
            });
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter
        public void onUnbind(ItemBridgeAdapter.ViewHolder viewHolder) {
            if (this.mViewHolder.getOnItemViewClickedListener() == null && DetailsOverviewRowPresenter.this.mActionClickedListener == null) {
                return;
            }
            viewHolder.getPresenter().setOnClickListener(viewHolder.getViewHolder(), null);
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter
        public void onAttachedToWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            viewHolder.itemView.removeOnLayoutChangeListener(this.mViewHolder.mLayoutChangeListener);
            viewHolder.itemView.addOnLayoutChangeListener(this.mViewHolder.mLayoutChangeListener);
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter
        public void onDetachedFromWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            viewHolder.itemView.removeOnLayoutChangeListener(this.mViewHolder.mLayoutChangeListener);
            this.mViewHolder.checkFirstAndLastPosition(false);
        }
    }

    public final class ViewHolder extends RowPresenter.ViewHolder {
        ItemBridgeAdapter mActionBridgeAdapter;
        final HorizontalGridView mActionsRow;
        final OnChildSelectedListener mChildSelectedListener;
        final FrameLayout mDetailsDescriptionFrame;
        public final Presenter.ViewHolder mDetailsDescriptionViewHolder;
        final Handler mHandler;
        final ImageView mImageView;
        final View.OnLayoutChangeListener mLayoutChangeListener;
        final DetailsOverviewRow.Listener mListener;
        int mNumItems;
        final FrameLayout mOverviewFrame;
        final ViewGroup mOverviewView;
        final ViewGroup mRightPanel;
        final RecyclerView.OnScrollListener mScrollListener;
        boolean mShowMoreLeft;
        boolean mShowMoreRight;
        final Runnable mUpdateDrawableCallback;

        void bindActions(ObjectAdapter objectAdapter) {
            this.mActionBridgeAdapter.setAdapter(objectAdapter);
            this.mActionsRow.setAdapter(this.mActionBridgeAdapter);
            this.mNumItems = this.mActionBridgeAdapter.getItemCount();
            this.mShowMoreRight = false;
            this.mShowMoreLeft = true;
            showMoreLeft(false);
        }

        void dispatchItemSelection(View view) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForPosition;
            if (isSelected()) {
                if (view != null) {
                    viewHolderFindViewHolderForPosition = this.mActionsRow.getChildViewHolder(view);
                } else {
                    viewHolderFindViewHolderForPosition = this.mActionsRow.findViewHolderForPosition(this.mActionsRow.getSelectedPosition());
                }
                ItemBridgeAdapter.ViewHolder viewHolder = (ItemBridgeAdapter.ViewHolder) viewHolderFindViewHolderForPosition;
                if (viewHolder == null) {
                    if (getOnItemViewSelectedListener() != null) {
                        getOnItemViewSelectedListener().onItemSelected(null, null, this, getRow());
                    }
                } else if (getOnItemViewSelectedListener() != null) {
                    getOnItemViewSelectedListener().onItemSelected(viewHolder.getViewHolder(), viewHolder.getItem(), this, getRow());
                }
            }
        }

        private int getViewCenter(View view) {
            return (view.getRight() - view.getLeft()) / 2;
        }

        void checkFirstAndLastPosition(boolean z) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForPosition = this.mActionsRow.findViewHolderForPosition(this.mNumItems - 1);
            boolean z2 = viewHolderFindViewHolderForPosition == null || viewHolderFindViewHolderForPosition.itemView.getRight() > this.mActionsRow.getWidth();
            RecyclerView.ViewHolder viewHolderFindViewHolderForPosition2 = this.mActionsRow.findViewHolderForPosition(0);
            boolean z3 = viewHolderFindViewHolderForPosition2 == null || viewHolderFindViewHolderForPosition2.itemView.getLeft() < 0;
            showMoreRight(z2);
            showMoreLeft(z3);
        }

        private void showMoreLeft(boolean z) {
            if (z != this.mShowMoreLeft) {
                this.mActionsRow.setFadingLeftEdge(z);
                this.mShowMoreLeft = z;
            }
        }

        private void showMoreRight(boolean z) {
            if (z != this.mShowMoreRight) {
                this.mActionsRow.setFadingRightEdge(z);
                this.mShowMoreRight = z;
            }
        }

        public ViewHolder(View view, Presenter presenter) {
            super(view);
            this.mHandler = new Handler();
            this.mUpdateDrawableCallback = new Runnable() { // from class: android.support.v17.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.1
                @Override // java.lang.Runnable
                public void run() {
                    DetailsOverviewRowPresenter.this.bindImageDrawable(ViewHolder.this);
                }
            };
            this.mListener = new DetailsOverviewRow.Listener() { // from class: android.support.v17.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.2
                @Override // android.support.v17.leanback.widget.DetailsOverviewRow.Listener
                public void onImageDrawableChanged(DetailsOverviewRow detailsOverviewRow) {
                    ViewHolder.this.mHandler.removeCallbacks(ViewHolder.this.mUpdateDrawableCallback);
                    ViewHolder.this.mHandler.post(ViewHolder.this.mUpdateDrawableCallback);
                }

                @Override // android.support.v17.leanback.widget.DetailsOverviewRow.Listener
                public void onItemChanged(DetailsOverviewRow detailsOverviewRow) {
                    if (ViewHolder.this.mDetailsDescriptionViewHolder != null) {
                        DetailsOverviewRowPresenter.this.mDetailsPresenter.onUnbindViewHolder(ViewHolder.this.mDetailsDescriptionViewHolder);
                    }
                    DetailsOverviewRowPresenter.this.mDetailsPresenter.onBindViewHolder(ViewHolder.this.mDetailsDescriptionViewHolder, detailsOverviewRow.getItem());
                }

                @Override // android.support.v17.leanback.widget.DetailsOverviewRow.Listener
                public void onActionsAdapterChanged(DetailsOverviewRow detailsOverviewRow) {
                    ViewHolder.this.bindActions(detailsOverviewRow.getActionsAdapter());
                }
            };
            this.mLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: android.support.v17.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.3
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    ViewHolder.this.checkFirstAndLastPosition(false);
                }
            };
            this.mChildSelectedListener = new OnChildSelectedListener() { // from class: android.support.v17.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.4
                @Override // android.support.v17.leanback.widget.OnChildSelectedListener
                public void onChildSelected(ViewGroup viewGroup, View view2, int i, long j) {
                    ViewHolder.this.dispatchItemSelection(view2);
                }
            };
            this.mScrollListener = new RecyclerView.OnScrollListener() { // from class: android.support.v17.leanback.widget.DetailsOverviewRowPresenter.ViewHolder.5
                @Override // android.support.v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                }

                @Override // android.support.v7.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    ViewHolder.this.checkFirstAndLastPosition(true);
                }
            };
            this.mOverviewFrame = (FrameLayout) view.findViewById(R.id.details_frame);
            this.mOverviewView = (ViewGroup) view.findViewById(R.id.details_overview);
            this.mImageView = (ImageView) view.findViewById(R.id.details_overview_image);
            this.mRightPanel = (ViewGroup) view.findViewById(R.id.details_overview_right_panel);
            this.mDetailsDescriptionFrame = (FrameLayout) this.mRightPanel.findViewById(R.id.details_overview_description);
            this.mActionsRow = (HorizontalGridView) this.mRightPanel.findViewById(R.id.details_overview_actions);
            this.mActionsRow.setHasOverlappingRendering(false);
            this.mActionsRow.setOnScrollListener(this.mScrollListener);
            this.mActionsRow.setAdapter(this.mActionBridgeAdapter);
            this.mActionsRow.setOnChildSelectedListener(this.mChildSelectedListener);
            int dimensionPixelSize = view.getResources().getDimensionPixelSize(R.dimen.lb_details_overview_actions_fade_size);
            this.mActionsRow.setFadingRightEdgeLength(dimensionPixelSize);
            this.mActionsRow.setFadingLeftEdgeLength(dimensionPixelSize);
            this.mDetailsDescriptionViewHolder = presenter.onCreateViewHolder(this.mDetailsDescriptionFrame);
            this.mDetailsDescriptionFrame.addView(this.mDetailsDescriptionViewHolder.view);
        }
    }

    public DetailsOverviewRowPresenter(Presenter presenter) {
        setHeaderPresenter(null);
        setSelectEffectEnabled(false);
        this.mDetailsPresenter = presenter;
    }

    public void setOnActionClickedListener(OnActionClickedListener onActionClickedListener) {
        this.mActionClickedListener = onActionClickedListener;
    }

    public OnActionClickedListener getOnActionClickedListener() {
        return this.mActionClickedListener;
    }

    public void setBackgroundColor(@ColorInt int i) {
        this.mBackgroundColor = i;
        this.mBackgroundColorSet = true;
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setStyleLarge(boolean z) {
        this.mIsStyleLarge = z;
    }

    public boolean isStyleLarge() {
        return this.mIsStyleLarge;
    }

    public final void setSharedElementEnterTransition(Activity activity, String str, long j) {
        if (this.mSharedElementHelper == null) {
            this.mSharedElementHelper = new DetailsOverviewSharedElementHelper();
        }
        this.mSharedElementHelper.setSharedElementEnterTransition(activity, str, j);
    }

    public final void setSharedElementEnterTransition(Activity activity, String str) {
        setSharedElementEnterTransition(activity, str, DEFAULT_TIMEOUT);
    }

    private int getDefaultBackgroundColor(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.defaultBrandColor, typedValue, true)) {
            return context.getResources().getColor(typedValue.resourceId);
        }
        return context.getResources().getColor(R.color.lb_default_brand_color);
    }

    @Override // android.support.v17.leanback.widget.RowPresenter
    protected void onRowViewSelected(RowPresenter.ViewHolder viewHolder, boolean z) {
        super.onRowViewSelected(viewHolder, z);
        if (z) {
            ((ViewHolder) viewHolder).dispatchItemSelection(null);
        }
    }

    @Override // android.support.v17.leanback.widget.RowPresenter
    protected RowPresenter.ViewHolder createRowViewHolder(ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.lb_details_overview, viewGroup, false), this.mDetailsPresenter);
        initDetailsOverview(viewHolder);
        return viewHolder;
    }

    private int getCardHeight(Context context) {
        return context.getResources().getDimensionPixelSize(this.mIsStyleLarge ? R.dimen.lb_details_overview_height_large : R.dimen.lb_details_overview_height_small);
    }

    private void initDetailsOverview(final ViewHolder viewHolder) {
        viewHolder.mActionBridgeAdapter = new ActionsItemBridgeAdapter(viewHolder);
        FrameLayout frameLayout = viewHolder.mOverviewFrame;
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        layoutParams.height = getCardHeight(frameLayout.getContext());
        frameLayout.setLayoutParams(layoutParams);
        if (!getSelectEffectEnabled()) {
            viewHolder.mOverviewFrame.setForeground(null);
        }
        viewHolder.mActionsRow.setOnUnhandledKeyListener(new BaseGridView.OnUnhandledKeyListener() { // from class: android.support.v17.leanback.widget.DetailsOverviewRowPresenter.1
            @Override // android.support.v17.leanback.widget.BaseGridView.OnUnhandledKeyListener
            public boolean onUnhandledKey(KeyEvent keyEvent) {
                return viewHolder.getOnKeyListener() != null && viewHolder.getOnKeyListener().onKey(viewHolder.view, keyEvent.getKeyCode(), keyEvent);
            }
        });
    }

    private static int getNonNegativeWidth(Drawable drawable) {
        int intrinsicWidth = drawable == null ? 0 : drawable.getIntrinsicWidth();
        if (intrinsicWidth > 0) {
            return intrinsicWidth;
        }
        return 0;
    }

    private static int getNonNegativeHeight(Drawable drawable) {
        int intrinsicHeight = drawable == null ? 0 : drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            return intrinsicHeight;
        }
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0064  */
    void bindImageDrawable(ViewHolder viewHolder) {
        boolean z;
        boolean z2;
        DetailsOverviewRow detailsOverviewRow = (DetailsOverviewRow) viewHolder.getRow();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewHolder.mImageView.getLayoutParams();
        int cardHeight = getCardHeight(viewHolder.mImageView.getContext());
        int dimensionPixelSize = viewHolder.mImageView.getResources().getDimensionPixelSize(R.dimen.lb_details_overview_image_margin_vertical);
        int dimensionPixelSize2 = viewHolder.mImageView.getResources().getDimensionPixelSize(R.dimen.lb_details_overview_image_margin_horizontal);
        int nonNegativeWidth = getNonNegativeWidth(detailsOverviewRow.getImageDrawable());
        int nonNegativeHeight = getNonNegativeHeight(detailsOverviewRow.getImageDrawable());
        boolean zIsImageScaleUpAllowed = detailsOverviewRow.isImageScaleUpAllowed();
        if (detailsOverviewRow.getImageDrawable() != null) {
            if (nonNegativeWidth > nonNegativeHeight) {
                if (this.mIsStyleLarge) {
                    z2 = true;
                } else {
                    z = false;
                    z2 = true;
                }
                if ((!z2 && nonNegativeWidth > cardHeight) || (!z2 && nonNegativeHeight > cardHeight)) {
                }
                if (!zIsImageScaleUpAllowed) {
                    z = true;
                }
                if (z && !zIsImageScaleUpAllowed && ((z2 && nonNegativeWidth > cardHeight - dimensionPixelSize2) || (!z2 && nonNegativeHeight > cardHeight - (dimensionPixelSize * 2)))) {
                    zIsImageScaleUpAllowed = true;
                }
            } else {
                z2 = false;
            }
            z = z2;
            zIsImageScaleUpAllowed = !z2 ? true : true;
            if (!zIsImageScaleUpAllowed) {
                z = true;
            }
            if (z) {
                zIsImageScaleUpAllowed = true;
            }
        } else {
            z = false;
        }
        int defaultBackgroundColor = this.mBackgroundColorSet ? this.mBackgroundColor : getDefaultBackgroundColor(viewHolder.mOverviewView.getContext());
        if (z) {
            marginLayoutParams.setMarginStart(dimensionPixelSize2);
            marginLayoutParams.bottomMargin = dimensionPixelSize;
            marginLayoutParams.topMargin = dimensionPixelSize;
            viewHolder.mOverviewFrame.setBackgroundColor(defaultBackgroundColor);
            viewHolder.mRightPanel.setBackground(null);
            viewHolder.mImageView.setBackground(null);
        } else {
            marginLayoutParams.bottomMargin = 0;
            marginLayoutParams.topMargin = 0;
            marginLayoutParams.leftMargin = 0;
            viewHolder.mRightPanel.setBackgroundColor(defaultBackgroundColor);
            viewHolder.mImageView.setBackgroundColor(defaultBackgroundColor);
            viewHolder.mOverviewFrame.setBackground(null);
        }
        RoundedRectHelper.getInstance().setClipToRoundedOutline(viewHolder.mOverviewFrame, true);
        if (zIsImageScaleUpAllowed) {
            viewHolder.mImageView.setScaleType(ImageView.ScaleType.FIT_START);
            viewHolder.mImageView.setAdjustViewBounds(true);
            viewHolder.mImageView.setMaxWidth(cardHeight);
            marginLayoutParams.height = -1;
            marginLayoutParams.width = -2;
        } else {
            viewHolder.mImageView.setScaleType(ImageView.ScaleType.CENTER);
            viewHolder.mImageView.setAdjustViewBounds(false);
            marginLayoutParams.height = -2;
            marginLayoutParams.width = Math.min(cardHeight, nonNegativeWidth);
        }
        viewHolder.mImageView.setLayoutParams(marginLayoutParams);
        viewHolder.mImageView.setImageDrawable(detailsOverviewRow.getImageDrawable());
        if (detailsOverviewRow.getImageDrawable() == null || this.mSharedElementHelper == null) {
            return;
        }
        this.mSharedElementHelper.onBindToDrawable(viewHolder);
    }

    @Override // android.support.v17.leanback.widget.RowPresenter
    protected void onBindRowViewHolder(RowPresenter.ViewHolder viewHolder, Object obj) {
        super.onBindRowViewHolder(viewHolder, obj);
        DetailsOverviewRow detailsOverviewRow = (DetailsOverviewRow) obj;
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        bindImageDrawable(viewHolder2);
        this.mDetailsPresenter.onBindViewHolder(viewHolder2.mDetailsDescriptionViewHolder, detailsOverviewRow.getItem());
        viewHolder2.bindActions(detailsOverviewRow.getActionsAdapter());
        detailsOverviewRow.addListener(viewHolder2.mListener);
    }

    @Override // android.support.v17.leanback.widget.RowPresenter
    protected void onUnbindRowViewHolder(RowPresenter.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        ((DetailsOverviewRow) viewHolder2.getRow()).removeListener(viewHolder2.mListener);
        if (viewHolder2.mDetailsDescriptionViewHolder != null) {
            this.mDetailsPresenter.onUnbindViewHolder(viewHolder2.mDetailsDescriptionViewHolder);
        }
        super.onUnbindRowViewHolder(viewHolder);
    }

    @Override // android.support.v17.leanback.widget.RowPresenter
    protected void onSelectLevelChanged(RowPresenter.ViewHolder viewHolder) {
        super.onSelectLevelChanged(viewHolder);
        if (getSelectEffectEnabled()) {
            ViewHolder viewHolder2 = (ViewHolder) viewHolder;
            ((ColorDrawable) viewHolder2.mOverviewFrame.getForeground().mutate()).setColor(viewHolder2.mColorDimmer.getPaint().getColor());
        }
    }

    @Override // android.support.v17.leanback.widget.RowPresenter
    protected void onRowViewAttachedToWindow(RowPresenter.ViewHolder viewHolder) {
        super.onRowViewAttachedToWindow(viewHolder);
        if (this.mDetailsPresenter != null) {
            this.mDetailsPresenter.onViewAttachedToWindow(((ViewHolder) viewHolder).mDetailsDescriptionViewHolder);
        }
    }

    @Override // android.support.v17.leanback.widget.RowPresenter
    protected void onRowViewDetachedFromWindow(RowPresenter.ViewHolder viewHolder) {
        super.onRowViewDetachedFromWindow(viewHolder);
        if (this.mDetailsPresenter != null) {
            this.mDetailsPresenter.onViewDetachedFromWindow(((ViewHolder) viewHolder).mDetailsDescriptionViewHolder);
        }
    }
}
