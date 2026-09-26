package ddth2;

import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public class f {
    public volatile long a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public b f65a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public c f66a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public e f67a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public l2 f69a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public Future<?> f73a;

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final Random f72a = new Random();

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final Object f70a = new Object();
    public final Object b = new Object();

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final AtomicLong f75a = new AtomicLong(0);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public AtomicBoolean f74a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: b, reason: collision with other field name */
    public AtomicBoolean f76b = new AtomicBoolean(false);

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public a f68a = a.b();

    /* JADX INFO: renamed from: a, reason: collision with other field name */
    public final List<d> f71a = new CopyOnWriteArrayList();

    public static final class a {
        public final int a;

        /* JADX INFO: renamed from: a, reason: collision with other field name */
        public final long f77a;

        /* JADX INFO: renamed from: a, reason: collision with other field name */
        public final Runnable f78a;

        /* JADX INFO: renamed from: a, reason: collision with other field name */
        public final boolean f79a;
        public final Runnable b;

        /* JADX INFO: renamed from: b, reason: collision with other field name */
        public final boolean f80b;
        public final Runnable c;
        public final Runnable d;
        public final Runnable e;

        public a(long j, Runnable runnable, Runnable runnable2, Runnable runnable3, Runnable runnable4, Runnable runnable5, boolean z, boolean z2, int i) {
            this.f77a = j;
            this.f78a = runnable;
            this.b = runnable2;
            this.c = runnable3;
            this.d = runnable4;
            this.e = runnable5;
            this.f79a = z;
            this.f80b = z2;
            this.a = i;
        }

        public static a b() {
            return new a(0L, null, null, null, null, null, false, false, 0);
        }

        public static a b(long j, Runnable runnable, Runnable runnable2, Runnable runnable3, Runnable runnable4, Runnable runnable5) {
            return new a(j, runnable, runnable2, runnable3, runnable4, runnable5, false, false, 0);
        }

        public final a a(boolean z) {
            return new a(this.f77a, this.f78a, this.b, this.c, this.d, this.e, this.f79a, this.f80b || z, 2);
        }

        public final a c() {
            return new a(this.f77a, this.f78a, this.b, this.c, this.d, this.e, this.f79a, this.f80b, 1);
        }

        public final a d() {
            return new a(this.f77a, this.f78a, this.b, this.c, this.d, this.e, this.f79a, true, this.a);
        }

        public final a e() {
            return new a(this.f77a, this.f78a, this.b, this.c, this.d, this.e, true, this.f80b, this.a);
        }
    }

    public final int a(int i, int i2) {
        return g2.a(i, i2);
    }

    public final long a() {
        long j;
        synchronized (this.b) {
            j = this.f68a.f77a;
        }
        return j;
    }

    public long a(Runnable runnable, Runnable runnable2, Runnable runnable3, Runnable runnable4, Runnable runnable5) {
        synchronized (this.b) {
            if (this.f68a.f77a != 0 && this.f74a.get()) {
                return this.f68a.f77a;
            }
            long jIncrementAndGet = this.f75a.incrementAndGet();
            this.f68a = a.b(jIncrementAndGet, runnable, runnable2, runnable3, runnable4, runnable5);
            return jIncrementAndGet;
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public MetricsSnapshot m46a() {
        int size;
        int i;
        int iB;
        synchronized (this.f70a) {
            size = this.f71a.size();
            i = 0;
            iB = 0;
            for (d dVar : this.f71a) {
                if (dVar.m15a()) {
                    i++;
                }
                iB += dVar.b();
            }
        }
        return j2.a(size, i, iB);
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public final String m47a() {
        if (this.f67a.m41d() != null && !this.f67a.m41d().isEmpty()) {
            return this.f67a.m41d();
        }
        return "ANDROID_" + System.currentTimeMillis();
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public void m48a() {
        long jNanoTime;
        e eVar;
        long jNanoTime2 = System.nanoTime();
        long jNanoTime3 = System.nanoTime();
        while (this.f74a.get() && !this.f76b.get()) {
            try {
                if (System.nanoTime() >= jNanoTime3) {
                    m50b();
                    jNanoTime3 = System.nanoTime() + b();
                }
                if (System.nanoTime() >= jNanoTime2) {
                    long jA = a();
                    try {
                        n2 n2VarA = this.f65a.a();
                        if (n2VarA.m65a()) {
                            boolean z = (n2VarA.m64a() == null || n2VarA.m64a().isEmpty()) ? false : true;
                            if (z) {
                                this.a = SystemClock.elapsedRealtime();
                                e(jA);
                            } else {
                                d(jA);
                            }
                            if (z) {
                                b(jA);
                            } else {
                                a(jA);
                            }
                            a(1, "success to get config, count: " + n2VarA.m64a().size());
                            if (n2VarA.m64a() != null && !n2VarA.m64a().isEmpty()) {
                                c(jA);
                            }
                            a(n2VarA.m64a());
                            jNanoTime = System.nanoTime();
                            eVar = this.f67a;
                        } else {
                            d(jA);
                            a(jA);
                            a(3, "get config failed: " + n2VarA.a());
                            jNanoTime = System.nanoTime();
                            eVar = this.f67a;
                        }
                        jNanoTime2 = jNanoTime + (((long) a(eVar.m26a(), 3600)) * 1000000000);
                    } catch (Exception e) {
                        d(jA);
                        a(jA);
                        a(3, "main loop error: " + e.getMessage());
                        jNanoTime2 = System.nanoTime() + 60000000000L;
                    }
                }
                long jMin = Math.min(jNanoTime3, jNanoTime2) - System.nanoTime();
                long j = 1;
                if (jMin > 0) {
                    long jMin2 = Math.min(jMin / 1000000, 50L);
                    if (jMin2 > 0) {
                        j = jMin2;
                    }
                }
                Thread.sleep(j);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public final void a(int i, String str) {
        l2 l2Var = this.f69a;
        if (l2Var != null) {
            l2Var.a(i, str);
        }
    }

    public final void a(long j) {
        synchronized (this.b) {
            if (j == this.f68a.f77a && this.f68a.a == 0) {
                Runnable runnable = this.f68a.b;
                this.f68a = this.f68a.c();
                if (runnable != null) {
                    try {
                        runnable.run();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public final void a(d dVar, long j, long j2, int i, int i2) {
        m2 m2VarM10a = dVar.m10a();
        String strM12a = dVar.m12a();
        String strM17b = dVar.m17b();
        synchronized (this.f70a) {
            this.f71a.remove(dVar);
        }
        dVar.m14a();
        synchronized (this.f70a) {
            if (this.f71a.size() >= this.f67a.e()) {
                a(3, "max proxy instances after swap, skip placeholder add");
                return;
            }
            d dVar2 = new d(m2VarM10a, this.f67a, strM12a, strM17b, this.f69a);
            dVar2.a(j, j2, i, i2);
            this.f71a.add(dVar2);
        }
    }

    public void a(e eVar) {
        this.f67a = eVar;
        e2.a(eVar);
        d2.a(eVar);
        f2.b(eVar);
        this.f65a = new b(eVar);
        this.f66a = new c(eVar);
    }

    public void a(m2 m2Var) {
        String strM47a = m47a();
        for (int i = 0; i < m2Var.a() && !this.f76b.get(); i++) {
            synchronized (this.f70a) {
                if (!e2.a(this.f71a.size())) {
                    a(3, "max proxy client instances reached, skip remaining connections");
                    return;
                }
            }
            String strValueOf = m2Var.b() > 0 ? String.valueOf(i) : "";
            String str = strValueOf.isEmpty() ? strM47a : strM47a + "_" + strValueOf;
            if (!a(m2Var, str, strValueOf)) {
                synchronized (this.f70a) {
                    if (e2.a(this.f71a.size())) {
                        d dVar = new d(m2Var, this.f67a, str, strValueOf, this.f69a);
                        synchronized (this.f70a) {
                            this.f71a.add(dVar);
                        }
                    } else {
                        a(3, "max proxy client instances, skip placeholder after failed connect");
                    }
                }
            }
        }
    }

    public final void a(List<m2> list) {
        synchronized (this.f70a) {
            Iterator<d> it = this.f71a.iterator();
            while (it.hasNext()) {
                it.next().m14a();
            }
            this.f71a.clear();
        }
        for (m2 m2Var : list) {
            if (this.f76b.get()) {
                return;
            } else {
                a(m2Var);
            }
        }
    }

    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public boolean m49a() {
        return this.f74a.get();
    }

    public final boolean a(m2 m2Var, String str, String str2) {
        StringBuilder sb;
        synchronized (this.f70a) {
            if (!e2.a(this.f71a.size())) {
                a(3, "max proxy client instances, abort connect");
                return false;
            }
            o2 o2VarA = this.f66a.a(m2Var.m62a(), str, str2);
            if (o2VarA.m67a()) {
                r2 r2VarM70a = o2VarA.a().m70a();
                if (r2VarM70a == null || r2VarM70a.b() <= 0) {
                    return false;
                }
                d dVar = new d(m2Var, this.f67a, str, str2, this.f69a);
                dVar.a(o2VarA.a());
                if (dVar.a(r2VarM70a.m71a(), r2VarM70a.a())) {
                    synchronized (this.f70a) {
                        this.f71a.add(dVar);
                    }
                    a(1, "connected to server: " + r2VarM70a.m71a() + ":" + r2VarM70a.a());
                    return true;
                }
                sb = new StringBuilder();
                sb.append("connect to server failed: ");
                sb.append(r2VarM70a.m71a());
                sb.append(":");
                sb.append(r2VarM70a.a());
            } else {
                sb = new StringBuilder();
                sb.append("failed to get connect info: ");
                sb.append(o2VarA.m66a());
            }
            a(3, sb.toString());
            return false;
        }
    }

    public final long b() {
        return (((long) (this.f72a.nextInt(601) + 700)) + ((long) (this.f72a.nextInt(401) + 300))) * 1000000;
    }

    /* JADX INFO: renamed from: b, reason: collision with other method in class */
    public final void m50b() {
        if (!this.f74a.get() || this.f76b.get()) {
            return;
        }
        try {
            ArrayList<d> arrayList = new ArrayList();
            synchronized (this.f70a) {
                for (d dVar : this.f71a) {
                    if (!dVar.m15a()) {
                        arrayList.add(dVar);
                        a(2, "checked client disconnected: " + dVar.m12a());
                    }
                }
            }
            for (d dVar2 : arrayList) {
                long jM16b = dVar2.m16b();
                long jM8a = dVar2.m8a();
                int iA = dVar2.a();
                int i = dVar2.m13a().get();
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis < jM8a) {
                    a(2, "reconnect circuit open, wait");
                } else if (jCurrentTimeMillis >= jM16b) {
                    j2.a();
                    synchronized (this.f70a) {
                        this.f71a.remove(dVar2);
                        if (e2.a(this.f71a.size())) {
                            dVar2.m14a();
                            if (!a(dVar2.m10a(), dVar2.m12a(), dVar2.m17b())) {
                                int i2 = i + 1;
                                if (i2 > 10) {
                                    a(2, "reconnect abandoned after max retries");
                                } else {
                                    d dVar3 = new d(dVar2.m10a(), this.f67a, dVar2.m12a(), dVar2.m17b(), this.f69a);
                                    dVar3.a(0L, jM8a, iA, i2);
                                    dVar3.m19c();
                                    synchronized (this.f70a) {
                                        if (e2.a(this.f71a.size())) {
                                            this.f71a.add(dVar3);
                                        } else {
                                            a(3, "max proxy instances, drop reconnect placeholder");
                                        }
                                    }
                                }
                            }
                        } else {
                            a(3, "max proxy instances, defer reconnect");
                        }
                    }
                }
                a(dVar2, jM16b, jM8a, iA, i);
            }
        } catch (Exception e) {
            a(3, "check client retry connect failed: " + e.getMessage());
        }
    }

    public final void b(long j) {
        synchronized (this.b) {
            if (j == this.f68a.f77a && this.f68a.a != 2) {
                Runnable runnable = this.f68a.f78a;
                this.f68a = this.f68a.a(false);
                if (runnable != null) {
                    try {
                        runnable.run();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public void c() {
        if (this.f74a.get()) {
            return;
        }
        this.a = 0L;
        this.f74a.set(true);
        this.f76b.set(false);
        k2.a(this.f67a);
        this.f73a = k2.a((Runnable) new h2(this));
        a(1, "sdk start");
    }

    public final void c(long j) {
        synchronized (this.b) {
            if (j == this.f68a.f77a && !this.f68a.f80b) {
                Runnable runnable = this.f68a.e;
                this.f68a = this.f68a.d();
                if (runnable != null) {
                    try {
                        runnable.run();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public void d() {
        this.f76b.set(true);
        this.f74a.set(false);
        synchronized (this.f70a) {
            Iterator<d> it = this.f71a.iterator();
            while (it.hasNext()) {
                it.next().m14a();
            }
            this.f71a.clear();
        }
        Future<?> future = this.f73a;
        if (future != null) {
            future.cancel(true);
            this.f73a = null;
        }
        k2.m60a();
        a(1, "sdk stop");
    }

    public final void d(long j) {
        synchronized (this.b) {
            if (j == this.f68a.f77a && !this.f68a.f79a) {
                Runnable runnable = this.f68a.d;
                this.f68a = this.f68a.e();
                if (runnable != null) {
                    try {
                        runnable.run();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public final void e(long j) {
        synchronized (this.b) {
            if (j == this.f68a.f77a && !this.f68a.f79a) {
                Runnable runnable = this.f68a.c;
                this.f68a = this.f68a.e();
                if (runnable != null) {
                    try {
                        runnable.run();
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }
}
