package v0;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class x0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        try {
            int iX = bVar.X();
            if (iX <= 65535 && iX >= -32768) {
                return Short.valueOf((short) iX);
            }
            throw new s0.r("Lossy conversion from " + iX + " to short; at path " + bVar.K(true));
        } catch (NumberFormatException e4) {
            throw new s0.r(e4);
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        Number number = (Number) obj;
        if (number == null) {
            dVar.S();
        } else {
            dVar.Y(number.shortValue());
        }
    }
}
