package androidx.core.f;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakReference<View> f1103a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Runnable f1104b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Runnable f1105c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f1106d = -1;

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ y f1107a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1108b;

        a(x xVar, y yVar, View view) {
            this.f1107a = yVar;
            this.f1108b = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            this.f1107a.c(this.f1108b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1107a.a(this.f1108b);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            this.f1107a.b(this.f1108b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    class b implements ValueAnimator.AnimatorUpdateListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ a0 f1109a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1110b;

        b(x xVar, a0 a0Var, View view) {
            this.f1109a = a0Var;
            this.f1110b = view;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f1109a.a(this.f1110b);
        }
    }

    /* JADX INFO: compiled from: ViewPropertyAnimatorCompat.java */
    static class c implements y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        x f1111a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1112b;

        c(x xVar) {
            this.f1111a = xVar;
        }

        @Override // androidx.core.f.y
        public void a(View view) {
            int i = this.f1111a.f1106d;
            if (i > -1) {
                view.setLayerType(i, null);
                this.f1111a.f1106d = -1;
            }
            if (Build.VERSION.SDK_INT >= 16 || !this.f1112b) {
                x xVar = this.f1111a;
                Runnable runnable = xVar.f1105c;
                if (runnable != null) {
                    xVar.f1105c = null;
                    runnable.run();
                }
                Object tag = view.getTag(2113929216);
                y yVar = tag instanceof y ? (y) tag : null;
                if (yVar != null) {
                    yVar.a(view);
                }
                this.f1112b = true;
            }
        }

        @Override // androidx.core.f.y
        public void b(View view) {
            this.f1112b = false;
            if (this.f1111a.f1106d > -1) {
                view.setLayerType(2, null);
            }
            x xVar = this.f1111a;
            Runnable runnable = xVar.f1104b;
            if (runnable != null) {
                xVar.f1104b = null;
                runnable.run();
            }
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.b(view);
            }
        }

        @Override // androidx.core.f.y
        public void c(View view) {
            Object tag = view.getTag(2113929216);
            y yVar = tag instanceof y ? (y) tag : null;
            if (yVar != null) {
                yVar.c(view);
            }
        }
    }

    x(View view) {
        this.f1103a = new WeakReference<>(view);
    }

    public x a(long j) {
        View view = this.f1103a.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public x b(float f2) {
        View view = this.f1103a.get();
        if (view != null) {
            view.animate().translationY(f2);
        }
        return this;
    }

    public void c() {
        View view = this.f1103a.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public x a(float f2) {
        View view = this.f1103a.get();
        if (view != null) {
            view.animate().alpha(f2);
        }
        return this;
    }

    public long b() {
        View view = this.f1103a.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0L;
    }

    public x a(Interpolator interpolator) {
        View view = this.f1103a.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public x b(long j) {
        View view = this.f1103a.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public void a() {
        View view = this.f1103a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public x a(y yVar) {
        View view = this.f1103a.get();
        if (view != null) {
            if (Build.VERSION.SDK_INT >= 16) {
                a(view, yVar);
            } else {
                view.setTag(2113929216, yVar);
                a(view, new c(this));
            }
        }
        return this;
    }

    private void a(View view, y yVar) {
        if (yVar != null) {
            view.animate().setListener(new a(this, yVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public x a(a0 a0Var) {
        View view = this.f1103a.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            view.animate().setUpdateListener(a0Var != null ? new b(this, a0Var, view) : null);
        }
        return this;
    }
}
