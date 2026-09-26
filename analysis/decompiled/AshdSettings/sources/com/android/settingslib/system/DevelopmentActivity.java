package com.android.settingslib.system;

import android.app.Fragment;

/* JADX INFO: loaded from: classes.dex */
public class DevelopmentActivity extends TvSettingsActivity {
    @Override // com.android.settingslib.system.TvSettingsActivity
    protected Fragment createSettingsFragment() {
        return SettingsFragment.newInstance();
    }

    public static class SettingsFragment extends BaseSettingsFragment {
        public static SettingsFragment newInstance() {
            return new SettingsFragment();
        }

        @Override // android.support.v17.preference.LeanbackSettingsFragment
        public void onPreferenceStartInitialScreen() {
            startPreferenceFragment(DevelopmentFragment.newInstance());
        }
    }
}
