package p;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import r2.y0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g extends j2.h implements i2.l {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f1640k;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(int i4, Object obj, Class cls, String str, String str2, int i5, int i6, int i7) {
        super(i4, obj, cls, str, str2, i5, i6);
        this.f1640k = i7;
    }

    @Override // i2.l
    public final Object h(Object obj) {
        switch (this.f1640k) {
            case 0:
                j2.i.e((Set) obj, "p0");
                h hVar = (h) this.f1264e;
                ReentrantLock reentrantLock = hVar.f1648d;
                reentrantLock.lock();
                try {
                    List listG0 = v1.j.G0(hVar.f1647c.values());
                    reentrantLock.unlock();
                    Iterator it = listG0.iterator();
                    if (!it.hasNext()) {
                        return u1.k.f2301a;
                    }
                    ((m) it.next()).getClass();
                    throw null;
                } catch (Throwable th) {
                    reentrantLock.unlock();
                    throw th;
                }
            default:
                ((y0) this.f1264e).l((Throwable) obj);
                return u1.k.f2301a;
        }
    }
}
