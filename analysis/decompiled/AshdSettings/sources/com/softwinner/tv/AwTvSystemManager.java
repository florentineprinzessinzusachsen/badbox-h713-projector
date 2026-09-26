package com.softwinner.tv;

import android.content.Context;
import android.os.PowerManager;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import com.softwinner.tv.client.AwTvServerManagerProxy;
import com.softwinner.tv.common.AwTvSystemTypes;
import java.util.ArrayList;
import vendor.aw.homlet.tvsystem.tvserver.V1_0.ITvServer;

/* JADX INFO: loaded from: classes.dex */
public final class AwTvSystemManager {
    private static final boolean ISDEBUG = false;
    private static boolean ISPowerOffWakeup = true;
    private static String PowerOffWakeupReason = null;
    private static final int TYPE_BACKLIGHT_ENABLE = 19;
    private static AwTvSystemManager sAwTvSystemManager;
    private Context mContext;
    ITvServer mSystemHidlManager;
    private static final String TAG = "AwTvSystemManager";
    private static final boolean DEBUG = Log.isLoggable(TAG, 3);

    interface IntCmd {
        int run() throws RemoteException;
    }

    public boolean setPlayReady40Key() {
        return false;
    }

    private void debug(String str) {
        if (DEBUG) {
            Log.d(TAG, str);
        }
    }

    public AwTvSystemManager(Context context) {
        this.mSystemHidlManager = null;
        this.mContext = context;
        this.mSystemHidlManager = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
    }

    public static AwTvSystemManager getInstance(Context context) {
        if (sAwTvSystemManager == null) {
            sAwTvSystemManager = new AwTvSystemManager(context);
        }
        return sAwTvSystemManager;
    }

    public int setUartDebugEnable(boolean z) {
        SystemProperties.set("persist.debug.console", z ? "1" : "0");
        return 0;
    }

    public boolean getUartDebugEnable() {
        return 1 == SystemProperties.getInt("persist.debug.console", 0);
    }

    public boolean setChannelLockEnable(boolean z) {
        return Settings.System.putInt(this.mContext.getContentResolver(), "tv_channel_lock_enable", z ? 1 : 0);
    }

    public boolean getChannelLockEnable() {
        return 1 == Settings.System.getInt(this.mContext.getContentResolver(), "tv_channel_lock_enable", 1);
    }

    public boolean setSnowScreenEnable(boolean z) {
        return Settings.System.putInt(this.mContext.getContentResolver(), "tv_snow_screen_enable", z ? 1 : 0);
    }

    public boolean getSnowScreenEnable() {
        return 1 == Settings.System.getInt(this.mContext.getContentResolver(), "tv_snow_screen_enable", 0);
    }

    public boolean setAfcEnable(boolean z) {
        return Settings.System.putInt(this.mContext.getContentResolver(), "tv_atv_afc_enable", z ? 1 : 0);
    }

    public boolean getAfcEnable() {
        return 1 == Settings.System.getInt(this.mContext.getContentResolver(), "tv_atv_afc_enable", 0);
    }

    public void setStrEnable(boolean z) {
        if (z) {
            Settings.Global.putInt(this.mContext.getContentResolver(), "power_button_short_press", 1);
        } else {
            Settings.Global.putInt(this.mContext.getContentResolver(), "power_button_short_press", 7);
        }
    }

