package a.c.a;

import java.util.Arrays;

/* JADX INFO: compiled from: SolverVariable.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    private static int k = 1;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f97a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f101e;
    a g;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f98b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f99c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f100d = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float[] f102f = new float[7];
    b[] h = new b[8];
    int i = 0;
    public int j = 0;

    /* JADX INFO: compiled from: SolverVariable.java */
    public enum a {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN
    }

    public i(a aVar, String str) {
        this.g = aVar;
    }

    static void b() {
        k++;
    }

    public final void a(b bVar) {
        int i = 0;
        while (true) {
            int i2 = this.i;
            if (i >= i2) {
                b[] bVarArr = this.h;
                if (i2 >= bVarArr.length) {
                    this.h = (b[]) Arrays.copyOf(bVarArr, bVarArr.length * 2);
                }
                b[] bVarArr2 = this.h;
                int i3 = this.i;
                bVarArr2[i3] = bVar;
                this.i = i3 + 1;
                return;
            }
            if (this.h[i] == bVar) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void c(b bVar) {
        int i = this.i;
        for (int i2 = 0; i2 < i; i2++) {
            b[] bVarArr = this.h;
            bVarArr[i2].f78d.a(bVarArr[i2], bVar, false);
        }
        this.i = 0;
    }

    public String toString() {
        return "" + this.f97a;
    }

    public final void b(b bVar) {
        int i = this.i;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.h[i2] == bVar) {
                for (int i3 = 0; i3 < (i - i2) - 1; i3++) {
                    b[] bVarArr = this.h;
                    int i4 = i2 + i3;
                    bVarArr[i4] = bVarArr[i4 + 1];
                }
                this.i--;
                return;
            }
        }
    }

    public void a() {
        this.f97a = null;
        this.g = a.UNKNOWN;
        this.f100d = 0;
        this.f98b = -1;
        this.f99c = -1;
        this.f101e = 0.0f;
        this.i = 0;
        this.j = 0;
    }

    public void a(a aVar, String str) {
        this.g = aVar;
    }
}
