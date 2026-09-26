package androidx.appcompat.b.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.SparseArray;

/* JADX INFO: compiled from: DrawableContainer.java */
/* JADX INFO: loaded from: classes.dex */
class b extends Drawable implements Drawable.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private c f343a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Rect f344b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Drawable f345c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Drawable f346d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f348f;
    private boolean h;
    private Runnable i;
    private long j;
    private long k;
    private C0007b l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f347e = 255;
    private int g = -1;

    /* JADX INFO: compiled from: DrawableContainer.java */
    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            b.this.a(true);
            b.this.invalidateSelf();
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.b.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DrawableContainer.java */
    static class C0007b implements Drawable.Callback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Drawable.Callback f350a;

        C0007b() {
        }

        public C0007b a(Drawable.Callback callback) {
            this.f350a = callback;
            return this;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            Drawable.Callback callback = this.f350a;
            if (callback != null) {
                callback.scheduleDrawable(drawable, runnable, j);
            }
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            Drawable.Callback callback = this.f350a;
            if (callback != null) {
                callback.unscheduleDrawable(drawable, runnable);
            }
        }

        public Drawable.Callback a() {
            Drawable.Callback callback = this.f350a;
            this.f350a = null;
            return callback;
        }
    }

    /* JADX INFO: compiled from: DrawableContainer.java */
    static abstract class c extends Drawable.ConstantState {
        int A;
        int B;
        boolean C;
        ColorFilter D;
        boolean E;
        ColorStateList F;
        PorterDuff.Mode G;
        boolean H;
        boolean I;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final b f351a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Resources f352b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f353c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f354d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f355e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        SparseArray<Drawable.ConstantState> f356f;
        Drawable[] g;
        int h;
        boolean i;
        boolean j;
        Rect k;
        boolean l;
        boolean m;
        int n;
        int o;
        int p;
        int q;
        boolean r;
        int s;
        boolean t;
        boolean u;
        boolean v;
        boolean w;
        boolean x;
        boolean y;
        int z;

        c(c cVar, b bVar, Resources resources) {
            this.f353c = 160;
            this.i = false;
            this.l = false;
            this.x = true;
            this.A = 0;
            this.B = 0;
            this.f351a = bVar;
            this.f352b = resources != null ? resources : cVar != null ? cVar.f352b : null;
            this.f353c = b.a(resources, cVar != null ? cVar.f353c : 0);
            if (cVar == null) {
                this.g = new Drawable[10];
                this.h = 0;
                return;
            }
            this.f354d = cVar.f354d;
            this.f355e = cVar.f355e;
            this.v = true;
            this.w = true;
            this.i = cVar.i;
            this.l = cVar.l;
            this.x = cVar.x;
            this.y = cVar.y;
            this.z = cVar.z;
            this.A = cVar.A;
            this.B = cVar.B;
            this.C = cVar.C;
            this.D = cVar.D;
            this.E = cVar.E;
            this.F = cVar.F;
            this.G = cVar.G;
            this.H = cVar.H;
            this.I = cVar.I;
            if (cVar.f353c == this.f353c) {
                if (cVar.j) {
                    this.k = new Rect(cVar.k);
                    this.j = true;
                }
                if (cVar.m) {
                    this.n = cVar.n;
                    this.o = cVar.o;
                    this.p = cVar.p;
                    this.q = cVar.q;
                    this.m = true;
                }
            }
            if (cVar.r) {
                this.s = cVar.s;
                this.r = true;
            }
            if (cVar.t) {
                this.u = cVar.u;
                this.t = true;
            }
            Drawable[] drawableArr = cVar.g;
            this.g = new Drawable[drawableArr.length];
            this.h = cVar.h;
            SparseArray<Drawable.ConstantState> sparseArray = cVar.f356f;
            if (sparseArray != null) {
                this.f356f = sparseArray.clone();
            } else {
                this.f356f = new SparseArray<>(this.h);
            }
            int i = this.h;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2] != null) {
                    Drawable.ConstantState constantState = drawableArr[i2].getConstantState();
                    if (constantState != null) {
                        this.f356f.put(i2, constantState);
                    } else {
                        this.g[i2] = drawableArr[i2];
                    }
                }
            }
        }

        private Drawable b(Drawable drawable) {
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(this.z);
            }
            Drawable drawableMutate = drawable.mutate();
            drawableMutate.setCallback(this.f351a);
            return drawableMutate;
        }

        private void n() {
            SparseArray<Drawable.ConstantState> sparseArray = this.f356f;
            if (sparseArray != null) {
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    this.g[this.f356f.keyAt(i)] = b(this.f356f.valueAt(i).newDrawable(this.f352b));
                }
                this.f356f = null;
            }
        }

        public final int a(Drawable drawable) {
            int i = this.h;
            if (i >= this.g.length) {
                a(i, i + 10);
            }
            drawable.mutate();
            drawable.setVisible(false, true);
            drawable.setCallback(this.f351a);
            this.g[i] = drawable;
            this.h++;
            this.f355e = drawable.getChangingConfigurations() | this.f355e;
            k();
            this.k = null;
            this.j = false;
            this.m = false;
            this.v = false;
            return i;
        }

        final int c() {
            return this.g.length;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                if (drawable == null) {
                    Drawable.ConstantState constantState = this.f356f.get(i2);
                    if (constantState != null && constantState.canApplyTheme()) {
                        return true;
                    }
                } else if (drawable.canApplyTheme()) {
                    return true;
                }
            }
            return false;
        }

        public final int d() {
            return this.h;
        }

        public final int e() {
            if (!this.m) {
                b();
            }
            return this.o;
        }

        public final int f() {
            if (!this.m) {
                b();
            }
            return this.q;
        }

        public final int g() {
            if (!this.m) {
                b();
            }
            return this.p;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f354d | this.f355e;
        }

        public final Rect h() {
            if (this.i) {
                return null;
            }
            if (this.k != null || this.j) {
                return this.k;
            }
            n();
            Rect rect = new Rect();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            Rect rect2 = null;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getPadding(rect)) {
                    if (rect2 == null) {
                        rect2 = new Rect(0, 0, 0, 0);
                    }
                    int i3 = rect.left;
                    if (i3 > rect2.left) {
                        rect2.left = i3;
                    }
                    int i4 = rect.top;
                    if (i4 > rect2.top) {
                        rect2.top = i4;
                    }
                    int i5 = rect.right;
                    if (i5 > rect2.right) {
                        rect2.right = i5;
                    }
                    int i6 = rect.bottom;
                    if (i6 > rect2.bottom) {
                        rect2.bottom = i6;
                    }
                }
            }
            this.j = true;
            this.k = rect2;
            return rect2;
        }

        public final int i() {
            if (!this.m) {
                b();
            }
            return this.n;
        }

        public final int j() {
            if (this.r) {
                return this.s;
            }
            n();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            int opacity = i > 0 ? drawableArr[0].getOpacity() : -2;
            for (int i2 = 1; i2 < i; i2++) {
                opacity = Drawable.resolveOpacity(opacity, drawableArr[i2].getOpacity());
            }
            this.s = opacity;
            this.r = true;
            return opacity;
        }

        void k() {
            this.r = false;
            this.t = false;
        }

        public final boolean l() {
            return this.l;
        }

        abstract void m();

        public final void c(int i) {
            this.B = i;
        }

        final boolean b(int i, int i2) {
            int i3 = this.h;
            Drawable[] drawableArr = this.g;
            boolean z = false;
            for (int i4 = 0; i4 < i3; i4++) {
                if (drawableArr[i4] != null) {
                    boolean layoutDirection = Build.VERSION.SDK_INT >= 23 ? drawableArr[i4].setLayoutDirection(i) : false;
                    if (i4 == i2) {
                        z = layoutDirection;
                    }
                }
            }
            this.z = i;
            return z;
        }

        public final void b(boolean z) {
            this.i = z;
        }

        protected void b() {
            this.m = true;
            n();
            int i = this.h;
            Drawable[] drawableArr = this.g;
            this.o = -1;
            this.n = -1;
            this.q = 0;
            this.p = 0;
            for (int i2 = 0; i2 < i; i2++) {
                Drawable drawable = drawableArr[i2];
                int intrinsicWidth = drawable.getIntrinsicWidth();
                if (intrinsicWidth > this.n) {
                    this.n = intrinsicWidth;
                }
                int intrinsicHeight = drawable.getIntrinsicHeight();
                if (intrinsicHeight > this.o) {
                    this.o = intrinsicHeight;
                }
                int minimumWidth = drawable.getMinimumWidth();
                if (minimumWidth > this.p) {
                    this.p = minimumWidth;
                }
                int minimumHeight = drawable.getMinimumHeight();
                if (minimumHeight > this.q) {
                    this.q = minimumHeight;
                }
            }
        }

        public final Drawable a(int i) {
            int iIndexOfKey;
            Drawable drawable = this.g[i];
            if (drawable != null) {
                return drawable;
            }
            SparseArray<Drawable.ConstantState> sparseArray = this.f356f;
            if (sparseArray == null || (iIndexOfKey = sparseArray.indexOfKey(i)) < 0) {
                return null;
            }
            Drawable drawableB = b(this.f356f.valueAt(iIndexOfKey).newDrawable(this.f352b));
            this.g[i] = drawableB;
            this.f356f.removeAt(iIndexOfKey);
            if (this.f356f.size() == 0) {
                this.f356f = null;
            }
            return drawableB;
        }

        final void a(Resources resources) {
            if (resources != null) {
                this.f352b = resources;
                int iA = b.a(resources, this.f353c);
                int i = this.f353c;
                this.f353c = iA;
                if (i != iA) {
                    this.m = false;
                    this.j = false;
                }
            }
        }

        public final void b(int i) {
            this.A = i;
        }

        final void a(Resources.Theme theme) {
            if (theme != null) {
                n();
                int i = this.h;
                Drawable[] drawableArr = this.g;
                for (int i2 = 0; i2 < i; i2++) {
                    if (drawableArr[i2] != null && drawableArr[i2].canApplyTheme()) {
                        drawableArr[i2].applyTheme(theme);
                        this.f355e |= drawableArr[i2].getChangingConfigurations();
                    }
                }
                a(theme.getResources());
            }
        }

        public final void a(boolean z) {
            this.l = z;
        }

        public void a(int i, int i2) {
            Drawable[] drawableArr = new Drawable[i2];
            System.arraycopy(this.g, 0, drawableArr, 0, i);
            this.g = drawableArr;
        }

        public synchronized boolean a() {
            if (this.v) {
                return this.w;
            }
            n();
            this.v = true;
            int i = this.h;
            Drawable[] drawableArr = this.g;
            for (int i2 = 0; i2 < i; i2++) {
                if (drawableArr[i2].getConstantState() == null) {
                    this.w = false;
                    return false;
                }
            }
            this.w = true;
            return true;
        }
    }

    b() {
    }

    private boolean c() {
        return isAutoMirrored() && androidx.core.graphics.drawable.a.d(this) == 1;
    }

    c a() {
        throw null;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0055  */
    boolean a(int i) {
        if (i == this.g) {
            return false;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.f343a.B > 0) {
            Drawable drawable = this.f346d;
            if (drawable != null) {
                drawable.setVisible(false, false);
            }
            Drawable drawable2 = this.f345c;
            if (drawable2 != null) {
                this.f346d = drawable2;
                this.k = ((long) this.f343a.B) + jUptimeMillis;
            } else {
                this.f346d = null;
                this.k = 0L;
            }
        } else {
            Drawable drawable3 = this.f345c;
            if (drawable3 != null) {
                drawable3.setVisible(false, false);
            }
        }
        if (i >= 0) {
            c cVar = this.f343a;
            if (i < cVar.h) {
                Drawable drawableA = cVar.a(i);
                this.f345c = drawableA;
                this.g = i;
                if (drawableA != null) {
                    int i2 = this.f343a.A;
                    if (i2 > 0) {
                        this.j = jUptimeMillis + ((long) i2);
                    }
                    a(drawableA);
                }
            } else {
                this.f345c = null;
                this.g = -1;
            }
        } else {
            this.f345c = null;
            this.g = -1;
        }
        if (this.j != 0 || this.k != 0) {
            Runnable runnable = this.i;
            if (runnable == null) {
                this.i = new a();
            } else {
                unscheduleSelf(runnable);
            }
            a(true);
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        this.f343a.a(theme);
    }

    int b() {
        return this.g;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        return this.f343a.canApplyTheme();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f345c;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f346d;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.f347e;
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        return super.getChangingConfigurations() | this.f343a.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        if (!this.f343a.a()) {
            return null;
        }
        this.f343a.f354d = getChangingConfigurations();
        return this.f343a;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        return this.f345c;
    }

    @Override // android.graphics.drawable.Drawable
    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f344b;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        if (this.f343a.l()) {
            return this.f343a.e();
        }
        Drawable drawable = this.f345c;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        if (this.f343a.l()) {
            return this.f343a.i();
        }
        Drawable drawable = this.f345c;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        if (this.f343a.l()) {
            return this.f343a.f();
        }
        Drawable drawable = this.f345c;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        if (this.f343a.l()) {
            return this.f343a.g();
        }
        Drawable drawable = this.f345c;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f345c;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f343a.j();
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        Drawable drawable = this.f345c;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        boolean padding;
        Rect rectH = this.f343a.h();
        if (rectH != null) {
            rect.set(rectH);
            padding = (rectH.right | ((rectH.left | rectH.top) | rectH.bottom)) != 0;
        } else {
            Drawable drawable = this.f345c;
            padding = drawable != null ? drawable.getPadding(rect) : super.getPadding(rect);
        }
        if (c()) {
            int i = rect.left;
            rect.left = rect.right;
            rect.right = i;
        }
        return padding;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        c cVar = this.f343a;
        if (cVar != null) {
            cVar.k();
        }
        if (drawable != this.f345c || getCallback() == null) {
            return;
        }
        getCallback().invalidateDrawable(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        return this.f343a.C;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.f346d;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f346d = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f345c;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f348f) {
                this.f345c.setAlpha(this.f347e);
            }
        }
        if (this.k != 0) {
            this.k = 0L;
            z = true;
        }
        if (this.j != 0) {
            this.j = 0L;
            z = true;
        }
        if (z) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.h && super.mutate() == this) {
            c cVarA = a();
            cVarA.m();
            a(cVarA);
            this.h = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f346d;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f345c;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int i) {
        return this.f343a.b(i, b());
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.f346d;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.f345c;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f346d;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f345c;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable != this.f345c || getCallback() == null) {
            return;
        }
        getCallback().scheduleDrawable(this, runnable, j);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        if (this.f348f && this.f347e == i) {
            return;
        }
        this.f348f = true;
        this.f347e = i;
        Drawable drawable = this.f345c;
        if (drawable != null) {
            if (this.j == 0) {
                drawable.setAlpha(i);
            } else {
                a(false);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        c cVar = this.f343a;
        if (cVar.C != z) {
            cVar.C = z;
            Drawable drawable = this.f345c;
            if (drawable != null) {
                androidx.core.graphics.drawable.a.a(drawable, cVar.C);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        c cVar = this.f343a;
        cVar.E = true;
        if (cVar.D != colorFilter) {
            cVar.D = colorFilter;
            Drawable drawable = this.f345c;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        c cVar = this.f343a;
        if (cVar.x != z) {
            cVar.x = z;
            Drawable drawable = this.f345c;
            if (drawable != null) {
                drawable.setDither(cVar.x);
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f2, float f3) {
        Drawable drawable = this.f345c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, f2, f3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.f344b;
        if (rect == null) {
            this.f344b = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.f345c;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, i, i2, i3, i4);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        c cVar = this.f343a;
        cVar.H = true;
        if (cVar.F != colorStateList) {
            cVar.F = colorStateList;
            androidx.core.graphics.drawable.a.a(this.f345c, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        c cVar = this.f343a;
        cVar.I = true;
        if (cVar.G != mode) {
            cVar.G = mode;
            androidx.core.graphics.drawable.a.a(this.f345c, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.f346d;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f345c;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable != this.f345c || getCallback() == null) {
            return;
        }
        getCallback().unscheduleDrawable(this, runnable);
    }

    private void a(Drawable drawable) {
        if (this.l == null) {
            this.l = new C0007b();
        }
        C0007b c0007b = this.l;
        c0007b.a(drawable.getCallback());
        drawable.setCallback(c0007b);
        try {
            if (this.f343a.A <= 0 && this.f348f) {
                drawable.setAlpha(this.f347e);
            }
            if (this.f343a.E) {
                drawable.setColorFilter(this.f343a.D);
            } else {
                if (this.f343a.H) {
                    androidx.core.graphics.drawable.a.a(drawable, this.f343a.F);
                }
                if (this.f343a.I) {
                    androidx.core.graphics.drawable.a.a(drawable, this.f343a.G);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f343a.x);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            if (Build.VERSION.SDK_INT >= 23) {
                drawable.setLayoutDirection(getLayoutDirection());
            }
            if (Build.VERSION.SDK_INT >= 19) {
                drawable.setAutoMirrored(this.f343a.C);
            }
            Rect rect = this.f344b;
            if (Build.VERSION.SDK_INT >= 21 && rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.l.a());
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x003f  */
    /* JADX WARN: Code duplicated, block: B:16:0x0045  */
    /* JADX WARN: Code duplicated, block: B:18:0x0049  */
    /* JADX WARN: Code duplicated, block: B:19:0x0052  */
    /* JADX WARN: Code duplicated, block: B:20:0x0065  */
    /* JADX WARN: Code duplicated, block: B:23:0x006a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:26:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    void a(boolean z) {
        boolean z2;
        Drawable drawable;
        long j;
        boolean z3 = true;
        this.f348f = true;
        long jUptimeMillis = SystemClock.uptimeMillis();
        Drawable drawable2 = this.f345c;
        if (drawable2 != null) {
            long j2 = this.j;
            if (j2 != 0) {
                if (j2 <= jUptimeMillis) {
                    drawable2.setAlpha(this.f347e);
                    this.j = 0L;
                } else {
                    drawable2.setAlpha(((255 - (((int) ((j2 - jUptimeMillis) * 255)) / this.f343a.A)) * this.f347e) / 255);
                    z2 = true;
                }
            }
            drawable = this.f346d;
            if (drawable != null) {
                j = this.k;
                if (j == 0) {
                    if (j <= jUptimeMillis) {
                        drawable.setVisible(false, false);
                        this.f346d = null;
                        this.k = 0L;
                    } else {
                        drawable.setAlpha(((((int) ((j - jUptimeMillis) * 255)) / this.f343a.B) * this.f347e) / 255);
                    }
                }
                if (z || !z3) {
                }
                scheduleSelf(this.i, jUptimeMillis + 16);
                return;
            }
            this.k = 0L;
            z3 = z2;
            if (z) {
            }
        }
        this.j = 0L;
        z2 = false;
        drawable = this.f346d;
        if (drawable != null) {
            j = this.k;
            if (j == 0) {
                if (j <= jUptimeMillis) {
                    drawable.setVisible(false, false);
                    this.f346d = null;
                    this.k = 0L;
                } else {
                    drawable.setAlpha(((((int) ((j - jUptimeMillis) * 255)) / this.f343a.B) * this.f347e) / 255);
                }
            }
            if (z) {
            }
        }
        this.k = 0L;
        z3 = z2;
        if (z) {
        }
    }

    final void a(Resources resources) {
        this.f343a.a(resources);
    }

    void a(c cVar) {
        this.f343a = cVar;
        int i = this.g;
        if (i >= 0) {
            this.f345c = cVar.a(i);
            Drawable drawable = this.f345c;
            if (drawable != null) {
                a(drawable);
            }
        }
        this.f346d = null;
    }

    static int a(Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        if (i == 0) {
            return 160;
        }
        return i;
    }
}
