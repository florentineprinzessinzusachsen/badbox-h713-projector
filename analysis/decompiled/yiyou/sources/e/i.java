package e;

import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: ForwardingTimeout.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends t {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private t f4737e;

    public i(t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f4737e = tVar;
    }

    public final i a(t tVar) {
        if (tVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f4737e = tVar;
        return this;
    }

    @Override // e.t
    public t b() {
        return this.f4737e.b();
    }

    @Override // e.t
    public long c() {
        return this.f4737e.c();
    }

    @Override // e.t
    public boolean d() {
        return this.f4737e.d();
    }

    @Override // e.t
    public void e() throws InterruptedIOException {
        this.f4737e.e();
    }

    public final t g() {
        return this.f4737e;
    }

    @Override // e.t
    public t a(long j, TimeUnit timeUnit) {
        return this.f4737e.a(j, timeUnit);
    }

    @Override // e.t
    public t a(long j) {
        return this.f4737e.a(j);
    }

    @Override // e.t
    public t a() {
        return this.f4737e.a();
    }
}
