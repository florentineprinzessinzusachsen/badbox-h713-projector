package d.h0.i;

import e.s;
import e.t;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: Http2Reader.java */
/* JADX INFO: loaded from: classes.dex */
final class h implements Closeable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    static final Logger f4538e = Logger.getLogger(e.class.getName());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final e.e f4539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a f4540b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f4541c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final d.a f4542d;

    /* JADX INFO: compiled from: Http2Reader.java */
    static final class a implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e.e f4543a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f4544b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        byte f4545c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f4546d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f4547e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        short f4548f;

        a(e.e eVar) {
            this.f4543a = eVar;
        }

        private void a() throws IOException {
            int i = this.f4546d;
            int iA = h.a(this.f4543a);
            this.f4547e = iA;
            this.f4544b = iA;
            byte b2 = (byte) (this.f4543a.readByte() & 255);
            this.f4545c = (byte) (this.f4543a.readByte() & 255);
            if (h.f4538e.isLoggable(Level.FINE)) {
                h.f4538e.fine(e.a(true, this.f4546d, this.f4544b, b2, this.f4545c));
            }
            this.f4546d = this.f4543a.readInt() & Integer.MAX_VALUE;
            if (b2 != 9) {
                e.b("%s != TYPE_CONTINUATION", Byte.valueOf(b2));
                throw null;
            }
            if (this.f4546d == i) {
                return;
            }
            e.b("TYPE_CONTINUATION streamId changed", new Object[0]);
            throw null;
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // e.s
        public long read(e.c cVar, long j) throws IOException {
            while (true) {
                int i = this.f4547e;
                if (i != 0) {
                    long j2 = this.f4543a.read(cVar, Math.min(j, i));
                    if (j2 == -1) {
                        return -1L;
                    }
                    this.f4547e = (int) (((long) this.f4547e) - j2);
                    return j2;
                }
                this.f4543a.skip(this.f4548f);
                this.f4548f = (short) 0;
                if ((this.f4545c & 4) != 0) {
                    return -1L;
                }
                a();
            }
        }

        @Override // e.s
        public t timeout() {
            return this.f4543a.timeout();
        }
    }

    /* JADX INFO: compiled from: Http2Reader.java */
    interface b {
        void a();

        void a(int i, int i2, int i3, boolean z);

        void a(int i, int i2, List<c> list);

        void a(int i, long j);

        void a(int i, d.h0.i.b bVar);

        void a(int i, d.h0.i.b bVar, e.f fVar);

        void a(boolean z, int i, int i2);

        void a(boolean z, int i, int i2, List<c> list);

        void a(boolean z, int i, e.e eVar, int i2);

        void a(boolean z, m mVar);
    }

    h(e.e eVar, boolean z) {
        this.f4539a = eVar;
        this.f4541c = z;
        this.f4540b = new a(this.f4539a);
        this.f4542d = new d.a(4096, this.f4540b);
    }

    private void b(b bVar, int i, byte b2, int i2) throws IOException {
        if (i < 8) {
            e.b("TYPE_GOAWAY length < 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            e.b("TYPE_GOAWAY streamId != 0", new Object[0]);
            throw null;
        }
        int i3 = this.f4539a.readInt();
        int i4 = this.f4539a.readInt();
        int i5 = i - 8;
        d.h0.i.b bVarA = d.h0.i.b.a(i4);
        if (bVarA == null) {
            e.b("TYPE_GOAWAY unexpected error code: %d", Integer.valueOf(i4));
            throw null;
        }
        e.f fVarE = e.f.f4732e;
        if (i5 > 0) {
            fVarE = this.f4539a.e(i5);
        }
        bVar.a(i3, bVarA, fVarE);
    }

    private void c(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            e.b("PROTOCOL_ERROR: TYPE_HEADERS streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        short s = (b2 & 8) != 0 ? (short) (this.f4539a.readByte() & 255) : (short) 0;
        if ((b2 & 32) != 0) {
            a(bVar, i2);
            i -= 5;
        }
        bVar.a(z, i2, -1, a(a(i, b2, s), s, b2, i2));
    }

    private void d(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 8) {
            e.b("TYPE_PING length != 8: %s", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            e.b("TYPE_PING streamId != 0", new Object[0]);
            throw null;
        }
        bVar.a((b2 & 1) != 0, this.f4539a.readInt(), this.f4539a.readInt());
    }

    private void e(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 5) {
            e.b("TYPE_PRIORITY length: %d != 5", Integer.valueOf(i));
            throw null;
        }
        if (i2 != 0) {
            a(bVar, i2);
        } else {
            e.b("TYPE_PRIORITY streamId == 0", new Object[0]);
            throw null;
        }
    }

    private void f(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            e.b("PROTOCOL_ERROR: TYPE_PUSH_PROMISE streamId == 0", new Object[0]);
            throw null;
        }
        short s = (b2 & 8) != 0 ? (short) (this.f4539a.readByte() & 255) : (short) 0;
        bVar.a(i2, this.f4539a.readInt() & Integer.MAX_VALUE, a(a(i - 4, b2, s), s, b2, i2));
    }

    private void g(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            e.b("TYPE_RST_STREAM length: %d != 4", Integer.valueOf(i));
            throw null;
        }
        if (i2 == 0) {
            e.b("TYPE_RST_STREAM streamId == 0", new Object[0]);
            throw null;
        }
        int i3 = this.f4539a.readInt();
        d.h0.i.b bVarA = d.h0.i.b.a(i3);
        if (bVarA != null) {
            bVar.a(i2, bVarA);
        } else {
            e.b("TYPE_RST_STREAM unexpected error code: %d", Integer.valueOf(i3));
            throw null;
        }
    }

    private void h(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 != 0) {
            e.b("TYPE_SETTINGS streamId != 0", new Object[0]);
            throw null;
        }
        if ((b2 & 1) != 0) {
            if (i == 0) {
                bVar.a();
                return;
            } else {
                e.b("FRAME_SIZE_ERROR ack frame should be empty!", new Object[0]);
                throw null;
            }
        }
        if (i % 6 != 0) {
            e.b("TYPE_SETTINGS length %% 6 != 0: %s", Integer.valueOf(i));
            throw null;
        }
        m mVar = new m();
        for (int i3 = 0; i3 < i; i3 += 6) {
            int i4 = this.f4539a.readShort() & 65535;
            int i5 = this.f4539a.readInt();
            switch (i4) {
                case 2:
                    if (i5 != 0 && i5 != 1) {
                        e.b("PROTOCOL_ERROR SETTINGS_ENABLE_PUSH != 0 or 1", new Object[0]);
                        throw null;
                    }
                    break;
                    break;
                case 3:
                    i4 = 4;
                    break;
                case 4:
                    i4 = 7;
                    if (i5 < 0) {
                        e.b("PROTOCOL_ERROR SETTINGS_INITIAL_WINDOW_SIZE > 2^31 - 1", new Object[0]);
                        throw null;
                    }
                    break;
                    break;
                case 5:
                    if (i5 < 16384 || i5 > 16777215) {
                        e.b("PROTOCOL_ERROR SETTINGS_MAX_FRAME_SIZE: %s", Integer.valueOf(i5));
                        throw null;
                    }
                    break;
                    break;
            }
            mVar.a(i4, i5);
        }
        bVar.a(false, mVar);
    }

    private void i(b bVar, int i, byte b2, int i2) throws IOException {
        if (i != 4) {
            e.b("TYPE_WINDOW_UPDATE length !=4: %s", Integer.valueOf(i));
            throw null;
        }
        long j = ((long) this.f4539a.readInt()) & 2147483647L;
        if (j != 0) {
            bVar.a(i2, j);
        } else {
            e.b("windowSizeIncrement was 0", Long.valueOf(j));
            throw null;
        }
    }

    public void a(b bVar) throws IOException {
        if (this.f4541c) {
            if (a(true, bVar)) {
                return;
            }
            e.b("Required SETTINGS preface not received", new Object[0]);
            throw null;
        }
        e.f fVarE = this.f4539a.e(e.f4480a.f());
        if (f4538e.isLoggable(Level.FINE)) {
            f4538e.fine(d.h0.c.a("<< CONNECTION %s", fVarE.b()));
        }
        if (e.f4480a.equals(fVarE)) {
            return;
        }
        e.b("Expected a connection header but was %s", fVarE.i());
        throw null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f4539a.close();
    }

    public boolean a(boolean z, b bVar) throws IOException {
        try {
            this.f4539a.g(9L);
            int iA = a(this.f4539a);
            if (iA < 0 || iA > 16384) {
                e.b("FRAME_SIZE_ERROR: %s", Integer.valueOf(iA));
                throw null;
            }
            byte b2 = (byte) (this.f4539a.readByte() & 255);
            if (z && b2 != 4) {
                e.b("Expected a SETTINGS frame but was %s", Byte.valueOf(b2));
                throw null;
            }
            byte b3 = (byte) (this.f4539a.readByte() & 255);
            int i = this.f4539a.readInt() & Integer.MAX_VALUE;
            if (f4538e.isLoggable(Level.FINE)) {
                f4538e.fine(e.a(true, i, iA, b2, b3));
            }
            switch (b2) {
                case 0:
                    a(bVar, iA, b3, i);
                    return true;
                case 1:
                    c(bVar, iA, b3, i);
                    return true;
                case 2:
                    e(bVar, iA, b3, i);
                    return true;
                case 3:
                    g(bVar, iA, b3, i);
                    return true;
                case 4:
                    h(bVar, iA, b3, i);
                    return true;
                case 5:
                    f(bVar, iA, b3, i);
                    return true;
                case 6:
                    d(bVar, iA, b3, i);
                    return true;
                case 7:
                    b(bVar, iA, b3, i);
                    return true;
                case 8:
                    i(bVar, iA, b3, i);
                    return true;
                default:
                    this.f4539a.skip(iA);
                    return true;
            }
        } catch (IOException unused) {
            return false;
        }
    }

    private List<c> a(int i, short s, byte b2, int i2) throws IOException {
        a aVar = this.f4540b;
        aVar.f4547e = i;
        aVar.f4544b = i;
        aVar.f4548f = s;
        aVar.f4545c = b2;
        aVar.f4546d = i2;
        this.f4542d.c();
        return this.f4542d.a();
    }

    private void a(b bVar, int i, byte b2, int i2) throws IOException {
        if (i2 == 0) {
            e.b("PROTOCOL_ERROR: TYPE_DATA streamId == 0", new Object[0]);
            throw null;
        }
        boolean z = (b2 & 1) != 0;
        if (!((b2 & 32) != 0)) {
            short s = (b2 & 8) != 0 ? (short) (this.f4539a.readByte() & 255) : (short) 0;
            bVar.a(z, i2, this.f4539a, a(i, b2, s));
            this.f4539a.skip(s);
            return;
        }
        e.b("PROTOCOL_ERROR: FLAG_COMPRESSED without SETTINGS_COMPRESS_DATA", new Object[0]);
        throw null;
    }

    private void a(b bVar, int i) {
        int i2 = this.f4539a.readInt();
        bVar.a(i, i2 & Integer.MAX_VALUE, (this.f4539a.readByte() & 255) + 1, (Integer.MIN_VALUE & i2) != 0);
    }

    static int a(e.e eVar) {
        return (eVar.readByte() & 255) | ((eVar.readByte() & 255) << 16) | ((eVar.readByte() & 255) << 8);
    }

    static int a(int i, byte b2, short s) throws IOException {
        if ((b2 & 8) != 0) {
            i--;
        }
        if (s <= i) {
            return (short) (i - s);
        }
        e.b("PROTOCOL_ERROR padding %s > remaining length %s", Short.valueOf(s), Integer.valueOf(i));
        throw null;
    }
}
