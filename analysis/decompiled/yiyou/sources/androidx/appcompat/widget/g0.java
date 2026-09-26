package androidx.appcompat.widget;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityManager;

/* JADX INFO: compiled from: TooltipCompatHandler.java */
/* JADX INFO: loaded from: classes.dex */
class g0 implements View.OnLongClickListener, View.OnHoverListener, View.OnAttachStateChangeListener {
    private static g0 j;
    private static g0 k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final View f758a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final CharSequence f759b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f760c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Runnable f761d = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final Runnable f762e = new b();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f763f;
    private int g;
    private h0 h;
    private boolean i;

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g0.this.a(false);
        }
    }

    /* JADX INFO: compiled from: TooltipCompatHandler.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            g0.this.a();
        }
    }

    private g0(View view, CharSequence charSequence) {
        this.f758a = view;
        this.f759b = charSequence;
        this.f760c = androidx.core.f.u.a(ViewConfiguration.get(this.f758a.getContext()));
        c();
        this.f758a.setOnLongClickListener(this);
        this.f758a.setOnHoverListener(this);
    }

    public static void a(View view, CharSequence charSequence) {
        g0 g0Var = j;
        if (g0Var != null && g0Var.f758a == view) {
            a((g0) null);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            new g0(view, charSequence);
            return;
        }
        g0 g0Var2 = k;
        if (g0Var2 != null && g0Var2.f758a == view) {
            g0Var2.a();
        }
        view.setOnLongClickListener(null);
        view.setLongClickable(false);
        view.setOnHoverListener(null);
    }

    private void b() {
        this.f758a.removeCallbacks(this.f761d);
    }

    private void c() {
        this.f763f = Integer.MAX_VALUE;
        this.g = Integer.MAX_VALUE;
    }

    private void d() {
        this.f758a.postDelayed(this.f761d, ViewConfiguration.getLongPressTimeout());
    }

    @Override // android.view.View.OnHoverListener
    public boolean onHover(View view, MotionEvent motionEvent) {
        if (this.h != null && this.i) {
            return false;
        }
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f758a.getContext().getSystemService("accessibility");
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action != 7) {
            if (action == 10) {
                c();
                a();
            }
        } else if (this.f758a.isEnabled() && this.h == null && a(motionEvent)) {
            a(this);
        }
        return false;
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.f763f = view.getWidth() / 2;
        this.g = view.getHeight() / 2;
        a(true);
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        a();
    }

    void a(boolean z) {
        long j2;
        int longPressTimeout;
        long j3;
        if (androidx.core.f.t.q(this.f758a)) {
            a((g0) null);
            g0 g0Var = k;
            if (g0Var != null) {
                g0Var.a();
            }
            k = this;
            this.i = z;
            this.h = new h0(this.f758a.getContext());
            this.h.a(this.f758a, this.f763f, this.g, this.i, this.f759b);
            this.f758a.addOnAttachStateChangeListener(this);
            if (this.i) {
                j3 = 2500;
            } else {
                if ((androidx.core.f.t.n(this.f758a) & 1) == 1) {
                    j2 = 3000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                } else {
                    j2 = 15000;
                    longPressTimeout = ViewConfiguration.getLongPressTimeout();
                }
                j3 = j2 - ((long) longPressTimeout);
            }
            this.f758a.removeCallbacks(this.f762e);
            this.f758a.postDelayed(this.f762e, j3);
        }
    }

    void a() {
        if (k == this) {
            k = null;
            h0 h0Var = this.h;
            if (h0Var != null) {
                h0Var.a();
                this.h = null;
                c();
                this.f758a.removeOnAttachStateChangeListener(this);
            } else {
                Log.e("TooltipCompatHandler", "sActiveHandler.mPopup == null");
            }
        }
        if (j == this) {
            a((g0) null);
        }
        this.f758a.removeCallbacks(this.f762e);
    }

    private static void a(g0 g0Var) {
        g0 g0Var2 = j;
        if (g0Var2 != null) {
            g0Var2.b();
        }
        j = g0Var;
        g0 g0Var3 = j;
        if (g0Var3 != null) {
            g0Var3.d();
        }
    }

    private boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (Math.abs(x - this.f763f) <= this.f760c && Math.abs(y - this.g) <= this.f760c) {
            return false;
        }
        this.f763f = x;
        this.g = y;
        return true;
    }
}
