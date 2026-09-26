package com.alibaba.fastjson.util;

import android.hardware.audio.common.V2_0.AudioFormat;

/* JADX INFO: loaded from: classes.dex */
public final class RyuFloat {
    private static final int[][] POW5_SPLIT = {new int[]{AudioFormat.APTX, 0}, new int[]{671088640, 0}, new int[]{838860800, 0}, new int[]{1048576000, 0}, new int[]{655360000, 0}, new int[]{819200000, 0}, new int[]{1024000000, 0}, new int[]{640000000, 0}, new int[]{800000000, 0}, new int[]{1000000000, 0}, new int[]{625000000, 0}, new int[]{781250000, 0}, new int[]{976562500, 0}, new int[]{610351562, 1073741824}, new int[]{762939453, AudioFormat.EVRC}, new int[]{953674316, 872415232}, new int[]{596046447, 1619001344}, new int[]{745058059, 1486880768}, new int[]{931322574, 1321730048}, new int[]{582076609, 289210368}, new int[]{727595761, 898383872}, new int[]{909494701, 1659850752}, new int[]{568434188, 1305842176}, new int[]{710542735, 1632302720}, new int[]{888178419, 1503507488}, new int[]{555111512, 671256724}, new int[]{693889390, 839070905}, new int[]{867361737, 2122580455}, new int[]{542101086, 521306416}, new int[]{677626357, 1725374844}, new int[]{847032947, 546105819}, new int[]{1058791184, 145761362}, new int[]{661744490, 91100851}, new int[]{827180612, 1187617888}, new int[]{1033975765, 1484522360}, new int[]{646234853, 1196261931}, new int[]{807793566, 2032198326}, new int[]{1009741958, 1466506084}, new int[]{631088724, 379695390}, new int[]{788860905, 474619238}, new int[]{986076131, 1130144959}, new int[]{616297582, 437905143}, new int[]{770371977, 1621123253}, new int[]{962964972, 415791331}, new int[]{601853107, 1333611405}, new int[]{752316384, 1130143345}, new int[]{940395480, 1412679181}};
    private static final int[][] POW5_INV_SPLIT = {new int[]{AudioFormat.EVRC, 1}, new int[]{214748364, 1717986919}, new int[]{171798691, 1803886265}, new int[]{137438953, 1013612282}, new int[]{219902325, 1192282922}, new int[]{175921860, 953826338}, new int[]{140737488, 763061070}, new int[]{225179981, 791400982}, new int[]{180143985, 203624056}, new int[]{144115188, 162899245}, new int[]{230584300, 1978625710}, new int[]{184467440, 1582900568}, new int[]{147573952, 1266320455}, new int[]{236118324, 308125809}, new int[]{188894659, 675997377}, new int[]{151115727, 970294631}, new int[]{241785163, 1981968139}, new int[]{193428131, 297084323}, new int[]{154742504, 1955654377}, new int[]{247588007, 1840556814}, new int[]{198070406, 613451992}, new int[]{158456325, 61264864}, new int[]{253530120, 98023782}, new int[]{202824096, 78419026}, new int[]{162259276, 1780722139}, new int[]{259614842, 1990161963}, new int[]{207691874, 733136111}, new int[]{166153499, 1016005619}, new int[]{265845599, 337118801}, new int[]{212676479, 699191770}, new int[]{170141183, 988850146}};

    public static String toString(float f) {
        char[] cArr = new char[15];
        return new String(cArr, 0, toString(f, cArr, 0));
    }

