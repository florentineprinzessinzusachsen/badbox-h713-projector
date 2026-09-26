package androidx.fragment.app;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class q implements androidx.lifecycle.h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private androidx.lifecycle.i f1356a = null;

    q() {
    }

    @Override // androidx.lifecycle.h
    public androidx.lifecycle.e a() {
        d();
        return this.f1356a;
    }

    void d() {
        if (this.f1356a == null) {
            this.f1356a = new androidx.lifecycle.i(this);
        }
    }

    boolean e() {
        return this.f1356a != null;
    }

    void a(androidx.lifecycle.e.a aVar) {
        this.f1356a.a(aVar);
    }
}
