package com.hs.p.common.utils;

import com.cloudmedia.tv.server.a;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /Users/ruben/projector-dump/downloads/plugin.jar */
public class IoUtils {
    public static void close(Closeable... closeableArr) {
        if (closeableArr != null) {
            for (Closeable closeable : closeableArr) {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Throwable unused) {
                    }
                }
            }
        }
    }

    public static byte[] getAsByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr);
                if (-1 == i) {
                    return byteArrayOutputStream.toByteArray();
                }
                if (i > 0) {
                    byteArrayOutputStream.write(bArr, 0, i);
                }
            }
        } finally {
            byteArrayOutputStream.close();
        }
    }

    public static String getAsString(InputStream inputStream, String str) throws IOException {
        return new String(getAsByteArray(inputStream), str);
    }

    public static long write(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[a.l.r];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                return j;
            }
            if (i > 0) {
                outputStream.write(bArr, 0, i);
                j += (long) i;
            }
        }
    }
}
