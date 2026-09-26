package m2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f1446d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1447e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1448f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1449g;

    public b(int i4, int i5, int i6) {
        this.f1446d = i6;
        this.f1447e = i5;
        boolean z3 = false;
        if (i6 <= 0 ? i4 >= i5 : i4 <= i5) {
            z3 = true;
        }
        this.f1448f = z3;
        this.f1449g = z3 ? i4 : i5;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1448f;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i4 = this.f1449g;
        if (i4 != this.f1447e) {
            this.f1449g = this.f1446d + i4;
        } else {
            if (!this.f1448f) {
                throw new NoSuchElementException();
            }
            this.f1448f = false;
        }
        return Integer.valueOf(i4);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
