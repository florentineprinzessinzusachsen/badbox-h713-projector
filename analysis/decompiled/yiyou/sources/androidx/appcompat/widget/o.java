package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;

/* JADX INFO: compiled from: DecorToolbar.java */
/* JADX INFO: loaded from: classes.dex */
public interface o {
    androidx.core.f.x a(int i, long j);

    void a(int i);

    void a(Menu menu, androidx.appcompat.view.menu.m.a aVar);

    void a(ScrollingTabContainerView scrollingTabContainerView);

    void a(boolean z);

    boolean a();

    void b(int i);

    void b(boolean z);

    boolean b();

    void c(int i);

    boolean c();

    void collapseActionView();

    boolean d();

    void e();

    boolean f();

    void g();

    CharSequence getTitle();

    int h();

    ViewGroup i();

    Context j();

    int k();

    void l();

    boolean m();

    void n();

    void setIcon(int i);

    void setIcon(Drawable drawable);

    void setWindowCallback(Window.Callback callback);

    void setWindowTitle(CharSequence charSequence);
}
