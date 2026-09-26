package q3;

import java.util.RandomAccess;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m extends v1.d implements RandomAccess {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final h[] f1839d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f1840e;

    public m(h[] hVarArr, int[] iArr) {
        this.f1839d = hVarArr;
        this.f1840e = iArr;
    }

    @Override // v1.a
    public final int a() {
        return this.f1839d.length;
    }

    @Override // v1.a, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof h) {
            return super.contains((h) obj);
        }
        return false;
    }

    @Override // java.util.List
    public final Object get(int i4) {
        return this.f1839d[i4];
    }

    @Override // v1.d, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof h) {
            return super.indexOf((h) obj);
        }
        return -1;
    }

    @Override // v1.d, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof h) {
            return super.lastIndexOf((h) obj);
        }
        return -1;
    }
}
