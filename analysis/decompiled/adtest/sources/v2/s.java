package v2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class s implements y1.c, a2.d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final y1.c f2565d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y1.h f2566e;

    public s(y1.c cVar, y1.h hVar) {
        this.f2565d = cVar;
        this.f2566e = hVar;
    }

    @Override // a2.d
    public final a2.d e() {
        y1.c cVar = this.f2565d;
        if (cVar instanceof a2.d) {
            return (a2.d) cVar;
        }
        return null;
    }

    @Override // y1.c
    public final y1.h g() {
        return this.f2566e;
    }

    @Override // y1.c
    public final void j(Object obj) {
        this.f2565d.j(obj);
    }
}
