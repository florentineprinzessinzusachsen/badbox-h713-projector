package w1;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final f f2582d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2583e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2584f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2585g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f2586h;

    public d(f fVar, int i4) {
        this.f2586h = i4;
        j2.i.e(fVar, "map");
        this.f2582d = fVar;
        this.f2584f = -1;
        this.f2585g = fVar.f2598k;
        b();
    }

    public final void a() {
        if (this.f2582d.f2598k != this.f2585g) {
            throw new ConcurrentModificationException();
        }
    }

    public final void b() {
        while (true) {
            int i4 = this.f2583e;
            f fVar = this.f2582d;
            if (i4 >= fVar.f2596i || fVar.f2593f[i4] >= 0) {
                return;
            } else {
                this.f2583e = i4 + 1;
            }
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2583e < this.f2582d.f2596i;
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f2586h) {
            case 0:
                a();
                int i4 = this.f2583e;
                f fVar = this.f2582d;
                if (i4 >= fVar.f2596i) {
                    throw new NoSuchElementException();
                }
                this.f2583e = i4 + 1;
                this.f2584f = i4;
                e eVar = new e(fVar, i4);
                b();
                return eVar;
            case 1:
                a();
                int i5 = this.f2583e;
                f fVar2 = this.f2582d;
                if (i5 >= fVar2.f2596i) {
                    throw new NoSuchElementException();
                }
                this.f2583e = i5 + 1;
                this.f2584f = i5;
                Object obj = fVar2.f2591d[i5];
                b();
                return obj;
            default:
                a();
                int i6 = this.f2583e;
                f fVar3 = this.f2582d;
                if (i6 >= fVar3.f2596i) {
                    throw new NoSuchElementException();
                }
                this.f2583e = i6 + 1;
                this.f2584f = i6;
                Object[] objArr = fVar3.f2592e;
                j2.i.b(objArr);
                Object obj2 = objArr[this.f2584f];
                b();
                return obj2;
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        a();
        if (this.f2584f == -1) {
            throw new IllegalStateException("Call next() before removing element from the iterator.");
        }
        f fVar = this.f2582d;
        fVar.b();
        fVar.k(this.f2584f);
        this.f2584f = -1;
        this.f2585g = fVar.f2598k;
    }
}
