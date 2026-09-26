package com.rk_itvui.settings.network.wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.security.KeyStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes.dex */
public class Wifi_setting extends FullScreenActivity implements DialogInterface.OnClickListener {
    private static final String EXTRA_AUTO_FINISH_ON_CONNECT = "wifi_auto_finish_on_connect";
    private static final int MENU_ID_ADVANCED = 2;
    private static final int MENU_ID_CONNECT = 3;
    private static final int MENU_ID_FORGET = 4;
    private static final int MENU_ID_MODIFY = 5;
    private static final int MENU_ID_SCAN = 1;
    private static final String TAG = "WifiSettings";
    ImageView img_back;
    private boolean mAutoFinishOnConnection;
    private WifiUICallBack mCallBack;
    private WifiManager.ActionListener mConnectListener;
    private Context mContext;
    private Wifi_Dialog mDialog;
    private WifiManager.ActionListener mForgetListener;
    private WifiInfo mLastInfo;
    private int mLastPriority;
    private NetworkInfo.DetailedState mLastState;
    private boolean mP2pSupported;
    private final BroadcastReceiver mReceiver;
    private WifiManager.ActionListener mSaveListener;
    private final Scanner mScanner;
    private AccessPoint mSelected;
    Runnable mTimeRunnable;
    private LinearLayout mWifiContentLayout;
    private LinearLayout mWifiDisconnectedLayout;
    private WifiManager mWifiManager;
    private LinearLayout mWifiOpenningLayout;
    private Wifi_Enabler mWifi_Enabler;
    private Button wifiSwitcher;
    private final Object lock = new Object();
    private boolean mResetNetworks = false;
    private int mKeyStoreNetworkId = -1;
    private ListView mListView = null;
    ArrayList<AccessPoint> accessPoints = new ArrayList<>();
    WifiScanListViewAdapter mAdapter = null;
    private LayoutInflater flater = null;
    private LinearLayout mView = null;
    private AtomicBoolean mConnected = new AtomicBoolean(false);
    private final AdapterView.OnItemClickListener mListItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.8
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i == 0) {
                Wifi_setting.this.LOGD("AdapterView.OnItemClickListener mListItemClickListener, position = 0, showDialog(null, true)");
                Wifi_setting.this.showDialog((AccessPoint) null, true);
                return;
            }
            Wifi_setting.this.mSelected = Wifi_setting.this.accessPoints.get(i);
            Log.i("Settings", "submit" + Wifi_setting.this.mSelected.toString());
            Wifi_setting.this.showDialog(Wifi_setting.this.mSelected, false);
        }
    };
    private CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.9
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        }
    };
    private final Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.10
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Wifi_setting.this.LOGD("mHandler,handleMessage() msg.what = " + message.what);
            switch (message.what) {
                case 0:
                    Wifi_setting.this.wifiSwitcher.setBackgroundResource(R.drawable.switch_off);
                    Wifi_setting.this.wifiSwitcher.setEnabled(false);
                    Wifi_setting.this.mWifiContentLayout.setVisibility(0);
                    Wifi_setting.this.mWifiDisconnectedLayout.setVisibility(8);
                    Wifi_setting.this.mWifiOpenningLayout.setVisibility(8);
                    break;
                case 1:
                    Wifi_setting.this.wifiSwitcher.setBackgroundResource(R.drawable.switch_off);
                    Wifi_setting.this.wifiSwitcher.setEnabled(true);
                    Wifi_setting.this.mWifiContentLayout.setVisibility(8);
                    Wifi_setting.this.mWifiDisconnectedLayout.setVisibility(0);
                    Wifi_setting.this.mWifiOpenningLayout.setVisibility(8);
                    break;
                case 2:
                    Wifi_setting.this.wifiSwitcher.setBackgroundResource(R.drawable.switch_on);
                    Wifi_setting.this.wifiSwitcher.setEnabled(false);
                    Wifi_setting.this.mWifiContentLayout.setVisibility(8);
                    Wifi_setting.this.mWifiDisconnectedLayout.setVisibility(8);
                    Wifi_setting.this.mWifiOpenningLayout.setVisibility(0);
                    break;
                case 3:
                    Wifi_setting.this.wifiSwitcher.setBackgroundResource(R.drawable.switch_on);
                    Wifi_setting.this.wifiSwitcher.setEnabled(true);
                    Wifi_setting.this.mWifiContentLayout.setVisibility(0);
                    Wifi_setting.this.mWifiDisconnectedLayout.setVisibility(8);
                    Wifi_setting.this.mWifiOpenningLayout.setVisibility(8);
                    break;
                default:
                    Wifi_setting.this.mWifiContentLayout.setVisibility(8);
                    Wifi_setting.this.mWifiDisconnectedLayout.setVisibility(0);
                    Wifi_setting.this.mWifiOpenningLayout.setVisibility(8);
                    Wifi_setting.this.wifiSwitcher.setBackgroundResource(R.drawable.switch_off);
                    Wifi_setting.this.wifiSwitcher.setEnabled(true);
                    break;
            }
        }
    };
    private final IntentFilter mFilter = new IntentFilter();

    /* JADX INFO: Access modifiers changed from: private */
    public void LOGD(String str) {
    }

    public Wifi_setting() {
        this.mFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        this.mFilter.addAction("android.net.wifi.SCAN_RESULTS");
        this.mFilter.addAction("android.net.wifi.NETWORK_IDS_CHANGED");
        this.mFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.mFilter.addAction("android.net.wifi.CONFIGURED_NETWORKS_CHANGE");
        this.mFilter.addAction("android.net.wifi.LINK_CONFIGURATION_CHANGED");
        this.mFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.mFilter.addAction("android.net.wifi.RSSI_CHANGED");
        this.mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Wifi_setting.this.handleEvent(intent);
                Wifi_setting.this.LOGD("wifi setting handleevent");
            }
        };
        this.mScanner = new Scanner();
    }

    private void createListView() {
        this.flater = (LayoutInflater) getSystemService("layout_inflater");
        this.mListView = (ListView) findViewById(R.id.listview);
        this.mAdapter = new WifiScanListViewAdapter(this, this.accessPoints);
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        this.mListView.setOnItemClickListener(this.mListItemClickListener);
        this.mListView.requestFocus();
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wifi_layout);
        this.mContext = this;
        this.mP2pSupported = getPackageManager().hasSystemFeature("android.hardware.wifi.direct");
        this.mWifiManager = (WifiManager) getSystemService("wifi");
        this.mConnectListener = new WifiManager.ActionListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.2
            public void onSuccess() {
                Wifi_setting.this.LOGD("onSuccess****************");
            }

            public void onFailure(int i) {
                Wifi_setting.this.LOGD("onFailure****************" + i);
                Toast.makeText(Wifi_setting.this, R.string.wifi_failed_connect_message, 0).show();
            }
        };
        this.mSaveListener = new WifiManager.ActionListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.3
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Toast.makeText(Wifi_setting.this, R.string.wifi_failed_save_message, 0).show();
            }
        };
        this.mForgetListener = new WifiManager.ActionListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.4
            public void onSuccess() {
            }

            public void onFailure(int i) {
                Toast.makeText(Wifi_setting.this, R.string.wifi_failed_forget_message, 0).show();
            }
        };
        boolean z = SystemProperties.getBoolean("persist.ashd.wifi.showback", true);
        this.img_back = (ImageView) findViewById(R.id.img_back);
        if (z) {
            this.img_back.setVisibility(0);
            this.img_back.setOnClickListener(new View.OnClickListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    Wifi_setting.this.onBackPressed();
                }
            });
        } else {
            this.img_back.setVisibility(4);
        }
        this.mCallBack = new WifiUICallBack();
        createListView();
        getWifiCurrentState();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAccessPoints() {
        synchronized (this.lock) {
            LOGD("updateAccessPoints****************");
            if (this.accessPoints != null) {
                this.accessPoints.clear();
            }
            List<WifiConfiguration> configuredNetworks = this.mWifiManager.getConfiguredNetworks();
            if (configuredNetworks != null) {
                this.mLastPriority = 0;
                for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                    if (wifiConfiguration.priority > this.mLastPriority) {
                        this.mLastPriority = wifiConfiguration.priority;
                    }
                    if (wifiConfiguration.status == 0) {
                        wifiConfiguration.status = 2;
                    } else if (this.mResetNetworks && wifiConfiguration.status == 1) {
                        wifiConfiguration.status = 0;
                    }
                    AccessPoint accessPoint = new AccessPoint(this, wifiConfiguration);
                    accessPoint.update(this.mLastInfo, this.mLastState);
                    accessPoint.setCallBack(this.mCallBack);
                    this.accessPoints.add(accessPoint);
                }
            }
            List<ScanResult> scanResults = this.mWifiManager.getScanResults();
            if (scanResults != null) {
                for (ScanResult scanResult : scanResults) {
                    if (scanResult.SSID != null && scanResult.SSID.length() != 0 && !scanResult.capabilities.contains("[IBSS]")) {
                        Iterator<AccessPoint> it = this.accessPoints.iterator();
                        boolean z = false;
                        while (it.hasNext()) {
                            if (it.next().update(scanResult)) {
                                z = true;
                            }
                        }
                        if (!z) {
                            this.accessPoints.add(new AccessPoint(this, scanResult));
                        }
                    }
                }
            }
            Collections.sort(this.accessPoints);
            AccessPoint accessPoint2 = new AccessPoint(this, getResources().getString(R.string.wifi_add_network));
            if (this.accessPoints.size() > 0) {
                this.accessPoints.add(0, accessPoint2);
            } else {
                this.accessPoints.add(accessPoint2);
            }
        }
        synchronized (this.lock) {
            ((Activity) this.mContext).runOnUiThread(new Runnable() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.6
                @Override // java.lang.Runnable
                public void run() {
                    Wifi_setting.this.mAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleEvent(Intent intent) {
        int i;
        LOGD("handleEvent(), action = " + intent.getAction());
        String action = intent.getAction();
        if ("android.net.wifi.WIFI_STATE_CHANGED".equals(action)) {
            updateWifiState(intent.getIntExtra("wifi_state", 4));
            return;
        }
        if ("android.net.wifi.SCAN_RESULTS".equals(action) || "android.net.wifi.CONFIGURED_NETWORKS_CHANGE".equals(action) || "android.net.wifi.LINK_CONFIGURATION_CHANGED".equals(action)) {
            updateAccessPoints();
            return;
        }
        if ("android.net.wifi.supplicant.STATE_CHANGE".equals(action)) {
            SupplicantState supplicantState = (SupplicantState) intent.getParcelableExtra("newState");
            int intExtra = intent.getIntExtra("supplicantError", 0);
            if (this.mSelected != null && this.mWifiManager != null && intExtra == 1) {
                if (this.mWifiManager.isWifiEnabled()) {
                    this.mScanner.resume();
                }
                updateAccessPoints();
                Log.i(TAG, "selected SSID:" + this.mSelected.ssid);
                Log.i(TAG, "selected networkId:" + this.mSelected.networkId);
                Log.i(TAG, "selected bssid:" + this.mSelected.bssid);
                Log.i(TAG, "selected Config:" + this.mSelected.getConfig());
                if (this.mSelected.networkId == -1) {
                    i = -1;
                    for (WifiConfiguration wifiConfiguration : this.mWifiManager.getConfiguredNetworks()) {
                        Log.i(TAG, "wifiConfiguration SSID:" + wifiConfiguration.SSID);
                        Log.i(TAG, "wifiConfiguration BSSID:" + wifiConfiguration.BSSID);
                        Log.i(TAG, "wifiConfiguration networkId:" + wifiConfiguration.networkId);
                        if (wifiConfiguration.SSID.equals("\"" + this.mSelected.ssid + "\"")) {
                            i = wifiConfiguration.networkId;
                        }
                    }
                } else {
                    i = this.mSelected.networkId;
                }
                if (i != -1) {
                    this.mWifiManager.forget(i, new WifiManager.ActionListener() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.7
                        public void onFailure(int i2) {
                            Log.i(Wifi_setting.TAG, "onFailure");
                        }

                        public void onSuccess() {
                            Log.i(Wifi_setting.TAG, "onSuccess");
                            Toast.makeText(Wifi_setting.this.mContext, R.string.wifi_disabled_password_failure, 1).show();
                            if (Wifi_setting.this.mSelected.networkId != -1) {
                                Log.e(Wifi_setting.TAG, "networkId != INVALID_NETWORK_ID");
                                Wifi_setting.this.updateAccessPoints();
                                synchronized (Wifi_setting.this.lock) {
                                    for (AccessPoint accessPoint : Wifi_setting.this.accessPoints) {
                                        if (accessPoint.ssid != null && accessPoint.ssid.equals(Wifi_setting.this.mSelected.ssid)) {
                                            Wifi_setting.this.mSelected = accessPoint;
                                        }
                                    }
                                }
                            }
                            Log.i(Wifi_setting.TAG, "mSelected networkID" + Wifi_setting.this.mSelected.networkId);
                            Wifi_setting.this.showDialog(Wifi_setting.this.mSelected, false);
                        }
                    });
                } else {
                    Toast.makeText(this.mContext, R.string.wifi_disabled_password_failure, 1).show();
                }
            }
            if (this.mConnected.get() || !SupplicantState.isHandshakeState(supplicantState)) {
                return;
            }
            updateConnectionState(WifiInfo.getDetailedStateOf(supplicantState));
            return;
        }
        if ("android.net.wifi.STATE_CHANGE".equals(action)) {
            NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
            this.mConnected.set(networkInfo.isConnected());
            updateAccessPoints();
            updateConnectionState(networkInfo.getDetailedState());
            if (this.mAutoFinishOnConnection && networkInfo.isConnected()) {
                setResult(-1);
                finish();
                return;
            }
            return;
        }
        if ("android.net.wifi.RSSI_CHANGED".equals(action)) {
            updateConnectionState(null);
        }
    }

    private void updateWifiState(int i) {
        if (i == 3) {
            this.mScanner.resume();
            updateAccessPoints();
        } else {
            this.mScanner.pause();
        }
    }

    private void updateConnectionState(NetworkInfo.DetailedState detailedState) {
        if (!this.mWifiManager.isWifiEnabled()) {
            this.mScanner.pause();
            return;
        }
        if (detailedState == NetworkInfo.DetailedState.OBTAINING_IPADDR) {
            this.mScanner.pause();
        } else {
            this.mScanner.resume();
        }
        this.mLastInfo = this.mWifiManager.getConnectionInfo();
        if (detailedState != null) {
            this.mLastState = detailedState;
        }
        for (int size = this.accessPoints.size() - 1; size >= 0; size--) {
            this.accessPoints.get(size).update(this.mLastInfo, this.mLastState);
        }
        if (this.mResetNetworks) {
            if (detailedState == NetworkInfo.DetailedState.CONNECTED || detailedState == NetworkInfo.DetailedState.DISCONNECTED || detailedState == NetworkInfo.DetailedState.FAILED) {
                updateAccessPoints();
                enableNetworks();
            }
        }
    }

    void submit() {
        Log.i("Settings", "submit" + this.mSelected.toString());
        WifiConfiguration config = this.mDialog.getConfig();
        if (config == null) {
            Log.i("Settings", "config == null");
            if (this.mSelected != null && this.mSelected.networkId != -1) {
                Log.i("Settings", "connect=" + this.mSelected.networkId);
                this.mWifiManager.disconnect();
                this.mWifiManager.connect(this.mSelected.networkId, this.mConnectListener);
            }
        } else if (config.networkId != -1) {
            Log.i("Settings", "config.networkId != INVALID_NETWORK_ID");
            if (this.mSelected != null) {
                this.mWifiManager.save(config, this.mSaveListener);
            }
        } else if (this.mDialog.isEdit()) {
            Log.i("Settings", "else save");
            this.mWifiManager.save(config, this.mSaveListener);
        } else {
            Log.i("Settings", "else connect");
            this.mWifiManager.disconnect();
            this.mWifiManager.addNetwork(config);
            this.mWifiManager.connect(config, this.mConnectListener);
        }
        if (this.mWifiManager.isWifiEnabled()) {
            this.mScanner.resume();
        }
        updateAccessPoints();
    }

    private boolean isNetworkConnected(WifiConfiguration wifiConfiguration) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(ConnectivityManager.class)).getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            Log.d(TAG, "NetworkInfo is null; network is not connected");
            return false;
        }
        Log.d(TAG, "NetworkInfo: " + activeNetworkInfo.toString());
        if (activeNetworkInfo.isConnected() && activeNetworkInfo.getType() == 1) {
            WifiInfo connectionInfo = this.mWifiManager.getConnectionInfo();
            StringBuilder sb = new StringBuilder();
            sb.append("Connected to ");
            sb.append(connectionInfo == null ? "nothing" : connectionInfo.getSSID());
            Log.d(TAG, sb.toString());
            if (connectionInfo != null && connectionInfo.getSSID().equals(wifiConfiguration.SSID)) {
                return true;
            }
        } else {
            Log.d(TAG, "Network is not connected");
        }
        return false;
    }

    void forget() {
        if (this.mSelected.networkId == -1) {
            Log.e(TAG, "Failed to forget invalid network " + this.mSelected.getConfig());
            return;
        }
        this.mWifiManager.forget(this.mSelected.networkId, this.mForgetListener);
        if (this.mWifiManager.isWifiEnabled()) {
            this.mScanner.resume();
        }
        updateAccessPoints();
    }

    private void enableNetworks() {
        this.mResetNetworks = false;
    }

    private void saveNetworks() {
        enableNetworks();
        this.mWifiManager.saveConfiguration();
        updateAccessPoints();
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        if (i == -3 && this.mSelected != null) {
            forget();
        } else if (i == -1) {
            submit();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(AccessPoint accessPoint, boolean z) {
        if (this.mDialog != null) {
            this.mDialog.dismiss();
        }
        this.mDialog = new Wifi_Dialog(this, this, accessPoint, z);
        Utils.fixButtonStyle(this.mDialog);
        this.mDialog.show();
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        registerReceiver(this.mReceiver, this.mFilter);
        if (this.mKeyStoreNetworkId != -1 && KeyStore.getInstance().state() != KeyStore.State.UNLOCKED) {
            this.mWifiManager.connect(this.mKeyStoreNetworkId, this.mConnectListener);
        }
        this.mKeyStoreNetworkId = -1;
        updateAccessPoints();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        unregisterReceiver(this.mReceiver);
        this.mScanner.pause();
        if (this.mDialog != null) {
            this.mDialog.dismiss();
            this.mDialog = null;
        }
        if (this.mResetNetworks) {
            enableNetworks();
        }
    }

    public class WifiUICallBack implements CallBack {
        public WifiUICallBack() {
        }

        @Override // com.rk_itvui.settings.network.wifi.CallBack
        public void onCallBack() {
            if (Wifi_setting.this.mListView == null || Wifi_setting.this.mAdapter == null) {
                return;
            }
            synchronized (Wifi_setting.this.lock) {
                ((Activity) Wifi_setting.this.mContext).runOnUiThread(new Runnable() { // from class: com.rk_itvui.settings.network.wifi.Wifi_setting.WifiUICallBack.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Wifi_setting.this.mAdapter.notifyDataSetChanged();
                    }
                });
            }
        }
    }

    private class Scanner extends Handler {
        private int mRetry;

        private Scanner() {
            this.mRetry = 0;
        }

        void resume() {
            if (hasMessages(0)) {
                return;
            }
            sendEmptyMessage(0);
        }

        void pause() {
            this.mRetry = 0;
            removeMessages(0);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (Wifi_setting.this.mWifiManager.startScan()) {
                this.mRetry = 0;
            } else {
                int i = this.mRetry + 1;
                this.mRetry = i;
                if (i >= 3) {
                    this.mRetry = 0;
                    Toast.makeText(Wifi_setting.this, R.string.wifi_fail_to_scan, 1).show();
                    return;
                }
            }
            sendEmptyMessageDelayed(0, 6000L);
        }
    }

    private void initSwitcher() {
        this.wifiSwitcher = (Button) findViewById(R.id.switcher);
        this.mWifiContentLayout = (LinearLayout) findViewById(R.id.wifi_list_content);
        this.mWifiDisconnectedLayout = (LinearLayout) findViewById(R.id.wifi_list_content_closed);
        this.mWifiOpenningLayout = (LinearLayout) findViewById(R.id.wifi_enable_wait);
    }

    private void getWifiCurrentState() {
        if (getPackageManager().hasSystemFeature("android.hardware.wifi")) {
            this.mWifi_Enabler = new Wifi_Enabler(this, this.mHandler);
            if (this.mWifi_Enabler != null) {
                this.mWifi_Enabler.resume();
                initSwitcher();
                if (this.mWifi_Enabler.getCurrentState()) {
                    this.wifiSwitcher.setBackgroundResource(R.drawable.switch_on);
                } else {
                    this.wifiSwitcher.setBackgroundResource(R.drawable.switch_off);
                }
            }
        }
    }

    public void onClickWifi(View view) {
        this.wifiSwitcher.setEnabled(false);
        if (this.mWifi_Enabler != null) {
            this.mWifi_Enabler.onWiFiClick();
        }
        this.mAdapter.notifyDataSetInvalidated();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mWifi_Enabler.pause();
        if (this.mDialog == null || !this.mDialog.isShowing()) {
            return;
        }
        this.mDialog.dismiss();
    }

    public void updateWifiConnectionState(String str) {
        ((TextView) findViewById(R.id.netState)).setText(str);
    }

    public void AlertOpenError() {
        Toast.makeText(this, R.string.wifi_open_error, 0).show();
    }
}
