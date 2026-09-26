package androidx.vectordrawable.a.a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import com.baidu.mobstat.Config;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: VectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends androidx.vectordrawable.a.a.h {
    static final PorterDuff.Mode j = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private h f1443b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PorterDuffColorFilter f1444c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ColorFilter f1445d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1446e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1447f;
    private final float[] g;
    private final Matrix h;
    private final Rect i;

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class b extends f {
        b() {
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (androidx.core.content.c.g.a(xmlPullParser, "pathData")) {
                TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1424d);
                a(typedArrayA, xmlPullParser);
                typedArrayA.recycle();
            }
        }

        @Override // androidx.vectordrawable.a.a.i.f
        public boolean b() {
            return true;
        }

        b(b bVar) {
            super(bVar);
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f1457b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f1456a = androidx.core.a.b.a(string2);
            }
            this.f1458c = androidx.core.content.c.g.b(typedArray, xmlPullParser, "fillType", 2, 0);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static abstract class e {
        private e() {
        }

        public boolean a() {
            return false;
        }

        public boolean a(int[] iArr) {
            return false;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1466a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        g f1467b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ColorStateList f1468c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        PorterDuff.Mode f1469d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        boolean f1470e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        Bitmap f1471f;
        ColorStateList g;
        PorterDuff.Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public h(h hVar) {
            this.f1468c = null;
            this.f1469d = i.j;
            if (hVar != null) {
                this.f1466a = hVar.f1466a;
                this.f1467b = new g(hVar.f1467b);
                Paint paint = hVar.f1467b.f1464e;
                if (paint != null) {
                    this.f1467b.f1464e = new Paint(paint);
                }
                Paint paint2 = hVar.f1467b.f1463d;
                if (paint2 != null) {
                    this.f1467b.f1463d = new Paint(paint2);
                }
                this.f1468c = hVar.f1468c;
                this.f1469d = hVar.f1469d;
                this.f1470e = hVar.f1470e;
            }
        }

        public void a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f1471f, (Rect) null, rect, a(colorFilter));
        }

        public boolean b() {
            return this.f1467b.getRootAlpha() < 255;
        }

        public void c(int i, int i2) {
            this.f1471f.eraseColor(0);
            this.f1467b.a(new Canvas(this.f1471f), i, i2, (ColorFilter) null);
        }

        public void d() {
            this.g = this.f1468c;
            this.h = this.f1469d;
            this.i = this.f1467b.getRootAlpha();
            this.j = this.f1470e;
            this.k = false;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1466a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new i(this);
        }

        public void b(int i, int i2) {
            if (this.f1471f == null || !a(i, i2)) {
                this.f1471f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new i(this);
        }

        public Paint a(ColorFilter colorFilter) {
            if (!b() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                this.l = new Paint();
                this.l.setFilterBitmap(true);
            }
            this.l.setAlpha(this.f1467b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean c() {
            return this.f1467b.a();
        }

        public boolean a(int i, int i2) {
            return i == this.f1471f.getWidth() && i2 == this.f1471f.getHeight();
        }

        public boolean a() {
            return !this.k && this.g == this.f1468c && this.h == this.f1469d && this.j == this.f1470e && this.i == this.f1467b.getRootAlpha();
        }

        public h() {
            this.f1468c = null;
            this.f1469d = i.j;
            this.f1467b = new g();
        }

        public boolean a(int[] iArr) {
            boolean zA = this.f1467b.a(iArr);
            this.k |= zA;
            return zA;
        }
    }

    i() {
        this.f1447f = true;
        this.g = new float[9];
        this.h = new Matrix();
        this.i = new Rect();
        this.f1443b = new h();
    }

    public static i createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        i iVar = new i();
        iVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return iVar;
    }

    Object a(String str) {
        return this.f1443b.f1467b.p.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f1442a;
        if (drawable == null) {
            return false;
        }
        androidx.core.graphics.drawable.a.a(drawable);
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.i);
        if (this.i.width() <= 0 || this.i.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f1445d;
        if (colorFilter == null) {
            colorFilter = this.f1444c;
        }
        canvas.getMatrix(this.h);
        this.h.getValues(this.g);
        float fAbs = Math.abs(this.g[0]);
        float fAbs2 = Math.abs(this.g[4]);
        float fAbs3 = Math.abs(this.g[1]);
        float fAbs4 = Math.abs(this.g[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iWidth = (int) (this.i.width() * fAbs);
        int iHeight = (int) (this.i.height() * fAbs2);
        int iMin = Math.min(2048, iWidth);
        int iMin2 = Math.min(2048, iHeight);
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.i;
        canvas.translate(rect.left, rect.top);
        if (a()) {
            canvas.translate(this.i.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.i.offsetTo(0, 0);
        this.f1443b.b(iMin, iMin2);
        if (!this.f1447f) {
            this.f1443b.c(iMin, iMin2);
        } else if (!this.f1443b.a()) {
            this.f1443b.c(iMin, iMin2);
            this.f1443b.d();
        }
        this.f1443b.a(canvas, colorFilter, this.i);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f1442a;
        return drawable != null ? androidx.core.graphics.drawable.a.b(drawable) : this.f1443b.f1467b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f1443b.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f1442a;
        return drawable != null ? androidx.core.graphics.drawable.a.c(drawable) : this.f1445d;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f1442a;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new C0034i(drawable.getConstantState());
        }
        this.f1443b.f1466a = getChangingConfigurations();
        return this.f1443b;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f1443b.f1467b.j;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f1443b.f1467b.i;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f1442a;
        return drawable != null ? androidx.core.graphics.drawable.a.e(drawable) : this.f1443b.f1470e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        h hVar;
        ColorStateList colorStateList;
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            return drawable.isStateful();
        }
        return super.isStateful() || ((hVar = this.f1443b) != null && (hVar.c() || ((colorStateList = this.f1443b.f1468c) != null && colorStateList.isStateful())));
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.f1446e && super.mutate() == this) {
            this.f1443b = new h(this.f1443b);
            this.f1446e = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        h hVar = this.f1443b;
        ColorStateList colorStateList = hVar.f1468c;
        if (colorStateList != null && (mode = hVar.f1469d) != null) {
            this.f1444c = a(this.f1444c, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!hVar.c() || !hVar.a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j2) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j2);
        } else {
            super.scheduleSelf(runnable, j2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f1443b.f1467b.getRootAlpha() != i) {
            this.f1443b.f1467b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, z);
        } else {
            this.f1443b.f1470e = z;
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.b(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, colorStateList);
            return;
        }
        h hVar = this.f1443b;
        if (hVar.f1468c != colorStateList) {
            hVar.f1468c = colorStateList;
            this.f1444c = a(this.f1444c, colorStateList, hVar.f1469d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, mode);
            return;
        }
        h hVar = this.f1443b;
        if (hVar.f1469d != mode) {
            hVar.f1469d = mode;
            this.f1444c = a(this.f1444c, hVar.f1468c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: renamed from: androidx.vectordrawable.a.a.i$i, reason: collision with other inner class name */
    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class C0034i extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f1472a;

        public C0034i(Drawable.ConstantState constantState) {
            this.f1472a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f1472a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1472a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            i iVar = new i();
            iVar.f1442a = (VectorDrawable) this.f1472a.newDrawable();
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            i iVar = new i();
            iVar.f1442a = (VectorDrawable) this.f1472a.newDrawable(resources);
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            i iVar = new i();
            iVar.f1442a = (VectorDrawable) this.f1472a.newDrawable(resources, theme);
            return iVar;
        }
    }

    PorterDuffColorFilter a(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f1445d = colorFilter;
            invalidateSelf();
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class c extends f {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int[] f1448e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        androidx.core.content.c.b f1449f;
        float g;
        androidx.core.content.c.b h;
        float i;
        float j;
        float k;
        float l;
        float m;
        Paint.Cap n;
        Paint.Join o;
        float p;

        c() {
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        private Paint.Cap a(int i, Paint.Cap cap) {
            if (i == 0) {
                return Paint.Cap.BUTT;
            }
            if (i != 1) {
                return i != 2 ? cap : Paint.Cap.SQUARE;
            }
            return Paint.Cap.ROUND;
        }

        float getFillAlpha() {
            return this.j;
        }

        int getFillColor() {
            return this.h.a();
        }

        float getStrokeAlpha() {
            return this.i;
        }

        int getStrokeColor() {
            return this.f1449f.a();
        }

        float getStrokeWidth() {
            return this.g;
        }

        float getTrimPathEnd() {
            return this.l;
        }

        float getTrimPathOffset() {
            return this.m;
        }

        float getTrimPathStart() {
            return this.k;
        }

        void setFillAlpha(float f2) {
            this.j = f2;
        }

        void setFillColor(int i) {
            this.h.a(i);
        }

        void setStrokeAlpha(float f2) {
            this.i = f2;
        }

        void setStrokeColor(int i) {
            this.f1449f.a(i);
        }

        void setStrokeWidth(float f2) {
            this.g = f2;
        }

        void setTrimPathEnd(float f2) {
            this.l = f2;
        }

        void setTrimPathOffset(float f2) {
            this.m = f2;
        }

        void setTrimPathStart(float f2) {
            this.k = f2;
        }

        private Paint.Join a(int i, Paint.Join join) {
            if (i == 0) {
                return Paint.Join.MITER;
            }
            if (i != 1) {
                return i != 2 ? join : Paint.Join.BEVEL;
            }
            return Paint.Join.ROUND;
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1423c);
            a(typedArrayA, xmlPullParser, theme);
            typedArrayA.recycle();
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.f1448e = null;
            if (androidx.core.content.c.g.a(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f1457b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f1456a = androidx.core.a.b.a(string2);
                }
                this.h = androidx.core.content.c.g.a(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = androidx.core.content.c.g.a(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = a(androidx.core.content.c.g.b(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = a(androidx.core.content.c.g.b(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = androidx.core.content.c.g.a(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f1449f = androidx.core.content.c.g.a(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.i = androidx.core.content.c.g.a(typedArray, xmlPullParser, "strokeAlpha", 11, this.i);
                this.g = androidx.core.content.c.g.a(typedArray, xmlPullParser, "strokeWidth", 4, this.g);
                this.l = androidx.core.content.c.g.a(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = androidx.core.content.c.g.a(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = androidx.core.content.c.g.a(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f1458c = androidx.core.content.c.g.b(typedArray, xmlPullParser, "fillType", 13, this.f1458c);
            }
        }

        c(c cVar) {
            super(cVar);
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.f1448e = cVar.f1448e;
            this.f1449f = cVar.f1449f;
            this.g = cVar.g;
            this.i = cVar.i;
            this.h = cVar.h;
            this.f1458c = cVar.f1458c;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
        }

        @Override // androidx.vectordrawable.a.a.i.e
        public boolean a() {
            return this.h.d() || this.f1449f.d();
        }

        @Override // androidx.vectordrawable.a.a.i.e
        public boolean a(int[] iArr) {
            return this.f1449f.a(iArr) | this.h.a(iArr);
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Matrix f1450a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ArrayList<e> f1451b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1452c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f1453d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private float f1454e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private float f1455f;
        private float g;
        private float h;
        private float i;
        final Matrix j;
        int k;
        private int[] l;
        private String m;

        public d(d dVar, a.b.a<String, Object> aVar) {
            f bVar;
            super();
            this.f1450a = new Matrix();
            this.f1451b = new ArrayList<>();
            this.f1452c = 0.0f;
            this.f1453d = 0.0f;
            this.f1454e = 0.0f;
            this.f1455f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
            this.f1452c = dVar.f1452c;
            this.f1453d = dVar.f1453d;
            this.f1454e = dVar.f1454e;
            this.f1455f = dVar.f1455f;
            this.g = dVar.g;
            this.h = dVar.h;
            this.i = dVar.i;
            this.l = dVar.l;
            this.m = dVar.m;
            this.k = dVar.k;
            String str = this.m;
            if (str != null) {
                aVar.put(str, this);
            }
            this.j.set(dVar.j);
            ArrayList<e> arrayList = dVar.f1451b;
            for (int i = 0; i < arrayList.size(); i++) {
                e eVar = arrayList.get(i);
                if (eVar instanceof d) {
                    this.f1451b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f1451b.add(bVar);
                    String str2 = bVar.f1457b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        private void b() {
            this.j.reset();
            this.j.postTranslate(-this.f1453d, -this.f1454e);
            this.j.postScale(this.f1455f, this.g);
            this.j.postRotate(this.f1452c, 0.0f, 0.0f);
            this.j.postTranslate(this.h + this.f1453d, this.i + this.f1454e);
        }

        public void a(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1422b);
            a(typedArrayA, xmlPullParser);
            typedArrayA.recycle();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.f1453d;
        }

        public float getPivotY() {
            return this.f1454e;
        }

        public float getRotation() {
            return this.f1452c;
        }

        public float getScaleX() {
            return this.f1455f;
        }

        public float getScaleY() {
            return this.g;
        }

        public float getTranslateX() {
            return this.h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f2) {
            if (f2 != this.f1453d) {
                this.f1453d = f2;
                b();
            }
        }

        public void setPivotY(float f2) {
            if (f2 != this.f1454e) {
                this.f1454e = f2;
                b();
            }
        }

        public void setRotation(float f2) {
            if (f2 != this.f1452c) {
                this.f1452c = f2;
                b();
            }
        }

        public void setScaleX(float f2) {
            if (f2 != this.f1455f) {
                this.f1455f = f2;
                b();
            }
        }

        public void setScaleY(float f2) {
            if (f2 != this.g) {
                this.g = f2;
                b();
            }
        }

        public void setTranslateX(float f2) {
            if (f2 != this.h) {
                this.h = f2;
                b();
            }
        }

        public void setTranslateY(float f2) {
            if (f2 != this.i) {
                this.i = f2;
                b();
            }
        }

        private void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f1452c = androidx.core.content.c.g.a(typedArray, xmlPullParser, "rotation", 5, this.f1452c);
            this.f1453d = typedArray.getFloat(1, this.f1453d);
            this.f1454e = typedArray.getFloat(2, this.f1454e);
            this.f1455f = androidx.core.content.c.g.a(typedArray, xmlPullParser, "scaleX", 3, this.f1455f);
            this.g = androidx.core.content.c.g.a(typedArray, xmlPullParser, "scaleY", 4, this.g);
            this.h = androidx.core.content.c.g.a(typedArray, xmlPullParser, "translateX", 6, this.h);
            this.i = androidx.core.content.c.g.a(typedArray, xmlPullParser, "translateY", 7, this.i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            b();
        }

        @Override // androidx.vectordrawable.a.a.i.e
        public boolean a() {
            for (int i = 0; i < this.f1451b.size(); i++) {
                if (this.f1451b.get(i).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // androidx.vectordrawable.a.a.i.e
        public boolean a(int[] iArr) {
            boolean zA = false;
            for (int i = 0; i < this.f1451b.size(); i++) {
                zA |= this.f1451b.get(i).a(iArr);
            }
            return zA;
        }

        public d() {
            super();
            this.f1450a = new Matrix();
            this.f1451b = new ArrayList<>();
            this.f1452c = 0.0f;
            this.f1453d = 0.0f;
            this.f1454e = 0.0f;
            this.f1455f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected androidx.core.a.b.C0013b[] f1456a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f1457b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1458c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1459d;

        public f() {
            super();
            this.f1456a = null;
            this.f1458c = 0;
        }

        public void a(Path path) {
            path.reset();
            androidx.core.a.b.C0013b[] c0013bArr = this.f1456a;
            if (c0013bArr != null) {
                androidx.core.a.b.C0013b.a(c0013bArr, path);
            }
        }

        public boolean b() {
            return false;
        }

        public androidx.core.a.b.C0013b[] getPathData() {
            return this.f1456a;
        }

        public String getPathName() {
            return this.f1457b;
        }

        public void setPathData(androidx.core.a.b.C0013b[] c0013bArr) {
            if (androidx.core.a.b.a(this.f1456a, c0013bArr)) {
                androidx.core.a.b.b(this.f1456a, c0013bArr);
            } else {
                this.f1456a = androidx.core.a.b.a(c0013bArr);
            }
        }

        public f(f fVar) {
            super();
            this.f1456a = null;
            this.f1458c = 0;
            this.f1457b = fVar.f1457b;
            this.f1459d = fVar.f1459d;
            this.f1456a = androidx.core.a.b.a(fVar.f1456a);
        }
    }

    public static i a(Resources resources, int i, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            i iVar = new i();
            iVar.f1442a = androidx.core.content.c.f.a(resources, i, theme);
            new C0034i(iVar.f1442a.getConstantState());
            return iVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return createFromXmlInner(resources, (XmlPullParser) xml, attributeSetAsAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        } catch (XmlPullParserException e3) {
            Log.e("VectorDrawableCompat", "parser error", e3);
            return null;
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        h hVar = this.f1443b;
        hVar.f1467b = new g();
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1421a);
        a(typedArrayA, xmlPullParser, theme);
        typedArrayA.recycle();
        hVar.f1466a = getChangingConfigurations();
        hVar.k = true;
        a(resources, xmlPullParser, attributeSet, theme);
        this.f1444c = a(this.f1444c, hVar.f1468c, hVar.f1469d);
    }

    i(h hVar) {
        this.f1447f = true;
        this.g = new float[9];
        this.h = new Matrix();
        this.i = new Rect();
        this.f1443b = hVar;
        this.f1444c = a(this.f1444c, hVar.f1468c, hVar.f1469d);
    }

    /* JADX INFO: compiled from: VectorDrawableCompat.java */
    private static class g {
        private static final Matrix q = new Matrix();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Path f1460a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Path f1461b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Matrix f1462c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Paint f1463d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        Paint f1464e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private PathMeasure f1465f;
        private int g;
        final d h;
        float i;
        float j;
        float k;
        float l;
        int m;
        String n;
        Boolean o;
        final a.b.a<String, Object> p;

        public g() {
            this.f1462c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            this.p = new a.b.a<>();
            this.h = new d();
            this.f1460a = new Path();
            this.f1461b = new Path();
        }

        private static float a(float f2, float f3, float f4, float f5) {
            return (f2 * f5) - (f3 * f4);
        }

        private void a(d dVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            dVar.f1450a.set(matrix);
            dVar.f1450a.preConcat(dVar.j);
            canvas.save();
            for (int i3 = 0; i3 < dVar.f1451b.size(); i3++) {
                e eVar = dVar.f1451b.get(i3);
                if (eVar instanceof d) {
                    a((d) eVar, dVar.f1450a, canvas, i, i2, colorFilter);
                } else if (eVar instanceof f) {
                    a(dVar, (f) eVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.m;
        }

        public void setAlpha(float f2) {
            setRootAlpha((int) (f2 * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.m = i;
        }

        public void a(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            a(this.h, q, canvas, i, i2, colorFilter);
        }

        public g(g gVar) {
            this.f1462c = new Matrix();
            this.i = 0.0f;
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 255;
            this.n = null;
            this.o = null;
            this.p = new a.b.a<>();
            this.h = new d(gVar.h, this.p);
            this.f1460a = new Path(gVar.f1460a);
            this.f1461b = new Path(gVar.f1461b);
            this.i = gVar.i;
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.g = gVar.g;
            this.m = gVar.m;
            this.n = gVar.n;
            String str = gVar.n;
            if (str != null) {
                this.p.put(str, this);
            }
            this.o = gVar.o;
        }

        private void a(d dVar, f fVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f2 = i / this.k;
            float f3 = i2 / this.l;
            float fMin = Math.min(f2, f3);
            Matrix matrix = dVar.f1450a;
            this.f1462c.set(matrix);
            this.f1462c.postScale(f2, f3);
            float fA = a(matrix);
            if (fA == 0.0f) {
                return;
            }
            fVar.a(this.f1460a);
            Path path = this.f1460a;
            this.f1461b.reset();
            if (fVar.b()) {
                this.f1461b.setFillType(fVar.f1458c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f1461b.addPath(path, this.f1462c);
                canvas.clipPath(this.f1461b);
                return;
            }
            c cVar = (c) fVar;
            if (cVar.k != 0.0f || cVar.l != 1.0f) {
                float f4 = cVar.k;
                float f5 = cVar.m;
                float f6 = (f4 + f5) % 1.0f;
                float f7 = (cVar.l + f5) % 1.0f;
                if (this.f1465f == null) {
                    this.f1465f = new PathMeasure();
                }
                this.f1465f.setPath(this.f1460a, false);
                float length = this.f1465f.getLength();
                float f8 = f6 * length;
                float f9 = f7 * length;
                path.reset();
                if (f8 > f9) {
                    this.f1465f.getSegment(f8, length, path, true);
                    this.f1465f.getSegment(0.0f, f9, path, true);
                } else {
                    this.f1465f.getSegment(f8, f9, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f1461b.addPath(path, this.f1462c);
            if (cVar.h.e()) {
                androidx.core.content.c.b bVar = cVar.h;
                if (this.f1464e == null) {
                    this.f1464e = new Paint(1);
                    this.f1464e.setStyle(Paint.Style.FILL);
                }
                Paint paint = this.f1464e;
                if (bVar.c()) {
                    Shader shaderB = bVar.b();
                    shaderB.setLocalMatrix(this.f1462c);
                    paint.setShader(shaderB);
                    paint.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint.setShader(null);
                    paint.setAlpha(255);
                    paint.setColor(i.a(bVar.a(), cVar.j));
                }
                paint.setColorFilter(colorFilter);
                this.f1461b.setFillType(cVar.f1458c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f1461b, paint);
            }
            if (cVar.f1449f.e()) {
                androidx.core.content.c.b bVar2 = cVar.f1449f;
                if (this.f1463d == null) {
                    this.f1463d = new Paint(1);
                    this.f1463d.setStyle(Paint.Style.STROKE);
                }
                Paint paint2 = this.f1463d;
                Paint.Join join = cVar.o;
                if (join != null) {
                    paint2.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.n;
                if (cap != null) {
                    paint2.setStrokeCap(cap);
                }
                paint2.setStrokeMiter(cVar.p);
                if (bVar2.c()) {
                    Shader shaderB2 = bVar2.b();
                    shaderB2.setLocalMatrix(this.f1462c);
                    paint2.setShader(shaderB2);
                    paint2.setAlpha(Math.round(cVar.i * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(i.a(bVar2.a(), cVar.i));
                }
                paint2.setColorFilter(colorFilter);
                paint2.setStrokeWidth(cVar.g * fMin * fA);
                canvas.drawPath(this.f1461b, paint2);
            }
        }

        private float a(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float fHypot = (float) Math.hypot(fArr[0], fArr[1]);
            float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float fA = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float fMax = Math.max(fHypot, fHypot2);
            if (fMax > 0.0f) {
                return Math.abs(fA) / fMax;
            }
            return 0.0f;
        }

        public boolean a() {
            if (this.o == null) {
                this.o = Boolean.valueOf(this.h.a());
            }
            return this.o.booleanValue();
        }

        public boolean a(int[] iArr) {
            return this.h.a(iArr);
        }
    }

    static int a(int i, float f2) {
        return (i & 16777215) | (((int) (Color.alpha(i) * f2)) << 24);
    }

    private static PorterDuff.Mode a(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i != 9) {
            switch (i) {
                case 14:
                    return PorterDuff.Mode.MULTIPLY;
                case 15:
                    return PorterDuff.Mode.SCREEN;
                case 16:
                    return PorterDuff.Mode.ADD;
                default:
                    return mode;
            }
        }
        return PorterDuff.Mode.SRC_ATOP;
    }

    private void a(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        h hVar = this.f1443b;
        g gVar = hVar.f1467b;
        hVar.f1469d = a(androidx.core.content.c.g.b(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListA = androidx.core.content.c.g.a(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListA != null) {
            hVar.f1468c = colorStateListA;
        }
        hVar.f1470e = androidx.core.content.c.g.a(typedArray, xmlPullParser, "autoMirrored", 5, hVar.f1470e);
        gVar.k = androidx.core.content.c.g.a(typedArray, xmlPullParser, "viewportWidth", 7, gVar.k);
        gVar.l = androidx.core.content.c.g.a(typedArray, xmlPullParser, "viewportHeight", 8, gVar.l);
        if (gVar.k <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (gVar.l > 0.0f) {
            gVar.i = typedArray.getDimension(3, gVar.i);
            gVar.j = typedArray.getDimension(2, gVar.j);
            if (gVar.i <= 0.0f) {
                throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
            }
            if (gVar.j > 0.0f) {
                gVar.setAlpha(androidx.core.content.c.g.a(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
                String string = typedArray.getString(0);
                if (string != null) {
                    gVar.n = string;
                    gVar.p.put(string, gVar);
                    return;
                }
                return;
            }
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    }

    private void a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        h hVar = this.f1443b;
        g gVar = hVar.f1467b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.h);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if (Config.FEED_LIST_ITEM_PATH.equals(name)) {
                    c cVar = new c();
                    cVar.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.f1451b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.p.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    hVar.f1466a = cVar.f1459d | hVar.f1466a;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.f1451b.add(bVar);
                    if (bVar.getPathName() != null) {
                        gVar.p.put(bVar.getPathName(), bVar);
                    }
                    hVar.f1466a = bVar.f1459d | hVar.f1466a;
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.a(resources, attributeSet, theme, xmlPullParser);
                    dVar.f1451b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.p.put(dVar2.getGroupName(), dVar2);
                    }
                    hVar.f1466a = dVar2.k | hVar.f1466a;
                }
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    void a(boolean z) {
        this.f1447f = z;
    }

    private boolean a() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && androidx.core.graphics.drawable.a.d(this) == 1;
    }
}
