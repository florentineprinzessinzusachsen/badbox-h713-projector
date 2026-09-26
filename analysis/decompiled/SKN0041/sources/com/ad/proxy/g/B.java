package com.ad.proxy.g;

import android.content.Intent;

/* JADX INFO: loaded from: classes.dex */
public abstract class B {
    public static boolean a() {
        try {
            new Intent().putExtra("test", "test");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
