package androidx.appcompat.b.a;

import a.b.h;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.StateSet;
import androidx.appcompat.resources.R$styleable;
import androidx.appcompat.widget.u;
import androidx.vectordrawable.a.a.i;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class a extends androidx.appcompat.b.a.d implements androidx.core.graphics.drawable.b {
    private c o;
    private g p;
    private int q;
    private int r;
    private boolean s;

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    private static class b extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Animatable f336a;

        b(Animatable animatable) {
            super();
            this.f336a = animatable;
        }

        @Override // androidx.appcompat.b.a.a.g
        public void c() {
            this.f336a.start();
        }

        @Override // androidx.appcompat.b.a.a.g
        public void d() {
            this.f336a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    static class c extends androidx.appcompat.b.a.d.a {
        a.b.d<Long> K;
        h<Integer> L;

        c(c cVar, a aVar, Resources resources) {
            super(cVar, aVar, resources);
            if (cVar != null) {
                this.K = cVar.K;
                this.L = cVar.L;
            } else {
                this.K = new a.b.d<>();
                this.L = new h<>();
            }
        }

        private static long f(int i, int i2) {
            return ((long) i2) | (((long) i) << 32);
        }

        int a(int i, int i2, Drawable drawable, boolean z) {
            int iA = super.a(drawable);
            long jF = f(i, i2);
            long j = z ? 8589934592L : 0L;
            long j2 = iA;
            this.K.a(jF, Long.valueOf(j2 | j));
            if (z) {
                this.K.a(f(i2, i), Long.valueOf(4294967296L | j2 | j));
            }
            return iA;
        }

        int b(int[] iArr) {
            int iA = super.a(iArr);
            return iA >= 0 ? iA : super.a(StateSet.WILD_CARD);
        }

        int c(int i, int i2) {
            return (int) this.K.b(f(i, i2), -1L).longValue();
        }

        int d(int i) {
            if (i < 0) {
                return 0;
            }
            return this.L.b(i, 0).intValue();
        }

        boolean e(int i, int i2) {
            return (this.K.b(f(i, i2), -1L).longValue() & 8589934592L) != 0;
        }

        @Override // androidx.appcompat.b.a.d.a, androidx.appcompat.b.a.b.c
        void m() {
            this.K = this.K.m0clone();
            this.L = this.L.m1clone();
        }

        @Override // androidx.appcompat.b.a.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new a(this, null);
        }

        boolean d(int i, int i2) {
            return (this.K.b(f(i, i2), -1L).longValue() & 4294967296L) != 0;
        }

        @Override // androidx.appcompat.b.a.d.a, android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new a(this, resources);
        }

        int a(int[] iArr, Drawable drawable, int i) {
            int iA = super.a(iArr, drawable);
            this.L.c(iA, Integer.valueOf(i));
            return iA;
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    private static class d extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final androidx.vectordrawable.a.a.c f337a;

        d(androidx.vectordrawable.a.a.c cVar) {
            super();
            this.f337a = cVar;
        }

        @Override // androidx.appcompat.b.a.a.g
        public void c() {
            this.f337a.start();
        }

        @Override // androidx.appcompat.b.a.a.g
        public void d() {
            this.f337a.stop();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    private static class e extends g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ObjectAnimator f338a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f339b;

        e(AnimationDrawable animationDrawable, boolean z, boolean z2) {
            super();
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            int i = z ? numberOfFrames - 1 : 0;
            int i2 = z ? 0 : numberOfFrames - 1;
            f fVar = new f(animationDrawable, z);
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(animationDrawable, "currentIndex", i, i2);
            if (Build.VERSION.SDK_INT >= 18) {
                objectAnimatorOfInt.setAutoCancel(true);
            }
            objectAnimatorOfInt.setDuration(fVar.a());
            objectAnimatorOfInt.setInterpolator(fVar);
            this.f339b = z2;
            this.f338a = objectAnimatorOfInt;
        }

        @Override // androidx.appcompat.b.a.a.g
        public boolean a() {
            return this.f339b;
        }

        @Override // androidx.appcompat.b.a.a.g
        public void b() {
            this.f338a.reverse();
        }

        @Override // androidx.appcompat.b.a.a.g
        public void c() {
            this.f338a.start();
        }

        @Override // androidx.appcompat.b.a.a.g
        public void d() {
            this.f338a.cancel();
        }
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    private static abstract class g {
        private g() {
        }

        public boolean a() {
            return false;
        }

        public void b() {
        }

        public abstract void c();

        public abstract void d();
    }

    public a() {
        this(null, null);
    }

    public static a b(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        String name = xmlPullParser.getName();
        if (name.equals("animated-selector")) {
            a aVar = new a();
            aVar.a(context, resources, xmlPullParser, attributeSet, theme);
            return aVar;
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": invalid animated-selector tag " + name);
    }

    private void c() {
        onStateChange(getState());
    }

    private int d(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableItem);
        int resourceId = typedArrayA.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_id, 0);
        int resourceId2 = typedArrayA.getResourceId(R$styleable.AnimatedStateListDrawableItem_android_drawable, -1);
        Drawable drawableA = resourceId2 > 0 ? u.a().a(context, resourceId2) : null;
        typedArrayA.recycle();
        int[] iArrA = a(attributeSet);
        if (drawableA == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            if (xmlPullParser.getName().equals("vector")) {
                drawableA = i.createFromXmlInner(resources, xmlPullParser, attributeSet, theme);
            } else {
                drawableA = Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            }
        }
        if (drawableA != null) {
            return this.o.a(iArrA, drawableA, resourceId);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <item> tag requires a 'drawable' attribute or child tag defining a drawable");
    }

    private int e(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int next;
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableTransition);
        int resourceId = typedArrayA.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_fromId, -1);
        int resourceId2 = typedArrayA.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_toId, -1);
        int resourceId3 = typedArrayA.getResourceId(R$styleable.AnimatedStateListDrawableTransition_android_drawable, -1);
        Drawable drawableA = resourceId3 > 0 ? u.a().a(context, resourceId3) : null;
        boolean z = typedArrayA.getBoolean(R$styleable.AnimatedStateListDrawableTransition_android_reversible, false);
        typedArrayA.recycle();
        if (drawableA == null) {
            do {
                next = xmlPullParser.next();
            } while (next == 4);
            if (next != 2) {
                throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
            }
            if (xmlPullParser.getName().equals("animated-vector")) {
                drawableA = androidx.vectordrawable.a.a.c.a(context, resources, xmlPullParser, attributeSet, theme);
            } else {
                drawableA = Build.VERSION.SDK_INT >= 21 ? Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet, theme) : Drawable.createFromXmlInner(resources, xmlPullParser, attributeSet);
            }
        }
        if (drawableA == null) {
            throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable");
        }
        if (resourceId != -1 && resourceId2 != -1) {
            return this.o.a(resourceId, resourceId2, drawableA, z);
        }
        throw new XmlPullParserException(xmlPullParser.getPositionDescription() + ": <transition> tag requires 'fromId' & 'toId' attributes");
    }

    @Override // androidx.appcompat.b.a.d, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    @Override // androidx.appcompat.b.a.b, android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        g gVar = this.p;
        if (gVar != null) {
            gVar.d();
            this.p = null;
            a(this.q);
            this.q = -1;
            this.r = -1;
        }
    }

    @Override // androidx.appcompat.b.a.d, androidx.appcompat.b.a.b, android.graphics.drawable.Drawable
    public Drawable mutate() {
        if (!this.s) {
            super.mutate();
            if (this == this) {
                this.o.m();
                this.s = true;
            }
        }
        return this;
    }

    @Override // androidx.appcompat.b.a.d, androidx.appcompat.b.a.b, android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        int iB = this.o.b(iArr);
        boolean z = iB != b() && (b(iB) || a(iB));
        Drawable current = getCurrent();
        return current != null ? z | current.setState(iArr) : z;
    }

    @Override // androidx.appcompat.b.a.b, android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.p != null && (visible || z2)) {
            if (z) {
                this.p.c();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    a(c cVar, Resources resources) {
        super(null);
        this.q = -1;
        this.r = -1;
        a(new c(cVar, this, resources));
        onStateChange(getState());
        jumpToCurrentState();
    }

    private void c(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int depth = xmlPullParser.getDepth() + 1;
        while (true) {
            int next = xmlPullParser.next();
            if (next == 1) {
                return;
            }
            int depth2 = xmlPullParser.getDepth();
            if (depth2 < depth && next == 3) {
                return;
            }
            if (next == 2 && depth2 <= depth) {
                if (xmlPullParser.getName().equals("item")) {
                    d(context, resources, xmlPullParser, attributeSet, theme);
                } else if (xmlPullParser.getName().equals("transition")) {
                    e(context, resources, xmlPullParser, attributeSet, theme);
                }
            }
        }
    }

    public void a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, R$styleable.AnimatedStateListDrawableCompat);
        setVisible(typedArrayA.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_visible, true), true);
        a(typedArrayA);
        a(resources);
        typedArrayA.recycle();
        c(context, resources, xmlPullParser, attributeSet, theme);
        c();
    }

    private boolean b(int i) {
        int iB;
        int iC;
        g bVar;
        g gVar = this.p;
        if (gVar != null) {
            if (i == this.q) {
                return true;
            }
            if (i == this.r && gVar.a()) {
                gVar.b();
                this.q = this.r;
                this.r = i;
                return true;
            }
            iB = this.q;
            gVar.d();
        } else {
            iB = b();
        }
        this.p = null;
        this.r = -1;
        this.q = -1;
        c cVar = this.o;
        int iD = cVar.d(iB);
        int iD2 = cVar.d(i);
        if (iD2 == 0 || iD == 0 || (iC = cVar.c(iD, iD2)) < 0) {
            return false;
        }
        boolean zE = cVar.e(iD, iD2);
        a(iC);
        Object current = getCurrent();
        if (current instanceof AnimationDrawable) {
            bVar = new e((AnimationDrawable) current, cVar.d(iD, iD2), zE);
        } else if (current instanceof androidx.vectordrawable.a.a.c) {
            bVar = new d((androidx.vectordrawable.a.a.c) current);
        } else {
            if (current instanceof Animatable) {
                bVar = new b((Animatable) current);
            }
            return false;
        }
        bVar.c();
        this.p = bVar;
        this.r = iB;
        this.q = i;
        return true;
    }

    /* JADX INFO: compiled from: AnimatedStateListDrawableCompat.java */
    private static class f implements TimeInterpolator {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int[] f340a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f341b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f342c;

        f(AnimationDrawable animationDrawable, boolean z) {
            a(animationDrawable, z);
        }

        int a(AnimationDrawable animationDrawable, boolean z) {
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            this.f341b = numberOfFrames;
            int[] iArr = this.f340a;
            if (iArr == null || iArr.length < numberOfFrames) {
                this.f340a = new int[numberOfFrames];
            }
            int[] iArr2 = this.f340a;
            int i = 0;
            for (int i2 = 0; i2 < numberOfFrames; i2++) {
                int duration = animationDrawable.getDuration(z ? (numberOfFrames - i2) - 1 : i2);
                iArr2[i2] = duration;
                i += duration;
            }
            this.f342c = i;
            return i;
        }

        @Override // android.animation.TimeInterpolator
        public float getInterpolation(float f2) {
            int i = (int) ((f2 * this.f342c) + 0.5f);
            int i2 = this.f341b;
            int[] iArr = this.f340a;
            int i3 = 0;
            while (i3 < i2 && i >= iArr[i3]) {
                i -= iArr[i3];
                i3++;
            }
            return (i3 / i2) + (i3 < i2 ? i / this.f342c : 0.0f);
        }

        int a() {
            return this.f342c;
        }
    }

    private void a(TypedArray typedArray) {
        c cVar = this.o;
        if (Build.VERSION.SDK_INT >= 21) {
            cVar.f354d |= typedArray.getChangingConfigurations();
        }
        cVar.b(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_variablePadding, cVar.i));
        cVar.a(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_constantSize, cVar.l));
        cVar.b(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration, cVar.A));
        cVar.c(typedArray.getInt(R$styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration, cVar.B));
        setDither(typedArray.getBoolean(R$styleable.AnimatedStateListDrawableCompat_android_dither, cVar.x));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.appcompat.b.a.d, androidx.appcompat.b.a.b
    public c a() {
        return new c(this.o, this, null);
    }

    @Override // androidx.appcompat.b.a.d, androidx.appcompat.b.a.b
    void a(androidx.appcompat.b.a.b.c cVar) {
        super.a(cVar);
        if (cVar instanceof c) {
            this.o = (c) cVar;
        }
    }
}
