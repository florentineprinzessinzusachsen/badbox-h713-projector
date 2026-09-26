package ddth2.hidden;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* JADX INFO: renamed from: ddth2.hidden.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0013m implements Runnable {
    public final InputStream a;
    public final OutputStream b;
    public final V c;
    public final boolean d;

    public RunnableC0013m(InputStream inputStream, OutputStream outputStream, V v, boolean z, String str, String str2) {
        this.a = inputStream;
        this.b = outputStream;
        this.c = v;
        this.d = z;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0033  */
    /* JADX WARN: Code duplicated, block: B:19:0x0039  */
    /* JADX WARN: Code duplicated, block: B:20:0x003c  */
    /* JADX WARN: Code duplicated, block: B:30:0x004f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0055  */
    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        Object obj;
        Object th;
        Object obj2;
        boolean z2;
        V v;
        V v2;
        Socket socket;
        byte[] bArr = new byte[8192];
        while (true) {
            z = true;
            obj = null;
            try {
                int i = this.a.read(bArr);
                if (i < 0) {
                    break;
                }
                if (i != 0) {
                    try {
                        this.b.write(bArr, 0, i);
                        this.b.flush();
                        this.c.s = System.nanoTime();
                    } catch (Throwable th2) {
                        th = th2;
                        obj2 = th;
                        z2 = true;
                        z = false;
                        if (z) {
                            v2 = this.c;
                            if (this.d) {
                                socket = v2.m;
                            } else {
                                socket = v2.n;
                            }
                            if (socket != null) {
                                try {
                                    socket.shutdownOutput();
                                } catch (Throwable unused) {
                                }
                            }
                        }
                        if (obj != null) {
                            obj.toString();
                        }
                        if (obj2 != null) {
                            obj2.toString();
                        }
                        v = this.c;
                        if (z2) {
                        }
                        v.a(0);
                    }
                }
            } catch (Throwable th3) {
                obj = th3;
                th = null;
            }
        }
        obj2 = null;
        z2 = false;
        if (z) {
            v2 = this.c;
            if (this.d) {
                socket = v2.m;
            } else {
                socket = v2.n;
            }
            if (socket != null && !socket.isClosed()) {
                socket.shutdownOutput();
            }
        }
        if (obj != null) {
            obj.toString();
        }
        if (obj2 != null) {
            obj2.toString();
        }
        v = this.c;
        if (!z2 || v.l.incrementAndGet() >= 2) {
            v.a(0);
        }
    }
}
