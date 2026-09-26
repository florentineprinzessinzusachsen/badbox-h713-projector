package androidx.core.f;

import android.content.Context;
import android.util.Log;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

/* JADX INFO: compiled from: ActionProvider.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f1056a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private InterfaceC0023b f1057b;

    /* JADX INFO: compiled from: ActionProvider.java */
    public interface a {
        void b(boolean z);
    }

    /* JADX INFO: renamed from: androidx.core.f.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ActionProvider.java */
    public interface InterfaceC0023b {
        void onActionProviderVisibilityChanged(boolean z);
    }

    public b(Context context) {
    }

    public View a(MenuItem menuItem) {
        return c();
    }

    public void a(SubMenu subMenu) {
    }

    public boolean a() {
        return false;
    }

    public boolean b() {
        return true;
    }

    public abstract View c();

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public void f() {
        this.f1057b = null;
        this.f1056a = null;
    }

    public void a(boolean z) {
        a aVar = this.f1056a;
        if (aVar != null) {
            aVar.b(z);
        }
    }

    public void a(a aVar) {
        this.f1056a = aVar;
    }

    public void a(InterfaceC0023b interfaceC0023b) {
        if (this.f1057b != null && interfaceC0023b != null) {
            Log.w("ActionProvider(support)", "setVisibilityListener: Setting a new ActionProvider.VisibilityListener when one is already set. Are you reusing this " + getClass().getSimpleName() + " instance while it is still in use somewhere else?");
        }
        this.f1057b = interfaceC0023b;
    }
}
