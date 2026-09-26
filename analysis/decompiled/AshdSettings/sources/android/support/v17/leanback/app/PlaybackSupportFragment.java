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
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.BaseGridView;
import android.support.v17.leanback.widget.BaseOnItemViewClickedListener;
import android.support.v17.leanback.widget.BaseOnItemViewSelectedListener;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.ItemBridgeAdapter;
import android.support.v17.leanback.widget.ObjectAdapter;
import android.support.v17.leanback.widget.PlaybackRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackSupportFragment extends Fragment {
    private static final int ANIMATION_MULTIPLIER = 1;
    public static final int BG_DARK = 1;
    public static final int BG_LIGHT = 2;
    public static final int BG_NONE = 0;
    private static final boolean DEBUG = false;
    private static final int IDLE = 0;
    private static final int IN = 1;
    private static final int OUT = 2;
    private static int START_FADE_OUT = 1;
    private static final String TAG = "PlaybackSupportFragment";
    private ObjectAdapter mAdapter;
    private int mAnimationTranslateY;
    private int mBgAlpha;
    private int mBgDarkColor;
    private ValueAnimator mBgFadeInAnimator;
    private ValueAnimator mBgFadeOutAnimator;
    private int mBgLightColor;
    private ValueAnimator mControlRowFadeInAnimator;
    private ValueAnimator mControlRowFadeOutAnimator;
    private BaseOnItemViewClickedListener mExternalItemClickedListener;
    private BaseOnItemViewSelectedListener mExternalItemSelectedListener;
    private OnFadeCompleteListener mFadeCompleteListener;
    private PlaybackGlueHost.HostCallback mHostCallback;
    private View.OnKeyListener mInputEventHandler;
    private int mMajorFadeTranslateY;
    private int mMinorFadeTranslateY;
    private ValueAnimator mOtherRowFadeInAnimator;
    private ValueAnimator mOtherRowFadeOutAnimator;
    private int mPaddingBottom;
    private int mPaddingTop;
    private BaseOnItemViewClickedListener mPlaybackItemClickedListener;
    private PlaybackRowPresenter mPresenter;
    private View mRootView;
    private Row mRow;
    private RowsSupportFragment mRowsSupportFragment;
    private int mShowTimeMs;
    private final BaseOnItemViewClickedListener mOnItemViewClickedListener = new BaseOnItemViewClickedListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.1
        @Override // android.support.v17.leanback.widget.BaseOnItemViewClickedListener
        public void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
            if (PlaybackSupportFragment.this.mPlaybackItemClickedListener != null && (viewHolder2 instanceof PlaybackRowPresenter.ViewHolder)) {
                PlaybackSupportFragment.this.mPlaybackItemClickedListener.onItemClicked(viewHolder, obj, viewHolder2, obj2);
            }
            if (PlaybackSupportFragment.this.mExternalItemClickedListener != null) {
                PlaybackSupportFragment.this.mExternalItemClickedListener.onItemClicked(viewHolder, obj, viewHolder2, obj2);
            }
        }
    };
    private final BaseOnItemViewSelectedListener mOnItemViewSelectedListener = new BaseOnItemViewSelectedListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.2
        @Override // android.support.v17.leanback.widget.BaseOnItemViewSelectedListener
        public void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
            if (PlaybackSupportFragment.this.mExternalItemSelectedListener != null) {
                PlaybackSupportFragment.this.mExternalItemSelectedListener.onItemSelected(viewHolder, obj, viewHolder2, obj2);
            }
        }
    };
    private final SetSelectionRunnable mSetSelectionRunnable = new SetSelectionRunnable();
    private int mBackgroundType = 1;
    private boolean mFadingEnabled = true;
    private int mFadingStatus = 0;
    private final Animator.AnimatorListener mFadeListener = new Animator.AnimatorListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.3
        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            PlaybackSupportFragment.this.enableVerticalGridAnimations(false);
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ItemBridgeAdapter.ViewHolder viewHolder;
            if (PlaybackSupportFragment.this.mBgAlpha > 0) {
                PlaybackSupportFragment.this.enableVerticalGridAnimations(true);
                PlaybackSupportFragment.this.startFadeTimer();
                if (PlaybackSupportFragment.this.mFadeCompleteListener != null) {
                    PlaybackSupportFragment.this.mFadeCompleteListener.onFadeInComplete();
                }
            } else {
                VerticalGridView verticalGridView = PlaybackSupportFragment.this.getVerticalGridView();
                if (verticalGridView != null && verticalGridView.getSelectedPosition() == 0 && (viewHolder = (ItemBridgeAdapter.ViewHolder) verticalGridView.findViewHolderForAdapterPosition(0)) != null && (viewHolder.getPresenter() instanceof PlaybackRowPresenter)) {
                    ((PlaybackRowPresenter) viewHolder.getPresenter()).onReappear((RowPresenter.ViewHolder) viewHolder.getViewHolder());
                }
                if (PlaybackSupportFragment.this.mFadeCompleteListener != null) {
                    PlaybackSupportFragment.this.mFadeCompleteListener.onFadeOutComplete();
                }
            }
            PlaybackSupportFragment.this.mFadingStatus = 0;
        }
    };
    private final Handler mHandler = new Handler() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == PlaybackSupportFragment.START_FADE_OUT && PlaybackSupportFragment.this.mFadingEnabled) {
                PlaybackSupportFragment.this.fade(false);
            }
        }
    };
    private final BaseGridView.OnTouchInterceptListener mOnTouchInterceptListener = new BaseGridView.OnTouchInterceptListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.5
        @Override // android.support.v17.leanback.widget.BaseGridView.OnTouchInterceptListener
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return PlaybackSupportFragment.this.onInterceptInputEvent(motionEvent);
        }
    };
    private final BaseGridView.OnKeyInterceptListener mOnKeyInterceptListener = new BaseGridView.OnKeyInterceptListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.6
        @Override // android.support.v17.leanback.widget.BaseGridView.OnKeyInterceptListener
        public boolean onInterceptKeyEvent(KeyEvent keyEvent) {
            return PlaybackSupportFragment.this.onInterceptInputEvent(keyEvent);
        }
    };
    private TimeInterpolator mLogDecelerateInterpolator = new LogDecelerateInterpolator(100, 0);
    private TimeInterpolator mLogAccelerateInterpolator = new LogAccelerateInterpolator(100, 0);
    private final ItemBridgeAdapter.AdapterListener mAdapterListener = new ItemBridgeAdapter.AdapterListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.12
        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onBind(ItemBridgeAdapter.ViewHolder viewHolder) {
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onAttachedToWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            if ((PlaybackSupportFragment.this.mFadingStatus == 0 && PlaybackSupportFragment.this.mBgAlpha == 0) || PlaybackSupportFragment.this.mFadingStatus == 2) {
                viewHolder.getViewHolder().view.setAlpha(0.0f);
            }
        }

        @Override // android.support.v17.leanback.widget.ItemBridgeAdapter.AdapterListener
        public void onDetachedFromWindow(ItemBridgeAdapter.ViewHolder viewHolder) {
            viewHolder.getViewHolder().view.setAlpha(1.0f);
            viewHolder.getViewHolder().view.setTranslationY(0.0f);
            viewHolder.getViewHolder().view.setAlpha(1.0f);
        }
    };

    public static class OnFadeCompleteListener {
        public void onFadeInComplete() {
        }

        public void onFadeOutComplete() {
        }
    }

    public void resetFocus() {
        ItemBridgeAdapter.ViewHolder viewHolder = (ItemBridgeAdapter.ViewHolder) getVerticalGridView().findViewHolderForAdapterPosition(0);
        if (viewHolder == null || !(viewHolder.getPresenter() instanceof PlaybackRowPresenter)) {
            return;
        }
        ((PlaybackRowPresenter) viewHolder.getPresenter()).onReappear((RowPresenter.ViewHolder) viewHolder.getViewHolder());
    }

    private class SetSelectionRunnable implements Runnable {
        int mPosition;
        boolean mSmooth;

        private SetSelectionRunnable() {
            this.mSmooth = true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PlaybackSupportFragment.this.mRowsSupportFragment == null) {
                return;
            }
            PlaybackSupportFragment.this.mRowsSupportFragment.setSelectedPosition(this.mPosition, this.mSmooth);
        }
    }

    public ObjectAdapter getAdapter() {
        return this.mAdapter;
    }

    VerticalGridView getVerticalGridView() {
        if (this.mRowsSupportFragment == null) {
            return null;
        }
        return this.mRowsSupportFragment.getVerticalGridView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBgAlpha(int i) {
        this.mBgAlpha = i;
        if (this.mRootView != null) {
            this.mRootView.getBackground().setAlpha(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enableVerticalGridAnimations(boolean z) {
        if (getVerticalGridView() != null) {
            getVerticalGridView().setAnimateChildLayout(z);
        }
    }

    public void setFadingEnabled(boolean z) {
        if (z != this.mFadingEnabled) {
            this.mFadingEnabled = z;
            if (this.mFadingEnabled) {
                if (isResumed() && this.mFadingStatus == 0 && !this.mHandler.hasMessages(START_FADE_OUT)) {
                    startFadeTimer();
                    return;
                }
                return;
            }
            this.mHandler.removeMessages(START_FADE_OUT);
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

    public final void setOnKeyInterceptListener(View.OnKeyListener onKeyListener) {
        this.mInputEventHandler = onKeyListener;
    }

    public void tickle() {
        if (this.mFadingEnabled && isResumed()) {
            if (this.mHandler.hasMessages(START_FADE_OUT)) {
                startFadeTimer();
            } else {
                fade(true);
            }
        }
    }

    public void fadeOut() {
        this.mHandler.removeMessages(START_FADE_OUT);
        fade(false);
    }

    private boolean areControlsHidden() {
        return this.mFadingStatus == 0 && this.mBgAlpha == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onInterceptInputEvent(InputEvent inputEvent) {
        boolean zOnKey;
        int keyCode;
        boolean zAreControlsHidden = areControlsHidden();
        if (inputEvent instanceof KeyEvent) {
            KeyEvent keyEvent = (KeyEvent) inputEvent;
            keyCode = keyEvent.getKeyCode();
            zOnKey = this.mInputEventHandler != null ? this.mInputEventHandler.onKey(getView(), keyCode, keyEvent) : false;
        } else {
            zOnKey = false;
            keyCode = 0;
        }
        if (keyCode != 4 && keyCode != 111) {
            switch (keyCode) {
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    if (zAreControlsHidden) {
                        zOnKey = true;
                    }
                    tickle();
                    return zOnKey;
                default:
                    if (!zOnKey) {
                        return zOnKey;
                    }
                    tickle();
                    return zOnKey;
            }
        }
        if (this.mFadingEnabled && !zAreControlsHidden) {
            this.mHandler.removeMessages(START_FADE_OUT);
            fade(false);
            return true;
        }
        if (!zOnKey) {
            return zOnKey;
        }
        tickle();
        return zOnKey;
    }

    @Override // android.support.v4.app.Fragment
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

    /* JADX INFO: Access modifiers changed from: private */
    public void startFadeTimer() {
        if (this.mHandler != null) {
            this.mHandler.removeMessages(START_FADE_OUT);
            this.mHandler.sendEmptyMessageDelayed(START_FADE_OUT, this.mShowTimeMs);
        }
    }

    private static ValueAnimator loadAnimator(Context context, int i) {
        ValueAnimator valueAnimator = (ValueAnimator) AnimatorInflater.loadAnimator(context, i);
        valueAnimator.setDuration(valueAnimator.getDuration() * 1);
        return valueAnimator;
    }

    private void loadBgAnimator() {
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PlaybackSupportFragment.this.setBgAlpha(((Integer) valueAnimator.getAnimatedValue()).intValue());
            }
        };
        Context context = getContext();
        this.mBgFadeInAnimator = loadAnimator(context, R.animator.lb_playback_bg_fade_in);
        this.mBgFadeInAnimator.addUpdateListener(animatorUpdateListener);
        this.mBgFadeInAnimator.addListener(this.mFadeListener);
        this.mBgFadeOutAnimator = loadAnimator(context, R.animator.lb_playback_bg_fade_out);
        this.mBgFadeOutAnimator.addUpdateListener(animatorUpdateListener);
        this.mBgFadeOutAnimator.addListener(this.mFadeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getControlRowView() {
        RecyclerView.ViewHolder viewHolderFindViewHolderForPosition;
        if (getVerticalGridView() == null || (viewHolderFindViewHolderForPosition = getVerticalGridView().findViewHolderForPosition(0)) == null) {
            return null;
        }
        return viewHolderFindViewHolderForPosition.itemView;
    }

    private void loadControlRowAnimator() {
        AnimatorListener animatorListener = new AnimatorListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.8
            @Override // android.support.v17.leanback.app.PlaybackSupportFragment.AnimatorListener
            void getViews(ArrayList<View> arrayList) {
                View controlRowView = PlaybackSupportFragment.this.getControlRowView();
                if (controlRowView != null) {
                    arrayList.add(controlRowView);
                }
            }
        };
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.9
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                View controlRowView = PlaybackSupportFragment.this.getControlRowView();
                if (controlRowView != null) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    controlRowView.setAlpha(fFloatValue);
                    controlRowView.setTranslationY(PlaybackSupportFragment.this.mAnimationTranslateY * (1.0f - fFloatValue));
                }
            }
        };
        Context context = getContext();
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
        final AnimatorListener animatorListener = new AnimatorListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.10
            @Override // android.support.v17.leanback.app.PlaybackSupportFragment.AnimatorListener
            void getViews(ArrayList<View> arrayList) {
                if (PlaybackSupportFragment.this.getVerticalGridView() == null) {
                    return;
                }
                int childCount = PlaybackSupportFragment.this.getVerticalGridView().getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = PlaybackSupportFragment.this.getVerticalGridView().getChildAt(i);
                    if (childAt != null) {
                        arrayList.add(childAt);
                    }
                }
            }
        };
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.PlaybackSupportFragment.11
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (PlaybackSupportFragment.this.getVerticalGridView() == null) {
                    return;
                }
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                for (View view : animatorListener.mViews) {
                    if (PlaybackSupportFragment.this.getVerticalGridView().getChildPosition(view) > 0) {
                        view.setAlpha(fFloatValue);
                        view.setTranslationY(PlaybackSupportFragment.this.mAnimationTranslateY * (1.0f - fFloatValue));
                    }
                }
            }
        };
        Context context = getContext();
        this.mOtherRowFadeInAnimator = loadAnimator(context, R.animator.lb_playback_controls_fade_in);
        this.mOtherRowFadeInAnimator.addListener(animatorListener);
        this.mOtherRowFadeInAnimator.addUpdateListener(animatorUpdateListener);
        this.mOtherRowFadeInAnimator.setInterpolator(this.mLogDecelerateInterpolator);
        this.mOtherRowFadeOutAnimator = loadAnimator(context, R.animator.lb_playback_controls_fade_out);
        this.mOtherRowFadeOutAnimator.addListener(animatorListener);
        this.mOtherRowFadeOutAnimator.addUpdateListener(animatorUpdateListener);
        this.mOtherRowFadeOutAnimator.setInterpolator(new AccelerateInterpolator());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fade(boolean z) {
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
                    } else {
                        this.mBgFadeOutAnimator.start();
                        this.mControlRowFadeOutAnimator.start();
                        this.mOtherRowFadeOutAnimator.start();
                    }
                } else if (z) {
                    this.mBgFadeOutAnimator.reverse();
                    this.mControlRowFadeOutAnimator.reverse();
                    this.mOtherRowFadeOutAnimator.reverse();
                } else {
                    this.mBgFadeInAnimator.reverse();
                    this.mControlRowFadeInAnimator.reverse();
                    this.mOtherRowFadeInAnimator.reverse();
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

    public void setSelectedPosition(int i) {
        setSelectedPosition(i, true);
    }

    public void setSelectedPosition(int i, boolean z) {
        this.mSetSelectionRunnable.mPosition = i;
        this.mSetSelectionRunnable.mSmooth = z;
        if (getView() == null || getView().getHandler() == null) {
            return;
        }
        getView().getHandler().post(this.mSetSelectionRunnable);
    }

    private void setupChildFragmentLayout() {
        setVerticalGridViewLayout(this.mRowsSupportFragment.getVerticalGridView());
    }

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

    @Override // android.support.v4.app.Fragment
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

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.lb_playback_fragment, viewGroup, false);
        this.mRowsSupportFragment = (RowsSupportFragment) getChildFragmentManager().findFragmentById(R.id.playback_controls_dock);
        if (this.mRowsSupportFragment == null) {
            this.mRowsSupportFragment = new RowsSupportFragment();
            getChildFragmentManager().beginTransaction().replace(R.id.playback_controls_dock, this.mRowsSupportFragment).commit();
        }
        if (this.mAdapter == null) {
            setAdapter(new ArrayObjectAdapter(new ClassPresenterSelector()));
        } else {
            this.mRowsSupportFragment.setAdapter(this.mAdapter);
        }
        this.mRowsSupportFragment.setOnItemViewSelectedListener(this.mOnItemViewSelectedListener);
        this.mRowsSupportFragment.setOnItemViewClickedListener(this.mOnItemViewClickedListener);
        this.mBgAlpha = 255;
        updateBackground();
        this.mRowsSupportFragment.setExternalAdapterListener(this.mAdapterListener);
        return this.mRootView;
    }

    public void setHostCallback(PlaybackGlueHost.HostCallback hostCallback) {
        this.mHostCallback = hostCallback;
    }

    @Override // android.support.v4.app.Fragment
    public void onStart() {
        super.onStart();
        setupChildFragmentLayout();
        this.mRowsSupportFragment.setAdapter(this.mAdapter);
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostStart();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onStop() {
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostStop();
        }
        super.onStop();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostPause();
        }
        super.onPause();
    }

    public void setOnItemViewSelectedListener(BaseOnItemViewSelectedListener baseOnItemViewSelectedListener) {
        this.mExternalItemSelectedListener = baseOnItemViewSelectedListener;
    }

    public void setOnItemViewClickedListener(BaseOnItemViewClickedListener baseOnItemViewClickedListener) {
        this.mExternalItemClickedListener = baseOnItemViewClickedListener;
    }

    public void setOnPlaybackItemViewClickedListener(BaseOnItemViewClickedListener baseOnItemViewClickedListener) {
        this.mPlaybackItemClickedListener = baseOnItemViewClickedListener;
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroyView() {
        this.mRootView = null;
        super.onDestroyView();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        if (this.mHostCallback != null) {
            this.mHostCallback.onHostDestroy();
        }
        super.onDestroy();
    }

    public void setPlaybackRow(Row row) {
        this.mRow = row;
        setupRow();
        setupPresenter();
    }

    public void setPlaybackRowPresenter(PlaybackRowPresenter playbackRowPresenter) {
        this.mPresenter = playbackRowPresenter;
        setupPresenter();
    }

    public void notifyPlaybackRowChanged() {
        if (this.mAdapter == null) {
            return;
        }
        this.mAdapter.notifyItemRangeChanged(0, 1);
    }

    public void setAdapter(ObjectAdapter objectAdapter) {
        this.mAdapter = objectAdapter;
        setupRow();
        setupPresenter();
        if (this.mRowsSupportFragment != null) {
            this.mRowsSupportFragment.setAdapter(objectAdapter);
        }
    }

    private void setupRow() {
        if (!(this.mAdapter instanceof ArrayObjectAdapter) || this.mRow == null) {
            return;
        }
        ArrayObjectAdapter arrayObjectAdapter = (ArrayObjectAdapter) this.mAdapter;
        if (arrayObjectAdapter.size() == 0) {
            arrayObjectAdapter.add(this.mRow);
        } else {
            arrayObjectAdapter.replace(0, this.mRow);
        }
    }

    private void setupPresenter() {
        if (this.mAdapter == null || this.mRow == null || this.mPresenter == null) {
            return;
        }
        PresenterSelector presenterSelector = this.mAdapter.getPresenterSelector();
        if (presenterSelector == null) {
            presenterSelector = new ClassPresenterSelector();
            this.mAdapter.setPresenterSelector(presenterSelector);
        }
        if (presenterSelector instanceof ClassPresenterSelector) {
            ((ClassPresenterSelector) presenterSelector).addClassPresenter(this.mRow.getClass(), this.mPresenter);
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
