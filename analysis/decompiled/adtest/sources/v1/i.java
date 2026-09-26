package v1;

import d0.l0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class i extends l0 {
    public static List R(Object[] objArr) {
        j2.i.e(objArr, "<this>");
        List listAsList = Arrays.asList(objArr);
        j2.i.d(listAsList, "asList(...)");
        return listAsList;
    }

    public static boolean S(Object[] objArr, Object[] objArr2) {
        if (objArr == objArr2) {
            return true;
        }
        if (objArr.length == objArr2.length) {
            int length = objArr.length;
            for (int i4 = 0; i4 < length; i4++) {
                Object obj = objArr[i4];
                Object obj2 = objArr2[i4];
                if (obj != obj2) {
                    if (obj != null && obj2 != null) {
                        if ((obj instanceof Object[]) && (obj2 instanceof Object[])) {
                            if (!S((Object[]) obj, (Object[]) obj2)) {
                            }
                        } else if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
                            if (!Arrays.equals((byte[]) obj, (byte[]) obj2)) {
                            }
                        } else if ((obj instanceof short[]) && (obj2 instanceof short[])) {
                            if (!Arrays.equals((short[]) obj, (short[]) obj2)) {
                            }
                        } else if ((obj instanceof int[]) && (obj2 instanceof int[])) {
                            if (!Arrays.equals((int[]) obj, (int[]) obj2)) {
                            }
                        } else if ((obj instanceof long[]) && (obj2 instanceof long[])) {
                            if (!Arrays.equals((long[]) obj, (long[]) obj2)) {
                            }
                        } else if ((obj instanceof float[]) && (obj2 instanceof float[])) {
                            if (!Arrays.equals((float[]) obj, (float[]) obj2)) {
                            }
                        } else if ((obj instanceof double[]) && (obj2 instanceof double[])) {
                            if (!Arrays.equals((double[]) obj, (double[]) obj2)) {
                            }
                        } else if ((obj instanceof char[]) && (obj2 instanceof char[])) {
                            if (!Arrays.equals((char[]) obj, (char[]) obj2)) {
                            }
                        } else if ((obj instanceof boolean[]) && (obj2 instanceof boolean[])) {
                            if (!Arrays.equals((boolean[]) obj, (boolean[]) obj2)) {
                            }
                        } else if (!obj.equals(obj2)) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static void T(int i4, int i5, int i6, byte[] bArr, byte[] bArr2) {
        j2.i.e(bArr, "<this>");
        j2.i.e(bArr2, "destination");
        System.arraycopy(bArr, i5, bArr2, i4, i6 - i5);
    }

    public static void U(int[] iArr, int[] iArr2, int i4, int i5, int i6) {
        j2.i.e(iArr, "<this>");
        j2.i.e(iArr2, "destination");
        System.arraycopy(iArr, i5, iArr2, i4, i6 - i5);
    }

    public static void V(Object[] objArr, Object[] objArr2, int i4, int i5, int i6) {
        j2.i.e(objArr, "<this>");
        j2.i.e(objArr2, "destination");
        System.arraycopy(objArr, i5, objArr2, i4, i6 - i5);
    }

    public static /* synthetic */ void W(Object[] objArr, Object[] objArr2, int i4, int i5, int i6) {
        if ((i6 & 4) != 0) {
            i4 = 0;
        }
        if ((i6 & 8) != 0) {
            i5 = objArr.length;
        }
        V(objArr, objArr2, 0, i4, i5);
    }

    public static byte[] X(byte[] bArr, int i4, int i5) {
        j2.i.e(bArr, "<this>");
        l0.k(i5, bArr.length);
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i4, i5);
        j2.i.d(bArrCopyOfRange, "copyOfRange(...)");
        return bArrCopyOfRange;
    }

    public static Object[] Y(Object[] objArr, int i4, int i5) {
        j2.i.e(objArr, "<this>");
        l0.k(i5, objArr.length);
        Object[] objArrCopyOfRange = Arrays.copyOfRange(objArr, i4, i5);
        j2.i.d(objArrCopyOfRange, "copyOfRange(...)");
        return objArrCopyOfRange;
    }

    public static void Z(Object[] objArr, Object obj, int i4, int i5) {
        j2.i.e(objArr, "<this>");
        Arrays.fill(objArr, i4, i5, obj);
    }

    public static Object b0(Object[] objArr, int i4) {
        j2.i.e(objArr, "<this>");
        if (i4 < 0 || i4 >= objArr.length) {
            return null;
        }
        return objArr[i4];
    }

    public static String c0(byte[] bArr, i2.l lVar) {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int i4 = 0;
        for (byte b4 : bArr) {
            i4++;
            if (i4 > 1) {
                sb.append((CharSequence) ":");
            }
            sb.append((CharSequence) lVar.h(Byte.valueOf(b4)));
        }
        sb.append((CharSequence) "");
        return sb.toString();
    }

    public static List d0(Object[] objArr) {
        int length = objArr.length;
        if (length != 0) {
            return length != 1 ? new ArrayList(new g(objArr, false)) : l3.h.S(objArr[0]);
        }
        return p.f2517d;
    }
}
