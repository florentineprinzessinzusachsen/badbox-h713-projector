package v1;

import java.util.List;
import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c extends d implements RandomAccess {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final d f2507d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2508e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f2509f;

    public c(d dVar, int i4, int i5) {
        this.f2507d = dVar;
        this.f2508e = i4;
        a.a.g(i4, i5, dVar.a());
        this.f2509f = i5 - i4;
    }

    @Override // v1.a
    public final int a() {
        return this.f2509f;
    }

    @Override // java.util.List
    public final Object get(int i4) {
        int i5 = this.f2509f;
        if (i4 < 0 || i4 >= i5) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, i5, "index: ", ", size: "));
        }
        return this.f2507d.get(this.f2508e + i4);
    }

    @Override // v1.d, java.util.List
    public final List subList(int i4, int i5) {
        a.a.g(i4, i5, this.f2509f);
        int i6 = this.f2508e;
        return new c(this.f2507d, i4 + i6, i6 + i5);
    }
}
