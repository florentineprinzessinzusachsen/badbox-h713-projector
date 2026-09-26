package z2;

import java.util.concurrent.atomic.AtomicReferenceArray;
import w2.r;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j extends r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AtomicReferenceArray f2802e;

    public j(long j4, j jVar, int i4) {
        super(j4, jVar, i4);
        this.f2802e = new AtomicReferenceArray(i.f2801f);
    }

    @Override // w2.r
    public final int f() {
        return i.f2801f;
    }

    @Override // w2.r
    public final void g(int i4, y1.h hVar) {
        this.f2802e.set(i4, i.f2800e);
        h();
    }

    public final String toString() {
        return "SemaphoreSegment[id=" + this.f2649c + ", hashCode=" + hashCode() + ']';
    }
}
