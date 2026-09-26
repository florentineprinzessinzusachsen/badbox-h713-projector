package u0;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import s0.b0;
import s0.c0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class d extends b0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile b0 f2239a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ boolean f2240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ boolean f2241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ s0.n f2242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ z0.a f2243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ e f2244f;

    public d(e eVar, boolean z3, boolean z4, s0.n nVar, z0.a aVar) {
        this.f2244f = eVar;
        this.f2240b = z3;
        this.f2241c = z4;
        this.f2242d = nVar;
        this.f2243e = aVar;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0061  */
    @Override // s0.b0
    public final Object b(a1.b bVar) throws IOException {
        if (this.f2240b) {
            bVar.m0();
            return null;
        }
        b0 b0VarC = this.f2239a;
        if (b0VarC == null) {
            s0.n nVar = this.f2242d;
            c0 c0Var = this.f2244f;
            z0.a aVar = this.f2243e;
            v0.l lVar = nVar.f2103d;
            lVar.getClass();
            ConcurrentHashMap concurrentHashMap = lVar.f2465e;
            if (c0Var == v0.l.f2463f) {
                c0Var = lVar;
            } else {
                Class cls = aVar.f2778a;
                c0 c0Var2 = (c0) concurrentHashMap.get(cls);
                if (c0Var2 == null) {
                    t0.a aVar2 = (t0.a) cls.getAnnotation(t0.a.class);
                    if (aVar2 != null) {
                        Class clsValue = aVar2.value();
                        if (c0.class.isAssignableFrom(clsValue)) {
                            c0 c0Var3 = (c0) lVar.f2464d.b(new z0.a(clsValue), true).a();
                            c0 c0Var4 = (c0) concurrentHashMap.putIfAbsent(cls, c0Var3);
                            if (c0Var4 != null) {
                                c0Var3 = c0Var4;
                            }
                            if (c0Var3 == c0Var) {
                                c0Var = lVar;
                            }
                        }
                    }
                } else if (c0Var2 == c0Var) {
                    c0Var = lVar;
                }
            }
            Iterator it = nVar.f2104e.iterator();
            boolean z3 = false;
            while (true) {
                if (!it.hasNext()) {
                    if (!z3) {
                        b0VarC = nVar.c(aVar);
                        break;
                    }
                    throw new IllegalArgumentException("GSON cannot serialize or deserialize " + aVar);
                }
                c0 c0Var5 = (c0) it.next();
                if (z3) {
                    b0 b0VarA = c0Var5.a(nVar, aVar);
                    if (b0VarA != null) {
                        b0VarC = b0VarA;
                        break;
                    }
                } else if (c0Var5 == c0Var) {
                    z3 = true;
                }
            }
            this.f2239a = b0VarC;
        }
        return b0VarC.b(bVar);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0060  */
    @Override // s0.b0
    public final void c(a1.d dVar, Object obj) throws IOException {
        if (this.f2241c) {
            dVar.S();
            return;
        }
        b0 b0VarC = this.f2239a;
        if (b0VarC == null) {
            s0.n nVar = this.f2242d;
            c0 c0Var = this.f2244f;
            z0.a aVar = this.f2243e;
            v0.l lVar = nVar.f2103d;
            lVar.getClass();
            ConcurrentHashMap concurrentHashMap = lVar.f2465e;
            if (c0Var == v0.l.f2463f) {
                c0Var = lVar;
            } else {
                Class cls = aVar.f2778a;
                c0 c0Var2 = (c0) concurrentHashMap.get(cls);
                if (c0Var2 == null) {
                    t0.a aVar2 = (t0.a) cls.getAnnotation(t0.a.class);
                    if (aVar2 != null) {
                        Class clsValue = aVar2.value();
                        if (c0.class.isAssignableFrom(clsValue)) {
                            c0 c0Var3 = (c0) lVar.f2464d.b(new z0.a(clsValue), true).a();
                            c0 c0Var4 = (c0) concurrentHashMap.putIfAbsent(cls, c0Var3);
                            if (c0Var4 != null) {
                                c0Var3 = c0Var4;
                            }
                            if (c0Var3 == c0Var) {
                                c0Var = lVar;
                            }
                        }
                    }
                } else if (c0Var2 == c0Var) {
                    c0Var = lVar;
                }
            }
            Iterator it = nVar.f2104e.iterator();
            boolean z3 = false;
            while (true) {
                if (!it.hasNext()) {
                    if (!z3) {
                        b0VarC = nVar.c(aVar);
                        break;
                    } else {
                        throw new IllegalArgumentException("GSON cannot serialize or deserialize " + aVar);
                    }
                }
                c0 c0Var5 = (c0) it.next();
                if (z3) {
                    b0 b0VarA = c0Var5.a(nVar, aVar);
                    if (b0VarA != null) {
                        b0VarC = b0VarA;
                        break;
                    }
                } else if (c0Var5 == c0Var) {
                    z3 = true;
                }
            }
            this.f2239a = b0VarC;
        }
        b0VarC.c(dVar, obj);
    }
}
