package w1;

import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements ListIterator {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2569e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2571g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final v1.e f2572h;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2568d = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2570f = -1;

    public a(c cVar, int i4) {
        this.f2572h = cVar;
        this.f2569e = i4;
        this.f2571g = ((AbstractList) cVar).modCount;
    }

    public void a() {
        if (((AbstractList) ((b) this.f2572h).f2577h).modCount != this.f2571g) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        switch (this.f2568d) {
            case 0:
                a();
                b bVar = (b) this.f2572h;
                int i4 = this.f2569e;
                this.f2569e = i4 + 1;
                bVar.add(i4, obj);
                this.f2570f = -1;
                this.f2571g = ((AbstractList) bVar).modCount;
                break;
            default:
                b();
                c cVar = (c) this.f2572h;
                int i5 = this.f2569e;
                this.f2569e = i5 + 1;
                cVar.add(i5, obj);
                this.f2570f = -1;
                this.f2571g = ((AbstractList) cVar).modCount;
                break;
        }
    }

    public void b() {
        if (((AbstractList) ((c) this.f2572h)).modCount != this.f2571g) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        switch (this.f2568d) {
            case 0:
                return this.f2569e < ((b) this.f2572h).f2575f;
            default:
                return this.f2569e < ((c) this.f2572h).f2580e;
        }
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        switch (this.f2568d) {
            case 0:
                return this.f2569e > 0;
            default:
                return this.f2569e > 0;
        }
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        switch (this.f2568d) {
            case 0:
                a();
                int i4 = this.f2569e;
                b bVar = (b) this.f2572h;
                if (i4 >= bVar.f2575f) {
                    throw new NoSuchElementException();
                }
                this.f2569e = i4 + 1;
                this.f2570f = i4;
                return bVar.f2573d[bVar.f2574e + i4];
            default:
                b();
                int i5 = this.f2569e;
                c cVar = (c) this.f2572h;
                if (i5 >= cVar.f2580e) {
                    throw new NoSuchElementException();
                }
                this.f2569e = i5 + 1;
                this.f2570f = i5;
                return cVar.f2579d[i5];
        }
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        switch (this.f2568d) {
            case 0:
                break;
        }
        return this.f2569e;
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        switch (this.f2568d) {
            case 0:
                a();
                int i4 = this.f2569e;
                if (i4 <= 0) {
                    throw new NoSuchElementException();
                }
                int i5 = i4 - 1;
                this.f2569e = i5;
                this.f2570f = i5;
                b bVar = (b) this.f2572h;
                return bVar.f2573d[bVar.f2574e + i5];
            default:
                b();
                int i6 = this.f2569e;
                if (i6 <= 0) {
                    throw new NoSuchElementException();
                }
                int i7 = i6 - 1;
                this.f2569e = i7;
                this.f2570f = i7;
                return ((c) this.f2572h).f2579d[i7];
        }
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        int i4;
        switch (this.f2568d) {
            case 0:
                i4 = this.f2569e;
                break;
            default:
                i4 = this.f2569e;
                break;
        }
        return i4 - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        switch (this.f2568d) {
            case 0:
                b bVar = (b) this.f2572h;
                a();
                int i4 = this.f2570f;
                if (i4 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                bVar.b(i4);
                this.f2569e = this.f2570f;
                this.f2570f = -1;
                this.f2571g = ((AbstractList) bVar).modCount;
                return;
            default:
                c cVar = (c) this.f2572h;
                b();
                int i5 = this.f2570f;
                if (i5 == -1) {
                    throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
                }
                cVar.b(i5);
                this.f2569e = this.f2570f;
                this.f2570f = -1;
                this.f2571g = ((AbstractList) cVar).modCount;
                return;
        }
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        switch (this.f2568d) {
            case 0:
                a();
                int i4 = this.f2570f;
                if (i4 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((b) this.f2572h).set(i4, obj);
                return;
            default:
                b();
                int i5 = this.f2570f;
                if (i5 == -1) {
                    throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
                }
                ((c) this.f2572h).set(i5, obj);
                return;
        }
    }

    public a(b bVar, int i4) {
        this.f2572h = bVar;
        this.f2569e = i4;
        this.f2571g = ((AbstractList) bVar).modCount;
    }
}
