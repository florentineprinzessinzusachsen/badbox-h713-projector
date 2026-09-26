package android.support.v17.leanback.util;

/* JADX INFO: loaded from: classes.dex */
public final class MathUtil {
    private MathUtil() {
    }

    public static int safeLongToInt(long j) {
        int i = (int) j;
        if (i != j) {
            throw new ArithmeticException("Input overflows int.\n");
        }
        return i;
    }
}
