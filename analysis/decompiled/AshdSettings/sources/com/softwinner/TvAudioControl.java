package com.softwinner;

import android.content.ContentResolver;
import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManagerEx;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class TvAudioControl {
    private static final int BAND_NUM = 7;
    private static final int CURVE_NUM = 101;
    private static final boolean DEBUG = true;
    public static final int DRC_AVC = 1;
    public static final int DRC_AVCMULTI = 2;
    public static final int DRC_NIGHT = 3;
    public static final int DRC_OFF = 0;
    public static final int DRC_POWER = 4;
    public static final int DRC_VOLUME = 5;
    public static final int MODE_MOVIE = 2;
    public static final int MODE_MUSIC = 1;
    public static final int MODE_NEWS = 3;
    public static final int MODE_STANDARD = 0;
    public static final int MODE_USER = 4;
    public static final int PEQ_OFF = 0;
    public static final int PEQ_ON = 1;
    public static final int RET_FAIL = -1;
    public static final int RET_SUCCESS = 0;
    private static final String TAG = "TvAudioControl";
    private AudioManager mAudioManager;
    private AudioManagerEx mAudioManagerEx;
    private ContentResolver mContentResolver;
    private Context mContext;
    private final String[] mAudioModeMap = {"0", "1", "2", "3", "4"};
    private final String[] mAudioModeGainMap = {AudioManagerEx.STANDARD_MODE_GAIN, AudioManagerEx.MUSIC_MODE_GAIN, AudioManagerEx.MOVIE_MODE_GAIN, AudioManagerEx.NEWS_MODE_GAIN, AudioManagerEx.USER_MODE_GAIN};
    private final String[] mAudioAvcEffectMap = {AudioManagerEx.AVC_EFFECT_GAIN, AudioManagerEx.AVC_EFFECT_THRESHOLD, AudioManagerEx.AVC_EFFECT_LIMIT, AudioManagerEx.AVC_EFFECT_DECAY};

    public TvAudioControl(Context context) {
        this.mAudioManagerEx = null;
        Log.d(TAG, "new TvAudioControl");
        this.mContext = context;
        this.mContentResolver = this.mContext.getContentResolver();
        this.mAudioManager = (AudioManager) context.getSystemService("audio");
        this.mAudioManagerEx = new AudioManagerEx(this.mContext);
    }

    public int setAudioMode(int i) {
        Log.d(TAG, "setAudioMode mode=" + i);
        this.mAudioManager.setParameter(AudioManagerEx.AUDIO_MODE, Integer.toString(i));
        return 0;
    }

    public int getAudioMode() {
        Log.d(TAG, "getAudioMode");
        return matchAudioMode(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.AUDIO_MODE));
    }

    public int getAudioModeGainMin() {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.AUDIO_MODE_GAIN_MIN));
    }

    public int getAudioModeGainMax() {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.AUDIO_MODE_GAIN_MAX));
    }

    public int setAudioCustomModeBand(int i, int i2) {
        Log.d(TAG, "setAudioCustomMode band=" + i + " value=" + i2);
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(i));
        sb.append(":");
        sb.append(Integer.toString(i2));
        this.mAudioManager.setParameter(AudioManagerEx.GEQ_BAND, sb.toString());
        return 0;
    }

    public int getAudioModeBandGain(int i) {
        Log.d(TAG, "getAudioModeBandGain band=" + i);
        return getAudioModeData(getAudioMode())[i];
    }

    public int[] getAudioModeData(int i) {
        Log.d(TAG, "getAudioMode mode=" + i);
        int[] iArr = new int[7];
        String audioParameters = this.mAudioManagerEx.getAudioParameters(this.mAudioModeGainMap[i]);
        String[] strArrSplit = audioParameters.split(",");
        Log.d(TAG, "getAudioMode " + audioParameters);
        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
            if (strArrSplit[i2].length() > 0) {
                iArr[i2] = Integer.parseInt(strArrSplit[i2]);
            }
        }
        return iArr;
    }

    public int resetAudioModeSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_AUDIO_MODE, "1");
        return 0;
    }

    public int resetAudioEQModeSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_GEQ_BAND, "1");
        return 0;
    }

    public int setDRCMode(int i) {
        Log.d(TAG, "setDRCMode mode=" + i);
        this.mAudioManager.setParameter(AudioManagerEx.DRC_MODE, Integer.toString(i));
        return 0;
    }

    public int setAVCMode(int i) {
        Log.d(TAG, "setAVCMode mode=" + i);
        this.mAudioManager.setParameter(AudioManagerEx.AVC_MODE, Integer.toString(i));
        return 0;
    }

    public int setAVCParams(int i, int i2) {
        Log.d(TAG, "setAVCParams effect=" + i + ", value=" + i2);
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(i));
        sb.append(":");
        sb.append(Integer.toString(i2));
        this.mAudioManager.setParameter(AudioManagerEx.AVC_EFFECT, sb.toString());
        return 0;
    }

    public int getAVCParams(int i) {
        Log.d(TAG, "getAVCParams effect=" + i);
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(this.mAudioAvcEffectMap[i]));
    }

    public int resetAudioAvlSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_AVC, "1");
        return 0;
    }

    public int setPEQMode(int i) {
        Log.d(TAG, "setPEQMode mode=" + i);
        this.mAudioManager.setParameter(AudioManagerEx.PEQ_MODE, Integer.toString(i));
        return 0;
    }

    public int setVolumeMute(boolean z) {
        Log.d(TAG, "setVolumeMute mute=" + z);
        if (!z) {
            this.mAudioManager.setParameter(AudioManagerEx.SET_VOLUME, this.mAudioManagerEx.getAudioParameters(AudioManagerEx.SPEAKER_VOLUME));
        }
        this.mAudioManager.setParameter(AudioManagerEx.SPK_MUTE, Integer.toString(z ? 1 : 0));
        return 0;
    }

    public int setVolume(int i) {
        Log.d(TAG, "setVolume value=" + i);
        if (i < 0 || i > 100) {
            Log.e(TAG, "setVolume invalid param value=" + i);
            return -1;
        }
        this.mAudioManager.setParameter(AudioManagerEx.SPK_MUTE, "0");
        this.mAudioManager.setParameter(AudioManagerEx.SET_VOLUME, Integer.toString(i));
        return 0;
    }

    public int factorySetAudioModeBand(int i, String str, int i2) {
        Log.d(TAG, "factorySetAudioModeBand mode=" + i + " key=" + str + " value=" + i2);
        this.mAudioManager.setParameter(AudioManagerEx.AUDIO_MODE, Integer.toString(i));
        this.mAudioManager.setParameter(str, Integer.toString(i2));
        return 0;
    }

    public int factorySetVolumeCurve(int i, int i2) {
        Log.d(TAG, "factorySetVolumeCurve osd=" + i + " value=" + i2);
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(i));
        sb.append(":");
        sb.append(Integer.toString(i2));
        this.mAudioManager.setParameter(AudioManagerEx.VOLUME_CURVE, sb.toString());
        return 0;
    }

    public String getATVStandardMode() {
        return this.mAudioManagerEx.getAudioParameters(AudioManagerEx.ATV_STANDARD_MODE);
    }

    public boolean setATVStandardMode(int i) {
        this.mAudioManager.setParameter(AudioManagerEx.ATV_STANDARD_MODE, Integer.toString(i));
        return true;
    }

    public int getATVMTSInput() {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.ATV_MTS_MODE_LIST));
    }

    public int setATVMTSMode(int i) {
        this.mAudioManager.setParameter(AudioManagerEx.ATV_MTS_MODE, Integer.toString(i));
        return 0;
    }

    public int getATVMTSMode() {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.ATV_MTS_MODE));
    }

    public boolean setArcVolume(int i) {
        if (this.mAudioManager == null) {
            Log.e(TAG, "AudioManager failed");
            return false;
        }
        this.mAudioManager.setStreamVolume(3, i, 4);
        return true;
    }

    public int getArcVolume() {
        if (this.mAudioManager == null) {
            Log.e(TAG, "AudioManager failed");
            return 0;
        }
        return this.mAudioManager.getStreamVolume(3);
    }

    public boolean isArcMute() {
        if (this.mAudioManager == null) {
            Log.e(TAG, "AudioManager failed");
            return false;
        }
        return this.mAudioManager.isStreamMute(3);
    }

    public boolean setArcMute(boolean z) {
        if (this.mAudioManager == null) {
            Log.e(TAG, "AudioManager failed");
            return false;
        }
        if (z) {
            this.mAudioManager.adjustStreamVolume(3, -100, 0);
            return true;
        }
        this.mAudioManager.adjustStreamVolume(3, 100, 0);
        return true;
    }

    public int getDRCMode() {
        Log.d(TAG, "getDRCMode");
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.DRC_MODE));
    }

    public int getAVCMode() {
        Log.d(TAG, "getAVCMode");
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.AVC_MODE));
    }

    public int getPEQMode() {
        Log.d(TAG, "getPEQMode ");
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.PEQ_MODE));
    }

    public int setSPKDelay(int i) {
        Log.d(TAG, "setSPKDelay delay=" + i);
        this.mAudioManager.setParameter(AudioManagerEx.SPK_DELAY, Integer.toString(i));
        return 0;
    }

    public int getSPKDelay() {
        Log.d(TAG, "getSPKDelay");
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.SPK_DELAY));
    }

    public int setOWADelay(int i) {
        Log.d(TAG, "setOWADelay delay=" + i);
        this.mAudioManagerEx.setOWADelay(i);
        return 0;
    }

    public int getOWADelay() {
        Log.d(TAG, "setOWADelay");
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.OWA_DELAY));
    }

    public int resetAudioOutputDelaySetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_AUDIO_DELAY, "1");
        return 0;
    }

    public boolean isVolumeMute() {
        Log.d(TAG, "isVolumeMute");
        String audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.SPK_MUTE);
        return audioParameters.length() != 0 && Integer.parseInt(audioParameters) > 0;
    }

    public int setPathMute(int i, boolean z) {
        String str;
        String str2;
        String audioParameters;
        switch (i) {
            case 1:
                str = AudioManagerEx.HEADPHONE_MUTE;
                str2 = AudioManagerEx.HEADPHONE_VOLUME;
                audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.HEADPHONE_VOLUME);
                break;
            case 2:
                str = AudioManagerEx.OWA_MUTE;
                str2 = AudioManagerEx.OWA_VOLUME;
                audioParameters = "100";
                break;
            case 3:
                str = AudioManagerEx.ARC_MUTE;
                str2 = AudioManagerEx.ARC_VOLUME;
                audioParameters = "100";
                break;
            default:
                str = AudioManagerEx.SPK_MUTE;
                str2 = AudioManagerEx.SPEAKER_VOLUME;
                audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.SPEAKER_VOLUME);
                break;
        }
        if (!z) {
            this.mAudioManager.setParameter(str2, audioParameters);
        }
        this.mAudioManager.setParameter(str, Integer.toString(z ? 1 : 0));
        return 0;
    }

    public boolean isPathMute(int i) {
        String str;
        switch (i) {
            case 1:
                str = AudioManagerEx.HEADPHONE_MUTE;
                break;
            case 2:
                str = AudioManagerEx.OWA_MUTE;
                break;
            case 3:
                str = AudioManagerEx.ARC_MUTE;
                break;
            default:
                str = AudioManagerEx.SPK_MUTE;
                break;
        }
        String audioParameters = this.mAudioManagerEx.getAudioParameters(str);
        if (audioParameters.length() == 0) {
            return false;
        }
        Log.d(TAG, "Integer.parseInt(ret): " + Integer.parseInt(audioParameters));
        return Integer.parseInt(audioParameters) == 1;
    }

    public void setPathVolume(int i, int i2) {
        String str;
        String string;
        Log.d(TAG, "setPathVolume path= " + i + ", value=" + i2);
        if (i2 < 0 || i2 > 100) {
            Log.e(TAG, "setPathVolume invalid param value = " + i2);
            return;
        }
        if (isPathMute(i)) {
            return;
        }
        switch (i) {
            case 1:
                str = AudioManagerEx.HEADPHONE_VOLUME;
                string = Integer.toString(i2);
                break;
            case 2:
                str = AudioManagerEx.OWA_VOLUME;
                string = "100";
                break;
            case 3:
                str = AudioManagerEx.ARC_VOLUME;
                string = "100";
                break;
            default:
                str = AudioManagerEx.SPEAKER_VOLUME;
                string = Integer.toString(i2);
                break;
        }
        this.mAudioManager.setParameter(str, string);
    }

    public int getPathVolume(int i) {
        Log.d(TAG, "getPathVolume path= " + i);
        switch (i) {
            case 1:
                return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.HEADPHONE_VOLUME));
            case 2:
                return Integer.parseInt("100");
            case 3:
                return Integer.parseInt("100");
            default:
                String audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.SPEAKER_VOLUME);
                if (audioParameters.length() != 0) {
                    return Integer.parseInt(audioParameters);
                }
                return 5;
        }
    }

    public int getPathPrescale(int i) {
        String str;
        switch (i) {
            case 0:
                str = AudioManagerEx.ATV_PRESCALE;
                break;
            case 1:
                str = AudioManagerEx.HDMI_PRESCALE;
                break;
            case 2:
                str = AudioManagerEx.AV_PRESCALE;
                break;
            case 3:
                str = AudioManagerEx.USB_PRESCALE;
                break;
            default:
                return 32;
        }
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(str));
    }

    public void setPathPrescale(int i, int i2) {
        String str;
        switch (i) {
            case 0:
                str = AudioManagerEx.ATV_PRESCALE;
                break;
            case 1:
                str = AudioManagerEx.HDMI_PRESCALE;
                break;
            case 2:
                str = AudioManagerEx.AV_PRESCALE;
                break;
            case 3:
                str = AudioManagerEx.USB_PRESCALE;
                break;
            default:
                str = AudioManagerEx.AV_PRESCALE;
                break;
        }
        this.mAudioManager.setParameter(str, Integer.toString(i2));
    }

    public int resetAudioPreScalerGainSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_PRESCALE, "1");
        return 0;
    }

    public void setSurroundMode(int i) {
        this.mAudioManager.setParameter(AudioManagerEx.SURROUND_MODE, Integer.toString(i));
    }

    public int getSurroundMode() {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.SURROUND_MODE));
    }

    public int resetAudioSurroundSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_SURROUND, "1");
        return 0;
    }

    public void setOutputBalance(int i, int i2) {
        if (i == 0) {
            this.mAudioManager.setParameter(AudioManagerEx.BALANCE_SPEAKER, Integer.toString(i2));
        } else {
            this.mAudioManager.setParameter(AudioManagerEx.BALANCE_HEADPHONE, Integer.toString(i2));
        }
    }

    public int getOutputBalance(int i) {
        if (i == 0) {
            return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.BALANCE_SPEAKER));
        }
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.BALANCE_HEADPHONE));
    }

    public int resetAudioOutputBalanceSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_BANLANCE, "1");
        return 0;
    }

    public void setPeqBandFreq(int i, int i2) {
        this.mAudioManager.setParameter(AudioManagerEx.PEQ_BAND_FREQ, Integer.toString(i) + ":" + Integer.toString(i2));
    }

    public int getPeqBandFreq(int i) {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.PEQ_BAND_FREQ).split(",")[i - 1]);
    }

    public void setPeqBandQuality(int i, int i2) {
        this.mAudioManager.setParameter(AudioManagerEx.PEQ_BAND_QUALITY, Integer.toString(i) + ":" + Integer.toString(i2));
    }

    public int getPeqBandQuality(int i) {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.PEQ_BAND_QUALITY).split(",")[i - 1]);
    }

    public void setPeqBandGain(int i, int i2) {
        this.mAudioManager.setParameter(AudioManagerEx.PEQ_BAND_GAIN, Integer.toString(i) + ":" + Integer.toString(i2));
    }

    public int getPeqBandGain(int i) {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.PEQ_BAND_GAIN).split(",")[i - 1]);
    }

    public void setPeqOverAllGain(int i) {
        this.mAudioManager.setParameter(AudioManagerEx.PEQ_OVERALL_GAIN, Integer.toString(i));
    }

    public int getPeqOverAllGain() {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.PEQ_OVERALL_GAIN));
    }

    public int resetAudioPEQSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_PEQ, "1");
        return 0;
    }

    public void setPathDrcEanble(int i, int i2) {
        this.mAudioManager.setParameter(i == 1 ? AudioManagerEx.DRC_HEADPHONE_ENABLE : AudioManagerEx.DRC_SPEAKER_ENABLE, Integer.toString(i2));
    }

    public int getPathDrcEanble(int i) {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(i == 1 ? AudioManagerEx.DRC_HEADPHONE_ENABLE : AudioManagerEx.DRC_SPEAKER_ENABLE));
    }

    public void setPathDrcThreshold(int i, int i2) {
        this.mAudioManager.setParameter(i == 1 ? AudioManagerEx.DRC_HEADPHONE_THRESHOLD : AudioManagerEx.DRC_SPEAKER_THRESHOLD, Integer.toString(i2));
    }

    public int getPathDrcThreshold(int i) {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(i == 1 ? AudioManagerEx.DRC_HEADPHONE_THRESHOLD : AudioManagerEx.DRC_SPEAKER_THRESHOLD));
    }

    public void setPathDrcDecay(int i, int i2) {
        this.mAudioManager.setParameter(i == 1 ? AudioManagerEx.DRC_HEADPHONE_DECAY : AudioManagerEx.DRC_SPEAKER_DECAY, Integer.toString(i2));
    }

    public int getPathDrcDecay(int i) {
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(i == 1 ? AudioManagerEx.DRC_HEADPHONE_DECAY : AudioManagerEx.DRC_SPEAKER_DECAY));
    }

    public int resetAudioDrcSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_DRC, "1");
        return 0;
    }

    public void setOutPathGain(int i, int i2) {
        String str;
        switch (i) {
            case 1:
                str = AudioManagerEx.GAIN_HEADPHONE;
                break;
            case 2:
                str = AudioManagerEx.GAIN_OWA;
                break;
            default:
                str = AudioManagerEx.GAIN_SPEAKER;
                break;
        }
        this.mAudioManager.setParameter(str, Integer.toString(i2));
    }

    public int getOutPathGain(int i) {
        String audioParameters;
        switch (i) {
            case 1:
                audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.GAIN_HEADPHONE);
                break;
            case 2:
                audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.GAIN_OWA);
                break;
            default:
                audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.GAIN_SPEAKER);
                break;
        }
        return Integer.parseInt(audioParameters);
    }

    public int resetAudioOutputGainSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_OUTPUT_GAIN, "1");
        return 0;
    }

    public int getVolume(boolean z) {
        Log.d(TAG, "getVolume fromFile=" + z);
        return Integer.parseInt(this.mAudioManagerEx.getAudioParameters(AudioManagerEx.SPEAKER_VOLUME));
    }

    public int[] getVolumeCurveData() {
        Log.d(TAG, "getVolumeCurveData");
        int[] iArr = new int[101];
        String audioParameters = this.mAudioManagerEx.getAudioParameters(AudioManagerEx.VOLUME_CURVE);
        String[] strArrSplit = audioParameters.split(",");
        Log.d(TAG, "getVolumeCurveData " + audioParameters);
        for (int i = 0; i < strArrSplit.length; i++) {
            iArr[i] = Integer.parseInt(strArrSplit[i]);
        }
        return iArr;
    }

    public int resetAudioVolumeCurveSetting() {
        this.mAudioManager.setParameter(AudioManagerEx.RESET_VOLUME_CURVE, "1");
        return 0;
    }

    public int resetAllSettings() {
        setAudioMode(1);
        this.mAudioManager.setParameter(AudioManagerEx.DRC_MODE, Integer.toString(0));
        this.mAudioManager.setParameter(AudioManagerEx.PEQ_MODE, Integer.toString(0));
        return 0;
    }

    private int matchAudioMode(String str) {
        int i = 0;
        while (i < this.mAudioModeMap.length && !this.mAudioModeMap[i].equals(str)) {
            i++;
        }
        if (i != this.mAudioModeMap.length) {
            return i;
        }
        return 0;
    }

    public static String arrayToString(int[] iArr) {
        if (iArr == null) {
            return "null";
        }
        int length = iArr.length - 1;
        if (length == -1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (true) {
            sb.append(iArr[i]);
            if (i == length) {
                return sb.toString();
            }
            sb.append(",");
            i++;
        }
    }
}
