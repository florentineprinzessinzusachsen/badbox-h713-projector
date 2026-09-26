package androidx.lifecycle;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: LifecycleRegistry.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends e {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final WeakReference<h> f1393c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a.a.a.b.a<g, b> f1391a = new a.a.a.b.a<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f1394d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f1395e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f1396f = false;
    private ArrayList<e.b> g = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private e.b f1392b = e.b.INITIALIZED;

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1397a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        static final /* synthetic */ int[] f1398b = new int[e.b.values().length];

        static {
            try {
                f1398b[e.b.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1398b[e.b.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1398b[e.b.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1398b[e.b.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1398b[e.b.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            f1397a = new int[e.a.values().length];
            try {
                f1397a[e.a.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1397a[e.a.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f1397a[e.a.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1397a[e.a.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1397a[e.a.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1397a[e.a.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1397a[e.a.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX INFO: compiled from: LifecycleRegistry.java */
    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        e.b f1399a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        f f1400b;

        b(g gVar, e.b bVar) {
            this.f1400b = k.a(gVar);
            this.f1399a = bVar;
        }

        void a(h hVar, e.a aVar) {
            e.b bVarB = i.b(aVar);
            this.f1399a = i.a(this.f1399a, bVarB);
            this.f1400b.a(hVar, aVar);
            this.f1399a = bVarB;
        }
    }

    public i(h hVar) {
        this.f1393c = new WeakReference<>(hVar);
    }

    private e.b c(g gVar) {
        Map.Entry<g, b> entryB = this.f1391a.b(gVar);
        e.b bVar = null;
        e.b bVar2 = entryB != null ? entryB.getValue().f1399a : null;
        if (!this.g.isEmpty()) {
            ArrayList<e.b> arrayList = this.g;
            bVar = arrayList.get(arrayList.size() - 1);
        }
        return a(a(this.f1392b, bVar2), bVar);
    }

    private void d(e.b bVar) {
        if (this.f1392b == bVar) {
            return;
        }
        this.f1392b = bVar;
        if (this.f1395e || this.f1394d != 0) {
            this.f1396f = true;
            return;
        }
        this.f1395e = true;
        d();
        this.f1395e = false;
    }

    private void e(e.b bVar) {
        this.g.add(bVar);
    }

    private static e.a f(e.b bVar) {
        int i = a.f1398b[bVar.ordinal()];
        if (i != 1) {
            if (i == 2) {
                return e.a.ON_START;
            }
            if (i == 3) {
                return e.a.ON_RESUME;
            }
            if (i == 4) {
                throw new IllegalArgumentException();
            }
            if (i != 5) {
                throw new IllegalArgumentException("Unexpected state value " + bVar);
            }
        }
        return e.a.ON_CREATE;
    }

    @Deprecated
    public void a(e.b bVar) {
        b(bVar);
    }

    public void b(e.b bVar) {
        d(bVar);
    }

    private boolean b() {
        if (this.f1391a.size() == 0) {
            return true;
        }
        e.b bVar = this.f1391a.a().getValue().f1399a;
        e.b bVar2 = this.f1391a.c().getValue().f1399a;
        return bVar == bVar2 && this.f1392b == bVar2;
    }

    public void a(e.a aVar) {
        d(b(aVar));
    }

    @Override // androidx.lifecycle.e
    public void a(g gVar) {
        h hVar;
        e.b bVar = this.f1392b;
        e.b bVar2 = e.b.DESTROYED;
        if (bVar != bVar2) {
            bVar2 = e.b.INITIALIZED;
        }
        b bVar3 = new b(gVar, bVar2);
        if (this.f1391a.b(gVar, bVar3) == null && (hVar = this.f1393c.get()) != null) {
            boolean z = this.f1394d != 0 || this.f1395e;
            e.b bVarC = c(gVar);
            this.f1394d++;
            while (bVar3.f1399a.compareTo(bVarC) < 0 && this.f1391a.contains(gVar)) {
                e(bVar3.f1399a);
                bVar3.a(hVar, f(bVar3.f1399a));
                c();
                bVarC = c(gVar);
            }
            if (!z) {
                d();
            }
            this.f1394d--;
        }
    }

    private void c() {
        ArrayList<e.b> arrayList = this.g;
        arrayList.remove(arrayList.size() - 1);
    }

    private static e.a c(e.b bVar) {
        int i = a.f1398b[bVar.ordinal()];
        if (i == 1) {
            throw new IllegalArgumentException();
        }
        if (i == 2) {
            return e.a.ON_DESTROY;
        }
        if (i == 3) {
            return e.a.ON_STOP;
        }
        if (i == 4) {
            return e.a.ON_PAUSE;
        }
        if (i != 5) {
            throw new IllegalArgumentException("Unexpected state value " + bVar);
        }
        throw new IllegalArgumentException();
    }

    @Override // androidx.lifecycle.e
    public void b(g gVar) {
        this.f1391a.remove(gVar);
    }

    static e.b b(e.a aVar) {
        switch (a.f1397a[aVar.ordinal()]) {
            case 1:
            case 2:
                return e.b.CREATED;
            case 3:
            case 4:
                return e.b.STARTED;
            case 5:
                return e.b.RESUMED;
            case 6:
                return e.b.DESTROYED;
            default:
                throw new IllegalArgumentException("Unexpected event value " + aVar);
        }
    }

    private void d() {
        h hVar = this.f1393c.get();
        if (hVar != null) {
            while (!b()) {
                this.f1396f = false;
                if (this.f1392b.compareTo(this.f1391a.a().getValue().f1399a) < 0) {
                    a(hVar);
                }
                Map.Entry<g, b> entryC = this.f1391a.c();
                if (!this.f1396f && entryC != null && this.f1392b.compareTo(entryC.getValue().f1399a) > 0) {
                    b(hVar);
                }
            }
            this.f1396f = false;
            return;
        }
        throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is alreadygarbage collected. It is too late to change lifecycle state.");
    }

    private void b(h hVar) {
        a.a.a.b.b<g, b>.d dVarB = this.f1391a.b();
        while (dVarB.hasNext() && !this.f1396f) {
            Map.Entry next = dVarB.next();
            b bVar = (b) next.getValue();
            while (bVar.f1399a.compareTo(this.f1392b) < 0 && !this.f1396f && this.f1391a.contains((g) next.getKey())) {
                e(bVar.f1399a);
                bVar.a(hVar, f(bVar.f1399a));
                c();
            }
        }
    }

    @Override // androidx.lifecycle.e
    public e.b a() {
        return this.f1392b;
    }

    private void a(h hVar) {
        Iterator<Map.Entry<g, b>> itDescendingIterator = this.f1391a.descendingIterator();
        while (itDescendingIterator.hasNext() && !this.f1396f) {
            Map.Entry<g, b> next = itDescendingIterator.next();
            b value = next.getValue();
            while (value.f1399a.compareTo(this.f1392b) > 0 && !this.f1396f && this.f1391a.contains(next.getKey())) {
                e.a aVarC = c(value.f1399a);
                e(b(aVarC));
                value.a(hVar, aVarC);
                c();
            }
        }
    }

    static e.b a(e.b bVar, e.b bVar2) {
        return (bVar2 == null || bVar2.compareTo(bVar) >= 0) ? bVar : bVar2;
    }
}
