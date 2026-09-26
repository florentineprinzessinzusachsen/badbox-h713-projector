package o2;

import d0.l0;
import java.util.Iterator;
import java.util.NoSuchElementException;
import r2.d1;
import u1.k;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterator, y1.c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1564d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public d1 f1565e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public y1.c f1566f;

    public final RuntimeException a() {
        int i4 = this.f1564d;
        if (i4 == 4) {
            return new NoSuchElementException();
        }
        if (i4 == 5) {
            return new IllegalStateException("Iterator has failed.");
        }
        return new IllegalStateException("Unexpected state of the iterator: " + this.f1564d);
    }

    @Override // y1.c
    public final y1.h g() {
        return y1.i.f2726d;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        int i4;
        while (true) {
            i4 = this.f1564d;
            if (i4 != 0) {
                break;
            }
            this.f1564d = 5;
            y1.c cVar = this.f1566f;
            j2.i.b(cVar);
            this.f1566f = null;
            cVar.j(k.f2301a);
        }
        if (i4 == 1) {
            j2.i.b(null);
            throw null;
        }
        if (i4 == 2 || i4 == 3) {
            return true;
        }
        if (i4 == 4) {
            return false;
        }
        throw a();
    }

    @Override // y1.c
    public final void j(Object obj) {
        l0.M(obj);
        this.f1564d = 4;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i4 = this.f1564d;
        if (i4 == 0 || i4 == 1) {
            if (hasNext()) {
                return next();
            }
            throw new NoSuchElementException();
        }
        if (i4 == 2) {
            this.f1564d = 1;
            j2.i.b(null);
            throw null;
        }
        if (i4 != 3) {
            throw a();
        }
        this.f1564d = 0;
        d1 d1Var = this.f1565e;
        this.f1565e = null;
        return d1Var;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
