package ddth2.hidden;

import java.net.DatagramPacket;
import java.net.InetSocketAddress;

/* JADX INFO: loaded from: classes.dex */
public final class E implements Runnable {
    public final /* synthetic */ InetSocketAddress a;
    public final /* synthetic */ byte[] b;
    public final /* synthetic */ G c;

    public E(G g, InetSocketAddress inetSocketAddress, byte[] bArr) {
        this.c = g;
        this.a = inetSocketAddress;
        this.b = bArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        G g = this.c;
        InetSocketAddress inetSocketAddress = this.a;
        byte[] bArr = this.b;
        try {
            g.a.send(new DatagramPacket(bArr, bArr.length, inetSocketAddress));
        } catch (Exception unused) {
        }
    }
}
