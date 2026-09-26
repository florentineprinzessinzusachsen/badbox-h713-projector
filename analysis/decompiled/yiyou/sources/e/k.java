package e;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: InflaterSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class k implements s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e f4743a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Inflater f4744b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f4745c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f4746d;

    k(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f4743a = eVar;
        this.f4744b = inflater;
    }

    private void b() {
        int i = this.f4745c;
        if (i == 0) {
            return;
        }
        int remaining = i - this.f4744b.getRemaining();
        this.f4745c -= remaining;
        this.f4743a.skip(remaining);
    }

    public boolean a() {
        if (!this.f4744b.needsInput()) {
            return false;
        }
        b();
        if (this.f4744b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f4743a.j()) {
            return true;
        }
        o oVar = this.f4743a.c().f4727a;
        int i = oVar.f4761c;
        int i2 = oVar.f4760b;
        this.f4745c = i - i2;
        this.f4744b.setInput(oVar.f4759a, i2, this.f4745c);
        return false;
    }

    @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f4746d) {
            return;
        }
        this.f4744b.end();
        this.f4746d = true;
        this.f4743a.close();
    }

    @Override // e.s
    public long read(c cVar, long j) throws IOException {
        boolean zA;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f4746d) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            zA = a();
            try {
                o oVarB = cVar.b(1);
                int iInflate = this.f4744b.inflate(oVarB.f4759a, oVarB.f4761c, (int) Math.min(j, 8192 - oVarB.f4761c));
                if (iInflate > 0) {
                    oVarB.f4761c += iInflate;
                    long j2 = iInflate;
                    cVar.f4728b += j2;
                    return j2;
                }
                if (!this.f4744b.finished() && !this.f4744b.needsDictionary()) {
                }
                b();
                if (oVarB.f4760b != oVarB.f4761c) {
                    return -1L;
                }
                cVar.f4727a = oVarB.b();
                p.a(oVarB);
                return -1L;
            } catch (DataFormatException e2) {
                throw new IOException(e2);
            }
        } while (!zA);
        throw new EOFException("source exhausted prematurely");
    }

    @Override // e.s
    public t timeout() {
        return this.f4743a.timeout();
    }
}
