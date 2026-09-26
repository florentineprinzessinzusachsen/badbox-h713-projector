package c.a;

/* JADX INFO: compiled from: Flowable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class f<T> implements f.a.a<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final int f3134a = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    public static int d() {
        return f3134a;
    }

    public final f<T> a() {
        return a(d(), false, true);
    }

    public final f<T> b() {
        return c.a.e0.a.a(new c.a.b0.e.a.d(this));
    }

    protected abstract void b(f.a.b<? super T> bVar);

    public final f<T> c() {
        return c.a.e0.a.a(new c.a.b0.e.a.f(this));
    }

    public final f<T> a(int i, boolean z, boolean z2) {
        c.a.b0.b.b.a(i, "bufferSize");
        return c.a.e0.a.a(new c.a.b0.e.a.c(this, i, z2, z, c.a.b0.b.a.f1759c));
    }

    @Override // f.a.a
    public final void a(f.a.b<? super T> bVar) {
        if (bVar instanceof g) {
            a((g) bVar);
        } else {
            c.a.b0.b.b.a(bVar, "s is null");
            a((g) new c.a.b0.h.a(bVar));
        }
    }

    public final void a(g<? super T> gVar) {
        c.a.b0.b.b.a(gVar, "s is null");
        try {
            f.a.b<? super T> bVarA = c.a.e0.a.a(this, gVar);
            c.a.b0.b.b.a(bVarA, "Plugin returned null Subscriber");
            b(bVarA);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.e0.a.b(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }
}
