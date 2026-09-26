package d0;

import android.content.Context;
import androidx.work.WorkerParameters;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f514a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WorkerParameters f515b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AtomicInteger f516c = new AtomicInteger(-256);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f517d;

    public z(Context context, WorkerParameters workerParameters) {
        this.f514a = context;
        this.f515b = workerParameters;
    }

    public abstract g.l a();

    public abstract g.l b();
}
