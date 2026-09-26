package com.rk_itvui.settings.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.ashd.settings.R;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class EventManager {
    private EventCallback callback;
    private final Context mActivity;
    private final CachedBluetoothDeviceManager mCachedDeviceManager;
    private final LocalBluetoothAdapter mLocalBluetoothAdapter;
    private final LocalBluetoothManager mLocalBluetoothManager;
    private final String TAG = "BluetoothEvent";
    private boolean isRegister = false;
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.bluetooth.EventManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (EventManager.this.callback == null) {
                return;
            }
            String action = intent.getAction();
            Log.w("BluetoothEvent", "action == " + action);
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Handler handler = (Handler) EventManager.this.mHandlerMap.get(action);
            if (handler != null) {
                handler.onReceive(context, intent, bluetoothDevice);
            } else {
                Log.w("BluetoothEvent", "handler == null");
            }
        }
    };
    private final Map<String, Handler> mHandlerMap = new HashMap();
    private final IntentFilter mAdapterIntentFilter = new IntentFilter();

    interface Handler {
        void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice);
    }

    public static EventManager getEventManager(Context context, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, LocalBluetoothAdapter localBluetoothAdapter, LocalBluetoothManager localBluetoothManager) {
        if (context == null) {
            Log.e("Settings", "activity == null");
            return null;
        }
        if (cachedBluetoothDeviceManager == null) {
            Log.e("Settings", "cachedBluetoothDeviceManager == null");
            return null;
        }
        if (localBluetoothAdapter == null) {
            Log.e("Settings", "localBluetoothAdapter == null");
            return null;
        }
        if (localBluetoothManager == null) {
            Log.e("Settings", "localBluetoothManager == null");
            return null;
        }
        return new EventManager(context, cachedBluetoothDeviceManager, localBluetoothAdapter, localBluetoothManager);
    }

    private EventManager(Context context, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, LocalBluetoothAdapter localBluetoothAdapter, LocalBluetoothManager localBluetoothManager) {
        this.mActivity = context;
        this.mCachedDeviceManager = cachedBluetoothDeviceManager;
        this.mLocalBluetoothAdapter = localBluetoothAdapter;
        this.mLocalBluetoothManager = localBluetoothManager;
        addHandler("android.bluetooth.device.action.ACL_CONNECTED", new ConnectionStateChangedHandler());
        addHandler("android.bluetooth.adapter.action.STATE_CHANGED", new AdapterStateChangedHandler());
        addHandler("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED", new ConnectionStateChangedHandler());
        addHandler("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED", new ConnectionStateChangedHandler());
        addHandler("android.bluetooth.device.action.FOUND", new FoundDeviceHandler());
        addHandler("android.bluetooth.adapter.action.DISCOVERY_STARTED", new ScanningStateChangedHandler(true));
        addHandler("android.bluetooth.adapter.action.DISCOVERY_FINISHED", new ScanningStateChangedHandler(false));
        addHandler("android.bluetooth.device.action.DISAPPEARED", new DeviceDisappearedHandler());
        addHandler("android.bluetooth.device.action.NAME_CHANGED", new DeviceNameChangedHandler());
        addHandler("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED", new LocalDeviceNameChangedHandler());
        addHandler("android.bluetooth.device.action.ALIAS_CHANGED", new DeviceNameChangedHandler());
        addHandler("android.bluetooth.device.action.PAIRING_CANCEL", new PairingCancelHandler());
        addHandler("android.bluetooth.device.action.BOND_STATE_CHANGED", new DeviceBondStateChangedHandler());
        addHandler("android.bluetooth.device.action.PAIRING_REQUEST", new DeviceBondHandler());
        addHandler("android.bluetooth.device.action.CLASS_CHANGED", new ClassChangedHandler());
        addHandler("android.intent.action.DOCK_EVENT", new DockEventHandler());
    }

    private void addHandler(String str, Handler handler) {
        this.mHandlerMap.put(str, handler);
        this.mAdapterIntentFilter.addAction(str);
    }

    public void registerCallback(EventCallback eventCallback) {
        this.mActivity.registerReceiver(this.mBroadcastReceiver, this.mAdapterIntentFilter);
        this.callback = eventCallback;
        this.isRegister = true;
    }

    public void unregisterCallback() {
        this.mActivity.unregisterReceiver(this.mBroadcastReceiver);
        this.callback = null;
        this.isRegister = false;
    }

    private class AdapterStateChangedHandler implements Handler {
        private AdapterStateChangedHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.d("BluetoothEvent", "AdapterStateChangedHandler");
            EventManager.this.callback.onBluetoothStateChanged(intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE));
        }
    }

    private class ScanningStateChangedHandler implements Handler {
        private final boolean mStarted;

        public ScanningStateChangedHandler(boolean z) {
            this.mStarted = z;
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.d("BluetoothEvent", "ScanningStateChangedHandler");
            EventManager.this.callback.onScanningStateChanged(this.mStarted);
        }
    }

    private class FoundDeviceHandler implements Handler {
        private FoundDeviceHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.d("BluetoothEvent", "FoundDeviceHandler");
            if (bluetoothDevice == null) {
                Log.e("BluetoothEvent", "FoundDevice == null");
            }
            CachedBluetoothDevice cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice);
            if (cachedDevice == null) {
                return;
            }
            short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
            if (shortExtra == 0) {
                shortExtra = Short.MIN_VALUE;
            }
            EventManager.this.callback.onDeviceAdded(cachedDevice, shortExtra);
        }
    }

    private class DeviceBondHandler implements Handler {
        private DeviceBondHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.d("BluetoothEvent", "DeviceBondHandler");
            if (intent == null) {
                return;
            }
            EventManager.this.callback.onDeviceBond(intent);
        }
    }

    private class DeviceBondStateChangedHandler implements Handler {
        private DeviceBondStateChangedHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.d("BluetoothEvent", "DeviceBondStateChangedHandler");
            if (bluetoothDevice == null) {
                Log.e("BluetoothEvent", "DeviceBondStateDevice == null");
                return;
            }
            CachedBluetoothDevice cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice);
            if (cachedDevice == null) {
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
            EventManager.this.callback.onDeviceBondStateChanged(cachedDevice, intExtra);
            if (intExtra == 10) {
                int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.REASON", Integer.MIN_VALUE);
                EventManager.this.callback.onDeviceBondStateChanged(cachedDevice, Integer.MIN_VALUE);
                EventManager.this.showUnbondMessage(context, bluetoothDevice.getName(), intExtra2);
            }
        }
    }

    private class ConnectionStateChangedHandler implements Handler {
        private ConnectionStateChangedHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.e("BluetoothEvent", "state2 ==" + intent.getIntExtra("android.bluetooth.profile.extra.STATE", 1));
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", Integer.MIN_VALUE);
            if (bluetoothDevice == null) {
                Log.e("BluetoothEvent", "ConnectionStateDevice == null");
                return;
            }
            CachedBluetoothDevice cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice);
            if (cachedDevice == null) {
                return;
            }
            EventManager.this.callback.onConnectionStateChanged(cachedDevice, intExtra);
        }
    }

    private class LocalDeviceNameChangedHandler implements Handler {
        private LocalDeviceNameChangedHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.i("BluetoothEvent", "LocalDeviceNameChangedHandler");
            EventManager.this.callback.onLocalDeviceNameChanged();
        }
    }

    private class DeviceNameChangedHandler implements Handler {
        private DeviceNameChangedHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.i("BluetoothEvent", "DeviceNameChangedHandler");
            if (bluetoothDevice == null) {
                Log.e("BluetoothEvent", "DeviceNameChangedDevice == null");
            }
            CachedBluetoothDevice cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice);
            if (cachedDevice == null) {
                return;
            }
            EventManager.this.callback.onDeviceNameChanged(cachedDevice);
        }
    }

    private class DeviceDisappearedHandler implements Handler {
        private DeviceDisappearedHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.i("BluetoothEvent", "DeviceDisappearedHandler");
            if (bluetoothDevice == null) {
                Log.e("BluetoothEvent", "DisappearedDevice == null");
                return;
            }
            CachedBluetoothDevice cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice);
            if (cachedDevice == null) {
                return;
            }
            EventManager.this.callback.onDeviceDisappeared(cachedDevice);
        }
    }

    private class PairingCancelHandler implements Handler {
        private PairingCancelHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.i("BluetoothEvent", "PairingCancelHandler");
            if (bluetoothDevice == null) {
                Log.e("BluetoothEvent", "PairingCancelDevice == null");
                return;
            }
            Log.i("BluetoothEvent", "蓝牙配对取消");
            CachedBluetoothDevice cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice);
            if (cachedDevice == null) {
                return;
            }
            EventManager.this.callback.onDeviceBondStateChanged(cachedDevice, Integer.MIN_VALUE);
            if (context != null) {
                BluetoothUtils.showError(context, bluetoothDevice.getName(), R.string.bluetooth_pairing_error_message);
            }
        }
    }

    private class ClassChangedHandler implements Handler {
        private ClassChangedHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            Log.i("BluetoothEvent", "ClassChangedHandler");
            if (bluetoothDevice == null) {
                Log.e("BluetoothEvent", "ClassChangedHandlerDevice == null");
                return;
            }
            CachedBluetoothDevice cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice);
            if (cachedDevice == null) {
                return;
            }
            EventManager.this.callback.onDeviceClassChanged(cachedDevice, cachedDevice.getBtClass());
        }
    }

    private class DockEventHandler implements Handler {
        private DockEventHandler() {
        }

        @Override // com.rk_itvui.settings.bluetooth.EventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedDevice;
            Log.i("BluetoothEvent", "DockEventHandler");
            if (intent.getIntExtra("android.intent.extra.DOCK_STATE", 1) != 0 || bluetoothDevice == null || bluetoothDevice.getBondState() != 10 || (cachedDevice = EventManager.this.toCachedDevice(bluetoothDevice)) == null) {
                return;
            }
            cachedDevice.setVisible(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUnbondMessage(Context context, String str, int i) {
        int i2;
        switch (i) {
            case 1:
                i2 = R.string.bluetooth_pairing_pin_error_message;
                break;
            case 2:
                i2 = R.string.bluetooth_pairing_rejected_error_message;
                break;
            case 3:
            default:
                Log.w("BluetoothEvent", "showUnbondMessage: Not displaying any message for reason: " + i);
                return;
            case 4:
                i2 = R.string.bluetooth_pairing_device_down_error_message;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                i2 = R.string.bluetooth_pairing_error_message;
                break;
        }
        BluetoothUtils.showError(context, str, i2);
    }

    CachedBluetoothDevice toCachedDevice(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            return null;
        }
        CachedBluetoothDevice cachedBluetoothDeviceFindDevice = this.mCachedDeviceManager.findDevice(bluetoothDevice);
        return cachedBluetoothDeviceFindDevice == null ? this.mCachedDeviceManager.addDevice(this.mLocalBluetoothAdapter, this.mLocalBluetoothManager.getProfileManager(), bluetoothDevice) : cachedBluetoothDeviceFindDevice;
    }

    public boolean isRegister() {
        return this.isRegister;
    }
}
