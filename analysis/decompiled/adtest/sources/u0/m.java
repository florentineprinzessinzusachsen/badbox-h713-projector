package u0;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public o f2258d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public o f2259e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2260f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ p f2261g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f2262h;

    public m(p pVar, int i4) {
        this.f2262h = i4;
        this.f2261g = pVar;
        this.f2258d = pVar.f2280i.f2268g;
        this.f2260f = pVar.f2279h;
    }

    public final Object a() {
        return b();
    }

    public final o b() {
        o oVar = this.f2258d;
        p pVar = this.f2261g;
        if (oVar == pVar.f2280i) {
            throw new NoSuchElementException();
        }
        if (pVar.f2279h != this.f2260f) {
            throw new ConcurrentModificationException();
        }
        this.f2258d = oVar.f2268g;
        this.f2259e = oVar;
        return oVar;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f2258d != this.f2261g.f2280i;
    }

    @Override // java.util.Iterator
    public Object next() {
        switch (this.f2262h) {
            case 1:
                return b().f2270i;
            default:
                return a();
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        o oVar = this.f2259e;
        if (oVar == null) {
            throw new IllegalStateException();
        }
        p pVar = this.f2261g;
        pVar.c(oVar, true);
        this.f2259e = null;
        this.f2260f = pVar.f2279h;
    }
}
