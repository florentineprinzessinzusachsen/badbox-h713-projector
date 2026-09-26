package w2;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public class q extends r2.a implements a2.d {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final y1.c f2647g;

    public q(y1.c cVar, y1.h hVar) {
        super(hVar, true);
        this.f2647g = cVar;
    }

    @Override // r2.d1
    public final boolean L() {
        return true;
    }

    @Override // a2.d
    public final a2.d e() {
        y1.c cVar = this.f2647g;
        if (cVar instanceof a2.d) {
            return (a2.d) cVar;
        }
        return null;
    }

    @Override // r2.d1
    public void p(Object obj) {
        a.h(r2.x.q(obj), z1.d.a(this.f2647g));
    }

    @Override // r2.d1
    public void q(Object obj) {
        this.f2647g.j(r2.x.q(obj));
    }
}
