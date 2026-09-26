package com.rk_itvui.settings.sound;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.SeekBar;
import android.widget.TextView;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class VolumeSetting extends Activity {
    public AudioManager audiomanage;
    KTask changeTask;
    private int currentVolume;
    private TextView mVolume;
    private int maxVolume;
    public SeekBar soundBar;
    private final BroadcastReceiver VolumeReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.sound.VolumeSetting.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int streamVolume = VolumeSetting.this.audiomanage.getStreamVolume(3);
            Log.e("VolumeSetting", " VolumeReceiver currentVolume=" + streamVolume);
            if (VolumeSetting.this.soundBar != null) {
                VolumeSetting.this.soundBar.setProgress(streamVolume);
            }
        }
    };
    Handler mHandler = new Handler();

    public class KTask implements Runnable {
        AudioManager audioManager;
        int progress;

        public int getProgress() {
            return this.progress;
        }

        public void setProgress(int i) {
            this.progress = i;
        }

        public KTask(int i, AudioManager audioManager) {
            this.progress = 0;
            this.progress = i;
            this.audioManager = audioManager;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.audioManager.setStreamVolume(1, this.progress, 0);
            this.audioManager.setStreamVolume(3, this.progress, 0);
            this.audioManager.setStreamVolume(4, this.progress, 0);
            VolumeSetting.this.currentVolume = VolumeSetting.this.audiomanage.getStreamVolume(3);
            Log.e("VolumeSetting", " onProgressChanged currentVolume=" + VolumeSetting.this.currentVolume);
            VolumeSetting.this.mVolume.setText(((VolumeSetting.this.currentVolume * 100) / VolumeSetting.this.maxVolume) + " %");
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.volume_settings);
        this.soundBar = (SeekBar) findViewById(R.id.volume_seekbar);
        this.mVolume = (TextView) findViewById(R.id.volume_test);
        this.audiomanage = (AudioManager) getSystemService("audio");
        if (this.audiomanage == null) {
            finish();
        }
        this.changeTask = new KTask(0, this.audiomanage);
        this.maxVolume = this.audiomanage.getStreamMaxVolume(3);
        this.soundBar.setMax(this.maxVolume);
        Log.e("VolumeSetting", "maxVolume=" + this.maxVolume);
        this.soundBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.rk_itvui.settings.sound.VolumeSetting.2
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                Log.e("VolumeSetting", " onProgressChanged progress=" + i);
                VolumeSetting.this.mHandler.removeCallbacks(VolumeSetting.this.changeTask);
                VolumeSetting.this.changeTask.setProgress(i);
                VolumeSetting.this.mHandler.post(VolumeSetting.this.changeTask);
            }
        });
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.currentVolume = this.audiomanage.getStreamVolume(1);
        this.soundBar.setProgress(this.currentVolume);
        this.audiomanage.setStreamVolume(1, this.soundBar.getProgress(), 0);
        this.audiomanage.setStreamVolume(3, this.soundBar.getProgress(), 0);
        this.audiomanage.setStreamVolume(4, this.soundBar.getProgress(), 0);
        this.mVolume.setText(((this.currentVolume * 100) / this.maxVolume) + " %");
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Log.e("VolumeSetting", "keyCode=" + i);
        if (i == 164) {
            Log.e("VolumeSetting", " onKeyDown currentVolume2=0");
            if (this.soundBar != null) {
                this.soundBar.setProgress(0);
            }
        }
        return super.onKeyDown(i, keyEvent);
    }
}
