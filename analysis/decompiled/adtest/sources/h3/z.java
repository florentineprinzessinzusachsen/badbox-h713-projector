package h3;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class z implements Closeable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Logger f1196i = Logger.getLogger(h.class.getName());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q3.f f1197d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final q3.e f1198e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1199f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1200g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final f f1201h;

    public z(q3.n nVar) {
        j2.i.e(nVar, "sink");
        this.f1197d = nVar;
        q3.e eVar = new q3.e();
        this.f1198e = eVar;
        this.f1199f = 16384;
        this.f1201h = new f(eVar);
    }

    public final void A(boolean z3, int i4, ArrayList arrayList) {
        synchronized (this) {
            if (this.f1200g) {
                throw new IOException("closed");
            }
            this.f1201h.d(arrayList);
            long j4 = this.f1198e.f1822e;
            long jMin = Math.min(this.f1199f, j4);
            int i5 = j4 == jMin ? 4 : 0;
            if (z3) {
                i5 |= 1;
            }
            k(i4, (int) jMin, 1, i5);
            this.f1197d.R(jMin, this.f1198e);
            if (j4 > jMin) {
                long j5 = j4 - jMin;
                while (j5 > 0) {
                    long jMin2 = Math.min(this.f1199f, j5);
                    j5 -= jMin2;
                    k(i4, (int) jMin2, 9, j5 == 0 ? 4 : 0);
                    this.f1197d.R(jMin2, this.f1198e);
                }
            }
        }
    }

    public final void C(int i4, int i5, boolean z3) {
        synchronized (this) {
            if (this.f1200g) {
                throw new IOException("closed");
            }
            k(0, 8, 6, z3 ? 1 : 0);
            this.f1197d.writeInt(i4);
            this.f1197d.writeInt(i5);
            this.f1197d.flush();
        }
    }

    public final void J(int i4, b bVar) {
        synchronized (this) {
            if (this.f1200g) {
                throw new IOException("closed");
            }
            if (bVar.f1073d == -1) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            k(i4, 4, 3, 0);
            this.f1197d.writeInt(bVar.f1073d);
            this.f1197d.flush();
        }
    }

    public final void K(d0 d0Var) {
        j2.i.e(d0Var, "settings");
        synchronized (this) {
            try {
                if (this.f1200g) {
                    throw new IOException("closed");
                }
                k(0, Integer.bitCount(d0Var.f1088a) * 6, 4, 0);
                for (int i4 = 0; i4 < 10; i4++) {
                    boolean z3 = true;
                    if (((1 << i4) & d0Var.f1088a) == 0) {
                        z3 = false;
                    }
                    if (z3) {
                        this.f1197d.writeShort(i4);
                        this.f1197d.writeInt(d0Var.f1089b[i4]);
                    }
                }
                this.f1197d.flush();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void S(int i4, long j4) {
        synchronized (this) {
            try {
                if (this.f1200g) {
                    throw new IOException("closed");
                }
                if (j4 == 0 || j4 > 2147483647L) {
                    throw new IllegalArgumentException(("windowSizeIncrement == 0 || windowSizeIncrement > 0x7fffffffL: " + j4).toString());
                }
                Logger logger = f1196i;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(h.c(false, i4, 4, j4));
                }
                k(i4, 4, 8, 0);
                this.f1197d.writeInt((int) j4);
                this.f1197d.flush();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void b(d0 d0Var) {
        j2.i.e(d0Var, "peerSettings");
        synchronized (this) {
            try {
                if (this.f1200g) {
                    throw new IOException("closed");
                }
                int i4 = this.f1199f;
                int i5 = d0Var.f1088a;
                if ((i5 & 32) != 0) {
                    i4 = d0Var.f1089b[5];
                }
                this.f1199f = i4;
                if (((i5 & 2) != 0 ? d0Var.f1089b[1] : -1) != -1) {
                    f fVar = this.f1201h;
                    int i6 = (i5 & 2) != 0 ? d0Var.f1089b[1] : -1;
                    fVar.getClass();
                    int iMin = Math.min(i6, 16384);
                    int i7 = fVar.f1101d;
                    if (i7 != iMin) {
                        if (iMin < i7) {
                            fVar.f1099b = Math.min(fVar.f1099b, iMin);
                        }
                        fVar.f1100c = true;
                        fVar.f1101d = iMin;
                        int i8 = fVar.f1105h;
                        if (iMin < i8) {
                            if (iMin == 0) {
                                d[] dVarArr = fVar.f1102e;
                                v1.i.Z(dVarArr, null, 0, dVarArr.length);
                                fVar.f1103f = fVar.f1102e.length - 1;
                                fVar.f1104g = 0;
                                fVar.f1105h = 0;
                            } else {
                                fVar.a(i8 - iMin);
                            }
                        }
                    }
                }
                k(0, 0, 4, 1);
                this.f1197d.flush();
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void c(boolean z3, int i4, q3.e eVar, int i5) {
        synchronized (this) {
            if (this.f1200g) {
                throw new IOException("closed");
            }
            k(i4, i5, 0, z3 ? 1 : 0);
            if (i5 > 0) {
                q3.f fVar = this.f1197d;
                j2.i.b(eVar);
                fVar.R(i5, eVar);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        synchronized (this) {
            this.f1200g = true;
            this.f1197d.close();
        }
    }

    public final void flush() {
        synchronized (this) {
            if (this.f1200g) {
                throw new IOException("closed");
            }
            this.f1197d.flush();
        }
    }

    public final void k(int i4, int i5, int i6, int i7) {
        if (i6 != 8) {
            Level level = Level.FINE;
            Logger logger = f1196i;
            if (logger.isLoggable(level)) {
                logger.fine(h.b(false, i4, i5, i6, i7));
            }
        }
        if (i5 > this.f1199f) {
            throw new IllegalArgumentException(("FRAME_SIZE_ERROR length > " + this.f1199f + ": " + i5).toString());
        }
        if ((Integer.MIN_VALUE & i4) != 0) {
            throw new IllegalArgumentException(a1.c.c(i4, "reserved bit set: ").toString());
        }
        byte[] bArr = b3.d.f343a;
        q3.f fVar = this.f1197d;
        j2.i.e(fVar, "<this>");
        fVar.writeByte((i5 >>> 16) & 255);
        fVar.writeByte((i5 >>> 8) & 255);
        fVar.writeByte(i5 & 255);
        fVar.writeByte(i6 & 255);
        fVar.writeByte(i7 & 255);
        fVar.writeInt(i4 & Integer.MAX_VALUE);
    }

    public final void l(int i4, b bVar, byte[] bArr) {
        synchronized (this) {
            if (this.f1200g) {
                throw new IOException("closed");
            }
            if (bVar.f1073d == -1) {
                throw new IllegalArgumentException("errorCode.httpCode == -1");
            }
            k(0, bArr.length + 8, 7, 0);
            this.f1197d.writeInt(i4);
            this.f1197d.writeInt(bVar.f1073d);
            if (bArr.length != 0) {
                this.f1197d.write(bArr);
            }
            this.f1197d.flush();
        }
    }
}
