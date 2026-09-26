package androidx.appcompat.app;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ScrollingTabContainerView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.o;
import androidx.core.f.a0;
import androidx.core.f.t;
import androidx.core.f.x;
import androidx.core.f.y;
import androidx.core.f.z;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* JADX INFO: compiled from: WindowDecorActionBar.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends androidx.appcompat.app.a implements ActionBarOverlayLayout.d {
    private static final Interpolator B = new AccelerateInterpolator();
    private static final Interpolator C = new DecelerateInterpolator();
    final a0 A;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Context f323a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f324b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ActionBarOverlayLayout f325c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    ActionBarContainer f326d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    o f327e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    ActionBarContextView f328f;
    View g;
    ScrollingTabContainerView h;
    private boolean i;
    d j;
    androidx.appcompat.d.b k;
    androidx.appcompat.d.b.a l;
    private boolean m;
    private ArrayList<androidx.appcompat.app.a.b> n;
    private boolean o;
    private int p;
    boolean q;
    boolean r;
    boolean s;
    private boolean t;
    private boolean u;
    androidx.appcompat.d.h v;
    private boolean w;
    boolean x;
    final y y;
    final y z;

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class a extends z {
        a() {
        }

        @Override // androidx.core.f.y
        public void a(View view) {
            View view2;
            k kVar = k.this;
            if (kVar.q && (view2 = kVar.g) != null) {
                view2.setTranslationY(0.0f);
                k.this.f326d.setTranslationY(0.0f);
            }
            k.this.f326d.setVisibility(8);
            k.this.f326d.setTransitioning(false);
            k kVar2 = k.this;
            kVar2.v = null;
            kVar2.l();
            ActionBarOverlayLayout actionBarOverlayLayout = k.this.f325c;
            if (actionBarOverlayLayout != null) {
                t.u(actionBarOverlayLayout);
            }
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class b extends z {
        b() {
        }

        @Override // androidx.core.f.y
        public void a(View view) {
            k kVar = k.this;
            kVar.v = null;
            kVar.f326d.requestLayout();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    class c implements a0 {
        c() {
        }

        @Override // androidx.core.f.a0
        public void a(View view) {
            ((View) k.this.f326d.getParent()).invalidate();
        }
    }

    /* JADX INFO: compiled from: WindowDecorActionBar.java */
    public class d extends androidx.appcompat.d.b implements androidx.appcompat.view.menu.g.a {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Context f332c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final androidx.appcompat.view.menu.g f333d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private androidx.appcompat.d.b.a f334e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private WeakReference<View> f335f;

        public d(Context context, androidx.appcompat.d.b.a aVar) {
            this.f332c = context;
            this.f334e = aVar;
            androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(context);
            gVar.c(1);
            this.f333d = gVar;
            this.f333d.a(this);
        }

        @Override // androidx.appcompat.d.b
        public void a() {
            k kVar = k.this;
            if (kVar.j != this) {
                return;
            }
            if (k.a(kVar.r, kVar.s, false)) {
                this.f334e.a(this);
            } else {
                k kVar2 = k.this;
                kVar2.k = this;
                kVar2.l = this.f334e;
            }
            this.f334e = null;
            k.this.e(false);
            k.this.f328f.a();
            k.this.f327e.i().sendAccessibilityEvent(32);
            k kVar3 = k.this;
            kVar3.f325c.setHideOnContentScrollEnabled(kVar3.x);
            k.this.j = null;
        }

        @Override // androidx.appcompat.d.b
        public void b(CharSequence charSequence) {
            k.this.f328f.setTitle(charSequence);
        }

        @Override // androidx.appcompat.d.b
        public Menu c() {
            return this.f333d;
        }

        @Override // androidx.appcompat.d.b
        public MenuInflater d() {
            return new androidx.appcompat.d.g(this.f332c);
        }

        @Override // androidx.appcompat.d.b
        public CharSequence e() {
            return k.this.f328f.getSubtitle();
        }

        @Override // androidx.appcompat.d.b
        public CharSequence g() {
            return k.this.f328f.getTitle();
        }

        @Override // androidx.appcompat.d.b
        public void i() {
            if (k.this.j != this) {
                return;
            }
            this.f333d.s();
            try {
                this.f334e.a(this, this.f333d);
            } finally {
                this.f333d.r();
            }
        }

        @Override // androidx.appcompat.d.b
        public boolean j() {
            return k.this.f328f.b();
        }

        public boolean k() {
            this.f333d.s();
            try {
                return this.f334e.b(this, this.f333d);
            } finally {
                this.f333d.r();
            }
        }

        @Override // androidx.appcompat.d.b
        public void b(int i) {
            b(k.this.f323a.getResources().getString(i));
        }

        @Override // androidx.appcompat.d.b
        public View b() {
            WeakReference<View> weakReference = this.f335f;
            if (weakReference != null) {
                return weakReference.get();
            }
            return null;
        }

        @Override // androidx.appcompat.d.b
        public void a(View view) {
            k.this.f328f.setCustomView(view);
            this.f335f = new WeakReference<>(view);
        }

        @Override // androidx.appcompat.d.b
        public void a(CharSequence charSequence) {
            k.this.f328f.setSubtitle(charSequence);
        }

        @Override // androidx.appcompat.d.b
        public void a(int i) {
            a((CharSequence) k.this.f323a.getResources().getString(i));
        }

        @Override // androidx.appcompat.d.b
        public void a(boolean z) {
            super.a(z);
            k.this.f328f.setTitleOptional(z);
        }

        @Override // androidx.appcompat.view.menu.g.a
        public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
            androidx.appcompat.d.b.a aVar = this.f334e;
            if (aVar != null) {
                return aVar.a(this, menuItem);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.g.a
        public void a(androidx.appcompat.view.menu.g gVar) {
            if (this.f334e == null) {
                return;
            }
            i();
            k.this.f328f.d();
        }
    }

    public k(Activity activity, boolean z) {
        new ArrayList();
        this.n = new ArrayList<>();
        this.p = 0;
        this.q = true;
        this.u = true;
        this.y = new a();
        this.z = new b();
        this.A = new c();
        View decorView = activity.getWindow().getDecorView();
        b(decorView);
        if (z) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private o a(View view) {
        if (view instanceof o) {
            return (o) view;
        }
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getWrapper();
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't make a decor toolbar out of ");
        sb.append(view != 0 ? view.getClass().getSimpleName() : "null");
        throw new IllegalStateException(sb.toString());
    }

    static boolean a(boolean z, boolean z2, boolean z3) {
        if (z3) {
            return true;
        }
        return (z || z2) ? false : true;
    }

    private void b(View view) {
        this.f325c = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        ActionBarOverlayLayout actionBarOverlayLayout = this.f325c;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        this.f327e = a(view.findViewById(R$id.action_bar));
        this.f328f = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        this.f326d = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        o oVar = this.f327e;
        if (oVar == null || this.f328f == null || this.f326d == null) {
            throw new IllegalStateException(k.class.getSimpleName() + " can only be used with a compatible window decor layout");
        }
        this.f323a = oVar.j();
        boolean z = (this.f327e.h() & 4) != 0;
        if (z) {
            this.i = true;
        }
        androidx.appcompat.d.a aVarA = androidx.appcompat.d.a.a(this.f323a);
        j(aVarA.a() || z);
        k(aVarA.f());
        TypedArray typedArrayObtainStyledAttributes = this.f323a.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            i(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            a(dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void k(boolean z) {
        this.o = z;
        if (this.o) {
            this.f326d.setTabContainer(null);
            this.f327e.a(this.h);
        } else {
            this.f327e.a((ScrollingTabContainerView) null);
            this.f326d.setTabContainer(this.h);
        }
        boolean z2 = m() == 2;
        ScrollingTabContainerView scrollingTabContainerView = this.h;
        if (scrollingTabContainerView != null) {
            if (z2) {
                scrollingTabContainerView.setVisibility(0);
                ActionBarOverlayLayout actionBarOverlayLayout = this.f325c;
                if (actionBarOverlayLayout != null) {
                    t.u(actionBarOverlayLayout);
                }
            } else {
                scrollingTabContainerView.setVisibility(8);
            }
        }
        this.f327e.b(!this.o && z2);
        this.f325c.setHasNonEmbeddedTabs(!this.o && z2);
    }

    private void n() {
        if (this.t) {
            this.t = false;
            ActionBarOverlayLayout actionBarOverlayLayout = this.f325c;
            if (actionBarOverlayLayout != null) {
                actionBarOverlayLayout.setShowingForActionMode(false);
            }
            l(false);
        }
    }

    private boolean o() {
        return t.r(this.f326d);
    }

    private void p() {
        if (this.t) {
            return;
        }
        this.t = true;
        ActionBarOverlayLayout actionBarOverlayLayout = this.f325c;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setShowingForActionMode(true);
        }
        l(false);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void c() {
        if (this.s) {
            return;
        }
        this.s = true;
        l(true);
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void d() {
    }

    @Override // androidx.appcompat.app.a
    public void d(boolean z) {
        androidx.appcompat.d.h hVar;
        this.w = z;
        if (z || (hVar = this.v) == null) {
            return;
        }
        hVar.a();
    }

    public void e(boolean z) {
        x xVarA;
        x xVarA2;
        if (z) {
            p();
        } else {
            n();
        }
        if (!o()) {
            if (z) {
                this.f327e.a(4);
                this.f328f.setVisibility(0);
                return;
            } else {
                this.f327e.a(0);
                this.f328f.setVisibility(8);
                return;
            }
        }
        if (z) {
            xVarA2 = this.f327e.a(4, 100L);
            xVarA = this.f328f.a(0, 200L);
        } else {
            xVarA = this.f327e.a(0, 200L);
            xVarA2 = this.f328f.a(8, 100L);
        }
        androidx.appcompat.d.h hVar = new androidx.appcompat.d.h();
        hVar.a(xVarA2, xVarA);
        hVar.c();
    }

    public void f(boolean z) {
        View view;
        androidx.appcompat.d.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
        }
        if (this.p != 0 || (!this.w && !z)) {
            this.y.a(null);
            return;
        }
        this.f326d.setAlpha(1.0f);
        this.f326d.setTransitioning(true);
        androidx.appcompat.d.h hVar2 = new androidx.appcompat.d.h();
        float f2 = -this.f326d.getHeight();
        if (z) {
            int[] iArr = {0, 0};
            this.f326d.getLocationInWindow(iArr);
            f2 -= iArr[1];
        }
        x xVarA = t.a(this.f326d);
        xVarA.b(f2);
        xVarA.a(this.A);
        hVar2.a(xVarA);
        if (this.q && (view = this.g) != null) {
            x xVarA2 = t.a(view);
            xVarA2.b(f2);
            hVar2.a(xVarA2);
        }
        hVar2.a(B);
        hVar2.a(250L);
        hVar2.a(this.y);
        this.v = hVar2;
        hVar2.c();
    }

    @Override // androidx.appcompat.app.a
    public int g() {
        return this.f327e.h();
    }

    public void h(boolean z) {
        a(z ? 4 : 0, 4);
    }

    public void i(boolean z) {
        if (z && !this.f325c.i()) {
            throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
        }
        this.x = z;
        this.f325c.setHideOnContentScrollEnabled(z);
    }

    public void j(boolean z) {
        this.f327e.a(z);
    }

    void l() {
        androidx.appcompat.d.b.a aVar = this.l;
        if (aVar != null) {
            aVar.a(this.k);
            this.k = null;
            this.l = null;
        }
    }

    public int m() {
        return this.f327e.k();
    }

    public void g(boolean z) {
        View view;
        View view2;
        androidx.appcompat.d.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
        }
        this.f326d.setVisibility(0);
        if (this.p == 0 && (this.w || z)) {
            this.f326d.setTranslationY(0.0f);
            float f2 = -this.f326d.getHeight();
            if (z) {
                int[] iArr = {0, 0};
                this.f326d.getLocationInWindow(iArr);
                f2 -= iArr[1];
            }
            this.f326d.setTranslationY(f2);
            androidx.appcompat.d.h hVar2 = new androidx.appcompat.d.h();
            x xVarA = t.a(this.f326d);
            xVarA.b(0.0f);
            xVarA.a(this.A);
            hVar2.a(xVarA);
            if (this.q && (view2 = this.g) != null) {
                view2.setTranslationY(f2);
                x xVarA2 = t.a(this.g);
                xVarA2.b(0.0f);
                hVar2.a(xVarA2);
            }
            hVar2.a(C);
            hVar2.a(250L);
            hVar2.a(this.z);
            this.v = hVar2;
            hVar2.c();
        } else {
            this.f326d.setAlpha(1.0f);
            this.f326d.setTranslationY(0.0f);
            if (this.q && (view = this.g) != null) {
                view.setTranslationY(0.0f);
            }
            this.z.a(null);
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.f325c;
        if (actionBarOverlayLayout != null) {
            t.u(actionBarOverlayLayout);
        }
    }

    @Override // androidx.appcompat.app.a
    public Context h() {
        if (this.f324b == null) {
            TypedValue typedValue = new TypedValue();
            this.f323a.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.f324b = new ContextThemeWrapper(this.f323a, i);
            } else {
                this.f324b = this.f323a;
            }
        }
        return this.f324b;
    }

    @Override // androidx.appcompat.app.a
    public void c(boolean z) {
        if (this.i) {
            return;
        }
        h(z);
    }

    private void l(boolean z) {
        if (a(this.r, this.s, this.t)) {
            if (this.u) {
                return;
            }
            this.u = true;
            g(z);
            return;
        }
        if (this.u) {
            this.u = false;
            f(z);
        }
    }

    public void a(float f2) {
        t.a(this.f326d, f2);
    }

    @Override // androidx.appcompat.app.a
    public void a(Configuration configuration) {
        k(androidx.appcompat.d.a.a(this.f323a).f());
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(int i) {
        this.p = i;
    }

    @Override // androidx.appcompat.app.a
    public void a(CharSequence charSequence) {
        this.f327e.setWindowTitle(charSequence);
    }

    public void a(int i, int i2) {
        int iH = this.f327e.h();
        if ((i2 & 4) != 0) {
            this.i = true;
        }
        this.f327e.c((i & i2) | ((i2 ^ (-1)) & iH));
    }

    public k(Dialog dialog) {
        new ArrayList();
        this.n = new ArrayList<>();
        this.p = 0;
        this.q = true;
        this.u = true;
        this.y = new a();
        this.z = new b();
        this.A = new c();
        b(dialog.getWindow().getDecorView());
    }

    @Override // androidx.appcompat.app.a
    public androidx.appcompat.d.b a(androidx.appcompat.d.b.a aVar) {
        d dVar = this.j;
        if (dVar != null) {
            dVar.a();
        }
        this.f325c.setHideOnContentScrollEnabled(false);
        this.f328f.c();
        d dVar2 = new d(this.f328f.getContext(), aVar);
        if (!dVar2.k()) {
            return null;
        }
        this.j = dVar2;
        dVar2.i();
        this.f328f.a(dVar2);
        e(true);
        this.f328f.sendAccessibilityEvent(32);
        return dVar2;
    }

    @Override // androidx.appcompat.app.a
    public void b(boolean z) {
        if (z == this.m) {
            return;
        }
        this.m = z;
        int size = this.n.size();
        for (int i = 0; i < size; i++) {
            this.n.get(i).a(z);
        }
    }

    @Override // androidx.appcompat.app.a
    public boolean f() {
        o oVar = this.f327e;
        if (oVar == null || !oVar.m()) {
            return false;
        }
        this.f327e.collapseActionView();
        return true;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a(boolean z) {
        this.q = z;
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void b() {
        androidx.appcompat.d.h hVar = this.v;
        if (hVar != null) {
            hVar.a();
            this.v = null;
        }
    }

    @Override // androidx.appcompat.widget.ActionBarOverlayLayout.d
    public void a() {
        if (this.s) {
            this.s = false;
            l(true);
        }
    }

    @Override // androidx.appcompat.app.a
    public boolean a(int i, KeyEvent keyEvent) {
        Menu menuC;
        d dVar = this.j;
        if (dVar == null || (menuC = dVar.c()) == null) {
            return false;
        }
        menuC.setQwertyMode(KeyCharacterMap.load(keyEvent != null ? keyEvent.getDeviceId() : -1).getKeyboardType() != 1);
        return menuC.performShortcut(i, keyEvent, 0);
    }
}
