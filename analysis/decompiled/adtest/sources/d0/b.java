package d0;

import android.os.Build;
import java.util.concurrent.ExecutorService;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ExecutorService f404a = l0.b(false);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final y2.e f405b = r2.e0.f1974a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ExecutorService f406c = l0.b(true);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final l f407d = new l();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final l f408e = l.f474a;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final l f409f = l.f475b;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final a3.h f410g = new a3.h(3, (byte) 0);

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f411h = 4;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f412i = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f413j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f414k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f415l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final l f416m;

    public b(l lVar) {
        this.f414k = Build.VERSION.SDK_INT == 23 ? 10 : 20;
        this.f413j = 8;
        this.f415l = true;
        this.f416m = new l();
    }
}
