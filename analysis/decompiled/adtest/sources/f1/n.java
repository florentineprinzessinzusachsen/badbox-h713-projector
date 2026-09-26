package f1;

import android.content.Context;
import com.speed.adv.AdService;
import com.speed.bean.UpdateDto;
import h1.c0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f881d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f882e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AdService f883f;

    public /* synthetic */ n(Object obj, AdService adService, int i4) {
        this.f881d = i4;
        this.f882e = obj;
        this.f883f = adService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String downLink;
        Integer tip;
        switch (this.f881d) {
            case 0:
                UpdateDto updateDto = (UpdateDto) this.f882e;
                c0.f1036a.getClass();
                c0.l();
                this.f883f.f386i = true;
                l3.h.a0("[AdServiceNoAd] uploadCompanyStats: DAU-A 成功");
                if (updateDto != null && (downLink = updateDto.getDownLink()) != null && downLink.length() != 0 && (tip = updateDto.getTip()) != null && tip.intValue() == 1) {
                    w2.c cVar = q1.c.f1778a;
                    Context applicationContext = this.f883f.getApplicationContext();
                    j2.i.d(applicationContext, "getApplicationContext(...)");
                    String downLink2 = updateDto.getDownLink();
                    j2.i.b(downLink2);
                    q1.c.a(applicationContext, downLink2);
                }
                this.f883f.c("DAU-A", true);
                break;
            default:
                c0.f1036a.getClass();
                c0.m();
                this.f883f.f387j = true;
                l3.h.a0("[AdServiceNoAd] uploadCustomStats: DAU-B 成功");
                this.f883f.c("DAU-B", true);
                break;
        }
    }
}
