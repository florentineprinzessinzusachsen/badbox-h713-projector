package com.hs.p.q;

import android.content.Context;
import android.content.Intent;
import com.hs.p.basic.BasicProcessor;
import com.hs.p.basic.Settings;
import com.hs.p.common.PROP;
import com.hs.p.common.utils.LOG;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class QP extends BasicProcessor {
    public static final String ID = "QP";

    public QP() {
        super(ID, ID, true);
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected long getLastHandleTime(Context context) {
        return Settings.getLastConfigTime(context, 0L);
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected long getPeriods(Context context) {
        return PROP.isBeta() ? 20L : 3600L;
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected void onTimeHandle(Context context, Intent intent) {
        try {
            ConfigBean configBeanRequest = new GetConfigApi(context).request();
            if (configBeanRequest != null) {
                ConfigHandler.handle(context, configBeanRequest);
            }
        } catch (Exception e) {
            LOG.e(this.TAG, "get configuration failed: " + e);
        }
    }

    @Override // com.hs.p.basic.BasicProcessor
    protected void putLastHandleTime(Context context, long j) {
        Settings.putLastConfigTime(context, j);
    }
}
