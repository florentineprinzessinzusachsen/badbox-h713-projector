package androidx.fragment.app;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import androidx.activity.OnBackPressedDispatcher;
import androidx.lifecycle.s;
import androidx.lifecycle.t;
import com.baidu.mobstat.Config;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: compiled from: FragmentManagerImpl.java */
/* JADX INFO: loaded from: classes.dex */
final class i extends androidx.fragment.app.h implements LayoutInflater.Factory2 {
    static boolean I = false;
    static final Interpolator J = new DecelerateInterpolator(2.5f);
    static final Interpolator K = new DecelerateInterpolator(1.5f);
    ArrayList<Boolean> A;
    ArrayList<Fragment> B;
    ArrayList<m> F;
    private androidx.fragment.app.k G;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    ArrayList<k> f1246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1247d;
    ArrayList<androidx.fragment.app.a> h;
    ArrayList<Fragment> i;
    private OnBackPressedDispatcher j;
    ArrayList<androidx.fragment.app.a> l;
    ArrayList<Integer> m;
    ArrayList<androidx.fragment.app.h.c> n;
    androidx.fragment.app.g q;
    androidx.fragment.app.d r;
    Fragment s;
    Fragment t;
    boolean u;
    boolean v;
    boolean w;
    boolean x;
    boolean y;
    ArrayList<androidx.fragment.app.a> z;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f1248e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final ArrayList<Fragment> f1249f = new ArrayList<>();
    final HashMap<String, Fragment> g = new HashMap<>();
    private final androidx.activity.b k = new a(false);
    private final CopyOnWriteArrayList<C0029i> o = new CopyOnWriteArrayList<>();
    int p = 0;
    Bundle C = null;
    SparseArray<Parcelable> D = null;
    Runnable H = new b();

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class a extends androidx.activity.b {
        a(boolean z) {
            super(z);
        }

        @Override // androidx.activity.b
        public void a() {
            i.this.w();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            i.this.t();
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class c implements Animation.AnimationListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1252a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Fragment f1253b;

        /* JADX INFO: compiled from: FragmentManagerImpl.java */
        class a implements Runnable {
            a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (c.this.f1253b.i() != null) {
                    c.this.f1253b.a((View) null);
                    c cVar = c.this;
                    i iVar = i.this;
                    Fragment fragment = cVar.f1253b;
                    iVar.a(fragment, fragment.E(), 0, 0, false);
                }
            }
        }

        c(ViewGroup viewGroup, Fragment fragment) {
            this.f1252a = viewGroup;
            this.f1253b = fragment;
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            this.f1252a.post(new a());
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class d extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1256a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1257b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1258c;

        d(ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1256a = viewGroup;
            this.f1257b = view;
            this.f1258c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1256a.endViewTransition(this.f1257b);
            Animator animatorJ = this.f1258c.j();
            this.f1258c.a((Animator) null);
            if (animatorJ == null || this.f1256a.indexOfChild(this.f1257b) >= 0) {
                return;
            }
            i iVar = i.this;
            Fragment fragment = this.f1258c;
            iVar.a(fragment, fragment.E(), 0, 0, false);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class e extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ViewGroup f1260a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f1261b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ Fragment f1262c;

        e(i iVar, ViewGroup viewGroup, View view, Fragment fragment) {
            this.f1260a = viewGroup;
            this.f1261b = view;
            this.f1262c = fragment;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            this.f1260a.endViewTransition(this.f1261b);
            animator.removeListener(this);
            Fragment fragment = this.f1262c;
            View view = fragment.H;
            if (view == null || !fragment.y) {
                return;
            }
            view.setVisibility(8);
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    class f extends androidx.fragment.app.f {
        f() {
        }

        @Override // androidx.fragment.app.f
        public Fragment a(ClassLoader classLoader, String str) {
            androidx.fragment.app.g gVar = i.this.q;
            return gVar.a(gVar.f(), str, null);
        }
    }

    /* JADX INFO: renamed from: androidx.fragment.app.i$i, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static final class C0029i {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final androidx.fragment.app.h.b f1271a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final boolean f1272b;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    static class j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final int[] f1273a = {R.attr.name, R.attr.id, R.attr.tag};
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    interface k {
        boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private class l implements k {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final String f1274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f1275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final int f1276c;

        l(String str, int i, int i2) {
            this.f1274a = str;
            this.f1275b = i;
            this.f1276c = i2;
        }

        @Override // androidx.fragment.app.i.k
        public boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
            Fragment fragment = i.this.t;
            if (fragment == null || this.f1275b >= 0 || this.f1274a != null || !fragment.l().g()) {
                return i.this.a(arrayList, arrayList2, this.f1274a, this.f1275b, this.f1276c);
            }
            return false;
        }
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    static class m implements Fragment.f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final boolean f1278a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final androidx.fragment.app.a f1279b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f1280c;

        m(androidx.fragment.app.a aVar, boolean z) {
            this.f1278a = z;
            this.f1279b = aVar;
        }

        @Override // androidx.fragment.app.Fragment.f
        public void a() {
            this.f1280c--;
            if (this.f1280c != 0) {
                return;
            }
            this.f1279b.s.C();
        }

        @Override // androidx.fragment.app.Fragment.f
        public void b() {
            this.f1280c++;
        }

        public void c() {
            androidx.fragment.app.a aVar = this.f1279b;
            aVar.s.a(aVar, this.f1278a, false, false);
        }

        public void d() {
            boolean z = this.f1280c > 0;
            i iVar = this.f1279b.s;
            int size = iVar.f1249f.size();
            for (int i = 0; i < size; i++) {
                Fragment fragment = iVar.f1249f.get(i);
                fragment.a((Fragment.f) null);
                if (z && fragment.O()) {
                    fragment.p0();
                }
            }
            androidx.fragment.app.a aVar = this.f1279b;
            aVar.s.a(aVar, this.f1278a, !z, true);
        }

        public boolean e() {
            return this.f1280c == 0;
        }
    }

    i() {
    }

    private void E() {
        this.g.values().removeAll(Collections.singleton(null));
    }

    private void F() {
        if (y()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void G() {
        this.f1247d = false;
        this.A.clear();
        this.z.clear();
    }

    private void H() {
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                if (fragment.i() != null) {
                    int iE = fragment.E();
                    View viewI = fragment.i();
                    Animation animation = viewI.getAnimation();
                    if (animation != null) {
                        animation.cancel();
                        viewI.clearAnimation();
                    }
                    fragment.a((View) null);
                    a(fragment, iE, 0, 0, false);
                } else if (fragment.j() != null) {
                    fragment.j().end();
                }
            }
        }
    }

    private void I() {
        if (this.F != null) {
            while (!this.F.isEmpty()) {
                this.F.remove(0).d();
            }
        }
    }

    private void J() {
        ArrayList<k> arrayList = this.f1246c;
        if (arrayList == null || arrayList.isEmpty()) {
            this.k.a(c() > 0 && i(this.s));
        } else {
            this.k.a(true);
        }
    }

    private void a(RuntimeException runtimeException) {
        Log.e("FragmentManager", runtimeException.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter = new PrintWriter(new androidx.core.e.b("FragmentManager"));
        androidx.fragment.app.g gVar = this.q;
        if (gVar != null) {
            try {
                gVar.a("  ", null, printWriter, new String[0]);
                throw runtimeException;
            } catch (Exception e2) {
                Log.e("FragmentManager", "Failed dumping state", e2);
                throw runtimeException;
            }
        }
        try {
            a("  ", (FileDescriptor) null, printWriter, new String[0]);
            throw runtimeException;
        } catch (Exception e3) {
            Log.e("FragmentManager", "Failed dumping state", e3);
            throw runtimeException;
        }
    }

    public static int b(int i, boolean z) {
        if (i == 4097) {
            return z ? 1 : 2;
        }
        if (i == 4099) {
            return z ? 5 : 6;
        }
        if (i != 8194) {
            return -1;
        }
        return z ? 3 : 4;
    }

    public static int f(int i) {
        if (i == 4097) {
            return com.umeng.analytics.pro.k.a.o;
        }
        if (i == 4099) {
            return com.umeng.analytics.pro.k.a.f3684c;
        }
        if (i != 8194) {
            return 0;
        }
        return com.umeng.analytics.pro.k.a.f3682a;
    }

    private void u(Fragment fragment) {
        if (fragment == null || this.g.get(fragment.f1207e) != fragment) {
            return;
        }
        fragment.i0();
    }

    private Fragment v(Fragment fragment) {
        ViewGroup viewGroup = fragment.G;
        View view = fragment.H;
        if (viewGroup != null && view != null) {
            for (int iIndexOf = this.f1249f.indexOf(fragment) - 1; iIndexOf >= 0; iIndexOf--) {
                Fragment fragment2 = this.f1249f.get(iIndexOf);
                if (fragment2.G == viewGroup && fragment2.H != null) {
                    return fragment2;
                }
            }
        }
        return null;
    }

    void A() {
        if (this.n != null) {
            for (int i = 0; i < this.n.size(); i++) {
                this.n.get(i).a();
            }
        }
    }

    Parcelable B() {
        ArrayList<String> arrayList;
        int size;
        I();
        H();
        t();
        this.v = true;
        androidx.fragment.app.b[] bVarArr = null;
        if (this.g.isEmpty()) {
            return null;
        }
        ArrayList<androidx.fragment.app.l> arrayList2 = new ArrayList<>(this.g.size());
        boolean z = false;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                if (fragment.r != this) {
                    a(new IllegalStateException("Failure saving state: active " + fragment + " was removed from the FragmentManager"));
                    throw null;
                }
                androidx.fragment.app.l lVar = new androidx.fragment.app.l(fragment);
                arrayList2.add(lVar);
                if (fragment.f1203a <= 0 || lVar.m != null) {
                    lVar.m = fragment.f1204b;
                } else {
                    lVar.m = q(fragment);
                    String str = fragment.h;
                    if (str != null) {
                        Fragment fragment2 = this.g.get(str);
                        if (fragment2 == null) {
                            a(new IllegalStateException("Failure saving state: " + fragment + " has target not in fragment manager: " + fragment.h));
                            throw null;
                        }
                        if (lVar.m == null) {
                            lVar.m = new Bundle();
                        }
                        a(lVar.m, "android:target_state", fragment2);
                        int i = fragment.i;
                        if (i != 0) {
                            lVar.m.putInt("android:target_req_state", i);
                        }
                    }
                }
                if (I) {
                    Log.v("FragmentManager", "Saved state of " + fragment + ": " + lVar.m);
                }
                z = true;
            }
        }
        if (!z) {
            if (I) {
                Log.v("FragmentManager", "saveAllState: no fragments!");
            }
            return null;
        }
        int size2 = this.f1249f.size();
        if (size2 > 0) {
            arrayList = new ArrayList<>(size2);
            for (Fragment fragment3 : this.f1249f) {
                arrayList.add(fragment3.f1207e);
                if (fragment3.r != this) {
                    a(new IllegalStateException("Failure saving state: active " + fragment3 + " was removed from the FragmentManager"));
                    throw null;
                }
                if (I) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment3.f1207e + "): " + fragment3);
                }
            }
        } else {
            arrayList = null;
        }
        ArrayList<androidx.fragment.app.a> arrayList3 = this.h;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            bVarArr = new androidx.fragment.app.b[size];
            for (int i2 = 0; i2 < size; i2++) {
                bVarArr[i2] = new androidx.fragment.app.b(this.h.get(i2));
                if (I) {
                    Log.v("FragmentManager", "saveAllState: adding back stack #" + i2 + ": " + this.h.get(i2));
                }
            }
        }
        androidx.fragment.app.j jVar = new androidx.fragment.app.j();
        jVar.f1281a = arrayList2;
        jVar.f1282b = arrayList;
        jVar.f1283c = bVarArr;
        Fragment fragment4 = this.t;
        if (fragment4 != null) {
            jVar.f1284d = fragment4.f1207e;
        }
        jVar.f1285e = this.f1248e;
        return jVar;
    }

    void C() {
        synchronized (this) {
            boolean z = false;
            boolean z2 = (this.F == null || this.F.isEmpty()) ? false : true;
            if (this.f1246c != null && this.f1246c.size() == 1) {
                z = true;
            }
            if (z2 || z) {
                this.q.g().removeCallbacks(this.H);
                this.q.g().post(this.H);
                J();
            }
        }
    }

    void D() {
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                n(fragment);
            }
        }
    }

    @Override // androidx.fragment.app.h
    public boolean b() {
        boolean zT = t();
        I();
        return zT;
    }

    @Override // androidx.fragment.app.h
    public int c() {
        ArrayList<androidx.fragment.app.a> arrayList = this.h;
        if (arrayList != null) {
            return arrayList.size();
        }
        return 0;
    }

    boolean d(int i) {
        return this.p >= i;
    }

    @Override // androidx.fragment.app.h
    public List<Fragment> e() {
        List<Fragment> list;
        if (this.f1249f.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f1249f) {
            list = (List) this.f1249f.clone();
        }
        return list;
    }

    @Override // androidx.fragment.app.h
    public void f() {
        a((k) new l(null, -1, 0), false);
    }

    @Override // androidx.fragment.app.h
    public boolean g() {
        F();
        return a((String) null, -1, 0);
    }

    public void h(Fragment fragment) {
        if (I) {
            Log.v("FragmentManager", "hide: " + fragment);
        }
        if (fragment.y) {
            return;
        }
        fragment.y = true;
        fragment.N = true ^ fragment.N;
    }

    boolean i(Fragment fragment) {
        if (fragment == null) {
            return true;
        }
        i iVar = fragment.r;
        return fragment == iVar.v() && i(iVar.s);
    }

    void j(Fragment fragment) {
        if (this.g.get(fragment.f1207e) != null) {
            return;
        }
        this.g.put(fragment.f1207e, fragment);
        if (fragment.B) {
            if (fragment.A) {
                a(fragment);
            } else {
                p(fragment);
            }
            fragment.B = false;
        }
        if (I) {
            Log.v("FragmentManager", "Added fragment to active set " + fragment);
        }
    }

    void k(Fragment fragment) {
        if (this.g.get(fragment.f1207e) == null) {
            return;
        }
        if (I) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment);
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && fragment.f1207e.equals(fragment2.h)) {
                fragment2.g = fragment;
                fragment2.h = null;
            }
        }
        this.g.put(fragment.f1207e, null);
        p(fragment);
        String str = fragment.h;
        if (str != null) {
            fragment.g = this.g.get(str);
        }
        fragment.I();
    }

    void l(Fragment fragment) {
        if (fragment == null) {
            return;
        }
        if (!this.g.containsKey(fragment.f1207e)) {
            if (I) {
                Log.v("FragmentManager", "Ignoring moving " + fragment + " to state " + this.p + "since it is not added to " + this);
                return;
            }
            return;
        }
        int iMin = this.p;
        if (fragment.l) {
            iMin = fragment.N() ? Math.min(iMin, 1) : Math.min(iMin, 0);
        }
        a(fragment, iMin, fragment.v(), fragment.w(), false);
        if (fragment.H != null) {
            Fragment fragmentV = v(fragment);
            if (fragmentV != null) {
                View view = fragmentV.H;
                ViewGroup viewGroup = fragment.G;
                int iIndexOfChild = viewGroup.indexOfChild(view);
                int iIndexOfChild2 = viewGroup.indexOfChild(fragment.H);
                if (iIndexOfChild2 < iIndexOfChild) {
                    viewGroup.removeViewAt(iIndexOfChild2);
                    viewGroup.addView(fragment.H, iIndexOfChild);
                }
            }
            if (fragment.M && fragment.G != null) {
                float f2 = fragment.O;
                if (f2 > 0.0f) {
                    fragment.H.setAlpha(f2);
                }
                fragment.O = 0.0f;
                fragment.M = false;
                g gVarA = a(fragment, fragment.v(), true, fragment.w());
                if (gVarA != null) {
                    Animation animation = gVarA.f1264a;
                    if (animation != null) {
                        fragment.H.startAnimation(animation);
                    } else {
                        gVarA.f1265b.setTarget(fragment.H);
                        gVarA.f1265b.start();
                    }
                }
            }
        }
        if (fragment.N) {
            c(fragment);
        }
    }

    void m(Fragment fragment) {
        a(fragment, this.p, 0, 0, false);
    }

    public void n(Fragment fragment) {
        if (fragment.J) {
            if (this.f1247d) {
                this.y = true;
            } else {
                fragment.J = false;
                a(fragment, this.p, 0, 0, false);
            }
        }
    }

    public void o(Fragment fragment) {
        if (I) {
            Log.v("FragmentManager", "remove: " + fragment + " nesting=" + fragment.q);
        }
        boolean z = !fragment.N();
        if (!fragment.z || z) {
            synchronized (this.f1249f) {
                this.f1249f.remove(fragment);
            }
            if (w(fragment)) {
                this.u = true;
            }
            fragment.k = false;
            fragment.l = true;
        }
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        Fragment fragment;
        if (!"fragment".equals(str)) {
            return null;
        }
        String attributeValue = attributeSet.getAttributeValue(null, "class");
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j.f1273a);
        if (attributeValue == null) {
            attributeValue = typedArrayObtainStyledAttributes.getString(0);
        }
        String str2 = attributeValue;
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        String string = typedArrayObtainStyledAttributes.getString(2);
        typedArrayObtainStyledAttributes.recycle();
        if (str2 == null || !androidx.fragment.app.f.b(context.getClassLoader(), str2)) {
            return null;
        }
        int id = view != null ? view.getId() : 0;
        if (id == -1 && resourceId == -1 && string == null) {
            throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Must specify unique android:id, android:tag, or have a parent with an id for " + str2);
        }
        Fragment fragmentB = resourceId != -1 ? b(resourceId) : null;
        if (fragmentB == null && string != null) {
            fragmentB = a(string);
        }
        if (fragmentB == null && id != -1) {
            fragmentB = b(id);
        }
        if (I) {
            Log.v("FragmentManager", "onCreateView: id=0x" + Integer.toHexString(resourceId) + " fname=" + str2 + " existing=" + fragmentB);
        }
        if (fragmentB == null) {
            Fragment fragmentA = d().a(context.getClassLoader(), str2);
            fragmentA.m = true;
            fragmentA.v = resourceId != 0 ? resourceId : id;
            fragmentA.w = id;
            fragmentA.x = string;
            fragmentA.n = true;
            fragmentA.r = this;
            androidx.fragment.app.g gVar = this.q;
            fragmentA.s = gVar;
            fragmentA.a(gVar.f(), attributeSet, fragmentA.f1204b);
            a(fragmentA, true);
            fragment = fragmentA;
        } else {
            if (fragmentB.n) {
                throw new IllegalArgumentException(attributeSet.getPositionDescription() + ": Duplicate id 0x" + Integer.toHexString(resourceId) + ", tag " + string + ", or parent id 0x" + Integer.toHexString(id) + " with another fragment for " + str2);
            }
            fragmentB.n = true;
            androidx.fragment.app.g gVar2 = this.q;
            fragmentB.s = gVar2;
            fragmentB.a(gVar2.f(), attributeSet, fragmentB.f1204b);
            fragment = fragmentB;
        }
        if (this.p >= 1 || !fragment.m) {
            m(fragment);
        } else {
            a(fragment, 1, 0, 0, false);
        }
        View view2 = fragment.H;
        if (view2 != null) {
            if (resourceId != 0) {
                view2.setId(resourceId);
            }
            if (fragment.H.getTag() == null) {
                fragment.H.setTag(string);
            }
            return fragment.H;
        }
        throw new IllegalStateException("Fragment " + str2 + " did not create a view.");
    }

    void p(Fragment fragment) {
        if (y()) {
            if (I) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
            }
        } else if (this.G.e(fragment) && I) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    Bundle q(Fragment fragment) {
        Bundle bundle;
        if (this.C == null) {
            this.C = new Bundle();
        }
        fragment.j(this.C);
        d(fragment, this.C, false);
        if (this.C.isEmpty()) {
            bundle = null;
        } else {
            bundle = this.C;
            this.C = null;
        }
        if (fragment.H != null) {
            r(fragment);
        }
        if (fragment.f1205c != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray("android:view_state", fragment.f1205c);
        }
        if (!fragment.K) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean("android:user_visible_hint", fragment.K);
        }
        return bundle;
    }

    void r(Fragment fragment) {
        if (fragment.I == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = this.D;
        if (sparseArray == null) {
            this.D = new SparseArray<>();
        } else {
            sparseArray.clear();
        }
        fragment.I.saveHierarchyState(this.D);
        if (this.D.size() > 0) {
            fragment.f1205c = this.D;
            this.D = null;
        }
    }

    void s() {
        if (this.y) {
            this.y = false;
            D();
        }
    }

    public void t(Fragment fragment) {
        if (I) {
            Log.v("FragmentManager", "show: " + fragment);
        }
        if (fragment.y) {
            fragment.y = false;
            fragment.N = !fragment.N;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("FragmentManager{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" in ");
        Fragment fragment = this.s;
        if (fragment != null) {
            androidx.core.e.a.a(fragment, sb);
        } else {
            androidx.core.e.a.a(this.q, sb);
        }
        sb.append("}}");
        return sb.toString();
    }

    LayoutInflater.Factory2 u() {
        return this;
    }

    void w() {
        t();
        if (this.k.b()) {
            g();
        } else {
            this.j.a();
        }
    }

    public boolean x() {
        return this.x;
    }

    public boolean y() {
        return this.v || this.w;
    }

    public void z() {
        this.v = false;
        this.w = false;
        int size = this.f1249f.size();
        for (int i = 0; i < size; i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null) {
                fragment.T();
            }
        }
    }

    void c(Fragment fragment) {
        Animator animator;
        if (fragment.H != null) {
            g gVarA = a(fragment, fragment.v(), !fragment.y, fragment.w());
            if (gVarA == null || (animator = gVarA.f1265b) == null) {
                if (gVarA != null) {
                    fragment.H.startAnimation(gVarA.f1264a);
                    gVarA.f1264a.start();
                }
                fragment.H.setVisibility((!fragment.y || fragment.M()) ? 0 : 8);
                if (fragment.M()) {
                    fragment.g(false);
                }
            } else {
                animator.setTarget(fragment.H);
                if (!fragment.y) {
                    fragment.H.setVisibility(0);
                } else if (fragment.M()) {
                    fragment.g(false);
                } else {
                    ViewGroup viewGroup = fragment.G;
                    View view = fragment.H;
                    viewGroup.startViewTransition(view);
                    gVarA.f1265b.addListener(new e(this, viewGroup, view, fragment));
                }
                gVarA.f1265b.start();
            }
        }
        if (fragment.k && w(fragment)) {
            this.u = true;
        }
        fragment.N = false;
        fragment.a(fragment.y);
    }

    public void d(Fragment fragment) {
        if (I) {
            Log.v("FragmentManager", "detach: " + fragment);
        }
        if (fragment.z) {
            return;
        }
        fragment.z = true;
        if (fragment.k) {
            if (I) {
                Log.v("FragmentManager", "remove from detach: " + fragment);
            }
            synchronized (this.f1249f) {
                this.f1249f.remove(fragment);
            }
            if (w(fragment)) {
                this.u = true;
            }
            fragment.k = false;
        }
    }

    androidx.fragment.app.k f(Fragment fragment) {
        return this.G.c(fragment);
    }

    public void m() {
        for (int i = 0; i < this.f1249f.size(); i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null) {
                fragment.g0();
            }
        }
    }

    @Override // androidx.fragment.app.h
    public boolean b(String str, int i) {
        F();
        return a(str, -1, i);
    }

    void f(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).f(fragment, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.e(this, fragment);
            }
        }
    }

    s g(Fragment fragment) {
        return this.G.d(fragment);
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Animation f1264a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final Animator f1265b;

        g(Animation animation) {
            this.f1264a = animation;
            this.f1265b = null;
            if (animation == null) {
                throw new IllegalStateException("Animation cannot be null");
            }
        }

        g(Animator animator) {
            this.f1264a = null;
            this.f1265b = animator;
            if (animator == null) {
                throw new IllegalStateException("Animator cannot be null");
            }
        }
    }

    void g(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).g(fragment, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.f(this, fragment);
            }
        }
    }

    public void s(Fragment fragment) {
        if (fragment != null && (this.g.get(fragment.f1207e) != fragment || (fragment.s != null && fragment.r() != this))) {
            throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
        }
        Fragment fragment2 = this.t;
        this.t = fragment;
        u(fragment2);
        u(this.t);
    }

    private boolean w(Fragment fragment) {
        return (fragment.C && fragment.D) || fragment.t.h();
    }

    @Override // androidx.fragment.app.h
    public boolean b(int i, int i2) {
        F();
        t();
        if (i >= 0) {
            return a((String) null, i, i2);
        }
        throw new IllegalArgumentException("Bad id: " + i);
    }

    void h(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).h(fragment, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.g(this, fragment);
            }
        }
    }

    public void i() {
        this.v = false;
        this.w = false;
        e(2);
    }

    public boolean t() {
        c(true);
        boolean z = false;
        while (b(this.z, this.A)) {
            this.f1247d = true;
            try {
                c(this.z, this.A);
                G();
                z = true;
            } catch (Throwable th) {
                G();
                throw th;
            }
        }
        J();
        s();
        E();
        return z;
    }

    /* JADX INFO: compiled from: FragmentManagerImpl.java */
    private static class h extends AnimationSet implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final ViewGroup f1266a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final View f1267b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private boolean f1268c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1269d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1270e;

        h(Animation animation, ViewGroup viewGroup, View view) {
            super(false);
            this.f1270e = true;
            this.f1266a = viewGroup;
            this.f1267b = view;
            addAnimation(animation);
            this.f1266a.post(this);
        }

        @Override // android.view.animation.AnimationSet, android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation) {
            this.f1270e = true;
            if (this.f1268c) {
                return !this.f1269d;
            }
            if (!super.getTransformation(j, transformation)) {
                this.f1268c = true;
                androidx.core.f.q.a(this.f1266a, this);
            }
            return true;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f1268c || !this.f1270e) {
                this.f1266a.endViewTransition(this.f1267b);
                this.f1269d = true;
            } else {
                this.f1270e = false;
                this.f1266a.post(this);
            }
        }

        @Override // android.view.animation.Animation
        public boolean getTransformation(long j, Transformation transformation, float f2) {
            this.f1270e = true;
            if (this.f1268c) {
                return !this.f1269d;
            }
            if (!super.getTransformation(j, transformation, f2)) {
                this.f1268c = true;
                androidx.core.f.q.a(this.f1266a, this);
            }
            return true;
        }
    }

    void e(Fragment fragment) {
        if (!fragment.m || fragment.p) {
            return;
        }
        fragment.b(fragment.i(fragment.f1204b), (ViewGroup) null, fragment.f1204b);
        View view = fragment.H;
        if (view != null) {
            fragment.I = view;
            view.setSaveFromParentEnabled(false);
            if (fragment.y) {
                fragment.H.setVisibility(8);
            }
            fragment.a(fragment.H, fragment.f1204b);
            a(fragment, fragment.H, fragment.f1204b, false);
            return;
        }
        fragment.I = null;
    }

    public void n() {
        e(3);
    }

    public Fragment v() {
        return this.t;
    }

    public void p() {
        this.v = false;
        this.w = false;
        e(4);
    }

    public void b(Fragment fragment) {
        if (I) {
            Log.v("FragmentManager", "attach: " + fragment);
        }
        if (fragment.z) {
            fragment.z = false;
            if (fragment.k) {
                return;
            }
            if (!this.f1249f.contains(fragment)) {
                if (I) {
                    Log.v("FragmentManager", "add from attach: " + fragment);
                }
                synchronized (this.f1249f) {
                    this.f1249f.add(fragment);
                }
                fragment.k = true;
                if (w(fragment)) {
                    this.u = true;
                    return;
                }
                return;
            }
            throw new IllegalStateException("Fragment already added: " + fragment);
        }
    }

    public void j() {
        this.v = false;
        this.w = false;
        e(1);
    }

    public void r() {
        this.w = true;
        e(2);
    }

    @Override // androidx.fragment.app.h
    public androidx.fragment.app.m a() {
        return new androidx.fragment.app.a(this);
    }

    @Override // androidx.fragment.app.h
    public void a(String str, int i) {
        a((k) new l(str, -1, i), false);
    }

    public void k() {
        this.x = true;
        t();
        e(0);
        this.q = null;
        this.r = null;
        this.s = null;
        if (this.j != null) {
            this.k.c();
            this.j = null;
        }
    }

    void o() {
        J();
        u(this.t);
    }

    @Override // androidx.fragment.app.h
    public void a(int i, int i2) {
        if (i >= 0) {
            a((k) new l(null, i, i2), false);
            return;
        }
        throw new IllegalArgumentException("Bad id: " + i);
    }

    boolean h() {
        boolean zW = false;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null) {
                zW = w(fragment);
            }
            if (zW) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.h
    public androidx.fragment.app.f d() {
        if (super.d() == androidx.fragment.app.h.f1244b) {
            Fragment fragment = this.s;
            if (fragment != null) {
                return fragment.r.d();
            }
            a(new f());
        }
        return super.d();
    }

    private boolean a(String str, int i, int i2) {
        t();
        c(true);
        Fragment fragment = this.t;
        if (fragment != null && i < 0 && str == null && fragment.l().g()) {
            return true;
        }
        boolean zA = a(this.z, this.A, str, i, i2);
        if (zA) {
            this.f1247d = true;
            try {
                c(this.z, this.A);
                G();
            } catch (Throwable th) {
                G();
                throw th;
            }
        }
        J();
        s();
        E();
        return zA;
    }

    private void e(int i) {
        try {
            this.f1247d = true;
            a(i, false);
            this.f1247d = false;
            t();
        } catch (Throwable th) {
            this.f1247d = false;
            throw th;
        }
    }

    public void q() {
        this.v = false;
        this.w = false;
        e(3);
    }

    void d(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).d(fragment, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.c(this, fragment);
            }
        }
    }

    void e(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).e(fragment, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.d(this, fragment);
            }
        }
    }

    public Fragment b(int i) {
        for (int size = this.f1249f.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1249f.get(size);
            if (fragment != null && fragment.v == i) {
                return fragment;
            }
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && fragment2.v == i) {
                return fragment2;
            }
        }
        return null;
    }

    public void c(int i) {
        synchronized (this) {
            this.l.set(i, null);
            if (this.m == null) {
                this.m = new ArrayList<>();
            }
            if (I) {
                Log.v("FragmentManager", "Freeing back stack index " + i);
            }
            this.m.add(Integer.valueOf(i));
        }
    }

    @Override // androidx.fragment.app.h
    public androidx.fragment.app.h.a a(int i) {
        return this.h.get(i);
    }

    public Fragment b(String str) {
        Fragment fragmentA;
        for (Fragment fragment : this.g.values()) {
            if (fragment != null && (fragmentA = fragment.a(str)) != null) {
                return fragmentA;
            }
        }
        return null;
    }

    void d(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).d(fragment, bundle, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.d(this, fragment, bundle);
            }
        }
    }

    public void a(Bundle bundle, String str, Fragment fragment) {
        if (fragment.r == this) {
            bundle.putString(str, fragment.f1207e);
            return;
        }
        a(new IllegalStateException("Fragment " + fragment + " is not currently in the FragmentManager"));
        throw null;
    }

    public int b(androidx.fragment.app.a aVar) {
        synchronized (this) {
            if (this.m != null && this.m.size() > 0) {
                int iIntValue = this.m.remove(this.m.size() - 1).intValue();
                if (I) {
                    Log.v("FragmentManager", "Adding back stack index " + iIntValue + " with " + aVar);
                }
                this.l.set(iIntValue, aVar);
                return iIntValue;
            }
            if (this.l == null) {
                this.l = new ArrayList<>();
            }
            int size = this.l.size();
            if (I) {
                Log.v("FragmentManager", "Setting back stack index " + size + " to " + aVar);
            }
            this.l.add(aVar);
            return size;
        }
    }

    public Fragment a(Bundle bundle, String str) {
        String string = bundle.getString(str);
        if (string == null) {
            return null;
        }
        Fragment fragment = this.g.get(string);
        if (fragment != null) {
            return fragment;
        }
        a(new IllegalStateException("Fragment no longer exists for key " + str + ": unique id " + string));
        throw null;
    }

    public void l() {
        e(1);
    }

    private void c(boolean z) {
        if (!this.f1247d) {
            if (this.q != null) {
                if (Looper.myLooper() == this.q.g().getLooper()) {
                    if (!z) {
                        F();
                    }
                    if (this.z == null) {
                        this.z = new ArrayList<>();
                        this.A = new ArrayList<>();
                    }
                    this.f1247d = true;
                    try {
                        a((ArrayList<androidx.fragment.app.a>) null, (ArrayList<Boolean>) null);
                        return;
                    } finally {
                        this.f1247d = false;
                    }
                }
                throw new IllegalStateException("Must be called from main thread of fragment host");
            }
            throw new IllegalStateException("Fragment host has been destroyed");
        }
        throw new IllegalStateException("FragmentManager is already executing transactions");
    }

    void a(Fragment fragment) {
        if (y()) {
            if (I) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else if (this.G.a(fragment) && I) {
            Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
        }
    }

    @Override // androidx.fragment.app.h
    public void a(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        int size;
        int size2;
        int size3;
        int size4;
        String str2 = str + "    ";
        if (!this.g.isEmpty()) {
            printWriter.print(str);
            printWriter.print("Active Fragments in ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(Config.TRACE_TODAY_VISIT_SPLIT);
            for (Fragment fragment : this.g.values()) {
                printWriter.print(str);
                printWriter.println(fragment);
                if (fragment != null) {
                    fragment.a(str2, fileDescriptor, printWriter, strArr);
                }
            }
        }
        int size5 = this.f1249f.size();
        if (size5 > 0) {
            printWriter.print(str);
            printWriter.println("Added Fragments:");
            for (int i = 0; i < size5; i++) {
                Fragment fragment2 = this.f1249f.get(i);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i);
                printWriter.print(": ");
                printWriter.println(fragment2.toString());
            }
        }
        ArrayList<Fragment> arrayList = this.i;
        if (arrayList != null && (size4 = arrayList.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Fragments Created Menus:");
            for (int i2 = 0; i2 < size4; i2++) {
                Fragment fragment3 = this.i.get(i2);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i2);
                printWriter.print(": ");
                printWriter.println(fragment3.toString());
            }
        }
        ArrayList<androidx.fragment.app.a> arrayList2 = this.h;
        if (arrayList2 != null && (size3 = arrayList2.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Back Stack:");
            for (int i3 = 0; i3 < size3; i3++) {
                androidx.fragment.app.a aVar = this.h.get(i3);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i3);
                printWriter.print(": ");
                printWriter.println(aVar.toString());
                aVar.a(str2, printWriter);
            }
        }
        synchronized (this) {
            if (this.l != null && (size2 = this.l.size()) > 0) {
                printWriter.print(str);
                printWriter.println("Back Stack Indices:");
                for (int i4 = 0; i4 < size2; i4++) {
                    Object obj = (androidx.fragment.app.a) this.l.get(i4);
                    printWriter.print(str);
                    printWriter.print("  #");
                    printWriter.print(i4);
                    printWriter.print(": ");
                    printWriter.println(obj);
                }
            }
            if (this.m != null && this.m.size() > 0) {
                printWriter.print(str);
                printWriter.print("mAvailBackStackIndices: ");
                printWriter.println(Arrays.toString(this.m.toArray()));
            }
        }
        ArrayList<k> arrayList3 = this.f1246c;
        if (arrayList3 != null && (size = arrayList3.size()) > 0) {
            printWriter.print(str);
            printWriter.println("Pending Actions:");
            for (int i5 = 0; i5 < size; i5++) {
                Object obj2 = (k) this.f1246c.get(i5);
                printWriter.print(str);
                printWriter.print("  #");
                printWriter.print(i5);
                printWriter.print(": ");
                printWriter.println(obj2);
            }
        }
        printWriter.print(str);
        printWriter.println("FragmentManager misc state:");
        printWriter.print(str);
        printWriter.print("  mHost=");
        printWriter.println(this.q);
        printWriter.print(str);
        printWriter.print("  mContainer=");
        printWriter.println(this.r);
        if (this.s != null) {
            printWriter.print(str);
            printWriter.print("  mParent=");
            printWriter.println(this.s);
        }
        printWriter.print(str);
        printWriter.print("  mCurState=");
        printWriter.print(this.p);
        printWriter.print(" mStateSaved=");
        printWriter.print(this.v);
        printWriter.print(" mStopped=");
        printWriter.print(this.w);
        printWriter.print(" mDestroyed=");
        printWriter.println(this.x);
        if (this.u) {
            printWriter.print(str);
            printWriter.print("  mNeedMenuInvalidate=");
            printWriter.println(this.u);
        }
    }

    private void b(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        int i3;
        int i4;
        int i5 = i;
        boolean z = arrayList.get(i5).q;
        ArrayList<Fragment> arrayList3 = this.B;
        if (arrayList3 == null) {
            this.B = new ArrayList<>();
        } else {
            arrayList3.clear();
        }
        this.B.addAll(this.f1249f);
        Fragment fragmentV = v();
        boolean z2 = false;
        for (int i6 = i5; i6 < i2; i6++) {
            androidx.fragment.app.a aVar = arrayList.get(i6);
            if (!arrayList2.get(i6).booleanValue()) {
                fragmentV = aVar.a(this.B, fragmentV);
            } else {
                fragmentV = aVar.b(this.B, fragmentV);
            }
            z2 = z2 || aVar.h;
        }
        this.B.clear();
        if (!z) {
            n.a(this, arrayList, arrayList2, i, i2, false);
        }
        a(arrayList, arrayList2, i, i2);
        if (z) {
            a.b.b<Fragment> bVar = new a.b.b<>();
            a(bVar);
            int iA = a(arrayList, arrayList2, i, i2, bVar);
            b(bVar);
            i3 = iA;
        } else {
            i3 = i2;
        }
        if (i3 != i5 && z) {
            n.a(this, arrayList, arrayList2, i, i3, true);
            a(this.p, true);
        }
        while (i5 < i2) {
            androidx.fragment.app.a aVar2 = arrayList.get(i5);
            if (arrayList2.get(i5).booleanValue() && (i4 = aVar2.u) >= 0) {
                c(i4);
                aVar2.u = -1;
            }
            aVar2.g();
            i5++;
        }
        if (z2) {
            A();
        }
    }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }

    private void c(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        if (arrayList2 != null && arrayList.size() == arrayList2.size()) {
            a(arrayList, arrayList2);
            int size = arrayList.size();
            int i = 0;
            int i2 = 0;
            while (i < size) {
                if (!arrayList.get(i).q) {
                    if (i2 != i) {
                        b(arrayList, arrayList2, i2, i);
                    }
                    i2 = i + 1;
                    if (arrayList2.get(i).booleanValue()) {
                        while (i2 < size && arrayList2.get(i2).booleanValue() && !arrayList.get(i2).q) {
                            i2++;
                        }
                    }
                    b(arrayList, arrayList2, i, i2);
                    i = i2 - 1;
                }
                i++;
            }
            if (i2 != size) {
                b(arrayList, arrayList2, i2, size);
                return;
            }
            return;
        }
        throw new IllegalStateException("Internal error with the back stack records");
    }

    void c(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).c(fragment, bundle, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.c(this, fragment, bundle);
            }
        }
    }

    void c(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).c(fragment, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.b(this, fragment);
            }
        }
    }

    private void b(a.b.b<Fragment> bVar) {
        int size = bVar.size();
        for (int i = 0; i < size; i++) {
            Fragment fragmentC = bVar.c(i);
            if (!fragmentC.k) {
                View viewO0 = fragmentC.o0();
                fragmentC.O = viewO0.getAlpha();
                viewO0.setAlpha(0.0f);
            }
        }
    }

    private boolean b(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        synchronized (this) {
            if (this.f1246c != null && this.f1246c.size() != 0) {
                int size = this.f1246c.size();
                boolean zA = false;
                for (int i = 0; i < size; i++) {
                    zA |= this.f1246c.get(i).a(arrayList, arrayList2);
                }
                this.f1246c.clear();
                this.q.g().removeCallbacks(this.H);
                return zA;
            }
            return false;
        }
    }

    public void b(boolean z) {
        for (int size = this.f1249f.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1249f.get(size);
            if (fragment != null) {
                fragment.f(z);
            }
        }
    }

    public boolean b(Menu menu) {
        if (this.p < 1) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < this.f1249f.size(); i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null && fragment.d(menu)) {
                z = true;
            }
        }
        return z;
    }

    public boolean b(MenuItem menuItem) {
        if (this.p < 1) {
            return false;
        }
        for (int i = 0; i < this.f1249f.size(); i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null && fragment.d(menuItem)) {
                return true;
            }
        }
        return false;
    }

    void b(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).b(fragment, context, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.b(this, fragment, context);
            }
        }
    }

    static g a(float f2, float f3, float f4, float f5) {
        AnimationSet animationSet = new AnimationSet(false);
        ScaleAnimation scaleAnimation = new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(J);
        scaleAnimation.setDuration(220L);
        animationSet.addAnimation(scaleAnimation);
        AlphaAnimation alphaAnimation = new AlphaAnimation(f4, f5);
        alphaAnimation.setInterpolator(K);
        alphaAnimation.setDuration(220L);
        animationSet.addAnimation(alphaAnimation);
        return new g(animationSet);
    }

    void b(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).b(fragment, bundle, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.b(this, fragment, bundle);
            }
        }
    }

    static g a(float f2, float f3) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(f2, f3);
        alphaAnimation.setInterpolator(K);
        alphaAnimation.setDuration(220L);
        return new g(alphaAnimation);
    }

    void b(Fragment fragment, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).b(fragment, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.a(this, fragment);
            }
        }
    }

    g a(Fragment fragment, int i, boolean z, int i2) {
        int iB;
        int iU = fragment.u();
        boolean z2 = false;
        fragment.a(0);
        ViewGroup viewGroup = fragment.G;
        if (viewGroup != null && viewGroup.getLayoutTransition() != null) {
            return null;
        }
        Animation animationA = fragment.a(i, z, iU);
        if (animationA != null) {
            return new g(animationA);
        }
        Animator animatorB = fragment.b(i, z, iU);
        if (animatorB != null) {
            return new g(animatorB);
        }
        if (iU != 0) {
            boolean zEquals = "anim".equals(this.q.f().getResources().getResourceTypeName(iU));
            if (zEquals) {
                try {
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(this.q.f(), iU);
                    if (animationLoadAnimation != null) {
                        return new g(animationLoadAnimation);
                    }
                    z2 = true;
                } catch (Resources.NotFoundException e2) {
                    throw e2;
                } catch (RuntimeException unused) {
                }
            }
            if (!z2) {
                try {
                    Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(this.q.f(), iU);
                    if (animatorLoadAnimator != null) {
                        return new g(animatorLoadAnimator);
                    }
                } catch (RuntimeException e3) {
                    if (!zEquals) {
                        Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(this.q.f(), iU);
                        if (animationLoadAnimation2 != null) {
                            return new g(animationLoadAnimation2);
                        }
                    } else {
                        throw e3;
                    }
                }
            }
        }
        if (i == 0 || (iB = b(i, z)) < 0) {
            return null;
        }
        switch (iB) {
            case 1:
                return a(1.125f, 1.0f, 0.0f, 1.0f);
            case 2:
                return a(1.0f, 0.975f, 1.0f, 0.0f);
            case 3:
                return a(0.975f, 1.0f, 0.0f, 1.0f);
            case 4:
                return a(1.0f, 1.075f, 1.0f, 0.0f);
            case 5:
                return a(0.0f, 1.0f);
            case 6:
                return a(1.0f, 0.0f);
            default:
                if (i2 == 0 && this.q.k()) {
                    i2 = this.q.j();
                }
                if (i2 == 0) {
                }
                return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:151:0x02df  */
    /* JADX WARN: Code duplicated, block: B:153:0x02e3  */
    /* JADX WARN: Code duplicated, block: B:156:0x0301  */
    /* JADX WARN: Code duplicated, block: B:158:0x0305  */
    /* JADX WARN: Code duplicated, block: B:216:0x0407  */
    /* JADX WARN: Code duplicated, block: B:218:0x040b  */
    /* JADX WARN: Code duplicated, block: B:220:0x0411  */
    /* JADX WARN: Code duplicated, block: B:221:0x041c  */
    /* JADX WARN: Code duplicated, block: B:223:0x0422  */
    /* JADX WARN: Code duplicated, block: B:271:0x04db  */
    /* JADX WARN: Code duplicated, block: B:275:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:153:0x02e3, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:158:0x0305, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:271:0x04db, please report this as an issue */
    void a(Fragment fragment, int i, int i2, int i3, boolean z) {
        int i4;
        int iMin;
        Fragment fragment2;
        ViewGroup viewGroup;
        ViewGroup viewGroup2;
        String resourceName;
        int i5;
        int i6 = 1;
        boolean zIsChangingConfigurations = true;
        if (!fragment.k || fragment.z) {
            i4 = i;
            if (i4 > 1) {
                i4 = 1;
            }
        } else {
            i4 = i;
        }
        if (fragment.l && i4 > (i5 = fragment.f1203a)) {
            i4 = (i5 == 0 && fragment.N()) ? 1 : fragment.f1203a;
        }
        if (fragment.J && fragment.f1203a < 3 && i4 > 2) {
            i4 = 2;
        }
        androidx.lifecycle.e.b bVar = fragment.R;
        if (bVar == androidx.lifecycle.e.b.CREATED) {
            iMin = Math.min(i4, 1);
        } else {
            iMin = Math.min(i4, bVar.ordinal());
        }
        int i7 = iMin;
        int i8 = fragment.f1203a;
        if (i8 <= i7) {
            if (fragment.m && !fragment.n) {
                return;
            }
            if (fragment.i() != null || fragment.j() != null) {
                fragment.a((View) null);
                fragment.a((Animator) null);
                a(fragment, fragment.E(), 0, 0, true);
            }
            int i9 = fragment.f1203a;
            if (i9 != 0) {
                if (i9 != 1) {
                    if (i9 != 2) {
                        if (i9 == 3) {
                        }
                    }
                    if (i7 > 3) {
                        if (I) {
                            Log.v("FragmentManager", "moveto RESUMED: " + fragment);
                        }
                        fragment.j0();
                        e(fragment, false);
                        fragment.f1204b = null;
                        fragment.f1205c = null;
                    }
                }
                if (i7 > 2) {
                    if (I) {
                        Log.v("FragmentManager", "moveto STARTED: " + fragment);
                    }
                    fragment.k0();
                    f(fragment, false);
                }
                if (i7 > 3) {
                    if (I) {
                        Log.v("FragmentManager", "moveto RESUMED: " + fragment);
                    }
                    fragment.j0();
                    e(fragment, false);
                    fragment.f1204b = null;
                    fragment.f1205c = null;
                }
            } else if (i7 > 0) {
                if (I) {
                    Log.v("FragmentManager", "moveto CREATED: " + fragment);
                }
                Bundle bundle = fragment.f1204b;
                if (bundle != null) {
                    bundle.setClassLoader(this.q.f().getClassLoader());
                    fragment.f1205c = fragment.f1204b.getSparseParcelableArray("android:view_state");
                    Fragment fragmentA = a(fragment.f1204b, "android:target_state");
                    fragment.h = fragmentA != null ? fragmentA.f1207e : null;
                    if (fragment.h != null) {
                        fragment.i = fragment.f1204b.getInt("android:target_req_state", 0);
                    }
                    Boolean bool = fragment.f1206d;
                    if (bool != null) {
                        fragment.K = bool.booleanValue();
                        fragment.f1206d = null;
                    } else {
                        fragment.K = fragment.f1204b.getBoolean("android:user_visible_hint", true);
                    }
                    if (!fragment.K) {
                        fragment.J = true;
                        if (i7 > 2) {
                            i7 = 2;
                        }
                    }
                }
                androidx.fragment.app.g gVar = this.q;
                fragment.s = gVar;
                Fragment fragment3 = this.s;
                fragment.u = fragment3;
                fragment.r = fragment3 != null ? fragment3.t : gVar.f1243e;
                Fragment fragment4 = fragment.g;
                String str = " declared target fragment ";
                String str2 = "Fragment ";
                if (fragment4 != null) {
                    Fragment fragment5 = this.g.get(fragment4.f1207e);
                    Fragment fragment6 = fragment.g;
                    if (fragment5 == fragment6) {
                        if (fragment6.f1203a < 1) {
                            a(fragment6, 1, 0, 0, true);
                        }
                        fragment.h = fragment.g.f1207e;
                        fragment.g = null;
                    } else {
                        throw new IllegalStateException("Fragment " + fragment + " declared target fragment " + fragment.g + " that does not belong to this FragmentManager!");
                    }
                } else {
                    str2 = "Fragment ";
                    str = " declared target fragment ";
                }
                String str3 = fragment.h;
                if (str3 != null) {
                    Fragment fragment7 = this.g.get(str3);
                    if (fragment7 != null) {
                        if (fragment7.f1203a < 1) {
                            a(fragment7, 1, 0, 0, true);
                        }
                    } else {
                        throw new IllegalStateException(str2 + fragment + str + fragment.h + " that does not belong to this FragmentManager!");
                    }
                }
                b(fragment, this.q.f(), false);
                fragment.c0();
                Fragment fragment8 = fragment.u;
                if (fragment8 == null) {
                    this.q.a(fragment);
                } else {
                    fragment8.a(fragment);
                }
                a(fragment, this.q.f(), false);
                if (!fragment.Q) {
                    c(fragment, fragment.f1204b, false);
                    fragment.h(fragment.f1204b);
                    b(fragment, fragment.f1204b, false);
                } else {
                    fragment.k(fragment.f1204b);
                    fragment.f1203a = 1;
                }
            }
            if (i7 > 0) {
                e(fragment);
            }
            if (i7 > 1) {
                if (I) {
                    Log.v("FragmentManager", "moveto ACTIVITY_CREATED: " + fragment);
                }
                if (!fragment.m) {
                    int i10 = fragment.w;
                    if (i10 == 0) {
                        viewGroup2 = null;
                    } else if (i10 != -1) {
                        viewGroup2 = (ViewGroup) this.r.a(i10);
                        if (viewGroup2 == null && !fragment.o) {
                            try {
                                resourceName = fragment.z().getResourceName(fragment.w);
                            } catch (Resources.NotFoundException unused) {
                                resourceName = "unknown";
                            }
                            a(new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(fragment.w) + " (" + resourceName + ") for fragment " + fragment));
                            throw null;
                        }
                    } else {
                        a(new IllegalArgumentException("Cannot create fragment " + fragment + " for a container view with no id"));
                        throw null;
                    }
                    fragment.G = viewGroup2;
                    fragment.b(fragment.i(fragment.f1204b), viewGroup2, fragment.f1204b);
                    View view = fragment.H;
                    if (view != null) {
                        fragment.I = view;
                        view.setSaveFromParentEnabled(false);
                        if (viewGroup2 != null) {
                            viewGroup2.addView(fragment.H);
                        }
                        if (fragment.y) {
                            fragment.H.setVisibility(8);
                        }
                        fragment.a(fragment.H, fragment.f1204b);
                        a(fragment, fragment.H, fragment.f1204b, false);
                        fragment.M = fragment.H.getVisibility() == 0 && fragment.G != null;
                    } else {
                        fragment.I = null;
                    }
                }
                fragment.g(fragment.f1204b);
                a(fragment, fragment.f1204b, false);
                if (fragment.H != null) {
                    fragment.l(fragment.f1204b);
                }
                fragment.f1204b = null;
            }
            if (i7 > 2) {
                if (I) {
                    Log.v("FragmentManager", "moveto STARTED: " + fragment);
                }
                fragment.k0();
                f(fragment, false);
            }
            if (i7 > 3) {
                if (I) {
                    Log.v("FragmentManager", "moveto RESUMED: " + fragment);
                }
                fragment.j0();
                e(fragment, false);
                fragment.f1204b = null;
                fragment.f1205c = null;
            }
        } else {
            if (i8 > i7) {
                if (i8 != 1) {
                    if (i8 != 2) {
                        if (i8 != 3) {
                            if (i8 == 4) {
                                if (i7 < 4) {
                                    if (I) {
                                        Log.v("FragmentManager", "movefrom RESUMED: " + fragment);
                                    }
                                    fragment.h0();
                                    d(fragment, false);
                                }
                            }
                        }
                        if (i7 < 3) {
                            if (I) {
                                Log.v("FragmentManager", "movefrom STARTED: " + fragment);
                            }
                            fragment.l0();
                            g(fragment, false);
                        }
                    }
                    if (i7 < 2) {
                        if (I) {
                            Log.v("FragmentManager", "movefrom ACTIVITY_CREATED: " + fragment);
                        }
                        if (fragment.H != null && this.q.b(fragment) && fragment.f1205c == null) {
                            r(fragment);
                        }
                        fragment.e0();
                        h(fragment, false);
                        View view2 = fragment.H;
                        if (view2 != null && (viewGroup = fragment.G) != null) {
                            viewGroup.endViewTransition(view2);
                            fragment.H.clearAnimation();
                            if (fragment.x() == null || !fragment.x().l) {
                                g gVarA = (this.p <= 0 || this.x || fragment.H.getVisibility() != 0 || fragment.O < 0.0f) ? null : a(fragment, i2, false, i3);
                                fragment.O = 0.0f;
                                if (gVarA != null) {
                                    a(fragment, gVarA, i7);
                                }
                                fragment.G.removeView(fragment.H);
                            }
                        }
                        fragment.G = null;
                        fragment.H = null;
                        fragment.T = null;
                        fragment.U.a((androidx.lifecycle.h) null);
                        fragment.I = null;
                        fragment.n = false;
                    }
                    if (i7 >= 1) {
                        if (this.x) {
                            if (fragment.i() != null) {
                                View viewI = fragment.i();
                                fragment.a((View) null);
                                viewI.clearAnimation();
                            } else if (fragment.j() != null) {
                                Animator animatorJ = fragment.j();
                                fragment.a((Animator) null);
                                animatorJ.cancel();
                            }
                        }
                        if (fragment.i() != null) {
                        }
                        fragment.b(i7);
                    }
                } else if (i7 >= 1) {
                    if (this.x) {
                        if (fragment.i() != null) {
                            View viewI2 = fragment.i();
                            fragment.a((View) null);
                            viewI2.clearAnimation();
                        } else if (fragment.j() != null) {
                            Animator animatorJ2 = fragment.j();
                            fragment.a((Animator) null);
                            animatorJ2.cancel();
                        }
                    }
                    if (fragment.i() != null && fragment.j() == null) {
                        if (I) {
                            Log.v("FragmentManager", "movefrom CREATED: " + fragment);
                        }
                        boolean z2 = fragment.l && !fragment.N();
                        if (!z2 && !this.G.f(fragment)) {
                            fragment.f1203a = 0;
                        } else {
                            androidx.fragment.app.g gVar2 = this.q;
                            if (gVar2 instanceof t) {
                                zIsChangingConfigurations = this.G.d();
                            } else if (gVar2.f() instanceof Activity) {
                                zIsChangingConfigurations = true ^ ((Activity) this.q.f()).isChangingConfigurations();
                            }
                            if (z2 || zIsChangingConfigurations) {
                                this.G.b(fragment);
                            }
                            fragment.d0();
                            b(fragment, false);
                        }
                        fragment.f0();
                        c(fragment, false);
                        if (!z) {
                            if (!z2 && !this.G.f(fragment)) {
                                fragment.s = null;
                                fragment.u = null;
                                fragment.r = null;
                                String str4 = fragment.h;
                                if (str4 != null && (fragment2 = this.g.get(str4)) != null && fragment2.A()) {
                                    fragment.g = fragment2;
                                }
                            } else {
                                k(fragment);
                            }
                        }
                    } else {
                        fragment.b(i7);
                    }
                }
            }
            if (fragment.f1203a != i6) {
                Log.w("FragmentManager", "moveToState: Fragment state for " + fragment + " not updated inline; expected state " + i6 + " found " + fragment.f1203a);
                fragment.f1203a = i6;
            }
        }
        i6 = i7;
        if (fragment.f1203a != i6) {
            Log.w("FragmentManager", "moveToState: Fragment state for " + fragment + " not updated inline; expected state " + i6 + " found " + fragment.f1203a);
            fragment.f1203a = i6;
        }
    }

    private void a(Fragment fragment, g gVar, int i) {
        View view = fragment.H;
        ViewGroup viewGroup = fragment.G;
        viewGroup.startViewTransition(view);
        fragment.b(i);
        Animation animation = gVar.f1264a;
        if (animation != null) {
            h hVar = new h(animation, viewGroup, view);
            fragment.a(fragment.H);
            hVar.setAnimationListener(new c(viewGroup, fragment));
            fragment.H.startAnimation(hVar);
            return;
        }
        Animator animator = gVar.f1265b;
        fragment.a(animator);
        animator.addListener(new d(viewGroup, view, fragment));
        animator.setTarget(fragment.H);
        animator.start();
    }

    void a(int i, boolean z) {
        androidx.fragment.app.g gVar;
        if (this.q == null && i != 0) {
            throw new IllegalStateException("No activity");
        }
        if (z || i != this.p) {
            this.p = i;
            int size = this.f1249f.size();
            for (int i2 = 0; i2 < size; i2++) {
                l(this.f1249f.get(i2));
            }
            for (Fragment fragment : this.g.values()) {
                if (fragment != null && (fragment.l || fragment.z)) {
                    if (!fragment.M) {
                        l(fragment);
                    }
                }
            }
            D();
            if (this.u && (gVar = this.q) != null && this.p == 4) {
                gVar.l();
                this.u = false;
            }
        }
    }

    public void a(Fragment fragment, boolean z) {
        if (I) {
            Log.v("FragmentManager", "add: " + fragment);
        }
        j(fragment);
        if (fragment.z) {
            return;
        }
        if (!this.f1249f.contains(fragment)) {
            synchronized (this.f1249f) {
                this.f1249f.add(fragment);
            }
            fragment.k = true;
            fragment.l = false;
            if (fragment.H == null) {
                fragment.N = false;
            }
            if (w(fragment)) {
                this.u = true;
            }
            if (z) {
                m(fragment);
                return;
            }
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fragment);
    }

    @Override // androidx.fragment.app.h
    public Fragment a(String str) {
        if (str != null) {
            for (int size = this.f1249f.size() - 1; size >= 0; size--) {
                Fragment fragment = this.f1249f.get(size);
                if (fragment != null && str.equals(fragment.x)) {
                    return fragment;
                }
            }
        }
        if (str == null) {
            return null;
        }
        for (Fragment fragment2 : this.g.values()) {
            if (fragment2 != null && str.equals(fragment2.x)) {
                return fragment2;
            }
        }
        return null;
    }

    public void a(k kVar, boolean z) {
        if (!z) {
            F();
        }
        synchronized (this) {
            if (!this.x && this.q != null) {
                if (this.f1246c == null) {
                    this.f1246c = new ArrayList<>();
                }
                this.f1246c.add(kVar);
                C();
                return;
            }
            if (!z) {
                throw new IllegalStateException("Activity has been destroyed");
            }
        }
    }

    public void a(int i, androidx.fragment.app.a aVar) {
        synchronized (this) {
            if (this.l == null) {
                this.l = new ArrayList<>();
            }
            int size = this.l.size();
            if (i < size) {
                if (I) {
                    Log.v("FragmentManager", "Setting back stack index " + i + " to " + aVar);
                }
                this.l.set(i, aVar);
            } else {
                while (size < i) {
                    this.l.add(null);
                    if (this.m == null) {
                        this.m = new ArrayList<>();
                    }
                    if (I) {
                        Log.v("FragmentManager", "Adding available back stack index " + size);
                    }
                    this.m.add(Integer.valueOf(size));
                    size++;
                }
                if (I) {
                    Log.v("FragmentManager", "Adding back stack index " + i + " with " + aVar);
                }
                this.l.add(aVar);
            }
        }
    }

    private void a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2) {
        int iIndexOf;
        int iIndexOf2;
        ArrayList<m> arrayList3 = this.F;
        int size = arrayList3 == null ? 0 : arrayList3.size();
        int i = 0;
        while (i < size) {
            m mVar = this.F.get(i);
            if (arrayList != null && !mVar.f1278a && (iIndexOf2 = arrayList.indexOf(mVar.f1279b)) != -1 && arrayList2.get(iIndexOf2).booleanValue()) {
                this.F.remove(i);
                i--;
                size--;
                mVar.c();
            } else if (mVar.e() || (arrayList != null && mVar.f1279b.a(arrayList, 0, arrayList.size()))) {
                this.F.remove(i);
                i--;
                size--;
                if (arrayList != null && !mVar.f1278a && (iIndexOf = arrayList.indexOf(mVar.f1279b)) != -1 && arrayList2.get(iIndexOf).booleanValue()) {
                    mVar.c();
                } else {
                    mVar.d();
                }
            }
            i++;
        }
    }

    private int a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2, a.b.b<Fragment> bVar) {
        int i3 = i2;
        for (int i4 = i2 - 1; i4 >= i; i4--) {
            androidx.fragment.app.a aVar = arrayList.get(i4);
            boolean zBooleanValue = arrayList2.get(i4).booleanValue();
            if (aVar.f() && !aVar.a(arrayList, i4 + 1, i2)) {
                if (this.F == null) {
                    this.F = new ArrayList<>();
                }
                m mVar = new m(aVar, zBooleanValue);
                this.F.add(mVar);
                aVar.a(mVar);
                if (zBooleanValue) {
                    aVar.d();
                } else {
                    aVar.b(false);
                }
                i3--;
                if (i4 != i3) {
                    arrayList.remove(i4);
                    arrayList.add(i3, aVar);
                }
                a(bVar);
            }
        }
        return i3;
    }

    void a(androidx.fragment.app.a aVar, boolean z, boolean z2, boolean z3) {
        if (z) {
            aVar.b(z3);
        } else {
            aVar.d();
        }
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        arrayList.add(aVar);
        arrayList2.add(Boolean.valueOf(z));
        if (z2) {
            n.a(this, (ArrayList<androidx.fragment.app.a>) arrayList, (ArrayList<Boolean>) arrayList2, 0, 1, true);
        }
        if (z3) {
            a(this.p, true);
        }
        for (Fragment fragment : this.g.values()) {
            if (fragment != null && fragment.H != null && fragment.M && aVar.b(fragment.w)) {
                float f2 = fragment.O;
                if (f2 > 0.0f) {
                    fragment.H.setAlpha(f2);
                }
                if (z3) {
                    fragment.O = 0.0f;
                } else {
                    fragment.O = -1.0f;
                    fragment.M = false;
                }
            }
        }
    }

    private static void a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, int i, int i2) {
        while (i < i2) {
            androidx.fragment.app.a aVar = arrayList.get(i);
            if (arrayList2.get(i).booleanValue()) {
                aVar.a(-1);
                aVar.b(i == i2 + (-1));
            } else {
                aVar.a(1);
                aVar.d();
            }
            i++;
        }
    }

    private void a(a.b.b<Fragment> bVar) {
        int i = this.p;
        if (i < 1) {
            return;
        }
        int iMin = Math.min(i, 3);
        int size = this.f1249f.size();
        for (int i2 = 0; i2 < size; i2++) {
            Fragment fragment = this.f1249f.get(i2);
            if (fragment.f1203a < iMin) {
                a(fragment, iMin, fragment.u(), fragment.v(), false);
                if (fragment.H != null && !fragment.y && fragment.M) {
                    bVar.add(fragment);
                }
            }
        }
    }

    void a(androidx.fragment.app.a aVar) {
        if (this.h == null) {
            this.h = new ArrayList<>();
        }
        this.h.add(aVar);
    }

    boolean a(ArrayList<androidx.fragment.app.a> arrayList, ArrayList<Boolean> arrayList2, String str, int i, int i2) {
        int size;
        ArrayList<androidx.fragment.app.a> arrayList3 = this.h;
        if (arrayList3 == null) {
            return false;
        }
        if (str == null && i < 0 && (i2 & 1) == 0) {
            int size2 = arrayList3.size() - 1;
            if (size2 < 0) {
                return false;
            }
            arrayList.add(this.h.remove(size2));
            arrayList2.add(true);
        } else {
            if (str != null || i >= 0) {
                size = this.h.size() - 1;
                while (size >= 0) {
                    androidx.fragment.app.a aVar = this.h.get(size);
                    if ((str != null && str.equals(aVar.e())) || (i >= 0 && i == aVar.u)) {
                        break;
                    }
                    size--;
                }
                if (size < 0) {
                    return false;
                }
                if ((i2 & 1) != 0) {
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        androidx.fragment.app.a aVar2 = this.h.get(size);
                        if (str == null || !str.equals(aVar2.e())) {
                            if (i < 0 || i != aVar2.u) {
                                break;
                            }
                        }
                    }
                }
            } else {
                size = -1;
            }
            if (size == this.h.size() - 1) {
                return false;
            }
            for (int size3 = this.h.size() - 1; size3 > size; size3--) {
                arrayList.add(this.h.remove(size3));
                arrayList2.add(true);
            }
        }
        return true;
    }

    void a(Parcelable parcelable) {
        androidx.fragment.app.l next;
        if (parcelable == null) {
            return;
        }
        androidx.fragment.app.j jVar = (androidx.fragment.app.j) parcelable;
        if (jVar.f1281a == null) {
            return;
        }
        for (Fragment fragment : this.G.c()) {
            if (I) {
                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment);
            }
            Iterator<androidx.fragment.app.l> it = jVar.f1281a.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!next.f1292b.equals(fragment.f1207e));
            if (next == null) {
                if (I) {
                    Log.v("FragmentManager", "Discarding retained Fragment " + fragment + " that was not found in the set of active Fragments " + jVar.f1281a);
                }
                a(fragment, 1, 0, 0, false);
                fragment.l = true;
                a(fragment, 0, 0, 0, false);
            } else {
                next.n = fragment;
                fragment.f1205c = null;
                fragment.q = 0;
                fragment.n = false;
                fragment.k = false;
                Fragment fragment2 = fragment.g;
                fragment.h = fragment2 != null ? fragment2.f1207e : null;
                fragment.g = null;
                Bundle bundle = next.m;
                if (bundle != null) {
                    bundle.setClassLoader(this.q.f().getClassLoader());
                    fragment.f1205c = next.m.getSparseParcelableArray("android:view_state");
                    fragment.f1204b = next.m;
                }
            }
        }
        this.g.clear();
        for (androidx.fragment.app.l lVar : jVar.f1281a) {
            if (lVar != null) {
                Fragment fragmentA = lVar.a(this.q.f().getClassLoader(), d());
                fragmentA.r = this;
                if (I) {
                    Log.v("FragmentManager", "restoreSaveState: active (" + fragmentA.f1207e + "): " + fragmentA);
                }
                this.g.put(fragmentA.f1207e, fragmentA);
                lVar.n = null;
            }
        }
        this.f1249f.clear();
        ArrayList<String> arrayList = jVar.f1282b;
        if (arrayList != null) {
            for (String str : arrayList) {
                Fragment fragment3 = this.g.get(str);
                if (fragment3 != null) {
                    fragment3.k = true;
                    if (I) {
                        Log.v("FragmentManager", "restoreSaveState: added (" + str + "): " + fragment3);
                    }
                    if (!this.f1249f.contains(fragment3)) {
                        synchronized (this.f1249f) {
                            this.f1249f.add(fragment3);
                        }
                    } else {
                        throw new IllegalStateException("Already added " + fragment3);
                    }
                } else {
                    a(new IllegalStateException("No instantiated fragment for (" + str + ")"));
                    throw null;
                }
            }
        }
        androidx.fragment.app.b[] bVarArr = jVar.f1283c;
        if (bVarArr != null) {
            this.h = new ArrayList<>(bVarArr.length);
            int i = 0;
            while (true) {
                androidx.fragment.app.b[] bVarArr2 = jVar.f1283c;
                if (i >= bVarArr2.length) {
                    break;
                }
                androidx.fragment.app.a aVarA = bVarArr2[i].a(this);
                if (I) {
                    Log.v("FragmentManager", "restoreAllState: back stack #" + i + " (index " + aVarA.u + "): " + aVarA);
                    PrintWriter printWriter = new PrintWriter(new androidx.core.e.b("FragmentManager"));
                    aVarA.a("  ", printWriter, false);
                    printWriter.close();
                }
                this.h.add(aVarA);
                int i2 = aVarA.u;
                if (i2 >= 0) {
                    a(i2, aVarA);
                }
                i++;
            }
        } else {
            this.h = null;
        }
        String str2 = jVar.f1284d;
        if (str2 != null) {
            this.t = this.g.get(str2);
            u(this.t);
        }
        this.f1248e = jVar.f1285e;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void a(androidx.fragment.app.g gVar, androidx.fragment.app.d dVar, Fragment fragment) {
        androidx.lifecycle.h hVar;
        if (this.q == null) {
            this.q = gVar;
            this.r = dVar;
            this.s = fragment;
            if (this.s != null) {
                J();
            }
            if (gVar instanceof androidx.activity.c) {
                androidx.activity.c cVar = (androidx.activity.c) gVar;
                this.j = cVar.b();
                if (fragment != null) {
                    hVar = cVar;
                    hVar = fragment;
                }
                hVar = cVar;
                this.j.a(hVar, this.k);
            }
            if (fragment != null) {
                this.G = fragment.r.f(fragment);
                return;
            } else if (gVar instanceof t) {
                this.G = androidx.fragment.app.k.a(((t) gVar).d());
                return;
            } else {
                this.G = new androidx.fragment.app.k(false);
                return;
            }
        }
        throw new IllegalStateException("Already attached");
    }

    public void a(boolean z) {
        for (int size = this.f1249f.size() - 1; size >= 0; size--) {
            Fragment fragment = this.f1249f.get(size);
            if (fragment != null) {
                fragment.e(z);
            }
        }
    }

    public void a(Configuration configuration) {
        for (int i = 0; i < this.f1249f.size(); i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null) {
                fragment.a(configuration);
            }
        }
    }

    public boolean a(Menu menu, MenuInflater menuInflater) {
        if (this.p < 1) {
            return false;
        }
        ArrayList<Fragment> arrayList = null;
        boolean z = false;
        for (int i = 0; i < this.f1249f.size(); i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null && fragment.b(menu, menuInflater)) {
                if (arrayList == null) {
                    arrayList = new ArrayList<>();
                }
                arrayList.add(fragment);
                z = true;
            }
        }
        if (this.i != null) {
            for (int i2 = 0; i2 < this.i.size(); i2++) {
                Fragment fragment2 = this.i.get(i2);
                if (arrayList == null || !arrayList.contains(fragment2)) {
                    fragment2.V();
                }
            }
        }
        this.i = arrayList;
        return z;
    }

    public boolean a(MenuItem menuItem) {
        if (this.p < 1) {
            return false;
        }
        for (int i = 0; i < this.f1249f.size(); i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null && fragment.c(menuItem)) {
                return true;
            }
        }
        return false;
    }

    public void a(Menu menu) {
        if (this.p < 1) {
            return;
        }
        for (int i = 0; i < this.f1249f.size(); i++) {
            Fragment fragment = this.f1249f.get(i);
            if (fragment != null) {
                fragment.c(menu);
            }
        }
    }

    public void a(Fragment fragment, androidx.lifecycle.e.b bVar) {
        if (this.g.get(fragment.f1207e) == fragment && (fragment.s == null || fragment.r() == this)) {
            fragment.R = bVar;
            return;
        }
        throw new IllegalArgumentException("Fragment " + fragment + " is not an active fragment of FragmentManager " + this);
    }

    void a(Fragment fragment, Context context, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).a(fragment, context, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.a(this, fragment, context);
            }
        }
    }

    void a(Fragment fragment, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).a(fragment, bundle, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.a(this, fragment, bundle);
            }
        }
    }

    void a(Fragment fragment, View view, Bundle bundle, boolean z) {
        Fragment fragment2 = this.s;
        if (fragment2 != null) {
            androidx.fragment.app.h hVarR = fragment2.r();
            if (hVarR instanceof i) {
                ((i) hVarR).a(fragment, view, bundle, true);
            }
        }
        for (C0029i c0029i : this.o) {
            if (!z || c0029i.f1272b) {
                c0029i.f1271a.a(this, fragment, view, bundle);
            }
        }
    }
}
