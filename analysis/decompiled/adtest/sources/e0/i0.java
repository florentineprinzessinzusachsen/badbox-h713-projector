package e0;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import r2.x0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class i0 extends a2.i implements i2.p {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f632h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f633i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ k0 f634j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i0(k0 k0Var, y1.c cVar, int i4) {
        super(2, cVar);
        this.f632h = i4;
        this.f634j = k0Var;
    }

    @Override // i2.p
    public final Object f(Object obj, Object obj2) {
        r2.v vVar = (r2.v) obj;
        y1.c cVar = (y1.c) obj2;
        switch (this.f632h) {
            case 0:
                break;
        }
        return ((i0) i(vVar, cVar)).l(u1.k.f2301a);
    }

    @Override // a2.a
    public final y1.c i(Object obj, y1.c cVar) {
        switch (this.f632h) {
            case 0:
                return new i0(this.f634j, cVar, 0);
            default:
                return new i0(this.f634j, cVar, 1);
        }
    }

    @Override // a2.a
    public final Object l(Object obj) {
        final g0 d0Var;
        switch (this.f632h) {
            case 0:
                int i4 = this.f633i;
                if (i4 != 0) {
                    if (i4 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    d0.l0.M(obj);
                    return obj;
                }
                d0.l0.M(obj);
                this.f633i = 1;
                Object objA = k0.a(this.f634j, this);
                z1.a aVar = z1.a.f2781d;
                return objA == aVar ? aVar : objA;
            default:
                int i5 = this.f633i;
                final k0 k0Var = this.f634j;
                try {
                    if (i5 == 0) {
                        d0.l0.M(obj);
                        x0 x0Var = k0Var.f655m;
                        i0 i0Var = new i0(k0Var, null, 0);
                        this.f633i = 1;
                        obj = r2.x.w(x0Var, i0Var, this);
                        z1.a aVar2 = z1.a.f2781d;
                        if (obj == aVar2) {
                            return aVar2;
                        }
                    } else {
                        if (i5 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        d0.l0.M(obj);
                    }
                    d0Var = (g0) obj;
                    break;
                } catch (z e4) {
                    d0Var = new f0(e4.f702d);
                } catch (CancellationException unused) {
                    d0Var = new d0();
                } catch (Throwable th) {
                    d0.a0.e().d(m0.f662a, "Unexpected error in WorkerWrapper", th);
                    d0Var = new d0();
                }
                Object objN = k0Var.f650h.n(new Callable() { // from class: e0.h0
                    /* JADX WARN: Code duplicated, block: B:14:0x005b  */
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        k0 k0Var2 = k0Var;
                        l0.p pVar = k0Var2.f643a;
                        String str = k0Var2.f645c;
                        l0.t tVar = k0Var2.f651i;
                        g0 g0Var = d0Var;
                        boolean z3 = g0Var instanceof e0;
                        d0.k0 k0Var3 = d0.k0.f467d;
                        boolean z4 = true;
                        boolean z5 = false;
                        if (z3) {
                            d0.y yVar = ((e0) g0Var).f610a;
                            d0.k0 k0VarB = tVar.b(str);
                            l0.n nVarV = k0Var2.f650h.v();
                            nVarV.getClass();
                            l3.h.W(nVarV.f1327a, false, true, new l0.b(6, str));
                            if (k0VarB == null) {
                                z4 = false;
                            } else if (k0VarB == d0.k0.f468e) {
                                String str2 = k0Var2.f654l;
                                if (yVar instanceof d0.x) {
                                    String str3 = m0.f662a;
                                    d0.a0.e().f(str3, "Worker result SUCCESS for " + str2);
                                    if (pVar.c()) {
                                        k0Var2.c();
                                    } else {
                                        tVar.h(d0.k0.f469f, str);
                                        d0.j jVar = ((d0.x) yVar).f513a;
                                        j2.i.d(jVar, "getOutputData(...)");
                                        l3.h.W(tVar.f1361a, false, true, new h0.e(6, jVar, str));
                                        k0Var2.f648f.getClass();
                                        long jCurrentTimeMillis = System.currentTimeMillis();
                                        l0.d dVar = k0Var2.f652j;
                                        for (String str4 : dVar.a(str)) {
                                            if (tVar.b(str4) == d0.k0.f471h && ((Boolean) l3.h.W(dVar.f1307a, true, false, new l0.b(2, str4))).booleanValue()) {
                                                d0.a0.e().f(m0.f662a, "Setting status to enqueued for ".concat(str4));
                                                tVar.h(k0Var3, str4);
                                                tVar.g(str4, jCurrentTimeMillis);
                                            }
                                        }
                                    }
                                } else if (yVar instanceof d0.w) {
                                    String str5 = m0.f662a;
                                    d0.a0.e().f(str5, "Worker result RETRY for " + str2);
                                    k0Var2.b(-256);
                                } else {
                                    String str6 = m0.f662a;
                                    d0.a0.e().f(str6, "Worker result FAILURE for " + str2);
                                    if (pVar.c()) {
                                        k0Var2.c();
                                    } else {
                                        k0Var2.d(yVar);
                                    }
                                }
                                z4 = false;
                            } else if (k0VarB.a()) {
                                z4 = false;
                            } else {
                                k0Var2.b(-512);
                            }
                            z5 = z4;
                        } else if (g0Var instanceof d0) {
                            k0Var2.d(((d0) g0Var).f606a);
                        } else {
                            if (!(g0Var instanceof f0)) {
                                throw new a0.c();
                            }
                            int i6 = ((f0) g0Var).f623a;
                            if (j2.i.a(pVar.f1355y, Boolean.TRUE)) {
                                String str7 = m0.f662a;
                                d0.a0.e().a(str7, "Worker " + pVar.f1333c + " was interrupted. Backing off.");
                                k0Var2.b(i6);
                            } else {
                                d0.k0 k0VarB2 = tVar.b(str);
                                if (k0VarB2 == null || k0VarB2.a()) {
                                    String str8 = m0.f662a;
                                    d0.a0.e().a(str8, "Status for " + str + " is " + k0VarB2 + " ; not doing any work");
                                    z4 = false;
                                } else {
                                    String str9 = m0.f662a;
                                    d0.a0.e().a(str9, "Status for " + str + " is " + k0VarB2 + "; not doing any work and rescheduling for later execution");
                                    tVar.h(k0Var3, str);
                                    tVar.i(i6, str);
                                    tVar.e(str, -1L);
                                }
                            }
                            z5 = z4;
                        }
                        return Boolean.valueOf(z5);
                    }
                });
                j2.i.d(objN, "runInTransaction(...)");
                return objN;
        }
    }
}
