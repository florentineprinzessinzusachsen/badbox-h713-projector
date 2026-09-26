package a.a.b;

import com.android.sysapp.DialogActivity;

/* JADX INFO: loaded from: classes.dex */
public class b implements a.a.c.a.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a.a.c.a.a f0a;
    public final /* synthetic */ DialogActivity b;

    public b(DialogActivity dialogActivity, a.a.c.a.a aVar) {
        this.b = dialogActivity;
        this.f0a = aVar;
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0081 A[Catch: Exception -> 0x00d0, TryCatch #0 {Exception -> 0x00d0, blocks: (B:3:0x000e, B:5:0x0016, B:6:0x0027, B:16:0x0079, B:18:0x0081, B:19:0x0092, B:26:0x00ca, B:20:0x0096, B:22:0x009e, B:23:0x00b0, B:25:0x00b8, B:7:0x002b, B:9:0x0033, B:10:0x0045, B:12:0x004d, B:13:0x005f, B:15:0x0067), top: B:31:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:20:0x0096 A[Catch: Exception -> 0x00d0, TryCatch #0 {Exception -> 0x00d0, blocks: (B:3:0x000e, B:5:0x0016, B:6:0x0027, B:16:0x0079, B:18:0x0081, B:19:0x0092, B:26:0x00ca, B:20:0x0096, B:22:0x009e, B:23:0x00b0, B:25:0x00b8, B:7:0x002b, B:9:0x0033, B:10:0x0045, B:12:0x004d, B:13:0x005f, B:15:0x0067), top: B:31:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:22:0x009e A[Catch: Exception -> 0x00d0, TryCatch #0 {Exception -> 0x00d0, blocks: (B:3:0x000e, B:5:0x0016, B:6:0x0027, B:16:0x0079, B:18:0x0081, B:19:0x0092, B:26:0x00ca, B:20:0x0096, B:22:0x009e, B:23:0x00b0, B:25:0x00b8, B:7:0x002b, B:9:0x0033, B:10:0x0045, B:12:0x004d, B:13:0x005f, B:15:0x0067), top: B:31:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:23:0x00b0 A[Catch: Exception -> 0x00d0, TryCatch #0 {Exception -> 0x00d0, blocks: (B:3:0x000e, B:5:0x0016, B:6:0x0027, B:16:0x0079, B:18:0x0081, B:19:0x0092, B:26:0x00ca, B:20:0x0096, B:22:0x009e, B:23:0x00b0, B:25:0x00b8, B:7:0x002b, B:9:0x0033, B:10:0x0045, B:12:0x004d, B:13:0x005f, B:15:0x0067), top: B:31:0x000e }] */
    /* JADX WARN: Code duplicated, block: B:25:0x00b8 A[Catch: Exception -> 0x00d0, TryCatch #0 {Exception -> 0x00d0, blocks: (B:3:0x000e, B:5:0x0016, B:6:0x0027, B:16:0x0079, B:18:0x0081, B:19:0x0092, B:26:0x00ca, B:20:0x0096, B:22:0x009e, B:23:0x00b0, B:25:0x00b8, B:7:0x002b, B:9:0x0033, B:10:0x0045, B:12:0x004d, B:13:0x005f, B:15:0x0067), top: B:31:0x000e }] */
    @Override // a.a.c.a.a.b
    public void a() {
        a.a.c.a.a aVar;
        a.a.c.a.a aVar2;
        try {
            if (this.b.a("com.softwinner.TvdFileManager")) {
                this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.softwinner.TvdFileManager"));
                aVar = this.f0a;
            } else if (this.b.a("com.android.rockchip")) {
                this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.android.rockchip"));
                aVar = this.f0a;
            } else {
                if (!this.b.a("com.rockchips.mediacenter")) {
                    if (this.b.a("com.hisilicon.explorer")) {
                        this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.hisilicon.explorer"));
                        aVar = this.f0a;
                    }
                    if (this.b.a("com.android.rk")) {
                        this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.android.rk"));
                        aVar2 = this.f0a;
                    } else {
                        if (this.b.a("com.xiaobaifile.tv")) {
                            if (this.b.a("com.konka.multimedia")) {
                                this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.konka.multimedia"));
                                aVar2 = this.f0a;
                            }
                            this.b.finish();
                        }
                        this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.xiaobaifile.tv"));
                        aVar2 = this.f0a;
                    }
                    aVar2.dismiss();
                    this.b.finish();
                }
                this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.rockchips.mediacenter"));
                aVar = this.f0a;
            }
            aVar.dismiss();
            if (this.b.a("com.android.rk")) {
                this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.android.rk"));
                aVar2 = this.f0a;
            } else {
                if (this.b.a("com.xiaobaifile.tv")) {
                    if (this.b.a("com.konka.multimedia")) {
                        this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.konka.multimedia"));
                        aVar2 = this.f0a;
                    }
                    this.b.finish();
                }
                this.b.startActivity(this.b.getPackageManager().getLaunchIntentForPackage("com.xiaobaifile.tv"));
                aVar2 = this.f0a;
            }
            aVar2.dismiss();
            this.b.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // a.a.c.a.a.b
    public void b() {
        this.f0a.dismiss();
        this.b.finish();
    }
}
