package e;

import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: BufferedSink.java */
/* JADX INFO: loaded from: classes.dex */
public interface d extends r, WritableByteChannel {
    long a(s sVar);

    d a(f fVar);

    d b(String str);

    c c();

    d d(long j);

    d e();

    @Override // e.r, java.io.Flushable
    void flush();

    d h(long j);

    d write(byte[] bArr);

    d write(byte[] bArr, int i, int i2);

    d writeByte(int i);

    d writeInt(int i);

    d writeShort(int i);
}
