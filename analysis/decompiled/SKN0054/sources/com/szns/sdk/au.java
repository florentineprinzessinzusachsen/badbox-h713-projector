package com.szns.sdk;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
final class au extends aj {
    final /* synthetic */ Socket b;

    au(Socket socket) {
        this.b = socket;
    }

    @Override // com.szns.sdk.aj
    protected final IOException b(@Nullable IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // com.szns.sdk.aj
    protected final void b() {
        Level level;
        StringBuilder sb;
        Logger logger;
        Throwable th;
        try {
            this.b.close();
        } catch (AssertionError e) {
            if (!ar.a(e)) {
                throw e;
            }
            Logger logger2 = ar.a;
            level = Level.WARNING;
            sb = new StringBuilder("Failed to close timed out socket ");
            th = e;
            logger = logger2;
            sb.append(this.b);
            logger.log(level, sb.toString(), th);
        } catch (Exception e2) {
            Logger logger3 = ar.a;
            level = Level.WARNING;
            sb = new StringBuilder("Failed to close timed out socket ");
            th = e2;
            logger = logger3;
            sb.append(this.b);
            logger.log(level, sb.toString(), th);
        }
    }
}
