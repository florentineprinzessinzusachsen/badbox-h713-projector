package f1;

import com.speed.adv.AdService;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements i2.l {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f866d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f867e;

    public /* synthetic */ c(int i4, Object obj) {
        this.f866d = i4;
        this.f867e = obj;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        int i4 = this.f866d;
        u1.k kVar = u1.k.f2301a;
        Object obj2 = this.f867e;
        switch (i4) {
            case 0:
                AdService adService = (AdService) obj2;
                boolean zBooleanValue = ((Boolean) obj).booleanValue();
                int i5 = AdService.f377l;
                if (!zBooleanValue) {
                    l3.h.a0("[AdServiceNoAd] 日志上传失败，等待下一轮");
                    t1.o.f2158a.a("log_upload_failed", "日志上传失败，等待下一轮");
                }
                adService.c("log_upload", zBooleanValue);
                return kVar;
            case 1:
                x.a aVar = (x.a) obj;
                j2.i.e(aVar, "db");
                ((p.p) obj2).f1689g = aVar;
                return kVar;
            case 2:
                return ((p2.g) obj2).b(((Integer) obj).intValue());
            case 3:
                return obj == ((v1.a) obj2) ? "(this Collection)" : String.valueOf(obj);
            default:
                ((z2.c) obj2).b(null);
                return kVar;
        }
    }

    public /* synthetic */ c(z2.c cVar, z2.b bVar) {
        this.f866d = 4;
        this.f867e = cVar;
    }
}
