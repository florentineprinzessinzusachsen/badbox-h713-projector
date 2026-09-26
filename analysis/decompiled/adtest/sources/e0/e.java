package e0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class e implements Runnable {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f607d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f608e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f609f;

    public /* synthetic */ e(int i4, Object obj, Object obj2) {
        this.f607d = i4;
        this.f608e = obj;
        this.f609f = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f607d) {
            case 0:
                f fVar = (f) this.f608e;
                l0.k kVar = (l0.k) this.f609f;
                synchronized (fVar.f622k) {
                    try {
                        ArrayList arrayList = fVar.f621j;
                        int size = arrayList.size();
                        int i4 = 0;
                        while (i4 < size) {
                            Object obj = arrayList.get(i4);
                            i4++;
                            ((b) obj).d(kVar, false);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
            case 1:
                ((f0.e) this.f608e).f862b.j((l) this.f609f, 3);
                return;
            case 2:
                List list = (List) this.f608e;
                j0.g gVar = (j0.g) this.f609f;
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((i0.a) it.next()).a(gVar.f1224e);
                }
                return;
            case 3:
                y yVar = (y) this.f608e;
                String string = ((UUID) this.f609f).toString();
                j2.i.d(string, "toString(...)");
                m0.g.a(yVar, string);
                return;
            default:
                Runnable runnable = (Runnable) this.f608e;
                m0.j jVar = (m0.j) this.f609f;
                try {
                    runnable.run();
                    return;
                } finally {
                    jVar.b();
                }
        }
    }
}
