package e;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class a implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f553d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f554e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f555f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f556g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ Object f557h;

    public a(int i4) {
        this.f553d = i4;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f554e < this.f553d;
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object objH;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int i4 = this.f554e;
        switch (this.f556g) {
            case 0:
                objH = ((e) this.f557h).h(i4);
                break;
            case 1:
                objH = ((e) this.f557h).l(i4);
                break;
            default:
                objH = ((f) this.f557h).f571e[i4];
                break;
        }
        this.f554e++;
        this.f555f = true;
        return objH;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.f555f) {
            throw new IllegalStateException("Call next() before removing an element.");
        }
        int i4 = this.f554e - 1;
        this.f554e = i4;
        switch (this.f556g) {
            case 0:
                ((e) this.f557h).j(i4);
                break;
            case 1:
                ((e) this.f557h).j(i4);
                break;
            default:
                ((f) this.f557h).a(i4);
                break;
        }
        this.f553d--;
        this.f555f = false;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(f fVar) {
        this(fVar.f572f);
        this.f556g = 2;
        this.f557h = fVar;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a(e eVar, int i4) {
        this(eVar.f566f);
        this.f556g = i4;
        switch (i4) {
            case 1:
                this.f557h = eVar;
                this(eVar.f566f);
                break;
            default:
                this.f557h = eVar;
                break;
        }
    }
}
