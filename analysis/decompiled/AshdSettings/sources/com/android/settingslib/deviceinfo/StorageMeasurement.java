package com.android.settingslib.deviceinfo;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.pm.UserInfo;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.UserHandle;
import android.os.UserManager;
import android.os.storage.VolumeInfo;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseLongArray;
import com.android.internal.app.IMediaContainerService;
import com.android.internal.util.ArrayUtils;
import com.google.android.collect.Sets;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class StorageMeasurement {
    private static final boolean LOCAL_LOGV = false;
    static final boolean LOGV = false;
    private static final String TAG = "StorageMeasurement";
    private final Context mContext;
    private final MainHandler mMainHandler;
    private final MeasurementHandler mMeasurementHandler;
    private WeakReference<MeasurementReceiver> mReceiver;
    private final VolumeInfo mSharedVolume;
    private final VolumeInfo mVolume;
    private static final String DEFAULT_CONTAINER_PACKAGE = "com.android.defcontainer";
    public static final ComponentName DEFAULT_CONTAINER_COMPONENT = new ComponentName(DEFAULT_CONTAINER_PACKAGE, "com.android.defcontainer.DefaultContainerService");
    private static final Set<String> sMeasureMediaTypes = Sets.newHashSet(new String[]{Environment.DIRECTORY_DCIM, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_PICTURES, Environment.DIRECTORY_MUSIC, Environment.DIRECTORY_ALARMS, Environment.DIRECTORY_NOTIFICATIONS, Environment.DIRECTORY_RINGTONES, Environment.DIRECTORY_PODCASTS, Environment.DIRECTORY_DOWNLOADS, "Android"});

    public interface MeasurementReceiver {
        void onDetailsChanged(MeasurementDetails measurementDetails);
    }

    public static class MeasurementDetails {
        public long availSize;
        public long cacheSize;
        public long totalSize;
        public SparseLongArray appsSize = new SparseLongArray();
        public SparseArray<HashMap<String, Long>> mediaSize = new SparseArray<>();
        public SparseLongArray miscSize = new SparseLongArray();
        public SparseLongArray usersSize = new SparseLongArray();

        public String toString() {
            return "MeasurementDetails: [totalSize: " + this.totalSize + " availSize: " + this.availSize + " cacheSize: " + this.cacheSize + " mediaSize: " + this.mediaSize + " miscSize: " + this.miscSize + "usersSize: " + this.usersSize + "]";
        }
    }

    public StorageMeasurement(Context context, VolumeInfo volumeInfo, VolumeInfo volumeInfo2) {
        this.mContext = context.getApplicationContext();
        this.mVolume = volumeInfo;
        this.mSharedVolume = volumeInfo2;
        HandlerThread handlerThread = new HandlerThread("MemoryMeasurement");
        handlerThread.start();
        this.mMainHandler = new MainHandler();
        this.mMeasurementHandler = new MeasurementHandler(handlerThread.getLooper());
    }

    public void setReceiver(MeasurementReceiver measurementReceiver) {
        if (this.mReceiver == null || this.mReceiver.get() == null) {
            this.mReceiver = new WeakReference<>(measurementReceiver);
        }
    }

    public void forceMeasure() {
        invalidate();
        measure();
    }

    public void measure() {
        if (this.mMeasurementHandler.hasMessages(1)) {
            return;
        }
        this.mMeasurementHandler.sendEmptyMessage(1);
    }

    public void onDestroy() {
        this.mReceiver = null;
        this.mMeasurementHandler.removeMessages(1);
        this.mMeasurementHandler.sendEmptyMessage(3);
    }

    private void invalidate() {
        this.mMeasurementHandler.sendEmptyMessage(5);
    }

    private static class StatsObserver extends IPackageStatsObserver.Stub {
        private final int mCurrentUser;
        private final MeasurementDetails mDetails;
        private final Message mFinished;
        private final boolean mIsPrivate;
        private int mRemaining;

        public StatsObserver(boolean z, MeasurementDetails measurementDetails, int i, List<UserInfo> list, Message message, int i2) {
            this.mIsPrivate = z;
            this.mDetails = measurementDetails;
            this.mCurrentUser = i;
            if (z) {
                Iterator<UserInfo> it = list.iterator();
                while (it.hasNext()) {
                    this.mDetails.appsSize.put(it.next().id, 0L);
                }
            }
            this.mFinished = message;
            this.mRemaining = i2;
        }

        public void onGetStatsCompleted(PackageStats packageStats, boolean z) {
            synchronized (this.mDetails) {
                if (z) {
                    try {
                        addStatsLocked(packageStats);
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                int i = this.mRemaining - 1;
                this.mRemaining = i;
                if (i == 0) {
                    this.mFinished.sendToTarget();
                }
            }
        }

        private void addStatsLocked(PackageStats packageStats) {
            if (!this.mIsPrivate) {
                StorageMeasurement.addValue(this.mDetails.appsSize, this.mCurrentUser, packageStats.externalCodeSize + packageStats.externalDataSize + packageStats.externalMediaSize + packageStats.externalObbSize);
                this.mDetails.cacheSize += packageStats.externalCacheSize;
                return;
            }
            long j = packageStats.codeSize;
            long j2 = packageStats.dataSize;
            long j3 = packageStats.cacheSize;
            if (Environment.isExternalStorageEmulated()) {
                j += packageStats.externalCodeSize + packageStats.externalObbSize;
                j2 += packageStats.externalDataSize + packageStats.externalMediaSize;
                j3 += packageStats.externalCacheSize;
            }
            StorageMeasurement.addValueIfKeyExists(this.mDetails.appsSize, packageStats.userHandle, j + j2);
            StorageMeasurement.addValue(this.mDetails.usersSize, packageStats.userHandle, j2);
            this.mDetails.cacheSize += j3;
        }
    }

    private class MainHandler extends Handler {
        private MainHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            MeasurementDetails measurementDetails = (MeasurementDetails) message.obj;
            MeasurementReceiver measurementReceiver = StorageMeasurement.this.mReceiver != null ? (MeasurementReceiver) StorageMeasurement.this.mReceiver.get() : null;
            if (measurementReceiver != null) {
                measurementReceiver.onDetailsChanged(measurementDetails);
            }
        }
    }

    private class MeasurementHandler extends Handler {
        public static final int MSG_COMPLETED = 4;
        public static final int MSG_CONNECTED = 2;
        public static final int MSG_DISCONNECT = 3;
        public static final int MSG_INVALIDATE = 5;
        public static final int MSG_MEASURE = 1;
        private volatile boolean mBound;
        private MeasurementDetails mCached;
        private final ServiceConnection mDefContainerConn;
        private IMediaContainerService mDefaultContainer;
        private Object mLock;

        public MeasurementHandler(Looper looper) {
            super(looper);
            this.mLock = new Object();
            this.mBound = false;
            this.mDefContainerConn = new ServiceConnection() { // from class: com.android.settingslib.deviceinfo.StorageMeasurement.MeasurementHandler.1
                @Override // android.content.ServiceConnection
                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    IMediaContainerService iMediaContainerServiceAsInterface = IMediaContainerService.Stub.asInterface(iBinder);
                    MeasurementHandler.this.mDefaultContainer = iMediaContainerServiceAsInterface;
                    MeasurementHandler.this.mBound = true;
                    MeasurementHandler.this.sendMessage(MeasurementHandler.this.obtainMessage(2, iMediaContainerServiceAsInterface));
                }

                @Override // android.content.ServiceConnection
                public void onServiceDisconnected(ComponentName componentName) {
                    MeasurementHandler.this.mBound = false;
                    MeasurementHandler.this.removeMessages(2);
                }
            };
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (this.mCached != null) {
                        StorageMeasurement.this.mMainHandler.obtainMessage(0, this.mCached).sendToTarget();
                        return;
                    }
                    synchronized (this.mLock) {
                        if (this.mBound) {
                            removeMessages(3);
                            sendMessage(obtainMessage(2, this.mDefaultContainer));
                        } else {
                            StorageMeasurement.this.mContext.bindServiceAsUser(new Intent().setComponent(StorageMeasurement.DEFAULT_CONTAINER_COMPONENT), this.mDefContainerConn, 1, UserHandle.SYSTEM);
                        }
                        break;
                    }
                    return;
                case 2:
                    StorageMeasurement.this.measureExactStorage((IMediaContainerService) message.obj);
                    return;
                case 3:
                    synchronized (this.mLock) {
                        if (this.mBound) {
                            this.mBound = false;
                            StorageMeasurement.this.mContext.unbindService(this.mDefContainerConn);
                        }
                        break;
                    }
                    return;
                case 4:
                    this.mCached = (MeasurementDetails) message.obj;
                    StorageMeasurement.this.mMainHandler.obtainMessage(0, this.mCached).sendToTarget();
                    return;
                case 5:
                    this.mCached = null;
                    return;
                default:
                    return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void measureExactStorage(IMediaContainerService iMediaContainerService) {
        UserManager userManager = (UserManager) this.mContext.getSystemService(UserManager.class);
        PackageManager packageManager = this.mContext.getPackageManager();
        List<UserInfo> users = userManager.getUsers();
        List enabledProfiles = userManager.getEnabledProfiles(ActivityManager.getCurrentUser());
        MeasurementDetails measurementDetails = new MeasurementDetails();
        Message messageObtainMessage = this.mMeasurementHandler.obtainMessage(4, measurementDetails);
        if (this.mVolume == null || !this.mVolume.isMountedReadable()) {
            messageObtainMessage.sendToTarget();
            return;
        }
        if (this.mSharedVolume != null && this.mSharedVolume.isMountedReadable()) {
            Iterator it = enabledProfiles.iterator();
            while (it.hasNext()) {
                int i = ((UserInfo) it.next()).id;
                File pathForUser = this.mSharedVolume.getPathForUser(i);
                HashMap<String, Long> map = new HashMap<>(sMeasureMediaTypes.size());
                measurementDetails.mediaSize.put(i, map);
                for (String str : sMeasureMediaTypes) {
                    map.put(str, Long.valueOf(getDirectorySize(iMediaContainerService, new File(pathForUser, str))));
                }
                addValue(measurementDetails.miscSize, i, measureMisc(iMediaContainerService, pathForUser));
            }
            if (this.mSharedVolume.getType() == 2) {
                for (UserInfo userInfo : users) {
                    addValue(measurementDetails.usersSize, userInfo.id, getDirectorySize(iMediaContainerService, this.mSharedVolume.getPathForUser(userInfo.id)));
                }
            }
        }
        File path = this.mVolume.getPath();
        if (path != null) {
            measurementDetails.totalSize = path.getTotalSpace();
            measurementDetails.availSize = path.getFreeSpace();
        }
        if (this.mVolume.getType() == 1) {
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(8704);
            ArrayList arrayList = new ArrayList();
            for (ApplicationInfo applicationInfo : installedApplications) {
                if (Objects.equals(applicationInfo.volumeUuid, this.mVolume.getFsUuid())) {
                    arrayList.add(applicationInfo);
                }
            }
            int size = users.size() * arrayList.size();
            if (size == 0) {
                messageObtainMessage.sendToTarget();
                return;
            }
            StatsObserver statsObserver = new StatsObserver(true, measurementDetails, ActivityManager.getCurrentUser(), enabledProfiles, messageObtainMessage, size);
            for (UserInfo userInfo2 : users) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    packageManager.getPackageSizeInfoAsUser(((ApplicationInfo) it2.next()).packageName, userInfo2.id, statsObserver);
                }
            }
            return;
        }
        messageObtainMessage.sendToTarget();
    }

    private static long getDirectorySize(IMediaContainerService iMediaContainerService, File file) {
        try {
            long jCalculateDirectorySize = iMediaContainerService.calculateDirectorySize(file.toString());
            if (LOGV) {
                Log.v(TAG, "getDirectorySize(" + file + ") returned " + jCalculateDirectorySize);
            }
            return jCalculateDirectorySize;
        } catch (Exception e) {
            Log.w(TAG, "Could not read memory from default container service for " + file, e);
            return 0L;
        }
    }

    private long measureMisc(IMediaContainerService iMediaContainerService, File file) {
        File[] fileArrListFiles = file.listFiles();
        long directorySize = 0;
        if (ArrayUtils.isEmpty(fileArrListFiles)) {
            return 0L;
        }
        for (File file2 : fileArrListFiles) {
            if (!sMeasureMediaTypes.contains(file2.getName())) {
                if (file2.isFile()) {
                    directorySize += file2.length();
                } else if (file2.isDirectory()) {
                    directorySize += getDirectorySize(iMediaContainerService, file2);
                }
            }
        }
        return directorySize;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addValue(SparseLongArray sparseLongArray, int i, long j) {
        sparseLongArray.put(i, sparseLongArray.get(i) + j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addValueIfKeyExists(SparseLongArray sparseLongArray, int i, long j) {
        int iIndexOfKey = sparseLongArray.indexOfKey(i);
        if (iIndexOfKey >= 0) {
            sparseLongArray.put(i, sparseLongArray.valueAt(iIndexOfKey) + j);
        }
    }
}
