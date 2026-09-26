package q3;

import java.nio.charset.Charset;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r extends h {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final transient byte[][] f1857h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final transient int[] f1858i;

    public r(byte[][] bArr, int[] iArr) {
        super(h.f1823g.f1824d);
        this.f1857h = bArr;
        this.f1858i = iArr;
    }

    @Override // q3.h
    public final int a() {
        return this.f1858i[this.f1857h.length - 1];
    }

    @Override // q3.h
    public final String b() {
        return new h(l()).b();
    }

    @Override // q3.h
    public final byte[] c() {
        return l();
    }

    @Override // q3.h
    public final byte d(int i4) {
        byte[][] bArr = this.f1857h;
        int length = bArr.length - 1;
        int[] iArr = this.f1858i;
        a.a.f(iArr[length], i4, 1L);
        int iB = r3.b.b(this, i4);
        return bArr[iB][(i4 - (iB == 0 ? 0 : iArr[iB - 1])) + iArr[bArr.length + iB]];
    }

    @Override // q3.h
    public final boolean e(int i4, byte[] bArr, int i5, int i6) {
        j2.i.e(bArr, "other");
        if (i4 < 0 || i4 > a() - i6 || i5 < 0 || i5 > bArr.length - i6) {
            return false;
        }
        int i7 = i6 + i4;
        int iB = r3.b.b(this, i4);
        while (i4 < i7) {
            int[] iArr = this.f1858i;
            int i8 = iB == 0 ? 0 : iArr[iB - 1];
            int i9 = iArr[iB] - i8;
            byte[][] bArr2 = this.f1857h;
            int i10 = iArr[bArr2.length + iB];
            int iMin = Math.min(i7, i9 + i8) - i4;
            if (!a.a.e((i4 - i8) + i10, i5, iMin, bArr2[iB], bArr)) {
                return false;
            }
            i5 += iMin;
            i4 += iMin;
            iB++;
        }
        return true;
    }

    @Override // q3.h
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        return hVar.a() == a() && f(hVar, a());
    }

    @Override // q3.h
    public final boolean f(h hVar, int i4) {
        j2.i.e(hVar, "other");
        if (a() - i4 >= 0) {
            int iB = r3.b.b(this, 0);
            int i5 = 0;
            int i6 = 0;
            while (i5 < i4) {
                int[] iArr = this.f1858i;
                int i7 = iB == 0 ? 0 : iArr[iB - 1];
                int i8 = iArr[iB] - i7;
                byte[][] bArr = this.f1857h;
                int i9 = iArr[bArr.length + iB];
                int iMin = Math.min(i4, i8 + i7) - i5;
                if (hVar.e(i6, bArr[iB], (i5 - i7) + i9, iMin)) {
                    i6 += iMin;
                    i5 += iMin;
                    iB++;
                }
            }
            return true;
        }
        return false;
    }

    @Override // q3.h
    public final String g(Charset charset) {
        j2.i.e(charset, "charset");
        return new h(l()).g(charset);
    }

    @Override // q3.h
    public final h h(int i4, int i5) {
        if (i5 == -1234567890) {
            i5 = a();
        }
        if (i4 < 0) {
            throw new IllegalArgumentException(a1.c.d(i4, "beginIndex=", " < 0").toString());
        }
        if (i5 > a()) {
            throw new IllegalArgumentException(("endIndex=" + i5 + " > length(" + a() + ')').toString());
        }
        int i6 = i5 - i4;
        if (i6 < 0) {
            throw new IllegalArgumentException(a1.c.b(i5, i4, "endIndex=", " < beginIndex=").toString());
        }
        if (i4 == 0 && i5 == a()) {
            return this;
        }
        if (i4 == i5) {
            return h.f1823g;
        }
        int iB = r3.b.b(this, i4);
        int iB2 = r3.b.b(this, i5 - 1);
        byte[][] bArr = this.f1857h;
        byte[][] bArr2 = (byte[][]) v1.i.Y(bArr, iB, iB2 + 1);
        int[] iArr = new int[bArr2.length * 2];
        int[] iArr2 = this.f1858i;
        if (iB <= iB2) {
            int i7 = iB;
            int i8 = 0;
            while (true) {
                iArr[i8] = Math.min(iArr2[i7] - i4, i6);
                int i9 = i8 + 1;
                iArr[i8 + bArr2.length] = iArr2[bArr.length + i7];
                if (i7 == iB2) {
                    break;
                }
                i7++;
                i8 = i9;
            }
        }
        int i10 = iB != 0 ? iArr2[iB - 1] : 0;
        int length = bArr2.length;
        iArr[length] = (i4 - i10) + iArr[length];
        return new r(bArr2, iArr);
    }

    @Override // q3.h
    public final int hashCode() {
        int i4 = this.f1825e;
        if (i4 != 0) {
            return i4;
        }
        byte[][] bArr = this.f1857h;
        int length = bArr.length;
        int i5 = 0;
        int i6 = 1;
        int i7 = 0;
        while (i5 < length) {
            int[] iArr = this.f1858i;
            int i8 = iArr[length + i5];
            int i9 = iArr[i5];
            byte[] bArr2 = bArr[i5];
            int i10 = (i9 - i7) + i8;
            while (i8 < i10) {
                i6 = (i6 * 31) + bArr2[i8];
                i8++;
            }
            i5++;
            i7 = i9;
        }
        this.f1825e = i6;
        return i6;
    }

    @Override // q3.h
    public final h i() {
        return new h(l()).i();
    }

    @Override // q3.h
    public final void k(e eVar, int i4) {
        int iB = r3.b.b(this, 0);
        int i5 = 0;
        while (i5 < i4) {
            int[] iArr = this.f1858i;
            int i6 = iB == 0 ? 0 : iArr[iB - 1];
            int i7 = iArr[iB] - i6;
            byte[][] bArr = this.f1857h;
            int i8 = iArr[bArr.length + iB];
            int iMin = Math.min(i4, i7 + i6) - i5;
            int i9 = (i5 - i6) + i8;
            p pVar = new p(bArr[iB], i9, i9 + iMin, true);
            p pVar2 = eVar.f1821d;
            if (pVar2 == null) {
                pVar.f1853g = pVar;
                pVar.f1852f = pVar;
                eVar.f1821d = pVar;
            } else {
                p pVar3 = pVar2.f1853g;
                j2.i.b(pVar3);
                pVar3.b(pVar);
            }
            i5 += iMin;
            iB++;
        }
        eVar.f1822e += (long) i4;
    }

    public final byte[] l() {
        byte[] bArr = new byte[a()];
        byte[][] bArr2 = this.f1857h;
        int length = bArr2.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < length) {
            int[] iArr = this.f1858i;
            int i7 = iArr[length + i4];
            int i8 = iArr[i4];
            int i9 = i8 - i5;
            v1.i.T(i6, i7, i7 + i9, bArr2[i4], bArr);
            i6 += i9;
            i4++;
            i5 = i8;
        }
        return bArr;
    }

    @Override // q3.h
    public final String toString() {
        return new h(l()).toString();
    }
}
