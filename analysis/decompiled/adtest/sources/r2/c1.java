package r2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class c1 extends a2.h implements i2.p, j2.g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f1963e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public f1 f1964f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public m f1965g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1966h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f1967i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ d1 f1968j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c1(d1 d1Var, y1.c cVar) {
        super(cVar);
        this.f1968j = d1Var;
        this.f1963e = 2;
    }

    @Override // j2.g
    public final int b() {
        return this.f1963e;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((c1) i((o2.d) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        c1 c1Var = new c1(this.f1968j, cVar);
        c1Var.f1967i = obj;
        return c1Var;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0068  */
    /* JADX WARN: Code duplicated, block: B:23:0x006c  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x006a -> B:25:0x007f). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // a2.a
    public final java.lang.Object l(java.lang.Object r7) {
        /*
            r6 = this;
            int r0 = r6.f1966h
            r1 = 3
            r2 = 2
            r3 = 1
            z1.a r4 = z1.a.f2781d
            if (r0 == 0) goto L25
            if (r0 == r3) goto L21
            if (r0 != r2) goto L19
            r2.m r0 = r6.f1965g
            r2.f1 r3 = r6.f1964f
            java.lang.Object r5 = r6.f1967i
            o2.d r5 = (o2.d) r5
            d0.l0.M(r7)
            goto L7f
        L19:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L21:
            d0.l0.M(r7)
            goto L84
        L25:
            d0.l0.M(r7)
            java.lang.Object r7 = r6.f1967i
            o2.d r7 = (o2.d) r7
            r2.d1 r0 = r6.f1968j
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = r2.d1.f1971d
            java.lang.Object r0 = r5.get(r0)
            boolean r5 = r0 instanceof r2.m
            if (r5 == 0) goto L45
            r2.m r0 = (r2.m) r0
            r2.d1 r0 = r0.f1999h
            r6.f1966h = r3
            r7.f1565e = r0
            r7.f1564d = r1
            r7.f1566f = r6
            return r4
        L45:
            boolean r3 = r0 instanceof r2.s0
            if (r3 == 0) goto L84
            r2.s0 r0 = (r2.s0) r0
            r2.f1 r0 = r0.d()
            if (r0 == 0) goto L84
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = w2.j.f2633d
            java.lang.Object r3 = r3.get(r0)
            java.lang.String r5 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode"
            j2.i.c(r3, r5)
            w2.j r3 = (w2.j) r3
            r5 = r3
            r3 = r0
            r0 = r5
            r5 = r7
        L62:
            boolean r7 = r0.equals(r3)
            if (r7 != 0) goto L84
            boolean r7 = r0 instanceof r2.m
            if (r7 == 0) goto L7f
            r2.m r0 = (r2.m) r0
            r2.d1 r7 = r0.f1999h
            r6.f1967i = r5
            r6.f1964f = r3
            r6.f1965g = r0
            r6.f1966h = r2
            r5.f1565e = r7
            r5.f1564d = r1
            r5.f1566f = r6
            return r4
        L7f:
            w2.j r0 = r0.h()
            goto L62
        L84:
            u1.k r7 = u1.k.f2301a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: r2.c1.l(java.lang.Object):java.lang.Object");
    }

    @Override // a2.a
    public final String toString() {
        if (this.f40d != null) {
            return super.toString();
        }
        j2.o.f1277a.getClass();
        String strA = j2.p.a(this);
        j2.i.d(strA, "renderLambdaToString(...)");
        return strA;
    }
}
