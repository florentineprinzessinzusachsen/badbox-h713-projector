package c.a;

/* JADX INFO: compiled from: Completable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements d {
    private static NullPointerException a(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    protected abstract void b(c cVar);

    @Override // c.a.d
    public final void a(c cVar) {
        c.a.b0.b.b.a(cVar, "s is null");
        try {
            b(c.a.e0.a.a(this, cVar));
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            c.a.z.b.b(th);
            c.a.e0.a.b(th);
            throw a(th);
        }
    }
}
