package ddth2.hidden;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class x {
    public static final byte[] a = {67, 78};

    public static byte[] a(C0018s c0018s, String str) {
        byte[] byteArray = new A().a(1, c0018s.a).a(2, "1.0.3").a(4, str).a(5, c0018s.b).a(6, c0018s.c).a(7, c0018s.d).a.toByteArray();
        byte[] bArr = new byte[byteArray.length + 6];
        byte[] bArr2 = a;
        bArr[0] = bArr2[0];
        bArr[1] = bArr2[1];
        bArr[2] = 1;
        bArr[3] = (byte) 1;
        int length = byteArray.length;
        bArr[4] = (byte) ((length >>> 8) & 255);
        bArr[5] = (byte) (length & 255);
        System.arraycopy(byteArray, 0, bArr, 6, byteArray.length);
        return bArr;
    }

    public static C0019t a(InputStream inputStream, byte[] bArr) throws IOException {
        byte b = bArr[0];
        byte[] bArr2 = a;
        if (b == bArr2[0] && bArr[1] == bArr2[1] && bArr[2] == 1) {
            int iA = r.a(4, bArr);
            if (iA <= 16384) {
                byte[] bArr3 = new byte[iA];
                try {
                    r.a(inputStream, bArr3, 0, iA);
                    return new C0019t(bArr[3] & 255, bArr3);
                } catch (SocketTimeoutException e) {
                    throw new IOException("CN body timed out", e);
                }
            }
            throw new IOException("CN body too large");
        }
        throw new IOException("bad CN header");
    }

    public static C0021v a(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayListA = C.a(bArr);
        int size = arrayListA.size();
        int i = 0;
        while (true) {
            boolean z = false;
            while (i < size) {
                Object obj = arrayListA.get(i);
                i++;
                y yVar = (y) obj;
                int i2 = yVar.a;
                if (i2 == 16) {
                    int iMin = Math.min(yVar.b.length, 8);
                    long j = 0;
                    for (int i3 = 0; i3 < iMin; i3++) {
                        j = (j << 8) | (((long) yVar.b[i3]) & 255);
                    }
                    if (j != 0) {
                        z = true;
                    }
                } else if (i2 != 17 && i2 == 18) {
                    try {
                        arrayList.add(I.a(new String(yVar.b, StandardCharsets.UTF_8)));
                    } catch (RuntimeException unused) {
                    }
                }
            }
            return new C0021v(z, arrayList);
        }
    }
}
