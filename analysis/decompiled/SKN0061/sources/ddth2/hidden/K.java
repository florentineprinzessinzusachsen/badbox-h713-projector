package ddth2.hidden;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public abstract class K {
    public static final byte[] a = {71, 87};

    public static J a(InputStream inputStream, byte[] bArr) throws IOException {
        byte b = bArr[0];
        byte[] bArr2 = a;
        if (b != bArr2[0] || bArr[1] != bArr2[1] || bArr[2] != 1) {
            throw new IOException("bad GW header");
        }
        long jB = r.b(4, bArr);
        if (jB > 65536) {
            throw new IOException("GW body too large");
        }
        int i = (int) jB;
        byte[] bArr3 = new byte[i];
        try {
            r.a(inputStream, bArr3, 0, i);
            return new J(bArr[3] & 255, bArr3);
        } catch (SocketTimeoutException e) {
            throw new IOException("GW body timed out", e);
        }
    }

    public static byte[] a(C0018s c0018s, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(byteArrayOutputStream, 1, c0018s.a);
        a(byteArrayOutputStream, 2, "1.0.3");
        a(byteArrayOutputStream, 3, "20260625");
        a(byteArrayOutputStream, 4, c0018s.b);
        a(byteArrayOutputStream, 5, c0018s.c);
        a(byteArrayOutputStream, 6, c0018s.d);
        long j = 1000;
        byte[] bArr = new byte[8];
        for (int i3 = 7; i3 >= 0; i3--) {
            bArr[i3] = (byte) (255 & j);
            j >>>= 8;
        }
        byteArrayOutputStream.write(7);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(8);
        byteArrayOutputStream.write(bArr, 0, 8);
        long j2 = i;
        byte[] bArr2 = new byte[8];
        for (int i4 = 7; i4 >= 0; i4--) {
            bArr2[i4] = (byte) (j2 & 255);
            j2 >>>= 8;
        }
        byteArrayOutputStream.write(8);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(8);
        byteArrayOutputStream.write(bArr2, 0, 8);
        long j3 = i2;
        byte[] bArr3 = new byte[8];
        for (int i5 = 7; i5 >= 0; i5--) {
            bArr3[i5] = (byte) (j3 & 255);
            j3 >>>= 8;
        }
        byteArrayOutputStream.write(9);
        byteArrayOutputStream.write(0);
        byteArrayOutputStream.write(8);
        byteArrayOutputStream.write(bArr3, 0, 8);
        return a(1, byteArrayOutputStream.toByteArray());
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, int i, String str) {
        byte[] bytes = str == null ? new byte[0] : str.getBytes(StandardCharsets.UTF_8);
        if (bytes.length <= 65535) {
            byteArrayOutputStream.write(i & 255);
            byteArrayOutputStream.write((bytes.length >>> 8) & 255);
            byteArrayOutputStream.write(bytes.length & 255);
            byteArrayOutputStream.write(bytes, 0, bytes.length);
            return;
        }
        throw new IllegalArgumentException("GW TLV too large");
    }

    public static byte[] a(int i, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 8];
        byte[] bArr3 = a;
        bArr2[0] = bArr3[0];
        bArr2[1] = bArr3[1];
        bArr2[2] = 1;
        bArr2[3] = (byte) i;
        long length = bArr.length;
        bArr2[4] = (byte) ((length >>> 24) & 255);
        bArr2[5] = (byte) ((length >>> 16) & 255);
        bArr2[6] = (byte) ((length >>> 8) & 255);
        bArr2[7] = (byte) (length & 255);
        System.arraycopy(bArr, 0, bArr2, 8, bArr.length);
        return bArr2;
    }
}
