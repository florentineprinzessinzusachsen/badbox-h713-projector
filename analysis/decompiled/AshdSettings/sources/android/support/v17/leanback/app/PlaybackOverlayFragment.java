package android.support.v17.leanback.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v17.leanback.R;
import android.support.v17.leanback.animation.LogAccelerateInterpolator;
import android.support.v17.leanback.animation.LogDecelerateInterpolator;
import android.support.v17.leanback.media.PlaybackGlueHost;
import android.support.v17.leanback.widget.BaseGridView;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.PlaybackControlsRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v7.widget.RecyclerView;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class PlaybackOverlayFragment extends DetailsFragment {
    private static final int ANIMATION_MULTIPLIER = 1;
    public static final int BG_DARK = 1;
    public static final int BG_LIGHT = 2;
    public static final int BG_NONE = 0;
    static final boolean DEBUG = false;
    static final int IDLE = 0;
    private static final int IN = 1;
    static final int OUT = 2;
    static int START_FADE_OUT = 1;
    static final String TAG = "PlaybackOverlayFragment";
    static final Handler sHandler = new FadeHandler();
    int mAnimationTranslateY;
    int mBgAlpha;
    private int mBgDarkColor;
    private ValueAnimator mBgFadeInAnimator;
    private ValueAnimator mBgFadeOutAnimator;
    private int mBgLightColor;
    private ValueAnimator mControlRowFadeInAnimator;
    private ValueAnimator mControlRowFadeOutAnimator;
    private ValueAnimator mDescriptionFadeInAnimator;
    private ValueAnimator mDescriptionFadeOutAnimator;
    OnFadeCompleteListener mFadeCompleteListener;
    PlaybackGlueHost.HostCallback mHostCallback;
    private PlaybackControlGlue.InputEventHandler mInputEventHandler;
    private int mMajorFadeTranslateY;
    private int mMinorFadeTranslateY;
    private ValueAnimator mOtherRowFadeInAnimator;
    private ValueAnimator mOtherRowFadeOutAnimator;
    private int mPaddingBottom;
    private int mPaddingTop;
    boolean mResetControlsToPrimaryActionsPending;
    private View mRootView;
    private int mShowTimeMs;
    private int mBackgroundType = 1;
    boolean mFadingEnabled = true;
    int mFadingStatus = 0;
    private final Animator.AnimatorListener mFadeListener = new Animator.AnimatorListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.1
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            PlaybackOverlayFragment.this.enableVerticalGridAnimations(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (PlaybackOverlayFragment.this.mBgAlpha > 0) {
                PlaybackOverlayFragment.this.enableVerticalGridAnimations(true);
                PlaybackOverlayFragment.this.startFadeTimer();
                if (PlaybackOverlayFragment.this.mFadeCompleteListener != null) {
                    PlaybackOverlayFragment.this.mFadeCompleteListener.onFadeInComplete();
                }
            } else {
                VerticalGridView verticalGridView = PlaybackOverlayFragment.this.getVerticalGridView();
                if (verticalGridView != null && verticalGridView.getSelectedPosition() == 0) {
                    PlaybackOverlayFragment.this.resetControlsToPrimaryActions(null);
                }
                if (PlaybackOverlayFragment.this.mFadeCompleteListener != null) {
                    PlaybackOverlayFragment.this.mFadeCompleteListener.onFadeOutComplete();
                }
            }
            PlaybackOverlayFragment.this.mFadingStatus = 0;
        }
    };
    final WeakReference<PlaybackOverlayFragment> mFragmentReference = new WeakReference<>(this);
    private final BaseGridView.OnTouchInterceptListener mOnTouchInterceptListener = new BaseGridView.OnTouchInterceptListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.2
        @Override // android.support.v17.leanback.widget.BaseGridView.OnTouchInterceptListener
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return PlaybackOverlayFragment.this.onInterceptInputEvent(motionEvent);
        }
    };
    private final BaseGridView.OnKeyInterceptListener mOnKeyInterceptListener = new BaseGridView.OnKeyInterceptListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.3
        @Override // android.support.v17.leanback.widget.BaseGridView.OnKeyInterceptListener
        public boolean onInterceptKeyEvent(KeyEvent keyEvent) {
            return PlaybackOverlayFragment.this.onInterceptInputEvent(keyEvent);
        }
    };
    private TimeInterpolator mLogDecelerateInterpolator = new LogDecelerateInterpolator(100, 0);
    private TimeInterpolator mLogAccelerateInterpolator = new LogAccelerateInterpolator(100, 0);
    private final ItemBridgeAdapter.AdapterListener mAdapterListener = new ItemBridgeAdapter.AdapterListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.10
        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onAttachedToWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            if ((PlaybackOverlayFragment.this.mFadingStatus == 0 && PlaybackOverlayFragment.this.mBgAlpha == 0) || PlaybackOverlayFragment.this.mFadingStatus == 2) {
                viewHolder.getViewHolder().view.setAlpha(0.0f);
            }
            if (viewHolder.getPosition() == 0 && PlaybackOverlayFragment.this.mResetControlsToPrimaryActionsPending) {
                PlaybackOverlayFragment.this.resetControlsToPrimaryActions(viewHolder);
            }
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onDetachedFromWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            Presenter.ViewHolder viewHolder2;
            viewHolder.getViewHolder().view.setAlpha(1.0f);
            viewHolder.getViewHolder().view.setTranslationY(0.0f);
            if (!(viewHolder.getViewHolder() instanceof PlaybackControlsRowPresenter.ViewHolder) || (viewHolder2 = ((PlaybackControlsRowPresenter.ViewHolder) viewHolder.getViewHolder()).mDescriptionViewHolder) == null) {
                return;
            }
            viewHolder2.view.setAlpha(1.0f);
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onBind(ItemBridgeAdapter.ViewHolder viewHolder) {
            if (viewHolder.getPosition() == 0) {
                PlaybackOverlayFragment.this.updateControlsBottomSpace(viewHolder);
            }
        }
    };
    private final ObjectAdapter.DataObserver mObserver = new ObjectAdapter.DataObserver() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.11
        @Override // android.support.v17.leanback.widget.ObjectAdapter.DataObserver
        public void onChanged() {
            PlaybackOverlayFragment.this.updateControlsBottomSpace(null);
        }
    };

    @Deprecated
    public interface InputEventHandler extends PlaybackControlGlue.InputEventHandler {
    }

    public static class OnFadeCompleteListener {
        public void onFadeInComplete() {
        }

        public void onFadeOutComplete() {
        }
    }

    static class FadeHandler extends Handler {
        FadeHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            PlaybackOverlayFragment playbackOverlayFragment;
            if (message.what == PlaybackOverlayFragment.START_FADE_OUT && (playbackOverlayFragment = (PlaybackOverlayFragment) ((WeakReference) message.obj).get()) != null && playbackOverlayFragment.mFadingEnabled) {
                playbackOverlayFragment.fade(false);
            }
        }
    }

    void setBgAlpha(int i) {
        this.mBgAlpha = i;
        if (this.mRootView != null) {
            this.mRootView.getBackground().setAlpha(i);
        }
    }

    void enableVerticalGridAnimations(boolean z) {
        if (getVerticalGridView() != null) {
            getVerticalGridView().setAnimateChildLayout(z);
        }
    }

    void resetControlsToPrimaryActions(ItemBridgeAdapter.ViewHolder viewHolder) {
        if (viewHolder == null && getVerticalGridView() != null) {
            viewHolder = (ItemBridgeAdapter.ViewHolder) getVerticalGridView().findViewHolderForPosition(0);
        }
        if (viewHolder == null) {
            this.mResetControlsToPrimaryActionsPending = true;
        } else if (viewHolder.getPresenter() instanceof PlaybackControlsRowPresenter) {
            this.mResetControlsToPrimaryActionsPending = false;
            ((PlaybackControlsRowPresenter) viewHolder.getPresenter()).showPrimaryActions((PlaybackControlsRowPresenter.ViewHolder) viewHolder.getViewHolder());
        }
    }

    public void setFadingEnabled(boolean z) {
        if (z != this.mFadingEnabled) {
            this.mFadingEnabled = z;
            if (this.mFadingEnabled) {
                if (isResumed() && this.mFadingStatus == 0 && !sHandler.hasMessages(START_FADE_OUT, this.mFragmentReference)) {
                    startFadeTimer();
                    return;
                }
                return;
            }
            sHandler.removeMessages(START_FADE_OUT, this.mFragmentReference);
            fade(true);
        }
    }

    public boolean isFadingEnabled() {
        return this.mFadingEnabled;
    }

    public void setFadeCompleteListener(OnFadeCompleteListener onFadeCompleteListener) {
        this.mFadeCompleteListener = onFadeCompleteListener;
    }

    public OnFadeCompleteListener getFadeCompleteListener() {
        return this.mFadeCompleteListener;
    }

    @Deprecated
    public final void setInputEventHandler(InputEventHandler inputEventHandler) {
        this.mInputEventHandler = inputEventHandler;
    }

    @Deprecated
    public final InputEventHandler getInputEventHandler() {
        return (InputEventHandler) this.mInputEventHandler;
    }

    public final void setEventHandler(PlaybackControlGlue.InputEventHandler inputEventHandler) {
        this.mInputEventHandler = inputEventHandler;
    }

    public final PlaybackControlGlue.InputEventHandler getEventHandler() {
        return this.mInputEventHandler;
    }

    public void tickle() {
        if (this.mFadingEnabled && isResumed()) {
            if (sHandler.hasMessages(START_FADE_OUT, this.mFragmentReference)) {
                startFadeTimer();
            } else {
                fade(true);
            }
        }
    }

    public void fadeOut() {
        sHandler.removeMessages(START_FADE_OUT, this.mFragmentReference);
        fade(false);
    }

    void setHostCallback(PlaybackGlueHost.HostCallback hostCallback) {
        this.mHostCallback = hostCallback;
    }

    @Override // android.support.v17.leanback.app.DetailsFragment, android.app.Fragment
    public void onStop() {
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostStop();
        }
        super.onStop();
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onPause() {
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostPause();
        }
        super.onPause();
    }

    private boolean areControlsHidden() {
        return this.mFadingStatus == 0 && this.mBgAlpha == 0;
    }

    boolean onInterceptInputEvent(InputEvent inputEvent) {
        boolean zAreControlsHidden = areControlsHidden();
        boolean zHandleInputEvent = this.mInputEventHandler != null ? this.mInputEventHandler.handleInputEvent(inputEvent) : false;
        int keyCode = inputEvent instanceof KeyEvent ? ((KeyEvent) inputEvent).getKeyCode() : 0;
        if (keyCode != 4 && keyCode != 111) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    if (zAreControlsHidden) {
                        zHandleInputEvent = true;
                    }
                    tickle();
                    return zHandleInputEvent;
                default:
                    if (!zHandleInputEvent) {
                        return zHandleInputEvent;
                    }
                    tickle();
                    return zHandleInputEvent;
            }
        }
        if (this.mFadingEnabled && !zAreControlsHidden) {
            sHandler.removeMessages(START_FADE_OUT, this.mFragmentReference);
            fade(false);
            return true;
        }
        if (!zHandleInputEvent) {
            return zHandleInputEvent;
        }
        tickle();
        return zHandleInputEvent;
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.mFadingEnabled) {
            setBgAlpha(0);
            fade(true);
        }
        getVerticalGridView().setOnTouchInterceptListener(this.mOnTouchInterceptListener);
        getVerticalGridView().setOnKeyInterceptListener(this.mOnKeyInterceptListener);
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostResume();
        }
    }

    void startFadeTimer() {
        sHandler.removeMessages(START_FADE_OUT, this.mFragmentReference);
        sHandler.sendMessageDelayed(sHandler.obtainMessage(START_FADE_OUT, this.mFragmentReference), this.mShowTimeMs);
    }

    private static ValueAnimator loadAnimator(Context context, int i) {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(context, i);
        valueAnimator.setDuration(valueAnimator.getDuration() * 1);
        return valueAnimator;
    }

    private void loadBgAnimator() {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PlaybackOverlayFragment.this.setBgAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        };
        Context context = FragmentUtil.getContext(this);
        this.mBgFadeInAnimator = loadAnimator(context, R.animator.lb_playback_bg_fade_in);
        this.mBgFadeInAnimator.addUpdateListener(animatorUpdateListener);
        this.mBgFadeInAnimator.addListener(this.mFadeListener);
        this.mBgFadeOutAnimator = loadAnimator(context, R.animator.lb_playback_bg_fade_out);
        this.mBgFadeOutAnimator.addUpdateListener(animatorUpdateListener);
        this.mBgFadeOutAnimator.addListener(this.mFadeListener);
    }

    View getControlRowView() {
        RecyclerView.ViewHolder viewHolderFindViewHolderForPosition;
        if (getVerticalGridView() == null || (viewHolderFindViewHolderForPosition = getVerticalGridView().findViewHolderForPosition(0)) == null) {
            return null;
        }
        return viewHolderFindViewHolderForPosition.itemView;
    }

    private void loadControlRowAnimator() {
        AnimatorListener animatorListener = new AnimatorListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.5
            @Override // android.support.v17.leanback.app.PlaybackOverlayFragment.AnimatorListener
            void getViews(ArrayList<View> arrayList) {
                View controlRowView = PlaybackOverlayFragment.this.getControlRowView();
                if (controlRowView != null) {
                    arrayList.add(controlRowView);
                }
            }
        };
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                View controlRowView = PlaybackOverlayFragment.this.getControlRowView();
                if (controlRowView != null) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    controlRowView.setAlpha(fFloatValue);
                    controlRowView.setTranslationY(PlaybackOverlayFragment.this.mAnimationTranslateY * (1.0f - fFloatValue));
                }
            }
        };
        Context context = FragmentUtil.getContext(this);
        this.mControlRowFadeInAnimator = loadAnimator(context, R.animator.lb_playback_controls_fade_in);
        this.mControlRowFadeInAnimator.addUpdateListener(animatorUpdateListener);
        this.mControlRowFadeInAnimator.addListener(animatorListener);
        this.mControlRowFadeInAnimator.setInterpolator(this.mLogDecelerateInterpolator);
        this.mControlRowFadeOutAnimator = loadAnimator(context, R.animator.lb_playback_controls_fade_out);
        this.mControlRowFadeOutAnimator.addUpdateListener(animatorUpdateListener);
        this.mControlRowFadeOutAnimator.addListener(animatorListener);
        this.mControlRowFadeOutAnimator.setInterpolator(this.mLogAccelerateInterpolator);
    }

    private void loadOtherRowAnimator() {
        final AnimatorListener animatorListener = new AnimatorListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.7
            @Override // android.support.v17.leanback.app.PlaybackOverlayFragment.AnimatorListener
            void getViews(ArrayList<View> arrayList) {
                if (PlaybackOverlayFragment.this.getVerticalGridView() == null) {
                    return;
                }
                int childCount = PlaybackOverlayFragment.this.getVerticalGridView().getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = PlaybackOverlayFragment.this.getVerticalGridView().getChildAt(i);
                    if (childAt != null) {
                        arrayList.add(childAt);
                    }
                }
            }
        };
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.8
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (PlaybackOverlayFragment.this.getVerticalGridView() == null) {
                    return;
                }
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                for (View view : animatorListener.mViews) {
                    if (PlaybackOverlayFragment.this.getVerticalGridView().getChildPosition(view) > 0) {
                        view.setAlpha(fFloatValue);
                        view.setTranslationY(PlaybackOverlayFragment.this.mAnimationTranslateY * (1.0f - fFloatValue));
                    }
                }
            }
        };
        Context context = FragmentUtil.getContext(this);
        this.mOtherRowFadeInAnimator = loadAnimator(context, R.animator.lb_playback_controls_fade_in);
        this.mOtherRowFadeInAnimator.addListener(animatorListener);
        this.mOtherRowFadeInAnimator.addUpdateListener(animatorUpdateListener);
        this.mOtherRowFadeInAnimator.setInterpolator(this.mLogDecelerateInterpolator);
        this.mOtherRowFadeOutAnimator = loadAnimator(context, R.animator.lb_playback_controls_fade_out);
        this.mOtherRowFadeOutAnimator.addListener(animatorListener);
        this.mOtherRowFadeOutAnimator.addUpdateListener(animatorUpdateListener);
        this.mOtherRowFadeOutAnimator.setInterpolator(new AccelerateInterpolator());
    }

    private void loadDescriptionAnimator() {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.PlaybackOverlayFragment.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ItemBridgeAdapter.ViewHolder viewHolder;
                Presenter.ViewHolder viewHolder2;
                if (PlaybackOverlayFragment.this.getVerticalGridView() == null || (viewHolder = (ItemBridgeAdapter.ViewHolder) PlaybackOverlayFragment.this.getVerticalGridView().findViewHolderForPosition(0)) == null || !(viewHolder.getViewHolder() instanceof PlaybackControlsRowPresenter.ViewHolder) || (viewHolder2 = ((PlaybackControlsRowPresenter.ViewHolder) viewHolder.getViewHolder()).mDescriptionViewHolder) == null) {
                    return;
                }
                viewHolder2.view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        };
        Context context = FragmentUtil.getContext(this);
        this.mDescriptionFadeInAnimator = loadAnimator(context, R.animator.lb_playback_description_fade_in);
        this.mDescriptionFadeInAnimator.addUpdateListener(animatorUpdateListener);
        this.mDescriptionFadeInAnimator.setInterpolator(this.mLogDecelerateInterpolator);
        this.mDescriptionFadeOutAnimator = loadAnimator(context, R.animator.lb_playback_description_fade_out);
        this.mDescriptionFadeOutAnimator.addUpdateListener(animatorUpdateListener);
    }

    void fade(boolean z) {
        if (getView() == null) {
            return;
        }
        if (z && this.mFadingStatus == 1) {
            return;
        }
        if (z || this.mFadingStatus != 2) {
            if (z && this.mBgAlpha == 255) {
                return;
            }
            if (z || this.mBgAlpha != 0) {
                this.mAnimationTranslateY = getVerticalGridView().getSelectedPosition() == 0 ? this.mMajorFadeTranslateY : this.mMinorFadeTranslateY;
                if (this.mFadingStatus == 0) {
                    if (z) {
                        this.mBgFadeInAnimator.start();
                        this.mControlRowFadeInAnimator.start();
                        this.mOtherRowFadeInAnimator.start();
                        this.mDescriptionFadeInAnimator.start();
                    } else {
                        this.mBgFadeOutAnimator.start();
                        this.mControlRowFadeOutAnimator.start();
                        this.mOtherRowFadeOutAnimator.start();
                        this.mDescriptionFadeOutAnimator.start();
                    }
                } else if (z) {
                    this.mBgFadeOutAnimator.reverse();
                    this.mControlRowFadeOutAnimator.reverse();
                    this.mOtherRowFadeOutAnimator.reverse();
                    this.mDescriptionFadeOutAnimator.reverse();
                } else {
                    this.mBgFadeInAnimator.reverse();
                    this.mControlRowFadeInAnimator.reverse();
                    this.mOtherRowFadeInAnimator.reverse();
                    this.mDescriptionFadeInAnimator.reverse();
                }
                getView().announceForAccessibility(getString(z ? R.string.lb_playback_controls_shown : R.string.lb_playback_controls_hidden));
                if (z && this.mFadingStatus == 0) {
                    int childCount = getVerticalGridView().getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        getVerticalGridView().getChildAt(i).setTranslationY(this.mAnimationTranslateY);
                    }
                }
                this.mFadingStatus = z ? 1 : 2;
            }
        }
    }

    @Override // android.support.v17.leanback.app.DetailsFragment
    public void setAdapter(ObjectAdapter objectAdapter) {
        if (getAdapter() != null) {
            getAdapter().unregisterObserver(this.mObserver);
        }
        super.setAdapter(objectAdapter);
        if (objectAdapter != null) {
            objectAdapter.registerObserver(this.mObserver);
        }
    }

    @Override // android.support.v17.leanback.app.DetailsFragment
    void setVerticalGridViewLayout(VerticalGridView verticalGridView) {
        if (verticalGridView == null) {
            return;
        }
        setPadding(verticalGridView, this.mPaddingTop, this.mPaddingBottom);
        verticalGridView.setItemAlignmentOffset(0);
        verticalGridView.setItemAlignmentOffsetPercent(50.0f);
        verticalGridView.setWindowAlignmentOffset(0);
        verticalGridView.setWindowAlignmentOffsetPercent(50.0f);
        verticalGridView.setWindowAlignment(3);
    }

    private static void setPadding(View view, int i, int i2) {
        view.setPadding(view.getPaddingLeft(), i, view.getPaddingRight(), i2);
    }

    @Override // android.support.v17.leanback.app.DetailsFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPaddingTop = getResources().getDimensionPixelSize(R.dimen.lb_playback_controls_padding_top);
        this.mPaddingBottom = getResources().getDimensionPixelSize(R.dimen.lb_playback_controls_padding_bottom);
        this.mBgDarkColor = getResources().getColor(R.color.lb_playback_controls_background_dark);
        this.mBgLightColor = getResources().getColor(R.color.lb_playback_controls_background_light);
        this.mShowTimeMs = getResources().getInteger(R.integer.lb_playback_controls_show_time_ms);
        this.mMajorFadeTranslateY = getResources().getDimensionPixelSize(R.dimen.lb_playback_major_fade_translate_y);
        this.mMinorFadeTranslateY = getResources().getDimensionPixelSize(R.dimen.lb_playback_minor_fade_translate_y);
        loadBgAnimator();
        loadControlRowAnimator();
        loadOtherRowAnimator();
        loadDescriptionAnimator();
    }

    public void setBackgroundType(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
                if (i != this.mBackgroundType) {
                    this.mBackgroundType = i;
                    updateBackground();
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("Invalid background type");
        }
    }

    public int getBackgroundType() {
        return this.mBackgroundType;
    }

    private void updateBackground() {
        if (this.mRootView != null) {
            int i = this.mBgDarkColor;
            switch (this.mBackgroundType) {
                case 0:
                    i = 0;
                    break;
                case 2:
                    i = this.mBgLightColor;
                    break;
            }
            this.mRootView.setBackground(new ColorDrawable(i));
        }
    }

    void updateControlsBottomSpace(ItemBridgeAdapter.ViewHolder viewHolder) {
        if (viewHolder == null && getVerticalGridView() != null) {
            viewHolder = (ItemBridgeAdapter.ViewHolder) getVerticalGridView().findViewHolderForPosition(0);
        }
        if (viewHolder == null || !(viewHolder.getPresenter() instanceof PlaybackControlsRowPresenter)) {
            return;
        }
        ((PlaybackControlsRowPresenter) viewHolder.getPresenter()).showBottomSpace((PlaybackControlsRowPresenter.ViewHolder) viewHolder.getViewHolder(), (getAdapter() == null ? 0 : getAdapter().size()) > 1);
    }

    @Override // android.support.v17.leanback.app.DetailsFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mBgAlpha = 255;
        updateBackground();
        getRowsFragment().setExternalAdapterListener(this.mAdapterListener);
        return this.mRootView;
    }

    @Override // android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onDestroyView() {
        this.mRootView = null;
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostDestroy();
        }
        super.onDestroyView();
    }

    @Override // android.support.v17.leanback.app.DetailsFragment, android.support.v17.leanback.app.BrandedFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        getRowsFragment().getView().requestFocus();
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostStart();
        }
    }

    static abstract class AnimatorListener implements Animator.AnimatorListener {
        ArrayList<View> mViews = new ArrayList<>();
        ArrayList<Integer> mLayerType = new ArrayList<>();

        abstract void getViews(ArrayList<View> arrayList);

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        AnimatorListener() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            getViews(this.mViews);
            for (View view : this.mViews) {
                this.mLayerType.add(Integer.valueOf(view.getLayerType()));
                view.setLayerType(2, null);
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            for (int i = 0; i < this.mViews.size(); i++) {
                this.mViews.get(i).setLayerType(this.mLayerType.get(i).intValue(), null);
            }
            this.mLayerType.clear();
            this.mViews.clear();
        }
    }
}
