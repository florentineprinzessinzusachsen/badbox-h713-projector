package com.android.settingslib.bluetooth;

import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.android.settingslib.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class BluetoothEventManager {
    private static final String TAG = "BluetoothEventManager";
    private Context mContext;
    private final CachedBluetoothDeviceManager mDeviceManager;
    private final LocalBluetoothAdapter mLocalAdapter;
    private LocalBluetoothProfileManager mProfileManager;
    private android.os.Handler mReceiverHandler;
    private final Collection<BluetoothCallback> mCallbacks = new ArrayList();
    private final BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.android.settingslib.bluetooth.BluetoothEventManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            Handler handler = (Handler) BluetoothEventManager.this.mHandlerMap.get(action);
            if (handler != null) {
                handler.onReceive(context, intent, bluetoothDevice);
            }
        }
    };
    private final IntentFilter mAdapterIntentFilter = new IntentFilter();
    private final IntentFilter mProfileIntentFilter = new IntentFilter();
    private final Map<String, Handler> mHandlerMap = new HashMap();

    interface Handler {
        void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice);
    }

    private void addHandler(String str, Handler handler) {
        this.mHandlerMap.put(str, handler);
        this.mAdapterIntentFilter.addAction(str);
    }

    void addProfileHandler(String str, Handler handler) {
        this.mHandlerMap.put(str, handler);
        this.mProfileIntentFilter.addAction(str);
    }

    void setProfileManager(LocalBluetoothProfileManager localBluetoothProfileManager) {
        this.mProfileManager = localBluetoothProfileManager;
    }

    BluetoothEventManager(LocalBluetoothAdapter localBluetoothAdapter, CachedBluetoothDeviceManager cachedBluetoothDeviceManager, Context context) {
        this.mLocalAdapter = localBluetoothAdapter;
        this.mDeviceManager = cachedBluetoothDeviceManager;
        this.mContext = context;
        addHandler("android.bluetooth.adapter.action.STATE_CHANGED", new AdapterStateChangedHandler());
        addHandler("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED", new ConnectionStateChangedHandler());
        addHandler("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED", new ConnectionStateChangedHandler());
        addHandler("android.bluetooth.adapter.action.DISCOVERY_STARTED", new ScanningStateChangedHandler(true));
        addHandler("android.bluetooth.adapter.action.DISCOVERY_FINISHED", new ScanningStateChangedHandler(false));
        addHandler("android.bluetooth.device.action.FOUND", new DeviceFoundHandler());
        addHandler("android.bluetooth.device.action.DISAPPEARED", new DeviceDisappearedHandler());
        addHandler("android.bluetooth.device.action.NAME_CHANGED", new NameChangedHandler());
        addHandler("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED", new ConnectionStateChangedHandler());
        addHandler("android.bluetooth.device.action.BOND_STATE_CHANGED", new BondStateChangedHandler());
        addHandler("android.bluetooth.device.action.PAIRING_CANCEL", new PairingCancelHandler());
        addHandler("android.bluetooth.device.action.CLASS_CHANGED", new ClassChangedHandler());
        addHandler("android.bluetooth.device.action.UUID", new UuidChangedHandler());
        addHandler("android.intent.action.DOCK_EVENT", new DockEventHandler());
        this.mContext.registerReceiver(this.mBroadcastReceiver, this.mAdapterIntentFilter, null, this.mReceiverHandler);
    }

    void registerProfileIntentReceiver() {
        this.mContext.registerReceiver(this.mBroadcastReceiver, this.mProfileIntentFilter, null, this.mReceiverHandler);
    }

    public void setReceiverHandler(android.os.Handler handler) {
        this.mContext.unregisterReceiver(this.mBroadcastReceiver);
        this.mReceiverHandler = handler;
        this.mContext.registerReceiver(this.mBroadcastReceiver, this.mAdapterIntentFilter, null, this.mReceiverHandler);
        registerProfileIntentReceiver();
    }

    public void registerCallback(BluetoothCallback bluetoothCallback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.add(bluetoothCallback);
        }
    }

    public void unregisterCallback(BluetoothCallback bluetoothCallback) {
        synchronized (this.mCallbacks) {
            this.mCallbacks.remove(bluetoothCallback);
        }
    }

    private class AdapterStateChangedHandler implements Handler {
        private AdapterStateChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            BluetoothEventManager.this.mLocalAdapter.setBluetoothStateInt(intExtra);
            synchronized (BluetoothEventManager.this.mCallbacks) {
                Iterator it = BluetoothEventManager.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((BluetoothCallback) it.next()).onBluetoothStateChanged(intExtra);
                }
            }
            BluetoothEventManager.this.mDeviceManager.onBluetoothStateChanged(intExtra);
        }
    }

    private class ScanningStateChangedHandler implements Handler {
        private final boolean mStarted;

        ScanningStateChangedHandler(boolean z) {
            this.mStarted = z;
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            synchronized (BluetoothEventManager.this.mCallbacks) {
                Iterator it = BluetoothEventManager.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((BluetoothCallback) it.next()).onScanningStateChanged(this.mStarted);
                }
            }
            BluetoothEventManager.this.mDeviceManager.onScanningStateChanged(this.mStarted);
        }
    }

    private class DeviceFoundHandler implements Handler {
        private DeviceFoundHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            short shortExtra = intent.getShortExtra("android.bluetooth.device.extra.RSSI", Short.MIN_VALUE);
            BluetoothClass bluetoothClass = (BluetoothClass) intent.getParcelableExtra("android.bluetooth.device.extra.CLASS");
            String stringExtra = intent.getStringExtra("android.bluetooth.device.extra.NAME");
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice == null) {
                cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.addDevice(BluetoothEventManager.this.mLocalAdapter, BluetoothEventManager.this.mProfileManager, bluetoothDevice);
                Log.d(BluetoothEventManager.TAG, "DeviceFoundHandler created new CachedBluetoothDevice: " + cachedBluetoothDeviceFindDevice);
            }
            cachedBluetoothDeviceFindDevice.setRssi(shortExtra);
            cachedBluetoothDeviceFindDevice.setBtClass(bluetoothClass);
            cachedBluetoothDeviceFindDevice.setNewName(stringExtra);
            cachedBluetoothDeviceFindDevice.setVisible(true);
        }
    }

    private class ConnectionStateChangedHandler implements Handler {
        private ConnectionStateChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", Integer.MIN_VALUE);
            Log.d(BluetoothEventManager.TAG, "state33333 : " + intExtra);
            BluetoothEventManager.this.dispatchConnectionStateChanged(cachedBluetoothDeviceFindDevice, intExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        synchronized (this.mCallbacks) {
            Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onConnectionStateChanged(cachedBluetoothDevice, i);
            }
        }
    }

    void dispatchDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice) {
        synchronized (this.mCallbacks) {
            Iterator<BluetoothCallback> it = this.mCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onDeviceAdded(cachedBluetoothDevice);
            }
        }
    }

    private class DeviceDisappearedHandler implements Handler {
        private DeviceDisappearedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice == null) {
                Log.w(BluetoothEventManager.TAG, "received ACTION_DISAPPEARED for an unknown device: " + bluetoothDevice);
                return;
            }
            if (CachedBluetoothDeviceManager.onDeviceDisappeared(cachedBluetoothDeviceFindDevice)) {
                synchronized (BluetoothEventManager.this.mCallbacks) {
                    Iterator it = BluetoothEventManager.this.mCallbacks.iterator();
                    while (it.hasNext()) {
                        ((BluetoothCallback) it.next()).onDeviceDeleted(cachedBluetoothDeviceFindDevice);
                    }
                }
            }
        }
    }

    private class NameChangedHandler implements Handler {
        private NameChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            BluetoothEventManager.this.mDeviceManager.onDeviceNameUpdated(bluetoothDevice);
        }
    }

    private class BondStateChangedHandler implements Handler {
        private BondStateChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice == null) {
                Log.e(BluetoothEventManager.TAG, "ACTION_BOND_STATE_CHANGED with no EXTRA_DEVICE");
                return;
            }
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.BOND_STATE", Integer.MIN_VALUE);
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
            if (cachedBluetoothDeviceFindDevice == null) {
                Log.w(BluetoothEventManager.TAG, "CachedBluetoothDevice for device " + bluetoothDevice + " not found, calling readPairedDevices().");
                if (BluetoothEventManager.this.readPairedDevices()) {
                    cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
                }
                if (cachedBluetoothDeviceFindDevice == null) {
                    Log.w(BluetoothEventManager.TAG, "Got bonding state changed for " + bluetoothDevice + ", but we have no record of that device.");
                    cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.addDevice(BluetoothEventManager.this.mLocalAdapter, BluetoothEventManager.this.mProfileManager, bluetoothDevice);
                    BluetoothEventManager.this.dispatchDeviceAdded(cachedBluetoothDeviceFindDevice);
                }
            }
            synchronized (BluetoothEventManager.this.mCallbacks) {
                Iterator it = BluetoothEventManager.this.mCallbacks.iterator();
                while (it.hasNext()) {
                    ((BluetoothCallback) it.next()).onDeviceBondStateChanged(cachedBluetoothDeviceFindDevice, intExtra);
                }
            }
            cachedBluetoothDeviceFindDevice.onBondingStateChanged(intExtra);
            if (intExtra == 10) {
                showUnbondMessage(context, cachedBluetoothDeviceFindDevice.getName(), intent.getIntExtra("android.bluetooth.device.extra.REASON", Integer.MIN_VALUE));
            }
        }

        private void showUnbondMessage(Context context, String str, int i) {
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
                    Log.w(BluetoothEventManager.TAG, "showUnbondMessage: Not displaying any message for reason: " + i);
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
            Utils.showError(context, str, i2);
        }
    }

    private class ClassChangedHandler implements Handler {
        private ClassChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            BluetoothEventManager.this.mDeviceManager.onBtClassChanged(bluetoothDevice);
        }
    }

    private class UuidChangedHandler implements Handler {
        private UuidChangedHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            BluetoothEventManager.this.mDeviceManager.onUuidChanged(bluetoothDevice);
        }
    }

    private class PairingCancelHandler implements Handler {
        private PairingCancelHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            if (bluetoothDevice != null) {
                CachedBluetoothDevice cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice);
                if (cachedBluetoothDeviceFindDevice == null) {
                    Log.e(BluetoothEventManager.TAG, "ACTION_PAIRING_CANCEL with no cached device");
                    return;
                }
                int i = R.string.bluetooth_pairing_error_message;
                if (context == null || cachedBluetoothDeviceFindDevice == null) {
                    return;
                }
                Utils.showError(context, cachedBluetoothDeviceFindDevice.getName(), i);
                return;
            }
            Log.e(BluetoothEventManager.TAG, "ACTION_PAIRING_CANCEL with no EXTRA_DEVICE");
        }
    }

    private class DockEventHandler implements Handler {
        private DockEventHandler() {
        }

        @Override // com.android.settingslib.bluetooth.BluetoothEventManager.Handler
        public void onReceive(Context context, Intent intent, BluetoothDevice bluetoothDevice) {
            CachedBluetoothDevice cachedBluetoothDeviceFindDevice;
            if (intent.getIntExtra("android.intent.extra.DOCK_STATE", 1) != 0 || bluetoothDevice == null || bluetoothDevice.getBondState() != 10 || (cachedBluetoothDeviceFindDevice = BluetoothEventManager.this.mDeviceManager.findDevice(bluetoothDevice)) == null) {
                return;
            }
            cachedBluetoothDeviceFindDevice.setVisible(false);
        }
    }

    boolean readPairedDevices() {
        Set<BluetoothDevice> bondedDevices = this.mLocalAdapter.getBondedDevices();
        boolean z = false;
        if (bondedDevices == null) {
            return false;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            if (this.mDeviceManager.findDevice(bluetoothDevice) == null) {
                dispatchDeviceAdded(this.mDeviceManager.addDevice(this.mLocalAdapter, this.mProfileManager, bluetoothDevice));
                z = true;
            }
        }
        return z;
    }
}
