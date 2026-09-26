package com.szns.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class ar {
    static final Logger a = Logger.getLogger(ar.class.getName());

    private ar() {
    }

    public static ak a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getOutputStream() == null) {
            throw new IOException("socket's output stream == null");
        }
        au auVarC = c(socket);
        OutputStream outputStream = socket.getOutputStream();
        if (outputStream != null) {
            return new ak(auVarC, new as(auVarC, outputStream));
        }
        throw new IllegalArgumentException("out == null");
    }

    public static av a(ak akVar) {
        return new av(akVar);
    }

    public static aw a(al alVar) {
        return new aw(alVar);
    }

    static boolean a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    public static al b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        if (socket.getInputStream() == null) {
            throw new IOException("socket's input stream == null");
        }
        au auVarC = c(socket);
        InputStream inputStream = socket.getInputStream();
        if (inputStream != null) {
            return new al(auVarC, new at(auVarC, inputStream));
        }
        throw new IllegalArgumentException("in == null");
    }

    private static au c(Socket socket) {
        return new au(socket);
    }
}
