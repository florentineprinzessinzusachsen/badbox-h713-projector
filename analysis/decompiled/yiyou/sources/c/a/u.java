package c.a;

/* JADX INFO: compiled from: Single.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class u<T> implements w<T> {
    public final <R> u<R> a(c.a.a0.n<? super T, ? extends R> nVar) {
        c.a.b0.b.b.a(nVar, "mapper is null");
        return c.a.e0.a.a(new c.a.b0.e.e.a(this, nVar));
    }

    public final T b() {
        c.a.b0.d.g gVar = new c.a.b0.d.g();
        a(gVar);
        return (T) gVar.a();
    }

    protected abstract void b(v<? super T> vVar);

    /* JADX WARN: Multi-variable type inference failed */
    public final l<T> c() {
        return this instanceof c.a.b0.c.a ? ((c.a.b0.c.a) this).a() : c.a.e0.a.a(new c.a.b0.e.e.b(this));
    }

    @Override // c.a.w
    public final void a(v<? super T> vVar) {
        c.a.b0.b.b.a(vVar, "subscriber is null");
        v<? super T> vVarA = c.a.e0.a.a(this, vVar);
        c.a.b0.b.b.a(vVarA, "subscriber returned by the RxJavaPlugins hook is null");
        try {
            b(vVarA);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            c.a.z.b.b(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }
}
