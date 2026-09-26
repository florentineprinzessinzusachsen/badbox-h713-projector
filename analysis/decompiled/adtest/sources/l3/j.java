package l3;

import javax.net.ssl.SSLSocket;
import org.conscrypt.Conscrypt;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j implements m {
    @Override // l3.m
    public final boolean a(SSLSocket sSLSocket) {
        return l.f1392b && Conscrypt.isConscrypt(sSLSocket);
    }

    @Override // l3.m
    public final o c(SSLSocket sSLSocket) {
        return new l();
    }
}
