package a3;

import d0.l0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r implements Iterable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final r f198e = new r(new String[0]);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String[] f199d;

    public r(String[] strArr) {
        j2.i.e(strArr, "namesAndValues");
        this.f199d = strArr;
    }

    public final String a(String str) {
        String[] strArr = this.f199d;
        j2.i.e(strArr, "namesAndValues");
        int length = strArr.length - 2;
        int iW = l0.w(length, 0, -2);
        if (iW > length) {
            return null;
        }
        while (!str.equalsIgnoreCase(strArr[length])) {
            if (length == iW) {
                return null;
            }
            length -= 2;
        }
        return strArr[length + 1];
    }

    public final String b(int i4) {
        String str = (String) v1.i.b0(this.f199d, i4 * 2);
        if (str != null) {
            return str;
        }
        throw new IndexOutOfBoundsException("name[" + i4 + ']');
    }

    public final q c() {
        q qVar = new q();
        ArrayList arrayList = qVar.f197a;
        j2.i.e(arrayList, "<this>");
        String[] strArr = this.f199d;
        j2.i.e(strArr, "elements");
        arrayList.addAll(v1.i.R(strArr));
        return qVar;
    }

    public final String d(int i4) {
        String str = (String) v1.i.b0(this.f199d, (i4 * 2) + 1);
        if (str != null) {
            return str;
        }
        throw new IndexOutOfBoundsException("value[" + i4 + ']');
    }

    public final boolean equals(Object obj) {
        if (obj instanceof r) {
            return Arrays.equals(this.f199d, ((r) obj).f199d);
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f199d);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int size = size();
        u1.f[] fVarArr = new u1.f[size];
        for (int i4 = 0; i4 < size; i4++) {
            fVarArr[i4] = new u1.f(b(i4), d(i4));
        }
        return new j2.a(0, fVarArr);
    }

    public final int size() {
        return this.f199d.length / 2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        int size = size();
        for (int i4 = 0; i4 < size; i4++) {
            String strB = b(i4);
            String strD = d(i4);
            sb.append(strB);
            sb.append(": ");
            if (b3.d.j(strB)) {
                strD = "██";
            }
            sb.append(strD);
            sb.append("\n");
        }
        return sb.toString();
    }
}
