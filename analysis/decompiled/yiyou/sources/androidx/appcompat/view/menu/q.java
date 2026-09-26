package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.core.f.t;

/* JADX INFO: compiled from: StandardMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
final class q extends k implements PopupWindow.OnDismissListener, AdapterView.OnItemClickListener, m, View.OnKeyListener {
    private static final int v = R$layout.abc_popup_menu_item_layout;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f493b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final g f494c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f f495d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f496e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final int f497f;
    private final int g;
    private final int h;
    final MenuPopupWindow i;
    private PopupWindow.OnDismissListener l;
    private View m;
    View n;
    private m.a o;
    ViewTreeObserver p;
    private boolean q;
    private boolean r;
    private int s;
    private boolean u;
    final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    private final View.OnAttachStateChangeListener k = new b();
    private int t = 0;

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!q.this.b() || q.this.i.k()) {
                return;
            }
            View view = q.this.n;
            if (view == null || !view.isShown()) {
                q.this.dismiss();
            } else {
                q.this.i.show();
            }
        }
    }

    /* JADX INFO: compiled from: StandardMenuPopup.java */
    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = q.this.p;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    q.this.p = view.getViewTreeObserver();
                }
                q qVar = q.this;
                qVar.p.removeGlobalOnLayoutListener(qVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    public q(Context context, g gVar, View view, int i, int i2, boolean z) {
        this.f493b = context;
        this.f494c = gVar;
        this.f496e = z;
        this.f495d = new f(gVar, LayoutInflater.from(context), this.f496e, v);
        this.g = i;
        this.h = i2;
        Resources resources = context.getResources();
        this.f497f = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.m = view;
        this.i = new MenuPopupWindow(this.f493b, null, this.g, this.h);
        gVar.a(this, context);
    }

    private boolean e() {
        View view;
        if (b()) {
            return true;
        }
        if (this.q || (view = this.m) == null) {
            return false;
        }
        this.n = view;
        this.i.a((PopupWindow.OnDismissListener) this);
        this.i.a((AdapterView.OnItemClickListener) this);
        this.i.a(true);
        View view2 = this.n;
        boolean z = this.p == null;
        this.p = view2.getViewTreeObserver();
        if (z) {
            this.p.addOnGlobalLayoutListener(this.j);
        }
        view2.addOnAttachStateChangeListener(this.k);
        this.i.a(view2);
        this.i.f(this.t);
        if (!this.r) {
            this.s = k.a(this.f495d, null, this.f493b, this.f497f);
            this.r = true;
        }
        this.i.e(this.s);
        this.i.g(2);
        this.i.a(d());
        this.i.show();
        ListView listViewF = this.i.f();
        listViewF.setOnKeyListener(this);
        if (this.u && this.f494c.h() != null) {
            FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(this.f493b).inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewF, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            if (textView != null) {
                textView.setText(this.f494c.h());
            }
            frameLayout.setEnabled(false);
            listViewF.addHeaderView(frameLayout, null, false);
        }
        this.i.a((ListAdapter) this.f495d);
        this.i.show();
        return true;
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(int i) {
        this.t = i;
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(g gVar) {
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k
    public void b(boolean z) {
        this.f495d.a(z);
    }

    @Override // androidx.appcompat.view.menu.k
    public void c(int i) {
        this.i.b(i);
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        if (b()) {
            this.i.dismiss();
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView f() {
        return this.i.f();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        this.q = true;
        this.f494c.close();
        ViewTreeObserver viewTreeObserver = this.p;
        if (viewTreeObserver != null) {
            if (!viewTreeObserver.isAlive()) {
                this.p = this.n.getViewTreeObserver();
            }
            this.p.removeGlobalOnLayoutListener(this.j);
            this.p = null;
        }
        this.n.removeOnAttachStateChangeListener(this.k);
        PopupWindow.OnDismissListener onDismissListener = this.l;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 1 || i != 82) {
            return false;
        }
        dismiss();
        return true;
    }

    @Override // androidx.appcompat.view.menu.p
    public void show() {
        if (!e()) {
            throw new IllegalStateException("StandardMenuPopup cannot be used without an anchor");
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(boolean z) {
        this.r = false;
        f fVar = this.f495d;
        if (fVar != null) {
            fVar.notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean b() {
        return !this.q && this.i.b();
    }

    @Override // androidx.appcompat.view.menu.k
    public void c(boolean z) {
        this.u = z;
    }

    @Override // androidx.appcompat.view.menu.k
    public void b(int i) {
        this.i.a(i);
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(m.a aVar) {
        this.o = aVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a(r rVar) {
        if (rVar.hasVisibleItems()) {
            l lVar = new l(this.f493b, rVar, this.n, this.f496e, this.g, this.h);
            lVar.a(this.o);
            lVar.a(k.b(rVar));
            lVar.a(this.l);
            this.l = null;
            this.f494c.a(false);
            int iA = this.i.a();
            int iC = this.i.c();
            if ((Gravity.getAbsoluteGravity(this.t, t.j(this.m)) & 7) == 5) {
                iA += this.m.getWidth();
            }
            if (lVar.a(iA, iC)) {
                m.a aVar = this.o;
                if (aVar == null) {
                    return true;
                }
                aVar.a(rVar);
                return true;
            }
        }
        return false;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        if (gVar != this.f494c) {
            return;
        }
        dismiss();
        m.a aVar = this.o;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(View view) {
        this.m = view;
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.l = onDismissListener;
    }
}
