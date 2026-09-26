package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.s;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
class k extends androidx.lifecycle.q {
    private static final androidx.lifecycle.r.a h = new a();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final boolean f1289e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashSet<Fragment> f1286b = new HashSet<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final HashMap<String, k> f1287c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashMap<String, s> f1288d = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1290f = false;
    private boolean g = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    static class a implements androidx.lifecycle.r.a {
        a() {
        }

        @Override // androidx.lifecycle.r.a
        public <T extends androidx.lifecycle.q> T a(Class<T> cls) {
            return new k(true);
        }
    }

    k(boolean z) {
        this.f1289e = z;
    }

    static k a(s sVar) {
        return (k) new androidx.lifecycle.r(sVar, h).a(k.class);
    }

    @Override // androidx.lifecycle.q
    protected void b() {
        if (i.I) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f1290f = true;
    }

    Collection<Fragment> c() {
        return this.f1286b;
    }

    boolean d() {
        return this.f1290f;
    }

    boolean e(Fragment fragment) {
        return this.f1286b.remove(fragment);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return this.f1286b.equals(kVar.f1286b) && this.f1287c.equals(kVar.f1287c) && this.f1288d.equals(kVar.f1288d);
    }

    boolean f(Fragment fragment) {
        if (this.f1286b.contains(fragment)) {
            return this.f1289e ? this.f1290f : !this.g;
        }
        return true;
    }

    public int hashCode() {
        return (((this.f1286b.hashCode() * 31) + this.f1287c.hashCode()) * 31) + this.f1288d.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1286b.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1287c.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f1288d.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }

    k c(Fragment fragment) {
        k kVar = this.f1287c.get(fragment.f1207e);
        if (kVar != null) {
            return kVar;
        }
        k kVar2 = new k(this.f1289e);
        this.f1287c.put(fragment.f1207e, kVar2);
        return kVar2;
    }

    s d(Fragment fragment) {
        s sVar = this.f1288d.get(fragment.f1207e);
        if (sVar != null) {
            return sVar;
        }
        s sVar2 = new s();
        this.f1288d.put(fragment.f1207e, sVar2);
        return sVar2;
    }

    boolean a(Fragment fragment) {
        return this.f1286b.add(fragment);
    }

    void b(Fragment fragment) {
        if (i.I) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        k kVar = this.f1287c.get(fragment.f1207e);
        if (kVar != null) {
            kVar.b();
            this.f1287c.remove(fragment.f1207e);
        }
        s sVar = this.f1288d.get(fragment.f1207e);
        if (sVar != null) {
            sVar.a();
            this.f1288d.remove(fragment.f1207e);
        }
    }
}
