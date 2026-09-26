package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.ActionProvider;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionMenuItem.java */
/* JADX INFO: loaded from: classes.dex */
public class a implements androidx.core.b.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f423a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f424b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f425c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private CharSequence f426d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private CharSequence f427e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private Intent f428f;
    private char g;
    private char i;
    private Drawable k;
    private Context l;
    private CharSequence m;
    private CharSequence n;
    private int h = 4096;
    private int j = 4096;
    private ColorStateList o = null;
    private PorterDuff.Mode p = null;
    private boolean q = false;
    private boolean r = false;
    private int s = 16;

    public a(Context context, int i, int i2, int i3, int i4, CharSequence charSequence) {
        this.l = context;
        this.f423a = i2;
        this.f424b = i;
        this.f425c = i4;
        this.f426d = charSequence;
    }

    private void b() {
        if (this.k != null) {
            if (this.q || this.r) {
                this.k = androidx.core.graphics.drawable.a.h(this.k);
                this.k = this.k.mutate();
                if (this.q) {
                    androidx.core.graphics.drawable.a.a(this.k, this.o);
                }
                if (this.r) {
                    androidx.core.graphics.drawable.a.a(this.k, this.p);
                }
            }
        }
    }

    @Override // androidx.core.b.a.b
    public androidx.core.b.a.b a(androidx.core.f.b bVar) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.b.a.b
    public androidx.core.f.b a() {
        return null;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public boolean collapseActionView() {
        return false;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public boolean expandActionView() {
        return false;
    }

    @Override // android.view.MenuItem
    public ActionProvider getActionProvider() {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public View getActionView() {
        return null;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public int getAlphabeticModifiers() {
        return this.j;
    }

    @Override // android.view.MenuItem
    public char getAlphabeticShortcut() {
        return this.i;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public CharSequence getContentDescription() {
        return this.m;
    }

    @Override // android.view.MenuItem
    public int getGroupId() {
        return this.f424b;
    }

    @Override // android.view.MenuItem
    public Drawable getIcon() {
        return this.k;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public ColorStateList getIconTintList() {
        return this.o;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public PorterDuff.Mode getIconTintMode() {
        return this.p;
    }

    @Override // android.view.MenuItem
    public Intent getIntent() {
        return this.f428f;
    }

    @Override // android.view.MenuItem
    public int getItemId() {
        return this.f423a;
    }

    @Override // android.view.MenuItem
    public ContextMenu.ContextMenuInfo getMenuInfo() {
        return null;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public int getNumericModifiers() {
        return this.h;
    }

    @Override // android.view.MenuItem
    public char getNumericShortcut() {
        return this.g;
    }

    @Override // android.view.MenuItem
    public int getOrder() {
        return this.f425c;
    }

    @Override // android.view.MenuItem
    public SubMenu getSubMenu() {
        return null;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitle() {
        return this.f426d;
    }

    @Override // android.view.MenuItem
    public CharSequence getTitleCondensed() {
        CharSequence charSequence = this.f427e;
        return charSequence != null ? charSequence : this.f426d;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public CharSequence getTooltipText() {
        return this.n;
    }

    @Override // android.view.MenuItem
    public boolean hasSubMenu() {
        return false;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public boolean isActionViewExpanded() {
        return false;
    }

    @Override // android.view.MenuItem
    public boolean isCheckable() {
        return (this.s & 1) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isChecked() {
        return (this.s & 2) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isEnabled() {
        return (this.s & 16) != 0;
    }

    @Override // android.view.MenuItem
    public boolean isVisible() {
        return (this.s & 8) == 0;
    }

    @Override // android.view.MenuItem
    public MenuItem setActionProvider(ActionProvider actionProvider) {
        throw new UnsupportedOperationException();
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setActionView(int i) {
        setActionView(i);
        throw null;
    }

    @Override // android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2) {
        this.i = Character.toLowerCase(c2);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setCheckable(boolean z) {
        this.s = (z ? 1 : 0) | (this.s & (-2));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setChecked(boolean z) {
        this.s = (z ? 2 : 0) | (this.s & (-3));
        return this;
    }

    @Override // android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setContentDescription(CharSequence charSequence) {
        setContentDescription(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setEnabled(boolean z) {
        this.s = (z ? 16 : 0) | (this.s & (-17));
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(Drawable drawable) {
        this.k = drawable;
        b();
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public MenuItem setIconTintList(ColorStateList colorStateList) {
        this.o = colorStateList;
        this.q = true;
        b();
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public MenuItem setIconTintMode(PorterDuff.Mode mode) {
        this.p = mode;
        this.r = true;
        b();
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setIntent(Intent intent) {
        this.f428f = intent;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setNumericShortcut(char c2) {
        this.g = c2;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setOnActionExpandListener(MenuItem.OnActionExpandListener onActionExpandListener) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3) {
        this.g = c2;
        this.i = Character.toLowerCase(c3);
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public void setShowAsAction(int i) {
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setShowAsActionFlags(int i) {
        setShowAsActionFlags(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(CharSequence charSequence) {
        this.f426d = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitleCondensed(CharSequence charSequence) {
        this.f427e = charSequence;
        return this;
    }

    @Override // android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setTooltipText(CharSequence charSequence) {
        setTooltipText(charSequence);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setVisible(boolean z) {
        this.s = (this.s & 8) | (z ? 0 : 8);
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public /* bridge */ /* synthetic */ MenuItem setActionView(View view) {
        setActionView(view);
        throw null;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public MenuItem setAlphabeticShortcut(char c2, int i) {
        this.i = Character.toLowerCase(c2);
        this.j = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public androidx.core.b.a.b setContentDescription(CharSequence charSequence) {
        this.m = charSequence;
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public MenuItem setNumericShortcut(char c2, int i) {
        this.g = c2;
        this.h = KeyEvent.normalizeMetaState(i);
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public androidx.core.b.a.b setShowAsActionFlags(int i) {
        setShowAsAction(i);
        return this;
    }

    @Override // android.view.MenuItem
    public MenuItem setTitle(int i) {
        this.f426d = this.l.getResources().getString(i);
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public androidx.core.b.a.b setTooltipText(CharSequence charSequence) {
        this.n = charSequence;
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public androidx.core.b.a.b setActionView(View view) {
        throw new UnsupportedOperationException();
    }

    @Override // android.view.MenuItem
    public MenuItem setIcon(int i) {
        this.k = androidx.core.content.a.c(this.l, i);
        b();
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public MenuItem setShortcut(char c2, char c3, int i, int i2) {
        this.g = c2;
        this.h = KeyEvent.normalizeMetaState(i);
        this.i = Character.toLowerCase(c3);
        this.j = KeyEvent.normalizeMetaState(i2);
        return this;
    }

    @Override // androidx.core.b.a.b, android.view.MenuItem
    public androidx.core.b.a.b setActionView(int i) {
        throw new UnsupportedOperationException();
    }
}
