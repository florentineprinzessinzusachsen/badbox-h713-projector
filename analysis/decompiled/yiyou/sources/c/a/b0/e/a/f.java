package c.a.b0.e.a;

import c.a.g;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: FlowableOnBackpressureLatest.java */
/* JADX INFO: loaded from: classes.dex */
public final class f<T> extends c.a.b0.e.a.a<T, T> {
    public f(c.a.f<T> fVar) {
        super(fVar);
    }

    @Override // c.a.f
    protected void b(f.a.b<? super T> bVar) {
        this.f1838b.a((g) new a(bVar));
    }

    /* JADX INFO: compiled from: FlowableOnBackpressureLatest.java */
    static final class a<T> extends AtomicInteger implements g<T>, f.a.c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final f.a.b<? super T> f1860a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        f.a.c f1861b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        volatile boolean f1862c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        Throwable f1863d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        volatile boolean f1864e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final AtomicLong f1865f = new AtomicLong();
        final AtomicReference<T> g = new AtomicReference<>();

        a(f.a.b<? super T> bVar) {
            this.f1860a = bVar;
        }

        @Override // f.a.b
        public void a(f.a.c cVar) {
            if (c.a.b0.i.b.a(this.f1861b, cVar)) {
                this.f1861b = cVar;
                this.f1860a.a(this);
                cVar.c(Long.MAX_VALUE);
            }
        }

        @Override // f.a.c
        public void c(long j) {
            if (c.a.b0.i.b.a(j)) {
                c.a.b0.j.d.a(this.f1865f, j);
                a();
            }
        }

        @Override // f.a.c
        public void cancel() {
            if (this.f1864e) {
                return;
            }
            this.f1864e = true;
            this.f1861b.cancel();
            if (getAndIncrement() == 0) {
                this.g.lazySet(null);
            }
        }

        @Override // f.a.b
        public void onComplete() {
            this.f1862c = true;
            a();
        }

        @Override // f.a.b
        public void onError(Throwable th) {
            this.f1863d = th;
            this.f1862c = true;
            a();
        }

        @Override // f.a.b
        public void onNext(T t) {
            this.g.lazySet(t);
            a();
        }

        void a() {
            if (getAndIncrement() != 0) {
                return;
            }
            f.a.b<? super T> bVar = this.f1860a;
            AtomicLong atomicLong = this.f1865f;
            AtomicReference<T> atomicReference = this.g;
            int iAddAndGet = 1;
            do {
                long j = 0;
                while (true) {
                    if (j == atomicLong.get()) {
                        break;
                    }
                    boolean z = this.f1862c;
                    T andSet = atomicReference.getAndSet(null);
                    boolean z2 = andSet == null;
                    if (a(z, z2, bVar, atomicReference)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    bVar.onNext(andSet);
                    j++;
                }
                if (j == atomicLong.get()) {
                    if (a(this.f1862c, atomicReference.get() == null, bVar, atomicReference)) {
                        return;
                    }
                }
                if (j != 0) {
                    c.a.b0.j.d.b(atomicLong, j);
                }
                iAddAndGet = addAndGet(-iAddAndGet);
            } while (iAddAndGet != 0);
        }

        boolean a(boolean z, boolean z2, f.a.b<?> bVar, AtomicReference<T> atomicReference) {
            if (this.f1864e) {
                atomicReference.lazySet(null);
                return true;
            }
            if (!z) {
                return false;
            }
            Throwable th = this.f1863d;
            if (th != null) {
                atomicReference.lazySet(null);
                bVar.onError(th);
                return true;
            }
            if (!z2) {
                return false;
            }
            bVar.onComplete();
            return true;
        }
    }
}
