package d.h0.f;

import java.io.IOException;

/* JADX INFO: compiled from: RouteException.java */
/* JADX INFO: loaded from: classes.dex */
public final class e extends RuntimeException {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private IOException f4393a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private IOException f4394b;

    public e(IOException iOException) {
        super(iOException);
        this.f4393a = iOException;
        this.f4394b = iOException;
    }

    public IOException a() {
        return this.f4393a;
    }

    public IOException b() {
        return this.f4394b;
    }

    public void a(IOException iOException) {
        d.h0.c.a((Throwable) this.f4393a, (Throwable) iOException);
        this.f4394b = iOException;
    }
}
