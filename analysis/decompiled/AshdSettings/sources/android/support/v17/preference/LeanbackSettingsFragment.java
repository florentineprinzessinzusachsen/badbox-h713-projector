package android.support.v17.preference;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v14.preference.MultiSelectListPreference;
import android.support.v14.preference.PreferenceFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;

/* JADX INFO: loaded from: classes.dex */
public abstract class LeanbackSettingsFragment extends Fragment implements PreferenceFragment.OnPreferenceStartFragmentCallback, PreferenceFragment.OnPreferenceStartScreenCallback, PreferenceFragment.OnPreferenceDisplayDialogCallback {
    private static final String PREFERENCE_FRAGMENT_TAG = "android.support.v17.preference.LeanbackSettingsFragment.PREFERENCE_FRAGMENT";
    private final RootViewOnKeyListener mRootViewOnKeyListener = new RootViewOnKeyListener();

    public abstract void onPreferenceStartInitialScreen();

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.leanback_settings_fragment, viewGroup, false);
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle == null) {
            onPreferenceStartInitialScreen();
        }
    }

    @Override // android.app.Fragment
    public void onResume() {
        super.onResume();
        LeanbackSettingsRootView leanbackSettingsRootView = (LeanbackSettingsRootView) getView();
        if (leanbackSettingsRootView != null) {
            leanbackSettingsRootView.setOnBackKeyListener(this.mRootViewOnKeyListener);
        }
    }

    @Override // android.app.Fragment
    public void onPause() {
        super.onPause();
        LeanbackSettingsRootView leanbackSettingsRootView = (LeanbackSettingsRootView) getView();
        if (leanbackSettingsRootView != null) {
            leanbackSettingsRootView.setOnBackKeyListener(null);
        }
    }

    @Override // android.support.v14.preference.PreferenceFragment.OnPreferenceDisplayDialogCallback
    public boolean onPreferenceDisplayDialog(PreferenceFragment preferenceFragment, Preference preference) {
        if (preference instanceof ListPreference) {
            LeanbackListPreferenceDialogFragment leanbackListPreferenceDialogFragmentNewInstanceSingle = LeanbackListPreferenceDialogFragment.newInstanceSingle(((ListPreference) preference).getKey());
            leanbackListPreferenceDialogFragmentNewInstanceSingle.setTargetFragment(preferenceFragment, 0);
            startPreferenceFragment(leanbackListPreferenceDialogFragmentNewInstanceSingle);
            return true;
        }
        if (!(preference instanceof MultiSelectListPreference)) {
            return false;
        }
        LeanbackListPreferenceDialogFragment leanbackListPreferenceDialogFragmentNewInstanceMulti = LeanbackListPreferenceDialogFragment.newInstanceMulti(((MultiSelectListPreference) preference).getKey());
        leanbackListPreferenceDialogFragmentNewInstanceMulti.setTargetFragment(preferenceFragment, 0);
        startPreferenceFragment(leanbackListPreferenceDialogFragmentNewInstanceMulti);
        return true;
    }

    public void startPreferenceFragment(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        if (getChildFragmentManager().findFragmentByTag(PREFERENCE_FRAGMENT_TAG) != null) {
            fragmentTransactionBeginTransaction.addToBackStack(null).replace(R.id.settings_preference_fragment_container, fragment, PREFERENCE_FRAGMENT_TAG);
        } else {
            fragmentTransactionBeginTransaction.add(R.id.settings_preference_fragment_container, fragment, PREFERENCE_FRAGMENT_TAG);
        }
        fragmentTransactionBeginTransaction.commit();
    }

    public void startImmersiveFragment(@NonNull Fragment fragment) {
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Fragment fragmentFindFragmentByTag = getChildFragmentManager().findFragmentByTag(PREFERENCE_FRAGMENT_TAG);
        if (fragmentFindFragmentByTag != null && !fragmentFindFragmentByTag.isHidden()) {
            if (Build.VERSION.SDK_INT < 23) {
                fragmentTransactionBeginTransaction.add(R.id.settings_preference_fragment_container, new DummyFragment());
            }
            fragmentTransactionBeginTransaction.remove(fragmentFindFragmentByTag);
        }
        fragmentTransactionBeginTransaction.add(R.id.settings_dialog_container, fragment).addToBackStack(null).commit();
    }

    private class RootViewOnKeyListener implements View.OnKeyListener {
        private RootViewOnKeyListener() {
        }

        @Override // android.view.View.OnKeyListener
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i == 4) {
                return LeanbackSettingsFragment.this.getChildFragmentManager().popBackStackImmediate();
            }
            return false;
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static class DummyFragment extends Fragment {
        @Override // android.app.Fragment
        @Nullable
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            Space space = new Space(layoutInflater.getContext());
            space.setVisibility(8);
            return space;
        }
    }
}
