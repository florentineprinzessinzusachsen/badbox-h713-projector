package android.support.v17.leanback.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.ControlButtonPresenterSelector;
import android.support.v17.leanback.widget.OnActionClickedListener;
import android.support.v17.leanback.widget.PlaybackControlsRow;
import android.support.v17.leanback.widget.PlaybackControlsRowPresenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;
import android.view.KeyEvent;
import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlaybackControlGlue extends PlaybackGlue implements OnActionClickedListener, View.OnKeyListener {
    public static final int ACTION_CUSTOM_LEFT_FIRST = 1;
    public static final int ACTION_CUSTOM_RIGHT_FIRST = 4096;
    public static final int ACTION_FAST_FORWARD = 128;
    public static final int ACTION_PLAY_PAUSE = 64;
    public static final int ACTION_REWIND = 32;
    public static final int ACTION_SKIP_TO_NEXT = 256;
    public static final int ACTION_SKIP_TO_PREVIOUS = 16;
    static final boolean DEBUG = false;
    static final int MSG_UPDATE_PLAYBACK_STATE = 100;
    private static final int NUMBER_OF_SEEK_SPEEDS = 5;
    public static final int PLAYBACK_SPEED_FAST_L0 = 10;
    public static final int PLAYBACK_SPEED_FAST_L1 = 11;
    public static final int PLAYBACK_SPEED_FAST_L2 = 12;
    public static final int PLAYBACK_SPEED_FAST_L3 = 13;
    public static final int PLAYBACK_SPEED_FAST_L4 = 14;
    public static final int PLAYBACK_SPEED_INVALID = -1;
    public static final int PLAYBACK_SPEED_NORMAL = 1;
    public static final int PLAYBACK_SPEED_PAUSED = 0;
    static final String TAG = "PlaybackControlGlue";
    private static final int UPDATE_PLAYBACK_STATE_DELAY_MS = 2000;
    static final Handler sHandler = new UpdatePlaybackStateHandler();
    private PlaybackControlsRow mControlsRow;
    private PlaybackControlsRowPresenter mControlsRowPresenter;
    private boolean mFadeWhenPlaying;
    private PlaybackControlsRow.FastForwardAction mFastForwardAction;
    private final int[] mFastForwardSpeeds;
    final WeakReference<PlaybackControlGlue> mGlueWeakReference;
    private PlaybackControlsRow.PlayPauseAction mPlayPauseAction;
    private int mPlaybackSpeed;
    private PlaybackControlsRow.RewindAction mRewindAction;
    private final int[] mRewindSpeeds;
    private PlaybackControlsRow.SkipNextAction mSkipNextAction;
    private PlaybackControlsRow.SkipPreviousAction mSkipPreviousAction;

    private static String getSpeedString(int i) {
        switch (i) {
            case -14:
                return "-PLAYBACK_SPEED_FAST_L4";
            case -13:
                return "-PLAYBACK_SPEED_FAST_L3";
            case -12:
                return "-PLAYBACK_SPEED_FAST_L2";
            case -11:
                return "-PLAYBACK_SPEED_FAST_L1";
            case -10:
                return "-PLAYBACK_SPEED_FAST_L0";
            default:
                switch (i) {
                    case -1:
                        return "PLAYBACK_SPEED_INVALID";
                    case 0:
                        return "PLAYBACK_SPEED_PAUSED";
                    case 1:
                        return "PLAYBACK_SPEED_NORMAL";
                    default:
                        switch (i) {
                            case 10:
                                return "PLAYBACK_SPEED_FAST_L0";
                            case 11:
                                return "PLAYBACK_SPEED_FAST_L1";
                            case 12:
                                return "PLAYBACK_SPEED_FAST_L2";
                            case 13:
                                return "PLAYBACK_SPEED_FAST_L3";
                            case 14:
                                return "PLAYBACK_SPEED_FAST_L4";
                            default:
                                return null;
                        }
                }
        }
    }

    public void enableProgressUpdating(boolean z) {
    }

    public abstract int getCurrentPosition();

    public abstract int getCurrentSpeedId();

    public abstract Drawable getMediaArt();

    public abstract int getMediaDuration();

    public abstract CharSequence getMediaSubtitle();

    public abstract CharSequence getMediaTitle();

    public abstract long getSupportedActions();

    public int getUpdatePeriod() {
        return 500;
    }

    public abstract boolean hasValidMedia();

    public abstract boolean isMediaPlaying();

    protected void onCreatePrimaryActions(SparseArrayObjectAdapter sparseArrayObjectAdapter) {
    }

    protected void onCreateSecondaryActions(ArrayObjectAdapter arrayObjectAdapter) {
    }

    public void play(int i) {
    }

    static class UpdatePlaybackStateHandler extends Handler {
        UpdatePlaybackStateHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            PlaybackControlGlue playbackControlGlue;
            if (message.what != 100 || (playbackControlGlue = (PlaybackControlGlue) ((WeakReference) message.obj).get()) == null) {
                return;
            }
            playbackControlGlue.updatePlaybackState();
        }
    }

    public PlaybackControlGlue(Context context, int[] iArr) {
        this(context, iArr, iArr);
    }

    public PlaybackControlGlue(Context context, int[] iArr, int[] iArr2) {
        super(context);
        this.mPlaybackSpeed = 1;
        this.mFadeWhenPlaying = true;
        this.mGlueWeakReference = new WeakReference<>(this);
        if (iArr.length == 0 || iArr.length > 5) {
            throw new IllegalStateException("invalid fastForwardSpeeds array size");
        }
        this.mFastForwardSpeeds = iArr;
        if (iArr2.length == 0 || iArr2.length > 5) {
            throw new IllegalStateException("invalid rewindSpeeds array size");
        }
        this.mRewindSpeeds = iArr2;
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        super.onAttachedToHost(playbackGlueHost);
        playbackGlueHost.setOnKeyInterceptListener(this);
        playbackGlueHost.setOnActionClickedListener(this);
        if (getControlsRow() == null || getControlsRowPresenter() == null) {
            onCreateControlsRowAndPresenter();
        }
        playbackGlueHost.setPlaybackRowPresenter(getControlsRowPresenter());
        playbackGlueHost.setPlaybackRow(getControlsRow());
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    protected void onHostStart() {
        enableProgressUpdating(true);
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    protected void onHostStop() {
        enableProgressUpdating(false);
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    protected void onDetachedFromHost() {
        enableProgressUpdating(false);
        super.onDetachedFromHost();
    }

    protected void onCreateControlsRowAndPresenter() {
        setControlsRow(new PlaybackControlsRow(this));
        setControlsRowPresenter(new PlaybackControlsRowPresenter(new AbstractDetailsDescriptionPresenter() { // from class: android.support.v17.leanback.media.PlaybackControlGlue.1
            @Override // android.support.v17.leanback.widget.AbstractDetailsDescriptionPresenter
            protected void onBindDescription(AbstractDetailsDescriptionPresenter.ViewHolder viewHolder, Object obj) {
                PlaybackControlGlue playbackControlGlue = (PlaybackControlGlue) obj;
                if (playbackControlGlue.hasValidMedia()) {
                    viewHolder.getTitle().setText(playbackControlGlue.getMediaTitle());
                    viewHolder.getSubtitle().setText(playbackControlGlue.getMediaSubtitle());
                } else {
                    viewHolder.getTitle().setText("");
                    viewHolder.getSubtitle().setText("");
                }
            }
        }) { // from class: android.support.v17.leanback.media.PlaybackControlGlue.2
            @Override // android.support.v17.leanback.widget.PlaybackControlsRowPresenter, android.support.v17.leanback.widget.RowPresenter
            protected void onBindRowViewHolder(RowPresenter.ViewHolder viewHolder, Object obj) {
                super.onBindRowViewHolder(viewHolder, obj);
                viewHolder.setOnKeyListener(PlaybackControlGlue.this);
            }

            @Override // android.support.v17.leanback.widget.PlaybackControlsRowPresenter, android.support.v17.leanback.widget.RowPresenter
            protected void onUnbindRowViewHolder(RowPresenter.ViewHolder viewHolder) {
                super.onUnbindRowViewHolder(viewHolder);
                viewHolder.setOnKeyListener(null);
            }
        });
    }

    public int[] getFastForwardSpeeds() {
        return this.mFastForwardSpeeds;
    }

    public int[] getRewindSpeeds() {
        return this.mRewindSpeeds;
    }

    public void setFadingEnabled(boolean z) {
        this.mFadeWhenPlaying = z;
        if (this.mFadeWhenPlaying || getHost() == null) {
            return;
        }
        getHost().setFadingEnabled(false);
    }

    public boolean isFadingEnabled() {
        return this.mFadeWhenPlaying;
    }

    public void setControlsRow(PlaybackControlsRow playbackControlsRow) {
        this.mControlsRow = playbackControlsRow;
        this.mControlsRow.setPrimaryActionsAdapter(createPrimaryActionsAdapter(new ControlButtonPresenterSelector()));
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(new ControlButtonPresenterSelector());
        onCreateSecondaryActions(arrayObjectAdapter);
        getControlsRow().setSecondaryActionsAdapter(arrayObjectAdapter);
        updateControlsRow();
    }

    protected SparseArrayObjectAdapter createPrimaryActionsAdapter(PresenterSelector presenterSelector) {
        SparseArrayObjectAdapter sparseArrayObjectAdapter = new SparseArrayObjectAdapter(presenterSelector);
        onCreatePrimaryActions(sparseArrayObjectAdapter);
        return sparseArrayObjectAdapter;
    }

    public void setControlsRowPresenter(PlaybackControlsRowPresenter playbackControlsRowPresenter) {
        this.mControlsRowPresenter = playbackControlsRowPresenter;
    }

    public PlaybackControlsRow getControlsRow() {
        return this.mControlsRow;
    }

    public PlaybackControlsRowPresenter getControlsRowPresenter() {
        return this.mControlsRowPresenter;
    }

    public void updateProgress() {
        int currentPosition = getCurrentPosition();
        if (this.mControlsRow != null) {
            this.mControlsRow.setCurrentTime(currentPosition);
        }
    }

    public void onActionClicked(Action action) {
        dispatchAction(action, null);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i != 4 && i != 111) {
            switch (i) {
                case 19:
                case 20:
                case 21:
                case 22:
                    break;
                default:
                    SparseArrayObjectAdapter sparseArrayObjectAdapter = (SparseArrayObjectAdapter) this.mControlsRow.getPrimaryActionsAdapter();
                    Action actionForKeyCode = this.mControlsRow.getActionForKeyCode(sparseArrayObjectAdapter, i);
                    if (actionForKeyCode == null || !(actionForKeyCode == sparseArrayObjectAdapter.lookup(64) || actionForKeyCode == sparseArrayObjectAdapter.lookup(32) || actionForKeyCode == sparseArrayObjectAdapter.lookup(128) || actionForKeyCode == sparseArrayObjectAdapter.lookup(16) || actionForKeyCode == sparseArrayObjectAdapter.lookup(256))) {
                        return false;
                    }
                    if (keyEvent.getAction() == 0) {
                        dispatchAction(actionForKeyCode, keyEvent);
                    }
                    return true;
            }
        }
        if (!(this.mPlaybackSpeed >= 10 || this.mPlaybackSpeed <= -10)) {
            return false;
        }
        this.mPlaybackSpeed = 1;
        play(this.mPlaybackSpeed);
        updatePlaybackStatusAfterUserAction();
        return i == 4 || i == 111;
    }

    boolean dispatchAction(Action action, KeyEvent keyEvent) {
        if (action == this.mPlayPauseAction) {
            boolean z = keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 126;
            if ((keyEvent == null || keyEvent.getKeyCode() == 85 || keyEvent.getKeyCode() == 127) && (!z ? this.mPlaybackSpeed != 0 : this.mPlaybackSpeed == 1)) {
                this.mPlaybackSpeed = 0;
                pause();
            } else if (z && this.mPlaybackSpeed != 1) {
                this.mPlaybackSpeed = 1;
                play(this.mPlaybackSpeed);
            }
            updatePlaybackStatusAfterUserAction();
            return true;
        }
        if (action == this.mSkipNextAction) {
            next();
            return true;
        }
        if (action == this.mSkipPreviousAction) {
            previous();
            return true;
        }
        if (action == this.mFastForwardAction) {
            if (this.mPlaybackSpeed >= getMaxForwardSpeedId()) {
                return true;
            }
            switch (this.mPlaybackSpeed) {
                case 10:
                case 11:
                case 12:
                case 13:
                    this.mPlaybackSpeed++;
                    break;
                default:
                    this.mPlaybackSpeed = 10;
                    break;
            }
            play(this.mPlaybackSpeed);
            updatePlaybackStatusAfterUserAction();
            return true;
        }
        if (action != this.mRewindAction) {
            return false;
        }
        if (this.mPlaybackSpeed <= (-getMaxRewindSpeedId())) {
            return true;
        }
        switch (this.mPlaybackSpeed) {
            case -13:
            case -12:
            case -11:
            case -10:
                this.mPlaybackSpeed--;
                break;
            default:
                this.mPlaybackSpeed = -10;
                break;
        }
        play(this.mPlaybackSpeed);
        updatePlaybackStatusAfterUserAction();
        return true;
    }

    private int getMaxForwardSpeedId() {
        return (this.mFastForwardSpeeds.length - 1) + 10;
    }

    private int getMaxRewindSpeedId() {
        return (this.mRewindSpeeds.length - 1) + 10;
    }

    private void updateControlsRow() {
        updateRowMetadata();
        updateControlButtons();
        sHandler.removeMessages(100, this.mGlueWeakReference);
        updatePlaybackState();
    }

    private void updatePlaybackStatusAfterUserAction() {
        updatePlaybackState(this.mPlaybackSpeed);
        sHandler.removeMessages(100, this.mGlueWeakReference);
        sHandler.sendMessageDelayed(sHandler.obtainMessage(100, this.mGlueWeakReference), 2000L);
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    public final void play() {
        play(1);
    }

    private void updateRowMetadata() {
        if (this.mControlsRow == null) {
            return;
        }
        if (!hasValidMedia()) {
            this.mControlsRow.setImageDrawable(null);
            this.mControlsRow.setTotalTime(0);
            this.mControlsRow.setCurrentTime(0);
        } else {
            this.mControlsRow.setImageDrawable(getMediaArt());
            this.mControlsRow.setTotalTime(getMediaDuration());
            this.mControlsRow.setCurrentTime(getCurrentPosition());
        }
        if (getHost() != null) {
            getHost().notifyPlaybackRowChanged();
        }
    }

    void updatePlaybackState() {
        if (hasValidMedia()) {
            this.mPlaybackSpeed = getCurrentSpeedId();
            updatePlaybackState(this.mPlaybackSpeed);
        }
    }

    void updateControlButtons() {
        SparseArrayObjectAdapter sparseArrayObjectAdapter = (SparseArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter();
        long supportedActions = getSupportedActions();
        long j = 16 & supportedActions;
        if (j != 0 && this.mSkipPreviousAction == null) {
            this.mSkipPreviousAction = new PlaybackControlsRow.SkipPreviousAction(getContext());
            sparseArrayObjectAdapter.set(16, this.mSkipPreviousAction);
        } else if (j == 0 && this.mSkipPreviousAction != null) {
            sparseArrayObjectAdapter.clear(16);
            this.mSkipPreviousAction = null;
        }
        long j2 = 32 & supportedActions;
        if (j2 != 0 && this.mRewindAction == null) {
            this.mRewindAction = new PlaybackControlsRow.RewindAction(getContext(), this.mRewindSpeeds.length);
            sparseArrayObjectAdapter.set(32, this.mRewindAction);
        } else if (j2 == 0 && this.mRewindAction != null) {
            sparseArrayObjectAdapter.clear(32);
            this.mRewindAction = null;
        }
        long j3 = 64 & supportedActions;
        if (j3 != 0 && this.mPlayPauseAction == null) {
            this.mPlayPauseAction = new PlaybackControlsRow.PlayPauseAction(getContext());
            sparseArrayObjectAdapter.set(64, this.mPlayPauseAction);
        } else if (j3 == 0 && this.mPlayPauseAction != null) {
            sparseArrayObjectAdapter.clear(64);
            this.mPlayPauseAction = null;
        }
        long j4 = 128 & supportedActions;
        if (j4 != 0 && this.mFastForwardAction == null) {
            this.mFastForwardAction = new PlaybackControlsRow.FastForwardAction(getContext(), this.mFastForwardSpeeds.length);
            sparseArrayObjectAdapter.set(128, this.mFastForwardAction);
        } else if (j4 == 0 && this.mFastForwardAction != null) {
            sparseArrayObjectAdapter.clear(128);
            this.mFastForwardAction = null;
        }
        long j5 = supportedActions & 256;
        if (j5 != 0 && this.mSkipNextAction == null) {
            this.mSkipNextAction = new PlaybackControlsRow.SkipNextAction(getContext());
            sparseArrayObjectAdapter.set(256, this.mSkipNextAction);
        } else {
            if (j5 != 0 || this.mSkipNextAction == null) {
                return;
            }
            sparseArrayObjectAdapter.clear(256);
            this.mSkipNextAction = null;
        }
    }

    private void updatePlaybackState(int i) {
        if (this.mControlsRow == null) {
            return;
        }
        SparseArrayObjectAdapter sparseArrayObjectAdapter = (SparseArrayObjectAdapter) getControlsRow().getPrimaryActionsAdapter();
        if (this.mFastForwardAction != null) {
            int i2 = i >= 10 ? (i - 10) + 1 : 0;
            if (this.mFastForwardAction.getIndex() != i2) {
                this.mFastForwardAction.setIndex(i2);
                notifyItemChanged(sparseArrayObjectAdapter, this.mFastForwardAction);
            }
        }
        if (this.mRewindAction != null) {
            int i3 = i <= -10 ? ((-i) - 10) + 1 : 0;
            if (this.mRewindAction.getIndex() != i3) {
                this.mRewindAction.setIndex(i3);
                notifyItemChanged(sparseArrayObjectAdapter, this.mRewindAction);
            }
        }
        if (i == 0) {
            updateProgress();
            enableProgressUpdating(false);
        } else {
            enableProgressUpdating(true);
        }
        if (this.mFadeWhenPlaying && getHost() != null) {
            getHost().setFadingEnabled(i == 1);
        }
        if (this.mPlayPauseAction != null) {
            int i4 = i == 0 ? PlaybackControlsRow.PlayPauseAction.PLAY : PlaybackControlsRow.PlayPauseAction.PAUSE;
            if (this.mPlayPauseAction.getIndex() != i4) {
                this.mPlayPauseAction.setIndex(i4);
                notifyItemChanged(sparseArrayObjectAdapter, this.mPlayPauseAction);
            }
        }
    }

    private static void notifyItemChanged(SparseArrayObjectAdapter sparseArrayObjectAdapter, Object obj) {
        int iIndexOf = sparseArrayObjectAdapter.indexOf(obj);
        if (iIndexOf >= 0) {
            sparseArrayObjectAdapter.notifyArrayItemRangeChanged(iIndexOf, 1);
        }
    }

    protected void onStateChanged() {
        if (hasValidMedia()) {
            if (sHandler.hasMessages(100, this.mGlueWeakReference)) {
                sHandler.removeMessages(100, this.mGlueWeakReference);
                if (getCurrentSpeedId() != this.mPlaybackSpeed) {
                    sHandler.sendMessageDelayed(sHandler.obtainMessage(100, this.mGlueWeakReference), 2000L);
                    return;
                } else {
                    updatePlaybackState();
                    return;
                }
            }
            updatePlaybackState();
        }
    }

    protected void onMetadataChanged() {
        updateRowMetadata();
    }
}
