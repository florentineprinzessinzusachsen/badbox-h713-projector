package v2;

import r2.v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public t2.i f2543h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public byte[] f2544i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2545j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2546k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2547l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f2548m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ u2.g[] f2549n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ h0.m f2550o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ h0.n f2551p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ u2.h f2552q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public i(u2.g[] gVarArr, h0.m mVar, h0.n nVar, u2.h hVar, y1.c cVar) {
        super(2, cVar);
        this.f2549n = gVarArr;
        this.f2550o = mVar;
        this.f2551p = nVar;
        this.f2552q = hVar;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        return ((i) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        i iVar = new i(this.f2549n, this.f2550o, this.f2551p, this.f2552q, cVar);
        iVar.f2548m = obj;
        return iVar;
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:39:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ba A[DONT_INVERT, EDGE_INSN: B:42:0x00ba->B:20:0x0077 BREAK  A[LOOP:0: B:31:0x0099->B:48:?]] */
    /* JADX WARN: Code duplicated, block: B:43:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:47:0x00ba A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:48:? A[LOOP:0: B:31:0x0099->B:48:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00dc -> B:20:0x0077). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // a2.a
    public final java.lang.Object l(java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: v2.i.l(java.lang.Object):java.lang.Object");
    }
}
