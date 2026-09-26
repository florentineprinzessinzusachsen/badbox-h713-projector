package h3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q3.h f1108a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String[] f1109b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String[] f1110c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final String[] f1111d;

    static {
        q3.h hVar = q3.h.f1823g;
        f1108a = a1.a.m("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
        f1109b = new String[]{"DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION"};
        f1110c = new String[64];
        String[] strArr = new String[256];
        for (int i4 = 0; i4 < 256; i4++) {
            String binaryString = Integer.toBinaryString(i4);
            j2.i.d(binaryString, "toBinaryString(...)");
            String strReplace = b3.g.d("%8s", binaryString).replace(' ', '0');
            j2.i.d(strReplace, "replace(...)");
            strArr[i4] = strReplace;
        }
        f1111d = strArr;
        String[] strArr2 = f1110c;
        strArr2[0] = "";
        strArr2[1] = "END_STREAM";
        int[] iArr = {1};
        strArr2[8] = "PADDED";
        int i5 = iArr[0];
        strArr2[i5 | 8] = strArr2[i5] + "|PADDED";
        strArr2[4] = "END_HEADERS";
        strArr2[32] = "PRIORITY";
        strArr2[36] = "END_HEADERS|PRIORITY";
        int[] iArr2 = {4, 32, 36};
        for (int i6 = 0; i6 < 3; i6++) {
            int i7 = iArr2[i6];
            int i8 = iArr[0];
            String[] strArr3 = f1110c;
            int i9 = i8 | i7;
            strArr3[i9] = strArr3[i8] + '|' + strArr3[i7];
            strArr3[i9 | 8] = strArr3[i8] + '|' + strArr3[i7] + "|PADDED";
        }
        int length = f1110c.length;
        for (int i10 = 0; i10 < length; i10++) {
            String[] strArr4 = f1110c;
            if (strArr4[i10] == null) {
                strArr4[i10] = f1111d[i10];
            }
        }
    }

    public static String a(int i4) {
        String[] strArr = f1109b;
        return i4 < strArr.length ? strArr[i4] : b3.g.d("0x%02x", Integer.valueOf(i4));
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0055  */
    public static String b(boolean z3, int i4, int i5, int i6, int i7) {
        String strX0;
        String str;
        String strA = a(i6);
        if (i7 == 0) {
            strX0 = "";
        } else {
            String[] strArr = f1111d;
            if (i6 == 2 || i6 == 3) {
                strX0 = strArr[i7];
            } else if (i6 == 4 || i6 == 6) {
                strX0 = i7 == 1 ? "ACK" : strArr[i7];
            } else if (i6 == 7 || i6 == 8) {
                strX0 = strArr[i7];
            } else {
                String[] strArr2 = f1110c;
                if (i7 < strArr2.length) {
                    str = strArr2[i7];
                    j2.i.b(str);
                } else {
                    str = strArr[i7];
                }
                if (i6 != 5 || (i7 & 4) == 0) {
                    strX0 = (i6 != 0 || (i7 & 32) == 0) ? str : p2.p.x0(str, "PRIORITY", "COMPRESSED");
                } else {
                    strX0 = p2.p.x0(str, "HEADERS", "PUSH_PROMISE");
                }
            }
        }
        return b3.g.d("%s 0x%08x %5d %-13s %s", z3 ? "<<" : ">>", Integer.valueOf(i4), Integer.valueOf(i5), strA, strX0);
    }

    public static String c(boolean z3, int i4, int i5, long j4) {
        return b3.g.d("%s 0x%08x %5d %-13s %d", z3 ? "<<" : ">>", Integer.valueOf(i4), Integer.valueOf(i5), a(8), Long.valueOf(j4));
    }
}
