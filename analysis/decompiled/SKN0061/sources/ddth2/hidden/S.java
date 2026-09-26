package ddth2.hidden;

import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public final class S implements Runnable {
    public final /* synthetic */ V a;

    public S(V v) {
        this.a = v;
    }

    @Override // java.lang.Runnable
    public final void run() {
        V v = this.a;
        Socket socket = new Socket();
        v.m = socket;
        try {
            if (v.h.get()) {
                z.a(socket);
                return;
            }
            L l = v.b;
            String str = l.b;
            int i = l.c;
            int i2 = l.i;
            v.a(socket, str, i, i2 > 0 ? i2 : 5000, true);
            v.b();
            if (v.h.get()) {
                z.a(socket);
            } else {
                v.j.set(true);
                v.a();
            }
        } catch (O e) {
            z.a(socket);
            e.toString();
            v.b();
            v.a(5, true);
        } catch (Throwable th) {
            z.a(socket);
            th.toString();
            v.b();
            v.a(1, true);
        }
    }
}
