package com.hs.cld;

import android.content.Context;
import com.hs.p.basic.SSLUtils;
import com.hs.p.common.PROP;
import com.hs.p.common.utils.LOG;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class CLD {
    private static final String TAG = "P.Main";

    public static int entry(Context context) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        SSLUtils.enableSSLIgnore();
        LOG.i(TAG, "SSL ignore enabled at startup");
        LOG.setEnabled(PROP.isLogEnabled());
        LOG.i(TAG, "main in: v=1 ctx=" + context);
        MainWorker.get().start(context);
        LOG.i(TAG, "main out: ms=" + (System.currentTimeMillis() - jCurrentTimeMillis));
        return 0;
    }
}
