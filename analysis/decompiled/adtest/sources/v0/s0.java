package v0;

import java.io.IOException;
import java.util.BitSet;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class s0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        boolean zV;
        BitSet bitSet = new BitSet();
        bVar.b();
        int iF0 = bVar.f0();
        int i4 = 0;
        while (iF0 != 2) {
            int iA = o.e.a(iF0);
            if (iA == 5 || iA == 6) {
                int iX = bVar.X();
                if (iX == 0) {
                    zV = false;
                } else {
                    if (iX != 1) {
                        throw new s0.r("Invalid bitset value " + iX + ", expected 0 or 1; at path " + bVar.K(true));
                    }
                    zV = true;
                }
            } else {
                if (iA != 7) {
                    throw new s0.r("Invalid bitset value type: " + a1.c.h(iF0) + "; at path " + bVar.K(false));
                }
                zV = bVar.V();
            }
            if (zV) {
                bitSet.set(i4);
            }
            i4++;
            iF0 = bVar.f0();
        }
        bVar.A();
        return bitSet;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        BitSet bitSet = (BitSet) obj;
        dVar.c();
        int length = bitSet.length();
        for (int i4 = 0; i4 < length; i4++) {
            dVar.Y(bitSet.get(i4) ? 1L : 0L);
        }
        dVar.A();
    }
}
