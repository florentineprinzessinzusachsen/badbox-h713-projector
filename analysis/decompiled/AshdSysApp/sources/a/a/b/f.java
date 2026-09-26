package a.a.b;

import com.android.sysapp.OtaActivity;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class f implements a.a.c.a.a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ a.a.c.a.a f2a;
    public final /* synthetic */ OtaActivity b;

    public f(OtaActivity otaActivity, a.a.c.a.a aVar) {
        this.b = otaActivity;
        this.f2a = aVar;
    }

    @Override // a.a.c.a.a.b
    public void a() {
        this.f2a.dismiss();
        if (new File(this.b.f54a).exists()) {
            this.b.a();
        } else {
            this.b.finish();
        }
    }

    @Override // a.a.c.a.a.b
    public void b() {
        this.f2a.dismiss();
        this.b.finish();
    }
}
