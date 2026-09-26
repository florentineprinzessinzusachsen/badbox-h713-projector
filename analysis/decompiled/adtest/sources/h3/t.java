package h3;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class t implements q3.u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q3.g f1162d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1163e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1164f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1165g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1166h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f1167i;

    public t(q3.g gVar) {
        j2.i.e(gVar, "source");
        this.f1162d = gVar;
    }

    @Override // q3.u
    public final q3.w f() {
        return this.f1162d.f();
    }

    @Override // q3.u
    public final long g(long j4, q3.e eVar) throws IOException {
        int i4;
        int i5;
        j2.i.e(eVar, "sink");
        do {
            int i6 = this.f1166h;
            q3.g gVar = this.f1162d;
            if (i6 == 0) {
                gVar.skip(this.f1167i);
                this.f1167i = 0;
                if ((this.f1164f & 4) == 0) {
                    i4 = this.f1165g;
                    int iL = b3.d.l(gVar);
                    this.f1166h = iL;
                    this.f1163e = iL;
                    int i7 = gVar.readByte() & 255;
                    this.f1164f = gVar.readByte() & 255;
                    Logger logger = u.f1168g;
                    if (logger.isLoggable(Level.FINE)) {
                        q3.h hVar = h.f1108a;
                        logger.fine(h.b(true, this.f1165g, this.f1163e, i7, this.f1164f));
                    }
                    i5 = gVar.readInt() & Integer.MAX_VALUE;
                    this.f1165g = i5;
                    if (i7 != 9) {
                        throw new IOException(i7 + " != TYPE_CONTINUATION");
                    }
                }
            } else {
                long jG = gVar.g(Math.min(8192L, i6), eVar);
                if (jG != -1) {
                    this.f1166h -= (int) jG;
                    return jG;
                }
            }
            return -1L;
        } while (i5 == i4);
        throw new IOException("TYPE_CONTINUATION streamId changed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }
}
