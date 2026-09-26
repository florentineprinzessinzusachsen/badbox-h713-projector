package r;

import p.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o extends a2.c {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public s f1916g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public x f1917h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f f1918i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public /* synthetic */ Object f1919j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ s f1920k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f1921l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public o(s sVar, a2.c cVar) {
        super(cVar);
        this.f1920k = sVar;
    }

    @Override // a2.a
    public final Object l(Object obj) {
        this.f1919j = obj;
        this.f1921l |= Integer.MIN_VALUE;
        return this.f1920k.e(null, this);
    }
}
