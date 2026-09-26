package s;

import a2.i;
import d0.l0;
import i2.l;
import i2.p;
import l3.h;
import p.x;
import p.y;
import r.m;
import r.t;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d implements y, t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a f2073a;

    public d(a aVar) {
        this.f2073a = aVar;
    }

    @Override // p.y
    public final Object a(x xVar, p pVar, i iVar) {
        return e(xVar, pVar, iVar);
    }

    @Override // p.n
    public final Object b(String str, l lVar, a2.c cVar) {
        g gVarP = this.f2073a.P(str);
        try {
            Object objH = lVar.h(gVarP);
            h.k(gVarP, null);
            return objH;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                h.k(gVarP, th);
                throw th2;
            }
        }
    }

    @Override // p.y
    public final Object c(i iVar) {
        return Boolean.valueOf(this.f2073a.f2066d.N());
    }

    @Override // r.t
    public final w.a d() {
        return this.f2073a;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x0090  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object e(x xVar, p pVar, a2.c cVar) throws Throwable {
        c cVar2;
        Throwable th;
        x.a aVar;
        d dVar;
        if (cVar instanceof c) {
            cVar2 = (c) cVar;
            int i4 = cVar2.f2072k;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                cVar2.f2072k = i4 - Integer.MIN_VALUE;
            } else {
                cVar2 = new c(this, cVar);
            }
        } else {
            cVar2 = new c(this, cVar);
        }
        Object obj = cVar2.f2070i;
        int i5 = cVar2.f2072k;
        if (i5 == 0) {
            l0.M(obj);
            x.a aVar2 = this.f2073a.f2066d;
            aVar2.N();
            int iOrdinal = xVar.ordinal();
            if (iOrdinal == 0) {
                aVar2.I();
            } else if (iOrdinal == 1) {
                aVar2.B();
            } else {
                if (iOrdinal != 2) {
                    throw new a0.c();
                }
                aVar2.i();
            }
            try {
                Object mVar = new m(1, this);
                cVar2.f2068g = this;
                cVar2.f2069h = aVar2;
                cVar2.f2072k = 1;
                Object objF = pVar.f(mVar, cVar2);
                Object obj2 = z1.a.f2781d;
                if (objF == obj2) {
                    return obj2;
                }
                obj = objF;
                aVar = aVar2;
                dVar = this;
            } catch (Throwable th2) {
                th = th2;
                aVar = aVar2;
                dVar = this;
                aVar.h();
                if (!aVar.N()) {
                    dVar.getClass();
                }
                throw th;
            }
        } else {
            if (i5 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            aVar = cVar2.f2069h;
            dVar = cVar2.f2068g;
            try {
                l0.M(obj);
            } catch (Throwable th3) {
                th = th3;
                aVar.h();
                if (!aVar.N()) {
                    dVar.getClass();
                }
                throw th;
            }
        }
        aVar.y();
        aVar.h();
        if (!aVar.N()) {
            dVar.getClass();
        }
        return obj;
    }
}
