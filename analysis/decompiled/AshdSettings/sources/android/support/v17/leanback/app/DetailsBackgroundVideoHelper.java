package android.support.v17.leanback.app;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.media.PlaybackGlue;
import android.support.v17.leanback.widget.DetailsParallax;
import android.support.v17.leanback.widget.Parallax;
import android.support.v17.leanback.widget.ParallaxEffect;
import android.support.v17.leanback.widget.ParallaxTarget;

/* JADX INFO: loaded from: classes.dex */
final class DetailsBackgroundVideoHelper {
    private static final long BACKGROUND_CROSS_FADE_DURATION = 500;
    private static final long CROSSFADE_DELAY = 1000;
    static final int INITIAL = 0;
    static final int NO_VIDEO = 2;
    static final int PLAY_VIDEO = 1;
    private ValueAnimator mBackgroundAnimator;
    private Drawable mBackgroundDrawable;
    private int mCurrentState = 0;
    private final DetailsParallax mDetailsParallax;
    private ParallaxEffect mParallaxEffect;
    private PlaybackGlue mPlaybackGlue;

    DetailsBackgroundVideoHelper(PlaybackGlue playbackGlue, DetailsParallax detailsParallax, Drawable drawable) {
        this.mPlaybackGlue = playbackGlue;
        this.mDetailsParallax = detailsParallax;
        this.mBackgroundDrawable = drawable;
        startParallax();
    }

    void startParallax() {
        if (this.mParallaxEffect != null) {
            return;
        }
        Parallax.IntProperty overviewRowTop = this.mDetailsParallax.getOverviewRowTop();
        this.mParallaxEffect = this.mDetailsParallax.addEffect(overviewRowTop.atFraction(1.0f), overviewRowTop.atFraction(0.0f)).target(new ParallaxTarget() { // from class: android.support.v17.leanback.app.DetailsBackgroundVideoHelper.1
            @Override // android.support.v17.leanback.widget.ParallaxTarget
            public void update(float f) {
                if (f == 1.0f) {
                    DetailsBackgroundVideoHelper.this.updateState(2);
                } else {
                    DetailsBackgroundVideoHelper.this.updateState(1);
                }
            }
        });
    }

    void stopParallax() {
        this.mDetailsParallax.removeEffect(this.mParallaxEffect);
    }

    boolean isVideoVisible() {
        return this.mCurrentState == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateState(int i) {
        if (i == this.mCurrentState) {
        }
        this.mCurrentState = i;
        switch (i) {
            case 1:
                if (this.mPlaybackGlue.isReadyForPlayback()) {
                    internalStartPlayback();
                } else {
                    this.mPlaybackGlue.setPlayerCallback(new PlaybackControlStateCallback());
                }
                break;
            case 2:
                crossFadeBackgroundToVideo(false);
                this.mPlaybackGlue.setPlayerCallback(null);
                this.mPlaybackGlue.pause();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalStartPlayback() {
        this.mPlaybackGlue.play();
        this.mDetailsParallax.getRecyclerView().postDelayed(new Runnable() { // from class: android.support.v17.leanback.app.DetailsBackgroundVideoHelper.2
            @Override // java.lang.Runnable
            public void run() {
                DetailsBackgroundVideoHelper.this.crossFadeBackgroundToVideo(true);
            }
        }, CROSSFADE_DELAY);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void crossFadeBackgroundToVideo(boolean z) {
        if (this.mBackgroundAnimator != null) {
            this.mBackgroundAnimator.cancel();
            this.mBackgroundAnimator = null;
        }
        float f = z ? 1.0f : 0.0f;
        float f2 = z ? 0.0f : 1.0f;
        if (this.mBackgroundDrawable == null) {
            return;
        }
        this.mBackgroundAnimator = ValueAnimator.ofFloat(f, f2);
        this.mBackgroundAnimator.setDuration(BACKGROUND_CROSS_FADE_DURATION);
        this.mBackgroundAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: android.support.v17.leanback.app.DetailsBackgroundVideoHelper.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                DetailsBackgroundVideoHelper.this.mBackgroundDrawable.setAlpha((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f));
            }
        });
        this.mBackgroundAnimator.addListener(new Animator.AnimatorListener() { // from class: android.support.v17.leanback.app.DetailsBackgroundVideoHelper.4
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                DetailsBackgroundVideoHelper.this.mBackgroundAnimator = null;
            }
        });
        this.mBackgroundAnimator.start();
    }

    private class PlaybackControlStateCallback extends PlaybackGlue.PlayerCallback {
        private PlaybackControlStateCallback() {
        }

        @Override // android.support.v17.leanback.media.PlaybackGlue.PlayerCallback
        public void onReadyForPlayback() {
            DetailsBackgroundVideoHelper.this.internalStartPlayback();
        }
    }
}
