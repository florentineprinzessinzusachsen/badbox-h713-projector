package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbsActionBarView extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final a f501a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final Context f502b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected ActionMenuView f503c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ActionMenuPresenter f504d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected int f505e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected androidx.core.f.x f506f;
    private boolean g;
    private boolean h;

    AbsActionBarView(Context context) {
        this(context, null);
    }

    protected static int a(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    public int getAnimatedVisibility() {
        return this.f506f != null ? this.f501a.f508b : getVisibility();
    }

    public int getContentHeight() {
        return this.f505e;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f504d;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.a(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            androidx.core.f.x xVar = this.f506f;
            if (xVar != null) {
                xVar.a();
            }
            super.setVisibility(i);
        }
    }

    protected class a implements androidx.core.f.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f507a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f508b;

        protected a() {
        }

        public a a(androidx.core.f.x xVar, int i) {
            AbsActionBarView.this.f506f = xVar;
            this.f508b = i;
            return this;
        }

        @Override // androidx.core.f.y
        public void b(View view) {
            AbsActionBarView.super.setVisibility(0);
            this.f507a = false;
        }

        @Override // androidx.core.f.y
        public void c(View view) {
            this.f507a = true;
        }

        @Override // androidx.core.f.y
        public void a(View view) {
            if (this.f507a) {
                return;
            }
            AbsActionBarView absActionBarView = AbsActionBarView.this;
            absActionBarView.f506f = null;
            AbsActionBarView.super.setVisibility(this.f508b);
        }
    }

    AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public androidx.core.f.x a(int i, long j) {
        androidx.core.f.x xVar = this.f506f;
        if (xVar != null) {
            xVar.a();
        }
        if (i != 0) {
            androidx.core.f.x xVarA = androidx.core.f.t.a(this);
            xVarA.a(0.0f);
            xVarA.a(j);
            a aVar = this.f501a;
            aVar.a(xVarA, i);
            xVarA.a(aVar);
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        androidx.core.f.x xVarA2 = androidx.core.f.t.a(this);
        xVarA2.a(1.0f);
        xVarA2.a(j);
        a aVar2 = this.f501a;
        aVar2.a(xVarA2, i);
        xVarA2.a(aVar2);
        return xVarA2;
    }

    AbsActionBarView(Context context, AttributeSet attributeSet, int i) {
        int i2;
        super(context, attributeSet, i);
        this.f501a = new a();
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) && (i2 = typedValue.resourceId) != 0) {
            this.f502b = new ContextThemeWrapper(context, i2);
        } else {
            this.f502b = context;
        }
    }

    protected int a(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    protected int a(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }
}
