package androidx.appcompat.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* JADX INFO: compiled from: AppCompatDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f297a = -100;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final a.b.b<WeakReference<f>> f298b = new a.b.b<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Object f299c = new Object();

    f() {
    }

    public static f a(Activity activity, e eVar) {
        return new AppCompatDelegateImpl(activity, eVar);
    }

    static void b(f fVar) {
        synchronized (f299c) {
            c(fVar);
        }
    }

    private static void c(f fVar) {
        synchronized (f299c) {
            Iterator<WeakReference<f>> it = f298b.iterator();
            while (it.hasNext()) {
                f fVar2 = it.next().get();
                if (fVar2 == fVar || fVar2 == null) {
                    it.remove();
                }
            }
        }
    }

    public static int j() {
        return f297a;
    }

    public int a() {
        return -100;
    }

    public abstract <T extends View> T a(int i);

    public void a(Context context) {
    }

    public abstract void a(Configuration configuration);

    public abstract void a(Bundle bundle);

    public abstract void a(View view);

    public abstract void a(View view, ViewGroup.LayoutParams layoutParams);

    public abstract void a(CharSequence charSequence);

    public abstract MenuInflater b();

    public abstract void b(Bundle bundle);

    public abstract void b(View view, ViewGroup.LayoutParams layoutParams);

    public abstract boolean b(int i);

    public abstract a c();

    public abstract void c(int i);

    public abstract void c(Bundle bundle);

    public abstract void d();

    public void d(int i) {
    }

    public abstract void e();

    public abstract void f();

    public abstract void g();

    public abstract void h();

    public abstract void i();

    public static f a(Dialog dialog, e eVar) {
        return new AppCompatDelegateImpl(dialog, eVar);
    }

    static void a(f fVar) {
        synchronized (f299c) {
            c(fVar);
            f298b.add(new WeakReference<>(fVar));
        }
    }
}
