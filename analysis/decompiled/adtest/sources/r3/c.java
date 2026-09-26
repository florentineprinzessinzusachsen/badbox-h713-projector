package r3;

import a3.l;
import j2.i;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;
import q3.p;
import q3.q;
import q3.s;
import q3.w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c implements s {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final OutputStream f2058d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final e f2059e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ l f2060f;

    public c(l lVar) {
        this.f2060f = lVar;
        Socket socket = (Socket) lVar.f184e;
        this.f2058d = socket.getOutputStream();
        this.f2059e = new e(socket);
    }

    @Override // q3.s
    public final void R(long j4, q3.e eVar) throws IOException {
        a.a.f(eVar.f1822e, 0L, j4);
        while (j4 > 0) {
            e eVar2 = this.f2059e;
            eVar2.f();
            p pVar = eVar.f1821d;
            i.b(pVar);
            int iMin = (int) Math.min(j4, pVar.f1849c - pVar.f1848b);
            eVar2.h();
            try {
                try {
                    this.f2058d.write(pVar.f1847a, pVar.f1848b, iMin);
                    if (eVar2.i()) {
                        throw eVar2.j(null);
                    }
                    int i4 = pVar.f1848b + iMin;
                    pVar.f1848b = i4;
                    long j5 = iMin;
                    j4 -= j5;
                    eVar.f1822e -= j5;
                    if (i4 == pVar.f1849c) {
                        eVar.f1821d = pVar.a();
                        q.a(pVar);
                    }
                } catch (IOException e4) {
                    if (!eVar2.i()) {
                        throw e4;
                    }
                    throw eVar2.j(e4);
                }
            } catch (Throwable th) {
                eVar2.i();
                throw th;
            }
        }
    }

    @Override // q3.s, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        int i4;
        OutputStream outputStream = this.f2058d;
        l lVar = this.f2060f;
        e eVar = this.f2059e;
        eVar.h();
        try {
            try {
                AtomicInteger atomicInteger = (AtomicInteger) lVar.f185f;
                Socket socket = (Socket) lVar.f184e;
                i.e(atomicInteger, "<this>");
                while (true) {
                    int i5 = atomicInteger.get();
                    if ((i5 & 1) != 0) {
                        i4 = 0;
                        break;
                    }
                    int i6 = i5 | 1;
                    if (atomicInteger.compareAndSet(i5, i6)) {
                        i4 = i6;
                        break;
                    }
                }
                if (i4 == 0) {
                    eVar.i();
                    return;
                }
                if (i4 != 3) {
                    if (!socket.isClosed() && !socket.isOutputShutdown()) {
                        outputStream.flush();
                        try {
                            socket.shutdownOutput();
                        } catch (UnsupportedOperationException unused) {
                            outputStream.close();
                        }
                    }
                    eVar.i();
                    return;
                }
                socket.close();
                if (eVar.i()) {
                    throw eVar.j(null);
                }
            } catch (Throwable th) {
                eVar.i();
                throw th;
            }
        } catch (IOException e4) {
            if (!eVar.i()) {
                throw e4;
            }
            throw eVar.j(e4);
        }
    }

    @Override // q3.s
    public final w f() {
        return this.f2059e;
    }

    @Override // q3.s, java.io.Flushable
    public final void flush() throws IOException {
        e eVar = this.f2059e;
        eVar.h();
        try {
            try {
                this.f2058d.flush();
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

    public final String toString() {
        return "sink(" + ((Socket) this.f2060f.f184e) + ')';
    }
}
