package p2;

import d0.l0;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1743d = -1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1744e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1745f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m2.c f1746g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1747h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ c f1748i;

    public b(c cVar) {
        this.f1748i = cVar;
        int length = cVar.f1749a.length();
        if (length < 0) {
            throw new IllegalArgumentException(a1.c.d(length, "Cannot coerce value to an empty range: maximum ", " is less than minimum 0."));
        }
        length = length >= 0 ? 0 : length;
        this.f1744e = length;
        this.f1745f = length;
    }

    /* JADX WARN: Code duplicated, block: B:10:0x001a  */
    /* JADX WARN: Code duplicated, block: B:12:0x0022 A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Code duplicated, block: B:18:0x0075  */
    public final void a() {
        u1.f fVar;
        int i4 = this.f1745f;
        if (i4 < 0) {
            this.f1743d = 0;
            this.f1746g = null;
            return;
        }
        c cVar = this.f1748i;
        int i5 = cVar.f1750b;
        if (i5 > 0) {
            int i6 = this.f1747h + 1;
            this.f1747h = i6;
            if (i6 >= i5) {
                this.f1746g = new m2.c(this.f1744e, i.C0(cVar.f1749a), 1);
                this.f1745f = -1;
            } else if (i4 > cVar.f1749a.length() && (fVar = (u1.f) cVar.f1751c.f(cVar.f1749a, Integer.valueOf(this.f1745f))) != null) {
                int iIntValue = ((Number) fVar.f2294d).intValue();
                int iIntValue2 = ((Number) fVar.f2295e).intValue();
                this.f1746g = l0.Q(this.f1744e, iIntValue);
                int i7 = iIntValue + iIntValue2;
                this.f1744e = i7;
                this.f1745f = i7 + (iIntValue2 == 0 ? 1 : 0);
            } else {
                this.f1746g = new m2.c(this.f1744e, i.C0(cVar.f1749a), 1);
                this.f1745f = -1;
            }
        } else if (i4 > cVar.f1749a.length()) {
            this.f1746g = new m2.c(this.f1744e, i.C0(cVar.f1749a), 1);
            this.f1745f = -1;
        } else {
            int iIntValue3 = ((Number) fVar.f2294d).intValue();
            int iIntValue4 = ((Number) fVar.f2295e).intValue();
            this.f1746g = l0.Q(this.f1744e, iIntValue3);
            int i8 = iIntValue3 + iIntValue4;
            this.f1744e = i8;
            this.f1745f = i8 + (iIntValue4 == 0 ? 1 : 0);
        }
        this.f1743d = 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f1743d == -1) {
            a();
        }
        return this.f1743d == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f1743d == -1) {
            a();
        }
        if (this.f1743d == 0) {
            throw new NoSuchElementException();
        }
        m2.c cVar = this.f1746g;
        j2.i.c(cVar, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.f1746g = null;
        this.f1743d = -1;
        return cVar;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
