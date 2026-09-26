package w2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import r2.h1;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class r extends b implements h1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2648d = AtomicIntegerFieldUpdater.newUpdater(r.class, "cleanedAndPointers$volatile");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f2649c;
    private volatile /* synthetic */ int cleanedAndPointers$volatile;

    public r(long j4, r rVar, int i4) {
        super(rVar);
        this.f2649c = j4;
        this.cleanedAndPointers$volatile = i4 << 16;
    }

    @Override // w2.b
    public final boolean c() {
        return f2648d.get(this) == f() && b() != null;
    }

    public final boolean e() {
        return f2648d.addAndGet(this, -65536) == f() && b() != null;
    }

    public abstract int f();

    public abstract void g(int i4, y1.h hVar);

    public final void h() {
        if (f2648d.incrementAndGet(this) == f()) {
            d();
        }
    }

    public final boolean i() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i4;
        do {
            atomicIntegerFieldUpdater = f2648d;
            i4 = atomicIntegerFieldUpdater.get(this);
            if (i4 == f() && b() != null) {
                return false;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i4, 65536 + i4));
        return true;
    }
}
