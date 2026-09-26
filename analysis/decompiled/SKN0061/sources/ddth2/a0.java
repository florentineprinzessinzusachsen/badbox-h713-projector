package ddth2;

/* JADX INFO: loaded from: classes.dex */
public final class a0 {
    public static byte[] a(byte[] bArr, char c) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr2[i] = (byte) (bArr[i] ^ c);
        }
        return bArr2;
    }
}
