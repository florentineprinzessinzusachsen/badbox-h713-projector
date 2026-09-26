package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: compiled from: BaseMenuPresenter.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected Context f429a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected Context f430b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected g f431c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected LayoutInflater f432d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private m.a f433e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f434f;
    private int g;
    protected n h;

    public b(Context context, int i, int i2) {
        this.f429a = context;
        this.f432d = LayoutInflater.from(context);
        this.f434f = i;
        this.g = i2;
    }

    public void a(int i) {
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(Context context, g gVar) {
        this.f430b = context;
        LayoutInflater.from(this.f430b);
        this.f431c = gVar;
    }

    public abstract void a(j jVar, n.a aVar);

    public abstract boolean a(int i, j jVar);

    @Override // androidx.appcompat.view.menu.m
    public boolean a(g gVar, j jVar) {
        return false;
    }

    public n b(ViewGroup viewGroup) {
        if (this.h == null) {
            this.h = (n) this.f432d.inflate(this.f434f, viewGroup, false);
            this.h.a(this.f431c);
            a(true);
        }
        return this.h;
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean b(g gVar, j jVar) {
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.m
    public void a(boolean z) {
        ViewGroup viewGroup = (ViewGroup) this.h;
        if (viewGroup == null) {
            return;
        }
        g gVar = this.f431c;
        int i = 0;
        if (gVar != null) {
            gVar.b();
            ArrayList<j> arrayListN = this.f431c.n();
            int size = arrayListN.size();
            int i2 = 0;
            for (int i3 = 0; i3 < size; i3++) {
                j jVar = arrayListN.get(i3);
                if (a(i2, jVar)) {
                    View childAt = viewGroup.getChildAt(i2);
                    j itemData = childAt instanceof n.a ? ((n.a) childAt).getItemData() : null;
                    View viewA = a(jVar, childAt, viewGroup);
                    if (jVar != itemData) {
                        viewA.setPressed(false);
                        viewA.jumpDrawablesToCurrentState();
                    }
                    if (viewA != childAt) {
                        a(viewA, i2);
                    }
                    i2++;
                }
            }
            i = i2;
        }
        while (i < viewGroup.getChildCount()) {
            if (!a(viewGroup, i)) {
                i++;
            }
        }
    }

    public m.a b() {
        return this.f433e;
    }

    protected void a(View view, int i) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.h).addView(view, i);
    }

    protected boolean a(ViewGroup viewGroup, int i) {
        viewGroup.removeViewAt(i);
        return true;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(m.a aVar) {
        this.f433e = aVar;
    }

    public n.a a(ViewGroup viewGroup) {
        return (n.a) this.f432d.inflate(this.g, viewGroup, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View a(j jVar, View view, ViewGroup viewGroup) {
        n.a aVarA;
        if (view instanceof n.a) {
            aVarA = (n.a) view;
        } else {
            aVarA = a(viewGroup);
        }
        a(jVar, aVarA);
        return (View) aVarA;
    }

    @Override // androidx.appcompat.view.menu.m
    public void a(g gVar, boolean z) {
        m.a aVar = this.f433e;
        if (aVar != null) {
            aVar.a(gVar, z);
        }
    }

    @Override // androidx.appcompat.view.menu.m
    public boolean a(r rVar) {
        m.a aVar = this.f433e;
        if (aVar != null) {
            return aVar.a(rVar);
        }
        return false;
    }
}
