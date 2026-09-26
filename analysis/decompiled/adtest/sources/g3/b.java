package g3;

import a3.r;
import a3.t;
import a3.x;
import java.io.IOException;
import q3.i;
import q3.o;
import q3.u;
import q3.w;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements u {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final t f961d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i f962e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f963f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ h f964g;

    public b(h hVar, t tVar) {
        j2.i.e(tVar, "url");
        this.f964g = hVar;
        this.f961d = tVar;
        this.f962e = new i(((o) hVar.f980c.f46f).f1844d.f());
    }

    public final void b(r rVar) {
        x xVar;
        a3.b bVar;
        j2.i.e(rVar, "trailers");
        h hVar = this.f964g;
        int i4 = hVar.f981d;
        if (i4 == 6) {
            return;
        }
        if (i4 != 5) {
            throw new IllegalStateException("state: " + hVar.f981d);
        }
        i iVar = this.f962e;
        w wVar = iVar.f1827e;
        iVar.f1827e = w.f1859d;
        wVar.a();
        wVar.b();
        hVar.f981d = 6;
        if (rVar.size() <= 0 || (xVar = hVar.f978a) == null || (bVar = xVar.f253j) == null) {
            return;
        }
        f3.h.b(bVar, this.f961d, rVar);
    }

    @Override // q3.u
    public final w f() {
        return this.f962e;
    }

    @Override // q3.u
    public long g(long j4, q3.e eVar) throws IOException {
        h hVar = this.f964g;
        j2.i.e(eVar, "sink");
        try {
            return ((o) hVar.f980c.f46f).g(j4, eVar);
        } catch (IOException e4) {
            hVar.f979b.g();
            b(h.f977f);
            throw e4;
        }
    }
}
