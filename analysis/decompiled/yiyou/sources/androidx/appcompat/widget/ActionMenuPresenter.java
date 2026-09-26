package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class ActionMenuPresenter extends androidx.appcompat.view.menu.b implements androidx.core.f.b.a {
    final e A;
    int B;
    OverflowMenuButton i;
    private Drawable j;
    private boolean k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private int u;
    private final SparseBooleanArray v;
    d w;
    a x;
    c y;
    private b z;

    private class OverflowMenuButton extends AppCompatImageView implements ActionMenuView.a {

        class a extends r {
            a(View view, ActionMenuPresenter actionMenuPresenter) {
                super(view);
            }

            @Override // androidx.appcompat.widget.r
            public androidx.appcompat.view.menu.p a() {
                d dVar = ActionMenuPresenter.this.w;
                if (dVar == null) {
                    return null;
                }
                return dVar.b();
            }

            @Override // androidx.appcompat.widget.r
            public boolean b() {
                ActionMenuPresenter.this.i();
                return true;
            }

            @Override // androidx.appcompat.widget.r
            public boolean c() {
                ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
                if (actionMenuPresenter.y != null) {
                    return false;
                }
                actionMenuPresenter.e();
                return true;
            }
        }

        public OverflowMenuButton(Context context) {
            super(context, null, R$attr.actionOverflowButtonStyle);
            setClickable(true);
            setFocusable(true);
            setVisibility(0);
            setEnabled(true);
            f0.a(this, getContentDescription());
            setOnTouchListener(new a(this, ActionMenuPresenter.this));
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean a() {
            return false;
        }

        @Override // androidx.appcompat.widget.ActionMenuView.a
        public boolean b() {
            return false;
        }

        @Override // android.view.View
        public boolean performClick() {
            if (super.performClick()) {
                return true;
            }
            playSoundEffect(0);
            ActionMenuPresenter.this.i();
            return true;
        }

        @Override // android.widget.ImageView
        protected boolean setFrame(int i, int i2, int i3, int i4) {
            boolean frame = super.setFrame(i, i2, i3, i4);
            Drawable drawable = getDrawable();
            Drawable background = getBackground();
            if (drawable != null && background != null) {
                int width = getWidth();
                int height = getHeight();
                int iMax = Math.max(width, height) / 2;
                int paddingLeft = (width + (getPaddingLeft() - getPaddingRight())) / 2;
                int paddingTop = (height + (getPaddingTop() - getPaddingBottom())) / 2;
                androidx.core.graphics.drawable.a.a(background, paddingLeft - iMax, paddingTop - iMax, paddingLeft + iMax, paddingTop + iMax);
            }
            return frame;
        }
    }

    private class a extends androidx.appcompat.view.menu.l {
        public a(Context context, androidx.appcompat.view.menu.r rVar, View view) {
            super(context, rVar, view, false, R$attr.actionOverflowMenuStyle);
            if (!((androidx.appcompat.view.menu.j) rVar.getItem()).h()) {
                View view2 = ActionMenuPresenter.this.i;
                a(view2 == null ? (View) ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).h : view2);
            }
            a(ActionMenuPresenter.this.A);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void d() {
            ActionMenuPresenter actionMenuPresenter = ActionMenuPresenter.this;
            actionMenuPresenter.x = null;
            actionMenuPresenter.B = 0;
            super.d();
        }
    }

    private class b extends ActionMenuItemView.b {
        b() {
        }

        @Override // androidx.appcompat.view.menu.ActionMenuItemView.b
        public androidx.appcompat.view.menu.p a() {
            a aVar = ActionMenuPresenter.this.x;
            if (aVar != null) {
                return aVar.b();
            }
            return null;
        }
    }

    private class c implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private d f528a;

        public c(d dVar) {
            this.f528a = dVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f431c != null) {
                ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f431c.a();
            }
            View view = (View) ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).h;
            if (view != null && view.getWindowToken() != null && this.f528a.f()) {
                ActionMenuPresenter.this.w = this.f528a;
            }
            ActionMenuPresenter.this.y = null;
        }
    }

    private class d extends androidx.appcompat.view.menu.l {
        public d(Context context, androidx.appcompat.view.menu.g gVar, View view, boolean z) {
            super(context, gVar, view, z, R$attr.actionOverflowMenuStyle);
            a(8388613);
            a(ActionMenuPresenter.this.A);
        }

        @Override // androidx.appcompat.view.menu.l
        protected void d() {
            if (((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f431c != null) {
                ((androidx.appcompat.view.menu.b) ActionMenuPresenter.this).f431c.close();
            }
            ActionMenuPresenter.this.w = null;
            super.d();
        }
    }

    public ActionMenuPresenter(Context context) {
        super(context, R$layout.abc_action_menu_layout, R$layout.abc_action_menu_item_layout);
        this.v = new SparseBooleanArray();
        this.A = new e();
    }

    public boolean g() {
        return this.y != null || h();
    }

    public boolean h() {
        d dVar = this.w;
        return dVar != null && dVar.c();
    }

    public boolean i() {
        androidx.appcompat.view.menu.g gVar;
        if (!this.l || h() || (gVar = this.f431c) == null || this.h == null || this.y != null || gVar.j().isEmpty()) {
            return false;
        }
        this.y = new c(new d(this.f430b, this.f431c, this.i, true));
        ((View) this.h).post(this.y);
        super.a((androidx.appcompat.view.menu.r) null);
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void a(Context context, androidx.appcompat.view.menu.g gVar) {
        super.a(context, gVar);
        Resources resources = context.getResources();
        androidx.appcompat.d.a aVarA = androidx.appcompat.d.a.a(context);
        if (!this.m) {
            this.l = aVarA.g();
        }
        if (!this.s) {
            this.n = aVarA.b();
        }
        if (!this.q) {
            this.p = aVarA.c();
        }
        int measuredWidth = this.n;
        if (this.l) {
            if (this.i == null) {
                this.i = new OverflowMenuButton(this.f429a);
                if (this.k) {
                    this.i.setImageDrawable(this.j);
                    this.j = null;
                    this.k = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.i.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.i.getMeasuredWidth();
        } else {
            this.i = null;
        }
        this.o = measuredWidth;
        this.u = (int) (resources.getDisplayMetrics().density * 56.0f);
    }

    @Override // androidx.appcompat.view.menu.b
    public androidx.appcompat.view.menu.n b(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.n nVar = this.h;
        androidx.appcompat.view.menu.n nVarB = super.b(viewGroup);
        if (nVar != nVarB) {
            ((ActionMenuView) nVarB).setPresenter(this);
        }
        return nVarB;
    }

    public void c(boolean z) {
        this.t = z;
    }

    public void d(boolean z) {
        this.l = z;
        this.m = true;
    }

    public boolean e() {
        Object obj;
        c cVar = this.y;
        if (cVar != null && (obj = this.h) != null) {
            ((View) obj).removeCallbacks(cVar);
            this.y = null;
            return true;
        }
        d dVar = this.w;
        if (dVar == null) {
            return false;
        }
        dVar.a();
        return true;
    }

    public boolean f() {
        a aVar = this.x;
        if (aVar == null) {
            return false;
        }
        aVar.a();
        return true;
    }

    private class e implements androidx.appcompat.view.menu.m.a {
        e() {
        }

        @Override // androidx.appcompat.view.menu.m.a
        public boolean a(androidx.appcompat.view.menu.g gVar) {
            if (gVar == null) {
                return false;
            }
            ActionMenuPresenter.this.B = ((androidx.appcompat.view.menu.r) gVar).getItem().getItemId();
            androidx.appcompat.view.menu.m.a aVarB = ActionMenuPresenter.this.b();
            if (aVarB != null) {
                return aVarB.a(gVar);
            }
            return false;
        }

        @Override // androidx.appcompat.view.menu.m.a
        public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
            if (gVar instanceof androidx.appcompat.view.menu.r) {
                gVar.m().a(false);
            }
            androidx.appcompat.view.menu.m.a aVarB = ActionMenuPresenter.this.b();
            if (aVarB != null) {
                aVarB.a(gVar, z);
            }
        }
    }

    public boolean c() {
        return e() | f();
    }

    public Drawable d() {
        OverflowMenuButton overflowMenuButton = this.i;
        if (overflowMenuButton != null) {
            return overflowMenuButton.getDrawable();
        }
        if (this.k) {
            return this.j;
        }
        return null;
    }

    @Override // androidx.core.f.b.a
    public void b(boolean z) {
        if (z) {
            super.a((androidx.appcompat.view.menu.r) null);
            return;
        }
        androidx.appcompat.view.menu.g gVar = this.f431c;
        if (gVar != null) {
            gVar.a(false);
        }
    }

    public void a(Configuration configuration) {
        if (!this.q) {
            this.p = androidx.appcompat.d.a.a(this.f430b).c();
        }
        androidx.appcompat.view.menu.g gVar = this.f431c;
        if (gVar != null) {
            gVar.b(true);
        }
    }

    public void a(Drawable drawable) {
        OverflowMenuButton overflowMenuButton = this.i;
        if (overflowMenuButton != null) {
            overflowMenuButton.setImageDrawable(drawable);
        } else {
            this.k = true;
            this.j = drawable;
        }
    }

    @Override // androidx.appcompat.view.menu.b
    public View a(androidx.appcompat.view.menu.j jVar, View view, ViewGroup viewGroup) {
        View actionView = jVar.getActionView();
        if (actionView == null || jVar.f()) {
            actionView = super.a(jVar, view, viewGroup);
        }
        actionView.setVisibility(jVar.isActionViewExpanded() ? 8 : 0);
        ActionMenuView actionMenuView = (ActionMenuView) viewGroup;
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        if (!actionMenuView.checkLayoutParams(layoutParams)) {
            actionView.setLayoutParams(actionMenuView.generateLayoutParams(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.b
    public void a(androidx.appcompat.view.menu.j jVar, androidx.appcompat.view.menu.n.a aVar) {
        aVar.a(jVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) aVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.h);
        if (this.z == null) {
            this.z = new b();
        }
        actionMenuItemView.setPopupCallback(this.z);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean a(int i, androidx.appcompat.view.menu.j jVar) {
        return jVar.h();
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void a(boolean z) {
        super.a(z);
        ((View) this.h).requestLayout();
        androidx.appcompat.view.menu.g gVar = this.f431c;
        boolean z2 = false;
        if (gVar != null) {
            ArrayList<androidx.appcompat.view.menu.j> arrayListC = gVar.c();
            int size = arrayListC.size();
            for (int i = 0; i < size; i++) {
                androidx.core.f.b bVarA = arrayListC.get(i).a();
                if (bVarA != null) {
                    bVarA.a(this);
                }
            }
        }
        androidx.appcompat.view.menu.g gVar2 = this.f431c;
        ArrayList<androidx.appcompat.view.menu.j> arrayListJ = gVar2 != null ? gVar2.j() : null;
        if (this.l && arrayListJ != null) {
            int size2 = arrayListJ.size();
            if (size2 == 1) {
                z2 = !arrayListJ.get(0).isActionViewExpanded();
            } else if (size2 > 0) {
                z2 = true;
            }
        }
        if (z2) {
            if (this.i == null) {
                this.i = new OverflowMenuButton(this.f429a);
            }
            ViewGroup viewGroup = (ViewGroup) this.i.getParent();
            if (viewGroup != this.h) {
                if (viewGroup != null) {
                    viewGroup.removeView(this.i);
                }
                ActionMenuView actionMenuView = (ActionMenuView) this.h;
                actionMenuView.addView(this.i, actionMenuView.d());
            }
        } else {
            OverflowMenuButton overflowMenuButton = this.i;
            if (overflowMenuButton != null) {
                Object parent = overflowMenuButton.getParent();
                Object obj = this.h;
                if (parent == obj) {
                    ((ViewGroup) obj).removeView(this.i);
                }
            }
        }
        ((ActionMenuView) this.h).setOverflowReserved(this.l);
    }

    @Override // androidx.appcompat.view.menu.b
    public boolean a(ViewGroup viewGroup, int i) {
        if (viewGroup.getChildAt(i) == this.i) {
            return false;
        }
        return super.a(viewGroup, i);
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public boolean a(androidx.appcompat.view.menu.r rVar) {
        boolean z = false;
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.r rVar2 = rVar;
        while (rVar2.t() != this.f431c) {
            rVar2 = (androidx.appcompat.view.menu.r) rVar2.t();
        }
        View viewA = a(rVar2.getItem());
        if (viewA == null) {
            return false;
        }
        rVar.getItem().getItemId();
        int size = rVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = rVar.getItem(i);
            if (item.isVisible() && item.getIcon() != null) {
                z = true;
                break;
            }
        }
        this.x = new a(this.f430b, rVar, viewA);
        this.x.a(z);
        this.x.e();
        super.a(rVar);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private View a(MenuItem menuItem) {
        ViewGroup viewGroup = (ViewGroup) this.h;
        if (viewGroup == null) {
            return null;
        }
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if ((childAt instanceof androidx.appcompat.view.menu.n.a) && ((androidx.appcompat.view.menu.n.a) childAt).getItemData() == menuItem) {
                return childAt;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a() {
        ArrayList<androidx.appcompat.view.menu.j> arrayListN;
        int size;
        int i;
        int iA;
        ActionMenuPresenter actionMenuPresenter = this;
        androidx.appcompat.view.menu.g gVar = actionMenuPresenter.f431c;
        View view = null;
        int i2 = 0;
        if (gVar != null) {
            arrayListN = gVar.n();
            size = arrayListN.size();
        } else {
            arrayListN = null;
            size = 0;
        }
        int i3 = actionMenuPresenter.p;
        int i4 = actionMenuPresenter.o;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        ViewGroup viewGroup = (ViewGroup) actionMenuPresenter.h;
        int i5 = i3;
        boolean z = false;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < size; i8++) {
            androidx.appcompat.view.menu.j jVar = arrayListN.get(i8);
            if (jVar.k()) {
                i6++;
            } else if (jVar.j()) {
                i7++;
            } else {
                z = true;
            }
            if (actionMenuPresenter.t && jVar.isActionViewExpanded()) {
                i5 = 0;
            }
        }
        if (actionMenuPresenter.l && (z || i7 + i6 > i5)) {
            i5--;
        }
        int i9 = i5 - i6;
        SparseBooleanArray sparseBooleanArray = actionMenuPresenter.v;
        sparseBooleanArray.clear();
        if (actionMenuPresenter.r) {
            int i10 = actionMenuPresenter.u;
            iA = i4 / i10;
            i = i10 + ((i4 % i10) / iA);
        } else {
            i = 0;
            iA = 0;
        }
        int i11 = i4;
        int i12 = 0;
        int i13 = 0;
        while (i12 < size) {
            androidx.appcompat.view.menu.j jVar2 = arrayListN.get(i12);
            if (jVar2.k()) {
                View viewA = actionMenuPresenter.a(jVar2, view, viewGroup);
                if (actionMenuPresenter.r) {
                    iA -= ActionMenuView.a(viewA, i, iA, iMakeMeasureSpec, i2);
                } else {
                    viewA.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                }
                int measuredWidth = viewA.getMeasuredWidth();
                i11 -= measuredWidth;
                if (i13 != 0) {
                    measuredWidth = i13;
                }
                int groupId = jVar2.getGroupId();
                if (groupId != 0) {
                    sparseBooleanArray.put(groupId, true);
                }
                jVar2.d(true);
                i13 = measuredWidth;
            } else {
                if (jVar2.j()) {
                    int groupId2 = jVar2.getGroupId();
                    boolean z2 = sparseBooleanArray.get(groupId2);
                    boolean z3 = (i9 > 0 || z2) && i11 > 0 && (!actionMenuPresenter.r || iA > 0);
                    boolean z4 = z3;
                    if (z3) {
                        View viewA2 = actionMenuPresenter.a(jVar2, null, viewGroup);
                        if (actionMenuPresenter.r) {
                            int iA2 = ActionMenuView.a(viewA2, i, iA, iMakeMeasureSpec, 0);
                            iA -= iA2;
                            z4 = iA2 == 0 ? false : z4;
                        } else {
                            viewA2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                        }
                        int measuredWidth2 = viewA2.getMeasuredWidth();
                        i11 -= measuredWidth2;
                        if (i13 == 0) {
                            i13 = measuredWidth2;
                        }
                        z3 = z4 & (!actionMenuPresenter.r ? i11 + i13 <= 0 : i11 < 0);
                    }
                    if (z3 && groupId2 != 0) {
                        sparseBooleanArray.put(groupId2, true);
                    } else if (z2) {
                        sparseBooleanArray.put(groupId2, false);
                        for (int i14 = 0; i14 < i12; i14++) {
                            androidx.appcompat.view.menu.j jVar3 = arrayListN.get(i14);
                            if (jVar3.getGroupId() == groupId2) {
                                if (jVar3.h()) {
                                    i9++;
                                }
                                jVar3.d(false);
                            }
                        }
                    }
                    if (z3) {
                        i9--;
                    }
                    jVar2.d(z3);
                } else {
                    size = size;
                    jVar2.d(false);
                }
                i12++;
                size = size;
                view = null;
                i2 = 0;
                actionMenuPresenter = this;
            }
            i12++;
            size = size;
            view = null;
            i2 = 0;
            actionMenuPresenter = this;
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.b, androidx.appcompat.view.menu.m
    public void a(androidx.appcompat.view.menu.g gVar, boolean z) {
        c();
        super.a(gVar, z);
    }

    public void a(ActionMenuView actionMenuView) {
        this.h = actionMenuView;
        actionMenuView.a(this.f431c);
    }
}