    public boolean isStrEnable() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "power_button_short_press", 1) == 1;
    }

    public AwTvSystemTypes.EnumWakeUpReason getWakeupReason() {
        int lastWakeUpReason = ((PowerManager) this.mContext.getSystemService("power")).getLastWakeUpReason();
        if (lastWakeUpReason > 0) {
            ISPowerOffWakeup = false;
        }
        if (PowerOffWakeupReason == null) {
            PowerOffWakeupReason = getEnv("PowerOffWakeupReason");
            Log.d(TAG, "PowerOffWakeupReason " + PowerOffWakeupReason);
            if (PowerOffWakeupReason != null && PowerOffWakeupReason.equals(String.valueOf(8))) {
                setEnv("PowerOffWakeupReason", "0");
            }
        }
        switch (lastWakeUpReason) {
            case 1:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_KEY;
            case 2:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_APP;
            case 3:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_PLUG;
            case 4:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_GESTURE;
            case 5:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_GESTURE;
            case 6:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_KEY;
            case 7:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_MOTION;
            case 8:
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_HDMI;
            default:
                if (ISPowerOffWakeup && PowerOffWakeupReason != null && PowerOffWakeupReason.equals(String.valueOf(8))) {
                    return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_HDMI;
                }
                return AwTvSystemTypes.EnumWakeUpReason.E_AW_WAKE_UP_REASON_UNKNOWN;
        }
    }

    public void setRTCWakeUpEnable(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "rtc_control_enable", z ? 1 : 0);
    }

    public boolean isRTCWakeUpEnable() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "rtc_control_enable", 1) == 1;
    }

    public boolean getCECStatus() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "hdmi_control_enabled", 0) > 0;
    }

    public void setCECEnable(boolean z) {
        Settings.Global.putInt(this.mContext.getContentResolver(), "hdmi_control_enabled", z ? 1 : 0);
    }

    public boolean setArcEnable(boolean z) {
        return Settings.Global.putInt(this.mContext.getContentResolver(), "hdmi_system_audio_control_enabled", z ? 1 : 0);
    }

    public boolean checkArcStatus() {
        return Settings.Global.getInt(this.mContext.getContentResolver(), "hdmi_system_audio_control_enabled", 1) > 0;
    }

    public int factorySetDDRValue(AwTvSystemTypes.EnumConfigType enumConfigType, int i) {
        return runIntCmd(() -> {
            return this.mSystemHidlManager.factorySetDDRValue(EnumConfigTypeToHidlType(enumConfigType), i);
        }, "factorySetDDRValue", "configType= " + enumConfigType.getValue() + " value= " + i);
    }

    public int factoryGetDDRValue(AwTvSystemTypes.EnumConfigType enumConfigType) {
        return runIntCmd(() -> {
            return this.mSystemHidlManager.factoryGetDDRValue(EnumConfigTypeToHidlType(enumConfigType));
        }, "factoryGetDDRValue", null);
    }

    public int setPowerOnMode(AwTvSystemTypes.EnumPowerMode enumPowerMode) {
        return runIntCmd(() -> {
            return this.mSystemHidlManager.factorySetPowerMode(enumPowerMode.getValue());
        }, "setPowerOnMode", "mode= " + enumPowerMode.getValue());
    }

    public AwTvSystemTypes.EnumPowerMode getPowerOnMode() {
        return AwTvSystemTypes.EnumPowerMode.valueOf(runIntCmd(() -> {
            return this.mSystemHidlManager.factoryGetPowerMode();
        }, "getPowerOnMode", null));
    }

    public int setBacklightEnable(boolean z) {
        return runIntCmd(() -> {
            return this.mSystemHidlManager.setPQValue(19, z ? 1 : 0);
        }, "setBacklightEnable", "state= " + z);
    }

    public boolean getBacklightEnable() {
        return runIntCmd(() -> {
            return this.mSystemHidlManager.getPQValue(19);
        }, "getBacklightEnable", null) != 0;
    }

    private int runIntCmd(IntCmd intCmd, String str, String str2) {
        int iRun;
        if (this.mSystemHidlManager != null) {
            try {
                iRun = intCmd.run();
            } catch (RemoteException e) {
                e.printStackTrace();
                this.mSystemHidlManager = AwTvServerManagerProxy.getInstance().getAwTvServerManager();
                iRun = -1;
            }
        } else {
            iRun = -1;
        }
        debug("" + str + ": param (" + str2 + ") ret(" + iRun + ")");
        return iRun;
    }

    public byte[] getEDID2Key() {
        try {
            ArrayList<Byte> edidKey = this.mSystemHidlManager.getEdidKey(1);
            byte[] bArr = new byte[edidKey.size()];
            for (int i = 0; i < edidKey.size(); i++) {
                bArr[i] = edidKey.get(i).byteValue();
            }
            return bArr;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setEDID2Key(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        ArrayList<Byte> arrayList = new ArrayList<>(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        try {
            return this.mSystemHidlManager.setEdidKey(1, arrayList) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public byte[] getEDID1Key() {
        try {
            ArrayList<Byte> edidKey = this.mSystemHidlManager.getEdidKey(0);
            byte[] bArr = new byte[edidKey.size()];
            for (int i = 0; i < edidKey.size(); i++) {
                bArr[i] = edidKey.get(i).byteValue();
            }
            return bArr;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setEDID1Key(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        ArrayList<Byte> arrayList = new ArrayList<>(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        try {
            return this.mSystemHidlManager.setEdidKey(0, arrayList) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public String getHDCP2xKey() {
        try {
            return this.mSystemHidlManager.getHdcpKeySum(1);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setHDCP2xKey(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        ArrayList<Byte> arrayList = new ArrayList<>(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        try {
            return this.mSystemHidlManager.setHdcpKey(1, arrayList) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public String getHDCP1xKey() {
        try {
            return this.mSystemHidlManager.getHdcpKeySum(0);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setHDCP1xKey(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        ArrayList<Byte> arrayList = new ArrayList<>(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        try {
            return this.mSystemHidlManager.setHdcpKey(0, arrayList) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public boolean removeHDCP1xKey() {
        try {
            return this.mSystemHidlManager.removeHdcpKey(0) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public boolean removeHDCP2xKey() {
        try {
            return this.mSystemHidlManager.removeHdcpKey(1) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public void reloadHDCP1xKey() {
        try {
            this.mSystemHidlManager.reloadHdcpKey(0);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
        }
    }

    public void reloadHDCP2xKey() {
        try {
            this.mSystemHidlManager.reloadHdcpKey(1);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
        }
    }

    public String getWidevineKey() {
        try {
            return this.mSystemHidlManager.getWidevineKeySum();
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setWidevineKey(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        ArrayList<Byte> arrayList = new ArrayList<>(bArr.length);
        for (byte b : bArr) {
            arrayList.add(Byte.valueOf(b));
        }
        try {
            return this.mSystemHidlManager.setWidevineKey(arrayList) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public boolean removeWidevineKey() {
        try {
            return this.mSystemHidlManager.removeWidevineKey() == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public String getEthMacAddr() {
        try {
            return this.mSystemHidlManager.getMacKey("mac");
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setEthMacAddr(String str) {
        try {
            return this.mSystemHidlManager.setMacKey("mac", str) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public String getWifiMacAddr() {
        try {
            return this.mSystemHidlManager.getMacKey("wifi_mac");
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setWifiMacAddr(String str) {
        try {
            return this.mSystemHidlManager.setMacKey("wifi_mac", str) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public String getBluetoothMacAddr() {
        try {
            return this.mSystemHidlManager.getMacKey("bt_mac");
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public boolean setBluetoothMacAddr(String str) {
        try {
            return this.mSystemHidlManager.setMacKey("bt_mac", str) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public boolean setSecureStorageKey(String str, String str2) {
        try {
            return this.mSystemHidlManager.setSecureStorageKey(str, str2) == 0;
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return false;
        }
    }

    public String getSecureStorageKey(String str) {
        try {
            return this.mSystemHidlManager.getSecureStorageKey(str);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public String getEnv(String str) {
        try {
            return this.mSystemHidlManager.getEnv(str);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return null;
        }
    }

    public int setEnv(String str, String str2) {
        try {
            return this.mSystemHidlManager.setEnv(str, str2);
        } catch (RemoteException unused) {
            Log.e(TAG, "exception, fail to get service");
            return -1;
        }
    }

    private int EnumConfigTypeToHidlType(AwTvSystemTypes.EnumConfigType enumConfigType) {
        switch (enumConfigType) {
            case E_AW_CONFIG_DDR_ENABLE:
                return 40;
            case E_AW_CONFIG_DDR_STEP:
                return 42;
            case E_AW_CONFIG_DDR_SPAN:
                return 41;
            default:
                return 40;
        }
    }

    public boolean setHdmiRxEdidType(AwTvSystemTypes.EnumHdmiRxEdidType enumHdmiRxEdidType) {
        switch (enumHdmiRxEdidType) {
            case E_AW_HDMI_EDID_Version_14:
                return true;
            case E_AW_HDMI_EDID_Version_20:
                return false;
            default:
                return false;
        }
    }

    public AwTvSystemTypes.EnumHdmiRxEdidType getHdmiRxEdidType() {
        return AwTvSystemTypes.EnumHdmiRxEdidType.E_AW_HDMI_EDID_Version_14;
    }
}
