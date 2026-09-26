package g3;

import a3.t;
import d0.l0;
import j2.i;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import p2.p;
import q3.o;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f968h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f969i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ h f970j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(h hVar, t tVar) {
        super(hVar, tVar);
        i.e(tVar, "url");
        this.f970j = hVar;
        this.f968h = -1L;
        this.f969i = true;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        boolean zG;
        if (this.f963f) {
            return;
        }
        if (this.f969i) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            TimeZone timeZone = b3.g.f348a;
            i.e(timeUnit, "timeUnit");
            try {
                zG = b3.g.g(this, 100);
            } catch (IOException unused) {
                zG = false;
            }
            if (!zG) {
                this.f970j.f979b.g();
                b(h.f977f);
            }
        }
        this.f963f = true;
    }

    @Override // g3.b, q3.u
    public final long g(long j4, q3.e eVar) throws IOException {
        long j5;
        h hVar = this.f970j;
        a2.f fVar = hVar.f980c;
        i.e(eVar, "sink");
        if (this.f963f) {
            throw new IllegalStateException("closed");
        }
        long j6 = -1;
        if (!this.f969i) {
            return -1L;
        }
        long j7 = this.f968h;
        if (j7 == 0 || j7 == -1) {
            if (j7 != -1) {
                ((o) fVar.f46f).t(Long.MAX_VALUE);
            }
            try {
                o oVar = (o) fVar.f46f;
                q3.e eVar2 = oVar.f1845e;
                oVar.G(1L);
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    j5 = j6;
                    if (!oVar.l(i5)) {
                        break;
                    }
                    byte bK = eVar2.k(i4);
                    if ((bK >= 48 && bK <= 57) || ((bK >= 97 && bK <= 102) || (bK >= 65 && bK <= 70))) {
                        j6 = j5;
                        i4 = i5;
                    }
                    if (i4 != 0) {
                        break;
                    }
                    l0.h(16);
                    String string = Integer.toString(bK, 16);
                    i.d(string, "toString(...)");
                    throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(string));
                }
                this.f968h = eVar2.C();
                String string2 = p2.i.S0(((o) fVar.f46f).t(Long.MAX_VALUE)).toString();
                if (this.f968h < 0 || (string2.length() > 0 && !p.z0(string2, ";", false))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f968h + string2 + '\"');
                }
                if (this.f968h == 0) {
                    this.f969i = false;
                    b(hVar.f982e.a());
                }
                if (!this.f969i) {
                    return j5;
                }
            } catch (NumberFormatException e4) {
                throw new ProtocolException(e4.getMessage());
            }
        } else {
            j5 = -1;
        }
        long jG = super.g(Math.min(8192L, this.f968h), eVar);
        if (jG != j5) {
            this.f968h -= jG;
            return jG;
        }
        hVar.f979b.g();
        ProtocolException protocolException = new ProtocolException("unexpected end of stream");
        b(h.f977f);
        throw protocolException;
    }
}
