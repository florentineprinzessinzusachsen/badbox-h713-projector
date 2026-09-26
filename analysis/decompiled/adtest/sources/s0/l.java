package s0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l extends b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f2093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ b0 f2094b;

    public /* synthetic */ l(b0 b0Var, int i4) {
        this.f2093a = i4;
        this.f2094b = b0Var;
    }

    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        switch (this.f2093a) {
            case 0:
                return new AtomicLong(((Number) this.f2094b.b(bVar)).longValue());
            default:
                ArrayList arrayList = new ArrayList();
                bVar.b();
                while (bVar.S()) {
                    arrayList.add(Long.valueOf(((Number) this.f2094b.b(bVar)).longValue()));
                }
                bVar.A();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i4 = 0; i4 < size; i4++) {
                    atomicLongArray.set(i4, ((Long) arrayList.get(i4)).longValue());
                }
                return atomicLongArray;
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        switch (this.f2093a) {
            case 0:
                this.f2094b.c(dVar, Long.valueOf(((AtomicLong) obj).get()));
                break;
            default:
                AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
                dVar.c();
                int length = atomicLongArray.length();
                for (int i4 = 0; i4 < length; i4++) {
                    this.f2094b.c(dVar, Long.valueOf(atomicLongArray.get(i4)));
                }
                dVar.A();
                break;
        }
    }
}
