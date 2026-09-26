package p;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class h0 extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public k[] f1652h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public i0 f1653i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public y f1654j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f1655k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1656l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f1657m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f1658n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ k[] f1659o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ i0 f1660p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ y f1661q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h0(k[] kVarArr, i0 i0Var, y yVar, y1.c cVar) {
        super(2, cVar);
        this.f1659o = kVarArr;
        this.f1660p = i0Var;
        this.f1661q = yVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((h0) i((r.m) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        return new h0(this.f1659o, this.f1660p, this.f1661q, cVar);
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0032  */
    /* JADX WARN: Code duplicated, block: B:13:0x003c  */
    /* JADX WARN: Code duplicated, block: B:15:0x0040 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:16:0x0042  */
    /* JADX WARN: Code duplicated, block: B:19:0x0057  */
    /* JADX WARN: Code duplicated, block: B:21:0x005a  */
    /* JADX WARN: Code duplicated, block: B:23:0x0060  */
    /* JADX WARN: Code duplicated, block: B:26:0x0075  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0075 -> B:27:0x0076). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // a2.a
    public final java.lang.Object l(java.lang.Object r11) {
        /*
            r10 = this;
            int r0 = r10.f1658n
            r1 = 2
            r2 = 1
            if (r0 == 0) goto L22
            if (r0 == r2) goto La
            if (r0 != r1) goto L1a
        La:
            int r0 = r10.f1657m
            int r3 = r10.f1656l
            int r4 = r10.f1655k
            p.y r5 = r10.f1654j
            p.i0 r6 = r10.f1653i
            p.k[] r7 = r10.f1652h
            d0.l0.M(r11)
            goto L58
        L1a:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L22:
            d0.l0.M(r11)
            p.k[] r11 = r10.f1659o
            int r0 = r11.length
            r3 = 0
            p.i0 r4 = r10.f1660p
            p.y r5 = r10.f1661q
            r7 = r11
            r11 = r3
            r6 = r4
        L30:
            if (r3 >= r0) goto L78
            r4 = r7[r3]
            int r8 = r11 + 1
            int r4 = r4.ordinal()
            if (r4 == 0) goto L75
            z1.a r9 = z1.a.f2781d
            if (r4 == r2) goto L60
            if (r4 != r1) goto L5a
            r10.f1652h = r7
            r10.f1653i = r6
            r10.f1654j = r5
            r10.f1655k = r8
            r10.f1656l = r3
            r10.f1657m = r0
            r10.f1658n = r1
            java.lang.Object r11 = p.i0.d(r6, r5, r11, r10)
            if (r11 != r9) goto L57
            goto L74
        L57:
            r4 = r8
        L58:
            r11 = r4
            goto L76
        L5a:
            a0.c r11 = new a0.c
            r11.<init>()
            throw r11
        L60:
            r10.f1652h = r7
            r10.f1653i = r6
            r10.f1654j = r5
            r10.f1655k = r8
            r10.f1656l = r3
            r10.f1657m = r0
            r10.f1658n = r2
            java.lang.Object r11 = p.i0.c(r6, r5, r11, r10)
            if (r11 != r9) goto L57
        L74:
            return r9
        L75:
            r11 = r8
        L76:
            int r3 = r3 + r2
            goto L30
        L78:
            u1.k r11 = u1.k.f2301a
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: p.h0.l(java.lang.Object):java.lang.Object");
    }
}
