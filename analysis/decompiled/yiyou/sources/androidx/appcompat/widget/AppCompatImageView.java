package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageView extends ImageView implements androidx.core.f.s, androidx.core.widget.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f568a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f569b;

    public AppCompatImageView(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        c cVar = this.f568a;
        if (cVar != null) {
            cVar.a();
        }
        g gVar = this.f569b;
        if (gVar != null) {
            gVar.a();
        }
    }

    @Override // androidx.core.f.s
    public ColorStateList getSupportBackgroundTintList() {
        c cVar = this.f568a;
        if (cVar != null) {
            return cVar.b();
        }
        return null;
    }

    @Override // androidx.core.f.s
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        c cVar = this.f568a;
        if (cVar != null) {
            return cVar.c();
        }
        return null;
    }

    @Override // androidx.core.widget.l
    public ColorStateList getSupportImageTintList() {
        g gVar = this.f569b;
        if (gVar != null) {
            return gVar.b();
        }
        return null;
    }

    @Override // androidx.core.widget.l
    public PorterDuff.Mode getSupportImageTintMode() {
        g gVar = this.f569b;
        if (gVar != null) {
            return gVar.c();
        }
        return null;
    }

    @Override // android.widget.ImageView, android.view.View
    public boolean hasOverlappingRendering() {
        return this.f569b.d() && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        c cVar = this.f568a;
        if (cVar != null) {
            cVar.a(drawable);
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        c cVar = this.f568a;
        if (cVar != null) {
            cVar.a(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        g gVar = this.f569b;
        if (gVar != null) {
            gVar.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        g gVar = this.f569b;
        if (gVar != null) {
            gVar.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        g gVar = this.f569b;
        if (gVar != null) {
            gVar.a(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        g gVar = this.f569b;
        if (gVar != null) {
            gVar.a();
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        c cVar = this.f568a;
        if (cVar != null) {
            cVar.b(colorStateList);
        }
    }

    @Override // androidx.core.f.s
    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        c cVar = this.f568a;
        if (cVar != null) {
            cVar.a(mode);
        }
    }

    @Override // androidx.core.widget.l
    public void setSupportImageTintList(ColorStateList colorStateList) {
        g gVar = this.f569b;
        if (gVar != null) {
            gVar.a(colorStateList);
        }
    }

    @Override // androidx.core.widget.l
    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        g gVar = this.f569b;
        if (gVar != null) {
            gVar.a(mode);
        }
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppCompatImageView(Context context, AttributeSet attributeSet, int i) {
        super(a0.b(context), attributeSet, i);
        this.f568a = new c(this);
        this.f568a.a(attributeSet, i);
        this.f569b = new g(this);
        this.f569b.a(attributeSet, i);
    }
}
