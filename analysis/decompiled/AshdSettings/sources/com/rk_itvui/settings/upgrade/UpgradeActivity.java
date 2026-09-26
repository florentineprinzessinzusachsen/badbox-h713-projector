package com.rk_itvui.settings.upgrade;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.adapter.SettingListAdapter;
import com.rk_itvui.settings.model.ListItem;
import com.rk_itvui.utils.ReflectionUtils;
import com.rk_itvui.utils.WindowHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class UpgradeActivity extends FullScreenActivity implements View.OnClickListener {
    private static final int STATE_DETECT = 1;
    private static final int STATE_UPDATE = 2;
    private int mState;
    private Button mUpgradeButton;
    private ProgressBar mUpgradeProgress;
    private TextView mUpgradeTxt;

    public void upgradeVersion() {
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.setting_upgrade);
        ListView listView = (ListView) findViewById(R.id.list_view);
        this.mUpgradeButton = (Button) findViewById(R.id.upgrade_btn_upgrade);
        this.mUpgradeTxt = (TextView) findViewById(R.id.upgrade_txt_update);
        this.mUpgradeProgress = (ProgressBar) findViewById(R.id.upgrade_progress);
        this.mUpgradeButton.setOnClickListener(this);
        listView.setAdapter((ListAdapter) new SettingListAdapter(this, buildListItem()));
        ViewGroup.LayoutParams layoutParams = this.mUpgradeProgress.getLayoutParams();
        layoutParams.width = (int) (((double) WindowHelper.getWinWidth(this)) * 0.7d);
        this.mUpgradeProgress.setLayoutParams(layoutParams);
        this.mState = 1;
    }

    private ArrayList<ListItem> buildListItem() {
        ArrayList<ListItem> arrayList = new ArrayList<>();
        ListItem listItem = new ListItem();
        listItem.setIconRes(R.drawable.upgrade_device);
        listItem.setTitle(getString(R.string.upgrade_device_name));
        listItem.setDetail(Build.MODEL);
        arrayList.add(listItem);
        ListItem listItem2 = new ListItem();
        listItem2.setIconRes(R.drawable.upgrade_wifi);
        listItem2.setTitle(getString(R.string.upgrade_wifi_mac));
        listItem2.setDetail(getWifiMac());
        arrayList.add(listItem2);
        ListItem listItem3 = new ListItem();
        listItem3.setIconRes(R.drawable.upgrade_ethernet);
        listItem3.setTitle(getString(R.string.upgrade_ether_mac));
        listItem3.setDetail(getEthernetMac());
        arrayList.add(listItem3);
        ListItem listItem4 = new ListItem();
        listItem4.setIconRes(R.drawable.upgrade_version);
        listItem4.setTitle(getString(R.string.upgrade_version));
        listItem4.setDetail(getProductVersion());
        arrayList.add(listItem4);
        return arrayList;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.upgrade_btn_upgrade) {
            if (this.mState == 1) {
                detectVersion();
            } else if (this.mState == 2) {
                upgradeVersion();
            }
        }
    }

    public Intent getExplicitIntent(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.size() != 1) {
            return null;
        }
        ResolveInfo resolveInfo = listQueryIntentServices.get(0);
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    public void detectVersion() {
        this.mUpgradeTxt.setText(R.string.upgrade_version_new);
    }

    private String getWifiMac() {
        WifiInfo connectionInfo = ((WifiManager) getSystemService("wifi")).getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getMacAddress();
        }
        return null;
    }

    private String getEthernetMac() {
        Object systemService = getSystemService("ethernet");
        return (String) ReflectionUtils.invokeMethod(systemService, "getEthernetHwaddr", ReflectionUtils.invokeMethod(systemService, "getEthernetIfaceName", new Object[0]));
    }

    private String getProductVersion() {
        return (String) ReflectionUtils.invokeStaticMethod("android.os.SystemProperties", "get", "ro.product.version", "");
    }
}
