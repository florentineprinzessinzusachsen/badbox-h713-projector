package com.rk_itvui.settings.sound;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.ashd.settings.R;
import com.rk_itvui.settings.Utils;

/* JADX INFO: loaded from: classes.dex */
public class VolumeSettings implements View.OnKeyListener {
    private static final boolean DEBUG = true;
    private static final int MSG_RINGER_MODE_CHANGED = 101;
    private static final String TAG = "VolumeSettings";
    private AudioManager mAudioManager;
    private Context mContext;
    private BroadcastReceiver mRingModeChangedReceiver;
    private Handler mUIHandler;
    private static final int[] SEEKBAR_ID = {R.id.media_volume_seekbar, R.id.ringer_volume_seekbar, R.id.notification_volume_seekbar, R.id.alarm_volume_seekbar};
    private static final int[] SEEKBAR_TYPE = {3, 2, 5, 4};
    private static final int[] CHECKBOX_VIEW_ID = {R.id.media_mute_button, R.id.ringer_mute_button, R.id.notification_mute_button, R.id.alarm_mute_button};
    private static final int[] SEEKBAR_MUTED_RES_ID = {android.R.drawable.conversation_badge_ring, android.R.drawable.control_background_32dp_material, android.R.drawable.config_scrollbarThumbVertical, android.R.drawable.code_lock_top};
    private static final int[] SEEKBAR_UNMUTED_RES_ID = {android.R.drawable.conversation_badge_background, android.R.drawable.contact_header_bg, android.R.drawable.compass_arrow, android.R.drawable.code_lock_left};
    private LayoutInflater mInflater = null;
    private Dialog mDialog = null;
    private ImageView[] mCheckBoxes = new ImageView[SEEKBAR_MUTED_RES_ID.length];
    private SeekBar[] mSeekBars = new SeekBar[SEEKBAR_ID.length];
    private Handler mVolumeHandler = new Handler() { // from class: com.rk_itvui.settings.sound.VolumeSettings.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            VolumeSettings.this.updateSlidersAndMutedStates();
        }
    };
    private SeekBarVolumizer[] mSeekBarVolumizer = new SeekBarVolumizer[SEEKBAR_ID.length];

    public static class VolumeStore {
        public int volume = -1;
        public int originalVolume = -1;
    }

    public void Resume() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void LOG(String str) {
        Log.d(TAG, str);
    }

    public VolumeSettings(Context context, Handler handler) {
        this.mContext = null;
        this.mUIHandler = null;
        this.mContext = context;
        this.mUIHandler = handler;
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
    }

    public void OnClick() {
        this.mInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
        View viewInflate = this.mInflater.inflate(R.layout.preference_dialog_ringervolume, (ViewGroup) null);
        onBindDialogView(viewInflate);
        this.mDialog = new AlertDialog.Builder(this.mContext).setView(viewInflate).setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.sound.VolumeSettings.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                VolumeSettings.this.onDialogClosed(true);
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.rk_itvui.settings.sound.VolumeSettings.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                VolumeSettings.this.onDialogClosed(false);
            }
        }).create();
        this.mDialog.show();
    }

    public void Pause() {
        onActivityStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSlidersAndMutedStates() {
        int streamVolume;
        for (int i = 0; i < SEEKBAR_TYPE.length; i++) {
            int i2 = SEEKBAR_TYPE[i];
            boolean zIsStreamMute = this.mAudioManager.isStreamMute(i2);
            if (this.mCheckBoxes[i] != null) {
                if (i2 == 2 && zIsStreamMute && this.mAudioManager.shouldVibrate(0)) {
                    this.mCheckBoxes[i].setImageResource(android.R.drawable.control_background_40dp_material);
                } else {
                    this.mCheckBoxes[i].setImageResource(zIsStreamMute ? SEEKBAR_MUTED_RES_ID[i] : SEEKBAR_UNMUTED_RES_ID[i]);
                }
            }
            if (this.mSeekBars[i] != null) {
                if (zIsStreamMute) {
                    streamVolume = this.mAudioManager.getLastAudibleStreamVolume(i2);
                } else {
                    streamVolume = this.mAudioManager.getStreamVolume(i2);
                }
                this.mSeekBars[i].setProgress(streamVolume);
            }
        }
    }

    protected void onBindDialogView(View view) {
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        for (int i = 0; i < SEEKBAR_ID.length; i++) {
            SeekBar seekBar = (SeekBar) view.findViewById(SEEKBAR_ID[i]);
            this.mSeekBars[i] = seekBar;
            if (SEEKBAR_TYPE[i] == 3) {
                this.mSeekBarVolumizer[i] = new SeekBarVolumizer(this.mContext, seekBar, SEEKBAR_TYPE[i], getMediaVolumeUri(this.mContext));
            } else {
                this.mSeekBarVolumizer[i] = new SeekBarVolumizer(this, this.mContext, seekBar, SEEKBAR_TYPE[i]);
            }
        }
        Settings.System.getInt(this.mContext.getContentResolver(), "mode_ringer_streams_affected", 36);
        for (int i2 = 0; i2 < this.mCheckBoxes.length; i2++) {
            this.mCheckBoxes[i2] = (ImageView) view.findViewById(CHECKBOX_VIEW_ID[i2]);
        }
        updateSlidersAndMutedStates();
        if (this.mRingModeChangedReceiver == null) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.media.RINGER_MODE_CHANGED");
            this.mRingModeChangedReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.sound.VolumeSettings.4
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if ("android.media.RINGER_MODE_CHANGED".equals(intent.getAction())) {
                        VolumeSettings.this.mVolumeHandler.sendMessage(VolumeSettings.this.mVolumeHandler.obtainMessage(101, intent.getIntExtra("android.media.EXTRA_RINGER_MODE", -1), 0));
                    }
                }
            };
            this.mContext.registerReceiver(this.mRingModeChangedReceiver, intentFilter);
        }
        view.findViewById(!Utils.isVoiceCapable(this.mContext) ? R.id.ringer_section : R.id.notification_section).setVisibility(8);
    }

    private Uri getMediaVolumeUri(Context context) {
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.media_volume);
    }

    protected void onDialogClosed(boolean z) {
        if (!z) {
            for (SeekBarVolumizer seekBarVolumizer : this.mSeekBarVolumizer) {
                if (seekBarVolumizer != null) {
                    seekBarVolumizer.revertVolume();
                }
            }
        }
        cleanup();
    }

    private void onActivityStop() {
        LOG("volume stop");
        for (SeekBarVolumizer seekBarVolumizer : this.mSeekBarVolumizer) {
            if (seekBarVolumizer != null) {
                seekBarVolumizer.stopSample();
            }
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        keyEvent.getAction();
        if (i == 164) {
            return true;
        }
        switch (i) {
            case 24:
            case 25:
                return true;
            default:
                return false;
        }
    }

    protected void onSampleStarting(SeekBarVolumizer seekBarVolumizer) {
        for (SeekBarVolumizer seekBarVolumizer2 : this.mSeekBarVolumizer) {
            if (seekBarVolumizer2 != null && seekBarVolumizer2 != seekBarVolumizer) {
                seekBarVolumizer2.stopSample();
            }
        }
    }

    private void cleanup() {
        for (int i = 0; i < SEEKBAR_ID.length; i++) {
            if (this.mSeekBarVolumizer[i] != null) {
                this.mSeekBarVolumizer[i].stop();
                this.mSeekBarVolumizer[i] = null;
            }
        }
        if (this.mRingModeChangedReceiver != null) {
            this.mRingModeChangedReceiver = null;
        }
    }

    public class SeekBarVolumizer implements SeekBar.OnSeekBarChangeListener, View.OnKeyListener, Runnable {
        private AudioManager mAudioManager;
        private Context mContext;
        private Handler mHandler;
        private int mLastProgress;
        private int mOriginalStreamVolume;
        private Ringtone mRingtone;
        private SeekBar mSeekBar;
        private int mStreamType;
        private int mVolumeBeforeMute;
        private ContentObserver mVolumeObserver;

        public SeekBarVolumizer(VolumeSettings volumeSettings, Context context, SeekBar seekBar, int i) {
            this(context, seekBar, i, null);
        }

        public SeekBarVolumizer(Context context, SeekBar seekBar, int i, Uri uri) {
            this.mHandler = new Handler();
            this.mLastProgress = -1;
            this.mVolumeBeforeMute = -1;
            this.mVolumeObserver = new ContentObserver(this.mHandler) { // from class: com.rk_itvui.settings.sound.VolumeSettings.SeekBarVolumizer.1
                @Override // android.database.ContentObserver
                public void onChange(boolean z) {
                    super.onChange(z);
                    if (SeekBarVolumizer.this.mSeekBar == null || SeekBarVolumizer.this.mAudioManager == null) {
                        return;
                    }
                    SeekBarVolumizer.this.mSeekBar.setProgress(SeekBarVolumizer.this.mAudioManager.isStreamMute(SeekBarVolumizer.this.mStreamType) ? SeekBarVolumizer.this.mAudioManager.getLastAudibleStreamVolume(SeekBarVolumizer.this.mStreamType) : SeekBarVolumizer.this.mAudioManager.getStreamVolume(SeekBarVolumizer.this.mStreamType));
                }
            };
            this.mContext = context;
            this.mAudioManager = (AudioManager) context.getSystemService("audio");
            this.mStreamType = i;
            this.mSeekBar = seekBar;
            initSeekBar(seekBar, uri);
        }

        private void initSeekBar(SeekBar seekBar, Uri uri) {
            seekBar.setMax(this.mAudioManager.getStreamMaxVolume(this.mStreamType));
            this.mOriginalStreamVolume = this.mAudioManager.getStreamVolume(this.mStreamType);
            seekBar.setProgress(this.mOriginalStreamVolume);
            seekBar.setOnSeekBarChangeListener(this);
            seekBar.setOnKeyListener(this);
            this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor(Settings.System.VOLUME_SETTINGS[this.mStreamType]), false, this.mVolumeObserver);
            if (uri == null) {
                if (this.mStreamType == 2) {
                    uri = Settings.System.DEFAULT_RINGTONE_URI;
                } else if (this.mStreamType == 5) {
                    uri = Settings.System.DEFAULT_NOTIFICATION_URI;
                } else {
                    uri = Settings.System.DEFAULT_ALARM_ALERT_URI;
                }
            }
            this.mRingtone = RingtoneManager.getRingtone(this.mContext, uri);
            if (this.mRingtone != null) {
                this.mRingtone.setStreamType(this.mStreamType);
            }
        }

        public void stop() {
            stopSample();
            this.mContext.getContentResolver().unregisterContentObserver(this.mVolumeObserver);
            this.mSeekBar.setOnSeekBarChangeListener(null);
        }

        public void revertVolume() {
            this.mAudioManager.setStreamVolume(this.mStreamType, this.mOriginalStreamVolume, 0);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            VolumeSettings.this.LOG("onProgressChanged:" + z);
            if (z) {
                postSetVolume(i);
            }
        }

        void postSetVolume(int i) {
            this.mLastProgress = i;
            this.mHandler.removeCallbacks(this);
            this.mHandler.post(this);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            VolumeSettings.this.LOG("onStartTrackingTouch");
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            VolumeSettings.this.LOG("onStopTrackingTouch");
            if (isSamplePlaying()) {
                return;
            }
            startSample();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mAudioManager.setStreamVolume(this.mStreamType, this.mLastProgress, 0);
        }

        public boolean isSamplePlaying() {
            return this.mRingtone != null && this.mRingtone.isPlaying();
        }

        public void startSample() {
            VolumeSettings.this.onSampleStarting(this);
            if (this.mRingtone != null) {
                this.mRingtone.play();
            }
        }

        public void stopSample() {
            if (this.mRingtone != null) {
                this.mRingtone.stop();
            }
        }

        public SeekBar getSeekBar() {
            return this.mSeekBar;
        }

        public void changeVolumeBy(int i) {
            this.mSeekBar.incrementProgressBy(i);
            if (!isSamplePlaying()) {
                startSample();
            }
            postSetVolume(this.mSeekBar.getProgress());
            this.mVolumeBeforeMute = -1;
        }

        public void muteVolume() {
            if (this.mVolumeBeforeMute != -1) {
                this.mSeekBar.setProgress(this.mVolumeBeforeMute);
                startSample();
                postSetVolume(this.mVolumeBeforeMute);
                this.mVolumeBeforeMute = -1;
                return;
            }
            this.mVolumeBeforeMute = this.mSeekBar.getProgress();
            this.mSeekBar.setProgress(0);
            stopSample();
            postSetVolume(0);
        }

        public void onSaveInstanceState(VolumeStore volumeStore) {
            if (this.mLastProgress >= 0) {
                volumeStore.volume = this.mLastProgress;
                volumeStore.originalVolume = this.mOriginalStreamVolume;
            }
        }

        public void onRestoreInstanceState(VolumeStore volumeStore) {
            if (volumeStore.volume != -1) {
                this.mOriginalStreamVolume = volumeStore.originalVolume;
                this.mLastProgress = volumeStore.volume;
                postSetVolume(this.mLastProgress);
            }
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            VolumeSettings.this.LOG("keycode:" + i);
            switch (i) {
                case 21:
                    if (!isSamplePlaying()) {
                        startSample();
                    }
                    break;
                case 22:
                    if (!isSamplePlaying()) {
                        startSample();
                    }
                    break;
            }
            return false;
        }
    }
}
