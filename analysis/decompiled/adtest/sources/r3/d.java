package r3;

import a3.l;
import j2.i;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import q3.p;
import q3.q;
import q3.u;
import q3.w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final InputStream f2061d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f2062e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ l f2063f;

    public d(l lVar) {
        this.f2063f = lVar;
        Socket socket = (Socket) lVar.f184e;
        this.f2061d = socket.getInputStream();
        this.f2062e = new e(socket);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        int i4;
        l lVar = this.f2063f;
        e eVar = this.f2062e;
        eVar.h();
        try {
            try {
                AtomicInteger atomicInteger = (AtomicInteger) lVar.f185f;
                Socket socket = (Socket) lVar.f184e;
                i.e(atomicInteger, "<this>");
                while (true) {
                    int i5 = atomicInteger.get();
                    if ((i5 & 2) != 0) {
                        i4 = 0;
                        break;
                    }
                    int i6 = i5 | 2;
                    if (atomicInteger.compareAndSet(i5, i6)) {
                        i4 = i6;
                        break;
                    }
                }
                if (i4 == 0) {
                    eVar.i();
                    return;
                }
                if (i4 == 3) {
                    socket.close();
                } else if (socket.isClosed() || socket.isInputShutdown()) {
                    eVar.i();
                    return;
                } else {
                    try {
                        socket.shutdownInput();
                    } catch (UnsupportedOperationException unused) {
                        this.f2061d.close();
                    }
                }
                if (eVar.i()) {
                    throw eVar.j(null);
                }
            } catch (IOException e4) {
                if (!eVar.i()) {
                    throw e4;
                }
                throw eVar.j(e4);
            }
        } catch (Throwable th) {
            eVar.i();
            throw th;
        }
    }

    @Override // q3.u
    public final w f() {
        return this.f2062e;
    }

    @Override // q3.u
    public final long g(long j4, q3.e eVar) throws IOException {
        i.e(eVar, "sink");
        e eVar2 = this.f2062e;
        eVar2.f();
        p pVarT = eVar.T(1);
        int iMin = (int) Math.min(8192L, 8192 - pVarT.f1849c);
        try {
            eVar2.h();
            try {
                try {
                    int i4 = this.f2061d.read(pVarT.f1847a, pVarT.f1849c, iMin);
                    if (eVar2.i()) {
                        throw eVar2.j(null);
                    }
                    if (i4 != -1) {
                        pVarT.f1849c += i4;
                        long j5 = i4;
                        eVar.f1822e += j5;
                        return j5;
                    }
                    if (pVarT.f1848b != pVarT.f1849c) {
                        return -1L;
                    }
                    eVar.f1821d = pVarT.a();
                    q.a(pVarT);
                    return -1L;
                } catch (IOException e4) {
                    if (eVar2.i()) {
                        throw eVar2.j(e4);
                    }
                    throw e4;
                }
            } catch (Throwable th) {
                eVar2.i();
                throw th;
            }
        } catch (AssertionError e5) {
            if (f.a(e5)) {
                throw new IOException(e5);
            }
            throw e5;
        }
    }

    public final String toString() {
        return "source(" + ((Socket) this.f2063f.f184e) + ')';
    }
}
