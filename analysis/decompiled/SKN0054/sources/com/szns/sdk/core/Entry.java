package com.szns.sdk.core;

import android.content.Context;
import com.szns.sdk.a;
import com.szns.sdk.n;

/* JADX INFO: loaded from: classes.dex */
public final class Entry {
    private static final a INSTANCE = new a();

    private Entry() {
    }

    public static boolean active() {
        return INSTANCE.b();
    }

    public static void observe(Callback callback) {
        INSTANCE.a(callback);
    }

    public static void start(Context context, String str) {
        a aVar = INSTANCE;
        aVar.a(str);
        n.a().b();
        aVar.a(context);
    }

    public static void stop() {
        INSTANCE.a();
        n.a().c();
    }
}
