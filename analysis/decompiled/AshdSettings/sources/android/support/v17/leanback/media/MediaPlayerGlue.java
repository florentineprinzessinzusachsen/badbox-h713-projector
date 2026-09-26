package android.support.v17.leanback.media;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.PlaybackControlsRow;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.View;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlayerGlue extends PlaybackControlGlue implements OnItemViewSelectedListener {
    public static final int FAST_FORWARD_REWIND_REPEAT_DELAY = 200;
    public static final int FAST_FORWARD_REWIND_STEP = 10000;
    public static final int NO_REPEAT = 0;
    public static final int REPEAT_ALL = 2;
    public static final int REPEAT_ONE = 1;
    private static final String TAG = "MediaPlayerGlue";
    private String mArtist;
    private Drawable mCover;
    private Handler mHandler;
    private boolean mInitialized;
    private long mLastKeyDownEvent;
    private String mMediaSourcePath;
    private Uri mMediaSourceUri;
    private MediaPlayer.OnCompletionListener mOnCompletionListener;
    MediaPlayer mPlayer;
    private PlaybackGlue.PlayerCallback mPlayerCallback;
    private final PlaybackControlsRow.RepeatAction mRepeatAction;
    private Runnable mRunnable;
    private Action mSelectedAction;
    protected final PlaybackControlsRow.ThumbsDownAction mThumbsDownAction;
    protected final PlaybackControlsRow.ThumbsUpAction mThumbsUpAction;
    private String mTitle;

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public long getSupportedActions() {
        return 224L;
    }

    public void setCover(Drawable drawable) {
        this.mCover = drawable;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setVideoUrl(String str) {
        setMediaSource(str);
        onMetadataChanged();
    }

    public MediaPlayerGlue(Context context) {
        this(context, new int[]{1}, new int[]{1});
    }

    public MediaPlayerGlue(Context context, int[] iArr, int[] iArr2) {
        super(context, iArr, iArr2);
        this.mPlayer = new MediaPlayer();
        this.mHandler = new Handler();
        this.mInitialized = false;
        this.mLastKeyDownEvent = 0L;
        this.mMediaSourceUri = null;
        this.mMediaSourcePath = null;
        this.mRepeatAction = new PlaybackControlsRow.RepeatAction(getContext());
        this.mThumbsDownAction = new PlaybackControlsRow.ThumbsDownAction(getContext());
        this.mThumbsUpAction = new PlaybackControlsRow.ThumbsUpAction(getContext());
        this.mThumbsDownAction.setIndex(PlaybackControlsRow.ThumbsAction.OUTLINE);
        this.mThumbsUpAction.setIndex(PlaybackControlsRow.ThumbsAction.OUTLINE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v17.leanback.media.PlaybackControlGlue, android.support.v17.leanback.media.PlaybackGlue
    protected void onAttachedToHost(PlaybackGlueHost playbackGlueHost) {
        super.onAttachedToHost(playbackGlueHost);
        if (playbackGlueHost instanceof SurfaceHolderGlueHost) {
            ((SurfaceHolderGlueHost) playbackGlueHost).setSurfaceHolderCallback(new VideoPlayerSurfaceHolderCallback());
        }
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    public void setPlayerCallback(PlaybackGlue.PlayerCallback playerCallback) {
        this.mPlayerCallback = playerCallback;
    }

    public void reset() {
        this.mInitialized = false;
        this.mPlayer.reset();
    }

    public void release() {
        this.mInitialized = false;
        this.mPlayer.release();
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue, android.support.v17.leanback.media.PlaybackGlue
    protected void onDetachedFromHost() {
        if (getHost() instanceof SurfaceHolderGlueHost) {
            ((SurfaceHolderGlueHost) getHost()).setSurfaceHolderCallback(null);
        }
        reset();
        release();
        super.onDetachedFromHost();
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    protected void onCreateSecondaryActions(ArrayObjectAdapter arrayObjectAdapter) {
        arrayObjectAdapter.add(this.mRepeatAction);
        arrayObjectAdapter.add(this.mThumbsDownAction);
        arrayObjectAdapter.add(this.mThumbsUpAction);
    }

    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mPlayer.setDisplay(surfaceHolder);
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public void enableProgressUpdating(boolean z) {
        if (this.mRunnable != null) {
            this.mHandler.removeCallbacks(this.mRunnable);
        }
        if (z) {
            if (this.mRunnable == null) {
                this.mRunnable = new Runnable() { // from class: android.support.v17.leanback.media.MediaPlayerGlue.1
                    @Override // java.lang.Runnable
                    public void run() {
                        MediaPlayerGlue.this.updateProgress();
                        MediaPlayerGlue.this.mHandler.postDelayed(this, MediaPlayerGlue.this.getUpdatePeriod());
                    }
                };
            }
            this.mHandler.postDelayed(this.mRunnable, getUpdatePeriod());
        }
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue, android.support.v17.leanback.widget.OnActionClickedListener
    public void onActionClicked(Action action) {
        super.onActionClicked(action);
        if (action instanceof PlaybackControlsRow.RepeatAction) {
            this.mRepeatAction.nextIndex();
        } else if (action instanceof PlaybackControlsRow.ThumbsUpAction) {
            if (this.mThumbsUpAction.getIndex() == PlaybackControlsRow.ThumbsAction.SOLID) {
                this.mThumbsUpAction.setIndex(PlaybackControlsRow.ThumbsAction.OUTLINE);
            } else {
                this.mThumbsUpAction.setIndex(PlaybackControlsRow.ThumbsAction.SOLID);
                this.mThumbsDownAction.setIndex(PlaybackControlsRow.ThumbsAction.OUTLINE);
            }
        } else if (action instanceof PlaybackControlsRow.ThumbsDownAction) {
            if (this.mThumbsDownAction.getIndex() == PlaybackControlsRow.ThumbsAction.SOLID) {
                this.mThumbsDownAction.setIndex(PlaybackControlsRow.ThumbsAction.OUTLINE);
            } else {
                this.mThumbsDownAction.setIndex(PlaybackControlsRow.ThumbsAction.SOLID);
                this.mThumbsUpAction.setIndex(PlaybackControlsRow.ThumbsAction.OUTLINE);
            }
        }
        onMetadataChanged();
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue, android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if ((((((this.mSelectedAction instanceof PlaybackControlsRow.RewindAction) || (this.mSelectedAction instanceof PlaybackControlsRow.FastForwardAction)) && this.mInitialized) && keyEvent.getKeyCode() == 23) && keyEvent.getAction() == 0) && System.currentTimeMillis() - this.mLastKeyDownEvent > 200) {
            this.mLastKeyDownEvent = System.currentTimeMillis();
            int currentPosition = getCurrentPosition() + FAST_FORWARD_REWIND_STEP;
            if (this.mSelectedAction instanceof PlaybackControlsRow.RewindAction) {
                currentPosition = getCurrentPosition() - 10000;
            }
            if (currentPosition < 0) {
                currentPosition = 0;
            }
            if (currentPosition > getMediaDuration()) {
                currentPosition = getMediaDuration();
            }
            seekTo(currentPosition);
            return true;
        }
        return super.onKey(view, i, keyEvent);
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public boolean hasValidMedia() {
        return (this.mTitle == null || (this.mMediaSourcePath == null && this.mMediaSourceUri == null)) ? false : true;
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public boolean isMediaPlaying() {
        return this.mInitialized && this.mPlayer.isPlaying();
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public CharSequence getMediaTitle() {
        return this.mTitle != null ? this.mTitle : "N/a";
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public CharSequence getMediaSubtitle() {
        return this.mArtist != null ? this.mArtist : "N/a";
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public int getMediaDuration() {
        if (this.mInitialized) {
            return this.mPlayer.getDuration();
        }
        return 0;
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public Drawable getMediaArt() {
        return this.mCover;
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public int getCurrentSpeedId() {
        return isMediaPlaying() ? 1 : 0;
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public int getCurrentPosition() {
        if (this.mInitialized) {
            return this.mPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.support.v17.leanback.media.PlaybackControlGlue
    public void play(int i) {
        if (!this.mInitialized || this.mPlayer.isPlaying()) {
            return;
        }
        this.mPlayer.start();
        onMetadataChanged();
        onStateChanged();
        updateProgress();
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    public void pause() {
        if (isMediaPlaying()) {
            this.mPlayer.pause();
            onStateChanged();
        }
    }

    public void setMode(int i) {
        switch (i) {
            case 0:
                this.mOnCompletionListener = null;
                break;
            case 1:
                this.mOnCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: android.support.v17.leanback.media.MediaPlayerGlue.2
                    public boolean mFirstRepeat;

                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        if (!this.mFirstRepeat) {
                            this.mFirstRepeat = true;
                            mediaPlayer.setOnCompletionListener(null);
                        }
                        MediaPlayerGlue.this.play();
                    }
                };
                break;
            case 2:
                this.mOnCompletionListener = new MediaPlayer.OnCompletionListener() { // from class: android.support.v17.leanback.media.MediaPlayerGlue.3
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        MediaPlayerGlue.this.play();
                    }
                };
                break;
        }
    }

    protected void seekTo(int i) {
        if (this.mInitialized) {
            this.mPlayer.seekTo(i);
        }
    }

    public boolean setMediaSource(Uri uri) {
        if (this.mMediaSourceUri != null && this.mMediaSourceUri.equals(uri)) {
            return false;
        }
        if (this.mMediaSourceUri != null || this.mMediaSourcePath != null) {
            this.mMediaSourceUri = uri;
            this.mMediaSourcePath = null;
            prepareMediaForPlaying();
            return true;
        }
        this.mMediaSourceUri = uri;
        prepareMediaForPlaying();
        return true;
    }

    public boolean setMediaSource(String str) {
        if (this.mMediaSourcePath != null && this.mMediaSourcePath.equals(this.mMediaSourcePath)) {
            return false;
        }
        if (this.mMediaSourceUri != null || this.mMediaSourcePath != null) {
            this.mMediaSourceUri = null;
            this.mMediaSourcePath = str;
            prepareMediaForPlaying();
            return true;
        }
        this.mMediaSourcePath = str;
        prepareMediaForPlaying();
        return true;
    }

    private void prepareMediaForPlaying() {
        reset();
        try {
            if (this.mMediaSourceUri != null) {
                this.mPlayer.setDataSource(getContext(), this.mMediaSourceUri);
            } else if (this.mMediaSourcePath == null) {
                return;
            } else {
                this.mPlayer.setDataSource(this.mMediaSourcePath);
            }
            this.mPlayer.setAudioStreamType(3);
            this.mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: android.support.v17.leanback.media.MediaPlayerGlue.4
                @Override // android.media.MediaPlayer.OnPreparedListener
                public void onPrepared(MediaPlayer mediaPlayer) {
                    MediaPlayerGlue.this.mInitialized = true;
                    if (MediaPlayerGlue.this.mPlayerCallback != null) {
                        MediaPlayerGlue.this.mPlayerCallback.onReadyForPlayback();
                    }
                }
            });
            if (this.mOnCompletionListener != null) {
                this.mPlayer.setOnCompletionListener(this.mOnCompletionListener);
            }
            this.mPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() { // from class: android.support.v17.leanback.media.MediaPlayerGlue.5
                @Override // android.media.MediaPlayer.OnBufferingUpdateListener
                public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                    if (MediaPlayerGlue.this.getControlsRow() == null) {
                        return;
                    }
                    MediaPlayerGlue.this.getControlsRow().setBufferedProgress((int) (mediaPlayer.getDuration() * (i / 100.0f)));
                }
            });
            this.mPlayer.prepareAsync();
            onStateChanged();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v17.leanback.widget.BaseOnItemViewSelectedListener
    public void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Row row) {
        if (obj instanceof Action) {
            this.mSelectedAction = (Action) obj;
        } else {
            this.mSelectedAction = null;
        }
    }

    @Override // android.support.v17.leanback.media.PlaybackGlue
    public boolean isReadyForPlayback() {
        return this.mInitialized;
    }

    class VideoPlayerSurfaceHolderCallback implements SurfaceHolder.Callback {
        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        }

        VideoPlayerSurfaceHolderCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            MediaPlayerGlue.this.setDisplay(surfaceHolder);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            MediaPlayerGlue.this.setDisplay(null);
        }
    }
}
