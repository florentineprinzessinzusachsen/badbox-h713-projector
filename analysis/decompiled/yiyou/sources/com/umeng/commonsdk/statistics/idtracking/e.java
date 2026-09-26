package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.AnalyticsConstants;
import com.umeng.commonsdk.statistics.SdkVersion;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: compiled from: IdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final long f4109a = 86400000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static e f4110b;
    private static Object j = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private File f4112d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private long f4114f;
    private a i;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f4111c = "umeng_it.cache";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private com.umeng.commonsdk.statistics.proto.c f4113e = null;
    private Set<com.umeng.commonsdk.statistics.idtracking.a> h = new HashSet();
    private long g = 86400000;

    /* JADX INFO: compiled from: IdTracker.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Context f4115a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Set<String> f4116b = new HashSet();

        public a(Context context) {
            this.f4115a = context;
        }

        public synchronized boolean a(String str) {
            return !this.f4116b.contains(str);
        }

        public synchronized void b(String str) {
            this.f4116b.add(str);
        }

        public void c(String str) {
            this.f4116b.remove(str);
        }

        public synchronized void a() {
            if (!this.f4116b.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                Iterator<String> it = this.f4116b.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                PreferenceWrapper.getDefault(this.f4115a).edit().putString("invld_id", sb.toString()).commit();
            }
        }

        public synchronized void b() {
            String[] strArrSplit;
            String string = PreferenceWrapper.getDefault(this.f4115a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string) && (strArrSplit = string.split(",")) != null) {
                for (String str : strArrSplit) {
                    if (!TextUtils.isEmpty(str)) {
                        this.f4116b.add(str);
                    }
                }
            }
        }
    }

    e(Context context) {
        this.i = null;
        this.f4112d = new File(context.getFilesDir(), "umeng_it.cache");
        this.i = new a(context);
        this.i.b();
    }

    public static synchronized e a(Context context) {
        if (f4110b == null) {
            f4110b = new e(context);
            f4110b.a(new f(context));
            f4110b.a(new b(context));
            f4110b.a(new r(context));
            f4110b.a(new d(context));
            f4110b.a(new c(context));
            f4110b.a(new g(context));
            f4110b.a(new j());
            f4110b.a(new s(context));
            q qVar = new q(context);
            if (!TextUtils.isEmpty(qVar.f())) {
                f4110b.a(qVar);
            }
            i iVar = new i(context);
            if (iVar.g()) {
                f4110b.a(iVar);
                f4110b.a(new h(context));
                iVar.i();
            }
            if (SdkVersion.SDK_TYPE != 1) {
                f4110b.a(new p(context));
                f4110b.a(new m(context));
                f4110b.a(new o(context));
                f4110b.a(new n(context));
                f4110b.a(new l(context));
                f4110b.a(new k(context));
            }
            f4110b.e();
        }
        return f4110b;
    }

    private synchronized void g() {
        com.umeng.commonsdk.statistics.proto.c cVar = new com.umeng.commonsdk.statistics.proto.c();
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (com.umeng.commonsdk.statistics.idtracking.a aVar : this.h) {
            if (aVar.c()) {
                if (aVar.d() != null) {
                    map.put(aVar.b(), aVar.d());
                }
                if (aVar.e() != null && !aVar.e().isEmpty()) {
                    arrayList.addAll(aVar.e());
                }
            }
        }
        cVar.a(arrayList);
        cVar.a(map);
        synchronized (this) {
            this.f4113e = cVar;
        }
    }

    private com.umeng.commonsdk.statistics.proto.c h() {
        Throwable th;
        FileInputStream fileInputStream;
        synchronized (j) {
            if (!this.f4112d.exists()) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(this.f4112d);
                try {
                    try {
                        byte[] streamToByteArray = HelperUtils.readStreamToByteArray(fileInputStream);
                        com.umeng.commonsdk.statistics.proto.c cVar = new com.umeng.commonsdk.statistics.proto.c();
                        new com.umeng.commonsdk.proguard.m().a(cVar, streamToByteArray);
                        HelperUtils.safeClose(fileInputStream);
                        return cVar;
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        HelperUtils.safeClose(fileInputStream);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    HelperUtils.safeClose(fileInputStream);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                HelperUtils.safeClose(fileInputStream);
                throw th;
            }
        }
    }

    public synchronized com.umeng.commonsdk.statistics.proto.c b() {
        return this.f4113e;
    }

    public String c() {
        return null;
    }

    public synchronized void d() {
        boolean z = false;
        for (com.umeng.commonsdk.statistics.idtracking.a aVar : this.h) {
            if (aVar.c() && aVar.e() != null && !aVar.e().isEmpty()) {
                aVar.a((List<com.umeng.commonsdk.statistics.proto.a>) null);
                z = true;
            }
        }
        if (z) {
            this.f4113e.b(false);
            f();
        }
    }

    public synchronized void e() {
        com.umeng.commonsdk.statistics.proto.c cVarH = h();
        if (cVarH == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.h.size());
        synchronized (this) {
            this.f4113e = cVarH;
            for (com.umeng.commonsdk.statistics.idtracking.a aVar : this.h) {
                aVar.a(this.f4113e);
                if (!aVar.c()) {
                    arrayList.add(aVar);
                }
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.h.remove((com.umeng.commonsdk.statistics.idtracking.a) it.next());
            }
            g();
        }
    }

    public synchronized void f() {
        if (this.f4113e != null) {
            a(this.f4113e);
        }
    }

    private boolean a(com.umeng.commonsdk.statistics.idtracking.a aVar) {
        if (this.i.a(aVar.b())) {
            return this.h.add(aVar);
        }
        if (!AnalyticsConstants.UM_DEBUG) {
            return false;
        }
        MLog.w("invalid domain: " + aVar.b());
        return false;
    }

    public void a(long j2) {
        this.g = j2;
    }

    public synchronized void a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f4114f >= this.g) {
            boolean z = false;
            for (com.umeng.commonsdk.statistics.idtracking.a aVar : this.h) {
                if (aVar.c() && aVar.a()) {
                    z = true;
                    if (!aVar.c()) {
                        this.i.b(aVar.b());
                    }
                }
            }
            if (z) {
                g();
                this.i.a();
                f();
            }
            this.f4114f = jCurrentTimeMillis;
        }
    }

    private void a(com.umeng.commonsdk.statistics.proto.c cVar) {
        byte[] bArrA;
        synchronized (j) {
            if (cVar != null) {
                try {
                    synchronized (this) {
                        bArrA = new com.umeng.commonsdk.proguard.s().a(cVar);
                    }
                    if (bArrA != null) {
                        HelperUtils.writeFile(this.f4112d, bArrA);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
