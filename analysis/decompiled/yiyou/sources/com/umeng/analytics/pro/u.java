package com.umeng.analytics.pro;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: SessionIdManager.java */
/* JADX INFO: loaded from: classes.dex */
public class u {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static volatile u f3727c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private s f3728a = new t();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3729b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<a> f3730d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3731e;

    /* JADX INFO: compiled from: SessionIdManager.java */
    public interface a {
        void a(String str, long j, long j2);

        void a(String str, String str2, long j, long j2);
    }

    private u() {
    }

    public static u a() {
        if (f3727c == null) {
            synchronized (u.class) {
                if (f3727c == null) {
                    f3727c = new u();
                }
            }
        }
        return f3727c;
    }

    private String f(Context context) {
        try {
            SharedPreferences.Editor editorEdit = PreferenceWrapper.getDefault(context).edit();
            editorEdit.putString(q.f3717d, d(context));
            editorEdit.commit();
        } catch (Exception unused) {
        }
        long jH = h(context);
        long jI = i(context);
        String str = this.f3729b;
        a(jI, jH, str, false);
        this.f3729b = this.f3728a.a(context);
        a(jI, jH, str, true);
        this.f3728a.a(context, this.f3729b);
        return this.f3729b;
    }

    private boolean g(Context context) {
        return !TextUtils.isEmpty(this.f3729b) && g.a(context).a(this.f3729b) > 0;
    }

    private long h(Context context) {
        return a(context, q.f3719f);
    }

    private long i(Context context) {
        return a(context, q.f3714a);
    }

    private boolean j(Context context) {
        try {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(UMGlobalContext.getAppContext(context));
            long j = sharedPreferences.getLong(q.f3718e, 0L);
            long j2 = sharedPreferences.getLong(q.f3719f, 0L);
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> interval of last session is: " + (j2 - j));
            return this.f3728a.a(j, j2);
        } catch (Exception unused) {
            return false;
        }
    }

    public long b() {
        return this.f3728a.a();
    }

    public String c(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        try {
            this.f3729b = f(appContext);
        } catch (Exception unused) {
        }
        return this.f3729b;
    }

    public String d(Context context) {
        if (TextUtils.isEmpty(this.f3729b)) {
            try {
                this.f3729b = PreferenceWrapper.getDefault(context).getString(q.f3716c, null);
            } catch (Exception unused) {
            }
        }
        return this.f3729b;
    }

    public boolean e(Context context) {
        if (TextUtils.isEmpty(this.f3729b)) {
            this.f3729b = d(context);
        }
        return TextUtils.isEmpty(this.f3729b) || j(context) || g(context);
    }

    public synchronized String b(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        this.f3729b = d(appContext);
        if (e(appContext)) {
            try {
                this.f3729b = f(appContext);
            } catch (Exception unused) {
            }
        }
        return this.f3729b;
    }

    public void a(long j) {
        this.f3728a.a(j);
    }

    public String a(Context context) {
        Context appContext = UMGlobalContext.getAppContext(context);
        if (appContext == null) {
            return "";
        }
        String string = "";
        try {
            synchronized (u.class) {
                string = PreferenceWrapper.getDefault(appContext).getString(q.f3717d, "");
            }
        } catch (Exception unused) {
        }
        return string;
    }

    public void b(a aVar) {
        List<a> list;
        if (aVar == null || (list = this.f3730d) == null || list.size() == 0) {
            return;
        }
        this.f3730d.remove(aVar);
    }

    public String a(Context context, long j) {
        if (TextUtils.isEmpty(this.f3731e)) {
            String str = "SUB" + j;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(String.format("%0" + (32 - str.length()) + "d", 0));
            this.f3731e = sb.toString();
        }
        return this.f3731e;
    }

    private long a(Context context, String str) {
        long j;
        try {
            j = PreferenceWrapper.getDefault(context).getLong(str, 0L);
        } catch (Exception unused) {
            j = 0;
        }
        return j <= 0 ? System.currentTimeMillis() : j;
    }

    private void a(long j, long j2, String str, boolean z) {
        List<a> list = this.f3730d;
        if (list != null) {
            for (a aVar : list) {
                if (z) {
                    try {
                        aVar.a(str, this.f3729b, j, j2);
                    } catch (Exception unused) {
                    }
                } else {
                    aVar.a(this.f3729b, j, j2);
                }
            }
        }
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        if (this.f3730d == null) {
            this.f3730d = new ArrayList();
        }
        if (this.f3730d.contains(aVar)) {
            return;
        }
        this.f3730d.add(aVar);
    }
}
