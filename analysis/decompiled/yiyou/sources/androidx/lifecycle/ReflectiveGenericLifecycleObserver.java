package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1375a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.C0030a f1376b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1375a = obj;
        this.f1376b = a.f1378c.a(this.f1375a.getClass());
    }

    @Override // androidx.lifecycle.f
    public void a(h hVar, e.a aVar) {
        this.f1376b.a(hVar, aVar, this.f1375a);
    }
}
