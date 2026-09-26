package android.support.v17.leanback.widget;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.v17.leanback.transition.TransitionHelper;
import android.support.v17.leanback.transition.TransitionListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class DetailsOverviewSharedElementHelper extends SharedElementCallback {
    static final boolean DEBUG = false;
    static final String TAG = "DetailsTransitionHelper";
    Activity mActivityToRunTransition;
    int mRightPanelHeight;
    int mRightPanelWidth;
    private Matrix mSavedMatrix;
    private ImageView.ScaleType mSavedScaleType;
    String mSharedElementName;
    boolean mStartedPostpone;
    DetailsOverviewRowPresenter.ViewHolder mViewHolder;

    DetailsOverviewSharedElementHelper() {
    }

    static class TransitionTimeOutRunnable implements Runnable {
        WeakReference<DetailsOverviewSharedElementHelper> mHelperRef;

        TransitionTimeOutRunnable(DetailsOverviewSharedElementHelper detailsOverviewSharedElementHelper) {
            this.mHelperRef = new WeakReference<>(detailsOverviewSharedElementHelper);
        }

        @Override // java.lang.Runnable
        public void run() {
            DetailsOverviewSharedElementHelper detailsOverviewSharedElementHelper = this.mHelperRef.get();
            if (detailsOverviewSharedElementHelper == null) {
                return;
            }
            detailsOverviewSharedElementHelper.startPostponedEnterTransition();
        }
    }

    private boolean hasImageViewScaleChange(View view) {
        return view instanceof ImageView;
    }

    private void saveImageViewScale() {
        if (this.mSavedScaleType == null) {
            ImageView imageView = this.mViewHolder.mImageView;
            this.mSavedScaleType = imageView.getScaleType();
            this.mSavedMatrix = this.mSavedScaleType == ImageView.ScaleType.MATRIX ? imageView.getMatrix() : null;
        }
    }

    private static void updateImageViewAfterScaleTypeChange(ImageView imageView) {
        imageView.measure(View.MeasureSpec.makeMeasureSpec(imageView.getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(imageView.getMeasuredHeight(), 1073741824));
        imageView.layout(imageView.getLeft(), imageView.getTop(), imageView.getRight(), imageView.getBottom());
    }

    private void changeImageViewScale(View view) {
        ImageView imageView = (ImageView) view;
        ImageView imageView2 = this.mViewHolder.mImageView;
        imageView2.setScaleType(imageView.getScaleType());
        if (imageView.getScaleType() == ImageView.ScaleType.MATRIX) {
            imageView2.setImageMatrix(imageView.getImageMatrix());
        }
        updateImageViewAfterScaleTypeChange(imageView2);
    }

    private void restoreImageViewScale() {
        if (this.mSavedScaleType != null) {
            ImageView imageView = this.mViewHolder.mImageView;
            imageView.setScaleType(this.mSavedScaleType);
            if (this.mSavedScaleType == ImageView.ScaleType.MATRIX) {
                imageView.setImageMatrix(this.mSavedMatrix);
            }
            this.mSavedScaleType = null;
            updateImageViewAfterScaleTypeChange(imageView);
        }
    }

    @Override // android.support.v4.app.SharedElementCallback
    public void onSharedElementStart(List<String> list, List<View> list2, List<View> list3) {
        if (list2.size() < 1) {
            return;
        }
        View view = list2.get(0);
        if (this.mViewHolder == null || this.mViewHolder.mOverviewFrame != view) {
            return;
        }
        View view2 = list3.get(0);
        if (hasImageViewScaleChange(view2)) {
            saveImageViewScale();
            changeImageViewScale(view2);
        }
        ImageView imageView = this.mViewHolder.mImageView;
        int width = view.getWidth();
        int height = view.getHeight();
        imageView.measure(View.MeasureSpec.makeMeasureSpec(width, 1073741824), View.MeasureSpec.makeMeasureSpec(height, 1073741824));
        imageView.layout(0, 0, width, height);
        ViewGroup viewGroup = this.mViewHolder.mRightPanel;
        if (this.mRightPanelWidth != 0 && this.mRightPanelHeight != 0) {
            viewGroup.measure(View.MeasureSpec.makeMeasureSpec(this.mRightPanelWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(this.mRightPanelHeight, 1073741824));
            viewGroup.layout(width, viewGroup.getTop(), this.mRightPanelWidth + width, viewGroup.getTop() + this.mRightPanelHeight);
        } else {
            viewGroup.offsetLeftAndRight(width - viewGroup.getLeft());
        }
        this.mViewHolder.mActionsRow.setVisibility(4);
        this.mViewHolder.mDetailsDescriptionFrame.setVisibility(4);
    }

    @Override // android.support.v4.app.SharedElementCallback
    public void onSharedElementEnd(List<String> list, List<View> list2, List<View> list3) {
        if (list2.size() < 1) {
            return;
        }
        View view = list2.get(0);
        if (this.mViewHolder == null || this.mViewHolder.mOverviewFrame != view) {
            return;
        }
        restoreImageViewScale();
        this.mViewHolder.mActionsRow.setDescendantFocusability(131072);
        this.mViewHolder.mActionsRow.setVisibility(0);
        this.mViewHolder.mActionsRow.setDescendantFocusability(262144);
        this.mViewHolder.mDetailsDescriptionFrame.setVisibility(0);
    }

    void setSharedElementEnterTransition(Activity activity, String str, long j) {
        if ((activity == null && !TextUtils.isEmpty(str)) || (activity != null && TextUtils.isEmpty(str))) {
            throw new IllegalArgumentException();
        }
        if (activity == this.mActivityToRunTransition && TextUtils.equals(str, this.mSharedElementName)) {
            return;
        }
        if (this.mActivityToRunTransition != null) {
            ActivityCompat.setEnterSharedElementCallback(this.mActivityToRunTransition, null);
        }
        this.mActivityToRunTransition = activity;
        this.mSharedElementName = str;
        ActivityCompat.setEnterSharedElementCallback(this.mActivityToRunTransition, this);
        ActivityCompat.postponeEnterTransition(this.mActivityToRunTransition);
        if (j > 0) {
            new Handler().postDelayed(new TransitionTimeOutRunnable(this), j);
        }
    }

    void onBindToDrawable(DetailsOverviewRowPresenter.ViewHolder viewHolder) {
        if (this.mViewHolder != null) {
            ViewCompat.setTransitionName(this.mViewHolder.mOverviewFrame, null);
        }
        this.mViewHolder = viewHolder;
        this.mViewHolder.mRightPanel.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: android.support.v17.leanback.widget.DetailsOverviewSharedElementHelper.1
            @Override // android.view.View.OnLayoutChangeListener
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                DetailsOverviewSharedElementHelper.this.mViewHolder.mRightPanel.removeOnLayoutChangeListener(this);
                DetailsOverviewSharedElementHelper.this.mRightPanelWidth = DetailsOverviewSharedElementHelper.this.mViewHolder.mRightPanel.getWidth();
                DetailsOverviewSharedElementHelper.this.mRightPanelHeight = DetailsOverviewSharedElementHelper.this.mViewHolder.mRightPanel.getHeight();
            }
        });
        this.mViewHolder.mRightPanel.postOnAnimation(new Runnable() { // from class: android.support.v17.leanback.widget.DetailsOverviewSharedElementHelper.2
            @Override // java.lang.Runnable
            public void run() {
                ViewCompat.setTransitionName(DetailsOverviewSharedElementHelper.this.mViewHolder.mOverviewFrame, DetailsOverviewSharedElementHelper.this.mSharedElementName);
                Object sharedElementEnterTransition = TransitionHelper.getSharedElementEnterTransition(DetailsOverviewSharedElementHelper.this.mActivityToRunTransition.getWindow());
                if (sharedElementEnterTransition != null) {
                    TransitionHelper.addTransitionListener(sharedElementEnterTransition, new TransitionListener() { // from class: android.support.v17.leanback.widget.DetailsOverviewSharedElementHelper.2.1
                        @Override // android.support.v17.leanback.transition.TransitionListener
                        public void onTransitionEnd(Object obj) {
                            if (DetailsOverviewSharedElementHelper.this.mViewHolder.mActionsRow.isFocused()) {
                                DetailsOverviewSharedElementHelper.this.mViewHolder.mActionsRow.requestFocus();
                            }
                            TransitionHelper.removeTransitionListener(obj, this);
                        }
                    });
                }
                DetailsOverviewSharedElementHelper.this.startPostponedEnterTransition();
            }
        });
    }

    void startPostponedEnterTransition() {
        if (this.mStartedPostpone) {
            return;
        }
        ActivityCompat.startPostponedEnterTransition(this.mActivityToRunTransition);
        this.mStartedPostpone = true;
    }
}
