package r2;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class w0 extends CancellationException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient d1 f2038d;

    public w0(String str, Throwable th, d1 d1Var) {
        super(str);
        this.f2038d = d1Var;
        if (th != null) {
            initCause(th);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof w0)) {
            return false;
        }
        w0 w0Var = (w0) obj;
        return j2.i.a(w0Var.getMessage(), getMessage()) && j2.i.a(w0Var.f2038d, this.f2038d) && j2.i.a(w0Var.getCause(), getCause());
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }

    public final int hashCode() {
        String message = getMessage();
        j2.i.b(message);
        int iHashCode = (this.f2038d.hashCode() + (message.hashCode() * 31)) * 31;
        Throwable cause = getCause();
        return iHashCode + (cause != null ? cause.hashCode() : 0);
    }

    @Override // java.lang.Throwable
    public final String toString() {
        return super.toString() + "; job=" + this.f2038d;
    }
}
