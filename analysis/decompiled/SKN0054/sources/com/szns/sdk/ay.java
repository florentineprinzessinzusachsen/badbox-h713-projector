package com.szns.sdk;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
final class ay {

    @Nullable
    static ax a;
    static long b;

    private ay() {
    }

    static ax a() {
        synchronized (ay.class) {
            ax axVar = a;
            if (axVar == null) {
                return new ax();
            }
            a = axVar.f;
            axVar.f = null;
            b -= 8192;
            return axVar;
        }
    }

    static void a(ax axVar) {
        if (axVar.f != null || axVar.g != null) {
            throw new IllegalArgumentException();
        }
        if (axVar.d) {
            return;
        }
        synchronized (ay.class) {
            long j = b;
            if (j + 8192 > 65536) {
                return;
            }
            b = j + 8192;
            axVar.f = a;
            axVar.c = 0;
            axVar.b = 0;
            a = axVar;
        }
    }
}
