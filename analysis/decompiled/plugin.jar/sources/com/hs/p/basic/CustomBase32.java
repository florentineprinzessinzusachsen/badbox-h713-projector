package com.hs.p.basic;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class CustomBase32 {
    private static final byte[] DECODE_TABLE = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1};
    private static final String ENCODE_TABLE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    public static byte[] decode(String str) {
        byte b2;
        int length = str.length();
        int i = (length * 5) / 8;
        byte[] bArr = new byte[i];
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int iCharAt = str.charAt(i4) - '0';
            if (iCharAt >= 0) {
                byte[] bArr2 = DECODE_TABLE;
                if (iCharAt < bArr2.length && (b2 = bArr2[iCharAt]) != -1) {
                    if (i2 <= 3) {
                        i2 = (i2 + 5) % 8;
                        if (i2 == 0) {
                            bArr[i3] = (byte) (b2 | bArr[i3]);
                            i3++;
                            if (i3 >= i) {
                                break;
                            }
                        } else {
                            bArr[i3] = (byte) ((b2 << (8 - i2)) | bArr[i3]);
                        }
                    } else {
                        i2 = (i2 + 5) % 8;
                        bArr[i3] = (byte) (bArr[i3] | (b2 >> i2));
                        i3++;
                        if (i3 >= i) {
                            break;
                        }
                        bArr[i3] = (byte) ((b2 << (8 - i2)) | bArr[i3]);
                    }
                }
            }
        }
        return bArr;
    }

    public static String encode(byte[] bArr) {
        int i;
        int i2;
        StringBuilder sb = new StringBuilder(((bArr.length + 7) * 8) / 5);
        int i3 = 0;
        int i4 = 0;
        while (i3 < bArr.length) {
            int i5 = bArr[i3];
            if (i5 < 0) {
                i5 += 256;
            }
            if (i4 > 3) {
                i3++;
                if (i3 < bArr.length) {
                    i2 = bArr[i3];
                    if (i2 < 0) {
                        i2 += 256;
                    }
                } else {
                    i2 = 0;
                }
                int i6 = i5 & (255 >> i4);
                i4 = (i4 + 5) % 8;
                i = (i6 << i4) | (i2 >> (8 - i4));
            } else {
                int i7 = i4 + 5;
                i = (i5 >> (8 - i7)) & 31;
                i4 = i7 % 8;
                if (i4 == 0) {
                    i3++;
                }
            }
            sb.append(ENCODE_TABLE.charAt(i));
        }
        return sb.toString();
    }
}
