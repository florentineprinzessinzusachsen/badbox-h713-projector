package h3;

import d0.l0;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class u implements Closeable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Logger f1168g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final q3.g f1169d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final t f1170e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final e f1171f;

    static {
        Logger logger = Logger.getLogger(h.class.getName());
        j2.i.d(logger, "getLogger(...)");
        f1168g = logger;
    }

    public u(q3.o oVar) {
        j2.i.e(oVar, "source");
        this.f1169d = oVar;
        t tVar = new t(oVar);
        this.f1170e = tVar;
        this.f1171f = new e(tVar);
    }

    public final void A(p pVar, int i4, int i5, int i6) throws IOException {
        if (i6 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0");
        }
        int i7 = 0;
        int i8 = 1;
        boolean z3 = (i5 & 1) != 0;
        if ((i5 & 8) != 0) {
            byte b4 = this.f1169d.readByte();
            byte[] bArr = b3.d.f343a;
            i7 = b4 & 255;
        }
        if ((i5 & 32) != 0) {
            q3.g gVar = this.f1169d;
            gVar.readInt();
            gVar.readByte();
            byte[] bArr2 = b3.d.f343a;
            i4 -= 5;
        }
        List listL = l(s.a(i4, i5, i7), i7, i5, i6);
        q qVar = pVar.f1130e;
        if (i6 != 0 && (i6 & 1) == 0) {
            d3.c.c(qVar.f1139l, qVar.f1133f + '[' + i6 + "] onHeaders", new l(qVar, i6, listL, z3));
            return;
        }
        synchronized (qVar) {
            y yVarC = qVar.c(i6);
            if (yVarC != null) {
                yVarC.k(b3.g.h(listL), z3);
                return;
            }
            if (qVar.f1136i) {
                return;
            }
            if (i6 <= qVar.f1134g) {
                return;
            }
            if (i6 % 2 == qVar.f1135h % 2) {
                return;
            }
            y yVar = new y(i6, qVar, false, z3, b3.g.h(listL));
            qVar.f1134g = i6;
            qVar.f1132e.put(Integer.valueOf(i6), yVar);
            d3.c.c(qVar.f1137j.d(), qVar.f1133f + '[' + i6 + "] onStream", new h0.k(i8, qVar, yVar));
        }
    }

    public final void C(p pVar, int i4, int i5, int i6) throws IOException {
        if (i4 != 8) {
            throw new IOException(a1.c.c(i4, "TYPE_PING length != 8: "));
        }
        if (i6 != 0) {
            throw new IOException("TYPE_PING streamId != 0");
        }
        final int i7 = this.f1169d.readInt();
        final int i8 = this.f1169d.readInt();
        if (!((i5 & 1) != 0)) {
            d3.c cVar = pVar.f1130e.f1138k;
            String str = pVar.f1130e.f1133f + " ping";
            final q qVar = pVar.f1130e;
            d3.c.c(cVar, str, new i2.a() { // from class: h3.o
                @Override // i2.a
                public final Object a() {
                    q qVar2 = qVar;
                    try {
                        qVar2.f1153z.C(i7, i8, true);
                    } catch (IOException e4) {
                        b bVar = b.PROTOCOL_ERROR;
                        qVar2.b(bVar, bVar, e4);
                    }
                    return u1.k.f2301a;
                }
            });
            return;
        }
        q qVar2 = pVar.f1130e;
        synchronized (qVar2) {
            try {
                if (i7 == 1) {
                    qVar2.f1142o++;
                } else if (i7 == 2) {
                    qVar2.f1144q++;
                } else if (i7 == 3) {
                    qVar2.notifyAll();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void J(p pVar, int i4, int i5, int i6) throws IOException {
        int i7;
        if (i6 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0");
        }
        int i8 = 0;
        if ((i5 & 8) != 0) {
            byte b4 = this.f1169d.readByte();
            byte[] bArr = b3.d.f343a;
            i7 = b4 & 255;
        } else {
            i7 = 0;
        }
        int i9 = this.f1169d.readInt() & Integer.MAX_VALUE;
        List listL = l(s.a(i4 - 4, i5, i7), i7, i5, i6);
        q qVar = pVar.f1130e;
        synchronized (qVar) {
            if (qVar.B.contains(Integer.valueOf(i9))) {
                qVar.K(i9, b.PROTOCOL_ERROR);
                return;
            }
            qVar.B.add(Integer.valueOf(i9));
            d3.c.c(qVar.f1139l, qVar.f1133f + '[' + i9 + "] onRequest", new l(qVar, i9, listL, i8));
        }
    }

    public final boolean b(boolean z3, p pVar) throws Exception {
        b bVar;
        int i4 = 0;
        try {
            this.f1169d.G(9L);
            int iL = b3.d.l(this.f1169d);
            if (iL > 16384) {
                throw new IOException(a1.c.c(iL, "FRAME_SIZE_ERROR: "));
            }
            int i5 = this.f1169d.readByte() & 255;
            byte b4 = this.f1169d.readByte();
            int i6 = b4 & 255;
            int i7 = this.f1169d.readInt();
            int i8 = Integer.MAX_VALUE & i7;
            int i9 = 1;
            if (i5 != 8) {
                Logger logger = f1168g;
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(h.b(true, i8, iL, i5, i6));
                }
            }
            if (z3 && i5 != 4) {
                throw new IOException("Expected a SETTINGS frame but was " + h.a(i5));
            }
            switch (i5) {
                case 0:
                    c(pVar, iL, i6, i8);
                    return true;
                case 1:
                    A(pVar, iL, i6, i8);
                    return true;
                case 2:
                    if (iL != 5) {
                        throw new IOException(a1.c.d(iL, "TYPE_PRIORITY length: ", " != 5"));
                    }
                    if (i8 == 0) {
                        throw new IOException("TYPE_PRIORITY streamId == 0");
                    }
                    q3.g gVar = this.f1169d;
                    gVar.readInt();
                    gVar.readByte();
                    return true;
                case 3:
                    if (iL != 4) {
                        throw new IOException(a1.c.d(iL, "TYPE_RST_STREAM length: ", " != 4"));
                    }
                    if (i8 == 0) {
                        throw new IOException("TYPE_RST_STREAM streamId == 0");
                    }
                    int i10 = this.f1169d.readInt();
                    b.f1065e.getClass();
                    b[] bVarArrValues = b.values();
                    int length = bVarArrValues.length;
                    while (true) {
                        if (i4 < length) {
                            bVar = bVarArrValues[i4];
                            if (bVar.f1073d != i10) {
                                i4++;
                            }
                        } else {
                            bVar = null;
                        }
                    }
                    if (bVar == null) {
                        throw new IOException(a1.c.c(i10, "TYPE_RST_STREAM unexpected error code: "));
                    }
                    q qVar = pVar.f1130e;
                    if (i8 == 0 || (i7 & 1) != 0) {
                        y yVarL = qVar.l(i8);
                        if (yVarL != null) {
                            yVarL.l(bVar);
                        }
                        return true;
                    }
                    d3.c.c(qVar.f1139l, qVar.f1133f + '[' + i8 + "] onReset", new l(qVar, i8, bVar, i9));
                    return true;
                case 4:
                    q3.g gVar2 = this.f1169d;
                    if (i8 != 0) {
                        throw new IOException("TYPE_SETTINGS streamId != 0");
                    }
                    if ((b4 & 1) != 0) {
                        if (iL != 0) {
                            throw new IOException("FRAME_SIZE_ERROR ack frame should be empty!");
                        }
                        return true;
                    }
                    if (iL % 6 != 0) {
                        throw new IOException(a1.c.c(iL, "TYPE_SETTINGS length % 6 != 0: "));
                    }
                    d0 d0Var = new d0();
                    m2.a aVarK = l0.K(l0.Q(0, iL), 6);
                    int i11 = aVarK.f1443d;
                    int i12 = aVarK.f1444e;
                    int i13 = aVarK.f1445f;
                    int i14 = 2;
                    if ((i13 > 0 && i11 <= i12) || (i13 < 0 && i12 <= i11)) {
                        while (true) {
                            short s3 = gVar2.readShort();
                            byte[] bArr = b3.d.f343a;
                            int i15 = s3 & 65535;
                            int i16 = gVar2.readInt();
                            if (i15 != 2) {
                                if (i15 != 4) {
                                    if (i15 == 5 && (i16 < 16384 || i16 > 16777215)) {
                                        throw new IOException(a1.c.c(i16, "PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: "));
                                    }
                                } else if (i16 < 0) {
                                    throw new IOException("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1");
                                }
                            } else if (i16 != 0 && i16 != 1) {
                                throw new IOException("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1");
                            }
                            d0Var.c(i15, i16);
                            if (i11 != i12) {
                                i11 += i13;
                            }
                        }
                    }
                    q qVar2 = pVar.f1130e;
                    d3.c.c(qVar2.f1138k, qVar2.f1133f + " applyAndAckSettings", new h0.k(i14, pVar, d0Var));
                    return true;
                case 5:
                    J(pVar, iL, i6, i8);
                    return true;
                case 6:
                    C(pVar, iL, i6, i8);
                    return true;
                case 7:
                    k(pVar, iL, i8);
                    return true;
                case 8:
                    try {
                        if (iL != 4) {
                            throw new IOException("TYPE_WINDOW_UPDATE length !=4: " + iL);
                        }
                        long j4 = 2147483647L & ((long) this.f1169d.readInt());
                        if (j4 == 0) {
                            throw new IOException("windowSizeIncrement was 0");
                        }
                        Logger logger2 = f1168g;
                        if (logger2.isLoggable(Level.FINE)) {
                            logger2.fine(h.c(true, i8, iL, j4));
                        }
                        if (i8 == 0) {
                            q qVar3 = pVar.f1130e;
                            synchronized (qVar3) {
                                qVar3.f1151x += j4;
                                qVar3.notifyAll();
                            }
                            return true;
                        }
                        y yVarC = pVar.f1130e.c(i8);
                        if (yVarC != null) {
                            synchronized (yVarC) {
                                yVarC.f1187h += j4;
                                if (j4 > 0) {
                                    yVarC.notifyAll();
                                }
                                break;
                            }
                            return true;
                        }
                        return true;
                    } catch (Exception e4) {
                        f1168g.fine(h.b(true, i8, iL, 8, i6));
                        throw e4;
                    }
                default:
                    this.f1169d.skip(iL);
                    return true;
            }
        } catch (EOFException unused) {
            return false;
        }
    }

    public final void c(p pVar, int i4, int i5, final int i6) throws IOException {
        int i7;
        boolean z3;
        boolean z4;
        boolean z5;
        if (i6 == 0) {
            throw new IOException("PROTOCOL_ERROR: TYPE_DATA streamId == 0");
        }
        final boolean z6 = (i5 & 1) != 0;
        if ((i5 & 32) != 0) {
            throw new IOException("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA");
        }
        if ((i5 & 8) != 0) {
            byte b4 = this.f1169d.readByte();
            byte[] bArr = b3.d.f343a;
            i7 = b4 & 255;
        } else {
            i7 = 0;
        }
        final int iA = s.a(i4, i5, i7);
        q3.g gVar = this.f1169d;
        j2.i.e(gVar, "source");
        final q qVar = pVar.f1130e;
        if (i6 == 0 || (i6 & 1) != 0) {
            y yVarC = qVar.c(i6);
            if (yVarC == null) {
                pVar.f1130e.K(i6, b.PROTOCOL_ERROR);
                long j4 = iA;
                pVar.f1130e.C(j4);
                gVar.skip(j4);
            } else {
                TimeZone timeZone = b3.g.f348a;
                w wVar = yVarC.f1190k;
                long j5 = iA;
                wVar.getClass();
                long j6 = j5;
                while (true) {
                    if (j6 <= 0) {
                        z3 = z6;
                        y yVar = wVar.f1181i;
                        TimeZone timeZone2 = b3.g.f348a;
                        yVar.f1184e.C(j5);
                        wVar.f1181i.f1184e.f1146s.getClass();
                        break;
                    }
                    synchronized (wVar.f1181i) {
                        z4 = wVar.f1177e;
                        z3 = z6;
                        z5 = wVar.f1179g.f1822e + j6 > wVar.f1176d;
                    }
                    if (z5) {
                        gVar.skip(j6);
                        wVar.f1181i.g(b.FLOW_CONTROL_ERROR);
                        break;
                    }
                    if (z4) {
                        gVar.skip(j6);
                        break;
                    }
                    long jG = gVar.g(j6, wVar.f1178f);
                    if (jG == -1) {
                        throw new EOFException();
                    }
                    j6 -= jG;
                    y yVar2 = wVar.f1181i;
                    synchronized (yVar2) {
                        try {
                            if (wVar.f1180h) {
                                q3.e eVar = wVar.f1178f;
                                eVar.skip(eVar.f1822e);
                            } else {
                                q3.e eVar2 = wVar.f1179g;
                                boolean z7 = eVar2.f1822e == 0;
                                eVar2.W(wVar.f1178f);
                                if (z7) {
                                    yVar2.notifyAll();
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    z6 = z3;
                }
                if (z3) {
                    yVarC.k(a3.r.f198e, true);
                }
            }
        } else {
            final q3.e eVar3 = new q3.e();
            long j7 = iA;
            gVar.G(j7);
            gVar.g(j7, eVar3);
            d3.c.c(qVar.f1139l, qVar.f1133f + '[' + i6 + "] onData", new i2.a(i6, eVar3, iA, z6) { // from class: h3.k

                /* JADX INFO: renamed from: e, reason: collision with root package name */
                public final /* synthetic */ int f1119e;

                /* JADX INFO: renamed from: f, reason: collision with root package name */
                public final /* synthetic */ q3.e f1120f;

                /* JADX INFO: renamed from: g, reason: collision with root package name */
                public final /* synthetic */ int f1121g;

                @Override // i2.a
                public final Object a() {
                    q qVar2 = this.f1118d;
                    int i8 = this.f1119e;
                    q3.e eVar4 = this.f1120f;
                    int i9 = this.f1121g;
                    try {
                        qVar2.f1141n.getClass();
                        eVar4.skip(i9);
                        qVar2.f1153z.J(i8, b.CANCEL);
                        synchronized (qVar2) {
                            qVar2.B.remove(Integer.valueOf(i8));
                        }
                    } catch (IOException unused) {
                    }
                    return u1.k.f2301a;
                }
            });
        }
        this.f1169d.skip(i7);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f1169d.close();
    }

    public final void k(p pVar, int i4, int i5) throws IOException {
        b bVar;
        Object[] array;
        if (i4 < 8) {
            throw new IOException(a1.c.c(i4, "TYPE_GOAWAY length < 8: "));
        }
        if (i5 != 0) {
            throw new IOException("TYPE_GOAWAY streamId != 0");
        }
        int i6 = this.f1169d.readInt();
        int i7 = this.f1169d.readInt();
        int i8 = i4 - 8;
        b.f1065e.getClass();
        b[] bVarArrValues = b.values();
        int length = bVarArrValues.length;
        int i9 = 0;
        while (true) {
            if (i9 >= length) {
                bVar = null;
                break;
            }
            bVar = bVarArrValues[i9];
            if (bVar.f1073d == i7) {
                break;
            } else {
                i9++;
            }
        }
        if (bVar == null) {
            throw new IOException(a1.c.c(i7, "TYPE_GOAWAY unexpected error code: "));
        }
        q3.h hVarQ = q3.h.f1823g;
        if (i8 > 0) {
            hVarQ = this.f1169d.q(i8);
        }
        j2.i.e(hVarQ, "debugData");
        hVarQ.a();
        q qVar = pVar.f1130e;
        synchronized (qVar) {
            array = qVar.f1132e.values().toArray(new y[0]);
            qVar.f1136i = true;
        }
        for (y yVar : (y[]) array) {
            if (yVar.f1183d > i6 && yVar.i()) {
                yVar.l(b.REFUSED_STREAM);
                pVar.f1130e.l(yVar.f1183d);
            }
        }
    }

    public final List l(int i4, int i5, int i6, int i7) throws IOException {
        t tVar = this.f1170e;
        tVar.f1166h = i4;
        tVar.f1163e = i4;
        tVar.f1167i = i5;
        tVar.f1164f = i6;
        tVar.f1165g = i7;
        e eVar = this.f1171f;
        q3.o oVar = eVar.f1092c;
        ArrayList arrayList = eVar.f1091b;
        while (!oVar.b()) {
            byte b4 = oVar.readByte();
            byte[] bArr = b3.d.f343a;
            int i8 = b4 & 255;
            if (i8 == 128) {
                throw new IOException("index == 0");
            }
            if ((b4 & 128) == 128) {
                int iE = eVar.e(i8, 127);
                int i9 = iE - 1;
                if (i9 >= 0) {
                    d[] dVarArr = g.f1106a;
                    if (i9 <= dVarArr.length - 1) {
                        arrayList.add(dVarArr[i9]);
                    }
                }
                int length = eVar.f1094e + 1 + (i9 - g.f1106a.length);
                if (length >= 0) {
                    d[] dVarArr2 = eVar.f1093d;
                    if (length < dVarArr2.length) {
                        d dVar = dVarArr2[length];
                        j2.i.b(dVar);
                        arrayList.add(dVar);
                    }
                }
                throw new IOException(a1.c.c(iE, "Header index too large "));
            }
            if (i8 == 64) {
                d[] dVarArr3 = g.f1106a;
                q3.h hVarD = eVar.d();
                g.a(hVarD);
                eVar.c(new d(hVarD, eVar.d()));
            } else if ((b4 & 64) == 64) {
                eVar.c(new d(eVar.b(eVar.e(i8, 63) - 1), eVar.d()));
            } else if ((b4 & 32) == 32) {
                int iE2 = eVar.e(i8, 31);
                eVar.f1090a = iE2;
                if (iE2 < 0 || iE2 > 4096) {
                    throw new IOException("Invalid dynamic table size update " + eVar.f1090a);
                }
                int i10 = eVar.f1096g;
                if (iE2 < i10) {
                    if (iE2 == 0) {
                        d[] dVarArr4 = eVar.f1093d;
                        v1.i.Z(dVarArr4, null, 0, dVarArr4.length);
                        eVar.f1094e = eVar.f1093d.length - 1;
                        eVar.f1095f = 0;
                        eVar.f1096g = 0;
                    } else {
                        eVar.a(i10 - iE2);
                    }
                }
            } else if (i8 == 16 || i8 == 0) {
                d[] dVarArr5 = g.f1106a;
                q3.h hVarD2 = eVar.d();
                g.a(hVarD2);
                arrayList.add(new d(hVarD2, eVar.d()));
            } else {
                arrayList.add(new d(eVar.b(eVar.e(i8, 15) - 1), eVar.d()));
            }
        }
        List listG0 = v1.j.G0(arrayList);
        arrayList.clear();
        return listG0;
    }
}
