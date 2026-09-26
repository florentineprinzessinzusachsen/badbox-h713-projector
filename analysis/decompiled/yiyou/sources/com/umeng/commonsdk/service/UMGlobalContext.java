package com.umeng.commonsdk.service;

import android.content.Context;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.utils.UMUtils;

/* JADX INFO: loaded from: classes.dex */
public class UMGlobalContext {
    private static final String TAG = "UMGlobalContext";
    private String mAppVersion;
    private String mAppkey;
    private Context mApplicationContext;
    private String mChannel;
    private int mDeviceType;
    private boolean mIsDebugMode;
    private boolean mIsMainProcess;
    private String mModules;
    private String mProcessName;
    private String mPushSecret;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public Context f4014a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4015b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f4016c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public String f4017d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f4018e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public String f4019f;
        public boolean g;
        public String h;
        public String i;
        public boolean j;
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final UMGlobalContext f4020a = new UMGlobalContext();

        private b() {
        }
    }

    public static Context getAppContext(Context context) {
        if (context == null) {
            return b.f4020a.mApplicationContext;
        }
        Context context2 = b.f4020a.mApplicationContext;
        return context2 != null ? context2 : context.getApplicationContext();
    }

    public static UMGlobalContext getInstance() {
        return b.f4020a;
    }

    public static UMGlobalContext newUMGlobalContext(a aVar) {
        getInstance();
        b.f4020a.mDeviceType = aVar.f4015b;
        b.f4020a.mPushSecret = aVar.f4016c;
        b.f4020a.mAppkey = aVar.f4017d;
        b.f4020a.mChannel = aVar.f4018e;
        b.f4020a.mModules = aVar.f4019f;
        b.f4020a.mIsDebugMode = aVar.g;
        b.f4020a.mProcessName = aVar.h;
        b.f4020a.mAppVersion = aVar.i;
        b.f4020a.mIsMainProcess = aVar.j;
        if (aVar.f4014a != null) {
            b.f4020a.mApplicationContext = aVar.f4014a.getApplicationContext();
        }
        return b.f4020a;
    }

    public Context getAppContextDirectly() {
        return this.mApplicationContext;
    }

    public String getAppVersion() {
        return this.mAppVersion;
    }

    public String getAppkey() {
        return this.mAppkey;
    }

    public String getChannel() {
        return this.mChannel;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public String getProcessName(Context context) {
        if (context != null) {
            return b.f4020a.mApplicationContext != null ? this.mProcessName : UMFrUtils.getCurrentProcessName(context);
        }
        return b.f4020a.mProcessName;
    }

    public String getPushSecret() {
        return this.mPushSecret;
    }

    public boolean hasAnalyticsSdk() {
        return this.mModules.contains("a");
    }

    public boolean hasErrorSdk() {
        return this.mModules.contains("e");
    }

    public boolean hasInternalModule() {
        return true;
    }

    public boolean hasOplusModule() {
        return this.mModules.contains(Config.OS);
    }

    public boolean hasPushSdk() {
        return this.mModules.contains("p");
    }

    public boolean hasShareSdk() {
        return this.mModules.contains("s");
    }

    public boolean hasVisualDebugSdk() {
        return this.mModules.contains(Config.EVENT_HEAT_X);
    }

    public boolean hasVisualSdk() {
        return this.mModules.contains("v");
    }

    public boolean isDebugMode() {
        return this.mIsDebugMode;
    }

    public boolean isMainProcess(Context context) {
        if (context != null && b.f4020a.mApplicationContext == null) {
            return UMUtils.isMainProgress(context.getApplicationContext());
        }
        return b.f4020a.mIsMainProcess;
    }

    public String toString() {
        if (b.f4020a.mApplicationContext == null) {
            return "uninitialized.";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append("devType:" + this.mDeviceType + ",");
        sb.append("appkey:" + this.mAppkey + ",");
        sb.append("channel:" + this.mChannel + ",");
        sb.append("procName:" + this.mProcessName + "]");
        return sb.toString();
    }

    private UMGlobalContext() {
        this.mProcessName = "unknown";
    }
}
