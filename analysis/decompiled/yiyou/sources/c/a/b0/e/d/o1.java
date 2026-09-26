package c.a.b0.e.d;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: ObservableInterval.java */
/* JADX INFO: loaded from: classes.dex */
public final class o1 extends c.a.l<Long> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final c.a.t f2516a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final long f2517b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final long f2518c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final TimeUnit f2519d;

    /* JADX INFO: compiled from: ObservableInterval.java */
    static final class a extends AtomicReference<c.a.y.b> implements c.a.y.b, Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final c.a.s<? super Long> f2520a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f2521b;

        a(c.a.s<? super Long> sVar) {
            this.f2520a = sVar;
        }

        public void a(c.a.y.b bVar) {
            c.a.b0.a.c.c(this, bVar);
        }

        @Override // c.a.y.b
        public void dispose() {
            c.a.b0.a.c.a((AtomicReference<c.a.y.b>) this);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() != c.a.b0.a.c.DISPOSED) {
                c.a.s<? super Long> sVar = this.f2520a;
                long j = this.f2521b;
                this.f2521b = 1 + j;
                sVar.onNext(Long.valueOf(j));
            }
        }
    }

    public o1(long j, long j2, TimeUnit timeUnit, c.a.t tVar) {
        this.f2517b = j;
        this.f2518c = j2;
        this.f2519d = timeUnit;
        this.f2516a = tVar;
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
        a aVar = new a(sVar);
        sVar.onSubscribe(aVar);
        c.a.t tVar = this.f2516a;
        if (!(tVar instanceof c.a.b0.g.o)) {
            aVar.a(tVar.a(aVar, this.f2517b, this.f2518c, this.f2519d));
            return;
        }
        c.a.t.c cVarA = tVar.a();
        aVar.a(cVarA);
        cVarA.a(aVar, this.f2517b, this.f2518c, this.f2519d);
    }
}
