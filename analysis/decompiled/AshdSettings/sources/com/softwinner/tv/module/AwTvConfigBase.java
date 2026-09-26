package com.softwinner.tv.module;

import android.os.RemoteException;
import android.os.SystemProperties;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import android.util.SparseArray;
import com.softwinner.tv.client.AwTvBrocastManagerProxy;
import com.softwinner.tv.data.AwTunerLockInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import vendor.aw.hardware.btvserver.V1_0.IBtvConfiger;
import vendor.aw.hardware.btvserver.V1_0.IBtvServer;
import vendor.aw.hardware.btvserver.V1_0.TvLockInfo;

/* JADX INFO: loaded from: classes.dex */
public class AwTvConfigBase {
    public static final int ATV_COLOR_STANDARD_AUTO = 0;
    public static final int ATV_COLOR_STANDARD_NTSC = 2;
    public static final int ATV_COLOR_STANDARD_PAL = 1;
    public static final int ATV_COLOR_STANDARD_SECAM = 3;
    public static final int ATV_SOUND_COUNTRY_BRAZIL = 7;
    public static final int ATV_SOUND_COUNTRY_CHINA = 2;
    public static final int ATV_SOUND_COUNTRY_FRANCE = 1;
    public static final int ATV_SOUND_COUNTRY_GAOLI = 6;
    public static final int ATV_SOUND_COUNTRY_INDIA = 8;
    public static final int ATV_SOUND_COUNTRY_NIPPON = 5;
    public static final int ATV_SOUND_COUNTRY_OTHERS = 10;
    public static final int ATV_SOUND_COUNTRY_RUSSIA = 9;
    public static final int ATV_SOUND_COUNTRY_SWEDEN = 3;
    public static final int ATV_SOUND_COUNTRY_UNKNOWN = 0;
    public static final int ATV_SOUND_COUNTRY_USA = 4;
    public static final int ATV_SOUND_STANDARD_AUTO = 0;
    public static final int ATV_SOUND_STANDARD_BG = 2;
    public static final int ATV_SOUND_STANDARD_DK = 4;
    public static final int ATV_SOUND_STANDARD_I = 3;
    public static final int ATV_SOUND_STANDARD_L = 5;
    public static final int ATV_SOUND_STANDARD_M = 1;
    public static final int ATV_TUNER_AIR = 0;
    public static final int ATV_TUNER_CABLE_AUTO = 4;
    public static final int ATV_TUNER_CABLE_HRC = 2;
    public static final int ATV_TUNER_CABLE_IRC = 3;
    public static final int ATV_TUNER_CABLE_STD = 1;
    public static final int ATV_TUNER_UNKNOWN = -1;
    public static final int DTV_MOD_QAM128 = 3;
    public static final int DTV_MOD_QAM16 = 0;
    public static final int DTV_MOD_QAM256 = 4;
    public static final int DTV_MOD_QAM32 = 1;
    public static final int DTV_MOD_QAM64 = 2;
    public static final int DTV_MOD_VSB8 = 5;
    public static final int DTV_STD_ATSC = 5;
    public static final int DTV_STD_DTMB = 1;
    public static final int DTV_STD_DVBC = 2;
    public static final int DTV_STD_DVBS = 4;
    public static final int DTV_STD_DVBT = 3;
    public static final int DTV_STD_ISDB = 6;
    public static String[] DtvModulationList = null;
    private static final String TAG = "AwTvConfigBase";
    private static AwTvConfigBase sInstance;
    private boolean debug = true;
    private AwTvBrocastManagerProxy mTvBrocastManagerProxy = AwTvBrocastManagerProxy.getInstance();
    private IBtvConfiger mTvConfiger;
    private static HashMap<String, Integer> mCountryCode = new HashMap<>();
    public static SparseArray<String> ATV_VIDEO_FMT_MAP = new SparseArray<>();
    public static SparseArray<String> ATV_AUDIO_FMT_MAP = new SparseArray<>();

    static {
        mCountryCode.put(EnvironmentCompat.MEDIA_UNKNOWN, 0);
        mCountryCode.put("france", 1);
        mCountryCode.put("cn", 2);
        mCountryCode.put("sweden", 3);
        mCountryCode.put("us", 4);
        mCountryCode.put("nippon", 5);
        mCountryCode.put("korea", 6);
        mCountryCode.put("brazil", 7);
        mCountryCode.put("india", 8);
        mCountryCode.put("russia", 9);
        mCountryCode.put("others", 10);
        ATV_VIDEO_FMT_MAP.put(0, "AUTO");
        ATV_VIDEO_FMT_MAP.put(1, "PAL");
        ATV_VIDEO_FMT_MAP.put(2, "NTSC");
        ATV_VIDEO_FMT_MAP.put(3, "SECAM");
        ATV_AUDIO_FMT_MAP.put(0, "AUTO");
        ATV_AUDIO_FMT_MAP.put(1, "M");
        ATV_AUDIO_FMT_MAP.put(2, "BG");
        ATV_AUDIO_FMT_MAP.put(3, "I");
        ATV_AUDIO_FMT_MAP.put(4, "DK");
        ATV_AUDIO_FMT_MAP.put(5, "L");
        DtvModulationList = new String[]{"QAM16", "QAM32", "QAM64", "QAM128", "QAM256", "VSB8"};
    }

