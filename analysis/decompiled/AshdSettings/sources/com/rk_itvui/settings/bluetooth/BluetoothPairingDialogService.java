package com.rk_itvui.settings.bluetooth;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.IBinder;
import android.util.Log;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.rk_itvui.settings.Utils;
import com.rk_itvui.settings.bluetooth.dialog.BluetoothPairingDialog;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothPairingDialogService extends Service implements EventCallback {
    private BluetoothAdapter mBluetoothAdapter;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.bluetooth.BluetoothPairingDialogService.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Log.d("Settings", "BluetoothPairingDialogService");
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
            Intent intent2 = new Intent();
            intent2.setClass(BluetoothPairingDialogService.this.getApplicationContext(), BluetoothPairingDialog.class);
            intent2.putExtra("android.bluetooth.device.extra.DEVICE", bluetoothDevice);
            intent2.putExtra("android.bluetooth.device.extra.PAIRING_VARIANT", intExtra);
            int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE);
            if (intExtra == 2 || intExtra == 3 || intExtra == 4 || intExtra == 5) {
                if (intExtra == 5 && intExtra2 != Integer.MIN_VALUE) {
                    bluetoothDevice.setPin(BluetoothDevice.convertPinToBytes(intExtra2 + ""));
                }
                if (Utils.FilterBtRequest(bluetoothDevice.getName())) {
                    return;
                }
                bluetoothDevice.setPairingConfirmation(true);
                Log.d("Settings", "setPairingConfirmation");
                return;
            }
            intent2.setAction("android.bluetooth.device.action.PAIRING_REQUEST");
            intent2.setFlags(AudioFormat.EVRC);
            BluetoothPairingDialogService.this.startActivity(intent2);
        }
    };
    private CachedBluetoothDeviceManager mCachedBluetoothDeviceManager;
    private EventManager mEventManger;
    private LocalBluetoothAdapter mLocalBluetoothAdapter;
    private LocalBluetoothManager mLocalBluetoothManager;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onBluetoothStateChanged(int i) {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice, int i) {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceClassChanged(CachedBluetoothDevice cachedBluetoothDevice, BluetoothClass bluetoothClass) {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceDisappeared(CachedBluetoothDevice cachedBluetoothDevice) {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceNameChanged(CachedBluetoothDevice cachedBluetoothDevice) {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onLocalDeviceNameChanged() {
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onScanningStateChanged(boolean z) {
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        this.mLocalBluetoothManager = BluetoothUtils.getLocalBtManager(this);
        if (this.mLocalBluetoothManager != null) {
            this.mLocalBluetoothAdapter = this.mLocalBluetoothManager.getBluetoothAdapter();
            this.mCachedBluetoothDeviceManager = this.mLocalBluetoothManager.getCachedDeviceManager();
        }
        this.mEventManger = EventManager.getEventManager(this, this.mCachedBluetoothDeviceManager, this.mLocalBluetoothAdapter, this.mLocalBluetoothManager);
        if (this.mEventManger != null) {
            this.mEventManger.registerCallback(this);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.d("Settings", "创建服务");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        registerReceiver(this.mBroadcastReceiver, intentFilter);
        return super.onStartCommand(intent, i, i2);
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterReceiver(this.mBroadcastReceiver);
        Log.d("Settings", "销毁服务");
        if (this.mEventManger != null) {
            this.mEventManger.unregisterCallback();
        }
        super.onDestroy();
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceBond(Intent intent) {
        Log.d("Settings", "onDeviceBond");
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        Intent intent2 = new Intent(getBaseContext(), (Class<?>) BluetoothPairingDialog.class);
        intent2.putExtra("android.bluetooth.device.extra.DEVICE", bluetoothDevice);
        intent2.putExtra("android.bluetooth.device.extra.PAIRING_VARIANT", intExtra);
        if (intExtra == 2 || intExtra == 3 || intExtra == 4 || intExtra == 5) {
            if (Utils.FilterBtRequest(bluetoothDevice.getName())) {
                bluetoothDevice.setPairingConfirmation(true);
                Log.d("Settings", "setPairingConfirmation");
                return;
            }
            return;
        }
        intent2.setAction("android.bluetooth.device.action.PAIRING_REQUEST");
        intent2.setFlags(AudioFormat.EVRC);
        startActivity(intent2);
    }
}
