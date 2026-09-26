package com.rk_itvui.settings.sound;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.SystemProperties;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceGroup;
import android.preference.PreferenceScreen;
import android.util.Log;
import com.ashd.settings.R;
import com.rk_itvui.settings.RadioPreference;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SoundDevicesManager extends PreferenceActivity {
    private static final String AUDIOPCMLISTUPDATE = "com.android.server.audiopcmlistupdate";
    private static final boolean DEBUG = true;
    private static String HDMI_AUDIO_MULTICHANNEL = "media.cfg.audio.mul";
    private static final String HDMI_MULTICHANNEL_KEY = "7";
    private static String HDMI_MULTICHANNEL_NAME = "";
    private static final String HDMI_PASSTHROUGH_KEY = "6";
    private static String HDMI_PASSTHROUGH_NAME = "";
    private static String MEDIA_CFG_AUDIO_BYPASS = "media.cfg.audio.bypass";
    private static final String MULTICHANNEL = "5POINT1 MULTICHANNEL";
    private static final String PASSTHROUGH = "PASSTHROUGH";
    private static final String SOC_AND_SPDIF_KEY = "9";
    private static String SOC_AND_SPDIF_NAME = "";
    private static final String SPDIF_CARD_DRIVER_NAME = "SPDIF";
    private static final String SPDIF_PASSTHROUGH_KEY = "8";
    private static String SPDIF_PASSTHROUGH_NAME = "";
    private static final String TAG = "SoundDevicesManager";
    private static final String USBAUDIO_CARD_DRIVER_NAME = "USB-Audio";
    private static boolean bAudioPassthroughSupport = false;
    private List<SndElement> mCardsList;
    private Context mContext;
    private String mSelectedCaptureKey;
    private String mSelectedPlaybackKey;
    private PreferenceGroup mSoundOutput_List;
    private int mCaptureCounts = 0;
    private int mPlaybackCounts = 0;
    private IntentFilter mAudioDevicesListUpdate_IF = null;
    private BroadcastReceiver mAudioDevicesListUpdate_BR = new BroadcastReceiver() { // from class: com.rk_itvui.settings.sound.SoundDevicesManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) throws Throwable {
            SoundDevicesManager.this.update();
        }
    };
    Preference.OnPreferenceClickListener mPlaybackListClickListener = new Preference.OnPreferenceClickListener() { // from class: com.rk_itvui.settings.sound.SoundDevicesManager.2
        /* JADX WARN: Code duplicated, block: B:11:0x008c  */
        @Override // android.preference.Preference.OnPreferenceClickListener
        public boolean onPreferenceClick(Preference preference) {
            int i;
            boolean z;
            boolean z2;
            int i2;
            SoundDevicesManager.this.logd("Key: " + preference.getKey() + ", last key: " + SoundDevicesManager.this.mSelectedPlaybackKey + " Card: " + preference.getTitle().toString());
            if (!SoundDevicesManager.this.mSelectedPlaybackKey.equals(preference.getKey())) {
                RadioPreference radioPreference = (RadioPreference) SoundDevicesManager.this.mSoundOutput_List.findPreference(SoundDevicesManager.this.mSelectedPlaybackKey);
                if (radioPreference != null) {
                    radioPreference.setChecked(false);
                }
                SoundDevicesManager.this.mSelectedPlaybackKey = preference.getKey();
                RadioPreference radioPreference2 = (RadioPreference) preference;
                radioPreference2.setChecked(true);
                String string = radioPreference2.getTitle().toString();
                if (string.equals(SoundDevicesManager.this.getString(R.string.sound_output_default))) {
                    z2 = false;
                    z = false;
                    i = 0;
                } else {
                    if (string.equals(SoundDevicesManager.this.getString(R.string.sound_output_spdif_passthrough))) {
                        i2 = 6;
                    } else if (SoundDevicesManager.this.isUsbAudio(SoundDevicesManager.this.mSelectedPlaybackKey)) {
                        i = 1;
                        z2 = false;
                        z = false;
                    } else if (string.equals(SoundDevicesManager.this.getString(R.string.sound_output_hdmi_passthrough))) {
                        i2 = 5;
                    } else if (string.equals(SoundDevicesManager.this.getString(R.string.sound_output_hdmi_multilpcm))) {
                        i = 4;
                        z = true;
                        z2 = false;
                    } else {
                        z2 = false;
                        z = false;
                        i = 0;
                    }
                    i = i2;
                    z2 = true;
                    z = false;
                }
                if (z2) {
                    SystemProperties.set(SoundDevicesManager.MEDIA_CFG_AUDIO_BYPASS, "true");
                } else {
                    SystemProperties.set(SoundDevicesManager.MEDIA_CFG_AUDIO_BYPASS, "false");
                }
                if (z) {
                    SystemProperties.set(SoundDevicesManager.HDMI_AUDIO_MULTICHANNEL, "true");
                } else {
                    SystemProperties.set(SoundDevicesManager.HDMI_AUDIO_MULTICHANNEL, "false");
                }
                com.android.server.AudioCommon.doAudioDevicesRouting(SoundDevicesManager.this.mContext, i, 0, SoundDevicesManager.this.mSelectedPlaybackKey);
                SoundDevicesManager.this.finish();
                return true;
            }
            ((RadioPreference) preference).setChecked(true);
            return true;
        }
    };
    Preference.OnPreferenceChangeListener mPlaybackChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.rk_itvui.settings.sound.SoundDevicesManager.3
        @Override // android.preference.Preference.OnPreferenceChangeListener
        public boolean onPreferenceChange(Preference preference, Object obj) {
            SoundDevicesManager.this.logd("onPreferenceChange(): Preference - " + preference + ", key - " + preference.getKey() + ", newValue - " + obj + ", newValue type - " + obj.getClass());
            return true;
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void logd(String str) {
        Log.d(TAG, str);
    }

    protected void update() throws Throwable {
        fillList();
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        this.mContext = getApplicationContext();
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.sound_devices_manager);
        getListView().setItemsCanFocus(true);
        this.mSoundOutput_List = (PreferenceGroup) findPreference("sound_playback_list");
        this.mCardsList = new ArrayList();
        this.mAudioDevicesListUpdate_IF = new IntentFilter();
        this.mAudioDevicesListUpdate_IF.addAction("com.android.server.audiopcmlistupdate");
        if (SystemProperties.get(MEDIA_CFG_AUDIO_BYPASS).isEmpty()) {
            return;
        }
        bAudioPassthroughSupport = true;
    }

    @Override // android.app.Activity
    protected void onResume() throws Throwable {
        super.onResume();
        registerReceiver(this.mAudioDevicesListUpdate_BR, this.mAudioDevicesListUpdate_IF);
        fillList();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.mAudioDevicesListUpdate_BR);
    }

    private class SndElement {
        public String idx = "";
        public String cardName = "";
        public boolean hasCapture = false;
        public boolean hasPlayback = false;
        public int devType = 0;

        public SndElement() {
        }
    }

    private void fillList() throws Throwable {
        SOC_AND_SPDIF_NAME = "";
        this.mSelectedCaptureKey = "0";
        this.mSelectedPlaybackKey = "0";
        this.mSoundOutput_List.removeAll();
        this.mCaptureCounts = 0;
        this.mPlaybackCounts = 0;
        this.mCardsList.clear();
        readProcFileAndInit();
        initSelectedKey();
        boolean z = false;
        boolean z2 = false;
        for (SndElement sndElement : this.mCardsList) {
            if (sndElement.devType != 1 && (!z2 || sndElement.devType != 0)) {
                if (sndElement.hasPlayback) {
                    RadioPreference radioPreference = new RadioPreference(this, null);
                    radioPreference.setKey(sndElement.idx);
                    radioPreference.setPersistent(false);
                    radioPreference.setWidgetLayoutResource(R.layout.preference_radio);
                    radioPreference.setOnPreferenceClickListener(this.mPlaybackListClickListener);
                    radioPreference.setOnPreferenceChangeListener(this.mPlaybackChangeListener);
                    this.mSoundOutput_List.addPreference(radioPreference);
                    this.mPlaybackCounts++;
                    if (sndElement.devType == 0) {
                        radioPreference.setTitle(R.string.sound_output_default);
                        z = true;
                        z2 = true;
                    } else if (sndElement.devType == 6) {
                        radioPreference.setTitle(R.string.sound_output_spdif_passthrough);
                        radioPreference.setKey("8");
                    }
                    if (this.mSelectedPlaybackKey.equals(radioPreference.getKey())) {
                        radioPreference.setChecked(true);
                    }
                }
            }
        }
        if (z) {
            RadioPreference radioPreference2 = new RadioPreference(this, null);
            radioPreference2.setKey("6");
            radioPreference2.setTitle(R.string.sound_output_hdmi_passthrough);
            radioPreference2.setPersistent(false);
            radioPreference2.setWidgetLayoutResource(R.layout.preference_radio);
            radioPreference2.setOnPreferenceClickListener(this.mPlaybackListClickListener);
            radioPreference2.setOnPreferenceChangeListener(this.mPlaybackChangeListener);
            this.mSoundOutput_List.addPreference(radioPreference2);
            if (this.mSelectedPlaybackKey.equals(radioPreference2.getKey())) {
                radioPreference2.setChecked(true);
            }
        }
        if (this.mPlaybackCounts == 0) {
            Preference preference = new Preference(this);
            preference.setTitle("There is no Sound Output Devices");
            this.mSoundOutput_List.addPreference(preference);
        }
    }

    private void initSelectedKey() {
        this.mSelectedCaptureKey = com.android.server.AudioCommon.getCurrentCaptureDevice();
        this.mSelectedPlaybackKey = com.android.server.AudioCommon.getCurrentPlaybackDevice();
        logd("mSelectedCaptureKey:" + this.mSelectedCaptureKey + "; mSelectedPlaybackKey" + this.mSelectedPlaybackKey);
    }

    /* JADX WARN: Code duplicated, block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:72:0x0117 A[Catch: IOException -> 0x011b, TRY_ENTER, TryCatch #13 {IOException -> 0x011b, blocks: (B:44:0x00e6, B:46:0x00eb, B:48:0x00f0, B:50:0x00f5, B:72:0x0117, B:76:0x011f, B:78:0x0124, B:80:0x0129), top: B:105:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:76:0x011f A[Catch: IOException -> 0x011b, TryCatch #13 {IOException -> 0x011b, blocks: (B:44:0x00e6, B:46:0x00eb, B:48:0x00f0, B:50:0x00f5, B:72:0x0117, B:76:0x011f, B:78:0x0124, B:80:0x0129), top: B:105:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0124 A[Catch: IOException -> 0x011b, TryCatch #13 {IOException -> 0x011b, blocks: (B:44:0x00e6, B:46:0x00eb, B:48:0x00f0, B:50:0x00f5, B:72:0x0117, B:76:0x011f, B:78:0x0124, B:80:0x0129), top: B:105:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:80:0x0129 A[Catch: IOException -> 0x011b, TRY_LEAVE, TryCatch #13 {IOException -> 0x011b, blocks: (B:44:0x00e6, B:46:0x00eb, B:48:0x00f0, B:50:0x00f5, B:72:0x0117, B:76:0x011f, B:78:0x0124, B:80:0x0129), top: B:105:0x000f }] */
    /* JADX WARN: Code duplicated, block: B:90:0x013b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:91:0x013d A[Catch: IOException -> 0x0139, TryCatch #0 {IOException -> 0x0139, blocks: (B:87:0x0135, B:91:0x013d, B:93:0x0142, B:95:0x0147), top: B:99:0x0135 }] */
    /* JADX WARN: Code duplicated, block: B:93:0x0142 A[Catch: IOException -> 0x0139, TryCatch #0 {IOException -> 0x0139, blocks: (B:87:0x0135, B:91:0x013d, B:93:0x0142, B:95:0x0147), top: B:99:0x0135 }] */
    /* JADX WARN: Code duplicated, block: B:95:0x0147 A[Catch: IOException -> 0x0139, TRY_LEAVE, TryCatch #0 {IOException -> 0x0139, blocks: (B:87:0x0135, B:91:0x013d, B:93:0x0142, B:95:0x0147), top: B:99:0x0135 }] */
    /* JADX WARN: Code duplicated, block: B:99:0x0135 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    private void readProcFileAndInit() throws Throwable {
        FileReader fileReader;
        BufferedReader bufferedReader;
        FileReader fileReader2;
        BufferedReader bufferedReader2;
        this.mCaptureCounts = 0;
        this.mPlaybackCounts = 0;
        this.mCardsList.clear();
        BufferedReader bufferedReader3 = null;
        try {
            try {
                try {
                    try {
                        fileReader2 = new FileReader("/proc/asound/cards");
                        try {
                            try {
                                fileReader = new FileReader("/proc/asound/pcm");
                                while (true) {
                                    try {
                                        String line = bufferedReader.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        int iLastIndexOf = line.lastIndexOf(" - ");
                                        if (iLastIndexOf > 0) {
                                            SndElement sndElement = new SndElement();
                                            String strTrim = line.substring(0, 2).trim();
                                            String strSubstring = line.substring(iLastIndexOf + 3);
                                            if (line.indexOf(USBAUDIO_CARD_DRIVER_NAME) > 0) {
                                                sndElement.devType = 1;
                                            }
                                            if (line.indexOf(SPDIF_CARD_DRIVER_NAME) > 0) {
                                                sndElement.devType = 6;
                                            }
                                            sndElement.idx = strTrim;
                                            sndElement.cardName = strSubstring;
                                            logd("idx: " + strTrim + " cardname: " + strSubstring);
                                            this.mCardsList.add(sndElement);
                                        }
                                    } catch (IOException e) {
                                        e = e;
                                        bufferedReader3 = bufferedReader;
                                        try {
                                            e.printStackTrace();
                                            if (bufferedReader3 != null) {
                                                bufferedReader3.close();
                                            }
                                            if (fileReader2 != null) {
                                                fileReader2.close();
                                            }
                                            if (bufferedReader2 != null) {
                                                bufferedReader2.close();
                                            }
                                            if (fileReader != null) {
                                                fileReader.close();
                                                return;
                                            }
                                            return;
                                        } catch (Throwable th) {
                                            th = th;
                                            bufferedReader = bufferedReader3;
                                            if (bufferedReader != null) {
                                                try {
                                                    bufferedReader.close();
                                                    if (fileReader2 != null) {
                                                        fileReader2.close();
                                                    }
                                                    if (bufferedReader2 != null) {
                                                        bufferedReader2.close();
                                                    }
                                                    if (fileReader != null) {
                                                        fileReader.close();
                                                    }
                                                } catch (IOException e2) {
                                                    e2.printStackTrace();
                                                    throw th;
                                                }
                                            } else {
                                                if (fileReader2 != null) {
                                                    fileReader2.close();
                                                }
                                                if (bufferedReader2 != null) {
                                                    bufferedReader2.close();
                                                }
                                                if (fileReader != null) {
                                                    fileReader.close();
                                                }
                                            }
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (bufferedReader != null) {
                                            bufferedReader.close();
                                            if (fileReader2 != null) {
                                                fileReader2.close();
                                            }
                                            if (bufferedReader2 != null) {
                                                bufferedReader2.close();
                                            }
                                            if (fileReader != null) {
                                                fileReader.close();
                                            }
                                        } else {
                                            if (fileReader2 != null) {
                                                fileReader2.close();
                                            }
                                            if (bufferedReader2 != null) {
                                                bufferedReader2.close();
                                            }
                                            if (fileReader != null) {
                                                fileReader.close();
                                            }
                                        }
                                        throw th;
                                    }
                                }
                                while (true) {
                                    String line2 = bufferedReader2.readLine();
                                    if (line2 == null) {
                                        break;
                                    }
                                    String strSubstring2 = line2.substring(1, 2);
                                    logd("idx: " + strSubstring2);
                                    for (SndElement sndElement2 : this.mCardsList) {
                                        if (sndElement2.idx.equals(strSubstring2)) {
                                            if (line2.indexOf("capture") > 0) {
                                                sndElement2.hasCapture = true;
                                            }
                                            if (line2.indexOf("playback") <= 0) {
                                                break;
                                            }
                                            sndElement2.hasPlayback = true;
                                            break;
                                        }
                                    }
                                }
                            } catch (FileNotFoundException e3) {
                                e = e3;
                                e.printStackTrace();
                                fileReader = null;
                            }
                        } catch (IOException e4) {
                            e = e4;
                            fileReader = null;
                            bufferedReader2 = null;
                            e.printStackTrace();
                            if (bufferedReader3 != null) {
                                bufferedReader3.close();
                            }
                            if (fileReader2 != null) {
                                fileReader2.close();
                            }
                            if (bufferedReader2 != null) {
                                bufferedReader2.close();
                            }
                            if (fileReader != null) {
                                fileReader.close();
                                return;
                            }
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            fileReader = null;
                            bufferedReader = null;
                            bufferedReader2 = bufferedReader;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                if (fileReader2 != null) {
                                    fileReader2.close();
                                }
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                if (fileReader != null) {
                                    fileReader.close();
                                }
                            } else {
                                if (fileReader2 != null) {
                                    fileReader2.close();
                                }
                                if (bufferedReader2 != null) {
                                    bufferedReader2.close();
                                }
                                if (fileReader != null) {
                                    fileReader.close();
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return;
                    }
                } catch (FileNotFoundException e6) {
                    e = e6;
                    fileReader2 = null;
                } catch (IOException e7) {
                    e = e7;
                    fileReader = null;
                    fileReader2 = null;
                    bufferedReader2 = null;
                    e.printStackTrace();
                    if (bufferedReader3 != null) {
                        bufferedReader3.close();
                    }
                    if (fileReader2 != null) {
                        fileReader2.close();
                    }
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (fileReader != null) {
                        fileReader.close();
                        return;
                    }
                    return;
                } catch (Throwable th4) {
                    th = th4;
                    fileReader = null;
                    bufferedReader = null;
                    fileReader2 = null;
                    bufferedReader2 = null;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                    } else {
                        if (fileReader2 != null) {
                            fileReader2.close();
                        }
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (fileReader != null) {
                            fileReader.close();
                        }
                    }
                    throw th;
                }
                bufferedReader2 = new BufferedReader(fileReader);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                    return;
                }
                return;
            } catch (IOException e8) {
                e = e8;
                bufferedReader2 = null;
            } catch (Throwable th5) {
                th = th5;
                bufferedReader2 = null;
            }
            bufferedReader = new BufferedReader(fileReader2);
        } catch (IOException e9) {
            e = e9;
            bufferedReader2 = null;
        } catch (Throwable th6) {
            th = th6;
            bufferedReader = null;
            bufferedReader2 = bufferedReader;
            if (bufferedReader != null) {
                bufferedReader.close();
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } else {
                if (fileReader2 != null) {
                    fileReader2.close();
                }
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isUsbAudio(String str) {
        for (SndElement sndElement : this.mCardsList) {
            if (sndElement.idx.equals(str)) {
                return sndElement.devType == 1;
            }
        }
        return false;
    }

    @Override // android.preference.PreferenceActivity
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        logd("onPreferenceTreeClick: " + preference.getTitle().toString());
        return true;
    }
}
