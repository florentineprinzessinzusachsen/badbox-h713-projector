package androidx.core.f;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: compiled from: OneShotPreDrawListener.java */
/* JADX INFO: loaded from: classes.dex */
public final class q implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f1085a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private ViewTreeObserver f1086b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Runnable f1087c;

    private q(View view, Runnable runnable) {
        this.f1085a = view;
        this.f1086b = view.getViewTreeObserver();
        this.f1087c = runnable;
    }

    public static q a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        if (runnable == null) {
            throw new NullPointerException("runnable == null");
        }
        q qVar = new q(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(qVar);
        view.addOnAttachStateChangeListener(qVar);
        return qVar;
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public boolean onPreDraw() {
        a();
        this.f1087c.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
        this.f1086b = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }

    public void a() {
        if (this.f1086b.isAlive()) {
            this.f1086b.removeOnPreDrawListener(this);
        } else {
            this.f1085a.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        this.f1085a.removeOnAttachStateChangeListener(this);
    }
}
