package android.support.v17.leanback.app;

import android.os.Bundle;
import android.support.v17.leanback.R;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public class VideoFragment extends PlaybackFragment {
    static final int SURFACE_CREATED = 1;
    static final int SURFACE_NOT_CREATED = 0;
    SurfaceHolder.Callback mMediaPlaybackCallback;
    int mState = 0;
    SurfaceView mVideoSurface;

    @Override // android.support.v17.leanback.app.PlaybackFragment, android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle);
        this.mVideoSurface = (SurfaceView) LayoutInflater.from(FragmentUtil.getContext(this)).inflate(R.layout.lb_video_surface, viewGroup2, false);
        viewGroup2.addView(this.mVideoSurface, 0);
        this.mVideoSurface.getHolder().addCallback(new SurfaceHolder.Callback() { // from class: android.support.v17.leanback.app.VideoFragment.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (VideoFragment.this.mMediaPlaybackCallback != null) {
                    VideoFragment.this.mMediaPlaybackCallback.surfaceCreated(surfaceHolder);
                }
                VideoFragment.this.mState = 1;
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                if (VideoFragment.this.mMediaPlaybackCallback != null) {
                    VideoFragment.this.mMediaPlaybackCallback.surfaceChanged(surfaceHolder, i, i2, i3);
                }
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                if (VideoFragment.this.mMediaPlaybackCallback != null) {
                    VideoFragment.this.mMediaPlaybackCallback.surfaceDestroyed(surfaceHolder);
                }
                VideoFragment.this.mState = 0;
            }
        });
        setBackgroundType(2);
        return viewGroup2;
    }

    public void setSurfaceHolderCallback(SurfaceHolder.Callback callback) {
        this.mMediaPlaybackCallback = callback;
        if (callback == null || this.mState != 1) {
            return;
        }
        this.mMediaPlaybackCallback.surfaceCreated(this.mVideoSurface.getHolder());
    }

    public SurfaceView getSurfaceView() {
        return this.mVideoSurface;
    }

    @Override // android.support.v17.leanback.app.PlaybackFragment, android.app.Fragment
    public void onDestroyView() {
        this.mVideoSurface = null;
        this.mState = 0;
        super.onDestroyView();
    }
}
