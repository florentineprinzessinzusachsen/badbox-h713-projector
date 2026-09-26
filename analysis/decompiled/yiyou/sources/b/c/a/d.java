package b.c.a;

import d.a0;
import d.b0;
import d.c0;
import d.d0;
import d.t;
import d.u;
import d.v;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: LoggingInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public class d implements u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final boolean f1716a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e f1717b;

    /* JADX INFO: compiled from: LoggingInterceptor.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e f1718a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ a0 f1719b;

        a(e eVar, a0 a0Var) {
            this.f1718a = eVar;
            this.f1719b = a0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.c.a.e.b(this.f1718a, this.f1719b);
        }
    }

    /* JADX INFO: compiled from: LoggingInterceptor.java */
    static class b implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e f1720a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ a0 f1721b;

        b(e eVar, a0 a0Var) {
            this.f1720a = eVar;
            this.f1721b = a0Var;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.c.a.e.a(this.f1720a, this.f1721b);
        }
    }

    /* JADX INFO: compiled from: LoggingInterceptor.java */
    static class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e f1722a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f1723b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1724c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1725d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f1726e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ String f1727f;
        final /* synthetic */ List g;
        final /* synthetic */ String h;
        final /* synthetic */ String i;

        c(e eVar, long j, boolean z, int i, String str, String str2, List list, String str3, String str4) {
            this.f1722a = eVar;
            this.f1723b = j;
            this.f1724c = z;
            this.f1725d = i;
            this.f1726e = str;
            this.f1727f = str2;
            this.g = list;
            this.h = str3;
            this.i = str4;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.c.a.e.a(this.f1722a, this.f1723b, this.f1724c, this.f1725d, this.f1726e, this.f1727f, this.g, this.h, this.i);
        }
    }

    /* JADX INFO: renamed from: b.c.a.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: LoggingInterceptor.java */
    static class RunnableC0043d implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ e f1728a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f1729b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ boolean f1730c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ int f1731d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ String f1732e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ List f1733f;
        final /* synthetic */ String g;

        RunnableC0043d(e eVar, long j, boolean z, int i, String str, List list, String str2) {
            this.f1728a = eVar;
            this.f1729b = j;
            this.f1730c = z;
            this.f1731d = i;
            this.f1732e = str;
            this.f1733f = list;
            this.g = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            b.c.a.e.a(this.f1728a, this.f1729b, this.f1730c, this.f1731d, this.f1732e, (List<String>) this.f1733f, this.g);
        }
    }

    /* JADX INFO: compiled from: LoggingInterceptor.java */
    public static class e {
        private static String k = "LoggingI";

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1737d;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f1739f;
        private String g;
        private b.c.a.c i;
        private Executor j;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1736c = false;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f1738e = 4;
        private b.c.a.b h = b.c.a.b.BASIC;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final HashMap<String, String> f1734a = new HashMap<>();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final HashMap<String, String> f1735b = new HashMap<>();

        HashMap<String, String> c() {
            return this.f1735b;
        }

        b.c.a.b d() {
            return this.h;
        }

        b.c.a.c e() {
            return this.i;
        }

        int f() {
            return this.f1738e;
        }

        boolean g() {
            return this.f1736c;
        }

        public e a(b.c.a.b bVar) {
            this.h = bVar;
            return this;
        }

        HashMap<String, String> b() {
            return this.f1734a;
        }

        String a(boolean z) {
            if (z) {
                return f.a(this.f1739f) ? k : this.f1739f;
            }
            return f.a(this.g) ? k : this.g;
        }

        public e b(String str) {
            this.g = str;
            return this;
        }

        public e b(boolean z) {
            this.f1737d = z;
            return this;
        }

        public e a(String str) {
            this.f1739f = str;
            return this;
        }

        public e a(int i) {
            this.f1738e = i;
            return this;
        }

        public d a() {
            return new d(this, null);
        }
    }

    /* synthetic */ d(e eVar, a aVar) {
        this(eVar);
    }

    private boolean a(String str) {
        return str != null && (str.contains("json") || str.contains("xml") || str.contains("plain") || str.contains("html"));
    }

    private static Runnable b(e eVar, a0 a0Var) {
        return new a(eVar, a0Var);
    }

    @Override // d.u
    public c0 intercept(u.a aVar) {
        a0 a0VarRequest = aVar.request();
        HashMap<String, String> mapB = this.f1717b.b();
        if (mapB.size() > 0) {
            a0.a aVarF = a0VarRequest.f();
            for (String str : mapB.keySet()) {
                aVarF.a(str, mapB.get(str));
            }
            a0VarRequest = aVarF.a();
        }
        HashMap<String, String> mapC = this.f1717b.c();
        if (mapC.size() > 0) {
            t.a aVarA = a0VarRequest.g().a(a0VarRequest.g().toString());
            for (String str2 : mapC.keySet()) {
                aVarA.b(str2, mapC.get(str2));
            }
            a0.a aVarF2 = a0VarRequest.f();
            aVarF2.a(aVarA.a());
            a0VarRequest = aVarF2.a();
        }
        if (!this.f1716a || this.f1717b.d() == b.c.a.b.NONE) {
            return aVar.a(a0VarRequest);
        }
        b0 b0VarA = a0VarRequest.a();
        String strB = (b0VarA == null || b0VarA.contentType() == null) ? null : b0VarA.contentType().b();
        Executor executor = this.f1717b.j;
        if (a(strB)) {
            if (executor != null) {
                executor.execute(b(this.f1717b, a0VarRequest));
            } else {
                b.c.a.e.b(this.f1717b, a0VarRequest);
            }
        } else if (executor != null) {
            executor.execute(a(this.f1717b, a0VarRequest));
        } else {
            b.c.a.e.a(this.f1717b, a0VarRequest);
        }
        long jNanoTime = System.nanoTime();
        c0 c0VarA = aVar.a(a0VarRequest);
        long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
        List<String> listD = a0VarRequest.g().d();
        String string = c0VarA.o().toString();
        int iM = c0VarA.m();
        boolean zP = c0VarA.p();
        String strQ = c0VarA.q();
        d0 d0VarA = c0VarA.a();
        v vVarContentType = d0VarA.contentType();
        if (!a(vVarContentType != null ? vVarContentType.b() : null)) {
            if (executor != null) {
                executor.execute(a(this.f1717b, millis, zP, iM, string, listD, strQ));
            } else {
                b.c.a.e.a(this.f1717b, millis, zP, iM, string, listD, strQ);
            }
            return c0VarA;
        }
        String strB2 = b.c.a.e.b(d0VarA.string());
        String string2 = c0VarA.w().g().toString();
        if (executor != null) {
            executor.execute(a(this.f1717b, millis, zP, iM, string, strB2, listD, strQ, string2));
        } else {
            b.c.a.e.a(this.f1717b, millis, zP, iM, string, strB2, listD, strQ, string2);
        }
        d0 d0VarCreate = d0.create(vVarContentType, strB2);
        c0.a aVarS = c0VarA.s();
        aVarS.a(d0VarCreate);
        return aVarS.a();
    }

    private d(e eVar) {
        this.f1717b = eVar;
        this.f1716a = eVar.f1737d;
    }

    private static Runnable a(e eVar, a0 a0Var) {
        return new b(eVar, a0Var);
    }

    private static Runnable a(e eVar, long j, boolean z, int i, String str, String str2, List<String> list, String str3, String str4) {
        return new c(eVar, j, z, i, str, str2, list, str3, str4);
    }

    private static Runnable a(e eVar, long j, boolean z, int i, String str, List<String> list, String str2) {
        return new RunnableC0043d(eVar, j, z, i, str, list, str2);
    }
}
