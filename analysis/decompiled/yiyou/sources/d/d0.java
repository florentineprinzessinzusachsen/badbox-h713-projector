package d;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: ResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d0 implements Closeable {
    private Reader reader;

    /* JADX INFO: compiled from: ResponseBody.java */
    class a extends d0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ v f4305a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ long f4306b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ e.e f4307c;

        a(v vVar, long j, e.e eVar) {
            this.f4305a = vVar;
            this.f4306b = j;
            this.f4307c = eVar;
        }

        @Override // d.d0
        public long contentLength() {
            return this.f4306b;
        }

        @Override // d.d0
        public v contentType() {
            return this.f4305a;
        }

        @Override // d.d0
        public e.e source() {
            return this.f4307c;
        }
    }

    /* JADX INFO: compiled from: ResponseBody.java */
    static final class b extends Reader {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final e.e f4308a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Charset f4309b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f4310c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private Reader f4311d;

        b(e.e eVar, Charset charset) {
            this.f4308a = eVar;
            this.f4309b = charset;
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f4310c = true;
            Reader reader = this.f4311d;
            if (reader != null) {
                reader.close();
            } else {
                this.f4308a.close();
            }
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.f4310c) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.f4311d;
            if (reader == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.f4308a.l(), d.h0.c.a(this.f4308a, this.f4309b));
                this.f4311d = inputStreamReader;
                reader = inputStreamReader;
            }
            return reader.read(cArr, i, i2);
        }
    }

    private Charset charset() {
        v vVarContentType = contentType();
        return vVarContentType != null ? vVarContentType.a(d.h0.c.i) : d.h0.c.i;
    }

    public static d0 create(v vVar, String str) {
        Charset charsetA = d.h0.c.i;
        if (vVar != null && (charsetA = vVar.a()) == null) {
            charsetA = d.h0.c.i;
            vVar = v.b(vVar + "; charset=utf-8");
        }
        e.c cVar = new e.c();
        cVar.a(str, charsetA);
        return create(vVar, cVar.q(), cVar);
    }

    public final InputStream byteStream() {
        return source().l();
    }

    public final byte[] bytes() throws IOException {
        long jContentLength = contentLength();
        if (jContentLength > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + jContentLength);
        }
        e.e eVarSource = source();
        try {
            byte[] bArrH = eVarSource.h();
            d.h0.c.a(eVarSource);
            if (jContentLength == -1 || jContentLength == bArrH.length) {
                return bArrH;
            }
            throw new IOException("Content-Length (" + jContentLength + ") and stream length (" + bArrH.length + ") disagree");
        } catch (Throwable th) {
            d.h0.c.a(eVarSource);
            throw th;
        }
    }

    public final Reader charStream() {
        Reader reader = this.reader;
        if (reader != null) {
            return reader;
        }
        b bVar = new b(source(), charset());
        this.reader = bVar;
        return bVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        d.h0.c.a(source());
    }

    public abstract long contentLength();

    public abstract v contentType();

    public abstract e.e source();

    public final String string() {
        e.e eVarSource = source();
        try {
            return eVarSource.a(d.h0.c.a(eVarSource, charset()));
        } finally {
            d.h0.c.a(eVarSource);
        }
    }

    public static d0 create(v vVar, byte[] bArr) {
        e.c cVar = new e.c();
        cVar.write(bArr);
        return create(vVar, bArr.length, cVar);
    }

    public static d0 create(v vVar, e.f fVar) {
        e.c cVar = new e.c();
        cVar.a(fVar);
        return create(vVar, fVar.f(), cVar);
    }

    public static d0 create(v vVar, long j, e.e eVar) {
        if (eVar != null) {
            return new a(vVar, j, eVar);
        }
        throw new NullPointerException("source == null");
    }
}
