package c.a.b0.e.e;

import c.a.b0.a.c;
import c.a.b0.d.i;
import c.a.l;
import c.a.s;
import c.a.v;
import c.a.w;

/* JADX INFO: compiled from: SingleToObservable.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final w<? extends T> f2976a;

    /* JADX INFO: compiled from: SingleToObservable.java */
    static final class a<T> extends i<T> implements v<T> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2977c;

        a(s<? super T> sVar) {
            super(sVar);
        }

        @Override // c.a.v, c.a.i
        public void a(T t) {
            b(t);
        }

        @Override // c.a.b0.d.i, c.a.y.b
        public void dispose() {
            super.dispose();
            this.f2977c.dispose();
        }

        @Override // c.a.v, c.a.c, c.a.i
        public void onError(Throwable th) {
            a(th);
        }

        @Override // c.a.v, c.a.c, c.a.i
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a(this.f2977c, bVar)) {
                this.f2977c = bVar;
                this.f1810a.onSubscribe(this);
            }
        }
    }

    public b(w<? extends T> wVar) {
        this.f2976a = wVar;
    }

    public static <T> v<T> a(s<? super T> sVar) {
        return new a(sVar);
    }

    @Override // c.a.l
    public void subscribeActual(s<? super T> sVar) {
        this.f2976a.a(a(sVar));
    }
}
