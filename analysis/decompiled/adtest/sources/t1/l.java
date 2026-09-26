package t1;

import a3.x;
import com.speed.ad.bean.PluginInfoEntity;
import com.speed.net.ApiResponse;
import h1.c0;
import java.util.Map;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f2151d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ApiResponse f2152e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f2153f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ u1.a f2154g;

    public /* synthetic */ l(ApiResponse apiResponse, String str, u1.a aVar, int i4) {
        this.f2151d = i4;
        this.f2152e = apiResponse;
        this.f2153f = str;
        this.f2154g = aVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        o oVar;
        PluginInfoEntity pluginInfoEntity;
        switch (this.f2151d) {
            case 0:
                if (this.f2152e.getResult() != null) {
                    oVar = o.f2158a;
                    synchronized (oVar) {
                        c0.f1036a.getClass();
                        Map mapE = c0.e();
                        mapE.clear();
                        c0.o(mapE);
                        break;
                    }
                } else {
                    if (this.f2152e.getData() == null) {
                        try {
                            synchronized (o.f2158a) {
                                c0.f1036a.getClass();
                                Map mapE2 = c0.e();
                                mapE2.clear();
                                c0.o(mapE2);
                            }
                            ((i2.l) this.f2154g).h(Boolean.TRUE);
                            return;
                        } catch (Exception unused) {
                            a1.c.f("Failed to upload logs: ", new IllegalStateException("No data/result and direct parse failed").getMessage());
                            ((i2.l) this.f2154g).h(Boolean.FALSE);
                            return;
                        }
                    }
                    oVar = o.f2158a;
                    synchronized (oVar) {
                        c0.f1036a.getClass();
                        Map mapE3 = c0.e();
                        mapE3.clear();
                        c0.o(mapE3);
                        break;
                    }
                }
                ((i2.l) this.f2154g).h(Boolean.TRUE);
                return;
            default:
                i2.p pVar = (i2.p) this.f2154g;
                ApiResponse apiResponse = this.f2152e;
                if (apiResponse.getResult() != null) {
                    pluginInfoEntity = (PluginInfoEntity) apiResponse.getResult();
                } else {
                    if (apiResponse.getData() == null) {
                        try {
                            x xVar = l1.f.f1368a;
                            pVar.f((PluginInfoEntity) l1.f.f1369b.b(this.f2153f, new q().f2779b), null);
                            return;
                        } catch (Exception unused2) {
                            pVar.f(null, new IllegalStateException("No data/result and direct parse failed"));
                            return;
                        }
                    }
                    pluginInfoEntity = (PluginInfoEntity) apiResponse.getData();
                }
                pVar.f(pluginInfoEntity, null);
                return;
        }
    }
}
