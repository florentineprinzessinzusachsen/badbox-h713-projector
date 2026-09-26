package c.a.b0.e.b;

import c.a.b0.a.c;
import c.a.b0.c.b;
import c.a.b0.d.i;
import c.a.l;
import c.a.s;

/* JADX INFO: compiled from: MaybeToObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> extends l<T> implements b<T> {

    /* JADX INFO: renamed from: c.a.b0.e.b.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: MaybeToObservable.java */
    static final class C0045a<T> extends i<T> implements c.a.i<T> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f1866c;

        C0045a(s<? super T> sVar) {
            super(sVar);
        }

        @Override // c.a.i
        public void a(T t) {
            b(t);
        }

        @Override // c.a.b0.d.i, c.a.y.b
        public void dispose() {
            super.dispose();
            this.f1866c.dispose();
        }

        @Override // c.a.i
        public void onComplete() {
            a();
        }

        @Override // c.a.i
        public void onError(Throwable th) {
            a(th);
        }

        @Override // c.a.i
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a(this.f1866c, bVar)) {
                this.f1866c = bVar;
                this.f1810a.onSubscribe(this);
            }
        }
    }

    public static <T> c.a.i<T> a(s<? super T> sVar) {
        return new C0045a(sVar);
    }
}
