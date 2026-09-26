package com.rk_itvui.settings.bluetooth;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemProperties;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.android.settingslib.bluetooth.CachedBluetoothDevice;
import com.android.settingslib.bluetooth.CachedBluetoothDeviceManager;
import com.android.settingslib.bluetooth.LocalBluetoothAdapter;
import com.android.settingslib.bluetooth.LocalBluetoothManager;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.Utils;
import com.rk_itvui.settings.bluetooth.dialog.BluetoothPairingDialog;
import com.rk_itvui.settings.bluetooth.dialog.DeviceProfilesSettings;
import com.rk_itvui.utils.SharedUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothSettingActivity extends FullScreenActivity implements View.OnClickListener, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, EventCallback {
    private static final int BLUETOOTH_NAME_MAX_LENGTH_BYTES = 248;
    public static final String SINK_KEY = "persist.bluetooth.a2dp.sink";
    public static boolean sIsDebugBt = true;
    private Intent PairingService;
    private LinearLayout bluetooth_closed_wait;
    private LinearLayout bluetooth_enable_wait;
    private LinearLayout bluetooth_list_content;
    private LinearLayout bluetooth_list_content_closed;
    ImageView img_back;
    boolean isListviewFocused;
    private BluetoothSettingsListAdapter listAdapter;
    ListView listview;
    private View ll_sink;
    private LoadingView loading_view;
    private AlertDialog mAlertDialog;
    private BluetoothAdapter mBluetoothAdapter;
    private CachedBluetoothDeviceManager mCachedBluetoothDeviceManager;
    private boolean mDeviceNameEdited;
    private boolean mDeviceNameUpdated;
    private EditText mDeviceNameView;
    private AlertDialog mDisconnectDialog;
    private EventManager mEventManger;
    private LocalBluetoothAdapter mLocalBluetoothAdapter;
    private LocalBluetoothManager mLocalBluetoothManager;
    private Button mOkButton;
    SharedUtils mSharedUtils;
    private Button mSwitcherSink;
    private Button switcher;
    private TextView tv_refresh;
    private TextView tv_rename;
    private final String TAG = "BLSetting";
    private final List<DeviceInfo> devicesList = new ArrayList();
    private boolean isReset = false;
    private boolean isSink = false;
    private boolean isRefresh = false;
    private boolean disconnectAll = false;
    ArrayList<HistoryDevice> mPairedList = new ArrayList<>();
    BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.ashd.settings.bluetoothsetting.refreshdeviceslist")) {
                BluetoothSettingActivity.this.RefreshDevicesList();
            }
        }
    };
    int restart = 0;
    boolean isListFocused = false;

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceDisappeared(CachedBluetoothDevice cachedBluetoothDevice) {
    }

    public boolean isA2dpsinkMode() {
        return SystemProperties.getBoolean(SINK_KEY, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toggleSwitch() {
        if (!isA2dpsinkMode()) {
            this.mSwitcherSink.setBackgroundResource(R.drawable.switch_on);
            SystemProperties.set(SINK_KEY, "true");
            if (this.mBluetoothAdapter.isEnabled()) {
                this.mBluetoothAdapter.disable();
                new Handler().postDelayed(new Runnable() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.2
                    @Override // java.lang.Runnable
                    public void run() {
                        BluetoothSettingActivity.this.mBluetoothAdapter.enable();
                    }
                }, 500L);
                return;
            }
            return;
        }
        SystemProperties.set(SINK_KEY, "false");
        this.mSwitcherSink.setBackgroundResource(R.drawable.switch_off);
        if (this.mBluetoothAdapter.isEnabled()) {
            this.mBluetoothAdapter.disable();
            new Handler().postDelayed(new Runnable() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.3
                @Override // java.lang.Runnable
                public void run() {
                    BluetoothSettingActivity.this.mBluetoothAdapter.enable();
                }
            }, 500L);
        }
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_bluetooth_settings);
        this.isSink = isA2dpsinkMode();
        this.restart = getIntent().getIntExtra("restart", 0);
        this.PairingService = new Intent(this, (Class<?>) BluetoothPairingDialogService.class);
        this.PairingService.setAction("android.intent.action.RESPOND_VIA_MESSAGE");
        if (isRunService("com.rk_itvui.settings.bluetooth.BluetoothPairingDialogService")) {
            stopService(this.PairingService);
        }
        boolean z = SystemProperties.getBoolean("persist.ashd.uidisp.btspeaker", true);
        this.mSharedUtils = new SharedUtils(this);
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (this.mBluetoothAdapter == null) {
            Log.i("BLSetting", "BluetoothAdapter == null");
            Toast.makeText(this, "蓝牙异常", 0).show();
            finish();
        }
        this.mLocalBluetoothManager = BluetoothUtils.getLocalBtManager(this);
        if (this.mLocalBluetoothManager != null) {
            this.mLocalBluetoothAdapter = this.mLocalBluetoothManager.getBluetoothAdapter();
            this.mCachedBluetoothDeviceManager = this.mLocalBluetoothManager.getCachedDeviceManager();
        }
        this.ll_sink = findViewById(R.id.ll_sink);
        if (z) {
            this.ll_sink.setVisibility(0);
        } else {
            this.ll_sink.setVisibility(4);
        }
        boolean z2 = SystemProperties.getBoolean("persist.ashd.bt.showback", true);
        this.img_back = (ImageView) findViewById(R.id.img_back);
        if (z2) {
            this.img_back.setVisibility(0);
            this.img_back.setOnClickListener(new View.OnClickListener() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BluetoothSettingActivity.this.onBackPressed();
                }
            });
        } else {
            this.img_back.setVisibility(4);
        }
        this.mSwitcherSink = (Button) findViewById(R.id.switcher_sink);
        if (this.isSink) {
            this.mSwitcherSink.setBackgroundResource(R.drawable.switch_on);
        } else {
            this.mSwitcherSink.setBackgroundResource(R.drawable.switch_off);
        }
        this.mSwitcherSink.setOnClickListener(new View.OnClickListener() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BluetoothSettingActivity.this.toggleSwitch();
            }
        });
        this.switcher = (Button) findViewById(R.id.switcher);
        this.loading_view = (LoadingView) findViewById(R.id.loading_view);
        this.tv_refresh = (TextView) findViewById(R.id.tv_refresh);
        this.tv_refresh.setOnClickListener(this);
        this.tv_rename = (TextView) findViewById(R.id.tv_rename);
        this.tv_rename.setOnClickListener(this);
        findViewById(R.id.tv_rename).setOnClickListener(this);
        this.listview = (ListView) findViewById(R.id.listview);
        this.bluetooth_list_content_closed = (LinearLayout) findViewById(R.id.bluetooth_list_content_closed);
        this.bluetooth_list_content = (LinearLayout) findViewById(R.id.bluetooth_list_content);
        this.bluetooth_enable_wait = (LinearLayout) findViewById(R.id.bluetooth_enable_wait);
        this.bluetooth_closed_wait = (LinearLayout) findViewById(R.id.bluetooth_closed_wait);
        getConnectDevices();
        this.listAdapter = new BluetoothSettingsListAdapter(this, this.devicesList);
        this.listview.setAdapter((ListAdapter) this.listAdapter);
        this.listview.setOnItemClickListener(this);
        this.listview.setOnItemLongClickListener(this);
        this.listview.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.6
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z3) {
                BluetoothSettingActivity.this.isListFocused = z3;
            }
        });
        this.switcher.setOnClickListener(this);
        this.mEventManger = EventManager.getEventManager(this, this.mCachedBluetoothDeviceManager, this.mLocalBluetoothAdapter, this.mLocalBluetoothManager);
        if (this.mEventManger != null) {
            this.mEventManger.registerCallback(this);
        }
        handleStateChanged(this.mBluetoothAdapter.getState());
        if (!getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            Log.i("Settings", "本机不支持低功耗蓝牙！");
        }
        Log.i("BLSetting", "restart == " + this.restart);
        if (this.restart == 1) {
            this.disconnectAll = true;
            OFFBluetooth();
            openBluetooth();
        }
        registerReceiver(this.receiver, new IntentFilter("com.ashd.settings.bluetoothsetting.refreshdeviceslist"));
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.isSink = "true".equalsIgnoreCase(SystemProperties.get("persist.bluetooth.enablesink", "false"));
        this.restart = getIntent().getIntExtra("restart", 0);
        if (this.restart == 1) {
            this.disconnectAll = true;
            OFFBluetooth();
            openBluetooth();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.mEventManger != null && !this.mEventManger.isRegister()) {
            this.mEventManger.registerCallback(this);
        }
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.loading_view.setVisibility(0);
        } else {
            this.loading_view.setVisibility(8);
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.mLocalBluetoothAdapter == null) {
            return;
        }
        try {
            if (this.mEventManger != null) {
                this.mEventManger.unregisterCallback();
            }
            if (this.mBluetoothAdapter.isDiscovering()) {
                this.mBluetoothAdapter.cancelDiscovery();
            }
            if (this.mBluetoothAdapter.isEnabled()) {
                startService(this.PairingService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.devicesList.clear();
        try {
            unregisterReceiver(this.receiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.switcher) {
            if (this.mBluetoothAdapter.isEnabled()) {
                OFFBluetooth();
                return;
            } else {
                openBluetooth();
                return;
            }
        }
        if (id == R.id.tv_refresh) {
            RefreshDevicesList();
        } else if (id == R.id.tv_rename) {
            LocalDeviceRename();
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CachedBluetoothDevice cachedBluetoothDeviceAddDevice;
        if (this.mCachedBluetoothDeviceManager == null) {
            return;
        }
        DeviceInfo deviceInfo = this.devicesList.get(i);
        BluetoothDevice device = deviceInfo.getDevice();
        if (device == null) {
            BluetoothUtils.showError(this, deviceInfo.getDeviceName(), R.string.bluetooth_pairing_device_down_error_message);
            return;
        }
        if (this.mCachedBluetoothDeviceManager.findDevice(device) != null) {
            cachedBluetoothDeviceAddDevice = this.mCachedBluetoothDeviceManager.findDevice(device);
        } else {
            cachedBluetoothDeviceAddDevice = this.mCachedBluetoothDeviceManager.addDevice(this.mLocalBluetoothAdapter, this.mLocalBluetoothManager.getProfileManager(), device);
        }
        if (cachedBluetoothDeviceAddDevice == null) {
            return;
        }
        int bondState = cachedBluetoothDeviceAddDevice.getBondState();
        Log.e("BLSetting", "onItemClick bondState=" + bondState);
        if (cachedBluetoothDeviceAddDevice.isConnected()) {
            askDisconnect(cachedBluetoothDeviceAddDevice);
        } else if (bondState == 12) {
            cachedBluetoothDeviceAddDevice.connect(true);
        } else if (bondState == 10) {
            pair(cachedBluetoothDeviceAddDevice);
        }
    }

    @Override // android.widget.AdapterView.OnItemLongClickListener
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        BluetoothDevice device = this.devicesList.get(i).getDevice();
        if (device == null || device.getBondState() != 12) {
            return false;
        }
        Bundle bundle = new Bundle();
        bundle.putString(DeviceProfilesSettings.ARG_DEVICE_ADDRESS, device.getAddress());
        DeviceProfilesSettings deviceProfilesSettings = new DeviceProfilesSettings();
        deviceProfilesSettings.setArguments(bundle);
        deviceProfilesSettings.show(getFragmentManager(), DeviceProfilesSettings.class.getSimpleName());
        Dialog dialog = deviceProfilesSettings.getDialog();
        if (!(dialog instanceof AlertDialog)) {
            return true;
        }
        Utils.fixButtonStyle((AlertDialog) dialog);
        return true;
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onBluetoothStateChanged(int i) {
        Log.d("BLSetting", "onBluetoothStateChanged");
        handleStateChanged(i);
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onScanningStateChanged(boolean z) {
        if (z) {
            this.loading_view.setVisibility(0);
        } else {
            this.loading_view.setVisibility(8);
        }
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceAdded(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        if (this.mLocalBluetoothAdapter.getBluetoothState() != 12) {
            return;
        }
        String string = cachedBluetoothDevice.getBondState() == 10 ? "" : "";
        if (cachedBluetoothDevice.getBondState() == 11) {
            string = getResources().getString(R.string.bluetooth_pairing);
        }
        if (cachedBluetoothDevice.getBondState() == 12) {
            string = getResources().getString(R.string.bluetooth_preference_paired_devices);
        }
        if (cachedBluetoothDevice.isConnected()) {
            string = getResources().getString(R.string.connected);
        }
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (device == null) {
            Log.e("BLSetting", "device == null");
            return;
        }
        DeviceInfo deviceInfo = new DeviceInfo(device, i);
        deviceInfo.setDeviceState(string);
        Log.d("DeviceInfo", "DeviceMac：" + deviceInfo.getDeviceMac() + "\nDeviceName：" + deviceInfo.getDeviceName());
        addData(deviceInfo);
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceBond(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        Intent intent2 = new Intent(this, (Class<?>) BluetoothPairingDialog.class);
        intent2.putExtra("android.bluetooth.device.extra.DEVICE", bluetoothDevice);
        intent2.putExtra("android.bluetooth.device.extra.PAIRING_VARIANT", intExtra);
        if (intExtra == 2 || intExtra == 3 || intExtra == 4 || intExtra == 5) {
            if (Utils.FilterBtRequest(bluetoothDevice.getName())) {
                bluetoothDevice.setPairingConfirmation(true);
            }
            Log.e("BLSetting", "paring");
        } else {
            intent2.setAction("android.bluetooth.device.action.PAIRING_REQUEST");
            intent2.setFlags(AudioFormat.EVRC);
            startActivity(intent2);
        }
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceBondStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        DeviceInfo deviceInfoSearchDeviceInfo;
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (device == null || (deviceInfoSearchDeviceInfo = SearchDeviceInfo(device.getAddress())) == null) {
            return;
        }
        deviceInfoSearchDeviceInfo.setDeviceState(BluetoothUtils.BondToString(this, i));
        notifyDataSetChanged(this.listAdapter);
    }

    private boolean isConnectedDevice(DeviceInfo deviceInfo) {
        if (this.mPairedList == null) {
            return false;
        }
        for (HistoryDevice historyDevice : this.mPairedList) {
            if (historyDevice.getDeviceMac() != null && historyDevice.getDeviceMac().equals(deviceInfo.getDeviceMac())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean removeDevice(String str) {
        if (this.mPairedList != null) {
            Iterator<HistoryDevice> it = this.mPairedList.iterator();
            while (it.hasNext()) {
                HistoryDevice next = it.next();
                if (next.getDeviceMac() != null && next.getDeviceMac().equals(str)) {
                    it.remove();
                    break;
                }
            }
        }
        String jSONString = JSON.toJSONString(this.mPairedList);
        Log.e("BLSetting", "logData=" + jSONString);
        this.mSharedUtils.putString("connected_list", jSONString);
        return false;
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onConnectionStateChanged(CachedBluetoothDevice cachedBluetoothDevice, int i) {
        DeviceInfo deviceInfoSearchDeviceInfo;
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (device == null || (deviceInfoSearchDeviceInfo = SearchDeviceInfo(device.getAddress())) == null) {
            return;
        }
        deviceInfoSearchDeviceInfo.setDeviceState(BluetoothUtils.ConnectToString(this, i, cachedBluetoothDevice.getBondState() == 12));
        Log.d("BLSetting", "state=" + i + "onConnectionStateChanged=" + cachedBluetoothDevice.getBondState() + "," + deviceInfoSearchDeviceInfo.getDeviceName());
        if (i == 2 && BluetoothUtils.isDeviceCanDisconnect(deviceInfoSearchDeviceInfo.getDeviceName())) {
            SystemProperties.set("persist.sys.yyyklj", "true");
            if (!isConnectedDevice(deviceInfoSearchDeviceInfo)) {
                this.mPairedList.add(new HistoryDevice(deviceInfoSearchDeviceInfo));
            }
            String jSONString = JSON.toJSONString(this.mPairedList);
            Log.e("BLSetting", "logData=" + jSONString);
            this.mSharedUtils.putString("connected_list", jSONString);
        }
        notifyDataSetChanged(this.listAdapter);
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceClassChanged(CachedBluetoothDevice cachedBluetoothDevice, BluetoothClass bluetoothClass) {
        DeviceInfo deviceInfoSearchDeviceInfo;
        String address = cachedBluetoothDevice.getDevice().getAddress();
        if (address == null || (deviceInfoSearchDeviceInfo = SearchDeviceInfo(address)) == null) {
            return;
        }
        deviceInfoSearchDeviceInfo.setBtClass(bluetoothClass);
        notifyDataSetChanged(this.listAdapter);
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onLocalDeviceNameChanged() {
        Log.i("BLSetting", "onLocalDeviceNameChanged");
    }

    @Override // com.rk_itvui.settings.bluetooth.EventCallback
    public void onDeviceNameChanged(CachedBluetoothDevice cachedBluetoothDevice) {
        DeviceInfo deviceInfoSearchDeviceInfo;
        Log.d("BLSetting", "onDeviceNameChanged");
        BluetoothDevice device = cachedBluetoothDevice.getDevice();
        if (device == null || (deviceInfoSearchDeviceInfo = SearchDeviceInfo(device.getAddress())) == null) {
            return;
        }
        deviceInfoSearchDeviceInfo.setDeviceName(device.getName());
        notifyDataSetChanged(this.listAdapter);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0) {
            return false;
        }
        if (i == 140) {
            RefreshDevicesList();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void handleStateChanged(int i) {
        switch (i) {
            case 10:
                Log.d("BLSetting", "关闭");
                this.switcher.setBackgroundResource(R.drawable.switch_off);
                Intent intent = new Intent();
                intent.setAction("com.aispeech.tvui.action.BLE_RC");
                intent.setComponent(new ComponentName("com.aispeech.tvui", "com.aispeech.tvui.recorder.receiver.ExternalCommandReceiver"));
                intent.putExtra("switch", "disconnect");
                sendBroadcast(intent);
                this.loading_view.setVisibility(4);
                this.switcher.setEnabled(true);
                this.tv_refresh.setEnabled(true);
                this.tv_refresh.setVisibility(4);
                this.tv_rename.setVisibility(4);
                RefreshCenterView(this.bluetooth_list_content_closed);
                if (this.isReset) {
                    openBluetooth();
                    this.isReset = false;
                }
                break;
            case 11:
                Log.d("BLSetting", "开启中");
                this.switcher.setBackgroundResource(R.drawable.switch_on);
                this.switcher.setEnabled(false);
                this.loading_view.setVisibility(4);
                this.tv_refresh.setEnabled(false);
                this.tv_refresh.setVisibility(4);
                this.tv_rename.setVisibility(4);
                RefreshCenterView(this.bluetooth_enable_wait);
                break;
            case 12:
                Log.d("BLSetting", "打开");
                this.switcher.setBackgroundResource(R.drawable.switch_on);
                this.mLocalBluetoothAdapter.setDiscoverableTimeout(0);
                this.mLocalBluetoothAdapter.setScanMode(23, 0);
                this.switcher.setEnabled(true);
                RefreshCenterView(this.bluetooth_list_content);
                this.tv_refresh.setEnabled(true);
                this.tv_refresh.setVisibility(0);
                this.tv_rename.setVisibility(0);
                startDiscovery();
                break;
            case 13:
                Log.d("BLSetting", "关闭中");
                this.switcher.setBackgroundResource(R.drawable.switch_off);
                this.loading_view.setVisibility(4);
                this.switcher.setEnabled(false);
                this.tv_refresh.setEnabled(false);
                this.tv_refresh.setVisibility(4);
                this.tv_rename.setVisibility(4);
                RefreshCenterView(this.bluetooth_closed_wait);
                break;
            default:
                Log.d("BLSetting", "default\n" + i);
                this.switcher.setBackgroundResource(R.drawable.switch_off);
                RefreshCenterView(this.bluetooth_list_content_closed);
                Toast.makeText(this, R.string.battery_info_health_unspecified_failure, 0).show();
                break;
        }
    }

    private void RefreshCenterView(LinearLayout linearLayout) {
        this.bluetooth_list_content.setVisibility(8);
        this.bluetooth_enable_wait.setVisibility(8);
        this.bluetooth_closed_wait.setVisibility(8);
        this.bluetooth_list_content_closed.setVisibility(8);
        linearLayout.setVisibility(0);
    }

    public void startDiscovery() {
        if (this.mBluetoothAdapter.isDiscovering()) {
            this.mBluetoothAdapter.cancelDiscovery();
            this.loading_view.setVisibility(8);
        }
        this.mBluetoothAdapter.startDiscovery();
        this.loading_view.setVisibility(0);
    }

    public void addData(DeviceInfo deviceInfo) {
        for (int i = 0; i < this.devicesList.size(); i++) {
            if (this.devicesList.get(i).getDeviceMac().equals(deviceInfo.getDeviceMac())) {
                this.devicesList.set(i, deviceInfo);
                notifyDataSetChanged(this.listAdapter);
                Log.d("BLSetting", "addData fail" + deviceInfo.getDeviceMac());
                return;
            }
        }
        this.devicesList.add(deviceInfo);
        notifyDataSetChanged(this.listAdapter);
        Log.d("BLSetting", "addData success " + deviceInfo.getDeviceMac());
    }

    public void openBluetooth() {
        this.mLocalBluetoothAdapter.enable();
        this.switcher.setBackgroundResource(R.drawable.switch_on);
        this.switcher.setEnabled(false);
        RefreshCenterView(this.bluetooth_enable_wait);
    }

    public void OFFBluetooth() {
        this.mLocalBluetoothAdapter.disable();
        this.devicesList.clear();
    }

    public void RefreshDevicesList() {
        if (this.mBluetoothAdapter.isEnabled()) {
            if (!this.mLocalBluetoothAdapter.isEnabled()) {
                openBluetooth();
            }
            this.devicesList.clear();
            getConnectDevices();
            notifyDataSetChanged(this.listAdapter);
            startDiscovery();
        }
    }

    private void notifyDataSetChanged(final BluetoothSettingsListAdapter bluetoothSettingsListAdapter) {
        Collections.sort(this.devicesList);
        if (isMainLoop()) {
            bluetoothSettingsListAdapter.notifyDataSetChanged();
        } else {
            runOnUiThread(new Runnable() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.7
                @Override // java.lang.Runnable
                public void run() {
                    bluetoothSettingsListAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    private void LocalDeviceRename() {
        this.mAlertDialog = new AlertDialog.Builder(this).setTitle(R.string.bluetooth_rename_device).setView(createDialogView(this.mLocalBluetoothAdapter.getName())).setPositiveButton(R.string.bluetooth_rename_button, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                BluetoothSettingActivity.this.setDeviceName(BluetoothSettingActivity.this.mDeviceNameView.getText().toString());
            }
        }).setNegativeButton(android.R.string.cancel, (DialogInterface.OnClickListener) null).create();
        if (this.mAlertDialog.getWindow() != null) {
            this.mAlertDialog.getWindow().setSoftInputMode(5);
            this.mAlertDialog.show();
            if (this.mOkButton == null) {
                this.mOkButton = this.mAlertDialog.getButton(-1);
                this.mOkButton.setEnabled(this.mDeviceNameEdited);
            }
            Utils.fixButtonStyle(this.mAlertDialog);
            return;
        }
        Toast.makeText(this, R.string.battery_info_health_unspecified_failure, 0).show();
    }

    private void askDisconnect(final CachedBluetoothDevice cachedBluetoothDevice) {
        String name = cachedBluetoothDevice.getName();
        if (TextUtils.isEmpty(name)) {
            name = getString(R.string.bluetooth_device);
        }
        final boolean zIsDeviceCanDisconnect = BluetoothUtils.isDeviceCanDisconnect(name);
        String string = getString(R.string.bluetooth_disconnect_all_profiles, new Object[]{name});
        this.mDisconnectDialog = BluetoothUtils.showDisconnectDialog(this, this.mDisconnectDialog, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (zIsDeviceCanDisconnect) {
                    cachedBluetoothDevice.unpair();
                    SystemProperties.set("persist.sys.yyyklj", "false");
                    Log.d("BLSetting", "persist.sys.yyyklj=false");
                    BluetoothSettingActivity.this.removeDevice(cachedBluetoothDevice.getDevice().getAddress());
                    BluetoothSettingActivity.this.isReset = true;
                    Intent intent = new Intent();
                    intent.setAction("com.aispeech.tvui.action.BLE_RC");
                    intent.setComponent(new ComponentName("com.aispeech.tvui", "com.aispeech.tvui.recorder.receiver.ExternalCommandReceiver"));
                    intent.putExtra("switch", "disconnect");
                    BluetoothSettingActivity.this.sendBroadcast(intent);
                } else {
                    cachedBluetoothDevice.disconnect();
                }
                BluetoothSettingActivity.this.RefreshDevicesList();
            }
        }, getString(R.string.bluetooth_disconnect_title), Html.fromHtml(string));
        Utils.fixButtonStyle(this.mDisconnectDialog);
    }

    private void pair(CachedBluetoothDevice cachedBluetoothDevice) {
        if (cachedBluetoothDevice.startPairing()) {
            return;
        }
        BluetoothUtils.showError(this, cachedBluetoothDevice.getName(), R.string.bluetooth_pairing_error_message);
        Log.d("BLSetting", "showError");
    }

    private DeviceInfo SearchDeviceInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (int i = 0; i < this.devicesList.size(); i++) {
            if (this.devicesList.get(i).getDeviceMac().equals(str)) {
                return this.devicesList.get(i);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceName(String str) {
        Log.d("BLSetting", "Setting device name to " + str);
        this.mLocalBluetoothAdapter.setName(str);
    }

    private View createDialogView(String str) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService("layout_inflater");
        View viewInflate = null;
        if (layoutInflater != null) {
            viewInflate = layoutInflater.inflate(R.layout.dialog_edittext, (ViewGroup) null);
            this.mDeviceNameView = (EditText) viewInflate.findViewById(R.id.edittext);
        } else {
            Log.d("BLSetting", "view == null");
            Toast.makeText(this, "Error", 0).show();
        }
        this.mDeviceNameView.setFilters(new InputFilter[]{new Utf8ByteLengthFilter(BLUETOOTH_NAME_MAX_LENGTH_BYTES)});
        this.mDeviceNameView.setText(str);
        this.mDeviceNameView.addTextChangedListener(new TextWatcher() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.10
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (BluetoothSettingActivity.this.mDeviceNameUpdated) {
                    BluetoothSettingActivity.this.mDeviceNameUpdated = false;
                    BluetoothSettingActivity.this.mOkButton.setEnabled(false);
                } else {
                    BluetoothSettingActivity.this.mDeviceNameEdited = true;
                    if (BluetoothSettingActivity.this.mOkButton != null) {
                        BluetoothSettingActivity.this.mOkButton.setEnabled(editable.toString().trim().length() != 0);
                    }
                }
            }
        });
        this.mDeviceNameView.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.rk_itvui.settings.bluetooth.BluetoothSettingActivity.11
            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                BluetoothSettingActivity.this.setDeviceName(textView.getText().toString());
                BluetoothSettingActivity.this.mAlertDialog.dismiss();
                return true;
            }
        });
        return viewInflate;
    }

    public boolean isRunService(String str) {
        ActivityManager activityManager = (ActivityManager) getSystemService("activity");
        if (activityManager == null) {
            return false;
        }
        Iterator<ActivityManager.RunningServiceInfo> it = activityManager.getRunningServices(Integer.MAX_VALUE).iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void getConnectDevices() {
        boolean z;
        boolean z2;
        if (this.mBluetoothAdapter == null) {
            return;
        }
        for (BluetoothDevice bluetoothDevice : this.mBluetoothAdapter.getBondedDevices()) {
            if (bluetoothDevice.isConnected()) {
                DeviceInfo deviceInfo = new DeviceInfo(bluetoothDevice, 0);
                deviceInfo.setDeviceState(getResources().getString(R.string.connected));
                Log.e("BLSetting", "connected=" + bluetoothDevice.getName());
                this.devicesList.add(deviceInfo);
            }
        }
        try {
            List<HistoryDevice> array = JSON.parseArray(this.mSharedUtils.getString("connected_list"), HistoryDevice.class);
            if (array != null) {
                for (HistoryDevice historyDevice : array) {
                    Log.e("BLSetting", "name=" + historyDevice.getDeviceMac() + ",name=" + historyDevice.getDeviceName());
                    Iterator<DeviceInfo> it = this.devicesList.iterator();
                    while (true) {
                        z = true;
                        if (!it.hasNext()) {
                            z2 = false;
                            break;
                        }
                        if (!historyDevice.getDeviceMac().equals(it.next().getDeviceMac())) {
                            z2 = true;
                            break;
                        }
                    }
                    if (!z2) {
                        DeviceInfo deviceInfo2 = new DeviceInfo(null, 0);
                        deviceInfo2.setDeviceName(historyDevice.getDeviceName());
                        deviceInfo2.setDeviceMac(historyDevice.getDeviceMac());
                        deviceInfo2.setDeviceState(getResources().getString(R.string.bluetooth_preference_paired_devices));
                        int i = 0;
                        while (true) {
                            if (i >= this.devicesList.size()) {
                                z = false;
                                break;
                            } else if (this.devicesList.get(i).getDeviceMac().equals(deviceInfo2.getDeviceMac())) {
                                break;
                            } else {
                                i++;
                            }
                        }
                        if (!z) {
                            this.devicesList.add(deviceInfo2);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isMainLoop() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int selectedItemPosition = this.listview.getSelectedItemPosition();
        Log.e("BLSetting", "position=" + selectedItemPosition + ",isListFocused=" + this.isListFocused);
        if (this.isListFocused) {
            if (keyEvent.getKeyCode() == 19 || keyEvent.getKeyCode() == 21) {
                if (selectedItemPosition == 0) {
                    this.tv_rename.requestFocus();
                    return true;
                }
                this.listview.setSelection(selectedItemPosition - 1);
                return true;
            }
            if (keyEvent.getKeyCode() == 20 || keyEvent.getKeyCode() == 22) {
                this.listview.setSelection(selectedItemPosition + 1);
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
