package e3;

import java.net.Proxy;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a3.a f827a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a3.h f828b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final boolean f829c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final List f830d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f831e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f832f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f833g;

    public x(a3.a aVar, a3.h hVar, p pVar, boolean z3) {
        List listK;
        j2.i.e(hVar, "routeDatabase");
        this.f827a = aVar;
        this.f828b = hVar;
        this.f829c = z3;
        v1.p pVar2 = v1.p.f2517d;
        this.f830d = pVar2;
        this.f832f = pVar2;
        this.f833g = new ArrayList();
        a3.t tVar = aVar.f58h;
        j2.i.e(tVar, "url");
        URI uriG = tVar.g();
        if (uriG.getHost() == null) {
            listK = b3.g.k(new Proxy[]{Proxy.NO_PROXY});
        } else {
            List<Proxy> listSelect = aVar.f57g.select(uriG);
            listK = (listSelect == null || listSelect.isEmpty()) ? b3.g.k(new Proxy[]{Proxy.NO_PROXY}) : b3.g.j(listSelect);
        }
        this.f830d = listK;
        this.f831e = 0;
    }

    public final boolean a() {
        return this.f831e < this.f830d.size() || !this.f833g.isEmpty();
    }
}
