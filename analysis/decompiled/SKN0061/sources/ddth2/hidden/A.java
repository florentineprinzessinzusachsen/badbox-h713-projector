package ddth2.hidden;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public final class A {
    public final ByteArrayOutputStream a = new ByteArrayOutputStream();

    public final A a(int i, String str) {
        byte[] bytes = str == null ? new byte[0] : str.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > 65535) {
            throw new IllegalArgumentException("tlv too large");
        }
        this.a.write(i & 255);
        this.a.write(1);
        this.a.write((bytes.length >>> 8) & 255);
        this.a.write(bytes.length & 255);
        this.a.write(bytes, 0, bytes.length);
        return this;
    }
}
