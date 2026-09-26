package android.support.v17.leanback.app;

import android.animation.PropertyValuesHolder;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v17.leanback.R;
import android.support.v17.leanback.graphics.FitWidthBitmapDrawable;
import android.support.v17.leanback.media.PlaybackGlue;
import android.support.v17.leanback.media.PlaybackGlueHost;
import android.support.v17.leanback.widget.DetailsParallaxDrawable;
import android.support.v17.leanback.widget.ParallaxTarget;
import android.support.v4.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class DetailsSupportFragmentBackgroundController {
    private boolean mCanUseHost = false;
    private Bitmap mCoverBitmap;
    private final DetailsSupportFragment mFragment;
    private DetailsParallaxDrawable mParallaxDrawable;
    private int mParallaxDrawableMaxOffset;
    private PlaybackGlue mPlaybackGlue;
    private int mSolidColor;
    private DetailsBackgroundVideoHelper mVideoHelper;

    public DetailsSupportFragmentBackgroundController(DetailsSupportFragment detailsSupportFragment) {
        if (detailsSupportFragment.mDetailsBackgroundController != null) {
            throw new IllegalStateException("Each DetailsSupportFragment is allowed to initialize DetailsSupportFragmentBackgroundController once");
        }
        detailsSupportFragment.mDetailsBackgroundController = this;
        this.mFragment = detailsSupportFragment;
    }

    public void enableParallax() {
        int dimensionPixelSize = this.mParallaxDrawableMaxOffset;
        if (dimensionPixelSize == 0) {
            dimensionPixelSize = this.mFragment.getContext().getResources().getDimensionPixelSize(R.dimen.lb_details_cover_drawable_parallax_movement);
        }
        FitWidthBitmapDrawable fitWidthBitmapDrawable = new FitWidthBitmapDrawable();
        enableParallax(fitWidthBitmapDrawable, new ColorDrawable(), new ParallaxTarget.PropertyValuesHolderTarget(fitWidthBitmapDrawable, PropertyValuesHolder.ofInt(FitWidthBitmapDrawable.PROPERTY_VERTICAL_OFFSET, 0, -dimensionPixelSize)));
    }

    public void enableParallax(@NonNull Drawable drawable, @NonNull Drawable drawable2, @Nullable ParallaxTarget.PropertyValuesHolderTarget propertyValuesHolderTarget) {
        if (this.mParallaxDrawable != null) {
            return;
        }
        if (this.mCoverBitmap != null && (drawable instanceof FitWidthBitmapDrawable)) {
            ((FitWidthBitmapDrawable) drawable).setBitmap(this.mCoverBitmap);
        }
        if (this.mSolidColor != 0 && (drawable2 instanceof ColorDrawable)) {
            ((ColorDrawable) drawable2).setColor(this.mSolidColor);
        }
        if (this.mPlaybackGlue != null) {
            throw new IllegalStateException("enableParallaxDrawable must be called before enableVideoPlayback");
        }
        this.mParallaxDrawable = new DetailsParallaxDrawable(this.mFragment.getContext(), this.mFragment.getParallax(), drawable, drawable2, propertyValuesHolderTarget);
        this.mFragment.setBackgroundDrawable(this.mParallaxDrawable);
    }

    public void setupVideoPlayback(@NonNull PlaybackGlue playbackGlue) {
        if (this.mPlaybackGlue == playbackGlue) {
            return;
        }
        this.mPlaybackGlue = playbackGlue;
        this.mVideoHelper = new DetailsBackgroundVideoHelper(this.mPlaybackGlue, this.mFragment.getParallax(), this.mParallaxDrawable.getCoverDrawable());
        if (this.mCanUseHost) {
            this.mPlaybackGlue.setHost(onCreateGlueHost());
        }
    }

    void onStart() {
        if (!this.mCanUseHost) {
            this.mCanUseHost = true;
            if (this.mPlaybackGlue != null) {
                this.mPlaybackGlue.setHost(onCreateGlueHost());
            }
        }
        if (this.mPlaybackGlue == null || !this.mPlaybackGlue.isReadyForPlayback()) {
            return;
        }
        this.mPlaybackGlue.play();
    }

    void onStop() {
        if (this.mPlaybackGlue != null) {
            this.mPlaybackGlue.pause();
        }
    }

    boolean disableVideoParallax() {
        if (this.mVideoHelper == null) {
            return false;
        }
        this.mVideoHelper.stopParallax();
        return this.mVideoHelper.isVideoVisible();
    }

    public final Drawable getCoverDrawable() {
        if (this.mParallaxDrawable == null) {
            return null;
        }
        return this.mParallaxDrawable.getCoverDrawable();
    }

    public final Drawable getBottomDrawable() {
        if (this.mParallaxDrawable == null) {
            return null;
        }
        return this.mParallaxDrawable.getBottomDrawable();
    }

    public Fragment onCreateVideoSupportFragment() {
        return new VideoSupportFragment();
    }

    public PlaybackGlueHost onCreateGlueHost() {
        return new VideoSupportFragmentGlueHost((VideoSupportFragment) findOrCreateVideoSupportFragment());
    }

    public final Fragment findOrCreateVideoSupportFragment() {
        return this.mFragment.findOrCreateVideoSupportFragment();
    }

    public final void setCoverBitmap(Bitmap bitmap) {
        this.mCoverBitmap = bitmap;
        Drawable coverDrawable = getCoverDrawable();
        if (coverDrawable instanceof FitWidthBitmapDrawable) {
            ((FitWidthBitmapDrawable) coverDrawable).setBitmap(this.mCoverBitmap);
        }
    }

    public final Bitmap getCoverBitmap() {
        return this.mCoverBitmap;
    }

    @ColorInt
    public final int getSolidColor() {
        return this.mSolidColor;
    }

    public final void setSolidColor(@ColorInt int i) {
        this.mSolidColor = i;
        Drawable bottomDrawable = getBottomDrawable();
        if (bottomDrawable instanceof ColorDrawable) {
            ((ColorDrawable) bottomDrawable).setColor(i);
        }
    }

    public final void setParallaxDrawableMaxOffset(int i) {
        if (this.mParallaxDrawable != null) {
            throw new IllegalStateException("enableParallax already called");
        }
        this.mParallaxDrawableMaxOffset = i;
    }

    public final int getParallaxDrawableMaxOffset() {
        return this.mParallaxDrawableMaxOffset;
    }
}
