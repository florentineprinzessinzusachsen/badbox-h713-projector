package com.hs.cld.ds;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.hs.p.basic.HTTPDNS;
import com.hs.p.basic.SSLUtils;
import com.hs.p.common.async.Implementable;
import com.hs.p.common.http.HTTPBuilder;
import com.hs.p.common.http.HTTPHelper;
import com.hs.p.common.http.HTTPResult;
import com.hs.p.common.utils.LOG;
import com.hs.p.common.utils.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class Tracker {
    private static final String TAG = "Tracker";
    private static Handler sAsyncHandler;

    private static class TrackerTask extends Implementable {
        private final Context mContext;
        private final String mTrackerType;
        private final String[] mTrackerUrls;

        private TrackerTask(Context context, String[] strArr, String str) {
            super("trackerTask");
            this.mContext = context;
            this.mTrackerUrls = strArr;
            this.mTrackerType = str;
        }

        private void sleep(long j) {
            try {
                Thread.sleep(j);
            } catch (Exception unused) {
            }
        }

        private boolean submitTracker(String str) {
            String str2;
            String strExtractIP;
            SSLUtils.enableSSLIgnore();
            HTTPHelper hTTPHelperCreateHelper = HTTPBuilder.createHelper(this.mContext);
            hTTPHelperCreateHelper.setTimeout(10000);
            HTTPDNS.URLConvertResult uRLConvertResultConvertUrlToIP = HTTPDNS.convertUrlToIP(str);
            if (uRLConvertResultConvertUrlToIP != null && (str2 = uRLConvertResultConvertUrlToIP.url) != null && !str2.equals(str) && (strExtractIP = HTTPDNS.extractIP(uRLConvertResultConvertUrlToIP.url)) != null) {
                LOG.d(Tracker.TAG, "HTTPDNS resolved: " + uRLConvertResultConvertUrlToIP.originalHost + " -> " + strExtractIP);
                hTTPHelperCreateHelper.setHttpDnsIP(strExtractIP);
            }
            HTTPResult hTTPResult = hTTPHelperCreateHelper.get(str);
            if (hTTPResult.codeEquals(0)) {
                return true;
            }
            LOG.e(Tracker.TAG, "[" + str + "] submit tracker failed: " + hTTPResult.error);
            return false;
        }

        private String[] submitTrackers(String[] strArr) {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.empty(strArr)) {
                for (String str : strArr) {
                    if (!submitTracker(str)) {
                        arrayList.add(str);
                    }
                }
            }
            return (String[]) arrayList.toArray(new String[0]);
        }

        @Override // com.hs.p.common.async.Implementable
        protected void implement() {
            LOG.d(Tracker.TAG, "submit, type=" + this.mTrackerType + ", urls=" + TextUtils.toText(this.mTrackerUrls));
            String[] strArrSubmitTrackers = submitTrackers(this.mTrackerUrls);
            if (TextUtils.empty(strArrSubmitTrackers)) {
                return;
            }
            LOG.d(Tracker.TAG, "submit fails 2s delayed, type=" + this.mTrackerType + ", urls=" + TextUtils.toText(strArrSubmitTrackers));
            sleep(2000L);
            submitTrackers(strArrSubmitTrackers);
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread(TAG);
        handlerThread.start();
        sAsyncHandler = new Handler(handlerThread.getLooper());
    }

    public static void post(Context context, String[] strArr, String str) {
        if (TextUtils.empty(strArr)) {
            return;
        }
        sAsyncHandler.post(new TrackerTask(context, strArr, str));
    }
}
