package android.support.v17.leanback.app;

import android.content.Context;
import android.support.v17.leanback.media.PlaybackGlueHost;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.PlaybackControlsRow;
import android.support.v17.leanback.widget.PlaybackControlsRowPresenter;
import android.support.v17.leanback.widget.PlaybackRowPresenter;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class PlaybackControlGlue extends android.support.v17.leanback.media.PlaybackControlGlue {
    OnItemViewClickedListener mExternalOnItemViewClickedListener;

    @Deprecated
    public interface InputEventHandler {
        boolean handleInputEvent(InputEvent inputEvent);
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    protected void onCreateControlsRowAndPresenter() {
    }

    protected void onRowChanged(PlaybackControlsRow playbackControlsRow) {
    }

    @Deprecated
    protected void pausePlayback() {
    }

    @Deprecated
    protected void skipToNext() {
    }

    @Deprecated
    protected void skipToPrevious() {
    }

    @Deprecated
    protected void startPlayback(int i) {
    }

    public PlaybackControlGlue(Context context, int[] iArr) {
        super(context, iArr, iArr);
    }

    public PlaybackControlGlue(Context context, int[] iArr, int[] iArr2) {
        super(context, iArr, iArr2);
    }

    public PlaybackControlGlue(Context context, PlaybackOverlayFragment playbackOverlayFragment, int[] iArr) {
        this(context, playbackOverlayFragment, iArr, iArr);
    }

    public PlaybackControlGlue(Context context, PlaybackOverlayFragment playbackOverlayFragment, int[] iArr, int[] iArr2) {
        super(context, iArr, iArr2);
        setHost(playbackOverlayFragment == null ? (PlaybackGlueHost) null : new PlaybackGlueHostOld(playbackOverlayFragment));
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue, android.support.v17.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        super.onAttachedToHost(playbackGlueHost);
        if (playbackGlueHost instanceof PlaybackGlueHostOld) {
            ((PlaybackGlueHostOld) playbackGlueHost).mGlue = this;
        }
    }

    public PlaybackOverlayFragment getFragment() {
        if (getHost() instanceof PlaybackGlueHostOld) {
            return ((PlaybackGlueHostOld) getHost()).mFragment;
        }
        return null;
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    public final void next() {
        skipToNext();
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    public final void previous() {
        skipToPrevious();
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public final void play(int i) {
        startPlayback(i);
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    public final void pause() {
        pausePlayback();
    }

    @Deprecated
    public void setOnItemViewClickedListener(OnItemViewClickedListener onItemViewClickedListener) {
        this.mExternalOnItemViewClickedListener = onItemViewClickedListener;
    }

    @Deprecated
    public OnItemViewClickedListener getOnItemViewClickedListener() {
        return this.mExternalOnItemViewClickedListener;
    }

    public PlaybackControlsRowPresenter createControlsRowAndPresenter() {
        super.onCreateControlsRowAndPresenter();
        return getControlsRowPresenter();
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    protected SparseArrayObjectAdapter createPrimaryActionsAdapter(PresenterSelector presenterSelector) {
        return super.createPrimaryActionsAdapter(presenterSelector);
    }

    static final class PlaybackGlueHostOld extends PlaybackGlueHost {
        OnActionClickedListener mActionClickedListener;
        final PlaybackOverlayFragment mFragment;
        PlaybackControlGlue mGlue;

        public PlaybackGlueHostOld(PlaybackOverlayFragment playbackOverlayFragment) {
            this.mFragment = playbackOverlayFragment;
            this.mFragment.setOnItemViewClickedListener(new OnItemViewClickedListener() { // from class: android.support.v17.leanback.app.PlaybackControlGlue.PlaybackGlueHostOld.1
                @Override // android.support.v17.leanback.widget.BaseOnItemViewClickedListener
                public void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
                    if ((obj instanceof Action) && (viewHolder2 instanceof PlaybackRowPresenter.ViewHolder) && PlaybackGlueHostOld.this.mActionClickedListener != null) {
                        PlaybackGlueHostOld.this.mActionClickedListener.onActionClicked((Action) obj);
                    } else {
                        if (PlaybackGlueHostOld.this.mGlue == null || PlaybackGlueHostOld.this.mGlue.getOnItemViewClickedListener() == null) {
                            return;
                        }
                        PlaybackGlueHostOld.this.mGlue.getOnItemViewClickedListener().onItemClicked(viewHolder, obj, viewHolder2, row);
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
            this.mFragment.setEventHandler(new InputEventHandler() { // from class: android.support.v17.leanback.app.PlaybackControlGlue.PlaybackGlueHostOld.2
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
