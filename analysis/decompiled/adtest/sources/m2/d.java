package m2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final long f1451d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f1452e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f1453f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public long f1454g;

    public d(long j4, long j5, long j6) {
        this.f1451d = j6;
        this.f1452e = j5;
        boolean z3 = false;
        if (j6 <= 0 ? j4 >= j5 : j4 <= j5) {
            z3 = true;
        }
        this.f1453f = z3;
        this.f1454g = z3 ? j4 : j5;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f1453f;
    }

    @Override // java.util.Iterator
    public final Object next() {
        long j4 = this.f1454g;
        if (j4 != this.f1452e) {
            this.f1454g = this.f1451d + j4;
        } else {
            if (!this.f1453f) {
                throw new NoSuchElementException();
            }
            this.f1453f = false;
        }
        return Long.valueOf(j4);
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
