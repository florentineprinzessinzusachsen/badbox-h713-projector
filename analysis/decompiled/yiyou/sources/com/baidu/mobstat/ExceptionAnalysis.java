package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;
import com.android.umanalytics.utils.ShellUtils;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ExceptionAnalysis {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ExceptionAnalysis f3350a = new ExceptionAnalysis();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f3352c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3354e;
    public Callback mCallback;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3351b = false;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private HeadObject f3353d = new HeadObject();

    public interface Callback {
        void onCallback(JSONObject jSONObject);
    }

    private ExceptionAnalysis() {
    }

    private JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(Config.TRACE_APPLICATION_SESSION, 0);
        } catch (Exception unused) {
        }
        try {
            jSONObject.put(Config.TRACE_FAILED_CNT, 0);
        } catch (Exception unused2) {
        }
        return jSONObject;
    }

    public static ExceptionAnalysis getInstance() {
        return f3350a;
    }

    public void openExceptionAnalysis(Context context, boolean z) {
        if (context != null) {
            this.f3352c = context.getApplicationContext();
        }
        if (this.f3352c == null || this.f3351b) {
            return;
        }
        this.f3351b = true;
        ad.a().a(this.f3352c);
        if (z) {
            return;
        }
        NativeCrashHandler.init(this.f3352c);
    }

    public void saveCrashInfo(Context context, Throwable th, boolean z) {
        int i;
        if (context != null) {
            this.f3352c = context.getApplicationContext();
        }
        if (this.f3352c == null) {
            return;
        }
        String string = th.toString();
        String str = "";
        if (!TextUtils.isEmpty(string)) {
            try {
                String[] strArrSplit = string.split(Config.TRACE_TODAY_VISIT_SPLIT);
                str = strArrSplit.length > 1 ? strArrSplit[0] : string;
            } catch (Exception unused) {
            }
        }
        String str2 = TextUtils.isEmpty(str) ? string : str;
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String string2 = stringWriter.toString();
        if (z) {
            i = 0;
        } else if (th instanceof Exception) {
            i = 11;
        } else {
            i = th instanceof Error ? 12 : 13;
        }
        saveCrashInfo(this.f3352c, System.currentTimeMillis(), string2, str2, 0, i);
    }

    public void setCrashExtraInfo(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.length() > 256) {
            str = str.substring(0, 256);
        }
        this.f3354e = str;
    }

    public ExceptionAnalysis(Callback callback) {
        this.mCallback = callback;
    }

    @SuppressLint({"NewApi"})
    private JSONObject a(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null) {
            return null;
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        JSONObject jSONObject = new JSONObject();
        try {
            if (android.os.Build.VERSION.SDK_INT >= 16) {
                jSONObject.put(Config.EXCEPTION_MEMORY_TOTAL, memoryInfo.totalMem);
            }
            jSONObject.put(Config.EXCEPTION_MEMORY_FREE, memoryInfo.availMem);
            jSONObject.put(Config.EXCEPTION_MEMORY_LOW, memoryInfo.lowMemory ? 1 : 0);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public void saveCrashInfo(Context context, long j, String str, String str2, int i, int i2) {
        BDStatCore.instance().autoTrackSessionEndTime(context);
        if (context == null || str == null || str.trim().equals("")) {
            return;
        }
        try {
            StringBuilder sb = new StringBuilder(str);
            if (!TextUtils.isEmpty(this.f3354e)) {
                sb.append(ShellUtils.COMMAND_LINE_END);
                sb.append("ExtraInfo:");
                sb.append(this.f3354e);
            }
            String appVersionName = CooperService.instance().getAppVersionName(context);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("t", j);
            jSONObject.put("c", sb.toString());
            jSONObject.put("y", str2);
            jSONObject.put("v", appVersionName);
            jSONObject.put(Config.EXCEPTION_CRASH_TYPE, i);
            jSONObject.put("mem", a(context));
            jSONObject.put(Config.EXCEPTION_CRASH_CHANNEL, i2);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            this.f3353d.installHeader(context, jSONObject2);
            jSONObject2.put("ss", 0);
            jSONObject2.put(Config.SEQUENCE_INDEX, 0);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(Config.HEADER_PART, jSONObject2);
            jSONObject3.put(Config.PRINCIPAL_PART, new JSONArray());
            jSONObject3.put(Config.EVENT_PART, new JSONArray());
            jSONObject3.put(Config.EXCEPTION_PART, jSONArray);
            jSONObject3.put(Config.TRACE_PART, a());
            if (this.mCallback != null) {
                this.mCallback.onCallback(jSONObject3);
            }
            at.a(context, Config.PREFIX_SEND_DATA + System.currentTimeMillis(), jSONObject3.toString(), false);
            am.c().a("dump exception, exception: " + str);
        } catch (Exception unused) {
        }
    }
}
