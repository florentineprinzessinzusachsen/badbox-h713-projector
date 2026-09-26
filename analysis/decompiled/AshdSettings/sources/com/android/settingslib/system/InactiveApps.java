package com.android.settingslib.system;

import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.v17.preference.LeanbackPreferenceFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import com.android.settingslib.R;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class InactiveApps extends LeanbackPreferenceFragment implements Preference.OnPreferenceClickListener {
    private UsageStatsManager mUsageStats;

    @Override // android.support.v14.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        this.mUsageStats = (UsageStatsManager) getActivity().getSystemService(UsageStatsManager.class);
        super.onCreate(bundle);
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        init();
    }

    @Override // android.support.v14.preference.PreferenceFragment
    public void onCreatePreferences(Bundle bundle, String str) {
        PreferenceScreen preferenceScreenCreatePreferenceScreen = getPreferenceManager().createPreferenceScreen(getPreferenceManager().getContext());
        preferenceScreenCreatePreferenceScreen.setTitle(R.string.inactive_apps_title);
        setPreferenceScreen(preferenceScreenCreatePreferenceScreen);
    }

    private void init() {
        Context context = getPreferenceManager().getContext();
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        preferenceScreen.removeAll();
        preferenceScreen.setOrderingAsAdded(false);
        PackageManager packageManager = getActivity().getPackageManager();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            String str = resolveInfo.activityInfo.applicationInfo.packageName;
            Preference preference = new Preference(context);
            preference.setTitle(resolveInfo.loadLabel(packageManager));
            preference.setIcon(resolveInfo.loadIcon(packageManager));
            preference.setKey(str);
            updateSummary(preference);
            preference.setOnPreferenceClickListener(this);
            preferenceScreen.addPreference(preference);
        }
    }

    private void updateSummary(Preference preference) {
        preference.setSummary(this.mUsageStats.isAppInactive(preference.getKey()) ? R.string.inactive_app_inactive_summary : R.string.inactive_app_active_summary);
    }

    @Override // android.support.v7.preference.Preference.OnPreferenceClickListener
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        this.mUsageStats.setAppInactive(key, !this.mUsageStats.isAppInactive(key));
        updateSummary(preference);
        return false;
    }
}
