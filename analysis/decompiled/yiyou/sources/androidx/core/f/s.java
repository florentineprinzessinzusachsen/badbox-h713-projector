package androidx.core.f;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* JADX INFO: compiled from: TintableBackgroundView.java */
/* JADX INFO: loaded from: classes.dex */
public interface s {
    ColorStateList getSupportBackgroundTintList();

    PorterDuff.Mode getSupportBackgroundTintMode();

    void setSupportBackgroundTintList(ColorStateList colorStateList);

    void setSupportBackgroundTintMode(PorterDuff.Mode mode);
}
