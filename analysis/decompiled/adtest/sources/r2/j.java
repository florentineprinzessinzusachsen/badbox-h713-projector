package r2;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f1990c = AtomicIntegerFieldUpdater.newUpdater(j.class, "_resumed$volatile");
    private volatile /* synthetic */ int _resumed$volatile;

    public j(i iVar, Throwable th, boolean z3) {
        if (th == null) {
            th = new CancellationException("Continuation " + iVar + " was cancelled normally");
        }
        super(th, z3);
        this._resumed$volatile = 0;
    }
}
