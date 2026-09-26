package com.rk_itvui.settings.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.provider.Settings;
import com.ashd.settings.R;
import com.rk_itvui.settings.developer.DevelopmentSettings;
import com.rk_itvui.settings.developer.ListViewAdapter;

/* JADX INFO: loaded from: classes.dex */
public class UnknownSources {
    private static final String TAG = "UnknownSources";
    private Context mContext;
    private Handler mHandler;
    private ListViewAdapter mListViewAdapter;
    private DialogInterface mWarnInstallApps;
    private boolean mselect = false;

    public UnknownSources(Context context, Handler handler, ListViewAdapter listViewAdapter) {
        this.mContext = context;
        this.mHandler = handler;
        this.mListViewAdapter = listViewAdapter;
        updateItemStatus();
    }

    public void SourcesUnknown() {
        this.mselect = Settings.Secure.getInt(this.mContext.getContentResolver(), "install_non_market_apps", 0) == 1;
        if (!this.mselect) {
            warnAppInstallation();
            return;
        }
        this.mselect = false;
        setNonMarketAppsAllowed(false);
        updateItemStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateItemStatus() {
        ((DevelopmentSettings) this.mContext).updateSettingItem(R.string.unknown_sources, Settings.Secure.getInt(this.mContext.getContentResolver(), "install_non_market_apps", 0) == 1 ? R.string.source_yes : R.string.source_no, -1, R.drawable.source);
        this.mHandler.sendEmptyMessage(0);
    }

    private boolean isNonMarketAppsAllowed() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "install_non_market_apps", 0) > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNonMarketAppsAllowed(boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "install_non_market_apps", z ? 1 : 0);
    }

    private void warnAppInstallation() {
        this.mWarnInstallApps = new AlertDialog.Builder(this.mContext).setTitle(this.mContext.getString(R.string.error_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(this.mContext.getResources().getString(R.string.install_all_warning)).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.UnknownSources.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                UnknownSources.this.setNonMarketAppsAllowed(true);
                UnknownSources.this.mselect = true;
                UnknownSources.this.updateItemStatus();
            }
        }).setNegativeButton(android.R.string.no, (DialogInterface.OnClickListener) null).show();
    }
}
