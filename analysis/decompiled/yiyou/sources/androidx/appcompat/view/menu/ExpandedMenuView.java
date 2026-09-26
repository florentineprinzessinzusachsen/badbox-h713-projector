package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.d0;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements g.b, n, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int[] f404c = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private g f405a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f406b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.listViewStyle);
    }

    @Override // androidx.appcompat.view.menu.n
    public void a(g gVar) {
        this.f405a = gVar;
    }

    public int getWindowAnimations() {
        return this.f406b;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        a((j) getAdapter().getItem(i));
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        d0 d0VarA = d0.a(context, attributeSet, f404c, i, 0);
        if (d0VarA.g(0)) {
            setBackgroundDrawable(d0VarA.b(0));
        }
        if (d0VarA.g(1)) {
            setDivider(d0VarA.b(1));
        }
        d0VarA.a();
    }

    @Override // androidx.appcompat.view.menu.g.b
    public boolean a(j jVar) {
        return this.f405a.a(jVar, 0);
    }
}
