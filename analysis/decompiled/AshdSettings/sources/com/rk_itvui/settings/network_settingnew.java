package com.rk_itvui.settings;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import com.rk_itvui.settings.bluetooth.BluetoothFragment;
import com.rk_itvui.settings.bluetooth.BluetoothSettingActivity;
import com.rk_itvui.settings.network.WifiApFragment;
import com.rk_itvui.settings.network.WifiFragment;
import com.rk_itvui.settings.network.wifi.WifiAp_Settings;
import com.rk_itvui.settings.network.wifi.Wifi_setting;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class network_settingnew extends FullScreenActivity implements AdapterView.OnItemClickListener {
    private static final String TAG = "wxx";
    BluetoothFragment bluetoothFragment;
    boolean hasFocused;
    ImageView img_back;
    ListView list;
    SimpleAdapter listItemAdapter;
    Context mContext;
    WifiApFragment wifiApFragment;
    WifiFragment wifiFragment;
    ArrayList<HashMap<String, Object>> listItem = new ArrayList<>();
    HashMap<String, Object> map_WiredItem = new HashMap<>();
    HashMap<String, Object> map_WiFiItem = new HashMap<>();
    HashMap<String, Object> map_WiFiApItem = new HashMap<>();
    HashMap<String, Object> map_BluetoothItem = new HashMap<>();
    private final Handler wireNetworkHandler = new Handler() { // from class: com.rk_itvui.settings.network_settingnew.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    network_settingnew.this.map_WiredItem.put("networkSettingStatus", network_settingnew.this.getResources().getString(com.ashd.settings.R.string.wiredNetworkUnconnected));
                    break;
                case 1:
                    network_settingnew.this.map_WiredItem.put("networkSettingStatus", network_settingnew.this.getResources().getString(com.ashd.settings.R.string.wiredNetworkConnected));
                    break;
            }
            network_settingnew.this.listItemAdapter.notifyDataSetChanged();
        }
    };
    private final Handler wifiHandler = new Handler() { // from class: com.rk_itvui.settings.network_settingnew.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            switch (message.what) {
                case 0:
                    i = com.ashd.settings.R.string.wifi_stopping;
                    break;
                case 1:
                    i = com.ashd.settings.R.string.turn_off;
                    break;
                case 2:
                    i = com.ashd.settings.R.string.wifi_starting;
                    break;
                case 3:
                    i = com.ashd.settings.R.string.turn_on;
                    break;
                default:
                    i = com.ashd.settings.R.string.wifi_error;
                    break;
            }
            network_settingnew.this.map_WiFiItem.put("networkSettingStatus", network_settingnew.this.getResources().getString(i));
            network_settingnew.this.listItemAdapter.notifyDataSetChanged();
        }
    };
    private final Handler wifiApHandler = new Handler() { // from class: com.rk_itvui.settings.network_settingnew.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            Log.d("blb", "wifiApHandler ,handleMessage() msg.what = " + message.what);
            switch (message.what) {
                case 10:
                    i = com.ashd.settings.R.string.wifi_stopping;
                    break;
                case 11:
                    i = com.ashd.settings.R.string.accessibility_service_state_off;
                    break;
                case 12:
                    i = com.ashd.settings.R.string.wifi_starting;
                    break;
                case 13:
                    i = com.ashd.settings.R.string.accessibility_service_state_on;
                    break;
                default:
                    i = com.ashd.settings.R.string.wifi_error;
                    break;
            }
            network_settingnew.this.map_WiFiApItem.put("networkSettingStatus", network_settingnew.this.getResources().getString(i));
            network_settingnew.this.listItemAdapter.notifyDataSetChanged();
        }
    };
    private final Handler bluetoothHandler = new Handler() { // from class: com.rk_itvui.settings.network_settingnew.7
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            String string = "";
            switch (message.what) {
                case 10:
                    Log.d(network_settingnew.TAG, "Bluetooth State: STATE_OFF");
                    string = network_settingnew.this.getString(com.ashd.settings.R.string.turn_off);
                    network_settingnew.this.map_BluetoothItem.put("networkSettingIcon", Integer.valueOf(com.ashd.settings.R.drawable.ic_bluetooth_off));
                    break;
                case 11:
                    Log.d(network_settingnew.TAG, "Bluetooth State: STATE_TURNING_ON");
                    string = network_settingnew.this.getString(com.ashd.settings.R.string.turning_on);
                    break;
                case 12:
                    Log.d(network_settingnew.TAG, "Bluetooth State: STATE_ON");
                    string = network_settingnew.this.getString(com.ashd.settings.R.string.turn_on);
                    network_settingnew.this.map_BluetoothItem.put("networkSettingIcon", Integer.valueOf(com.ashd.settings.R.drawable.ic_bluetooth_on));
                    break;
                case 13:
                    Log.d(network_settingnew.TAG, "Bluetooth State: STATE_TURNING_OFF");
                    string = network_settingnew.this.getString(com.ashd.settings.R.string.turning_off);
                    break;
                default:
                    Log.e(network_settingnew.TAG, "Bluetooth State: STATE_ERROR");
                    break;
            }
            network_settingnew.this.map_BluetoothItem.put("networkSettingStatus", string);
        }
    };

    private void registerReciver() {
    }

    public void initFragment() {
        Log.i(TAG, "initFragment: 加载Fragment");
        if (this.mContext == null) {
            this.mContext = this;
        }
        this.wifiFragment = new WifiFragment(this.wifiHandler, this.mContext);
        this.bluetoothFragment = new BluetoothFragment(this.bluetoothHandler, this.mContext);
        getFragmentManager().beginTransaction().replace(com.ashd.settings.R.id.networkInfo, this.wifiFragment).commit();
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(com.ashd.settings.R.layout.network_settingnew);
        this.mContext = this;
        if (BuildConfig.FLAVOR.contains(BuildConfig.FLAVOR)) {
            TextView textView = (TextView) findViewById(com.ashd.settings.R.id.app_text);
            TextView textView2 = (TextView) findViewById(com.ashd.settings.R.id.settings_line);
            textView.setCompoundDrawablesWithIntrinsicBounds(getDrawable(com.ashd.settings.R.drawable.icon_ashd2), (Drawable) null, (Drawable) null, (Drawable) null);
            textView2.setBackgroundResource(com.ashd.settings.R.drawable.settings_head_line_ashd2);
        }
        boolean z = SystemProperties.getBoolean("persist.ashd.bt.showback", true);
        this.img_back = (ImageView) findViewById(com.ashd.settings.R.id.img_back);
        if (z) {
            this.img_back.setVisibility(0);
            this.img_back.setOnClickListener(new View.OnClickListener() { // from class: com.rk_itvui.settings.network_settingnew.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    network_settingnew.this.onBackPressed();
                }
            });
        } else {
            this.img_back.setVisibility(4);
        }
        initFragment();
        addListView();
        try {
            registerReciver();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addListView() {
        Resources resources;
        String string = getResources().getString(com.ashd.settings.R.string.ethernet);
        String string2 = getResources().getString(com.ashd.settings.R.string.disconncect);
        this.map_WiredItem.put("networkSettingIcon", Integer.valueOf(com.ashd.settings.R.drawable.network_icon_eth));
        this.map_WiredItem.put("networkSettingItem", string);
        this.map_WiredItem.put("networkSettingStatus", string2);
        Resources resources2 = getResources();
        int i = com.ashd.settings.R.string.off;
        String string3 = resources2.getString(com.ashd.settings.R.string.off);
        this.map_WiFiItem.put("networkSettingIcon", Integer.valueOf(com.ashd.settings.R.drawable.network_icon_wifi));
        this.map_WiFiItem.put("networkSettingItem", "Wi-Fi");
        this.map_WiFiItem.put("networkSettingStatus", string3);
        this.map_BluetoothItem.put("networkSettingIcon", Integer.valueOf(this.bluetoothFragment.bluetoothIsEnable() ? com.ashd.settings.R.drawable.ic_bluetooth_on : com.ashd.settings.R.drawable.ic_bluetooth_off));
        this.map_BluetoothItem.put("networkSettingItem", getResources().getString(com.ashd.settings.R.string.bluetooth));
        if (this.bluetoothFragment.bluetoothIsEnable()) {
            resources = getResources();
            i = com.ashd.settings.R.string.open;
        } else {
            resources = getResources();
        }
        this.map_BluetoothItem.put("networkSettingStatus", resources.getString(i));
        String string4 = getResources().getString(com.ashd.settings.R.string.Ap);
        String string5 = getResources().getString(com.ashd.settings.R.string.accessibility_service_state_off);
        this.map_WiFiApItem.put("networkSettingIcon", Integer.valueOf(com.ashd.settings.R.drawable.network_icon_hotspot));
        this.map_WiFiApItem.put("networkSettingItem", string4);
        this.map_WiFiApItem.put("networkSettingStatus", string5);
        this.listItem.add(this.map_WiFiItem);
        if (SystemProperties.get("persist.sys.has.wifiAP", "false").equals("true")) {
            this.listItem.add(this.map_WiFiApItem);
        }
        SystemProperties.get("persist.sys.wifitype", "unkown");
        if (!(SystemProperties.getInt("persist.sys.settings.bluetooth", 2) != 0)) {
            ((TextView) findViewById(com.ashd.settings.R.id.title_name)).setText(getString(com.ashd.settings.R.string.network_settings));
        }
        this.list = (ListView) findViewById(com.ashd.settings.R.id.networkListView);
        this.listItemAdapter = new SimpleAdapter(this, this.listItem, com.ashd.settings.R.layout.network_item, new String[]{"networkSettingIcon", "networkSettingItem", "networkSettingStatus"}, new int[]{com.ashd.settings.R.id.networkSettingIcon, com.ashd.settings.R.id.networkSettingItem, com.ashd.settings.R.id.networkSettingStatus});
        this.list.setAdapter((ListAdapter) this.listItemAdapter);
        this.list.setOnItemClickListener(this);
        this.list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.rk_itvui.settings.network_settingnew.2
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                String str = (String) network_settingnew.this.listItem.get(i2).get("networkSettingItem");
                if (str.equals("Wi-Fi")) {
                    network_settingnew.this.getFragmentManager().beginTransaction().replace(com.ashd.settings.R.id.networkInfo, network_settingnew.this.wifiFragment).commit();
                } else if (str.equals(network_settingnew.this.getResources().getString(com.ashd.settings.R.string.Ap))) {
                    network_settingnew.this.getFragmentManager().beginTransaction().replace(com.ashd.settings.R.id.networkInfo, network_settingnew.this.wifiApFragment).commit();
                } else if (str.equals(network_settingnew.this.getResources().getString(com.ashd.settings.R.string.bluetooth))) {
                    network_settingnew.this.getFragmentManager().beginTransaction().replace(com.ashd.settings.R.id.networkInfo, network_settingnew.this.bluetoothFragment).commit();
                }
            }
        });
        this.list.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.rk_itvui.settings.network_settingnew.3
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View view, boolean z) {
                network_settingnew.this.hasFocused = z;
            }
        });
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        String str = (String) this.listItem.get(i).get("networkSettingItem");
        if (str.equals("Wi-Fi")) {
            startActivity(new Intent(this, (Class<?>) Wifi_setting.class));
        } else if (str.equals(getResources().getString(com.ashd.settings.R.string.Ap))) {
            startActivity(new Intent(this, (Class<?>) WifiAp_Settings.class));
        } else if (str.equals(getResources().getString(com.ashd.settings.R.string.bluetooth))) {
            startActivity(new Intent(this, (Class<?>) BluetoothSettingActivity.class));
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.wifiFragment != null) {
            this.wifiFragment.pause();
        }
        if (this.bluetoothFragment != null) {
            this.bluetoothFragment.pause();
        }
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int selectedItemPosition = this.list.getSelectedItemPosition();
        if (this.hasFocused) {
            if (keyEvent.getKeyCode() == 20 || keyEvent.getKeyCode() == 22) {
                if (selectedItemPosition == this.list.getChildCount() - 1) {
                    this.list.setSelection(0);
                    return true;
                }
                this.list.setSelection(selectedItemPosition + 1);
                return true;
            }
            if (keyEvent.getKeyCode() == 19 || keyEvent.getKeyCode() == 21) {
                if (selectedItemPosition == 0) {
                    this.img_back.requestFocus();
                    return true;
                }
                this.list.setSelection(selectedItemPosition - 1);
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
