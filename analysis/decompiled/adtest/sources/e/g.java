package e;

import v1.i;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object[] f573a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f574b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f575c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f576d;

    public final void a(r.f fVar) {
        Object[] objArr = this.f573a;
        int i4 = this.f575c;
        objArr[i4] = fVar;
        int i5 = this.f576d & (i4 + 1);
        this.f575c = i5;
        int i6 = this.f574b;
        if (i5 == i6) {
            int length = objArr.length;
            int i7 = length - i6;
            int i8 = length << 1;
            if (i8 < 0) {
                throw new RuntimeException("Max array capacity exceeded");
            }
            Object[] objArr2 = new Object[i8];
            i.V(objArr, objArr2, 0, i6, length);
            i.V(this.f573a, objArr2, i7, 0, this.f574b);
            this.f573a = objArr2;
            this.f574b = 0;
            this.f575c = length;
            this.f576d = i8 - 1;
        }
    }
}