    public static synchronized AwTvConfigBase getInstance() {
        if (sInstance == null) {
            sInstance = new AwTvConfigBase();
        }
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryGetBtvConfiger() {
        try {
            this.mTvBrocastManagerProxy.getAwTvBrocastService().getTvConfiger(new IBtvServer.getTvConfigerCallback() { // from class: com.softwinner.tv.module.AwTvConfigBase.1
                @Override // vendor.aw.hardware.btvserver.V1_0.IBtvServer.getTvConfigerCallback
                public void onValues(int i, IBtvConfiger iBtvConfiger) {
                    Log.d(AwTvConfigBase.TAG, "initBtvConfiger : onValues");
                    AwTvConfigBase.this.mTvConfiger = iBtvConfiger;
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private AwTvConfigBase() {
        tryGetBtvConfiger();
        this.mTvBrocastManagerProxy.setTvBrocastServiceListener(new AwTvBrocastManagerProxy.AwTvBrocastServiceListener() { // from class: com.softwinner.tv.module.AwTvConfigBase.2
            @Override // com.softwinner.tv.client.AwTvBrocastManagerProxy.AwTvBrocastServiceListener
            public void onServiceStatusChange(int i) {
                if (i == 1) {
                    Log.d(AwTvConfigBase.TAG, "service has reconnected, try to get configer again.");
                    AwTvConfigBase.this.tryGetBtvConfiger();
                }
            }
        });
    }

    public int getCurrentCountryIndex() {
        String tvDefaultCountryWithCode;
        try {
            tvDefaultCountryWithCode = this.mTvConfiger.getTvDefaultCountryWithCode();
        } catch (RemoteException e) {
            e.printStackTrace();
            tvDefaultCountryWithCode = null;
        }
        if (tvDefaultCountryWithCode == null) {
            return 10;
        }
        if (mCountryCode.containsKey(tvDefaultCountryWithCode)) {
            return mCountryCode.get(tvDefaultCountryWithCode).intValue();
        }
        Log.d(TAG, "unknown country code ...." + tvDefaultCountryWithCode);
        return 10;
    }

    public ArrayList<String> getTvSupportCountryList() {
        try {
            ArrayList<String> tvSupportCountryList = this.mTvConfiger.getTvSupportCountryList();
            if (this.debug) {
                Log.d(TAG, "getTvSupportCountryList count: " + tvSupportCountryList.size());
                for (int i = 0; i < tvSupportCountryList.size(); i++) {
                    Log.d(TAG, "getTvSupportCountryList : " + tvSupportCountryList.get(i));
                }
            }
            return tvSupportCountryList;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AwTunerLockInfo> getTvAtvScanFreqList() {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList<TvLockInfo> tvAtvScanFreqList = this.mTvConfiger.getTvAtvScanFreqList();
            if (this.debug) {
                Log.d(TAG, "getTvAtvScanFreqList count: " + tvAtvScanFreqList.size());
                for (TvLockInfo tvLockInfo : tvAtvScanFreqList) {
                    AwTunerLockInfo awTunerLockInfo = new AwTunerLockInfo();
                    awTunerLockInfo.frequency = tvLockInfo.atvLockInfo().frequency;
                    awTunerLockInfo.channelno = tvLockInfo.atvLockInfo().channel_id;
                    awTunerLockInfo.videostd = tvLockInfo.atvLockInfo().video_std;
                    awTunerLockInfo.audiostd = tvLockInfo.atvLockInfo().audio_std;
                    awTunerLockInfo.tunerstd = tvLockInfo.atvLockInfo().tuner_std;
                    arrayList.add(awTunerLockInfo);
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<AwTunerLockInfo> getTvDtvScanFreqList() {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList<TvLockInfo> tvDtvScanFreqList = this.mTvConfiger.getTvDtvScanFreqList();
            if (this.debug) {
                Log.d(TAG, "getTvDtvScanFreqList count: " + tvDtvScanFreqList.size());
                for (TvLockInfo tvLockInfo : tvDtvScanFreqList) {
                    AwTunerLockInfo awTunerLockInfo = new AwTunerLockInfo();
                    awTunerLockInfo.frequency = tvLockInfo.dtvLockInfo().frequency;
                    awTunerLockInfo.symbolRate = tvLockInfo.dtvLockInfo().symbolRate;
                    awTunerLockInfo.modulation = tvLockInfo.dtvLockInfo().modulation;
                    awTunerLockInfo.bandwidth = tvLockInfo.dtvLockInfo().bandwidth;
                    arrayList.add(awTunerLockInfo);
                }
            }
            return arrayList;
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getTvDefaultCountryWithCode() {
        String tvDefaultCountryWithCode;
        try {
            tvDefaultCountryWithCode = this.mTvConfiger.getTvDefaultCountryWithCode();
        } catch (RemoteException e) {
            e.printStackTrace();
            tvDefaultCountryWithCode = null;
        }
        if (this.debug) {
            Log.d(TAG, "getTvDefaultCountryWithCode : " + tvDefaultCountryWithCode);
        }
        return tvDefaultCountryWithCode;
    }

    public int getTvAtvColorStandard(String str) {
        int tvAtvColorSys;
        try {
            tvAtvColorSys = this.mTvConfiger.getTvAtvColorSys(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvAtvColorSys = 0;
        }
        if (this.debug) {
            Log.d(TAG, "getAtvColorStandard : " + str + " = " + tvAtvColorSys);
        }
        return tvAtvColorSys;
    }

    public int getTvAtvSoundStandard(String str) {
        int tvAtvSoundSys;
        try {
            tvAtvSoundSys = this.mTvConfiger.getTvAtvSoundSys(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvAtvSoundSys = 0;
        }
        if (this.debug) {
            Log.d(TAG, "getTvAtvSoundSys : " + str + " = " + tvAtvSoundSys);
        }
        return tvAtvSoundSys;
    }

    public int getTvAtvMinFreq(String str) {
        int tvAtvMinFreq;
        try {
            tvAtvMinFreq = this.mTvConfiger.getTvAtvMinFreq(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvAtvMinFreq = 0;
        }
        if (this.debug) {
            Log.d(TAG, "getAtvMinFreq : " + str + " = " + tvAtvMinFreq);
        }
        return tvAtvMinFreq;
    }

    public int getTvAtvMaxFreq(String str) {
        int tvAtvMaxFreq;
        try {
            tvAtvMaxFreq = this.mTvConfiger.getTvAtvMaxFreq(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvAtvMaxFreq = 0;
        }
        if (this.debug) {
            Log.d(TAG, "getAtvMaxFreq : " + str + " = " + tvAtvMaxFreq);
        }
        return tvAtvMaxFreq;
    }

    public int getTvAtvManualScanStep(String str) {
        int tvAtvScanStep;
        try {
            tvAtvScanStep = this.mTvConfiger.getTvAtvScanStep(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvAtvScanStep = 0;
        }
        if (this.debug) {
            Log.d(TAG, "getTvAtvManualScanStep : " + str + " = " + tvAtvScanStep);
        }
        return tvAtvScanStep;
    }

    public boolean getTvAtvSupport(String str) {
        int tvAtvSupport;
        try {
            tvAtvSupport = this.mTvConfiger.getTvAtvSupport(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvAtvSupport = 0;
        }
        return tvAtvSupport == 1;
    }

    public int getAtvTvTunerStandard(String str) {
        int tvTunerStandardType;
        try {
            tvTunerStandardType = this.mTvConfiger.getTvTunerStandardType(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvTunerStandardType = 0;
        }
        if (this.debug) {
            Log.d(TAG, "getAtvTvTunerStandard : " + str + " = " + tvTunerStandardType);
        }
        return tvTunerStandardType;
    }

    public boolean getTvDtvSupport(String str) {
        int tvDtvSupport;
        try {
            tvDtvSupport = this.mTvConfiger.getTvDtvSupport(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvDtvSupport = 0;
        }
        return tvDtvSupport == 1;
    }

    public int tunerDtvGetDeviceInfo() {
        return dtvStandardToType(SystemProperties.get("persist.vendor.dtv.standard"));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0049  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private int dtvStandardToType(String str) {
        byte b;
        switch (str.hashCode()) {
            case 3004867:
                if (!str.equals("atsc")) {
                    b = -1;
                } else {
                    b = 4;
                }
                break;
            case 3094053:
                if (!str.equals("dtmb")) {
                    b = -1;
                } else {
                    b = 0;
                }
                break;
            case 3095635:
                if (!str.equals("dvbc")) {
                    b = -1;
                } else {
                    b = 1;
                }
                break;
            case 3095651:
                if (!str.equals("dvbs")) {
                    b = -1;
                } else {
                    b = 3;
                }
                break;
            case 3095652:
                if (!str.equals("dvbt")) {
                    b = -1;
                } else {
                    b = 2;
                }
                break;
            case 3241768:
                if (!str.equals("isdb")) {
                    b = -1;
                } else {
                    b = 5;
                }
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
            default:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
        }
    }

    public int getTvDtvStandard(String str) {
        int tvDtvStandard;
        try {
            tvDtvStandard = this.mTvConfiger.getTvDtvStandard(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            tvDtvStandard = 1;
        }
        if (this.debug) {
            Log.d(TAG, "getTvDtvStandard : " + str + " = " + tvDtvStandard);
        }
        return tvDtvStandard;
    }
}
