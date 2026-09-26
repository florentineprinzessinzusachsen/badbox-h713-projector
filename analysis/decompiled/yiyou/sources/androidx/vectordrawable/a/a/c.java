package androidx.vectordrawable.a.a;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends h implements androidx.vectordrawable.a.a.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f1427b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f1428c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ArgbEvaluator f1429d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final Drawable.Callback f1430e;

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    class a implements Drawable.Callback {
        a() {
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable drawable) {
            c.this.invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
            c.this.scheduleSelf(runnable, j);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
            c.this.unscheduleSelf(runnable);
        }
    }

    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    private static class b extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1432a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        i f1433b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        AnimatorSet f1434c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        ArrayList<Animator> f1435d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        a.b.a<Animator, String> f1436e;

        public b(Context context, b bVar, Drawable.Callback callback, Resources resources) {
            if (bVar != null) {
                this.f1432a = bVar.f1432a;
                i iVar = bVar.f1433b;
                if (iVar != null) {
                    Drawable.ConstantState constantState = iVar.getConstantState();
                    if (resources != null) {
                        this.f1433b = (i) constantState.newDrawable(resources);
                    } else {
                        this.f1433b = (i) constantState.newDrawable();
                    }
                    i iVar2 = this.f1433b;
                    iVar2.mutate();
                    this.f1433b = iVar2;
                    this.f1433b.setCallback(callback);
                    this.f1433b.setBounds(bVar.f1433b.getBounds());
                    this.f1433b.a(false);
                }
                ArrayList<Animator> arrayList = bVar.f1435d;
                if (arrayList != null) {
                    int size = arrayList.size();
                    this.f1435d = new ArrayList<>(size);
                    this.f1436e = new a.b.a<>(size);
                    for (int i = 0; i < size; i++) {
                        Animator animator = bVar.f1435d.get(i);
                        Animator animatorClone = animator.clone();
                        String str = bVar.f1436e.get(animator);
                        animatorClone.setTarget(this.f1433b.a(str));
                        this.f1435d.add(animatorClone);
                        this.f1436e.put(animatorClone, str);
                    }
                    a();
                }
            }
        }

        public void a() {
            if (this.f1434c == null) {
                this.f1434c = new AnimatorSet();
            }
            this.f1434c.playTogether(this.f1435d);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1432a;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 24.");
        }
    }

    c() {
        this(null, null, null);
    }

    public static c a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        c cVar = new c(context);
        cVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return cVar;
    }

    @Override // androidx.vectordrawable.a.a.h, android.graphics.drawable.Drawable
    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, theme);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            return androidx.core.graphics.drawable.a.a(drawable);
        }
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        this.f1427b.f1433b.draw(canvas);
        if (this.f1427b.f1434c.isStarted()) {
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f1442a;
        return drawable != null ? androidx.core.graphics.drawable.a.b(drawable) : this.f1427b.f1433b.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f1427b.f1432a;
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f1442a;
        return drawable != null ? androidx.core.graphics.drawable.a.c(drawable) : this.f1427b.f1433b.getColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f1442a;
        if (drawable == null || Build.VERSION.SDK_INT < 24) {
            return null;
        }
        return new C0033c(drawable.getConstantState());
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.getIntrinsicHeight() : this.f1427b.f1433b.getIntrinsicHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.getIntrinsicWidth() : this.f1427b.f1433b.getIntrinsicWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.getOpacity() : this.f1427b.f1433b.getOpacity();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                if ("animated-vector".equals(name)) {
                    TypedArray typedArrayA = androidx.core.content.c.g.a(resources, theme, attributeSet, androidx.vectordrawable.a.a.a.f1425e);
                    int resourceId = typedArrayA.getResourceId(0, 0);
                    if (resourceId != 0) {
                        i iVarA = i.a(resources, resourceId, theme);
                        iVarA.a(false);
                        iVarA.setCallback(this.f1430e);
                        i iVar = this.f1427b.f1433b;
                        if (iVar != null) {
                            iVar.setCallback(null);
                        }
                        this.f1427b.f1433b = iVarA;
                    }
                    typedArrayA.recycle();
                } else if ("target".equals(name)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, androidx.vectordrawable.a.a.a.f1426f);
                    String string = typedArrayObtainAttributes.getString(0);
                    int resourceId2 = typedArrayObtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        Context context = this.f1428c;
                        if (context == null) {
                            typedArrayObtainAttributes.recycle();
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                        a(string, e.a(context, resourceId2));
                    }
                    typedArrayObtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
        this.f1427b.a();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f1442a;
        return drawable != null ? androidx.core.graphics.drawable.a.e(drawable) : this.f1427b.f1433b.isAutoMirrored();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        Drawable drawable = this.f1442a;
        return drawable != null ? ((AnimatedVectorDrawable) drawable).isRunning() : this.f1427b.f1434c.isRunning();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.isStateful() : this.f1427b.f1433b.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.mutate();
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.setBounds(rect);
        } else {
            this.f1427b.f1433b.setBounds(rect);
        }
    }

    @Override // androidx.vectordrawable.a.a.h, android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.setLevel(i) : this.f1427b.f1433b.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f1442a;
        return drawable != null ? drawable.setState(iArr) : this.f1427b.f1433b.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else {
            this.f1427b.f1433b.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, z);
        } else {
            this.f1427b.f1433b.setAutoMirrored(z);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.b(drawable, i);
        } else {
            this.f1427b.f1433b.setTint(i);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, colorStateList);
        } else {
            this.f1427b.f1433b.setTintList(colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.a(drawable, mode);
        } else {
            this.f1427b.f1433b.setTintMode(mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        this.f1427b.f1433b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).start();
        } else {
            if (this.f1427b.f1434c.isStarted()) {
                return;
            }
            this.f1427b.f1434c.start();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            ((AnimatedVectorDrawable) drawable).stop();
        } else {
            this.f1427b.f1434c.end();
        }
    }

    private c(Context context) {
        this(context, null, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f1442a;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f1427b.f1433b.setColorFilter(colorFilter);
        }
    }

    /* JADX INFO: renamed from: androidx.vectordrawable.a.a.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AnimatedVectorDrawableCompat.java */
    private static class C0033c extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f1437a;

        public C0033c(Drawable.ConstantState constantState) {
            this.f1437a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f1437a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f1437a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            c cVar = new c();
            cVar.f1442a = this.f1437a.newDrawable();
            cVar.f1442a.setCallback(cVar.f1430e);
            return cVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            c cVar = new c();
            cVar.f1442a = this.f1437a.newDrawable(resources);
            cVar.f1442a.setCallback(cVar.f1430e);
            return cVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            c cVar = new c();
            cVar.f1442a = this.f1437a.newDrawable(resources, theme);
            cVar.f1442a.setCallback(cVar.f1430e);
            return cVar;
        }
    }

    private c(Context context, b bVar, Resources resources) {
        this.f1429d = null;
        this.f1430e = new a();
        this.f1428c = context;
        if (bVar != null) {
            this.f1427b = bVar;
        } else {
            this.f1427b = new b(context, bVar, this.f1430e, resources);
        }
    }

    private void a(Animator animator) {
        ArrayList<Animator> childAnimations;
        if ((animator instanceof AnimatorSet) && (childAnimations = ((AnimatorSet) animator).getChildAnimations()) != null) {
            for (int i = 0; i < childAnimations.size(); i++) {
                a(childAnimations.get(i));
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.f1429d == null) {
                    this.f1429d = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.f1429d);
            }
        }
    }

    private void a(String str, Animator animator) {
        animator.setTarget(this.f1427b.f1433b.a(str));
        if (Build.VERSION.SDK_INT < 21) {
            a(animator);
        }
        b bVar = this.f1427b;
        if (bVar.f1435d == null) {
            bVar.f1435d = new ArrayList<>();
            this.f1427b.f1436e = new a.b.a<>();
        }
        this.f1427b.f1435d.add(animator);
        this.f1427b.f1436e.put(animator, str);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }
}
