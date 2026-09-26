package androidx.fragment.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.lifecycle.t;

/* JADX INFO: compiled from: FragmentController.java */
/* JADX INFO: loaded from: classes.dex */
public class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final g<?> f1237a;

    private e(g<?> gVar) {
        this.f1237a = gVar;
    }

    public static e a(g<?> gVar) {
        androidx.core.e.e.a(gVar, "callbacks == null");
        return new e(gVar);
    }

    public void b() {
        this.f1237a.f1243e.j();
    }

    public void c() {
        this.f1237a.f1243e.k();
    }

    public void d() {
        this.f1237a.f1243e.m();
    }

    public void e() {
        this.f1237a.f1243e.n();
    }

    public void f() {
        this.f1237a.f1243e.p();
    }

    public void g() {
        this.f1237a.f1243e.q();
    }

    public void h() {
        this.f1237a.f1243e.r();
    }

    public boolean i() {
        return this.f1237a.f1243e.t();
    }

    public h j() {
        return this.f1237a.f1243e;
    }

    public void k() {
        this.f1237a.f1243e.z();
    }

    public Parcelable l() {
        return this.f1237a.f1243e.B();
    }

    public Fragment a(String str) {
        return this.f1237a.f1243e.b(str);
    }

    public void b(boolean z) {
        this.f1237a.f1243e.b(z);
    }

    public void a(Fragment fragment) {
        g<?> gVar = this.f1237a;
        gVar.f1243e.a(gVar, gVar, fragment);
    }

    public boolean b(Menu menu) {
        return this.f1237a.f1243e.b(menu);
    }

    public View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f1237a.f1243e.onCreateView(view, str, context, attributeSet);
    }

    public boolean b(MenuItem menuItem) {
        return this.f1237a.f1243e.b(menuItem);
    }

    public void a(Parcelable parcelable) {
        g<?> gVar = this.f1237a;
        if (gVar instanceof t) {
            gVar.f1243e.a(parcelable);
            return;
        }
        throw new IllegalStateException("Your FragmentHostCallback must implement ViewModelStoreOwner to call restoreSaveState(). Call restoreAllState()  if you're still using retainNestedNonConfig().");
    }

    public void a() {
        this.f1237a.f1243e.i();
    }

    public void a(boolean z) {
        this.f1237a.f1243e.a(z);
    }

    public void a(Configuration configuration) {
        this.f1237a.f1243e.a(configuration);
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        return this.f1237a.f1243e.a(menu, menuInflater);
    }

    public boolean a(MenuItem menuItem) {
        return this.f1237a.f1243e.a(menuItem);
    }

    public void a(Menu menu) {
        this.f1237a.f1243e.a(menu);
    }
}
