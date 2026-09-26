package c.a.b0.e.a;

import c.a.g;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: FlowableOnBackpressureError.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> extends c.a.b0.e.a.a<T, T> {

    /* JADX INFO: compiled from: FlowableOnBackpressureError.java */
    static final class a<T> extends AtomicLong implements g<T>, f.a.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final f.a.b<? super T> f1857a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        f.a.c f1858b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        boolean f1859c;

        a(f.a.b<? super T> bVar) {
            this.f1857a = bVar;
        }

        @Override // f.a.b
        public void a(f.a.c cVar) {
            if (c.a.b0.i.b.a(this.f1858b, cVar)) {
                this.f1858b = cVar;
                this.f1857a.a(this);
                cVar.c(Long.MAX_VALUE);
            }
        }

        @Override // f.a.c
        public void c(long j) {
            if (c.a.b0.i.b.a(j)) {
                c.a.b0.j.d.a(this, j);
            }
        }

        @Override // f.a.c
        public void cancel() {
            this.f1858b.cancel();
        }

        @Override // f.a.b
        public void onComplete() {
            if (this.f1859c) {
                return;
            }
            this.f1859c = true;
            this.f1857a.onComplete();
        }

        @Override // f.a.b
        public void onError(Throwable th) {
            if (this.f1859c) {
                c.a.e0.a.b(th);
            } else {
                this.f1859c = true;
                this.f1857a.onError(th);
            }
        }

        @Override // f.a.b
        public void onNext(T t) {
            if (this.f1859c) {
                return;
            }
            if (get() == 0) {
                onError(new c.a.z.c("could not emit value due to lack of requests"));
            } else {
                this.f1857a.onNext(t);
                c.a.b0.j.d.b(this, 1L);
            }
        }
    }

    public e(c.a.f<T> fVar) {
        super(fVar);
    }

    @Override // c.a.f
    protected void b(f.a.b<? super T> bVar) {
        this.f1838b.a((g) new a(bVar));
    }
}
