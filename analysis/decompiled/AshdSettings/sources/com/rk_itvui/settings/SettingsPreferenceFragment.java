package com.rk_itvui.settings;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

/* JADX INFO: loaded from: classes.dex */
public class SettingsPreferenceFragment extends PreferenceFragment implements DialogCreatable {
    private static final int MENU_HELP = 101;
    private static final String TAG = "SettingsPreferenceFragment";
    private SettingsDialogFragment mDialogFragment;
    private String mHelpUrl;

    protected int getHelpResource() {
        return 0;
    }

    @Override // com.rk_itvui.settings.DialogCreatable
    public Dialog onCreateDialog(int i) {
        return null;
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int helpResource = getHelpResource();
        if (helpResource != 0) {
            this.mHelpUrl = getResources().getString(helpResource);
        }
    }

    @Override // android.preference.PreferenceFragment, android.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (TextUtils.isEmpty(this.mHelpUrl)) {
            return;
        }
        setHasOptionsMenu(true);
    }

    @Override // android.app.Fragment
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (this.mHelpUrl != null) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(this.mHelpUrl));
            intent.setFlags(276824064);
            MenuItem menuItemAdd = menu.add(0, 101, 0, com.ashd.settings.R.string.help_label);
            menuItemAdd.setShowAsAction(0);
            menuItemAdd.setIntent(intent);
        }
    }

    public final void finishFragment() {
        getActivity().onBackPressed();
    }

    protected ContentResolver getContentResolver() {
        return getActivity().getContentResolver();
    }

    protected Object getSystemService(String str) {
        return getActivity().getSystemService(str);
    }

    protected PackageManager getPackageManager() {
        return getActivity().getPackageManager();
    }

    @Override // android.app.Fragment
    public void onDetach() {
        if (isRemoving() && this.mDialogFragment != null) {
            this.mDialogFragment.dismiss();
            this.mDialogFragment = null;
        }
        super.onDetach();
    }

    protected void showDialog(int i) {
        if (this.mDialogFragment != null) {
            Log.e(TAG, "Old dialog fragment not null!");
        }
        this.mDialogFragment = new SettingsDialogFragment(this, i);
        this.mDialogFragment.show(getActivity().getFragmentManager(), Integer.toString(i));
    }

    protected void removeDialog(int i) {
        if (this.mDialogFragment != null && this.mDialogFragment.getDialogId() == i) {
            this.mDialogFragment.dismiss();
        }
        this.mDialogFragment = null;
    }

    protected void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        if (this.mDialogFragment != null) {
            this.mDialogFragment.mOnCancelListener = onCancelListener;
        }
    }

    protected void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        if (this.mDialogFragment != null) {
            this.mDialogFragment.mOnDismissListener = onDismissListener;
        }
    }

    public static class SettingsDialogFragment extends DialogFragment {
        private static final String KEY_DIALOG_ID = "key_dialog_id";
        private static final String KEY_PARENT_FRAGMENT_ID = "key_parent_fragment_id";
        private int mDialogId;
        private DialogInterface.OnCancelListener mOnCancelListener;
        private DialogInterface.OnDismissListener mOnDismissListener;
        private Fragment mParentFragment;

        public SettingsDialogFragment() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        public SettingsDialogFragment(DialogCreatable dialogCreatable, int i) {
            this.mDialogId = i;
            if (!(dialogCreatable instanceof Fragment)) {
                throw new IllegalArgumentException("fragment argument must be an instance of " + Fragment.class.getName());
            }
            this.mParentFragment = (Fragment) dialogCreatable;
        }

        @Override // android.app.DialogFragment, android.app.Fragment
        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            if (this.mParentFragment != null) {
                bundle.putInt(KEY_DIALOG_ID, this.mDialogId);
                bundle.putInt(KEY_PARENT_FRAGMENT_ID, this.mParentFragment.getId());
            }
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            if (bundle != null) {
                this.mDialogId = bundle.getInt(KEY_DIALOG_ID, 0);
                int i = bundle.getInt(KEY_PARENT_FRAGMENT_ID, -1);
                if (i > -1) {
                    this.mParentFragment = getFragmentManager().findFragmentById(i);
                    if (!(this.mParentFragment instanceof DialogCreatable)) {
                        throw new IllegalArgumentException("key_parent_fragment_id must implement " + DialogCreatable.class.getName());
                    }
                }
                if (this.mParentFragment instanceof SettingsPreferenceFragment) {
                    ((SettingsPreferenceFragment) this.mParentFragment).mDialogFragment = this;
                }
            }
            return ((DialogCreatable) this.mParentFragment).onCreateDialog(this.mDialogId);
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            super.onCancel(dialogInterface);
            if (this.mOnCancelListener != null) {
                this.mOnCancelListener.onCancel(dialogInterface);
            }
        }

        @Override // android.app.DialogFragment, android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            super.onDismiss(dialogInterface);
            if (this.mOnDismissListener != null) {
                this.mOnDismissListener.onDismiss(dialogInterface);
            }
        }

        public int getDialogId() {
            return this.mDialogId;
        }

        @Override // android.app.DialogFragment, android.app.Fragment
        public void onDetach() {
            super.onDetach();
            if ((this.mParentFragment instanceof SettingsPreferenceFragment) && ((SettingsPreferenceFragment) this.mParentFragment).mDialogFragment == this) {
                ((SettingsPreferenceFragment) this.mParentFragment).mDialogFragment = null;
            }
        }
    }

    protected boolean hasNextButton() {
        return ((ButtonBarHandler) getActivity()).hasNextButton();
    }

    protected Button getNextButton() {
        return ((ButtonBarHandler) getActivity()).getNextButton();
    }

    public void finish() {
        getActivity().onBackPressed();
    }

    public boolean startFragment(Fragment fragment, String str, int i, Bundle bundle) {
        if (getActivity() instanceof PreferenceActivity) {
            ((PreferenceActivity) getActivity()).startPreferencePanel(str, bundle, com.ashd.settings.R.string.lock_settings_picker_title, null, fragment, i);
            return true;
        }
        Log.w(TAG, "Parent isn't PreferenceActivity, thus there's no way to launch the given Fragment (name: " + str + ", requestCode: " + i + ")");
        return false;
    }
}
