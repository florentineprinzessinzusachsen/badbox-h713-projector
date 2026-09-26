package q3;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final p f1854a = new p(new byte[0], 0, 0, false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int f1855b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicReference[] f1856c;

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f1855b = iHighestOneBit;
        AtomicReference[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i4 = 0; i4 < iHighestOneBit; i4++) {
            atomicReferenceArr[i4] = new AtomicReference();
        }
        f1856c = atomicReferenceArr;
    }

    public static final void a(p pVar) {
        j2.i.e(pVar, "segment");
        if (pVar.f1852f != null || pVar.f1853g != null) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (pVar.f1850d) {
            return;
        }
        AtomicReference atomicReference = f1856c[(int) (Thread.currentThread().getId() & (((long) f1855b) - 1))];
        p pVar2 = f1854a;
        p pVar3 = (p) atomicReference.getAndSet(pVar2);
        if (pVar3 == pVar2) {
            return;
        }
        int i4 = pVar3 != null ? pVar3.f1849c : 0;
        if (i4 >= 65536) {
            atomicReference.set(pVar3);
            return;
        }
        pVar.f1852f = pVar3;
        pVar.f1848b = 0;
        pVar.f1849c = i4 + 8192;
        atomicReference.set(pVar);
    }

    public static final p b() {
        AtomicReference atomicReference = f1856c[(int) (Thread.currentThread().getId() & (((long) f1855b) - 1))];
        p pVar = f1854a;
        p pVar2 = (p) atomicReference.getAndSet(pVar);
        if (pVar2 == pVar) {
            return new p();
        }
        if (pVar2 == null) {
            atomicReference.set(null);
            return new p();
        }
        atomicReference.set(pVar2.f1852f);
        pVar2.f1852f = null;
        pVar2.f1849c = 0;
        return pVar2;
    }
}
