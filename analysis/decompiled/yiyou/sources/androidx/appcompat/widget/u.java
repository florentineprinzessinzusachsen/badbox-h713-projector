package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import androidx.appcompat.resources.R$drawable;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: ResourceManagerInternal.java */
/* JADX INFO: loaded from: classes.dex */
public final class u {
    private static u i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private WeakHashMap<Context, a.b.h<ColorStateList>> f827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a.b.a<String, d> f828b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a.b.h<String> f829c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final WeakHashMap<Context, a.b.d<WeakReference<Drawable.ConstantState>>> f830d = new WeakHashMap<>(0);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TypedValue f831e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f832f;
    private e g;
    private static final PorterDuff.Mode h = PorterDuff.Mode.SRC_IN;
    private static final c j = new c(6);

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    static class a implements d {
        a() {
        }

        @Override // androidx.appcompat.widget.u.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return androidx.appcompat.b.a.a.b(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private static class b implements d {
        b() {
        }

        @Override // androidx.appcompat.widget.u.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return androidx.vectordrawable.a.a.c.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", e2);
                return null;
            }
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private static class c extends a.b.e<Integer, PorterDuffColorFilter> {
        public c(int i) {
            super(i);
        }

        private static int b(int i, PorterDuff.Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter a(int i, PorterDuff.Mode mode) {
            return b(Integer.valueOf(b(i, mode)));
        }

        PorterDuffColorFilter a(int i, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return a(Integer.valueOf(b(i, mode)), porterDuffColorFilter);
        }
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private interface d {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    interface e {
        ColorStateList a(Context context, int i);

        PorterDuff.Mode a(int i);

        Drawable a(u uVar, Context context, int i);

        boolean a(Context context, int i, Drawable drawable);

        boolean b(Context context, int i, Drawable drawable);
    }

    /* JADX INFO: compiled from: ResourceManagerInternal.java */
    private static class f implements d {
        f() {
        }

        @Override // androidx.appcompat.widget.u.d
        public Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
            try {
                return androidx.vectordrawable.a.a.i.createFromXmlInner(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e2) {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", e2);
                return null;
            }
        }
    }

    public static synchronized u a() {
        if (i == null) {
            i = new u();
            a(i);
        }
        return i;
    }

    private Drawable c(Context context, int i2) {
        if (this.f831e == null) {
            this.f831e = new TypedValue();
        }
        TypedValue typedValue = this.f831e;
        context.getResources().getValue(i2, typedValue, true);
        long jA = a(typedValue);
        Drawable drawableA = a(context, jA);
        if (drawableA != null) {
            return drawableA;
        }
        e eVar = this.g;
        Drawable drawableA2 = eVar == null ? null : eVar.a(this, context, i2);
        if (drawableA2 != null) {
            drawableA2.setChangingConfigurations(typedValue.changingConfigurations);
            a(context, jA, drawableA2);
        }
        return drawableA2;
    }

    private ColorStateList d(Context context, int i2) {
        a.b.h<ColorStateList> hVar;
        WeakHashMap<Context, a.b.h<ColorStateList>> weakHashMap = this.f827a;
        if (weakHashMap == null || (hVar = weakHashMap.get(context)) == null) {
            return null;
        }
        return hVar.a(i2);
    }

    private Drawable e(Context context, int i2) {
        int next;
        a.b.a<String, d> aVar = this.f828b;
        if (aVar == null || aVar.isEmpty()) {
            return null;
        }
        a.b.h<String> hVar = this.f829c;
        if (hVar != null) {
            String strA = hVar.a(i2);
            if ("appcompat_skip_skip".equals(strA) || (strA != null && this.f828b.get(strA) == null)) {
                return null;
            }
        } else {
            this.f829c = new a.b.h<>();
        }
        if (this.f831e == null) {
            this.f831e = new TypedValue();
        }
        TypedValue typedValue = this.f831e;
        Resources resources = context.getResources();
        resources.getValue(i2, typedValue, true);
        long jA = a(typedValue);
        Drawable drawableA = a(context, jA);
        if (drawableA != null) {
            return drawableA;
        }
        CharSequence charSequence = typedValue.string;
        if (charSequence != null && charSequence.toString().endsWith(".xml")) {
            try {
                XmlResourceParser xml = resources.getXml(i2);
                AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                String name = xml.getName();
                this.f829c.a(i2, name);
                d dVar = this.f828b.get(name);
                if (dVar != null) {
                    drawableA = dVar.a(context, xml, attributeSetAsAttributeSet, context.getTheme());
                }
                if (drawableA != null) {
                    drawableA.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, jA, drawableA);
                }
            } catch (Exception e2) {
                Log.e("ResourceManagerInternal", "Exception while inflating drawable", e2);
            }
        }
        if (drawableA == null) {
            this.f829c.a(i2, "appcompat_skip_skip");
        }
        return drawableA;
    }

    synchronized ColorStateList b(Context context, int i2) {
        ColorStateList colorStateListD;
        colorStateListD = d(context, i2);
        if (colorStateListD == null) {
            colorStateListD = this.g == null ? null : this.g.a(context, i2);
            if (colorStateListD != null) {
                a(context, i2, colorStateListD);
            }
        }
        return colorStateListD;
    }

    private static void a(u uVar) {
        if (Build.VERSION.SDK_INT < 24) {
            uVar.a("vector", new f());
            uVar.a("animated-vector", new b());
            uVar.a("animated-selector", new a());
        }
    }

    private void b(Context context) {
        if (this.f832f) {
            return;
        }
        this.f832f = true;
        Drawable drawableA = a(context, R$drawable.abc_vector_test);
        if (drawableA == null || !a(drawableA)) {
            this.f832f = false;
            throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
        }
    }

    public synchronized void a(e eVar) {
        this.g = eVar;
    }

    public synchronized Drawable a(Context context, int i2) {
        return a(context, i2, false);
    }

    synchronized Drawable a(Context context, int i2, boolean z) {
        Drawable drawableE;
        b(context);
        drawableE = e(context, i2);
        if (drawableE == null) {
            drawableE = c(context, i2);
        }
        if (drawableE == null) {
            drawableE = androidx.core.content.a.c(context, i2);
        }
        if (drawableE != null) {
            drawableE = a(context, i2, z, drawableE);
        }
        if (drawableE != null) {
            p.b(drawableE);
        }
        return drawableE;
    }

    public synchronized void a(Context context) {
        a.b.d<WeakReference<Drawable.ConstantState>> dVar = this.f830d.get(context);
        if (dVar != null) {
            dVar.a();
        }
    }

    private static long a(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    private Drawable a(Context context, int i2, boolean z, Drawable drawable) {
        ColorStateList colorStateListB = b(context, i2);
        if (colorStateListB != null) {
            if (p.a(drawable)) {
                drawable = drawable.mutate();
            }
            Drawable drawableH = androidx.core.graphics.drawable.a.h(drawable);
            androidx.core.graphics.drawable.a.a(drawableH, colorStateListB);
            PorterDuff.Mode modeA = a(i2);
            if (modeA == null) {
                return drawableH;
            }
            androidx.core.graphics.drawable.a.a(drawableH, modeA);
            return drawableH;
        }
        e eVar = this.g;
        if ((eVar == null || !eVar.b(context, i2, drawable)) && !a(context, i2, drawable) && z) {
            return null;
        }
        return drawable;
    }

    private synchronized Drawable a(Context context, long j2) {
        a.b.d<WeakReference<Drawable.ConstantState>> dVar = this.f830d.get(context);
        if (dVar == null) {
            return null;
        }
        WeakReference<Drawable.ConstantState> weakReferenceB = dVar.b(j2);
        if (weakReferenceB != null) {
            Drawable.ConstantState constantState = weakReferenceB.get();
            if (constantState != null) {
                return constantState.newDrawable(context.getResources());
            }
            dVar.a(j2);
        }
        return null;
    }

    private synchronized boolean a(Context context, long j2, Drawable drawable) {
        Drawable.ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        a.b.d<WeakReference<Drawable.ConstantState>> dVar = this.f830d.get(context);
        if (dVar == null) {
            dVar = new a.b.d<>();
            this.f830d.put(context, dVar);
        }
        dVar.c(j2, new WeakReference<>(constantState));
        return true;
    }

    synchronized Drawable a(Context context, i0 i0Var, int i2) {
        Drawable drawableE = e(context, i2);
        if (drawableE == null) {
            drawableE = i0Var.a(i2);
        }
        if (drawableE == null) {
            return null;
        }
        return a(context, i2, false, drawableE);
    }

    boolean a(Context context, int i2, Drawable drawable) {
        e eVar = this.g;
        return eVar != null && eVar.a(context, i2, drawable);
    }

    private void a(String str, d dVar) {
        if (this.f828b == null) {
            this.f828b = new a.b.a<>();
        }
        this.f828b.put(str, dVar);
    }

    PorterDuff.Mode a(int i2) {
        e eVar = this.g;
        if (eVar == null) {
            return null;
        }
        return eVar.a(i2);
    }

    private void a(Context context, int i2, ColorStateList colorStateList) {
        if (this.f827a == null) {
            this.f827a = new WeakHashMap<>();
        }
        a.b.h<ColorStateList> hVar = this.f827a.get(context);
        if (hVar == null) {
            hVar = new a.b.h<>();
            this.f827a.put(context, hVar);
        }
        hVar.a(i2, colorStateList);
    }

    static void a(Drawable drawable, b0 b0Var, int[] iArr) {
        if (p.a(drawable) && drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if (b0Var.f717d || b0Var.f716c) {
            drawable.setColorFilter(a(b0Var.f717d ? b0Var.f714a : null, b0Var.f716c ? b0Var.f715b : h, iArr));
        } else {
            drawable.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 23) {
            drawable.invalidateSelf();
        }
    }

    private static PorterDuffColorFilter a(ColorStateList colorStateList, PorterDuff.Mode mode, int[] iArr) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return a(colorStateList.getColorForState(iArr, 0), mode);
    }

    public static synchronized PorterDuffColorFilter a(int i2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilterA;
        porterDuffColorFilterA = j.a(i2, mode);
        if (porterDuffColorFilterA == null) {
            porterDuffColorFilterA = new PorterDuffColorFilter(i2, mode);
            j.a(i2, mode, porterDuffColorFilterA);
        }
        return porterDuffColorFilterA;
    }

    private static boolean a(Drawable drawable) {
        return (drawable instanceof androidx.vectordrawable.a.a.i) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }
}
