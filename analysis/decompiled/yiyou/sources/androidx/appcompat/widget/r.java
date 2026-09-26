package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;

/* JADX INFO: compiled from: ForwardingListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class r implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float f806a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f807b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f808c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final View f809d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Runnable f810e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Runnable f811f;
    private boolean g;
    private int h;
    private final int[] i = new int[2];

    /* JADX INFO: compiled from: ForwardingListener.java */
    private class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ViewParent parent = r.this.f809d.getParent();
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
            }
        }
    }

    /* JADX INFO: compiled from: ForwardingListener.java */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            r.this.d();
        }
    }

    public r(View view) {
        this.f809d = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f806a = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        this.f807b = ViewConfiguration.getTapTimeout();
        this.f808c = (this.f807b + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private boolean a(MotionEvent motionEvent) {
        DropDownListView dropDownListView;
        View view = this.f809d;
        androidx.appcompat.view.menu.p pVarA = a();
        if (pVarA == null || !pVarA.b() || (dropDownListView = (DropDownListView) pVarA.f()) == null || !dropDownListView.isShown()) {
            return false;
        }
        MotionEvent motionEventObtainNoHistory = MotionEvent.obtainNoHistory(motionEvent);
        a(view, motionEventObtainNoHistory);
        b(dropDownListView, motionEventObtainNoHistory);
        boolean zA = dropDownListView.a(motionEventObtainNoHistory, this.h);
        motionEventObtainNoHistory.recycle();
        int actionMasked = motionEvent.getActionMasked();
        return zA && (actionMasked != 1 && actionMasked != 3);
    }

    /* JADX WARN: Code duplicated, block: B:20:0x003d  */
    private boolean b(MotionEvent motionEvent) {
        View view = this.f809d;
        if (!view.isEnabled()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.h = motionEvent.getPointerId(0);
            if (this.f810e == null) {
                this.f810e = new a();
            }
            view.postDelayed(this.f810e, this.f807b);
            if (this.f811f == null) {
                this.f811f = new b();
            }
            view.postDelayed(this.f811f, this.f808c);
        } else if (actionMasked == 1) {
            e();
        } else if (actionMasked == 2) {
            int iFindPointerIndex = motionEvent.findPointerIndex(this.h);
            if (iFindPointerIndex >= 0 && !a(view, motionEvent.getX(iFindPointerIndex), motionEvent.getY(iFindPointerIndex), this.f806a)) {
                e();
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            }
        } else if (actionMasked == 3) {
            e();
        }
        return false;
    }

    private void e() {
        Runnable runnable = this.f811f;
        if (runnable != null) {
            this.f809d.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f810e;
        if (runnable2 != null) {
            this.f809d.removeCallbacks(runnable2);
        }
    }

    public abstract androidx.appcompat.view.menu.p a();

    protected abstract boolean b();

    protected boolean c() {
        androidx.appcompat.view.menu.p pVarA = a();
        if (pVarA == null || !pVarA.b()) {
            return true;
        }
        pVarA.dismiss();
        return true;
    }

    void d() {
        e();
        View view = this.f809d;
        if (view.isEnabled() && !view.isLongClickable() && b()) {
            view.getParent().requestDisallowInterceptTouchEvent(true);
            long jUptimeMillis = SystemClock.uptimeMillis();
            MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
            view.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
            this.g = true;
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        boolean z;
        boolean z2 = this.g;
        if (z2) {
            z = a(motionEvent) || !c();
        } else {
            z = b(motionEvent) && b();
            if (z) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                this.f809d.onTouchEvent(motionEventObtain);
                motionEventObtain.recycle();
            }
        }
        this.g = z;
        return z || z2;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View view) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View view) {
        this.g = false;
        this.h = -1;
        Runnable runnable = this.f810e;
        if (runnable != null) {
            this.f809d.removeCallbacks(runnable);
        }
    }

    private static boolean a(View view, float f2, float f3, float f4) {
        float f5 = -f4;
        return f2 >= f5 && f3 >= f5 && f2 < ((float) (view.getRight() - view.getLeft())) + f4 && f3 < ((float) (view.getBottom() - view.getTop())) + f4;
    }

    private boolean a(View view, MotionEvent motionEvent) {
        int[] iArr = this.i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation(iArr[0], iArr[1]);
        return true;
    }

    private boolean b(View view, MotionEvent motionEvent) {
        int[] iArr = this.i;
        view.getLocationOnScreen(iArr);
        motionEvent.offsetLocation(-iArr[0], -iArr[1]);
        return true;
    }
}
