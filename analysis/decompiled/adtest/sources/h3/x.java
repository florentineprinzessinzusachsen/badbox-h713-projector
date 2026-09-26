package h3;

import java.io.IOException;
import java.net.SocketTimeoutException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class x extends q3.c {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ y f1182n;

    public x(y yVar) {
        this.f1182n = yVar;
    }

    @Override // q3.c
    public final IOException j(IOException iOException) {
        return new SocketTimeoutException("timeout");
    }

    @Override // q3.c
    public final void k() {
        this.f1182n.g(b.CANCEL);
        q qVar = this.f1182n.f1184e;
        synchronized (qVar) {
            long j4 = qVar.f1144q;
            long j5 = qVar.f1143p;
            if (j4 < j5) {
                return;
            }
            qVar.f1143p = j5 + 1;
            qVar.f1145r = System.nanoTime() + ((long) 1000000000);
            d3.c.c(qVar.f1138k, qVar.f1133f + " ping", new a3.o(4, qVar));
        }
    }

    public final void l() {
        if (i()) {
            throw j(null);
        }
    }
}
