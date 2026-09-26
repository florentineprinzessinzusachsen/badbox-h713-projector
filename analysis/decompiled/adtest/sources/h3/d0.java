package h3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1088a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f1089b = new int[10];

    public final int a() {
        if ((this.f1088a & 16) != 0) {
            return this.f1089b[4];
        }
        return 65535;
    }

    public final void b(d0 d0Var) {
        j2.i.e(d0Var, "other");
        for (int i4 = 0; i4 < 10; i4++) {
            if (((1 << i4) & d0Var.f1088a) != 0) {
                c(i4, d0Var.f1089b[i4]);
            }
        }
    }

    public final void c(int i4, int i5) {
        if (i4 >= 0) {
            int[] iArr = this.f1089b;
            if (i4 >= iArr.length) {
                return;
            }
            this.f1088a = (1 << i4) | this.f1088a;
            iArr[i4] = i5;
        }
    }
}
