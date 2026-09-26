package c.a.b0.e.e;

import c.a.a0.n;
import c.a.u;
import c.a.v;
import c.a.w;

/* JADX INFO: compiled from: SingleMap.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T, R> extends u<R> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final w<? extends T> f2972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final n<? super T, ? extends R> f2973b;

    /* JADX INFO: renamed from: c.a.b0.e.e.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SingleMap.java */
    static final class C0071a<T, R> implements v<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final v<? super R> f2974a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final n<? super T, ? extends R> f2975b;

        C0071a(v<? super R> vVar, n<? super T, ? extends R> nVar) {
            this.f2974a = vVar;
            this.f2975b = nVar;
        }

        @Override // c.a.v, c.a.i
        public void a(T t) {
            try {
                R rApply = this.f2975b.apply(t);
                c.a.b0.b.b.a(rApply, "The mapper function returned a null value.");
                this.f2974a.a(rApply);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                onError(th);
            }
        }

        @Override // c.a.v, c.a.c, c.a.i
        public void onError(Throwable th) {
            this.f2974a.onError(th);
        }

        @Override // c.a.v, c.a.c, c.a.i
        public void onSubscribe(c.a.y.b bVar) {
            this.f2974a.onSubscribe(bVar);
        }
    }

    public a(w<? extends T> wVar, n<? super T, ? extends R> nVar) {
        this.f2972a = wVar;
        this.f2973b = nVar;
    }

    @Override // c.a.u
    protected void b(v<? super R> vVar) {
        this.f2972a.a(new C0071a(vVar, this.f2973b));
    }
}
