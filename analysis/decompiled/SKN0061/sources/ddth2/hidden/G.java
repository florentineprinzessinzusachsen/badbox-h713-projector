package ddth2.hidden;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class G {
    public final DatagramSocket a;
    public final ScheduledExecutorService b;

    public G(ScheduledExecutorService scheduledExecutorService) {
        DatagramSocket datagramSocket;
        this.b = scheduledExecutorService;
        try {
            datagramSocket = new DatagramSocket();
        } catch (Exception unused) {
            datagramSocket = null;
        }
        this.a = datagramSocket;
    }

    public final void a(String str, int i, byte[] bArr, int i2) {
        if (this.a == null || str.length() == 0 || i <= 0 || bArr == null || bArr.length != 16) {
            if (bArr == null) {
                return;
            }
            M.a(bArr);
            return;
        }
        InetAddress inetAddressA = P.a(str);
        if (inetAddressA == null) {
            M.a(bArr);
            return;
        }
        InetSocketAddress inetSocketAddress = new InetSocketAddress(inetAddressA, i);
        byte[] bArr2 = new byte[32];
        bArr2[0] = 83;
        bArr2[1] = 70;
        bArr2[2] = 1;
        bArr2[3] = 1;
        System.arraycopy(bArr, 0, bArr2, 4, 16);
        bArr2[20] = (byte) i2;
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (int i3 = 7; i3 >= 0; i3--) {
            bArr2[24 + i3] = (byte) (255 & jCurrentTimeMillis);
            jCurrentTimeMillis >>>= 8;
        }
        M.a(bArr);
        try {
            this.a.send(new DatagramPacket(bArr2, 32, inetSocketAddress));
        } catch (Exception unused) {
        }
        ScheduledExecutorService scheduledExecutorService = this.b;
        E e = new E(this, inetSocketAddress, bArr2);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        scheduledExecutorService.schedule(e, 3L, timeUnit);
        this.b.schedule(new E(this, inetSocketAddress, bArr2), 8L, timeUnit);
    }
}
