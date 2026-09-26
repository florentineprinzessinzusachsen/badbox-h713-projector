package h3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class q implements Closeable {
    public static final d0 C;
    public final p A;
    public final LinkedHashSet B;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final n f1131d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final LinkedHashMap f1132e = new LinkedHashMap();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f1133f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1134g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1135h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1136i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final d3.e f1137j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final d3.c f1138k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final d3.c f1139l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d3.c f1140m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c0 f1141n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f1142o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f1143p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public long f1144q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public long f1145r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final c f1146s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final d0 f1147t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public d0 f1148u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final i3.a f1149v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public long f1150w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public long f1151x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final a2.f f1152y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final z f1153z;

    static {
        d0 d0Var = new d0();
        d0Var.c(4, 65535);
        d0Var.c(5, 16384);
        C = d0Var;
    }

    public q(a3.z zVar) {
        this.f1131d = (n) zVar.f283d;
        String str = (String) zVar.f280a;
        if (str == null) {
            j2.i.h("connectionName");
            throw null;
        }
        this.f1133f = str;
        this.f1135h = 3;
        d3.e eVar = (d3.e) zVar.f281b;
        this.f1137j = eVar;
        this.f1138k = eVar.d();
        this.f1139l = eVar.d();
        this.f1140m = eVar.d();
        this.f1141n = c0.f1078a;
        this.f1146s = (c) zVar.f284e;
        d0 d0Var = new d0();
        d0Var.c(4, 16777216);
        this.f1147t = d0Var;
        d0 d0Var2 = C;
        this.f1148u = d0Var2;
        this.f1149v = new i3.a(0);
        this.f1151x = d0Var2.a();
        a2.f fVar = (a2.f) zVar.f282c;
        if (fVar == null) {
            j2.i.h("socket");
            throw null;
        }
        this.f1152y = fVar;
        this.f1153z = new z((q3.n) fVar.f47g);
        this.A = new p(this, new u((q3.o) fVar.f46f));
        this.B = new LinkedHashSet();
    }

    public final void A(b bVar) {
        synchronized (this.f1153z) {
            synchronized (this) {
                if (this.f1136i) {
                    return;
                }
                this.f1136i = true;
                this.f1153z.l(this.f1134g, bVar, b3.d.f343a);
            }
        }
    }

    public final void C(long j4) {
        synchronized (this) {
            try {
                i3.a.b(this.f1149v, j4, 0L, 2);
                long jA = this.f1149v.a();
                if (jA >= this.f1147t.a() / 2) {
                    S(0, jA);
                    i3.a.b(this.f1149v, 0L, jA, 1);
                }
                c cVar = this.f1146s;
                i3.a aVar = this.f1149v;
                cVar.getClass();
                j2.i.e(aVar, "windowCounter");
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void J(int i4, boolean z3, q3.e eVar, long j4) {
        long j5;
        long j6;
        int iMin;
        long j7;
        if (j4 == 0) {
            this.f1153z.c(z3, i4, eVar, 0);
            return;
        }
        while (j4 > 0) {
            synchronized (this) {
                while (true) {
                    try {
                        try {
                            j5 = this.f1150w;
                            j6 = this.f1151x;
                            if (j5 >= j6) {
                                if (!this.f1132e.containsKey(Integer.valueOf(i4))) {
                                    throw new IOException("stream closed");
                                }
                                wait();
                            }
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            throw new InterruptedIOException();
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                iMin = Math.min((int) Math.min(j4, j6 - j5), this.f1153z.f1199f);
                j7 = iMin;
                this.f1150w += j7;
            }
            j4 -= j7;
            this.f1153z.c(z3 && j4 == 0, i4, eVar, iMin);
        }
    }

    public final void K(final int i4, final b bVar) {
        d3.c.c(this.f1138k, this.f1133f + '[' + i4 + "] writeSynReset", new i2.a() { // from class: h3.j
            @Override // i2.a
            public final Object a() {
                q qVar = this.f1115d;
                try {
                    qVar.f1153z.J(i4, bVar);
                } catch (IOException e4) {
                    b bVar2 = b.PROTOCOL_ERROR;
                    qVar.b(bVar2, bVar2, e4);
                }
                return u1.k.f2301a;
            }
        });
    }

    public final void S(final int i4, final long j4) {
        d3.c.c(this.f1138k, this.f1133f + '[' + i4 + "] windowUpdate", new i2.a() { // from class: h3.i
            @Override // i2.a
            public final Object a() {
                q qVar = this.f1112d;
                try {
                    qVar.f1153z.S(i4, j4);
                } catch (IOException e4) {
                    b bVar = b.PROTOCOL_ERROR;
                    qVar.b(bVar, bVar, e4);
                }
                return u1.k.f2301a;
            }
        });
    }

    public final void b(b bVar, b bVar2, IOException iOException) {
        int i4;
        Object[] array;
        TimeZone timeZone = b3.g.f348a;
        try {
            A(bVar);
        } catch (IOException unused) {
        }
        synchronized (this) {
            if (this.f1132e.isEmpty()) {
                array = null;
            } else {
                array = this.f1132e.values().toArray(new y[0]);
                this.f1132e.clear();
            }
        }
        y[] yVarArr = (y[]) array;
        if (yVarArr != null) {
            for (y yVar : yVarArr) {
                try {
                    yVar.e(bVar2, iOException);
                } catch (IOException unused2) {
                }
            }
        }
        try {
            this.f1153z.close();
        } catch (IOException unused3) {
        }
        try {
            ((Socket) ((a3.l) this.f1152y.f45e).f184e).close();
        } catch (IOException unused4) {
        }
        this.f1138k.f();
        this.f1139l.f();
        this.f1140m.f();
    }

    public final y c(int i4) {
        y yVar;
        synchronized (this) {
            yVar = (y) this.f1132e.get(Integer.valueOf(i4));
        }
        return yVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        b(b.NO_ERROR, b.CANCEL, null);
    }

    public final void flush() {
        this.f1153z.flush();
    }

    public final boolean k(long j4) {
        synchronized (this) {
            if (this.f1136i) {
                return false;
            }
            return this.f1144q >= this.f1143p || j4 < this.f1145r;
        }
    }

    public final y l(int i4) {
        y yVar;
        synchronized (this) {
            yVar = (y) this.f1132e.remove(Integer.valueOf(i4));
            notifyAll();
        }
        return yVar;
    }
}
