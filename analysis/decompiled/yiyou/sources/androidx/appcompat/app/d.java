package androidx.appcompat.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.widget.i0;
import androidx.core.app.n;

/* JADX INFO: compiled from: AppCompatActivity.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends androidx.fragment.app.c implements e, n.a, b {
    private f p;
    private Resources q;

    @Override // androidx.appcompat.app.e
    public androidx.appcompat.d.b a(androidx.appcompat.d.b.a aVar) {
        return null;
    }

    @Override // androidx.appcompat.app.e
    public void a(androidx.appcompat.d.b bVar) {
    }

    public void a(n nVar) {
        nVar.a((Activity) this);
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        j().a(view, layoutParams);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        j().a(context);
    }

    protected void b(int i) {
    }

    @Override // androidx.appcompat.app.e
    public void b(androidx.appcompat.d.b bVar) {
    }

    public void b(n nVar) {
    }

    public boolean b(Intent intent) {
        return androidx.core.app.f.b(this, intent);
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        a aVarK = k();
        if (getWindow().hasFeature(0)) {
            if (aVarK == null || !aVarK.e()) {
                super.closeOptionsMenu();
            }
        }
    }

    @Override // androidx.core.app.e, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        a aVarK = k();
        if (keyCode == 82 && aVarK != null && aVarK.a(keyEvent)) {
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // androidx.core.app.n.a
    public Intent e() {
        return androidx.core.app.f.a(this);
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        return (T) j().a(i);
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        return j().b();
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.q == null && i0.b()) {
            this.q = new i0(this, super.getResources());
        }
        Resources resources = this.q;
        return resources == null ? super.getResources() : resources;
    }

    @Override // androidx.fragment.app.c
    public void i() {
        j().e();
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        j().e();
    }

    public f j() {
        if (this.p == null) {
            this.p = f.a(this, this);
        }
        return this.p;
    }

    public a k() {
        return j().c();
    }

    @Deprecated
    public void l() {
    }

    public boolean m() {
        Intent intentE = e();
        if (intentE == null) {
            return false;
        }
        if (!b(intentE)) {
            a(intentE);
            return true;
        }
        n nVarA = n.a((Context) this);
        a(nVarA);
        b(nVarA);
        nVarA.a();
        try {
            androidx.core.app.a.a((Activity) this);
            return true;
        } catch (IllegalStateException unused) {
            finish();
            return true;
        }
    }

    @Override // androidx.fragment.app.c, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.q != null) {
            this.q.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
        j().a(configuration);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        l();
    }

    @Override // androidx.fragment.app.c, androidx.activity.ComponentActivity, androidx.core.app.e, android.app.Activity
    protected void onCreate(Bundle bundle) {
        f fVarJ = j();
        fVarJ.d();
        fVarJ.a(bundle);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.c, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        j().f();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.c, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        a aVarK = k();
        if (menuItem.getItemId() != 16908332 || aVarK == null || (aVarK.g() & 4) == 0) {
            return false;
        }
        return m();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // androidx.fragment.app.c, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        j().b(bundle);
    }

    @Override // androidx.fragment.app.c, android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        j().g();
    }

    @Override // androidx.fragment.app.c, androidx.activity.ComponentActivity, androidx.core.app.e, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        j().c(bundle);
    }

    @Override // androidx.fragment.app.c, android.app.Activity
    protected void onStart() {
        super.onStart();
        j().h();
    }

    @Override // androidx.fragment.app.c, android.app.Activity
    protected void onStop() {
        super.onStop();
        j().i();
    }

    @Override // android.app.Activity
    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        j().a(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        a aVarK = k();
        if (getWindow().hasFeature(0)) {
            if (aVarK == null || !aVarK.k()) {
                super.openOptionsMenu();
            }
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        j().c(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        j().d(i);
    }

    public void a(Intent intent) {
        androidx.core.app.f.a(this, intent);
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        j().a(view);
    }

    private boolean a(int i, KeyEvent keyEvent) {
        Window window;
        return (Build.VERSION.SDK_INT >= 26 || keyEvent.isCtrlPressed() || KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) || keyEvent.getRepeatCount() != 0 || KeyEvent.isModifierKey(keyEvent.getKeyCode()) || (window = getWindow()) == null || window.getDecorView() == null || !window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) ? false : true;
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        j().b(view, layoutParams);
    }
}
