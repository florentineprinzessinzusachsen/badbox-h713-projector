package android.support.v17.leanback.media;

import android.content.Context;
import android.support.annotation.CallSuper;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlaybackGlue {
    private final Context mContext;
    private PlaybackGlueHost mPlaybackGlueHost;

    public static abstract class PlayerCallback {
        public abstract void onReadyForPlayback();
    }

    public boolean isReadyForPlayback() {
        return true;
    }

    public void next() {
    }

    protected void onHostPause() {
    }

    protected void onHostResume() {
    }

    protected void onHostStart() {
    }

    protected void onHostStop() {
    }

    public void pause() {
    }

    public void play() {
    }

    public void previous() {
    }

    public void setPlayerCallback(PlayerCallback playerCallback) {
    }

    public PlaybackGlue(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    public final void setHost(PlaybackGlueHost playbackGlueHost) {
        if (this.mPlaybackGlueHost == playbackGlueHost) {
            return;
        }
        if (this.mPlaybackGlueHost != null) {
            this.mPlaybackGlueHost.attachToGlue(null);
        }
        this.mPlaybackGlueHost = playbackGlueHost;
        if (this.mPlaybackGlueHost != null) {
            this.mPlaybackGlueHost.attachToGlue(this);
        }
    }

    @CallSuper
    protected void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        this.mPlaybackGlueHost = playbackGlueHost;
        this.mPlaybackGlueHost.setHostCallback(new PlaybackGlueHost.HostCallback() { // from class: android.support.v17.leanback.media.PlaybackGlue.1
            @Override // android.support.v17.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostStart() {
                PlaybackGlue.this.onHostStart();
            }

            @Override // android.support.v17.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostStop() {
                PlaybackGlue.this.onHostStop();
            }

            @Override // android.support.v17.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostResume() {
                PlaybackGlue.this.onHostResume();
            }

            @Override // android.support.v17.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostPause() {
                PlaybackGlue.this.onHostPause();
            }

            @Override // android.support.v17.leanback.media.PlaybackGlueHost.HostCallback
            public void onHostDestroy() {
                if (PlaybackGlue.this.mPlaybackGlueHost != null) {
                    PlaybackGlue.this.mPlaybackGlueHost.attachToGlue(null);
                }
            }
        });
    }

    @CallSuper
    protected void onDetachedFromHost() {
        if (this.mPlaybackGlueHost != null) {
            this.mPlaybackGlueHost.setHostCallback(null);
            this.mPlaybackGlueHost = null;
        }
    }

    public PlaybackGlueHost getHost() {
        return this.mPlaybackGlueHost;
    }
}
