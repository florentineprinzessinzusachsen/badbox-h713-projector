package com.ad.proxy.b;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c0 {
    public static volatile c0 b;
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public static c0 a() {
        if (b == null) {
            synchronized (c0.class) {
                if (b == null) {
                    b = new c0();
                }
            }
        }
        return b;
    }

    public final void a(a0 a0Var, com.ad.proxy.c.A a, ArrayList arrayList, int i) {
        if (i >= arrayList.size()) {
            return;
        }
        a0 a0Var2 = (a0) arrayList.get(i);
        if (a0Var2 == a0Var) {
            a(a0Var, a, arrayList, i + 1);
        } else {
            a0Var2.e.execute(new T(a0Var2, a, new b0(this, a0Var, a, arrayList, i)));
        }
    }
}
