package v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g implements Comparable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f2396d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2397e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final String f2398f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final String f2399g;

    public g(int i4, int i5, String str, String str2) {
        j2.i.e(str, "from");
        j2.i.e(str2, "to");
        this.f2396d = i4;
        this.f2397e = i5;
        this.f2398f = str;
        this.f2399g = str2;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        g gVar = (g) obj;
        j2.i.e(gVar, "other");
        int i4 = this.f2396d - gVar.f2396d;
        return i4 == 0 ? this.f2397e - gVar.f2397e : i4;
    }
}
