package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class MenuItemWrapperICS extends androidx.appcompat.view.menu.c implements MenuItem {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final androidx.core.b.a.b f413d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Method f414e;

    static class CollapsibleActionViewWrapper extends FrameLayout implements androidx.appcompat.d.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final CollapsibleActionView f415a;

        /* JADX WARN: Multi-variable type inference failed */
        CollapsibleActionViewWrapper(View view) {
            super(view.getContext());
            this.f415a = (CollapsibleActionView) view;
            addView(view);
        }

        @Override // androidx.appcompat.d.c
        public void a() {
            this.f415a.onActionViewExpanded();
        }

        @Override // androidx.appcompat.d.c
        public void b() {
            this.f415a.onActionViewCollapsed();
        }

        View c() {
            return (View) this.f415a;
        }
    }

    private class a extends androidx.core.f.b {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final ActionProvider f416c;

        a(Context context, ActionProvider actionProvider) {
            super(context);
            this.f416c = actionProvider;
        }

        @Override // androidx.core.f.b
        public boolean a() {
            return this.f416c.hasSubMenu();
        }

        @Override // androidx.core.f.b
        public View c() {
            return this.f416c.onCreateActionView();
        }

        @Override // androidx.core.f.b
        public boolean d() {
            return this.f416c.onPerformDefaultAction();
        }

        @Override // androidx.core.f.b
        public void a(SubMenu subMenu) {
            this.f416c.onPrepareSubMenu(MenuItemWrapperICS.this.a(subMenu));
        }
    }

    private class b extends a implements ActionProvider.VisibilityListener {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private androidx.core.f.b.InterfaceC0023b f418e;

        b(MenuItemWrapperICS menuItemWrapperICS, Context context, ActionProvider actionProvider) {
            super(context, actionProvider);
        }

        @Override // androidx.core.f.b
        public View a(MenuItem menuItem) {
            return this.f416c.onCreateActionView(menuItem);
        }

        @Override // androidx.core.f.b
        public boolean b() {
            return this.f416c.isVisible();
        }

        @Override // androidx.core.f.b
        public boolean e() {
            return this.f416c.overridesItemVisibility();
        }

        @Override // android.view.ActionProvider.VisibilityListener
        public void onActionProviderVisibilityChanged(boolean z) {
            androidx.core.f.b.InterfaceC0023b interfaceC0023b = this.f418e;
            if (interfaceC0023b != null) {
                interfaceC0023b.onActionProviderVisibilityChanged(z);
            }
        }

        @Override // androidx.core.f.b
        public void a(androidx.core.f.b.InterfaceC0023b interfaceC0023b) {
            this.f418e = interfaceC0023b;
            this.f416c.setVisibilityListener(interfaceC0023b != null ? this : null);
        }
    }

    private class c implements MenuItem.OnActionExpandListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final MenuItem.OnActionExpandListener f419a;

        c(MenuItem.OnActionExpandListener onActionExpandListener) {
            this.f419a = onActionExpandListener;
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return this.f419a.onMenuItemActionCollapse(MenuItemWrapperICS.this.a(menuItem));
        }

        @Override // android.view.MenuItem.OnActionExpandListener
        public boolean onMenuItemActionExpand(MenuItem menuItem) {
            return this.f419a.onMenuItemActionExpand(MenuItemWrapperICS.this.a(menuItem));
        }
    }

    private class d implements MenuItem.OnMenuItemClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final MenuItem.OnMenuItemClickListener f421a;

        d(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
            this.f421a = onMenuItemClickListener;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem menuItem) {
            return this.f421a.onMenuItemClick(MenuItemWrapperICS.this.a(menuItem));
        }
    }

    public MenuItemWrapperICS(Context context, androidx.core.b.a.b bVar) {
        super(context);
        if (bVar == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.f413d = bVar;
    }

    public void a(boolean z) {
        try {
            if (this.f414e == null) {
                this.f414e = this.f413d.getClass().getDeclaredMethod("setExclusiveCheckable", Boolean.TYPE);
            }
            this.f414e.invoke(this.f413d, Boolean.valueOf(z));
        } catch (Exception e2) {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", e2);
        }
    }

    @Override // android.view.MenuItem
    public boolean collapseActionView() {
        return this.f413d.collapseActionView();
    }

    @Override // android.view.MenuItem
    public boolean expandActionView() {
        return this.f413d.expandActionView();
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        androidx.core.f.b bVarA = this.f413d.a();
        if (bVarA instanceof a) {
            return ((a) bVarA).f416c;
        }
        return null;
    }

    @Override // android.view.MenuItem
    public View getActionView() {
        View actionView = this.f413d.getActionView();
        return actionView instanceof CollapsibleActionViewWrapper ? ((CollapsibleActionViewWrapper) actionView).c() : actionView;
    }

    @Override // android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.f413d.getAlphabeticModifiers();
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.f413d.getAlphabeticShortcut();
    }

    @Override // android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.f413d.getContentDescription();
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f413d.getGroupId();
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.f413d.getIcon();
    }

    @Override // android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.f413d.getIconTintList();
    }

    @Override // android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.f413d.getIconTintMode();
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f413d.getIntent();
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f413d.getItemId();
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return this.f413d.getMenuInfo();
    }

    @Override // android.view.MenuItem
    public int getNumericModifiers() {
        return this.f413d.getNumericModifiers();
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.f413d.getNumericShortcut();
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f413d.getOrder();
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return a(this.f413d.getSubMenu());
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f413d.getTitle();
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        return this.f413d.getTitleCondensed();
    }

    @Override // android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.f413d.getTooltipText();
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return this.f413d.hasSubMenu();
    }

    @Override // android.view.MenuItem
    public boolean isActionViewExpanded() {
        return this.f413d.isActionViewExpanded();
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return this.f413d.isCheckable();
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return this.f413d.isChecked();
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return this.f413d.isEnabled();
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return this.f413d.isVisible();
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        androidx.core.f.b bVar = Build.VERSION.SDK_INT >= 16 ? new b(this, this.f435a, actionProvider) : new a(this.f435a, actionProvider);
        androidx.core.b.a.b bVar2 = this.f413d;
        if (actionProvider == null) {
            bVar = null;
        }
        bVar2.a(bVar);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new CollapsibleActionViewWrapper(view);
        }
        this.f413d.setActionView(view);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.f413d.setAlphabeticShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.f413d.setCheckable(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.f413d.setChecked(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setContentDescription(CharSequence charSequence) {
        this.f413d.setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.f413d.setEnabled(z);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.f413d.setIcon(drawable);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.f413d.setIconTintList(colorStateList);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.f413d.setIconTintMode(mode);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f413d.setIntent(intent);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.f413d.setNumericShortcut(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        this.f413d.setOnActionExpandListener(onActionExpandListener != null ? new c(onActionExpandListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.f413d.setOnMenuItemClickListener(onMenuItemClickListener != null ? new d(onMenuItemClickListener) : null);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.f413d.setShortcut(c2, c3);
        return this;
    }

    @Override // android.view.MenuItem
    public void setShowAsAction(int i) {
        this.f413d.setShowAsAction(i);
    }

    @Override // android.view.MenuItem
    public MenuItem setShowAsActionFlags(int i) {
        this.f413d.setShowAsActionFlags(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f413d.setTitle(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f413d.setTitleCondensed(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTooltipText(CharSequence charSequence) {
        this.f413d.setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        return this.f413d.setVisible(z);
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i) {
        this.f413d.setAlphabeticShortcut(c2, i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.f413d.setIcon(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i) {
        this.f413d.setNumericShortcut(c2, i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        this.f413d.setShortcut(c2, c3, i, i2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        this.f413d.setTitle(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionView(int i) {
        this.f413d.setActionView(i);
        View actionView = this.f413d.getActionView();
        if (actionView instanceof CollapsibleActionView) {
            this.f413d.setActionView(new CollapsibleActionViewWrapper(actionView));
        }
        return this;
    }
}
