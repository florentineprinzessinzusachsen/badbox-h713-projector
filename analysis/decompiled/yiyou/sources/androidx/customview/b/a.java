package androidx.customview.b;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.f.t;
import com.blankj.utilcode.constant.TimeConstants;
import java.util.Arrays;

/* JADX INFO: compiled from: ViewDragHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {
    private static final Interpolator w = new InterpolatorC0027a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f1175a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f1176b;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float[] f1178d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float[] f1179e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float[] f1180f;
    private float[] g;
    private int[] h;
    private int[] i;
    private int[] j;
    private int k;
    private VelocityTracker l;
    private float m;
    private float n;
    private int o;
    private int p;
    private OverScroller q;
    private final c r;
    private View s;
    private boolean t;
    private final ViewGroup u;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1177c = -1;
    private final Runnable v = new b();

    /* JADX INFO: renamed from: androidx.customview.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ViewDragHelper.java */
    static class InterpolatorC0027a implements Interpolator {
        InterpolatorC0027a() {
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            float f3 = f2 - 1.0f;
            return (f3 * f3 * f3 * f3 * f3) + 1.0f;
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.c(0);
        }
    }

    /* JADX INFO: compiled from: ViewDragHelper.java */
    public static abstract class c {
        public int a(int i) {
            return i;
        }

        public abstract int a(View view);

        public abstract int a(View view, int i, int i2);

        public abstract void a(int i, int i2);

        public abstract void a(View view, float f2, float f3);

        public abstract void a(View view, int i);

        public abstract void a(View view, int i, int i2, int i3, int i4);

        public int b(View view) {
            return 0;
        }

        public abstract int b(View view, int i, int i2);

        public abstract void b(int i, int i2);

        public abstract boolean b(int i);

        public abstract boolean b(View view, int i);

        public abstract void c(int i);
    }

    private a(Context context, ViewGroup viewGroup, c cVar) {
        if (viewGroup == null) {
            throw new IllegalArgumentException("Parent view may not be null");
        }
        if (cVar == null) {
            throw new IllegalArgumentException("Callback may not be null");
        }
        this.u = viewGroup;
        this.r = cVar;
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.o = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
        this.f1176b = viewConfiguration.getScaledTouchSlop();
        this.m = viewConfiguration.getScaledMaximumFlingVelocity();
        this.n = viewConfiguration.getScaledMinimumFlingVelocity();
        this.q = new OverScroller(context, w);
    }

    public static a a(ViewGroup viewGroup, c cVar) {
        return new a(viewGroup.getContext(), viewGroup, cVar);
    }

    private void f() {
        float[] fArr = this.f1178d;
        if (fArr == null) {
            return;
        }
        Arrays.fill(fArr, 0.0f);
        Arrays.fill(this.f1179e, 0.0f);
        Arrays.fill(this.f1180f, 0.0f);
        Arrays.fill(this.g, 0.0f);
        Arrays.fill(this.h, 0);
        Arrays.fill(this.i, 0);
        Arrays.fill(this.j, 0);
        this.k = 0;
    }

    private void g() {
        this.l.computeCurrentVelocity(TimeConstants.SEC, this.m);
        a(a(this.l.getXVelocity(this.f1177c), this.n, this.m), a(this.l.getYVelocity(this.f1177c), this.n, this.m));
    }

    public View b() {
        return this.s;
    }

    public int c() {
        return this.o;
    }

    public void d(int i) {
        this.p = i;
    }

    public int e() {
        return this.f1175a;
    }

    public static a a(ViewGroup viewGroup, float f2, c cVar) {
        a aVarA = a(viewGroup, cVar);
        aVarA.f1176b = (int) (aVarA.f1176b * (1.0f / f2));
        return aVarA;
    }

    private void c(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (g(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.f1180f[pointerId] = x;
                this.g[pointerId] = y;
            }
        }
    }

    private void e(int i) {
        if (this.f1178d == null || !b(i)) {
            return;
        }
        this.f1178d[i] = 0.0f;
        this.f1179e[i] = 0.0f;
        this.f1180f[i] = 0.0f;
        this.g[i] = 0.0f;
        this.h[i] = 0;
        this.i[i] = 0;
        this.j[i] = 0;
        this.k = ((1 << i) ^ (-1)) & this.k;
    }

    public boolean b(View view, int i, int i2) {
        this.s = view;
        this.f1177c = -1;
        boolean zB = b(i, i2, 0, 0);
        if (!zB && this.f1175a == 0 && this.s != null) {
            this.s = null;
        }
        return zB;
    }

    public int d() {
        return this.f1176b;
    }

    public boolean d(int i, int i2) {
        if (this.t) {
            return b(i, i2, (int) this.l.getXVelocity(this.f1177c), (int) this.l.getYVelocity(this.f1177c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    public void a(float f2) {
        this.n = f2;
    }

    public void a(View view, int i) {
        if (view.getParent() == this.u) {
            this.s = view;
            this.f1177c = i;
            this.r.a(view, i);
            c(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.u + ")");
    }

    private boolean b(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.q.abortAnimation();
            c(0);
            return false;
        }
        this.q.startScroll(left, top, i5, i6, a(this.s, i5, i6, i3, i4));
        c(2);
        return true;
    }

    private boolean g(int i) {
        if (b(i)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    void c(int i) {
        this.u.removeCallbacks(this.v);
        if (this.f1175a != i) {
            this.f1175a = i;
            this.r.c(i);
            if (this.f1175a == 0) {
                this.s = null;
            }
        }
    }

    private void f(int i) {
        float[] fArr = this.f1178d;
        if (fArr == null || fArr.length <= i) {
            int i2 = i + 1;
            float[] fArr2 = new float[i2];
            float[] fArr3 = new float[i2];
            float[] fArr4 = new float[i2];
            float[] fArr5 = new float[i2];
            int[] iArr = new int[i2];
            int[] iArr2 = new int[i2];
            int[] iArr3 = new int[i2];
            float[] fArr6 = this.f1178d;
            if (fArr6 != null) {
                System.arraycopy(fArr6, 0, fArr2, 0, fArr6.length);
                float[] fArr7 = this.f1179e;
                System.arraycopy(fArr7, 0, fArr3, 0, fArr7.length);
                float[] fArr8 = this.f1180f;
                System.arraycopy(fArr8, 0, fArr4, 0, fArr8.length);
                float[] fArr9 = this.g;
                System.arraycopy(fArr9, 0, fArr5, 0, fArr9.length);
                int[] iArr4 = this.h;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.i;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.j;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f1178d = fArr2;
            this.f1179e = fArr3;
            this.f1180f = fArr4;
            this.g = fArr5;
            this.h = iArr;
            this.i = iArr2;
            this.j = iArr3;
        }
    }

    private int e(int i, int i2) {
        int i3 = i < this.u.getLeft() + this.o ? 1 : 0;
        if (i2 < this.u.getTop() + this.o) {
            i3 |= 4;
        }
        if (i > this.u.getRight() - this.o) {
            i3 |= 2;
        }
        return i2 > this.u.getBottom() - this.o ? i3 | 8 : i3;
    }

    public void a() {
        this.f1177c = -1;
        f();
        VelocityTracker velocityTracker = this.l;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.l = null;
        }
    }

    private int b(int i, int i2, int i3) {
        int iAbs;
        if (i == 0) {
            return 0;
        }
        int width = this.u.getWidth();
        float f2 = width / 2;
        float fB = f2 + (b(Math.min(1.0f, Math.abs(i) / width)) * f2);
        int iAbs2 = Math.abs(i2);
        if (iAbs2 > 0) {
            iAbs = Math.round(Math.abs(fB / iAbs2) * 1000.0f) * 4;
        } else {
            iAbs = (int) (((Math.abs(i) / i3) + 1.0f) * 256.0f);
        }
        return Math.min(iAbs, 600);
    }

    public boolean c(int i, int i2) {
        return a(this.s, i, i2);
    }

    private int a(View view, int i, int i2, int i3, int i4) {
        float f2;
        float f3;
        float f4;
        float f5;
        int iA = a(i3, (int) this.n, (int) this.m);
        int iA2 = a(i4, (int) this.n, (int) this.m);
        int iAbs = Math.abs(i);
        int iAbs2 = Math.abs(i2);
        int iAbs3 = Math.abs(iA);
        int iAbs4 = Math.abs(iA2);
        int i5 = iAbs3 + iAbs4;
        int i6 = iAbs + iAbs2;
        if (iA != 0) {
            f2 = iAbs3;
            f3 = i5;
        } else {
            f2 = iAbs;
            f3 = i6;
        }
        float f6 = f2 / f3;
        if (iA2 != 0) {
            f4 = iAbs4;
            f5 = i5;
        } else {
            f4 = iAbs2;
            f5 = i6;
        }
        return (int) ((b(i, iA, this.r.a(view)) * f6) + (b(i2, iA2, this.r.b(view)) * (f4 / f5)));
    }

    private float b(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    private void b(float f2, float f3, int i) {
        f(i);
        float[] fArr = this.f1178d;
        this.f1180f[i] = f2;
        fArr[i] = f2;
        float[] fArr2 = this.f1179e;
        this.g[i] = f3;
        fArr2[i] = f3;
        this.h[i] = e((int) f2, (int) f3);
        this.k |= 1 << i;
    }

    private int a(int i, int i2, int i3) {
        int iAbs = Math.abs(i);
        if (iAbs < i2) {
            return 0;
        }
        if (iAbs > i3) {
            return i > 0 ? i3 : -i3;
        }
        return i;
    }

    private float a(float f2, float f3, float f4) {
        float fAbs = Math.abs(f2);
        if (fAbs < f3) {
            return 0.0f;
        }
        if (fAbs > f4) {
            return f2 > 0.0f ? f4 : -f4;
        }
        return f2;
    }

    public boolean a(boolean z) {
        if (this.f1175a == 2) {
            boolean zComputeScrollOffset = this.q.computeScrollOffset();
            int currX = this.q.getCurrX();
            int currY = this.q.getCurrY();
            int left = currX - this.s.getLeft();
            int top = currY - this.s.getTop();
            if (left != 0) {
                t.d(this.s, left);
            }
            if (top != 0) {
                t.e(this.s, top);
            }
            if (left != 0 || top != 0) {
                this.r.a(this.s, currX, currY, left, top);
            }
            if (zComputeScrollOffset && currX == this.q.getFinalX() && currY == this.q.getFinalY()) {
                this.q.abortAnimation();
                zComputeScrollOffset = false;
            }
            if (!zComputeScrollOffset) {
                if (z) {
                    this.u.post(this.v);
                } else {
                    c(0);
                }
            }
        }
        return this.f1175a == 2;
    }

    public boolean b(int i) {
        return ((1 << i) & this.k) != 0;
    }

    boolean b(View view, int i) {
        if (view == this.s && this.f1177c == i) {
            return true;
        }
        if (view == null || !this.r.b(view, i)) {
            return false;
        }
        this.f1177c = i;
        a(view, i);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:54:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ff  */
    public boolean b(MotionEvent motionEvent) {
        boolean z;
        View viewB;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                a();
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    a();
                } else if (actionMasked == 5) {
                    int pointerId = motionEvent.getPointerId(actionIndex);
                    float x = motionEvent.getX(actionIndex);
                    float y = motionEvent.getY(actionIndex);
                    b(x, y, pointerId);
                    int i = this.f1175a;
                    if (i == 0) {
                        int i2 = this.h[pointerId];
                        int i3 = this.p;
                        if ((i2 & i3) != 0) {
                            this.r.b(i2 & i3, pointerId);
                        }
                    } else if (i == 2 && (viewB = b((int) x, (int) y)) == this.s) {
                        b(viewB, pointerId);
                    }
                } else if (actionMasked == 6) {
                    e(motionEvent.getPointerId(actionIndex));
                }
            } else if (this.f1178d != null && this.f1179e != null) {
                int pointerCount = motionEvent.getPointerCount();
                for (int i4 = 0; i4 < pointerCount; i4++) {
                    int pointerId2 = motionEvent.getPointerId(i4);
                    if (g(pointerId2)) {
                        float x2 = motionEvent.getX(i4);
                        float y2 = motionEvent.getY(i4);
                        float f2 = x2 - this.f1178d[pointerId2];
                        float f3 = y2 - this.f1179e[pointerId2];
                        View viewB2 = b((int) x2, (int) y2);
                        boolean z2 = viewB2 != null && a(viewB2, f2, f3);
                        if (z2) {
                            int left = viewB2.getLeft();
                            int i5 = (int) f2;
                            int iA = this.r.a(viewB2, left + i5, i5);
                            int top = viewB2.getTop();
                            int i6 = (int) f3;
                            int iB = this.r.b(viewB2, top + i6, i6);
                            int iA2 = this.r.a(viewB2);
                            int iB2 = this.r.b(viewB2);
                            if ((iA2 == 0 || (iA2 > 0 && iA == left)) && (iB2 == 0 || (iB2 > 0 && iB == top))) {
                                break;
                            }
                            a(f2, f3, pointerId2);
                            if (this.f1175a != 1 || (z2 && b(viewB2, pointerId2))) {
                                break;
                            }
                        } else {
                            a(f2, f3, pointerId2);
                            if (this.f1175a != 1) {
                                break;
                            }
                        }
                    }
                }
                c(motionEvent);
            }
            z = false;
        } else {
            float x3 = motionEvent.getX();
            float y3 = motionEvent.getY();
            z = false;
            int pointerId3 = motionEvent.getPointerId(0);
            b(x3, y3, pointerId3);
            View viewB3 = b((int) x3, (int) y3);
            if (viewB3 == this.s && this.f1175a == 2) {
                b(viewB3, pointerId3);
            }
            int i7 = this.h[pointerId3];
            int i8 = this.p;
            if ((i7 & i8) != 0) {
                this.r.b(i7 & i8, pointerId3);
            }
        }
        if (this.f1175a == 1) {
            return true;
        }
        return z;
    }

    private void a(float f2, float f3) {
        this.t = true;
        this.r.a(this.s, f2, f3);
        this.t = false;
        if (this.f1175a == 1) {
            c(0);
        }
    }

    public void a(MotionEvent motionEvent) {
        int i;
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = motionEvent.getActionIndex();
        if (actionMasked == 0) {
            a();
        }
        if (this.l == null) {
            this.l = VelocityTracker.obtain();
        }
        this.l.addMovement(motionEvent);
        int i2 = 0;
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            int pointerId = motionEvent.getPointerId(0);
            View viewB = b((int) x, (int) y);
            b(x, y, pointerId);
            b(viewB, pointerId);
            int i3 = this.h[pointerId];
            int i4 = this.p;
            if ((i3 & i4) != 0) {
                this.r.b(i3 & i4, pointerId);
                return;
            }
            return;
        }
        if (actionMasked == 1) {
            if (this.f1175a == 1) {
                g();
            }
            a();
            return;
        }
        if (actionMasked == 2) {
            if (this.f1175a == 1) {
                if (g(this.f1177c)) {
                    int iFindPointerIndex = motionEvent.findPointerIndex(this.f1177c);
                    float x2 = motionEvent.getX(iFindPointerIndex);
                    float y2 = motionEvent.getY(iFindPointerIndex);
                    float[] fArr = this.f1180f;
                    int i5 = this.f1177c;
                    int i6 = (int) (x2 - fArr[i5]);
                    int i7 = (int) (y2 - this.g[i5]);
                    a(this.s.getLeft() + i6, this.s.getTop() + i7, i6, i7);
                    c(motionEvent);
                    return;
                }
                return;
            }
            int pointerCount = motionEvent.getPointerCount();
            while (i2 < pointerCount) {
                int pointerId2 = motionEvent.getPointerId(i2);
                if (g(pointerId2)) {
                    float x3 = motionEvent.getX(i2);
                    float y3 = motionEvent.getY(i2);
                    float f2 = x3 - this.f1178d[pointerId2];
                    float f3 = y3 - this.f1179e[pointerId2];
                    a(f2, f3, pointerId2);
                    if (this.f1175a != 1) {
                        View viewB2 = b((int) x3, (int) y3);
                        if (a(viewB2, f2, f3) && b(viewB2, pointerId2)) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                i2++;
            }
            c(motionEvent);
            return;
        }
        if (actionMasked == 3) {
            if (this.f1175a == 1) {
                a(0.0f, 0.0f);
            }
            a();
            return;
        }
        if (actionMasked == 5) {
            int pointerId3 = motionEvent.getPointerId(actionIndex);
            float x4 = motionEvent.getX(actionIndex);
            float y4 = motionEvent.getY(actionIndex);
            b(x4, y4, pointerId3);
            if (this.f1175a == 0) {
                b(b((int) x4, (int) y4), pointerId3);
                int i8 = this.h[pointerId3];
                int i9 = this.p;
                if ((i8 & i9) != 0) {
                    this.r.b(i8 & i9, pointerId3);
                    return;
                }
                return;
            }
            if (c((int) x4, (int) y4)) {
                b(this.s, pointerId3);
                return;
            }
            return;
        }
        if (actionMasked != 6) {
            return;
        }
        int pointerId4 = motionEvent.getPointerId(actionIndex);
        if (this.f1175a == 1 && pointerId4 == this.f1177c) {
            int pointerCount2 = motionEvent.getPointerCount();
            while (true) {
                if (i2 >= pointerCount2) {
                    i = -1;
                    break;
                }
                int pointerId5 = motionEvent.getPointerId(i2);
                if (pointerId5 != this.f1177c) {
                    View viewB3 = b((int) motionEvent.getX(i2), (int) motionEvent.getY(i2));
                    View view = this.s;
                    if (viewB3 == view && b(view, pointerId5)) {
                        i = this.f1177c;
                        break;
                    }
                }
                i2++;
            }
            if (i == -1) {
                g();
            }
        }
        e(pointerId4);
    }

    public View b(int i, int i2) {
        for (int childCount = this.u.getChildCount() - 1; childCount >= 0; childCount--) {
            ViewGroup viewGroup = this.u;
            this.r.a(childCount);
            View childAt = viewGroup.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    private void a(float f2, float f3, int i) {
        int i2 = a(f2, f3, i, 1) ? 1 : 0;
        if (a(f3, f2, i, 4)) {
            i2 |= 4;
        }
        if (a(f2, f3, i, 2)) {
            i2 |= 2;
        }
        if (a(f3, f2, i, 8)) {
            i2 |= 8;
        }
        if (i2 != 0) {
            int[] iArr = this.i;
            iArr[i] = iArr[i] | i2;
            this.r.a(i2, i);
        }
    }

    private boolean a(float f2, float f3, int i, int i2) {
        float fAbs = Math.abs(f2);
        float fAbs2 = Math.abs(f3);
        if ((this.h[i] & i2) != i2 || (this.p & i2) == 0 || (this.j[i] & i2) == i2 || (this.i[i] & i2) == i2) {
            return false;
        }
        int i3 = this.f1176b;
        if (fAbs <= i3 && fAbs2 <= i3) {
            return false;
        }
        if (fAbs >= fAbs2 * 0.5f || !this.r.b(i2)) {
            return (this.i[i] & i2) == 0 && fAbs > ((float) this.f1176b);
        }
        int[] iArr = this.j;
        iArr[i] = iArr[i] | i2;
        return false;
    }

    private boolean a(View view, float f2, float f3) {
        if (view == null) {
            return false;
        }
        boolean z = this.r.a(view) > 0;
        boolean z2 = this.r.b(view) > 0;
        if (z && z2) {
            float f4 = (f2 * f2) + (f3 * f3);
            int i = this.f1176b;
            return f4 > ((float) (i * i));
        }
        if (z) {
            return Math.abs(f2) > ((float) this.f1176b);
        }
        return z2 && Math.abs(f3) > ((float) this.f1176b);
    }

    public boolean a(int i) {
        int length = this.f1178d.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (a(i, i2)) {
                return true;
            }
        }
        return false;
    }

    public boolean a(int i, int i2) {
        if (!b(i2)) {
            return false;
        }
        boolean z = (i & 1) == 1;
        boolean z2 = (i & 2) == 2;
        float f2 = this.f1180f[i2] - this.f1178d[i2];
        float f3 = this.g[i2] - this.f1179e[i2];
        if (z && z2) {
            float f4 = (f2 * f2) + (f3 * f3);
            int i3 = this.f1176b;
            return f4 > ((float) (i3 * i3));
        }
        if (z) {
            return Math.abs(f2) > ((float) this.f1176b);
        }
        return z2 && Math.abs(f3) > ((float) this.f1176b);
    }

    private void a(int i, int i2, int i3, int i4) {
        int left = this.s.getLeft();
        int top = this.s.getTop();
        if (i3 != 0) {
            i = this.r.a(this.s, i, i3);
            t.d(this.s, i - left);
        }
        int i5 = i;
        if (i4 != 0) {
            i2 = this.r.b(this.s, i2, i4);
            t.e(this.s, i2 - top);
        }
        int i6 = i2;
        if (i3 == 0 && i4 == 0) {
            return;
        }
        this.r.a(this.s, i5, i6, i5 - left, i6 - top);
    }

    public boolean a(View view, int i, int i2) {
        return view != null && i >= view.getLeft() && i < view.getRight() && i2 >= view.getTop() && i2 < view.getBottom();
    }
}
