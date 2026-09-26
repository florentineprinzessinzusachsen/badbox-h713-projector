package android.support.v17.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v17.leanback.R;
import android.support.v17.leanback.util.MathUtil;
import android.support.v4.media.TransportMediator;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackControlsRow extends Row {
    private long mBufferedProgressMs;
    private long mCurrentTimeMs;
    private Drawable mImageDrawable;
    private Object mItem;
    private OnPlaybackStateChangedListener mListener;
    private ObjectAdapter mPrimaryActionsAdapter;
    private ObjectAdapter mSecondaryActionsAdapter;
    private long mTotalTimeMs;

    interface OnPlaybackStateChangedListener {
        void onBufferedProgressChanged(long j);

        void onCurrentTimeChanged(long j);
    }

    public static abstract class MultiAction extends Action {
        private Drawable[] mDrawables;
        private int mIndex;
        private String[] mLabels;
        private String[] mLabels2;

        public MultiAction(int i) {
            super(i);
        }

        public void setDrawables(Drawable[] drawableArr) {
            this.mDrawables = drawableArr;
            setIndex(0);
        }

        public void setLabels(String[] strArr) {
            this.mLabels = strArr;
            setIndex(0);
        }

        public void setSecondaryLabels(String[] strArr) {
            this.mLabels2 = strArr;
            setIndex(0);
        }

        public int getActionCount() {
            if (this.mDrawables != null) {
                return this.mDrawables.length;
            }
            if (this.mLabels != null) {
                return this.mLabels.length;
            }
            return 0;
        }

        public Drawable getDrawable(int i) {
            if (this.mDrawables == null) {
                return null;
            }
            return this.mDrawables[i];
        }

        public String getLabel(int i) {
            if (this.mLabels == null) {
                return null;
            }
            return this.mLabels[i];
        }

        public String getSecondaryLabel(int i) {
            if (this.mLabels2 == null) {
                return null;
            }
            return this.mLabels2[i];
        }

        public void nextIndex() {
            setIndex(this.mIndex < getActionCount() + (-1) ? this.mIndex + 1 : 0);
        }

        public void setIndex(int i) {
            this.mIndex = i;
            if (this.mDrawables != null) {
                setIcon(this.mDrawables[this.mIndex]);
            }
            if (this.mLabels != null) {
                setLabel1(this.mLabels[this.mIndex]);
            }
            if (this.mLabels2 != null) {
                setLabel2(this.mLabels2[this.mIndex]);
            }
        }

        public int getIndex() {
            return this.mIndex;
        }
    }

    public static class PlayPauseAction extends MultiAction {
        public static int PAUSE = 1;
        public static int PLAY;

        public PlayPauseAction(Context context) {
            super(R.id.lb_control_play_pause);
            Drawable[] drawableArr = new Drawable[2];
            drawableArr[PLAY] = PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_play);
            drawableArr[PAUSE] = PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_pause);
            setDrawables(drawableArr);
            String[] strArr = new String[drawableArr.length];
            strArr[PLAY] = context.getString(R.string.lb_playback_controls_play);
            strArr[PAUSE] = context.getString(R.string.lb_playback_controls_pause);
            setLabels(strArr);
            addKeyCode(85);
            addKeyCode(126);
            addKeyCode(TransportMediator.KEYCODE_MEDIA_PAUSE);
        }
    }

    public static class FastForwardAction extends MultiAction {
        public FastForwardAction(Context context) {
            this(context, 1);
        }

        public FastForwardAction(Context context, int i) {
            super(R.id.lb_control_fast_forward);
            if (i < 1) {
                throw new IllegalArgumentException("numSpeeds must be > 0");
            }
            Drawable[] drawableArr = new Drawable[i + 1];
            drawableArr[0] = PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_fast_forward);
            setDrawables(drawableArr);
            String[] strArr = new String[getActionCount()];
            strArr[0] = context.getString(R.string.lb_playback_controls_fast_forward);
            String[] strArr2 = new String[getActionCount()];
            strArr2[0] = strArr[0];
            int i2 = 1;
            while (i2 <= i) {
                int i3 = i2 + 1;
                strArr[i2] = context.getResources().getString(R.string.lb_control_display_fast_forward_multiplier, Integer.valueOf(i3));
                strArr2[i2] = context.getResources().getString(R.string.lb_playback_controls_fast_forward_multiplier, Integer.valueOf(i3));
                i2 = i3;
            }
            setLabels(strArr);
            setSecondaryLabels(strArr2);
            addKeyCode(90);
        }
    }

    public static class RewindAction extends MultiAction {
        public RewindAction(Context context) {
            this(context, 1);
        }

        public RewindAction(Context context, int i) {
            super(R.id.lb_control_fast_rewind);
            if (i < 1) {
                throw new IllegalArgumentException("numSpeeds must be > 0");
            }
            Drawable[] drawableArr = new Drawable[i + 1];
            drawableArr[0] = PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_rewind);
            setDrawables(drawableArr);
            String[] strArr = new String[getActionCount()];
            strArr[0] = context.getString(R.string.lb_playback_controls_rewind);
            String[] strArr2 = new String[getActionCount()];
            strArr2[0] = strArr[0];
            int i2 = 1;
            while (i2 <= i) {
                int i3 = i2 + 1;
                String string = context.getResources().getString(R.string.lb_control_display_rewind_multiplier, Integer.valueOf(i3));
                strArr[i2] = string;
                strArr[i2] = string;
                strArr2[i2] = context.getResources().getString(R.string.lb_playback_controls_rewind_multiplier, Integer.valueOf(i3));
                i2 = i3;
            }
            setLabels(strArr);
            setSecondaryLabels(strArr2);
            addKeyCode(89);
        }
    }

    public static class SkipNextAction extends Action {
        public SkipNextAction(Context context) {
            super(R.id.lb_control_skip_next);
            setIcon(PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_skip_next));
            setLabel1(context.getString(R.string.lb_playback_controls_skip_next));
            addKeyCode(87);
        }
    }

    public static class SkipPreviousAction extends Action {
        public SkipPreviousAction(Context context) {
            super(R.id.lb_control_skip_previous);
            setIcon(PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_skip_previous));
            setLabel1(context.getString(R.string.lb_playback_controls_skip_previous));
            addKeyCode(88);
        }
    }

    public static class PictureInPictureAction extends Action {
        public PictureInPictureAction(Context context) {
            super(R.id.lb_control_picture_in_picture);
            setIcon(PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_picture_in_picture));
            setLabel1(context.getString(R.string.lb_playback_controls_picture_in_picture));
            addKeyCode(171);
        }
    }

    public static class MoreActions extends Action {
        public MoreActions(Context context) {
            super(R.id.lb_control_more_actions);
            setIcon(context.getResources().getDrawable(R.drawable.lb_ic_more));
            setLabel1(context.getString(R.string.lb_playback_controls_more_actions));
        }
    }

    public static abstract class ThumbsAction extends MultiAction {
        public static int OUTLINE = 1;
        public static int SOLID;

        public ThumbsAction(int i, Context context, int i2, int i3) {
            super(i);
            Drawable[] drawableArr = new Drawable[2];
            drawableArr[SOLID] = PlaybackControlsRow.getStyledDrawable(context, i2);
            drawableArr[OUTLINE] = PlaybackControlsRow.getStyledDrawable(context, i3);
            setDrawables(drawableArr);
        }
    }

    public static class ThumbsUpAction extends ThumbsAction {
        public ThumbsUpAction(Context context) {
            super(R.id.lb_control_thumbs_up, context, R.styleable.lbPlaybackControlsActionIcons_thumb_up, R.styleable.lbPlaybackControlsActionIcons_thumb_up_outline);
            String[] strArr = new String[getActionCount()];
            strArr[SOLID] = context.getString(R.string.lb_playback_controls_thumb_up);
            strArr[OUTLINE] = context.getString(R.string.lb_playback_controls_thumb_up_outline);
            setLabels(strArr);
        }
    }

    public static class ThumbsDownAction extends ThumbsAction {
        public ThumbsDownAction(Context context) {
            super(R.id.lb_control_thumbs_down, context, R.styleable.lbPlaybackControlsActionIcons_thumb_down, R.styleable.lbPlaybackControlsActionIcons_thumb_down_outline);
            String[] strArr = new String[getActionCount()];
            strArr[SOLID] = context.getString(R.string.lb_playback_controls_thumb_down);
            strArr[OUTLINE] = context.getString(R.string.lb_playback_controls_thumb_down_outline);
            setLabels(strArr);
        }
    }

    public static class RepeatAction extends MultiAction {
        public static int ALL = 1;
        public static int NONE = 0;
        public static int ONE = 2;

        public RepeatAction(Context context) {
            this(context, PlaybackControlsRow.getIconHighlightColor(context));
        }

        public RepeatAction(Context context, int i) {
            this(context, i, i);
        }

        public RepeatAction(Context context, int i, int i2) {
            super(R.id.lb_control_repeat);
            Drawable[] drawableArr = new Drawable[3];
            BitmapDrawable bitmapDrawable = (BitmapDrawable) PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_repeat);
            BitmapDrawable bitmapDrawable2 = (BitmapDrawable) PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_repeat_one);
            drawableArr[NONE] = bitmapDrawable;
            drawableArr[ALL] = bitmapDrawable == null ? null : new BitmapDrawable(context.getResources(), PlaybackControlsRow.createBitmap(bitmapDrawable.getBitmap(), i));
            drawableArr[ONE] = bitmapDrawable2 != null ? new BitmapDrawable(context.getResources(), PlaybackControlsRow.createBitmap(bitmapDrawable2.getBitmap(), i2)) : null;
            setDrawables(drawableArr);
            String[] strArr = new String[drawableArr.length];
            strArr[NONE] = context.getString(R.string.lb_playback_controls_repeat_all);
            strArr[ALL] = context.getString(R.string.lb_playback_controls_repeat_one);
            strArr[ONE] = context.getString(R.string.lb_playback_controls_repeat_none);
            setLabels(strArr);
        }
    }

    public static class ShuffleAction extends MultiAction {
        public static int OFF = 0;
        public static int ON = 1;

        public ShuffleAction(Context context) {
            this(context, PlaybackControlsRow.getIconHighlightColor(context));
        }

        public ShuffleAction(Context context, int i) {
            super(R.id.lb_control_shuffle);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_shuffle);
            Drawable[] drawableArr = {bitmapDrawable, new BitmapDrawable(context.getResources(), PlaybackControlsRow.createBitmap(bitmapDrawable.getBitmap(), i))};
            setDrawables(drawableArr);
            String[] strArr = new String[drawableArr.length];
            strArr[OFF] = context.getString(R.string.lb_playback_controls_shuffle_enable);
            strArr[ON] = context.getString(R.string.lb_playback_controls_shuffle_disable);
            setLabels(strArr);
        }
    }

    public static class HighQualityAction extends MultiAction {
        public static int OFF = 0;
        public static int ON = 1;

        public HighQualityAction(Context context) {
            this(context, PlaybackControlsRow.getIconHighlightColor(context));
        }

        public HighQualityAction(Context context, int i) {
            super(R.id.lb_control_high_quality);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_high_quality);
            Drawable[] drawableArr = {bitmapDrawable, new BitmapDrawable(context.getResources(), PlaybackControlsRow.createBitmap(bitmapDrawable.getBitmap(), i))};
            setDrawables(drawableArr);
            String[] strArr = new String[drawableArr.length];
            strArr[OFF] = context.getString(R.string.lb_playback_controls_high_quality_enable);
            strArr[ON] = context.getString(R.string.lb_playback_controls_high_quality_disable);
            setLabels(strArr);
        }
    }

    public static class ClosedCaptioningAction extends MultiAction {
        public static int OFF = 0;
        public static int ON = 1;

        public ClosedCaptioningAction(Context context) {
            this(context, PlaybackControlsRow.getIconHighlightColor(context));
        }

        public ClosedCaptioningAction(Context context, int i) {
            super(R.id.lb_control_closed_captioning);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) PlaybackControlsRow.getStyledDrawable(context, R.styleable.lbPlaybackControlsActionIcons_closed_captioning);
            Drawable[] drawableArr = {bitmapDrawable, new BitmapDrawable(context.getResources(), PlaybackControlsRow.createBitmap(bitmapDrawable.getBitmap(), i))};
            setDrawables(drawableArr);
            String[] strArr = new String[drawableArr.length];
            strArr[OFF] = context.getString(R.string.lb_playback_controls_closed_captioning_enable);
            strArr[ON] = context.getString(R.string.lb_playback_controls_closed_captioning_disable);
            setLabels(strArr);
        }
    }

    static Bitmap createBitmap(Bitmap bitmap, int i) {
        Bitmap bitmapCopy = bitmap.copy(bitmap.getConfig(), true);
        Canvas canvas = new Canvas(bitmapCopy);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return bitmapCopy;
    }

    static int getIconHighlightColor(Context context) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R.attr.playbackControlsIconHighlightColor, typedValue, true)) {
            return typedValue.data;
        }
        return context.getResources().getColor(R.color.lb_playback_icon_highlight_no_theme);
    }

    static Drawable getStyledDrawable(Context context, int i) {
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.playbackControlsActionIcons, typedValue, false)) {
            return null;
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(typedValue.data, R.styleable.lbPlaybackControlsActionIcons);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(i);
        typedArrayObtainStyledAttributes.recycle();
        return drawable;
    }

    public PlaybackControlsRow(Object obj) {
        this.mItem = obj;
    }

    public PlaybackControlsRow() {
    }

    public final Object getItem() {
        return this.mItem;
    }

    public final void setImageDrawable(Drawable drawable) {
        this.mImageDrawable = drawable;
    }

    public final void setImageBitmap(Context context, Bitmap bitmap) {
        this.mImageDrawable = new BitmapDrawable(context.getResources(), bitmap);
    }

    public final Drawable getImageDrawable() {
        return this.mImageDrawable;
    }

    public final void setPrimaryActionsAdapter(ObjectAdapter objectAdapter) {
        this.mPrimaryActionsAdapter = objectAdapter;
    }

    public final void setSecondaryActionsAdapter(ObjectAdapter objectAdapter) {
        this.mSecondaryActionsAdapter = objectAdapter;
    }

    public final ObjectAdapter getPrimaryActionsAdapter() {
        return this.mPrimaryActionsAdapter;
    }

    public final ObjectAdapter getSecondaryActionsAdapter() {
        return this.mSecondaryActionsAdapter;
    }

    public void setTotalTime(int i) {
        setTotalTimeLong(i);
    }

    public void setTotalTimeLong(long j) {
        this.mTotalTimeMs = j;
    }

    public int getTotalTime() {
        return MathUtil.safeLongToInt(getTotalTimeLong());
    }

    public long getTotalTimeLong() {
        return this.mTotalTimeMs;
    }

    public void setCurrentTime(int i) {
        setCurrentTimeLong(i);
    }

    public void setCurrentTimeLong(long j) {
        if (this.mCurrentTimeMs != j) {
            this.mCurrentTimeMs = j;
            currentTimeChanged();
        }
    }

    public int getCurrentTime() {
        return MathUtil.safeLongToInt(getCurrentTimeLong());
    }

    public long getCurrentTimeLong() {
        return this.mCurrentTimeMs;
    }

    public void setBufferedProgress(int i) {
        setBufferedProgressLong(i);
    }

    public void setBufferedProgressLong(long j) {
        if (this.mBufferedProgressMs != j) {
            this.mBufferedProgressMs = j;
            bufferedProgressChanged();
        }
    }

    public int getBufferedProgress() {
        return MathUtil.safeLongToInt(getBufferedProgressLong());
    }

    public long getBufferedProgressLong() {
        return this.mBufferedProgressMs;
    }

    public Action getActionForKeyCode(int i) {
        Action actionForKeyCode = getActionForKeyCode(getPrimaryActionsAdapter(), i);
        return actionForKeyCode != null ? actionForKeyCode : getActionForKeyCode(getSecondaryActionsAdapter(), i);
    }

    public Action getActionForKeyCode(ObjectAdapter objectAdapter, int i) {
        if (objectAdapter != this.mPrimaryActionsAdapter && objectAdapter != this.mSecondaryActionsAdapter) {
            throw new IllegalArgumentException("Invalid adapter");
        }
        for (int i2 = 0; i2 < objectAdapter.size(); i2++) {
            Action action = (Action) objectAdapter.get(i2);
            if (action.respondsToKeyCode(i)) {
                return action;
            }
        }
        return null;
    }

    void setOnPlaybackStateChangedListener(OnPlaybackStateChangedListener onPlaybackStateChangedListener) {
        this.mListener = onPlaybackStateChangedListener;
    }

    OnPlaybackStateChangedListener getOnPlaybackStateChangedListener() {
        return this.mListener;
    }

    private void currentTimeChanged() {
        if (this.mListener != null) {
            this.mListener.onCurrentTimeChanged(this.mCurrentTimeMs);
        }
    }

    private void bufferedProgressChanged() {
        if (this.mListener != null) {
            this.mListener.onBufferedProgressChanged(this.mBufferedProgressMs);
        }
    }
}
