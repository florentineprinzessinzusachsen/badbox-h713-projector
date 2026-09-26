package com.softwinner.tv;

import android.content.Context;
import android.hardware.hdmi.IHdmiControlService;
import android.media.AudioManagerEx;
import android.os.ServiceManager;
import android.util.Log;
import com.softwinner.TvAudioControl;
import com.softwinner.tv.common.AwTvAudioTypes;
import com.softwinner.tv.data.AwChannelListFilter;
import com.softwinner.tv.data.AwTvChannelInfo;
import com.softwinner.tv.module.AwTvHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvAudioManager {
    private static final boolean DEBUG = true;
    private static final String TAG = "AwTvAudioManager";
    private static AwTvAudioManager sAwTvAudioManager;
    private TvAudioControl mAudioControl;
    private AudioManagerEx mAudioManagerEx;
    Context mContext;
    private List<AwTvAudioTypes.EnumATVMtsOutput> mCurMtsList = null;
    private IHdmiControlService mHdmiControlService;

    private void debug(String str) {
        Log.d(TAG, str);
    }

    public AwTvAudioManager(Context context) {
        this.mAudioControl = null;
        this.mAudioManagerEx = null;
        this.mAudioControl = new TvAudioControl(context);
        this.mAudioManagerEx = new AudioManagerEx(context);
        this.mContext = context;
    }

    public static AwTvAudioManager getInstance(Context context) {
        if (sAwTvAudioManager == null) {
            sAwTvAudioManager = new AwTvAudioManager(context);
        }
        return sAwTvAudioManager;
    }

    public boolean setAvlEnable(boolean z) {
        debug("setAvlEnable value =" + (z ? 1 : 0));
        try {
            this.mAudioControl.setAVCMode(z ? 1 : 0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public boolean getAvlEnable() {
        int aVCMode;
        try {
            aVCMode = this.mAudioControl.getAVCMode();
        } catch (Exception e) {
            e.printStackTrace();
            aVCMode = 0;
        }
        debug("getAvlEnable avlEnable =" + aVCMode);
        return aVCMode > 0;
    }

    public boolean setAvlGain(AwTvAudioTypes.EnumAVCGainValue enumAVCGainValue) {
        debug("setAvlGain avcGain =" + enumAVCGainValue);
        try {
            this.mAudioControl.setAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_GAIN.ordinal(), enumAVCGainValue.getValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public AwTvAudioTypes.EnumAVCGainValue getAvlGain() {
        debug("getAvlGain");
        AwTvAudioTypes.EnumAVCGainValue enumAVCGainValue = AwTvAudioTypes.EnumAVCGainValue.getEnum(this.mAudioControl.getAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_GAIN.ordinal()));
        debug("getAvlGain: " + enumAVCGainValue);
        return enumAVCGainValue;
    }

    public boolean setAvlThreshold(AwTvAudioTypes.EnumAVCThresholdValue enumAVCThresholdValue) {
        debug("setAvlThreshold avcThreshold =" + enumAVCThresholdValue);
        try {
            this.mAudioControl.setAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_THRESHOLD.ordinal(), enumAVCThresholdValue.getValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public AwTvAudioTypes.EnumAVCThresholdValue getAvlThreshold() {
        debug("getAvlThreshold");
        AwTvAudioTypes.EnumAVCThresholdValue enumAVCThresholdValue = AwTvAudioTypes.EnumAVCThresholdValue.getEnum(this.mAudioControl.getAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_THRESHOLD.ordinal()));
        debug("getAvlThreshold: " + enumAVCThresholdValue);
        return enumAVCThresholdValue;
    }

    public boolean setAvlDecayTime(AwTvAudioTypes.EnumAVCDecayTimeValue enumAVCDecayTimeValue) {
        debug("setAvlDecayTime avcDecay =" + enumAVCDecayTimeValue);
        try {
            this.mAudioControl.setAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_DECAY.ordinal(), enumAVCDecayTimeValue.getValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public AwTvAudioTypes.EnumAVCDecayTimeValue getAvlDecayTime() {
        debug("getAvlDecayTime");
        AwTvAudioTypes.EnumAVCDecayTimeValue enumAVCDecayTimeValue = AwTvAudioTypes.EnumAVCDecayTimeValue.getEnum(this.mAudioControl.getAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_DECAY.ordinal()));
        debug("getAvlDecayTime: " + enumAVCDecayTimeValue);
        return enumAVCDecayTimeValue;
    }

    public boolean setAvlLimit(int i) {
        debug("setAvlLimit arcLimit =" + i);
        if (i < 0 || i > 15) {
            debug("Avl Limit range: 0~15, -18 ~ -3dBFS");
            return false;
        }
        try {
            this.mAudioControl.setAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_LIMIT.ordinal(), i);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    public int getAvlLimit() {
        int aVCParams = this.mAudioControl.getAVCParams(AwTvAudioTypes.EnumAVCEffectType.E_AW_AVC_EFFECT_LIMIT.ordinal());
        debug("getAvlLimit: " + aVCParams);
        return aVCParams;
    }

    public boolean resetAudioAvlSetting() {
        this.mAudioControl.resetAudioAvlSetting();
        return true;
    }

    public boolean setAudioMode(AwTvAudioTypes.EnumGEQType enumGEQType) {
        debug("setAudioMode soundMode = " + enumGEQType);
        this.mAudioControl.setAudioMode(enumGEQType.ordinal());
        return true;
    }

    public AwTvAudioTypes.EnumGEQType getAudioMode() {
        AwTvAudioTypes.EnumGEQType enumGEQType = AwTvAudioTypes.EnumGEQType.values()[this.mAudioControl.getAudioMode()];
        debug("getAudioMode soundMode = " + enumGEQType);
        return enumGEQType;
    }

    public boolean getAudioModeSupport(AwTvAudioTypes.EnumGEQType enumGEQType) {
        switch (enumGEQType) {
            case E_AW_GEQ_TYPE_STANDARD:
            case E_AW_GEQ_TYPE_MUSIC:
            case E_AW_GEQ_TYPE_MOVIE:
            case E_AW_GEQ_TYPE_NEWS:
            case E_AW_GEQ_TYPE_USER:
                return true;
            default:
                return false;
        }
    }

    public boolean resetAudioModeSetting() {
        this.mAudioControl.resetAudioModeSetting();
        return true;
    }

    public int getAudioEqBand(AwTvAudioTypes.EnumGEQBand enumGEQBand) {
        int audioModeBandGain = this.mAudioControl.getAudioModeBandGain(enumGEQBand.ordinal());
        debug("getAudioEqBand intBandValue =" + audioModeBandGain + ", enumGEQBand: " + enumGEQBand);
        return audioModeBandGain;
    }

    public boolean setAudioEqBand(AwTvAudioTypes.EnumGEQBand enumGEQBand, int i) {
        if (i < this.mAudioControl.getAudioModeGainMin() || i > this.mAudioControl.getAudioModeGainMax()) {
            debug("setAudioEqBand gain range: " + this.mAudioControl.getAudioModeGainMin() + " ~ " + this.mAudioControl.getAudioModeGainMax());
            return false;
        }
        this.mAudioControl.setAudioCustomModeBand(enumGEQBand.ordinal(), i);
        return true;
    }

    public boolean resetAudioEQModeSetting() {
        this.mAudioControl.resetAudioEQModeSetting();
        return true;
    }

    public void setSoundOutMode(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode) {
        debug("setSoundOutMode: " + enumAudioOutMode);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(enumAudioOutMode.getValue());
        ArrayList<String> activeAudioDevices = this.mAudioManagerEx.getActiveAudioDevices(AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
        if (AwTvAudioTypes.EnumAudioOutMode.getEnum(activeAudioDevices.get(0)) == enumAudioOutMode) {
            return;
        }
        this.mAudioManagerEx.setAudioOutPreDevice(activeAudioDevices.get(0));
        this.mAudioManagerEx.setAudioDeviceActive(arrayList, AudioManagerEx.AUDIO_OUTPUT_ACTIVE);
    }

    public AwTvAudioTypes.EnumAudioOutMode getSoundOutMode() {
        debug("getSoundOutMode");
        return AwTvAudioTypes.EnumAudioOutMode.getEnum(this.mAudioManagerEx.getActiveAudioDevices(AudioManagerEx.AUDIO_OUTPUT_ACTIVE).get(0));
    }

    public AwTvAudioTypes.EnumAudioOutMode getPreSoundOutMode() {
        debug("getPreSoundOutMode");
        AwTvAudioTypes.EnumAudioOutMode soundOutMode = AwTvAudioTypes.EnumAudioOutMode.getEnum(this.mAudioManagerEx.getPreAudioOutDevice());
        if (soundOutMode == null) {
            soundOutMode = getSoundOutMode();
        }
        debug("getPreSoundOutMode: " + soundOutMode);
        return soundOutMode;
    }

    public boolean resetSoundOutModeSetting() {
        setSoundOutMode(AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER);
        return true;
    }

    public boolean setAudioOWAOutMode(AwTvAudioTypes.EnumAudioOWAOutMode enumAudioOWAOutMode) {
        debug("setAudioOWAOutMode: " + enumAudioOWAOutMode);
        switch (enumAudioOWAOutMode) {
            case E_AW_AUDIO_DIGITAL_RAW:
            case E_AW_AUDIO_DIGITAL_AUTO:
                this.mAudioManagerEx.setAudioPassThroughMode(1);
                return true;
            default:
                this.mAudioManagerEx.setAudioPassThroughMode(0);
                return true;
        }
    }

    public AwTvAudioTypes.EnumAudioOWAOutMode getAudioOWAOutMode() {
        debug("getAudioOWAOutMode");
        switch (this.mAudioManagerEx.getAudioPassThroughMode()) {
            case 0:
                return AwTvAudioTypes.EnumAudioOWAOutMode.E_AW_AUDIO_DIGITAL_PCM;
            case 1:
                return AwTvAudioTypes.EnumAudioOWAOutMode.E_AW_AUDIO_DIGITAL_RAW;
            default:
                return AwTvAudioTypes.EnumAudioOWAOutMode.E_AW_AUDIO_DIGITAL_PCM;
        }
    }

    public boolean getAudioOWAOutModeSupport(AwTvAudioTypes.EnumAudioOWAOutMode enumAudioOWAOutMode) {
        int i = AnonymousClass1.$SwitchMap$com$softwinner$tv$common$AwTvAudioTypes$EnumAudioOWAOutMode[enumAudioOWAOutMode.ordinal()];
        return i == 1 || i == 3;
    }

    public boolean resetAudioOWAOutSetting() {
        setAudioOWAOutMode(AwTvAudioTypes.EnumAudioOWAOutMode.E_AW_AUDIO_DIGITAL_PCM);
        return true;
    }

    public boolean setAudioVolumeCurvePoint(int i, AwTvAudioTypes.EnumAudioCurveIdndex enumAudioCurveIdndex, int i2) {
        if (i2 < -512 || i2 > 120) {
            return false;
        }
        debug("setAudioVolumeCurvePoint vendorCurveIndex: " + enumAudioCurveIdndex + ", volumeDB: " + i2);
        this.mAudioControl.factorySetVolumeCurve(enumAudioCurveIdndex.ordinal(), i2);
        return true;
    }

    public int getAudioVolumeCurvePoint(int i, AwTvAudioTypes.EnumAudioCurveIdndex enumAudioCurveIdndex) {
        debug("getAudioVolumeCurvePoint vendorCurveIndex: " + enumAudioCurveIdndex);
        return this.mAudioControl.getVolumeCurveData()[enumAudioCurveIdndex.ordinal()];
    }

    public int[] getAudioVolumeCurveData(int i) {
        debug("getAudioVolumeCurveData");
        return this.mAudioControl.getVolumeCurveData();
    }

    public boolean resetAudioVolumeCurveSetting() {
        this.mAudioControl.resetAudioVolumeCurveSetting();
        return true;
    }

    public boolean setAudioOutputVolume(int i, int i2) {
        if ((i2 < 0) || (i2 > 100)) {
            return false;
        }
        debug("setAudioOutputVolume  audioPath: " + i + ": " + i2);
        this.mAudioControl.setPathVolume(i, i2);
        return true;
    }

    public int getAudioOutputVolume(int i) {
        debug("getAudioOutputVolume  " + i);
        return this.mAudioControl.getPathVolume(i);
    }

    public boolean setAudioMute(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode, boolean z) {
        debug("setAudioMute  " + enumAudioOutMode + ": " + z);
        return this.mAudioControl.setPathMute(enumAudioOutMode.ordinal(), z) == 0;
    }

    public boolean getAudioMute(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode) {
        debug("getAudioMute " + enumAudioOutMode);
        return this.mAudioControl.isPathMute(enumAudioOutMode.ordinal());
    }

    public AwTvAudioTypes.EnumATVStandardMode getATVStandardModeFromNative() {
        String aTVStandardMode = this.mAudioControl.getATVStandardMode();
        debug("getATVStandardModeFromNative: " + aTVStandardMode);
        return AwTvAudioTypes.EnumATVStandardMode.getEnum(aTVStandardMode);
    }

    public boolean setATVStandardMode(AwTvAudioTypes.EnumATVStandardMode enumATVStandardMode) {
        boolean aTVStandardMode = this.mAudioControl.setATVStandardMode(enumATVStandardMode.ordinal());
        debug("setATVStandardMode: " + enumATVStandardMode);
        return aTVStandardMode;
    }

    public boolean setATVAudioSys(int i) {
        boolean aTVStandardMode = this.mAudioControl.setATVStandardMode(i);
        debug("setATVStandardMode: " + i);
        return aTVStandardMode;
    }

    public List<AwTvAudioTypes.EnumATVMtsOutput> getATVMTSOptionlist() {
        ArrayList arrayList = new ArrayList();
        int aTVMTSInput = this.mAudioControl.getATVMTSInput();
        AwTvChannelInfo currentChannelInfo = AwTvHelper.getInstance(this.mContext).getCurrentChannelInfo(2);
        int audioMts = currentChannelInfo.getAudioMts();
        Log.d(TAG, "audioMts: " + audioMts + "  input: " + aTVMTSInput);
        if (aTVMTSInput != audioMts) {
            currentChannelInfo.setAudioMts(aTVMTSInput);
            AwTvHelper.getInstance(this.mContext).updateTvChannelToProvider(currentChannelInfo);
        }
        switch (AwTvAudioTypes.EnumATVMTSInput.values()[aTVMTSInput]) {
            case E_AW_ATV_MTS_FM_MONO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_FM_MONO);
                break;
            case E_AW_ATV_MTS_AM_MONO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_AM_MONO);
                break;
            case E_AW_ATV_MTS_A2_MONO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_A2_MONO);
                break;
            case E_AW_ATV_MTS_A2_STEREO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_A2_MONO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_A2_STEREO);
                break;
            case E_AW_ATV_MTS_A2_DUAL:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_A2_MONO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_A2_DUAL1);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_A2_DUAL2);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_A2_DUAL1_PLUS_DUAL2);
                break;
            case E_AW_ATV_MTS_FM_MONO_NICAM_MONO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_NICAM_MONO);
                break;
            case E_AW_ATV_MTS_FM_MONO_NICAM_STEREO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_NICAM_MONO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_NICAM_STEREO);
                break;
            case E_AW_ATV_MTS_FM_MONO_NICAM_DUAL:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_NICAM_MONO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_NICAM_DUAL1);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_NICAM_DUAL2);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_NICAM_DUAL1_PLUS_DUAL2);
                break;
            case E_AW_ATV_MTS_BTSC_MONO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_MONO);
                break;
            case E_AW_ATV_MTS_BTSC_STEREO:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_MONO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_STEREO);
                break;
            case E_AW_ATV_MTS_BTSC_MONO_SAP:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_MONO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_SAP);
                break;
            case E_AW_ATV_MTS_BTSC_STEREO_SAP:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_MONO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_STEREO);
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_BTSC_SAP);
                break;
            case E_AW_ATV_MTS_JAPAN_EIAJ:
                arrayList.add(AwTvAudioTypes.EnumATVMtsOutput.E_AW_ATV_MTS_OUT_JAPAN_EIAJ);
                break;
        }
        this.mCurMtsList = arrayList;
        return arrayList;
    }

    public boolean setATVPlayedMTSMode(int i) {
        this.mAudioControl.setATVMTSMode(i);
        return true;
    }

    public boolean setATVMTSMode(AwTvAudioTypes.EnumATVMtsOutput enumATVMtsOutput) {
        Log.d(TAG, "setATVMTSMode output: " + enumATVMtsOutput);
        AwTvChannelInfo currentChannelInfo = AwTvHelper.getInstance(this.mContext).getCurrentChannelInfo(2);
        currentChannelInfo.setAudioOutMts(enumATVMtsOutput.ordinal());
        currentChannelInfo.setAudioManualOutMts(enumATVMtsOutput.ordinal());
        AwTvHelper.getInstance(this.mContext).updateTvChannelToProvider(currentChannelInfo);
        this.mAudioControl.setATVMTSMode(enumATVMtsOutput.ordinal());
        return true;
    }

    public AwTvAudioTypes.EnumATVMtsOutput getATVMTSMode() {
        int aTVMTSMode = this.mAudioControl.getATVMTSMode();
        AwTvChannelInfo currentChannelInfo = AwTvHelper.getInstance(this.mContext).getCurrentChannelInfo(2);
        int audioOutMts = currentChannelInfo.getAudioOutMts();
        Log.d(TAG, "audiooutMts: " + audioOutMts + "  output: " + aTVMTSMode);
        if (aTVMTSMode != audioOutMts) {
            currentChannelInfo.setAudioOutMts(aTVMTSMode);
            AwTvHelper.getInstance(this.mContext).updateTvChannelToProvider(currentChannelInfo);
        }
        return AwTvAudioTypes.EnumATVMtsOutput.values()[aTVMTSMode];
    }

    public boolean resetAudioATVMTSMode() {
        for (AwTvChannelInfo awTvChannelInfo : AwTvHelper.getInstance(this.mContext).getChannelListByFilter(AwChannelListFilter.ENUM_CHANNEL_LIST_FILTER_ATV_ALL, 2, null)) {
            awTvChannelInfo.setAudioManualOutMts(-1);
            awTvChannelInfo.setAudioOutMts(0);
            awTvChannelInfo.setAudioMts(0);
            AwTvHelper.getInstance(this.mContext).updateTvChannelToProvider(awTvChannelInfo);
        }
        AwTvChannelInfo currentChannelInfo = AwTvHelper.getInstance(this.mContext).getCurrentChannelInfo(2);
        currentChannelInfo.setAudioOutMts(0);
        AwTvHelper.getInstance(this.mContext).updateTvChannelToProvider(currentChannelInfo);
        this.mAudioControl.setATVMTSMode(0);
        return true;
    }

    public boolean setAudioPreScalerGain(int i, int i2) {
        debug("setAudioPreScalerGain: inputSource(" + i + "): " + i2);
        this.mAudioControl.setPathPrescale(i, i2);
        return true;
    }

    public int getAudioPreScalerGain(int i) {
        int pathPrescale = this.mAudioControl.getPathPrescale(i);
        debug("getAudioPreScalerGain: inputSource(" + i + "): " + pathPrescale);
        return pathPrescale;
    }

    public boolean resetAudioPreScalerGainSetting() {
        this.mAudioControl.resetAudioPreScalerGainSetting();
        return true;
    }

    public boolean setAudioSurroundMode(AwTvAudioTypes.EnumSurroundMode enumSurroundMode) {
        debug("setAudioSurroundMode: " + enumSurroundMode);
        this.mAudioControl.setSurroundMode(enumSurroundMode.getValue());
        return true;
    }

    public AwTvAudioTypes.EnumSurroundMode getAudioSurroundMode() {
        int surroundMode = this.mAudioControl.getSurroundMode();
        debug("getAudioSurroundMode: " + surroundMode + ", " + AwTvAudioTypes.EnumSurroundMode.getEnum(surroundMode));
        return AwTvAudioTypes.EnumSurroundMode.getEnum(surroundMode);
    }

    public boolean resetAudioSurroundSetting() {
        this.mAudioControl.resetAudioSurroundSetting();
        return true;
    }

    public boolean setAudioOutputBalance(int i, int i2) {
        if (i == 0 || i == 1) {
            if (i2 < -50 || i2 > 50) {
                debug("setAudioOutputBalance out of range.");
                return false;
            }
            this.mAudioControl.setOutputBalance(i, i2);
        }
        return true;
    }

    public int getAudioOutputBalance(int i) {
        int outputBalance = (i == 0 || i == 1) ? this.mAudioControl.getOutputBalance(i) : 0;
        debug("getAudioOutputBalance(" + i + "): " + outputBalance);
        return outputBalance;
    }

    public boolean resetAudioOutputBalanceSetting() {
        this.mAudioControl.resetAudioOutputBalanceSetting();
        return true;
    }

    public boolean setAudioPEQEnable(boolean z) {
        debug("setAudioPEQEnable: " + z);
        return this.mAudioControl.setPEQMode(z ? 1 : 0) == 0;
    }

    public boolean getAudioPEQEnable() {
        debug("getAudioPEQEnable");
        return this.mAudioControl.getPEQMode() == 1;
    }

    public boolean setAudioPEQFreq(int i, int i2) {
        if (i > 10 || i < 0) {
            return false;
        }
        if ((i == 1 || i == 6) && (i2 > 2047 || i2 < 1)) {
            debug("setAudioPEQFreq(band " + i + ") freq range: 1Hz ~ 2047Hz, step: 1Hz");
            return false;
        }
        if (((i >= 2 && i <= 5) || (i >= 7 && i <= 10)) && (i2 > 20470 || i2 < 10 || i2 % 10 != 0)) {
            debug("setAudioPEQFreq(band " + i + ") freq range: 10Hz ~ 20470Hz, step: 10Hz");
            return false;
        }
        this.mAudioControl.setPeqBandFreq(i, i2);
        return true;
    }

    public int getAudioPEQFreq(int i) {
        int peqBandFreq = this.mAudioControl.getPeqBandFreq(i);
        debug("getAudioPEQFreq(" + i + "): " + peqBandFreq);
        return peqBandFreq;
    }

    public boolean setAudioPEQRange(int i, int i2) {
        if (i > 10 || i < 0) {
            return false;
        }
        if ((i < 4 || i > 7) && (i2 == 0 || i2 == 1)) {
            debug("setAudioPEQRange(band " + i + ") low/high shelf filter only support band 4~7");
            return false;
        }
        if (i2 < 0 || i2 > 255) {
            debug("setAudioPEQRange(band " + i + ") Q range: 0x00(low shelf filter) ~ 0xFF(31.750), step: 1(0.125)");
            return false;
        }
        this.mAudioControl.setPeqBandQuality(i, i2);
        return true;
    }

    public int getAudioPEQRange(int i) {
        int peqBandQuality = this.mAudioControl.getPeqBandQuality(i);
        debug("getAudioPEQRange(" + i + "): " + peqBandQuality);
        return peqBandQuality;
    }

    public boolean setAudioPEQGain(int i, int i2) {
        if (i > 10 || i < 0) {
            return false;
        }
        if (i2 < -19 || i2 > 12) {
            debug("setAudioPEQGain(band " + i + ") gain range: 0x00(Notch Filter), 0x01(-18db) ~ 0xFF(+12db), step: 1(1db)");
            return false;
        }
        this.mAudioControl.setPeqBandGain(i, i2);
        return true;
    }

    public int getAudioPEQGain(int i) {
        int peqBandGain = this.mAudioControl.getPeqBandGain(i);
        debug("getAudioPEQGain(" + i + "): " + peqBandGain);
        return peqBandGain;
    }

    public boolean setAudioOverAllGain(int i) {
        if (i < 0 || i > 176) {
            debug("setAudioOverAllGain gain range: 0~176(-32db ~ +12db), step: 0.25db");
            return false;
        }
        this.mAudioControl.setPeqOverAllGain(i);
        return true;
    }

    public int getAudioOverAllGain() {
        int peqOverAllGain = this.mAudioControl.getPeqOverAllGain();
        debug("getAudioOverAllGain: " + peqOverAllGain);
        return peqOverAllGain;
    }

    public boolean resetAudioPEQSetting() {
        this.mAudioControl.resetAudioPEQSetting();
        return true;
    }

    public boolean setPathDrcEanble(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode, boolean z) {
        debug("setPathDrcEanble " + enumAudioOutMode + ": " + z);
        if (enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER && enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_HEADPHONE) {
            debug("setPathDrcEanble only support speaker and headphone");
            return false;
        }
        this.mAudioControl.setPathDrcEanble(enumAudioOutMode.ordinal(), z ? 1 : 0);
        return true;
    }

    public boolean getPathDrcEanble(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode) {
        debug("getPathDrcEanble " + enumAudioOutMode + ": enumAudioOutMode.ordinal()");
        return this.mAudioControl.getPathDrcEanble(enumAudioOutMode.ordinal()) == 1;
    }

    public boolean setPathDrcThreshold(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode, int i) {
        debug("setPathDrcThreshold " + enumAudioOutMode + ": " + i);
        if (enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER && enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_HEADPHONE) {
            debug("setPathDrcThreshold only support speaker and headphone");
            return false;
        }
        this.mAudioControl.setPathDrcThreshold(enumAudioOutMode.ordinal(), i);
        return true;
    }

    public int getPathDrcThreshold(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode) {
        debug("getPathDrcThreshold " + enumAudioOutMode + ": enumAudioOutMode.ordinal()");
        return this.mAudioControl.getPathDrcThreshold(enumAudioOutMode.ordinal());
    }

    public boolean setPathDrcDecay(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode, int i) {
        debug("setPathDrcDecay " + enumAudioOutMode + ": " + i);
        if (enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER && enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_HEADPHONE) {
            debug("setPathDrcDecay only support speaker and headphone");
            return false;
        }
        this.mAudioControl.setPathDrcDecay(enumAudioOutMode.ordinal(), i);
        return true;
    }

    public int getPathDrcDecay(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode) {
        debug("getPathDrcDecay " + enumAudioOutMode + ": enumAudioOutMode.ordinal()");
        return this.mAudioControl.getPathDrcDecay(enumAudioOutMode.ordinal());
    }

    public boolean resetAudioDrcSetting() {
        this.mAudioControl.resetAudioDrcSetting();
        return true;
    }

    public boolean setOutputGain(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode, int i) {
        debug("setOutputGain " + enumAudioOutMode + ": " + i);
        if (enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER && enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_HEADPHONE && enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_OWA) {
            debug("setOutputGain only support speaker, headphone, owa");
            return false;
        }
        if (enumAudioOutMode == AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER && (i < 0 || i > 31)) {
            debug("setOutputGain speaker gain range: 0 ~ 31");
        }
        if (enumAudioOutMode == AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_HEADPHONE && (i < 0 || i > 7)) {
            debug("setOutputGain headphone gain range: 0 ~ 71");
        }
        if (enumAudioOutMode == AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_OWA && (i < -50 || i > 120)) {
            debug("setOutputGain owa gain range:-50 ~ 120");
        }
        this.mAudioControl.setOutPathGain(enumAudioOutMode.ordinal(), i);
        return true;
    }

    public int getOutputGain(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode) {
        debug("getOutputGain " + enumAudioOutMode + ": enumAudioOutMode.ordinal()");
        return this.mAudioControl.getOutPathGain(enumAudioOutMode.ordinal());
    }

    public boolean resetAudioOutputGainSetting() {
        this.mAudioControl.resetAudioOutputGainSetting();
        return true;
    }

    public boolean setArcVolume(int i) {
        debug("setArcVolume: " + i);
        return this.mAudioControl.setArcVolume(i);
    }

    public int getArcVolume() {
        return this.mAudioControl.getArcVolume();
    }

    public boolean isArcVolume() {
        IHdmiControlService iHdmiControlServiceAsInterface = IHdmiControlService.Stub.asInterface(ServiceManager.getService("hdmi_control"));
        if (iHdmiControlServiceAsInterface == null) {
            Log.e(TAG, "mHdmiControlService is null!");
            return false;
        }
        try {
            return iHdmiControlServiceAsInterface.isArcEstablished();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isArcMute() {
        return this.mAudioControl.isArcMute();
    }

    public boolean setArcMute(boolean z) {
        return this.mAudioControl.setArcMute(z);
    }

    public boolean setAudioOutputDelay(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode, int i) {
        if (i < 0 || i > 500) {
            Log.e(TAG, "set delay out of range: 0 ~ 500");
            return false;
        }
        if (enumAudioOutMode == AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER) {
            this.mAudioControl.setSPKDelay(i);
            return true;
        }
        if (enumAudioOutMode != AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_OWA) {
            return true;
        }
        this.mAudioControl.setOWADelay(i);
        return true;
    }

    public int getAudioOutputDelay(AwTvAudioTypes.EnumAudioOutMode enumAudioOutMode) {
        if (enumAudioOutMode == AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_SPEAKER) {
            return this.mAudioControl.getSPKDelay();
        }
        if (enumAudioOutMode == AwTvAudioTypes.EnumAudioOutMode.E_AW_AUDIO_OUT_MODE_OWA) {
            return this.mAudioControl.getOWADelay();
        }
        return 0;
    }

    public boolean resetAudioOutputDelaySetting() {
        this.mAudioControl.resetAudioOutputDelaySetting();
        return true;
    }

    public boolean resetAudioAllSetting() {
        resetSoundOutModeSetting();
        resetAudioOWAOutSetting();
        resetAudioOutputDelaySetting();
        resetAudioVolumeCurveSetting();
        resetAudioOutputGainSetting();
        resetAudioPreScalerGainSetting();
        resetAudioDrcSetting();
        resetAudioPEQSetting();
        resetAudioModeSetting();
        resetAudioEQModeSetting();
        resetAudioAvlSetting();
        resetAudioSurroundSetting();
        resetAudioOutputBalanceSetting();
        return true;
    }
}
