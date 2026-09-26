package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: TintResources.java */
/* JADX INFO: loaded from: classes.dex */
class c0 extends v {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakReference<Context> f724b;

    public c0(Context context, Resources resources) {
        super(resources);
        this.f724b = new WeakReference<>(context);
    }

    @Override // androidx.appcompat.widget.v, android.content.res.Resources
    public Drawable getDrawable(int i) {
        Drawable drawable = super.getDrawable(i);
        Context context = this.f724b.get();
        if (drawable != null && context != null) {
            u.a().a(context, i, drawable);
        }
        return drawable;
    }
}
