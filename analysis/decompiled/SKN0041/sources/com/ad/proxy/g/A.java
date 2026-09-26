package com.ad.proxy.g;

import java.security.SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public abstract class A {
    public static final Object a = new Object();
    public static final SecureRandom b = new SecureRandom();

    public static String a() {
        String strA;
        String strA2 = J.a();
        if (strA2 != null && strA2.length() != 0) {
            return strA2;
        }
        synchronized (a) {
            strA = J.a();
            if (strA == null || strA.length() == 0) {
                strA = String.format("%016x", Long.valueOf((System.currentTimeMillis() << 22) | ((long) b.nextInt(4194304))));
                J.a(strA);
            }
        }
        return strA;
    }
}
