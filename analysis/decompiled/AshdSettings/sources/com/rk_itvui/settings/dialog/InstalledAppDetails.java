package com.rk_itvui.settings.dialog;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.admin.DeviceAdminInfo;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDataObserver;
import android.content.pm.IPackageMoveObserver;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.view.View;
import android.widget.AppSecurityPermissions;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenActivity;
import com.rk_itvui.settings.MyAppSecurityPermissions;
import com.rk_itvui.settings.Utils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class InstalledAppDetails extends FullScreenActivity implements View.OnClickListener, ApplicationsState.Callbacks {
    private static final int CLEAR_CACHE = 3;
    private static final int CLEAR_USER_DATA = 1;
    private static final int DLG_APP_NOT_FOUND = 3;
    private static final int DLG_BASE = 0;
    private static final int DLG_CANNOT_CLEAR_DATA = 4;
    private static final int DLG_CLEAR_DATA = 1;
    private static final int DLG_FACTORY_RESET = 2;
    private static final int DLG_FORCE_STOP = 5;
    private static final int DLG_MOVE_FAILED = 6;
    private static final int OP_FAILED = 2;
    private static final int OP_SUCCESSFUL = 1;
    private static final int PACKAGE_MOVE = 4;
    private static final int SIZE_INVALID = -1;
    static final boolean SUPPORT_DISABLE_APPS = false;
    private static final String TAG = "InstalledAppDetails";
    private static final boolean localLOGV = false;
    private Button mActivitiesButton;
    private ApplicationsState.AppEntry mAppEntry;
    private TextView mAppSize;
    private TextView mAppVersion;
    private TextView mCacheSize;
    private CanBeOnSdCardChecker mCanBeOnSdCardChecker;
    private Button mClearCacheButton;
    private ClearCacheObserver mClearCacheObserver;
    private Button mClearDataButton;
    private ClearUserDataObserver mClearDataObserver;
    private CharSequence mComputingStr;
    private TextView mDataSize;
    DeviceAdminInfo mDeviceAdmin;
    private Button mForceStopButton;
    private CharSequence mInvalidSizeStr;
    private Button mMoveAppButton;
    private int mMoveErrorCode;
    private PackageInfo mPackageInfo;
    private PackageMoveObserver mPackageMoveObserver;
    private PackageManager mPm;
    private ApplicationsState mState;
    private TextView mTotalSize;
    private Button mUninstallButton;
    private boolean mMoveInProgress = false;
    private boolean mUpdatedSysApp = false;
    private boolean mCanClearData = true;
    private boolean mHaveSizes = false;
    private long mLastCodeSize = -1;
    private long mLastDataSize = -1;
    private long mLastCacheSize = -1;
    private long mLastTotalSize = -1;
    private Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (InstalledAppDetails.this.isFinishing()) {
                return;
            }
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case 3:
                        InstalledAppDetails.this.mState.requestSize(InstalledAppDetails.this.mAppEntry.info.packageName, InstalledAppDetails.this.mAppEntry.info);
                        break;
                    case 4:
                        InstalledAppDetails.this.processMoveMsg(message);
                        break;
                }
                return;
            }
            InstalledAppDetails.this.processClearMsg(message);
        }
    };
    private final BroadcastReceiver mCheckKillProcessesReceiver = new BroadcastReceiver() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.8
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            InstalledAppDetails.this.mForceStopButton.setEnabled(getResultCode() != 0);
            InstalledAppDetails.this.mForceStopButton.setOnClickListener(InstalledAppDetails.this);
        }
    };

    @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
    public void onAllSizesComputed() {
    }

    @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
    public void onPackageIconChanged() {
    }

    @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
    public void onRebuildComplete(ArrayList<ApplicationsState.AppEntry> arrayList) {
    }

    @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
    public void onRunningStateChanged(boolean z) {
    }

    class ClearUserDataObserver extends IPackageDataObserver.Stub {
        ClearUserDataObserver() {
        }

        public void onRemoveCompleted(String str, boolean z) {
            Message messageObtainMessage = InstalledAppDetails.this.mHandler.obtainMessage(1);
            messageObtainMessage.arg1 = z ? 1 : 2;
            InstalledAppDetails.this.mHandler.sendMessage(messageObtainMessage);
        }
    }

    class ClearCacheObserver extends IPackageDataObserver.Stub {
        public void onCreated(int i, Bundle bundle) {
        }

        public void onStatusChanged(int i, int i2, long j) {
        }

        ClearCacheObserver() {
        }

        public void onRemoveCompleted(String str, boolean z) {
            Message messageObtainMessage = InstalledAppDetails.this.mHandler.obtainMessage(3);
            messageObtainMessage.arg1 = z ? 1 : 2;
            InstalledAppDetails.this.mHandler.sendMessage(messageObtainMessage);
        }
    }

    class PackageMoveObserver extends IPackageMoveObserver.Stub {
        public void onCreated(int i, Bundle bundle) {
        }

        public void onStatusChanged(int i, int i2, long j) {
        }

        PackageMoveObserver() {
        }

        public void packageMoved(String str, int i) throws RemoteException {
            Message messageObtainMessage = InstalledAppDetails.this.mHandler.obtainMessage(4);
            messageObtainMessage.arg1 = i;
            InstalledAppDetails.this.mHandler.sendMessage(messageObtainMessage);
        }
    }

    private String getSizeStr(long j) {
        if (j == -1) {
            return this.mInvalidSizeStr.toString();
        }
        return Utils.formatSize(j, "InstalledAppDetail");
    }

    private void initDataButtons() {
        if ((this.mAppEntry.info.flags & 65) == 1) {
            this.mClearDataButton.setText(R.string.clear_user_data_text);
            this.mClearDataButton.setEnabled(false);
            this.mCanClearData = false;
        } else {
            if (this.mAppEntry.info.manageSpaceActivityName != null) {
                this.mClearDataButton.setText(R.string.manage_space_text);
            } else {
                this.mClearDataButton.setText(R.string.clear_user_data_text);
            }
            this.mClearDataButton.setOnClickListener(this);
        }
    }

    private CharSequence getMoveErrMsg(int i) {
        switch (i) {
            case -6:
                return "";
            case -5:
                return getString(R.string.invalid_location);
            case -4:
                return getString(R.string.app_forward_locked);
            case -3:
                return getString(R.string.system_package);
            case -2:
                return getString(R.string.does_not_exist);
            case -1:
                return getString(R.string.insufficient_storage);
            default:
                return "";
        }
    }

    private void initMoveButton() {
        boolean z;
        if (this.mPackageInfo == null && this.mAppEntry != null) {
            this.mMoveAppButton.setText(R.string.move_app);
            z = true;
        } else if ((this.mAppEntry.info.flags & 262144) != 0) {
            this.mMoveAppButton.setText(R.string.move_app_to_internal);
            z = false;
        } else {
            this.mMoveAppButton.setText(R.string.move_app_to_sdcard);
            this.mCanBeOnSdCardChecker.init();
            z = !this.mCanBeOnSdCardChecker.check(this.mAppEntry.info);
        }
        if (z) {
            this.mMoveAppButton.setEnabled(false);
        } else {
            this.mMoveAppButton.setOnClickListener(this);
            this.mMoveAppButton.setEnabled(true);
        }
        this.mMoveAppButton.setVisibility(4);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0038  */
    /* JADX WARN: Code duplicated, block: B:18:? A[RETURN, SYNTHETIC] */
    private void initUninstallButtons() {
        boolean z = false;
        this.mUpdatedSysApp = (this.mAppEntry.info.flags & 128) != 0;
        if (this.mUpdatedSysApp) {
            this.mUninstallButton.setText(R.string.app_factory_reset);
        } else {
            if ((this.mAppEntry.info.flags & 1) == 0) {
                this.mUninstallButton.setText(R.string.uninstall_text);
            }
            this.mUninstallButton.setEnabled(z);
            if (z) {
                this.mUninstallButton.setOnClickListener(this);
            }
        }
        z = true;
        this.mUninstallButton.setEnabled(z);
        if (z) {
            this.mUninstallButton.setOnClickListener(this);
        }
    }

    @Override // com.rk_itvui.settings.FullScreenActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mState = ApplicationsState.getInstance(getApplication());
        this.mPm = getPackageManager();
        this.mCanBeOnSdCardChecker = new CanBeOnSdCardChecker();
        setContentView(R.layout.installed_app_details);
        this.mComputingStr = getText(R.string.computing_size);
        this.mTotalSize = (TextView) findViewById(R.id.total_size_text);
        this.mAppSize = (TextView) findViewById(R.id.application_size_text);
        this.mDataSize = (TextView) findViewById(R.id.data_size_text);
        View viewFindViewById = findViewById(R.id.control_buttons_panel);
        this.mForceStopButton = (Button) viewFindViewById.findViewById(R.id.left_button);
        this.mForceStopButton.setText(R.string.force_stop);
        this.mUninstallButton = (Button) viewFindViewById.findViewById(R.id.right_button);
        this.mForceStopButton.setEnabled(false);
        View viewFindViewById2 = findViewById(R.id.data_buttons_panel);
        this.mClearDataButton = (Button) viewFindViewById2.findViewById(R.id.left_button);
        this.mMoveAppButton = (Button) viewFindViewById2.findViewById(R.id.right_button);
        this.mCacheSize = (TextView) findViewById(R.id.cache_size_text);
        this.mClearCacheButton = (Button) findViewById(R.id.clear_cache_button);
        this.mActivitiesButton = (Button) findViewById(R.id.clear_activities_button);
    }

    private void InitDeviceAdmin() {
        String schemeSpecificPart = getIntent().getData().getSchemeSpecificPart();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(schemeSpecificPart);
        List<ResolveInfo> listQueryIntentActivities = getPackageManager().queryIntentActivities(intent, 0);
        if (listQueryIntentActivities == null || listQueryIntentActivities.size() == 0) {
            Log.w(TAG, "no  launcher activity for " + schemeSpecificPart);
            finish();
            return;
        }
        ComponentName componentName = new ComponentName(listQueryIntentActivities.get(0).activityInfo.packageName, listQueryIntentActivities.get(0).activityInfo.name);
        try {
            ActivityInfo activityInfo = getPackageManager().getActivityInfo(componentName, 128);
            ResolveInfo resolveInfo = new ResolveInfo();
            resolveInfo.activityInfo = activityInfo;
            try {
                this.mDeviceAdmin = new DeviceAdminInfo(this, resolveInfo);
            } catch (IOException e) {
                Log.w(TAG, "Unable to retrieve device policy " + componentName, e);
                finish();
            } catch (XmlPullParserException e2) {
                Log.w(TAG, "Unable to retrieve device policy " + componentName, e2);
                finish();
            }
        } catch (PackageManager.NameNotFoundException e3) {
            Log.w(TAG, "Unable to retrieve device policy " + componentName, e3);
            finish();
        }
    }

    private void setAppLabelAndIcon(PackageInfo packageInfo) {
        View viewFindViewById = findViewById(R.id.app_snippet);
        ImageView imageView = (ImageView) viewFindViewById.findViewById(R.id.app_icon);
        this.mState.ensureIcon(this.mAppEntry);
        imageView.setImageDrawable(this.mAppEntry.icon);
        ((TextView) viewFindViewById.findViewById(R.id.app_name)).setText(this.mAppEntry.label);
        this.mAppVersion = (TextView) viewFindViewById.findViewById(R.id.app_size);
        if (packageInfo != null && packageInfo.versionName != null) {
            this.mAppVersion.setVisibility(0);
            this.mAppVersion.setText(getString(R.string.version_text, new Object[]{String.valueOf(packageInfo.versionName)}));
        } else {
            this.mAppVersion.setVisibility(4);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.mState.resume(this);
        if (refreshUi()) {
            return;
        }
        setIntentAndFinish(true, true);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.mState.pause();
    }

    @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
    public void onPackageListChanged() {
        refreshUi();
    }

    @Override // com.rk_itvui.settings.dialog.ApplicationsState.Callbacks
    public void onPackageSizeChanged(String str) {
        if (str.equals(this.mAppEntry.info.packageName)) {
            refreshSizeInfo();
        }
    }

    private boolean refreshUi() {
        if (this.mMoveInProgress) {
            return true;
        }
        String schemeSpecificPart = getIntent().getData().getSchemeSpecificPart();
        this.mAppEntry = this.mState.getEntry(schemeSpecificPart);
        if (this.mAppEntry == null) {
            return false;
        }
        try {
            this.mPackageInfo = this.mPm.getPackageInfo(this.mAppEntry.info.packageName, 8768);
            ArrayList arrayList = new ArrayList();
            this.mPm.getPreferredActivities(new ArrayList(), arrayList, schemeSpecificPart);
            TextView textView = (TextView) findViewById(R.id.auto_launch);
            if (arrayList.size() <= 0) {
                textView.setText(R.string.auto_launch_disable_text);
                this.mActivitiesButton.setEnabled(false);
            } else {
                textView.setText(R.string.auto_launch_enable_text);
                this.mActivitiesButton.setEnabled(true);
                this.mActivitiesButton.setOnClickListener(this);
            }
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.permissions_section);
            MyAppSecurityPermissions myAppSecurityPermissions = new MyAppSecurityPermissions(this, schemeSpecificPart);
            if (myAppSecurityPermissions.getPermissionCount() > 0) {
                linearLayout.setVisibility(0);
                LinearLayout linearLayout2 = (LinearLayout) linearLayout.findViewById(R.id.security_settings_list);
                linearLayout2.removeAllViews();
                for (MyAppSecurityPermissions.MyPermissionInfo myPermissionInfo : myAppSecurityPermissions.getmPermsList()) {
                    linearLayout2.addView(AppSecurityPermissions.getPermissionItemView(this, myPermissionInfo.mLabel, myPermissionInfo.loadDescription(getPackageManager()), true));
                }
            } else {
                linearLayout.setVisibility(8);
            }
            checkForceStop();
            setAppLabelAndIcon(this.mPackageInfo);
            refreshButtons();
            refreshSizeInfo();
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Exception when retrieving package:" + this.mAppEntry.info.packageName, e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setIntentAndFinish(boolean z, boolean z2) {
        Intent intent = new Intent();
        intent.putExtra(ManageApplications.APP_CHG, z2);
        setResult(-1, intent);
        if (z) {
            finish();
        }
    }

    private void refreshSizeInfo() {
        if (this.mAppEntry.size == -2 || this.mAppEntry.size == -1) {
            this.mLastTotalSize = -1L;
            this.mLastCacheSize = -1L;
            this.mLastDataSize = -1L;
            this.mLastCodeSize = -1L;
            if (!this.mHaveSizes) {
                this.mAppSize.setText(this.mComputingStr);
                this.mDataSize.setText(this.mComputingStr);
                this.mCacheSize.setText(this.mComputingStr);
                this.mTotalSize.setText(this.mComputingStr);
            }
            this.mClearDataButton.setEnabled(false);
            this.mClearCacheButton.setEnabled(false);
            return;
        }
        this.mHaveSizes = true;
        if (this.mLastCodeSize != this.mAppEntry.codeSize) {
            this.mLastCodeSize = this.mAppEntry.codeSize;
            this.mAppSize.setText(getSizeStr(this.mAppEntry.codeSize));
        }
        if (this.mLastDataSize != this.mAppEntry.dataSize) {
            this.mLastDataSize = this.mAppEntry.dataSize;
            this.mDataSize.setText(getSizeStr(this.mAppEntry.dataSize));
        }
        if (this.mLastCacheSize != this.mAppEntry.cacheSize) {
            this.mLastCacheSize = this.mAppEntry.cacheSize;
            this.mCacheSize.setText(getSizeStr(this.mAppEntry.cacheSize));
        }
        if (this.mLastTotalSize != this.mAppEntry.size) {
            this.mLastTotalSize = this.mAppEntry.size;
            this.mTotalSize.setText(getSizeStr(this.mAppEntry.size));
        }
        if (this.mAppEntry.dataSize <= 0 || !this.mCanClearData) {
            this.mClearDataButton.setEnabled(false);
        } else {
            this.mClearDataButton.setEnabled(true);
            this.mClearDataButton.setOnClickListener(this);
        }
        if (this.mAppEntry.cacheSize <= 0) {
            this.mClearCacheButton.setEnabled(false);
        } else {
            this.mClearCacheButton.setEnabled(true);
            this.mClearCacheButton.setOnClickListener(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processClearMsg(Message message) {
        int i = message.arg1;
        String str = this.mAppEntry.info.packageName;
        this.mClearDataButton.setText(R.string.clear_user_data_text);
        if (i == 1) {
            Log.i(TAG, "Cleared user data for package : " + str);
            this.mState.requestSize(this.mAppEntry.info.packageName, this.mAppEntry.info);
        } else {
            this.mClearDataButton.setEnabled(true);
        }
        checkForceStop();
    }

    private void refreshButtons() {
        if (!this.mMoveInProgress) {
            initUninstallButtons();
            initDataButtons();
            initMoveButton();
        } else {
            this.mMoveAppButton.setText(R.string.moving);
            this.mMoveAppButton.setEnabled(false);
            this.mUninstallButton.setEnabled(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMoveMsg(Message message) {
        int i = message.arg1;
        String str = this.mAppEntry.info.packageName;
        this.mMoveInProgress = false;
        if (i == -100) {
            Log.i(TAG, "Moved resources for " + str);
            this.mState.requestSize(this.mAppEntry.info.packageName, this.mAppEntry.info);
        } else {
            this.mMoveErrorCode = i;
            showDialogInner(6);
        }
        refreshUi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initiateClearUserData() {
        this.mClearDataButton.setEnabled(false);
        String str = this.mAppEntry.info.packageName;
        Log.i(TAG, "Clearing user data for package : " + str);
        if (this.mClearDataObserver == null) {
            this.mClearDataObserver = new ClearUserDataObserver();
        }
        if (!((ActivityManager) getSystemService("activity")).clearApplicationUserData(str, this.mClearDataObserver)) {
            Log.i(TAG, "Couldnt clear application user data for package:" + str);
            showDialogInner(4);
            return;
        }
        this.mClearDataButton.setText(R.string.recompute_size);
    }

    private void showDialogInner(int i) {
        showDialog(i);
    }

    @Override // android.app.Activity
    public Dialog onCreateDialog(int i, Bundle bundle) {
        final AlertDialog alertDialogCreate = null;
        switch (i) {
            case 1:
                alertDialogCreate = new AlertDialog.Builder(this).setTitle(getString(R.string.clear_data_dlg_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(getString(R.string.clear_data_dlg_text)).setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        InstalledAppDetails.this.initiateClearUserData();
                    }
                }).setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null).create();
                break;
            case 2:
                alertDialogCreate = new AlertDialog.Builder(this).setTitle(getString(R.string.app_factory_reset_dlg_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(getString(R.string.app_factory_reset_dlg_text)).setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.3
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        Log.d(InstalledAppDetails.TAG, "--onClick-Uninstall");
                        InstalledAppDetails.this.uninstallPkg(InstalledAppDetails.this.mAppEntry.info.packageName);
                    }
                }).setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null).create();
                break;
            case 3:
                alertDialogCreate = new AlertDialog.Builder(this).setTitle(getString(R.string.app_not_found_dlg_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(getString(R.string.app_not_found_dlg_title)).setNeutralButton(getString(R.string.dlg_ok), new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.4
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        InstalledAppDetails.this.setIntentAndFinish(true, true);
                    }
                }).create();
                break;
            case 4:
                alertDialogCreate = new AlertDialog.Builder(this).setTitle(getString(R.string.clear_failed_dlg_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(getString(R.string.clear_failed_dlg_text)).setNeutralButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.5
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        InstalledAppDetails.this.mClearDataButton.setEnabled(false);
                        InstalledAppDetails.this.setIntentAndFinish(false, false);
                    }
                }).create();
                break;
            case 5:
                alertDialogCreate = new AlertDialog.Builder(this).setTitle(getString(R.string.force_stop_dlg_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(getString(R.string.force_stop_dlg_text)).setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.6
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i2) {
                        InstalledAppDetails.this.forceStopPackage(InstalledAppDetails.this.mAppEntry.info.packageName);
                    }
                }).setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null).create();
                break;
            case 6:
                alertDialogCreate = new AlertDialog.Builder(this).setTitle(getString(R.string.move_app_failed_dlg_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(getString(R.string.move_app_failed_dlg_text, new Object[]{getMoveErrMsg(this.mMoveErrorCode)})).setNeutralButton(R.string.dlg_ok, (DialogInterface.OnClickListener) null).create();
                break;
        }
        if (alertDialogCreate != null) {
            alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.rk_itvui.settings.dialog.InstalledAppDetails.7
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    Utils.fixButtonStyle(alertDialogCreate);
                }
            });
        }
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uninstallPkg(String str) {
        Log.d(TAG, "---ok-uninstallPkg-new-activity?");
        startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + str)));
        setIntentAndFinish(true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceStopPackage(String str) {
        ((ActivityManager) getSystemService("activity")).forceStopPackage(str);
        checkForceStop();
    }

    private void checkForceStop() {
        Intent intent = new Intent("android.intent.action.QUERY_PACKAGE_RESTART", Uri.fromParts("package", this.mAppEntry.info.packageName, null));
        intent.putExtra("android.intent.extra.PACKAGES", new String[]{this.mAppEntry.info.packageName});
        intent.putExtra("android.intent.extra.UID", this.mAppEntry.info.uid);
        intent.putExtra("android.intent.extra.user_handle", UserHandle.getUserId(this.mAppEntry.info.uid));
        sendOrderedBroadcastAsUser(intent, UserHandle.CURRENT, null, this.mCheckKillProcessesReceiver, null, 0, null, null);
    }

    static class DisableChanger extends AsyncTask<Object, Object, Object> {
        final WeakReference<InstalledAppDetails> mActivity;
        final ApplicationInfo mInfo;
        final PackageManager mPm;
        final int mState;

        DisableChanger(InstalledAppDetails installedAppDetails, ApplicationInfo applicationInfo, int i) {
            this.mPm = installedAppDetails.mPm;
            this.mActivity = new WeakReference<>(installedAppDetails);
            this.mInfo = applicationInfo;
            this.mState = i;
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object... objArr) {
            this.mPm.setApplicationEnabledSetting(this.mInfo.packageName, this.mState, 0);
            return null;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        String str = this.mAppEntry.info.packageName;
        if (view == this.mUninstallButton) {
            if (this.mUpdatedSysApp) {
                showDialogInner(2);
                return;
            } else if ((this.mAppEntry.info.flags & 1) != 0) {
                new DisableChanger(this, this.mAppEntry.info, this.mAppEntry.info.enabled ? 2 : 0).execute(null);
                return;
            } else {
                uninstallPkg(str);
                return;
            }
        }
        if (view == this.mActivitiesButton) {
            this.mPm.clearPackagePreferredActivities(str);
            this.mActivitiesButton.setEnabled(false);
            return;
        }
        if (view == this.mClearDataButton) {
            if (this.mAppEntry.info.manageSpaceActivityName != null) {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setClassName(this.mAppEntry.info.packageName, this.mAppEntry.info.manageSpaceActivityName);
                startActivityForResult(intent, -1);
                return;
            }
            showDialogInner(1);
            return;
        }
        if (view == this.mClearCacheButton) {
            if (this.mClearCacheObserver == null) {
                this.mClearCacheObserver = new ClearCacheObserver();
            }
            this.mPm.deleteApplicationCacheFiles(str, this.mClearCacheObserver);
        } else {
            if (view == this.mForceStopButton) {
                showDialogInner(5);
                return;
            }
            if (view == this.mMoveAppButton) {
                if (this.mPackageMoveObserver == null) {
                    this.mPackageMoveObserver = new PackageMoveObserver();
                }
                int i = this.mAppEntry.info.flags & 262144;
                this.mMoveInProgress = true;
                refreshButtons();
            }
        }
    }
}
