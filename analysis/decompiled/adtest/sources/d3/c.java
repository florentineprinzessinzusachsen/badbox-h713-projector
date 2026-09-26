package d3;

import b3.g;
import d0.l0;
import j2.i;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.RejectedExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final e f533a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f534b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f535c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f536d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f537e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f538f;

    public c(e eVar, String str) {
        i.e(str, "name");
        this.f533a = eVar;
        this.f534b = str;
        this.f537e = new ArrayList();
    }

    public static void c(c cVar, String str, i2.a aVar) {
        cVar.getClass();
        i.e(str, "name");
        i.e(aVar, "block");
        cVar.d(new b(str, aVar), 0L);
    }

    public final void a() {
        e eVar = this.f533a;
        TimeZone timeZone = g.f348a;
        synchronized (eVar) {
            if (b()) {
                this.f533a.c(this);
            }
        }
    }

    public final boolean b() {
        a aVar = this.f536d;
        if (aVar != null && aVar.f528b) {
            this.f538f = true;
        }
        ArrayList arrayList = this.f537e;
        boolean z3 = false;
        for (int size = arrayList.size() - 1; -1 < size; size--) {
            if (((a) arrayList.get(size)).f528b) {
                Logger logger = this.f533a.f544b;
                a aVar2 = (a) arrayList.get(size);
                if (logger.isLoggable(Level.FINE)) {
                    l0.c(logger, aVar2, this, "canceled");
                }
                arrayList.remove(size);
                z3 = true;
            }
        }
        return z3;
    }

    public final void d(a aVar, long j4) {
        i.e(aVar, "task");
        synchronized (this.f533a) {
            if (!this.f535c) {
                if (e(aVar, j4, false)) {
                    this.f533a.c(this);
                }
            } else if (aVar.f528b) {
                Logger logger = this.f533a.f544b;
                if (logger.isLoggable(Level.FINE)) {
                    l0.c(logger, aVar, this, "schedule canceled (queue is shutdown)");
                }
            } else {
                Logger logger2 = this.f533a.f544b;
                if (logger2.isLoggable(Level.FINE)) {
                    l0.c(logger2, aVar, this, "schedule failed (queue is shutdown)");
                }
                throw new RejectedExecutionException();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0043 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:19:0x0045  */
    /* JADX WARN: Code duplicated, block: B:20:0x0051  */
    /* JADX WARN: Code duplicated, block: B:24:0x0067  */
    /* JADX WARN: Code duplicated, block: B:27:0x0077 A[LOOP:0: B:23:0x0065->B:27:0x0077, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:30:0x007d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0086 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:38:0x007a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:39:0x007b A[EDGE_INSN: B:39:0x007b->B:29:0x007b BREAK  A[LOOP:0: B:23:0x0065->B:27:0x0077], SYNTHETIC] */
    public final boolean e(a aVar, long j4, boolean z3) {
        int size;
        int size2;
        int i4;
        Object obj;
        String strConcat;
        Logger logger = this.f533a.f544b;
        i.e(aVar, "task");
        c cVar = aVar.f529c;
        if (cVar != this) {
            if (cVar != null) {
                throw new IllegalStateException("task is in multiple queues");
            }
            aVar.f529c = this;
        }
        long jNanoTime = System.nanoTime();
        long j5 = jNanoTime + j4;
        ArrayList arrayList = this.f537e;
        int iIndexOf = arrayList.indexOf(aVar);
        if (iIndexOf == -1) {
            aVar.f530d = j5;
            if (logger.isLoggable(Level.FINE)) {
                if (z3) {
                    strConcat = "run again after ".concat(l0.r(j5 - jNanoTime));
                } else {
                    strConcat = "scheduled after ".concat(l0.r(j5 - jNanoTime));
                }
                l0.c(logger, aVar, this, strConcat);
            }
            size = arrayList.size();
            size2 = 0;
            i4 = 0;
            while (true) {
                if (i4 < size) {
                    size2 = -1;
                    break;
                }
                obj = arrayList.get(i4);
                i4++;
                if (((a) obj).f530d - jNanoTime > j4) {
                    break;
                }
                size2++;
            }
            if (size2 == -1) {
                size2 = arrayList.size();
            }
            arrayList.add(size2, aVar);
            if (size2 == 0) {
                return true;
            }
        } else if (aVar.f530d > j5) {
            arrayList.remove(iIndexOf);
            aVar.f530d = j5;
            if (logger.isLoggable(Level.FINE)) {
                if (z3) {
                    strConcat = "run again after ".concat(l0.r(j5 - jNanoTime));
                } else {
                    strConcat = "scheduled after ".concat(l0.r(j5 - jNanoTime));
                }
                l0.c(logger, aVar, this, strConcat);
            }
            size = arrayList.size();
            size2 = 0;
            i4 = 0;
            while (true) {
                if (i4 < size) {
                    size2 = -1;
                    break;
                }
                obj = arrayList.get(i4);
                i4++;
                if (((a) obj).f530d - jNanoTime > j4) {
                    break;
                    break;
                }
                size2++;
            }
            if (size2 == -1) {
                size2 = arrayList.size();
            }
            arrayList.add(size2, aVar);
            if (size2 == 0) {
                return true;
            }
        } else if (logger.isLoggable(Level.FINE)) {
            l0.c(logger, aVar, this, "already scheduled");
            return false;
        }
        return false;
    }

    public final void f() {
        e eVar = this.f533a;
        TimeZone timeZone = g.f348a;
        synchronized (eVar) {
            this.f535c = true;
            if (b()) {
                this.f533a.c(this);
            }
        }
    }

    public final String toString() {
        return this.f534b;
    }
}
