package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.proguard.ab;
import com.umeng.commonsdk.proguard.m;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.idtracking.e;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.internal.d;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: compiled from: NetWorkManager.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final int f4068b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f4069c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final int f4070d = 3;
    private static final String o = "thtstart";
    private static final String p = "gkvc";
    private static final String q = "ekvc";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f4071a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private com.umeng.commonsdk.statistics.internal.c f4073f;
    private ImprintHandler g;
    private e h;
    private ImprintHandler.a i;
    private Defcon k;
    private long l;
    private int m;
    private int n;
    private Context r;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f4072e = 1;
    private ABTest j = null;
    private ReportPolicy.ReportStrategy s = null;

    public c(Context context) {
        this.i = null;
        this.k = null;
        this.l = 0L;
        this.m = 0;
        this.n = 0;
        this.f4071a = null;
        this.r = context;
        this.i = ImprintHandler.getImprintService(this.r).c();
        this.k = Defcon.getService(this.r);
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.r);
        this.l = sharedPreferences.getLong(o, 0L);
        this.m = sharedPreferences.getInt(p, 0);
        this.n = sharedPreferences.getInt(q, 0);
        this.f4071a = UMEnvelopeBuild.imprintProperty(this.r, "track_list", null);
        this.g = ImprintHandler.getImprintService(this.r);
        this.g.a(new d() { // from class: com.umeng.commonsdk.statistics.c.1
            @Override // com.umeng.commonsdk.statistics.internal.d
            public void onImprintChanged(ImprintHandler.a aVar) throws IllegalAccessException, InvocationTargetException {
                Class<?> cls;
                c.this.k.onImprintChanged(aVar);
                c cVar = c.this;
                cVar.f4071a = UMEnvelopeBuild.imprintProperty(cVar.r, "track_list", null);
                try {
                    String strA = com.umeng.commonsdk.framework.a.a(c.this.r, com.umeng.commonsdk.proguard.e.f3966e, (String) null);
                    if (TextUtils.isEmpty(strA) || (cls = Class.forName("com.umeng.commonsdk.internal.utils.SDStorageAgent")) == null) {
                        return;
                    }
                    cls.getMethod("updateUMTT", Context.class, String.class).invoke(cls, c.this.r, strA);
                } catch (Throwable unused) {
                }
            }
        });
        this.h = e.a(this.r);
        this.f4073f = new com.umeng.commonsdk.statistics.internal.c(this.r);
        this.f4073f.a(StatTracer.getInstance(this.r));
    }

    public boolean a(File file) {
        if (file == null) {
            return false;
        }
        try {
            byte[] byteArray = UMFrUtils.toByteArray(file.getPath());
            if (byteArray == null) {
                return false;
            }
            com.umeng.commonsdk.statistics.internal.a.a(this.r).c(file.getName());
            byte[] bArrA = this.f4073f.a(byteArray, com.umeng.commonsdk.statistics.internal.a.a(this.r).a(file.getName()));
            int iA = bArrA == null ? 1 : a(bArrA);
            if (iA != 1) {
                if (iA == 2) {
                    this.h.d();
                    StatTracer.getInstance(this.r).saveSate();
                } else if (iA == 3) {
                    StatTracer.getInstance(this.r).saveSate();
                }
            }
            return iA == 2;
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
            return false;
        }
    }

    private int a(byte[] bArr) {
        Response response = new Response();
        try {
            new m(new ab.a()).a(response, bArr);
            if (response.resp_code == 1) {
                this.g.b(response.getImprint());
                this.g.d();
            }
            MLog.i("send log:" + response.getMsg());
            UMRTLog.i(UMRTLog.RTLOG_TAG, "send log: " + response.getMsg());
        } catch (Throwable th) {
            UMCrashManager.reportCrash(this.r, th);
        }
        return response.resp_code == 1 ? 2 : 3;
    }
}
