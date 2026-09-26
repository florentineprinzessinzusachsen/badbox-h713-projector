package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$layout;
import androidx.appcompat.widget.MenuPopupWindow;
import androidx.appcompat.widget.t;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: CascadingMenuPopup.java */
/* JADX INFO: loaded from: classes.dex */
final class d extends k implements m, View.OnKeyListener, PopupWindow.OnDismissListener {
    private static final int B = R$layout.abc_cascading_menu_item_layout;
    boolean A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f438b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f439c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f440d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f441e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final boolean f442f;
    final Handler g;
    private View o;
    View p;
    private boolean r;
    private boolean s;
    private int t;
    private int u;
    private boolean w;
    private m.a x;
    ViewTreeObserver y;
    private PopupWindow.OnDismissListener z;
    private final List<g> h = new ArrayList();
    final List<C0008d> i = new ArrayList();
    final ViewTreeObserver.OnGlobalLayoutListener j = new a();
    private final View.OnAttachStateChangeListener k = new b();
    private final t l = new c();
    private int m = 0;
    private int n = 0;
    private boolean v = false;
    private int q = g();

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {
        a() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (!d.this.b() || d.this.i.size() <= 0 || d.this.i.get(0).f450a.k()) {
                return;
            }
            View view = d.this.p;
            if (view == null || !view.isShown()) {
                d.this.dismiss();
                return;
            }
            Iterator<C0008d> it = d.this.i.iterator();
            while (it.hasNext()) {
                it.next().f450a.show();
            }
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class b implements View.OnAttachStateChangeListener {
        b() {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            ViewTreeObserver viewTreeObserver = d.this.y;
            if (viewTreeObserver != null) {
                if (!viewTreeObserver.isAlive()) {
                    d.this.y = view.getViewTreeObserver();
                }
                d dVar = d.this;
                dVar.y.removeGlobalOnLayoutListener(dVar.j);
            }
            view.removeOnAttachStateChangeListener(this);
        }
    }

    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    class c implements t {

        /* JADX INFO: compiled from: CascadingMenuPopup.java */
        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ C0008d f446a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ MenuItem f447b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            final /* synthetic */ g f448c;

            a(C0008d c0008d, MenuItem menuItem, g gVar) {
                this.f446a = c0008d;
                this.f447b = menuItem;
                this.f448c = gVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                C0008d c0008d = this.f446a;
                if (c0008d != null) {
                    d.this.A = true;
                    c0008d.f451b.a(false);
                    d.this.A = false;
                }
                if (this.f447b.isEnabled() && this.f447b.hasSubMenu()) {
                    this.f448c.a(this.f447b, 4);
                }
            }
        }

        c() {
        }

        @Override // androidx.appcompat.widget.t
        public void a(g gVar, MenuItem menuItem) {
            d.this.g.removeCallbacksAndMessages(null);
            int size = d.this.i.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    i = -1;
                    break;
                } else if (gVar == d.this.i.get(i).f451b) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == -1) {
                return;
            }
            int i2 = i + 1;
            d.this.g.postAtTime(new a(i2 < d.this.i.size() ? d.this.i.get(i2) : null, menuItem, gVar), gVar, SystemClock.uptimeMillis() + 200);
        }

        @Override // androidx.appcompat.widget.t
        public void b(g gVar, MenuItem menuItem) {
            d.this.g.removeCallbacksAndMessages(gVar);
        }
    }

    /* JADX INFO: renamed from: androidx.appcompat.view.menu.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CascadingMenuPopup.java */
    private static class C0008d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final MenuPopupWindow f450a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g f451b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f452c;

        public C0008d(MenuPopupWindow menuPopupWindow, g gVar, int i) {
            this.f450a = menuPopupWindow;
            this.f451b = gVar;
            this.f452c = i;
        }

        public ListView a() {
            return this.f450a.f();
        }
    }

    public d(Context context, View view, int i, int i2, boolean z) {
        this.f438b = context;
        this.o = view;
        this.f440d = i;
        this.f441e = i2;
        this.f442f = z;
        Resources resources = context.getResources();
        this.f439c = Math.max(resources.getDisplayMetrics().widthPixels / 2, resources.getDimensionPixelSize(R$dimen.abc_config_prefDialogWidth));
        this.g = new Handler();
    }

    private int c(g gVar) {
        int size = this.i.size();
        for (int i = 0; i < size; i++) {
            if (gVar == this.i.get(i).f451b) {
                return i;
            }
        }
        return -1;
    }

    private int d(int i) {
        List<C0008d> list = this.i;
        ListView listViewA = list.get(list.size() - 1).a();
        int[] iArr = new int[2];
        listViewA.getLocationOnScreen(iArr);
        Rect rect = new Rect();
        this.p.getWindowVisibleDisplayFrame(rect);
        if (this.q == 1) {
            return (iArr[0] + listViewA.getWidth()) + i > rect.right ? 0 : 1;
        }
        return iArr[0] - i < 0 ? 1 : 0;
    }

    private MenuPopupWindow e() {
        MenuPopupWindow menuPopupWindow = new MenuPopupWindow(this.f438b, null, this.f440d, this.f441e);
        menuPopupWindow.a(this.l);
        menuPopupWindow.a((AdapterView.OnItemClickListener) this);
        menuPopupWindow.a((PopupWindow.OnDismissListener) this);
        menuPopupWindow.a(this.o);
        menuPopupWindow.f(this.n);
        menuPopupWindow.a(true);
        menuPopupWindow.g(2);
        return menuPopupWindow;
    }

    private int g() {
        return androidx.core.f.t.j(this.o) == 1 ? 0 : 1;
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(g gVar) {
        gVar.a(this, this.f438b);
        if (b()) {
            d(gVar);
        } else {
            this.h.add(gVar);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k
    public void b(boolean z) {
        this.v = z;
    }

    @Override // androidx.appcompat.view.menu.k
    protected boolean c() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.p
    public void dismiss() {
        int size = this.i.size();
        if (size > 0) {
            C0008d[] c0008dArr = (C0008d[]) this.i.toArray(new C0008d[size]);
            for (int i = size - 1; i >= 0; i--) {
                C0008d c0008d = c0008dArr[i];
                if (c0008d.f450a.b()) {
                    c0008d.f450a.dismiss();
                }
            }
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public ListView f() {
        if (this.i.isEmpty()) {
            return null;
        }
        List<C0008d> list = this.i;
        return list.get(list.size() - 1).a();
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public void onDismiss() {
        C0008d c0008d;
        int size = this.i.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                c0008d = null;
                break;
            }
            c0008d = this.i.get(i);
            if (!c0008d.f450a.b()) {
                break;
            } else {
                i++;
            }
        }
        if (c0008d != null) {
            c0008d.f451b.a(false);
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
        if (b()) {
            return;
        }
        Iterator<g> it = this.h.iterator();
        while (it.hasNext()) {
            d(it.next());
        }
        this.h.clear();
        this.p = this.o;
        if (this.p != null) {
            boolean z = this.y == null;
            this.y = this.p.getViewTreeObserver();
            if (z) {
                this.y.addOnGlobalLayoutListener(this.j);
            }
            this.p.addOnAttachStateChangeListener(this.k);
        }
    }

    @Override // androidx.appcompat.view.menu.p
    public boolean b() {
        return this.i.size() > 0 && this.i.get(0).f450a.b();
    }

    @Override // androidx.appcompat.view.menu.k
    public void b(int i) {
        this.r = true;
        this.t = i;
    }

    @Override // androidx.appcompat.view.menu.k
    public void c(int i) {
        this.s = true;
        this.u = i;
    }

    private MenuItem a(g gVar, g gVar2) {
        int size = gVar.size();
        for (int i = 0; i < size; i++) {
            MenuItem item = gVar.getItem(i);
            if (item.hasSubMenu() && gVar2 == item.getSubMenu()) {
                return item;
            }
        }
        return null;
    }

    @Override // androidx.appcompat.view.menu.k
    public void c(boolean z) {
        this.w = z;
    }

    private View a(C0008d c0008d, g gVar) {
        f fVar;
        int headersCount;
        int firstVisiblePosition;
        MenuItem menuItemA = a(c0008d.f451b, gVar);
        if (menuItemA == null) {
            return null;
        }
        ListView listViewA = c0008d.a();
        ListAdapter adapter = listViewA.getAdapter();
        int i = 0;
        if (adapter instanceof HeaderViewListAdapter) {
            HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) adapter;
            headersCount = headerViewListAdapter.getHeadersCount();
            fVar = (f) headerViewListAdapter.getWrappedAdapter();
        } else {
            fVar = (f) adapter;
            headersCount = 0;
        }
        int count = fVar.getCount();
        while (true) {
            if (i >= count) {
                i = -1;
                break;
            }
            if (menuItemA == fVar.getItem(i)) {
                break;
            }
            i++;
        }
        if (i != -1 && (firstVisiblePosition = (i + headersCount) - listViewA.getFirstVisiblePosition()) >= 0 && firstVisiblePosition < listViewA.getChildCount()) {
            return listViewA.getChildAt(firstVisiblePosition);
        }
        return null;
    }

    private void d(g gVar) {
        C0008d c0008d;
        View viewA;
        int i;
        int i2;
        int i3;
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f438b);
        f fVar = new f(gVar, layoutInflaterFrom, this.f442f, B);
        if (!b() && this.v) {
            fVar.a(true);
        } else if (b()) {
            fVar.a(k.b(gVar));
        }
        int iA = k.a(fVar, null, this.f438b, this.f439c);
        MenuPopupWindow menuPopupWindowE = e();
        menuPopupWindowE.a((ListAdapter) fVar);
        menuPopupWindowE.e(iA);
        menuPopupWindowE.f(this.n);
        if (this.i.size() > 0) {
            List<C0008d> list = this.i;
            c0008d = list.get(list.size() - 1);
            viewA = a(c0008d, gVar);
        } else {
            c0008d = null;
            viewA = null;
        }
        if (viewA != null) {
            menuPopupWindowE.c(false);
            menuPopupWindowE.a((Object) null);
            int iD = d(iA);
            boolean z = iD == 1;
            this.q = iD;
            if (Build.VERSION.SDK_INT >= 26) {
                menuPopupWindowE.a(viewA);
                i2 = 0;
                i = 0;
            } else {
                int[] iArr = new int[2];
                this.o.getLocationOnScreen(iArr);
                int[] iArr2 = new int[2];
                viewA.getLocationOnScreen(iArr2);
                if ((this.n & 7) == 5) {
                    iArr[0] = iArr[0] + this.o.getWidth();
                    iArr2[0] = iArr2[0] + viewA.getWidth();
                }
                i = iArr2[0] - iArr[0];
                i2 = iArr2[1] - iArr[1];
            }
            if ((this.n & 5) == 5) {
                if (z) {
                    i3 = i + iA;
                } else {
                    iA = viewA.getWidth();
                    i3 = i - iA;
                }
            } else if (z) {
                iA = viewA.getWidth();
                i3 = i + iA;
            } else {
                i3 = i - iA;
            }
            menuPopupWindowE.a(i3);
            menuPopupWindowE.b(true);
            menuPopupWindowE.b(i2);
        } else {
            if (this.r) {
                menuPopupWindowE.a(this.t);
            }
            if (this.s) {
                menuPopupWindowE.b(this.u);
            }
            menuPopupWindowE.a(d());
        }
        this.i.add(new C0008d(menuPopupWindowE, gVar, this.q));
        menuPopupWindowE.show();
        ListView listViewF = menuPopupWindowE.f();
        listViewF.setOnKeyListener(this);
        if (c0008d == null && this.w && gVar.h() != null) {
            FrameLayout frameLayout = (FrameLayout) layoutInflaterFrom.inflate(R$layout.abc_popup_menu_header_item_layout, (ViewGroup) listViewF, false);
            TextView textView = (TextView) frameLayout.findViewById(R.id.title);
            frameLayout.setEnabled(false);
            textView.setText(gVar.h());
            listViewF.addHeaderView(frameLayout, null, false);
            menuPopupWindowE.show();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(boolean z) {
        Iterator<C0008d> it = this.i.iterator();
        while (it.hasNext()) {
            k.a(it.next().a().getAdapter()).notifyDataSetChanged();
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(m.a aVar) {
        this.x = aVar;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a(r rVar) {
        for (C0008d c0008d : this.i) {
            if (rVar == c0008d.f451b) {
                c0008d.a().requestFocus();
                return true;
            }
        }
        if (!rVar.hasVisibleItems()) {
            return false;
        }
        a((g) rVar);
        m.a aVar = this.x;
        if (aVar != null) {
            aVar.a(rVar);
        }
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        int iC = c(gVar);
        if (iC < 0) {
            return;
        }
        int i = iC + 1;
        if (i < this.i.size()) {
            this.i.get(i).f451b.a(false);
        }
        C0008d c0008dRemove = this.i.remove(iC);
        c0008dRemove.f451b.b(this);
        if (this.A) {
            c0008dRemove.f450a.b((Object) null);
            c0008dRemove.f450a.d(0);
        }
        c0008dRemove.f450a.dismiss();
        int size = this.i.size();
        if (size > 0) {
            this.q = this.i.get(size - 1).f452c;
        } else {
            this.q = g();
        }
        if (size != 0) {
            if (z) {
                this.i.get(0).f451b.a(false);
                return;
            }
            return;
        }
        dismiss();
        m.a aVar = this.x;
        if (aVar != null) {
            aVar.a(gVar, true);
        }
        ViewTreeObserver viewTreeObserver = this.y;
        if (viewTreeObserver != null) {
            if (viewTreeObserver.isAlive()) {
                this.y.removeGlobalOnLayoutListener(this.j);
            }
            this.y = null;
        }
        this.p.removeOnAttachStateChangeListener(this.k);
        this.z.onDismiss();
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(int i) {
        if (this.m != i) {
            this.m = i;
            this.n = androidx.core.f.c.a(i, androidx.core.f.t.j(this.o));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(View view) {
        if (this.o != view) {
            this.o = view;
            this.n = androidx.core.f.c.a(this.m, androidx.core.f.t.j(this.o));
        }
    }

    @Override // androidx.appcompat.view.menu.k
    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.z = onDismissListener;
    }
}
