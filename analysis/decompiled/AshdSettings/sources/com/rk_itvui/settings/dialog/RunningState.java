package com.rk_itvui.settings.dialog;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Debug;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import com.ashd.settings.R;
import com.rk_itvui.settings.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RunningState {
    static final long CONTENTS_UPDATE_DELAY = 2000;
    static final int MAX_SERVICES = 100;
    static final int MSG_REFRESH_UI = 2;
    static final int MSG_UPDATE_CONTENTS = 1;
    static final int MSG_UPDATE_TIME = 3;
    static final long TIME_UPDATE_DELAY = 1000;
    static Object sGlobalLock = new Object();
    static RunningState sInstance;
    final ActivityManager mAm;
    final Context mApplicationContext;
    final BackgroundHandler mBackgroundHandler;
    long mBackgroundProcessMemory;
    long mForegroundProcessMemory;
    boolean mHaveData;
    int mNumBackgroundProcesses;
    int mNumForegroundProcesses;
    int mNumServiceProcesses;
    final PackageManager mPm;
    OnRefreshUiListener mRefreshUiListener;
    long mServiceProcessMemory;
    boolean mWatchingBackgroundItems;
    final SparseArray<HashMap<String, ProcessItem>> mServiceProcessesByName = new SparseArray<>();
    final SparseArray<ProcessItem> mServiceProcessesByPid = new SparseArray<>();
    final ServiceProcessComparator mServiceProcessComparator = new ServiceProcessComparator();
    final ArrayList<ProcessItem> mInterestingProcesses = new ArrayList<>();
    final SparseArray<ProcessItem> mRunningProcesses = new SparseArray<>();
    final ArrayList<ProcessItem> mProcessItems = new ArrayList<>();
    final ArrayList<ProcessItem> mAllProcessItems = new ArrayList<>();
    int mSequence = 0;
    final Object mLock = new Object();
    ArrayList<BaseItem> mItems = new ArrayList<>();
    ArrayList<MergedItem> mMergedItems = new ArrayList<>();
    ArrayList<MergedItem> mBackgroundItems = new ArrayList<>();
    final Handler mHandler = new Handler() { // from class: com.rk_itvui.settings.dialog.RunningState.1
        int mNextUpdate = 0;

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 2:
                    this.mNextUpdate = message.arg1 != 0 ? 2 : 1;
                    return;
                case 3:
                    synchronized (RunningState.this.mLock) {
                        if (RunningState.this.mResumed) {
                            removeMessages(3);
                            sendMessageDelayed(obtainMessage(3), RunningState.TIME_UPDATE_DELAY);
                            if (RunningState.this.mRefreshUiListener != null) {
                                RunningState.this.mRefreshUiListener.onRefreshUi(this.mNextUpdate);
                                this.mNextUpdate = 0;
                                return;
                            }
                            return;
                        }
                        return;
                    }
                default:
                    return;
            }
        }
    };
    boolean mResumed = false;
    final HandlerThread mBackgroundThread = new HandlerThread("RunningState:Background");

    interface OnRefreshUiListener {
        public static final int REFRESH_DATA = 1;
        public static final int REFRESH_STRUCTURE = 2;
        public static final int REFRESH_TIME = 0;

        void onRefreshUi(int i);
    }

    final class BackgroundHandler extends Handler {
        public BackgroundHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            synchronized (RunningState.this.mLock) {
                if (RunningState.this.mResumed) {
                    Message messageObtainMessage = RunningState.this.mHandler.obtainMessage(2);
                    messageObtainMessage.arg1 = RunningState.this.update(RunningState.this.mApplicationContext, RunningState.this.mAm) ? 1 : 0;
                    RunningState.this.mHandler.sendMessage(messageObtainMessage);
                    removeMessages(1);
                    sendMessageDelayed(obtainMessage(1), RunningState.CONTENTS_UPDATE_DELAY);
                }
            }
        }
    }

    static class BaseItem {
        long mActiveSince;
        boolean mBackground;
        int mCurSeq;
        String mCurSizeStr;
        String mDescription;
        CharSequence mDisplayLabel;
        final boolean mIsProcess;
        String mLabel;
        boolean mNeedDivider;
        PackageItemInfo mPackageInfo;
        long mSize;
        String mSizeStr;

        public BaseItem(boolean z) {
            this.mIsProcess = z;
        }
    }

    static class ServiceItem extends BaseItem {
        MergedItem mMergedItem;
        ActivityManager.RunningServiceInfo mRunningService;
        ServiceInfo mServiceInfo;
        boolean mShownAsStarted;

        public ServiceItem() {
            super(false);
        }
    }

    static class ProcessItem extends BaseItem {
        long mActiveSince;
        ProcessItem mClient;
        final SparseArray<ProcessItem> mDependentProcesses;
        boolean mIsStarted;
        boolean mIsSystem;
        int mLastNumDependentProcesses;
        MergedItem mMergedItem;
        int mPid;
        final String mProcessName;
        ActivityManager.RunningAppProcessInfo mRunningProcessInfo;
        int mRunningSeq;
        final HashMap<ComponentName, ServiceItem> mServices;
        final int mUid;

        public ProcessItem(Context context, int i, String str) {
            super(true);
            this.mServices = new HashMap<>();
            this.mDependentProcesses = new SparseArray<>();
            this.mDescription = context.getResources().getString(R.string.service_process_name, str);
            this.mUid = i;
            this.mProcessName = str;
        }

        void ensureLabel(PackageManager packageManager) {
            CharSequence text;
            if (this.mLabel != null) {
                return;
            }
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(this.mProcessName, 0);
                if (applicationInfo.uid == this.mUid) {
                    this.mDisplayLabel = applicationInfo.loadLabel(packageManager);
                    this.mLabel = this.mDisplayLabel.toString();
                    this.mPackageInfo = applicationInfo;
                    return;
                }
            } catch (PackageManager.NameNotFoundException unused) {
            }
            String[] packagesForUid = packageManager.getPackagesForUid(this.mUid);
            if (packagesForUid.length == 1) {
                try {
                    ApplicationInfo applicationInfo2 = packageManager.getApplicationInfo(packagesForUid[0], 0);
                    this.mDisplayLabel = applicationInfo2.loadLabel(packageManager);
                    this.mLabel = this.mDisplayLabel.toString();
                    this.mPackageInfo = applicationInfo2;
                    return;
                } catch (PackageManager.NameNotFoundException unused2) {
                }
            }
            for (String str : packagesForUid) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                    if (packageInfo.sharedUserLabel != 0 && (text = packageManager.getText(str, packageInfo.sharedUserLabel, packageInfo.applicationInfo)) != null) {
                        this.mDisplayLabel = text;
                        this.mLabel = text.toString();
                        this.mPackageInfo = packageInfo.applicationInfo;
                        return;
                    }
                } catch (PackageManager.NameNotFoundException unused3) {
                }
            }
            if (this.mServices.size() > 0) {
                this.mPackageInfo = this.mServices.values().iterator().next().mServiceInfo.applicationInfo;
                this.mDisplayLabel = this.mPackageInfo.loadLabel(packageManager);
                this.mLabel = this.mDisplayLabel.toString();
            } else {
                try {
                    ApplicationInfo applicationInfo3 = packageManager.getApplicationInfo(packagesForUid[0], 0);
                    this.mDisplayLabel = applicationInfo3.loadLabel(packageManager);
                    this.mLabel = this.mDisplayLabel.toString();
                    this.mPackageInfo = applicationInfo3;
                } catch (PackageManager.NameNotFoundException unused4) {
                }
            }
        }

        boolean updateService(Context context, ActivityManager.RunningServiceInfo runningServiceInfo) {
            boolean z;
            PackageManager packageManager = context.getPackageManager();
            ServiceItem serviceItem = this.mServices.get(runningServiceInfo.service);
            if (serviceItem == null) {
                serviceItem = new ServiceItem();
                serviceItem.mRunningService = runningServiceInfo;
                try {
                    serviceItem.mServiceInfo = packageManager.getServiceInfo(runningServiceInfo.service, 0);
                } catch (PackageManager.NameNotFoundException unused) {
                }
                serviceItem.mDisplayLabel = RunningState.makeLabel(packageManager, serviceItem.mRunningService.service.getClassName(), serviceItem.mServiceInfo);
                this.mLabel = this.mDisplayLabel != null ? this.mDisplayLabel.toString() : null;
                serviceItem.mPackageInfo = serviceItem.mServiceInfo.applicationInfo;
                this.mServices.put(runningServiceInfo.service, serviceItem);
                z = true;
            } else {
                z = false;
            }
            serviceItem.mCurSeq = this.mCurSeq;
            serviceItem.mRunningService = runningServiceInfo;
            long j = runningServiceInfo.restarting == 0 ? runningServiceInfo.activeSince : -1L;
            if (serviceItem.mActiveSince != j) {
                serviceItem.mActiveSince = j;
                z = true;
            }
            if (runningServiceInfo.clientPackage != null && runningServiceInfo.clientLabel != 0) {
                if (serviceItem.mShownAsStarted) {
                    serviceItem.mShownAsStarted = false;
                    z = true;
                }
                try {
                    serviceItem.mDescription = context.getResources().getString(R.string.service_client_name, packageManager.getResourcesForApplication(runningServiceInfo.clientPackage).getString(runningServiceInfo.clientLabel));
                } catch (PackageManager.NameNotFoundException unused2) {
                    serviceItem.mDescription = null;
                }
            } else {
                if (!serviceItem.mShownAsStarted) {
                    serviceItem.mShownAsStarted = true;
                    z = true;
                }
                serviceItem.mDescription = context.getResources().getString(R.string.service_started_by_app);
            }
            return z;
        }

        boolean updateSize(Context context, Debug.MemoryInfo memoryInfo, int i) {
            this.mSize = ((long) memoryInfo.getTotalPss()) * 1024;
            if (this.mCurSeq == i) {
                String size = Utils.formatSize(this.mSize, "RunningState updateSize");
                if (!size.equals(this.mSizeStr)) {
                    this.mSizeStr = size;
                    return false;
                }
            }
            return false;
        }

        boolean buildDependencyChain(Context context, PackageManager packageManager, int i) {
            int size = this.mDependentProcesses.size();
            boolean zBuildDependencyChain = false;
            for (int i2 = 0; i2 < size; i2++) {
                ProcessItem processItemValueAt = this.mDependentProcesses.valueAt(i2);
                if (processItemValueAt.mClient != this) {
                    processItemValueAt.mClient = this;
                    zBuildDependencyChain = true;
                }
                processItemValueAt.mCurSeq = i;
                processItemValueAt.ensureLabel(packageManager);
                zBuildDependencyChain |= processItemValueAt.buildDependencyChain(context, packageManager, i);
            }
            if (this.mLastNumDependentProcesses == this.mDependentProcesses.size()) {
                return zBuildDependencyChain;
            }
            this.mLastNumDependentProcesses = this.mDependentProcesses.size();
            return true;
        }

        void addDependentProcesses(ArrayList<BaseItem> arrayList, ArrayList<ProcessItem> arrayList2) {
            int size = this.mDependentProcesses.size();
            for (int i = 0; i < size; i++) {
                ProcessItem processItemValueAt = this.mDependentProcesses.valueAt(i);
                processItemValueAt.addDependentProcesses(arrayList, arrayList2);
                arrayList.add(processItemValueAt);
                if (processItemValueAt.mPid > 0) {
                    arrayList2.add(processItemValueAt);
                }
            }
        }
    }

    static class MergedItem extends BaseItem {
        private int mLastNumProcesses;
        private int mLastNumServices;
        final ArrayList<ProcessItem> mOtherProcesses;
        ProcessItem mProcess;
        final ArrayList<ServiceItem> mServices;

        MergedItem() {
            super(false);
            this.mOtherProcesses = new ArrayList<>();
            this.mServices = new ArrayList<>();
            this.mLastNumProcesses = -1;
            this.mLastNumServices = -1;
        }

        boolean update(Context context, boolean z) {
            this.mPackageInfo = this.mProcess.mPackageInfo;
            this.mDisplayLabel = this.mProcess.mDisplayLabel;
            this.mLabel = this.mProcess.mLabel;
            this.mBackground = z;
            if (!this.mBackground) {
                int size = (this.mProcess.mPid > 0 ? 1 : 0) + this.mOtherProcesses.size();
                int size2 = this.mServices.size();
                if (this.mLastNumProcesses != size || this.mLastNumServices != size2) {
                    this.mLastNumProcesses = size;
                    this.mLastNumServices = size2;
                    int i = R.string.running_processes_item_description_s_s;
                    if (size != 1) {
                        i = size2 != 1 ? R.string.running_processes_item_description_p_p : R.string.running_processes_item_description_p_s;
                    } else if (size2 != 1) {
                        i = R.string.running_processes_item_description_s_p;
                    }
                    this.mDescription = context.getResources().getString(i, Integer.valueOf(size), Integer.valueOf(size2));
                }
            }
            this.mActiveSince = -1L;
            for (int i2 = 0; i2 < this.mServices.size(); i2++) {
                ServiceItem serviceItem = this.mServices.get(i2);
                if (serviceItem.mActiveSince >= 0 && this.mActiveSince < serviceItem.mActiveSince) {
                    this.mActiveSince = serviceItem.mActiveSince;
                }
            }
            return false;
        }

        boolean updateSize(Context context) {
            this.mSize = this.mProcess.mSize;
            for (int i = 0; i < this.mOtherProcesses.size(); i++) {
                this.mSize += this.mOtherProcesses.get(i).mSize;
            }
            String size = Utils.formatSize(this.mSize, "RunningState updateSize ");
            if (size.equals(this.mSizeStr)) {
                return false;
            }
            this.mSizeStr = size;
            return false;
        }
    }

    static class ServiceProcessComparator implements Comparator<ProcessItem> {
        ServiceProcessComparator() {
        }

        @Override // java.util.Comparator
        public int compare(ProcessItem processItem, ProcessItem processItem2) {
            if (processItem.mIsStarted != processItem2.mIsStarted) {
                return processItem.mIsStarted ? -1 : 1;
            }
            if (processItem.mIsSystem != processItem2.mIsSystem) {
                return processItem.mIsSystem ? 1 : -1;
            }
            if (processItem.mActiveSince != processItem2.mActiveSince) {
                return processItem.mActiveSince > processItem2.mActiveSince ? -1 : 1;
            }
            return 0;
        }
    }

    static CharSequence makeLabel(PackageManager packageManager, String str, PackageItemInfo packageItemInfo) {
        CharSequence charSequenceLoadLabel;
        if (packageItemInfo != null && ((packageItemInfo.labelRes != 0 || packageItemInfo.nonLocalizedLabel != null) && (charSequenceLoadLabel = packageItemInfo.loadLabel(packageManager)) != null)) {
            return charSequenceLoadLabel;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf >= 0 ? str.substring(iLastIndexOf + 1, str.length()) : str;
    }

    static RunningState getInstance(Context context) {
        RunningState runningState;
        synchronized (sGlobalLock) {
            if (sInstance == null) {
                sInstance = new RunningState(context);
            }
            runningState = sInstance;
        }
        return runningState;
    }

    private RunningState(Context context) {
        this.mApplicationContext = context.getApplicationContext();
        this.mAm = (ActivityManager) this.mApplicationContext.getSystemService("activity");
        this.mPm = this.mApplicationContext.getPackageManager();
        this.mBackgroundThread.start();
        this.mBackgroundHandler = new BackgroundHandler(this.mBackgroundThread.getLooper());
    }

    void resume(OnRefreshUiListener onRefreshUiListener) {
        synchronized (this.mLock) {
            this.mResumed = true;
            this.mRefreshUiListener = onRefreshUiListener;
            if (!this.mBackgroundHandler.hasMessages(1)) {
                this.mBackgroundHandler.sendEmptyMessage(1);
            }
            this.mHandler.sendEmptyMessage(3);
        }
    }

    void updateNow() {
        synchronized (this.mLock) {
            this.mBackgroundHandler.removeMessages(1);
            this.mBackgroundHandler.sendEmptyMessage(1);
        }
    }

    boolean hasData() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mHaveData;
        }
        return z;
    }

    void waitForData() {
        synchronized (this.mLock) {
            while (!this.mHaveData) {
                try {
                    this.mLock.wait(0L);
                } catch (InterruptedException unused) {
                }
            }
        }
    }

    void pause() {
        synchronized (this.mLock) {
            this.mResumed = false;
            this.mRefreshUiListener = null;
            this.mHandler.removeMessages(3);
        }
    }

    private boolean isInterestingProcess(ActivityManager.RunningAppProcessInfo runningAppProcessInfo) {
        if ((runningAppProcessInfo.flags & 1) != 0) {
            return true;
        }
        return (runningAppProcessInfo.flags & 2) == 0 && runningAppProcessInfo.importance == 100 && runningAppProcessInfo.importanceReasonCode == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    public boolean update(Context context, ActivityManager activityManager) {
        int i;
        boolean zUpdateSize;
        ArrayList<MergedItem> arrayList;
        long j;
        long j2;
        long j3;
        Debug.MemoryInfo[] memoryInfoArr;
        int[] iArr;
        MergedItem mergedItem;
        PackageManager packageManager = context.getPackageManager();
        this.mSequence++;
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(100);
        int size = runningServices != null ? runningServices.size() : 0;
        boolean zBuildDependencyChain = false;
        for (int i2 = 0; i2 < size; i2++) {
            ActivityManager.RunningServiceInfo runningServiceInfo = runningServices.get(i2);
            if ((runningServiceInfo.started || runningServiceInfo.clientLabel != 0) && (runningServiceInfo.flags & 8) == 0) {
                HashMap<String, ProcessItem> map = this.mServiceProcessesByName.get(runningServiceInfo.uid);
                if (map == null) {
                    map = new HashMap<>();
                    this.mServiceProcessesByName.put(runningServiceInfo.uid, map);
                }
                ProcessItem processItem = map.get(runningServiceInfo.process);
                if (processItem == null) {
                    processItem = new ProcessItem(context, runningServiceInfo.uid, runningServiceInfo.process);
                    map.put(runningServiceInfo.process, processItem);
                    zBuildDependencyChain = true;
                }
                if (processItem.mCurSeq != this.mSequence) {
                    int i3 = runningServiceInfo.restarting == 0 ? runningServiceInfo.pid : 0;
                    if (i3 != processItem.mPid) {
                        if (processItem.mPid != i3) {
                            if (processItem.mPid != 0) {
                                this.mServiceProcessesByPid.remove(processItem.mPid);
                            }
                            if (i3 != 0) {
                                this.mServiceProcessesByPid.put(i3, processItem);
                            }
                            processItem.mPid = i3;
                        }
                        zBuildDependencyChain = true;
                    }
                    processItem.mDependentProcesses.clear();
                    processItem.mCurSeq = this.mSequence;
                }
                zBuildDependencyChain |= processItem.updateService(context, runningServiceInfo);
            }
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        int size2 = runningAppProcesses != null ? runningAppProcesses.size() : 0;
        for (int i4 = 0; i4 < size2; i4++) {
            ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i4);
            ProcessItem processItem2 = this.mServiceProcessesByPid.get(runningAppProcessInfo.pid);
            if (processItem2 == null) {
                processItem2 = this.mRunningProcesses.get(runningAppProcessInfo.pid);
                if (processItem2 == null) {
                    processItem2 = new ProcessItem(context, runningAppProcessInfo.uid, runningAppProcessInfo.processName);
                    processItem2.mPid = runningAppProcessInfo.pid;
                    this.mRunningProcesses.put(runningAppProcessInfo.pid, processItem2);
                    zBuildDependencyChain = true;
                }
                processItem2.mDependentProcesses.clear();
            }
            if (isInterestingProcess(runningAppProcessInfo)) {
                if (!this.mInterestingProcesses.contains(processItem2)) {
                    this.mInterestingProcesses.add(processItem2);
                    zBuildDependencyChain = true;
                }
                processItem2.mCurSeq = this.mSequence;
                processItem2.ensureLabel(packageManager);
            }
            processItem2.mRunningSeq = this.mSequence;
            processItem2.mRunningProcessInfo = runningAppProcessInfo;
        }
        int size3 = this.mRunningProcesses.size();
        for (int i5 = 0; i5 < size3; i5++) {
            ProcessItem processItemValueAt = this.mRunningProcesses.valueAt(i5);
            if (processItemValueAt != null) {
                if (processItemValueAt.mRunningSeq == this.mSequence) {
                    int i6 = processItemValueAt.mRunningProcessInfo.importanceReasonPid;
                    if (i6 != 0) {
                        ProcessItem processItem3 = this.mServiceProcessesByPid.get(i6);
                        if (processItem3 == null) {
                            processItem3 = this.mRunningProcesses.get(i6);
                        }
                        if (processItem3 != null) {
                            processItem3.mDependentProcesses.put(processItemValueAt.mPid, processItemValueAt);
                        }
                    } else {
                        processItemValueAt.mClient = null;
                    }
                } else {
                    this.mRunningProcesses.remove(this.mRunningProcesses.keyAt(i5));
                    zBuildDependencyChain = true;
                }
            }
        }
        int size4 = this.mInterestingProcesses.size();
        int i7 = 0;
        while (i7 < size4) {
            if (this.mRunningProcesses.get(this.mInterestingProcesses.get(i7).mPid) == null) {
                this.mInterestingProcesses.remove(i7);
                i7--;
                size4--;
                zBuildDependencyChain = true;
            }
            i7++;
        }
        int size5 = this.mServiceProcessesByPid.size();
        for (int i8 = 0; i8 < size5; i8++) {
            ProcessItem processItemValueAt2 = this.mServiceProcessesByPid.valueAt(i8);
            if (processItemValueAt2.mCurSeq == this.mSequence) {
                zBuildDependencyChain = processItemValueAt2.buildDependencyChain(context, packageManager, this.mSequence) | zBuildDependencyChain;
            }
        }
        for (int i9 = 0; i9 < this.mServiceProcessesByName.size(); i9++) {
            HashMap<String, ProcessItem> mapValueAt = this.mServiceProcessesByName.valueAt(i9);
            Iterator<ProcessItem> it = mapValueAt.values().iterator();
            while (it.hasNext()) {
                ProcessItem next = it.next();
                if (next.mCurSeq == this.mSequence) {
                    next.ensureLabel(packageManager);
                    if (next.mPid == 0) {
                        next.mDependentProcesses.clear();
                    }
                    Iterator<ServiceItem> it2 = next.mServices.values().iterator();
                    while (it2.hasNext()) {
                        if (it2.next().mCurSeq != this.mSequence) {
                            it2.remove();
                            zBuildDependencyChain = true;
                        }
                    }
                } else {
                    it.remove();
                    if (mapValueAt.size() == 0) {
                        this.mServiceProcessesByName.remove(this.mServiceProcessesByName.keyAt(i9));
                    }
                    if (next.mPid != 0) {
                        this.mServiceProcessesByPid.remove(next.mPid);
                    }
                    zBuildDependencyChain = true;
                }
            }
        }
        if (zBuildDependencyChain) {
            ArrayList arrayList2 = new ArrayList();
            for (int i10 = 0; i10 < this.mServiceProcessesByName.size(); i10++) {
                for (ProcessItem processItem4 : this.mServiceProcessesByName.valueAt(i10).values()) {
                    processItem4.mIsSystem = false;
                    processItem4.mIsStarted = true;
                    processItem4.mActiveSince = Long.MAX_VALUE;
                    for (ServiceItem serviceItem : processItem4.mServices.values()) {
                        if (serviceItem.mServiceInfo != null && (serviceItem.mServiceInfo.applicationInfo.flags & 1) != 0) {
                            processItem4.mIsSystem = true;
                        }
                        if (serviceItem.mRunningService != null && serviceItem.mRunningService.clientLabel != 0) {
                            processItem4.mIsStarted = false;
                            if (processItem4.mActiveSince > serviceItem.mRunningService.activeSince) {
                                processItem4.mActiveSince = serviceItem.mRunningService.activeSince;
                            }
                        }
                    }
                    arrayList2.add(processItem4);
                }
            }
            Collections.sort(arrayList2, this.mServiceProcessComparator);
            ArrayList<BaseItem> arrayList3 = new ArrayList<>();
            ArrayList<MergedItem> arrayList4 = new ArrayList<>();
            this.mProcessItems.clear();
            for (int i11 = 0; i11 < arrayList2.size(); i11++) {
                ProcessItem processItem5 = (ProcessItem) arrayList2.get(i11);
                processItem5.mNeedDivider = false;
                processItem5.addDependentProcesses(arrayList3, this.mProcessItems);
                arrayList3.add(processItem5);
                if (processItem5.mPid > 0) {
                    this.mProcessItems.add(processItem5);
                }
                boolean z = false;
                MergedItem mergedItem2 = null;
                for (ServiceItem serviceItem2 : processItem5.mServices.values()) {
                    serviceItem2.mNeedDivider = z;
                    arrayList3.add(serviceItem2);
                    if (serviceItem2.mMergedItem != null) {
                        if (mergedItem2 != null) {
                            MergedItem mergedItem3 = serviceItem2.mMergedItem;
                        }
                        mergedItem2 = serviceItem2.mMergedItem;
                    }
                    z = true;
                }
                MergedItem mergedItem4 = new MergedItem();
                for (ServiceItem serviceItem3 : processItem5.mServices.values()) {
                    mergedItem4.mServices.add(serviceItem3);
                    serviceItem3.mMergedItem = mergedItem4;
                }
                mergedItem4.mProcess = processItem5;
                mergedItem4.mOtherProcesses.clear();
                for (int size6 = this.mProcessItems.size(); size6 < this.mProcessItems.size() - 1; size6++) {
                    mergedItem4.mOtherProcesses.add(this.mProcessItems.get(size6));
                }
                mergedItem4.update(context, false);
                arrayList4.add(mergedItem4);
            }
            int size7 = this.mInterestingProcesses.size();
            for (int i12 = 0; i12 < size7; i12++) {
                ProcessItem processItem6 = this.mInterestingProcesses.get(i12);
                if (processItem6.mClient == null && processItem6.mServices.size() <= 0) {
                    if (processItem6.mMergedItem == null) {
                        processItem6.mMergedItem = new MergedItem();
                        processItem6.mMergedItem.mProcess = processItem6;
                    }
                    processItem6.mMergedItem.update(context, false);
                    arrayList4.add(0, processItem6.mMergedItem);
                    this.mProcessItems.add(processItem6);
                }
            }
            i = 0;
            synchronized (this.mLock) {
                this.mItems = arrayList3;
                this.mMergedItems = arrayList4;
            }
        } else {
            i = 0;
        }
        this.mAllProcessItems.clear();
        this.mAllProcessItems.addAll(this.mProcessItems);
        int size8 = this.mRunningProcesses.size();
        int i13 = i;
        int i14 = i13;
        int i15 = i14;
        int i16 = i15;
        while (i13 < size8) {
            ProcessItem processItemValueAt3 = this.mRunningProcesses.valueAt(i13);
            if (processItemValueAt3.mCurSeq == this.mSequence) {
                i14++;
            } else if (processItemValueAt3.mRunningProcessInfo.importance >= 400) {
                i15++;
                this.mAllProcessItems.add(processItemValueAt3);
            } else if (processItemValueAt3.mRunningProcessInfo.importance <= 200) {
                i16++;
                this.mAllProcessItems.add(processItemValueAt3);
            } else {
                Log.i("RunningState", "Unknown non-service process: " + processItemValueAt3.mProcessName + " #" + processItemValueAt3.mPid);
            }
            i13++;
        }
        try {
            int size9 = this.mAllProcessItems.size();
            int[] iArr2 = new int[size9];
            for (int i17 = i; i17 < size9; i17++) {
                iArr2[i17] = this.mAllProcessItems.get(i17).mPid;
            }
            Debug.MemoryInfo[] processMemoryInfo = ActivityManagerNative.getDefault().getProcessMemoryInfo(iArr2);
            zUpdateSize = zBuildDependencyChain;
            int i18 = i;
            arrayList = null;
            j = 0;
            j2 = 0;
            j3 = 0;
            while (i18 < iArr2.length) {
                try {
                    ProcessItem processItem7 = this.mAllProcessItems.get(i18);
                    zUpdateSize |= processItem7.updateSize(context, processMemoryInfo[i18], this.mSequence);
                    if (processItem7.mCurSeq == this.mSequence) {
                        memoryInfoArr = processMemoryInfo;
                        iArr = iArr2;
                        j2 += processItem7.mSize;
                    } else {
                        memoryInfoArr = processMemoryInfo;
                        iArr = iArr2;
                        if (processItem7.mRunningProcessInfo.importance >= 400) {
                            long j4 = j3 + processItem7.mSize;
                            if (arrayList != null) {
                                try {
                                    mergedItem = new MergedItem();
                                    processItem7.mMergedItem = mergedItem;
                                    processItem7.mMergedItem.mProcess = processItem7;
                                    arrayList.add(mergedItem);
                                } catch (RemoteException unused) {
                                    j3 = j4;
                                }
                            } else if (i >= this.mBackgroundItems.size() || this.mBackgroundItems.get(i).mProcess != processItem7) {
                                ArrayList<MergedItem> arrayList5 = new ArrayList<>(i15);
                                for (int i19 = 0; i19 < i; i19++) {
                                    try {
                                        arrayList5.add(this.mBackgroundItems.get(i19));
                                    } catch (RemoteException unused2) {
                                        j3 = j4;
                                        arrayList = arrayList5;
                                    }
                                }
                                MergedItem mergedItem5 = new MergedItem();
                                processItem7.mMergedItem = mergedItem5;
                                processItem7.mMergedItem.mProcess = processItem7;
                                arrayList5.add(mergedItem5);
                                arrayList = arrayList5;
                                mergedItem = mergedItem5;
                            } else {
                                mergedItem = this.mBackgroundItems.get(i);
                            }
                            mergedItem.update(context, true);
                            mergedItem.updateSize(context);
                            i++;
                            j3 = j4;
                        } else if (processItem7.mRunningProcessInfo.importance <= 200) {
                            j += processItem7.mSize;
                        }
                    }
                    i18++;
                    iArr2 = iArr;
                    processMemoryInfo = memoryInfoArr;
                } catch (RemoteException unused3) {
                }
            }
        } catch (RemoteException unused4) {
            zUpdateSize = zBuildDependencyChain;
            arrayList = null;
            j = 0;
            j2 = 0;
            j3 = 0;
        }
        long j5 = j2;
        long j6 = j3;
        boolean z2 = zUpdateSize;
        if (arrayList == null && this.mBackgroundItems.size() > i15) {
            arrayList = new ArrayList<>(i15);
            for (int i20 = 0; i20 < i15; i20++) {
                arrayList.add(this.mBackgroundItems.get(i20));
            }
        }
        for (int i21 = 0; i21 < this.mMergedItems.size(); i21++) {
            this.mMergedItems.get(i21).updateSize(context);
        }
        synchronized (this.mLock) {
            this.mNumBackgroundProcesses = i15;
            this.mNumForegroundProcesses = i16;
            this.mNumServiceProcesses = i14;
            this.mBackgroundProcessMemory = j6;
            this.mForegroundProcessMemory = j;
            this.mServiceProcessMemory = j5;
            if (arrayList != null) {
                this.mBackgroundItems = arrayList;
                if (this.mWatchingBackgroundItems) {
                    z2 = true;
                }
            }
            if (!this.mHaveData) {
                this.mHaveData = true;
                this.mLock.notifyAll();
            }
        }
        return z2;
    }

    ArrayList<BaseItem> getCurrentItems() {
        ArrayList<BaseItem> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mItems;
        }
        return arrayList;
    }

    void setWatchingBackgroundItems(boolean z) {
        synchronized (this.mLock) {
            this.mWatchingBackgroundItems = z;
        }
    }

    ArrayList<MergedItem> getCurrentMergedItems() {
        ArrayList<MergedItem> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mMergedItems;
        }
        return arrayList;
    }

    ArrayList<MergedItem> getCurrentBackgroundItems() {
        ArrayList<MergedItem> arrayList;
        synchronized (this.mLock) {
            arrayList = this.mBackgroundItems;
        }
        return arrayList;
    }
}
