package e;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: BufferedSource.java */
/* JADX INFO: loaded from: classes.dex */
public interface e extends s, ReadableByteChannel {
    long a(byte b2);

    long a(r rVar);

    String a(Charset charset);

    boolean a(long j, f fVar);

    c c();

    boolean c(long j);

    short d();

    f e(long j);

    long f();

    String f(long j);

    String g();

    void g(long j);

    byte[] h();

    int i();

    byte[] i(long j);

    boolean j();

    long k();

    InputStream l();

    byte readByte();

    void readFully(byte[] bArr);

    int readInt();

    short readShort();

    void skip(long j);
}
