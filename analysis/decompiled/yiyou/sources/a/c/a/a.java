package a.c.a;

import java.util.Arrays;

/* JADX INFO: compiled from: ArrayLinkedVariables.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f70b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f71c;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int[] f74f;
    private int[] g;
    private float[] h;
    private int i;
    private int j;
    private boolean k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f69a = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f72d = 8;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private i f73e = null;

    a(b bVar, c cVar) {
        int i = this.f72d;
        this.f74f = new int[i];
        this.g = new int[i];
        this.h = new float[i];
        this.i = -1;
        this.j = -1;
        this.k = false;
        this.f70b = bVar;
        this.f71c = cVar;
    }

    public final void a(i iVar, float f2) {
        if (f2 == 0.0f) {
            a(iVar, true);
            return;
        }
        int i = this.i;
        if (i == -1) {
            this.i = 0;
            float[] fArr = this.h;
            int i2 = this.i;
            fArr[i2] = f2;
            this.f74f[i2] = iVar.f98b;
            this.g[i2] = -1;
            iVar.j++;
            iVar.a(this.f70b);
            this.f69a++;
            if (this.k) {
                return;
            }
            this.j++;
            int i3 = this.j;
            int[] iArr = this.f74f;
            if (i3 >= iArr.length) {
                this.k = true;
                this.j = iArr.length - 1;
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i != -1 && i5 < this.f69a; i5++) {
            int[] iArr2 = this.f74f;
            int i6 = iArr2[i];
            int i7 = iVar.f98b;
            if (i6 == i7) {
                this.h[i] = f2;
                return;
            }
            if (iArr2[i] < i7) {
                i4 = i;
            }
            i = this.g[i];
        }
        int length = this.j;
        int i8 = length + 1;
        if (this.k) {
            int[] iArr3 = this.f74f;
            if (iArr3[length] != -1) {
                length = iArr3.length;
            }
        } else {
            length = i8;
        }
        int[] iArr4 = this.f74f;
        if (length >= iArr4.length && this.f69a < iArr4.length) {
            int i9 = 0;
            while (true) {
                int[] iArr5 = this.f74f;
                if (i9 >= iArr5.length) {
                    break;
                }
                if (iArr5[i9] == -1) {
                    length = i9;
                    break;
                }
                i9++;
            }
        }
        int[] iArr6 = this.f74f;
        if (length >= iArr6.length) {
            length = iArr6.length;
            this.f72d *= 2;
            this.k = false;
            this.j = length - 1;
            this.h = Arrays.copyOf(this.h, this.f72d);
            this.f74f = Arrays.copyOf(this.f74f, this.f72d);
            this.g = Arrays.copyOf(this.g, this.f72d);
        }
        this.f74f[length] = iVar.f98b;
        this.h[length] = f2;
        if (i4 != -1) {
            int[] iArr7 = this.g;
            iArr7[length] = iArr7[i4];
            iArr7[i4] = length;
        } else {
            this.g[length] = this.i;
            this.i = length;
        }
        iVar.j++;
        iVar.a(this.f70b);
        this.f69a++;
        if (!this.k) {
            this.j++;
        }
        if (this.f69a >= this.f74f.length) {
            this.k = true;
        }
        int i10 = this.j;
        int[] iArr8 = this.f74f;
        if (i10 >= iArr8.length) {
            this.k = true;
            this.j = iArr8.length - 1;
        }
    }

    void b() {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            float[] fArr = this.h;
            fArr[i] = fArr[i] * (-1.0f);
            i = this.g[i];
        }
    }

    public String toString() {
        int i = this.i;
        String str = "";
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            str = ((str + " -> ") + this.h[i] + " : ") + this.f71c.f82c[this.f74f[i]];
            i = this.g[i];
        }
        return str;
    }

    final float b(int i) {
        int i2 = this.i;
        for (int i3 = 0; i2 != -1 && i3 < this.f69a; i3++) {
            if (i3 == i) {
                return this.h[i2];
            }
            i2 = this.g[i2];
        }
        return 0.0f;
    }

    public final float b(i iVar) {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            if (this.f74f[i] == iVar.f98b) {
                return this.h[i];
            }
            i = this.g[i];
        }
        return 0.0f;
    }

    final void a(i iVar, float f2, boolean z) {
        if (f2 == 0.0f) {
            return;
        }
        int i = this.i;
        if (i == -1) {
            this.i = 0;
            float[] fArr = this.h;
            int i2 = this.i;
            fArr[i2] = f2;
            this.f74f[i2] = iVar.f98b;
            this.g[i2] = -1;
            iVar.j++;
            iVar.a(this.f70b);
            this.f69a++;
            if (this.k) {
                return;
            }
            this.j++;
            int i3 = this.j;
            int[] iArr = this.f74f;
            if (i3 >= iArr.length) {
                this.k = true;
                this.j = iArr.length - 1;
                return;
            }
            return;
        }
        int i4 = -1;
        for (int i5 = 0; i != -1 && i5 < this.f69a; i5++) {
            int[] iArr2 = this.f74f;
            int i6 = iArr2[i];
            int i7 = iVar.f98b;
            if (i6 == i7) {
                float[] fArr2 = this.h;
                fArr2[i] = fArr2[i] + f2;
                if (fArr2[i] == 0.0f) {
                    if (i == this.i) {
                        this.i = this.g[i];
                    } else {
                        int[] iArr3 = this.g;
                        iArr3[i4] = iArr3[i];
                    }
                    if (z) {
                        iVar.b(this.f70b);
                    }
                    if (this.k) {
                        this.j = i;
                    }
                    iVar.j--;
                    this.f69a--;
                    return;
                }
                return;
            }
            if (iArr2[i] < i7) {
                i4 = i;
            }
            i = this.g[i];
        }
        int length = this.j;
        int i8 = length + 1;
        if (this.k) {
            int[] iArr4 = this.f74f;
            if (iArr4[length] != -1) {
                length = iArr4.length;
            }
        } else {
            length = i8;
        }
        int[] iArr5 = this.f74f;
        if (length >= iArr5.length && this.f69a < iArr5.length) {
            int i9 = 0;
            while (true) {
                int[] iArr6 = this.f74f;
                if (i9 >= iArr6.length) {
                    break;
                }
                if (iArr6[i9] == -1) {
                    length = i9;
                    break;
                }
                i9++;
            }
        }
        int[] iArr7 = this.f74f;
        if (length >= iArr7.length) {
            length = iArr7.length;
            this.f72d *= 2;
            this.k = false;
            this.j = length - 1;
            this.h = Arrays.copyOf(this.h, this.f72d);
            this.f74f = Arrays.copyOf(this.f74f, this.f72d);
            this.g = Arrays.copyOf(this.g, this.f72d);
        }
        this.f74f[length] = iVar.f98b;
        this.h[length] = f2;
        if (i4 != -1) {
            int[] iArr8 = this.g;
            iArr8[length] = iArr8[i4];
            iArr8[i4] = length;
        } else {
            this.g[length] = this.i;
            this.i = length;
        }
        iVar.j++;
        iVar.a(this.f70b);
        this.f69a++;
        if (!this.k) {
            this.j++;
        }
        int i10 = this.j;
        int[] iArr9 = this.f74f;
        if (i10 >= iArr9.length) {
            this.k = true;
            this.j = iArr9.length - 1;
        }
    }

    public final float a(i iVar, boolean z) {
        if (this.f73e == iVar) {
            this.f73e = null;
        }
        int i = this.i;
        if (i == -1) {
            return 0.0f;
        }
        int i2 = 0;
        int i3 = -1;
        while (i != -1 && i2 < this.f69a) {
            if (this.f74f[i] == iVar.f98b) {
                if (i == this.i) {
                    this.i = this.g[i];
                } else {
                    int[] iArr = this.g;
                    iArr[i3] = iArr[i];
                }
                if (z) {
                    iVar.b(this.f70b);
                }
                iVar.j--;
                this.f69a--;
                this.f74f[i] = -1;
                if (this.k) {
                    this.j = i;
                }
                return this.h[i];
            }
            i2++;
            i3 = i;
            i = this.g[i];
        }
        return 0.0f;
    }

    public final void a() {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            i iVar = this.f71c.f82c[this.f74f[i]];
            if (iVar != null) {
                iVar.b(this.f70b);
            }
            i = this.g[i];
        }
        this.i = -1;
        this.j = -1;
        this.k = false;
        this.f69a = 0;
    }

    final boolean a(i iVar) {
        int i = this.i;
        if (i == -1) {
            return false;
        }
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            if (this.f74f[i] == iVar.f98b) {
                return true;
            }
            i = this.g[i];
        }
        return false;
    }

    void a(float f2) {
        int i = this.i;
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            float[] fArr = this.h;
            fArr[i] = fArr[i] / f2;
            i = this.g[i];
        }
    }

    private boolean a(i iVar, e eVar) {
        return iVar.j <= 1;
    }

    i a(e eVar) {
        int i = this.i;
        i iVar = null;
        i iVar2 = null;
        float f2 = 0.0f;
        boolean z = false;
        float f3 = 0.0f;
        boolean z2 = false;
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            float[] fArr = this.h;
            float f4 = fArr[i];
            i iVar3 = this.f71c.f82c[this.f74f[i]];
            if (f4 < 0.0f) {
                if (f4 > -0.001f) {
                    fArr[i] = 0.0f;
                    iVar3.b(this.f70b);
                    f4 = 0.0f;
                }
            } else if (f4 < 0.001f) {
                fArr[i] = 0.0f;
                iVar3.b(this.f70b);
                f4 = 0.0f;
            }
            if (f4 != 0.0f) {
                if (iVar3.g == i.a.UNRESTRICTED) {
                    if (iVar2 == null || f2 > f4) {
                        boolean zA = a(iVar3, eVar);
                        z = zA;
                        f2 = f4;
                        iVar2 = iVar3;
                    } else if (!z && a(iVar3, eVar)) {
                        f2 = f4;
                        iVar2 = iVar3;
                        z = true;
                    }
                } else if (iVar2 == null && f4 < 0.0f) {
                    if (iVar == null || f3 > f4) {
                        boolean zA2 = a(iVar3, eVar);
                        z2 = zA2;
                        f3 = f4;
                        iVar = iVar3;
                    } else if (!z2 && a(iVar3, eVar)) {
                        f3 = f4;
                        iVar = iVar3;
                        z2 = true;
                    }
                }
            }
            i = this.g[i];
        }
        return iVar2 != null ? iVar2 : iVar;
    }

    final void a(b bVar, b bVar2, boolean z) {
        int i = this.i;
        while (true) {
            for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
                int i3 = this.f74f[i];
                i iVar = bVar2.f75a;
                if (i3 == iVar.f98b) {
                    float f2 = this.h[i];
                    a(iVar, z);
                    a aVar = bVar2.f78d;
                    int i4 = aVar.i;
                    for (int i5 = 0; i4 != -1 && i5 < aVar.f69a; i5++) {
                        a(this.f71c.f82c[aVar.f74f[i4]], aVar.h[i4] * f2, z);
                        i4 = aVar.g[i4];
                    }
                    bVar.f76b += bVar2.f76b * f2;
                    if (z) {
                        bVar2.f75a.b(bVar);
                    }
                    i = this.i;
                } else {
                    i = this.g[i];
                }
            }
            return;
        }
    }

    void a(b bVar, b[] bVarArr) {
        int i = this.i;
        while (true) {
            for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
                i iVar = this.f71c.f82c[this.f74f[i]];
                if (iVar.f99c != -1) {
                    float f2 = this.h[i];
                    a(iVar, true);
                    b bVar2 = bVarArr[iVar.f99c];
                    if (!bVar2.f79e) {
                        a aVar = bVar2.f78d;
                        int i3 = aVar.i;
                        for (int i4 = 0; i3 != -1 && i4 < aVar.f69a; i4++) {
                            a(this.f71c.f82c[aVar.f74f[i3]], aVar.h[i3] * f2, true);
                            i3 = aVar.g[i3];
                        }
                    }
                    bVar.f76b += bVar2.f76b * f2;
                    bVar2.f75a.b(bVar);
                    i = this.i;
                } else {
                    i = this.g[i];
                }
            }
            return;
        }
    }

    i a(boolean[] zArr, i iVar) {
        i.a aVar;
        int i = this.i;
        i iVar2 = null;
        float f2 = 0.0f;
        for (int i2 = 0; i != -1 && i2 < this.f69a; i2++) {
            if (this.h[i] < 0.0f) {
                i iVar3 = this.f71c.f82c[this.f74f[i]];
                if ((zArr == null || !zArr[iVar3.f98b]) && iVar3 != iVar && ((aVar = iVar3.g) == i.a.SLACK || aVar == i.a.ERROR)) {
                    float f3 = this.h[i];
                    if (f3 < f2) {
                        iVar2 = iVar3;
                        f2 = f3;
                    }
                }
            }
            i = this.g[i];
        }
        return iVar2;
    }

    final i a(int i) {
        int i2 = this.i;
        for (int i3 = 0; i2 != -1 && i3 < this.f69a; i3++) {
            if (i3 == i) {
                return this.f71c.f82c[this.f74f[i2]];
            }
            i2 = this.g[i2];
        }
        return null;
    }
}
