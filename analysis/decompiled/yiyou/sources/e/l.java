package e;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: compiled from: Okio.java */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Logger f4747a = Logger.getLogger(l.class.getName());

    /* JADX INFO: compiled from: Okio.java */
    final class a implements r {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ t f4748a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ OutputStream f4749b;

        a(t tVar, OutputStream outputStream) {
            this.f4748a = tVar;
            this.f4749b = outputStream;
        }

        @Override // e.r
        public void a(e.c cVar, long j) throws IOException {
            u.a(cVar.f4728b, 0L, j);
            while (j > 0) {
                this.f4748a.e();
                o oVar = cVar.f4727a;
                int iMin = (int) Math.min(j, oVar.f4761c - oVar.f4760b);
                this.f4749b.write(oVar.f4759a, oVar.f4760b, iMin);
                oVar.f4760b += iMin;
                long j2 = iMin;
                j -= j2;
                cVar.f4728b -= j2;
                if (oVar.f4760b == oVar.f4761c) {
                    cVar.f4727a = oVar.b();
                    p.a(oVar);
                }
            }
        }

        @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f4749b.close();
        }

        @Override // e.r, java.io.Flushable
        public void flush() throws IOException {
            this.f4749b.flush();
        }

        @Override // e.r
        public t timeout() {
            return this.f4748a;
        }

        public String toString() {
            return "sink(" + this.f4749b + ")";
        }
    }

    /* JADX INFO: compiled from: Okio.java */
    final class b implements s {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ t f4750a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ InputStream f4751b;

        b(t tVar, InputStream inputStream) {
            this.f4750a = tVar;
            this.f4751b = inputStream;
        }

        @Override // e.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f4751b.close();
        }

        @Override // e.s
        public long read(e.c cVar, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            }
            if (j == 0) {
                return 0L;
            }
            try {
                this.f4750a.e();
                o oVarB = cVar.b(1);
                int i = this.f4751b.read(oVarB.f4759a, oVarB.f4761c, (int) Math.min(j, 8192 - oVarB.f4761c));
                if (i == -1) {
                    return -1L;
                }
                oVarB.f4761c += i;
                long j2 = i;
                cVar.f4728b += j2;
                return j2;
            } catch (AssertionError e2) {
                if (l.a(e2)) {
                    throw new IOException(e2);
                }
                throw e2;
            }
        }

        @Override // e.s
        public t timeout() {
            return this.f4750a;
        }

        public String toString() {
            return "source(" + this.f4751b + ")";
        }
    }

    /* JADX INFO: compiled from: Okio.java */
    final class c implements r {
        c() {
        }

        @Override // e.r
        public void a(e.c cVar, long j) throws EOFException {
            cVar.skip(j);
        }

        @Override // e.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
        }

        @Override // e.r, java.io.Flushable
        public void flush() {
        }

        @Override // e.r
        public t timeout() {
            return t.f4768d;
        }
    }

    /* JADX INFO: compiled from: Okio.java */
    final class d extends e.a {
        final /* synthetic */ Socket k;

        d(Socket socket) {
            this.k = socket;
        }

        @Override // e.a
        protected IOException b(IOException iOException) {
            SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
            if (iOException != null) {
                socketTimeoutException.initCause(iOException);
            }
            return socketTimeoutException;
        }

        @Override // e.a
        protected void i() {
            try {
                this.k.close();
            } catch (AssertionError e2) {
                if (!l.a(e2)) {
                    throw e2;
                }
                l.f4747a.log(Level.WARNING, "Failed to close timed out socket " + this.k, (Throwable) e2);
            } catch (Exception e3) {
                l.f4747a.log(Level.WARNING, "Failed to close timed out socket " + this.k, (Throwable) e3);
            }
        }
    }

    private l() {
    }

    public static e a(s sVar) {
        return new n(sVar);
    }

    public static r b(File file) {
        if (file != null) {
            return a(new FileOutputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static s c(File file) {
        if (file != null) {
            return a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static e.d a(r rVar) {
        return new m(rVar);
    }

    public static r a(OutputStream outputStream) {
        return a(outputStream, new t());
    }

    public static s b(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getInputStream() != null) {
                e.a aVarC = c(socket);
                return aVarC.a(a(socket.getInputStream(), aVarC));
            }
            throw new IOException("socket's input stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    private static e.a c(Socket socket) {
        return new d(socket);
    }

    private static r a(OutputStream outputStream, t tVar) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        if (tVar != null) {
            return new a(tVar, outputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static r a(Socket socket) throws IOException {
        if (socket != null) {
            if (socket.getOutputStream() != null) {
                e.a aVarC = c(socket);
                return aVarC.a(a(socket.getOutputStream(), aVarC));
            }
            throw new IOException("socket's output stream == null");
        }
        throw new IllegalArgumentException("socket == null");
    }

    public static s a(InputStream inputStream) {
        return a(inputStream, new t());
    }

    private static s a(InputStream inputStream, t tVar) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (tVar != null) {
            return new b(tVar, inputStream);
        }
        throw new IllegalArgumentException("timeout == null");
    }

    public static r a(File file) {
        if (file != null) {
            return a(new FileOutputStream(file, true));
        }
        throw new IllegalArgumentException("file == null");
    }

    public static r a() {
        return new c();
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
