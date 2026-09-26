package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$drawable;
import androidx.appcompat.R$id;
import androidx.appcompat.R$string;
import androidx.appcompat.R$styleable;

/* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class e0 implements o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    Toolbar f743a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f744b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private View f745c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private View f746d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Drawable f747e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Drawable f748f;
    private Drawable g;
    private boolean h;
    CharSequence i;
    private CharSequence j;
    private CharSequence k;
    Window.Callback l;
    boolean m;
    private ActionMenuPresenter n;
    private int o;
    private int p;
    private Drawable q;

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final androidx.appcompat.view.menu.a f749a;

        a() {
            this.f749a = new androidx.appcompat.view.menu.a(e0.this.f743a.getContext(), 0, R.id.home, 0, 0, e0.this.i);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            e0 e0Var = e0.this;
            Window.Callback callback = e0Var.l;
            if (callback == null || !e0Var.m) {
                return;
            }
            callback.onMenuItemSelected(0, this.f749a);
        }
    }

    /* JADX INFO: compiled from: ToolbarWidgetWrapper.java */
    class b extends androidx.core.f.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f751a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f752b;

        b(int i) {
            this.f752b = i;
        }

        @Override // androidx.core.f.y
        public void a(View view) {
            if (this.f751a) {
                return;
            }
            e0.this.f743a.setVisibility(this.f752b);
        }

        @Override // androidx.core.f.z, androidx.core.f.y
        public void b(View view) {
            e0.this.f743a.setVisibility(0);
        }

        @Override // androidx.core.f.z, androidx.core.f.y
        public void c(View view) {
            this.f751a = true;
        }
    }

    public e0(Toolbar toolbar, boolean z) {
        this(toolbar, z, R$string.abc_action_bar_up_description, R$drawable.abc_ic_ab_back_material);
    }

    private int o() {
        if (this.f743a.getNavigationIcon() == null) {
            return 11;
        }
        this.q = this.f743a.getNavigationIcon();
        return 15;
    }

    private void p() {
        if ((this.f744b & 4) != 0) {
            if (TextUtils.isEmpty(this.k)) {
                this.f743a.setNavigationContentDescription(this.p);
            } else {
                this.f743a.setNavigationContentDescription(this.k);
            }
        }
    }

    private void q() {
        if ((this.f744b & 4) == 0) {
            this.f743a.setNavigationIcon((Drawable) null);
            return;
        }
        Toolbar toolbar = this.f743a;
        Drawable drawable = this.g;
        if (drawable == null) {
            drawable = this.q;
        }
        toolbar.setNavigationIcon(drawable);
    }

    private void r() {
        Drawable drawable;
        int i = this.f744b;
        if ((i & 2) == 0) {
            drawable = null;
        } else if ((i & 1) == 0 || (drawable = this.f748f) == null) {
            drawable = this.f747e;
        }
        this.f743a.setLogo(drawable);
    }

    public void a(Drawable drawable) {
        this.f748f = drawable;
        r();
    }

    @Override // androidx.appcompat.widget.o
    public void a(boolean z) {
    }

    public void b(CharSequence charSequence) {
        this.j = charSequence;
        if ((this.f744b & 8) != 0) {
            this.f743a.setSubtitle(charSequence);
        }
    }

    public void c(CharSequence charSequence) {
        this.h = true;
        d(charSequence);
    }

    @Override // androidx.appcompat.widget.o
    public void collapseActionView() {
        this.f743a.c();
    }

    public void d(int i) {
        if (i == this.p) {
            return;
        }
        this.p = i;
        if (TextUtils.isEmpty(this.f743a.getNavigationContentDescription())) {
            e(this.p);
        }
    }

    @Override // androidx.appcompat.widget.o
    public void e() {
        this.m = true;
    }

    @Override // androidx.appcompat.widget.o
    public boolean f() {
        return this.f743a.b();
    }

    @Override // androidx.appcompat.widget.o
    public void g() {
        this.f743a.d();
    }

    @Override // androidx.appcompat.widget.o
    public CharSequence getTitle() {
        return this.f743a.getTitle();
    }

    @Override // androidx.appcompat.widget.o
    public int h() {
        return this.f744b;
    }

    @Override // androidx.appcompat.widget.o
    public ViewGroup i() {
        return this.f743a;
    }

    @Override // androidx.appcompat.widget.o
    public Context j() {
        return this.f743a.getContext();
    }

    @Override // androidx.appcompat.widget.o
    public int k() {
        return this.o;
    }

    @Override // androidx.appcompat.widget.o
    public void l() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.o
    public boolean m() {
        return this.f743a.f();
    }

    @Override // androidx.appcompat.widget.o
    public void n() {
        Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
    }

    @Override // androidx.appcompat.widget.o
    public void setIcon(int i) {
        setIcon(i != 0 ? androidx.appcompat.a.a.a.c(j(), i) : null);
    }

    @Override // androidx.appcompat.widget.o
    public void setWindowCallback(Window.Callback callback) {
        this.l = callback;
    }

    @Override // androidx.appcompat.widget.o
    public void setWindowTitle(CharSequence charSequence) {
        if (this.h) {
            return;
        }
        d(charSequence);
    }

    public e0(Toolbar toolbar, boolean z, int i, int i2) {
        Drawable drawable;
        this.o = 0;
        this.p = 0;
        this.f743a = toolbar;
        this.i = toolbar.getTitle();
        this.j = toolbar.getSubtitle();
        this.h = this.i != null;
        this.g = toolbar.getNavigationIcon();
        d0 d0VarA = d0.a(toolbar.getContext(), null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        this.q = d0VarA.b(R$styleable.ActionBar_homeAsUpIndicator);
        if (z) {
            CharSequence charSequenceE = d0VarA.e(R$styleable.ActionBar_title);
            if (!TextUtils.isEmpty(charSequenceE)) {
                c(charSequenceE);
            }
            CharSequence charSequenceE2 = d0VarA.e(R$styleable.ActionBar_subtitle);
            if (!TextUtils.isEmpty(charSequenceE2)) {
                b(charSequenceE2);
            }
            Drawable drawableB = d0VarA.b(R$styleable.ActionBar_logo);
            if (drawableB != null) {
                a(drawableB);
            }
            Drawable drawableB2 = d0VarA.b(R$styleable.ActionBar_icon);
            if (drawableB2 != null) {
                setIcon(drawableB2);
            }
            if (this.g == null && (drawable = this.q) != null) {
                b(drawable);
            }
            c(d0VarA.d(R$styleable.ActionBar_displayOptions, 0));
            int iG = d0VarA.g(R$styleable.ActionBar_customNavigationLayout, 0);
            if (iG != 0) {
                a(LayoutInflater.from(this.f743a.getContext()).inflate(iG, (ViewGroup) this.f743a, false));
                c(this.f744b | 16);
            }
            int iF = d0VarA.f(R$styleable.ActionBar_height, 0);
            if (iF > 0) {
                ViewGroup.LayoutParams layoutParams = this.f743a.getLayoutParams();
                layoutParams.height = iF;
                this.f743a.setLayoutParams(layoutParams);
            }
            int iB = d0VarA.b(R$styleable.ActionBar_contentInsetStart, -1);
            int iB2 = d0VarA.b(R$styleable.ActionBar_contentInsetEnd, -1);
            if (iB >= 0 || iB2 >= 0) {
                this.f743a.a(Math.max(iB, 0), Math.max(iB2, 0));
            }
            int iG2 = d0VarA.g(R$styleable.ActionBar_titleTextStyle, 0);
            if (iG2 != 0) {
                Toolbar toolbar2 = this.f743a;
                toolbar2.b(toolbar2.getContext(), iG2);
            }
            int iG3 = d0VarA.g(R$styleable.ActionBar_subtitleTextStyle, 0);
            if (iG3 != 0) {
                Toolbar toolbar3 = this.f743a;
                toolbar3.a(toolbar3.getContext(), iG3);
            }
            int iG4 = d0VarA.g(R$styleable.ActionBar_popupTheme, 0);
            if (iG4 != 0) {
                this.f743a.setPopupTheme(iG4);
            }
        } else {
            this.f744b = o();
        }
        d0VarA.a();
        d(i);
        this.k = this.f743a.getNavigationContentDescription();
        this.f743a.setNavigationOnClickListener(new a());
    }

    public void e(int i) {
        a(i == 0 ? null : j().getString(i));
    }

    @Override // androidx.appcompat.widget.o
    public void setIcon(Drawable drawable) {
        this.f747e = drawable;
        r();
    }

    @Override // androidx.appcompat.widget.o
    public boolean a() {
        return this.f743a.h();
    }

    @Override // androidx.appcompat.widget.o
    public boolean c() {
        return this.f743a.g();
    }

    @Override // androidx.appcompat.widget.o
    public void a(Menu menu, androidx.appcompat.view.menu.m.a aVar) {
        if (this.n == null) {
            this.n = new ActionMenuPresenter(this.f743a.getContext());
            this.n.a(R$id.action_menu_presenter);
        }
        this.n.a(aVar);
        this.f743a.a((androidx.appcompat.view.menu.g) menu, this.n);
    }

    @Override // androidx.appcompat.widget.o
    public void b(int i) {
        a(i != 0 ? androidx.appcompat.a.a.a.c(j(), i) : null);
    }

    @Override // androidx.appcompat.widget.o
    public void c(int i) {
        View view;
        int i2 = this.f744b ^ i;
        this.f744b = i;
        if (i2 != 0) {
            if ((i2 & 4) != 0) {
                if ((i & 4) != 0) {
                    p();
                }
                q();
            }
            if ((i2 & 3) != 0) {
                r();
            }
            if ((i2 & 8) != 0) {
                if ((i & 8) != 0) {
                    this.f743a.setTitle(this.i);
                    this.f743a.setSubtitle(this.j);
                } else {
                    this.f743a.setTitle((CharSequence) null);
                    this.f743a.setSubtitle((CharSequence) null);
                }
            }
            if ((i2 & 16) == 0 || (view = this.f746d) == null) {
                return;
            }
            if ((i & 16) != 0) {
                this.f743a.addView(view);
            } else {
                this.f743a.removeView(view);
            }
        }
    }

    private void d(CharSequence charSequence) {
        this.i = charSequence;
        if ((this.f744b & 8) != 0) {
            this.f743a.setTitle(charSequence);
        }
    }

    @Override // androidx.appcompat.widget.o
    public boolean b() {
        return this.f743a.i();
    }

    @Override // androidx.appcompat.widget.o
    public void b(boolean z) {
        this.f743a.setCollapsible(z);
    }

    public void b(Drawable drawable) {
        this.g = drawable;
        q();
    }

    @Override // androidx.appcompat.widget.o
    public boolean d() {
        return this.f743a.k();
    }

    @Override // androidx.appcompat.widget.o
    public void a(ScrollingTabContainerView scrollingTabContainerView) {
        View view = this.f745c;
        if (view != null) {
            ViewParent parent = view.getParent();
            Toolbar toolbar = this.f743a;
            if (parent == toolbar) {
                toolbar.removeView(this.f745c);
            }
        }
        this.f745c = scrollingTabContainerView;
        if (scrollingTabContainerView == null || this.o != 2) {
            return;
        }
        this.f743a.addView(this.f745c, 0);
        Toolbar.e eVar = (Toolbar.e) this.f745c.getLayoutParams();
        ((ViewGroup.MarginLayoutParams) eVar).width = -2;
        ((ViewGroup.MarginLayoutParams) eVar).height = -2;
        eVar.f293a = 8388691;
        scrollingTabContainerView.setAllowCollapse(true);
    }

    public void a(View view) {
        View view2 = this.f746d;
        if (view2 != null && (this.f744b & 16) != 0) {
            this.f743a.removeView(view2);
        }
        this.f746d = view;
        if (view == null || (this.f744b & 16) == 0) {
            return;
        }
        this.f743a.addView(this.f746d);
    }

    @Override // androidx.appcompat.widget.o
    public androidx.core.f.x a(int i, long j) {
        androidx.core.f.x xVarA = androidx.core.f.t.a(this.f743a);
        xVarA.a(i == 0 ? 1.0f : 0.0f);
        xVarA.a(j);
        xVarA.a(new b(i));
        return xVarA;
    }

    public void a(CharSequence charSequence) {
        this.k = charSequence;
        p();
    }

    @Override // androidx.appcompat.widget.o
    public void a(int i) {
        this.f743a.setVisibility(i);
    }
}
