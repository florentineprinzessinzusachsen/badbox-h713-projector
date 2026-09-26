package q3;

import java.io.InputStream;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public interface g extends u, ReadableByteChannel {
    void G(long j4);

    String O(Charset charset);

    InputStream Q();

    int m(m mVar);

    h q(long j4);

    byte readByte();

    int readInt();

    short readShort();

    void skip(long j4);

    String t(long j4);
}
