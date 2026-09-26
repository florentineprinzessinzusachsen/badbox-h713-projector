package com.android.settingslib.system;

import android.app.Fragment;
import android.support.v14.preference.PreferenceDialogFragment;
import android.support.v14.preference.PreferenceFragment;
import android.support.v17.preference.LeanbackSettingsFragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseSettingsFragment extends LeanbackSettingsFragment {
    @Override // android.support.v14.preference.PreferenceFragment.OnPreferenceStartScreenCallback
    public final boolean onPreferenceStartScreen(PreferenceFragment preferenceFragment, PreferenceScreen preferenceScreen) {
        return false;
    }

    @Override // android.support.v14.preference.PreferenceFragment.OnPreferenceStartFragmentCallback
    public final boolean onPreferenceStartFragment(PreferenceFragment preferenceFragment, Preference preference) {
        Fragment fragmentInstantiate = Fragment.instantiate(getActivity(), preference.getFragment(), preference.getExtras());
        fragmentInstantiate.setTargetFragment(preferenceFragment, 0);
        if ((fragmentInstantiate instanceof PreferenceFragment) || (fragmentInstantiate instanceof PreferenceDialogFragment)) {
            startPreferenceFragment(fragmentInstantiate);
            return true;
        }
        startImmersiveFragment(fragmentInstantiate);
        return true;
    }
}
