package c.a.d0;

import c.a.i;
import c.a.s;
import c.a.v;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: TestObserver.java */
/* JADX INFO: loaded from: classes.dex */
public class g<T> extends c.a.d0.a<T, g<T>> implements s<T>, c.a.y.b, i<T>, v<T>, c.a.c {
    private final s<? super T> h;
    private final AtomicReference<c.a.y.b> i;
    private c.a.b0.c.e<T> j;

    /* JADX INFO: compiled from: TestObserver.java */
    enum a implements s<Object> {
        INSTANCE;

        @Override // c.a.s
        public void onComplete() {
        }

        @Override // c.a.s
        public void onError(Throwable th) {
        }

        @Override // c.a.s
        public void onNext(Object obj) {
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
        }
    }

    public g() {
        this(a.INSTANCE);
    }

    @Override // c.a.i
    public void a(T t) {
        onNext(t);
        onComplete();
    }

    @Override // c.a.y.b
    public final void dispose() {
        c.a.b0.a.c.a(this.i);
    }

    @Override // c.a.s
    public void onComplete() {
        if (!this.f3113e) {
            this.f3113e = true;
            if (this.i.get() == null) {
                this.f3111c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            Thread.currentThread();
            this.f3112d++;
            this.h.onComplete();
        } finally {
            this.f3109a.countDown();
        }
    }

    @Override // c.a.s
    public void onError(Throwable th) {
        if (!this.f3113e) {
            this.f3113e = true;
            if (this.i.get() == null) {
                this.f3111c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        try {
            Thread.currentThread();
            if (th == null) {
                this.f3111c.add(new NullPointerException("onError received a null Throwable"));
            } else {
                this.f3111c.add(th);
            }
            this.h.onError(th);
        } finally {
            this.f3109a.countDown();
        }
    }

    @Override // c.a.s
    public void onNext(T t) {
        if (!this.f3113e) {
            this.f3113e = true;
            if (this.i.get() == null) {
                this.f3111c.add(new IllegalStateException("onSubscribe not called in proper order"));
            }
        }
        Thread.currentThread();
        if (this.g != 2) {
            this.f3110b.add(t);
            if (t == null) {
                this.f3111c.add(new NullPointerException("onNext received a null value"));
            }
            this.h.onNext(t);
            return;
        }
        while (true) {
            try {
                T tPoll = this.j.poll();
                if (tPoll == null) {
                    return;
                } else {
                    this.f3110b.add(tPoll);
                }
            } catch (Throwable th) {
                this.f3111c.add(th);
                this.j.dispose();
                return;
            }
        }
    }

    @Override // c.a.s
    public void onSubscribe(c.a.y.b bVar) {
        Thread.currentThread();
        if (bVar == null) {
            this.f3111c.add(new NullPointerException("onSubscribe received a null Subscription"));
            return;
        }
        if (!this.i.compareAndSet(null, bVar)) {
            bVar.dispose();
            if (this.i.get() != c.a.b0.a.c.DISPOSED) {
                this.f3111c.add(new IllegalStateException("onSubscribe received multiple subscriptions: " + bVar));
                return;
            }
            return;
        }
        int i = this.f3114f;
        if (i != 0 && (bVar instanceof c.a.b0.c.e)) {
            this.j = (c.a.b0.c.e) bVar;
            int iA = this.j.a(i);
            this.g = iA;
            if (iA == 1) {
                this.f3113e = true;
                Thread.currentThread();
                while (true) {
                    try {
                        T tPoll = this.j.poll();
                        if (tPoll == null) {
                            this.f3112d++;
                            this.i.lazySet(c.a.b0.a.c.DISPOSED);
                            return;
                        }
                        this.f3110b.add(tPoll);
                    } catch (Throwable th) {
                        this.f3111c.add(th);
                        return;
                    }
                }
            }
        }
        this.h.onSubscribe(bVar);
    }

    public g(s<? super T> sVar) {
        this.i = new AtomicReference<>();
        this.h = sVar;
    }
}
