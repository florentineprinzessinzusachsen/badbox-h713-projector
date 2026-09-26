package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;
import com.blankj.utilcode.constant.TimeConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SessionAnalysis {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f3412a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Map<String, a> f3413b = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f3414c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private a f3415d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f3416e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f3417f = 0;
    private Session g = new Session();
    private int h = 0;
    private int i = 0;
    private long j = 0;
    private boolean k = true;
    private LaunchInfo l;
    private LaunchInfo m;
    public Callback mCallback;

    public interface Callback {
        void onCallback(JSONObject jSONObject);
    }

    public SessionAnalysis() {
    }

    private void a(Context context, long j, long j2, int i, int i2) {
        if (j2 - j > ((long) getSessionTimeOut())) {
            if (j > 0) {
                if (2 == i2) {
                    this.g.setEndTime(j);
                }
                LaunchInfo launchInfo = this.m;
                a(context, j2, false, false, launchInfo != null ? launchInfo.getLaunchType(context) : 0);
            }
            this.g.setTrackStartTime(this.j);
            this.g.setInvokeType(i);
        }
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str) && this.f3413b.containsKey(str)) {
            this.f3413b.remove(str);
        }
    }

    public void autoTrackLaunchInfo(LaunchInfo launchInfo, boolean z) {
        if (z) {
            this.l = launchInfo;
        } else {
            this.m = launchInfo;
        }
    }

    public void autoTrackSessionEndTime(Context context, long j) {
        if (context == null) {
            return;
        }
        this.g.setTrackEndTime(j);
        a(context);
    }

    public void autoTrackSessionStartTime(Context context, long j) {
        if (context == null) {
            return;
        }
        this.g.setTrackStartTime(j);
        this.j = j;
    }

    public void clearLastSessionCache(Context context) {
        if (context == null) {
            return;
        }
        at.a(context, bb.s(context) + Config.LAST_SESSION_FILE_NAME, new JSONObject().toString(), false);
    }

    public void doSendLogCheck(Context context, long j) {
        long j2 = this.f3417f;
        if (j2 <= 0 || j - j2 <= getSessionTimeOut()) {
            return;
        }
        a(context, -1L, false, false, 0);
    }

    public JSONObject getPageSessionHead() {
        return this.g.getPageSessionHead();
    }

    public int getSessionSize() {
        return this.i;
    }

    public long getSessionStartTime() {
        return this.g.getStartTime();
    }

    public int getSessionTimeOut() {
        if (this.h <= 0) {
            this.h = Config.SESSION_PERIOD;
        }
        return this.h;
    }

    public boolean isSessionStart() {
        return this.g.getStartTime() > 0;
    }

    public void onPageEnd(Context context, String str, String str2, String str3, long j, ExtraInfo extraInfo, boolean z) {
        a aVarA;
        this.f3416e = false;
        if (TextUtils.isEmpty(str) || (aVarA = a(str)) == null) {
            return;
        }
        if (aVarA.f3420c) {
            a(context, aVarA.f3418a, str, aVarA.f3419b, j, str2, "", str3, false, extraInfo, z);
            b(str);
            this.f3417f = j;
        } else {
            am.c().c("[WARNING] 遗漏StatService.onPageStart(), 请检查邻近页面埋点: " + str);
        }
    }

    public void onPageEndAct(Context context, String str, String str2, String str3, long j, boolean z, ExtraInfo extraInfo) {
        this.f3416e = false;
        a aVar = z ? this.f3415d : this.f3414c;
        if (aVar.f3420c) {
            a(context, aVar.f3418a, str, aVar.f3419b, j, str2, str3, str, z, extraInfo, false);
            aVar.f3420c = false;
            this.f3417f = j;
        } else {
            if (z) {
                return;
            }
            am.c().c("[WARNING] 遗漏StatService.onResume(Activity), 请检查邻近页面埋点: " + str);
        }
    }

    public void onPageEndFrag(Context context, String str, String str2, String str3, long j) {
        a aVarA;
        if (TextUtils.isEmpty(str) || (aVarA = a(str)) == null) {
            return;
        }
        if (aVarA.f3420c) {
            a(context, aVarA.f3418a, str, aVarA.f3419b, j, str2, str3, null, false, null, false);
            b(str);
            this.f3417f = j;
        } else {
            am.c().c("[WARNING] 遗漏StatService.onResume(Fragment), 请检查邻近页面埋点: " + str);
        }
    }

    public void onPageStart(Context context, String str, int i, long j) {
        a aVarA;
        onSessionStart(context, j, false);
        if (TextUtils.isEmpty(str) || (aVarA = a(str)) == null) {
            return;
        }
        if (aVarA.f3420c) {
            am.c().c("[WARNING] 遗漏StatService.onPageEnd(), 请检查邻近页面埋点: " + str);
        }
        if (!this.f3416e) {
            a(context, this.f3417f, j, i, 3);
            this.f3416e = true;
        }
        aVarA.f3420c = true;
        aVarA.f3419b = j;
    }

    public void onPageStartAct(Context context, String str, long j, boolean z) {
        onSessionStart(context, j, false);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a aVar = z ? this.f3415d : this.f3414c;
        if (aVar.f3420c && !z) {
            am.c().c("[WARNING] 遗漏StatService.onPause(Activity), 请检查邻近页面埋点: " + str);
        }
        if (!this.f3416e) {
            a(context, this.f3417f, j, 1, 1);
            this.f3416e = true;
        }
        aVar.f3420c = true;
        aVar.f3418a = str;
        aVar.f3419b = j;
    }

    public void onPageStartFrag(Context context, String str, long j) {
        onSessionStart(context, j, false);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        a aVarA = a(str);
        if (aVarA.f3420c) {
            am.c().c("[WARNING] 遗漏StatService.onPause(Fragment), 请检查邻近页面埋点: " + str);
        }
        a(context, this.f3417f, j, 2, 2);
        aVarA.f3420c = true;
        aVarA.f3418a = str;
        aVarA.f3419b = j;
    }

    public void onSessionStart(Context context, long j, boolean z) {
        if (this.f3412a) {
            return;
        }
        DataCore.instance().init(context);
        LaunchInfo launchInfo = this.l;
        a(context, j, z, true, launchInfo != null ? launchInfo.getLaunchType(context) : 0);
        this.f3412a = true;
    }

    public void setAutoSend(boolean z) {
        this.k = z;
    }

    public void setSessionTimeOut(int i) {
        if (i < 1) {
            i = 30;
            am.c().b("[WARNING] SessionTimeout should be between 1 and 600. Default value[30] is used");
        } else if (i > 600) {
            am.c().b("[WARNING] SessionTimeout should be between 1 and 600. Default value[600] is used");
            i = 600;
        }
        this.h = i * TimeConstants.SEC;
    }

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        String f3418a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f3419b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f3420c = false;

        public a() {
        }

        public a(String str) {
            this.f3418a = str;
        }
    }

    private void a(Context context, String str, String str2, long j, long j2, String str3, String str4, String str5, boolean z, ExtraInfo extraInfo, boolean z2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !str.equals(str2)) {
            return;
        }
        this.g.addPageView(new Session.a(str3, str4, str5, j2 - j, j, z, extraInfo, z2));
        this.g.setEndTime(j2);
        a(context);
    }

    public SessionAnalysis(Callback callback) {
        this.mCallback = callback;
    }

    private a a(String str) {
        if (!this.f3413b.containsKey(str)) {
            this.f3413b.put(str, new a(str));
        }
        return this.f3413b.get(str);
    }

    private void a(Context context, long j, boolean z, boolean z2, int i) {
        if (this.g.hasEnd()) {
            DataCore.instance().putSession(this.g);
            DataCore.instance().flush(context);
            ah.a(this.g.getPageSessionHead());
            this.g.setEndTime(0L);
        }
        boolean z3 = j > 0;
        long startTime = z3 ? j : this.g.getStartTime();
        if (z3) {
            this.g.reset();
            this.g.setStartTime(j);
        }
        DataCore.instance().saveLogData(context, z3, z, startTime, z2, null);
        Callback callback = this.mCallback;
        if (callback != null) {
            callback.onCallback(DataCore.instance().getLogData());
        }
        if (z3 || this.k) {
            LogSender.instance().onSend(context);
        }
        clearLastSessionCache(context);
    }

    private void a(Context context) {
        if (this.g.hasStart()) {
            String string = this.g.constructJSONObject().toString();
            this.i = string.getBytes().length;
            at.a(context, bb.s(context) + Config.LAST_SESSION_FILE_NAME, string, false);
        }
    }
}
