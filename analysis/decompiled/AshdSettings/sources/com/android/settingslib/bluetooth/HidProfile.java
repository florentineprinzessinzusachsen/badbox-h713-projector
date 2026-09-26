package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;
import com.android.settingslib.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class HidProfile implements LocalBluetoothProfile {
    static final String NAME = "HID";
    private static final int ORDINAL = 3;
    private static int PRIORITY_OFF = 0;
    private static final String TAG = "HidProfile";
    private static boolean V = true;
    private final CachedBluetoothDeviceManager mDeviceManager;
    private boolean mIsProfileReady;
    private final LocalBluetoothAdapter mLocalAdapter;
    private final LocalBluetoothProfileManager mProfileManager;
    private BluetoothProfile mService;

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getOrdinal() {
        return 3;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isAutoConnectable() {
        return true;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isConnectable() {
        return true;
    }

    public String toString() {
        return NAME;
    }

    private final class InputDeviceServiceListener implements BluetoothProfile.ServiceListener {
        private InputDeviceServiceListener() {
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            if (HidProfile.V) {
                Log.d(HidProfile.TAG, "profile=" + i + ",===Bluetooth service connected" + bluetoothProfile.toString());
            }
            HidProfile.this.mService = bluetoothProfile;
            List list = (List) ReflectionUtils.invokeMethod(HidProfile.this.mService, "getConnectedDevices", new Object[0]);
            while (!list.isEmpty()) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) list.remove(0);
                CachedBluetoothDevice cachedBluetoothDeviceFindDevice = HidProfile.this.mDeviceManager.findDevice(bluetoothDevice);
                if (cachedBluetoothDeviceFindDevice == null) {
                    Log.w(HidProfile.TAG, "HidProfile found new device: " + bluetoothDevice);
                    cachedBluetoothDeviceFindDevice = HidProfile.this.mDeviceManager.addDevice(HidProfile.this.mLocalAdapter, HidProfile.this.mProfileManager, bluetoothDevice);
                }
                cachedBluetoothDeviceFindDevice.onProfileStateChanged(HidProfile.this, 2);
                cachedBluetoothDeviceFindDevice.refresh();
            }
            HidProfile.this.mIsProfileReady = true;
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            if (HidProfile.V) {
                Log.d(HidProfile.TAG, "Bluetooth service disconnected");
            }
            HidProfile.this.mIsProfileReady = false;
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isProfileReady() {
        return this.mIsProfileReady;
    }

    HidProfile(Context context, LocalBluetoothAdapter localBluetoothAdapter, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mLocalAdapter = localBluetoothAdapter;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mProfileManager = localBluetoothProfileManager;
        localBluetoothAdapter.getProfileProxy(context, new InputDeviceServiceListener(), 4);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean connect(BluetoothDevice bluetoothDevice) {
        if (this.mService == null) {
            return false;
        }
        return ((Boolean) ReflectionUtils.invokeMethod(this.mService, "connect", bluetoothDevice)).booleanValue();
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean disconnect(BluetoothDevice bluetoothDevice) {
        if (this.mService == null) {
            return false;
        }
        return ((Boolean) ReflectionUtils.invokeMethod(this.mService, "disconnect", bluetoothDevice)).booleanValue();
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getConnectionStatus(BluetoothDevice bluetoothDevice) {
        if (this.mService == null) {
            return 0;
        }
        List list = (List) ReflectionUtils.invokeMethod(this.mService, "getConnectedDevices", new Object[0]);
        if (list.isEmpty() || !((BluetoothDevice) list.get(0)).equals(bluetoothDevice)) {
            return 0;
        }
        return this.mService.getConnectionState(bluetoothDevice);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public boolean isPreferred(BluetoothDevice bluetoothDevice) {
        return this.mService != null && ((Integer) ReflectionUtils.invokeMethod(this.mService, "getPriority", bluetoothDevice)).intValue() > PRIORITY_OFF;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getPreferred(BluetoothDevice bluetoothDevice) {
        return this.mService == null ? PRIORITY_OFF : ((Integer) ReflectionUtils.invokeMethod(this.mService, "getPriority", bluetoothDevice)).intValue();
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public void setPreferred(BluetoothDevice bluetoothDevice, boolean z) {
        if (this.mService == null) {
            return;
        }
        if (!z) {
            ReflectionUtils.invokeMethod(this.mService, "setPriority", bluetoothDevice, 0);
        } else if (((Integer) ReflectionUtils.invokeMethod(this.mService, "getPriority", bluetoothDevice)).intValue() < 1) {
            ReflectionUtils.invokeMethod(this.mService, "setPriority", bluetoothDevice, 1);
        }
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getNameResource(BluetoothDevice bluetoothDevice) {
        return R.string.bluetooth_profile_hid;
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getSummaryResourceForDevice(BluetoothDevice bluetoothDevice) {
        int connectionStatus = getConnectionStatus(bluetoothDevice);
        if (connectionStatus == 0) {
            return R.string.bluetooth_hid_profile_summary_use_for;
        }
        if (connectionStatus == 2) {
            return R.string.bluetooth_hid_profile_summary_connected;
        }
        return Utils.getConnectionStateSummary(connectionStatus);
    }

    @Override // com.android.settingslib.bluetooth.LocalBluetoothProfile
    public int getDrawableResource(BluetoothClass bluetoothClass) {
        if (bluetoothClass == null) {
            return R.drawable.ic_lockscreen_ime;
        }
        return getHidClassDrawable(bluetoothClass);
    }

    public static int getHidClassDrawable(BluetoothClass bluetoothClass) {
        int deviceClass = bluetoothClass.getDeviceClass();
        if (deviceClass != 1344) {
            if (deviceClass == 1408) {
                return R.drawable.ic_bt_pointing_hid;
            }
            if (deviceClass != 1472) {
                return R.drawable.ic_bt_misc_hid;
            }
        }
        return R.drawable.ic_lockscreen_ime;
    }

    protected void finalize() {
        if (V) {
            Log.d(TAG, "finalize()");
        }
        if (this.mService != null) {
            try {
                BluetoothAdapter.getDefaultAdapter().closeProfileProxy(4, this.mService);
                this.mService = null;
            } catch (Throwable th) {
                Log.w(TAG, "Error cleaning up HID proxy", th);
            }
        }
    }
}
