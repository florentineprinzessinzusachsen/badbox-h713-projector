package q3;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Arrays;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class h implements Serializable, Comparable {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final h f1823g = new h(new byte[0]);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final byte[] f1824d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public transient int f1825e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public transient String f1826f;

    public h(byte[] bArr) {
        j2.i.e(bArr, "data");
        this.f1824d = bArr;
    }

    public int a() {
        return this.f1824d.length;
    }

    public String b() {
        byte[] bArr = this.f1824d;
        char[] cArr = new char[bArr.length * 2];
        int i4 = 0;
        for (byte b4 : bArr) {
            int i5 = i4 + 1;
            char[] cArr2 = r3.b.f2057a;
            cArr[i4] = cArr2[(b4 >> 4) & 15];
            i4 += 2;
            cArr[i5] = cArr2[b4 & 15];
        }
        return new String(cArr);
    }

    public byte[] c() {
        return this.f1824d;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        h hVar = (h) obj;
        j2.i.e(hVar, "other");
        int iA = a();
        int iA2 = hVar.a();
        int iMin = Math.min(iA, iA2);
        for (int i4 = 0; i4 < iMin; i4++) {
            int iD = d(i4) & 255;
            int iD2 = hVar.d(i4) & 255;
            if (iD != iD2) {
                return iD < iD2 ? -1 : 1;
            }
        }
        if (iA == iA2) {
            return 0;
        }
        return iA < iA2 ? -1 : 1;
    }

    public byte d(int i4) {
        return this.f1824d[i4];
    }

    public boolean e(int i4, byte[] bArr, int i5, int i6) {
        j2.i.e(bArr, "other");
        if (i4 < 0) {
            return false;
        }
        byte[] bArr2 = this.f1824d;
        return i4 <= bArr2.length - i6 && i5 >= 0 && i5 <= bArr.length - i6 && a.a.e(i4, i5, i6, bArr2, bArr);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof h) {
            h hVar = (h) obj;
            int iA = hVar.a();
            byte[] bArr = this.f1824d;
            if (iA == bArr.length && hVar.e(0, bArr, 0, bArr.length)) {
                return true;
            }
        }
        return false;
    }

    public boolean f(h hVar, int i4) {
        j2.i.e(hVar, "other");
        return hVar.e(0, this.f1824d, 0, i4);
    }

    public String g(Charset charset) {
        j2.i.e(charset, "charset");
        return new String(this.f1824d, charset);
    }

    public h h(int i4, int i5) {
        if (i5 == -1234567890) {
            i5 = a();
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        byte[] bArr = this.f1824d;
        if (i5 <= bArr.length) {
            if (i5 - i4 >= 0) {
                return (i4 == 0 && i5 == bArr.length) ? this : new h(v1.i.X(bArr, i4, i5));
            }
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        throw new IllegalArgumentException(("endIndex > length(" + bArr.length + ')').toString());
    }

    public int hashCode() {
        int i4 = this.f1825e;
        if (i4 != 0) {
            return i4;
        }
        int iHashCode = Arrays.hashCode(this.f1824d);
        this.f1825e = iHashCode;
        return iHashCode;
    }

    public h i() {
        int i4 = 0;
        while (true) {
            byte[] bArr = this.f1824d;
            if (i4 >= bArr.length) {
                return this;
            }
            byte b4 = bArr[i4];
            if (b4 >= 65 && b4 <= 90) {
                byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
                j2.i.d(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i4] = (byte) (b4 + 32);
                for (int i5 = i4 + 1; i5 < bArrCopyOf.length; i5++) {
                    byte b5 = bArrCopyOf[i5];
                    if (b5 >= 65 && b5 <= 90) {
                        bArrCopyOf[i5] = (byte) (b5 + 32);
                    }
                }
                return new h(bArrCopyOf);
            }
            i4++;
        }
    }

    public final String j() {
        String str = this.f1826f;
        if (str != null) {
            return str;
        }
        byte[] bArrC = c();
        j2.i.e(bArrC, "<this>");
        String str2 = new String(bArrC, p2.a.f1738a);
        this.f1826f = str2;
        return str2;
    }

    public void k(e eVar, int i4) {
        eVar.U(i4, this.f1824d);
    }

    /* JADX WARN: Code duplicated, block: B:179:0x01b6 A[EDGE_INSN: B:179:0x01b6->B:180:0x01b7 BREAK  A[LOOP:0: B:7:0x000e->B:241:0x000e]] */
    public String toString() {
        byte b4;
        int i4;
        byte[] bArr = this.f1824d;
        if (bArr.length == 0) {
            return "[size=0]";
        }
        int length = bArr.length;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        loop0: while (i5 < length) {
            byte b5 = bArr[i5];
            if (b5 < 0) {
                if ((b5 >> 5) != -2) {
                    if ((b5 >> 4) != -2) {
                        if ((b5 >> 3) != -2) {
                            if (i7 == 64) {
                                break;
                            }
                            i6 = -1;
                            break;
                        }
                        int i8 = i5 + 3;
                        if (length > i8) {
                            byte b6 = bArr[i5 + 1];
                            if ((b6 & 192) != 128) {
                                if (i7 == 64) {
                                    break;
                                }
                                i6 = -1;
                                break;
                            }
                            byte b7 = bArr[i5 + 2];
                            if ((b7 & 192) != 128) {
                                if (i7 == 64) {
                                    break;
                                }
                                i6 = -1;
                                break;
                            }
                            byte b8 = bArr[i8];
                            if ((b8 & 192) != 128) {
                                if (i7 == 64) {
                                    break;
                                }
                                i6 = -1;
                                break;
                            }
                            int i9 = (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << 12)) ^ (b5 << 18);
                            if (i9 <= 1114111) {
                                if (55296 <= i9 && i9 < 57344) {
                                    if (i7 == 64) {
                                        break;
                                    }
                                    i6 = -1;
                                    break;
                                }
                                if (i9 >= 65536) {
                                    i4 = i7 + 1;
                                    if (i7 == 64) {
                                        break;
                                    }
                                    if ((i9 != 10 && i9 != 13 && ((i9 >= 0 && i9 < 32) || (127 <= i9 && i9 < 160))) || i9 == 65533) {
                                        i6 = -1;
                                        break;
                                    }
                                    i6 += i9 < 65536 ? 1 : 2;
                                    i5 += 4;
                                    i7 = i4;
                                } else {
                                    if (i7 == 64) {
                                        break;
                                    }
                                    i6 = -1;
                                    break;
                                }
                            } else {
                                if (i7 == 64) {
                                    break;
                                }
                                i6 = -1;
                                break;
                            }
                        } else {
                            if (i7 == 64) {
                                break;
                            }
                            i6 = -1;
                            break;
                        }
                    } else {
                        int i10 = i5 + 2;
                        if (length > i10) {
                            byte b9 = bArr[i5 + 1];
                            if ((b9 & 192) != 128) {
                                if (i7 == 64) {
                                    break;
                                }
                                i6 = -1;
                                break;
                            }
                            byte b10 = bArr[i10];
                            if ((b10 & 192) != 128) {
                                if (i7 == 64) {
                                    break;
                                }
                                i6 = -1;
                                break;
                            }
                            int i11 = ((b10 ^ (-123008)) ^ (b9 << 6)) ^ (b5 << 12);
                            if (i11 >= 2048) {
                                if (55296 <= i11 && i11 < 57344) {
                                    if (i7 == 64) {
                                        break;
                                    }
                                    i6 = -1;
                                    break;
                                }
                                i4 = i7 + 1;
                                if (i7 == 64) {
                                    break;
                                }
                                if ((i11 != 10 && i11 != 13 && ((i11 >= 0 && i11 < 32) || (127 <= i11 && i11 < 160))) || i11 == 65533) {
                                    i6 = -1;
                                    break;
                                }
                                i6 += i11 < 65536 ? 1 : 2;
                                i5 += 3;
                                i7 = i4;
                            } else {
                                if (i7 == 64) {
                                    break;
                                }
                                i6 = -1;
                                break;
                            }
                        } else {
                            if (i7 == 64) {
                                break;
                            }
                            i6 = -1;
                            break;
                        }
                    }
                } else {
                    int i12 = i5 + 1;
                    if (length > i12) {
                        byte b11 = bArr[i12];
                        if ((b11 & 192) != 128) {
                            if (i7 == 64) {
                                break;
                            }
                            i6 = -1;
                            break;
                        }
                        int i13 = (b11 ^ 3968) ^ (b5 << 6);
                        if (i13 >= 128) {
                            i4 = i7 + 1;
                            if (i7 == 64) {
                                break;
                            }
                            if ((i13 != 10 && i13 != 13 && ((i13 >= 0 && i13 < 32) || (127 <= i13 && i13 < 160))) || i13 == 65533) {
                                i6 = -1;
                                break;
                            }
                            i6 += i13 < 65536 ? 1 : 2;
                            i5 += 2;
                            i7 = i4;
                        } else {
                            if (i7 == 64) {
                                break;
                            }
                            i6 = -1;
                            break;
                        }
                    } else {
                        if (i7 == 64) {
                            break;
                        }
                        i6 = -1;
                        break;
                    }
                }
            } else {
                int i14 = i7 + 1;
                if (i7 == 64) {
                    break;
                }
                if ((b5 == 10 || b5 == 13 || ((b5 < 0 || b5 >= 32) && (127 > b5 || b5 >= 160))) && b5 != 65533) {
                    i6 += b5 < 65536 ? 1 : 2;
                    i5++;
                    while (true) {
                        i7 = i14;
                        if (i5 < length && (b4 = bArr[i5]) >= 0) {
                            i5++;
                            i14 = i7 + 1;
                            if (i7 == 64) {
                                break loop0;
                            }
                            if ((b4 == 10 || b4 == 13 || ((b4 < 0 || b4 >= 32) && (127 > b4 || b4 >= 160))) && b4 != 65533) {
                                i6 += b4 < 65536 ? 1 : 2;
                            }
                        }
                    }
                }
                i6 = -1;
                break;
            }
        }
        if (i6 != -1) {
            String strJ = j();
            String strSubstring = strJ.substring(0, i6);
            j2.i.d(strSubstring, "substring(...)");
            String strX0 = p2.p.x0(p2.p.x0(p2.p.x0(strSubstring, "\\", "\\\\"), "\n", "\\n"), "\r", "\\r");
            if (i6 >= strJ.length()) {
                return "[text=" + strX0 + ']';
            }
            return "[size=" + bArr.length + " text=" + strX0 + "…]";
        }
        if (bArr.length <= 64) {
            return "[hex=" + b() + ']';
        }
        StringBuilder sb = new StringBuilder("[size=");
        sb.append(bArr.length);
        sb.append(" hex=");
        if (64 <= bArr.length) {
            sb.append((64 == bArr.length ? this : new h(v1.i.X(bArr, 0, 64))).b());
            sb.append("…]");
            return sb.toString();
        }
        throw new IllegalArgumentException(("endIndex > length(" + bArr.length + ')').toString());
    }
}
