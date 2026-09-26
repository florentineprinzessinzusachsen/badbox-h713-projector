package d.h0.i;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: Http2Writer.java */
/* JADX INFO: loaded from: classes.dex */
final class j implements Closeable {
    private static final Logger g = Logger.getLogger(e.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e.d f4565a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f4566b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f4569e;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final e.c f4567c = new e.c();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final d.b f4570f = new d.b(this.f4567c);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f4568d = 16384;

    j(e.d dVar, boolean z) {
        this.f4565a = dVar;
        this.f4566b = z;
    }

    public synchronized void a() {
        if (this.f4569e) {
            throw new IOException("closed");
        }
        if (this.f4566b) {
            if (g.isLoggable(Level.FINE)) {
                g.fine(d.h0.c.a(">> CONNECTION %s", e.f4480a.b()));
            }
            this.f4565a.write(e.f4480a.h());
            this.f4565a.flush();
        }
    }

    public int b() {
        return this.f4568d;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        this.f4569e = true;
        this.f4565a.close();
    }

    public synchronized void flush() {
        if (this.f4569e) {
            throw new IOException("closed");
        }
        this.f4565a.flush();
    }

    public synchronized void b(m mVar) {
        int i;
        if (this.f4569e) {
            throw new IOException("closed");
        }
        int i2 = 0;
        a(0, mVar.d() * 6, (byte) 4, (byte) 0);
        while (i2 < 10) {
            if (mVar.d(i2)) {
                if (i2 == 4) {
                    i = 3;
                } else {
                    i = i2 == 7 ? 4 : i2;
                }
                this.f4565a.writeShort(i);
                this.f4565a.writeInt(mVar.a(i2));
            }
            i2++;
        }
        this.f4565a.flush();
    }

    public synchronized void a(m mVar) {
        if (!this.f4569e) {
            this.f4568d = mVar.c(this.f4568d);
            if (mVar.b() != -1) {
                this.f4570f.a(mVar.b());
            }
            a(0, 0, (byte) 4, (byte) 1);
            this.f4565a.flush();
        } else {
            throw new IOException("closed");
        }
    }

    private void b(int i, long j) {
        while (j > 0) {
            int iMin = (int) Math.min(this.f4568d, j);
            long j2 = iMin;
            j -= j2;
            a(i, iMin, (byte) 9, j == 0 ? (byte) 4 : (byte) 0);
            this.f4565a.a(this.f4567c, j2);
        }
    }

    public synchronized void a(int i, int i2, List<c> list) {
        if (!this.f4569e) {
            this.f4570f.a(list);
            long jQ = this.f4567c.q();
            int iMin = (int) Math.min(this.f4568d - 4, jQ);
            long j = iMin;
            a(i, iMin + 4, (byte) 5, jQ == j ? (byte) 4 : (byte) 0);
            this.f4565a.writeInt(i2 & Integer.MAX_VALUE);
            this.f4565a.a(this.f4567c, j);
            if (jQ > j) {
                b(i, jQ - j);
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(boolean z, int i, int i2, List<c> list) {
        if (!this.f4569e) {
            a(z, i, list);
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(int i, b bVar) {
        if (!this.f4569e) {
            if (bVar.f4459a != -1) {
                a(i, 4, (byte) 3, (byte) 0);
                this.f4565a.writeInt(bVar.f4459a);
                this.f4565a.flush();
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(boolean z, int i, e.c cVar, int i2) {
        if (!this.f4569e) {
            a(i, z ? (byte) 1 : (byte) 0, cVar, i2);
        } else {
            throw new IOException("closed");
        }
    }

    void a(int i, byte b2, e.c cVar, int i2) {
        a(i, i2, (byte) 0, b2);
        if (i2 > 0) {
            this.f4565a.a(cVar, i2);
        }
    }

    public synchronized void a(boolean z, int i, int i2) {
        if (!this.f4569e) {
            a(0, 8, (byte) 6, z ? (byte) 1 : (byte) 0);
            this.f4565a.writeInt(i);
            this.f4565a.writeInt(i2);
            this.f4565a.flush();
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(int i, b bVar, byte[] bArr) {
        if (!this.f4569e) {
            if (bVar.f4459a != -1) {
                a(0, bArr.length + 8, (byte) 7, (byte) 0);
                this.f4565a.writeInt(i);
                this.f4565a.writeInt(bVar.f4459a);
                if (bArr.length > 0) {
                    this.f4565a.write(bArr);
                }
                this.f4565a.flush();
            } else {
                e.a("errorCode.httpCode == -1", new Object[0]);
                throw null;
            }
        } else {
            throw new IOException("closed");
        }
    }

    public synchronized void a(int i, long j) {
        if (this.f4569e) {
            throw new IOException("closed");
        }
        if (j != 0 && j <= 2147483647L) {
            a(i, 4, (byte) 8, (byte) 0);
            this.f4565a.writeInt((int) j);
            this.f4565a.flush();
        } else {
            e.a("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: %s", Long.valueOf(j));
            throw null;
        }
    }

    public void a(int i, int i2, byte b2, byte b3) {
        if (g.isLoggable(Level.FINE)) {
            g.fine(e.a(false, i, i2, b2, b3));
        }
        int i3 = this.f4568d;
        if (i2 > i3) {
            e.a("FRAME_SIZE_ERROR length > %d: %d", Integer.valueOf(i3), Integer.valueOf(i2));
            throw null;
        }
        if ((Integer.MIN_VALUE & i) != 0) {
            e.a("reserved bit set: %s", Integer.valueOf(i));
            throw null;
        }
        a(this.f4565a, i2);
        this.f4565a.writeByte(b2 & 255);
        this.f4565a.writeByte(b3 & 255);
        this.f4565a.writeInt(i & Integer.MAX_VALUE);
    }

    private static void a(e.d dVar, int i) {
        dVar.writeByte((i >>> 16) & 255);
        dVar.writeByte((i >>> 8) & 255);
        dVar.writeByte(i & 255);
    }

    void a(boolean z, int i, List<c> list) throws IOException {
        if (!this.f4569e) {
            this.f4570f.a(list);
            long jQ = this.f4567c.q();
            int iMin = (int) Math.min(this.f4568d, jQ);
            long j = iMin;
            byte b2 = jQ == j ? (byte) 4 : (byte) 0;
            if (z) {
                b2 = (byte) (b2 | 1);
            }
            a(i, iMin, (byte) 1, b2);
            this.f4565a.a(this.f4567c, j);
            if (jQ > j) {
                b(i, jQ - j);
                return;
            }
            return;
        }
        throw new IOException("closed");
    }
}
