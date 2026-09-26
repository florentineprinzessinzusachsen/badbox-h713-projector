package com.baidu.mobstat;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public final class az {
    public static boolean a(InputStream inputStream, OutputStream outputStream) {
        if (inputStream != null && outputStream != null) {
            byte[] bArr = new byte[4048];
            while (true) {
                try {
                    int i = inputStream.read(bArr);
                    if (i == -1) {
                        return true;
                    }
                    outputStream.write(bArr, 0, i);
                } catch (IOException unused) {
                }
            }
        }
        return false;
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable unused) {
            }
        }
    }
}
