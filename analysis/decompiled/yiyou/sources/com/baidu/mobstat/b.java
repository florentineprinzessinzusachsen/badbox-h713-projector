package com.baidu.mobstat;

import android.content.Context;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static b f3482a = new b();

    b() {
    }

    public synchronized void a(Context context) {
        String strN = bb.n(context);
        if (!TextUtils.isEmpty(strN)) {
            k.f3508a.a(System.currentTimeMillis(), strN);
        }
    }
}
