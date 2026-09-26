package v0;

import java.io.IOException;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class n0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        String strD0 = bVar.d0();
        try {
            return UUID.fromString(strD0);
        } catch (IllegalArgumentException e4) {
            throw new s0.r("Failed parsing '" + strD0 + "' as UUID; at path " + bVar.K(true), e4);
        }
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        UUID uuid = (UUID) obj;
        dVar.a0(uuid == null ? null : uuid.toString());
    }
}
