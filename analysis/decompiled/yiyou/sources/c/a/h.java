package c.a;

/* JADX INFO: compiled from: Maybe.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class h<T> implements j<T> {
    @Override // c.a.j
    public final void a(i<? super T> iVar) {
        c.a.b0.b.b.a(iVar, "observer is null");
        i<? super T> iVarA = c.a.e0.a.a(this, iVar);
        c.a.b0.b.b.a(iVarA, "observer returned by the RxJavaPlugins hook is null");
        try {
            b(iVarA);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            c.a.z.b.b(th);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final T b() {
        c.a.b0.d.g gVar = new c.a.b0.d.g();
        a(gVar);
        return (T) gVar.a();
    }

    protected abstract void b(i<? super T> iVar);
}
