package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import androidx.appcompat.R$dimen;
import androidx.core.f.t;

/* JADX INFO: compiled from: MenuPopupHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class l implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final g f486b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final boolean f487c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final int f488d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f489e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private View f490f;
    private int g;
    private boolean h;
    private m.a i;
    private k j;
    private PopupWindow.OnDismissListener k;
    private final PopupWindow.OnDismissListener l;

    /* JADX INFO: compiled from: MenuPopupHelper.java */
    class a implements PopupWindow.OnDismissListener {
        a() {
        }

        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            l.this.d();
        }
    }

    public l(Context context, g gVar, View view, boolean z, int i) {
        this(context, gVar, view, z, i, 0);
    }

    private k g() {
        Display defaultDisplay = ((WindowManager) this.f485a.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealSize(point);
        } else {
            defaultDisplay.getSize(point);
        }
        k dVar = Math.min(point.x, point.y) >= this.f485a.getResources().getDimensionPixelSize(R$dimen.abc_cascading_menus_min_smallest_width) ? new d(this.f485a, this.f490f, this.f488d, this.f489e, this.f487c) : new q(this.f485a, this.f486b, this.f490f, this.f488d, this.f489e, this.f487c);
        dVar.a(this.f486b);
        dVar.a(this.l);
        dVar.a(this.f490f);
        dVar.a(this.i);
        dVar.b(this.h);
        dVar.a(this.g);
        return dVar;
    }

    public void a(PopupWindow.OnDismissListener onDismissListener) {
        this.k = onDismissListener;
    }

    public k b() {
        if (this.j == null) {
            this.j = g();
        }
        return this.j;
    }

    public boolean c() {
        k kVar = this.j;
        return kVar != null && kVar.b();
    }

    protected void d() {
        this.j = null;
        PopupWindow.OnDismissListener onDismissListener = this.k;
        if (onDismissListener != null) {
            onDismissListener.onDismiss();
        }
    }

    public void e() {
        if (!f()) {
            throw new IllegalStateException("MenuPopupHelper cannot be used without an anchor");
        }
    }

    public boolean f() {
        if (c()) {
            return true;
        }
        if (this.f490f == null) {
            return false;
        }
        a(0, 0, false, false);
        return true;
    }

    public l(Context context, g gVar, View view, boolean z, int i, int i2) {
        this.g = 8388611;
        this.l = new a();
        this.f485a = context;
        this.f486b = gVar;
        this.f490f = view;
        this.f487c = z;
        this.f488d = i;
        this.f489e = i2;
    }

    public void a(View view) {
        this.f490f = view;
    }

    public void a(boolean z) {
        this.h = z;
        k kVar = this.j;
        if (kVar != null) {
            kVar.b(z);
        }
    }

    public void a(int i) {
        this.g = i;
    }

    public boolean a(int i, int i2) {
        if (c()) {
            return true;
        }
        if (this.f490f == null) {
            return false;
        }
        a(i, i2, true, true);
        return true;
    }

    private void a(int i, int i2, boolean z, boolean z2) {
        k kVarB = b();
        kVarB.c(z2);
        if (z) {
            if ((androidx.core.f.c.a(this.g, t.j(this.f490f)) & 7) == 5) {
                i -= this.f490f.getWidth();
            }
            kVarB.b(i);
            kVarB.c(i2);
            int i3 = (int) ((this.f485a.getResources().getDisplayMetrics().density * 48.0f) / 2.0f);
            kVarB.a(new Rect(i - i3, i2 - i3, i + i3, i2 + i3));
        }
        kVarB.show();
    }

    public void a() {
        if (c()) {
            this.j.dismiss();
        }
    }

    public void a(m.a aVar) {
        this.i = aVar;
        k kVar = this.j;
        if (kVar != null) {
            kVar.a(aVar);
        }
    }
}
