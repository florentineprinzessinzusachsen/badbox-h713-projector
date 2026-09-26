package com.android.settingslib.system;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v17.leanback.app.GuidedStepFragment;
import android.support.v17.leanback.widget.GuidanceStylist;
import android.support.v17.leanback.widget.GuidedAction;
import android.text.TextUtils;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppPicker extends Activity {
    public static final String EXTRA_DEBUGGABLE = "com.android.settings.extra.DEBUGGABLE";
    public static final String EXTRA_REQUESTIING_PERMISSION = "com.android.settings.extra.REQUESTIING_PERMISSION";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            GuidedStepFragment.addAsRoot(this, AppPickerFragment.newInstance(getIntent().getStringExtra(EXTRA_REQUESTIING_PERMISSION), Boolean.valueOf(getIntent().getBooleanExtra(EXTRA_DEBUGGABLE, false)).booleanValue()), R.id.content);
        }
    }

    public static class AppPickerFragment extends GuidedStepFragment {
        private boolean mDebuggableOnly;
        private String mPermissionName;

        public static AppPickerFragment newInstance(String str, boolean z) {
            AppPickerFragment appPickerFragment = new AppPickerFragment();
            Bundle bundle = new Bundle(2);
            bundle.putString(AppPicker.EXTRA_REQUESTIING_PERMISSION, str);
            bundle.putBoolean(AppPicker.EXTRA_DEBUGGABLE, z);
            appPickerFragment.setArguments(bundle);
            return appPickerFragment;
        }

        @Override // android.support.v17.leanback.app.GuidedStepFragment, android.app.Fragment
        public void onCreate(Bundle bundle) {
            this.mPermissionName = getArguments().getString(AppPicker.EXTRA_REQUESTIING_PERMISSION);
            this.mDebuggableOnly = getArguments().getBoolean(AppPicker.EXTRA_DEBUGGABLE);
            super.onCreate(bundle);
        }

        @Override // android.support.v17.leanback.app.GuidedStepFragment
        @NonNull
        public GuidanceStylist.Guidance onCreateGuidance(Bundle bundle) {
            return new GuidanceStylist.Guidance(getString(com.android.settingslib.R.string.choose_application), null, null, getContext().getDrawable(com.android.settingslib.R.drawable.ic_adb_132dp));
        }

        @Override // android.support.v17.leanback.app.GuidedStepFragment
        public void onCreateActions(@NonNull List<GuidedAction> list, Bundle bundle) {
            boolean z;
            List<ApplicationInfo> installedApplications = getActivity().getPackageManager().getInstalledApplications(0);
            PackageManager packageManager = getActivity().getPackageManager();
            for (ApplicationInfo applicationInfo : installedApplications) {
                if (applicationInfo.uid != 1000 && (!this.mDebuggableOnly || (applicationInfo.flags & 2) != 0 || !"user".equals(Build.TYPE))) {
                    if (this.mPermissionName != null) {
                        try {
                            PackageInfo packageInfo = packageManager.getPackageInfo(applicationInfo.packageName, 4096);
                            if (packageInfo.requestedPermissions != null) {
                                String[] strArr = packageInfo.requestedPermissions;
                                int length = strArr.length;
                                int i = 0;
                                while (true) {
                                    if (i >= length) {
                                        z = false;
                                        break;
                                    } else {
                                        if (strArr[i].equals(this.mPermissionName)) {
                                            z = true;
                                            break;
                                        }
                                        i++;
                                    }
                                }
                                if (!z) {
                                }
                            }
                        } catch (PackageManager.NameNotFoundException unused) {
                        }
                    }
                    list.add(new AppAction(applicationInfo.packageName, applicationInfo.loadLabel(packageManager).toString(), applicationInfo.loadIcon(packageManager)));
                }
            }
            Collections.sort(list, new Comparator<GuidedAction>() { // from class: com.android.settingslib.system.AppPicker.AppPickerFragment.1
                private final Collator mCollator = Collator.getInstance();

                @Override // java.util.Comparator
                public int compare(GuidedAction guidedAction, GuidedAction guidedAction2) {
                    return this.mCollator.compare(guidedAction.getTitle(), guidedAction2.getTitle());
                }
            });
            list.add(0, new AppAction(null, getString(com.android.settingslib.R.string.no_application), null));
        }

        @Override // android.support.v17.leanback.app.GuidedStepFragment
        public void onGuidedActionClicked(GuidedAction guidedAction) {
            Intent intent = new Intent();
            String packageName = ((AppAction) guidedAction).getPackageName();
            if (!TextUtils.isEmpty(packageName)) {
                intent.setAction(packageName);
            }
            getActivity().setResult(-1, intent);
            getActivity().finish();
        }

        private static class AppAction extends GuidedAction {
            private final String mPackageName;

            public AppAction(String str, String str2, Drawable drawable) {
                this.mPackageName = str;
                setTitle(str2);
                setDescription(str);
                setIcon(drawable);
                setEnabled(true);
                setFocusable(true);
            }

            public String getPackageName() {
                return this.mPackageName;
            }
        }
    }
}
