package s0;

import java.io.EOFException;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import v0.b1;
import v0.r0;
import v0.t0;

/* JADX INFO: compiled from: r8-map-id-01bd2f78e356d1793fe12b6cab4b4ddc2ec9524fab1d39c14fcb56e383ba4015 */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final i f2096t = i.f2087d;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final a f2097u = h.f2085d;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public static final v f2098v = z.f2137d;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public static final w f2099w = z.f2138e;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ThreadLocal f2100a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f2101b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final u0.c f2102c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final v0.l f2103d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f2104e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final u0.e f2105f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h f2106g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Map f2107h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f2108i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final i f2109j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f2110k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f2111l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f2112m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final List f2113n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final List f2114o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final z f2115p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final z f2116q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final List f2117r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f2118s;

    /* JADX WARN: Illegal instructions before constructor call */
    public n() {
        u0.e eVar = u0.e.f2245f;
        Map map = Collections.EMPTY_MAP;
        List list = Collections.EMPTY_LIST;
        this(eVar, f2097u, map, true, f2096t, true, 1, 2, 2, list, list, list, f2098v, f2099w, list);
    }

    public static void a(double d4) {
        if (Double.isNaN(d4) || Double.isInfinite(d4)) {
            throw new IllegalArgumentException(d4 + " is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
        }
    }

    public final Object b(String str, Type type) {
        z0.a aVar = new z0.a(type);
        Object obj = null;
        if (str == null) {
            return null;
        }
        a1.b bVar = new a1.b(new StringReader(str));
        bVar.i0(2);
        int i4 = bVar.f25r;
        boolean z3 = true;
        if (i4 == 2) {
            bVar.f25r = 1;
        }
        try {
            try {
                try {
                    bVar.f0();
                    z3 = false;
                    b0 b0VarC = c(aVar);
                    Class cls = aVar.f2778a;
                    Object objB = b0VarC.b(bVar);
                    Class clsM = u0.i.m(cls);
                    if (objB != null && !clsM.isInstance(objB)) {
                        throw new ClassCastException("Type adapter '" + b0VarC + "' returned wrong type; requested " + cls + " but got instance of " + objB.getClass() + "\nVerify that the adapter was registered for the correct type.");
                    }
                    bVar.i0(i4);
                    obj = objB;
                } catch (AssertionError e4) {
                    throw new AssertionError("AssertionError (GSON 2.13.2): " + e4.getMessage(), e4);
                } catch (IllegalStateException e5) {
                    throw new r(e5);
                }
            } catch (EOFException e6) {
                if (!z3) {
                    throw new r(e6);
                }
                bVar.i0(i4);
            } catch (IOException e7) {
                throw new r(e7);
            }
            if (obj != null) {
                try {
                    if (bVar.f0() != 10) {
                        throw new r("JSON document was not fully consumed.");
                    }
                } catch (a1.e e8) {
                    throw new r(e8);
                } catch (IOException e9) {
                    throw new r(e9);
                }
            }
            return obj;
        } catch (Throwable th) {
            bVar.i0(i4);
            throw th;
        }
    }

    public final b0 c(z0.a aVar) {
        boolean z3;
        ConcurrentHashMap concurrentHashMap = this.f2101b;
        b0 b0Var = (b0) concurrentHashMap.get(aVar);
        if (b0Var != null) {
            return b0Var;
        }
        ThreadLocal threadLocal = this.f2100a;
        Map map = (Map) threadLocal.get();
        if (map == null) {
            map = new HashMap();
            threadLocal.set(map);
            z3 = true;
        } else {
            b0 b0Var2 = (b0) map.get(aVar);
            if (b0Var2 != null) {
                return b0Var2;
            }
            z3 = false;
        }
        try {
            m mVar = new m();
            map.put(aVar, mVar);
            Iterator it = this.f2104e.iterator();
            b0 b0VarA = null;
            while (it.hasNext()) {
                b0VarA = ((c0) it.next()).a(this, aVar);
                if (b0VarA != null) {
                    if (mVar.f2095a != null) {
                        throw new AssertionError("Delegate is already set");
                    }
                    mVar.f2095a = b0VarA;
                    map.put(aVar, b0VarA);
                    break;
                }
            }
            if (z3) {
                threadLocal.remove();
            }
            if (b0VarA != null) {
                if (z3) {
                    concurrentHashMap.putAll(map);
                }
                return b0VarA;
            }
            throw new IllegalArgumentException("GSON (2.13.2) cannot handle " + aVar);
        } catch (Throwable th) {
            if (z3) {
                threadLocal.remove();
            }
            throw th;
        }
    }

    public final a1.d d(Writer writer) {
        a1.d dVar = new a1.d(writer);
        dVar.U(this.f2109j);
        dVar.f37l = this.f2108i;
        dVar.V(2);
        dVar.f39n = false;
        return dVar;
    }

    public final String e(Object obj) {
        if (obj == null) {
            return f(s.f2134d);
        }
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();
        try {
            h(obj, cls, d(new u0.s(sb)));
            return sb.toString();
        } catch (IOException e4) {
            throw new r(e4);
        }
    }

    public final String f(q qVar) {
        StringBuilder sb = new StringBuilder();
        try {
            g(d(new u0.s(sb)), qVar);
            return sb.toString();
        } catch (IOException e4) {
            throw new r(e4);
        }
    }

    public final void g(a1.d dVar, q qVar) {
        int i4 = dVar.f36k;
        boolean z3 = dVar.f37l;
        boolean z4 = dVar.f39n;
        dVar.f37l = this.f2108i;
        dVar.f39n = false;
        if (i4 == 2) {
            dVar.f36k = 1;
        }
        try {
            try {
                try {
                    b1.f2448z.getClass();
                    v0.m.e(dVar, qVar);
                    dVar.V(i4);
                    dVar.f37l = z3;
                    dVar.f39n = z4;
                } catch (IOException e4) {
                    throw new r(e4);
                }
            } catch (AssertionError e5) {
                throw new AssertionError("AssertionError (GSON 2.13.2): " + e5.getMessage(), e5);
            }
        } catch (Throwable th) {
            dVar.V(i4);
            dVar.f37l = z3;
            dVar.f39n = z4;
            throw th;
        }
    }

    public final void h(Object obj, Class cls, a1.d dVar) {
        b0 b0VarC = c(new z0.a(cls));
        int i4 = dVar.f36k;
        if (i4 == 2) {
            dVar.f36k = 1;
        }
        boolean z3 = dVar.f37l;
        boolean z4 = dVar.f39n;
        dVar.f37l = this.f2108i;
        dVar.f39n = false;
        try {
            try {
                b0VarC.c(dVar, obj);
                dVar.V(i4);
                dVar.f37l = z3;
                dVar.f39n = z4;
            } catch (IOException e4) {
                throw new r(e4);
            } catch (AssertionError e5) {
                throw new AssertionError("AssertionError (GSON 2.13.2): " + e5.getMessage(), e5);
            }
        } catch (Throwable th) {
            dVar.V(i4);
            dVar.f37l = z3;
            dVar.f39n = z4;
            throw th;
        }
    }

    public final String toString() {
        return "{serializeNulls:false,factories:" + this.f2104e + ",instanceCreators:" + this.f2102c + "}";
    }

    public n(u0.e eVar, h hVar, Map map, boolean z3, i iVar, boolean z4, int i4, int i5, int i6, List list, List list2, List list3, z zVar, z zVar2, List list4) {
        v0.o oVar;
        b0 kVar;
        v0.o oVar2;
        this.f2100a = new ThreadLocal();
        this.f2101b = new ConcurrentHashMap();
        this.f2105f = eVar;
        this.f2106g = hVar;
        this.f2107h = map;
        u0.c cVar = new u0.c(map, z4, list4);
        this.f2102c = cVar;
        this.f2108i = z3;
        this.f2109j = iVar;
        this.f2110k = z4;
        this.f2118s = i4;
        this.f2111l = i5;
        this.f2112m = i6;
        this.f2113n = list;
        this.f2114o = list2;
        this.f2115p = zVar;
        this.f2116q = zVar2;
        this.f2117r = list4;
        ArrayList arrayList = new ArrayList();
        arrayList.add(b1.A);
        if (zVar == z.f2137d) {
            oVar = v0.q.f2475c;
        } else {
            oVar = new v0.o(1, zVar);
        }
        arrayList.add(oVar);
        arrayList.add(eVar);
        arrayList.addAll(list3);
        arrayList.add(b1.f2438p);
        arrayList.add(b1.f2429g);
        arrayList.add(b1.f2426d);
        arrayList.add(b1.f2427e);
        arrayList.add(b1.f2428f);
        if (i4 == 1) {
            kVar = b1.f2433k;
        } else {
            kVar = new k();
        }
        arrayList.add(new t0(Long.TYPE, Long.class, kVar));
        arrayList.add(new t0(Double.TYPE, Double.class, new j(0)));
        arrayList.add(new t0(Float.TYPE, Float.class, new j(1)));
        if (zVar2 == z.f2138e) {
            oVar2 = v0.p.f2473b;
        } else {
            oVar2 = new v0.o(0, new v0.p(zVar2));
        }
        arrayList.add(oVar2);
        arrayList.add(b1.f2430h);
        arrayList.add(b1.f2431i);
        arrayList.add(new r0(AtomicLong.class, new l(kVar, 0).a(), 0));
        int i7 = 0;
        arrayList.add(new r0(AtomicLongArray.class, new l(kVar, 1).a(), i7));
        arrayList.add(b1.f2432j);
        arrayList.add(b1.f2434l);
        arrayList.add(b1.f2439q);
        arrayList.add(b1.f2440r);
        arrayList.add(new r0(BigDecimal.class, b1.f2435m, i7));
        arrayList.add(new r0(BigInteger.class, b1.f2436n, i7));
        arrayList.add(new r0(u0.k.class, b1.f2437o, i7));
        arrayList.add(b1.f2441s);
        arrayList.add(b1.f2442t);
        arrayList.add(b1.f2444v);
        arrayList.add(b1.f2445w);
        arrayList.add(b1.f2447y);
        arrayList.add(b1.f2443u);
        arrayList.add(b1.f2424b);
        arrayList.add(v0.h.f2456c);
        arrayList.add(b1.f2446x);
        if (y0.f.f2716a) {
            arrayList.add(y0.f.f2720e);
            arrayList.add(y0.f.f2719d);
            arrayList.add(y0.f.f2721f);
        }
        arrayList.add(v0.b.f2420c);
        arrayList.add(b1.f2423a);
        arrayList.add(new v0.d(cVar, 0));
        arrayList.add(new v0.d(cVar, 1));
        v0.l lVar = new v0.l(cVar);
        this.f2103d = lVar;
        arrayList.add(lVar);
        arrayList.add(b1.B);
        arrayList.add(new v0.w(cVar, hVar, eVar, lVar, list4));
        this.f2104e = Collections.unmodifiableList(arrayList);
    }
}
