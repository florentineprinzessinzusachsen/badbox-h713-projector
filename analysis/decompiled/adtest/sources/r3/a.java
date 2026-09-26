package r3;

import j2.i;
import java.io.EOFException;
import q3.m;
import q3.p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f2055a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final long[] f2056b;

    static {
        byte[] bytes = "0123456789abcdef".getBytes(p2.a.f1738a);
        i.d(bytes, "getBytes(...)");
        f2055a = bytes;
        f2056b = new long[]{-1, 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, 9999999999L, 99999999999L, 999999999999L, 9999999999999L, 99999999999999L, 999999999999999L, 9999999999999999L, 99999999999999999L, 999999999999999999L, Long.MAX_VALUE};
    }

    public static final String a(long j4, q3.e eVar) throws EOFException {
        if (j4 > 0) {
            long j5 = j4 - 1;
            if (eVar.k(j5) == 13) {
                String strJ = eVar.J(j5, p2.a.f1738a);
                eVar.skip(2L);
                return strJ;
            }
        }
        String strJ2 = eVar.J(j4, p2.a.f1738a);
        eVar.skip(1L);
        return strJ2;
    }

    /* JADX WARN: Code duplicated, block: B:49:0x00a3 A[LOOP:0: B:8:0x001e->B:49:0x00a3, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:55:0x00a2 A[SYNTHETIC] */
    public static final int b(q3.e eVar, m mVar, boolean z3) {
        int i4;
        int i5;
        int i6;
        p pVar;
        int i7;
        i.e(mVar, "options");
        p pVar2 = eVar.f1821d;
        if (pVar2 == null) {
            return z3 ? -2 : -1;
        }
        byte[] bArr = pVar2.f1847a;
        int i8 = pVar2.f1848b;
        int i9 = pVar2.f1849c;
        int[] iArr = mVar.f1840e;
        p pVar3 = pVar2;
        int i10 = -1;
        int i11 = 0;
        loop0: while (true) {
            int i12 = i11 + 1;
            int i13 = iArr[i11];
            int i14 = i11 + 2;
            int i15 = iArr[i12];
            if (i15 != -1) {
                i10 = i15;
            }
            if (pVar3 == null) {
                break;
            }
            if (i13 >= 0) {
                int i16 = i8 + 1;
                int i17 = bArr[i8] & 255;
                int i18 = i14 + i13;
                while (i14 != i18) {
                    if (i17 == iArr[i14]) {
                        i4 = iArr[i14 + i13];
                        if (i16 == i9) {
                            pVar3 = pVar3.f1852f;
                            i.b(pVar3);
                            int i19 = pVar3.f1848b;
                            byte[] bArr2 = pVar3.f1847a;
                            i5 = pVar3.f1849c;
                            if (pVar3 == pVar2) {
                                i6 = i19;
                                bArr = bArr2;
                                pVar3 = null;
                            } else {
                                i6 = i19;
                                bArr = bArr2;
                            }
                        } else {
                            i5 = i9;
                            i6 = i16;
                        }
                        if (i4 >= 0) {
                            return i4;
                        }
                        int i20 = i5;
                        i11 = -i4;
                        i8 = i6;
                        i9 = i20;
                    } else {
                        i14++;
                    }
                }
                return i10;
            }
            int i21 = (i13 * (-1)) + i14;
            while (true) {
                int i22 = i8 + 1;
                int i23 = i14 + 1;
                if ((bArr[i8] & 255) == iArr[i14]) {
                    boolean z4 = i23 == i21;
                    if (i22 == i9) {
                        i.b(pVar3);
                        p pVar4 = pVar3.f1852f;
                        i.b(pVar4);
                        i6 = pVar4.f1848b;
                        byte[] bArr3 = pVar4.f1847a;
                        i7 = pVar4.f1849c;
                        if (pVar4 != pVar2) {
                            pVar = pVar4;
                            bArr = bArr3;
                        } else {
                            if (!z4) {
                                break loop0;
                            }
                            bArr = bArr3;
                            pVar = null;
                        }
                    } else {
                        pVar = pVar3;
                        i7 = i9;
                        i6 = i22;
                    }
                    if (z4) {
                        i4 = iArr[i23];
                        int i24 = i7;
                        pVar3 = pVar;
                        i5 = i24;
                        break;
                    }
                    i8 = i6;
                    i9 = i7;
                    pVar3 = pVar;
                    i14 = i23;
                }
                return i10;
            }
            if (i4 >= 0) {
                return i4;
            }
            int i25 = i5;
            i11 = -i4;
            i8 = i6;
            i9 = i25;
        }
        if (z3) {
            return -2;
        }
        return i10;
    }
}
