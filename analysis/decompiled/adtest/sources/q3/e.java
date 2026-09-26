package q3;

import java.io.EOFException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e implements g, f, Cloneable, ByteChannel {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public p f1821d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1822e;

    public final byte[] A(long j4) throws EOFException {
        if (j4 < 0 || j4 > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j4).toString());
        }
        if (this.f1822e < j4) {
            throw new EOFException();
        }
        int i4 = (int) j4;
        byte[] bArr = new byte[i4];
        int i5 = 0;
        while (i5 < i4) {
            int i6 = read(bArr, i5, i4 - i5);
            if (i6 == -1) {
                throw new EOFException();
            }
            i5 += i6;
        }
        return bArr;
    }

    public final long C() throws EOFException {
        int i4;
        if (this.f1822e == 0) {
            throw new EOFException();
        }
        int i5 = 0;
        boolean z3 = false;
        long j4 = 0;
        do {
            p pVar = this.f1821d;
            j2.i.b(pVar);
            byte[] bArr = pVar.f1847a;
            int i6 = pVar.f1848b;
            int i7 = pVar.f1849c;
            while (i6 < i7) {
                byte b4 = bArr[i6];
                if (b4 >= 48 && b4 <= 57) {
                    i4 = b4 - 48;
                } else if (b4 >= 97 && b4 <= 102) {
                    i4 = b4 - 87;
                } else {
                    if (b4 < 65 || b4 > 70) {
                        z3 = true;
                        if (i5 != 0) {
                            break;
                        }
                        char[] cArr = r3.b.f2057a;
                        throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(new String(new char[]{cArr[(b4 >> 4) & 15], cArr[b4 & 15]})));
                    }
                    i4 = b4 - 55;
                }
                if (((-1152921504606846976L) & j4) != 0) {
                    e eVar = new e();
                    eVar.Z(j4);
                    eVar.X(b4);
                    throw new NumberFormatException("Number too large: ".concat(eVar.K()));
                }
                j4 = (j4 << 4) | ((long) i4);
                i6++;
                i5++;
            }
            if (i6 == i7) {
                this.f1821d = pVar.a();
                q.a(pVar);
            } else {
                pVar.f1848b = i6;
            }
            if (z3) {
                break;
            }
        } while (this.f1821d != null);
        this.f1822e -= (long) i5;
        return j4;
    }

    @Override // q3.f
    public final /* bridge */ /* synthetic */ f D(int i4, byte[] bArr) {
        U(i4, bArr);
        return this;
    }

    @Override // q3.g
    public final void G(long j4) throws EOFException {
        if (this.f1822e < j4) {
            throw new EOFException();
        }
    }

    @Override // q3.f
    public final /* bridge */ /* synthetic */ f H(String str) {
        c0(str);
        return this;
    }

    public final String J(long j4, Charset charset) throws EOFException {
        j2.i.e(charset, "charset");
        if (j4 < 0 || j4 > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j4).toString());
        }
        if (this.f1822e < j4) {
            throw new EOFException();
        }
        if (j4 == 0) {
            return "";
        }
        p pVar = this.f1821d;
        j2.i.b(pVar);
        int i4 = pVar.f1848b;
        if (((long) i4) + j4 > pVar.f1849c) {
            return new String(A(j4), charset);
        }
        int i5 = (int) j4;
        String str = new String(pVar.f1847a, i4, i5, charset);
        int i6 = pVar.f1848b + i5;
        pVar.f1848b = i6;
        this.f1822e -= j4;
        if (i6 == pVar.f1849c) {
            this.f1821d = pVar.a();
            q.a(pVar);
        }
        return str;
    }

    public final String K() {
        return J(this.f1822e, p2.a.f1738a);
    }

    @Override // q3.g
    public final String O(Charset charset) {
        j2.i.e(charset, "charset");
        return J(this.f1822e, charset);
    }

    @Override // q3.g
    public final InputStream Q() {
        return new d(this, 0);
    }

    @Override // q3.s
    public final void R(long j4, e eVar) {
        p pVarB;
        j2.i.e(eVar, "source");
        if (eVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        a.a.f(eVar.f1822e, 0L, j4);
        while (j4 > 0) {
            p pVar = eVar.f1821d;
            j2.i.b(pVar);
            int i4 = pVar.f1849c;
            p pVar2 = eVar.f1821d;
            j2.i.b(pVar2);
            long j5 = i4 - pVar2.f1848b;
            int i5 = 0;
            if (j4 < j5) {
                p pVar3 = this.f1821d;
                p pVar4 = pVar3 != null ? pVar3.f1853g : null;
                if (pVar4 != null && pVar4.f1851e) {
                    if ((((long) pVar4.f1849c) + j4) - ((long) (pVar4.f1850d ? 0 : pVar4.f1848b)) <= 8192) {
                        p pVar5 = eVar.f1821d;
                        j2.i.b(pVar5);
                        pVar5.d(pVar4, (int) j4);
                        eVar.f1822e -= j4;
                        this.f1822e += j4;
                        return;
                    }
                }
                p pVar6 = eVar.f1821d;
                j2.i.b(pVar6);
                int i6 = (int) j4;
                if (i6 <= 0 || i6 > pVar6.f1849c - pVar6.f1848b) {
                    throw new IllegalArgumentException("byteCount out of range");
                }
                if (i6 >= 1024) {
                    pVarB = pVar6.c();
                } else {
                    pVarB = q.b();
                    byte[] bArr = pVar6.f1847a;
                    byte[] bArr2 = pVarB.f1847a;
                    int i7 = pVar6.f1848b;
                    v1.i.T(0, i7, i7 + i6, bArr, bArr2);
                }
                pVarB.f1849c = pVarB.f1848b + i6;
                pVar6.f1848b += i6;
                p pVar7 = pVar6.f1853g;
                j2.i.b(pVar7);
                pVar7.b(pVarB);
                eVar.f1821d = pVarB;
            }
            p pVar8 = eVar.f1821d;
            j2.i.b(pVar8);
            long j6 = pVar8.f1849c - pVar8.f1848b;
            eVar.f1821d = pVar8.a();
            p pVar9 = this.f1821d;
            if (pVar9 == null) {
                this.f1821d = pVar8;
                pVar8.f1853g = pVar8;
                pVar8.f1852f = pVar8;
            } else {
                p pVar10 = pVar9.f1853g;
                j2.i.b(pVar10);
                pVar10.b(pVar8);
                p pVar11 = pVar8.f1853g;
                if (pVar11 == pVar8) {
                    throw new IllegalStateException("cannot compact");
                }
                j2.i.b(pVar11);
                if (pVar11.f1851e) {
                    int i8 = pVar8.f1849c - pVar8.f1848b;
                    p pVar12 = pVar8.f1853g;
                    j2.i.b(pVar12);
                    int i9 = 8192 - pVar12.f1849c;
                    p pVar13 = pVar8.f1853g;
                    j2.i.b(pVar13);
                    if (!pVar13.f1850d) {
                        p pVar14 = pVar8.f1853g;
                        j2.i.b(pVar14);
                        i5 = pVar14.f1848b;
                    }
                    if (i8 <= i9 + i5) {
                        p pVar15 = pVar8.f1853g;
                        j2.i.b(pVar15);
                        pVar8.d(pVar15, i8);
                        pVar8.a();
                        q.a(pVar8);
                    }
                }
            }
            eVar.f1822e -= j6;
            this.f1822e += j6;
            j4 -= j6;
        }
    }

    public final h S(int i4) {
        if (i4 == 0) {
            return h.f1823g;
        }
        a.a.f(this.f1822e, 0L, i4);
        p pVar = this.f1821d;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i6 < i4) {
            j2.i.b(pVar);
            int i8 = pVar.f1849c;
            int i9 = pVar.f1848b;
            if (i8 == i9) {
                throw new AssertionError("s.limit == s.pos");
            }
            i6 += i8 - i9;
            i7++;
            pVar = pVar.f1852f;
        }
        byte[][] bArr = new byte[i7][];
        int[] iArr = new int[i7 * 2];
        p pVar2 = this.f1821d;
        int i10 = 0;
        while (i5 < i4) {
            j2.i.b(pVar2);
            bArr[i10] = pVar2.f1847a;
            i5 += pVar2.f1849c - pVar2.f1848b;
            iArr[i10] = Math.min(i5, i4);
            iArr[i10 + i7] = pVar2.f1848b;
            pVar2.f1850d = true;
            i10++;
            pVar2 = pVar2.f1852f;
        }
        return new r(bArr, iArr);
    }

    public final p T(int i4) {
        if (i4 < 1 || i4 > 8192) {
            throw new IllegalArgumentException("unexpected capacity");
        }
        p pVar = this.f1821d;
        if (pVar == null) {
            p pVarB = q.b();
            this.f1821d = pVarB;
            pVarB.f1853g = pVarB;
            pVarB.f1852f = pVarB;
            return pVarB;
        }
        p pVar2 = pVar.f1853g;
        j2.i.b(pVar2);
        if (pVar2.f1849c + i4 <= 8192 && pVar2.f1851e) {
            return pVar2;
        }
        p pVarB2 = q.b();
        pVar2.b(pVarB2);
        return pVarB2;
    }

    public final void U(int i4, byte[] bArr) {
        j2.i.e(bArr, "source");
        int i5 = 0;
        long j4 = i4;
        a.a.f(bArr.length, 0, j4);
        while (i5 < i4) {
            p pVarT = T(1);
            int iMin = Math.min(i4 - i5, 8192 - pVarT.f1849c);
            int i6 = i5 + iMin;
            v1.i.T(pVarT.f1849c, i5, i6, bArr, pVarT.f1847a);
            pVarT.f1849c += iMin;
            i5 = i6;
        }
        this.f1822e += j4;
    }

    public final void V(h hVar) {
        j2.i.e(hVar, "byteString");
        hVar.k(this, hVar.a());
    }

    public final void W(u uVar) {
        j2.i.e(uVar, "source");
        while (uVar.g(8192L, this) != -1) {
        }
    }

    public final void X(int i4) {
        p pVarT = T(1);
        byte[] bArr = pVarT.f1847a;
        int i5 = pVarT.f1849c;
        pVarT.f1849c = i5 + 1;
        bArr[i5] = (byte) i4;
        this.f1822e++;
    }

    public final void Y(long j4) {
        boolean z3;
        if (j4 == 0) {
            X(48);
            return;
        }
        if (j4 < 0) {
            j4 = -j4;
            if (j4 < 0) {
                c0("-9223372036854775808");
                return;
            }
            z3 = true;
        } else {
            z3 = false;
        }
        byte[] bArr = r3.a.f2055a;
        int iNumberOfLeadingZeros = ((64 - Long.numberOfLeadingZeros(j4)) * 10) >>> 5;
        int i4 = iNumberOfLeadingZeros + (j4 > r3.a.f2056b[iNumberOfLeadingZeros] ? 1 : 0);
        if (z3) {
            i4++;
        }
        p pVarT = T(i4);
        byte[] bArr2 = pVarT.f1847a;
        int i5 = pVarT.f1849c + i4;
        while (j4 != 0) {
            long j5 = 10;
            i5--;
            bArr2[i5] = r3.a.f2055a[(int) (j4 % j5)];
            j4 /= j5;
        }
        if (z3) {
            bArr2[i5 - 1] = 45;
        }
        pVarT.f1849c += i4;
        this.f1822e += (long) i4;
    }

    public final void Z(long j4) {
        if (j4 == 0) {
            X(48);
            return;
        }
        long j5 = (j4 >>> 1) | j4;
        long j6 = j5 | (j5 >>> 2);
        long j7 = j6 | (j6 >>> 4);
        long j8 = j7 | (j7 >>> 8);
        long j9 = j8 | (j8 >>> 16);
        long j10 = j9 | (j9 >>> 32);
        long j11 = j10 - ((j10 >>> 1) & 6148914691236517205L);
        long j12 = ((j11 >>> 2) & 3689348814741910323L) + (j11 & 3689348814741910323L);
        long j13 = ((j12 >>> 4) + j12) & 1085102592571150095L;
        long j14 = j13 + (j13 >>> 8);
        long j15 = j14 + (j14 >>> 16);
        int i4 = (int) ((((j15 & 63) + ((j15 >>> 32) & 63)) + ((long) 3)) / ((long) 4));
        p pVarT = T(i4);
        byte[] bArr = pVarT.f1847a;
        int i5 = pVarT.f1849c;
        for (int i6 = (i5 + i4) - 1; i6 >= i5; i6--) {
            bArr[i6] = r3.a.f2055a[(int) (15 & j4)];
            j4 >>>= 4;
        }
        pVarT.f1849c += i4;
        this.f1822e += (long) i4;
    }

    public final void a0(int i4) {
        p pVarT = T(4);
        byte[] bArr = pVarT.f1847a;
        int i5 = pVarT.f1849c;
        bArr[i5] = (byte) ((i4 >>> 24) & 255);
        bArr[i5 + 1] = (byte) ((i4 >>> 16) & 255);
        bArr[i5 + 2] = (byte) ((i4 >>> 8) & 255);
        bArr[i5 + 3] = (byte) (i4 & 255);
        pVarT.f1849c = i5 + 4;
        this.f1822e += 4;
    }

    public final void b(e eVar, long j4, long j5) {
        j2.i.e(eVar, "out");
        long j6 = j4;
        a.a.f(this.f1822e, j6, j5);
        if (j5 == 0) {
            return;
        }
        eVar.f1822e += j5;
        p pVar = this.f1821d;
        while (true) {
            j2.i.b(pVar);
            long j7 = pVar.f1849c - pVar.f1848b;
            if (j6 < j7) {
                break;
            }
            j6 -= j7;
            pVar = pVar.f1852f;
        }
        p pVar2 = pVar;
        long j8 = j5;
        while (j8 > 0) {
            j2.i.b(pVar2);
            p pVarC = pVar2.c();
            int i4 = pVarC.f1848b + ((int) j6);
            pVarC.f1848b = i4;
            pVarC.f1849c = Math.min(i4 + ((int) j8), pVarC.f1849c);
            p pVar3 = eVar.f1821d;
            if (pVar3 == null) {
                pVarC.f1853g = pVarC;
                pVarC.f1852f = pVarC;
                eVar.f1821d = pVarC;
            } else {
                p pVar4 = pVar3.f1853g;
                j2.i.b(pVar4);
                pVar4.b(pVarC);
            }
            j8 -= (long) (pVarC.f1849c - pVarC.f1848b);
            pVar2 = pVar2.f1852f;
            j6 = 0;
        }
    }

    public final void b0(int i4) {
        p pVarT = T(2);
        byte[] bArr = pVarT.f1847a;
        int i5 = pVarT.f1849c;
        bArr[i5] = (byte) ((i4 >>> 8) & 255);
        bArr[i5 + 1] = (byte) (i4 & 255);
        pVarT.f1849c = i5 + 2;
        this.f1822e += 2;
    }

    public final boolean c() {
        return this.f1822e == 0;
    }

    public final void c0(String str) {
        j2.i.e(str, "string");
        d0(str, 0, str.length());
    }

    public final Object clone() {
        e eVar = new e();
        if (this.f1822e == 0) {
            return eVar;
        }
        p pVar = this.f1821d;
        j2.i.b(pVar);
        p pVarC = pVar.c();
        eVar.f1821d = pVarC;
        pVarC.f1853g = pVarC;
        pVarC.f1852f = pVarC;
        for (p pVar2 = pVar.f1852f; pVar2 != pVar; pVar2 = pVar2.f1852f) {
            p pVar3 = pVarC.f1853g;
            j2.i.b(pVar3);
            j2.i.b(pVar2);
            pVar3.b(pVar2.c());
        }
        eVar.f1822e = this.f1822e;
        return eVar;
    }

    public final void d0(String str, int i4, int i5) {
        char cCharAt;
        j2.i.e(str, "string");
        if (i4 < 0) {
            throw new IllegalArgumentException(a1.c.c(i4, "beginIndex < 0: ").toString());
        }
        if (i5 < i4) {
            throw new IllegalArgumentException(a1.c.b(i5, i4, "endIndex < beginIndex: ", " < ").toString());
        }
        if (i5 > str.length()) {
            throw new IllegalArgumentException(("endIndex > string.length: " + i5 + " > " + str.length()).toString());
        }
        while (i4 < i5) {
            char cCharAt2 = str.charAt(i4);
            if (cCharAt2 < 128) {
                p pVarT = T(1);
                byte[] bArr = pVarT.f1847a;
                int i6 = pVarT.f1849c - i4;
                int iMin = Math.min(i5, 8192 - i6);
                int i7 = i4 + 1;
                bArr[i4 + i6] = (byte) cCharAt2;
                while (true) {
                    i4 = i7;
                    if (i4 >= iMin || (cCharAt = str.charAt(i4)) >= 128) {
                        break;
                    }
                    i7 = i4 + 1;
                    bArr[i4 + i6] = (byte) cCharAt;
                }
                int i8 = pVarT.f1849c;
                int i9 = (i6 + i4) - i8;
                pVarT.f1849c = i8 + i9;
                this.f1822e += (long) i9;
            } else {
                if (cCharAt2 < 2048) {
                    p pVarT2 = T(2);
                    byte[] bArr2 = pVarT2.f1847a;
                    int i10 = pVarT2.f1849c;
                    bArr2[i10] = (byte) ((cCharAt2 >> 6) | 192);
                    bArr2[i10 + 1] = (byte) ((cCharAt2 & '?') | 128);
                    pVarT2.f1849c = i10 + 2;
                    this.f1822e += 2;
                } else if (cCharAt2 < 55296 || cCharAt2 > 57343) {
                    p pVarT3 = T(3);
                    byte[] bArr3 = pVarT3.f1847a;
                    int i11 = pVarT3.f1849c;
                    bArr3[i11] = (byte) ((cCharAt2 >> '\f') | 224);
                    bArr3[i11 + 1] = (byte) ((63 & (cCharAt2 >> 6)) | 128);
                    bArr3[i11 + 2] = (byte) ((cCharAt2 & '?') | 128);
                    pVarT3.f1849c = i11 + 3;
                    this.f1822e += 3;
                } else {
                    int i12 = i4 + 1;
                    char cCharAt3 = i12 < i5 ? str.charAt(i12) : (char) 0;
                    if (cCharAt2 > 56319 || 56320 > cCharAt3 || cCharAt3 >= 57344) {
                        X(63);
                        i4 = i12;
                    } else {
                        int i13 = (((cCharAt2 & 1023) << 10) | (cCharAt3 & 1023)) + 65536;
                        p pVarT4 = T(4);
                        byte[] bArr4 = pVarT4.f1847a;
                        int i14 = pVarT4.f1849c;
                        bArr4[i14] = (byte) ((i13 >> 18) | 240);
                        bArr4[i14 + 1] = (byte) (((i13 >> 12) & 63) | 128);
                        bArr4[i14 + 2] = (byte) (((i13 >> 6) & 63) | 128);
                        bArr4[i14 + 3] = (byte) ((i13 & 63) | 128);
                        pVarT4.f1849c = i14 + 4;
                        this.f1822e += 4;
                        i4 += 2;
                    }
                }
                i4++;
            }
        }
    }

    public final void e0(int i4) {
        if (i4 < 128) {
            X(i4);
            return;
        }
        if (i4 < 2048) {
            p pVarT = T(2);
            byte[] bArr = pVarT.f1847a;
            int i5 = pVarT.f1849c;
            bArr[i5] = (byte) ((i4 >> 6) | 192);
            bArr[i5 + 1] = (byte) ((i4 & 63) | 128);
            pVarT.f1849c = i5 + 2;
            this.f1822e += 2;
            return;
        }
        if (55296 <= i4 && i4 < 57344) {
            X(63);
            return;
        }
        if (i4 < 65536) {
            p pVarT2 = T(3);
            byte[] bArr2 = pVarT2.f1847a;
            int i6 = pVarT2.f1849c;
            bArr2[i6] = (byte) ((i4 >> 12) | 224);
            bArr2[i6 + 1] = (byte) (((i4 >> 6) & 63) | 128);
            bArr2[i6 + 2] = (byte) ((i4 & 63) | 128);
            pVarT2.f1849c = i6 + 3;
            this.f1822e += 3;
            return;
        }
        if (i4 > 1114111) {
            throw new IllegalArgumentException("Unexpected code point: 0x".concat(a.a.H(i4)));
        }
        p pVarT3 = T(4);
        byte[] bArr3 = pVarT3.f1847a;
        int i7 = pVarT3.f1849c;
        bArr3[i7] = (byte) ((i4 >> 18) | 240);
        bArr3[i7 + 1] = (byte) (((i4 >> 12) & 63) | 128);
        bArr3[i7 + 2] = (byte) (((i4 >> 6) & 63) | 128);
        bArr3[i7 + 3] = (byte) ((i4 & 63) | 128);
        pVarT3.f1849c = i7 + 4;
        this.f1822e += 4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof e)) {
            return false;
        }
        long j4 = this.f1822e;
        e eVar = (e) obj;
        if (j4 != eVar.f1822e) {
            return false;
        }
        if (j4 == 0) {
            return true;
        }
        p pVar = this.f1821d;
        j2.i.b(pVar);
        p pVar2 = eVar.f1821d;
        j2.i.b(pVar2);
        int i4 = pVar.f1848b;
        int i5 = pVar2.f1848b;
        long j5 = 0;
        while (j5 < this.f1822e) {
            long jMin = Math.min(pVar.f1849c - i4, pVar2.f1849c - i5);
            long j6 = 0;
            while (j6 < jMin) {
                int i6 = i4 + 1;
                int i7 = i5 + 1;
                if (pVar.f1847a[i4] != pVar2.f1847a[i5]) {
                    return false;
                }
                j6++;
                i4 = i6;
                i5 = i7;
            }
            if (i4 == pVar.f1849c) {
                pVar = pVar.f1852f;
                j2.i.b(pVar);
                i4 = pVar.f1848b;
            }
            if (i5 == pVar2.f1849c) {
                pVar2 = pVar2.f1852f;
                j2.i.b(pVar2);
                i5 = pVar2.f1848b;
            }
            j5 += jMin;
        }
        return true;
    }

    @Override // q3.u
    public final w f() {
        return w.f1859d;
    }

    @Override // q3.u
    public final long g(long j4, e eVar) {
        j2.i.e(eVar, "sink");
        if (j4 < 0) {
            throw new IllegalArgumentException(("byteCount < 0: " + j4).toString());
        }
        long j5 = this.f1822e;
        if (j5 == 0) {
            return -1L;
        }
        if (j4 > j5) {
            j4 = j5;
        }
        eVar.R(j4, this);
        return j4;
    }

    public final int hashCode() {
        p pVar = this.f1821d;
        if (pVar == null) {
            return 0;
        }
        int i4 = 1;
        do {
            int i5 = pVar.f1849c;
            for (int i6 = pVar.f1848b; i6 < i5; i6++) {
                i4 = (i4 * 31) + pVar.f1847a[i6];
            }
            pVar = pVar.f1852f;
            j2.i.b(pVar);
        } while (pVar != this.f1821d);
        return i4;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return true;
    }

    @Override // q3.f
    public final /* bridge */ /* synthetic */ f j(h hVar) {
        V(hVar);
        return this;
    }

    public final byte k(long j4) {
        a.a.f(this.f1822e, j4, 1L);
        p pVar = this.f1821d;
        if (pVar == null) {
            j2.i.b(null);
            throw null;
        }
        long j5 = this.f1822e;
        if (j5 - j4 < j4) {
            while (j5 > j4) {
                pVar = pVar.f1853g;
                j2.i.b(pVar);
                j5 -= (long) (pVar.f1849c - pVar.f1848b);
            }
            return pVar.f1847a[(int) ((((long) pVar.f1848b) + j4) - j5)];
        }
        long j6 = 0;
        while (true) {
            int i4 = pVar.f1849c;
            int i5 = pVar.f1848b;
            long j7 = ((long) (i4 - i5)) + j6;
            if (j7 > j4) {
                return pVar.f1847a[(int) ((((long) i5) + j4) - j6)];
            }
            pVar = pVar.f1852f;
            j2.i.b(pVar);
            j6 = j7;
        }
    }

    public final long l(byte b4, long j4, long j5) {
        p pVar;
        long j6 = 0;
        if (0 > j4 || j4 > j5) {
            throw new IllegalArgumentException(("size=" + this.f1822e + " fromIndex=" + j4 + " toIndex=" + j5).toString());
        }
        long j7 = this.f1822e;
        if (j5 > j7) {
            j5 = j7;
        }
        if (j4 == j5 || (pVar = this.f1821d) == null) {
            return -1L;
        }
        if (j7 - j4 < j4) {
            while (j7 > j4) {
                pVar = pVar.f1853g;
                j2.i.b(pVar);
                j7 -= (long) (pVar.f1849c - pVar.f1848b);
            }
            while (j7 < j5) {
                byte[] bArr = pVar.f1847a;
                int iMin = (int) Math.min(pVar.f1849c, (((long) pVar.f1848b) + j5) - j7);
                for (int i4 = (int) ((((long) pVar.f1848b) + j4) - j7); i4 < iMin; i4++) {
                    if (bArr[i4] == b4) {
                        return ((long) (i4 - pVar.f1848b)) + j7;
                    }
                }
                j7 += (long) (pVar.f1849c - pVar.f1848b);
                pVar = pVar.f1852f;
                j2.i.b(pVar);
                j4 = j7;
            }
            return -1L;
        }
        while (true) {
            long j8 = ((long) (pVar.f1849c - pVar.f1848b)) + j6;
            if (j8 > j4) {
                break;
            }
            pVar = pVar.f1852f;
            j2.i.b(pVar);
            j6 = j8;
        }
        while (j6 < j5) {
            byte[] bArr2 = pVar.f1847a;
            int iMin2 = (int) Math.min(pVar.f1849c, (((long) pVar.f1848b) + j5) - j6);
            for (int i5 = (int) ((((long) pVar.f1848b) + j4) - j6); i5 < iMin2; i5++) {
                if (bArr2[i5] == b4) {
                    return ((long) (i5 - pVar.f1848b)) + j6;
                }
            }
            j6 += (long) (pVar.f1849c - pVar.f1848b);
            pVar = pVar.f1852f;
            j2.i.b(pVar);
            j4 = j6;
        }
        return -1L;
    }

    @Override // q3.g
    public final int m(m mVar) throws EOFException {
        j2.i.e(mVar, "options");
        int iB = r3.a.b(this, mVar, false);
        if (iB == -1) {
            return -1;
        }
        skip(mVar.f1839d[iB].a());
        return iB;
    }

    @Override // q3.g
    public final h q(long j4) throws EOFException {
        if (j4 < 0 || j4 > 2147483647L) {
            throw new IllegalArgumentException(("byteCount: " + j4).toString());
        }
        if (this.f1822e < j4) {
            throw new EOFException();
        }
        if (j4 < 4096) {
            return new h(A(j4));
        }
        h hVarS = S((int) j4);
        skip(j4);
        return hVarS;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        j2.i.e(byteBuffer, "sink");
        p pVar = this.f1821d;
        if (pVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), pVar.f1849c - pVar.f1848b);
        byteBuffer.put(pVar.f1847a, pVar.f1848b, iMin);
        int i4 = pVar.f1848b + iMin;
        pVar.f1848b = i4;
        this.f1822e -= (long) iMin;
        if (i4 == pVar.f1849c) {
            this.f1821d = pVar.a();
            q.a(pVar);
        }
        return iMin;
    }

    @Override // q3.g
    public final byte readByte() throws EOFException {
        if (this.f1822e == 0) {
            throw new EOFException();
        }
        p pVar = this.f1821d;
        j2.i.b(pVar);
        int i4 = pVar.f1848b;
        int i5 = pVar.f1849c;
        int i6 = i4 + 1;
        byte b4 = pVar.f1847a[i4];
        this.f1822e--;
        if (i6 != i5) {
            pVar.f1848b = i6;
            return b4;
        }
        this.f1821d = pVar.a();
        q.a(pVar);
        return b4;
    }

    @Override // q3.g
    public final int readInt() throws EOFException {
        if (this.f1822e < 4) {
            throw new EOFException();
        }
        p pVar = this.f1821d;
        j2.i.b(pVar);
        int i4 = pVar.f1848b;
        int i5 = pVar.f1849c;
        if (i5 - i4 < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = pVar.f1847a;
        int i6 = i4 + 3;
        int i7 = ((bArr[i4 + 1] & 255) << 16) | ((bArr[i4] & 255) << 24) | ((bArr[i4 + 2] & 255) << 8);
        int i8 = i4 + 4;
        int i9 = (bArr[i6] & 255) | i7;
        this.f1822e -= 4;
        if (i8 != i5) {
            pVar.f1848b = i8;
            return i9;
        }
        this.f1821d = pVar.a();
        q.a(pVar);
        return i9;
    }

    @Override // q3.g
    public final short readShort() throws EOFException {
        if (this.f1822e < 2) {
            throw new EOFException();
        }
        p pVar = this.f1821d;
        j2.i.b(pVar);
        int i4 = pVar.f1848b;
        int i5 = pVar.f1849c;
        if (i5 - i4 < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = pVar.f1847a;
        int i6 = i4 + 1;
        int i7 = (bArr[i4] & 255) << 8;
        int i8 = i4 + 2;
        int i9 = (bArr[i6] & 255) | i7;
        this.f1822e -= 2;
        if (i8 == i5) {
            this.f1821d = pVar.a();
            q.a(pVar);
        } else {
            pVar.f1848b = i8;
        }
        return (short) i9;
    }

    @Override // q3.g
    public final void skip(long j4) throws EOFException {
        while (j4 > 0) {
            p pVar = this.f1821d;
            if (pVar == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j4, pVar.f1849c - pVar.f1848b);
            long j5 = iMin;
            this.f1822e -= j5;
            j4 -= j5;
            int i4 = pVar.f1848b + iMin;
            pVar.f1848b = i4;
            if (i4 == pVar.f1849c) {
                this.f1821d = pVar.a();
                q.a(pVar);
            }
        }
    }

    @Override // q3.g
    public final String t(long j4) throws EOFException {
        if (j4 < 0) {
            throw new IllegalArgumentException(("limit < 0: " + j4).toString());
        }
        long j5 = j4 != Long.MAX_VALUE ? j4 + 1 : Long.MAX_VALUE;
        long jL = l((byte) 10, 0L, j5);
        if (jL != -1) {
            return r3.a.a(jL, this);
        }
        if (j5 < this.f1822e && k(j5 - 1) == 13 && k(j5) == 10) {
            return r3.a.a(j5, this);
        }
        e eVar = new e();
        b(eVar, 0L, Math.min(32, this.f1822e));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f1822e, j4) + " content=" + eVar.q(eVar.f1822e).b() + (char) 8230);
    }

    public final String toString() {
        long j4 = this.f1822e;
        if (j4 <= 2147483647L) {
            return S((int) j4).toString();
        }
        throw new IllegalStateException(("size > Int.MAX_VALUE: " + this.f1822e).toString());
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        j2.i.e(byteBuffer, "source");
        int iRemaining = byteBuffer.remaining();
        int i4 = iRemaining;
        while (i4 > 0) {
            p pVarT = T(1);
            int iMin = Math.min(i4, 8192 - pVarT.f1849c);
            byteBuffer.get(pVarT.f1847a, pVarT.f1849c, iMin);
            i4 -= iMin;
            pVarT.f1849c += iMin;
        }
        this.f1822e += (long) iRemaining;
        return iRemaining;
    }

    @Override // q3.f
    public final /* bridge */ /* synthetic */ f writeByte(int i4) {
        X(i4);
        return this;
    }

    @Override // q3.f
    public final /* bridge */ /* synthetic */ f writeInt(int i4) {
        a0(i4);
        return this;
    }

    @Override // q3.f
    public final /* bridge */ /* synthetic */ f writeShort(int i4) {
        b0(i4);
        return this;
    }

    @Override // q3.f
    public final f write(byte[] bArr) {
        U(bArr.length, bArr);
        return this;
    }

    public final int read(byte[] bArr, int i4, int i5) {
        a.a.f(bArr.length, i4, i5);
        p pVar = this.f1821d;
        if (pVar == null) {
            return -1;
        }
        int iMin = Math.min(i5, pVar.f1849c - pVar.f1848b);
        byte[] bArr2 = pVar.f1847a;
        int i6 = pVar.f1848b;
        v1.i.T(i4, i6, i6 + iMin, bArr2, bArr);
        int i7 = pVar.f1848b + iMin;
        pVar.f1848b = i7;
        this.f1822e -= (long) iMin;
        if (i7 == pVar.f1849c) {
            this.f1821d = pVar.a();
            q.a(pVar);
        }
        return iMin;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel, q3.s
    public final void close() {
    }

    @Override // q3.f, q3.s, java.io.Flushable
    public final void flush() {
    }
}
