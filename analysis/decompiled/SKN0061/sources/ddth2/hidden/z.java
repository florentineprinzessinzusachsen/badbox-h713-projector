package ddth2.hidden;

import java.net.Socket;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {
    public static void a(Socket socket) {
        if (socket == null) {
            return;
        }
        try {
            socket.close();
        } catch (Throwable unused) {
        }
    }
}
