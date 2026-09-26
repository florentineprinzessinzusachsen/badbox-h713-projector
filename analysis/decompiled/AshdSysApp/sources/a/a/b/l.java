package a.a.b;

import com.android.sysapp.UpdateService;

/* JADX INFO: loaded from: classes.dex */
public class l implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ UpdateService f11a;

    public l(UpdateService updateService) {
        this.f11a = updateService;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        this.f11a.f.a(g.e + "/update.zip", this.f11a);
    }
}
