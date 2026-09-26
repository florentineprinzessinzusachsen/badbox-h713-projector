package e;

import java.util.Arrays;

/* JADX INFO: compiled from: SegmentedByteString.java */
/* JADX INFO: loaded from: classes.dex */
final class q extends f {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final transient byte[][] f4767f;
    final transient int[] g;

    q(c cVar, int i) {
        super(null);
        u.a(cVar.f4728b, 0L, i);
        int i2 = 0;
        o oVar = cVar.f4727a;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            int i5 = oVar.f4761c;
            int i6 = oVar.f4760b;
            if (i5 == i6) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += i5 - i6;
            i4++;
            oVar = oVar.f4764f;
        }
        this.f4767f = new byte[i4][];
        this.g = new int[i4 * 2];
        o oVar2 = cVar.f4727a;
        int i7 = 0;
        while (i2 < i) {
            this.f4767f[i7] = oVar2.f4759a;
            i2 += oVar2.f4761c - oVar2.f4760b;
            if (i2 > i) {
                i2 = i;
            }
            int[] iArr = this.g;
            iArr[i7] = i2;
            iArr[this.f4767f.length + i7] = oVar2.f4760b;
            oVar2.f4762d = true;
            i7++;
            oVar2 = oVar2.f4764f;
        }
    }

    private f j() {
        return new f(h());
    }

    @Override // e.f
    public String a() {
        return j().a();
    }

    @Override // e.f
    public String b() {
        return j().b();
    }

    @Override // e.f
    public f c() {
        return j().c();
    }

    @Override // e.f
    public f d() {
        return j().d();
    }

    @Override // e.f
    public f e() {
        return j().e();
    }

    @Override // e.f
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (fVar.f() == f() && a(0, fVar, 0, f())) {
                return true;
            }
        }
        return false;
    }

    @Override // e.f
    public int f() {
        return this.g[this.f4767f.length - 1];
    }

    @Override // e.f
    public f g() {
        return j().g();
    }

    @Override // e.f
    public byte[] h() {
        int[] iArr = this.g;
        byte[][] bArr = this.f4767f;
        byte[] bArr2 = new byte[iArr[bArr.length - 1]];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr2 = this.g;
            int i3 = iArr2[length + i];
            int i4 = iArr2[i];
            System.arraycopy(this.f4767f[i], i3, bArr2, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr2;
    }

    @Override // e.f
    public int hashCode() {
        int i = this.f4734b;
        if (i != 0) {
            return i;
        }
        int length = this.f4767f.length;
        int i2 = 0;
        int i3 = 1;
        int i4 = 0;
        while (i2 < length) {
            byte[] bArr = this.f4767f[i2];
            int[] iArr = this.g;
            int i5 = iArr[length + i2];
            int i6 = iArr[i2];
            int i7 = (i6 - i4) + i5;
            while (i5 < i7) {
                i3 = (i3 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i4 = i6;
        }
        this.f4734b = i3;
        return i3;
    }

    @Override // e.f
    public String i() {
        return j().i();
    }

    @Override // e.f
    public String toString() {
        return j().toString();
    }

    private int b(int i) {
        int iBinarySearch = Arrays.binarySearch(this.g, 0, this.f4767f.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : iBinarySearch ^ (-1);
    }

    @Override // e.f
    public f a(int i, int i2) {
        return j().a(i, i2);
    }

    @Override // e.f
    public byte a(int i) {
        u.a(this.g[this.f4767f.length - 1], i, 1L);
        int iB = b(i);
        int i2 = iB == 0 ? 0 : this.g[iB - 1];
        int[] iArr = this.g;
        byte[][] bArr = this.f4767f;
        return bArr[iB][(i - i2) + iArr[bArr.length + iB]];
    }

    @Override // e.f
    void a(c cVar) {
        int length = this.f4767f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int[] iArr = this.g;
            int i3 = iArr[length + i];
            int i4 = iArr[i];
            o oVar = new o(this.f4767f[i], i3, (i3 + i4) - i2, true, false);
            o oVar2 = cVar.f4727a;
            if (oVar2 == null) {
                oVar.g = oVar;
                oVar.f4764f = oVar;
                cVar.f4727a = oVar;
            } else {
                oVar2.g.a(oVar);
            }
            i++;
            i2 = i4;
        }
        cVar.f4728b += (long) i2;
    }

    @Override // e.f
    public boolean a(int i, f fVar, int i2, int i3) {
        if (i < 0 || i > f() - i3) {
            return false;
        }
        int iB = b(i);
        while (i3 > 0) {
            int i4 = iB == 0 ? 0 : this.g[iB - 1];
            int iMin = Math.min(i3, ((this.g[iB] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr = this.f4767f;
            if (!fVar.a(i2, bArr[iB], (i - i4) + iArr[bArr.length + iB], iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iB++;
        }
        return true;
    }

    @Override // e.f
    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > f() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iB = b(i);
        while (i3 > 0) {
            int i4 = iB == 0 ? 0 : this.g[iB - 1];
            int iMin = Math.min(i3, ((this.g[iB] - i4) + i4) - i);
            int[] iArr = this.g;
            byte[][] bArr2 = this.f4767f;
            if (!u.a(bArr2[iB], (i - i4) + iArr[bArr2.length + iB], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iB++;
        }
        return true;
    }
}
