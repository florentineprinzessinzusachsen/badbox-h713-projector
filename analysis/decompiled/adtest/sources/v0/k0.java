package v0;

import java.io.IOException;
import java.net.URL;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class k0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() == 9) {
            bVar.b0();
            return null;
        }
        String strD0 = bVar.d0();
        if (strD0.equals("null")) {
            return null;
        }
        return new URL(strD0);
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        URL url = (URL) obj;
        dVar.a0(url == null ? null : url.toExternalForm());
    }
}
