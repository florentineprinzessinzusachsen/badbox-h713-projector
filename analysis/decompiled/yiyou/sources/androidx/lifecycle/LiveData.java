package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
public abstract class LiveData<T> {
    static final Object i = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Object f1362a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private a.a.a.b.b<n<? super T>, LiveData<T>.b> f1363b = new a.a.a.b.b<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1364c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private volatile Object f1365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    volatile Object f1366e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f1367f;
    private boolean g;
    private boolean h;

    class a implements Runnable {
        a() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            Object obj;
            synchronized (LiveData.this.f1362a) {
                obj = LiveData.this.f1366e;
                LiveData.this.f1366e = LiveData.i;
            }
            LiveData.this.a(obj);
        }
    }

    private abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final n<? super T> f1371a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f1372b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1373c = -1;

        b(n<? super T> nVar) {
            this.f1371a = nVar;
        }

        void a() {
        }

        void a(boolean z) {
            if (z == this.f1372b) {
                return;
            }
            this.f1372b = z;
            boolean z2 = LiveData.this.f1364c == 0;
            LiveData.this.f1364c += this.f1372b ? 1 : -1;
            if (z2 && this.f1372b) {
                LiveData.this.a();
            }
            LiveData liveData = LiveData.this;
            if (liveData.f1364c == 0 && !this.f1372b) {
                liveData.b();
            }
            if (this.f1372b) {
                LiveData.this.a(this);
            }
        }

        boolean a(h hVar) {
            return false;
        }

        abstract boolean b();
    }

    public LiveData() {
        Object obj = i;
        this.f1365d = obj;
        this.f1366e = obj;
        this.f1367f = -1;
        new a();
    }

    private void b(LiveData<T>.b bVar) {
        if (bVar.f1372b) {
            if (!bVar.b()) {
                bVar.a(false);
                return;
            }
            int i2 = bVar.f1373c;
            int i3 = this.f1367f;
            if (i2 >= i3) {
                return;
            }
            bVar.f1373c = i3;
            bVar.f1371a.a((Object) this.f1365d);
        }
    }

    protected void a() {
    }

    void a(LiveData<T>.b bVar) {
        if (this.g) {
            this.h = true;
            return;
        }
        this.g = true;
        do {
            this.h = false;
            if (bVar != null) {
                b(bVar);
                bVar = null;
            } else {
                a.a.a.b.b<n<? super T>, LiveData<T>.b>.d dVarB = this.f1363b.b();
                while (dVarB.hasNext()) {
                    b((b) dVarB.next().getValue());
                    if (this.h) {
                        break;
                    }
                }
            }
        } while (this.h);
        this.g = false;
    }

    protected void b() {
    }

    class LifecycleBoundObserver extends LiveData<T>.b implements d {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final h f1368e;

        LifecycleBoundObserver(h hVar, n<? super T> nVar) {
            super(nVar);
            this.f1368e = hVar;
        }

        @Override // androidx.lifecycle.f
        public void a(h hVar, e.a aVar) {
            if (this.f1368e.a().a() == e.b.DESTROYED) {
                LiveData.this.a((n) this.f1371a);
            } else {
                a(b());
            }
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean b() {
            return this.f1368e.a().a().a(e.b.STARTED);
        }

        @Override // androidx.lifecycle.LiveData.b
        boolean a(h hVar) {
            return this.f1368e == hVar;
        }

        @Override // androidx.lifecycle.LiveData.b
        void a() {
            this.f1368e.a().b(this);
        }
    }

    public void a(h hVar, n<? super T> nVar) {
        a("observe");
        if (hVar.a().a() == e.b.DESTROYED) {
            return;
        }
        LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(hVar, nVar);
        LiveData<T>.b bVarB = this.f1363b.b(nVar, lifecycleBoundObserver);
        if (bVarB != null && !bVarB.a(hVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (bVarB != null) {
            return;
        }
        hVar.a().a(lifecycleBoundObserver);
    }

    public void a(n<? super T> nVar) {
        a("removeObserver");
        LiveData<T>.b bVarRemove = this.f1363b.remove(nVar);
        if (bVarRemove == null) {
            return;
        }
        bVarRemove.a();
        bVarRemove.a(false);
    }

    protected void a(T t) {
        a("setValue");
        this.f1367f++;
        this.f1365d = t;
        a((b) null);
    }

    private static void a(String str) {
        if (a.a.a.a.a.b().a()) {
            return;
        }
        throw new IllegalStateException("Cannot invoke " + str + " on a background thread");
    }
}
