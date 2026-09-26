package r3;

import j2.i;
import q3.r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f2057a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static final int a(char c4) {
        if ('0' <= c4 && c4 < ':') {
            return c4 - '0';
        }
        if ('a' <= c4 && c4 < 'g') {
            return c4 - 'W';
        }
        if ('A' <= c4 && c4 < 'G') {
            return c4 - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c4);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0026 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0027  */
    public static final int b(r rVar, int i4) {
        int i5;
        int[] iArr = rVar.f1858i;
        int i6 = i4 + 1;
        int length = rVar.f1857h.length;
        i.e(iArr, "<this>");
        int i7 = length - 1;
        int i8 = 0;
        while (i8 <= i7) {
            i5 = (i8 + i7) >>> 1;
            int i9 = iArr[i5];
            if (i9 < i6) {
                i8 = i5 + 1;
            } else {
                if (i9 <= i6) {
                    if (i5 >= 0) {
                        return i5;
                    }
                    return ~i5;
                }
                i7 = i5 - 1;
            }
        }
        i5 = (-i8) - 1;
        if (i5 >= 0) {
            return i5;
        }
        return ~i5;
    }
}
