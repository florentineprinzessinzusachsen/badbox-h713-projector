package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
class FullLifecycleObserverAdapter implements f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1358a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final f f1359b;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1360a = new int[e.a.values().length];

        static {
            try {
                f1360a[e.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1360a[e.a.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1360a[e.a.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1360a[e.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1360a[e.a.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1360a[e.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1360a[e.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    FullLifecycleObserverAdapter(b bVar, f fVar) {
        this.f1358a = bVar;
        this.f1359b = fVar;
    }

    @Override // androidx.lifecycle.f
    public void a(h hVar, e.a aVar) {
        switch (a.f1360a[aVar.ordinal()]) {
            case 1:
                this.f1358a.c(hVar);
                break;
            case 2:
                this.f1358a.f(hVar);
                break;
            case 3:
                this.f1358a.a(hVar);
                break;
            case 4:
                this.f1358a.d(hVar);
                break;
            case 5:
                this.f1358a.e(hVar);
                break;
            case 6:
                this.f1358a.b(hVar);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        f fVar = this.f1359b;
        if (fVar != null) {
            fVar.a(hVar, aVar);
        }
    }
}
