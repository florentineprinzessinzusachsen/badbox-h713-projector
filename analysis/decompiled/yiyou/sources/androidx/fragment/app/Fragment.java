package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.lifecycle.s;
import androidx.lifecycle.t;
import androidx.savedstate.SavedStateRegistry;
import com.baidu.mobstat.Config;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, androidx.lifecycle.h, t, androidx.savedstate.b {
    static final Object X = new Object();
    boolean A;
    boolean B;
    boolean C;
    private boolean F;
    ViewGroup G;
    View H;
    View I;
    boolean J;
    d L;
    boolean M;
    boolean N;
    float O;
    LayoutInflater P;
    boolean Q;
    androidx.lifecycle.e.b R;
    androidx.lifecycle.i S;
    q T;
    androidx.lifecycle.m<androidx.lifecycle.h> U;
    androidx.savedstate.a V;
    private int W;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Bundle f1204b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    SparseArray<Parcelable> f1205c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    Boolean f1206d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    Bundle f1208f;
    Fragment g;
    int i;
    boolean k;
    boolean l;
    boolean m;
    boolean n;
    boolean o;
    boolean p;
    int q;
    i r;
    g s;
    Fragment u;
    int v;
    int w;
    String x;
    boolean y;
    boolean z;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f1203a = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    String f1207e = UUID.randomUUID().toString();
    String h = null;
    private Boolean j = null;
    i t = new i();
    boolean D = true;
    boolean K = true;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.p0();
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Fragment.this.e();
        }
    }

    class c extends androidx.fragment.app.d {
        c() {
        }

        @Override // androidx.fragment.app.d
        public View a(int i) {
            View view = Fragment.this.H;
            if (view != null) {
                return view.findViewById(i);
            }
            throw new IllegalStateException("Fragment " + this + " does not have a view");
        }

        @Override // androidx.fragment.app.d
        public boolean c() {
            return Fragment.this.H != null;
        }
    }

    static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        View f1213a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        Animator f1214b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1215c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f1216d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f1217e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f1218f;
        Object g = null;
        Object h;
        Object i;
        Object j;
        Object k;
        Object l;
        Boolean m;
        Boolean n;
        androidx.core.app.m o;
        androidx.core.app.m p;
        boolean q;
        f r;
        boolean s;

        d() {
            Object obj = Fragment.X;
            this.h = obj;
            this.i = null;
            this.j = obj;
            this.k = null;
            this.l = obj;
            this.o = null;
            this.p = null;
        }
    }

    public static class e extends RuntimeException {
        public e(String str, Exception exc) {
            super(str, exc);
        }
    }

    interface f {
        void a();

        void b();
    }

    public Fragment() {
        new a();
        this.R = androidx.lifecycle.e.b.RESUMED;
        this.U = new androidx.lifecycle.m<>();
        r0();
    }

    private d q0() {
        if (this.L == null) {
            this.L = new d();
        }
        return this.L;
    }

    private void r0() {
        this.S = new androidx.lifecycle.i(this);
        this.V = androidx.savedstate.a.a(this);
        if (Build.VERSION.SDK_INT >= 19) {
            this.S.a(new androidx.lifecycle.f() { // from class: androidx.fragment.app.Fragment.2
                @Override // androidx.lifecycle.f
                public void a(androidx.lifecycle.h hVar, androidx.lifecycle.e.a aVar) {
                    View view;
                    if (aVar != androidx.lifecycle.e.a.ON_STOP || (view = Fragment.this.H) == null) {
                        return;
                    }
                    view.cancelPendingInputEvents();
                }
            });
        }
    }

    public final boolean A() {
        return this.A;
    }

    public Object B() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.h;
        return obj == X ? n() : obj;
    }

    public Object C() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.k;
    }

    public Object D() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.l;
        return obj == X ? C() : obj;
    }

    int E() {
        d dVar = this.L;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1215c;
    }

    public final Fragment F() {
        String str;
        Fragment fragment = this.g;
        if (fragment != null) {
            return fragment;
        }
        i iVar = this.r;
        if (iVar == null || (str = this.h) == null) {
            return null;
        }
        return iVar.g.get(str);
    }

    @Deprecated
    public boolean G() {
        return this.K;
    }

    public View H() {
        return this.H;
    }

    void I() {
        r0();
        this.f1207e = UUID.randomUUID().toString();
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = false;
        this.q = 0;
        this.r = null;
        this.t = new i();
        this.s = null;
        this.v = 0;
        this.w = 0;
        this.x = null;
        this.y = false;
        this.z = false;
    }

    public final boolean J() {
        return this.s != null && this.k;
    }

    public final boolean K() {
        return this.z;
    }

    public final boolean L() {
        return this.y;
    }

    boolean M() {
        d dVar = this.L;
        if (dVar == null) {
            return false;
        }
        return dVar.s;
    }

    final boolean N() {
        return this.q > 0;
    }

    boolean O() {
        d dVar = this.L;
        if (dVar == null) {
            return false;
        }
        return dVar.q;
    }

    public final boolean P() {
        return this.l;
    }

    public final boolean Q() {
        return this.f1203a >= 4;
    }

    public final boolean R() {
        i iVar = this.r;
        if (iVar == null) {
            return false;
        }
        return iVar.y();
    }

    public final boolean S() {
        View view;
        return (!J() || L() || (view = this.H) == null || view.getWindowToken() == null || this.H.getVisibility() != 0) ? false : true;
    }

    void T() {
        this.t.z();
    }

    public void U() {
        this.F = true;
    }

    public void V() {
    }

    public void W() {
        this.F = true;
    }

    public void X() {
        this.F = true;
    }

    public void Y() {
        this.F = true;
    }

    public void Z() {
        this.F = true;
    }

    public Animation a(int i, boolean z, int i2) {
        return null;
    }

    @Override // androidx.lifecycle.h
    public androidx.lifecycle.e a() {
        return this.S;
    }

    public void a(int i, int i2, Intent intent) {
    }

    public void a(int i, String[] strArr, int[] iArr) {
    }

    public void a(Menu menu) {
    }

    public void a(Menu menu, MenuInflater menuInflater) {
    }

    public void a(View view, Bundle bundle) {
    }

    public void a(Fragment fragment) {
    }

    public void a(boolean z) {
    }

    public boolean a(MenuItem menuItem) {
        return false;
    }

    public void a0() {
        this.F = true;
    }

    public Animator b(int i, boolean z, int i2) {
        return null;
    }

    public void b(Bundle bundle) {
        this.F = true;
    }

    public void b(Menu menu) {
    }

    public void b(boolean z) {
    }

    public boolean b(MenuItem menuItem) {
        return false;
    }

    public void b0() {
        this.F = true;
    }

    @Override // androidx.savedstate.b
    public final SavedStateRegistry c() {
        return this.V.a();
    }

    public void c(boolean z) {
    }

    void c0() {
        this.t.a(this.s, new c(), this);
        this.F = false;
        a(this.s.f());
        if (this.F) {
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onAttach()");
    }

    @Override // androidx.lifecycle.t
    public s d() {
        i iVar = this.r;
        if (iVar != null) {
            return iVar.g(this);
        }
        throw new IllegalStateException("Can't access ViewModels from detached fragment");
    }

    public void d(boolean z) {
    }

    void d0() {
        this.t.k();
        this.S.a(androidx.lifecycle.e.a.ON_DESTROY);
        this.f1203a = 0;
        this.F = false;
        this.Q = false;
        U();
        if (this.F) {
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onDestroy()");
    }

    void e() {
        d dVar = this.L;
        f fVar = null;
        if (dVar != null) {
            dVar.q = false;
            f fVar2 = dVar.r;
            dVar.r = null;
            fVar = fVar2;
        }
        if (fVar != null) {
            fVar.a();
        }
    }

    public void e(Bundle bundle) {
    }

    void e0() {
        this.t.l();
        if (this.H != null) {
            this.T.a(androidx.lifecycle.e.a.ON_DESTROY);
        }
        this.f1203a = 1;
        this.F = false;
        W();
        if (this.F) {
            androidx.loader.a.a.a(this).a();
            this.p = false;
        } else {
            throw new r("Fragment " + this + " did not call through to super.onDestroyView()");
        }
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final androidx.fragment.app.c f() {
        g gVar = this.s;
        if (gVar == null) {
            return null;
        }
        return (androidx.fragment.app.c) gVar.e();
    }

    void f0() {
        this.F = false;
        X();
        this.P = null;
        if (this.F) {
            if (this.t.x()) {
                return;
            }
            this.t.k();
            this.t = new i();
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onDetach()");
    }

    public boolean g() {
        Boolean bool;
        d dVar = this.L;
        if (dVar == null || (bool = dVar.n) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    void g0() {
        onLowMemory();
        this.t.m();
    }

    public boolean h() {
        Boolean bool;
        d dVar = this.L;
        if (dVar == null || (bool = dVar.m) == null) {
            return true;
        }
        return bool.booleanValue();
    }

    void h0() {
        this.t.n();
        if (this.H != null) {
            this.T.a(androidx.lifecycle.e.a.ON_PAUSE);
        }
        this.S.a(androidx.lifecycle.e.a.ON_PAUSE);
        this.f1203a = 3;
        this.F = false;
        Y();
        if (this.F) {
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onPause()");
    }

    public final int hashCode() {
        return super.hashCode();
    }

    LayoutInflater i(Bundle bundle) {
        this.P = d(bundle);
        return this.P;
    }

    void i0() {
        boolean zI = this.r.i(this);
        Boolean bool = this.j;
        if (bool == null || bool.booleanValue() != zI) {
            this.j = Boolean.valueOf(zI);
            d(zI);
            this.t.o();
        }
    }

    void j(Bundle bundle) {
        e(bundle);
        this.V.b(bundle);
        Parcelable parcelableB = this.t.B();
        if (parcelableB != null) {
            bundle.putParcelable("android:support:fragments", parcelableB);
        }
    }

    void j0() {
        this.t.z();
        this.t.t();
        this.f1203a = 4;
        this.F = false;
        Z();
        if (!this.F) {
            throw new r("Fragment " + this + " did not call through to super.onResume()");
        }
        this.S.a(androidx.lifecycle.e.a.ON_RESUME);
        if (this.H != null) {
            this.T.a(androidx.lifecycle.e.a.ON_RESUME);
        }
        this.t.p();
        this.t.t();
    }

    public final Bundle k() {
        return this.f1208f;
    }

    void k0() {
        this.t.z();
        this.t.t();
        this.f1203a = 3;
        this.F = false;
        a0();
        if (this.F) {
            this.S.a(androidx.lifecycle.e.a.ON_START);
            if (this.H != null) {
                this.T.a(androidx.lifecycle.e.a.ON_START);
            }
            this.t.q();
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onStart()");
    }

    final void l(Bundle bundle) {
        SparseArray<Parcelable> sparseArray = this.f1205c;
        if (sparseArray != null) {
            this.I.restoreHierarchyState(sparseArray);
            this.f1205c = null;
        }
        this.F = false;
        f(bundle);
        if (this.F) {
            if (this.H != null) {
                this.T.a(androidx.lifecycle.e.a.ON_CREATE);
            }
        } else {
            throw new r("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
    }

    void l0() {
        this.t.r();
        if (this.H != null) {
            this.T.a(androidx.lifecycle.e.a.ON_STOP);
        }
        this.S.a(androidx.lifecycle.e.a.ON_STOP);
        this.f1203a = 2;
        this.F = false;
        b0();
        if (this.F) {
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onStop()");
    }

    public void m(Bundle bundle) {
        if (this.r != null && R()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.f1208f = bundle;
    }

    public final androidx.fragment.app.c m0() {
        androidx.fragment.app.c cVarF = f();
        if (cVarF != null) {
            return cVarF;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
    }

    public Object n() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.g;
    }

    public final Context n0() {
        Context contextM = m();
        if (contextM != null) {
            return contextM;
        }
        throw new IllegalStateException("Fragment " + this + " not attached to a context.");
    }

    androidx.core.app.m o() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.o;
    }

    public final View o0() {
        View viewH = H();
        if (viewH != null) {
            return viewH;
        }
        throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        this.F = true;
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        m0().onCreateContextMenu(contextMenu, view, contextMenuInfo);
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.F = true;
    }

    public Object p() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.i;
    }

    public void p0() {
        i iVar = this.r;
        if (iVar == null || iVar.q == null) {
            q0().q = false;
        } else if (Looper.myLooper() != this.r.q.g().getLooper()) {
            this.r.q.g().postAtFrontOfQueue(new b());
        } else {
            e();
        }
    }

    androidx.core.app.m q() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.p;
    }

    public final h r() {
        return this.r;
    }

    public final Object s() {
        g gVar = this.s;
        if (gVar == null) {
            return null;
        }
        return gVar.h();
    }

    public final int t() {
        return this.v;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        androidx.core.e.a.a(this, sb);
        sb.append(" (");
        sb.append(this.f1207e);
        sb.append(")");
        if (this.v != 0) {
            sb.append(" id=0x");
            sb.append(Integer.toHexString(this.v));
        }
        if (this.x != null) {
            sb.append(" ");
            sb.append(this.x);
        }
        sb.append('}');
        return sb.toString();
    }

    int u() {
        d dVar = this.L;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1216d;
    }

    int v() {
        d dVar = this.L;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1217e;
    }

    int w() {
        d dVar = this.L;
        if (dVar == null) {
            return 0;
        }
        return dVar.f1218f;
    }

    public final Fragment x() {
        return this.u;
    }

    public Object y() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        Object obj = dVar.j;
        return obj == X ? p() : obj;
    }

    public final Resources z() {
        return n0().getResources();
    }

    @Deprecated
    public static Fragment a(Context context, String str, Bundle bundle) {
        try {
            Fragment fragmentNewInstance = androidx.fragment.app.f.d(context.getClassLoader(), str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (bundle != null) {
                bundle.setClassLoader(fragmentNewInstance.getClass().getClassLoader());
                fragmentNewInstance.m(bundle);
            }
            return fragmentNewInstance;
        } catch (IllegalAccessException e2) {
            throw new e("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e2);
        } catch (InstantiationException e3) {
            throw new e("Unable to instantiate fragment " + str + ": make sure class name exists, is public, and has an empty constructor that is public", e3);
        } catch (NoSuchMethodException e4) {
            throw new e("Unable to instantiate fragment " + str + ": could not find Fragment constructor", e4);
        } catch (InvocationTargetException e5) {
            throw new e("Unable to instantiate fragment " + str + ": calling Fragment constructor caused an exception", e5);
        }
    }

    void b(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.t.z();
        this.p = true;
        this.T = new q();
        this.H = a(layoutInflater, viewGroup, bundle);
        if (this.H != null) {
            this.T.d();
            this.U.a(this.T);
        } else {
            if (this.T.e()) {
                throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
            }
            this.T = null;
        }
    }

    public void c(Bundle bundle) {
        this.F = true;
        k(bundle);
        if (this.t.d(1)) {
            return;
        }
        this.t.j();
    }

    public void f(Bundle bundle) {
        this.F = true;
    }

    void k(Bundle bundle) {
        Parcelable parcelable;
        if (bundle == null || (parcelable = bundle.getParcelable("android:support:fragments")) == null) {
            return;
        }
        this.t.a(parcelable);
        this.t.j();
    }

    void f(boolean z) {
        c(z);
        this.t.b(z);
    }

    void g(Bundle bundle) {
        this.t.z();
        this.f1203a = 2;
        this.F = false;
        b(bundle);
        if (this.F) {
            this.t.i();
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    void h(Bundle bundle) {
        this.t.z();
        this.f1203a = 1;
        this.F = false;
        this.V.a(bundle);
        c(bundle);
        this.Q = true;
        if (this.F) {
            this.S.a(androidx.lifecycle.e.a.ON_CREATE);
            return;
        }
        throw new r("Fragment " + this + " did not call through to super.onCreate()");
    }

    public LayoutInflater d(Bundle bundle) {
        return a(bundle);
    }

    View i() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.f1213a;
    }

    public Context m() {
        g gVar = this.s;
        if (gVar == null) {
            return null;
        }
        return gVar.f();
    }

    boolean d(Menu menu) {
        boolean z = false;
        if (this.y) {
            return false;
        }
        if (this.C && this.D) {
            z = true;
            b(menu);
        }
        return z | this.t.b(menu);
    }

    Animator j() {
        d dVar = this.L;
        if (dVar == null) {
            return null;
        }
        return dVar.f1214b;
    }

    boolean c(MenuItem menuItem) {
        if (this.y) {
            return false;
        }
        return a(menuItem) || this.t.a(menuItem);
    }

    void e(boolean z) {
        b(z);
        this.t.a(z);
    }

    void c(Menu menu) {
        if (this.y) {
            return;
        }
        if (this.C && this.D) {
            a(menu);
        }
        this.t.a(menu);
    }

    boolean d(MenuItem menuItem) {
        if (this.y) {
            return false;
        }
        return (this.C && this.D && b(menuItem)) || this.t.b(menuItem);
    }

    void g(boolean z) {
        q0().s = z;
    }

    public final h l() {
        if (this.s != null) {
            return this.t;
        }
        throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
    }

    @Deprecated
    public LayoutInflater a(Bundle bundle) {
        g gVar = this.s;
        if (gVar != null) {
            LayoutInflater layoutInflaterI = gVar.i();
            i iVar = this.t;
            iVar.u();
            androidx.core.f.e.b(layoutInflaterI, iVar);
            return layoutInflaterI;
        }
        throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
    }

    boolean b(Menu menu, MenuInflater menuInflater) {
        boolean z = false;
        if (this.y) {
            return false;
        }
        if (this.C && this.D) {
            z = true;
            a(menu, menuInflater);
        }
        return z | this.t.a(menu, menuInflater);
    }

    public void a(Context context, AttributeSet attributeSet, Bundle bundle) {
        this.F = true;
        g gVar = this.s;
        Activity activityE = gVar == null ? null : gVar.e();
        if (activityE != null) {
            this.F = false;
            a(activityE, attributeSet, bundle);
        }
    }

    void b(int i) {
        q0().f1215c = i;
    }

    @Deprecated
    public void a(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        this.F = true;
    }

    public void a(Context context) {
        this.F = true;
        g gVar = this.s;
        Activity activityE = gVar == null ? null : gVar.e();
        if (activityE != null) {
            this.F = false;
            a(activityE);
        }
    }

    @Deprecated
    public void a(Activity activity) {
        this.F = true;
    }

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i = this.W;
        if (i != 0) {
            return layoutInflater.inflate(i, viewGroup, false);
        }
        return null;
    }

    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.print(str);
        printWriter.print("mFragmentId=#");
        printWriter.print(Integer.toHexString(this.v));
        printWriter.print(" mContainerId=#");
        printWriter.print(Integer.toHexString(this.w));
        printWriter.print(" mTag=");
        printWriter.println(this.x);
        printWriter.print(str);
        printWriter.print("mState=");
        printWriter.print(this.f1203a);
        printWriter.print(" mWho=");
        printWriter.print(this.f1207e);
        printWriter.print(" mBackStackNesting=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("mAdded=");
        printWriter.print(this.k);
        printWriter.print(" mRemoving=");
        printWriter.print(this.l);
        printWriter.print(" mFromLayout=");
        printWriter.print(this.m);
        printWriter.print(" mInLayout=");
        printWriter.println(this.n);
        printWriter.print(str);
        printWriter.print("mHidden=");
        printWriter.print(this.y);
        printWriter.print(" mDetached=");
        printWriter.print(this.z);
        printWriter.print(" mMenuVisible=");
        printWriter.print(this.D);
        printWriter.print(" mHasMenu=");
        printWriter.println(this.C);
        printWriter.print(str);
        printWriter.print("mRetainInstance=");
        printWriter.print(this.A);
        printWriter.print(" mUserVisibleHint=");
        printWriter.println(this.K);
        if (this.r != null) {
            printWriter.print(str);
            printWriter.print("mFragmentManager=");
            printWriter.println(this.r);
        }
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("mHost=");
            printWriter.println(this.s);
        }
        if (this.u != null) {
            printWriter.print(str);
            printWriter.print("mParentFragment=");
            printWriter.println(this.u);
        }
        if (this.f1208f != null) {
            printWriter.print(str);
            printWriter.print("mArguments=");
            printWriter.println(this.f1208f);
        }
        if (this.f1204b != null) {
            printWriter.print(str);
            printWriter.print("mSavedFragmentState=");
            printWriter.println(this.f1204b);
        }
        if (this.f1205c != null) {
            printWriter.print(str);
            printWriter.print("mSavedViewState=");
            printWriter.println(this.f1205c);
        }
        Fragment fragmentF = F();
        if (fragmentF != null) {
            printWriter.print(str);
            printWriter.print("mTarget=");
            printWriter.print(fragmentF);
            printWriter.print(" mTargetRequestCode=");
            printWriter.println(this.i);
        }
        if (u() != 0) {
            printWriter.print(str);
            printWriter.print("mNextAnim=");
            printWriter.println(u());
        }
        if (this.G != null) {
            printWriter.print(str);
            printWriter.print("mContainer=");
            printWriter.println(this.G);
        }
        if (this.H != null) {
            printWriter.print(str);
            printWriter.print("mView=");
            printWriter.println(this.H);
        }
        if (this.I != null) {
            printWriter.print(str);
            printWriter.print("mInnerView=");
            printWriter.println(this.H);
        }
        if (i() != null) {
            printWriter.print(str);
            printWriter.print("mAnimatingAway=");
            printWriter.println(i());
            printWriter.print(str);
            printWriter.print("mStateAfterAnimating=");
            printWriter.println(E());
        }
        if (m() != null) {
            androidx.loader.a.a.a(this).a(str, fileDescriptor, printWriter, strArr);
        }
        printWriter.print(str);
        printWriter.println("Child " + this.t + Config.TRACE_TODAY_VISIT_SPLIT);
        this.t.a(str + "  ", fileDescriptor, printWriter, strArr);
    }

    Fragment a(String str) {
        return str.equals(this.f1207e) ? this : this.t.b(str);
    }

    void a(Configuration configuration) {
        onConfigurationChanged(configuration);
        this.t.a(configuration);
    }

    void a(f fVar) {
        q0();
        f fVar2 = this.L.r;
        if (fVar == fVar2) {
            return;
        }
        if (fVar != null && fVar2 != null) {
            throw new IllegalStateException("Trying to set a replacement startPostponedEnterTransition on " + this);
        }
        d dVar = this.L;
        if (dVar.q) {
            dVar.r = fVar;
        }
        if (fVar != null) {
            fVar.b();
        }
    }

    void a(int i) {
        if (this.L == null && i == 0) {
            return;
        }
        q0().f1216d = i;
    }

    void a(int i, int i2) {
        if (this.L == null && i == 0 && i2 == 0) {
            return;
        }
        q0();
        d dVar = this.L;
        dVar.f1217e = i;
        dVar.f1218f = i2;
    }

    void a(View view) {
        q0().f1213a = view;
    }

    void a(Animator animator) {
        q0().f1214b = animator;
    }
}
