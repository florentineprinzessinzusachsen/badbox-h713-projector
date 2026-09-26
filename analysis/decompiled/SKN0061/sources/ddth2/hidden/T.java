package ddth2.hidden;

import java.net.Socket;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: loaded from: classes.dex */
public final class T implements Runnable {
    public final /* synthetic */ V a;

    public T(V v) {
        this.a = v;
    }

    @Override // java.lang.Runnable
    public final void run() {
        V v = this.a;
        Socket socket = new Socket();
        v.n = socket;
        try {
            if (v.h.get()) {
                z.a(socket);
                return;
            }
            L l = v.b;
            String str = l.d;
            int i = l.e;
            int i2 = l.j;
            v.a(socket, str, i, i2 > 0 ? i2 : 5000, false);
            v.b();
            ScheduledExecutorService scheduledExecutorService = v.f;
            L l2 = v.b;
            byte[] bArr = l2.a;
            int i3 = l2.j;
            int i4 = i3 > 0 ? i3 : 5000;
            byte[] bArr2 = new byte[20];
            bArr2[0] = 67;
            bArr2[1] = 66;
            bArr2[2] = 1;
            bArr2[3] = 1;
            System.arraycopy(bArr, 0, bArr2, 4, 16);
            r.a(scheduledExecutorService, socket, bArr2, i4);
            v.o = true;
            v.b();
            if (v.h.get()) {
                z.a(socket);
            } else {
                v.k.set(true);
                v.a();
            }
        } catch (Throwable th) {
            z.a(socket);
            th.toString();
            v.b();
            v.a(2, true);
        }
    }
}
