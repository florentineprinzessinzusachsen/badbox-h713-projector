package d.h0.i;

import java.util.Arrays;

/* JADX INFO: compiled from: Settings.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f4579a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f4580b = new int[10];

    void a() {
        this.f4579a = 0;
        Arrays.fill(this.f4580b, 0);
    }

    int b() {
        if ((this.f4579a & 2) != 0) {
            return this.f4580b[1];
        }
        return -1;
    }

    int c(int i) {
        return (this.f4579a & 32) != 0 ? this.f4580b[5] : i;
    }

    boolean d(int i) {
        return ((1 << i) & this.f4579a) != 0;
    }

    int b(int i) {
        return (this.f4579a & 16) != 0 ? this.f4580b[4] : i;
    }

    int c() {
        if ((this.f4579a & 128) != 0) {
            return this.f4580b[7];
        }
        return 65535;
    }

    int d() {
        return Integer.bitCount(this.f4579a);
    }

    m a(int i, int i2) {
        if (i >= 0) {
            int[] iArr = this.f4580b;
            if (i < iArr.length) {
                this.f4579a = (1 << i) | this.f4579a;
                iArr[i] = i2;
            }
        }
        return this;
    }

    int a(int i) {
        return this.f4580b[i];
    }

    void a(m mVar) {
        for (int i = 0; i < 10; i++) {
            if (mVar.d(i)) {
                a(i, mVar.a(i));
            }
        }
    }
}
