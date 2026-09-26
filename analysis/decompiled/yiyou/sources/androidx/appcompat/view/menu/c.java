package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: BaseMenuWrapper.java */
/* JADX INFO: loaded from: classes.dex */
abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f435a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Map<androidx.core.b.a.b, MenuItem> f436b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<androidx.core.b.a.c, SubMenu> f437c;

    c(Context context) {
        this.f435a = context;
    }

    final MenuItem a(MenuItem menuItem) {
        if (!(menuItem instanceof androidx.core.b.a.b)) {
            return menuItem;
        }
        androidx.core.b.a.b bVar = (androidx.core.b.a.b) menuItem;
        if (this.f436b == null) {
            this.f436b = new a.b.a();
        }
        MenuItem menuItem2 = this.f436b.get(menuItem);
        if (menuItem2 != null) {
            return menuItem2;
        }
        MenuItemWrapperICS menuItemWrapperICS = new MenuItemWrapperICS(this.f435a, bVar);
        this.f436b.put(bVar, menuItemWrapperICS);
        return menuItemWrapperICS;
    }

    final void b() {
        Map<androidx.core.b.a.b, MenuItem> map = this.f436b;
        if (map != null) {
            map.clear();
        }
        Map<androidx.core.b.a.c, SubMenu> map2 = this.f437c;
        if (map2 != null) {
            map2.clear();
        }
    }

    final void b(int i) {
        Map<androidx.core.b.a.b, MenuItem> map = this.f436b;
        if (map == null) {
            return;
        }
        Iterator<androidx.core.b.a.b> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == it.next().getItemId()) {
                it.remove();
                return;
            }
        }
    }

    final SubMenu a(SubMenu subMenu) {
        if (!(subMenu instanceof androidx.core.b.a.c)) {
            return subMenu;
        }
        androidx.core.b.a.c cVar = (androidx.core.b.a.c) subMenu;
        if (this.f437c == null) {
            this.f437c = new a.b.a();
        }
        SubMenu subMenu2 = this.f437c.get(cVar);
        if (subMenu2 != null) {
            return subMenu2;
        }
        s sVar = new s(this.f435a, cVar);
        this.f437c.put(cVar, sVar);
        return sVar;
    }

    final void a(int i) {
        Map<androidx.core.b.a.b, MenuItem> map = this.f436b;
        if (map == null) {
            return;
        }
        Iterator<androidx.core.b.a.b> it = map.keySet().iterator();
        while (it.hasNext()) {
            if (i == it.next().getGroupId()) {
                it.remove();
            }
        }
    }
}
