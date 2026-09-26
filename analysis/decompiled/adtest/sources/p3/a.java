package p3;

import b3.d;
import j2.i;
import java.io.EOFException;
import q3.e;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final char[] f1764a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(String str, int i4, int i5, String str2, int i6) throws EOFException {
        int i7 = (i6 & 1) != 0 ? 0 : i4;
        int length = (i6 & 2) != 0 ? str.length() : i5;
        boolean z3 = (i6 & 8) == 0;
        boolean z4 = (i6 & 16) == 0;
        boolean z5 = (i6 & 32) == 0;
        boolean z6 = (i6 & 64) == 0;
        i.e(str, "<this>");
        int iCharCount = i7;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            int i8 = 32;
            if (iCodePointAt < 32 || iCodePointAt == 127 || ((iCodePointAt >= 128 && !z6) || p2.i.A0(str2, (char) iCodePointAt) || ((iCodePointAt == 37 && (!z3 || (z4 && !b(str, iCharCount, length)))) || (iCodePointAt == 43 && z5)))) {
                e eVar = new e();
                eVar.d0(str, i7, iCharCount);
                e eVar2 = null;
                while (iCharCount < length) {
                    int iCodePointAt2 = str.codePointAt(iCharCount);
                    if (!z3 || (iCodePointAt2 != 9 && iCodePointAt2 != 10 && iCodePointAt2 != 12 && iCodePointAt2 != 13)) {
                        if (iCodePointAt2 == i8 && str2 == " !\"#$&'()+,/:;<=>?@[\\]^`{|}~") {
                            eVar.c0("+");
                        } else if (iCodePointAt2 == 43 && z5) {
                            eVar.c0(z3 ? "+" : "%2B");
                        } else {
                            if (iCodePointAt2 >= i8 && iCodePointAt2 != 127) {
                                if ((iCodePointAt2 < 128 || z6) && !p2.i.A0(str2, (char) iCodePointAt2) && (iCodePointAt2 != 37 || (z3 && (!z4 || b(str, iCharCount, length))))) {
                                    eVar.e0(iCodePointAt2);
                                }
                            }
                            if (eVar2 == null) {
                                eVar2 = new e();
                            }
                            eVar2.e0(iCodePointAt2);
                            while (!eVar2.c()) {
                                byte b4 = eVar2.readByte();
                                eVar.X(37);
                                char[] cArr = f1764a;
                                eVar.X(cArr[((b4 & 255) >> 4) & 15]);
                                eVar.X(cArr[b4 & 15]);
                            }
                        }
                    }
                    iCharCount += Character.charCount(iCodePointAt2);
                    i8 = 32;
                }
                return eVar.K();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        String strSubstring = str.substring(i7, length);
        i.d(strSubstring, "substring(...)");
        return strSubstring;
    }

    public static final boolean b(String str, int i4, int i5) {
        i.e(str, "<this>");
        int i6 = i4 + 2;
        return i6 < i5 && str.charAt(i4) == '%' && d.k(str.charAt(i4 + 1)) != -1 && d.k(str.charAt(i6)) != -1;
    }

    public static String c(String str, int i4, int i5, int i6) {
        int i7;
        if ((i6 & 1) != 0) {
            i4 = 0;
        }
        if ((i6 & 2) != 0) {
            i5 = str.length();
        }
        boolean z3 = (i6 & 4) == 0;
        i.e(str, "<this>");
        int iCharCount = i4;
        while (iCharCount < i5) {
            char cCharAt = str.charAt(iCharCount);
            if (cCharAt == '%' || (cCharAt == '+' && z3)) {
                e eVar = new e();
                eVar.d0(str, i4, iCharCount);
                while (iCharCount < i5) {
                    int iCodePointAt = str.codePointAt(iCharCount);
                    if (iCodePointAt == 37 && (i7 = iCharCount + 2) < i5) {
                        int iK = d.k(str.charAt(iCharCount + 1));
                        int iK2 = d.k(str.charAt(i7));
                        if (iK == -1 || iK2 == -1) {
                            eVar.e0(iCodePointAt);
                            iCharCount += Character.charCount(iCodePointAt);
                        } else {
                            eVar.X((iK << 4) + iK2);
                            iCharCount = Character.charCount(iCodePointAt) + i7;
                        }
                    } else if (iCodePointAt == 43 && z3) {
                        eVar.X(32);
                        iCharCount++;
                    } else {
                        eVar.e0(iCodePointAt);
                        iCharCount += Character.charCount(iCodePointAt);
                    }
                }
                return eVar.K();
            }
            iCharCount++;
        }
        String strSubstring = str.substring(i4, i5);
        i.d(strSubstring, "substring(...)");
        return strSubstring;
    }
}
