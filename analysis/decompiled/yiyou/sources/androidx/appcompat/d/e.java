package androidx.appcompat.d;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.ActionBarContextView;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: StandaloneActionMode.java */
/* JADX INFO: loaded from: classes.dex */
public class e extends b implements androidx.appcompat.view.menu.g.a {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Context f367c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private ActionBarContextView f368d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private b.a f369e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private WeakReference<View> f370f;
    private boolean g;
    private androidx.appcompat.view.menu.g h;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.f367c = context;
        this.f368d = actionBarContextView;
        this.f369e = aVar;
        androidx.appcompat.view.menu.g gVar = new androidx.appcompat.view.menu.g(actionBarContextView.getContext());
        gVar.c(1);
        this.h = gVar;
        this.h.a(this);
    }

    @Override // androidx.appcompat.d.b
    public void a(CharSequence charSequence) {
        this.f368d.setSubtitle(charSequence);
    }

    @Override // androidx.appcompat.d.b
    public void b(CharSequence charSequence) {
        this.f368d.setTitle(charSequence);
    }

    @Override // androidx.appcompat.d.b
    public Menu c() {
        return this.h;
    }

    @Override // androidx.appcompat.d.b
    public MenuInflater d() {
        return new g(this.f368d.getContext());
    }

    @Override // androidx.appcompat.d.b
    public CharSequence e() {
        return this.f368d.getSubtitle();
    }

    @Override // androidx.appcompat.d.b
    public CharSequence g() {
        return this.f368d.getTitle();
    }

    @Override // androidx.appcompat.d.b
    public void i() {
        this.f369e.a(this, this.h);
    }

    @Override // androidx.appcompat.d.b
    public boolean j() {
        return this.f368d.b();
    }

    @Override // androidx.appcompat.d.b
    public void a(int i) {
        a((CharSequence) this.f367c.getString(i));
    }

    @Override // androidx.appcompat.d.b
    public void b(int i) {
        b(this.f367c.getString(i));
    }

    @Override // androidx.appcompat.d.b
    public void a(boolean z) {
        super.a(z);
        this.f368d.setTitleOptional(z);
    }

    @Override // androidx.appcompat.d.b
    public View b() {
        WeakReference<View> weakReference = this.f370f;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    @Override // androidx.appcompat.d.b
    public void a(View view) {
        this.f368d.setCustomView(view);
        this.f370f = view != null ? new WeakReference<>(view) : null;
    }

    @Override // androidx.appcompat.d.b
    public void a() {
        if (this.g) {
            return;
        }
        this.g = true;
        this.f368d.sendAccessibilityEvent(32);
        this.f369e.a(this);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public boolean a(androidx.appcompat.view.menu.g gVar, MenuItem menuItem) {
        return this.f369e.a(this, menuItem);
    }

    @Override // androidx.appcompat.view.menu.g.a
    public void a(androidx.appcompat.view.menu.g gVar) {
        i();
        this.f368d.d();
    }
}
