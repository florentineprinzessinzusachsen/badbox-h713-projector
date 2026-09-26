package android.support.v17.leanback.media;

import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.PlaybackRowPresenter;
import android.support.v17.leanback.widget.Row;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlaybackGlueHost {
    PlaybackGlue mGlue;

    public static abstract class HostCallback {
        public void onHostDestroy() {
        }

        public void onHostPause() {
        }

        public void onHostResume() {
        }

        public void onHostStart() {
        }

        public void onHostStop() {
        }
    }

    public void fadeOut() {
    }

    public void notifyPlaybackRowChanged() {
    }

    public void setFadingEnabled(boolean z) {
    }

    public void setHostCallback(HostCallback hostCallback) {
    }

    public void setOnActionClickedListener(OnActionClickedListener onActionClickedListener) {
    }

    public void setOnKeyInterceptListener(View.OnKeyListener onKeyListener) {
    }

    public void setPlaybackRow(Row row) {
    }

    public void setPlaybackRowPresenter(PlaybackRowPresenter playbackRowPresenter) {
    }

    final void attachToGlue(PlaybackGlue playbackGlue) {
        if (this.mGlue != null) {
            this.mGlue.onDetachedFromHost();
        }
        this.mGlue = playbackGlue;
        if (this.mGlue != null) {
            this.mGlue.onAttachedToHost(this);
        }
    }
}
