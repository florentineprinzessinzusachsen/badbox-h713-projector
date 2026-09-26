package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class l0 implements Runnable, Comparable, g0 {
    private volatile Object _heap;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f1996d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1997e = -1;

    public l0(long j4) {
        this.f1996d = j4;
    }

    @Override // r2.g0
    public final void a() {
        synchronized (this) {
            try {
                Object obj = this._heap;
                a3.h hVar = x.f2040b;
                if (obj == hVar) {
                    return;
                }
                m0 m0Var = obj instanceof m0 ? (m0) obj : null;
                if (m0Var != null) {
                    m0Var.b(this);
                }
                this._heap = hVar;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final w2.w c() {
        Object obj = this._heap;
        if (obj instanceof w2.w) {
            return (w2.w) obj;
        }
        return null;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        long j4 = this.f1996d - ((l0) obj).f1996d;
        if (j4 > 0) {
            return 1;
        }
        return j4 < 0 ? -1 : 0;
    }

    public final int d(long j4, m0 m0Var, n0 n0Var) {
        synchronized (this) {
            if (this._heap == x.f2040b) {
                return 2;
            }
            synchronized (m0Var) {
                try {
                    l0[] l0VarArr = m0Var.f2657a;
                    l0 l0Var = l0VarArr != null ? l0VarArr[0] : null;
                    if (n0.f2004l.get(n0Var) != 0) {
                        return 1;
                    }
                    if (l0Var == null) {
                        m0Var.f2000c = j4;
                    } else {
                        long j5 = l0Var.f1996d;
                        if (j5 - j4 < 0) {
                            j4 = j5;
                        }
                        if (j4 - m0Var.f2000c > 0) {
                            m0Var.f2000c = j4;
                        }
                    }
                    long j6 = this.f1996d;
                    long j7 = m0Var.f2000c;
                    if (j6 - j7 < 0) {
                        this.f1996d = j7;
                    }
                    m0Var.a(this);
                    return 0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public final void e(m0 m0Var) {
        if (this._heap == x.f2040b) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        this._heap = m0Var;
    }

    public String toString() {
        return "Delayed[nanos=" + this.f1996d + ']';
    }
}
