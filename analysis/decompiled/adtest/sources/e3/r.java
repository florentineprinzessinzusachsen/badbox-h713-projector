package e3;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d3.c f802b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final d3.b f803c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ConcurrentLinkedQueue f804d;

    public r(d3.e eVar) {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        j2.i.e(eVar, "taskRunner");
        j2.i.e(timeUnit, "timeUnit");
        this.f801a = timeUnit.toNanos(5L);
        this.f802b = eVar.d();
        this.f803c = new d3.b(this, b3.g.f349b + " ConnectionPool connection closer");
        this.f804d = new ConcurrentLinkedQueue();
    }

    public final int a(q qVar, long j4) {
        TimeZone timeZone = b3.g.f348a;
        ArrayList arrayList = qVar.f799p;
        int i4 = 0;
        while (i4 < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i4);
            if (reference.get() != null) {
                i4++;
            } else {
                String str = "A connection to " + qVar.f786c.f145a.f58h + " was leaked. Did you forget to close a response body?";
                k3.e eVar = k3.e.f1300a;
                k3.e.f1300a.k(((n) reference).f765a, str);
                arrayList.remove(i4);
                if (arrayList.isEmpty()) {
                    qVar.f800q = j4 - this.f801a;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }
}
