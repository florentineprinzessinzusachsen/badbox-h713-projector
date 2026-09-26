package h3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a0[] f1062a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f1063b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1064c;

    public a0() {
        this.f1062a = new a0[256];
        this.f1063b = 0;
        this.f1064c = 0;
    }

    public a0(int i4, int i5) {
        this.f1062a = null;
        this.f1063b = i4;
        int i6 = i5 & 7;
        this.f1064c = i6 == 0 ? 8 : i6;
    }
}
