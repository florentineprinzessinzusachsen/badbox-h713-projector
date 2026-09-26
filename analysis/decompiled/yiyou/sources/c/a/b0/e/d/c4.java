package c.a.b0.e.d;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: ObservableUsing.java */
/* JADX INFO: loaded from: classes.dex */
public final class c4<T, D> extends c.a.l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Callable<? extends D> f2021a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final c.a.a0.n<? super D, ? extends c.a.q<? extends T>> f2022b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final c.a.a0.f<? super D> f2023c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f2024d;

    /* JADX INFO: compiled from: ObservableUsing.java */
    static final class a<T, D> extends AtomicBoolean implements c.a.s<T>, c.a.y.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super T> f2025a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final D f2026b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final c.a.a0.f<? super D> f2027c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f2028d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c.a.y.b f2029e;

        a(c.a.s<? super T> sVar, D d2, c.a.a0.f<? super D> fVar, boolean z) {
            this.f2025a = sVar;
            this.f2026b = d2;
            this.f2027c = fVar;
            this.f2028d = z;
        }

        void a() {
            if (compareAndSet(false, true)) {
                try {
                    this.f2027c.a(this.f2026b);
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    c.a.e0.a.b(th);
                }
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            a();
            this.f2029e.dispose();
        }

        @Override // c.a.s
        public void onComplete() {
            if (!this.f2028d) {
                this.f2025a.onComplete();
                this.f2029e.dispose();
                a();
                return;
            }
            if (compareAndSet(false, true)) {
                try {
                    this.f2027c.a(this.f2026b);
                } catch (Throwable th) {
                    c.a.z.b.b(th);
                    this.f2025a.onError(th);
                    return;
                }
            }
            this.f2029e.dispose();
            this.f2025a.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            if (!this.f2028d) {
                this.f2025a.onError(th);
                this.f2029e.dispose();
                a();
                return;
            }
            if (compareAndSet(false, true)) {
                try {
                    this.f2027c.a(this.f2026b);
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    th = new c.a.z.a(th, th2);
                }
            }
            this.f2029e.dispose();
            this.f2025a.onError(th);
        }

        @Override // c.a.s
        public void onNext(T t) {
            this.f2025a.onNext(t);
        }

        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.f2029e, bVar)) {
                this.f2029e = bVar;
                this.f2025a.onSubscribe(this);
            }
        }
    }

    public c4(Callable<? extends D> callable, c.a.a0.n<? super D, ? extends c.a.q<? extends T>> nVar, c.a.a0.f<? super D> fVar, boolean z) {
        this.f2021a = callable;
        this.f2022b = nVar;
        this.f2023c = fVar;
        this.f2024d = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super T> sVar) {
        try {
            D dCall = this.f2021a.call();
            try {
                c.a.q<? extends T> qVarApply = this.f2022b.apply(dCall);
                c.a.b0.b.b.a(qVarApply, "The sourceSupplier returned a null ObservableSource");
                qVarApply.subscribe(new a(sVar, dCall, this.f2023c, this.f2024d));
            } catch (Throwable th) {
                c.a.z.b.b(th);
                try {
                    this.f2023c.a(dCall);
                    c.a.b0.a.d.a(th, sVar);
                } catch (Throwable th2) {
                    c.a.z.b.b(th2);
                    c.a.b0.a.d.a(new c.a.z.a(th, th2), sVar);
                }
            }
        } catch (Throwable th3) {
            c.a.z.b.b(th3);
            c.a.b0.a.d.a(th3, sVar);
        }
    }
}
