package v0;

import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class u0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        int iF0 = bVar.f0();
        if (iF0 != 9) {
            return iF0 == 6 ? Boolean.valueOf(Boolean.parseBoolean(bVar.d0())) : Boolean.valueOf(bVar.V());
        }
        bVar.b0();
        return null;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        Boolean bool = (Boolean) obj;
        if (bool == null) {
            dVar.S();
            return;
        }
        dVar.c0();
        dVar.b();
        dVar.f29d.write(bool.booleanValue() ? "true" : "false");
    }
}
