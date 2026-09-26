package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* JADX INFO: compiled from: TintAwareDrawable.java */
/* JADX INFO: loaded from: classes.dex */
public interface b {
    void setTint(int i);

    void setTintList(ColorStateList colorStateList);

    void setTintMode(PorterDuff.Mode mode);
}
