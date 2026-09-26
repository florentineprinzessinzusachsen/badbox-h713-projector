package v1;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends j2.a implements ListIterator {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ d f2506g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(d dVar, int i4) {
        super(2, dVar);
        this.f2506g = dVar;
        int iA = dVar.a();
        if (i4 < 0 || i4 > iA) {
            throw new IndexOutOfBoundsException(a1.c.b(i4, iA, "index: ", ", size: "));
        }
        this.f1260e = i4;
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.f1260e > 0;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return this.f1260e;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        int i4 = this.f1260e - 1;
        this.f1260e = i4;
        return this.f2506g.get(i4);
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return this.f1260e - 1;
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
