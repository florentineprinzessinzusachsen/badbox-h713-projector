package androidx.appcompat.d;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.view.menu.o;
import java.util.ArrayList;

/* JADX INFO: compiled from: SupportActionModeWrapper.java */
/* JADX INFO: loaded from: classes.dex */
public class f extends ActionMode {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f371a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final b f372b;

    public f(Context context, b bVar) {
        this.f371a = context;
        this.f372b = bVar;
    }

    @Override // android.view.ActionMode
    public void finish() {
        this.f372b.a();
    }

    @Override // android.view.ActionMode
    public View getCustomView() {
        return this.f372b.b();
    }

    @Override // android.view.ActionMode
    public Menu getMenu() {
        return new o(this.f371a, (androidx.core.b.a.a) this.f372b.c());
    }

    @Override // android.view.ActionMode
    public MenuInflater getMenuInflater() {
        return this.f372b.d();
    }

    @Override // android.view.ActionMode
    public CharSequence getSubtitle() {
        return this.f372b.e();
    }

    @Override // android.view.ActionMode
    public Object getTag() {
        return this.f372b.f();
    }

    @Override // android.view.ActionMode
    public CharSequence getTitle() {
        return this.f372b.g();
    }

    @Override // android.view.ActionMode
    public boolean getTitleOptionalHint() {
        return this.f372b.h();
    }

    @Override // android.view.ActionMode
    public void invalidate() {
        this.f372b.i();
    }

    @Override // android.view.ActionMode
    public boolean isTitleOptional() {
        return this.f372b.j();
    }

    @Override // android.view.ActionMode
    public void setCustomView(View view) {
        this.f372b.a(view);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(CharSequence charSequence) {
        this.f372b.a(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTag(Object obj) {
        this.f372b.a(obj);
    }

    @Override // android.view.ActionMode
    public void setTitle(CharSequence charSequence) {
        this.f372b.b(charSequence);
    }

    @Override // android.view.ActionMode
    public void setTitleOptionalHint(boolean z) {
        this.f372b.a(z);
    }

    @Override // android.view.ActionMode
    public void setSubtitle(int i) {
        this.f372b.a(i);
    }

    @Override // android.view.ActionMode
    public void setTitle(int i) {
        this.f372b.b(i);
    }

    /* JADX INFO: compiled from: SupportActionModeWrapper.java */
    public static class a implements b.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final ActionMode.Callback f373a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final Context f374b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ArrayList<f> f375c = new ArrayList<>();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final a.b.g<Menu, Menu> f376d = new a.b.g<>();

        public a(Context context, ActionMode.Callback callback) {
            this.f374b = context;
            this.f373a = callback;
        }

        @Override // androidx.appcompat.d.b.a
        public boolean a(b bVar, Menu menu) {
            return this.f373a.onPrepareActionMode(b(bVar), a(menu));
        }

        @Override // androidx.appcompat.d.b.a
        public boolean b(b bVar, Menu menu) {
            return this.f373a.onCreateActionMode(b(bVar), a(menu));
        }

        @Override // androidx.appcompat.d.b.a
        public boolean a(b bVar, MenuItem menuItem) {
            return this.f373a.onActionItemClicked(b(bVar), new MenuItemWrapperICS(this.f374b, (androidx.core.b.a.b) menuItem));
        }

        public ActionMode b(b bVar) {
            int size = this.f375c.size();
            for (int i = 0; i < size; i++) {
                f fVar = this.f375c.get(i);
                if (fVar != null && fVar.f372b == bVar) {
                    return fVar;
                }
            }
            f fVar2 = new f(this.f374b, bVar);
            this.f375c.add(fVar2);
            return fVar2;
        }

        @Override // androidx.appcompat.d.b.a
        public void a(b bVar) {
            this.f373a.onDestroyActionMode(b(bVar));
        }

        private Menu a(Menu menu) {
            Menu menu2 = this.f376d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            o oVar = new o(this.f374b, (androidx.core.b.a.a) menu);
            this.f376d.put(menu, oVar);
            return oVar;
        }
    }
}
