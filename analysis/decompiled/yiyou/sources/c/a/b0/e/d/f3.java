package c.a.b0.e.d;

import java.util.ArrayDeque;

/* JADX INFO: compiled from: ObservableSkipLast.java */
/* JADX INFO: loaded from: classes.dex */
public final class f3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f2150b;

    /* JADX INFO: compiled from: ObservableSkipLast.java */
    static final class a<T> extends ArrayDeque<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2151a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2152b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2153c;

        a(c.a.s<? super T> sVar, int i) {
            super(i);
            this.f2151a = sVar;
            this.f2152b = i;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f2153c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f2151a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2151a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2152b == size()) {
                this.f2151a.onNext(poll());
            }
            offer(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2153c, bVar)) {
                this.f2153c = bVar;
                this.f2151a.onSubscribe(this);
            }
        }
    }

    public f3(c.a.q<T> qVar, int i) {
        super(qVar);
        this.f2150b = i;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2150b));
    }
}
