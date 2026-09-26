package d.h0.i;

import java.io.IOException;

/* JADX INFO: compiled from: Http2.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final e.f f4480a = e.f.d("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final String[] f4481b = {"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final String[] f4482c = new String[64];

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static final String[] f4483d = new String[256];

    static {
        int i = 0;
        int i2 = 0;
        while (true) {
            String[] strArr = f4483d;
            if (i2 >= strArr.length) {
                break;
            }
            strArr[i2] = d.h0.c.a("%8s", Integer.toBinaryString(i2)).replace(' ', '0');
            i2++;
        }
        String[] strArr2 = f4482c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        for (int i3 : iArr) {
            f4482c[i3 | 8] = f4482c[i3] + "|PADDED";
        }
        String[] strArr3 = f4482c;
        strArr3[4] = "END_HEADERS";
        strArr3[32] = "PRIORITY";
        strArr3[36] = "END_HEADERS|PRIORITY";
        for (int i4 : new int[]{4, 32, 36}) {
            for (int i5 : iArr) {
                int i6 = i5 | i4;
                f4482c[i6] = f4482c[i5] + '|' + f4482c[i4];
                f4482c[i6 | 8] = f4482c[i5] + '|' + f4482c[i4] + "|PADDED";
            }
        }
        while (true) {
            String[] strArr4 = f4482c;
            if (i >= strArr4.length) {
                return;
            }
            if (strArr4[i] == null) {
                strArr4[i] = f4483d[i];
            }
            i++;
        }
    }

    private e() {
    }

    static IllegalArgumentException a(String str, Object... objArr) {
        throw new IllegalArgumentException(d.h0.c.a(str, objArr));
    }

    static IOException b(String str, Object... objArr) throws IOException {
        throw new IOException(d.h0.c.a(str, objArr));
    }

    static String a(boolean z, int i, int i2, byte b2, byte b3) {
        String[] strArr = f4481b;
        String strA = b2 < strArr.length ? strArr[b2] : d.h0.c.a("0x%02x", Byte.valueOf(b2));
        String strA2 = a(b2, b3);
        Object[] objArr = new Object[5];
        objArr[0] = z ? "<<" : ">>";
        objArr[1] = Integer.valueOf(i);
        objArr[2] = Integer.valueOf(i2);
        objArr[3] = strA;
        objArr[4] = strA2;
        return d.h0.c.a("%s 0x%08x %5d %-13s %s", objArr);
    }

    static String a(byte b2, byte b3) {
        if (b3 == 0) {
            return "";
        }
        if (b2 != 2 && b2 != 3) {
            if (b2 == 4 || b2 == 6) {
                return b3 == 1 ? "ACK" : f4483d[b3];
            }
            if (b2 != 7 && b2 != 8) {
                String[] strArr = f4482c;
                String str = b3 < strArr.length ? strArr[b3] : f4483d[b3];
                if (b2 != 5 || (b3 & 4) == 0) {
                    return (b2 != 0 || (b3 & 32) == 0) ? str : str.replace("PRIORITY", "COMPRESSED");
                }
                return str.replace("HEADERS", "PUSH_PROMISE");
            }
        }
        return f4483d[b3];
    }
}
