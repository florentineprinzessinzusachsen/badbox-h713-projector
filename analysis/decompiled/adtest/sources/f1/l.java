package f1;

import android.content.Context;
import com.speed.adv.AdService;
import com.speed.bean.UpdateDto;
import com.speed.net.ApiResponse;
import h1.c0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f877d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ApiResponse f878e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ String f879f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ AdService f880g;

    public /* synthetic */ l(ApiResponse apiResponse, String str, AdService adService, int i4) {
        this.f877d = i4;
        this.f878e = apiResponse;
        this.f879f = str;
        this.f880g = adService;
    }

    /* JADX WARN: Code duplicated, block: B:29:0x00b7 A[PHI: r0
      0x00b7: PHI (r0v17 com.speed.bean.UpdateDto) = (r0v15 com.speed.bean.UpdateDto), (r0v21 com.speed.bean.UpdateDto) binds: [B:44:0x010c, B:28:0x00b5] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // java.lang.Runnable
    public final void run() {
        String downLink;
        Integer tip;
        UpdateDto updateDto;
        String downLink2;
        Integer tip2;
        String downLink3;
        Integer tip3;
        switch (this.f877d) {
            case 0:
                if (this.f878e.getResult() != null) {
                    updateDto = (UpdateDto) this.f878e.getResult();
                    c0.f1036a.getClass();
                    c0.l();
                    this.f880g.f386i = true;
                    l3.h.a0("[AdServiceNoAd] uploadCompanyStats: DAU-A 成功");
                    if (updateDto != null && (downLink3 = updateDto.getDownLink()) != null && downLink3.length() != 0 && (tip3 = updateDto.getTip()) != null && tip3.intValue() == 1) {
                        w2.c cVar = q1.c.f1778a;
                        Context applicationContext = this.f880g.getApplicationContext();
                        j2.i.d(applicationContext, "getApplicationContext(...)");
                        String downLink4 = updateDto.getDownLink();
                        j2.i.b(downLink4);
                        q1.c.a(applicationContext, downLink4);
                    }
                } else if (this.f878e.getData() != null) {
                    updateDto = (UpdateDto) this.f878e.getData();
                    c0.f1036a.getClass();
                    c0.l();
                    this.f880g.f386i = true;
                    l3.h.a0("[AdServiceNoAd] uploadCompanyStats: DAU-A 成功");
                    if (updateDto != null && (downLink2 = updateDto.getDownLink()) != null && downLink2.length() != 0 && (tip2 = updateDto.getTip()) != null && tip2.intValue() == 1) {
                        w2.c cVar2 = q1.c.f1778a;
                        Context applicationContext2 = this.f880g.getApplicationContext();
                        j2.i.d(applicationContext2, "getApplicationContext(...)");
                        String downLink5 = updateDto.getDownLink();
                        j2.i.b(downLink5);
                        q1.c.a(applicationContext2, downLink5);
                    }
                } else {
                    try {
                        UpdateDto updateDto2 = (UpdateDto) l1.f.f1369b.b(this.f879f, new k().f2779b);
                        c0.f1036a.getClass();
                        c0.l();
                        this.f880g.f386i = true;
                        l3.h.a0("[AdServiceNoAd] uploadCompanyStats: DAU-A 成功");
                        if (updateDto2 != null && (downLink = updateDto2.getDownLink()) != null && downLink.length() != 0 && (tip = updateDto2.getTip()) != null && tip.intValue() == 1) {
                            w2.c cVar3 = q1.c.f1778a;
                            Context applicationContext3 = this.f880g.getApplicationContext();
                            j2.i.d(applicationContext3, "getApplicationContext(...)");
                            String downLink6 = updateDto2.getDownLink();
                            j2.i.b(downLink6);
                            q1.c.a(applicationContext3, downLink6);
                        }
                        this.f880g.c("DAU-A", true);
                    } catch (Exception unused) {
                        a1.c.f("[AdServiceNoAd] uploadCompanyStats: DAU-A 失败 ", new IllegalStateException("No data/result and direct parse failed").getMessage());
                        AdService adService = this.f880g;
                        int i4 = AdService.f377l;
                        adService.c("DAU-A", false);
                        return;
                    }
                }
                this.f880g.c("DAU-A", true);
                break;
            default:
                if (this.f878e.getResult() != null) {
                    this.f878e.getResult();
                } else if (this.f878e.getData() != null) {
                    this.f878e.getData();
                } else {
                    try {
                        l1.f.f1369b.b(this.f879f, new r().f2779b);
                        c0.f1036a.getClass();
                        c0.m();
                        this.f880g.f387j = true;
                        l3.h.a0("[AdServiceNoAd] uploadCustomStats: DAU-B 成功");
                        this.f880g.c("DAU-B", true);
                    } catch (Exception unused2) {
                        a1.c.f("[AdServiceNoAd] uploadCustomStats: DAU-B 失败 ", new IllegalStateException("No data/result and direct parse failed").getMessage());
                        AdService adService2 = this.f880g;
                        int i5 = AdService.f377l;
                        adService2.c("DAU-B", false);
                    }
                }
                c0.f1036a.getClass();
                c0.m();
                this.f880g.f387j = true;
                l3.h.a0("[AdServiceNoAd] uploadCustomStats: DAU-B 成功");
                this.f880g.c("DAU-B", true);
                break;
        }
    }
}
