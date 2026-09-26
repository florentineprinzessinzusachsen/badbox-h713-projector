package h3;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class s {
    public static int a(int i4, int i5, int i6) throws IOException {
        if ((i5 & 8) != 0) {
            i4--;
        }
        if (i6 <= i4) {
            return i4 - i6;
        }
        throw new IOException(a1.c.b(i6, i4, "PROTOCOL_ERROR padding ", " > remaining length "));
    }
}
