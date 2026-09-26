package ddth2.hidden;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;

/* JADX INFO: renamed from: ddth2.hidden.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0011k {
    public final ScheduledExecutorService a;
    public final ScheduledExecutorService b;
    public final G c;
    public final C0022w d;
    public final ConcurrentHashMap e = new ConcurrentHashMap();

    public C0011k(ScheduledExecutorService scheduledExecutorService, ScheduledExecutorService scheduledExecutorService2, G g, C0022w c0022w) {
        this.a = scheduledExecutorService;
        this.b = scheduledExecutorService2;
        this.c = g;
        this.d = c0022w;
    }

    public final void a(F f, long j, L l, int i, boolean z) {
        M.a(l.a);
        if (z) {
            this.c.a(l.f, l.g, l.a, i);
        }
        byte[] bArr = new byte[17];
        System.arraycopy(l.a, 0, bArr, 0, 16);
        bArr[16] = (byte) i;
        try {
            f.a(j, null, K.a(18, bArr));
        } catch (IOException unused) {
        }
    }
}
