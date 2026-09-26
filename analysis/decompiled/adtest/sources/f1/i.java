package f1;

import com.speed.adv.AdService;
import java.io.IOException;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f872d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ IOException f873e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AdService f874f;

    public /* synthetic */ i(IOException iOException, AdService adService, int i4) {
        this.f872d = i4;
        this.f873e = iOException;
        this.f874f = adService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i4 = this.f872d;
        AdService adService = this.f874f;
        IOException iOException = this.f873e;
        switch (i4) {
            case 0:
                a1.c.f("[AdServiceNoAd] uploadCompanyStats: DAU-A 失败 ", iOException.getMessage());
                int i5 = AdService.f377l;
                adService.c("DAU-A", false);
                break;
            default:
                a1.c.f("[AdServiceNoAd] uploadCustomStats: DAU-B 失败 ", iOException.getMessage());
                int i6 = AdService.f377l;
                adService.c("DAU-B", false);
                break;
        }
    }
}
