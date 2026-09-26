package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableIntervalRange.java */
/* JADX INFO: loaded from: classes.dex */
public final class p1 extends c.a.l<Long> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.t f2548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2550c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final long f2551d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final long f2552e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    final TimeUnit f2553f;

    /* JADX INFO: compiled from: ObservableIntervalRange.java */
    static final class a extends AtomicReference<c.a.y.b> implements c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Long> f2554a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final long f2555b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        long f2556c;

        a(c.a.s<? super Long> sVar, long j, long j2) {
            this.f2554a = sVar;
            this.f2556c = j;
            this.f2555b = j2;
        }

        public boolean a() {
            return get() == c.a.b0.a.c.DISPOSED;
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (a()) {
                return;
            }
            long j = this.f2556c;
            this.f2554a.onNext(Long.valueOf(j));
            if (j != this.f2555b) {
                this.f2556c = j + 1;
            } else {
                c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
                this.f2554a.onComplete();
            }
        }

        public void a(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }
    }

    public p1(long j, long j2, long j3, long j4, TimeUnit timeUnit, c.a.t tVar) {
        this.f2551d = j3;
        this.f2552e = j4;
        this.f2553f = timeUnit;
        this.f2548a = tVar;
        this.f2549b = j;
        this.f2550c = j2;
    }

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
    @Override // c.a.l
    public void subscribeActual(c.a.s<? super Long> sVar) {
        a aVar = new a(sVar, this.f2549b, this.f2550c);
        sVar.onSubscribe(aVar);
        c.a.t tVar = this.f2548a;
        if (!(tVar instanceof c.a.b0.g.o)) {
            aVar.a(tVar.a(aVar, this.f2551d, this.f2552e, this.f2553f));
            return;
        }
        c.a.t.c cVarA = tVar.a();
        aVar.a(cVarA);
        cVarA.a(aVar, this.f2551d, this.f2552e, this.f2553f);
    }
}
