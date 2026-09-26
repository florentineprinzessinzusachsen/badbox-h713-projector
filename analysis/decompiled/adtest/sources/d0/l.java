package d0;

import android.content.Context;
import androidx.work.WorkerParameters;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final l f474a = new l();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final l f475b = new l();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final f0 f476c = new f0(1);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final f0 f477d = new f0(0);

    public l() {
        List list = Collections.EMPTY_LIST;
    }

    public z a(Context context, String str, WorkerParameters workerParameters) {
        j2.i.e(context, "appContext");
        j2.i.e(str, "workerClassName");
        j2.i.e(workerParameters, "workerParameters");
        try {
            Class<? extends U> clsAsSubclass = Class.forName(str).asSubclass(z.class);
            j2.i.b(clsAsSubclass);
            try {
                Object objNewInstance = clsAsSubclass.getDeclaredConstructor(Context.class, WorkerParameters.class).newInstance(context, workerParameters);
                j2.i.b(objNewInstance);
                z zVar = (z) objNewInstance;
                if (!zVar.f517d) {
                    return zVar;
                }
                throw new IllegalStateException("WorkerFactory (" + getClass().getName() + ") returned an instance of a ListenableWorker (" + str + ") which has already been invoked. createWorker() must always return a new instance of a ListenableWorker.");
            } catch (Throwable th) {
                a0.e().d(o0.f493a, "Could not instantiate ".concat(str), th);
                throw th;
            }
        } catch (Throwable th2) {
            a0.e().d(o0.f493a, "Invalid class: ".concat(str), th2);
            throw th2;
        }
    }
}
