package com.rk_itvui.settings.bluetooth;

import android.content.Context;
import android.os.SystemProperties;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ashd.settings.R;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothSettingsListAdapter extends BaseAdapter {
    private Context mContext;
    private List<DeviceInfo> mDeviceList;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public BluetoothSettingsListAdapter(Context context, List<DeviceInfo> list) {
        this.mContext = context;
        this.mDeviceList = list;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mDeviceList == null) {
            return 0;
        }
        return this.mDeviceList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mDeviceList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        String deviceMac;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_bluetooth_devices, viewGroup, false);
            viewHolder = new ViewHolder();
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.type_icon = (ImageView) view.findViewById(R.id.type_icon);
        viewHolder.tv_device_info = (TextView) view.findViewById(R.id.tv_device_info);
        viewHolder.tv_connect_state = (TextView) view.findViewById(R.id.tv_connect_state);
        if (this.mDeviceList.get(i).getDeviceName() != null) {
            deviceMac = this.mDeviceList.get(i).getDeviceName() + "     " + this.mDeviceList.get(i).getDeviceMac();
        } else {
            deviceMac = this.mDeviceList.get(i).getDeviceMac();
        }
        viewHolder.tv_device_info.setText(deviceMac);
        viewHolder.type_icon.setImageDrawable(BluetoothUtils.toDrawable(this.mContext, BluetoothUtils.getBtClassDrawableWithDescription(this.mDeviceList.get(i).getBtClass())));
        viewHolder.tv_connect_state.setText(this.mDeviceList.get(i).getDeviceState());
        if (BluetoothSettingActivity.sIsDebugBt) {
            String str = SystemProperties.get("persist.sys.yyyklj.connectedname", (String) null);
            if (str != null && str.equals(this.mDeviceList.get(i).getDeviceMac())) {
                viewHolder.tv_connect_state.setText(this.mDeviceList.get(i).getDeviceState() + " TEST CONNECTED");
            }
        } else {
            viewHolder.tv_connect_state.setText(this.mDeviceList.get(i).getDeviceState());
        }
        return view;
    }

    static class ViewHolder {
        TextView tv_connect_state;
        TextView tv_device_info;
        ImageView type_icon;

        ViewHolder() {
        }
    }
}
