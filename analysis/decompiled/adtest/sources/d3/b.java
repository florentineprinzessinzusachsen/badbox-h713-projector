package d3;

import b3.g;
import e3.q;
import e3.r;
import j2.i;
import java.util.Iterator;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class b extends a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f531e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f532f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(r rVar, String str) {
        super(str);
        this.f532f = rVar;
    }

    @Override // d3.a
    public final long a() {
        q qVar;
        switch (this.f531e) {
            case 0:
                ((i2.a) this.f532f).a();
                return -1L;
            default:
                r rVar = (r) this.f532f;
                long jNanoTime = System.nanoTime();
                long j4 = (jNanoTime - rVar.f801a) + 1;
                Iterator it = rVar.f804d.iterator();
                i.d(it, "iterator(...)");
                long j5 = Long.MAX_VALUE;
                int i4 = 0;
                int i5 = 0;
                q qVar2 = null;
                q qVar3 = null;
                while (it.hasNext()) {
                    q qVar4 = (q) it.next();
                    i.b(qVar4);
                    synchronized (qVar4) {
                        if (rVar.a(qVar4, jNanoTime) > 0) {
                            i5++;
                        } else {
                            long j6 = j5;
                            long j7 = qVar4.f800q;
                            if (j7 < j4) {
                                j4 = j7;
                                qVar2 = qVar4;
                            }
                            i4++;
                            if (j7 < j6) {
                                j5 = j7;
                                qVar3 = qVar4;
                            } else {
                                j5 = j6;
                            }
                        }
                    }
                }
                long j8 = j5;
                if (qVar2 != null) {
                    qVar = qVar2;
                } else if (i4 > 5) {
                    qVar = qVar3;
                    j4 = j8;
                } else {
                    j4 = -1;
                    qVar = null;
                }
                if (qVar == null) {
                    if (qVar3 != null) {
                        return (j8 + rVar.f801a) - jNanoTime;
                    }
                    if (i5 > 0) {
                        return rVar.f801a;
                    }
                    return -1L;
                }
                synchronized (qVar) {
                    if (qVar.f799p.isEmpty() && qVar.f800q == j4) {
                        qVar.f793j = true;
                        rVar.f804d.remove(qVar);
                        g.c(qVar.f788e);
                        if (!rVar.f804d.isEmpty()) {
                            return 0L;
                        }
                        rVar.f802b.a();
                        return 0L;
                    }
                    return 0L;
                }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b(String str, i2.a aVar) {
        super(str);
        this.f532f = aVar;
    }
}
