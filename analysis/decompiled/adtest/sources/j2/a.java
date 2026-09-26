package j2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class a implements Iterator {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f1259d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1260e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f1261f;

    public /* synthetic */ a(int i4, Object obj) {
        this.f1259d = i4;
        this.f1261f = obj;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.f1259d) {
            case 0:
                return this.f1260e < ((Object[]) this.f1261f).length;
            case 1:
                Iterator it = (Iterator) this.f1261f;
                while (this.f1260e > 0 && it.hasNext()) {
                    it.next();
                    this.f1260e--;
                }
                return it.hasNext();
            default:
                return this.f1260e < ((v1.d) this.f1261f).a();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.f1259d) {
            case 0:
                try {
                    Object[] objArr = (Object[]) this.f1261f;
                    int i4 = this.f1260e;
                    this.f1260e = i4 + 1;
                    return objArr[i4];
                } catch (ArrayIndexOutOfBoundsException e4) {
                    this.f1260e--;
                    throw new NoSuchElementException(e4.getMessage());
                }
            case 1:
                Iterator it = (Iterator) this.f1261f;
                while (this.f1260e > 0 && it.hasNext()) {
                    it.next();
                    this.f1260e--;
                }
                return it.next();
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                v1.d dVar = (v1.d) this.f1261f;
                int i5 = this.f1260e;
                this.f1260e = i5 + 1;
                return dVar.get(i5);
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.f1259d) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            case 1:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public a(o2.b bVar) {
        this.f1259d = 1;
        this.f1261f = bVar.f1562a.iterator();
        this.f1260e = bVar.f1563b;
    }
}
