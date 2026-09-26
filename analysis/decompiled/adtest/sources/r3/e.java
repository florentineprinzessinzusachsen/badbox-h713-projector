package r3;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends q3.c {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Socket f2064n;

    public e(Socket socket) {
        this.f2064n = socket;
    }

    @Override // q3.c
    public final IOException j(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // q3.c
    public final void k() {
        Socket socket = this.f2064n;
        try {
            socket.close();
        } catch (AssertionError e4) {
            if (!f.a(e4)) {
                throw e4;
            }
            f.f2065a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e4);
        } catch (Exception e5) {
            f.f2065a.log(Level.WARNING, "Failed to close timed out socket " + socket, (Throwable) e5);
        }
    }
}
