package b.a.a.a.a;

import com.umeng.commonsdk.proguard.ap;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final byte[] f1517a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    public static byte[] a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static byte[] a(byte[] bArr, int i) {
        byte b2;
        int i2;
        int i3;
        int i4 = (i / 4) * 3;
        if (i4 == 0) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i4];
        int i5 = i;
        int i6 = 0;
        while (true) {
            byte b3 = bArr[i5 - 1];
            b2 = 10;
            if (b3 != 10 && b3 != 13 && b3 != 32 && b3 != 9) {
                if (b3 != 61) {
                    break;
                }
                i6++;
            }
            i5--;
        }
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i7 < i5) {
            byte b4 = bArr[i7];
            if (b4 != b2 && b4 != 13 && b4 != 32 && b4 != 9) {
                if (b4 >= 65 && b4 <= 90) {
                    i2 = b4 - 65;
                } else if (b4 >= 97 && b4 <= 122) {
                    i2 = b4 - 71;
                } else if (b4 >= 48 && b4 <= 57) {
                    i2 = b4 + 4;
                } else if (b4 == 43) {
                    i2 = 62;
                } else {
                    if (b4 != 47) {
                        return null;
                    }
                    i2 = 63;
                }
                int i11 = (i8 << 6) | ((byte) i2);
                if (i10 % 4 == 3) {
                    int i12 = i9 + 1;
                    bArr2[i9] = (byte) ((16711680 & i11) >> 16);
                    int i13 = i12 + 1;
                    bArr2[i12] = (byte) ((65280 & i11) >> 8);
                    i3 = i13 + 1;
                    bArr2[i13] = (byte) (i11 & 255);
                } else {
                    i3 = i9;
                }
                i10++;
                i9 = i3;
                i8 = i11;
            }
            i7++;
            b2 = 10;
        }
        if (i6 > 0) {
            int i14 = i8 << (i6 * 6);
            int i15 = i9 + 1;
            bArr2[i9] = (byte) ((i14 & 16711680) >> 16);
            if (i6 == 1) {
                i9 = i15 + 1;
                bArr2[i15] = (byte) ((i14 & 65280) >> 8);
            } else {
                i9 = i15;
            }
        }
        byte[] bArr3 = new byte[i9];
        System.arraycopy(bArr2, 0, bArr3, 0, i9);
        return bArr3;
    }

    public static String a(byte[] bArr, String str) {
        int length = (bArr.length * 4) / 3;
        byte[] bArr2 = new byte[length + (length / 76) + 3];
        int length2 = bArr.length - (bArr.length % 3);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3 += 3) {
            int i4 = i + 1;
            byte[] bArr3 = f1517a;
            bArr2[i] = bArr3[(bArr[i3] & 255) >> 2];
            int i5 = i4 + 1;
            int i6 = i3 + 1;
            bArr2[i4] = bArr3[((bArr[i3] & 3) << 4) | ((bArr[i6] & 255) >> 4)];
            int i7 = i5 + 1;
            int i8 = i3 + 2;
            bArr2[i5] = bArr3[((bArr[i6] & ap.m) << 2) | ((bArr[i8] & 255) >> 6)];
            i = i7 + 1;
            bArr2[i7] = bArr3[bArr[i8] & 63];
            if ((i - i2) % 76 == 0 && i != 0) {
                bArr2[i] = 10;
                i2++;
                i++;
            }
        }
        int length3 = bArr.length % 3;
        if (length3 == 1) {
            int i9 = i + 1;
            byte[] bArr4 = f1517a;
            bArr2[i] = bArr4[(bArr[length2] & 255) >> 2];
            int i10 = i9 + 1;
            bArr2[i9] = bArr4[(bArr[length2] & 3) << 4];
            int i11 = i10 + 1;
            bArr2[i10] = 61;
            i = i11 + 1;
            bArr2[i11] = 61;
        } else if (length3 == 2) {
            int i12 = i + 1;
            byte[] bArr5 = f1517a;
            bArr2[i] = bArr5[(bArr[length2] & 255) >> 2];
            int i13 = i12 + 1;
            int i14 = (bArr[length2] & 3) << 4;
            int i15 = length2 + 1;
            bArr2[i12] = bArr5[((bArr[i15] & 255) >> 4) | i14];
            int i16 = i13 + 1;
            bArr2[i13] = bArr5[(bArr[i15] & ap.m) << 2];
            i = i16 + 1;
            bArr2[i16] = 61;
        }
        return new String(bArr2, 0, i, str);
    }
}
