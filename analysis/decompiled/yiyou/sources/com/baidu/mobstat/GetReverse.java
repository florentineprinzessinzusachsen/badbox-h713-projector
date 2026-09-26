package com.baidu.mobstat;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class GetReverse {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static ICooperService f3361a;

    private GetReverse() {
    }

    public static ICooperService getCooperService(Context context) {
        if (f3361a == null) {
            f3361a = CooperService.instance();
        }
        return f3361a;
    }
}
