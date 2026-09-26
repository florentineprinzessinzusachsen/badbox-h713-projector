package androidx.core.widget;

import android.content.res.Resources;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.f.t;

/* JADX INFO: compiled from: AutoScrollHelper.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnTouchListener {
    private static final int r = ViewConfiguration.getTapTimeout();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final View f1150c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Runnable f1151d;
    private int g;
    private int h;
    private boolean l;
    boolean m;
    boolean n;
    boolean o;
    private boolean p;
    private boolean q;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final C0025a f1148a = new C0025a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Interpolator f1149b = new AccelerateInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private float[] f1152e = {0.0f, 0.0f};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float[] f1153f = {Float.MAX_VALUE, Float.MAX_VALUE};
    private float[] i = {0.0f, 0.0f};
    private float[] j = {0.0f, 0.0f};
    private float[] k = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* JADX INFO: renamed from: androidx.core.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private static class C0025a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1154a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1155b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private float f1156c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f1157d;
        private float j;
        private int k;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private long f1158e = Long.MIN_VALUE;
        private long i = -1;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private long f1159f = 0;
        private int g = 0;
        private int h = 0;

        C0025a() {
        }

        private float a(float f2) {
            return ((-4.0f) * f2 * f2) + (f2 * 4.0f);
        }

        public void a(int i) {
            this.f1155b = i;
        }

        public void b(int i) {
            this.f1154a = i;
        }

        public int c() {
            return this.h;
        }

        public int d() {
            float f2 = this.f1156c;
            return (int) (f2 / Math.abs(f2));
        }

        public int e() {
            float f2 = this.f1157d;
            return (int) (f2 / Math.abs(f2));
        }

        public boolean f() {
            return this.i > 0 && AnimationUtils.currentAnimationTimeMillis() > this.i + ((long) this.k);
        }

        public void g() {
            long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            this.k = a.a((int) (jCurrentAnimationTimeMillis - this.f1158e), 0, this.f1155b);
            this.j = a(jCurrentAnimationTimeMillis);
            this.i = jCurrentAnimationTimeMillis;
        }

        public void h() {
            this.f1158e = AnimationUtils.currentAnimationTimeMillis();
            this.i = -1L;
            this.f1159f = this.f1158e;
            this.j = 0.5f;
            this.g = 0;
            this.h = 0;
        }

        private float a(long j) {
            if (j < this.f1158e) {
                return 0.0f;
            }
            long j2 = this.i;
            if (j2 < 0 || j < j2) {
                return a.a((j - this.f1158e) / this.f1154a, 0.0f, 1.0f) * 0.5f;
            }
            long j3 = j - j2;
            float f2 = this.j;
            return (1.0f - f2) + (f2 * a.a(j3 / this.k, 0.0f, 1.0f));
        }

        public int b() {
            return this.g;
        }

        public void a() {
            if (this.f1159f != 0) {
                long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
                float fA = a(a(jCurrentAnimationTimeMillis));
                long j = jCurrentAnimationTimeMillis - this.f1159f;
                this.f1159f = jCurrentAnimationTimeMillis;
                float f2 = j * fA;
                this.g = (int) (this.f1156c * f2);
                this.h = (int) (f2 * this.f1157d);
                return;
            }
            throw new RuntimeException("Cannot compute scroll delta before calling start()");
        }

        public void a(float f2, float f3) {
            this.f1156c = f2;
            this.f1157d = f3;
        }
    }

    /* JADX INFO: compiled from: AutoScrollHelper.java */
    private class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = a.this;
            if (aVar.o) {
                if (aVar.m) {
                    aVar.m = false;
                    aVar.f1148a.h();
                }
                C0025a c0025a = a.this.f1148a;
                if (c0025a.f() || !a.this.b()) {
                    a.this.o = false;
                    return;
                }
                a aVar2 = a.this;
                if (aVar2.n) {
                    aVar2.n = false;
                    aVar2.a();
                }
                c0025a.a();
                a.this.a(c0025a.b(), c0025a.c());
                t.a(a.this.f1150c, this);
            }
        }
    }

    public a(View view) {
        this.f1150c = view;
        float f2 = Resources.getSystem().getDisplayMetrics().density;
        float f3 = (int) ((1575.0f * f2) + 0.5f);
        b(f3, f3);
        float f4 = (int) ((f2 * 315.0f) + 0.5f);
        c(f4, f4);
        d(1);
        a(Float.MAX_VALUE, Float.MAX_VALUE);
        d(0.2f, 0.2f);
        e(1.0f, 1.0f);
        c(r);
        f(500);
        e(500);
    }

    static float a(float f2, float f3, float f4) {
        if (f2 > f4) {
            return f4;
        }
        return f2 < f3 ? f3 : f2;
    }

    static int a(int i, int i2, int i3) {
        if (i > i3) {
            return i3;
        }
        return i < i2 ? i2 : i;
    }

    public a a(boolean z) {
        if (this.p && !z) {
            c();
        }
        this.p = z;
        return this;
    }

    public abstract void a(int i, int i2);

    public abstract boolean a(int i);

    public a b(float f2, float f3) {
        float[] fArr = this.k;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public abstract boolean b(int i);

    public a c(float f2, float f3) {
        float[] fArr = this.j;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a d(int i) {
        this.g = i;
        return this;
    }

    public a e(float f2, float f3) {
        float[] fArr = this.i;
        fArr[0] = f2 / 1000.0f;
        fArr[1] = f3 / 1000.0f;
        return this;
    }

    public a f(int i) {
        this.f1148a.b(i);
        return this;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0016  */
    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!this.p) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 0) {
            if (actionMasked == 1) {
                c();
            } else if (actionMasked != 2) {
                if (actionMasked == 3) {
                    c();
                }
            }
            return this.q && this.o;
        }
        this.n = true;
        this.l = false;
        this.f1148a.a(a(0, motionEvent.getX(), view.getWidth(), this.f1150c.getWidth()), a(1, motionEvent.getY(), view.getHeight(), this.f1150c.getHeight()));
        if (!this.o && b()) {
            d();
        }
        if (this.q) {
            return false;
        }
    }

    private float f(float f2, float f3) {
        if (f3 == 0.0f) {
            return 0.0f;
        }
        int i = this.g;
        if (i == 0 || i == 1) {
            if (f2 < f3) {
                if (f2 >= 0.0f) {
                    return 1.0f - (f2 / f3);
                }
                if (this.o && this.g == 1) {
                    return 1.0f;
                }
            }
        } else if (i == 2 && f2 < 0.0f) {
            return f2 / (-f3);
        }
        return 0.0f;
    }

    public a d(float f2, float f3) {
        float[] fArr = this.f1152e;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    boolean b() {
        C0025a c0025a = this.f1148a;
        int iE = c0025a.e();
        int iD = c0025a.d();
        return (iE != 0 && b(iE)) || (iD != 0 && a(iD));
    }

    public a c(int i) {
        this.h = i;
        return this;
    }

    public a e(int i) {
        this.f1148a.a(i);
        return this;
    }

    private void c() {
        if (this.m) {
            this.o = false;
        } else {
            this.f1148a.g();
        }
    }

    private void d() {
        int i;
        if (this.f1151d == null) {
            this.f1151d = new b();
        }
        this.o = true;
        this.m = true;
        if (!this.l && (i = this.h) > 0) {
            t.a(this.f1150c, this.f1151d, i);
        } else {
            this.f1151d.run();
        }
        this.l = true;
    }

    public a a(float f2, float f3) {
        float[] fArr = this.f1153f;
        fArr[0] = f2;
        fArr[1] = f3;
        return this;
    }

    private float a(int i, float f2, float f3, float f4) {
        float fA = a(this.f1152e[i], f3, this.f1153f[i], f2);
        if (fA == 0.0f) {
            return 0.0f;
        }
        float f5 = this.i[i];
        float f6 = this.j[i];
        float f7 = this.k[i];
        float f8 = f5 * f4;
        if (fA > 0.0f) {
            return a(fA * f8, f6, f7);
        }
        return -a((-fA) * f8, f6, f7);
    }

    private float a(float f2, float f3, float f4, float f5) {
        float interpolation;
        float fA = a(f2 * f3, 0.0f, f4);
        float f6 = f(f3 - f5, fA) - f(f5, fA);
        if (f6 < 0.0f) {
            interpolation = -this.f1149b.getInterpolation(-f6);
        } else {
            if (f6 <= 0.0f) {
                return 0.0f;
            }
            interpolation = this.f1149b.getInterpolation(f6);
        }
        return a(interpolation, -1.0f, 1.0f);
    }

    void a() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
        this.f1150c.onTouchEvent(motionEventObtain);
        motionEventObtain.recycle();
    }
}
