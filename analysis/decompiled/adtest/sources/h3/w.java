package h3;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class w implements q3.u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1176d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1177e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q3.e f1178f = new q3.e();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final q3.e f1179g = new q3.e();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1180h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ y f1181i;

    public w(y yVar, long j4, boolean z3) {
        this.f1181i = yVar;
        this.f1176d = j4;
        this.f1177e = z3;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j4;
        y yVar = this.f1181i;
        synchronized (yVar) {
            this.f1180h = true;
            q3.e eVar = this.f1179g;
            j4 = eVar.f1822e;
            eVar.skip(j4);
            yVar.notifyAll();
        }
        if (j4 > 0) {
            y yVar2 = this.f1181i;
            TimeZone timeZone = b3.g.f348a;
            yVar2.f1184e.C(j4);
        }
        this.f1181i.a();
    }

    @Override // q3.u
    public final q3.w f() {
        return this.f1181i.f1192m;
    }

    @Override // q3.u
    public final long g(long j4, q3.e eVar) throws Throwable {
        boolean z3;
        Throwable e0Var;
        long jG;
        j2.i.e(eVar, "sink");
        do {
            y yVar = this.f1181i;
            synchronized (yVar) {
                yVar.f1184e.getClass();
                v vVar = yVar.f1191l;
                z3 = true;
                boolean z4 = vVar.f1174f || vVar.f1172d;
                if (z4) {
                    yVar.f1192m.h();
                }
                try {
                    if (yVar.h() == null || this.f1177e) {
                        e0Var = null;
                    } else {
                        e0Var = yVar.f1195p;
                        if (e0Var == null) {
                            b bVarH = yVar.h();
                            j2.i.b(bVarH);
                            e0Var = new e0(bVarH);
                        }
                    }
                    if (this.f1180h) {
                        throw new IOException("stream closed");
                    }
                    q3.e eVar2 = this.f1179g;
                    long j5 = eVar2.f1822e;
                    if (j5 > 0) {
                        jG = eVar2.g(Math.min(8192L, j5), eVar);
                        i3.a.b(yVar.f1185f, jG, 0L, 2);
                        long jA = yVar.f1185f.a();
                        if (e0Var == null && jA >= yVar.f1184e.f1147t.a() / 2) {
                            yVar.f1184e.S(yVar.f1183d, jA);
                            i3.a.b(yVar.f1185f, 0L, jA, 1);
                        }
                        z3 = false;
                    } else {
                        if (this.f1177e || e0Var != null) {
                            z3 = false;
                        } else {
                            try {
                                yVar.wait();
                            } catch (InterruptedException unused) {
                                Thread.currentThread().interrupt();
                                throw new InterruptedIOException();
                            }
                        }
                        jG = -1;
                    }
                    if (z4) {
                        yVar.f1192m.l();
                    }
                } catch (Throwable th) {
                    if (z4) {
                        yVar.f1192m.l();
                    }
                    throw th;
                }
            }
            this.f1181i.f1184e.f1146s.getClass();
        } while (z3);
        if (jG != -1) {
            return jG;
        }
        if (e0Var == null) {
            return -1L;
        }
        throw e0Var;
    }
}
