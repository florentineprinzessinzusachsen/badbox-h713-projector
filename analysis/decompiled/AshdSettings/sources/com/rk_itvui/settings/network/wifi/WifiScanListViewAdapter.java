package com.rk_itvui.settings.network.wifi;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ashd.settings.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class WifiScanListViewAdapter extends BaseAdapter {
    private LayoutInflater flater;
    private ArrayList<AccessPoint> mArrayList;
    private Context mContext;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public WifiScanListViewAdapter(Context context, ArrayList<AccessPoint> arrayList) {
        this.mContext = context;
        this.mArrayList = arrayList;
        this.flater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
    }

    @Override // android.widget.Adapter
    public int getCount() {
        if (this.mArrayList != null) {
            return this.mArrayList.size();
        }
        return 0;
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        if (this.mArrayList != null) {
            return this.mArrayList.get(i);
        }
        return null;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (i == 0) {
            LinearLayout linearLayout = (LinearLayout) this.flater.inflate(R.layout.wifi_ap_layout, (ViewGroup) null);
            if (this.mArrayList.size() <= 0) {
                return linearLayout;
            }
            ((TextView) linearLayout.findViewById(R.id.add_wifi_network)).setText(this.mArrayList.get(i).ssid);
            ImageView imageView = (ImageView) linearLayout.findViewById(R.id.devider);
            TextView textView = (TextView) linearLayout.findViewById(R.id.no_wifi_founded);
            if (this.mArrayList.size() > 1) {
                imageView.setVisibility(8);
                textView.setVisibility(8);
            }
            return linearLayout;
        }
        RelativeLayout relativeLayout = (RelativeLayout) this.flater.inflate(R.layout.wifi_item, (ViewGroup) null);
        if (this.mArrayList.size() <= i) {
            return relativeLayout;
        }
        TextView textView2 = (TextView) relativeLayout.findViewById(R.id.wifi_name);
        TextView textView3 = (TextView) relativeLayout.findViewById(R.id.wifi_summary);
        AccessPoint accessPoint = this.mArrayList.get(i);
        accessPoint.onBindView(relativeLayout);
        textView2.setText(accessPoint.ssid);
        StringBuilder sb = new StringBuilder();
        sb.append("point.ssid=");
        sb.append(accessPoint.ssid);
        sb.append(",point.states=");
        sb.append(accessPoint.getState() == null ? "null" : accessPoint.getState().toString());
        sb.append(":summary=");
        sb.append(accessPoint.getSummary());
        Log.e("WifiSettings", sb.toString());
        textView3.setText(accessPoint.getSummary());
        return relativeLayout;
    }
}
