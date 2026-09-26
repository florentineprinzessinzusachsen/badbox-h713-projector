package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* JADX INFO: compiled from: WrappedDrawableState.java */
/* JADX INFO: loaded from: classes.dex */
final class f extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1129a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Drawable.ConstantState f1130b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ColorStateList f1131c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    PorterDuff.Mode f1132d;

    f(f fVar) {
        this.f1131c = null;
        this.f1132d = d.g;
        if (fVar != null) {
            this.f1129a = fVar.f1129a;
            this.f1130b = fVar.f1130b;
            this.f1131c = fVar.f1131c;
            this.f1132d = fVar.f1132d;
        }
    }

    boolean a() {
        return this.f1130b != null;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public int getChangingConfigurations() {
        int i = this.f1129a;
        Drawable.ConstantState constantState = this.f1130b;
        return i | (constantState != null ? constantState.getChangingConfigurations() : 0);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable() {
        return newDrawable(null);
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        return Build.VERSION.SDK_INT >= 21 ? new e(this, resources) : new d(this, resources);
    }
}
