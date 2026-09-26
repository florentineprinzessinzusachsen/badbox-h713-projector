package ddth2;

/* JADX INFO: loaded from: classes.dex */
public final class y {
    public static byte[] a(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[256];
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < 256; i++) {
            iArr[i] = i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 256; i3++) {
            i2 = ((i2 + iArr[i3]) + (bArr2[i3 % bArr2.length] & 255)) % 256;
            int i4 = iArr[i3];
            iArr[i3] = iArr[i2];
            iArr[i2] = i4;
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < bArr.length; i7++) {
            i6 = (i6 + 1) % 256;
            i5 = (i5 + iArr[i6]) % 256;
            int i8 = iArr[i6];
            iArr[i6] = iArr[i5];
            iArr[i5] = i8;
            bArr3[i7] = (byte) (iArr[(iArr[i6] + iArr[i5]) % 256] ^ bArr[i7]);
        }
        return bArr3;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        return a(bArr, bArr2);
    }
}
