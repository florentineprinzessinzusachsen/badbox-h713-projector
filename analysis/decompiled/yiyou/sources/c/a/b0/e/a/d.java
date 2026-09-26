package c.a.b0.e.a;

import c.a.g;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: FlowableOnBackpressureDrop.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> extends c.a.b0.e.a.a<T, T> implements c.a.a0.f<T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.f<? super T> f1852c;

    /* JADX INFO: compiled from: FlowableOnBackpressureDrop.java */
    static final class a<T> extends AtomicLong implements g<T>, f.a.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final f.a.b<? super T> f1853a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final c.a.a0.f<? super T> f1854b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        f.a.c f1855c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        boolean f1856d;

        a(f.a.b<? super T> bVar, c.a.a0.f<? super T> fVar) {
            this.f1853a = bVar;
            this.f1854b = fVar;
        }

        @Override // f.a.b
        public void a(f.a.c cVar) {
            if (c.a.b0.i.b.a(this.f1855c, cVar)) {
                this.f1855c = cVar;
                this.f1853a.a(this);
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
            this.f1855c.cancel();
        }

        @Override // f.a.b
        public void onComplete() {
            if (this.f1856d) {
                return;
            }
            this.f1856d = true;
            this.f1853a.onComplete();
        }

        @Override // f.a.b
        public void onError(Throwable th) {
            if (this.f1856d) {
                c.a.e0.a.b(th);
            } else {
                this.f1856d = true;
                this.f1853a.onError(th);
            }
        }

        @Override // f.a.b
        public void onNext(T t) {
            if (this.f1856d) {
                return;
            }
            if (get() != 0) {
                this.f1853a.onNext(t);
                c.a.b0.j.d.b(this, 1L);
                return;
            }
            try {
                this.f1854b.a(t);
            } catch (Throwable th) {
                c.a.z.b.b(th);
                cancel();
                onError(th);
            }
        }
    }

    public d(c.a.f<T> fVar) {
        super(fVar);
        this.f1852c = this;
    }

    @Override // c.a.a0.f
    public void a(T t) {
    }

    @Override // c.a.f
    protected void b(f.a.b<? super T> bVar) {
        this.f1838b.a((g) new a(bVar, this.f1852c));
    }
}
