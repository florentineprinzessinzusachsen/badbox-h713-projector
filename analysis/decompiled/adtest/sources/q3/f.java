package q3;

import java.nio.channels.WritableByteChannel;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public interface f extends s, WritableByteChannel {
    f D(int i4, byte[] bArr);

    f H(String str);

    @Override // q3.s, java.io.Flushable
    void flush();

    f j(h hVar);

    f write(byte[] bArr);

    f writeByte(int i4);

    f writeInt(int i4);

    f writeShort(int i4);
}
