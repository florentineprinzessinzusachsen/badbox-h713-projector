package com.rk_itvui.settings.dialog;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ApplicationErrorReport;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.hardware.audio.common.V2_0.AudioFormat;
import android.os.Bundle;
import android.os.Debug;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.ashd.settings.R;
import com.rk_itvui.settings.FullScreenAlertActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class RunningServiceDetails extends FullScreenAlertActivity implements RunningState.OnRefreshUiListener {
    static final int DIALOG_CONFIRM_STOP = 1;
    static final String KEY_BACKGROUND = "background";
    static final String KEY_PROCESS = "process";
    static final String KEY_UID = "uid";
    static final String TAG = "RunningServicesDetails";
    ViewGroup mAllDetails;
    ActivityManager mAm;
    boolean mHaveData;
    LayoutInflater mInflater;
    RunningState.MergedItem mMergedItem;
    int mNumProcesses;
    int mNumServices;
    String mProcessName;
    TextView mProcessesHeader;
    TextView mServicesHeader;
    boolean mShowBackground;
    ViewGroup mSnippet;
    RunningProcessesView.ActiveItem mSnippetActiveItem;
    RunningProcessesView.ViewHolder mSnippetViewHolder;
    RunningState mState;
    int mUid;
    final ArrayList<ActiveDetail> mActiveDetails = new ArrayList<>();
    StringBuilder mBuilder = new StringBuilder(128);

    class ActiveDetail implements View.OnClickListener {
        RunningProcessesView.ActiveItem mActiveItem;
        ComponentName mInstaller;
        PendingIntent mManageIntent;
        Button mReportButton;
        View mRootView;
        RunningState.ServiceItem mServiceItem;
        Button mStopButton;
        RunningProcessesView.ViewHolder mViewHolder;

        ActiveDetail() {
        }

        void stopActiveService(boolean z) {
            RunningState.ServiceItem serviceItem = this.mServiceItem;
            if (!z && (serviceItem.mServiceInfo.applicationInfo.flags & 1) != 0) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("comp", serviceItem.mRunningService.service);
                RunningServiceDetails.this.removeDialog(1);
                RunningServiceDetails.this.showDialog(1, bundle);
                return;
            }
            RunningServiceDetails.this.stopService(new Intent().setComponent(serviceItem.mRunningService.service));
            if (RunningServiceDetails.this.mMergedItem == null) {
                RunningServiceDetails.this.mState.updateNow();
                RunningServiceDetails.this.finish();
            } else if (!RunningServiceDetails.this.mShowBackground && RunningServiceDetails.this.mMergedItem.mServices.size() <= 1) {
                RunningServiceDetails.this.mState.updateNow();
                RunningServiceDetails.this.finish();
            } else {
                RunningServiceDetails.this.mState.updateNow();
            }
        }

        /* JADX WARN: Code duplicated, block: B:75:0x013e A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:85:0x0097 A[EXC_TOP_SPLITTER, PHI: r6
          0x0097: PHI (r6v6 java.io.FileOutputStream) = (r6v5 java.io.FileOutputStream), (r6v9 java.io.FileOutputStream) binds: [B:25:0x00b9, B:15:0x0095] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) throws Throwable {
            FileOutputStream fileOutputStream;
            if (view == this.mReportButton) {
                ApplicationErrorReport applicationErrorReport = new ApplicationErrorReport();
                applicationErrorReport.type = 5;
                applicationErrorReport.packageName = this.mServiceItem.mServiceInfo.packageName;
                applicationErrorReport.installerPackageName = this.mInstaller.getPackageName();
                applicationErrorReport.processName = this.mServiceItem.mRunningService.process;
                applicationErrorReport.time = System.currentTimeMillis();
                applicationErrorReport.systemApp = (this.mServiceItem.mServiceInfo.applicationInfo.flags & 1) != 0;
                ApplicationErrorReport.RunningServiceInfo runningServiceInfo = new ApplicationErrorReport.RunningServiceInfo();
                if (this.mActiveItem.mFirstRunTime >= 0) {
                    runningServiceInfo.durationMillis = SystemClock.elapsedRealtime() - this.mActiveItem.mFirstRunTime;
                } else {
                    runningServiceInfo.durationMillis = -1L;
                }
                ComponentName componentName = new ComponentName(this.mServiceItem.mServiceInfo.packageName, this.mServiceItem.mServiceInfo.name);
                File fileStreamPath = RunningServiceDetails.this.getFileStreamPath("service_dump.txt");
                FileInputStream fileInputStream = null;
                try {
                    try {
                        fileOutputStream = new FileOutputStream(fileStreamPath);
                        try {
                            Debug.dumpService("activity", fileOutputStream.getFD(), new String[]{"-a", "service", componentName.flattenToString()});
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException unused) {
                                }
                            }
                        } catch (IOException e) {
                            e = e;
                            Log.w(RunningServiceDetails.TAG, "Can't dump service: " + componentName, e);
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = null;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
                try {
                    try {
                        try {
                            FileInputStream fileInputStream2 = new FileInputStream(fileStreamPath);
                            try {
                                byte[] bArr = new byte[(int) fileStreamPath.length()];
                                fileInputStream2.read(bArr);
                                runningServiceInfo.serviceDetails = new String(bArr);
                                if (fileInputStream2 != null) {
                                    fileInputStream2.close();
                                }
                            } catch (IOException e3) {
                                e = e3;
                                fileInputStream = fileInputStream2;
                                Log.w(RunningServiceDetails.TAG, "Can't read service dump: " + componentName, e);
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                fileStreamPath.delete();
                                Log.i(RunningServiceDetails.TAG, "Details: " + runningServiceInfo.serviceDetails);
                                applicationErrorReport.runningServiceInfo = runningServiceInfo;
                                Intent intent = new Intent("android.intent.action.APP_ERROR");
                                intent.setComponent(this.mInstaller);
                                intent.putExtra("android.intent.extra.BUG_REPORT", applicationErrorReport);
                                intent.addFlags(AudioFormat.EVRC);
                                RunningServiceDetails.this.startActivity(intent);
                                return;
                            } catch (Throwable th3) {
                                th = th3;
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException unused3) {
                                    }
                                }
                                throw th;
                            }
                        } catch (IOException e4) {
                            e = e4;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } catch (IOException unused4) {
                }
                fileStreamPath.delete();
                Log.i(RunningServiceDetails.TAG, "Details: " + runningServiceInfo.serviceDetails);
                applicationErrorReport.runningServiceInfo = runningServiceInfo;
                Intent intent2 = new Intent("android.intent.action.APP_ERROR");
                intent2.setComponent(this.mInstaller);
                intent2.putExtra("android.intent.extra.BUG_REPORT", applicationErrorReport);
                intent2.addFlags(AudioFormat.EVRC);
                RunningServiceDetails.this.startActivity(intent2);
                return;
            }
            if (this.mManageIntent != null) {
                try {
                    RunningServiceDetails.this.startIntentSender(this.mManageIntent.getIntentSender(), null, 268959744, 524288, 0);
                    return;
                } catch (ActivityNotFoundException e5) {
                    Log.w(RunningServiceDetails.TAG, e5);
                    return;
                } catch (IntentSender.SendIntentException e6) {
                    Log.w(RunningServiceDetails.TAG, e6);
                    return;
                } catch (IllegalArgumentException e7) {
                    Log.w(RunningServiceDetails.TAG, e7);
                    return;
                }
            }
            if (this.mServiceItem != null) {
                stopActiveService(false);
            } else if (this.mActiveItem.mItem.mBackground) {
                RunningServiceDetails.this.mAm.killBackgroundProcesses(this.mActiveItem.mItem.mPackageInfo.packageName);
                RunningServiceDetails.this.finish();
            } else {
                RunningServiceDetails.this.mAm.forceStopPackage(this.mActiveItem.mItem.mPackageInfo.packageName);
                RunningServiceDetails.this.finish();
            }
        }
    }

    boolean findMergedItem() {
        RunningState.MergedItem mergedItem;
        ArrayList<RunningState.MergedItem> currentBackgroundItems = this.mShowBackground ? this.mState.getCurrentBackgroundItems() : this.mState.getCurrentMergedItems();
        if (currentBackgroundItems == null) {
            mergedItem = null;
            break;
        }
        int i = 0;
        while (true) {
            if (i >= currentBackgroundItems.size()) {
                mergedItem = null;
                break;
            }
            mergedItem = currentBackgroundItems.get(i);
            if (mergedItem.mProcess.mUid == this.mUid && mergedItem.mProcess.mProcessName.equals(this.mProcessName)) {
                break;
            }
            i++;
        }
        if (this.mMergedItem == mergedItem) {
            return false;
        }
        this.mMergedItem = mergedItem;
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void addServiceDetailsView(RunningState.ServiceItem serviceItem, RunningState.MergedItem mergedItem) {
        if (this.mNumServices == 0) {
            this.mServicesHeader = (TextView) this.mInflater.inflate(R.layout.separator_label, this.mAllDetails, false);
            this.mServicesHeader.setText(R.string.runningservicedetails_services_title);
            this.mAllDetails.addView(this.mServicesHeader);
        }
        this.mNumServices++;
        RunningState.BaseItem baseItem = serviceItem != null ? serviceItem : mergedItem;
        ActiveDetail activeDetail = new ActiveDetail();
        View viewInflate = this.mInflater.inflate(R.layout.running_service_details_service, this.mAllDetails, false);
        this.mAllDetails.addView(viewInflate);
        activeDetail.mRootView = viewInflate;
        activeDetail.mServiceItem = serviceItem;
        activeDetail.mViewHolder = new RunningProcessesView.ViewHolder(viewInflate);
        activeDetail.mActiveItem = activeDetail.mViewHolder.bind(this.mState, baseItem, this.mBuilder);
        if (serviceItem != null && serviceItem.mRunningService.clientLabel != 0) {
            activeDetail.mManageIntent = this.mAm.getRunningServiceControlPanel(serviceItem.mRunningService.service);
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.comp_description);
        if (serviceItem != null && serviceItem.mServiceInfo.descriptionRes != 0) {
            textView.setText(getPackageManager().getText(serviceItem.mServiceInfo.packageName, serviceItem.mServiceInfo.descriptionRes, serviceItem.mServiceInfo.applicationInfo));
        } else if (mergedItem.mBackground) {
            textView.setText(R.string.background_process_stop_description);
        } else if (activeDetail.mManageIntent != null) {
            try {
                textView.setText(getString(R.string.service_manage_description, new Object[]{getPackageManager().getResourcesForApplication(serviceItem.mRunningService.clientPackage).getString(serviceItem.mRunningService.clientLabel)}));
            } catch (PackageManager.NameNotFoundException unused) {
            }
        } else {
            textView.setText(getText(serviceItem != null ? R.string.service_stop_description : R.string.heavy_weight_stop_description));
        }
        activeDetail.mStopButton = (Button) viewInflate.findViewById(R.id.left_button);
        activeDetail.mStopButton.setOnClickListener(activeDetail);
        activeDetail.mStopButton.setText(getText(activeDetail.mManageIntent != null ? R.string.service_manage : R.string.service_stop));
        activeDetail.mReportButton = (Button) viewInflate.findViewById(R.id.right_button);
        activeDetail.mReportButton.setOnClickListener(activeDetail);
        activeDetail.mReportButton.setText(android.R.string.etws_primary_default_message_test);
        if (Settings.Global.getInt(getContentResolver(), "send_action_app_error", 0) != 0 && serviceItem != null) {
            activeDetail.mInstaller = ApplicationErrorReport.getErrorReportReceiver(this, serviceItem.mServiceInfo.packageName, serviceItem.mServiceInfo.applicationInfo.flags);
            activeDetail.mReportButton.setEnabled(activeDetail.mInstaller != null);
        } else {
            activeDetail.mReportButton.setEnabled(false);
        }
        this.mActiveDetails.add(activeDetail);
    }

    void addProcessDetailsView(RunningState.ProcessItem processItem, boolean z) {
        CharSequence charSequenceMakeLabel;
        if (this.mNumProcesses == 0) {
            this.mProcessesHeader = (TextView) this.mInflater.inflate(R.layout.separator_label, this.mAllDetails, false);
            this.mProcessesHeader.setText(R.string.runningservicedetails_processes_title);
            this.mAllDetails.addView(this.mProcessesHeader);
        }
        this.mNumProcesses++;
        ActiveDetail activeDetail = new ActiveDetail();
        View viewInflate = this.mInflater.inflate(R.layout.running_service_details_process, this.mAllDetails, false);
        this.mAllDetails.addView(viewInflate);
        activeDetail.mRootView = viewInflate;
        activeDetail.mViewHolder = new RunningProcessesView.ViewHolder(viewInflate);
        activeDetail.mActiveItem = activeDetail.mViewHolder.bind(this.mState, processItem, this.mBuilder);
        TextView textView = (TextView) viewInflate.findViewById(R.id.comp_description);
        if (z) {
            textView.setText(R.string.main_running_process_description);
        } else {
            CharSequence charSequence = null;
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = processItem.mRunningProcessInfo;
            ComponentName componentName = runningAppProcessInfo.importanceReasonComponent;
            int i = runningAppProcessInfo.importanceReasonCode;
            try {
                switch (i) {
                    case 1:
                        i = R.string.process_provider_in_use_description;
                        if (runningAppProcessInfo.importanceReasonComponent != null) {
                            ProviderInfo providerInfo = getPackageManager().getProviderInfo(runningAppProcessInfo.importanceReasonComponent, 0);
                            charSequenceMakeLabel = RunningState.makeLabel(getPackageManager(), providerInfo.name, providerInfo);
                            charSequence = charSequenceMakeLabel;
                        }
                        break;
                    case 2:
                        i = R.string.process_service_in_use_description;
                        if (runningAppProcessInfo.importanceReasonComponent != null) {
                            ServiceInfo serviceInfo = getPackageManager().getServiceInfo(runningAppProcessInfo.importanceReasonComponent, 0);
                            charSequenceMakeLabel = RunningState.makeLabel(getPackageManager(), serviceInfo.name, serviceInfo);
                            charSequence = charSequenceMakeLabel;
                        }
                        break;
                    default:
                        i = 0;
                        break;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (i != 0 && charSequence != null) {
                textView.setText(getString(i, new Object[]{charSequence}));
            }
        }
        this.mActiveDetails.add(activeDetail);
    }

    void addDetailViews() {
        RunningState.ProcessItem processItem;
        for (int size = this.mActiveDetails.size() - 1; size >= 0; size--) {
            this.mAllDetails.removeView(this.mActiveDetails.get(size).mRootView);
        }
        this.mActiveDetails.clear();
        if (this.mServicesHeader != null) {
            this.mAllDetails.removeView(this.mServicesHeader);
            this.mServicesHeader = null;
        }
        if (this.mProcessesHeader != null) {
            this.mAllDetails.removeView(this.mProcessesHeader);
            this.mProcessesHeader = null;
        }
        this.mNumProcesses = 0;
        this.mNumServices = 0;
        if (this.mMergedItem != null) {
            for (int i = 0; i < this.mMergedItem.mServices.size(); i++) {
                addServiceDetailsView(this.mMergedItem.mServices.get(i), this.mMergedItem);
            }
            if (this.mMergedItem.mServices.size() <= 0) {
                addServiceDetailsView(null, this.mMergedItem);
            }
            int i2 = -1;
            while (i2 < this.mMergedItem.mOtherProcesses.size()) {
                if (i2 < 0) {
                    processItem = this.mMergedItem.mProcess;
                } else {
                    processItem = this.mMergedItem.mOtherProcesses.get(i2);
                }
                if (processItem.mPid > 0) {
                    addProcessDetailsView(processItem, i2 < 0);
                }
                i2++;
            }
        }
    }

    void refreshUi(boolean z) {
        if (findMergedItem()) {
            z = true;
        }
        if (z) {
            if (this.mMergedItem != null) {
                this.mSnippetActiveItem = this.mSnippetViewHolder.bind(this.mState, this.mMergedItem, this.mBuilder);
            } else if (this.mSnippetActiveItem != null) {
                this.mSnippetActiveItem.mHolder.size.setText("");
                this.mSnippetActiveItem.mHolder.uptime.setText("");
                this.mSnippetActiveItem.mHolder.description.setText(R.string.no_services);
            } else {
                finish();
                return;
            }
            addDetailViews();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.rk_itvui.settings.FullScreenAlertActivity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideSystemUI();
        this.mUid = getIntent().getIntExtra(KEY_UID, 0);
        this.mProcessName = getIntent().getStringExtra(KEY_PROCESS);
        this.mShowBackground = getIntent().getBooleanExtra(KEY_BACKGROUND, false);
        this.mAm = (ActivityManager) getSystemService("activity");
        this.mInflater = (LayoutInflater) getSystemService("layout_inflater");
        this.mState = RunningState.getInstance(this);
        setContentView(R.layout.running_service_details);
        this.mAllDetails = (ViewGroup) findViewById(R.id.all_details);
        this.mSnippet = (ViewGroup) findViewById(R.id.snippet);
        this.mSnippet.setPadding(0, this.mSnippet.getPaddingTop(), 0, this.mSnippet.getPaddingBottom());
        this.mSnippetViewHolder = new RunningProcessesView.ViewHolder(this.mSnippet);
        ensureData();
    }

    protected void onPause() {
        super.onPause();
        this.mHaveData = false;
        this.mState.pause();
    }

    protected void onResume() {
        super.onResume();
        ensureData();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    ActiveDetail activeDetailForService(ComponentName componentName) {
        for (int i = 0; i < this.mActiveDetails.size(); i++) {
            ActiveDetail activeDetail = this.mActiveDetails.get(i);
            if (activeDetail.mServiceItem != null && activeDetail.mServiceItem.mRunningService != null && componentName.equals(activeDetail.mServiceItem.mRunningService.service)) {
                return activeDetail;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected Dialog onCreateDialog(int i, Bundle bundle) {
        if (i == 1) {
            final ComponentName componentName = (ComponentName) bundle.getParcelable("comp");
            if (activeDetailForService(componentName) == null) {
                return null;
            }
            return new AlertDialog.Builder(this).setTitle(getString(R.string.runningservicedetails_stop_dlg_title)).setIcon(android.R.drawable.ic_dialog_alert).setMessage(getString(R.string.runningservicedetails_stop_dlg_text)).setPositiveButton(R.string.dlg_ok, new DialogInterface.OnClickListener() { // from class: com.rk_itvui.settings.dialog.RunningServiceDetails.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    ActiveDetail activeDetailActiveDetailForService = RunningServiceDetails.this.activeDetailForService(componentName);
                    if (activeDetailActiveDetailForService != null) {
                        activeDetailActiveDetailForService.stopActiveService(true);
                    }
                }
            }).setNegativeButton(R.string.dlg_cancel, (DialogInterface.OnClickListener) null).create();
        }
        return super.onCreateDialog(i, bundle);
    }

    void ensureData() {
        if (this.mHaveData) {
            return;
        }
        this.mHaveData = true;
        this.mState.resume(this);
        this.mState.waitForData();
        refreshUi(true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    void updateTimes() {
        if (this.mSnippetActiveItem != null) {
            this.mSnippetActiveItem.updateTime(this, this.mBuilder);
        }
        for (int i = 0; i < this.mActiveDetails.size(); i++) {
            this.mActiveDetails.get(i).mActiveItem.updateTime(this, this.mBuilder);
        }
    }

    @Override // com.rk_itvui.settings.dialog.RunningState.OnRefreshUiListener
    public void onRefreshUi(int i) {
        switch (i) {
            case 0:
                updateTimes();
                break;
            case 1:
                refreshUi(false);
                updateTimes();
                break;
            case 2:
                refreshUi(true);
                updateTimes();
                break;
        }
    }
}
