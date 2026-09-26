package android.support.v17.leanback.app;

import android.support.v17.leanback.media.SurfaceHolderGlueHost;
import android.view.SurfaceHolder;

/* JADX INFO: loaded from: classes.dex */
public class VideoFragmentGlueHost extends PlaybackFragmentGlueHost implements SurfaceHolderGlueHost {
    private final VideoFragment mFragment;

    public VideoFragmentGlueHost(VideoFragment videoFragment) {
        super(videoFragment);
        this.mFragment = videoFragment;
    }

    @Override // android.support.v17.leanback.media.SurfaceHolderGlueHost
    public void setSurfaceHolderCallback(SurfaceHolder.Callback callback) {
        this.mFragment.setSurfaceHolderCallback(callback);
    }
}
