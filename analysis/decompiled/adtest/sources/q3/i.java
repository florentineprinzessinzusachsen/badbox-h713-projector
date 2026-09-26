package q3;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i extends w {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public w f1827e;

    public i(w wVar) {
        j2.i.e(wVar, "delegate");
        this.f1827e = wVar;
    }

    @Override // q3.w
    public final w a() {
        return this.f1827e.a();
    }

    @Override // q3.w
    public final w b() {
        return this.f1827e.b();
    }

    @Override // q3.w
    public final long c() {
        return this.f1827e.c();
    }

    @Override // q3.w
    public final w d(long j4) {
        return this.f1827e.d(j4);
    }

    @Override // q3.w
    public final boolean e() {
        return this.f1827e.e();
    }

    @Override // q3.w
    public final void f() throws InterruptedIOException {
        this.f1827e.f();
    }

    @Override // q3.w
    public final w g(long j4) {
        j2.i.e(TimeUnit.MILLISECONDS, "unit");
        return this.f1827e.g(j4);
    }
}
