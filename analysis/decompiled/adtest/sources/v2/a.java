package v2;

import java.util.concurrent.CancellationException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a extends CancellationException {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final transient o0.e f2522d;

    public a(o0.e eVar) {
        super("Flow was aborted, no more elements needed");
        this.f2522d = eVar;
    }

    @Override // java.lang.Throwable
    public final Throwable fillInStackTrace() {
        setStackTrace(new StackTraceElement[0]);
        return this;
    }
}
