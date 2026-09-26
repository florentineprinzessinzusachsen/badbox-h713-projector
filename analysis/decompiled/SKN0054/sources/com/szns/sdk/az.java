package com.szns.sdk;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
final class az extends aq {
    final transient byte[][] f;
    final transient int[] g;

    az(an anVar, int i) {
        super(null);
        be.a(anVar.b, 0L, i);
        ax axVar = anVar.a;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (axVar.c == axVar.b) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += axVar.c - axVar.b;
            i4++;
            axVar = axVar.f;
        }
        this.f = new byte[i4][];
        this.g = new int[i4 * 2];
        ax axVar2 = anVar.a;
        int i5 = 0;
        while (i2 < i) {
            this.f[i5] = axVar2.a;
            i2 += axVar2.c - axVar2.b;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.g;
            iArr[i5] = i2;
            iArr[this.f.length + i5] = axVar2.b;
            axVar2.d = true;
            i5++;
            axVar2 = axVar2.f;
        }
    }

    private int b(int i) {
        int iBinarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    private aq e() {
        return new aq(d());
    }

    @Override // com.szns.sdk.aq
    public final byte a(int i) {
        be.a(this.g[this.f.length - 1], i, 1L);
        int iB = b(i);
        int i2 = iB == 0 ? 0 : this.g[iB - 1];
        int[] iArr = this.g;
        byte[][] bArr = this.f;
        return bArr[iB][(i - i2) + iArr[bArr.length + iB]];
    }

    @Override // com.szns.sdk.aq
    public final aq a(int i, int i2) {
        return e().a(i, i2);
    }

    @Override // com.szns.sdk.aq
    public final String a() {
        return e().a();
    }

    @Override // com.szns.sdk.aq
    public final boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > c() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iB = b(i);
        while (i3 > 0) {
            int i4 = iB == 0 ? 0 : this.g[iB - 1];
            int iMin = Math.min(i3, ((this.g[iB] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr2 = this.f;
            if (!be.a(bArr2[iB], (i - i4) + iArr[bArr2.length + iB], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iB++;
        }
        return true;
    }

    @Override // com.szns.sdk.aq
    public final String b() {
        return e().b();
    }

    @Override // com.szns.sdk.aq
    public final int c() {
        return this.g[this.f.length - 1];
    }

    @Override // com.szns.sdk.aq
    public final byte[] d() {
        int[] iArr = this.g;
        byte[][] bArr = this.f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.g;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.f[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // com.szns.sdk.aq
    public final boolean equals(Object obj) {
        boolean z;
        if (obj == this) {
            return true;
        }
        if (obj instanceof aq) {
            aq aqVar = (aq) obj;
            if (aqVar.c() == c()) {
                int iC = c();
                if (c() - iC < 0) {
                    z = false;
                } else {
                    int iB = b(0);
                    int i = 0;
                    int i2 = 0;
                    while (true) {
                        if (iC > 0) {
                            int i3 = iB == 0 ? 0 : this.g[iB - 1];
                            int iMin = Math.min(iC, ((this.g[iB] - i3) + i3) - i);
                            int[] iArr = this.g;
                            byte[][] bArr = this.f;
                            if (!aqVar.a(i2, bArr[iB], (i - i3) + iArr[bArr.length + iB], iMin)) {
                                break;
                            }
                            i += iMin;
                            i2 += iMin;
                            iC -= iMin;
                            iB++;
                        } else {
                            z = true;
                        }
                    }
                    z = false;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.szns.sdk.aq
    public final int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int length = this.f.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < length) {
            byte[] bArr = this.f[i2];
            int[] iArr = this.g;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i3) + i5;
            while (i5 < i7) {
                i4 = (i4 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i3 = i6;
        }
        this.d = i4;
        return i4;
    }

    @Override // com.szns.sdk.aq
    public final String toString() {
        return e().toString();
    }
}