    public static int toString(float f, char[] cArr, int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        if (Float.isNaN(f)) {
            int i16 = i + 1;
            cArr[i] = 'N';
            int i17 = i16 + 1;
            cArr[i16] = 'a';
            cArr[i17] = 'N';
            return (i17 + 1) - i;
        }
        if (f == Float.POSITIVE_INFINITY) {
            int i18 = i + 1;
            cArr[i] = 'I';
            int i19 = i18 + 1;
            cArr[i18] = 'n';
            int i20 = i19 + 1;
            cArr[i19] = 'f';
            int i21 = i20 + 1;
            cArr[i20] = 'i';
            int i22 = i21 + 1;
            cArr[i21] = 'n';
            int i23 = i22 + 1;
            cArr[i22] = 'i';
            int i24 = i23 + 1;
            cArr[i23] = 't';
            cArr[i24] = 'y';
            return (i24 + 1) - i;
        }
        if (f == Float.NEGATIVE_INFINITY) {
            int i25 = i + 1;
            cArr[i] = '-';
            int i26 = i25 + 1;
            cArr[i25] = 'I';
            int i27 = i26 + 1;
            cArr[i26] = 'n';
            int i28 = i27 + 1;
            cArr[i27] = 'f';
            int i29 = i28 + 1;
            cArr[i28] = 'i';
            int i30 = i29 + 1;
            cArr[i29] = 'n';
            int i31 = i30 + 1;
            cArr[i30] = 'i';
            int i32 = i31 + 1;
            cArr[i31] = 't';
            cArr[i32] = 'y';
            return (i32 + 1) - i;
        }
        int iFloatToIntBits = Float.floatToIntBits(f);
        if (iFloatToIntBits == 0) {
            int i33 = i + 1;
            cArr[i] = '0';
            int i34 = i33 + 1;
            cArr[i33] = '.';
            cArr[i34] = '0';
            return (i34 + 1) - i;
        }
        if (iFloatToIntBits == Integer.MIN_VALUE) {
            int i35 = i + 1;
            cArr[i] = '-';
            int i36 = i35 + 1;
            cArr[i35] = '0';
            int i37 = i36 + 1;
            cArr[i36] = '.';
            cArr[i37] = '0';
            return (i37 + 1) - i;
        }
        int i38 = (iFloatToIntBits >> 23) & 255;
        int i39 = 8388607 & iFloatToIntBits;
        if (i38 == 0) {
            i2 = -149;
        } else {
            i2 = (i38 - 127) - 23;
            i39 |= 8388608;
        }
        boolean z = iFloatToIntBits < 0;
        boolean z2 = (i39 & 1) == 0;
        int i40 = i39 * 4;
        int i41 = i40 + 2;
        int i42 = i40 - ((((long) i39) != 8388608 || i38 <= 1) ? 2 : 1);
        int i43 = i2 - 2;
        if (i43 >= 0) {
            int i44 = (int) ((((long) i43) * 3010299) / 10000000);
            int i45 = i44 == 0 ? 1 : (int) ((((((long) i44) * 23219280) + 10000000) - 1) / 10000000);
            int i46 = (-i43) + i44;
            long j = POW5_INV_SPLIT[i44][0];
            long j2 = POW5_INV_SPLIT[i44][1];
            long j3 = i40;
            int i47 = (((i45 + 59) - 1) + i46) - 31;
            int i48 = (int) (((j3 * j) + ((j3 * j2) >> 31)) >> i47);
            long j4 = i41;
            int i49 = (int) (((j4 * j) + ((j4 * j2) >> 31)) >> i47);
            int i50 = i40;
            long j5 = i42;
            int i51 = (int) (((j * j5) + ((j5 * j2) >> 31)) >> i47);
            if (i44 == 0 || (i49 - 1) / 10 > i51 / 10) {
                i7 = 0;
            } else {
                int i52 = i44 - 1;
                i7 = (int) ((((((long) POW5_INV_SPLIT[i52][0]) * j3) + ((j3 * ((long) POW5_INV_SPLIT[i52][1])) >> 31)) >> (((i46 - 1) + (((i52 == 0 ? 1 : (int) ((((((long) i52) * 23219280) + 10000000) - 1) / 10000000)) + 59) - 1)) - 31)) % 10);
            }
            int i53 = 0;
            while (i41 > 0 && i41 % 5 == 0) {
                i41 /= 5;
                i53++;
            }
            int i54 = 0;
            while (i50 > 0 && i50 % 5 == 0) {
                i50 /= 5;
                i54++;
            }
            int i55 = 0;
            while (i42 > 0 && i42 % 5 == 0) {
                i42 /= 5;
                i55++;
            }
            int i56 = i53 >= i44 ? 1 : 0;
            i11 = i54 >= i44 ? 1 : 0;
            i10 = i55 >= i44 ? 1 : 0;
            i9 = i56;
            i8 = i44;
            i5 = i51;
            i4 = i49;
            i3 = i48;
            i6 = 0;
        } else {
            int i57 = -i43;
            int i58 = (int) ((((long) i57) * 6989700) / 10000000);
            int i59 = i57 - i58;
            int i60 = i59 == 0 ? 1 : (int) ((((((long) i59) * 23219280) + 10000000) - 1) / 10000000);
            long j6 = POW5_SPLIT[i59][0];
            long j7 = POW5_SPLIT[i59][1];
            int i61 = (i58 - (i60 - 61)) - 31;
            long j8 = i40;
            i3 = (int) (((j8 * j6) + ((j8 * j7) >> 31)) >> i61);
            long j9 = i41;
            i4 = (int) (((j9 * j6) + ((j9 * j7) >> 31)) >> i61);
            long j10 = i42;
            i5 = (int) (((j6 * j10) + ((j10 * j7) >> 31)) >> i61);
            if (i58 == 0 || (i4 - 1) / 10 > i5 / 10) {
                i6 = 0;
                i7 = 0;
            } else {
                int i62 = i59 + 1;
                i6 = 0;
                i7 = (int) ((((j8 * ((long) POW5_SPLIT[i62][0])) + ((((long) POW5_SPLIT[i62][1]) * j8) >> 31)) >> (((i58 - 1) - ((i62 == 0 ? 1 : (int) ((((((long) i62) * 23219280) + 10000000) - 1) / 10000000)) - 61)) - 31)) % 10);
            }
            i8 = i58 + i43;
            i9 = 1 >= i58 ? 1 : i6;
            int i63 = (i58 >= 23 || (((1 << (i58 + (-1))) - 1) & i40) != 0) ? i6 : 1;
            i10 = (i42 % 2 == 1 ? i6 : 1) >= i58 ? 1 : i6;
            i11 = i63;
        }
        int i64 = 1000000000;
        int i65 = 10;
        while (i65 > 0 && i4 < i64) {
            i64 /= 10;
            i65--;
        }
        int i66 = (i8 + i65) - 1;
        int i67 = (i66 < -3 || i66 >= 7) ? 1 : i6;
        if (i9 != 0 && !z2) {
            i4--;
        }
        int i68 = i6;
        while (true) {
            int i69 = i4 / 10;
            int i70 = i5 / 10;
            if (i69 <= i70 || (i4 < 100 && i67 != 0)) {
                break;
            }
            i10 &= i5 % 10 == 0 ? 1 : i6;
            i7 = i3 % 10;
            i3 /= 10;
            i68++;
            i4 = i69;
            i5 = i70;
        }
        if (i10 != 0 && z2 != 0) {
            while (i5 % 10 == 0 && (i4 >= 100 || i67 == 0)) {
                i4 /= 10;
                i7 = i3 % 10;
                i3 /= 10;
                i5 /= 10;
                i68++;
            }
        }
        if (i11 != 0 && i7 == 5 && i3 % 2 == 0) {
            i7 = 4;
        }
        int i71 = i3 + ((!(i3 == i5 && (i10 == 0 || z2 == 0)) && i7 < 5) ? i6 : 1);
        int i72 = i65 - i68;
        if (z) {
            i12 = i + 1;
            cArr[i] = '-';
        } else {
            i12 = i;
        }
        if (i67 != 0) {
            while (i6 < i72 - 1) {
                int i73 = i71 % 10;
                i71 /= 10;
                cArr[(i12 + i72) - i6] = (char) (i73 + 48);
                i6++;
            }
            cArr[i12] = (char) ((i71 % 10) + 48);
            cArr[i12 + 1] = '.';
            int i74 = i12 + i72 + 1;
            if (i72 == 1) {
                cArr[i74] = '0';
                i74++;
            }
            int i75 = i74 + 1;
            cArr[i74] = 'E';
            if (i66 < 0) {
                i14 = i75 + 1;
                cArr[i75] = '-';
                i66 = -i66;
            } else {
                i14 = i75;
            }
            if (i66 >= 10) {
                i15 = 48;
                cArr[i14] = (char) ((i66 / 10) + 48);
                i14++;
            } else {
                i15 = 48;
            }
            i13 = i14 + 1;
            cArr[i14] = (char) ((i66 % 10) + i15);
        } else {
            int i76 = 48;
            if (i66 < 0) {
                int i77 = i12 + 1;
                cArr[i12] = '0';
                int i78 = i77 + 1;
                cArr[i77] = '.';
                int i79 = -1;
                while (i79 > i66) {
                    cArr[i78] = '0';
                    i79--;
                    i78++;
                }
                int i80 = i78;
                while (i6 < i72) {
                    cArr[((i78 + i72) - i6) - 1] = (char) ((i71 % 10) + i76);
                    i71 /= 10;
                    i80++;
                    i6++;
                    i76 = 48;
                }
                i13 = i80;
            } else {
                int i81 = i66 + 1;
                if (i81 >= i72) {
                    while (i6 < i72) {
                        cArr[((i12 + i72) - i6) - 1] = (char) ((i71 % 10) + 48);
                        i71 /= 10;
                        i6++;
                    }
                    int i82 = i12 + i72;
                    while (i72 < i81) {
                        cArr[i82] = '0';
                        i72++;
                        i82++;
                    }
                    int i83 = i82 + 1;
                    cArr[i82] = '.';
                    cArr[i83] = '0';
                    i13 = i83 + 1;
                } else {
                    int i84 = i12 + 1;
                    while (i6 < i72) {
                        if ((i72 - i6) - 1 == i66) {
                            cArr[((i84 + i72) - i6) - 1] = '.';
                            i84--;
                        }
                        cArr[((i84 + i72) - i6) - 1] = (char) ((i71 % 10) + 48);
                        i71 /= 10;
                        i6++;
                    }
                    i13 = i12 + i72 + 1;
                }
            }
        }
        return i13 - i;
    }
}
