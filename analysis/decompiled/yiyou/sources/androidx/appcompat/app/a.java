package androidx.appcompat.app;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: ActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: compiled from: ActionBar.java */
    public interface b {
        void a(boolean z);
    }

    /* JADX INFO: compiled from: ActionBar.java */
    @Deprecated
    public static abstract class c {
        public abstract CharSequence a();

        public abstract View b();

        public abstract Drawable c();

        public abstract CharSequence d();

        public abstract void e();
    }

    public abstract androidx.appcompat.d.b a(androidx.appcompat.d.b.a aVar);

    public abstract void a(Configuration configuration);

    public abstract void a(CharSequence charSequence);

    public abstract boolean a(int i, KeyEvent keyEvent);

    public boolean a(KeyEvent keyEvent) {
        return false;
    }

    public abstract void b(boolean z);

    public abstract void c(boolean z);

    public abstract void d(boolean z);

    public boolean e() {
        return false;
    }

    public abstract boolean f();

    public abstract int g();

    public abstract Context h();

    public boolean i() {
        return false;
    }

    void j() {
    }

    public boolean k() {
        return false;
    }

    /* JADX INFO: renamed from: androidx.appcompat.app.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionBar.java */
    public static class C0005a extends ViewGroup.MarginLayoutParams {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public int f293a;

        public C0005a(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.f293a = 0;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionBarLayout);
            this.f293a = typedArrayObtainStyledAttributes.getInt(R$styleable.ActionBarLayout_android_layout_gravity, 0);
            typedArrayObtainStyledAttributes.recycle();
        }

        public C0005a(int i, int i2) {
            super(i, i2);
            this.f293a = 0;
            this.f293a = 8388627;
        }

        public C0005a(C0005a c0005a) {
            super((ViewGroup.MarginLayoutParams) c0005a);
            this.f293a = 0;
            this.f293a = c0005a.f293a;
        }

        public C0005a(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.f293a = 0;
        }
    }
}
