package f1;

import com.speed.adv.AdService;
import d0.a0;
import d0.l0;
import h1.c0;
import java.net.InetAddress;
import r2.v;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class e extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f868h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f869i;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ e(Object obj, y1.c cVar, int i4) {
        super(2, cVar);
        this.f868h = i4;
        this.f869i = obj;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        switch (this.f868h) {
            case 0:
                e eVar = (e) i((v) obj, (y1.c) obj2);
                u1.k kVar = u1.k.f2301a;
                eVar.l(kVar);
                return kVar;
            case 1:
                return ((e) i((v) obj, (y1.c) obj2)).l(u1.k.f2301a);
            default:
                e eVar2 = (e) i((h0.c) obj, (y1.c) obj2);
                u1.k kVar2 = u1.k.f2301a;
                eVar2.l(kVar2);
                return kVar2;
        }
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f868h) {
            case 0:
                return new e((AdService) this.f869i, cVar, 0);
            case 1:
                return new e((String) this.f869i, cVar, 1);
            default:
                return new e((l0.p) this.f869i, cVar, 2);
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        int i4 = this.f868h;
        u1.k kVar = u1.k.f2301a;
        Object obj2 = this.f869i;
        switch (i4) {
            case 0:
                AdService adService = (AdService) obj2;
                l0.M(obj);
                try {
                    c0.f1036a.getClass();
                    if (((Boolean) c0.f1059x.a(c0.f1037b[18])).booleanValue()) {
                        s1.b.a();
                    } else {
                        l3.h.a0("[AdServiceNoAd] 未满足条件，不允许获取随机域名");
                    }
                    int i5 = AdService.f377l;
                    adService.c("random_domain", true);
                    break;
                } catch (Exception e4) {
                    a1.c.f("[AdServiceNoAd] 随机域名任务异常: ", e4.getMessage());
                    int i6 = AdService.f377l;
                    adService.c("random_domain", false);
                }
                return kVar;
            case 1:
                l0.M(obj);
                String hostAddress = InetAddress.getByName((String) obj2).getHostAddress();
                j2.i.d(hostAddress, "getHostAddress(...)");
                return Boolean.valueOf(hostAddress.length() > 0);
            default:
                l0.M(obj);
                String str = o0.g.f1556a;
                a0.e().a(str, "Constraints changed for " + ((l0.p) obj2));
                return kVar;
        }
    }
}
