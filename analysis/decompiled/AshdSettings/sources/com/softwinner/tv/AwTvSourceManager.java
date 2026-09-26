package com.softwinner.tv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.tv.TvInputInfo;
import android.media.tv.TvInputManager;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import com.softwinner.tv.client.AwTvServerManagerProxy;
import com.softwinner.tv.common.AwPortInfo;
import com.softwinner.tv.common.AwTvAudioTypes;
import com.softwinner.tv.common.AwTvDisplayTypes;
import com.softwinner.tv.common.AwTvSignalInfo;
import com.softwinner.tv.common.AwTvSourceTypes;
import com.softwinner.tv.data.AwTvChannelInfo;
import com.softwinner.tv.module.AwTvHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.THalSignalInfo;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvSourceManager {
    public static final String EXTRA_COLOR_FORMAT = "color_format";
    public static final String EXTRA_COLOR_SPACE = "color_space";
    public static final String EXTRA_DEVICE_ID = "device_id";
    public static final String EXTRA_DVIMODE = "dvi_mode";
    public static final String EXTRA_FRAME_RATE = "frame_rate";
    public static final String EXTRA_FULLRANGE = "full_range";
    public static final String EXTRA_HDRMODE = "hdrMode";
    public static final String EXTRA_HOTPLUG_STATE = "state";
    public static final String EXTRA_HSIZE = "hsize";
    public static final String EXTRA_INTERLACE = "interlace";
    public static final String EXTRA_SIGNAL_ID = "signal_id";
    public static final String EXTRA_VSIZE = "vsize";
    public static final int HARDWARE_SOURCE_ATV = 6;
    public static final int HARDWARE_SOURCE_CVBS1 = 4;
    public static final int HARDWARE_SOURCE_CVBS2 = 5;
    public static final int HARDWARE_SOURCE_DTV = 7;
    public static final int HARDWARE_SOURCE_HDMI1 = 1;
    public static final int HARDWARE_SOURCE_HDMI2 = 2;
    public static final int HARDWARE_SOURCE_HDMI3 = 3;
    public static final int HARDWARE_SOURCE_MAX = 10;
    public static final int HARDWARE_SOURCE_NULL = 0;
    public static final int HARDWARE_SOURCE_VIDEO = 8;
    private static final boolean ISDEBUG = false;
    public static final String TIF_HOTPLUG_CHANGE = "android.intent.action.TIF_HOTPLUG_CHANGE";
    public static final String TIF_SIGNAL_CHANGE = "android.intent.action.TIF_SIGNAL_CHANGE";
    private Context mContext;
    private int mCurSoundStd;
    private ITvServer mService;
    private TvInputManager mTvInputManager;
    private static final String TAG = "AwTvSourceManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);
    private HashMap<Integer, Integer> mPortMap = new HashMap<>();
    private HashMap<Integer, String> mSourceMap = new HashMap<>();
    private HashMap<Integer, TvInputInfo> mTifMap = new HashMap<>();
    private List<AwPortInfo> mPortInfo = new ArrayList();

    interface BoolCmd {
        boolean run() throws RemoteException;
    }

    interface IntCmd {
        int run() throws RemoteException;
    }

    public int getBootDefaultInputSource() {
        return 0;
    }

    public boolean isInputSourceLock(int i) {
        return false;
    }

    public boolean isTvSource(int i) {
        return i == 6 && i == 7;
    }

    public boolean setCurrentInputSource(int i) {
        return false;
    }

    public boolean setCurrentInputSourceById(int i) {
        return false;
    }

    public boolean setInputSourceLock(int i) {
        return false;
    }

    private void debug(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public AwTvSourceManager(Context context) throws RemoteException {
        this.mTvInputManager = null;
        this.mContext = context;
        this.mTvInputManager = (TvInputManager) this.mContext.getSystemService("tv_input");
        try {
            this.mService = ITvServer.getService(true);
            if (this.mService == null) {
                Log.e(TAG, "fail to get service");
            } else {
                Log.d(TAG, "success to get service");
            }
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
        }
        initSourcePortMap();
        initTifSourceMap();
    }

    private List<TvInputInfo> getInputList() {
        return this.mTvInputManager.getTvInputList();
    }

    private void initTifSourceMap() {
        List<TvInputInfo> inputList = getInputList();
        Iterator<AwPortInfo> it = this.mPortInfo.iterator();
        while (it.hasNext()) {
            int sourceIdByPortId = getSourceIdByPortId(it.next().mPortId);
            for (TvInputInfo tvInputInfo : inputList) {
                if (getTvInputInfoSourceId(tvInputInfo) == sourceIdByPortId) {
                    this.mTifMap.put(Integer.valueOf(sourceIdByPortId), tvInputInfo);
                }
            }
        }
    }

    private int getTvInputInfoSourceId(TvInputInfo tvInputInfo) {
        String[] strArrSplit = tvInputInfo.getId().split("HW");
        if (strArrSplit.length > 1) {
            return Integer.parseInt(strArrSplit[1]);
        }
        return -1;
    }

    private void initSourcePortMap() throws RemoteException {
        String strSubDeviceSourceLoadConfig = null;
        if (this.mService != null) {
            try {
                strSubDeviceSourceLoadConfig = this.mService.SubDeviceSourceLoadConfig();
            } catch (RemoteException unused) {
                Log.e(TAG, "exception, fail to get service");
            }
            debug("load map = " + strSubDeviceSourceLoadConfig);
        }
        for (String str : strSubDeviceSourceLoadConfig.split("\n")) {
            String strTrim = str.trim();
            if (strTrim.length() > 0 && Character.isDigit(strTrim.charAt(0))) {
                String[] strArrSplit = strTrim.split("\\s+");
                int iIntValue = 0;
                int iIntValue2 = 0;
                String str2 = "";
                for (int i = 0; i < strArrSplit.length; i++) {
                    String strTrim2 = strArrSplit[i].trim();
                    if (i == 0) {
                        iIntValue = Integer.valueOf(strTrim2).intValue();
                    } else if (i == 1) {
                        iIntValue2 = Integer.valueOf(strTrim2).intValue();
                    } else if (i == 2) {
                        str2 = strTrim2;
                    }
                }
                Log.d(TAG, "portId = " + iIntValue + " sourceId = " + iIntValue2 + " name = " + str2);
                this.mPortMap.put(Integer.valueOf(iIntValue), Integer.valueOf(iIntValue2));
                this.mSourceMap.put(Integer.valueOf(iIntValue2), str2);
                this.mPortInfo.add(new AwPortInfo(iIntValue, iIntValue2, str2));
                if (this.mService != null) {
                    try {
                        this.mService.setSourcePortInfo(iIntValue, iIntValue2, str2);
                    } catch (RemoteException unused2) {
                        Log.e(TAG, "exception, fail to get service");
                    }
                }
            }
        }
    }

    public static int AndroidId2TvSourceId(int i) {
        int iOrdinal = AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_MAX.ordinal();
        switch (i) {
            case 1:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_HDMI_1.ordinal();
            case 2:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_HDMI_2.ordinal();
            case 3:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_HDMI_3.ordinal();
            case 4:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_CVBS_1.ordinal();
            case 5:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_CVBS_2.ordinal();
            case 6:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_ATV.ordinal();
            case 7:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_DTV.ordinal();
            case 8:
                return AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_VideoDec.ordinal();
            default:
                return iOrdinal;
        }
    }

    private int TvSourceId2AndroidId(int i) {
        switch (AwTvSourceTypes.EnumTvSourceID.valueOf(i)) {
            case E_AW_SOURCE_ID_VideoDec:
                return 8;
            case E_AW_SOURCE_ID_HDMI_1:
                return 1;
            case E_AW_SOURCE_ID_HDMI_2:
                return 2;
            case E_AW_SOURCE_ID_HDMI_3:
                return 3;
            case E_AW_SOURCE_ID_CVBS_1:
                return 4;
            case E_AW_SOURCE_ID_CVBS_2:
                return 5;
            case E_AW_SOURCE_ID_ATV:
                return 6;
            case E_AW_SOURCE_ID_DTV:
                return 7;
            default:
                return 0;
        }
    }

    private void dumpAll() {
        Log.d(TAG, "mPortMap = " + this.mPortMap.toString());
        Log.d(TAG, "mSourceMap = " + this.mSourceMap.toString());
        Log.d(TAG, "mTifMap = " + this.mTifMap.toString());
        Log.d(TAG, "mPortInfo = " + this.mPortInfo.toString());
    }

    public int getCurrentInputSource() throws RemoteException {
        int iOrdinal = AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_VideoDec.ordinal();
        if (this.mService != null) {
            try {
                iOrdinal = this.mService.SubDeviceGetSource();
            } catch (RemoteException unused) {
                Log.e(TAG, "exception, fail to get service");
            }
        }
        return TvSourceId2AndroidId(iOrdinal);
    }

    public String getCurrentSourceName() {
        int iSubDeviceGetSource;
        if (this.mService != null) {
            try {
                iSubDeviceGetSource = this.mService.SubDeviceGetSource();
            } catch (RemoteException unused) {
                Log.e(TAG, "exception, fail to get service");
                iSubDeviceGetSource = -1;
            }
        } else {
            iSubDeviceGetSource = -1;
        }
        int iTvSourceId2AndroidId = TvSourceId2AndroidId(iSubDeviceGetSource);
        return this.mSourceMap.containsKey(Integer.valueOf(iTvSourceId2AndroidId)) ? this.mSourceMap.get(Integer.valueOf(iTvSourceId2AndroidId)) : "";
    }

    public int getSourceIdByPortId(int i) {
        if (this.mPortMap.containsKey(Integer.valueOf(i))) {
            return this.mPortMap.get(Integer.valueOf(i)).intValue();
        }
        return -1;
    }

    public String getTifUriBySource(int i) {
        return this.mTifMap.containsKey(Integer.valueOf(i)) ? this.mTifMap.get(Integer.valueOf(i)).getId() : "";
    }

    public boolean registerInputSignalCallback(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TIF_SIGNAL_CHANGE);
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
        return true;
    }

    public boolean unregisterInputSignalCallback(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return true;
        }
        this.mContext.unregisterReceiver(broadcastReceiver);
        return true;
    }

    public boolean registerInputSourceCallback(BroadcastReceiver broadcastReceiver) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(TIF_HOTPLUG_CHANGE);
        this.mContext.registerReceiver(broadcastReceiver, intentFilter);
        return true;
    }

    public boolean unregisterInputSourceCallback(BroadcastReceiver broadcastReceiver) {
        if (broadcastReceiver == null) {
            return true;
        }
        this.mContext.unregisterReceiver(broadcastReceiver);
        return true;
    }

    public AwTvSignalInfo getInputSourceStatus(int i) {
        int iAndroidId2TvSourceId = AndroidId2TvSourceId(i);
        if (this.mService == null) {
            return null;
        }
        try {
            THalSignalInfo tHalSignalInfoSubDeviceGetSourceSignalInfo = this.mService.SubDeviceGetSourceSignalInfo(iAndroidId2TvSourceId);
            if (iAndroidId2TvSourceId == AwTvSourceTypes.EnumTvSourceID.E_AW_SOURCE_ID_ATV.ordinal()) {
                AwTvChannelInfo currentChannelInfo = AwTvHelper.getInstance(this.mContext).getCurrentChannelInfo(2);
                switch (currentChannelInfo.getVideoStd()) {
                    case 1:
                        tHalSignalInfoSubDeviceGetSourceSignalInfo.signal_id = 16;
                        break;
                    case 2:
                        tHalSignalInfoSubDeviceGetSourceSignalInfo.signal_id = 20;
                        break;
                    case 3:
                        tHalSignalInfoSubDeviceGetSourceSignalInfo.signal_id = 22;
                        break;
                    default:
                        tHalSignalInfoSubDeviceGetSourceSignalInfo.signal_id = 0;
                        break;
                }
                this.mCurSoundStd = currentChannelInfo.getAudioStd();
                Log.d(TAG, "signal id: " + tHalSignalInfoSubDeviceGetSourceSignalInfo.signal_id + " audio std: " + this.mCurSoundStd);
            }
            if (tHalSignalInfoSubDeviceGetSourceSignalInfo != null) {
                return new AwTvSignalInfo(tHalSignalInfoSubDeviceGetSourceSignalInfo.signal_id, tHalSignalInfoSubDeviceGetSourceSignalInfo.frame_rate_x100, tHalSignalInfoSubDeviceGetSourceSignalInfo.b_interlace, tHalSignalInfoSubDeviceGetSourceSignalInfo.color_format, tHalSignalInfoSubDeviceGetSourceSignalInfo.color_space, tHalSignalInfoSubDeviceGetSourceSignalInfo.resolution.h_size, tHalSignalInfoSubDeviceGetSourceSignalInfo.resolution.v_size, tHalSignalInfoSubDeviceGetSourceSignalInfo.hdr_mode, tHalSignalInfoSubDeviceGetSourceSignalInfo.b_full_range, tHalSignalInfoSubDeviceGetSourceSignalInfo.b_dvi_mode);
            }
            return null;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public AwTvAudioTypes.EnumATVStandardMode getTvInputInfoSoundStandard() {
        switch (this.mCurSoundStd) {
            case 1:
                return AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_M;
            case 2:
                return AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_BG;
            case 3:
                return AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_I;
            case 4:
                return AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_DK;
            case 5:
                return AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_L;
            default:
                return AwTvAudioTypes.EnumATVStandardMode.E_AW_ATV_STANDARD_MODE_AUTO;
        }
    }

    public List<AwPortInfo> getInputSourceList() {
        return this.mPortInfo;
    }

    public boolean setBlueScreenEnable(boolean z) {
        return Settings.System.putInt(this.mContext.getContentResolver(), "tv_blue_screen_tips", z ? 1 : 0);
    }

    public boolean getBlueScreenEnable() {
        return 1 == Settings.System.getInt(this.mContext.getContentResolver(), "tv_blue_screen_tips", 0);
    }

    private int runIntCmd(IntCmd intCmd, String str, String str2) {
        int iRun;
        if (this.mService != null) {
            try {
                iRun = intCmd.run();
            } catch (RemoteException e) {
                e.printStackTrace();
                this.mService = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
                iRun = -1;
            }
        } else {
            iRun = -1;
        }
        debug("" + str + ": param (" + str2 + ") ret(" + iRun + ")");
        return iRun;
    }

    private boolean runBoolCmd(BoolCmd boolCmd, String str, String str2) {
        boolean zRun;
        if (this.mService != null) {
            try {
                zRun = boolCmd.run();
            } catch (RemoteException e) {
                e.printStackTrace();
                this.mService = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
                zRun = false;
            }
        } else {
            zRun = false;
        }
        debug("" + str + ": param (" + str2 + ") ret(" + zRun + ")");
        return zRun;
    }

    public boolean setSourceFreezeState(boolean z) {
        return runBoolCmd(() -> {
            return this.mService.SubDeviceSetVideoFreeze(z) == 0;
        }, "setSourceFreezeState", "state= " + z);
    }

    public boolean getSourceFreezeState() {
        return runBoolCmd(() -> {
            return this.mService.SubDeviceGetVideoFreeze();
        }, "getSourceFreezeState", null);
    }

    public int isDVIMode() {
        int i = getInputSourceStatus(getCurrentInputSource()).mDviMode;
        debug("isHDMIGraphicTiming ret(" + i + ")");
        return i;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0016. Please report as an issue. */
    public boolean isGraphicTiming() {
        switch (AwTvDisplayTypes.EnumOverScanTiming.valueOf(getInputSourceStatus(getCurrentInputSource()).mSignalId)) {
        }
        return false;
    }

    public boolean userSetCurrentScene(int i, boolean z) {
        debug("userSetCurrentScene source=" + i + " isEnter=" + z);
        int iAndroidId2TvSourceId = AndroidId2TvSourceId(i);
        if (this.mService == null) {
            return false;
        }
        try {
            return this.mService.userSetScene(iAndroidId2TvSourceId, z) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }
}
