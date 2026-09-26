package com.szns.sdk;

import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class aq implements Serializable, Comparable {
    static final char[] a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final aq b = new aq((byte[]) new byte[0].clone());
    final byte[] c;
    transient int d;
    transient String e;

    aq(byte[] bArr) {
        this.c = bArr;
    }

    public byte a(int i) {
        return this.c[i];
    }

    public aq a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.c;
        if (i2 > bArr.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.c.length + ")");
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && i2 == bArr.length) {
            return this;
        }
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return new aq(bArr2);
    }

    public String a() {
        String str = this.e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.c, be.a);
        this.e = str2;
        return str2;
    }

    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0) {
            return false;
        }
        byte[] bArr2 = this.c;
        return i <= bArr2.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && be.a(bArr2, i, bArr, i2, i3);
    }

    public String b() {
        byte[] bArr = this.c;
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            char[] cArr2 = a;
            cArr[i] = cArr2[(b2 >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public int c() {
        return this.c.length;
    }

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(Object obj) {
        aq aqVar = (aq) obj;
        int iC = c();
        int iC2 = aqVar.c();
        int iMin = Math.min(iC, iC2);
        for (int i = 0; i < iMin; i++) {
            int iA = a(i) & 255;
            int iA2 = aqVar.a(i) & 255;
            if (iA != iA2) {
                return iA < iA2 ? -1 : 1;
            }
        }
        if (iC == iC2) {
            return 0;
        }
        return iC < iC2 ? -1 : 1;
    }

    public byte[] d() {
        return (byte[]) this.c.clone();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof aq) {
            aq aqVar = (aq) obj;
            int iC = aqVar.c();
            byte[] bArr = this.c;
            if (iC == bArr.length && aqVar.a(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.c);
        this.d = iHashCode;
        return iHashCode;
    }

    public String toString() {
        String strReplace;
        StringBuilder sb;
        if (this.c.length == 0) {
            return "[size=0]";
        }
        String strA = a();
        int length = strA.length();
        int length2 = 0;
        int i = 0;
        while (true) {
            if (length2 >= length) {
                length2 = strA.length();
                break;
            }
            if (i != 64) {
                int iCodePointAt = strA.codePointAt(length2);
                if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                    length2 = -1;
                    break;
                }
                i++;
                length2 += Character.charCount(iCodePointAt);
            } else {
                break;
            }
        }
        if (length2 != -1) {
            strReplace = strA.substring(0, length2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
            if (length2 < strA.length()) {
                sb = new StringBuilder("[size=");
                sb.append(this.c.length);
                sb.append(" text=");
                sb.append(strReplace);
                sb.append("…]");
            } else {
                sb = new StringBuilder("[text=");
                sb.append(strReplace);
                sb.append("]");
            }
        } else if (this.c.length <= 64) {
            sb = new StringBuilder("[hex=");
            sb.append(b());
            sb.append("]");
        } else {
            sb = new StringBuilder("[size=");
            sb.append(this.c.length);
            sb.append(" hex=");
            strReplace = a(0, 64).b();
            sb.append(strReplace);
            sb.append("…]");
        }
        return sb.toString();
    }
}
