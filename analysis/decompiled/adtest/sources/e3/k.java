package e3;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k extends d3.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ v f754e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ l f755f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k(String str, v vVar, l lVar) {
        super(str);
        this.f754e = vVar;
        this.f755f = lVar;
    }

    @Override // d3.a
    public final long a() throws InterruptedException {
        u uVar;
        v vVar = this.f754e;
        try {
            uVar = vVar.d();
        } catch (Throwable th) {
            uVar = new u(vVar, th, 2);
        }
        l lVar = this.f755f;
        if (!lVar.f760h.contains(vVar)) {
            return -1L;
        }
        lVar.f761i.put(uVar);
        return -1L;
    }
}
