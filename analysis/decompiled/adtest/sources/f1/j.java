package f1;

import com.speed.adv.AdService;
import h1.c0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class j implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f875d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AdService f876e;

    public /* synthetic */ j(AdService adService, int i4) {
        this.f875d = i4;
        this.f876e = adService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f875d) {
            case 0:
                c0.f1036a.getClass();
                c0.l();
                this.f876e.f386i = true;
                l3.h.a0("[AdServiceNoAd] uploadCompanyStats: DAU-A 成功");
                this.f876e.c("DAU-A", true);
                break;
            default:
                c0.f1036a.getClass();
                c0.m();
                this.f876e.f387j = true;
                l3.h.a0("[AdServiceNoAd] uploadCustomStats: DAU-B 成功");
                this.f876e.c("DAU-B", true);
                break;
        }
    }
}
