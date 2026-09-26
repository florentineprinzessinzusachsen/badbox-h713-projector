package c.a.z;

/* JADX INFO: compiled from: OnErrorNotImplementedException.java */
/* JADX INFO: loaded from: classes.dex */
public final class d extends RuntimeException {
    public d(Throwable th) {
        super(th != null ? th.getMessage() : null, th == null ? new NullPointerException() : th);
    }
}
