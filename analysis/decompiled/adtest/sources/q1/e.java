package q1;

import android.content.Context;
import android.content.pm.PackageManager;
import l3.h;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final byte[] f1783a = {75, 68, 78, 88, 69, 67, 78, 4, 73, 69, 68, 94, 79, 68, 94, 4, 90, 71, 4, 122, 75, 73, 65, 75, 77, 79, 103, 75, 68, 75, 77, 79, 88};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final byte[] f1784b = {77, 79, 94, 122, 75, 73, 65, 75, 77, 79, 99, 68, 89, 94, 75, 70, 70, 79, 88};

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final byte[] f1785c = {75, 68, 78, 88, 69, 67, 78, 4, 73, 69, 68, 94, 79, 68, 94, 4, 90, 71, 4, 122, 75, 73, 65, 75, 77, 79, 99, 68, 89, 94, 75, 70, 70, 79, 88};

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final byte[] f1786d = {75, 68, 78, 88, 69, 67, 78, 4, 73, 69, 68, 94, 79, 68, 94, 4, 90, 71, 4, 122, 75, 73, 65, 75, 77, 79, 99, 68, 89, 94, 75, 70, 70, 79, 88, 14, 121, 79, 89, 89, 67, 69, 68, 122, 75, 88, 75, 71, 89};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final byte[] f1787e = {73, 88, 79, 75, 94, 79, 121, 79, 89, 89, 67, 69, 68};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f1788f = {69, 90, 79, 68, 121, 79, 89, 89, 67, 69, 68};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f1789g = {75, 68, 78, 88, 69, 67, 78, 4, 73, 69, 68, 94, 79, 68, 94, 4, 90, 71, 4, 122, 75, 73, 65, 75, 77, 79, 99, 68, 89, 94, 75, 70, 70, 79, 88, 14, 121, 79, 89, 89, 67, 69, 68};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f1790h = {69, 90, 79, 68, 125, 88, 67, 94, 79};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final byte[] f1791i = {76, 89, 83, 68, 73};

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f1792j = {73, 69, 71, 71, 67, 94};

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final byte[] f1793k = {73, 70, 69, 89, 79};

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final byte[] f1794l = {73, 69, 71, 4, 75, 68, 78, 88, 69, 67, 78, 4, 92, 79, 68, 78, 67, 68, 77};

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final byte[] f1795m = {75, 68, 78, 88, 69, 67, 78, 4, 73, 69, 68, 94, 79, 68, 94, 4, 90, 71, 4, 122, 75, 73, 65, 75, 77, 79, 103, 75, 68, 75, 77, 79, 88};

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final byte[] f1796n = {89, 79, 94, 107, 90, 90, 70, 67, 73, 75, 94, 67, 69, 68, 111, 68, 75, 72, 70, 79, 78, 121, 79, 94, 94, 67, 68, 77};

    public static boolean a(Context context, int i4) {
        String str = i4 == 3 ? "关闭 (Disabled)" : "恢复 (Default)";
        try {
            PackageManager packageManager = context.getPackageManager();
            String strC = c(f1794l);
            String strC2 = c(f1796n);
            Class<?> cls = Class.forName(c(f1795m));
            Class<?> cls2 = Integer.TYPE;
            cls.getMethod(strC2, String.class, cls2, cls2).invoke(packageManager, strC, Integer.valueOf(i4), 0);
            h.a0("[StealthPlayManager] PlayStore 状态已被更改至: ".concat(str));
            return true;
        } catch (Exception e4) {
            h.a0("[StealthPlayManager] PlayStore 状态更改 (" + str + ") 遭遇失败 - " + e4.getClass().getSimpleName() + ": " + e4.getMessage());
            return false;
        }
    }

    public static String b(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int length = bArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            bArr2[i4] = (byte) (bArr[i4] ^ 42);
        }
        return new String(bArr2, p2.a.f1738a);
    }

    public static String c(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length];
        int length = bArr.length;
        for (int i4 = 0; i4 < length; i4++) {
            bArr2[i4] = (byte) (bArr[i4] ^ 42);
        }
        return new String(bArr2, p2.a.f1738a);
    }
}
