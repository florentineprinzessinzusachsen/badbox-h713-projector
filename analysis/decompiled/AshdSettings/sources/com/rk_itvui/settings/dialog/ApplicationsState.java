package com.rk_itvui.settings.dialog;

import android.R;
import android.app.Application;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.os.UserHandle;
import android.os.storage.StorageManager;
import android.support.v4.content.IntentCompat;
import android.util.Log;
import com.rk_itvui.settings.Utils;
import com.rk_itvui.utils.ReflectionUtils;
import java.io.File;
import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationsState {
    static final boolean DEBUG = true;
    static final boolean DEBUG_LOCKING = true;
    static final int SIZE_INVALID = -2;
    static final int SIZE_UNKNOWN = -1;
    static final String TAG = "ApplicationsState";
    static ApplicationsState sInstance;
    final BackgroundHandler mBackgroundHandler;
    final Context mContext;
    Callbacks mCurCallbacks;
    String mCurComputingSizePkg;
    PackageIntentReceiver mPackageIntentReceiver;
    final PackageManager mPm;
    boolean mRebuildAsync;
    Comparator<AppEntry> mRebuildComparator;
    AppFilter mRebuildFilter;
    boolean mRebuildRequested;
    ArrayList<AppEntry> mRebuildResult;
    boolean mResumed;
    StorageManager storageManager;
    StorageStatsManager storageStatsManager;
    static final Pattern REMOVE_DIACRITICALS_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    public static final Comparator<AppEntry> ALPHA_COMPARATOR = new Comparator<AppEntry>() { // from class: com.rk_itvui.settings.dialog.ApplicationsState.1
        private final Collator sCollator = Collator.getInstance();

        @Override // java.util.Comparator
        public int compare(AppEntry appEntry, AppEntry appEntry2) {
            return this.sCollator.compare(appEntry.label, appEntry2.label);
        }
    };
    public static final Comparator<AppEntry> SIZE_COMPARATOR = new Comparator<AppEntry>() { // from class: com.rk_itvui.settings.dialog.ApplicationsState.2
        private final Collator sCollator = Collator.getInstance();

        @Override // java.util.Comparator
        public int compare(AppEntry appEntry, AppEntry appEntry2) {
            if (appEntry.size < appEntry2.size) {
                return 1;
            }
            if (appEntry.size > appEntry2.size) {
                return -1;
            }
            return this.sCollator.compare(appEntry.label, appEntry2.label);
        }
    };
    public static final AppFilter THIRD_PARTY_FILTER = new AppFilter() { // from class: com.rk_itvui.settings.dialog.ApplicationsState.3
        @Override // com.rk_itvui.settings.dialog.ApplicationsState.AppFilter
        public void init() {
        }

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationInfo applicationInfo) {
            return (applicationInfo.flags & 128) != 0 || (applicationInfo.flags & 1) == 0;
        }
    };
    public static final AppFilter ON_SD_CARD_FILTER = new AppFilter() { // from class: com.rk_itvui.settings.dialog.ApplicationsState.4
        final CanBeOnSdCardChecker mCanBeOnSdCardChecker = new CanBeOnSdCardChecker();

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.AppFilter
        public void init() {
            this.mCanBeOnSdCardChecker.init();
        }

        @Override // com.rk_itvui.settings.dialog.ApplicationsState.AppFilter
        public boolean filterApp(ApplicationInfo applicationInfo) {
            return this.mCanBeOnSdCardChecker.check(applicationInfo);
        }
    };
    static final Object sLock = new Object();
    final HashMap<String, AppEntry> mEntriesMap = new HashMap<>();
    final ArrayList<AppEntry> mAppEntries = new ArrayList<>();
    List<ApplicationInfo> mApplications = new ArrayList();
    long mCurId = 1;
    final Object mRebuildSync = new Object();
    final MainHandler mMainHandler = new MainHandler();
    final HandlerThread mThread = new HandlerThread("ApplicationsState.Loader", 10);

    public interface AppFilter {
        boolean filterApp(ApplicationInfo applicationInfo);

        void init();
    }

    public interface Callbacks {
        void onAllSizesComputed();

        void onPackageIconChanged();

        void onPackageListChanged();

        void onPackageSizeChanged(String str);

        void onRebuildComplete(ArrayList<AppEntry> arrayList);

        void onRunningStateChanged(boolean z);
    }

    public static class SizeInfo {
        long cacheSize;
        long codeSize;
        long dataSize;
    }

    public static String normalize(String str) {
        return REMOVE_DIACRITICALS_PATTERN.matcher(Normalizer.normalize(str, Normalizer.Form.NFD)).replaceAll("").toLowerCase();
    }

    public static class AppEntry extends SizeInfo {
        final File apkFile;
        Drawable icon;
        final long id;
        ApplicationInfo info;
        String label;
        boolean mounted;
        String normalizedLabel;
        long sizeLoadStart;
        String sizeStr;
        long size = -1;
        boolean sizeStale = true;

        String getNormalizedLabel() {
            if (this.normalizedLabel != null) {
                return this.normalizedLabel;
            }
            this.normalizedLabel = ApplicationsState.normalize(this.label);
            return this.normalizedLabel;
        }

        AppEntry(Context context, ApplicationInfo applicationInfo, long j) {
            this.apkFile = new File(applicationInfo.sourceDir);
            this.id = j;
            this.info = applicationInfo;
            ensureLabel(context);
        }

        void ensureLabel(Context context) {
            if (this.label == null || !this.mounted) {
                if (!this.apkFile.exists()) {
                    this.mounted = false;
                    this.label = this.info.packageName;
                } else {
                    this.mounted = true;
                    CharSequence charSequenceLoadLabel = this.info.loadLabel(context.getPackageManager());
                    this.label = charSequenceLoadLabel != null ? charSequenceLoadLabel.toString() : this.info.packageName;
                }
            }
        }

        boolean ensureIconLocked(Context context, PackageManager packageManager) {
            if (this.icon == null) {
                if (this.apkFile.exists()) {
                    this.icon = this.info.loadIcon(packageManager);
                    return true;
                }
                this.mounted = false;
                this.icon = context.getResources().getDrawable(R.drawable.perm_group_call_log);
            } else if (!this.mounted && this.apkFile.exists()) {
                this.mounted = true;
                this.icon = this.info.loadIcon(packageManager);
                return true;
            }
            return false;
        }
    }

    private class PackageIntentReceiver extends BroadcastReceiver {
        private PackageIntentReceiver() {
        }

        void registerReceiver() {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addDataScheme("package");
            ApplicationsState.this.mContext.registerReceiver(this, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction(IntentCompat.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE);
            intentFilter2.addAction(IntentCompat.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE);
            ApplicationsState.this.mContext.registerReceiver(this, intentFilter2);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String[] stringArrayExtra;
            String action = intent.getAction();
            if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                ApplicationsState.this.addPackage(intent.getData().getEncodedSchemeSpecificPart());
                return;
            }
            if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                ApplicationsState.this.removePackage(intent.getData().getEncodedSchemeSpecificPart());
                return;
            }
            if ("android.intent.action.PACKAGE_CHANGED".equals(action)) {
                String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
                ApplicationsState.this.removePackage(encodedSchemeSpecificPart);
                ApplicationsState.this.addPackage(encodedSchemeSpecificPart);
            } else if ((IntentCompat.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE.equals(action) || IntentCompat.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE.equals(action)) && (stringArrayExtra = intent.getStringArrayExtra(IntentCompat.EXTRA_CHANGED_PACKAGE_LIST)) != null && stringArrayExtra.length != 0 && IntentCompat.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE.equals(action)) {
                for (String str : stringArrayExtra) {
                    ApplicationsState.this.removePackage(str);
                    ApplicationsState.this.addPackage(str);
                }
            }
        }
    }

    class MainHandler extends Handler {
        static final int MSG_ALL_SIZES_COMPUTED = 5;
        static final int MSG_PACKAGE_ICON_CHANGED = 3;
        static final int MSG_PACKAGE_LIST_CHANGED = 2;
        static final int MSG_PACKAGE_SIZE_CHANGED = 4;
        static final int MSG_REBUILD_COMPLETE = 1;
        static final int MSG_RUNNING_STATE_CHANGED = 6;

        MainHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (ApplicationsState.this.mCurCallbacks != null) {
                        ApplicationsState.this.mCurCallbacks.onRebuildComplete((ArrayList) message.obj);
                    }
                    break;
                case 2:
                    if (ApplicationsState.this.mCurCallbacks != null) {
                        ApplicationsState.this.mCurCallbacks.onPackageListChanged();
                    }
                    break;
                case 3:
                    if (ApplicationsState.this.mCurCallbacks != null) {
                        ApplicationsState.this.mCurCallbacks.onPackageIconChanged();
                    }
                    break;
                case 4:
                    if (ApplicationsState.this.mCurCallbacks != null) {
                        ApplicationsState.this.mCurCallbacks.onPackageSizeChanged((String) message.obj);
                    }
                    break;
                case 5:
                    if (ApplicationsState.this.mCurCallbacks != null) {
                        ApplicationsState.this.mCurCallbacks.onAllSizesComputed();
                    }
                    break;
                case 6:
                    if (ApplicationsState.this.mCurCallbacks != null) {
                        ApplicationsState.this.mCurCallbacks.onRunningStateChanged(message.arg1 != 0);
                    }
                    break;
            }
        }
    }

    static ApplicationsState getInstance(Application application) {
        ApplicationsState applicationsState;
        synchronized (sLock) {
            if (sInstance == null) {
                sInstance = new ApplicationsState(application);
            }
            applicationsState = sInstance;
        }
        return applicationsState;
    }

    private ApplicationsState(Application application) {
        this.mContext = application;
        this.mPm = this.mContext.getPackageManager();
        this.storageStatsManager = (StorageStatsManager) this.mContext.getSystemService("storagestats");
        this.storageManager = (StorageManager) this.mContext.getSystemService("storage");
        this.mThread.start();
        this.mBackgroundHandler = new BackgroundHandler(this.mThread.getLooper());
        synchronized (this.mEntriesMap) {
            try {
                this.mEntriesMap.wait(1L);
            } catch (InterruptedException unused) {
            }
        }
    }

    void resume(Callbacks callbacks) {
        Log.v(TAG, "resume about to acquire lock...");
        synchronized (this.mEntriesMap) {
            this.mCurCallbacks = callbacks;
            this.mResumed = true;
            if (this.mPackageIntentReceiver == null) {
                this.mPackageIntentReceiver = new PackageIntentReceiver();
                this.mPackageIntentReceiver.registerReceiver();
            }
            this.mApplications = this.mPm.getInstalledApplications(8704);
            if (this.mApplications == null) {
                this.mApplications = new ArrayList();
            }
            for (int i = 0; i < this.mAppEntries.size(); i++) {
                this.mAppEntries.get(i).sizeStale = true;
            }
            for (int i2 = 0; i2 < this.mApplications.size(); i2++) {
                ApplicationInfo applicationInfo = this.mApplications.get(i2);
                AppEntry appEntry = this.mEntriesMap.get(applicationInfo.packageName);
                if (appEntry != null) {
                    appEntry.info = applicationInfo;
                }
            }
            this.mCurComputingSizePkg = null;
            if (!this.mBackgroundHandler.hasMessages(2)) {
                this.mBackgroundHandler.sendEmptyMessage(2);
            }
            Log.v(TAG, "...resume releasing lock");
        }
    }

    void pause() {
        Log.v(TAG, "pause about to acquire lock...");
        synchronized (this.mEntriesMap) {
            this.mCurCallbacks = null;
            this.mResumed = false;
            Log.v(TAG, "...pause releasing lock");
        }
    }

    ArrayList<AppEntry> rebuild(AppFilter appFilter, Comparator<AppEntry> comparator) {
        ArrayList<AppEntry> arrayList;
        synchronized (this.mRebuildSync) {
            this.mRebuildRequested = true;
            this.mRebuildAsync = false;
            this.mRebuildFilter = appFilter;
            this.mRebuildComparator = comparator;
            this.mRebuildResult = null;
            if (!this.mBackgroundHandler.hasMessages(1)) {
                this.mBackgroundHandler.sendEmptyMessage(1);
            }
            long jUptimeMillis = SystemClock.uptimeMillis() + 250;
            while (this.mRebuildResult == null) {
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                if (jUptimeMillis2 >= jUptimeMillis) {
                    break;
                }
                try {
                    this.mRebuildSync.wait(jUptimeMillis - jUptimeMillis2);
                } catch (InterruptedException unused) {
                }
            }
            this.mRebuildAsync = true;
            arrayList = this.mRebuildResult;
        }
        return arrayList;
    }

    void handleRebuildList() {
        ArrayList arrayList;
        synchronized (this.mRebuildSync) {
            if (this.mRebuildRequested) {
                AppFilter appFilter = this.mRebuildFilter;
                Comparator<AppEntry> comparator = this.mRebuildComparator;
                this.mRebuildRequested = false;
                this.mRebuildFilter = null;
                this.mRebuildComparator = null;
                Process.setThreadPriority(-2);
                if (appFilter != null) {
                    appFilter.init();
                }
                synchronized (this.mEntriesMap) {
                    arrayList = new ArrayList(this.mApplications);
                }
                ArrayList<AppEntry> arrayList2 = new ArrayList<>();
                Log.i(TAG, "Rebuilding...");
                for (int i = 0; i < arrayList.size(); i++) {
                    ApplicationInfo applicationInfo = (ApplicationInfo) arrayList.get(i);
                    if (appFilter == null || appFilter.filterApp(applicationInfo)) {
                        synchronized (this.mEntriesMap) {
                            Log.v(TAG, "rebuild acquired lock");
                            AppEntry entryLocked = getEntryLocked(applicationInfo);
                            entryLocked.ensureLabel(this.mContext);
                            Log.i(TAG, "Using " + applicationInfo.packageName + ": " + entryLocked);
                            arrayList2.add(entryLocked);
                            Log.v(TAG, "rebuild releasing lock");
                        }
                    }
                }
                Collections.sort(arrayList2, comparator);
                synchronized (this.mRebuildSync) {
                    if (!this.mRebuildRequested) {
                        if (!this.mRebuildAsync) {
                            this.mRebuildResult = arrayList2;
                            this.mRebuildSync.notifyAll();
                        } else if (!this.mMainHandler.hasMessages(1)) {
                            this.mMainHandler.sendMessage(this.mMainHandler.obtainMessage(1, arrayList2));
                        }
                    }
                }
                Process.setThreadPriority(10);
            }
        }
    }

    AppEntry getEntry(String str) {
        AppEntry entryLocked;
        Log.v(TAG, "getEntry about to acquire lock...");
        synchronized (this.mEntriesMap) {
            entryLocked = this.mEntriesMap.get(str);
            if (entryLocked == null) {
                for (int i = 0; i < this.mApplications.size(); i++) {
                    ApplicationInfo applicationInfo = this.mApplications.get(i);
                    if (str.equals(applicationInfo.packageName)) {
                        entryLocked = getEntryLocked(applicationInfo);
                        break;
                    }
                }
            }
            Log.v(TAG, "...getEntry releasing lock");
        }
        return entryLocked;
    }

    void ensureIcon(AppEntry appEntry) {
        if (appEntry.icon != null) {
            return;
        }
        synchronized (appEntry) {
            appEntry.ensureIconLocked(this.mContext, this.mPm);
        }
    }

    void requestSize(String str, ApplicationInfo applicationInfo) {
        Log.v(TAG, "requestSize about to acquire lock...");
        synchronized (this.mEntriesMap) {
            if (this.mEntriesMap.get(str) != null) {
                loadPackageSize(str, applicationInfo, this.mBackgroundHandler.mStatsObserver);
            }
            Log.v(TAG, "...requestSize releasing lock");
        }
    }

    long sumCacheSizes() {
        long j;
        Log.v(TAG, "sumCacheSizes about to acquire lock...");
        synchronized (this.mEntriesMap) {
            Log.v(TAG, "-> sumCacheSizes now has lock");
            j = 0;
            for (int size = this.mAppEntries.size() - 1; size >= 0; size--) {
                j += this.mAppEntries.get(size).cacheSize;
            }
            Log.v(TAG, "...sumCacheSizes releasing lock");
        }
        return j;
    }

    int indexOfApplicationInfoLocked(String str) {
        for (int size = this.mApplications.size() - 1; size >= 0; size--) {
            if (this.mApplications.get(size).packageName.equals(str)) {
                return size;
            }
        }
        return -1;
    }

    void addPackage(String str) {
        try {
            synchronized (this.mEntriesMap) {
                Log.v(TAG, "addPackage acquired lock");
                Log.i(TAG, "Adding package " + str);
                if (!this.mResumed) {
                    Log.v(TAG, "addPackage release lock: not resumed");
                    return;
                }
                if (indexOfApplicationInfoLocked(str) >= 0) {
                    Log.i(TAG, "Package already exists!");
                    Log.v(TAG, "addPackage release lock: already exists");
                    return;
                }
                this.mApplications.add(this.mPm.getApplicationInfo(str, 8704));
                if (!this.mBackgroundHandler.hasMessages(2)) {
                    this.mBackgroundHandler.sendEmptyMessage(2);
                }
                if (!this.mMainHandler.hasMessages(2)) {
                    this.mMainHandler.sendEmptyMessage(2);
                }
                Log.v(TAG, "addPackage releasing lock");
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
    }

    void removePackage(String str) {
        synchronized (this.mEntriesMap) {
            Log.v(TAG, "removePackage acquired lock");
            int iIndexOfApplicationInfoLocked = indexOfApplicationInfoLocked(str);
            Log.i(TAG, "removePackage: " + str + " @ " + iIndexOfApplicationInfoLocked);
            if (iIndexOfApplicationInfoLocked >= 0) {
                AppEntry appEntry = this.mEntriesMap.get(str);
                Log.i(TAG, "removePackage: " + appEntry);
                if (appEntry != null) {
                    this.mEntriesMap.remove(str);
                    this.mAppEntries.remove(appEntry);
                }
                this.mApplications.remove(iIndexOfApplicationInfoLocked);
                if (!this.mMainHandler.hasMessages(2)) {
                    this.mMainHandler.sendEmptyMessage(2);
                }
            }
            Log.v(TAG, "removePackage releasing lock");
        }
    }

    AppEntry getEntryLocked(ApplicationInfo applicationInfo) {
        AppEntry appEntry = this.mEntriesMap.get(applicationInfo.packageName);
        Log.i(TAG, "Looking up entry of pkg " + applicationInfo.packageName + ": " + appEntry);
        if (appEntry == null) {
            Log.i(TAG, "Creating AppEntry for " + applicationInfo.packageName);
            Context context = this.mContext;
            long j = this.mCurId;
            this.mCurId = 1 + j;
            AppEntry appEntry2 = new AppEntry(context, applicationInfo, j);
            this.mEntriesMap.put(applicationInfo.packageName, appEntry2);
            this.mAppEntries.add(appEntry2);
            return appEntry2;
        }
        if (appEntry.info == applicationInfo) {
            return appEntry;
        }
        appEntry.info = applicationInfo;
        return appEntry;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getTotalSize(PackageStats packageStats) {
        if (packageStats != null) {
            return packageStats.codeSize + packageStats.dataSize;
        }
        return -2L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getSizeStr(long j, String str) {
        if (j >= 0) {
            return Utils.formatSize(j, str);
        }
        return null;
    }

    class BackgroundHandler extends Handler {
        static final int MSG_LOAD_ENTRIES = 2;
        static final int MSG_LOAD_ICONS = 3;
        static final int MSG_LOAD_SIZES = 4;
        static final int MSG_REBUILD_LIST = 1;
        boolean mRunning;
        final IPackageStatsObserver.Stub mStatsObserver;

        BackgroundHandler(Looper looper) {
            super(looper);
            this.mStatsObserver = new IPackageStatsObserver.Stub() { // from class: com.rk_itvui.settings.dialog.ApplicationsState.BackgroundHandler.1
                public void onGetStatsCompleted(PackageStats packageStats, boolean z) {
                    boolean z2;
                    Log.v(ApplicationsState.TAG, "onGetStatsCompleted acquired 111：：" + z);
                    if (z) {
                        synchronized (ApplicationsState.this.mEntriesMap) {
                            Log.v(ApplicationsState.TAG, "onGetStatsCompleted acquired lock：：" + z);
                            AppEntry appEntry = ApplicationsState.this.mEntriesMap.get(packageStats.packageName);
                            if (appEntry != null) {
                                synchronized (appEntry) {
                                    z2 = false;
                                    appEntry.sizeStale = false;
                                    appEntry.sizeLoadStart = 0L;
                                    long totalSize = ApplicationsState.this.getTotalSize(packageStats);
                                    if (appEntry.size != totalSize || appEntry.cacheSize != packageStats.cacheSize || appEntry.codeSize != packageStats.codeSize || appEntry.dataSize != packageStats.dataSize) {
                                        appEntry.size = totalSize;
                                        appEntry.cacheSize = packageStats.cacheSize;
                                        appEntry.codeSize = packageStats.codeSize;
                                        appEntry.dataSize = packageStats.dataSize;
                                        appEntry.sizeStr = ApplicationsState.this.getSizeStr(appEntry.size, "mStatsObserver");
                                        Log.i(ApplicationsState.TAG, "Set size of " + appEntry.label + " " + appEntry + ": " + appEntry.sizeStr);
                                        z2 = true;
                                    }
                                }
                                if (z2) {
                                    ApplicationsState.this.mMainHandler.sendMessage(ApplicationsState.this.mMainHandler.obtainMessage(4, packageStats.packageName));
                                }
                            }
                            if (ApplicationsState.this.mCurComputingSizePkg == null || ApplicationsState.this.mCurComputingSizePkg.equals(packageStats.packageName)) {
                                ApplicationsState.this.mCurComputingSizePkg = null;
                                BackgroundHandler.this.sendEmptyMessage(4);
                            }
                            Log.v(ApplicationsState.TAG, "onGetStatsCompleted releasing lock");
                        }
                    }
                }
            };
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            int i2;
            int i3;
            ApplicationsState.this.handleRebuildList();
            int i4 = 0;
            switch (message.what) {
                case 1:
                default:
                    return;
                case 2:
                    synchronized (ApplicationsState.this.mEntriesMap) {
                        Log.v(ApplicationsState.TAG, "MSG_LOAD_ENTRIES acquired lock");
                        i = 0;
                        while (i4 < ApplicationsState.this.mApplications.size() && i < 6) {
                            if (!this.mRunning) {
                                this.mRunning = true;
                                ApplicationsState.this.mMainHandler.sendMessage(ApplicationsState.this.mMainHandler.obtainMessage(6, 1));
                            }
                            ApplicationInfo applicationInfo = ApplicationsState.this.mApplications.get(i4);
                            if (ApplicationsState.this.mEntriesMap.get(applicationInfo.packageName) == null) {
                                i++;
                                ApplicationsState.this.getEntryLocked(applicationInfo);
                            }
                            i4++;
                        }
                        Log.v(ApplicationsState.TAG, "MSG_LOAD_ENTRIES releasing lock");
                        break;
                    }
                    if (i >= 6) {
                        sendEmptyMessage(2);
                        return;
                    } else {
                        sendEmptyMessage(3);
                        return;
                    }
                case 3:
                    synchronized (ApplicationsState.this.mEntriesMap) {
                        Log.v(ApplicationsState.TAG, "MSG_LOAD_ICONS acquired lock");
                        i2 = 0;
                        while (i4 < ApplicationsState.this.mAppEntries.size() && i2 < 2) {
                            AppEntry appEntry = ApplicationsState.this.mAppEntries.get(i4);
                            if (appEntry.icon == null || !appEntry.mounted) {
                                synchronized (appEntry) {
                                    if (appEntry.ensureIconLocked(ApplicationsState.this.mContext, ApplicationsState.this.mPm)) {
                                        if (!this.mRunning) {
                                            this.mRunning = true;
                                            ApplicationsState.this.mMainHandler.sendMessage(ApplicationsState.this.mMainHandler.obtainMessage(6, 1));
                                        }
                                        i2++;
                                    }
                                }
                            }
                            i4++;
                        }
                        Log.v(ApplicationsState.TAG, "MSG_LOAD_ICONS releasing lock");
                    }
                    if (i2 > 0 && !ApplicationsState.this.mMainHandler.hasMessages(3)) {
                        ApplicationsState.this.mMainHandler.sendEmptyMessage(3);
                    }
                    if (i2 >= 2) {
                        sendEmptyMessage(3);
                        return;
                    } else {
                        sendEmptyMessage(4);
                        return;
                    }
                case 4:
                    synchronized (ApplicationsState.this.mEntriesMap) {
                        Log.v(ApplicationsState.TAG, "MSG_LOAD_SIZES acquired lock");
                        if (ApplicationsState.this.mCurComputingSizePkg != null) {
                            Log.v(ApplicationsState.TAG, "MSG_LOAD_SIZES releasing: currently computing");
                            return;
                        }
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        for (0; i3 < ApplicationsState.this.mAppEntries.size(); i3 + 1) {
                            AppEntry appEntry2 = ApplicationsState.this.mAppEntries.get(i3);
                            i3 = (appEntry2.size == -1 || appEntry2.sizeStale) ? 0 : i3 + 1;
                            if (appEntry2.sizeLoadStart == 0 || appEntry2.sizeLoadStart < jUptimeMillis - 20000) {
                                if (!this.mRunning) {
                                    this.mRunning = true;
                                    ApplicationsState.this.mMainHandler.sendMessage(ApplicationsState.this.mMainHandler.obtainMessage(6, 1));
                                }
                                appEntry2.sizeLoadStart = jUptimeMillis;
                                ApplicationsState.this.mCurComputingSizePkg = appEntry2.info.packageName;
                                ApplicationsState.this.loadPackageSize(ApplicationsState.this.mCurComputingSizePkg, appEntry2.info, this.mStatsObserver);
                            }
                            Log.v(ApplicationsState.TAG, "MSG_LOAD_SIZES releasing: now computing");
                            return;
                        }
                        if (!ApplicationsState.this.mMainHandler.hasMessages(5)) {
                            ApplicationsState.this.mMainHandler.sendEmptyMessage(5);
                            this.mRunning = false;
                            ApplicationsState.this.mMainHandler.sendMessage(ApplicationsState.this.mMainHandler.obtainMessage(6, 0));
                        }
                        Log.v(ApplicationsState.TAG, "MSG_LOAD_SIZES releasing lock");
                        return;
                    }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadPackageSize(String str, ApplicationInfo applicationInfo, IPackageStatsObserver iPackageStatsObserver) {
        try {
            StorageStats storageStatsQueryStatsForPackage = ((StorageStatsManager) this.mContext.getSystemService(StorageStatsManager.class)).queryStatsForPackage((UUID) ReflectionUtils.getPrivateFieldValue(this.mContext.getPackageManager().getApplicationInfo(str, 0), "storageUuid"), str, UserHandle.SYSTEM);
            Log.e(TAG, "getAppBytes=" + storageStatsQueryStatsForPackage.getAppBytes());
            Log.e(TAG, "getDataBytes=" + storageStatsQueryStatsForPackage.getDataBytes());
            Log.e(TAG, "getCacheBytes=" + storageStatsQueryStatsForPackage.getCacheBytes());
            PackageStats packageStats = new PackageStats(str);
            packageStats.cacheSize = storageStatsQueryStatsForPackage.getCacheBytes();
            packageStats.dataSize = storageStatsQueryStatsForPackage.getDataBytes();
            packageStats.codeSize = storageStatsQueryStatsForPackage.getAppBytes();
            iPackageStatsObserver.onGetStatsCompleted(packageStats, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
