package com.rk_itvui.settings.network;

import android.content.Context;
import android.content.Intent;
import android.net.EthernetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.network.pppoe.EthernetPppoeSetting;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class WireNetworkSetting extends FullScreenActivity implements AdapterView.OnItemClickListener {
    private String[] list;
    private ListView listView;
    private String mDHCPSetting;
    private EthernetManager mEthMgr;
    private int mEthState;
    private String mPppoeSetting;
    private String mStaticIPSetting;
    private HashMap<Integer, Integer> map = new HashMap<>();
    final RadioAdapter adapter = new RadioAdapter(this);

    public void init() {
        this.mPppoeSetting = getResources().getString(R.string.pppoe_setting);
        this.mDHCPSetting = getResources().getString(R.string.DHCP_setting);
        this.mStaticIPSetting = getResources().getString(R.string.StatiIP_setting);
        this.list = new String[]{this.mPppoeSetting, this.mDHCPSetting, this.mStaticIPSetting};
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wirenetwork_setting);
        init();
        this.listView = (ListView) findViewById(R.id.wireNetworkListView);
        this.listView.setAdapter((ListAdapter) this.adapter);
        this.listView.setOnItemClickListener(this);
        initRadioChecked();
    }

    void initRadioChecked() {
        this.mEthMgr = (EthernetManager) getSystemService("ethernet");
        EthernetIP ethernetIP = new EthernetIP();
        ethernetIP.transContext(this);
        this.mEthState = this.mEthMgr.getEthernetConnectState();
        if (ethernetIP.isUsingPppoe()) {
            this.map.put(0, 100);
        } else if (ethernetIP.isUsingStaticIp()) {
            this.map.put(2, 100);
        } else {
            this.map.put(1, 100);
        }
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.map.clear();
        this.map.put(Integer.valueOf(i), 100);
        this.adapter.notifyDataSetChanged();
        switch (i) {
            case 0:
                startActivity(new Intent(this, (Class<?>) EthernetPppoeSetting.class));
                break;
            case 1:
                startActivity(new Intent(this, (Class<?>) EthernetDHCPInfoSetting.class));
                break;
            case 2:
                startActivity(new Intent(this, (Class<?>) EthernetStaticIPSetting.class));
                break;
        }
    }

    class RadioHolder {
        private TextView item;
        private RadioButton radio;

        public RadioHolder(View view) {
            this.radio = (RadioButton) view.findViewById(R.id.item_radio);
            this.item = (TextView) view.findViewById(R.id.item_text);
        }
    }

    class RadioAdapter extends BaseAdapter {
        private Context context;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public RadioAdapter(Context context) {
            this.context = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return WireNetworkSetting.this.list.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return WireNetworkSetting.this.list[i];
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            RadioHolder radioHolder;
            if (view == null) {
                view = LayoutInflater.from(this.context).inflate(R.layout.wirenetwork_item, (ViewGroup) null);
                radioHolder = WireNetworkSetting.this.new RadioHolder(view);
                view.setTag(radioHolder);
            } else {
                radioHolder = (RadioHolder) view.getTag();
            }
            radioHolder.radio.setChecked(WireNetworkSetting.this.map.get(Integer.valueOf(i)) != null);
            radioHolder.item.setText(WireNetworkSetting.this.list[i]);
            return view;
        }
    }
}
