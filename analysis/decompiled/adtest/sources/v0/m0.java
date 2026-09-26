package v0;

import java.io.IOException;
import java.net.InetAddress;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class m0 extends s0.b0 {
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (bVar.f0() != 9) {
            return InetAddress.getByName(bVar.d0());
        }
        bVar.b0();
        return null;
    }

    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        InetAddress inetAddress = (InetAddress) obj;
        dVar.a0(inetAddress == null ? null : inetAddress.getHostAddress());
    }
}
