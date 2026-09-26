package com.rk_itvui.settings.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.SystemProperties;
import android.util.Log;
import com.ashd.settings.R;
import com.rk_itvui.settings.developer.DevelopmentSettings;
import com.rk_itvui.settings.developer.ListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class EventNotificationSetting {
    private Context mContext;
    private Handler mHandler;
    private ListViewAdapter mListViewAdapter;
    private String mMode;

    public EventNotificationSetting(Context context, Handler handler, ListViewAdapter listViewAdapter) {
        this.mMode = null;
        this.mContext = context;
        this.mHandler = handler;
        this.mListViewAdapter = listViewAdapter;
        this.mMode = SystemProperties.get("persist.sys.EventNotification", "open");
        Log.d("EventNotificationSetting", "mMode = " + this.mMode);
        ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.event_notification, this.mMode.equals("open") ? R.string.open : R.string.off, -1, -1);
    }

    public void onEventNotificationClick() {
        if (this.mMode.equals("open")) {
            SystemProperties.set("persist.sys.EventNotification", "close");
        } else if (this.mMode.equals("close")) {
            SystemProperties.set("persist.sys.EventNotification", "open");
        }
        this.mMode = SystemProperties.get("persist.sys.EventNotification", "open");
        ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.event_notification, this.mMode.equals("open") ? R.string.open : R.string.off, -1, -1);
        this.mHandler.sendEmptyMessage(0);
    }
}
