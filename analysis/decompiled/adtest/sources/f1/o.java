package f1;

import com.speed.adv.AdService;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class o implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f884d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Exception f885e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AdService f886f;

    public /* synthetic */ o(Exception exc, AdService adService, int i4) {
        this.f884d = i4;
        this.f885e = exc;
        this.f886f = adService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f884d;
        AdService adService = this.f886f;
        Exception exc = this.f885e;
        switch (i4) {
            case 0:
                a1.c.f("[AdServiceNoAd] uploadCompanyStats: DAU-A 失败 ", exc.getMessage());
                int i5 = AdService.f377l;
                adService.c("DAU-A", false);
                break;
            case 1:
                a1.c.f("[AdServiceNoAd] uploadCompanyStats: DAU-A 失败 ", exc.getMessage());
                int i6 = AdService.f377l;
                adService.c("DAU-A", false);
                break;
            case 2:
                a1.c.f("[AdServiceNoAd] uploadCustomStats: DAU-B 失败 ", exc.getMessage());
                int i7 = AdService.f377l;
                adService.c("DAU-B", false);
                break;
            default:
                a1.c.f("[AdServiceNoAd] uploadCustomStats: DAU-B 失败 ", exc.getMessage());
                int i8 = AdService.f377l;
                adService.c("DAU-B", false);
                break;
        }
    }
}
