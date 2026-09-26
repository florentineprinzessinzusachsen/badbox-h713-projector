package com.hotota.p.d.common.utils;

import java.io.Closeable;

/* JADX INFO: loaded from: classes.dex */
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
}
