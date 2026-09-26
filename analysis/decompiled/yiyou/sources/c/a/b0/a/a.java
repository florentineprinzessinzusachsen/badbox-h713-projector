package c.a.b0.a;

import java.util.concurrent.atomic.AtomicReferenceArray;

/* JADX INFO: compiled from: ArrayCompositeDisposable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends AtomicReferenceArray<c.a.y.b> implements c.a.y.b {
    public a(int i) {
        super(i);
    }

    public boolean a(int i, c.a.y.b bVar) {
        c.a.y.b bVar2;
        do {
            bVar2 = get(i);
            if (bVar2 == c.DISPOSED) {
                bVar.dispose();
                return false;
            }
        } while (!compareAndSet(i, bVar2, bVar));
        if (bVar2 == null) {
            return true;
        }
        bVar2.dispose();
        return true;
    }

    @Override // c.a.y.b
    public void dispose() {
        c.a.y.b andSet;
        if (get(0) != c.DISPOSED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                c.a.y.b bVar = get(i);
                c cVar = c.DISPOSED;
                if (bVar != cVar && (andSet = getAndSet(i, cVar)) != c.DISPOSED && andSet != null) {
                    andSet.dispose();
                }
            }
        }
    }
}
