package y2;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f2768b = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "lastScheduledTask$volatile");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2769c = AtomicIntegerFieldUpdater.newUpdater(m.class, "producerIndex$volatile");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2770d = AtomicIntegerFieldUpdater.newUpdater(m.class, "consumerIndex$volatile");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f2771e = AtomicIntegerFieldUpdater.newUpdater(m.class, "blockingTasksInBuffer$volatile");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReferenceArray f2772a = new AtomicReferenceArray(128);
    private volatile /* synthetic */ int blockingTasksInBuffer$volatile;
    private volatile /* synthetic */ int consumerIndex$volatile;
    private volatile /* synthetic */ Object lastScheduledTask$volatile;
    private volatile /* synthetic */ int producerIndex$volatile;

    public final i a(i iVar) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2769c;
        if (atomicIntegerFieldUpdater.get(this) - f2770d.get(this) == 127) {
            return iVar;
        }
        if (iVar.f2759e) {
            f2771e.incrementAndGet(this);
        }
        int i4 = atomicIntegerFieldUpdater.get(this) & 127;
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.f2772a;
            if (atomicReferenceArray.get(i4) == null) {
                atomicReferenceArray.lazySet(i4, iVar);
                atomicIntegerFieldUpdater.incrementAndGet(this);
                return null;
            }
            Thread.yield();
        }
    }

    public final i b() {
        i iVar;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f2770d;
            int i4 = atomicIntegerFieldUpdater.get(this);
            if (i4 - f2769c.get(this) == 0) {
                return null;
            }
            int i5 = i4 & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i4, i4 + 1) && (iVar = (i) this.f2772a.getAndSet(i5, null)) != null) {
                if (iVar.f2759e) {
                    f2771e.decrementAndGet(this);
                }
                return iVar;
            }
        }
    }

    public final i c(int i4, boolean z3) {
        int i5 = i4 & 127;
        AtomicReferenceArray atomicReferenceArray = this.f2772a;
        i iVar = (i) atomicReferenceArray.get(i5);
        if (iVar != null && iVar.f2759e == z3) {
            while (!atomicReferenceArray.compareAndSet(i5, iVar, null)) {
                if (atomicReferenceArray.get(i5) != iVar) {
                }
            }
            if (z3) {
                f2771e.decrementAndGet(this);
            }
            return iVar;
        }
        return null;
    }
}
