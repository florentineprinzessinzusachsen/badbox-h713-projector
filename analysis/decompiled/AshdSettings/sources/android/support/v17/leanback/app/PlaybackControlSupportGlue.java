package android.support.v17.leanback.app;

import android.content.Context;
import android.support.v17.leanback.media.PlaybackGlueHost;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.PlaybackRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class PlaybackControlSupportGlue extends PlaybackControlGlue {
    public static final int ACTION_CUSTOM_LEFT_FIRST = 1;
    public static final int ACTION_CUSTOM_RIGHT_FIRST = 4096;
    public static final int ACTION_FAST_FORWARD = 128;
    public static final int ACTION_PLAY_PAUSE = 64;
    public static final int ACTION_REWIND = 32;
    public static final int ACTION_SKIP_TO_NEXT = 256;
    public static final int ACTION_SKIP_TO_PREVIOUS = 16;
    public static final int PLAYBACK_SPEED_FAST_L0 = 10;
    public static final int PLAYBACK_SPEED_FAST_L1 = 11;
    public static final int PLAYBACK_SPEED_FAST_L2 = 12;
    public static final int PLAYBACK_SPEED_FAST_L3 = 13;
    public static final int PLAYBACK_SPEED_FAST_L4 = 14;
    public static final int PLAYBACK_SPEED_INVALID = -1;
    public static final int PLAYBACK_SPEED_NORMAL = 1;
    public static final int PLAYBACK_SPEED_PAUSED = 0;

    public PlaybackControlSupportGlue(Context context, int[] iArr) {
        this(context, null, iArr, iArr);
    }

    public PlaybackControlSupportGlue(Context context, int[] iArr, int[] iArr2) {
        this(context, null, iArr, iArr2);
    }

    public PlaybackControlSupportGlue(Context context, PlaybackOverlaySupportFragment playbackOverlaySupportFragment, int[] iArr) {
        this(context, playbackOverlaySupportFragment, iArr, iArr);
    }

    public PlaybackControlSupportGlue(Context context, PlaybackOverlaySupportFragment playbackOverlaySupportFragment, int[] iArr, int[] iArr2) {
        super(context, iArr, iArr2);
        setHost(playbackOverlaySupportFragment == null ? null : new PlaybackSupportGlueHostOld(playbackOverlaySupportFragment));
    }

    @Override // android.support.v17.leanback.app.PlaybackControlGlue, android.support.v17.leanback.media.PlaybackControlGlue, android.support.v17.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        super.onAttachedToHost(playbackGlueHost);
        if (playbackGlueHost instanceof PlaybackSupportGlueHostOld) {
            ((PlaybackSupportGlueHostOld) playbackGlueHost).mGlue = this;
        }
    }

    static final class PlaybackSupportGlueHostOld extends PlaybackGlueHost {
        OnActionClickedListener mActionClickedListener;
        final PlaybackOverlaySupportFragment mFragment;
        PlaybackControlSupportGlue mGlue;

        public PlaybackSupportGlueHostOld(PlaybackOverlaySupportFragment playbackOverlaySupportFragment) {
            this.mFragment = playbackOverlaySupportFragment;
            this.mFragment.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: android.support.v17.leanback.app.PlaybackControlSupportGlue.PlaybackSupportGlueHostOld.1
                @Override // android.support.v17.leanback.widget.BaseOnItemViewClickedListener
                public void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                    if ((obj instanceof Action) && (viewHolder2 instanceof PlaybackRowPresenter.ViewHolder) && PlaybackSupportGlueHostOld.this.mActionClickedListener != null) {
                        PlaybackSupportGlueHostOld.this.mActionClickedListener.onActionClicked((Action) obj);
                    } else {
                        if (PlaybackSupportGlueHostOld.this.mGlue == null || PlaybackSupportGlueHostOld.this.mGlue.getOnItemViewClickedListener() == null) {
                            return;
                        }
                        PlaybackSupportGlueHostOld.this.mGlue.getOnItemViewClickedListener().onItemClicked(viewHolder, obj, viewHolder2, row);
                    }
                }
            });
        }

        @Override // android.support.v17.leanback.media.PlaybackGlueHost
        public void setFadingEnabled(boolean z) {
            this.mFragment.setFadingEnabled(z);
        }

        @Override // android.support.v17.leanback.media.PlaybackGlueHost
        public void setOnKeyInterceptListener(final View.OnKeyListener onKeyListener) {
            this.mFragment.setEventHandler(new PlaybackControlGlue.InputEventHandler() { // from class: android.support.v17.leanback.app.PlaybackControlSupportGlue.PlaybackSupportGlueHostOld.2
                @Override // android.support.v17.leanback.app.PlaybackControlGlue.InputEventHandler
                public boolean handleInputEvent(InputEvent inputEvent) {
                    if (!(inputEvent instanceof KeyEvent)) {
                        return false;
                    }
                    KeyEvent keyEvent = (KeyEvent) inputEvent;
                    return onKeyListener.onKey(null, keyEvent.getKeyCode(), keyEvent);
                }
            });
        }

        @Override // android.support.v17.leanback.media.PlaybackGlueHost
        public void setOnActionClickedListener(OnActionClickedListener onActionClickedListener) {
            this.mActionClickedListener = onActionClickedListener;
        }

        @Override // android.support.v17.leanback.media.PlaybackGlueHost
        public void setHostCallback(PlaybackGlueHost.HostCallback hostCallback) {
            this.mFragment.setHostCallback(hostCallback);
        }

        @Override // android.support.v17.leanback.media.PlaybackGlueHost
        public void fadeOut() {
            this.mFragment.fadeOut();
        }

        @Override // android.support.v17.leanback.media.PlaybackGlueHost
        public void notifyPlaybackRowChanged() {
            this.mGlue.onRowChanged(this.mGlue.getControlsRow());
        }
    }
}
