package d3;

import a3.h;
import b3.f;
import b3.g;
import j2.i;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Logger f541k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final e f542l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final h f543a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Logger f544b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f545c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f546d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f547e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f548f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f549g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f550h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ArrayList f551i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d f552j;

    static {
        Logger logger = Logger.getLogger(e.class.getName());
        i.d(logger, "getLogger(...)");
        f541k = logger;
        String str = g.f349b + " TaskRunner";
        i.e(str, "name");
        f542l = new e(new h(new f(str, true)));
    }

    public e(h hVar) {
        Logger logger = f541k;
        i.e(logger, "logger");
        this.f543a = hVar;
        this.f544b = logger;
        this.f545c = 10000;
        this.f550h = new ArrayList();
        this.f551i = new ArrayList();
        this.f552j = new d(0, this);
    }

    public static final void a(e eVar, a aVar, long j4, boolean z3) {
        TimeZone timeZone = g.f348a;
        c cVar = aVar.f529c;
        i.b(cVar);
        if (cVar.f536d != aVar) {
            throw new IllegalStateException("Check failed.");
        }
        boolean z4 = cVar.f538f;
        cVar.f538f = false;
        cVar.f536d = null;
        eVar.f550h.remove(cVar);
        if (j4 != -1 && !z4 && !cVar.f535c) {
            cVar.e(aVar, j4, true);
        }
        if (cVar.f537e.isEmpty()) {
            return;
        }
        eVar.f551i.add(cVar);
        if (z3) {
            return;
        }
        eVar.e();
    }

    public final a b() {
        long j4;
        a aVar;
        boolean z3;
        TimeZone timeZone = g.f348a;
        while (true) {
            ArrayList arrayList = this.f551i;
            if (arrayList.isEmpty()) {
                return null;
            }
            long jNanoTime = System.nanoTime();
            int size = arrayList.size();
            long jMin = Long.MAX_VALUE;
            int i4 = 0;
            a aVar2 = null;
            while (true) {
                if (i4 >= size) {
                    j4 = jNanoTime;
                    aVar = null;
                    z3 = false;
                    break;
                }
                Object obj = arrayList.get(i4);
                i4++;
                a aVar3 = (a) ((c) obj).f537e.get(0);
                j4 = jNanoTime;
                aVar = null;
                long jMax = Math.max(0L, aVar3.f530d - j4);
                if (jMax > 0) {
                    jMin = Math.min(jMax, jMin);
                } else {
                    if (aVar2 != null) {
                        z3 = true;
                        break;
                    }
                    aVar2 = aVar3;
                }
                jNanoTime = j4;
            }
            ArrayList arrayList2 = this.f550h;
            if (aVar2 != null) {
                TimeZone timeZone2 = g.f348a;
                aVar2.f530d = -1L;
                c cVar = aVar2.f529c;
                i.b(cVar);
                cVar.f537e.remove(aVar2);
                arrayList.remove(cVar);
                cVar.f536d = aVar2;
                arrayList2.add(cVar);
                if (z3 || (!this.f546d && !arrayList.isEmpty())) {
                    e();
                }
                return aVar2;
            }
            if (this.f546d) {
                if (jMin >= this.f547e - j4) {
                    return aVar;
                }
                notify();
                return aVar;
            }
            this.f546d = true;
            this.f547e = j4 + jMin;
            try {
                try {
                    TimeZone timeZone3 = g.f348a;
                    if (jMin > 0) {
                        long j5 = jMin / 1000000;
                        Long.signum(j5);
                        long j6 = jMin - (1000000 * j5);
                        if (j5 > 0 || jMin > 0) {
                            wait(j5, (int) j6);
                        }
                    }
                } catch (InterruptedException unused) {
                    TimeZone timeZone4 = g.f348a;
                    for (int size2 = arrayList2.size() - 1; -1 < size2; size2--) {
                        ((c) arrayList2.get(size2)).b();
                    }
                    for (int size3 = arrayList.size() - 1; -1 < size3; size3--) {
                        c cVar2 = (c) arrayList.get(size3);
                        cVar2.b();
                        if (cVar2.f537e.isEmpty()) {
                            arrayList.remove(size3);
                        }
                    }
                }
                this.f546d = false;
            } catch (Throwable th) {
                this.f546d = false;
                throw th;
            }
        }
    }

    public final void c(c cVar) {
        i.e(cVar, "taskQueue");
        TimeZone timeZone = g.f348a;
        if (cVar.f536d == null) {
            boolean zIsEmpty = cVar.f537e.isEmpty();
            ArrayList arrayList = this.f551i;
            if (zIsEmpty) {
                arrayList.remove(cVar);
            } else {
                byte[] bArr = b3.d.f343a;
                i.e(arrayList, "<this>");
                if (!arrayList.contains(cVar)) {
                    arrayList.add(cVar);
                }
            }
        }
        if (this.f546d) {
            notify();
        } else {
            e();
        }
    }

    public final c d() {
        int i4;
        synchronized (this) {
            i4 = this.f545c;
            this.f545c = i4 + 1;
        }
        return new c(this, a1.c.c(i4, "Q"));
    }

    public final void e() {
        TimeZone timeZone = g.f348a;
        int i4 = this.f548f;
        if (i4 > this.f549g) {
            return;
        }
        this.f548f = i4 + 1;
        d dVar = this.f552j;
        i.e(dVar, "runnable");
        ((ThreadPoolExecutor) this.f543a.f149e).execute(dVar);
    }
}
