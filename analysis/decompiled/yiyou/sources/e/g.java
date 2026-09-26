package e;

/* JADX INFO: compiled from: ForwardingSink.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class g implements r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final r f4736a;

    public g(r rVar) {
        if (rVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f4736a = rVar;
    }

    @Override // e.r
    public void a(c cVar, long j) {
        this.f4736a.a(cVar, j);
    }

    @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f4736a.close();
    }

    @Override // e.r, java.io.Flushable
    public void flush() {
        this.f4736a.flush();
    }

    @Override // e.r
    public t timeout() {
        return this.f4736a.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f4736a.toString() + ")";
    }
}
