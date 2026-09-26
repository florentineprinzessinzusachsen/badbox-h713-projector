package ddth2.hidden;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class C {
    public static ArrayList a(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            int i2 = i + 4;
            if (i2 > bArr.length) {
                break;
            }
            int i3 = bArr[i] & 255;
            byte b = bArr[i + 1];
            int iA = r.a(i + 2, bArr);
            int i4 = i2 + iA;
            if (i4 > bArr.length) {
                break;
            }
            byte[] bArr2 = new byte[iA];
            System.arraycopy(bArr, i2, bArr2, 0, iA);
            arrayList.add(new y(i3, bArr2));
            i = i4;
        }
        return arrayList;
    }
}
