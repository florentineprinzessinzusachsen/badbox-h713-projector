package e3;

import java.io.IOException;
import java.net.ProtocolException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements q3.u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q3.u f741d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f742e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f743f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f744g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f745h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f746i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f747j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ h f748k;

    public g(h hVar, q3.u uVar, long j4, boolean z3) {
        j2.i.e(uVar, "delegate");
        this.f748k = hVar;
        this.f741d = uVar;
        this.f742e = j4;
        this.f743f = z3;
        this.f745h = true;
        if (j4 == 0) {
            c(null);
        }
    }

    public final void b() throws IOException {
        this.f741d.close();
    }

    public final IOException c(IOException iOException) {
        if (this.f746i) {
            return iOException;
        }
        this.f746i = true;
        if (iOException == null && this.f745h) {
            this.f745h = false;
        }
        return h.a(this.f748k, this.f743f, iOException, 8);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.f747j) {
            return;
        }
        this.f747j = true;
        try {
            b();
            c(null);
        } catch (IOException e4) {
            IOException iOExceptionC = c(e4);
            j2.i.b(iOExceptionC);
            throw iOExceptionC;
        }
    }

    @Override // q3.u
    public final q3.w f() {
        return this.f741d.f();
    }

    @Override // q3.u
    public final long g(long j4, q3.e eVar) throws IOException {
        h hVar = this.f748k;
        j2.i.e(eVar, "sink");
        if (this.f747j) {
            throw new IllegalStateException("closed");
        }
        try {
            long jG = this.f741d.g(8192L, eVar);
            if (this.f745h) {
                this.f745h = false;
            }
            if (jG == -1) {
                c(null);
                return -1L;
            }
            long j5 = this.f744g + jG;
            long j6 = this.f742e;
            if (j6 == -1 || j5 <= j6) {
                this.f744g = j5;
                if (((f3.g) hVar.f752g).b()) {
                    c(null);
                }
                return jG;
            }
            throw new ProtocolException("expected " + j6 + " bytes but received " + j5);
        } catch (IOException e4) {
            IOException iOExceptionC = c(e4);
            j2.i.b(iOExceptionC);
            throw iOExceptionC;
        }
    }

    public final String toString() {
        return g.class.getSimpleName() + '(' + this.f741d + ')';
    }
}
