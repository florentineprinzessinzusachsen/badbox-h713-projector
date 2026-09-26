package com.softwinner.tv;

import android.os.RemoteException;
import android.util.Log;
import com.softwinner.tv.client.AwTvServerManagerProxy;
import com.softwinner.tv.common.AwTvDisplayTypes;
import java.util.ArrayList;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.OverScanInfo;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.PictureCurveOsdValue;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvDisplayManager {
    public static final int BACKLIGHT_MAX = 100;
    public static final int BACKLIGHT_MIN = 0;
    public static final int BASIC_CONTROL_MAX = 100;
    public static final int BASIC_CONTROL_MIN = 0;
    public static final int CM_GAIN_ITEM_NUM = 3;
    public static final int CM_GAIN_RANGE_NUM = 12;
    public static final int DLC_MAX = 3;
    public static final int DLC_MIN = 0;
    public static final int INDEX_ATV_UID = 10;
    public static final int INDEX_COLOR_FORMAT = 3;
    public static final int INDEX_COLOR_SPACE = 4;
    public static final int INDEX_DVI_MODE = 9;
    public static final int INDEX_FRAME_RATE_X100 = 1;
    public static final int INDEX_FULL_RANGE = 8;
    public static final int INDEX_HDR_MODE = 7;
    public static final int INDEX_INTERLACE = 2;
    public static final int INDEX_RESOLUTION_H = 5;
    public static final int INDEX_RESOLUTION_V = 6;
    public static final int INDEX_SIGNAL_ID = 0;
    private static final String INVAILD_MODE = "0xFF";
    private static final boolean ISDEBUG = false;
    public static final int NLPC_BRIGHTNESS_MAX_VALUE = 1023;
    public static final int NLPC_BRIGHTNESS_MIN_VALUE = 0;
    public static final int NLPC_CONTRAST_MAX_VALUE = 3588;
    public static final int NLPC_CONTRAST_MIN_VALUE = 1196;
    public static final int NLPC_HUE_MAX_VALUE = 1023;
    public static final int NLPC_HUE_MIN_VALUE = 0;
    public static final int NLPC_LIMITED_MAX_VALUE = 255;
    public static final int NLPC_LIMITED_MIN_VALUE = 0;
    public static final int NLPC_SATURATION_MAX_VALUE = 192;
    public static final int NLPC_SATURATION_MIN_VALUE = 0;
    public static final int NLPC_SHARPNESS_MAX_VALUE = 255;
    public static final int NLPC_SHARPNESS_MIN_VALUE = 0;
    private static final int OTHER_TYPE_ASPECT_RATIO = 21;
    private static final int OTHER_TYPE_CVBS_PEDESTAL_MODE = 22;
    private static final int OTHER_TYPE_HDMI_CONTROL_PC_MODE = 20;
    private static final int OTHER_TYPE_OVERSCAN_ENABLE = 18;
    public static final int PANEL_CURRENT_DCLK_MAX = 7;
    public static final int PANEL_CURRENT_DCLK_MIN = 0;
    public static final int PANEL_CURRENT_DE_MAX = 63;
    public static final int PANEL_CURRENT_DE_MIN = 0;
    public static final int PANEL_EMC_LVDS_SPAN_MAX = 3;
    public static final int PANEL_EMC_LVDS_SPAN_MIN = 0;
    public static final int PANEL_EMC_LVDS_STEP_MAX = 1000;
    public static final int PANEL_EMC_LVDS_STEP_MIN = 0;
    public static final int PANEL_TIMING_WORK_MODE_MAX = 2;
    public static final int PANEL_TIMING_WORK_MODE_MIN = 0;
    private static final int PQ_OTHER_TYPE_BACKLIGHT = 6;
    private static final int PQ_OTHER_TYPE_BLACK_EXTENSION = 10;
    private static final int PQ_OTHER_TYPE_COLOR_TEMPERATURE = 12;
    private static final int PQ_OTHER_TYPE_DCI = 9;
    private static final int PQ_OTHER_TYPE_DYNAMIC_BACKLIGHT = 13;
    private static final int PQ_OTHER_TYPE_GAMMA = 11;
    private static final int PQ_OTHER_TYPE_NR = 255;
    private static final int PQ_OTHER_TYPE_SNR = 8;
    private static final int PQ_OTHER_TYPE_TNR = 7;
    public static final int PWM_FREQUENCY_MAX = 67108863;
    public static final int PWM_FREQUENCY_MIN = 1;
    public static final int SNR_MAX = 3;
    public static final int SNR_MIN = 0;
    public static final int TNR_MAX = 3;
    public static final int TNR_MIN = 0;
    public static final int WB_GAIN_MAX = 1023;
    public static final int WB_GAIN_MIN = 0;
    public static final int WB_OFFSET_MAX = 511;
    public static final int WB_OFFSET_MIN = -512;
    private static AwTvDisplayManager sAwTvDispalyManager;
    ITvServer mDisplayHidlManager;
    private static final String TAG = "AwTvDisplayManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);

    interface IntCmd {
        int run() throws RemoteException;
    }

    interface StringCmd {
        String run() throws RemoteException;
    }

    public boolean isLVDSPanel() {
        return true;
    }

    private void debug(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    private AwTvDisplayManager() {
        this.mDisplayHidlManager = null;
        this.mDisplayHidlManager = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
    }

    public static AwTvDisplayManager getInstance() {
        if (sAwTvDispalyManager == null) {
            sAwTvDispalyManager = new AwTvDisplayManager();
        }
        return sAwTvDispalyManager;
    }

    public int setBasicControl(AwTvDisplayTypes.EnumPQBasicType enumPQBasicType, int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(EnumPQBasicTypeToHidlType(enumPQBasicType), i);
        }, "setBasicControl", "type= " + enumPQBasicType + " level= " + i);
    }

    public int getBasicControl(AwTvDisplayTypes.EnumPQBasicType enumPQBasicType) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(EnumPQBasicTypeToHidlType(enumPQBasicType));
        }, "getBasicControl", "type=" + enumPQBasicType);
    }

    public int setBacklight(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(6, i);
        }, "setBacklight", "level= " + i);
    }

    public int getBacklight() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(6);
        }, "getBacklight", null);
    }

    public int setSNR(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(8, i);
        }, "setSNR", "level= " + i);
    }

    public int getSNR() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(8);
        }, "getSNR", null);
    }

    public int setTNR(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(7, i);
        }, "setTNR", "level= " + i);
    }

    public int getTNR() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(7);
        }, "getTNR", null);
    }

    public int setDynamicBacklight(boolean z) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(13, z ? 1 : 0);
        }, "setDynamicBacklight", "enable= " + z);
    }

    public boolean getDynamicBacklight() {
        return 1 == runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(13);
        }, "isDynamicBacklight", null);
    }

    public int setColorTemp(AwTvDisplayTypes.EnumColorTempMode enumColorTempMode) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(12, enumColorTempMode.getValue());
        }, "setColorTemp", "colorTemp mode= " + enumColorTempMode.getValue());
    }

    public AwTvDisplayTypes.EnumColorTempMode getColorTemp() {
        return AwTvDisplayTypes.EnumColorTempMode.valueOf(runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(12);
        }, "getColorTemp", null));
    }

    public int setDLC(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(9, i);
        }, "setDLC", "level= " + i);
    }

    public int getDLC() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(9);
        }, "getDLC", null);
    }

    public int setBlackExtension(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(10, i);
        }, "setBlackExtension", " level=" + i);
    }

    public int getBlackExtension() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(10);
        }, "getBlackExtension", null);
    }

    public int setGammaFactor(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(11, i);
        }, "setGammaFactor", " level=" + i);
    }

    public int getGammaFactor() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(11);
        }, "getGammaFactor", null);
    }

    public int setPictureModeByName(AwTvDisplayTypes.EnumPictureMode enumPictureMode) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPictureModeByName(enumPictureMode.getValue());
        }, "setPictureModeByName", "mode= " + enumPictureMode.getValue());
    }

    public AwTvDisplayTypes.EnumPictureMode getPictureModeName() {
        return AwTvDisplayTypes.EnumPictureMode.EnumValueOf(runStringCmd(() -> {
            return this.mDisplayHidlManager.getPictureModeName();
        }, "getPictureModeName", null));
    }

    public boolean setStorePictureMode(boolean z) {
        IntCmd intCmd = () -> {
            return this.mDisplayHidlManager.setStorePictureMode(z);
        };
        StringBuilder sb = new StringBuilder();
        sb.append("enable= ");
        sb.append(z);
        return runIntCmd(intCmd, "setStorePictureMode", sb.toString()) == 0;
    }

    public boolean getStorePictureMode() {
        return 1 == runIntCmd(() -> {
            return this.mDisplayHidlManager.getStorePictureMode();
        }, "getStorePictureMode", null);
    }

    public int getPannelWidth() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPannelWidth();
        }, "getPannelWidth", null);
    }

    public int getPannelHeight() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPannelHeight();
        }, "getPannelHeight", null);
    }

    public int setVideoRange(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setVideoRange(i);
        }, "setVideoRange", "mode= " + i);
    }

    public int getVideoRange() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getVideoRange();
        }, "getVideoRange", null);
    }

    public int setHDMIVideoPCMode(AwTvDisplayTypes.EnumVideoPCMode enumVideoPCMode) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(20, enumVideoPCMode.ordinal());
        }, "setHDMIVideoPCMode", "mode= " + enumVideoPCMode);
    }

    public AwTvDisplayTypes.EnumVideoPCMode getHDMIVideoPCMode() {
        return AwTvDisplayTypes.EnumVideoPCMode.valueOf(runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(20);
        }, "getHDMIVideoPCMode", null));
    }

    public int setOverScanState(boolean z) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(18, z ? 1 : 0);
        }, "setOverScanState", "enable= " + z);
    }

    public boolean getOverScanState() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(18);
        }, "getOverScanStates", null) != 0;
    }

    public int setSourceAspectRatioNotSave(AwTvDisplayTypes.EnumOverScanScreenMode enumOverScanScreenMode) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setAspectRatioNotSave(enumOverScanScreenMode.ordinal());
        }, "setSourceAspectRatioNotSave", "mode= " + enumOverScanScreenMode.toString());
    }

    public int setSourceAspectRatio(AwTvDisplayTypes.EnumOverScanScreenMode enumOverScanScreenMode) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.setPQValue(21, enumOverScanScreenMode.ordinal());
        }, "setSourceAspectRatio", "mode= " + enumOverScanScreenMode.toString());
    }

    public AwTvDisplayTypes.EnumOverScanScreenMode getSourceAspectRatio() {
        return AwTvDisplayTypes.EnumOverScanScreenMode.valueOf(runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(21);
        }, "getSourceAspectRatio", null));
    }

    public int resetAllPictureSettings() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.resetAllSettings();
        }, "resetAllPictureSettings", null);
    }

    public int factorySetPWMFrequency(int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPanelValue(AwTvDisplayTypes.EnumPanelConfigType.E_AW_PANEL_PWM_FREQ.getValue(), i);
        }, "factorySetPWMFrequency", "value= " + i);
    }

    public int factoryGetPWMFrequency() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryGetPanelValue(AwTvDisplayTypes.EnumPanelConfigType.E_AW_PANEL_PWM_FREQ.getValue());
        }, "factoryGetPWMFrequency", null);
    }

    public int factorySetWbGainOffset(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, AwTvDisplayTypes.EnumColorTempMode enumColorTempMode, AwTvDisplayTypes.EnumPQRGBOGType enumPQRGBOGType, int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetWBValue(enumTvSourceType.getValue(), enumColorTempMode.getValue(), enumPQRGBOGType.getValue(), i);
        }, "factorySetWbGainOffset", "sourceType= " + enumTvSourceType.getValue() + " mode= " + enumColorTempMode.getValue() + " item= " + enumPQRGBOGType.getValue() + " value= " + i);
    }

    public int factoryGetWbGainOffset(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, AwTvDisplayTypes.EnumColorTempMode enumColorTempMode, AwTvDisplayTypes.EnumPQRGBOGType enumPQRGBOGType) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryGetWBValue(enumTvSourceType.getValue(), enumColorTempMode.getValue(), enumPQRGBOGType.getValue());
        }, "factoryGetWbGainOffset", null);
    }

    public int factorySetPanelValue(AwTvDisplayTypes.EnumPanelConfigType enumPanelConfigType, int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPanelValue(enumPanelConfigType.getValue(), i);
        }, "factorySetPanelValue", "configType= " + enumPanelConfigType.getValue() + " value= " + i);
    }

    public int factoryGetPanelValue(AwTvDisplayTypes.EnumPanelConfigType enumPanelConfigType) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryGetPanelValue(enumPanelConfigType.getValue());
        }, "factoryGetPanelValue", null);
    }

    public int factoryResetPanelSettings() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryResetPanelSettings();
        }, "factoryResetPanelSettings", null);
    }

    public int factorySetOverScan(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, int i, AwTvDisplayTypes.EnumOverScanScreenMode enumOverScanScreenMode, AwTvDisplayTypes.EnumOverScanType enumOverScanType, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetOverScan(enumTvSourceType.getValue(), i, enumOverScanScreenMode.ordinal(), enumOverScanType.ordinal(), i2);
        }, "factorySetOverScan", "sourceType= " + enumTvSourceType.getValue() + " timingIndex= " + i + " screenMode=" + enumOverScanScreenMode + " type=" + enumOverScanType + " value=" + i2);
    }

    public AwTvDisplayTypes.AwOverScanInfo factoryGetOverScan(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, int i, AwTvDisplayTypes.EnumOverScanScreenMode enumOverScanScreenMode) {
        AwTvDisplayTypes.AwOverScanInfo awOverScanInfo = new AwTvDisplayTypes.AwOverScanInfo();
        if (this.mDisplayHidlManager != null) {
            try {
                OverScanInfo overScanInfoFactoryGetOverScan = this.mDisplayHidlManager.factoryGetOverScan(enumTvSourceType.getValue(), i, enumOverScanScreenMode.ordinal());
                awOverScanInfo.cropLeft = overScanInfoFactoryGetOverScan.cropLeft;
                awOverScanInfo.cropRight = overScanInfoFactoryGetOverScan.cropRight;
                awOverScanInfo.cropUp = overScanInfoFactoryGetOverScan.cropUp;
                awOverScanInfo.cropDown = overScanInfoFactoryGetOverScan.cropDown;
            } catch (RemoteException e) {
                e.printStackTrace();
                this.mDisplayHidlManager = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
            }
        }
        debug("factoryGetOverScan: info (" + awOverScanInfo.cropLeft + "," + awOverScanInfo.cropRight + "," + awOverScanInfo.cropUp + "," + awOverScanInfo.cropDown + ")");
        return awOverScanInfo;
    }

    public int factorySetPictureParam(AwTvDisplayTypes.FactoryPictureParamType factoryPictureParamType, int i, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPictureParam(factoryPictureParamType.getValue(), i, i2);
        }, "factorySetPictureParam", "itemType= " + i + " value= " + i2);
    }

    public int factorySetPictureLevel(AwTvDisplayTypes.FactoryPictureParamType factoryPictureParamType, int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPictureLevel(factoryPictureParamType.getValue(), i);
        }, "factorySetPictureLevel", "level= " + i);
    }

    public int factoryGetPictureLevel(AwTvDisplayTypes.FactoryPictureParamType factoryPictureParamType) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryGetPictureLevel(factoryPictureParamType.getValue());
        }, "factoryGetPictureLevel", null);
    }

    public int factoryGetPictureParam(AwTvDisplayTypes.FactoryPictureParamType factoryPictureParamType, int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryGetPictureParam(factoryPictureParamType.getValue(), i);
        }, "factoryGetPictureParam", null);
    }

    public int factorySetBasicControl(int i, String str, AwTvDisplayTypes.EnumPQBasicType enumPQBasicType, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, EnumPQBasicTypeToHidlType(enumPQBasicType), i2);
        }, "factorySetBasicControl", "tvin= " + i + " mode= " + str + " type= " + enumPQBasicType + " value= " + i2);
    }

    public int factorySetBacklight(int i, String str, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 6, i2);
        }, "factorySetBacklight", "tvin= " + i + " mode= " + str + " value= " + i2);
    }

    public int factorySetSNR(int i, String str, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 8, i2);
        }, "factorySetSNR", "tvin= " + i + " mode= " + str + " value= " + i2);
    }

    public int factorySetTNR(int i, String str, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 7, i2);
        }, "factorySetTNR", "tvin= " + i + " mode= " + str + " value= " + i2);
    }

    public int factorySetDynamicBacklight(int i, String str, boolean z) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 13, z ? 1 : 0);
        }, "factorySetDynamicBacklight", "tvin= " + i + " mode= " + str + " enable= " + z);
    }

    public int factorySetColorTemperature(int i, String str, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 12, i2);
        }, "factorySetColorTemperature", "tvin= " + i + " mode= " + str + " value= " + i2);
    }

    public int factorySetDLC(int i, String str, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 9, i2);
        }, "factorySetDLC", "tvin= " + i + " mode= " + str + " value= " + i2);
    }

    public int factorySetBlackExtension(int i, String str, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 10, i2);
        }, "factorySetBlackExtension", "tvin= " + i + " mode= " + str + " value= " + i2);
    }

    public int factorySetGammaFactor(int i, String str, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(i, str, 11, i2);
        }, "factorySetGammaFactor", "tvin= " + i + " mode= " + str + " value= " + i2);
    }

    public int factoryCopyWbGainOffetToOtherSrc(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryCopyWBInfoToOtherSrc(enumTvSourceType.getValue());
        }, "factoryCopyWbGainOffetToOtherSrc", "sourceType= " + enumTvSourceType.getValue());
    }

    public int factoryResetWbGainOffset() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryResetWBInfo();
        }, "factoryResetWbGainOffset", null);
    }

    public int factorySetNonLinearPQValue(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, AwTvDisplayTypes.EnumPQBasicType enumPQBasicType, AwTvDisplayTypes.EnumNonLinearPQCurveOsdPoint enumNonLinearPQCurveOsdPoint, int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetNonLinearPQValue(enumTvSourceType.getValue(), EnumPQBasicTypeToHidlType(enumPQBasicType), enumNonLinearPQCurveOsdPoint.getValue(), i);
        }, "factorySetNonLinearPQValue", "sourceType= " + enumTvSourceType.getValue() + " pqType= " + EnumPQBasicTypeToHidlType(enumPQBasicType) + " osdPoint= " + enumNonLinearPQCurveOsdPoint.getValue() + " value= " + i);
    }

    public AwTvDisplayTypes.AwPictureCurveOsdValue factoryGetNonLinearPQValue(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, AwTvDisplayTypes.EnumPQBasicType enumPQBasicType) {
        AwTvDisplayTypes.AwPictureCurveOsdValue awPictureCurveOsdValue = new AwTvDisplayTypes.AwPictureCurveOsdValue();
        try {
            if (this.mDisplayHidlManager != null) {
                PictureCurveOsdValue pictureCurveOsdValueFactoryGetNonLinearPQValue = this.mDisplayHidlManager.factoryGetNonLinearPQValue(enumTvSourceType.getValue(), EnumPQBasicTypeToHidlType(enumPQBasicType));
                awPictureCurveOsdValue.v0 = pictureCurveOsdValueFactoryGetNonLinearPQValue.v0;
                awPictureCurveOsdValue.v25 = pictureCurveOsdValueFactoryGetNonLinearPQValue.v25;
                awPictureCurveOsdValue.v50 = pictureCurveOsdValueFactoryGetNonLinearPQValue.v50;
                awPictureCurveOsdValue.v75 = pictureCurveOsdValueFactoryGetNonLinearPQValue.v75;
                awPictureCurveOsdValue.v100 = pictureCurveOsdValueFactoryGetNonLinearPQValue.v100;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG, "can not get picture curve osd value");
        }
        return awPictureCurveOsdValue;
    }

    public int factoryAdvanceSetColorManager(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, AwTvDisplayTypes.EnumColorManagerGainItem enumColorManagerGainItem, int i, int i2) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryAdvanceSetColorManager(enumTvSourceType.getValue(), enumColorManagerGainItem.getValue(), i, i2);
        }, "factoryAdvanceSetColorManager", "sourceType=" + enumTvSourceType + " cm_gain_item=" + enumColorManagerGainItem.getValue() + " range=" + i + " value=" + i2);
    }

    public int[] factoryAdvanceGetColorManager(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType) {
        int[] iArr = new int[36];
        try {
            return this.mDisplayHidlManager.factoryAdvanceGetColorManager(enumTvSourceType.getValue());
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.e(TAG, "factoryAdvanceGetColorManager failed");
            return iArr;
        }
    }

    public int factoryAdvanceResetColorManager(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, AwTvDisplayTypes.EnumColorManagerGainItem enumColorManagerGainItem) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryAdvanceResetColorManager(enumTvSourceType.getValue(), enumColorManagerGainItem.getValue());
        }, "factoryAdvanceResetColorManager", "sourceType=" + enumTvSourceType + " cm_gain_item=" + enumColorManagerGainItem.getValue());
    }

    public int factorySetWbGainOffsetNotSave(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType, AwTvDisplayTypes.EnumColorTempMode enumColorTempMode, AwTvDisplayTypes.EnumPQRGBOGType enumPQRGBOGType, int i) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetWBValueNotSave(enumTvSourceType.getValue(), enumColorTempMode.getValue(), enumPQRGBOGType.getValue(), i);
        }, "factorySetWbGainOffsetNotSave", "sourceType= " + enumTvSourceType.getValue() + " mode= " + enumColorTempMode.getValue() + " item= " + enumPQRGBOGType.getValue() + " value= " + i);
    }

    public int factoryCopyWbGainOffetToOtherSrcNotSave(AwTvDisplayTypes.EnumTvSourceType enumTvSourceType) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryCopyWBInfoToOtherSrcNotSave(enumTvSourceType.getValue());
        }, "factoryCopyWbGainOffetToOtherSrcNotSave", "sourceType= " + enumTvSourceType.getValue());
    }

    public int factoryWbGainOffetNotSaveValueWriteIni() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factoryColorTempNotSaveValueWriteIni();
        }, "factoryWbGainOffetNotSaveValueWriteIni", null);
    }

    public int factoryCvbsSetPedestalMode(boolean z) {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.factorySetPQValue(AwTvDisplayTypes.EnumTvSourceType.E_AW_SOURCE_TYPE_MAX.getValue(), INVAILD_MODE, 22, z ? 1 : 0);
        }, "factoryCvbsSetPedestalMode", null);
    }

    public int factoryCvbsGetPedestalMode() {
        return runIntCmd(() -> {
            return this.mDisplayHidlManager.getPQValue(22);
        }, "factoryCvbsGetPedestalMode", null);
    }

    public ArrayList<String> factoryGetSignalInfo() {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            return this.mDisplayHidlManager.getTvSourceSignalInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    private int runIntCmd(IntCmd intCmd, String str, String str2) {
        int iRun;
        if (this.mDisplayHidlManager != null) {
            try {
                iRun = intCmd.run();
            } catch (RemoteException e) {
                e.printStackTrace();
                this.mDisplayHidlManager = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
                iRun = -1;
            }
        } else {
            iRun = -1;
        }
        debug("" + str + ": param (" + str2 + ") ret(" + iRun + ")");
        return iRun;
    }

    private String runStringCmd(StringCmd stringCmd, String str, String str2) {
        String strRun;
        if (this.mDisplayHidlManager != null) {
            try {
                strRun = stringCmd.run();
            } catch (RemoteException e) {
                e.printStackTrace();
                this.mDisplayHidlManager = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
                strRun = null;
            }
        } else {
            strRun = null;
        }
        debug("" + str + ": param (" + str2 + ") ret(" + strRun + ")");
        return strRun;
    }

    private int setPQValue(int i, int i2, String str, String str2) {
        int pQValue;
        if (this.mDisplayHidlManager != null) {
            try {
                pQValue = this.mDisplayHidlManager.setPQValue(i, i2);
            } catch (RemoteException unused) {
                Log.e(TAG, "exception, fail to get service");
                pQValue = -1;
            }
        } else {
            pQValue = -1;
        }
        debug("" + str + ": param (" + str2 + ") ret(" + pQValue + ")");
        return pQValue;
    }

    private int getPQValue(int i, String str, String str2) {
        int pQValue;
        if (this.mDisplayHidlManager != null) {
            try {
                pQValue = this.mDisplayHidlManager.getPQValue(i);
            } catch (RemoteException unused) {
                Log.e(TAG, "exception, fail to get service");
                pQValue = 0;
            }
        } else {
            pQValue = 0;
        }
        debug("" + str + ": param (" + str2 + ") ret(" + pQValue + ")");
        return pQValue;
    }

    private int EnumPQBasicTypeToHidlType(AwTvDisplayTypes.EnumPQBasicType enumPQBasicType) {
        switch (enumPQBasicType) {
            case E_AW_PQ_BASIC_TYPE_BRIGHTNESS:
                return 1;
            case E_AW_PQ_BASIC_TYPE_CONTRAST:
                return 2;
            case E_AW_PQ_BASIC_TYPE_SATURATION:
                return 3;
            case E_AW_PQ_BASIC_TYPE_HUE:
                return 4;
            case E_AW_PQ_BASIC_TYPE_SHARPNESS:
                return 5;
            default:
                return 1;
        }
    }
}
