package ddth2.hidden;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public final class L {
    public final byte[] a;
    public final String b;
    public final int c;
    public final String d;
    public final int e;
    public final String f;
    public final int g;
    public final long h;
    public final int i;
    public final int j;
    public final int k;
    public final long l;

    public L(byte[] bArr, String str, int i, String str2, int i2, String str3, int i3, long j, int i4, int i5, int i6, long j2) {
        this.a = bArr;
        this.b = str;
        this.c = i;
        this.d = str2;
        this.e = i2;
        this.f = str3;
        this.g = i3;
        this.h = j;
        this.i = i4;
        this.j = i5;
        this.k = i6;
        this.l = j2;
    }

    public static L a(byte[] bArr) {
        if (bArr.length < 53) {
            throw new IllegalArgumentException("PROXY_CONNECT too short");
        }
        int i = bArr[0] & 255;
        if (i != 1) {
            throw new IllegalArgumentException("unsupported pc_version " + i);
        }
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 16);
        int iA = r.a(17, bArr);
        int iA2 = r.a(19, bArr);
        int iA3 = r.a(21, bArr);
        long j = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j = ((long) (bArr[23 + i2] & 255)) | (j << 8);
        }
        int iA4 = r.a(31, bArr);
        int iA5 = r.a(33, bArr);
        int iB = (int) r.b(35, bArr);
        long jB = r.b(39, bArr);
        r.b(43, bArr);
        int iA6 = r.a(47, bArr);
        int iA7 = r.a(49, bArr);
        int iA8 = r.a(51, bArr);
        if (iA6 > 255 || iA7 > 255 || iA8 > 255) {
            throw new IllegalArgumentException("host too large");
        }
        int i3 = iA6 + 53;
        int i4 = i3 + iA7;
        if (i4 + iA8 != bArr.length) {
            throw new IllegalArgumentException("PROXY_CONNECT length mismatch");
        }
        Charset charset = StandardCharsets.UTF_8;
        return new L(bArr2, new String(bArr, 53, iA6, charset), iA, new String(bArr, i3, iA7, charset), iA2, iA8 == 0 ? "" : new String(bArr, i4, iA8, charset), iA3, j, iA4, iA5, iB, jB);
    }
}
