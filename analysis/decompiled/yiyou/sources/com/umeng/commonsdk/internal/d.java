package com.umeng.commonsdk.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodInfo;
import com.baidu.mobstat.Config;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.utils.j;
import com.umeng.commonsdk.internal.utils.k;
import com.umeng.commonsdk.internal.utils.l;
import com.umeng.commonsdk.proguard.e;
import com.umeng.commonsdk.proguard.s;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.stateless.f;
import com.umeng.commonsdk.statistics.common.ULog;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: UMInternalManager.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static void a(Context context) {
        try {
            ULog.i("walle", "[internal] workEvent send envelope");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(e.aw, a.f3802d);
            JSONObject jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(context, jSONObject, e(context));
            if (jSONObjectBuildEnvelopeWithExtHeader == null || jSONObjectBuildEnvelopeWithExtHeader.has("exception")) {
                return;
            }
            ULog.i("walle", "[internal] workEvent send envelope back, result is ok");
            com.umeng.commonsdk.internal.utils.a.f(context);
            j.d(context);
            com.umeng.commonsdk.proguard.c.c(context);
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
        }
    }

    public static void b(Context context) {
        ULog.i("walle", "[internal] begin by stateful--->>>");
        if (context != null) {
            try {
                if (UMEnvelopeBuild.isReadyBuild(context, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                    UMWorkDispatch.sendEvent(context, a.f3803e, b.a(context).a(), null);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    public static void c(Context context) {
        if (context != null) {
            try {
                ULog.i("walle", "[internal] begin by not stateful--->>>");
                context = context.getApplicationContext();
                f.a(context, context.getFilesDir() + "/" + com.umeng.commonsdk.stateless.a.f4025e + "/" + Base64.encodeToString(a.f3799a.getBytes(), 0), 10);
                UMSLEnvelopeBuild uMSLEnvelopeBuild = new UMSLEnvelopeBuild();
                JSONObject jSONObjectBuildSLBaseHeader = uMSLEnvelopeBuild.buildSLBaseHeader(context);
                if (jSONObjectBuildSLBaseHeader != null && jSONObjectBuildSLBaseHeader.has("header")) {
                    try {
                        JSONObject jSONObject = (JSONObject) jSONObjectBuildSLBaseHeader.opt("header");
                        if (jSONObject != null) {
                            jSONObject.put(e.aw, a.f3802d);
                        }
                    } catch (Exception unused) {
                    }
                }
                ULog.i("walle", "[internal] header is " + jSONObjectBuildSLBaseHeader.toString());
                JSONObject jSONObjectD = d(context);
                ULog.i("walle", "[internal] body is " + jSONObjectD.toString());
                ULog.i("walle", uMSLEnvelopeBuild.buildSLEnvelope(context, jSONObjectBuildSLBaseHeader, jSONObjectD, a.f3799a).toString());
            } catch (Throwable th) {
                UMCrashManager.reportCrash(context, th);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0030 A[Catch: Exception -> 0x0036, TRY_LEAVE, TryCatch #3 {Exception -> 0x0036, blocks: (B:13:0x0026, B:15:0x0030), top: B:37:0x0026, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:21:0x0044 A[Catch: Exception -> 0x004a, TRY_LEAVE, TryCatch #1 {Exception -> 0x004a, blocks: (B:19:0x003a, B:21:0x0044), top: B:33:0x003a, outer: #2 }] */
    /* JADX WARN: Code duplicated, block: B:31:0x004e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:33:0x003a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static JSONObject d(Context context) {
        String strL;
        String strK;
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                try {
                    JSONArray jSONArrayP = p(applicationContext);
                    if (jSONArrayP == null || jSONArrayP.length() <= 0) {
                        try {
                            strK = com.umeng.commonsdk.internal.utils.a.k(applicationContext);
                            if (TextUtils.isEmpty(strK)) {
                                strL = com.umeng.commonsdk.internal.utils.a.l(applicationContext);
                                if (TextUtils.isEmpty(strL)) {
                                    jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                                } else {
                                    jSONObject2.put("meid", strL);
                                    jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                                }
                            } else {
                                jSONObject2.put("imsi", strK);
                                try {
                                    strL = com.umeng.commonsdk.internal.utils.a.l(applicationContext);
                                    if (TextUtils.isEmpty(strL)) {
                                        jSONObject2.put("meid", strL);
                                        try {
                                            jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                                        } catch (JSONException e2) {
                                            UMCrashManager.reportCrash(applicationContext, e2);
                                        }
                                    } else {
                                        jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                                    }
                                } catch (Exception e3) {
                                    UMCrashManager.reportCrash(applicationContext, e3);
                                }
                            }
                        } catch (Exception e4) {
                            UMCrashManager.reportCrash(applicationContext, e4);
                        }
                    } else {
                        jSONObject2.put("run_server", jSONArrayP);
                        strK = com.umeng.commonsdk.internal.utils.a.k(applicationContext);
                        if (TextUtils.isEmpty(strK)) {
                            jSONObject2.put("imsi", strK);
                            strL = com.umeng.commonsdk.internal.utils.a.l(applicationContext);
                            if (TextUtils.isEmpty(strL)) {
                                jSONObject2.put("meid", strL);
                                jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                            } else {
                                jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                            }
                        } else {
                            strL = com.umeng.commonsdk.internal.utils.a.l(applicationContext);
                            if (TextUtils.isEmpty(strL)) {
                                jSONObject2.put("meid", strL);
                                jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                            } else {
                                jSONObject.put(UMModuleRegister.INNER, jSONObject2);
                            }
                        }
                    }
                } catch (Exception e5) {
                    UMCrashManager.reportCrash(applicationContext, e5);
                }
            } catch (Exception unused) {
            }
        }
        return jSONObject;
    }

    public static JSONObject e(Context context) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            try {
                JSONArray jSONArrayP = p(applicationContext);
                if (jSONArrayP != null && jSONArrayP.length() > 0) {
                    jSONObject2.put("rs", jSONArrayP);
                }
            } catch (Exception e2) {
                UMCrashManager.reportCrash(applicationContext, e2);
            }
            try {
                JSONArray jSONArrayQ = q(applicationContext);
                if (jSONArrayQ != null && jSONArrayQ.length() > 0) {
                    jSONObject2.put("bstn", jSONArrayQ);
                }
            } catch (Exception e3) {
                UMCrashManager.reportCrash(applicationContext, e3);
            }
            try {
                JSONArray jSONArrayR = r(applicationContext);
                if (jSONArrayR != null && jSONArrayR.length() > 0) {
                    jSONObject2.put("by", jSONArrayR);
                }
            } catch (Exception e4) {
                UMCrashManager.reportCrash(applicationContext, e4);
            }
            try {
                a(applicationContext, jSONObject2);
            } catch (Exception e5) {
                UMCrashManager.reportCrash(applicationContext, e5);
            }
            try {
                b(applicationContext, jSONObject2);
            } catch (Exception e6) {
                UMCrashManager.reportCrash(applicationContext, e6);
            }
            try {
                JSONObject jSONObjectA = a();
                if (jSONObjectA != null && jSONObjectA.length() > 0) {
                    jSONObject2.put(Config.FEED_LIST_MAPPING, jSONObjectA);
                }
            } catch (Exception e7) {
                UMCrashManager.reportCrash(applicationContext, e7);
            }
            try {
                JSONObject jSONObjectB = b();
                if (jSONObjectB != null && jSONObjectB.length() > 0) {
                    jSONObject2.put("build", jSONObjectB);
                }
            } catch (Exception e8) {
                UMCrashManager.reportCrash(applicationContext, e8);
            }
            try {
                JSONObject jSONObject3 = new JSONObject();
                JSONArray jSONArrayG = g(applicationContext);
                if (jSONArrayG != null && jSONArrayG.length() > 0) {
                    try {
                        jSONObject3.put("a_sr", jSONArrayG);
                    } catch (JSONException unused) {
                    }
                }
                JSONArray jSONArrayC = j.c(applicationContext);
                if (jSONArrayC != null && jSONArrayC.length() > 0) {
                    try {
                        jSONObject3.put("stat", jSONArrayC);
                    } catch (JSONException unused2) {
                    }
                }
                jSONObject2.put("sr", jSONObject3);
            } catch (Exception e9) {
                UMCrashManager.reportCrash(applicationContext, e9);
            }
            try {
                JSONObject jSONObjectH = h(applicationContext);
                if (jSONObjectH != null && jSONObjectH.length() > 0) {
                    jSONObject2.put("scr", jSONObjectH);
                }
            } catch (Exception e10) {
                UMCrashManager.reportCrash(applicationContext, e10);
            }
            try {
                JSONObject jSONObjectI = i(applicationContext);
                if (jSONObjectI != null && jSONObjectI.length() > 0) {
                    jSONObject2.put("sinfo", jSONObjectI);
                }
            } catch (Exception e11) {
                UMCrashManager.reportCrash(applicationContext, e11);
            }
            try {
                JSONObject jSONObject4 = new JSONObject();
                JSONArray jSONArrayE = com.umeng.commonsdk.internal.utils.a.e(applicationContext);
                if (jSONArrayE != null && jSONArrayE.length() > 0) {
                    try {
                        jSONObject4.put("wl", jSONArrayE);
                    } catch (JSONException unused3) {
                    }
                }
                JSONArray jSONArrayJ = j(applicationContext);
                if (jSONArrayJ != null && jSONArrayJ.length() > 0) {
                    try {
                        jSONObject4.put("a_wls", jSONArrayJ);
                    } catch (JSONException unused4) {
                    }
                }
                jSONObject2.put("winfo", jSONObject4);
            } catch (Exception e12) {
                UMCrashManager.reportCrash(applicationContext, e12);
            }
            try {
                JSONArray jSONArrayK = k(applicationContext);
                if (jSONArrayK != null && jSONArrayK.length() > 0) {
                    jSONObject2.put(Config.INPUT_PART, jSONArrayK);
                }
            } catch (Exception e13) {
                UMCrashManager.reportCrash(applicationContext, e13);
            }
            try {
                JSONObject jSONObjectO = com.umeng.commonsdk.internal.utils.a.o(applicationContext);
                if (jSONObjectO != null && jSONObjectO.length() > 0) {
                    jSONObject2.put("bt", jSONObjectO);
                }
            } catch (Exception e14) {
                UMCrashManager.reportCrash(applicationContext, e14);
            }
            try {
                JSONArray jSONArrayL = l(applicationContext);
                if (jSONArrayL != null && jSONArrayL.length() > 0) {
                    jSONObject2.put("cam", jSONArrayL);
                }
            } catch (Exception e15) {
                UMCrashManager.reportCrash(applicationContext, e15);
            }
            try {
                JSONArray jSONArrayM = m(applicationContext);
                if (jSONArrayM != null && jSONArrayM.length() > 0) {
                    jSONObject2.put("appls", jSONArrayM);
                }
            } catch (Exception e16) {
                UMCrashManager.reportCrash(applicationContext, e16);
            }
            try {
                JSONObject jSONObjectN = n(applicationContext);
                if (jSONObjectN != null && jSONObjectN.length() > 0) {
                    jSONObject2.put("mem", jSONObjectN);
                }
            } catch (Exception e17) {
                UMCrashManager.reportCrash(applicationContext, e17);
            }
            try {
                JSONArray jSONArrayO = o(applicationContext);
                if (jSONArrayO != null && jSONArrayO.length() > 0) {
                    jSONObject2.put("lbs", jSONArrayO);
                }
            } catch (Exception e18) {
                UMCrashManager.reportCrash(applicationContext, e18);
            }
            try {
                JSONObject jSONObjectD = d();
                if (jSONObjectD != null && jSONObjectD.length() > 0) {
                    jSONObject2.put(e.v, jSONObjectD);
                }
            } catch (Exception unused5) {
            }
            try {
                JSONObject jSONObjectC = c();
                if (jSONObjectC != null && jSONObjectC.length() > 0) {
                    jSONObject2.put(Config.ROM, jSONObjectC);
                }
            } catch (Exception unused6) {
            }
            try {
                jSONObject.put(e.ak, jSONObject2);
            } catch (JSONException e19) {
                UMCrashManager.reportCrash(applicationContext, e19);
            }
        }
        return jSONObject;
    }

    public static String f(Context context) {
        try {
            com.umeng.commonsdk.statistics.idtracking.e eVarA = com.umeng.commonsdk.statistics.idtracking.e.a(context);
            if (eVarA == null) {
                return null;
            }
            eVarA.a();
            String strEncodeToString = Base64.encodeToString(new s().a(eVarA.b()), 0);
            if (TextUtils.isEmpty(strEncodeToString)) {
                return null;
            }
            return strEncodeToString;
        } catch (Exception e2) {
            UMCrashManager.reportCrash(context, e2);
            return null;
        }
    }

    public static JSONArray g(Context context) {
        if (context != null) {
            return k.g(context.getApplicationContext());
        }
        return null;
    }

    public static JSONObject h(Context context) {
        DisplayMetrics displayMetrics;
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            try {
                jSONObject.put("a_st_h", com.umeng.commonsdk.internal.utils.a.h(context));
                jSONObject.put("a_nav_h", com.umeng.commonsdk.internal.utils.a.i(context));
                if (context.getResources() != null && (displayMetrics = context.getResources().getDisplayMetrics()) != null) {
                    jSONObject.put("a_den", displayMetrics.density);
                    jSONObject.put("a_dpi", displayMetrics.densityDpi);
                }
            } catch (Exception e2) {
                UMCrashManager.reportCrash(context, e2);
            }
        }
        return jSONObject;
    }

    public static JSONObject i(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            String packageName = applicationContext.getPackageName();
            try {
                jSONObject.put("a_fit", com.umeng.commonsdk.internal.utils.a.a(applicationContext, packageName));
                jSONObject.put("a_alut", com.umeng.commonsdk.internal.utils.a.b(applicationContext, packageName));
                jSONObject.put("a_c", com.umeng.commonsdk.internal.utils.a.c(applicationContext, packageName));
                jSONObject.put("a_uid", com.umeng.commonsdk.internal.utils.a.d(applicationContext, packageName));
                if (com.umeng.commonsdk.internal.utils.a.a()) {
                    jSONObject.put("a_root", 1);
                } else {
                    jSONObject.put("a_root", 0);
                }
                jSONObject.put("tf", com.umeng.commonsdk.internal.utils.a.b());
                jSONObject.put("s_fs", com.umeng.commonsdk.internal.utils.a.a(applicationContext));
                jSONObject.put("a_meid", com.umeng.commonsdk.internal.utils.a.l(applicationContext));
                jSONObject.put("a_imsi", com.umeng.commonsdk.internal.utils.a.k(applicationContext));
                jSONObject.put("st", com.umeng.commonsdk.internal.utils.a.f());
                String strB = k.b(applicationContext);
                if (!TextUtils.isEmpty(strB)) {
                    try {
                        jSONObject.put("a_iccid", strB);
                    } catch (Exception unused) {
                    }
                }
                String strC = k.c(applicationContext);
                if (!TextUtils.isEmpty(strC)) {
                    try {
                        jSONObject.put("a_simei", strC);
                    } catch (Exception unused2) {
                    }
                }
                jSONObject.put("hn", com.umeng.commonsdk.internal.utils.a.g());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e2) {
                UMCrashManager.reportCrash(applicationContext, e2);
            }
        }
        return jSONObject;
    }

    public static JSONArray j(Context context) {
        Context applicationContext;
        List<ScanResult> listB;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listB = com.umeng.commonsdk.internal.utils.a.b((applicationContext = context.getApplicationContext()))) != null && listB.size() > 0) {
            for (ScanResult scanResult : listB) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_bssid", scanResult.BSSID);
                    jSONObject.put("a_ssid", scanResult.SSID);
                    jSONObject.put("a_cap", scanResult.capabilities);
                    jSONObject.put("a_fcy", scanResult.frequency);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (Build.VERSION.SDK_INT >= 23) {
                        jSONObject.put("a_c0", scanResult.centerFreq0);
                        jSONObject.put("a_c1", scanResult.centerFreq1);
                        jSONObject.put("a_cw", scanResult.channelWidth);
                        if (scanResult.is80211mcResponder()) {
                            jSONObject.put("a_is80211", 1);
                        } else {
                            jSONObject.put("a_is80211", 0);
                        }
                        if (scanResult.isPasspointNetwork()) {
                            jSONObject.put("a_isppn", 1);
                        } else {
                            jSONObject.put("a_isppn", 0);
                        }
                        jSONObject.put("a_ofn", scanResult.operatorFriendlyName);
                        jSONObject.put("a_vn", scanResult.venueName);
                    }
                    jSONObject.put("a_dc", scanResult.describeContents());
                    jSONArray.put(jSONObject);
                } catch (Exception e2) {
                    UMCrashManager.reportCrash(applicationContext, e2);
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray k(Context context) {
        Context applicationContext;
        List<InputMethodInfo> listM;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listM = com.umeng.commonsdk.internal.utils.a.m((applicationContext = context.getApplicationContext()))) != null) {
            for (InputMethodInfo inputMethodInfo : listM) {
                try {
                    CharSequence charSequenceLoadLabel = inputMethodInfo.loadLabel(applicationContext.getPackageManager());
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("a_la", charSequenceLoadLabel);
                    jSONObject.put("a_pn", inputMethodInfo.getPackageName());
                    jSONObject.put("ts", System.currentTimeMillis());
                    jSONArray.put(jSONObject);
                } catch (Exception e2) {
                    UMCrashManager.reportCrash(applicationContext, e2);
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray l(Context context) {
        Context applicationContext;
        List<j.a> listE;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listE = j.e((applicationContext = context.getApplicationContext()))) != null && !listE.isEmpty()) {
            for (j.a aVar : listE) {
                if (aVar != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_w", aVar.f3868a);
                        jSONObject.put("a_h", aVar.f3869b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONArray.put(jSONObject);
                    } catch (Exception e2) {
                        UMCrashManager.reportCrash(applicationContext, e2);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONArray m(Context context) {
        Context applicationContext;
        List<com.umeng.commonsdk.internal.utils.a.C0086a> listP;
        JSONArray jSONArray = new JSONArray();
        if (context != null && (listP = com.umeng.commonsdk.internal.utils.a.p((applicationContext = context.getApplicationContext()))) != null && !listP.isEmpty()) {
            for (com.umeng.commonsdk.internal.utils.a.C0086a c0086a : listP) {
                if (c0086a != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("a_pn", c0086a.f3813a);
                        jSONObject.put("a_la", c0086a.f3814b);
                        jSONObject.put("ts", System.currentTimeMillis());
                        jSONArray.put(jSONObject);
                    } catch (Exception e2) {
                        UMCrashManager.reportCrash(applicationContext, e2);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject n(Context context) {
        Context applicationContext;
        ActivityManager.MemoryInfo memoryInfoQ;
        JSONObject jSONObject = new JSONObject();
        if (context != null && (memoryInfoQ = com.umeng.commonsdk.internal.utils.a.q((applicationContext = context.getApplicationContext()))) != null) {
            try {
                if (Build.VERSION.SDK_INT >= 16) {
                    jSONObject.put("t", memoryInfoQ.totalMem);
                }
                jSONObject.put("f", memoryInfoQ.availMem);
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception e2) {
                UMCrashManager.reportCrash(applicationContext, e2);
            }
        }
        return jSONObject;
    }

    private static JSONArray o(Context context) {
        if (context != null) {
            return com.umeng.commonsdk.proguard.c.b(context.getApplicationContext());
        }
        return null;
    }

    private static JSONArray p(Context context) {
        List<ActivityManager.RunningServiceInfo> runningServices;
        JSONArray jSONArray = null;
        jSONArray = null;
        if (context == null) {
            return null;
        }
        try {
            ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService("activity");
            if (activityManager == null || (runningServices = activityManager.getRunningServices(Integer.MAX_VALUE)) == null || runningServices.isEmpty()) {
                return null;
            }
            int i = 0;
            while (i < runningServices.size()) {
                if (runningServices.get(i) != null && runningServices.get(i).service != null && runningServices.get(i).service.getClassName() != null && runningServices.get(i).service.getPackageName() != null) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("sn", runningServices.get(i).service.getClassName().toString());
                        jSONObject.put("pn", runningServices.get(i).service.getPackageName().toString());
                        jSONArray = jSONArray;
                        if (jSONArray == null) {
                            jSONArray = new JSONArray();
                        }
                        jSONArray.put(jSONObject);
                    } catch (JSONException unused) {
                    }
                }
                i++;
                jSONArray = jSONArray;
            }
            if (jSONArray == null) {
                return jSONArray;
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("ts", System.currentTimeMillis());
                jSONObject2.put("ls", jSONArray);
            } catch (JSONException unused2) {
            }
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("sers", jSONObject2);
            } catch (JSONException unused3) {
            }
            JSONArray jSONArray2 = new JSONArray();
            try {
                jSONArray2.put(jSONObject3);
                return jSONArray2;
            } catch (Throwable th) {
                th = th;
                jSONArray = jSONArray2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        UMCrashManager.reportCrash(context, th);
        return jSONArray;
    }

    private static JSONArray q(Context context) {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObjectD = k.d(context);
        if (jSONObjectD != null) {
            try {
                String strE = k.e(context);
                if (!TextUtils.isEmpty(strE)) {
                    jSONObjectD.put("sig", strE);
                }
                jSONArray.put(jSONObjectD);
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    private static JSONArray r(Context context) {
        JSONArray jSONArray = new JSONArray();
        String strF = k.f(context);
        if (!TextUtils.isEmpty(strF)) {
            try {
                jSONArray.put(new JSONObject(strF));
            } catch (Exception unused) {
            }
        }
        return jSONArray;
    }

    private static JSONArray s(Context context) {
        JSONArray jSONArray = new JSONArray();
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            String strA = k.a(applicationContext);
            JSONObject jSONObject = null;
            if (!TextUtils.isEmpty(strA)) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put(e.X, strA);
                    } catch (Exception unused) {
                    }
                    jSONObject = jSONObject2;
                } catch (Exception unused2) {
                }
            }
            String strB = k.b(applicationContext);
            if (!TextUtils.isEmpty(strB)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused3) {
                    }
                }
                jSONObject.put(e.Y, strB);
            }
            String strC = k.c(applicationContext);
            if (!TextUtils.isEmpty(strC)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused4) {
                    }
                }
                jSONObject.put(e.Z, strC);
            }
            JSONObject jSONObjectD = k.d(applicationContext);
            if (jSONObjectD != null) {
                try {
                    String strE = k.e(applicationContext);
                    if (!TextUtils.isEmpty(strE)) {
                        jSONObjectD.put("signalscale", strE);
                    }
                    if (jSONObject == null) {
                        jSONObject = new JSONObject();
                    }
                    jSONObject.put(e.ab, jSONObjectD);
                } catch (Exception unused5) {
                }
            }
            String strF = k.f(applicationContext);
            if (!TextUtils.isEmpty(strF)) {
                if (jSONObject == null) {
                    try {
                        jSONObject = new JSONObject();
                    } catch (Exception unused6) {
                    }
                }
                jSONObject.put(e.W, new JSONObject(strF));
            }
            if (jSONObject != null) {
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }

    private static void b(Context context, JSONObject jSONObject) {
        if (context != null) {
            String strA = l.a(context);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            try {
                JSONObject jSONObject2 = new JSONObject(strA);
                if (jSONObject == null) {
                    jSONObject = new JSONObject();
                }
                if (jSONObject2.has(l.f3877d)) {
                    jSONObject.put(l.f3877d, jSONObject2.opt(l.f3877d));
                }
                if (jSONObject2.has(l.f3876c)) {
                    jSONObject.put(l.f3876c, jSONObject2.opt(l.f3876c));
                }
                if (jSONObject2.has(l.f3875b)) {
                    jSONObject.put(l.f3875b, jSONObject2.opt(l.f3875b));
                }
            } catch (Exception unused) {
            }
        }
    }

    private static void a(Context context, JSONObject jSONObject) {
        PackageManager packageManager;
        if (context == null || (packageManager = context.getApplicationContext().getPackageManager()) == null) {
            return;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        a(jSONObject, "gp", packageManager.hasSystemFeature("android.hardware.location.gps"));
        a(jSONObject, "to", packageManager.hasSystemFeature("android.hardware.touchscreen"));
        a(jSONObject, "mo", packageManager.hasSystemFeature("android.hardware.telephony"));
        a(jSONObject, "ca", packageManager.hasSystemFeature("android.hardware.camera"));
        a(jSONObject, "fl", packageManager.hasSystemFeature("android.hardware.camera.flash"));
    }

    public static JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("a_pr", Build.PRODUCT);
            jSONObject.put("a_bl", Build.BOOTLOADER);
            if (Build.VERSION.SDK_INT >= 14) {
                jSONObject.put("a_rv", Build.getRadioVersion());
            }
            jSONObject.put("a_fp", Build.FINGERPRINT);
            jSONObject.put("a_hw", Build.HARDWARE);
            jSONObject.put("a_host", Build.HOST);
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < Build.SUPPORTED_32_BIT_ABIS.length; i++) {
                    jSONArray.put(Build.SUPPORTED_32_BIT_ABIS[i]);
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("a_s32", jSONArray);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < Build.SUPPORTED_64_BIT_ABIS.length; i2++) {
                    jSONArray2.put(Build.SUPPORTED_64_BIT_ABIS[i2]);
                }
                if (jSONArray2.length() > 0) {
                    jSONObject.put("a_s64", jSONArray2);
                }
            }
            if (Build.VERSION.SDK_INT >= 21) {
                JSONArray jSONArray3 = new JSONArray();
                for (int i3 = 0; i3 < Build.SUPPORTED_ABIS.length; i3++) {
                    jSONArray3.put(Build.SUPPORTED_ABIS[i3]);
                }
                if (jSONArray3.length() > 0) {
                    jSONObject.put("a_sa", jSONArray3);
                }
            }
            jSONObject.put("a_ta", Build.TAGS);
            jSONObject.put("a_uk", "unknown");
            jSONObject.put("a_user", Build.USER);
            jSONObject.put("a_cpu1", Build.CPU_ABI);
            jSONObject.put("a_cpu2", Build.CPU_ABI2);
            jSONObject.put("a_ra", Build.RADIO);
            if (Build.VERSION.SDK_INT >= 23) {
                jSONObject.put("a_bos", Build.VERSION.BASE_OS);
                jSONObject.put("a_pre", Build.VERSION.PREVIEW_SDK_INT);
                jSONObject.put("a_sp", Build.VERSION.SECURITY_PATCH);
            }
            jSONObject.put("a_cn", Build.VERSION.CODENAME);
            jSONObject.put("a_intl", Build.VERSION.INCREMENTAL);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private static JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("tot_s", com.umeng.commonsdk.internal.utils.a.h());
            jSONObject.put("ava_s", com.umeng.commonsdk.internal.utils.a.i());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private static JSONObject d() throws Throwable {
        try {
            com.umeng.commonsdk.internal.utils.d.a aVarA = com.umeng.commonsdk.internal.utils.d.a();
            if (aVarA == null) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("pro", aVarA.f3837a);
                jSONObject.put("pla", aVarA.f3838b);
                jSONObject.put("cpus", aVarA.f3839c);
                jSONObject.put("fea", aVarA.f3840d);
                jSONObject.put("imp", aVarA.f3841e);
                jSONObject.put("arc", aVarA.f3842f);
                jSONObject.put("var", aVarA.g);
                jSONObject.put("par", aVarA.h);
                jSONObject.put("rev", aVarA.i);
                jSONObject.put("har", aVarA.j);
                jSONObject.put("rev", aVarA.k);
                jSONObject.put("ser", aVarA.l);
                jSONObject.put("cur_cpu", com.umeng.commonsdk.internal.utils.d.d());
                jSONObject.put("max_cpu", com.umeng.commonsdk.internal.utils.d.b());
                jSONObject.put("min_cpu", com.umeng.commonsdk.internal.utils.d.c());
                jSONObject.put("ts", System.currentTimeMillis());
            } catch (Exception unused) {
            }
            return jSONObject;
        } catch (Exception unused2) {
            return null;
        }
    }

    private static void a(JSONObject jSONObject, String str, boolean z) {
        if (jSONObject == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            if (z) {
                jSONObject.put(str, 1);
            } else {
                jSONObject.put(str, 0);
            }
        } catch (Exception unused) {
        }
    }

    public static JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("f", com.umeng.commonsdk.internal.utils.a.c());
            jSONObject.put("t", com.umeng.commonsdk.internal.utils.a.d());
            jSONObject.put("ts", System.currentTimeMillis());
        } catch (Exception unused) {
        }
        return jSONObject;
    }
}
