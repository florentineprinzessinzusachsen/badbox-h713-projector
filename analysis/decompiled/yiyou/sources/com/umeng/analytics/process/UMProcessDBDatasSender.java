package com.umeng.analytics.process;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.pro.k;
import com.umeng.analytics.pro.n;
import com.umeng.analytics.pro.v;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.utils.FileLockCallback;
import com.umeng.commonsdk.utils.FileLockUtil;
import com.umeng.commonsdk.utils.UMUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UMProcessDBDatasSender implements UMLogDataProtocol {
    public static final int UM_PROCESS_CONSTRUCTMESSAGE = 36946;
    public static final int UM_PROCESS_EVENT_KEY = 36945;
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private static UMProcessDBDatasSender mInstance;
    private Context mContext;
    private List<Integer> mGeneralBodyIds;
    private FileLockUtil mLockUtil = new FileLockUtil();
    private k.c mPolicySelector;

    private class ConstructMessageCallback implements FileLockCallback {
        private ConstructMessageCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) {
            JSONObject jSONObjectBuildEnvelopeWithExtHeader;
            UMProcessDBDatasSender uMProcessDBDatasSender = UMProcessDBDatasSender.this;
            JSONObject jSONObject = uMProcessDBDatasSender.setupReportData(UMEnvelopeBuild.maxDataSpace(uMProcessDBDatasSender.mContext));
            if (jSONObject != null && jSONObject.length() >= 1) {
                JSONObject jSONObject2 = (JSONObject) jSONObject.opt("header");
                JSONObject jSONObject3 = (JSONObject) jSONObject.opt("content");
                if (UMProcessDBDatasSender.this.mContext != null && jSONObject2 != null && jSONObject3 != null && (jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(UMProcessDBDatasSender.this.mContext, jSONObject2, jSONObject3)) != null) {
                    UMProcessDBDatasSender.this.removeCacheData(jSONObjectBuildEnvelopeWithExtHeader);
                }
            }
            return true;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            return false;
        }
    }

    private class ReplaceCallback implements FileLockCallback {
        private ReplaceCallback() {
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(File file, int i) {
            return false;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str) throws Throwable {
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            if (str.startsWith(a.f3762c)) {
                str = str.replaceFirst(a.f3762c, "");
            }
            UMProcessDBHelper.getInstance(UMProcessDBDatasSender.this.mContext).deleteEventDatas(str.replace(a.f3763d, ""), null, null);
            return true;
        }

        @Override // com.umeng.commonsdk.utils.FileLockCallback
        public boolean onFileLock(String str, Object obj) {
            return false;
        }
    }

    private UMProcessDBDatasSender() {
    }

    private void constructMessage() {
        if (defconProcesserHandler() != 0) {
            return;
        }
        this.mLockUtil.doFileOperateion(b.b(this.mContext, ""), new ConstructMessageCallback());
    }

    private int defconProcesserHandler() throws Throwable {
        int iA = n.a().a(this.mContext);
        if (iA != 0) {
            try {
                DBFileTraversalUtil.traverseDBFiles(b.a(this.mContext), new ReplaceCallback(), null);
            } catch (Exception unused) {
            }
            UMProcessDBHelper.getInstance(this.mContext).deleteEventDatas(a.h, null, null);
        }
        return iA;
    }

    private JSONObject generalBody() {
        JSONObject mainEvents = null;
        try {
            if (this.mGeneralBodyIds == null) {
                this.mGeneralBodyIds = new ArrayList();
            }
            mainEvents = UMProcessDBHelper.getInstance(this.mContext).readMainEvents(UMEnvelopeBuild.maxDataSpace(this.mContext) - 2000, this.mGeneralBodyIds);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.mContext);
            if (sharedPreferences != null) {
                String string = sharedPreferences.getString("userlevel", "");
                if (!TextUtils.isEmpty(string)) {
                    mainEvents.put("userlevel", string);
                }
            }
            String[] strArrA = com.umeng.analytics.c.a(this.mContext);
            if (strArrA != null && !TextUtils.isEmpty(strArrA[0]) && !TextUtils.isEmpty(strArrA[1])) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(com.umeng.analytics.pro.b.L, strArrA[0]);
                jSONObject.put(com.umeng.analytics.pro.b.M, strArrA[1]);
                if (jSONObject.length() > 0) {
                    mainEvents.put(com.umeng.analytics.pro.b.K, jSONObject);
                }
            }
            if (ABTest.getService(this.mContext).isInTest()) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(ABTest.getService(this.mContext).getTestName(), ABTest.getService(this.mContext).getGroupInfo());
                mainEvents.put(com.umeng.analytics.pro.b.J, jSONObject2);
            }
        } catch (Throwable unused) {
        }
        return mainEvents;
    }

    private JSONObject generalHeader() {
        String strOptString;
        JSONObject versionInfoFromColumId;
        JSONObject jSONObject = new JSONObject();
        try {
            if (AnalyticsConfig.mWrapperType != null && AnalyticsConfig.mWrapperVersion != null) {
                jSONObject.put("wrapper_version", AnalyticsConfig.mWrapperVersion);
                jSONObject.put("wrapper_type", AnalyticsConfig.mWrapperType);
            }
            jSONObject.put(com.umeng.analytics.pro.b.i, AnalyticsConfig.getVerticalType(this.mContext));
            jSONObject.put("sdk_version", v.f3732a);
            String strOptString2 = "";
            if (this.mGeneralBodyIds.size() <= 0 || (versionInfoFromColumId = UMProcessDBHelper.getInstance(this.mContext).readVersionInfoFromColumId(this.mGeneralBodyIds.get(0))) == null) {
                strOptString = "";
            } else {
                strOptString2 = versionInfoFromColumId.optString("__av");
                strOptString = versionInfoFromColumId.optString("__vc");
            }
            if (TextUtils.isEmpty(strOptString2)) {
                jSONObject.put("app_version", UMUtils.getAppVersionName(this.mContext));
            } else {
                jSONObject.put("app_version", strOptString2);
            }
            if (TextUtils.isEmpty(strOptString)) {
                jSONObject.put("version_code", UMUtils.getAppVersionCode(this.mContext));
            } else {
                jSONObject.put("version_code", strOptString);
            }
            String strMD5 = HelperUtils.MD5(AnalyticsConfig.getSecretKey(this.mContext));
            if (!TextUtils.isEmpty(strMD5)) {
                jSONObject.put("secret", strMD5);
            }
            String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.mContext, "pr_ve", null);
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.mContext);
            jSONObject.put(com.umeng.analytics.pro.b.l, UMEnvelopeBuild.imprintProperty(this.mContext, "pr_ve", null));
            jSONObject.put(com.umeng.analytics.pro.b.m, UMEnvelopeBuild.imprintProperty(this.mContext, "ud_da", null));
            jSONObject.put(com.umeng.analytics.pro.b.ae, "1.0.0");
            if (TextUtils.isEmpty(strImprintProperty)) {
                jSONObject.put(com.umeng.analytics.pro.b.l, sharedPreferences.getString("vers_pre_version", "0"));
                jSONObject.put(com.umeng.analytics.pro.b.m, sharedPreferences.getString("vers_date", new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date(System.currentTimeMillis()))));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return jSONObject;
    }

    public static UMProcessDBDatasSender getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UMProcessDBDatasSender.class) {
                if (mInstance == null) {
                    mInstance = new UMProcessDBDatasSender();
                }
            }
        }
        UMProcessDBDatasSender uMProcessDBDatasSender = mInstance;
        uMProcessDBDatasSender.mContext = context;
        return uMProcessDBDatasSender;
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
        List<Integer> list;
        JSONObject jSONObjectOptJSONObject;
        if (obj == null || (list = this.mGeneralBodyIds) == null || list.size() == 0) {
            return;
        }
        JSONObject jSONObject = (JSONObject) obj;
        if (!jSONObject.has("analytics") || (jSONObjectOptJSONObject = jSONObject.optJSONObject("analytics")) == null || jSONObjectOptJSONObject.length() <= 0 || !jSONObjectOptJSONObject.has(com.umeng.analytics.pro.b.R)) {
            return;
        }
        UMProcessDBHelper.getInstance(this.mContext).deleteMainProcessEventDatasByIds(this.mGeneralBodyIds);
        this.mGeneralBodyIds.clear();
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        int iA = n.a().a(this.mContext);
        JSONObject jSONObjectGeneralBody = generalBody();
        if (jSONObjectGeneralBody.length() <= 0) {
            return null;
        }
        JSONObject jSONObjectGeneralHeader = generalHeader();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            if (iA == 3) {
                jSONObject2.put("analytics", new JSONObject());
            } else if (jSONObjectGeneralBody != null && jSONObjectGeneralBody.length() > 0) {
                jSONObject2.put("analytics", jSONObjectGeneralBody);
            }
            if (jSONObjectGeneralHeader != null && jSONObjectGeneralHeader.length() > 0) {
                jSONObject.put("header", jSONObjectGeneralHeader);
            }
            if (jSONObject2.length() > 0) {
                if (jSONObject2.has("analytics")) {
                    JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("analytics");
                    if (jSONObjectOptJSONObject.length() == 1 && (jSONObjectOptJSONObject.optJSONObject(com.umeng.analytics.pro.b.K) != null || !TextUtils.isEmpty(jSONObjectOptJSONObject.optString("userlevel")))) {
                        return null;
                    }
                    if (jSONObjectOptJSONObject.length() == 2 && jSONObjectOptJSONObject.optJSONObject(com.umeng.analytics.pro.b.K) != null && !TextUtils.isEmpty(jSONObjectOptJSONObject.optString("userlevel"))) {
                        return null;
                    }
                }
                jSONObject.put("content", jSONObject2);
            }
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) {
        if (UMGlobalContext.getInstance().isMainProcess(this.mContext)) {
            switch (i) {
                case UM_PROCESS_EVENT_KEY /* 36945 */:
                    executor.schedule(new Runnable() { // from class: com.umeng.analytics.process.UMProcessDBDatasSender.1
                        @Override // java.lang.Runnable
                        public void run() {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> call processDBToMain start.");
                            UMProcessDBHelper.getInstance(UMProcessDBDatasSender.this.mContext).processDBToMain();
                        }
                    }, 5L, TimeUnit.SECONDS);
                    break;
                case UM_PROCESS_CONSTRUCTMESSAGE /* 36946 */:
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> recv UM_PROCESS_CONSTRUCTMESSAGE msg.");
                    constructMessage();
                    break;
            }
        }
    }
}
