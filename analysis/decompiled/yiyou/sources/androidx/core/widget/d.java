package androidx.core.widget;

import android.os.Build;
import android.widget.EdgeEffect;

/* JADX INFO: compiled from: EdgeEffectCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static void a(EdgeEffect edgeEffect, float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            edgeEffect.onPull(f2, f3);
        } else {
            edgeEffect.onPull(f2);
        }
    }
}
