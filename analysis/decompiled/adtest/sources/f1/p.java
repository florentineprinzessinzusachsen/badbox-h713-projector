package f1;

import a3.d0;
import com.speed.adv.AdService;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class p implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f887d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ d0 f888e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AdService f889f;

    public /* synthetic */ p(d0 d0Var, AdService adService, int i4) {
        this.f887d = i4;
        this.f888e = d0Var;
        this.f889f = adService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f887d;
        AdService adService = this.f889f;
        d0 d0Var = this.f888e;
        switch (i4) {
            case 0:
                a1.c.f("[AdServiceNoAd] uploadCompanyStats: DAU-A 失败 ", new IOException("Unexpected code " + d0Var).getMessage());
                int i5 = AdService.f377l;
                adService.c("DAU-A", false);
                break;
            default:
                a1.c.f("[AdServiceNoAd] uploadCustomStats: DAU-B 失败 ", new IOException("Unexpected code " + d0Var).getMessage());
                int i6 = AdService.f377l;
                adService.c("DAU-B", false);
                break;
        }
    }
}
