package e;

/* JADX INFO: compiled from: Segment.java */
/* JADX INFO: loaded from: classes.dex */
final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final byte[] f4759a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f4760b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f4761c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f4762d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f4763e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    o f4764f;
    o g;

    o() {
        this.f4759a = new byte[8192];
        this.f4763e = true;
        this.f4762d = false;
    }

    public o a(o oVar) {
        oVar.g = this;
        oVar.f4764f = this.f4764f;
        this.f4764f.g = oVar;
        this.f4764f = oVar;
        return oVar;
    }

    public o b() {
        o oVar = this.f4764f;
        if (oVar == this) {
            oVar = null;
        }
        o oVar2 = this.g;
        oVar2.f4764f = this.f4764f;
        this.f4764f.g = oVar2;
        this.f4764f = null;
        this.g = null;
        return oVar;
    }

    o c() {
        this.f4762d = true;
        return new o(this.f4759a, this.f4760b, this.f4761c, true, false);
    }

    o(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f4759a = bArr;
        this.f4760b = i;
        this.f4761c = i2;
        this.f4762d = z;
        this.f4763e = z2;
    }

    public o a(int i) {
        o oVarA;
        if (i > 0 && i <= this.f4761c - this.f4760b) {
            if (i >= 1024) {
                oVarA = c();
            } else {
                oVarA = p.a();
                System.arraycopy(this.f4759a, this.f4760b, oVarA.f4759a, 0, i);
            }
            oVarA.f4761c = oVarA.f4760b + i;
            this.f4760b += i;
            this.g.a(oVarA);
            return oVarA;
        }
        throw new IllegalArgumentException();
    }

    public void a() {
        o oVar = this.g;
        if (oVar != this) {
            if (oVar.f4763e) {
                int i = this.f4761c - this.f4760b;
                if (i > (8192 - oVar.f4761c) + (oVar.f4762d ? 0 : oVar.f4760b)) {
                    return;
                }
                a(this.g, i);
                b();
                p.a(this);
                return;
            }
            return;
        }
        throw new IllegalStateException();
    }

    public void a(o oVar, int i) {
        if (oVar.f4763e) {
            int i2 = oVar.f4761c;
            if (i2 + i > 8192) {
                if (!oVar.f4762d) {
                    int i3 = oVar.f4760b;
                    if ((i2 + i) - i3 <= 8192) {
                        byte[] bArr = oVar.f4759a;
                        System.arraycopy(bArr, i3, bArr, 0, i2 - i3);
                        oVar.f4761c -= oVar.f4760b;
                        oVar.f4760b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.f4759a, this.f4760b, oVar.f4759a, oVar.f4761c, i);
            oVar.f4761c += i;
            this.f4760b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
