package v0;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class y extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        ArrayList arrayList = new ArrayList();
        bVar.b();
        while (bVar.S()) {
            try {
                arrayList.add(Integer.valueOf(bVar.X()));
            } catch (NumberFormatException e4) {
                throw new s0.r(e4);
            }
        }
        bVar.A();
        int size = arrayList.size();
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
        for (int i4 = 0; i4 < size; i4++) {
            atomicIntegerArray.set(i4, ((Integer) arrayList.get(i4)).intValue());
        }
        return atomicIntegerArray;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        AtomicIntegerArray atomicIntegerArray = (AtomicIntegerArray) obj;
        dVar.c();
        int length = atomicIntegerArray.length();
        for (int i4 = 0; i4 < length; i4++) {
            dVar.Y(atomicIntegerArray.get(i4));
        }
        dVar.A();
    }
}
