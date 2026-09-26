package v0;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class c0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        String strD0 = bVar.d0();
        if (strD0.length() == 1) {
            return Character.valueOf(strD0.charAt(0));
        }
        throw new s0.r("Expecting character, got: " + strD0 + "; at " + bVar.K(true));
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        Character ch = (Character) obj;
        dVar.a0(ch == null ? null : String.valueOf(ch));
    }
}
