package com.alibaba.fastjson.util;

import java.lang.reflect.Array;
import java.math.BigInteger;

/* JADX INFO: loaded from: classes.dex */
public final class RyuDouble {
    private static final int[][] POW5_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 326, 4);
    private static final int[][] POW5_INV_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 291, 4);

    static {
        BigInteger bigIntegerSubtract = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        BigInteger bigIntegerSubtract2 = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        int i = 0;
        while (i < 326) {
            BigInteger bigIntegerPow = BigInteger.valueOf(5L).pow(i);
            int iBitLength = bigIntegerPow.bitLength();
            int i2 = i == 0 ? 1 : (int) ((((((long) i) * 23219280) + 10000000) - 1) / 10000000);
            if (i2 != iBitLength) {
                throw new IllegalStateException(iBitLength + " != " + i2);
            }
            if (i < POW5_SPLIT.length) {
                for (int i3 = 0; i3 < 4; i3++) {
                    POW5_SPLIT[i][i3] = bigIntegerPow.shiftRight((iBitLength - 121) + ((3 - i3) * 31)).and(bigIntegerSubtract).intValue();
                }
            }
            if (i < POW5_INV_SPLIT.length) {
                BigInteger bigIntegerAdd = BigInteger.ONE.shiftLeft(iBitLength + 121).divide(bigIntegerPow).add(BigInteger.ONE);
                for (int i4 = 0; i4 < 4; i4++) {
                    if (i4 == 0) {
                        POW5_INV_SPLIT[i][i4] = bigIntegerAdd.shiftRight((3 - i4) * 31).intValue();
                    } else {
                        POW5_INV_SPLIT[i][i4] = bigIntegerAdd.shiftRight((3 - i4) * 31).and(bigIntegerSubtract2).intValue();
                    }
                }
            }
            i++;
        }
    }

    public static String toString(double d) {
        char[] cArr = new char[24];
        return new String(cArr, 0, toString(d, cArr, 0));
    }

    public static int toString(double d, char[] cArr, int i) {
        int i2;
        boolean z;
        boolean z2;
        long j;
        long j2;
        int i3;
        long j3;
        int i4;
        int i5;
        int i6;
        int i7;
        long j4;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        if (Double.isNaN(d)) {
            int i18 = i + 1;
            cArr[i] = 'N';
            int i19 = i18 + 1;
            cArr[i18] = 'a';
            cArr[i19] = 'N';
            return (i19 + 1) - i;
        }
        if (d == Double.POSITIVE_INFINITY) {
            int i20 = i + 1;
            cArr[i] = 'I';
            int i21 = i20 + 1;
            cArr[i20] = 'n';
            int i22 = i21 + 1;
            cArr[i21] = 'f';
            int i23 = i22 + 1;
            cArr[i22] = 'i';
            int i24 = i23 + 1;
            cArr[i23] = 'n';
            int i25 = i24 + 1;
            cArr[i24] = 'i';
            int i26 = i25 + 1;
            cArr[i25] = 't';
            cArr[i26] = 'y';
            return (i26 + 1) - i;
        }
        if (d == Double.NEGATIVE_INFINITY) {
            int i27 = i + 1;
            cArr[i] = '-';
            int i28 = i27 + 1;
            cArr[i27] = 'I';
            int i29 = i28 + 1;
            cArr[i28] = 'n';
            int i30 = i29 + 1;
            cArr[i29] = 'f';
            int i31 = i30 + 1;
            cArr[i30] = 'i';
            int i32 = i31 + 1;
            cArr[i31] = 'n';
            int i33 = i32 + 1;
            cArr[i32] = 'i';
            int i34 = i33 + 1;
            cArr[i33] = 't';
            cArr[i34] = 'y';
            return (i34 + 1) - i;
        }
        long jDoubleToLongBits = Double.doubleToLongBits(d);
        if (jDoubleToLongBits == 0) {
            int i35 = i + 1;
            cArr[i] = '0';
            int i36 = i35 + 1;
            cArr[i35] = '.';
            cArr[i36] = '0';
            return (i36 + 1) - i;
        }
        if (jDoubleToLongBits == Long.MIN_VALUE) {
            int i37 = i + 1;
            cArr[i] = '-';
            int i38 = i37 + 1;
            cArr[i37] = '0';
            int i39 = i38 + 1;
            cArr[i38] = '.';
            cArr[i39] = '0';
            return (i39 + 1) - i;
        }
        int i40 = (int) ((jDoubleToLongBits >>> 52) & 2047);
        long j5 = jDoubleToLongBits & 4503599627370495L;
        if (i40 == 0) {
            i2 = -1074;
        } else {
            i2 = (i40 - 1023) - 52;
            j5 |= 4503599627370496L;
        }
        boolean z3 = jDoubleToLongBits < 0;
        boolean z4 = (j5 & 1) == 0;
        long j6 = 4 * j5;
        long j7 = j6 + 2;
        int i41 = (j5 != 4503599627370496L || i40 <= 1) ? 1 : 0;
        long j8 = (j6 - 1) - ((long) i41);
        int i42 = i2 - 2;
        if (i42 >= 0) {
            int iMax = Math.max(0, ((int) ((((long) i42) * 3010299) / 10000000)) - 1);
            int i43 = ((((-i42) + iMax) + (((iMax == 0 ? 1 : (int) ((((((long) iMax) * 23219280) + 10000000) - 1) / 10000000)) + 122) - 1)) - 93) - 21;
            if (i43 < 0) {
                throw new IllegalArgumentException("" + i43);
            }
            int[] iArr = POW5_INV_SPLIT[iMax];
            long j9 = j6 >>> 31;
            long j10 = j6 & 2147483647L;
            long j11 = ((long) iArr[0]) * j9;
            long j12 = ((long) iArr[0]) * j10;
            z2 = z4;
            z = z3;
            long j13 = ((((((((((((j10 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j10)) + (j9 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j10)) + (((long) iArr[2]) * j9)) >>> 31) + j12) + (((long) iArr[1]) * j9)) >>> 21) + (j11 << 10)) >>> i43;
            long j14 = j7 >>> 31;
            long j15 = j7 & 2147483647L;
            long j16 = ((long) iArr[0]) * j14;
            long j17 = ((long) iArr[0]) * j15;
            long j18 = ((((((((((((j15 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j15)) + (j14 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j15)) + (((long) iArr[2]) * j14)) >>> 31) + j17) + (((long) iArr[1]) * j14)) >>> 21) + (j16 << 10)) >>> i43;
            long j19 = j8 >>> 31;
            long j20 = j8 & 2147483647L;
            long j21 = ((long) iArr[0]) * j19;
            long j22 = ((long) iArr[0]) * j20;
            j2 = j18;
            j3 = ((((((((((((j20 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j20)) + (j19 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j20)) + (((long) iArr[2]) * j19)) >>> 31) + j22) + (((long) iArr[1]) * j19)) >>> 21) + (j21 << 10)) >>> i43;
            if (iMax <= 21) {
                long j23 = j6 % 5;
                if (j23 == 0) {
                    if (j23 != 0) {
                        i17 = 0;
                    } else if (j6 % 25 != 0) {
                        i17 = 1;
                    } else if (j6 % 125 != 0) {
                        i17 = 2;
                    } else if (j6 % 625 != 0) {
                        i17 = 3;
                    } else {
                        long j24 = j6 / 625;
                        i17 = 4;
                        for (long j25 = 0; j24 > j25 && j24 % 5 == j25; j25 = 0) {
                            j24 /= 5;
                            i17++;
                        }
                    }
                    i14 = i17 >= iMax ? 1 : 0;
                    i6 = 0;
                } else if (z2) {
                    if (j8 % 5 != 0) {
                        i16 = 0;
                    } else if (j8 % 25 != 0) {
                        i16 = 1;
                    } else if (j8 % 125 != 0) {
                        i16 = 2;
                    } else if (j8 % 625 != 0) {
                        i16 = 3;
                    } else {
                        long j26 = j8 / 625;
                        i16 = 4;
                        for (long j27 = 0; j26 > j27 && j26 % 5 == j27; j27 = 0) {
                            j26 /= 5;
                            i16++;
                        }
                    }
                    i6 = i16 >= iMax ? 1 : 0;
                    i14 = 0;
                } else {
                    if (j7 % 5 != 0) {
                        i15 = 0;
                    } else if (j7 % 25 != 0) {
                        i15 = 1;
                    } else if (j7 % 125 != 0) {
                        i15 = 2;
                    } else if (j7 % 625 != 0) {
                        i15 = 3;
                    } else {
                        long j28 = j7 / 625;
                        i15 = 4;
                        for (long j29 = 0; j28 > j29 && j28 % 5 == j29; j29 = 0) {
                            j28 /= 5;
                            i15++;
                        }
                    }
                    if (i15 >= iMax) {
                        j2--;
                    }
                    i14 = 0;
                    i6 = 0;
                }
            } else {
                i14 = 0;
                i6 = 0;
            }
            i5 = i14;
            j = j13;
            i4 = iMax;
            i3 = 0;
        } else {
            z = z3;
            z2 = z4;
            int i44 = -i42;
            int iMax2 = Math.max(0, ((int) ((((long) i44) * 6989700) / 10000000)) - 1);
            int i45 = i44 - iMax2;
            int i46 = ((iMax2 - ((i45 == 0 ? 1 : (int) ((((((long) i45) * 23219280) + 10000000) - 1) / 10000000)) - 121)) - 93) - 21;
            if (i46 < 0) {
                throw new IllegalArgumentException("" + i46);
            }
            int[] iArr2 = POW5_SPLIT[i45];
            long j30 = j6 >>> 31;
            long j31 = j6 & 2147483647L;
            long j32 = ((long) iArr2[0]) * j30;
            long j33 = ((long) iArr2[0]) * j31;
            int i47 = i41;
            long j34 = ((((((((((((j31 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j31)) + (j30 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j31)) + (((long) iArr2[2]) * j30)) >>> 31) + j33) + (((long) iArr2[1]) * j30)) >>> 21) + (j32 << 10)) >>> i46;
            long j35 = j7 >>> 31;
            long j36 = j7 & 2147483647L;
            long j37 = ((long) iArr2[0]) * j35;
            long j38 = ((long) iArr2[0]) * j36;
            j = j34;
            j2 = ((((((((((((j36 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j36)) + (j35 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j36)) + (((long) iArr2[2]) * j35)) >>> 31) + j38) + (((long) iArr2[1]) * j35)) >>> 21) + (j37 << 10)) >>> i46;
            long j39 = j8 >>> 31;
            long j40 = j8 & 2147483647L;
            i3 = 0;
            long j41 = ((long) iArr2[0]) * j39;
            long j42 = ((long) iArr2[0]) * j40;
            j3 = ((((((((((((j40 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j40)) + (j39 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j40)) + (((long) iArr2[2]) * j39)) >>> 31) + j42) + (((long) iArr2[1]) * j39)) >>> 21) + (j41 << 10)) >>> i46;
            i4 = iMax2 + i42;
            i5 = 1;
            if (iMax2 <= 1) {
                if (!z2) {
                    j2--;
                } else if (i47 == 1) {
                    i6 = i5;
                }
                i6 = 0;
            } else if (iMax2 < 63) {
                i5 = (j6 & ((1 << (iMax2 - 1)) - 1)) == 0 ? 1 : 0;
                i6 = 0;
            } else {
                i5 = 0;
                i6 = i5;
            }
        }
        if (j2 >= 1000000000000000000L) {
            i7 = 19;
        } else if (j2 >= 100000000000000000L) {
            i7 = 18;
        } else if (j2 >= 10000000000000000L) {
            i7 = 17;
        } else if (j2 >= 1000000000000000L) {
            i7 = 16;
        } else if (j2 >= 100000000000000L) {
            i7 = 15;
        } else if (j2 >= 10000000000000L) {
            i7 = 14;
        } else if (j2 >= 1000000000000L) {
            i7 = 13;
        } else if (j2 >= 100000000000L) {
            i7 = 12;
        } else if (j2 >= 10000000000L) {
            i7 = 11;
        } else if (j2 >= 1000000000) {
            i7 = 10;
        } else if (j2 >= 100000000) {
            i7 = 9;
        } else if (j2 >= 10000000) {
            i7 = 8;
        } else if (j2 >= 1000000) {
            i7 = 7;
        } else if (j2 >= 100000) {
            i7 = 6;
        } else if (j2 >= 10000) {
            i7 = 5;
        } else if (j2 >= 1000) {
            i7 = 4;
        } else if (j2 >= 100) {
            i7 = 3;
        } else {
            i7 = j2 >= 10 ? 2 : 1;
        }
        int i48 = (i4 + i7) - 1;
        int i49 = (i48 < -3 || i48 >= 7) ? 1 : i3;
        if (i6 == 0 && i5 == 0) {
            i8 = i3;
            int i50 = i8;
            while (true) {
                long j43 = j2 / 10;
                long j44 = j3 / 10;
                if (j43 <= j44 || (j2 < 100 && i49 != 0)) {
                    break;
                }
                i50 = (int) (j % 10);
                j /= 10;
                i8++;
                j2 = j43;
                j3 = j44;
            }
            j4 = j + ((long) ((j == j3 || i50 >= 5) ? 1 : i3));
        } else {
            int i51 = i3;
            int i52 = i51;
            while (true) {
                long j45 = j2 / 10;
                long j46 = j3 / 10;
                if (j45 <= j46 || (j2 < 100 && i49 != 0)) {
                    break;
                }
                i6 &= j3 % 10 == 0 ? 1 : i3;
                i5 &= i51 == 0 ? 1 : i3;
                i51 = (int) (j % 10);
                j /= 10;
                i52++;
                j2 = j45;
                j3 = j46;
            }
            if (i6 != 0 && z2) {
                while (j3 % 10 == 0 && (j2 >= 100 || i49 == 0)) {
                    i5 &= i51 == 0 ? 1 : i3;
                    i51 = (int) (j % 10);
                    j2 /= 10;
                    j /= 10;
                    j3 /= 10;
                    i52++;
                }
            }
            if (i5 != 0 && i51 == 5 && j % 2 == 0) {
                i51 = 4;
            }
            j4 = j + ((long) (((j != j3 || (i6 != 0 && z2)) && i51 < 5) ? i3 : 1));
            i8 = i52;
        }
        int i53 = i7 - i8;
        if (z) {
            i9 = i + 1;
            cArr[i] = '-';
        } else {
            i9 = i;
        }
        if (i49 != 0) {
            while (i3 < i53 - 1) {
                int i54 = (int) (j4 % 10);
                j4 /= 10;
                cArr[(i9 + i53) - i3] = (char) (i54 + 48);
                i3++;
            }
            cArr[i9] = (char) ((j4 % 10) + 48);
            cArr[i9 + 1] = '.';
            int i55 = i9 + i53 + 1;
            if (i53 == 1) {
                i11 = i55 + 1;
                cArr[i55] = '0';
            } else {
                i11 = i55;
            }
            int i56 = i11 + 1;
            cArr[i11] = 'E';
            if (i48 < 0) {
                i12 = i56 + 1;
                cArr[i56] = '-';
                i48 = -i48;
            } else {
                i12 = i56;
            }
            if (i48 >= 100) {
                int i57 = i12 + 1;
                i13 = 48;
                cArr[i12] = (char) ((i48 / 100) + 48);
                i48 %= 100;
                i12 = i57 + 1;
                cArr[i57] = (char) ((i48 / 10) + 48);
            } else {
                i13 = 48;
                if (i48 >= 10) {
                    cArr[i12] = (char) ((i48 / 10) + 48);
                    i12++;
                }
            }
            cArr[i12] = (char) ((i48 % 10) + i13);
            return (i12 + 1) - i;
        }
        char c = '0';
        if (i48 < 0) {
            int i58 = i9 + 1;
            cArr[i9] = '0';
            int i59 = i58 + 1;
            cArr[i58] = '.';
            int i60 = -1;
            while (i60 > i48) {
                cArr[i59] = c;
                i60--;
                i59++;
                c = '0';
            }
            i10 = i59;
            while (i3 < i53) {
                cArr[((i59 + i53) - i3) - 1] = (char) ((j4 % 10) + 48);
                j4 /= 10;
                i10++;
                i3++;
            }
        } else {
            int i61 = i48 + 1;
            if (i61 >= i53) {
                while (i3 < i53) {
                    cArr[((i9 + i53) - i3) - 1] = (char) ((j4 % 10) + 48);
                    j4 /= 10;
                    i3++;
                }
                int i62 = i9 + i53;
                while (i53 < i61) {
                    cArr[i62] = '0';
                    i53++;
                    i62++;
                }
                int i63 = i62 + 1;
                cArr[i62] = '.';
                cArr[i63] = '0';
                i10 = i63 + 1;
            } else {
                int i64 = i9 + 1;
                while (i3 < i53) {
                    if ((i53 - i3) - 1 == i48) {
                        cArr[((i64 + i53) - i3) - 1] = '.';
                        i64--;
                    }
                    cArr[((i64 + i53) - i3) - 1] = (char) (48 + (j4 % 10));
                    j4 /= 10;
                    i3++;
                }
                i10 = i9 + i53 + 1;
            }
        }
        return i10 - i;
    }
}
