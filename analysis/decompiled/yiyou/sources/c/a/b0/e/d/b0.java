package c.a.b0.e.d;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableCreate.java */
/* JADX INFO: loaded from: classes.dex */
public final class b0<T> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.o<T> f1976a;

    public b0(c.a.o<T> oVar) {
        this.f1976a = oVar;
    }

    @Override // c.a.l
    protected void subscribeActual(c.a.s<? super T> sVar) {
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        try {
            this.f1976a.subscribe(aVar);
        } catch (Throwable th) {
            c.a.z.b.b(th);
            aVar.a(th);
        }
    }

    /* JADX INFO: compiled from: ObservableCreate.java */
    static final class a<T> extends AtomicReference<c.a.y.b> implements c.a.n<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f1977a;

        a(c.a.s<? super T> sVar) {
            this.f1977a = sVar;
        }

        public void a(Throwable th) {
            if (b(th)) {
                return;
            }
            c.a.e0.a.b(th);
        }

        public boolean b(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (a()) {
                return false;
            }
            try {
                this.f1977a.onError(th);
                return true;
            } finally {
                dispose();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // c.a.e
        public void onNext(T t) {
            if (t == null) {
                a(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                if (a()) {
                    return;
                }
                this.f1977a.onNext(t);
            }
        }

        @Override // java.util.concurrent.atomic.AtomicReference
        public String toString() {
            return String.format("%s{%s}", a.class.getSimpleName(), super.toString());
        }

        public boolean a() {
            return c.a.b0.a.c.a(get());
        }
    }
}
