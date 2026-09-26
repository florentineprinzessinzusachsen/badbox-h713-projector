package com.hs.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

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
}
