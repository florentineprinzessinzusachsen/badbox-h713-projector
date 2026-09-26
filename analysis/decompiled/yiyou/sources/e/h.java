package e;

/* JADX INFO: compiled from: ForwardingSource.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h implements s {
    private final s delegate;

    public h(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.delegate = sVar;
    }

    @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.delegate.close();
    }

    public final s delegate() {
        return this.delegate;
    }

    @Override // e.s
    public long read(c cVar, long j) {
        return this.delegate.read(cVar, j);
    }

    @Override // e.s
    public t timeout() {
        return this.delegate.timeout();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.delegate.toString() + ")";
    }
}
