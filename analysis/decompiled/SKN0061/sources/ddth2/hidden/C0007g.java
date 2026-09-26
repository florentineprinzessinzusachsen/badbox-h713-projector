package ddth2.hidden;

import java.nio.ByteBuffer;
import java.util.Locale;

/* JADX INFO: renamed from: ddth2.hidden.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0007g {
    private ByteBuffer a;
    private byte[] b;
    private int c = 0;
    private int d;

    public C0007g(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
        this.b = byteBuffer.array();
        this.d = byteBuffer.position();
    }

    public final int a(int i, int i2) {
        int i3 = this.c + i;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            i4 = (i4 << 8) + (this.b[i3 + i5] & 255);
        }
        return i4;
    }

    public final void b() {
        this.a = null;
        this.b = null;
        this.c = 0;
        this.d = 0;
    }

    public final C0007g c() {
        int i = this.c;
        if (i > 0) {
            int i2 = this.d;
            if (i == i2) {
                this.c = 0;
                this.d = 0;
                return this;
            }
            int i3 = i2 - i;
            if (i3 > 0) {
                byte[] bArr = new byte[i3];
                System.arraycopy(this.b, i, bArr, 0, i3);
                this.a.put(bArr);
                this.d = i3;
            } else {
                this.d = 0;
            }
            this.c = 0;
        }
        return this;
    }

    public final String d() {
        int i = this.c;
        Locale locale = Locale.ENGLISH;
        byte[] bArr = this.b;
        return (bArr[i + 7] & 255) + "." + (bArr[i + 8] & 255) + "." + (bArr[i + 9] & 255) + "." + (bArr[i + 10] & 255);
    }

    public final String e() {
        int i = this.c;
        return String.format(Locale.ENGLISH, "%x%x:%x%x:%x%x:%x%x:%x%x:%x%x:%x%x:%x%x", Integer.valueOf(this.b[i + 7] & 255), Integer.valueOf(this.b[i + 8] & 255), Integer.valueOf(this.b[i + 9] & 255), Integer.valueOf(this.b[i + 10] & 255), Integer.valueOf(this.b[i + 11] & 255), Integer.valueOf(this.b[i + 12] & 255), Integer.valueOf(this.b[i + 13] & 255), Integer.valueOf(this.b[i + 14] & 255), Integer.valueOf(this.b[i + 15] & 255), Integer.valueOf(this.b[i + 16] & 255), Integer.valueOf(this.b[i + 17] & 255), Integer.valueOf(this.b[i + 18] & 255), Integer.valueOf(this.b[i + 19] & 255), Integer.valueOf(this.b[i + 20] & 255), Integer.valueOf(this.b[i + 21] & 255), Integer.valueOf(this.b[i + 22] & 255));
    }

    public final int f() {
        return this.c;
    }

    public final int g() {
        return this.d - this.c;
    }

    public final String a(int i) {
        return new String(this.b, this.c + 8, i);
    }

    public final void b(int i) {
        this.c += i;
    }

    public final byte[] a() {
        return this.b;
    }

    public final void a(byte[] bArr) {
        System.arraycopy(this.b, this.c, bArr, 0, bArr.length);
    }
}
