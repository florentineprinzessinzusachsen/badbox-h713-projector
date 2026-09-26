package c.a.b0.e.a;

import c.a.b0.c.i;
import c.a.g;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: FlowableOnBackpressureBuffer.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> extends c.a.b0.e.a.a<T, T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final int f1842c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f1843d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final boolean f1844e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final c.a.a0.a f1845f;

    public c(c.a.f<T> fVar, int i, boolean z, boolean z2, c.a.a0.a aVar) {
        super(fVar);
        this.f1842c = i;
        this.f1843d = z;
        this.f1844e = z2;
        this.f1845f = aVar;
    }

    @Override // c.a.f
    protected void b(f.a.b<? super T> bVar) {
        this.f1838b.a((g) new a(bVar, this.f1842c, this.f1843d, this.f1844e, this.f1845f));
    }

    /* JADX INFO: compiled from: FlowableOnBackpressureBuffer.java */
    static final class a<T> extends c.a.b0.i.a<T> implements g<T> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final f.a.b<? super T> f1846a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final i<T> f1847b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final boolean f1848c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final c.a.a0.a f1849d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        f.a.c f1850e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        volatile boolean f1851f;
        volatile boolean g;
        Throwable h;
        final AtomicLong i = new AtomicLong();
        boolean j;

        a(f.a.b<? super T> bVar, int i, boolean z, boolean z2, c.a.a0.a aVar) {
            this.f1846a = bVar;
            this.f1849d = aVar;
            this.f1848c = z2;
            this.f1847b = z ? new c.a.b0.f.c<>(i) : new c.a.b0.f.b<>(i);
        }

        @Override // f.a.b
        public void a(f.a.c cVar) {
            if (c.a.b0.i.b.a(this.f1850e, cVar)) {
                this.f1850e = cVar;
                this.f1846a.a(this);
                cVar.c(Long.MAX_VALUE);
            }
        }

        @Override // f.a.c
        public void c(long j) {
            if (this.j || !c.a.b0.i.b.a(j)) {
                return;
            }
            c.a.b0.j.d.a(this.i, j);
            a();
        }

        @Override // f.a.c
        public void cancel() {
            if (this.f1851f) {
                return;
            }
            this.f1851f = true;
            this.f1850e.cancel();
            if (getAndIncrement() == 0) {
                this.f1847b.clear();
            }
        }

        @Override // c.a.b0.c.j
        public void clear() {
            this.f1847b.clear();
        }

        @Override // c.a.b0.c.j
        public boolean isEmpty() {
            return this.f1847b.isEmpty();
        }

        @Override // f.a.b
        public void onComplete() {
            this.g = true;
            if (this.j) {
                this.f1846a.onComplete();
            } else {
                a();
            }
        }

        @Override // f.a.b
        public void onError(Throwable th) {
            this.h = th;
            this.g = true;
            if (this.j) {
                this.f1846a.onError(th);
            } else {
                a();
            }
        }

        @Override // f.a.b
        public void onNext(T t) {
            if (this.f1847b.offer(t)) {
                if (this.j) {
                    this.f1846a.onNext(null);
                    return;
                } else {
                    a();
                    return;
                }
            }
            this.f1850e.cancel();
            c.a.z.c cVar = new c.a.z.c("Buffer is full");
            try {
                this.f1849d.run();
            } catch (Throwable th) {
                c.a.z.b.b(th);
                cVar.initCause(th);
            }
            onError(cVar);
        }

        @Override // c.a.b0.c.j
        public T poll() {
            return this.f1847b.poll();
        }

        void a() {
            if (getAndIncrement() == 0) {
                i<T> iVar = this.f1847b;
                f.a.b<? super T> bVar = this.f1846a;
                int iAddAndGet = 1;
                while (!a(this.g, iVar.isEmpty(), bVar)) {
                    long j = this.i.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z = this.g;
                        T tPoll = iVar.poll();
                        boolean z2 = tPoll == null;
                        if (a(z, z2, bVar)) {
                            return;
                        }
                        if (z2) {
                            break;
                        }
                        bVar.onNext(tPoll);
                        j2++;
                    }
                    if (j2 == j && a(this.g, iVar.isEmpty(), bVar)) {
                        return;
                    }
                    if (j2 != 0 && j != Long.MAX_VALUE) {
                        this.i.addAndGet(-j2);
                    }
                    iAddAndGet = addAndGet(-iAddAndGet);
                    if (iAddAndGet == 0) {
                        return;
                    }
                }
            }
        }

        boolean a(boolean z, boolean z2, f.a.b<? super T> bVar) {
            if (this.f1851f) {
                this.f1847b.clear();
                return true;
            }
            if (!z) {
                return false;
            }
            if (this.f1848c) {
                if (!z2) {
                    return false;
                }
                Throwable th = this.h;
                if (th != null) {
                    bVar.onError(th);
                } else {
                    bVar.onComplete();
                }
                return true;
            }
            Throwable th2 = this.h;
            if (th2 != null) {
                this.f1847b.clear();
                bVar.onError(th2);
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
