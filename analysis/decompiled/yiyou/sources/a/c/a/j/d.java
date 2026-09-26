package a.c.a.j;

import java.util.ArrayList;

/* JADX INFO: compiled from: ChainHead.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected f f109a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected f f110b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected f f111c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected f f112d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected f f113e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected f f114f;
    protected f g;
    protected ArrayList<f> h;
    protected int i;
    protected int j;
    protected float k = 0.0f;
    private int l;
    private boolean m;
    protected boolean n;
    protected boolean o;
    protected boolean p;
    private boolean q;

    public d(f fVar, int i, boolean z) {
        this.m = false;
        this.f109a = fVar;
        this.l = i;
        this.m = z;
    }

    private static boolean a(f fVar, int i) {
        if (fVar.r() != 8 && fVar.C[i] == f.b.MATCH_CONSTRAINT) {
            int[] iArr = fVar.g;
            if (iArr[i] == 0 || iArr[i] == 3) {
                return true;
            }
        }
        return false;
    }

    private void b() {
        int i = this.l * 2;
        boolean z = false;
        f fVar = this.f109a;
        f fVar2 = fVar;
        boolean z2 = false;
        while (!z2) {
            this.i++;
            f[] fVarArr = fVar.i0;
            int i2 = this.l;
            f fVar3 = null;
            fVarArr[i2] = null;
            fVar.h0[i2] = null;
            if (fVar.r() != 8) {
                if (this.f110b == null) {
                    this.f110b = fVar;
                }
                this.f112d = fVar;
                f.b[] bVarArr = fVar.C;
                int i3 = this.l;
                if (bVarArr[i3] == f.b.MATCH_CONSTRAINT) {
                    int[] iArr = fVar.g;
                    if (iArr[i3] == 0 || iArr[i3] == 3 || iArr[i3] == 2) {
                        this.j++;
                        float[] fArr = fVar.g0;
                        int i4 = this.l;
                        float f2 = fArr[i4];
                        if (f2 > 0.0f) {
                            this.k += fArr[i4];
                        }
                        if (a(fVar, this.l)) {
                            if (f2 < 0.0f) {
                                this.n = true;
                            } else {
                                this.o = true;
                            }
                            if (this.h == null) {
                                this.h = new ArrayList<>();
                            }
                            this.h.add(fVar);
                        }
                        if (this.f114f == null) {
                            this.f114f = fVar;
                        }
                        f fVar4 = this.g;
                        if (fVar4 != null) {
                            fVar4.h0[this.l] = fVar;
                        }
                        this.g = fVar;
                    }
                }
            }
            if (fVar2 != fVar) {
                fVar2.i0[this.l] = fVar;
            }
            e eVar = fVar.A[i + 1].f118d;
            if (eVar != null) {
                f fVar5 = eVar.f116b;
                e[] eVarArr = fVar5.A;
                if (eVarArr[i].f118d != null && eVarArr[i].f118d.f116b == fVar) {
                    fVar3 = fVar5;
                }
            }
            if (fVar3 == null) {
                fVar3 = fVar;
                z2 = true;
            }
            fVar2 = fVar;
            fVar = fVar3;
        }
        this.f111c = fVar;
        if (this.l == 0 && this.m) {
            this.f113e = this.f111c;
        } else {
            this.f113e = this.f109a;
        }
        if (this.o && this.n) {
            z = true;
        }
        this.p = z;
    }

    public void a() {
        if (!this.q) {
            b();
        }
        this.q = true;
    }
}
