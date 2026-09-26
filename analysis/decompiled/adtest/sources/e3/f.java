package e3;

import java.io.IOException;
import java.net.ProtocolException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements q3.s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q3.s f733d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f734e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f735f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f736g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f737h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f738i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f739j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ h f740k;

    public f(h hVar, q3.s sVar, long j4, boolean z3) {
        j2.i.e(sVar, "delegate");
        this.f740k = hVar;
        this.f733d = sVar;
        this.f734e = j4;
        this.f735f = z3;
        this.f738i = z3;
    }

    @Override // q3.s
    public final void R(long j4, q3.e eVar) throws IOException {
        if (this.f739j) {
            throw new IllegalStateException("closed");
        }
        long j5 = this.f734e;
        if (j5 != -1 && this.f737h + j4 > j5) {
            throw new ProtocolException("expected " + j5 + " bytes but received " + (this.f737h + j4));
        }
        try {
            if (this.f738i) {
                this.f738i = false;
            }
            this.f733d.R(j4, eVar);
            this.f737h += j4;
        } catch (IOException e4) {
            IOException iOExceptionC = c(e4);
            j2.i.b(iOExceptionC);
            throw iOExceptionC;
        }
    }

    public final void b() {
        this.f733d.close();
    }

    public final IOException c(IOException iOException) {
        if (this.f736g) {
            return iOException;
        }
        this.f736g = true;
        return h.a(this.f740k, this.f735f, iOException, 4);
    }

    @Override // q3.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f739j) {
            return;
        }
        this.f739j = true;
        long j4 = this.f734e;
        if (j4 != -1 && this.f737h != j4) {
            throw new ProtocolException("unexpected end of stream");
        }
        try {
            b();
            c(null);
        } catch (IOException e4) {
            IOException iOExceptionC = c(e4);
            j2.i.b(iOExceptionC);
            throw iOExceptionC;
        }
    }

    @Override // q3.s
    public final q3.w f() {
        return this.f733d.f();
    }

    @Override // q3.s, java.io.Flushable
    public final void flush() throws IOException {
        try {
            k();
        } catch (IOException e4) {
            IOException iOExceptionC = c(e4);
            j2.i.b(iOExceptionC);
            throw iOExceptionC;
        }
    }

    public final void k() {
        this.f733d.flush();
    }

    public final String toString() {
        return f.class.getSimpleName() + '(' + this.f733d + ')';
    }
}
