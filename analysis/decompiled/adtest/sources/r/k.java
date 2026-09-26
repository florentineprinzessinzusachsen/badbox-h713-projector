package r;

import d0.l0;
import java.util.concurrent.locks.ReentrantLock;
import r2.x;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f1902a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final i2.a f1903b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ReentrantLock f1904c = new ReentrantLock();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1905d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1906e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f[] f1907f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final z2.h f1908g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final e.g f1909h;

    public k(int i4, i2.a aVar) {
        this.f1902a = i4;
        this.f1903b = aVar;
        this.f1907f = new f[i4];
        int i5 = z2.i.f2796a;
        this.f1908g = new z2.h(i4);
        e.g gVar = new e.g();
        if (i4 < 1) {
            throw new IllegalArgumentException("capacity must be >= 1");
        }
        if (i4 > 1073741824) {
            throw new IllegalArgumentException("capacity must be <= 2^30");
        }
        i4 = Integer.bitCount(i4) != 1 ? Integer.highestOneBit(i4 - 1) << 1 : i4;
        gVar.f576d = i4 - 1;
        gVar.f573a = new Object[i4];
        this.f1909h = gVar;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object a(a2.c cVar) {
        j jVar;
        int andDecrement;
        k kVar;
        if (cVar instanceof j) {
            jVar = (j) cVar;
            int i4 = jVar.f1901j;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                jVar.f1901j = i4 - Integer.MIN_VALUE;
            } else {
                jVar = new j(this, cVar);
            }
        } else {
            jVar = new j(this, cVar);
        }
        Object obj = jVar.f1899h;
        int i5 = jVar.f1901j;
        if (i5 == 0) {
            l0.M(obj);
            jVar.f1898g = this;
            jVar.f1901j = 1;
            z2.h hVar = this.f1908g;
            hVar.getClass();
            int i6 = hVar.f2794d;
            do {
                andDecrement = z2.g.f2793j.getAndDecrement(hVar);
            } while (andDecrement > i6);
            Object obj2 = u1.k.f2301a;
            z1.a aVar = z1.a.f2781d;
            if (andDecrement <= 0) {
                r2.i iVarL = x.l(z1.d.a(jVar));
                try {
                    if (!hVar.a(iVarL)) {
                        while (true) {
                            int andDecrement2 = z2.g.f2793j.getAndDecrement(hVar);
                            if (andDecrement2 <= i6) {
                                if (andDecrement2 > 0) {
                                    iVarL.m(obj2, hVar.f2795e);
                                    break;
                                }
                                if (hVar.a(iVarL)) {
                                    break;
                                }
                            }
                        }
                    }
                    Object objU = iVarL.u();
                    if (objU != aVar) {
                        objU = obj2;
                    }
                    if (objU == aVar) {
                        obj2 = objU;
                    }
                } catch (Throwable th) {
                    iVarL.C();
                    throw th;
                }
            }
            if (obj2 == aVar) {
                return aVar;
            }
            kVar = this;
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            kVar = jVar.f1898g;
            l0.M(obj);
        }
        try {
            ReentrantLock reentrantLock = kVar.f1904c;
            e.g gVar = kVar.f1909h;
            reentrantLock.lock();
            try {
                if (kVar.f1906e) {
                    l3.h.m0(21, "Connection pool is closed");
                    throw null;
                }
                if (gVar.f574b == gVar.f575c && kVar.f1905d < kVar.f1902a) {
                    f fVar = new f((w.a) kVar.f1903b.a());
                    f[] fVarArr = kVar.f1907f;
                    int i7 = kVar.f1905d;
                    kVar.f1905d = i7 + 1;
                    fVarArr[i7] = fVar;
                    gVar.a(fVar);
                }
                int i8 = gVar.f574b;
                if (i8 == gVar.f575c) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                Object[] objArr = gVar.f573a;
                Object obj3 = objArr[i8];
                objArr[i8] = null;
                gVar.f574b = gVar.f576d & (i8 + 1);
                f fVar2 = (f) obj3;
                reentrantLock.unlock();
                return fVar2;
            } catch (Throwable th2) {
                reentrantLock.unlock();
                throw th2;
            }
        } catch (Throwable th3) {
            kVar.f1908g.d();
            throw th3;
        }
    }

    public final void b() {
        ReentrantLock reentrantLock = this.f1904c;
        reentrantLock.lock();
        try {
            this.f1906e = true;
            for (f fVar : this.f1907f) {
                if (fVar != null) {
                    fVar.close();
                }
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void c(StringBuilder sb) {
        e.g gVar = this.f1909h;
        ReentrantLock reentrantLock = this.f1904c;
        reentrantLock.lock();
        try {
            w1.c cVar = new w1.c(10);
            int i4 = (gVar.f575c - gVar.f574b) & gVar.f576d;
            for (int i5 = 0; i5 < i4; i5++) {
                if (i5 >= 0) {
                    int i6 = gVar.f575c;
                    int i7 = gVar.f574b;
                    int i8 = gVar.f576d;
                    if (i5 < ((i6 - i7) & i8)) {
                        Object obj = gVar.f573a[(i7 + i5) & i8];
                        j2.i.b(obj);
                        cVar.add(obj);
                    }
                }
                throw new ArrayIndexOutOfBoundsException();
            }
            w1.c cVarD = l3.h.d(cVar);
            sb.append('\t' + toString() + " (");
            sb.append("capacity=" + this.f1902a + ", ");
            StringBuilder sb2 = new StringBuilder();
            sb2.append("permits=");
            z2.h hVar = this.f1908g;
            hVar.getClass();
            sb2.append(Math.max(z2.g.f2793j.get(hVar), 0));
            sb2.append(", ");
            sb.append(sb2.toString());
            sb.append("queue=(size=" + cVarD.a() + ")[" + v1.j.y0(cVarD, null, null, null, null, 63) + "], ");
            sb.append(")");
            sb.append('\n');
            f[] fVarArr = this.f1907f;
            int length = fVarArr.length;
            int i9 = 0;
            for (int i10 = 0; i10 < length; i10++) {
                f fVar = fVarArr[i10];
                i9++;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("\t\t[");
                sb3.append(i9);
                sb3.append("] - ");
                sb3.append(fVar != null ? fVar.f1883d.toString() : null);
                sb.append(sb3.toString());
                sb.append('\n');
                if (fVar != null) {
                    fVar.k(sb);
                }
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    public final void d(f fVar) {
        j2.i.e(fVar, "connection");
        ReentrantLock reentrantLock = this.f1904c;
        reentrantLock.lock();
        try {
            this.f1909h.a(fVar);
            reentrantLock.unlock();
            this.f1908g.d();
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }
}
