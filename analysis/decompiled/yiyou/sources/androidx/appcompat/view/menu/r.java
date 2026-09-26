package androidx.appcompat.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.baidu.mobstat.Config;

/* JADX INFO: compiled from: SubMenuBuilder.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends g implements SubMenu {
    private g B;
    private j C;

    public r(Context context, g gVar, j jVar) {
        super(context);
        this.B = gVar;
        this.C = jVar;
    }

    @Override // androidx.appcompat.view.menu.g
    public void a(g.a aVar) {
        this.B.a(aVar);
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean b(j jVar) {
        return this.B.b(jVar);
    }

    @Override // androidx.appcompat.view.menu.g
    public String d() {
        j jVar = this.C;
        int itemId = jVar != null ? jVar.getItemId() : 0;
        if (itemId == 0) {
            return null;
        }
        return super.d() + Config.TRACE_TODAY_VISIT_SPLIT + itemId;
    }

    @Override // android.view.SubMenu
    public MenuItem getItem() {
        return this.C;
    }

    @Override // androidx.appcompat.view.menu.g
    public g m() {
        return this.B.m();
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean o() {
        return this.B.o();
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean p() {
        return this.B.p();
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean q() {
        return this.B.q();
    }

    @Override // androidx.appcompat.view.menu.g, android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.B.setGroupDividerEnabled(z);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(Drawable drawable) {
        super.a(drawable);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.a(charSequence);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderView(View view) {
        super.a(view);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(Drawable drawable) {
        this.C.setIcon(drawable);
        return this;
    }

    @Override // androidx.appcompat.view.menu.g, android.view.Menu
    public void setQwertyMode(boolean z) {
        this.B.setQwertyMode(z);
    }

    public Menu t() {
        return this.B;
    }

    @Override // androidx.appcompat.view.menu.g
    boolean a(g gVar, MenuItem menuItem) {
        return super.a(gVar, menuItem) || this.B.a(gVar, menuItem);
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderIcon(int i) {
        super.d(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setHeaderTitle(int i) {
        super.e(i);
        return this;
    }

    @Override // android.view.SubMenu
    public SubMenu setIcon(int i) {
        this.C.setIcon(i);
        return this;
    }

    @Override // androidx.appcompat.view.menu.g
    public boolean a(j jVar) {
        return this.B.a(jVar);
    }
}
