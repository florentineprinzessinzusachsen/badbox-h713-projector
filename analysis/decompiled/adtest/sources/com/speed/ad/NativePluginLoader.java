package com.speed.ad;

import android.content.Context;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class NativePluginLoader {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final NativePluginLoader f376a = new NativePluginLoader();

    static {
        System.loadLibrary("pluginloader");
    }

    public final native boolean prepareAndLoadPlugin(Context context, String str, String str2, String str3);

    public final native boolean prepareUpdatePayload(Context context, String str, String str2, String str3);
}
