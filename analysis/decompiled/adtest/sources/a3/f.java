package a3;

import java.util.Comparator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class f implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        String str = (String) obj;
        String str2 = (String) obj2;
        j2.i.e(str, "a");
        j2.i.e(str2, "b");
        int iMin = Math.min(str.length(), str2.length());
        for (int i4 = 4; i4 < iMin; i4++) {
            char cCharAt = str.charAt(i4);
            char cCharAt2 = str2.charAt(i4);
            if (cCharAt != cCharAt2) {
                return j2.i.f(cCharAt, cCharAt2) < 0 ? -1 : 1;
            }
        }
        int length = str.length();
        int length2 = str2.length();
        if (length != length2) {
            return length < length2 ? -1 : 1;
        }
        return 0;
    }
}
