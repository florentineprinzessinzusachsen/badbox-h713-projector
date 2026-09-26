package q3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f1847a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1848b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1849c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1850d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f1851e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public p f1852f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public p f1853g;

    public p() {
        this.f1847a = new byte[8192];
        this.f1851e = true;
        this.f1850d = false;
    }

    public final p a() {
        p pVar = this.f1852f;
        if (pVar == this) {
            pVar = null;
        }
        p pVar2 = this.f1853g;
        j2.i.b(pVar2);
        pVar2.f1852f = this.f1852f;
        p pVar3 = this.f1852f;
        j2.i.b(pVar3);
        pVar3.f1853g = this.f1853g;
        this.f1852f = null;
        this.f1853g = null;
        return pVar;
    }

    public final void b(p pVar) {
        j2.i.e(pVar, "segment");
        pVar.f1853g = this;
        pVar.f1852f = this.f1852f;
        p pVar2 = this.f1852f;
        j2.i.b(pVar2);
        pVar2.f1853g = pVar;
        this.f1852f = pVar;
    }

    public final p c() {
        this.f1850d = true;
        return new p(this.f1847a, this.f1848b, this.f1849c, true);
    }

    public final void d(p pVar, int i4) {
        j2.i.e(pVar, "sink");
        byte[] bArr = pVar.f1847a;
        if (!pVar.f1851e) {
            throw new IllegalStateException("only owner can write");
        }
        int i5 = pVar.f1849c;
        int i6 = i5 + i4;
        if (i6 > 8192) {
            if (pVar.f1850d) {
                throw new IllegalArgumentException();
            }
            int i7 = pVar.f1848b;
            if (i6 - i7 > 8192) {
                throw new IllegalArgumentException();
            }
            v1.i.T(0, i7, i5, bArr, bArr);
            pVar.f1849c -= pVar.f1848b;
            pVar.f1848b = 0;
        }
        int i8 = pVar.f1849c;
        int i9 = this.f1848b;
        v1.i.T(i8, i9, i9 + i4, this.f1847a, bArr);
        pVar.f1849c += i4;
        this.f1848b += i4;
    }

    public p(byte[] bArr, int i4, int i5, boolean z3) {
        j2.i.e(bArr, "data");
        this.f1847a = bArr;
        this.f1848b = i4;
        this.f1849c = i5;
        this.f1850d = z3;
        this.f1851e = false;
    }
}
