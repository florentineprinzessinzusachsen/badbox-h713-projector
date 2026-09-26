package c.a.b0.e.d;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableWindowTimed.java */
/* JADX INFO: loaded from: classes.dex */
public final class h4<T> extends c.a.b0.e.d.a<T, c.a.l<T>> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final TimeUnit f2242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final c.a.t f2243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final long f2244f;
    final int g;
    final boolean h;

    /* JADX INFO: compiled from: ObservableWindowTimed.java */
    static final class a<T> extends c.a.b0.d.p<T, Object, c.a.l<T>> implements c.a.y.b {
        final long g;
        final TimeUnit h;
        final c.a.t i;
        final int j;
        final boolean k;
        final long l;
        final c.a.t.c m;
        long n;
        long o;
        c.a.y.b p;
        c.a.g0.d<T> q;
        volatile boolean r;
        final AtomicReference<c.a.y.b> s;

        /* JADX INFO: renamed from: c.a.b0.e.d.h4$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: ObservableWindowTimed.java */
        static final class RunnableC0059a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final long f2245a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final a<?> f2246b;

            RunnableC0059a(long j, a<?> aVar) {
                this.f2245a = j;
                this.f2246b = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a<?> aVar = this.f2246b;
                if (((c.a.b0.d.p) aVar).f1834d) {
                    aVar.r = true;
                    aVar.f();
                } else {
                    ((c.a.b0.d.p) aVar).f1833c.offer(this);
                }
                if (aVar.d()) {
                    aVar.g();
                }
            }
        }

        a(c.a.s<? super c.a.l<T>> sVar, long j, TimeUnit timeUnit, c.a.t tVar, int i, long j2, boolean z) {
            super(sVar, new c.a.b0.f.a());
            this.s = new AtomicReference<>();
            this.g = j;
            this.h = timeUnit;
            this.i = tVar;
            this.j = i;
            this.l = j2;
            this.k = z;
            if (z) {
                this.m = tVar.a();
            } else {
                this.m = null;
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1834d = true;
        }

        void f() {
            c.a.b0.a.c.a(this.s);
            c.a.t.c cVar = this.m;
            if (cVar != null) {
                cVar.dispose();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [c.a.s, c.a.s<? super V>] */
        /* JADX WARN: Type inference failed for: r4v8, types: [c.a.g0.d] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        void g() {
            c.a.b0.f.a aVar = (c.a.b0.f.a) this.f1833c;
            ?? r1 = this.f1832b;
            c.a.g0.d dVar = this.q;
            int iA = 1;
            while (!this.r) {
                boolean z = this.f1835e;
                Object objPoll = aVar.poll();
                boolean z2 = objPoll == null;
                boolean z3 = objPoll instanceof RunnableC0059a;
                if (z && (z2 || z3)) {
                    this.q = null;
                    aVar.clear();
                    f();
                    Throwable th = this.f1836f;
                    if (th != null) {
                        dVar.onError(th);
                        return;
                    } else {
                        dVar.onComplete();
                        return;
                    }
                }
                if (z2) {
                    iA = a(-iA);
                    if (iA == 0) {
                        return;
                    }
                } else if (z3) {
                    RunnableC0059a runnableC0059a = (RunnableC0059a) objPoll;
                    if (this.k || this.o == runnableC0059a.f2245a) {
                        dVar.onComplete();
                        this.n = 0L;
                        dVar = (c.a.g0.d<T>) c.a.g0.d.a(this.j);
                        this.q = dVar;
                        r1.onNext(dVar);
                    }
                } else {
                    c.a.b0.j.n.b(objPoll);
                    dVar.onNext(objPoll);
                    long j = this.n + 1;
                    if (j >= this.l) {
                        this.o++;
                        this.n = 0L;
                        dVar.onComplete();
                        dVar = (c.a.g0.d<T>) c.a.g0.d.a(this.j);
                        this.q = dVar;
                        this.f1832b.onNext(dVar);
                        if (this.k) {
                            c.a.y.b bVar = this.s.get();
                            bVar.dispose();
                            c.a.t.c cVar = this.m;
                            RunnableC0059a runnableC0059a2 = new RunnableC0059a(this.o, this);
                            long j2 = this.g;
                            c.a.y.b bVarA = cVar.a(runnableC0059a2, j2, j2, this.h);
                            if (!this.s.compareAndSet(bVar, bVarA)) {
                                bVarA.dispose();
                            }
                        }
                    } else {
                        this.n = j;
                    }
                }
            }
            this.p.dispose();
            aVar.clear();
            f();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1835e = true;
            if (d()) {
                g();
            }
            this.f1832b.onComplete();
            f();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1836f = th;
            this.f1835e = true;
            if (d()) {
                g();
            }
            this.f1832b.onError(th);
            f();
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            if (this.r) {
                return;
            }
            if (e()) {
                c.a.g0.d<T> dVar = this.q;
                dVar.onNext(t);
                long j = this.n + 1;
                if (j >= this.l) {
                    this.o++;
                    this.n = 0L;
                    dVar.onComplete();
                    c.a.g0.d<T> dVarA = c.a.g0.d.a(this.j);
                    this.q = dVarA;
                    this.f1832b.onNext(dVarA);
                    if (this.k) {
                        this.s.get().dispose();
                        c.a.t.c cVar = this.m;
                        RunnableC0059a runnableC0059a = new RunnableC0059a(this.o, this);
                        long j2 = this.g;
                        c.a.b0.a.c.a(this.s, cVar.a(runnableC0059a, j2, j2, this.h));
                    }
                } else {
                    this.n = j;
                }
                if (a(-1) == 0) {
                    return;
                }
            } else {
                c.a.b0.c.i<U> iVar = this.f1833c;
                c.a.b0.j.n.e(t);
                iVar.offer((U) t);
                if (!d()) {
                    return;
                }
            }
            g();
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            c.a.y.b bVarA;
            if (c.a.b0.a.c.a(this.p, bVar)) {
                this.p = bVar;
                c.a.s<? super V> sVar = this.f1832b;
                sVar.onSubscribe(this);
                if (this.f1834d) {
                    return;
                }
                c.a.g0.d<T> dVarA = c.a.g0.d.a(this.j);
                this.q = dVarA;
                sVar.onNext(dVarA);
                RunnableC0059a runnableC0059a = new RunnableC0059a(this.o, this);
                if (this.k) {
                    c.a.t.c cVar = this.m;
                    long j = this.g;
                    bVarA = cVar.a(runnableC0059a, j, j, this.h);
                } else {
                    c.a.t tVar = this.i;
                    long j2 = this.g;
                    bVarA = tVar.a(runnableC0059a, j2, j2, this.h);
                }
                c.a.b0.a.c.a(this.s, bVarA);
            }
        }
    }

    /* JADX INFO: compiled from: ObservableWindowTimed.java */
    static final class b<T> extends c.a.b0.d.p<T, Object, c.a.l<T>> implements c.a.s<T>, c.a.y.b, Runnable {
        static final Object o = new Object();
        final long g;
        final TimeUnit h;
        final c.a.t i;
        final int j;
        c.a.y.b k;
        c.a.g0.d<T> l;
        final AtomicReference<c.a.y.b> m;
        volatile boolean n;

        b(c.a.s<? super c.a.l<T>> sVar, long j, TimeUnit timeUnit, c.a.t tVar, int i) {
            super(sVar, new c.a.b0.f.a());
            this.m = new AtomicReference<>();
            this.g = j;
            this.h = timeUnit;
            this.i = tVar;
            this.j = i;
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1834d = true;
        }

        void f() {
            c.a.b0.a.c.a(this.m);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0, types: [c.a.g0.d<T>] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        void g() {
            c.a.b0.f.a aVar = (c.a.b0.f.a) this.f1833c;
            c.a.s<? super V> sVar = this.f1832b;
            c.a.g0.d<T> dVar = this.l;
            int iA = 1;
            while (true) {
                boolean z = this.n;
                boolean z2 = this.f1835e;
                Object objPoll = aVar.poll();
                if (z2 && (objPoll == null || objPoll == o)) {
                    break;
                }
                if (objPoll == null) {
                    iA = a(-iA);
                    if (iA == 0) {
                        return;
                    }
                } else if (objPoll == o) {
                    dVar.onComplete();
                    if (z) {
                        this.k.dispose();
                    } else {
                        dVar = (c.a.g0.d<T>) c.a.g0.d.a(this.j);
                        this.l = dVar;
                        sVar.onNext(dVar);
                    }
                } else {
                    c.a.b0.j.n.b(objPoll);
                    dVar.onNext(objPoll);
                }
            }
            this.l = null;
            aVar.clear();
            f();
            Throwable th = this.f1836f;
            if (th != null) {
                dVar.onError(th);
            } else {
                dVar.onComplete();
            }
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1835e = true;
            if (d()) {
                g();
            }
            f();
            this.f1832b.onComplete();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1836f = th;
            this.f1835e = true;
            if (d()) {
                g();
            }
            f();
            this.f1832b.onError(th);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            if (this.n) {
                return;
            }
            if (e()) {
                this.l.onNext(t);
                if (a(-1) == 0) {
                    return;
                }
            } else {
                c.a.b0.c.i<U> iVar = this.f1833c;
                c.a.b0.j.n.e(t);
                iVar.offer((U) t);
                if (!d()) {
                    return;
                }
            }
            g();
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.k, bVar)) {
                this.k = bVar;
                this.l = c.a.g0.d.a(this.j);
                c.a.s<? super V> sVar = this.f1832b;
                sVar.onSubscribe(this);
                sVar.onNext(this.l);
                if (this.f1834d) {
                    return;
                }
                c.a.t tVar = this.i;
                long j = this.g;
                c.a.b0.a.c.a(this.m, tVar.a(this, j, j, this.h));
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // java.lang.Runnable
        public void run() {
            if (this.f1834d) {
                this.n = true;
                f();
            }
            this.f1833c.offer((U) o);
            if (d()) {
                g();
            }
        }
    }

    /* JADX INFO: compiled from: ObservableWindowTimed.java */
    static final class c<T> extends c.a.b0.d.p<T, Object, c.a.l<T>> implements c.a.y.b, Runnable {
        final long g;
        final long h;
        final TimeUnit i;
        final c.a.t.c j;
        final int k;
        final List<c.a.g0.d<T>> l;
        c.a.y.b m;
        volatile boolean n;

        /* JADX INFO: compiled from: ObservableWindowTimed.java */
        final class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private final c.a.g0.d<T> f2247a;

            a(c.a.g0.d<T> dVar) {
                this.f2247a = dVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.a(this.f2247a);
            }
        }

        /* JADX INFO: compiled from: ObservableWindowTimed.java */
        static final class b<T> {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final c.a.g0.d<T> f2249a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final boolean f2250b;

            b(c.a.g0.d<T> dVar, boolean z) {
                this.f2249a = dVar;
                this.f2250b = z;
            }
        }

        c(c.a.s<? super c.a.l<T>> sVar, long j, long j2, TimeUnit timeUnit, c.a.t.c cVar, int i) {
            super(sVar, new c.a.b0.f.a());
            this.g = j;
            this.h = j2;
            this.i = timeUnit;
            this.j = cVar;
            this.k = i;
            this.l = new LinkedList();
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        void a(c.a.g0.d<T> dVar) {
            this.f1833c.offer((U) new b(dVar, false));
            if (d()) {
                g();
            }
        }

        @Override // c.a.y.b
        public void dispose() {
            this.f1834d = true;
        }

        void f() {
            this.j.dispose();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        void g() {
            c.a.b0.f.a aVar = (c.a.b0.f.a) this.f1833c;
            c.a.s<? super V> sVar = this.f1832b;
            List<c.a.g0.d<T>> list = this.l;
            int iA = 1;
            while (!this.n) {
                boolean z = this.f1835e;
                Object objPoll = aVar.poll();
                boolean z2 = objPoll == null;
                boolean z3 = objPoll instanceof b;
                if (z && (z2 || z3)) {
                    aVar.clear();
                    Throwable th = this.f1836f;
                    if (th != null) {
                        Iterator<c.a.g0.d<T>> it = list.iterator();
                        while (it.hasNext()) {
                            it.next().onError(th);
                        }
                    } else {
                        Iterator<c.a.g0.d<T>> it2 = list.iterator();
                        while (it2.hasNext()) {
                            it2.next().onComplete();
                        }
                    }
                    f();
                    list.clear();
                    return;
                }
                if (z2) {
                    iA = a(-iA);
                    if (iA == 0) {
                        return;
                    }
                } else if (z3) {
                    b bVar = (b) objPoll;
                    if (!bVar.f2250b) {
                        list.remove(bVar.f2249a);
                        bVar.f2249a.onComplete();
                        if (list.isEmpty() && this.f1834d) {
                            this.n = true;
                        }
                    } else if (!this.f1834d) {
                        c.a.g0.d<T> dVarA = c.a.g0.d.a(this.k);
                        list.add(dVarA);
                        sVar.onNext(dVarA);
                        this.j.a(new a(dVarA), this.g, this.i);
                    }
                } else {
                    Iterator<c.a.g0.d<T>> it3 = list.iterator();
                    while (it3.hasNext()) {
                        it3.next().onNext(objPoll);
                    }
                }
            }
            this.m.dispose();
            f();
            aVar.clear();
            list.clear();
        }

        @Override // c.a.s
        public void onComplete() {
            this.f1835e = true;
            if (d()) {
                g();
            }
            this.f1832b.onComplete();
            f();
        }

        @Override // c.a.s
        public void onError(Throwable th) {
            this.f1836f = th;
            this.f1835e = true;
            if (d()) {
                g();
            }
            this.f1832b.onError(th);
            f();
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onNext(T t) {
            if (e()) {
                Iterator<c.a.g0.d<T>> it = this.l.iterator();
                while (it.hasNext()) {
                    it.next().onNext(t);
                }
                if (a(-1) == 0) {
                    return;
                }
            } else {
                this.f1833c.offer((U) t);
                if (!d()) {
                    return;
                }
            }
            g();
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // c.a.s
        public void onSubscribe(c.a.y.b bVar) {
            if (c.a.b0.a.c.a(this.m, bVar)) {
                this.m = bVar;
                this.f1832b.onSubscribe(this);
                if (this.f1834d) {
                    return;
                }
                c.a.g0.d<T> dVarA = c.a.g0.d.a(this.k);
                this.l.add(dVarA);
                this.f1832b.onNext(dVarA);
                this.j.a(new a(dVarA), this.g, this.i);
                c.a.t.c cVar = this.j;
                long j = this.h;
                cVar.a(this, j, j, this.i);
            }
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // java.lang.Runnable
        public void run() {
            Object bVar = new b(c.a.g0.d.a(this.k), true);
            if (!this.f1834d) {
                this.f1833c.offer((U) bVar);
            }
            if (d()) {
                g();
            }
        }
    }

    public h4(c.a.q<T> qVar, long j, long j2, TimeUnit timeUnit, c.a.t tVar, long j3, int i, boolean z) {
        super(qVar);
        this.f2240b = j;
        this.f2241c = j2;
        this.f2242d = timeUnit;
        this.f2243e = tVar;
        this.f2244f = j3;
        this.g = i;
        this.h = z;
    }

    @Override // c.a.l
    public void subscribeActual(c.a.s<? super c.a.l<T>> sVar) {
        c.a.d0.f fVar = new c.a.d0.f(sVar);
        long j = this.f2240b;
        long j2 = this.f2241c;
        if (j != j2) {
            this.f1932a.subscribe(new c(fVar, j, j2, this.f2242d, this.f2243e.a(), this.g));
            return;
        }
        long j3 = this.f2244f;
        if (j3 == Long.MAX_VALUE) {
            this.f1932a.subscribe(new b(fVar, j, this.f2242d, this.f2243e, this.g));
        } else {
            this.f1932a.subscribe(new a(fVar, j, this.f2242d, this.f2243e, this.g, j3, this.h));
        }
    }
}
