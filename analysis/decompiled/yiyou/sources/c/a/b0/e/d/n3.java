package c.a.b0.e.d;

import java.util.ArrayDeque;

/* JADX INFO: compiled from: ObservableTakeLast.java */
/* JADX INFO: loaded from: classes.dex */
public final class n3<T> extends c.a.b0.e.d.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final int f2506b;

    /* JADX INFO: compiled from: ObservableTakeLast.java */
    static final class a<T> extends ArrayDeque<T> implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2507a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final int f2508b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        c.a.y.b f2509c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        volatile boolean f2510d;

        a(c.a.s<? super T> sVar, int i) {
            this.f2507a = sVar;
            this.f2508b = i;
        }

        @Override // c.a.y.b
        public void dispose() {
            if (this.f2510d) {
                return;
            }
            this.f2510d = true;
            this.f2509c.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            c.a.s<? super T> sVar = this.f2507a;
            while (!this.f2510d) {
                T tPoll = poll();
                if (tPoll == null) {
                    if (this.f2510d) {
                        return;
                    }
                    sVar.onComplete();
                    return;
                }
                sVar.onNext(tPoll);
            }
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f2507a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            if (this.f2508b == size()) {
                poll();
            }
            offer(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2509c, bVar)) {
                this.f2509c = bVar;
                this.f2507a.onSubscribe(this);
            }
        }
    }

    public n3(c.a.q<T> qVar, int i) {
        super(qVar);
        this.f2506b = i;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        this.f1932a.subscribe(new a(sVar, this.f2506b));
    }
}
