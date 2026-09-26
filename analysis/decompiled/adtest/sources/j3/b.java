package j3;

import q3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f1279a;

    static {
        h hVar = h.f1823g;
        f1279a = a1.a.m("xn--");
    }

    public static int a(int i4, int i5, boolean z3) {
        int i6 = z3 ? i4 / 700 : i4 / 2;
        int i7 = (i6 / i5) + i6;
        int i8 = 0;
        while (i7 > 455) {
            i7 /= 35;
            i8 += 36;
        }
        return ((i7 * 36) / (i7 + 38)) + i8;
    }

    public static int b(int i4) {
        if (i4 < 26) {
            return i4 + 97;
        }
        if (i4 < 36) {
            return i4 + 22;
        }
        throw new IllegalStateException(("unexpected digit: " + i4).toString());
    }
}
