package com.rk_itvui.settings.bluetooth;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.ashd.settings.R;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothFragment extends Fragment {
    private static final String TAG = "wxx";
    private BluetoothAdapter bluetoothAdapter;
    private Context mContext;
    private IntentFilter mIntentFilter;
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.bluetooth.BluetoothFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
            if (!TextUtils.isEmpty(action) && action.equals("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED")) {
                BluetoothFragment.this.tv_bluetooth_name.setText(BluetoothFragment.this.bluetoothAdapter.getName());
            }
            BluetoothFragment.this.parentHandler.sendEmptyMessage(intExtra);
        }
    };
    private View mRootView;
    private Handler parentHandler;
    private TextView tv_bluetooth_mac;
    private TextView tv_bluetooth_name;

    public BluetoothFragment() {
    }

    @SuppressLint({"ValidFragment"})
    public BluetoothFragment(Handler handler, Context context) {
        this.parentHandler = handler;
        this.mContext = context;
        if (this.mContext == null) {
            Log.i(TAG, "BluetoothFragment: 加载蓝牙Fragment---context is null");
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        init();
        resume();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        getDeviceInfo();
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mRootView = layoutInflater.inflate(R.layout.fragment_bluetooth, viewGroup, false);
        this.tv_bluetooth_name = (TextView) this.mRootView.findViewById(R.id.tv_bluetooth_name);
        this.tv_bluetooth_mac = (TextView) this.mRootView.findViewById(R.id.tv_bluetooth_mac);
        return this.mRootView;
    }

    private void getDeviceInfo() {
        if (this.bluetoothAdapter == null) {
            return;
        }
        this.tv_bluetooth_name.setText(this.bluetoothAdapter.getName());
        this.tv_bluetooth_mac.setText(this.bluetoothAdapter.getAddress());
    }

    public void init() {
        try {
            this.mIntentFilter = new IntentFilter();
            this.mIntentFilter.addAction("android.bluetooth.adapter.action.LOCAL_NAME_CHANGED");
            this.mIntentFilter.addAction("android.bluetooth.device.action.FOUND");
            this.mIntentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        if (this.mContext == null) {
            return;
        }
        this.mContext.registerReceiver(this.mReceiver, this.mIntentFilter);
    }

    public void pause() {
        if (this.mContext == null) {
            return;
        }
        try {
            this.mContext.unregisterReceiver(this.mReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean bluetoothIsEnable() {
        if (this.bluetoothAdapter == null) {
            this.bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (this.bluetoothAdapter == null) {
                Log.e(TAG, "init: Device doesn't support Bluetooth");
                return false;
            }
        }
        Log.i(TAG, "init: bluetooth isEnabled=" + this.bluetoothAdapter.isEnabled());
        return this.bluetoothAdapter.isEnabled();
    }
}
