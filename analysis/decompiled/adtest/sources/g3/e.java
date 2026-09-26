package g3;

import a3.r;
import a3.t;
import j2.i;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f971h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ h f972i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(h hVar, t tVar, long j4) {
        super(hVar, tVar);
        i.e(tVar, "url");
        this.f972i = hVar;
        this.f971h = j4;
        if (j4 == 0) {
            b(r.f198e);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        boolean zG;
        if (this.f963f) {
            return;
        }
        if (this.f971h != 0) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            TimeZone timeZone = b3.g.f348a;
            i.e(timeUnit, "timeUnit");
            try {
                zG = b3.g.g(this, 100);
            } catch (IOException unused) {
                zG = false;
            }
            if (!zG) {
                this.f972i.f979b.g();
                b(h.f977f);
            }
        }
        this.f963f = true;
    }

    @Override // g3.b, q3.u
    public final long g(long j4, q3.e eVar) throws IOException {
        i.e(eVar, "sink");
        if (this.f963f) {
            throw new IllegalStateException("closed");
        }
        long j5 = this.f971h;
        if (j5 == 0) {
            return -1L;
        }
        long jG = super.g(Math.min(j5, 8192L), eVar);
        if (jG == -1) {
            this.f972i.f979b.g();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            b(h.f977f);
            throw protocolException;
        }
        long j6 = this.f971h - jG;
        this.f971h = j6;
        if (j6 == 0) {
            b(r.f198e);
        }
        return jG;
    }
}
