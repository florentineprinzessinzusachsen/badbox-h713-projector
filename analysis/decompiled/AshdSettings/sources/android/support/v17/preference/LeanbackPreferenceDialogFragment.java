package android.support.v17.preference;

import android.app.Fragment;
import android.content.ComponentCallbacks2;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.preference.DialogPreference;

/* JADX INFO: loaded from: classes.dex */
public class LeanbackPreferenceDialogFragment extends Fragment {
    public static final String ARG_KEY = "key";
    private DialogPreference mPreference;

    public LeanbackPreferenceDialogFragment() {
        if (Build.VERSION.SDK_INT >= 21) {
            LeanbackPreferenceFragmentTransitionHelperApi21.addTransitions(this);
        }
    }

    @Override // android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ComponentCallbacks2 targetFragment = getTargetFragment();
        if (!(targetFragment instanceof DialogPreference.TargetFragment)) {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        this.mPreference = (DialogPreference) ((DialogPreference.TargetFragment) targetFragment).findPreference(getArguments().getString(ARG_KEY));
    }

    public DialogPreference getPreference() {
        return this.mPreference;
    }
}
