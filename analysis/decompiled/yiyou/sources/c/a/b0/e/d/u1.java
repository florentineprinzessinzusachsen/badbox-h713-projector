package c.a.b0.e.d;

/* JADX INFO: compiled from: ObservableLift.java */
/* JADX INFO: loaded from: classes.dex */
public final class u1<R, T> extends a<T, R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.p<? extends R, ? super T> f2755b;

    public u1(c.a.q<T> qVar, c.a.p<? extends R, ? super T> pVar) {
        super(qVar);
        this.f2755b = pVar;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super R> sVar) {
        try {
            c.a.s<? super Object> sVarA = this.f2755b.a(sVar);
            c.a.b0.b.b.a(sVarA, "Operator " + this.f2755b + " returned a null Observer");
            this.f1932a.subscribe(sVarA);
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
