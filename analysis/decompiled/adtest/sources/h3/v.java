package h3;

import java.io.InterruptedIOException;
import java.util.TimeZone;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class v implements q3.s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f1172d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q3.e f1173e = new q3.e();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1174f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ y f1175g;

    public v(y yVar, boolean z3) {
        this.f1175g = yVar;
        this.f1172d = z3;
    }

    @Override // q3.s
    public final void R(long j4, q3.e eVar) {
        TimeZone timeZone = b3.g.f348a;
        q3.e eVar2 = this.f1173e;
        eVar2.R(j4, eVar);
        while (eVar2.f1822e >= 16384) {
            b(false);
        }
    }

    public final void b(boolean z3) {
        long jMin;
        boolean z4;
        y yVar = this.f1175g;
        synchronized (yVar) {
            yVar.f1193n.h();
            while (yVar.f1186g >= yVar.f1187h && !this.f1172d && !this.f1174f && yVar.h() == null) {
                try {
                    try {
                        yVar.wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                } catch (Throwable th) {
                    yVar.f1193n.l();
                    throw th;
                }
            }
            yVar.f1193n.l();
            yVar.b();
            jMin = Math.min(yVar.f1187h - yVar.f1186g, this.f1173e.f1822e);
            yVar.f1186g += jMin;
            z4 = z3 && jMin == this.f1173e.f1822e;
        }
        this.f1175g.f1193n.h();
        try {
            y yVar2 = this.f1175g;
            yVar2.f1184e.J(yVar2.f1183d, z4, this.f1173e, jMin);
        } finally {
            this.f1175g.f1193n.l();
        }
    }

    @Override // q3.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        y yVar = this.f1175g;
        TimeZone timeZone = b3.g.f348a;
        synchronized (yVar) {
            if (this.f1174f) {
                return;
            }
            boolean z3 = yVar.h() == null;
            y yVar2 = this.f1175g;
            if (!yVar2.f1191l.f1172d) {
                if (this.f1173e.f1822e > 0) {
                    while (this.f1173e.f1822e > 0) {
                        b(true);
                    }
                } else if (z3) {
                    yVar2.f1184e.J(yVar2.f1183d, true, null, 0L);
                }
            }
            y yVar3 = this.f1175g;
            synchronized (yVar3) {
                this.f1174f = true;
                yVar3.notifyAll();
            }
            this.f1175g.f1184e.flush();
            this.f1175g.a();
        }
    }

    @Override // q3.s
    public final q3.w f() {
        return this.f1175g.f1193n;
    }

    @Override // q3.s, java.io.Flushable
    public final void flush() {
        y yVar = this.f1175g;
        TimeZone timeZone = b3.g.f348a;
        synchronized (yVar) {
            yVar.b();
        }
        while (this.f1173e.f1822e > 0) {
            b(false);
            this.f1175g.f1184e.flush();
        }
    }
}
