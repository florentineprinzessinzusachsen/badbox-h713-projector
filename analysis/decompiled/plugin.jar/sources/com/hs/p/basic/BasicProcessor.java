package com.hs.p.basic;

import android.content.Context;
import android.content.Intent;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.SystemUtils;
import com.hs.p.common.utils.TextUtils;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public abstract class BasicProcessor implements Processor {
    public final String TAG;
    private final String mID;
    private final boolean mNeedNetwork;

    public BasicProcessor(String str, String str2, boolean z) {
        this.TAG = str;
        this.mID = str2;
        this.mNeedNetwork = z;
    }

    protected abstract long getLastHandleTime(Context context);

    protected abstract long getPeriods(Context context);

    @Override // com.hs.p.basic.Processor
    public String myId() {
        return this.mID;
    }

    protected abstract void onTimeHandle(Context context, Intent intent);

    protected void onTimeIdle(Context context, long j, long j2, String str) {
    }

    @Override // com.hs.p.basic.Processor
    public void process(Context context, Intent intent) {
        if (!this.mNeedNetwork || SystemUtils.isNetworkAvailable(context)) {
            if (SLT.isSilent(context)) {
                LOG.i(this.TAG, "app slt to " + SLT.ymd(SLT.millis(context)));
                return;
            }
            long lastHandleTime = getLastHandleTime(context);
            long jCurrentTimeMillis = System.currentTimeMillis();
            long periods = getPeriods(context);
            long j = (jCurrentTimeMillis - lastHandleTime) / 1000;
            if (j < 0) {
                onTimeIdle(context, periods, j, "[" + periods + "] " + j + "s elapsed from last(" + TextUtils.TSTR(lastHandleTime) + ") ...");
                putLastHandleTime(context, jCurrentTimeMillis);
                return;
            }
            if (j >= periods) {
                putLastHandleTime(context, jCurrentTimeMillis);
                onTimeHandle(context, intent);
                return;
            }
            onTimeIdle(context, periods, j, "[" + periods + "] " + j + "s elapsed from last(" + TextUtils.TSTR(lastHandleTime) + ") ...");
        }
    }

    protected abstract void putLastHandleTime(Context context, long j);
}
